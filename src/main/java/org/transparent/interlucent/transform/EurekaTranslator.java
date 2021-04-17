package org.transparent.interlucent.transform;

import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.EurekaFactory;
import org.transparent.eureka.api.factory.MemberFactory;
import org.transparent.eureka.api.factory.StatementFactory;
import org.transparent.eureka.impl.builder.BlockBuilder;
import org.transparent.eureka.impl.builder.FieldBuilder;
import org.transparent.eureka.impl.builder.MethodBuilder;
import org.transparent.eureka.impl.builder.statement.ArrayBuilder;
import org.transparent.eureka.impl.builder.statement.ForBuilder;
import org.transparent.eureka.impl.builder.statement.SwitchBuilder;
import org.transparent.interlucent.inject.ClassInjector;
import org.transparent.interlucent.inject.MethodInjector;
import org.transparent.lucent.transform.LucentTranslator;
import org.transparent.lucent.transform.LucentValidator;

public abstract class EurekaTranslator extends LucentTranslator implements MemberFactory, StatementFactory {
    protected final EurekaFactory factory;

    public EurekaTranslator(Names names, TreeMaker factory, LucentValidator validator) {
        super(names, factory, validator);
        this.factory = new EurekaFactory(names, factory);
    }

    public ClassInjector inject(JCClassDecl tree) {
        return new ClassInjector(tree);
    }

    public MethodInjector inject(JCMethodDecl tree) {
        return new MethodInjector(tree);
    }

    @Override
    public FieldBuilder field() {
        return factory.field();
    }

    @Override
    public MethodBuilder method() {
        return factory.method();
    }

    @Override
    public Name name(String name) {
        return factory.name(name);
    }

    @Override
    public JCModifiers mods(long flags) {
        return factory.mods(flags);
    }

    @Override
    public JCModifiers mods(long flags, JCAnnotation... annotations) {
        return factory.mods(flags, annotations);
    }

    @Override
    public JCModifiers annotations(JCAnnotation... annotations) {
        return factory.annotations(annotations);
    }

    @Override
    public JCExpression id(String s) {
        return factory.id(s);
    }

    @Override
    public JCPrimitiveTypeTree id(TypeTag tag) {
        return factory.id(tag);
    }

    @Override
    public JCExpression id(JCVariableDecl parameter) {
        return factory.id(parameter);
    }

    @Override
    public List<JCExpression> ids(JCVariableDecl... parameters) {
        return factory.ids(parameters);
    }

    @Override
    public JCLiteral literal(TypeTag tag, Object value) {
        return factory.literal(tag, value);
    }

    @Override
    public JCLiteral literal(Object value) {
        return factory.literal(value);
    }

    @Override
    public JCLiteral literal() {
        return factory.literal();
    }

    @Override
    public ArrayBuilder array() {
        return factory.array();
    }

    @Override
    public BlockBuilder block() {
        return factory.block();
    }

    @Override
    public JCReturn returnStat(Object value) {
        return factory.returnStat(value);
    }

    @Override
    public JCAssert assertion(JCExpression condition, Object detail) {
        return factory.assertion(condition, detail);
    }

    @Override
    public JCWhileLoop whileLoop(JCExpression condition, Object block) {
        return factory.whileLoop(condition, block);
    }

    @Override
    public JCDoWhileLoop doLoop(JCExpression condition, Object block) {
        return factory.doLoop(condition, block);
    }

    @Override
    public ForBuilder forLoop() {
        return factory.forLoop();
    }

    @Override
    public JCEnhancedForLoop forEach(JCVariableDecl variable, JCExpression expression, Object block) {
        return factory.forEach(variable, expression, block);
    }

    @Override
    public JCLabeledStatement label(String label, JCStatement statement) {
        return factory.label(label, statement);
    }

    @Override
    public JCIf ifStat(JCExpression condition, Object then, Object or) {
        return factory.ifStat(condition, then, or);
    }

    @Override
    public JCIf ifStat(JCExpression condition, Object then) {
        return factory.ifStat(condition, then);
    }

    @Override
    public SwitchBuilder switchStat(JCExpression selector) {
        return factory.switchStat(selector);
    }

    @Override
    public JCContinue continueStat(String label) {
        return factory.continueStat(label);
    }

    @Override
    public JCBreak breakStat(String label) {
        return factory.breakStat(label);
    }

    @Override
    public JCExpressionStatement exprStat(JCExpression expression) {
        return factory.exprStat(expression);
    }

    @Override
    public JCBinary bin(Object lhs, Tag operator, Object rhs) {
        return factory.bin(lhs, operator, rhs);
    }

    @Override
    public JCAssignOp assign(Tag operator, Object lhs, Object rhs) {
        return factory.assign(operator, lhs, rhs);
    }

    @Override
    public JCAssign assign(Object lhs, Object rhs) {
        return factory.assign(lhs, rhs);
    }

    @Override
    public JCExpressionStatement call(String name, Object... args) {
        return factory.call(name, args);
    }
}
