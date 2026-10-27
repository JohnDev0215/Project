package net.bytebuddy.implementation;

import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.DoubleConstant;
import net.bytebuddy.implementation.bytecode.constant.FloatConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.JavaConstantValue;
import net.bytebuddy.implementation.bytecode.constant.LongConstant;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.RandomString;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FixedValue implements Implementation {
    protected final Assigner assigner;
    protected final Assigner.Typing typing;

    public interface AssignerConfigurable extends Implementation {
        Implementation withAssigner(Assigner assigner, Assigner.Typing typing);
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof FixedValue;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FixedValue)) {
            return false;
        }
        FixedValue fixedValue = (FixedValue) obj;
        if (!fixedValue.canEqual(this)) {
            return false;
        }
        Assigner assigner = this.assigner;
        Assigner assigner2 = fixedValue.assigner;
        if (assigner != null ? !assigner.equals(assigner2) : assigner2 != null) {
            return false;
        }
        Assigner.Typing typing = this.typing;
        Assigner.Typing typing2 = fixedValue.typing;
        return typing != null ? typing.equals(typing2) : typing2 == null;
    }

    public int hashCode() {
        Assigner assigner = this.assigner;
        int iHashCode = assigner == null ? 43 : assigner.hashCode();
        Assigner.Typing typing = this.typing;
        return ((iHashCode + 59) * 59) + (typing != null ? typing.hashCode() : 43);
    }

    protected FixedValue(Assigner assigner, Assigner.Typing typing) {
        this.assigner = assigner;
        this.typing = typing;
    }

    public static AssignerConfigurable value(Object obj) {
        Class<?> cls = obj.getClass();
        if (cls == String.class) {
            return new ForPoolValue(new TextConstant((String) obj), TypeDescription.STRING);
        }
        if (cls == Class.class) {
            return new ForPoolValue(ClassConstant.of(new TypeDescription.ForLoadedType((Class) obj)), TypeDescription.CLASS);
        }
        if (cls == Boolean.class) {
            return new ForPoolValue(IntegerConstant.forValue(((Boolean) obj).booleanValue()), (Class<?>) Boolean.TYPE);
        }
        if (cls == Byte.class) {
            return new ForPoolValue(IntegerConstant.forValue(((Byte) obj).byteValue()), (Class<?>) Byte.TYPE);
        }
        if (cls == Short.class) {
            return new ForPoolValue(IntegerConstant.forValue(((Short) obj).shortValue()), (Class<?>) Short.TYPE);
        }
        if (cls == Character.class) {
            return new ForPoolValue(IntegerConstant.forValue(((Character) obj).charValue()), (Class<?>) Character.TYPE);
        }
        if (cls == Integer.class) {
            return new ForPoolValue(IntegerConstant.forValue(((Integer) obj).intValue()), (Class<?>) Integer.TYPE);
        }
        if (cls == Long.class) {
            return new ForPoolValue(LongConstant.forValue(((Long) obj).longValue()), (Class<?>) Long.TYPE);
        }
        if (cls == Float.class) {
            return new ForPoolValue(FloatConstant.forValue(((Float) obj).floatValue()), (Class<?>) Float.TYPE);
        }
        if (cls == Double.class) {
            return new ForPoolValue(DoubleConstant.forValue(((Double) obj).doubleValue()), (Class<?>) Double.TYPE);
        }
        if (JavaType.METHOD_HANDLE.getTypeStub().isAssignableFrom(cls)) {
            return new ForPoolValue(new JavaConstantValue(JavaConstant.MethodHandle.ofLoaded(obj)), cls);
        }
        if (JavaType.METHOD_TYPE.getTypeStub().represents(cls)) {
            return new ForPoolValue(new JavaConstantValue(JavaConstant.MethodType.ofLoaded(obj)), cls);
        }
        return reference(obj);
    }

    public static AssignerConfigurable reference(Object obj) {
        return new ForValue(obj);
    }

    public static AssignerConfigurable reference(Object obj, String str) {
        return new ForValue(str, obj);
    }

    public static AssignerConfigurable value(TypeDescription typeDescription) {
        return new ForPoolValue(ClassConstant.of(typeDescription), TypeDescription.CLASS);
    }

    public static AssignerConfigurable value(JavaConstant javaConstant) {
        return new ForPoolValue(javaConstant.asStackManipulation(), javaConstant.getType());
    }

    public static AssignerConfigurable argument(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Argument index cannot be negative: " + i);
        }
        return new ForArgument(i);
    }

    public static AssignerConfigurable self() {
        return new ForThisValue();
    }

    public static Implementation nullValue() {
        return ForNullValue.INSTANCE;
    }

    public static AssignerConfigurable originType() {
        return new ForOriginType();
    }

    protected ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription, TypeDescription.Generic generic, StackManipulation stackManipulation) {
        StackManipulation stackManipulationAssign = this.assigner.assign(generic, methodDescription.getReturnType(), this.typing);
        if (!stackManipulationAssign.isValid()) {
            throw new IllegalArgumentException("Cannot return value of type " + generic + " for " + methodDescription);
        }
        return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulation, stackManipulationAssign, MethodReturn.of(methodDescription.getReturnType())).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
    }

    protected enum ForNullValue implements Implementation, ByteCodeAppender {
        INSTANCE;

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return this;
        }

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            if (methodDescription.getReturnType().isPrimitive()) {
                throw new IllegalStateException("Cannot return null from " + methodDescription);
            }
            return new ByteCodeAppender.Simple(NullConstant.INSTANCE, MethodReturn.REFERENCE).apply(methodVisitor, context, methodDescription);
        }
    }

    protected static class ForOriginType extends FixedValue implements AssignerConfigurable {
        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        protected ForOriginType() {
            this(Assigner.DEFAULT, Assigner.Typing.STATIC);
        }

        private ForOriginType(Assigner assigner, Assigner.Typing typing) {
            super(assigner, typing);
        }

        @Override // net.bytebuddy.implementation.FixedValue.AssignerConfigurable
        public Implementation withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new ForOriginType(assigner, typing);
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(target.getOriginType().asErasure());
        }

        protected class Appender implements ByteCodeAppender {
            private final TypeDescription originType;

            protected Appender(TypeDescription typeDescription) {
                this.originType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                return ForOriginType.this.apply(methodVisitor, context, methodDescription, TypeDescription.CLASS.asGenericType(), ClassConstant.of(this.originType));
            }

            private ForOriginType getOuter() {
                return ForOriginType.this;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Appender appender = (Appender) obj;
                return this.originType.equals(appender.originType) && getOuter().equals(appender.getOuter());
            }

            public int hashCode() {
                return (getOuter().hashCode() * 31) + this.originType.hashCode();
            }
        }
    }

    protected static class ForThisValue extends FixedValue implements AssignerConfigurable {
        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        protected ForThisValue() {
            super(Assigner.DEFAULT, Assigner.Typing.STATIC);
        }

        private ForThisValue(Assigner assigner, Assigner.Typing typing) {
            super(assigner, typing);
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(target.getInstrumentedType());
        }

        @Override // net.bytebuddy.implementation.FixedValue.AssignerConfigurable
        public Implementation withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new ForThisValue(assigner, typing);
        }

        protected static class Appender implements ByteCodeAppender {
            private final TypeDescription instrumentedType;

            protected boolean canEqual(Object obj) {
                return obj instanceof Appender;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Appender)) {
                    return false;
                }
                Appender appender = (Appender) obj;
                if (!appender.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.instrumentedType;
                TypeDescription typeDescription2 = appender.instrumentedType;
                return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.instrumentedType;
                return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
            }

            protected Appender(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                if (methodDescription.isStatic() || !this.instrumentedType.isAssignableTo(methodDescription.getReturnType().asErasure())) {
                    throw new IllegalStateException("Cannot return 'this' from " + methodDescription);
                }
                return new ByteCodeAppender.Simple(MethodVariableAccess.loadThis(), MethodReturn.REFERENCE).apply(methodVisitor, context, methodDescription);
            }
        }
    }

    protected static class ForArgument extends FixedValue implements AssignerConfigurable, ByteCodeAppender {
        private final int index;

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return this;
        }

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        @Override // net.bytebuddy.implementation.FixedValue
        protected boolean canEqual(Object obj) {
            return obj instanceof ForArgument;
        }

        @Override // net.bytebuddy.implementation.FixedValue
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForArgument)) {
                return false;
            }
            ForArgument forArgument = (ForArgument) obj;
            return forArgument.canEqual(this) && super.equals(obj) && this.index == forArgument.index;
        }

        @Override // net.bytebuddy.implementation.FixedValue
        public int hashCode() {
            return ((super.hashCode() + 59) * 59) + this.index;
        }

        protected ForArgument(int i) {
            this(Assigner.DEFAULT, Assigner.Typing.STATIC, i);
        }

        private ForArgument(Assigner assigner, Assigner.Typing typing, int i) {
            super(assigner, typing);
            this.index = i;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            if (methodDescription.getParameters().size() <= this.index) {
                throw new IllegalStateException(methodDescription + " does not define a parameter with index " + this.index);
            }
            ParameterDescription parameterDescription = (ParameterDescription) methodDescription.getParameters().get(this.index);
            StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.load(parameterDescription), this.assigner.assign(parameterDescription.getType(), methodDescription.getReturnType(), this.typing), MethodReturn.of(methodDescription.getReturnType()));
            if (!compound.isValid()) {
                throw new IllegalStateException("Cannot assign " + methodDescription.getReturnType() + " to " + parameterDescription);
            }
            return new ByteCodeAppender.Size(compound.apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
        }

        @Override // net.bytebuddy.implementation.FixedValue.AssignerConfigurable
        public Implementation withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new ForArgument(assigner, typing, this.index);
        }
    }

    protected static class ForPoolValue extends FixedValue implements AssignerConfigurable, ByteCodeAppender {
        private final TypeDescription loadedType;
        private final StackManipulation valueLoadInstruction;

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return this;
        }

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        @Override // net.bytebuddy.implementation.FixedValue
        protected boolean canEqual(Object obj) {
            return obj instanceof ForPoolValue;
        }

        @Override // net.bytebuddy.implementation.FixedValue
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForPoolValue)) {
                return false;
            }
            ForPoolValue forPoolValue = (ForPoolValue) obj;
            if (!forPoolValue.canEqual(this) || !super.equals(obj)) {
                return false;
            }
            StackManipulation stackManipulation = this.valueLoadInstruction;
            StackManipulation stackManipulation2 = forPoolValue.valueLoadInstruction;
            if (stackManipulation != null ? !stackManipulation.equals(stackManipulation2) : stackManipulation2 != null) {
                return false;
            }
            TypeDescription typeDescription = this.loadedType;
            TypeDescription typeDescription2 = forPoolValue.loadedType;
            return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
        }

        @Override // net.bytebuddy.implementation.FixedValue
        public int hashCode() {
            int iHashCode = super.hashCode() + 59;
            StackManipulation stackManipulation = this.valueLoadInstruction;
            int iHashCode2 = (iHashCode * 59) + (stackManipulation == null ? 43 : stackManipulation.hashCode());
            TypeDescription typeDescription = this.loadedType;
            return (iHashCode2 * 59) + (typeDescription != null ? typeDescription.hashCode() : 43);
        }

        protected ForPoolValue(StackManipulation stackManipulation, Class<?> cls) {
            this(stackManipulation, new TypeDescription.ForLoadedType(cls));
        }

        protected ForPoolValue(StackManipulation stackManipulation, TypeDescription typeDescription) {
            this(Assigner.DEFAULT, Assigner.Typing.STATIC, stackManipulation, typeDescription);
        }

        private ForPoolValue(Assigner assigner, Assigner.Typing typing, StackManipulation stackManipulation, TypeDescription typeDescription) {
            super(assigner, typing);
            this.valueLoadInstruction = stackManipulation;
            this.loadedType = typeDescription;
        }

        @Override // net.bytebuddy.implementation.FixedValue.AssignerConfigurable
        public Implementation withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new ForPoolValue(assigner, typing, this.valueLoadInstruction, this.loadedType);
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            return apply(methodVisitor, context, methodDescription, this.loadedType.asGenericType(), this.valueLoadInstruction);
        }
    }

    protected static class ForValue extends FixedValue implements AssignerConfigurable {
        private static final String PREFIX = "value";
        private final String fieldName;
        private final TypeDescription.Generic fieldType;
        private final Object value;

        @Override // net.bytebuddy.implementation.FixedValue
        protected boolean canEqual(Object obj) {
            return obj instanceof ForValue;
        }

        @Override // net.bytebuddy.implementation.FixedValue
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForValue)) {
                return false;
            }
            ForValue forValue = (ForValue) obj;
            if (!forValue.canEqual(this) || !super.equals(obj)) {
                return false;
            }
            String str = this.fieldName;
            String str2 = forValue.fieldName;
            if (str != null ? !str.equals(str2) : str2 != null) {
                return false;
            }
            Object obj2 = this.value;
            Object obj3 = forValue.value;
            return obj2 != null ? obj2.equals(obj3) : obj3 == null;
        }

        @Override // net.bytebuddy.implementation.FixedValue
        public int hashCode() {
            int iHashCode = super.hashCode() + 59;
            String str = this.fieldName;
            int iHashCode2 = (iHashCode * 59) + (str == null ? 43 : str.hashCode());
            Object obj = this.value;
            return (iHashCode2 * 59) + (obj != null ? obj.hashCode() : 43);
        }

        protected ForValue(Object obj) {
            this(String.format("%s$%s", "value", RandomString.hashOf(obj.hashCode())), obj);
        }

        protected ForValue(String str, Object obj) {
            this(Assigner.DEFAULT, Assigner.Typing.STATIC, str, obj);
        }

        private ForValue(Assigner assigner, Assigner.Typing typing, String str, Object obj) {
            super(assigner, typing);
            this.fieldName = str;
            this.value = obj;
            this.fieldType = new TypeDescription.Generic.OfNonGenericType.ForLoadedType(obj.getClass());
        }

        @Override // net.bytebuddy.implementation.FixedValue.AssignerConfigurable
        public Implementation withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new ForValue(assigner, typing, this.fieldName, this.value);
        }

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType.withField(new FieldDescription.Token(this.fieldName, 4105, this.fieldType)).withInitializer(new LoadedTypeInitializer.ForStaticField(this.fieldName, this.value));
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return new StaticFieldByteCodeAppender(target.getInstrumentedType());
        }

        private class StaticFieldByteCodeAppender implements ByteCodeAppender {
            private final StackManipulation fieldGetAccess;

            protected boolean canEqual(Object obj) {
                return obj instanceof StaticFieldByteCodeAppender;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof StaticFieldByteCodeAppender)) {
                    return false;
                }
                StaticFieldByteCodeAppender staticFieldByteCodeAppender = (StaticFieldByteCodeAppender) obj;
                if (!staticFieldByteCodeAppender.canEqual(this)) {
                    return false;
                }
                StackManipulation stackManipulation = this.fieldGetAccess;
                StackManipulation stackManipulation2 = staticFieldByteCodeAppender.fieldGetAccess;
                return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
            }

            public int hashCode() {
                StackManipulation stackManipulation = this.fieldGetAccess;
                return 59 + (stackManipulation == null ? 43 : stackManipulation.hashCode());
            }

            private StaticFieldByteCodeAppender(TypeDescription typeDescription) {
                this.fieldGetAccess = FieldAccess.forField((FieldDescription.InDefinedShape) typeDescription.getDeclaredFields().filter(ElementMatchers.named(ForValue.this.fieldName)).getOnly()).read();
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                ForValue forValue = ForValue.this;
                return forValue.apply(methodVisitor, context, methodDescription, forValue.fieldType, this.fieldGetAccess);
            }
        }
    }
}
