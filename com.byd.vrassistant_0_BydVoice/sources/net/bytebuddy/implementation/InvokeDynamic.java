package net.bytebuddy.implementation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
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
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.RandomString;

/* JADX INFO: loaded from: classes3.dex */
public class InvokeDynamic implements Implementation.Composable {
    protected final Assigner assigner;
    protected final MethodDescription.InDefinedShape bootstrapMethod;
    protected final List<?> handleArguments;
    protected final InvocationProvider invocationProvider;
    protected final TerminationHandler terminationHandler;
    protected final Assigner.Typing typing;

    protected enum TerminationHandler {
        RETURNING { // from class: net.bytebuddy.implementation.InvokeDynamic.TerminationHandler.1
            @Override // net.bytebuddy.implementation.InvokeDynamic.TerminationHandler
            protected StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation stackManipulationAssign = assigner.assign(typeDescription.asGenericType(), methodDescription.getReturnType(), typing);
                if (!stackManipulationAssign.isValid()) {
                    throw new IllegalStateException("Cannot return " + typeDescription + " from " + methodDescription);
                }
                return new StackManipulation.Compound(stackManipulationAssign, MethodReturn.of(methodDescription.getReturnType()));
            }
        },
        DROPPING { // from class: net.bytebuddy.implementation.InvokeDynamic.TerminationHandler.2
            @Override // net.bytebuddy.implementation.InvokeDynamic.TerminationHandler
            protected StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing) {
                TypeDefinition returnType;
                if (methodDescription.isConstructor()) {
                    returnType = methodDescription.getDeclaringType();
                } else {
                    returnType = methodDescription.getReturnType();
                }
                return Removal.of(returnType);
            }
        };

        protected abstract StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing);
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof InvokeDynamic;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InvokeDynamic)) {
            return false;
        }
        InvokeDynamic invokeDynamic = (InvokeDynamic) obj;
        if (!invokeDynamic.canEqual(this)) {
            return false;
        }
        MethodDescription.InDefinedShape inDefinedShape = this.bootstrapMethod;
        MethodDescription.InDefinedShape inDefinedShape2 = invokeDynamic.bootstrapMethod;
        if (inDefinedShape != null ? !inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 != null) {
            return false;
        }
        List<?> list = this.handleArguments;
        List<?> list2 = invokeDynamic.handleArguments;
        if (list != null ? !list.equals(list2) : list2 != null) {
            return false;
        }
        InvocationProvider invocationProvider = getInvocationProvider();
        InvocationProvider invocationProvider2 = invokeDynamic.getInvocationProvider();
        if (invocationProvider != null ? !invocationProvider.equals(invocationProvider2) : invocationProvider2 != null) {
            return false;
        }
        TerminationHandler terminationHandler = this.terminationHandler;
        TerminationHandler terminationHandler2 = invokeDynamic.terminationHandler;
        if (terminationHandler != null ? !terminationHandler.equals(terminationHandler2) : terminationHandler2 != null) {
            return false;
        }
        Assigner assigner = this.assigner;
        Assigner assigner2 = invokeDynamic.assigner;
        if (assigner != null ? !assigner.equals(assigner2) : assigner2 != null) {
            return false;
        }
        Assigner.Typing typing = this.typing;
        Assigner.Typing typing2 = invokeDynamic.typing;
        return typing != null ? typing.equals(typing2) : typing2 == null;
    }

    public int hashCode() {
        MethodDescription.InDefinedShape inDefinedShape = this.bootstrapMethod;
        int iHashCode = inDefinedShape == null ? 43 : inDefinedShape.hashCode();
        List<?> list = this.handleArguments;
        int iHashCode2 = ((iHashCode + 59) * 59) + (list == null ? 43 : list.hashCode());
        InvocationProvider invocationProvider = getInvocationProvider();
        int iHashCode3 = (iHashCode2 * 59) + (invocationProvider == null ? 43 : invocationProvider.hashCode());
        TerminationHandler terminationHandler = this.terminationHandler;
        int iHashCode4 = (iHashCode3 * 59) + (terminationHandler == null ? 43 : terminationHandler.hashCode());
        Assigner assigner = this.assigner;
        int iHashCode5 = (iHashCode4 * 59) + (assigner == null ? 43 : assigner.hashCode());
        Assigner.Typing typing = this.typing;
        return (iHashCode5 * 59) + (typing != null ? typing.hashCode() : 43);
    }

    protected InvokeDynamic(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing) {
        this.bootstrapMethod = inDefinedShape;
        this.handleArguments = list;
        this.invocationProvider = invocationProvider;
        this.terminationHandler = terminationHandler;
        this.assigner = assigner;
        this.typing = typing;
    }

    public static WithImplicitTarget bootstrap(Method method, Object... objArr) {
        return bootstrap(new MethodDescription.ForLoadedMethod(method), objArr);
    }

    public static WithImplicitTarget bootstrap(Method method, List<?> list) {
        return bootstrap(new MethodDescription.ForLoadedMethod(method), list);
    }

    public static WithImplicitTarget bootstrap(Constructor<?> constructor, Object... objArr) {
        return bootstrap(new MethodDescription.ForLoadedConstructor(constructor), objArr);
    }

    public static WithImplicitTarget bootstrap(Constructor<?> constructor, List<?> list) {
        return bootstrap(new MethodDescription.ForLoadedConstructor(constructor), list);
    }

    public static WithImplicitTarget bootstrap(MethodDescription.InDefinedShape inDefinedShape, Object... objArr) {
        return bootstrap(inDefinedShape, (List<?>) Arrays.asList(objArr));
    }

    public static WithImplicitTarget bootstrap(MethodDescription.InDefinedShape inDefinedShape, List<?> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Object objOfLoaded : list) {
            if (objOfLoaded instanceof Class) {
                objOfLoaded = new TypeDescription.ForLoadedType((Class) objOfLoaded);
            } else if (JavaType.METHOD_HANDLE.getTypeStub().isInstance(objOfLoaded)) {
                objOfLoaded = JavaConstant.MethodHandle.ofLoaded(objOfLoaded);
            } else if (JavaType.METHOD_TYPE.getTypeStub().isInstance(objOfLoaded)) {
                objOfLoaded = JavaConstant.MethodType.ofLoaded(objOfLoaded);
            }
            arrayList.add(objOfLoaded);
        }
        if (!inDefinedShape.isBootstrap(arrayList)) {
            throw new IllegalArgumentException("Not a valid bootstrap method " + inDefinedShape + " for " + arrayList);
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (Object objAsConstantPoolValue : arrayList) {
            if (objAsConstantPoolValue instanceof TypeDescription) {
                objAsConstantPoolValue = Type.getType(((TypeDescription) objAsConstantPoolValue).getDescriptor());
            } else if (objAsConstantPoolValue instanceof JavaConstant) {
                objAsConstantPoolValue = ((JavaConstant) objAsConstantPoolValue).asConstantPoolValue();
            }
            arrayList2.add(objAsConstantPoolValue);
        }
        return new WithImplicitTarget(inDefinedShape, arrayList2, new InvocationProvider.Default(), TerminationHandler.RETURNING, Assigner.DEFAULT, Assigner.Typing.STATIC);
    }

    public InvokeDynamic withBooleanValue(boolean... zArr) {
        ArrayList arrayList = new ArrayList(zArr.length);
        for (boolean z : zArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForBooleanConstant(z));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withByteValue(byte... bArr) {
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte b : bArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForByteConstant(b));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withShortValue(short... sArr) {
        ArrayList arrayList = new ArrayList(sArr.length);
        for (short s : sArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForShortConstant(s));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withCharacterValue(char... cArr) {
        ArrayList arrayList = new ArrayList(cArr.length);
        for (char c : cArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForCharacterConstant(c));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withIntegerValue(int... iArr) {
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForIntegerConstant(i));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withLongValue(long... jArr) {
        ArrayList arrayList = new ArrayList(jArr.length);
        for (long j : jArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForLongConstant(j));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withFloatValue(float... fArr) {
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float f : fArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForFloatConstant(f));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withDoubleValue(double... dArr) {
        ArrayList arrayList = new ArrayList(dArr.length);
        for (double d : dArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForDoubleConstant(d));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withValue(Object... objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(InvocationProvider.ArgumentProvider.ConstantPoolWrapper.of(obj));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public WithImplicitType withReference(Object obj) {
        return new WithImplicitType.OfInstance(this.bootstrapMethod, this.handleArguments, this.invocationProvider, this.terminationHandler, this.assigner, this.typing, obj);
    }

    public InvokeDynamic withReference(Object... objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(InvocationProvider.ArgumentProvider.ForInstance.of(obj));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withType(TypeDescription... typeDescriptionArr) {
        ArrayList arrayList = new ArrayList(typeDescriptionArr.length);
        for (TypeDescription typeDescription : typeDescriptionArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForClassConstant(typeDescription));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withEnumeration(EnumerationDescription... enumerationDescriptionArr) {
        ArrayList arrayList = new ArrayList(enumerationDescriptionArr.length);
        for (EnumerationDescription enumerationDescription : enumerationDescriptionArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForEnumerationValue(enumerationDescription));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withInstance(JavaConstant... javaConstantArr) {
        ArrayList arrayList = new ArrayList(javaConstantArr.length);
        for (JavaConstant javaConstant : javaConstantArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForJavaConstant(javaConstant));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withNullValue(Class<?>... clsArr) {
        return withNullValue((TypeDescription[]) new TypeList.ForLoadedTypes(clsArr).toArray(new TypeDescription[clsArr.length]));
    }

    public InvokeDynamic withNullValue(TypeDescription... typeDescriptionArr) {
        ArrayList arrayList = new ArrayList(typeDescriptionArr.length);
        for (TypeDescription typeDescription : typeDescriptionArr) {
            if (typeDescription.isPrimitive()) {
                throw new IllegalArgumentException("Cannot assign null to primitive type: " + typeDescription);
            }
            arrayList.add(new InvocationProvider.ArgumentProvider.ForNullValue(typeDescription));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withArgument(int... iArr) {
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            if (i < 0) {
                throw new IllegalArgumentException("Method parameter indices cannot be negative: " + i);
            }
            arrayList.add(new InvocationProvider.ArgumentProvider.ForMethodParameter(i));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public WithImplicitType withArgument(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Method parameter indices cannot be negative: " + i);
        }
        return new WithImplicitType.OfArgument(this.bootstrapMethod, this.handleArguments, this.invocationProvider, this.terminationHandler, this.assigner, this.typing, i);
    }

    public InvokeDynamic withThis(Class<?>... clsArr) {
        return withThis((TypeDescription[]) new TypeList.ForLoadedTypes(clsArr).toArray(new TypeDescription[clsArr.length]));
    }

    public InvokeDynamic withThis(TypeDescription... typeDescriptionArr) {
        ArrayList arrayList = new ArrayList(typeDescriptionArr.length);
        for (TypeDescription typeDescription : typeDescriptionArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForThisInstance(typeDescription));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withMethodArguments() {
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(InvocationProvider.ArgumentProvider.ForInterceptedMethodParameters.INSTANCE), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withImplicitAndMethodArguments() {
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(InvocationProvider.ArgumentProvider.ForInterceptedMethodInstanceAndParameters.INSTANCE), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withField(String... strArr) {
        return withField(FieldLocator.ForClassHierarchy.Factory.INSTANCE, strArr);
    }

    public InvokeDynamic withField(FieldLocator.Factory factory, String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForField(str, factory));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public WithImplicitType withField(String str) {
        return withField(str, FieldLocator.ForClassHierarchy.Factory.INSTANCE);
    }

    public WithImplicitType withField(String str, FieldLocator.Factory factory) {
        return new WithImplicitType.OfField(this.bootstrapMethod, this.handleArguments, this.invocationProvider, this.terminationHandler, this.assigner, this.typing, str, factory);
    }

    public Implementation.Composable withAssigner(Assigner assigner, Assigner.Typing typing) {
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider, this.terminationHandler, assigner, typing);
    }

    @Override // net.bytebuddy.implementation.Implementation.Composable
    public Implementation andThen(Implementation implementation) {
        return new Implementation.Compound(new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider, TerminationHandler.DROPPING, this.assigner, this.typing), implementation);
    }

    @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return this.invocationProvider.prepare(instrumentedType);
    }

    @Override // net.bytebuddy.implementation.Implementation
    public ByteCodeAppender appender(Implementation.Target target) {
        return new Appender(target.getInstrumentedType());
    }

    protected InvocationProvider getInvocationProvider() {
        return this.invocationProvider;
    }

    protected interface InvocationProvider {
        InvocationProvider appendArgument(ArgumentProvider argumentProvider);

        InvocationProvider appendArguments(List<ArgumentProvider> list);

        Target make(MethodDescription methodDescription);

        InstrumentedType prepare(InstrumentedType instrumentedType);

        InvocationProvider withNameProvider(NameProvider nameProvider);

        InvocationProvider withReturnTypeProvider(ReturnTypeProvider returnTypeProvider);

        InvocationProvider withoutArguments();

        public interface Target {
            Resolved resolve(TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing);

            public interface Resolved {
                String getInternalName();

                List<TypeDescription> getParameterTypes();

                TypeDescription getReturnType();

                StackManipulation getStackManipulation();

                public static class Simple implements Resolved {
                    private final String internalName;
                    private final List<TypeDescription> parameterTypes;
                    private final TypeDescription returnType;
                    private final StackManipulation stackManipulation;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Simple;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Simple)) {
                            return false;
                        }
                        Simple simple = (Simple) obj;
                        if (!simple.canEqual(this)) {
                            return false;
                        }
                        StackManipulation stackManipulation = getStackManipulation();
                        StackManipulation stackManipulation2 = simple.getStackManipulation();
                        if (stackManipulation != null ? !stackManipulation.equals(stackManipulation2) : stackManipulation2 != null) {
                            return false;
                        }
                        String internalName = getInternalName();
                        String internalName2 = simple.getInternalName();
                        if (internalName != null ? !internalName.equals(internalName2) : internalName2 != null) {
                            return false;
                        }
                        TypeDescription returnType = getReturnType();
                        TypeDescription returnType2 = simple.getReturnType();
                        if (returnType != null ? !returnType.equals(returnType2) : returnType2 != null) {
                            return false;
                        }
                        List<TypeDescription> parameterTypes = getParameterTypes();
                        List<TypeDescription> parameterTypes2 = simple.getParameterTypes();
                        return parameterTypes != null ? parameterTypes.equals(parameterTypes2) : parameterTypes2 == null;
                    }

                    public int hashCode() {
                        StackManipulation stackManipulation = getStackManipulation();
                        int iHashCode = stackManipulation == null ? 43 : stackManipulation.hashCode();
                        String internalName = getInternalName();
                        int iHashCode2 = ((iHashCode + 59) * 59) + (internalName == null ? 43 : internalName.hashCode());
                        TypeDescription returnType = getReturnType();
                        int iHashCode3 = (iHashCode2 * 59) + (returnType == null ? 43 : returnType.hashCode());
                        List<TypeDescription> parameterTypes = getParameterTypes();
                        return (iHashCode3 * 59) + (parameterTypes != null ? parameterTypes.hashCode() : 43);
                    }

                    public Simple(StackManipulation stackManipulation, String str, TypeDescription typeDescription, List<TypeDescription> list) {
                        this.stackManipulation = stackManipulation;
                        this.internalName = str;
                        this.returnType = typeDescription;
                        this.parameterTypes = list;
                    }

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.Target.Resolved
                    public StackManipulation getStackManipulation() {
                        return this.stackManipulation;
                    }

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.Target.Resolved
                    public TypeDescription getReturnType() {
                        return this.returnType;
                    }

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.Target.Resolved
                    public String getInternalName() {
                        return this.internalName;
                    }

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.Target.Resolved
                    public List<TypeDescription> getParameterTypes() {
                        return this.parameterTypes;
                    }
                }
            }

            public static class ForMethodDescription implements Target, Resolved {
                private final MethodDescription.InDefinedShape methodDescription;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.Target
                public Resolved resolve(TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing) {
                    return this;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForMethodDescription;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForMethodDescription)) {
                        return false;
                    }
                    ForMethodDescription forMethodDescription = (ForMethodDescription) obj;
                    if (!forMethodDescription.canEqual(this)) {
                        return false;
                    }
                    MethodDescription.InDefinedShape inDefinedShape = this.methodDescription;
                    MethodDescription.InDefinedShape inDefinedShape2 = forMethodDescription.methodDescription;
                    return inDefinedShape != null ? inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 == null;
                }

                public int hashCode() {
                    MethodDescription.InDefinedShape inDefinedShape = this.methodDescription;
                    return 59 + (inDefinedShape == null ? 43 : inDefinedShape.hashCode());
                }

                protected ForMethodDescription(MethodDescription.InDefinedShape inDefinedShape) {
                    this.methodDescription = inDefinedShape;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.Target.Resolved
                public String getInternalName() {
                    return this.methodDescription.getInternalName();
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.Target.Resolved
                public TypeDescription getReturnType() {
                    return this.methodDescription.getReturnType().asErasure();
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.Target.Resolved
                public StackManipulation getStackManipulation() {
                    return MethodVariableAccess.allArgumentsOf(this.methodDescription).prependThisReference();
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.Target.Resolved
                public List<TypeDescription> getParameterTypes() {
                    if (this.methodDescription.isStatic()) {
                        return this.methodDescription.getParameters().asTypeList().asErasures();
                    }
                    return CompoundList.of(this.methodDescription.getDeclaringType().asErasure(), this.methodDescription.getParameters().asTypeList().asErasures());
                }
            }
        }

        public interface ArgumentProvider {
            InstrumentedType prepare(InstrumentedType instrumentedType);

            Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing);

            public enum ForInterceptedMethodInstanceAndParameters implements ArgumentProvider {
                INSTANCE;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    List listOf;
                    StackManipulation stackManipulationPrependThisReference = MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference();
                    if (methodDescription.isStatic()) {
                        listOf = methodDescription.getParameters().asTypeList().asErasures();
                    } else {
                        listOf = CompoundList.of(methodDescription.getDeclaringType().asErasure(), methodDescription.getParameters().asTypeList().asErasures());
                    }
                    return new Resolved.Simple(stackManipulationPrependThisReference, (List<TypeDescription>) listOf);
                }
            }

            public enum ForInterceptedMethodParameters implements ArgumentProvider {
                INSTANCE;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(MethodVariableAccess.allArgumentsOf(methodDescription), methodDescription.getParameters().asTypeList().asErasures());
                }
            }

            public enum ConstantPoolWrapper {
                BOOLEAN(Boolean.TYPE, Boolean.class) { // from class: net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper.1
                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper
                    protected ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(IntegerConstant.forValue(((Boolean) obj).booleanValue()));
                    }
                },
                BYTE(Byte.TYPE, Byte.class) { // from class: net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper.2
                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper
                    protected ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(IntegerConstant.forValue(((Byte) obj).byteValue()));
                    }
                },
                SHORT(Short.TYPE, Short.class) { // from class: net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper.3
                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper
                    protected ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(IntegerConstant.forValue(((Short) obj).shortValue()));
                    }
                },
                CHARACTER(Character.TYPE, Character.class) { // from class: net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper.4
                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper
                    protected ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(IntegerConstant.forValue(((Character) obj).charValue()));
                    }
                },
                INTEGER(Integer.TYPE, Integer.class) { // from class: net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper.5
                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper
                    protected ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(IntegerConstant.forValue(((Integer) obj).intValue()));
                    }
                },
                LONG(Long.TYPE, Long.class) { // from class: net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper.6
                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper
                    protected ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(LongConstant.forValue(((Long) obj).longValue()));
                    }
                },
                FLOAT(Float.TYPE, Float.class) { // from class: net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper.7
                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper
                    protected ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(FloatConstant.forValue(((Float) obj).floatValue()));
                    }
                },
                DOUBLE(Double.TYPE, Double.class) { // from class: net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper.8
                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ConstantPoolWrapper
                    protected ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(DoubleConstant.forValue(((Double) obj).doubleValue()));
                    }
                };

                private final TypeDescription primitiveType;
                private final TypeDescription wrapperType;

                protected abstract ArgumentProvider make(Object obj);

                ConstantPoolWrapper(Class cls, Class cls2) {
                    this.primitiveType = new TypeDescription.ForLoadedType(cls);
                    this.wrapperType = new TypeDescription.ForLoadedType(cls2);
                }

                public static ArgumentProvider of(Object obj) {
                    if (obj instanceof Boolean) {
                        return BOOLEAN.make(obj);
                    }
                    if (obj instanceof Byte) {
                        return BYTE.make(obj);
                    }
                    if (obj instanceof Short) {
                        return SHORT.make(obj);
                    }
                    if (obj instanceof Character) {
                        return CHARACTER.make(obj);
                    }
                    if (obj instanceof Integer) {
                        return INTEGER.make(obj);
                    }
                    if (obj instanceof Long) {
                        return LONG.make(obj);
                    }
                    if (obj instanceof Float) {
                        return FLOAT.make(obj);
                    }
                    if (obj instanceof Double) {
                        return DOUBLE.make(obj);
                    }
                    if (obj instanceof String) {
                        return new ForStringConstant((String) obj);
                    }
                    if (obj instanceof Class) {
                        return new ForClassConstant(new TypeDescription.ForLoadedType((Class) obj));
                    }
                    if (obj instanceof Enum) {
                        return new ForEnumerationValue(new EnumerationDescription.ForLoadedEnumeration((Enum) obj));
                    }
                    if (JavaType.METHOD_HANDLE.getTypeStub().isInstance(obj)) {
                        return new ForJavaConstant(JavaConstant.MethodHandle.ofLoaded(obj));
                    }
                    if (JavaType.METHOD_TYPE.getTypeStub().isInstance(obj)) {
                        return new ForJavaConstant(JavaConstant.MethodType.ofLoaded(obj));
                    }
                    return ForInstance.of(obj);
                }

                protected class WrappingArgumentProvider implements ArgumentProvider {
                    private final StackManipulation stackManipulation;

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                    public InstrumentedType prepare(InstrumentedType instrumentedType) {
                        return instrumentedType;
                    }

                    protected WrappingArgumentProvider(StackManipulation stackManipulation) {
                        this.stackManipulation = stackManipulation;
                    }

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                    public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                        return new Resolved.Simple(new StackManipulation.Compound(this.stackManipulation, assigner.assign(ConstantPoolWrapper.this.primitiveType.asGenericType(), ConstantPoolWrapper.this.wrapperType.asGenericType(), typing)), ConstantPoolWrapper.this.wrapperType);
                    }

                    public boolean equals(Object obj) {
                        if (this != obj) {
                            if (obj != null && getClass() == obj.getClass()) {
                                WrappingArgumentProvider wrappingArgumentProvider = (WrappingArgumentProvider) obj;
                                if (!ConstantPoolWrapper.this.equals(wrappingArgumentProvider.getOuter()) || !this.stackManipulation.equals(wrappingArgumentProvider.stackManipulation)) {
                                }
                            }
                            return false;
                        }
                        return true;
                    }

                    private ConstantPoolWrapper getOuter() {
                        return ConstantPoolWrapper.this;
                    }

                    public int hashCode() {
                        return this.stackManipulation.hashCode() + (ConstantPoolWrapper.this.hashCode() * 31);
                    }
                }
            }

            public interface Resolved {
                StackManipulation getLoadInstruction();

                List<TypeDescription> getLoadedTypes();

                public static class Simple implements Resolved {
                    private final List<TypeDescription> loadedTypes;
                    private final StackManipulation stackManipulation;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Simple;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Simple)) {
                            return false;
                        }
                        Simple simple = (Simple) obj;
                        if (!simple.canEqual(this)) {
                            return false;
                        }
                        StackManipulation stackManipulation = this.stackManipulation;
                        StackManipulation stackManipulation2 = simple.stackManipulation;
                        if (stackManipulation != null ? !stackManipulation.equals(stackManipulation2) : stackManipulation2 != null) {
                            return false;
                        }
                        List<TypeDescription> loadedTypes = getLoadedTypes();
                        List<TypeDescription> loadedTypes2 = simple.getLoadedTypes();
                        return loadedTypes != null ? loadedTypes.equals(loadedTypes2) : loadedTypes2 == null;
                    }

                    public int hashCode() {
                        StackManipulation stackManipulation = this.stackManipulation;
                        int iHashCode = stackManipulation == null ? 43 : stackManipulation.hashCode();
                        List<TypeDescription> loadedTypes = getLoadedTypes();
                        return ((iHashCode + 59) * 59) + (loadedTypes != null ? loadedTypes.hashCode() : 43);
                    }

                    public Simple(StackManipulation stackManipulation, TypeDescription typeDescription) {
                        this(stackManipulation, (List<TypeDescription>) Collections.singletonList(typeDescription));
                    }

                    public Simple(StackManipulation stackManipulation, List<TypeDescription> list) {
                        this.stackManipulation = stackManipulation;
                        this.loadedTypes = list;
                    }

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.Resolved
                    public StackManipulation getLoadInstruction() {
                        return this.stackManipulation;
                    }

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.Resolved
                    public List<TypeDescription> getLoadedTypes() {
                        return this.loadedTypes;
                    }
                }
            }

            public static class ForThisInstance implements ArgumentProvider {
                private final TypeDescription typeDescription;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForThisInstance;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForThisInstance)) {
                        return false;
                    }
                    ForThisInstance forThisInstance = (ForThisInstance) obj;
                    if (!forThisInstance.canEqual(this)) {
                        return false;
                    }
                    TypeDescription typeDescription = this.typeDescription;
                    TypeDescription typeDescription2 = forThisInstance.typeDescription;
                    return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                }

                public int hashCode() {
                    TypeDescription typeDescription = this.typeDescription;
                    return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
                }

                protected ForThisInstance(TypeDescription typeDescription) {
                    this.typeDescription = typeDescription;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    if (methodDescription.isStatic()) {
                        throw new IllegalStateException("Cannot get this instance from static method: " + methodDescription);
                    }
                    if (!typeDescription.isAssignableTo(this.typeDescription)) {
                        throw new IllegalStateException(typeDescription + " is not assignable to " + typeDescription);
                    }
                    return new Resolved.Simple(MethodVariableAccess.loadThis(), this.typeDescription);
                }
            }

            public static class ForInstance implements ArgumentProvider {
                private static final String FIELD_PREFIX = "invokeDynamic";
                private final TypeDescription fieldType;
                private final String name = String.format("%s$%s", FIELD_PREFIX, RandomString.make());
                private final Object value;

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
                    Object obj2 = this.value;
                    Object obj3 = forInstance.value;
                    if (obj2 != null ? !obj2.equals(obj3) : obj3 != null) {
                        return false;
                    }
                    TypeDescription typeDescription = this.fieldType;
                    TypeDescription typeDescription2 = forInstance.fieldType;
                    return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                }

                public int hashCode() {
                    Object obj = this.value;
                    int iHashCode = obj == null ? 43 : obj.hashCode();
                    TypeDescription typeDescription = this.fieldType;
                    return ((iHashCode + 59) * 59) + (typeDescription != null ? typeDescription.hashCode() : 43);
                }

                protected ForInstance(Object obj, TypeDescription typeDescription) {
                    this.value = obj;
                    this.fieldType = typeDescription;
                }

                protected static ArgumentProvider of(Object obj) {
                    return new ForInstance(obj, new TypeDescription.ForLoadedType(obj.getClass()));
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    FieldDescription fieldDescription = (FieldDescription) typeDescription.getDeclaredFields().filter(ElementMatchers.named(this.name)).getOnly();
                    StackManipulation stackManipulationAssign = assigner.assign(fieldDescription.getType(), this.fieldType.asGenericType(), typing);
                    if (!stackManipulationAssign.isValid()) {
                        throw new IllegalStateException("Cannot assign " + fieldDescription + " to " + this.fieldType);
                    }
                    return new Resolved.Simple(new StackManipulation.Compound(FieldAccess.forField(fieldDescription).read(), stackManipulationAssign), fieldDescription.getType().asErasure());
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType.withField(new FieldDescription.Token(this.name, 4105, this.fieldType.asGenericType())).withInitializer(new LoadedTypeInitializer.ForStaticField(this.name, this.value));
                }
            }

            public static class ForField implements ArgumentProvider {
                protected final FieldLocator.Factory fieldLocatorFactory;
                protected final String fieldName;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
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
                    String str = this.fieldName;
                    String str2 = forField.fieldName;
                    if (str != null ? !str.equals(str2) : str2 != null) {
                        return false;
                    }
                    FieldLocator.Factory factory = this.fieldLocatorFactory;
                    FieldLocator.Factory factory2 = forField.fieldLocatorFactory;
                    return factory != null ? factory.equals(factory2) : factory2 == null;
                }

                public int hashCode() {
                    String str = this.fieldName;
                    int iHashCode = str == null ? 43 : str.hashCode();
                    FieldLocator.Factory factory = this.fieldLocatorFactory;
                    return ((iHashCode + 59) * 59) + (factory != null ? factory.hashCode() : 43);
                }

                protected ForField(String str, FieldLocator.Factory factory) {
                    this.fieldName = str;
                    this.fieldLocatorFactory = factory;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    FieldLocator.Resolution resolutionLocate = this.fieldLocatorFactory.make(typeDescription).locate(this.fieldName);
                    if (!resolutionLocate.isResolved()) {
                        throw new IllegalStateException("Cannot find a field " + this.fieldName + " for " + typeDescription);
                    }
                    if (!resolutionLocate.getField().isStatic() && methodDescription.isStatic()) {
                        throw new IllegalStateException("Cannot access non-static " + resolutionLocate.getField() + " from " + methodDescription);
                    }
                    StackManipulation[] stackManipulationArr = new StackManipulation[2];
                    stackManipulationArr[0] = resolutionLocate.getField().isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
                    stackManipulationArr[1] = FieldAccess.forField(resolutionLocate.getField()).read();
                    return doResolve(new StackManipulation.Compound(stackManipulationArr), resolutionLocate.getField().getType(), assigner, typing);
                }

                protected Resolved doResolve(StackManipulation stackManipulation, TypeDescription.Generic generic, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(stackManipulation, generic.asErasure());
                }

                protected static class WithExplicitType extends ForField {
                    private final TypeDescription typeDescription;

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ForField
                    protected boolean canEqual(Object obj) {
                        return obj instanceof WithExplicitType;
                    }

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ForField
                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof WithExplicitType)) {
                            return false;
                        }
                        WithExplicitType withExplicitType = (WithExplicitType) obj;
                        if (!withExplicitType.canEqual(this) || !super.equals(obj)) {
                            return false;
                        }
                        TypeDescription typeDescription = this.typeDescription;
                        TypeDescription typeDescription2 = withExplicitType.typeDescription;
                        return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                    }

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ForField
                    public int hashCode() {
                        int iHashCode = super.hashCode() + 59;
                        TypeDescription typeDescription = this.typeDescription;
                        return (iHashCode * 59) + (typeDescription == null ? 43 : typeDescription.hashCode());
                    }

                    protected WithExplicitType(String str, FieldLocator.Factory factory, TypeDescription typeDescription) {
                        super(str, factory);
                        this.typeDescription = typeDescription;
                    }

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ForField
                    protected Resolved doResolve(StackManipulation stackManipulation, TypeDescription.Generic generic, Assigner assigner, Assigner.Typing typing) {
                        StackManipulation stackManipulationAssign = assigner.assign(generic, this.typeDescription.asGenericType(), typing);
                        if (!stackManipulationAssign.isValid()) {
                            throw new IllegalStateException("Cannot assign " + generic + " to " + this.typeDescription);
                        }
                        return new Resolved.Simple(new StackManipulation.Compound(stackManipulation, stackManipulationAssign), this.typeDescription);
                    }
                }
            }

            public static class ForMethodParameter implements ArgumentProvider {
                protected final int index;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
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

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    ParameterList<?> parameters = methodDescription.getParameters();
                    if (this.index >= parameters.size()) {
                        throw new IllegalStateException("No parameter " + this.index + " for " + methodDescription);
                    }
                    return doResolve(MethodVariableAccess.load((ParameterDescription) parameters.get(this.index)), ((ParameterDescription) parameters.get(this.index)).getType(), assigner, typing);
                }

                protected Resolved doResolve(StackManipulation stackManipulation, TypeDescription.Generic generic, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(stackManipulation, generic.asErasure());
                }

                protected static class WithExplicitType extends ForMethodParameter {
                    private final TypeDescription typeDescription;

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ForMethodParameter
                    protected boolean canEqual(Object obj) {
                        return obj instanceof WithExplicitType;
                    }

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ForMethodParameter
                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof WithExplicitType)) {
                            return false;
                        }
                        WithExplicitType withExplicitType = (WithExplicitType) obj;
                        if (!withExplicitType.canEqual(this) || !super.equals(obj)) {
                            return false;
                        }
                        TypeDescription typeDescription = this.typeDescription;
                        TypeDescription typeDescription2 = withExplicitType.typeDescription;
                        return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                    }

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ForMethodParameter
                    public int hashCode() {
                        int iHashCode = super.hashCode() + 59;
                        TypeDescription typeDescription = this.typeDescription;
                        return (iHashCode * 59) + (typeDescription == null ? 43 : typeDescription.hashCode());
                    }

                    protected WithExplicitType(int i, TypeDescription typeDescription) {
                        super(i);
                        this.typeDescription = typeDescription;
                    }

                    @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider.ForMethodParameter
                    protected Resolved doResolve(StackManipulation stackManipulation, TypeDescription.Generic generic, Assigner assigner, Assigner.Typing typing) {
                        StackManipulation stackManipulationAssign = assigner.assign(generic, this.typeDescription.asGenericType(), typing);
                        if (!stackManipulationAssign.isValid()) {
                            throw new IllegalStateException("Cannot assign " + generic + " to " + this.typeDescription);
                        }
                        return new Resolved.Simple(new StackManipulation.Compound(stackManipulation, stackManipulationAssign), this.typeDescription);
                    }
                }
            }

            public static class ForBooleanConstant implements ArgumentProvider {
                private final boolean value;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForBooleanConstant;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForBooleanConstant)) {
                        return false;
                    }
                    ForBooleanConstant forBooleanConstant = (ForBooleanConstant) obj;
                    return forBooleanConstant.canEqual(this) && this.value == forBooleanConstant.value;
                }

                public int hashCode() {
                    return 59 + (this.value ? 79 : 97);
                }

                protected ForBooleanConstant(boolean z) {
                    this.value = z;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(IntegerConstant.forValue(this.value), new TypeDescription.ForLoadedType(Boolean.TYPE));
                }
            }

            public static class ForByteConstant implements ArgumentProvider {
                private final byte value;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForByteConstant;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForByteConstant)) {
                        return false;
                    }
                    ForByteConstant forByteConstant = (ForByteConstant) obj;
                    return forByteConstant.canEqual(this) && this.value == forByteConstant.value;
                }

                public int hashCode() {
                    return 59 + this.value;
                }

                protected ForByteConstant(byte b) {
                    this.value = b;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(IntegerConstant.forValue(this.value), new TypeDescription.ForLoadedType(Byte.TYPE));
                }
            }

            public static class ForShortConstant implements ArgumentProvider {
                private final short value;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForShortConstant;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForShortConstant)) {
                        return false;
                    }
                    ForShortConstant forShortConstant = (ForShortConstant) obj;
                    return forShortConstant.canEqual(this) && this.value == forShortConstant.value;
                }

                public int hashCode() {
                    return 59 + this.value;
                }

                protected ForShortConstant(short s) {
                    this.value = s;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(IntegerConstant.forValue(this.value), new TypeDescription.ForLoadedType(Short.TYPE));
                }
            }

            public static class ForCharacterConstant implements ArgumentProvider {
                private final char value;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForCharacterConstant;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForCharacterConstant)) {
                        return false;
                    }
                    ForCharacterConstant forCharacterConstant = (ForCharacterConstant) obj;
                    return forCharacterConstant.canEqual(this) && this.value == forCharacterConstant.value;
                }

                public int hashCode() {
                    return ';' + this.value;
                }

                protected ForCharacterConstant(char c) {
                    this.value = c;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(IntegerConstant.forValue(this.value), new TypeDescription.ForLoadedType(Character.TYPE));
                }
            }

            public static class ForIntegerConstant implements ArgumentProvider {
                private final int value;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForIntegerConstant;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForIntegerConstant)) {
                        return false;
                    }
                    ForIntegerConstant forIntegerConstant = (ForIntegerConstant) obj;
                    return forIntegerConstant.canEqual(this) && this.value == forIntegerConstant.value;
                }

                public int hashCode() {
                    return 59 + this.value;
                }

                protected ForIntegerConstant(int i) {
                    this.value = i;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(IntegerConstant.forValue(this.value), new TypeDescription.ForLoadedType(Integer.TYPE));
                }
            }

            public static class ForLongConstant implements ArgumentProvider {
                private final long value;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForLongConstant;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForLongConstant)) {
                        return false;
                    }
                    ForLongConstant forLongConstant = (ForLongConstant) obj;
                    return forLongConstant.canEqual(this) && this.value == forLongConstant.value;
                }

                public int hashCode() {
                    long j = this.value;
                    return 59 + ((int) (j ^ (j >>> 32)));
                }

                protected ForLongConstant(long j) {
                    this.value = j;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(LongConstant.forValue(this.value), new TypeDescription.ForLoadedType(Long.TYPE));
                }
            }

            public static class ForFloatConstant implements ArgumentProvider {
                private final float value;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForFloatConstant;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForFloatConstant)) {
                        return false;
                    }
                    ForFloatConstant forFloatConstant = (ForFloatConstant) obj;
                    return forFloatConstant.canEqual(this) && Float.compare(this.value, forFloatConstant.value) == 0;
                }

                public int hashCode() {
                    return 59 + Float.floatToIntBits(this.value);
                }

                protected ForFloatConstant(float f) {
                    this.value = f;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(FloatConstant.forValue(this.value), new TypeDescription.ForLoadedType(Float.TYPE));
                }
            }

            public static class ForDoubleConstant implements ArgumentProvider {
                private final double value;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForDoubleConstant;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForDoubleConstant)) {
                        return false;
                    }
                    ForDoubleConstant forDoubleConstant = (ForDoubleConstant) obj;
                    return forDoubleConstant.canEqual(this) && Double.compare(this.value, forDoubleConstant.value) == 0;
                }

                public int hashCode() {
                    long jDoubleToLongBits = Double.doubleToLongBits(this.value);
                    return 59 + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)));
                }

                protected ForDoubleConstant(double d) {
                    this.value = d;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(DoubleConstant.forValue(this.value), new TypeDescription.ForLoadedType(Double.TYPE));
                }
            }

            public static class ForStringConstant implements ArgumentProvider {
                private final String value;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForStringConstant;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForStringConstant)) {
                        return false;
                    }
                    ForStringConstant forStringConstant = (ForStringConstant) obj;
                    if (!forStringConstant.canEqual(this)) {
                        return false;
                    }
                    String str = this.value;
                    String str2 = forStringConstant.value;
                    return str != null ? str.equals(str2) : str2 == null;
                }

                public int hashCode() {
                    String str = this.value;
                    return 59 + (str == null ? 43 : str.hashCode());
                }

                protected ForStringConstant(String str) {
                    this.value = str;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(new TextConstant(this.value), TypeDescription.STRING);
                }
            }

            public static class ForClassConstant implements ArgumentProvider {
                private final TypeDescription typeDescription;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForClassConstant;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForClassConstant)) {
                        return false;
                    }
                    ForClassConstant forClassConstant = (ForClassConstant) obj;
                    if (!forClassConstant.canEqual(this)) {
                        return false;
                    }
                    TypeDescription typeDescription = this.typeDescription;
                    TypeDescription typeDescription2 = forClassConstant.typeDescription;
                    return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                }

                public int hashCode() {
                    TypeDescription typeDescription = this.typeDescription;
                    return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
                }

                protected ForClassConstant(TypeDescription typeDescription) {
                    this.typeDescription = typeDescription;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(ClassConstant.of(this.typeDescription), TypeDescription.CLASS);
                }
            }

            public static class ForEnumerationValue implements ArgumentProvider {
                private final EnumerationDescription enumerationDescription;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForEnumerationValue;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForEnumerationValue)) {
                        return false;
                    }
                    ForEnumerationValue forEnumerationValue = (ForEnumerationValue) obj;
                    if (!forEnumerationValue.canEqual(this)) {
                        return false;
                    }
                    EnumerationDescription enumerationDescription = this.enumerationDescription;
                    EnumerationDescription enumerationDescription2 = forEnumerationValue.enumerationDescription;
                    return enumerationDescription != null ? enumerationDescription.equals(enumerationDescription2) : enumerationDescription2 == null;
                }

                public int hashCode() {
                    EnumerationDescription enumerationDescription = this.enumerationDescription;
                    return 59 + (enumerationDescription == null ? 43 : enumerationDescription.hashCode());
                }

                protected ForEnumerationValue(EnumerationDescription enumerationDescription) {
                    this.enumerationDescription = enumerationDescription;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(FieldAccess.forEnumeration(this.enumerationDescription), this.enumerationDescription.getEnumerationType());
                }
            }

            public static class ForNullValue implements ArgumentProvider {
                private final TypeDescription typeDescription;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForNullValue;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForNullValue)) {
                        return false;
                    }
                    ForNullValue forNullValue = (ForNullValue) obj;
                    if (!forNullValue.canEqual(this)) {
                        return false;
                    }
                    TypeDescription typeDescription = this.typeDescription;
                    TypeDescription typeDescription2 = forNullValue.typeDescription;
                    return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                }

                public int hashCode() {
                    TypeDescription typeDescription = this.typeDescription;
                    return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
                }

                protected ForNullValue(TypeDescription typeDescription) {
                    this.typeDescription = typeDescription;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(NullConstant.INSTANCE, this.typeDescription);
                }
            }

            public static class ForJavaConstant implements ArgumentProvider {
                private final JavaConstant javaConstant;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForJavaConstant;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForJavaConstant)) {
                        return false;
                    }
                    ForJavaConstant forJavaConstant = (ForJavaConstant) obj;
                    if (!forJavaConstant.canEqual(this)) {
                        return false;
                    }
                    JavaConstant javaConstant = this.javaConstant;
                    JavaConstant javaConstant2 = forJavaConstant.javaConstant;
                    return javaConstant != null ? javaConstant.equals(javaConstant2) : javaConstant2 == null;
                }

                public int hashCode() {
                    JavaConstant javaConstant = this.javaConstant;
                    return 59 + (javaConstant == null ? 43 : javaConstant.hashCode());
                }

                protected ForJavaConstant(JavaConstant javaConstant) {
                    this.javaConstant = javaConstant;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ArgumentProvider
                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(this.javaConstant.asStackManipulation(), this.javaConstant.getType());
                }
            }
        }

        public interface NameProvider {
            String resolve(MethodDescription methodDescription);

            public enum ForInterceptedMethod implements NameProvider {
                INSTANCE;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.NameProvider
                public String resolve(MethodDescription methodDescription) {
                    return methodDescription.getInternalName();
                }
            }

            public static class ForExplicitName implements NameProvider {
                private final String internalName;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForExplicitName;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForExplicitName)) {
                        return false;
                    }
                    ForExplicitName forExplicitName = (ForExplicitName) obj;
                    if (!forExplicitName.canEqual(this)) {
                        return false;
                    }
                    String str = this.internalName;
                    String str2 = forExplicitName.internalName;
                    return str != null ? str.equals(str2) : str2 == null;
                }

                public int hashCode() {
                    String str = this.internalName;
                    return 59 + (str == null ? 43 : str.hashCode());
                }

                protected ForExplicitName(String str) {
                    this.internalName = str;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.NameProvider
                public String resolve(MethodDescription methodDescription) {
                    return this.internalName;
                }
            }
        }

        public interface ReturnTypeProvider {
            TypeDescription resolve(MethodDescription methodDescription);

            public enum ForInterceptedMethod implements ReturnTypeProvider {
                INSTANCE;

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ReturnTypeProvider
                public TypeDescription resolve(MethodDescription methodDescription) {
                    return methodDescription.getReturnType().asErasure();
                }
            }

            public static class ForExplicitType implements ReturnTypeProvider {
                private final TypeDescription typeDescription;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForExplicitType;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForExplicitType)) {
                        return false;
                    }
                    ForExplicitType forExplicitType = (ForExplicitType) obj;
                    if (!forExplicitType.canEqual(this)) {
                        return false;
                    }
                    TypeDescription typeDescription = this.typeDescription;
                    TypeDescription typeDescription2 = forExplicitType.typeDescription;
                    return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                }

                public int hashCode() {
                    TypeDescription typeDescription = this.typeDescription;
                    return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
                }

                protected ForExplicitType(TypeDescription typeDescription) {
                    this.typeDescription = typeDescription;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.ReturnTypeProvider
                public TypeDescription resolve(MethodDescription methodDescription) {
                    return this.typeDescription;
                }
            }
        }

        public static class Default implements InvocationProvider {
            private final List<ArgumentProvider> argumentProviders;
            private final NameProvider nameProvider;
            private final ReturnTypeProvider returnTypeProvider;

            protected boolean canEqual(Object obj) {
                return obj instanceof Default;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Default)) {
                    return false;
                }
                Default r5 = (Default) obj;
                if (!r5.canEqual(this)) {
                    return false;
                }
                NameProvider nameProvider = this.nameProvider;
                NameProvider nameProvider2 = r5.nameProvider;
                if (nameProvider != null ? !nameProvider.equals(nameProvider2) : nameProvider2 != null) {
                    return false;
                }
                ReturnTypeProvider returnTypeProvider = this.returnTypeProvider;
                ReturnTypeProvider returnTypeProvider2 = r5.returnTypeProvider;
                if (returnTypeProvider != null ? !returnTypeProvider.equals(returnTypeProvider2) : returnTypeProvider2 != null) {
                    return false;
                }
                List<ArgumentProvider> list = this.argumentProviders;
                List<ArgumentProvider> list2 = r5.argumentProviders;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                NameProvider nameProvider = this.nameProvider;
                int iHashCode = nameProvider == null ? 43 : nameProvider.hashCode();
                ReturnTypeProvider returnTypeProvider = this.returnTypeProvider;
                int iHashCode2 = ((iHashCode + 59) * 59) + (returnTypeProvider == null ? 43 : returnTypeProvider.hashCode());
                List<ArgumentProvider> list = this.argumentProviders;
                return (iHashCode2 * 59) + (list != null ? list.hashCode() : 43);
            }

            protected Default() {
                this(NameProvider.ForInterceptedMethod.INSTANCE, ReturnTypeProvider.ForInterceptedMethod.INSTANCE, Collections.singletonList(ArgumentProvider.ForInterceptedMethodInstanceAndParameters.INSTANCE));
            }

            protected Default(NameProvider nameProvider, ReturnTypeProvider returnTypeProvider, List<ArgumentProvider> list) {
                this.nameProvider = nameProvider;
                this.returnTypeProvider = returnTypeProvider;
                this.argumentProviders = list;
            }

            @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider
            public Target make(MethodDescription methodDescription) {
                return new Target(this.nameProvider.resolve(methodDescription), this.returnTypeProvider.resolve(methodDescription), this.argumentProviders, methodDescription);
            }

            @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider
            public InvocationProvider appendArguments(List<ArgumentProvider> list) {
                return new Default(this.nameProvider, this.returnTypeProvider, CompoundList.of((List) this.argumentProviders, (List) list));
            }

            @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider
            public InvocationProvider appendArgument(ArgumentProvider argumentProvider) {
                return new Default(this.nameProvider, this.returnTypeProvider, CompoundList.of(this.argumentProviders, argumentProvider));
            }

            @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider
            public InvocationProvider withoutArguments() {
                return new Default(this.nameProvider, this.returnTypeProvider, Collections.emptyList());
            }

            @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider
            public InvocationProvider withNameProvider(NameProvider nameProvider) {
                return new Default(nameProvider, this.returnTypeProvider, this.argumentProviders);
            }

            @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider
            public InvocationProvider withReturnTypeProvider(ReturnTypeProvider returnTypeProvider) {
                return new Default(this.nameProvider, returnTypeProvider, this.argumentProviders);
            }

            @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                Iterator<ArgumentProvider> it = this.argumentProviders.iterator();
                while (it.hasNext()) {
                    instrumentedType = it.next().prepare(instrumentedType);
                }
                return instrumentedType;
            }

            protected static class Target implements Target {
                private final List<ArgumentProvider> argumentProviders;
                private final MethodDescription instrumentedMethod;
                private final String internalName;
                private final TypeDescription returnType;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Target;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Target)) {
                        return false;
                    }
                    Target target = (Target) obj;
                    if (!target.canEqual(this)) {
                        return false;
                    }
                    String str = this.internalName;
                    String str2 = target.internalName;
                    if (str != null ? !str.equals(str2) : str2 != null) {
                        return false;
                    }
                    TypeDescription typeDescription = this.returnType;
                    TypeDescription typeDescription2 = target.returnType;
                    if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                        return false;
                    }
                    List<ArgumentProvider> list = this.argumentProviders;
                    List<ArgumentProvider> list2 = target.argumentProviders;
                    if (list != null ? !list.equals(list2) : list2 != null) {
                        return false;
                    }
                    MethodDescription methodDescription = this.instrumentedMethod;
                    MethodDescription methodDescription2 = target.instrumentedMethod;
                    return methodDescription != null ? methodDescription.equals(methodDescription2) : methodDescription2 == null;
                }

                public int hashCode() {
                    String str = this.internalName;
                    int iHashCode = str == null ? 43 : str.hashCode();
                    TypeDescription typeDescription = this.returnType;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (typeDescription == null ? 43 : typeDescription.hashCode());
                    List<ArgumentProvider> list = this.argumentProviders;
                    int iHashCode3 = (iHashCode2 * 59) + (list == null ? 43 : list.hashCode());
                    MethodDescription methodDescription = this.instrumentedMethod;
                    return (iHashCode3 * 59) + (methodDescription != null ? methodDescription.hashCode() : 43);
                }

                protected Target(String str, TypeDescription typeDescription, List<ArgumentProvider> list, MethodDescription methodDescription) {
                    this.internalName = str;
                    this.returnType = typeDescription;
                    this.argumentProviders = list;
                    this.instrumentedMethod = methodDescription;
                }

                @Override // net.bytebuddy.implementation.InvokeDynamic.InvocationProvider.Target
                public Target.Resolved resolve(TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing) {
                    StackManipulation[] stackManipulationArr = new StackManipulation[this.argumentProviders.size()];
                    ArrayList arrayList = new ArrayList();
                    Iterator<ArgumentProvider> it = this.argumentProviders.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        ArgumentProvider.Resolved resolvedResolve = it.next().resolve(typeDescription, this.instrumentedMethod, assigner, typing);
                        arrayList.addAll(resolvedResolve.getLoadedTypes());
                        stackManipulationArr[i] = resolvedResolve.getLoadInstruction();
                        i++;
                    }
                    return new Target.Resolved.Simple(new StackManipulation.Compound(stackManipulationArr), this.internalName, this.returnType, arrayList);
                }
            }
        }
    }

    protected static abstract class AbstractDelegator extends InvokeDynamic {
        protected abstract InvokeDynamic materialize();

        protected AbstractDelegator(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing) {
            super(inDefinedShape, list, invocationProvider, terminationHandler, assigner, typing);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withBooleanValue(boolean... zArr) {
            return materialize().withBooleanValue(zArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withByteValue(byte... bArr) {
            return materialize().withByteValue(bArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withShortValue(short... sArr) {
            return materialize().withShortValue(sArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withCharacterValue(char... cArr) {
            return materialize().withCharacterValue(cArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withIntegerValue(int... iArr) {
            return materialize().withIntegerValue(iArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withLongValue(long... jArr) {
            return materialize().withLongValue(jArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withFloatValue(float... fArr) {
            return materialize().withFloatValue(fArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withDoubleValue(double... dArr) {
            return materialize().withDoubleValue(dArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withValue(Object... objArr) {
            return materialize().withValue(objArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public WithImplicitType withReference(Object obj) {
            return materialize().withReference(obj);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withReference(Object... objArr) {
            return materialize().withReference(objArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withType(TypeDescription... typeDescriptionArr) {
            return materialize().withType(typeDescriptionArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withInstance(JavaConstant... javaConstantArr) {
            return materialize().withInstance(javaConstantArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withNullValue(Class<?>... clsArr) {
            return materialize().withNullValue(clsArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withNullValue(TypeDescription... typeDescriptionArr) {
            return materialize().withNullValue(typeDescriptionArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withArgument(int... iArr) {
            return materialize().withArgument(iArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public WithImplicitType withArgument(int i) {
            return materialize().withArgument(i);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withThis(Class<?>... clsArr) {
            return materialize().withThis(clsArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withThis(TypeDescription... typeDescriptionArr) {
            return materialize().withThis(typeDescriptionArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withMethodArguments() {
            return materialize().withMethodArguments();
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withImplicitAndMethodArguments() {
            return materialize().withImplicitAndMethodArguments();
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withField(String... strArr) {
            return materialize().withField(strArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withEnumeration(EnumerationDescription... enumerationDescriptionArr) {
            return materialize().withEnumeration(enumerationDescriptionArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public InvokeDynamic withField(FieldLocator.Factory factory, String... strArr) {
            return materialize().withField(factory, strArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public WithImplicitType withField(String str) {
            return materialize().withField(str);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public WithImplicitType withField(String str, FieldLocator.Factory factory) {
            return materialize().withField(str, factory);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic
        public Implementation.Composable withAssigner(Assigner assigner, Assigner.Typing typing) {
            return materialize().withAssigner(assigner, typing);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic, net.bytebuddy.implementation.Implementation.Composable
        public Implementation andThen(Implementation implementation) {
            return materialize().andThen(implementation);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic, net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return materialize().prepare(instrumentedType);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic, net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return materialize().appender(target);
        }
    }

    public static class WithImplicitArguments extends AbstractDelegator {
        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic, net.bytebuddy.implementation.Implementation.Composable
        public /* bridge */ /* synthetic */ Implementation andThen(Implementation implementation) {
            return super.andThen(implementation);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic, net.bytebuddy.implementation.Implementation
        public /* bridge */ /* synthetic */ ByteCodeAppender appender(Implementation.Target target) {
            return super.appender(target);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic, net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public /* bridge */ /* synthetic */ InstrumentedType prepare(InstrumentedType instrumentedType) {
            return super.prepare(instrumentedType);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ WithImplicitType withArgument(int i) {
            return super.withArgument(i);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withArgument(int[] iArr) {
            return super.withArgument(iArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withBooleanValue(boolean[] zArr) {
            return super.withBooleanValue(zArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withByteValue(byte[] bArr) {
            return super.withByteValue(bArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withCharacterValue(char[] cArr) {
            return super.withCharacterValue(cArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withDoubleValue(double[] dArr) {
            return super.withDoubleValue(dArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withEnumeration(EnumerationDescription[] enumerationDescriptionArr) {
            return super.withEnumeration(enumerationDescriptionArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ WithImplicitType withField(String str) {
            return super.withField(str);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ WithImplicitType withField(String str, FieldLocator.Factory factory) {
            return super.withField(str, factory);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withField(FieldLocator.Factory factory, String[] strArr) {
            return super.withField(factory, strArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withField(String[] strArr) {
            return super.withField(strArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withFloatValue(float[] fArr) {
            return super.withFloatValue(fArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withImplicitAndMethodArguments() {
            return super.withImplicitAndMethodArguments();
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withInstance(JavaConstant[] javaConstantArr) {
            return super.withInstance(javaConstantArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withIntegerValue(int[] iArr) {
            return super.withIntegerValue(iArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withLongValue(long[] jArr) {
            return super.withLongValue(jArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withMethodArguments() {
            return super.withMethodArguments();
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withNullValue(Class[] clsArr) {
            return super.withNullValue((Class<?>[]) clsArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withNullValue(TypeDescription[] typeDescriptionArr) {
            return super.withNullValue(typeDescriptionArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ WithImplicitType withReference(Object obj) {
            return super.withReference(obj);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withReference(Object[] objArr) {
            return super.withReference(objArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withShortValue(short[] sArr) {
            return super.withShortValue(sArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withThis(Class[] clsArr) {
            return super.withThis((Class<?>[]) clsArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withThis(TypeDescription[] typeDescriptionArr) {
            return super.withThis(typeDescriptionArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withType(TypeDescription[] typeDescriptionArr) {
            return super.withType(typeDescriptionArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withValue(Object[] objArr) {
            return super.withValue(objArr);
        }

        protected WithImplicitArguments(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing) {
            super(inDefinedShape, list, invocationProvider, terminationHandler, assigner, typing);
        }

        public InvokeDynamic withoutArguments() {
            return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.withoutArguments(), this.terminationHandler, this.assigner, this.typing);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator
        protected InvokeDynamic materialize() {
            return withoutArguments();
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public WithImplicitArguments withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new WithImplicitArguments(this.bootstrapMethod, this.handleArguments, this.invocationProvider, this.terminationHandler, assigner, typing);
        }
    }

    public static class WithImplicitTarget extends WithImplicitArguments {
        protected WithImplicitTarget(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing) {
            super(inDefinedShape, list, invocationProvider, terminationHandler, assigner, typing);
        }

        public WithImplicitArguments invoke(Class<?> cls) {
            return invoke(new TypeDescription.ForLoadedType(cls));
        }

        public WithImplicitArguments invoke(TypeDescription typeDescription) {
            return new WithImplicitArguments(this.bootstrapMethod, this.handleArguments, this.invocationProvider.withReturnTypeProvider(new InvocationProvider.ReturnTypeProvider.ForExplicitType(typeDescription)), this.terminationHandler, this.assigner, this.typing);
        }

        public WithImplicitArguments invoke(String str) {
            return new WithImplicitArguments(this.bootstrapMethod, this.handleArguments, this.invocationProvider.withNameProvider(new InvocationProvider.NameProvider.ForExplicitName(str)), this.terminationHandler, this.assigner, this.typing);
        }

        public WithImplicitArguments invoke(String str, Class<?> cls) {
            return invoke(str, new TypeDescription.ForLoadedType(cls));
        }

        public WithImplicitArguments invoke(String str, TypeDescription typeDescription) {
            return new WithImplicitArguments(this.bootstrapMethod, this.handleArguments, this.invocationProvider.withNameProvider(new InvocationProvider.NameProvider.ForExplicitName(str)).withReturnTypeProvider(new InvocationProvider.ReturnTypeProvider.ForExplicitType(typeDescription)), this.terminationHandler, this.assigner, this.typing);
        }
    }

    public static abstract class WithImplicitType extends AbstractDelegator {
        public abstract InvokeDynamic as(TypeDescription typeDescription);

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic, net.bytebuddy.implementation.Implementation.Composable
        public /* bridge */ /* synthetic */ Implementation andThen(Implementation implementation) {
            return super.andThen(implementation);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic, net.bytebuddy.implementation.Implementation
        public /* bridge */ /* synthetic */ ByteCodeAppender appender(Implementation.Target target) {
            return super.appender(target);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic, net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public /* bridge */ /* synthetic */ InstrumentedType prepare(InstrumentedType instrumentedType) {
            return super.prepare(instrumentedType);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ WithImplicitType withArgument(int i) {
            return super.withArgument(i);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withArgument(int[] iArr) {
            return super.withArgument(iArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ Implementation.Composable withAssigner(Assigner assigner, Assigner.Typing typing) {
            return super.withAssigner(assigner, typing);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withBooleanValue(boolean[] zArr) {
            return super.withBooleanValue(zArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withByteValue(byte[] bArr) {
            return super.withByteValue(bArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withCharacterValue(char[] cArr) {
            return super.withCharacterValue(cArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withDoubleValue(double[] dArr) {
            return super.withDoubleValue(dArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withEnumeration(EnumerationDescription[] enumerationDescriptionArr) {
            return super.withEnumeration(enumerationDescriptionArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ WithImplicitType withField(String str) {
            return super.withField(str);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ WithImplicitType withField(String str, FieldLocator.Factory factory) {
            return super.withField(str, factory);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withField(FieldLocator.Factory factory, String[] strArr) {
            return super.withField(factory, strArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withField(String[] strArr) {
            return super.withField(strArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withFloatValue(float[] fArr) {
            return super.withFloatValue(fArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withImplicitAndMethodArguments() {
            return super.withImplicitAndMethodArguments();
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withInstance(JavaConstant[] javaConstantArr) {
            return super.withInstance(javaConstantArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withIntegerValue(int[] iArr) {
            return super.withIntegerValue(iArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withLongValue(long[] jArr) {
            return super.withLongValue(jArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withMethodArguments() {
            return super.withMethodArguments();
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withNullValue(Class[] clsArr) {
            return super.withNullValue((Class<?>[]) clsArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withNullValue(TypeDescription[] typeDescriptionArr) {
            return super.withNullValue(typeDescriptionArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ WithImplicitType withReference(Object obj) {
            return super.withReference(obj);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withReference(Object[] objArr) {
            return super.withReference(objArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withShortValue(short[] sArr) {
            return super.withShortValue(sArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withThis(Class[] clsArr) {
            return super.withThis((Class<?>[]) clsArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withThis(TypeDescription[] typeDescriptionArr) {
            return super.withThis(typeDescriptionArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withType(TypeDescription[] typeDescriptionArr) {
            return super.withType(typeDescriptionArr);
        }

        @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator, net.bytebuddy.implementation.InvokeDynamic
        public /* bridge */ /* synthetic */ InvokeDynamic withValue(Object[] objArr) {
            return super.withValue(objArr);
        }

        protected WithImplicitType(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing) {
            super(inDefinedShape, list, invocationProvider, terminationHandler, assigner, typing);
        }

        public InvokeDynamic as(Class<?> cls) {
            return as(new TypeDescription.ForLoadedType(cls));
        }

        protected static class OfInstance extends WithImplicitType {
            private final InvocationProvider.ArgumentProvider argumentProvider;
            private final Object value;

            protected OfInstance(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing, Object obj) {
                super(inDefinedShape, list, invocationProvider, terminationHandler, assigner, typing);
                this.value = obj;
                this.argumentProvider = InvocationProvider.ArgumentProvider.ForInstance.of(obj);
            }

            @Override // net.bytebuddy.implementation.InvokeDynamic.WithImplicitType
            public InvokeDynamic as(TypeDescription typeDescription) {
                if (!typeDescription.asBoxed().isInstance(this.value)) {
                    throw new IllegalArgumentException(this.value + " is not of type " + typeDescription);
                }
                return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(new InvocationProvider.ArgumentProvider.ForInstance(this.value, typeDescription)), this.terminationHandler, this.assigner, this.typing);
            }

            @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator
            protected InvokeDynamic materialize() {
                return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(this.argumentProvider), this.terminationHandler, this.assigner, this.typing);
            }
        }

        protected static class OfArgument extends WithImplicitType {
            private final int index;

            protected OfArgument(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing, int i) {
                super(inDefinedShape, list, invocationProvider, terminationHandler, assigner, typing);
                this.index = i;
            }

            @Override // net.bytebuddy.implementation.InvokeDynamic.WithImplicitType
            public InvokeDynamic as(TypeDescription typeDescription) {
                return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(new InvocationProvider.ArgumentProvider.ForMethodParameter.WithExplicitType(this.index, typeDescription)), this.terminationHandler, this.assigner, this.typing);
            }

            @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator
            protected InvokeDynamic materialize() {
                return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(new InvocationProvider.ArgumentProvider.ForMethodParameter(this.index)), this.terminationHandler, this.assigner, this.typing);
            }
        }

        protected static class OfField extends WithImplicitType {
            private final FieldLocator.Factory fieldLocatorFactory;
            private final String fieldName;

            protected OfField(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing, String str, FieldLocator.Factory factory) {
                super(inDefinedShape, list, invocationProvider, terminationHandler, assigner, typing);
                this.fieldName = str;
                this.fieldLocatorFactory = factory;
            }

            @Override // net.bytebuddy.implementation.InvokeDynamic.WithImplicitType
            public InvokeDynamic as(TypeDescription typeDescription) {
                return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(new InvocationProvider.ArgumentProvider.ForField.WithExplicitType(this.fieldName, this.fieldLocatorFactory, typeDescription)), this.terminationHandler, this.assigner, this.typing);
            }

            @Override // net.bytebuddy.implementation.InvokeDynamic.AbstractDelegator
            protected InvokeDynamic materialize() {
                return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(new InvocationProvider.ArgumentProvider.ForField(this.fieldName, this.fieldLocatorFactory)), this.terminationHandler, this.assigner, this.typing);
            }
        }
    }

    protected class Appender implements ByteCodeAppender {
        private final TypeDescription instrumentedType;

        public Appender(TypeDescription typeDescription) {
            this.instrumentedType = typeDescription;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            InvocationProvider.Target.Resolved resolvedResolve = InvokeDynamic.this.invocationProvider.make(methodDescription).resolve(this.instrumentedType, InvokeDynamic.this.assigner, InvokeDynamic.this.typing);
            return new ByteCodeAppender.Size(new StackManipulation.Compound(resolvedResolve.getStackManipulation(), MethodInvocation.invoke(InvokeDynamic.this.bootstrapMethod).dynamic(resolvedResolve.getInternalName(), resolvedResolve.getReturnType(), resolvedResolve.getParameterTypes(), InvokeDynamic.this.handleArguments), InvokeDynamic.this.terminationHandler.resolve(methodDescription, resolvedResolve.getReturnType(), InvokeDynamic.this.assigner, InvokeDynamic.this.typing)).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Appender appender = (Appender) obj;
            return this.instrumentedType.equals(appender.instrumentedType) && InvokeDynamic.this.equals(appender.getOuter());
        }

        private InvokeDynamic getOuter() {
            return InvokeDynamic.this;
        }

        public int hashCode() {
            return this.instrumentedType.hashCode() + (InvokeDynamic.this.hashCode() * 31);
        }
    }
}
