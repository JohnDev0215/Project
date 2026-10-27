package net.bytebuddy.implementation.bytecode.member;

import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;

/* JADX INFO: loaded from: classes3.dex */
public enum FieldAccess {
    STATIC(179, 178, StackSize.ZERO),
    INSTANCE(181, 180, StackSize.SINGLE);

    private final int getterOpcode;
    private final int putterOpcode;
    private final int targetSizeChange;

    public interface Defined {
        StackManipulation read();

        StackManipulation write();
    }

    FieldAccess(int i, int i2, StackSize stackSize) {
        this.putterOpcode = i;
        this.getterOpcode = i2;
        this.targetSizeChange = stackSize.getSize();
    }

    public static StackManipulation forEnumeration(EnumerationDescription enumerationDescription) {
        FieldList fieldListFilter = enumerationDescription.getEnumerationType().getDeclaredFields().filter(ElementMatchers.named(enumerationDescription.getValue()));
        if (fieldListFilter.size() != 1 || !((FieldDescription.InDefinedShape) fieldListFilter.getOnly()).isStatic() || !((FieldDescription.InDefinedShape) fieldListFilter.getOnly()).isPublic() || !((FieldDescription.InDefinedShape) fieldListFilter.getOnly()).isEnum()) {
            return StackManipulation.Illegal.INSTANCE;
        }
        FieldAccess fieldAccess = STATIC;
        fieldAccess.getClass();
        return fieldAccess.new AccessDispatcher((FieldDescription.InDefinedShape) fieldListFilter.getOnly()).read();
    }

    public static Defined forField(FieldDescription.InDefinedShape inDefinedShape) {
        if (inDefinedShape.isStatic()) {
            FieldAccess fieldAccess = STATIC;
            fieldAccess.getClass();
            return fieldAccess.new AccessDispatcher(inDefinedShape);
        }
        FieldAccess fieldAccess2 = INSTANCE;
        fieldAccess2.getClass();
        return fieldAccess2.new AccessDispatcher(inDefinedShape);
    }

    public static Defined forField(FieldDescription fieldDescription) {
        FieldDescription.InDefinedShape inDefinedShapeAsDefined = fieldDescription.asDefined();
        if (fieldDescription.getType().asErasure().equals(inDefinedShapeAsDefined.getType().asErasure())) {
            return forField(inDefinedShapeAsDefined);
        }
        return OfGenericField.of(fieldDescription, forField(inDefinedShapeAsDefined));
    }

    protected static class OfGenericField implements Defined {
        private final Defined defined;
        private final TypeDefinition targetType;

        protected boolean canEqual(Object obj) {
            return obj instanceof OfGenericField;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof OfGenericField)) {
                return false;
            }
            OfGenericField ofGenericField = (OfGenericField) obj;
            if (!ofGenericField.canEqual(this)) {
                return false;
            }
            TypeDefinition typeDefinition = this.targetType;
            TypeDefinition typeDefinition2 = ofGenericField.targetType;
            if (typeDefinition != null ? !typeDefinition.equals(typeDefinition2) : typeDefinition2 != null) {
                return false;
            }
            Defined defined = this.defined;
            Defined defined2 = ofGenericField.defined;
            return defined != null ? defined.equals(defined2) : defined2 == null;
        }

        public int hashCode() {
            TypeDefinition typeDefinition = this.targetType;
            int iHashCode = typeDefinition == null ? 43 : typeDefinition.hashCode();
            Defined defined = this.defined;
            return ((iHashCode + 59) * 59) + (defined != null ? defined.hashCode() : 43);
        }

        protected OfGenericField(TypeDefinition typeDefinition, Defined defined) {
            this.targetType = typeDefinition;
            this.defined = defined;
        }

        protected static Defined of(FieldDescription fieldDescription, Defined defined) {
            return new OfGenericField(fieldDescription.getType(), defined);
        }

        @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.Defined
        public StackManipulation read() {
            return new StackManipulation.Compound(this.defined.read(), TypeCasting.to(this.targetType));
        }

        @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.Defined
        public StackManipulation write() {
            return this.defined.write();
        }
    }

    protected class AccessDispatcher implements Defined {
        private final FieldDescription.InDefinedShape fieldDescription;

        protected AccessDispatcher(FieldDescription.InDefinedShape inDefinedShape) {
            this.fieldDescription = inDefinedShape;
        }

        @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.Defined
        public StackManipulation read() {
            return new FieldGetInstruction();
        }

        @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.Defined
        public StackManipulation write() {
            return new FieldPutInstruction();
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj != null && getClass() == obj.getClass()) {
                    AccessDispatcher accessDispatcher = (AccessDispatcher) obj;
                    if (!FieldAccess.this.equals(accessDispatcher.getFieldAccess()) || !this.fieldDescription.equals(accessDispatcher.fieldDescription)) {
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.fieldDescription.hashCode() + (FieldAccess.this.hashCode() * 31);
        }

        private FieldAccess getFieldAccess() {
            return FieldAccess.this;
        }

        private abstract class AbstractFieldInstruction implements StackManipulation {
            protected abstract int getOpcode();

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected abstract StackManipulation.Size resolveSize(StackSize stackSize);

            private AbstractFieldInstruction() {
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitFieldInsn(getOpcode(), AccessDispatcher.this.fieldDescription.getDeclaringType().getInternalName(), AccessDispatcher.this.fieldDescription.getInternalName(), AccessDispatcher.this.fieldDescription.getDescriptor());
                return resolveSize(AccessDispatcher.this.fieldDescription.getType().getStackSize());
            }
        }

        protected class FieldGetInstruction extends AbstractFieldInstruction {
            protected FieldGetInstruction() {
                super();
            }

            @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.AccessDispatcher.AbstractFieldInstruction
            protected int getOpcode() {
                return FieldAccess.this.getterOpcode;
            }

            @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.AccessDispatcher.AbstractFieldInstruction
            protected StackManipulation.Size resolveSize(StackSize stackSize) {
                int size = stackSize.getSize() - FieldAccess.this.targetSizeChange;
                return new StackManipulation.Size(size, size);
            }

            public boolean equals(Object obj) {
                return this == obj || (obj != null && getClass() == obj.getClass() && getAccessDispatcher().equals(((FieldGetInstruction) obj).getAccessDispatcher()));
            }

            public int hashCode() {
                return getAccessDispatcher().hashCode() + 7;
            }

            private AccessDispatcher getAccessDispatcher() {
                return AccessDispatcher.this;
            }
        }

        protected class FieldPutInstruction extends AbstractFieldInstruction {
            protected FieldPutInstruction() {
                super();
            }

            @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.AccessDispatcher.AbstractFieldInstruction
            protected int getOpcode() {
                return FieldAccess.this.putterOpcode;
            }

            @Override // net.bytebuddy.implementation.bytecode.member.FieldAccess.AccessDispatcher.AbstractFieldInstruction
            protected StackManipulation.Size resolveSize(StackSize stackSize) {
                return new StackManipulation.Size((stackSize.getSize() + FieldAccess.this.targetSizeChange) * (-1), 0);
            }

            public boolean equals(Object obj) {
                return this == obj || (obj != null && getClass() == obj.getClass() && getAccessDispatcher().equals(((FieldPutInstruction) obj).getAccessDispatcher()));
            }

            public int hashCode() {
                return getAccessDispatcher().hashCode() + 14;
            }

            private AccessDispatcher getAccessDispatcher() {
                return AccessDispatcher.this;
            }
        }
    }
}
