package net.bytebuddy.implementation.bytecode.member;

import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public enum MethodInvocation {
    VIRTUAL(182, 5),
    INTERFACE(185, 9),
    STATIC(184, 6),
    SPECIAL(183, 7),
    SPECIAL_CONSTRUCTOR(183, 8);

    private final int handle;
    private final int invocationOpcode;

    public interface WithImplicitInvocationTargetType extends StackManipulation {
        StackManipulation dynamic(String str, TypeDescription typeDescription, List<? extends TypeDescription> list, List<?> list2);

        StackManipulation onHandle(HandleType handleType);

        StackManipulation special(TypeDescription typeDescription);

        StackManipulation virtual(TypeDescription typeDescription);
    }

    MethodInvocation(int i, int i2) {
        this.invocationOpcode = i;
        this.handle = i2;
    }

    public static WithImplicitInvocationTargetType invoke(MethodDescription.InDefinedShape inDefinedShape) {
        if (inDefinedShape.isTypeInitializer()) {
            return IllegalInvocation.INSTANCE;
        }
        if (inDefinedShape.isStatic()) {
            MethodInvocation methodInvocation = STATIC;
            methodInvocation.getClass();
            return new Invocation(methodInvocation, inDefinedShape);
        }
        if (inDefinedShape.isConstructor()) {
            MethodInvocation methodInvocation2 = SPECIAL_CONSTRUCTOR;
            methodInvocation2.getClass();
            return new Invocation(methodInvocation2, inDefinedShape);
        }
        if (inDefinedShape.isPrivate()) {
            MethodInvocation methodInvocation3 = SPECIAL;
            methodInvocation3.getClass();
            return new Invocation(methodInvocation3, inDefinedShape);
        }
        if (inDefinedShape.getDeclaringType().isInterface()) {
            MethodInvocation methodInvocation4 = INTERFACE;
            methodInvocation4.getClass();
            return new Invocation(methodInvocation4, inDefinedShape);
        }
        MethodInvocation methodInvocation5 = VIRTUAL;
        methodInvocation5.getClass();
        return new Invocation(methodInvocation5, inDefinedShape);
    }

    public static WithImplicitInvocationTargetType invoke(MethodDescription methodDescription) {
        MethodDescription.InDefinedShape inDefinedShapeAsDefined = methodDescription.asDefined();
        if (inDefinedShapeAsDefined.getReturnType().asErasure().equals(methodDescription.getReturnType().asErasure())) {
            return invoke(inDefinedShapeAsDefined);
        }
        return OfGenericMethod.of(methodDescription, invoke(inDefinedShapeAsDefined));
    }

    protected enum IllegalInvocation implements WithImplicitInvocationTargetType {
        INSTANCE;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return false;
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation virtual(TypeDescription typeDescription) {
            return StackManipulation.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation special(TypeDescription typeDescription) {
            return StackManipulation.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation dynamic(String str, TypeDescription typeDescription, List<? extends TypeDescription> list, List<?> list2) {
            return StackManipulation.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation onHandle(HandleType handleType) {
            return StackManipulation.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return StackManipulation.Illegal.INSTANCE.apply(methodVisitor, context);
        }
    }

    protected static class OfGenericMethod implements WithImplicitInvocationTargetType {
        private final WithImplicitInvocationTargetType invocation;
        private final TypeDescription targetType;

        protected boolean canEqual(Object obj) {
            return obj instanceof OfGenericMethod;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof OfGenericMethod)) {
                return false;
            }
            OfGenericMethod ofGenericMethod = (OfGenericMethod) obj;
            if (!ofGenericMethod.canEqual(this)) {
                return false;
            }
            TypeDescription typeDescription = this.targetType;
            TypeDescription typeDescription2 = ofGenericMethod.targetType;
            if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                return false;
            }
            WithImplicitInvocationTargetType withImplicitInvocationTargetType = this.invocation;
            WithImplicitInvocationTargetType withImplicitInvocationTargetType2 = ofGenericMethod.invocation;
            return withImplicitInvocationTargetType != null ? withImplicitInvocationTargetType.equals(withImplicitInvocationTargetType2) : withImplicitInvocationTargetType2 == null;
        }

        public int hashCode() {
            TypeDescription typeDescription = this.targetType;
            int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
            WithImplicitInvocationTargetType withImplicitInvocationTargetType = this.invocation;
            return ((iHashCode + 59) * 59) + (withImplicitInvocationTargetType != null ? withImplicitInvocationTargetType.hashCode() : 43);
        }

        protected OfGenericMethod(TypeDescription typeDescription, WithImplicitInvocationTargetType withImplicitInvocationTargetType) {
            this.targetType = typeDescription;
            this.invocation = withImplicitInvocationTargetType;
        }

        protected static WithImplicitInvocationTargetType of(MethodDescription methodDescription, WithImplicitInvocationTargetType withImplicitInvocationTargetType) {
            return new OfGenericMethod(methodDescription.getReturnType().asErasure(), withImplicitInvocationTargetType);
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation virtual(TypeDescription typeDescription) {
            return new StackManipulation.Compound(this.invocation.virtual(typeDescription), TypeCasting.to(this.targetType));
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation special(TypeDescription typeDescription) {
            return new StackManipulation.Compound(this.invocation.special(typeDescription), TypeCasting.to(this.targetType));
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation dynamic(String str, TypeDescription typeDescription, List<? extends TypeDescription> list, List<?> list2) {
            return this.invocation.dynamic(str, typeDescription, list, list2);
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation onHandle(HandleType handleType) {
            return new StackManipulation.Compound(this.invocation.onHandle(handleType), TypeCasting.to(this.targetType));
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return this.invocation.isValid();
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return new StackManipulation.Compound(this.invocation, TypeCasting.to(this.targetType)).apply(methodVisitor, context);
        }
    }

    protected class Invocation implements WithImplicitInvocationTargetType {
        private final MethodDescription.InDefinedShape methodDescription;
        private final TypeDescription typeDescription;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        protected Invocation(MethodInvocation methodInvocation, MethodDescription.InDefinedShape inDefinedShape) {
            this(inDefinedShape, inDefinedShape.getDeclaringType());
        }

        protected Invocation(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription) {
            this.typeDescription = typeDescription;
            this.methodDescription = inDefinedShape;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitMethodInsn(MethodInvocation.this.invocationOpcode, this.typeDescription.getInternalName(), this.methodDescription.getInternalName(), this.methodDescription.getDescriptor(), this.typeDescription.isInterface());
            int size = this.methodDescription.getReturnType().getStackSize().getSize() - this.methodDescription.getStackSize();
            return new StackManipulation.Size(size, Math.max(0, size));
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation virtual(TypeDescription typeDescription) {
            if (this.methodDescription.isPrivate() || this.methodDescription.isConstructor() || this.methodDescription.isStatic()) {
                return StackManipulation.Illegal.INSTANCE;
            }
            if (typeDescription.isInterface()) {
                MethodInvocation methodInvocation = MethodInvocation.INTERFACE;
                methodInvocation.getClass();
                return methodInvocation.new Invocation(this.methodDescription, typeDescription);
            }
            MethodInvocation methodInvocation2 = MethodInvocation.VIRTUAL;
            methodInvocation2.getClass();
            return methodInvocation2.new Invocation(this.methodDescription, typeDescription);
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation special(TypeDescription typeDescription) {
            if (!this.methodDescription.isSpecializableFor(typeDescription)) {
                return StackManipulation.Illegal.INSTANCE;
            }
            MethodInvocation methodInvocation = MethodInvocation.SPECIAL;
            methodInvocation.getClass();
            return methodInvocation.new Invocation(this.methodDescription, typeDescription);
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation dynamic(String str, TypeDescription typeDescription, List<? extends TypeDescription> list, List<?> list2) {
            return this.methodDescription.isBootstrap() ? MethodInvocation.this.new DynamicInvocation(str, typeDescription, new TypeList.Explicit(list), this.methodDescription.asDefined(), list2) : StackManipulation.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.implementation.bytecode.member.MethodInvocation.WithImplicitInvocationTargetType
        public StackManipulation onHandle(HandleType handleType) {
            return new HandleInvocation(this.methodDescription, handleType);
        }

        private MethodInvocation getOuterInstance() {
            return MethodInvocation.this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Invocation invocation = (Invocation) obj;
            return MethodInvocation.this.equals(invocation.getOuterInstance()) && this.methodDescription.asSignatureToken().equals(invocation.methodDescription.asSignatureToken()) && this.typeDescription.equals(invocation.typeDescription);
        }

        public int hashCode() {
            return (((this.typeDescription.hashCode() * 31) + MethodInvocation.this.hashCode()) * 31) + this.methodDescription.asSignatureToken().hashCode();
        }
    }

    protected class DynamicInvocation implements StackManipulation {
        private final List<?> arguments;
        private final MethodDescription.InDefinedShape bootstrapMethod;
        private final String methodName;
        private final TypeList parameterTypes;
        private final TypeDescription returnType;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        public DynamicInvocation(String str, TypeDescription typeDescription, TypeList typeList, MethodDescription.InDefinedShape inDefinedShape, List<?> list) {
            this.methodName = str;
            this.returnType = typeDescription;
            this.parameterTypes = typeList;
            this.bootstrapMethod = inDefinedShape;
            this.arguments = list;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            StringBuilder sb = new StringBuilder("(");
            Iterator it = this.parameterTypes.iterator();
            while (it.hasNext()) {
                sb.append(((TypeDescription) it.next()).getDescriptor());
            }
            String string = sb.append(')').append(this.returnType.getDescriptor()).toString();
            String str = this.methodName;
            Handle handle = new Handle(MethodInvocation.this.handle, this.bootstrapMethod.getDeclaringType().getInternalName(), this.bootstrapMethod.getInternalName(), this.bootstrapMethod.getDescriptor(), this.bootstrapMethod.getDeclaringType().isInterface());
            List<?> list = this.arguments;
            methodVisitor.visitInvokeDynamicInsn(str, string, handle, list.toArray(new Object[list.size()]));
            int size = this.returnType.getStackSize().getSize() - this.parameterTypes.getStackSize();
            return new StackManipulation.Size(size, Math.max(size, 0));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DynamicInvocation dynamicInvocation = (DynamicInvocation) obj;
            return MethodInvocation.this == dynamicInvocation.getOuter() && this.arguments.equals(dynamicInvocation.arguments) && this.bootstrapMethod.equals(dynamicInvocation.bootstrapMethod) && this.returnType.equals(dynamicInvocation.returnType) && this.parameterTypes.equals(dynamicInvocation.parameterTypes) && this.methodName.equals(dynamicInvocation.methodName);
        }

        private MethodInvocation getOuter() {
            return MethodInvocation.this;
        }

        public int hashCode() {
            return (((((((((this.methodName.hashCode() * 31) + MethodInvocation.this.hashCode()) * 31) + this.returnType.hashCode()) * 31) + this.parameterTypes.hashCode()) * 31) + this.bootstrapMethod.hashCode()) * 31) + this.arguments.hashCode();
        }
    }

    protected static class HandleInvocation implements StackManipulation {
        private static final String METHOD_HANDLE = "java/lang/invoke/MethodHandle";
        private final MethodDescription.InDefinedShape methodDescription;
        private final HandleType type;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof HandleInvocation;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof HandleInvocation)) {
                return false;
            }
            HandleInvocation handleInvocation = (HandleInvocation) obj;
            if (!handleInvocation.canEqual(this)) {
                return false;
            }
            MethodDescription.InDefinedShape inDefinedShape = this.methodDescription;
            MethodDescription.InDefinedShape inDefinedShape2 = handleInvocation.methodDescription;
            if (inDefinedShape != null ? !inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 != null) {
                return false;
            }
            HandleType handleType = this.type;
            HandleType handleType2 = handleInvocation.type;
            return handleType != null ? handleType.equals(handleType2) : handleType2 == null;
        }

        public int hashCode() {
            MethodDescription.InDefinedShape inDefinedShape = this.methodDescription;
            int iHashCode = inDefinedShape == null ? 43 : inDefinedShape.hashCode();
            HandleType handleType = this.type;
            return ((iHashCode + 59) * 59) + (handleType != null ? handleType.hashCode() : 43);
        }

        protected HandleInvocation(MethodDescription.InDefinedShape inDefinedShape, HandleType handleType) {
            this.methodDescription = inDefinedShape;
            this.type = handleType;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            String descriptor;
            String methodName = this.type.getMethodName();
            if (this.methodDescription.isStatic() || this.methodDescription.isConstructor()) {
                descriptor = this.methodDescription.getDescriptor();
            } else {
                descriptor = "(" + this.methodDescription.getDeclaringType().getDescriptor() + this.methodDescription.getDescriptor().substring(1);
            }
            methodVisitor.visitMethodInsn(182, METHOD_HANDLE, methodName, descriptor, false);
            int size = this.methodDescription.getReturnType().getStackSize().getSize() - (this.methodDescription.getStackSize() + 1);
            return new StackManipulation.Size(size, Math.max(0, size));
        }
    }

    public enum HandleType {
        EXACT("invokeExact"),
        REGULAR("invoke");

        private final String methodName;

        HandleType(String str) {
            this.methodName = str;
        }

        protected String getMethodName() {
            return this.methodName;
        }
    }
}
