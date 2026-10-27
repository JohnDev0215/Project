package net.bytebuddy.implementation.bytecode.assign.primitive;

import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public enum PrimitiveUnboxingDelegate implements StackManipulation {
    BOOLEAN(Boolean.class, Boolean.TYPE, StackSize.ZERO, "booleanValue", "()Z"),
    BYTE(Byte.class, Byte.TYPE, StackSize.ZERO, "byteValue", "()B"),
    SHORT(Short.class, Short.TYPE, StackSize.ZERO, "shortValue", "()S"),
    CHARACTER(Character.class, Character.TYPE, StackSize.ZERO, "charValue", "()C"),
    INTEGER(Integer.class, Integer.TYPE, StackSize.ZERO, "intValue", "()I"),
    LONG(Long.class, Long.TYPE, StackSize.SINGLE, "longValue", "()J"),
    FLOAT(Float.class, Float.TYPE, StackSize.ZERO, "floatValue", "()F"),
    DOUBLE(Double.class, Double.TYPE, StackSize.SINGLE, "doubleValue", "()D");

    private final TypeDescription primitiveType;
    private final StackManipulation.Size size;
    private final String unboxingMethodDescriptor;
    private final String unboxingMethodName;
    private final TypeDescription wrapperType;

    public interface UnboxingResponsible {
        StackManipulation assignUnboxedTo(TypeDescription.Generic generic, Assigner assigner, Assigner.Typing typing);
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    PrimitiveUnboxingDelegate(Class cls, Class cls2, StackSize stackSize, String str, String str2) {
        this.size = stackSize.toIncreasingSize();
        this.wrapperType = new TypeDescription.ForLoadedType(cls);
        this.primitiveType = new TypeDescription.ForLoadedType(cls2);
        this.unboxingMethodName = str;
        this.unboxingMethodDescriptor = str2;
    }

    protected static PrimitiveUnboxingDelegate forPrimitive(TypeDescription.Generic generic) {
        if (generic.represents(Boolean.TYPE)) {
            return BOOLEAN;
        }
        if (generic.represents(Byte.TYPE)) {
            return BYTE;
        }
        if (generic.represents(Short.TYPE)) {
            return SHORT;
        }
        if (generic.represents(Character.TYPE)) {
            return CHARACTER;
        }
        if (generic.represents(Integer.TYPE)) {
            return INTEGER;
        }
        if (generic.represents(Long.TYPE)) {
            return LONG;
        }
        if (generic.represents(Float.TYPE)) {
            return FLOAT;
        }
        if (generic.represents(Double.TYPE)) {
            return DOUBLE;
        }
        throw new IllegalArgumentException("Expected non-void primitive type instead of " + generic);
    }

    public static UnboxingResponsible forReferenceType(TypeDefinition typeDefinition) {
        if (typeDefinition.isPrimitive()) {
            throw new IllegalArgumentException("Expected reference type instead of " + typeDefinition);
        }
        if (typeDefinition.represents(Boolean.class)) {
            return ExplicitlyTypedUnboxingResponsible.BOOLEAN;
        }
        if (typeDefinition.represents(Byte.class)) {
            return ExplicitlyTypedUnboxingResponsible.BYTE;
        }
        if (typeDefinition.represents(Short.class)) {
            return ExplicitlyTypedUnboxingResponsible.SHORT;
        }
        if (typeDefinition.represents(Character.class)) {
            return ExplicitlyTypedUnboxingResponsible.CHARACTER;
        }
        if (typeDefinition.represents(Integer.class)) {
            return ExplicitlyTypedUnboxingResponsible.INTEGER;
        }
        if (typeDefinition.represents(Long.class)) {
            return ExplicitlyTypedUnboxingResponsible.LONG;
        }
        if (typeDefinition.represents(Float.class)) {
            return ExplicitlyTypedUnboxingResponsible.FLOAT;
        }
        if (typeDefinition.represents(Double.class)) {
            return ExplicitlyTypedUnboxingResponsible.DOUBLE;
        }
        return new ImplicitlyTypedUnboxingResponsible(typeDefinition.asGenericType());
    }

    protected TypeDescription.Generic getWrapperType() {
        return this.wrapperType.asGenericType();
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitMethodInsn(182, this.wrapperType.asErasure().getInternalName(), this.unboxingMethodName, this.unboxingMethodDescriptor, false);
        return this.size;
    }

    protected enum ExplicitlyTypedUnboxingResponsible implements UnboxingResponsible {
        BOOLEAN(PrimitiveUnboxingDelegate.BOOLEAN),
        BYTE(PrimitiveUnboxingDelegate.BYTE),
        SHORT(PrimitiveUnboxingDelegate.SHORT),
        CHARACTER(PrimitiveUnboxingDelegate.CHARACTER),
        INTEGER(PrimitiveUnboxingDelegate.INTEGER),
        LONG(PrimitiveUnboxingDelegate.LONG),
        FLOAT(PrimitiveUnboxingDelegate.FLOAT),
        DOUBLE(PrimitiveUnboxingDelegate.DOUBLE);

        private final PrimitiveUnboxingDelegate primitiveUnboxingDelegate;

        ExplicitlyTypedUnboxingResponsible(PrimitiveUnboxingDelegate primitiveUnboxingDelegate) {
            this.primitiveUnboxingDelegate = primitiveUnboxingDelegate;
        }

        @Override // net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveUnboxingDelegate.UnboxingResponsible
        public StackManipulation assignUnboxedTo(TypeDescription.Generic generic, Assigner assigner, Assigner.Typing typing) {
            PrimitiveUnboxingDelegate primitiveUnboxingDelegate = this.primitiveUnboxingDelegate;
            return new StackManipulation.Compound(primitiveUnboxingDelegate, PrimitiveWideningDelegate.forPrimitive(primitiveUnboxingDelegate.primitiveType).widenTo(generic));
        }
    }

    protected static class ImplicitlyTypedUnboxingResponsible implements UnboxingResponsible {
        private final TypeDescription.Generic originalType;

        protected boolean canEqual(Object obj) {
            return obj instanceof ImplicitlyTypedUnboxingResponsible;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ImplicitlyTypedUnboxingResponsible)) {
                return false;
            }
            ImplicitlyTypedUnboxingResponsible implicitlyTypedUnboxingResponsible = (ImplicitlyTypedUnboxingResponsible) obj;
            if (!implicitlyTypedUnboxingResponsible.canEqual(this)) {
                return false;
            }
            TypeDescription.Generic generic = this.originalType;
            TypeDescription.Generic generic2 = implicitlyTypedUnboxingResponsible.originalType;
            return generic != null ? generic.equals(generic2) : generic2 == null;
        }

        public int hashCode() {
            TypeDescription.Generic generic = this.originalType;
            return 59 + (generic == null ? 43 : generic.hashCode());
        }

        protected ImplicitlyTypedUnboxingResponsible(TypeDescription.Generic generic) {
            this.originalType = generic;
        }

        @Override // net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveUnboxingDelegate.UnboxingResponsible
        public StackManipulation assignUnboxedTo(TypeDescription.Generic generic, Assigner assigner, Assigner.Typing typing) {
            PrimitiveUnboxingDelegate primitiveUnboxingDelegateForPrimitive = PrimitiveUnboxingDelegate.forPrimitive(generic);
            return new StackManipulation.Compound(assigner.assign(this.originalType, primitiveUnboxingDelegateForPrimitive.getWrapperType(), typing), primitiveUnboxingDelegateForPrimitive);
        }
    }
}
