package org.transparent.interlucent.util;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import org.transparent.eureka.impl.builder.FieldBuilder;
import org.transparent.eureka.impl.builder.MethodBuilder;

import java.util.Collection;

public abstract class Injector<T extends JCTree, E extends JCTree> {
    protected final T tree;

    protected Injector(T tree) {
        this.tree = tree;
    }

    public abstract Injector<T, E> add(E tree);

    public abstract Injector<T, E> add(Collection<E> trees);

    public T tree() {
        return tree;
    }

    public static class MemberInjector extends Injector<JCClassDecl, JCTree> {
        private Class<?> filterClass;

        public MemberInjector(JCClassDecl tree) {
            super(tree);
        }

        public MemberInjector filter(Class<?> filterClass) {
            this.filterClass = filterClass;
            return this;
        }

        public MemberInjector add(MethodBuilder builder) {
            return add(builder.build());
        }

        public MemberInjector add(FieldBuilder builder) {
            return add(builder.build());
        }

        @Override
        public MemberInjector add(JCTree tree) {
            if (filterClass != null
                    && !filterClass.isInstance(tree))
                return this;
            this.tree.defs = this.tree.defs
                    .append(tree);
            return this;
        }

        @Override
        public MemberInjector add(Collection<JCTree> trees) {
            trees.forEach(this::add);
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

        @Override
        public Injector<JCMethodDecl, JCStatement> add(Collection<JCStatement> trees) {
            trees.forEach(this::add);
            return this;
        }
    }
}
