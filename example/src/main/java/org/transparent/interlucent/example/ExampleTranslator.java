package org.transparent.interlucent.example;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;
import org.transparent.interlucent.transform.EurekaTranslator;

import javax.lang.model.element.Element;

import static org.transparent.eureka.util.Modifiers.PRIVATE_FINAL;

public final class ExampleTranslator extends EurekaTranslator {
    public ExampleTranslator(Names names, TreeMaker factory) {
        super(names, factory);
    }

    @Override
    public void translate(JCTree tree, Element element) {
        tree.accept(this);
    }

    @Override
    public void visitClassDef(JCClassDecl tree) {
        super.visitClassDef(tree);
        result = inject(tree)
                .add(field()
                        .mods(PRIVATE_FINAL)
                        .type(Object.class)
                        .name("field")
                        .value(null)
                )
                .tree();
    }
}
