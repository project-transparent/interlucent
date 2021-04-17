package org.transparent.interlucent.inject;

import static com.sun.tools.javac.tree.JCTree.*;

public class MethodInjector {
    private final JCMethodDecl method;

    public MethodInjector(JCMethodDecl method) {
        this.method = method;
    }

    public <T extends JCStatement> MethodInjector add(T tree) {
        method.body.stats = method.body.stats
                .append(tree);
        return this;
    }

    public JCMethodDecl tree() {
        return method;
    }
}