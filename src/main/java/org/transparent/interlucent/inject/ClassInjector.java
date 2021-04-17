package org.transparent.interlucent.inject;

import com.sun.tools.javac.tree.JCTree;
import org.transparent.eureka.api.builder.Builder;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.sun.tools.javac.tree.JCTree.*;

public class ClassInjector {
    private final JCClassDecl tree;

    public ClassInjector(JCClassDecl tree) {
        this.tree = tree;
    }

    public ClassInjector add(Builder<? extends JCTree> builder) {
        return add(builder.build());
    }

    public ClassInjector add(Collection<? extends JCTree> c) {
        c.forEach(this::add);
        return this;
    }

    public <T extends JCTree> ClassInjector add(T tree) {
        this.tree.defs = this.tree.defs
                .append(tree);
        return this;
    }

    public MethodsInjector methods() {
        return new MethodsInjector(tree, tree.defs.stream()
                .filter(JCMethodDecl.class::isInstance)
                .map(JCMethodDecl.class::cast)
                .collect(Collectors.toList()));
    }

    public FieldsInjector fields() {
        return new FieldsInjector(tree, tree.defs.stream()
                .filter(JCVariableDecl.class::isInstance)
                .map(JCVariableDecl.class::cast)
                .collect(Collectors.toList()));
    }

    public JCClassDecl tree() {
        return tree;
    }

    public static class MethodsInjector {
        private final JCClassDecl tree;
        private final List<JCMethodDecl> methods;

        public MethodsInjector(JCClassDecl tree, List<JCMethodDecl> methods) {
            this.tree = tree;
            this.methods = methods;
        }

        public MethodsInjector add(Function<JCMethodDecl, ? extends JCTree> function) {
            methods.forEach(method -> tree.defs = tree.defs
                    .append(function.apply(method)));
            return this;
        }

        public JCClassDecl tree() {
            return tree;
        }
    }

    public static class FieldsInjector {
        private final JCClassDecl tree;
        private final List<JCVariableDecl> fields;

        public FieldsInjector(JCClassDecl tree, List<JCVariableDecl> fields) {
            this.tree = tree;
            this.fields = fields;
        }

        public FieldsInjector add(Function<JCVariableDecl, ? extends JCTree> function) {
            fields.forEach(field -> tree.defs = tree.defs
                    .append(function.apply(field)));
            return this;
        }

        public JCClassDecl tree() {
            return tree;
        }
    }
}