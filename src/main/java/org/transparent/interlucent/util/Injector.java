package org.transparent.interlucent.util;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import org.transparent.eureka.impl.builder.FieldBuilder;
import org.transparent.eureka.impl.builder.MethodBuilder;

public abstract class Injector<T extends JCTree, E extends JCTree> {
    protected final T tree;

    protected Injector(T tree) {
        this.tree = tree;
    }

    public abstract Injector<T, E> add(E tree);

    public T tree() {
        return tree;
    }

    public static class MemberInjector extends Injector<JCClassDecl, JCTree> {
        public MemberInjector(JCClassDecl tree) {
            super(tree);
        }

        public MemberInjector add(MethodBuilder builder) {
            return add(builder.build());
        }

        public MemberInjector add(FieldBuilder builder) {
            return add(builder.build());
        }

        @Override
        public MemberInjector add(JCTree tree) {
            this.tree.defs = this.tree.defs
                    .append(tree);
            return this;
        }
    }

    public static class StatementInjector extends Injector<JCMethodDecl, JCStatement> {
        public StatementInjector(JCMethodDecl tree) {
            super(tree);
        }

        @Override
        public StatementInjector add(JCStatement statement) {
            this.tree.body.stats = this.tree.body.stats
                    .append(statement);
            return this;
        }
    }
}
