package org.transparent.interlucent.example;

import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;
import org.transparent.interlucent.transform.EurekaTranslator;
import org.transparent.lucent.transform.LucentValidator;

import static org.transparent.eureka.util.Modifiers.PRIVATE_FINAL;

public final class ExampleTranslator extends EurekaTranslator {
    public ExampleTranslator(Names names, TreeMaker factory, LucentValidator validator) {
        super(names, factory, validator);
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
                .add(method()
                        .mods(PRIVATE_FINAL)
                        .type(TypeTag.VOID)
                        .name("method")
                )
                .tree();
    }
}
