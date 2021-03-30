package org.transparent.interlucent.transform

import com.sun.tools.javac.tree.TreeMaker
import com.sun.tools.javac.util.Names
import org.transparent.eureka.api.factory.TreeFactory
import org.transparent.eureka.tree.factory.EurekaFactory
import org.transparent.lucent.transform.LucentTranslator

abstract class EurekaTranslator(
    names: Names,
    factory: TreeMaker
) : LucentTranslator(names, factory), TreeFactory by EurekaFactory(names, factory)