package net.bytebuddy.implementation;

import androidx.core.app.NotificationCompat;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.DoubleConstant;
import net.bytebuddy.implementation.bytecode.constant.FloatConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.LongConstant;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.RandomString;

/* JADX INFO: loaded from: classes3.dex */
public class MethodCall implements Implementation.Composable {
    protected final List<ArgumentLoader.Factory> argumentLoaders;
    protected final Assigner assigner;
    protected final MethodInvoker methodInvoker;
    protected final MethodLocator methodLocator;
    protected final TargetHandler targetHandler;
    protected final TerminationHandler terminationHandler;
    protected final Assigner.Typing typing;

    protected enum TerminationHandler {
        RETURNING { // from class: net.bytebuddy.implementation.MethodCall.TerminationHandler.1
            @Override // net.bytebuddy.implementation.MethodCall.TerminationHandler
            public StackManipulation resolve(MethodDescription methodDescription, MethodDescription methodDescription2, Assigner assigner, Assigner.Typing typing) {
                TypeDescription.Generic returnType;
                if (methodDescription.isConstructor()) {
                    returnType = methodDescription.getDeclaringType().asGenericType();
                } else {
                    returnType = methodDescription.getReturnType();
                }
                StackManipulation stackManipulationAssign = assigner.assign(returnType, methodDescription2.getReturnType(), typing);
                if (!stackManipulationAssign.isValid()) {
                    throw new IllegalStateException("Cannot return " + methodDescription.getReturnType() + " from " + methodDescription2);
                }
                return new StackManipulation.Compound(stackManipulationAssign, MethodReturn.of(methodDescription2.getReturnType()));
            }
        },
        DROPPING { // from class: net.bytebuddy.implementation.MethodCall.TerminationHandler.2
            @Override // net.bytebuddy.implementation.MethodCall.TerminationHandler
            protected StackManipulation resolve(MethodDescription methodDescription, MethodDescription methodDescription2, Assigner assigner, Assigner.Typing typing) {
                TypeDefinition returnType;
                if (methodDescription.isConstructor()) {
                    returnType = methodDescription.getDeclaringType();
                } else {
                    returnType = methodDescription.getReturnType();
                }
                return Removal.of(returnType);
            }
        };

