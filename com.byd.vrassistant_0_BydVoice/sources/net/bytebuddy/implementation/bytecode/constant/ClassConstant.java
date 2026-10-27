package net.bytebuddy.implementation.bytecode.constant;

import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;

/* JADX INFO: loaded from: classes3.dex */
public enum ClassConstant implements StackManipulation {
    VOID(Void.class),
    BOOLEAN(Boolean.class),
    BYTE(Byte.class),
    SHORT(Short.class),
    CHARACTER(Character.class),
    INTEGER(Integer.class),
    LONG(Long.class),
    FLOAT(Float.class),
    DOUBLE(Double.class);

    private static final String CLASS_TYPE_INTERNAL_NAME = "Ljava/lang/Class;";
    private static final String PRIMITIVE_TYPE_FIELD = "TYPE";
    private static final StackManipulation.Size SIZE = StackSize.SINGLE.toIncreasingSize();
    private final String fieldOwnerInternalName;

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    ClassConstant(Class cls) {
        this.fieldOwnerInternalName = Type.getInternalName(cls);
    }

    public static StackManipulation of(TypeDescription typeDescription) {
        if (typeDescription.represents(Void.TYPE)) {
            return VOID;
        }
        if (typeDescription.represents(Boolean.TYPE)) {
            return BOOLEAN;
        }
        if (typeDescription.represents(Byte.TYPE)) {
            return BYTE;
        }
        if (typeDescription.represents(Short.TYPE)) {
            return SHORT;
        }
        if (typeDescription.represents(Character.TYPE)) {
            return CHARACTER;
        }
        if (typeDescription.represents(Integer.TYPE)) {
            return INTEGER;
        }
        if (typeDescription.represents(Long.TYPE)) {
            return LONG;
        }
        if (typeDescription.represents(Float.TYPE)) {
            return FLOAT;
        }
        if (typeDescription.represents(Double.TYPE)) {
            return DOUBLE;
        }
        return new ForReferenceType(typeDescription);
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitFieldInsn(178, this.fieldOwnerInternalName, PRIMITIVE_TYPE_FIELD, CLASS_TYPE_INTERNAL_NAME);
        return SIZE;
    }

    protected static class ForReferenceType implements StackManipulation {
        private final TypeDescription typeDescription;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ForReferenceType;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForReferenceType)) {
                return false;
            }
            ForReferenceType forReferenceType = (ForReferenceType) obj;
            if (!forReferenceType.canEqual(this)) {
                return false;
            }
            TypeDescription typeDescription = this.typeDescription;
            TypeDescription typeDescription2 = forReferenceType.typeDescription;
            return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
        }

        public int hashCode() {
            TypeDescription typeDescription = this.typeDescription;
            return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
        }

        protected ForReferenceType(TypeDescription typeDescription) {
            this.typeDescription = typeDescription;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            if (context.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V5) && this.typeDescription.isVisibleTo(context.getInstrumentedType())) {
                methodVisitor.visitLdcInsn(Type.getType(this.typeDescription.getDescriptor()));
            } else {
                methodVisitor.visitLdcInsn(this.typeDescription.getName());
                methodVisitor.visitMethodInsn(184, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME, "forName", "(Ljava/lang/String;)Ljava/lang/Class;", false);
            }
            return ClassConstant.SIZE;
        }
    }
}