        protected abstract StackManipulation resolve(MethodDescription methodDescription, MethodDescription methodDescription2, Assigner assigner, Assigner.Typing typing);
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof MethodCall;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MethodCall)) {
            return false;
        }
        MethodCall methodCall = (MethodCall) obj;
        if (!methodCall.canEqual(this)) {
            return false;
        }
        MethodLocator methodLocator = this.methodLocator;
        MethodLocator methodLocator2 = methodCall.methodLocator;
        if (methodLocator != null ? !methodLocator.equals(methodLocator2) : methodLocator2 != null) {
            return false;
        }
        TargetHandler targetHandler = this.targetHandler;
        TargetHandler targetHandler2 = methodCall.targetHandler;
        if (targetHandler != null ? !targetHandler.equals(targetHandler2) : targetHandler2 != null) {
            return false;
        }
        List<ArgumentLoader.Factory> list = this.argumentLoaders;
        List<ArgumentLoader.Factory> list2 = methodCall.argumentLoaders;
        if (list != null ? !list.equals(list2) : list2 != null) {
            return false;
        }
        MethodInvoker methodInvoker = this.methodInvoker;
        MethodInvoker methodInvoker2 = methodCall.methodInvoker;
        if (methodInvoker != null ? !methodInvoker.equals(methodInvoker2) : methodInvoker2 != null) {
            return false;
        }
        TerminationHandler terminationHandler = this.terminationHandler;
        TerminationHandler terminationHandler2 = methodCall.terminationHandler;
        if (terminationHandler != null ? !terminationHandler.equals(terminationHandler2) : terminationHandler2 != null) {
            return false;
        }
        Assigner assigner = this.assigner;
        Assigner assigner2 = methodCall.assigner;
        if (assigner != null ? !assigner.equals(assigner2) : assigner2 != null) {
            return false;
        }
        Assigner.Typing typing = this.typing;
        Assigner.Typing typing2 = methodCall.typing;
        return typing != null ? typing.equals(typing2) : typing2 == null;
    }

    public int hashCode() {
        MethodLocator methodLocator = this.methodLocator;
        int iHashCode = methodLocator == null ? 43 : methodLocator.hashCode();
        TargetHandler targetHandler = this.targetHandler;
        int iHashCode2 = ((iHashCode + 59) * 59) + (targetHandler == null ? 43 : targetHandler.hashCode());
        List<ArgumentLoader.Factory> list = this.argumentLoaders;
        int iHashCode3 = (iHashCode2 * 59) + (list == null ? 43 : list.hashCode());
        MethodInvoker methodInvoker = this.methodInvoker;
        int iHashCode4 = (iHashCode3 * 59) + (methodInvoker == null ? 43 : methodInvoker.hashCode());
        TerminationHandler terminationHandler = this.terminationHandler;
        int iHashCode5 = (iHashCode4 * 59) + (terminationHandler == null ? 43 : terminationHandler.hashCode());
        Assigner assigner = this.assigner;
        int iHashCode6 = (iHashCode5 * 59) + (assigner == null ? 43 : assigner.hashCode());
        Assigner.Typing typing = this.typing;
        return (iHashCode6 * 59) + (typing != null ? typing.hashCode() : 43);
    }

    protected MethodCall(MethodLocator methodLocator, TargetHandler targetHandler, List<ArgumentLoader.Factory> list, MethodInvoker methodInvoker, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing) {
        this.methodLocator = methodLocator;
        this.targetHandler = targetHandler;
        this.argumentLoaders = list;
        this.methodInvoker = methodInvoker;
        this.terminationHandler = terminationHandler;
        this.assigner = assigner;
        this.typing = typing;
    }

    public static WithoutSpecifiedTarget invoke(Method method) {
        return invoke(new MethodDescription.ForLoadedMethod(method));
    }

    public static WithoutSpecifiedTarget invoke(Constructor<?> constructor) {
        return invoke(new MethodDescription.ForLoadedConstructor(constructor));
    }

    public static WithoutSpecifiedTarget invoke(MethodDescription methodDescription) {
        return invoke(new MethodLocator.ForExplicitMethod(methodDescription));
    }

    public static WithoutSpecifiedTarget invoke(ElementMatcher<? super MethodDescription> elementMatcher) {
        return invoke(elementMatcher, MethodGraph.Compiler.DEFAULT);
    }

    public static WithoutSpecifiedTarget invoke(ElementMatcher<? super MethodDescription> elementMatcher, MethodGraph.Compiler compiler) {
        return invoke(new MethodLocator.ForElementMatcher(elementMatcher, compiler));
    }

    public static WithoutSpecifiedTarget invoke(MethodLocator methodLocator) {
        return new WithoutSpecifiedTarget(methodLocator);
    }

    public static WithoutSpecifiedTarget invokeSelf() {
        return new WithoutSpecifiedTarget(MethodLocator.ForInstrumentedMethod.INSTANCE);
    }

    public static MethodCall invokeSuper() {
        return invokeSelf().onSuper();
    }

    public static Implementation.Composable call(Callable<?> callable) {
        try {
            return invoke(Callable.class.getMethod(NotificationCompat.CATEGORY_CALL, new Class[0])).on(callable, Callable.class).withAssigner(Assigner.DEFAULT, Assigner.Typing.DYNAMIC);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Could not locate Callable::call method", e);
        }
    }

    public static Implementation.Composable run(Runnable runnable) {
        try {
            return invoke(Runnable.class.getMethod("run", new Class[0])).on(runnable, Runnable.class).withAssigner(Assigner.DEFAULT, Assigner.Typing.DYNAMIC);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Could not locate Runnable::run method", e);
        }
    }

    public static MethodCall construct(Constructor<?> constructor) {
        return construct(new MethodDescription.ForLoadedConstructor(constructor));
    }

    public static MethodCall construct(MethodDescription methodDescription) {
        if (!methodDescription.isConstructor()) {
            throw new IllegalArgumentException("Not a constructor: " + methodDescription);
        }
        return new MethodCall(new MethodLocator.ForExplicitMethod(methodDescription), TargetHandler.ForConstructingInvocation.INSTANCE, Collections.emptyList(), MethodInvoker.ForContextualInvocation.INSTANCE, TerminationHandler.RETURNING, Assigner.DEFAULT, Assigner.Typing.STATIC);
    }

    public MethodCall with(Object... objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(ArgumentLoader.ForStackManipulation.of(obj));
        }
        return with(arrayList);
    }

    public MethodCall with(TypeDescription... typeDescriptionArr) {
        ArrayList arrayList = new ArrayList(typeDescriptionArr.length);
        for (TypeDescription typeDescription : typeDescriptionArr) {
            arrayList.add(new ArgumentLoader.ForStackManipulation(ClassConstant.of(typeDescription), Class.class));
        }
        return with(arrayList);
    }

    public MethodCall with(EnumerationDescription... enumerationDescriptionArr) {
        ArrayList arrayList = new ArrayList(enumerationDescriptionArr.length);
        for (EnumerationDescription enumerationDescription : enumerationDescriptionArr) {
            arrayList.add(new ArgumentLoader.ForStackManipulation(FieldAccess.forEnumeration(enumerationDescription), enumerationDescription.getEnumerationType()));
        }
        return with(arrayList);
    }

    public MethodCall with(JavaConstant... javaConstantArr) {
        ArrayList arrayList = new ArrayList(javaConstantArr.length);
        for (JavaConstant javaConstant : javaConstantArr) {
            arrayList.add(new ArgumentLoader.ForStackManipulation(javaConstant.asStackManipulation(), javaConstant.getType()));
        }
        return with(arrayList);
    }

    public MethodCall withReference(Object... objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            arrayList.add(obj == null ? ArgumentLoader.ForNullConstant.INSTANCE : new ArgumentLoader.ForInstance.Factory(obj));
        }
        return with(arrayList);
    }

    public MethodCall withArgument(int... iArr) {
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            if (i < 0) {
                throw new IllegalArgumentException("Negative index: " + i);
            }
            arrayList.add(new ArgumentLoader.ForMethodParameter.Factory(i));
        }
        return with(arrayList);
    }

    public MethodCall withAllArguments() {
        return with(ArgumentLoader.ForMethodParameter.OfInstrumentedMethod.INSTANCE);
    }

    public MethodCall withArgumentArray() {
        return with(ArgumentLoader.ForMethodParameterArray.ForInstrumentedMethod.INSTANCE);
    }

    public MethodCall withArgumentArrayElements(int i) {
        if (i >= 0) {
            return with(new ArgumentLoader.ForMethodParameterArrayElement.OfInvokedMethod(i));
        }
        throw new IllegalArgumentException("A parameter index cannot be negative: " + i);
    }

    public MethodCall withArgumentArrayElements(int i, int i2) {
        return withArgumentArrayElements(i, 0, i2);
    }

    public MethodCall withArgumentArrayElements(int i, int i2, int i3) {
        if (i < 0) {
            throw new IllegalArgumentException("A parameter index cannot be negative: " + i);
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("An array index cannot be negative: " + i2);
        }
        if (i3 == 0) {
            return this;
        }
        if (i3 < 0) {
            throw new IllegalArgumentException("Size cannot be negative: " + i3);
        }
        ArrayList arrayList = new ArrayList(i3);
        for (int i4 = 0; i4 < i3; i4++) {
            arrayList.add(new ArgumentLoader.ForMethodParameterArrayElement.OfParameter(i, i2 + i4));
        }
        return with(arrayList);
    }

    public MethodCall withThis() {
        return with(ArgumentLoader.ForThisReference.Factory.INSTANCE);
    }

    public MethodCall withOwnType() {
        return with(ArgumentLoader.ForInstrumentedType.Factory.INSTANCE);
    }

    public MethodCall withField(String... strArr) {
        return withField(FieldLocator.ForClassHierarchy.Factory.INSTANCE, strArr);
    }

    public MethodCall withField(FieldLocator.Factory factory, String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(new ArgumentLoader.ForField.Factory(str, factory));
        }
        return with(arrayList);
    }

    public MethodCall with(StackManipulation stackManipulation, Type type) {
        return with(stackManipulation, TypeDefinition.Sort.describe(type));
    }

    public MethodCall with(StackManipulation stackManipulation, TypeDefinition typeDefinition) {
        return with(new ArgumentLoader.ForStackManipulation(stackManipulation, typeDefinition));
    }

    public MethodCall with(ArgumentLoader.Factory... factoryArr) {
        return with(Arrays.asList(factoryArr));
    }

    public MethodCall with(List<? extends ArgumentLoader.Factory> list) {
        return new MethodCall(this.methodLocator, this.targetHandler, CompoundList.of((List) this.argumentLoaders, (List) list), this.methodInvoker, this.terminationHandler, this.assigner, this.typing);
    }

    public Implementation.Composable withAssigner(Assigner assigner, Assigner.Typing typing) {
        return new MethodCall(this.methodLocator, this.targetHandler, this.argumentLoaders, this.methodInvoker, this.terminationHandler, assigner, typing);
    }

    @Override // net.bytebuddy.implementation.Implementation.Composable
    public Implementation andThen(Implementation implementation) {
        return new Implementation.Compound(new MethodCall(this.methodLocator, this.targetHandler, this.argumentLoaders, this.methodInvoker, TerminationHandler.DROPPING, this.assigner, this.typing), implementation);
    }

    @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        Iterator<ArgumentLoader.Factory> it = this.argumentLoaders.iterator();
        while (it.hasNext()) {
            instrumentedType = it.next().prepare(instrumentedType);
        }
        return this.targetHandler.prepare(instrumentedType);
    }

    @Override // net.bytebuddy.implementation.Implementation
    public ByteCodeAppender appender(Implementation.Target target) {
        return new Appender(target);
    }

    public interface MethodLocator {

        public enum ForInstrumentedMethod implements MethodLocator {
            INSTANCE;

            @Override // net.bytebuddy.implementation.MethodCall.MethodLocator
            public MethodDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription) {
                return methodDescription;
            }
        }

        MethodDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription);

        public static class ForExplicitMethod implements MethodLocator {
            private final MethodDescription methodDescription;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForExplicitMethod;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForExplicitMethod)) {
                    return false;
                }
                ForExplicitMethod forExplicitMethod = (ForExplicitMethod) obj;
                if (!forExplicitMethod.canEqual(this)) {
                    return false;
                }
                MethodDescription methodDescription = this.methodDescription;
                MethodDescription methodDescription2 = forExplicitMethod.methodDescription;
                return methodDescription != null ? methodDescription.equals(methodDescription2) : methodDescription2 == null;
            }

            public int hashCode() {
                MethodDescription methodDescription = this.methodDescription;
                return 59 + (methodDescription == null ? 43 : methodDescription.hashCode());
            }

            protected ForExplicitMethod(MethodDescription methodDescription) {
                this.methodDescription = methodDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.MethodLocator
            public MethodDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription) {
                return this.methodDescription;
            }
        }

        public static class ForElementMatcher implements MethodLocator {
            private final ElementMatcher<? super MethodDescription> matcher;
            private final MethodGraph.Compiler methodGraphCompiler;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForElementMatcher;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForElementMatcher)) {
                    return false;
                }
                ForElementMatcher forElementMatcher = (ForElementMatcher) obj;
                if (!forElementMatcher.canEqual(this)) {
                    return false;
                }
                ElementMatcher<? super MethodDescription> elementMatcher = this.matcher;
                ElementMatcher<? super MethodDescription> elementMatcher2 = forElementMatcher.matcher;
                if (elementMatcher != null ? !elementMatcher.equals(elementMatcher2) : elementMatcher2 != null) {
                    return false;
                }
                MethodGraph.Compiler compiler = this.methodGraphCompiler;
                MethodGraph.Compiler compiler2 = forElementMatcher.methodGraphCompiler;
                return compiler != null ? compiler.equals(compiler2) : compiler2 == null;
            }

            public int hashCode() {
                ElementMatcher<? super MethodDescription> elementMatcher = this.matcher;
                int iHashCode = elementMatcher == null ? 43 : elementMatcher.hashCode();
                MethodGraph.Compiler compiler = this.methodGraphCompiler;
                return ((iHashCode + 59) * 59) + (compiler != null ? compiler.hashCode() : 43);
            }

            protected ForElementMatcher(ElementMatcher<? super MethodDescription> elementMatcher, MethodGraph.Compiler compiler) {
                this.matcher = elementMatcher;
                this.methodGraphCompiler = compiler;
            }

            @Override // net.bytebuddy.implementation.MethodCall.MethodLocator
            public MethodDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription) {
                MethodList methodListFilter = this.methodGraphCompiler.compile(typeDescription).listNodes().asMethodList().filter(this.matcher);
                if (methodListFilter.size() == 1) {
                    return (MethodDescription) methodListFilter.getOnly();
                }
                throw new IllegalStateException(typeDescription + " does not define exactly one virtual method for " + this.matcher);
            }
        }
    }

    public interface ArgumentLoader {

        public interface Factory {
            List<ArgumentLoader> make(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2);

            InstrumentedType prepare(InstrumentedType instrumentedType);
        }

        StackManipulation resolve(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing);

        public enum ForNullConstant implements ArgumentLoader, Factory {
            INSTANCE;

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
            public List<ArgumentLoader> make(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2) {
                return Collections.singletonList(this);
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation resolve(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                if (parameterDescription.getType().isPrimitive()) {
                    throw new IllegalStateException("Cannot assign null to " + parameterDescription);
                }
                return NullConstant.INSTANCE;
            }
        }

        public static class ForThisReference implements ArgumentLoader {
            private final TypeDescription instrumentedType;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForThisReference;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForThisReference)) {
                    return false;
                }
                ForThisReference forThisReference = (ForThisReference) obj;
                if (!forThisReference.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.instrumentedType;
                TypeDescription typeDescription2 = forThisReference.instrumentedType;
                return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.instrumentedType;
                return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
            }

            public ForThisReference(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation resolve(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.loadThis(), assigner.assign(this.instrumentedType.asGenericType(), parameterDescription.getType(), typing));
                if (compound.isValid()) {
                    return compound;
                }
                throw new IllegalStateException("Cannot assign " + this.instrumentedType + " to " + parameterDescription);
            }

            public enum Factory implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public List<ArgumentLoader> make(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2) {
                    if (methodDescription.isStatic()) {
                        throw new IllegalStateException(methodDescription + " is static and cannot supply an invoker instance");
                    }
                    return Collections.singletonList(new ForThisReference(typeDescription));
                }
            }
        }

        public static class ForInstrumentedType implements ArgumentLoader {
            private final TypeDescription instrumentedType;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForInstrumentedType;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForInstrumentedType)) {
                    return false;
                }
                ForInstrumentedType forInstrumentedType = (ForInstrumentedType) obj;
                if (!forInstrumentedType.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.instrumentedType;
                TypeDescription typeDescription2 = forInstrumentedType.instrumentedType;
                return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.instrumentedType;
                return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
            }

            public ForInstrumentedType(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation resolve(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation.Compound compound = new StackManipulation.Compound(ClassConstant.of(this.instrumentedType), assigner.assign(new TypeDescription.Generic.OfNonGenericType.ForLoadedType(Class.class), parameterDescription.getType(), typing));
                if (compound.isValid()) {
                    return compound;
                }
                throw new IllegalStateException("Cannot assign Class value to " + parameterDescription);
            }

            public enum Factory implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public List<ArgumentLoader> make(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2) {
                    return Collections.singletonList(new ForInstrumentedType(typeDescription));
                }
            }
        }

        public static class ForMethodParameter implements ArgumentLoader {
            private final int index;
            private final MethodDescription instrumentedMethod;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForMethodParameter;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForMethodParameter)) {
                    return false;
                }
                ForMethodParameter forMethodParameter = (ForMethodParameter) obj;
                if (!forMethodParameter.canEqual(this) || this.index != forMethodParameter.index) {
                    return false;
                }
                MethodDescription methodDescription = this.instrumentedMethod;
                MethodDescription methodDescription2 = forMethodParameter.instrumentedMethod;
                return methodDescription != null ? methodDescription.equals(methodDescription2) : methodDescription2 == null;
            }

            public int hashCode() {
                int i = this.index + 59;
                MethodDescription methodDescription = this.instrumentedMethod;
                return (i * 59) + (methodDescription == null ? 43 : methodDescription.hashCode());
            }

            public ForMethodParameter(int i, MethodDescription methodDescription) {
                this.index = i;
                this.instrumentedMethod = methodDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation resolve(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                ParameterDescription parameterDescription2 = (ParameterDescription) this.instrumentedMethod.getParameters().get(this.index);
                StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.load(parameterDescription2), assigner.assign(parameterDescription2.getType(), parameterDescription.getType(), typing));
                if (compound.isValid()) {
                    return compound;
                }
                throw new IllegalStateException("Cannot assign " + parameterDescription2 + " to " + parameterDescription + " for " + this.instrumentedMethod);
            }

            protected enum OfInstrumentedMethod implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public List<ArgumentLoader> make(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2) {
                    ArrayList arrayList = new ArrayList(methodDescription.getParameters().size());
                    Iterator it = methodDescription.getParameters().iterator();
                    while (it.hasNext()) {
                        arrayList.add(new ForMethodParameter(((ParameterDescription) it.next()).getIndex(), methodDescription));
                    }
                    return arrayList;
                }
            }

            protected static class Factory implements Factory {
                private final int index;

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof Factory;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Factory)) {
                        return false;
                    }
                    Factory factory = (Factory) obj;
                    return factory.canEqual(this) && this.index == factory.index;
                }

                public int hashCode() {
                    return 59 + this.index;
                }

                public Factory(int i) {
                    this.index = i;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public List<ArgumentLoader> make(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2) {
                    if (this.index >= methodDescription.getParameters().size()) {
                        throw new IllegalStateException(methodDescription + " does not have a parameter with index " + this.index);
                    }
                    return Collections.singletonList(new ForMethodParameter(this.index, methodDescription));
                }
            }
        }

        public static class ForMethodParameterArray implements ArgumentLoader {
            private final ParameterList<?> parameters;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForMethodParameterArray;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForMethodParameterArray)) {
                    return false;
                }
                ForMethodParameterArray forMethodParameterArray = (ForMethodParameterArray) obj;
                if (!forMethodParameterArray.canEqual(this)) {
                    return false;
                }
                ParameterList<?> parameterList = this.parameters;
                ParameterList<?> parameterList2 = forMethodParameterArray.parameters;
                return parameterList != null ? parameterList.equals(parameterList2) : parameterList2 == null;
            }

            public int hashCode() {
                ParameterList<?> parameterList = this.parameters;
                return 59 + (parameterList == null ? 43 : parameterList.hashCode());
            }

            public ForMethodParameterArray(ParameterList<?> parameterList) {
                this.parameters = parameterList;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation resolve(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                TypeDescription.Generic componentType;
                if (parameterDescription.getType().represents(Object.class)) {
                    componentType = TypeDescription.Generic.OBJECT;
                } else if (parameterDescription.getType().isArray()) {
                    componentType = parameterDescription.getType().getComponentType();
                } else {
                    throw new IllegalStateException();
                }
                ArrayList arrayList = new ArrayList(this.parameters.size());
                Iterator it = this.parameters.iterator();
                while (it.hasNext()) {
                    ParameterDescription parameterDescription2 = (ParameterDescription) it.next();
                    StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.load(parameterDescription2), assigner.assign(parameterDescription2.getType(), componentType, typing));
                    if (compound.isValid()) {
                        arrayList.add(compound);
                    } else {
                        throw new IllegalStateException("Cannot assign " + parameterDescription2 + " to " + componentType);
                    }
                }
                return new StackManipulation.Compound(ArrayFactory.forType(componentType).withValues(arrayList));
            }

            public enum ForInstrumentedMethod implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public List<ArgumentLoader> make(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2) {
                    return Collections.singletonList(new ForMethodParameterArray(methodDescription.getParameters()));
                }
            }
        }

        public static class ForMethodParameterArrayElement implements ArgumentLoader {
            private final int index;
            private final ParameterDescription parameterDescription;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForMethodParameterArrayElement;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForMethodParameterArrayElement)) {
                    return false;
                }
                ForMethodParameterArrayElement forMethodParameterArrayElement = (ForMethodParameterArrayElement) obj;
                if (!forMethodParameterArrayElement.canEqual(this)) {
                    return false;
                }
                ParameterDescription parameterDescription = this.parameterDescription;
                ParameterDescription parameterDescription2 = forMethodParameterArrayElement.parameterDescription;
                if (parameterDescription != null ? parameterDescription.equals(parameterDescription2) : parameterDescription2 == null) {
                    return this.index == forMethodParameterArrayElement.index;
                }
                return false;
            }

            public int hashCode() {
                ParameterDescription parameterDescription = this.parameterDescription;
                return (((parameterDescription == null ? 43 : parameterDescription.hashCode()) + 59) * 59) + this.index;
            }

            public ForMethodParameterArrayElement(ParameterDescription parameterDescription, int i) {
                this.parameterDescription = parameterDescription;
                this.index = i;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation resolve(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.load(this.parameterDescription), IntegerConstant.forValue(this.index), ArrayAccess.of(this.parameterDescription.getType().getComponentType()).load(), assigner.assign(this.parameterDescription.getType().getComponentType(), parameterDescription.getType(), typing));
                if (compound.isValid()) {
                    return compound;
                }
                throw new IllegalStateException("Cannot assign " + this.parameterDescription.getType().getComponentType() + " to " + parameterDescription);
            }

            protected static class OfParameter implements Factory {
                private final int arrayIndex;
                private final int index;

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof OfParameter;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof OfParameter)) {
                        return false;
                    }
                    OfParameter ofParameter = (OfParameter) obj;
                    return ofParameter.canEqual(this) && this.index == ofParameter.index && this.arrayIndex == ofParameter.arrayIndex;
                }

                public int hashCode() {
                    return ((this.index + 59) * 59) + this.arrayIndex;
                }

                public OfParameter(int i, int i2) {
                    this.index = i;
                    this.arrayIndex = i2;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public List<ArgumentLoader> make(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2) {
                    if (methodDescription.getParameters().size() <= this.index) {
                        throw new IllegalStateException(methodDescription + " does not declare a parameter with index " + this.index);
                    }
                    if (!((ParameterDescription) methodDescription.getParameters().get(this.index)).getType().isArray()) {
                        throw new IllegalStateException("Cannot access an item from non-array parameter " + methodDescription.getParameters().get(this.index));
                    }
                    return Collections.singletonList(new ForMethodParameterArrayElement((ParameterDescription) methodDescription.getParameters().get(this.index), this.arrayIndex));
                }
            }

            public static class OfInvokedMethod implements Factory {
                private final int index;

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof OfInvokedMethod;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof OfInvokedMethod)) {
                        return false;
                    }
                    OfInvokedMethod ofInvokedMethod = (OfInvokedMethod) obj;
                    return ofInvokedMethod.canEqual(this) && this.index == ofInvokedMethod.index;
                }

                public int hashCode() {
                    return 59 + this.index;
                }

                public OfInvokedMethod(int i) {
                    this.index = i;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public List<ArgumentLoader> make(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2) {
                    if (methodDescription.getParameters().size() <= this.index) {
                        throw new IllegalStateException(methodDescription + " does not declare a parameter with index " + this.index);
                    }
                    if (!((ParameterDescription) methodDescription.getParameters().get(this.index)).getType().isArray()) {
                        throw new IllegalStateException("Cannot access an item from non-array parameter " + methodDescription.getParameters().get(this.index));
                    }
                    ArrayList arrayList = new ArrayList(methodDescription.getParameters().size());
                    for (int i = 0; i < methodDescription2.getParameters().size(); i = i + 1 + 1) {
                        arrayList.add(new ForMethodParameterArrayElement((ParameterDescription) methodDescription.getParameters().get(this.index), i));
                    }
                    return arrayList;
                }
            }
        }

        public static class ForInstance implements ArgumentLoader {
            private final FieldDescription fieldDescription;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForInstance;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForInstance)) {
                    return false;
                }
                ForInstance forInstance = (ForInstance) obj;
                if (!forInstance.canEqual(this)) {
                    return false;
                }
                FieldDescription fieldDescription = this.fieldDescription;
                FieldDescription fieldDescription2 = forInstance.fieldDescription;
                return fieldDescription != null ? fieldDescription.equals(fieldDescription2) : fieldDescription2 == null;
            }

            public int hashCode() {
                FieldDescription fieldDescription = this.fieldDescription;
                return 59 + (fieldDescription == null ? 43 : fieldDescription.hashCode());
            }

            public ForInstance(FieldDescription fieldDescription) {
                this.fieldDescription = fieldDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation resolve(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation.Compound compound = new StackManipulation.Compound(FieldAccess.forField(this.fieldDescription).read(), assigner.assign(this.fieldDescription.getType(), parameterDescription.getType(), typing));
                if (compound.isValid()) {
                    return compound;
                }
                throw new IllegalStateException("Cannot assign " + this.fieldDescription.getType() + " to " + parameterDescription);
            }

            protected static class Factory implements Factory {
                private static final String FIELD_PREFIX = "methodCall";
                private final String name = String.format("%s$%s", FIELD_PREFIX, RandomString.make());
                private final Object value;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Factory;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Factory)) {
                        return false;
                    }
                    Factory factory = (Factory) obj;
                    if (!factory.canEqual(this)) {
                        return false;
                    }
                    Object obj2 = this.value;
                    Object obj3 = factory.value;
                    return obj2 != null ? obj2.equals(obj3) : obj3 == null;
                }

                public int hashCode() {
                    Object obj = this.value;
                    return 59 + (obj == null ? 43 : obj.hashCode());
                }

                public Factory(Object obj) {
                    this.value = obj;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType.withField(new FieldDescription.Token(this.name, 4105, new TypeDescription.Generic.OfNonGenericType.ForLoadedType(this.value.getClass()))).withInitializer(new LoadedTypeInitializer.ForStaticField(this.name, this.value));
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public List<ArgumentLoader> make(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2) {
                    return Collections.singletonList(new ForInstance((FieldDescription) typeDescription.getDeclaredFields().filter(ElementMatchers.named(this.name)).getOnly()));
                }
            }
        }

        public static class ForField implements ArgumentLoader {
            private final FieldDescription fieldDescription;
            private final MethodDescription instrumentedMethod;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForField;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForField)) {
                    return false;
                }
                ForField forField = (ForField) obj;
                if (!forField.canEqual(this)) {
                    return false;
                }
                FieldDescription fieldDescription = this.fieldDescription;
                FieldDescription fieldDescription2 = forField.fieldDescription;
                if (fieldDescription != null ? !fieldDescription.equals(fieldDescription2) : fieldDescription2 != null) {
                    return false;
                }
                MethodDescription methodDescription = this.instrumentedMethod;
                MethodDescription methodDescription2 = forField.instrumentedMethod;
                return methodDescription != null ? methodDescription.equals(methodDescription2) : methodDescription2 == null;
            }

            public int hashCode() {
                FieldDescription fieldDescription = this.fieldDescription;
                int iHashCode = fieldDescription == null ? 43 : fieldDescription.hashCode();
                MethodDescription methodDescription = this.instrumentedMethod;
                return ((iHashCode + 59) * 59) + (methodDescription != null ? methodDescription.hashCode() : 43);
            }

            public ForField(FieldDescription fieldDescription, MethodDescription methodDescription) {
                this.fieldDescription = fieldDescription;
                this.instrumentedMethod = methodDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation resolve(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                if (!this.fieldDescription.isStatic() && this.instrumentedMethod.isStatic()) {
                    throw new IllegalStateException("Cannot access non-static " + this.fieldDescription + " from " + this.instrumentedMethod);
                }
                StackManipulation[] stackManipulationArr = new StackManipulation[3];
                stackManipulationArr[0] = this.fieldDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
                stackManipulationArr[1] = FieldAccess.forField(this.fieldDescription).read();
                stackManipulationArr[2] = assigner.assign(this.fieldDescription.getType(), parameterDescription.getType(), typing);
                StackManipulation.Compound compound = new StackManipulation.Compound(stackManipulationArr);
                if (compound.isValid()) {
                    return compound;
                }
                throw new IllegalStateException("Cannot assign " + this.fieldDescription + " to " + parameterDescription);
            }

            protected static class Factory implements Factory {
                private final FieldLocator.Factory fieldLocatorFactory;
                private final String name;

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof Factory;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Factory)) {
                        return false;
                    }
                    Factory factory = (Factory) obj;
                    if (!factory.canEqual(this)) {
                        return false;
                    }
                    String str = this.name;
                    String str2 = factory.name;
                    if (str != null ? !str.equals(str2) : str2 != null) {
                        return false;
                    }
                    FieldLocator.Factory factory2 = this.fieldLocatorFactory;
                    FieldLocator.Factory factory3 = factory.fieldLocatorFactory;
                    return factory2 != null ? factory2.equals(factory3) : factory3 == null;
                }

                public int hashCode() {
                    String str = this.name;
                    int iHashCode = str == null ? 43 : str.hashCode();
                    FieldLocator.Factory factory = this.fieldLocatorFactory;
                    return ((iHashCode + 59) * 59) + (factory != null ? factory.hashCode() : 43);
                }

                public Factory(String str, FieldLocator.Factory factory) {
                    this.name = str;
                    this.fieldLocatorFactory = factory;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public List<ArgumentLoader> make(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2) {
                    FieldLocator.Resolution resolutionLocate = this.fieldLocatorFactory.make(typeDescription).locate(this.name);
                    if (!resolutionLocate.isResolved()) {
                        throw new IllegalStateException("Could not locate field '" + this.name + "' on " + typeDescription);
                    }
                    return Collections.singletonList(new ForField(resolutionLocate.getField(), methodDescription));
                }
            }
        }

        public static class ForStackManipulation implements ArgumentLoader, Factory {
            private final StackManipulation stackManipulation;
            private final TypeDefinition typeDefinition;

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof ForStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForStackManipulation)) {
                    return false;
                }
                ForStackManipulation forStackManipulation = (ForStackManipulation) obj;
                if (!forStackManipulation.canEqual(this)) {
                    return false;
                }
                StackManipulation stackManipulation = this.stackManipulation;
                StackManipulation stackManipulation2 = forStackManipulation.stackManipulation;
                if (stackManipulation != null ? !stackManipulation.equals(stackManipulation2) : stackManipulation2 != null) {
                    return false;
                }
                TypeDefinition typeDefinition = this.typeDefinition;
                TypeDefinition typeDefinition2 = forStackManipulation.typeDefinition;
                return typeDefinition != null ? typeDefinition.equals(typeDefinition2) : typeDefinition2 == null;
            }

            public int hashCode() {
                StackManipulation stackManipulation = this.stackManipulation;
                int iHashCode = stackManipulation == null ? 43 : stackManipulation.hashCode();
                TypeDefinition typeDefinition = this.typeDefinition;
                return ((iHashCode + 59) * 59) + (typeDefinition != null ? typeDefinition.hashCode() : 43);
            }

            public ForStackManipulation(StackManipulation stackManipulation, Type type) {
                this(stackManipulation, TypeDefinition.Sort.describe(type));
            }

            public ForStackManipulation(StackManipulation stackManipulation, TypeDefinition typeDefinition) {
                this.stackManipulation = stackManipulation;
                this.typeDefinition = typeDefinition;
            }

            public static Factory of(Object obj) {
                if (obj == null) {
                    return ForNullConstant.INSTANCE;
                }
                if (obj instanceof String) {
                    return new ForStackManipulation(new TextConstant((String) obj), String.class);
                }
                if (obj instanceof Boolean) {
                    return new ForStackManipulation(IntegerConstant.forValue(((Boolean) obj).booleanValue()), Boolean.TYPE);
                }
                if (obj instanceof Byte) {
                    return new ForStackManipulation(IntegerConstant.forValue(((Byte) obj).byteValue()), Byte.TYPE);
                }
                if (obj instanceof Short) {
                    return new ForStackManipulation(IntegerConstant.forValue(((Short) obj).shortValue()), Short.TYPE);
                }
                if (obj instanceof Character) {
                    return new ForStackManipulation(IntegerConstant.forValue(((Character) obj).charValue()), Character.TYPE);
                }
                if (obj instanceof Integer) {
                    return new ForStackManipulation(IntegerConstant.forValue(((Integer) obj).intValue()), Integer.TYPE);
                }
                if (obj instanceof Long) {
                    return new ForStackManipulation(LongConstant.forValue(((Long) obj).longValue()), Long.TYPE);
                }
                if (obj instanceof Float) {
                    return new ForStackManipulation(FloatConstant.forValue(((Float) obj).floatValue()), Float.TYPE);
                }
                if (obj instanceof Double) {
                    return new ForStackManipulation(DoubleConstant.forValue(((Double) obj).doubleValue()), Double.TYPE);
                }
                if (obj instanceof Class) {
                    return new ForStackManipulation(ClassConstant.of(new TypeDescription.ForLoadedType((Class) obj)), Class.class);
                }
                if (JavaType.METHOD_HANDLE.getTypeStub().isInstance(obj)) {
                    return new ForStackManipulation(JavaConstant.MethodHandle.ofLoaded(obj).asStackManipulation(), JavaType.METHOD_HANDLE.getTypeStub());
                }
                if (JavaType.METHOD_TYPE.getTypeStub().isInstance(obj)) {
                    return new ForStackManipulation(JavaConstant.MethodType.ofLoaded(obj).asStackManipulation(), JavaType.METHOD_TYPE.getTypeStub());
                }
                if (obj instanceof Enum) {
                    EnumerationDescription.ForLoadedEnumeration forLoadedEnumeration = new EnumerationDescription.ForLoadedEnumeration((Enum) obj);
                    return new ForStackManipulation(FieldAccess.forEnumeration(forLoadedEnumeration), forLoadedEnumeration.getEnumerationType());
                }
                return new ForInstance.Factory(obj);
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
            public List<ArgumentLoader> make(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2) {
                return Collections.singletonList(this);
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation resolve(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation stackManipulationAssign = assigner.assign(this.typeDefinition.asGenericType(), parameterDescription.getType(), typing);
                if (!stackManipulationAssign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + parameterDescription + " to " + this.typeDefinition);
                }
                return new StackManipulation.Compound(this.stackManipulation, stackManipulationAssign);
            }
        }
    }

    protected interface TargetHandler extends InstrumentedType.Prepareable {
        StackManipulation resolve(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing);

        public enum ForSelfOrStaticInvocation implements TargetHandler {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler
            public StackManipulation resolve(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation[] stackManipulationArr = new StackManipulation[2];
                stackManipulationArr[0] = methodDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
                stackManipulationArr[1] = methodDescription.isConstructor() ? Duplication.SINGLE : StackManipulation.Trivial.INSTANCE;
                return new StackManipulation.Compound(stackManipulationArr);
            }
        }

        public enum ForConstructingInvocation implements TargetHandler {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler
            public StackManipulation resolve(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing) {
                return new StackManipulation.Compound(TypeCreation.of(methodDescription.getDeclaringType().asErasure()), Duplication.SINGLE);
            }
        }

        public static class ForValue implements TargetHandler {
            private static final String FIELD_PREFIX = "invocationTarget";
            private final TypeDescription.Generic fieldType;
            private final String name = String.format("%s$%s", FIELD_PREFIX, RandomString.make());
            private final Object target;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForValue;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForValue)) {
                    return false;
                }
                ForValue forValue = (ForValue) obj;
                if (!forValue.canEqual(this)) {
                    return false;
                }
                Object obj2 = this.target;
                Object obj3 = forValue.target;
                if (obj2 != null ? !obj2.equals(obj3) : obj3 != null) {
                    return false;
                }
                TypeDescription.Generic generic = this.fieldType;
                TypeDescription.Generic generic2 = forValue.fieldType;
                return generic != null ? generic.equals(generic2) : generic2 == null;
            }

            public int hashCode() {
                Object obj = this.target;
                int iHashCode = obj == null ? 43 : obj.hashCode();
                TypeDescription.Generic generic = this.fieldType;
                return ((iHashCode + 59) * 59) + (generic != null ? generic.hashCode() : 43);
            }

            protected ForValue(Object obj, TypeDescription.Generic generic) {
                this.target = obj;
                this.fieldType = generic;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler
            public StackManipulation resolve(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation stackManipulationAssign = assigner.assign(this.fieldType, methodDescription.getDeclaringType().asGenericType(), typing);
                if (!stackManipulationAssign.isValid()) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.fieldType);
                }
                return new StackManipulation.Compound(FieldAccess.forField((FieldDescription.InDefinedShape) typeDescription.getDeclaredFields().filter(ElementMatchers.named(this.name)).getOnly()).read(), stackManipulationAssign);
            }

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType.withField(new FieldDescription.Token(this.name, 4105, this.fieldType)).withInitializer(new LoadedTypeInitializer.ForStaticField(this.name, this.target));
            }
        }

        public static class ForField implements TargetHandler {
            private final FieldLocator.Factory fieldLocatorFactory;
            private final String name;

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof ForField;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForField)) {
                    return false;
                }
                ForField forField = (ForField) obj;
                if (!forField.canEqual(this)) {
                    return false;
                }
                String str = this.name;
                String str2 = forField.name;
                if (str != null ? !str.equals(str2) : str2 != null) {
                    return false;
                }
                FieldLocator.Factory factory = this.fieldLocatorFactory;
                FieldLocator.Factory factory2 = forField.fieldLocatorFactory;
                return factory != null ? factory.equals(factory2) : factory2 == null;
            }

            public int hashCode() {
                String str = this.name;
                int iHashCode = str == null ? 43 : str.hashCode();
                FieldLocator.Factory factory = this.fieldLocatorFactory;
                return ((iHashCode + 59) * 59) + (factory != null ? factory.hashCode() : 43);
            }

            protected ForField(String str, FieldLocator.Factory factory) {
                this.name = str;
                this.fieldLocatorFactory = factory;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler
            public StackManipulation resolve(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing) {
                FieldLocator.Resolution resolutionLocate = this.fieldLocatorFactory.make(typeDescription).locate(this.name);
                if (!resolutionLocate.isResolved()) {
                    throw new IllegalStateException("Could not locate field name " + this.name + " on " + typeDescription);
                }
                if (!resolutionLocate.getField().isStatic() && !typeDescription.isAssignableTo(resolutionLocate.getField().getDeclaringType().asErasure())) {
                    throw new IllegalStateException("Cannot access " + resolutionLocate.getField() + " from " + typeDescription);
                }
                StackManipulation stackManipulationAssign = assigner.assign(resolutionLocate.getField().getType(), methodDescription.getDeclaringType().asGenericType(), typing);
                if (!stackManipulationAssign.isValid()) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + resolutionLocate.getField());
                }
                StackManipulation[] stackManipulationArr = new StackManipulation[3];
                stackManipulationArr[0] = methodDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
                stackManipulationArr[1] = FieldAccess.forField(resolutionLocate.getField()).read();
                stackManipulationArr[2] = stackManipulationAssign;
                return new StackManipulation.Compound(stackManipulationArr);
            }
        }

        public static class ForMethodParameter implements TargetHandler {
            private final int index;

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof ForMethodParameter;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForMethodParameter)) {
                    return false;
                }
                ForMethodParameter forMethodParameter = (ForMethodParameter) obj;
                return forMethodParameter.canEqual(this) && this.index == forMethodParameter.index;
            }

            public int hashCode() {
                return 59 + this.index;
            }

            protected ForMethodParameter(int i) {
                this.index = i;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler
            public StackManipulation resolve(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing) {
                if (methodDescription2.getParameters().size() < this.index) {
                    throw new IllegalArgumentException(methodDescription2 + " does not have a parameter with index " + this.index);
                }
                ParameterDescription parameterDescription = (ParameterDescription) methodDescription2.getParameters().get(this.index);
                StackManipulation stackManipulationAssign = assigner.assign(parameterDescription.getType(), methodDescription.getDeclaringType().asGenericType(), typing);
                if (!stackManipulationAssign.isValid()) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + parameterDescription.getType());
                }
                return new StackManipulation.Compound(MethodVariableAccess.load(parameterDescription), stackManipulationAssign);
            }
        }
    }

    protected interface MethodInvoker {
        StackManipulation invoke(MethodDescription methodDescription, Implementation.Target target);

        public enum ForContextualInvocation implements MethodInvoker {
            INSTANCE;

            @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker
            public StackManipulation invoke(MethodDescription methodDescription, Implementation.Target target) {
                if (methodDescription.isVirtual() && !methodDescription.isInvokableOn(target.getInstrumentedType())) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + target.getInstrumentedType());
                }
                if (!methodDescription.isVisibleTo(target.getInstrumentedType())) {
                    throw new IllegalStateException(methodDescription + " is not visible to " + target.getInstrumentedType());
                }
                if (methodDescription.isVirtual()) {
                    return MethodInvocation.invoke(methodDescription).virtual(target.getInstrumentedType());
                }
                return MethodInvocation.invoke(methodDescription);
            }
        }

        public static class ForVirtualInvocation implements MethodInvoker {
            private final TypeDescription typeDescription;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForVirtualInvocation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForVirtualInvocation)) {
                    return false;
                }
                ForVirtualInvocation forVirtualInvocation = (ForVirtualInvocation) obj;
                if (!forVirtualInvocation.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.typeDescription;
                TypeDescription typeDescription2 = forVirtualInvocation.typeDescription;
                return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.typeDescription;
                return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
            }

            protected ForVirtualInvocation(TypeDescription typeDescription) {
                this.typeDescription = typeDescription;
            }

            protected ForVirtualInvocation(Class<?> cls) {
                this(new TypeDescription.ForLoadedType(cls));
            }

            @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker
            public StackManipulation invoke(MethodDescription methodDescription, Implementation.Target target) {
                if (!methodDescription.isVirtual()) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " virtually");
                }
                if (!methodDescription.isInvokableOn(this.typeDescription.asErasure())) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.typeDescription);
                }
                if (!this.typeDescription.asErasure().isAccessibleTo(target.getInstrumentedType())) {
                    throw new IllegalStateException(this.typeDescription + " is not accessible to " + target.getInstrumentedType());
                }
                return MethodInvocation.invoke(methodDescription).virtual(this.typeDescription.asErasure());
            }

            public enum WithImplicitType implements MethodInvoker {
                INSTANCE;

                @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker
                public StackManipulation invoke(MethodDescription methodDescription, Implementation.Target target) {
                    if (!methodDescription.isVirtual()) {
                        throw new IllegalStateException("Cannot invoke " + methodDescription + " virtually");
                    }
                    return MethodInvocation.invoke(methodDescription);
                }
            }
        }

        public enum ForSuperMethodInvocation implements MethodInvoker {
            INSTANCE;

            @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker
            public StackManipulation invoke(MethodDescription methodDescription, Implementation.Target target) {
                if (target.getInstrumentedType().getSuperClass() == null) {
                    throw new IllegalStateException("Cannot invoke super method for " + target.getInstrumentedType());
                }
                if (!methodDescription.isInvokableOn(target.getOriginType().asErasure())) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " as super method of " + target.getInstrumentedType());
                }
                Implementation.SpecialMethodInvocation specialMethodInvocationInvokeDominant = target.invokeDominant(methodDescription.asSignatureToken());
                if (specialMethodInvocationInvokeDominant.isValid()) {
                    return specialMethodInvocationInvokeDominant;
                }
                throw new IllegalStateException("Cannot invoke " + methodDescription + " as a super method");
            }
        }

        public enum ForDefaultMethodInvocation implements MethodInvoker {
            INSTANCE;

            @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker
            public StackManipulation invoke(MethodDescription methodDescription, Implementation.Target target) {
                if (!methodDescription.isInvokableOn(target.getInstrumentedType())) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " as default method of " + target.getInstrumentedType());
                }
                Implementation.SpecialMethodInvocation specialMethodInvocationInvokeDefault = target.invokeDefault(methodDescription.asSignatureToken(), methodDescription.getDeclaringType().asErasure());
                if (specialMethodInvocationInvokeDefault.isValid()) {
                    return specialMethodInvocationInvokeDefault;
                }
                throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + target.getInstrumentedType());
            }
        }
    }

    public static class WithoutSpecifiedTarget extends MethodCall {
        protected WithoutSpecifiedTarget(MethodLocator methodLocator) {
            super(methodLocator, TargetHandler.ForSelfOrStaticInvocation.INSTANCE, Collections.emptyList(), MethodInvoker.ForContextualInvocation.INSTANCE, TerminationHandler.RETURNING, Assigner.DEFAULT, Assigner.Typing.STATIC);
        }

        public MethodCall on(Object obj) {
            return on(obj, obj.getClass());
        }

        public <T> MethodCall on(T t, Class<? super T> cls) {
            return new MethodCall(this.methodLocator, new TargetHandler.ForValue(t, new TypeDescription.Generic.OfNonGenericType.ForLoadedType(cls)), this.argumentLoaders, new MethodInvoker.ForVirtualInvocation(cls), this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onArgument(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("An argument index cannot be negative: " + i);
            }
            return new MethodCall(this.methodLocator, new TargetHandler.ForMethodParameter(i), this.argumentLoaders, MethodInvoker.ForVirtualInvocation.WithImplicitType.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onField(String str) {
            return onField(str, FieldLocator.ForClassHierarchy.Factory.INSTANCE);
        }

        public MethodCall onField(String str, FieldLocator.Factory factory) {
            return new MethodCall(this.methodLocator, new TargetHandler.ForField(str, factory), this.argumentLoaders, MethodInvoker.ForVirtualInvocation.WithImplicitType.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onSuper() {
            return new MethodCall(this.methodLocator, TargetHandler.ForSelfOrStaticInvocation.INSTANCE, this.argumentLoaders, MethodInvoker.ForSuperMethodInvocation.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onDefault() {
            return new MethodCall(this.methodLocator, TargetHandler.ForSelfOrStaticInvocation.INSTANCE, this.argumentLoaders, MethodInvoker.ForDefaultMethodInvocation.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }
    }

    protected class Appender implements ByteCodeAppender {
        private final Implementation.Target implementationTarget;

        protected Appender(Implementation.Target target) {
            this.implementationTarget = target;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            MethodDescription methodDescriptionResolve = MethodCall.this.methodLocator.resolve(this.implementationTarget.getInstrumentedType(), methodDescription);
            ArrayList arrayList = new ArrayList(MethodCall.this.argumentLoaders.size());
            Iterator<ArgumentLoader.Factory> it = MethodCall.this.argumentLoaders.iterator();
            while (it.hasNext()) {
                arrayList.addAll(it.next().make(this.implementationTarget.getInstrumentedType(), methodDescription, methodDescriptionResolve));
            }
            ParameterList<?> parameters = methodDescriptionResolve.getParameters();
            Iterator it2 = parameters.iterator();
            if (parameters.size() != arrayList.size()) {
                throw new IllegalStateException(methodDescriptionResolve + " does not take " + arrayList.size() + " arguments");
            }
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                arrayList2.add(((ArgumentLoader) it3.next()).resolve((ParameterDescription) it2.next(), MethodCall.this.assigner, MethodCall.this.typing));
            }
            return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodCall.this.targetHandler.resolve(methodDescriptionResolve, methodDescription, this.implementationTarget.getInstrumentedType(), MethodCall.this.assigner, MethodCall.this.typing), new StackManipulation.Compound(arrayList2), MethodCall.this.methodInvoker.invoke(methodDescriptionResolve, this.implementationTarget), MethodCall.this.terminationHandler.resolve(methodDescriptionResolve, methodDescription, MethodCall.this.assigner, MethodCall.this.typing)).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
        }

        private MethodCall getOuter() {
            return MethodCall.this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Appender appender = (Appender) obj;
            return this.implementationTarget.equals(appender.implementationTarget) && MethodCall.this.equals(appender.getOuter());
        }

        public int hashCode() {
            return this.implementationTarget.hashCode() + (MethodCall.this.hashCode() * 31);
        }
    }
}
