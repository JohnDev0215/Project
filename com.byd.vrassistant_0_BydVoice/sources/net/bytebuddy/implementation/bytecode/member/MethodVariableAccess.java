package net.bytebuddy.implementation.bytecode.member;

import java.util.ArrayList;
import java.util.Iterator;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public enum MethodVariableAccess {
    INTEGER(21, 54, StackSize.SINGLE),
    LONG(22, 55, StackSize.DOUBLE),
    FLOAT(23, 56, StackSize.SINGLE),
    DOUBLE(24, 57, StackSize.DOUBLE),
    REFERENCE(25, 58, StackSize.SINGLE);

    private final int loadOpcode;
    private final StackSize size;
    private final int storeOpcode;

    MethodVariableAccess(int i, int i2, StackSize stackSize) {
        this.loadOpcode = i;
        this.size = stackSize;
        this.storeOpcode = i2;
    }

    public static MethodVariableAccess of(TypeDefinition typeDefinition) {
        if (typeDefinition.isPrimitive()) {
            if (typeDefinition.represents(Long.TYPE)) {
                return LONG;
            }
            if (typeDefinition.represents(Double.TYPE)) {
                return DOUBLE;
            }
            if (typeDefinition.represents(Float.TYPE)) {
                return FLOAT;
            }
            if (typeDefinition.represents(Void.TYPE)) {
                throw new IllegalArgumentException("Variable type cannot be void");
            }
            return INTEGER;
        }
        return REFERENCE;
    }

    public static MethodLoading allArgumentsOf(MethodDescription methodDescription) {
        return new MethodLoading(methodDescription, MethodLoading.TypeCastingHandler.NoOp.INSTANCE);
    }

    public static StackManipulation loadThis() {
        return REFERENCE.loadFrom(0);
    }

    public StackManipulation loadFrom(int i) {
        return new OffsetLoading(i);
    }

    public StackManipulation storeAt(int i) {
        return new OffsetWriting(i);
    }

    public StackManipulation increment(int i, int i2) {
        if (this != INTEGER) {
            throw new IllegalStateException("Cannot increment type: " + this);
        }
        return new OffsetIncrementing(i, i2);
    }

    public static StackManipulation load(ParameterDescription parameterDescription) {
        return of(parameterDescription.getType()).loadFrom(parameterDescription.getOffset());
    }

    public static StackManipulation store(ParameterDescription parameterDescription) {
        return of(parameterDescription.getType()).storeAt(parameterDescription.getOffset());
    }

    public static StackManipulation increment(ParameterDescription parameterDescription, int i) {
        return of(parameterDescription.getType()).increment(parameterDescription.getOffset(), i);
    }

    public static class MethodLoading implements StackManipulation {
        private final MethodDescription methodDescription;
        private final TypeCastingHandler typeCastingHandler;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof MethodLoading;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MethodLoading)) {
                return false;
            }
            MethodLoading methodLoading = (MethodLoading) obj;
            if (!methodLoading.canEqual(this)) {
                return false;
            }
            MethodDescription methodDescription = this.methodDescription;
            MethodDescription methodDescription2 = methodLoading.methodDescription;
            if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                return false;
            }
            TypeCastingHandler typeCastingHandler = this.typeCastingHandler;
            TypeCastingHandler typeCastingHandler2 = methodLoading.typeCastingHandler;
            return typeCastingHandler != null ? typeCastingHandler.equals(typeCastingHandler2) : typeCastingHandler2 == null;
        }

        public int hashCode() {
            MethodDescription methodDescription = this.methodDescription;
            int iHashCode = methodDescription == null ? 43 : methodDescription.hashCode();
            TypeCastingHandler typeCastingHandler = this.typeCastingHandler;
            return ((iHashCode + 59) * 59) + (typeCastingHandler != null ? typeCastingHandler.hashCode() : 43);
        }

        protected MethodLoading(MethodDescription methodDescription, TypeCastingHandler typeCastingHandler) {
            this.methodDescription = methodDescription;
            this.typeCastingHandler = typeCastingHandler;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            ArrayList arrayList = new ArrayList();
            Iterator it = this.methodDescription.getParameters().iterator();
            while (it.hasNext()) {
                ParameterDescription parameterDescription = (ParameterDescription) it.next();
                TypeDescription typeDescriptionAsErasure = parameterDescription.getType().asErasure();
                arrayList.add(MethodVariableAccess.of(typeDescriptionAsErasure).loadFrom(parameterDescription.getOffset()));
                arrayList.add(this.typeCastingHandler.ofIndex(typeDescriptionAsErasure, parameterDescription.getIndex()));
            }
            return new StackManipulation.Compound(arrayList).apply(methodVisitor, context);
        }

        public StackManipulation prependThisReference() {
            return this.methodDescription.isStatic() ? this : new StackManipulation.Compound(MethodVariableAccess.loadThis(), this);
        }

        public MethodLoading asBridgeOf(MethodDescription methodDescription) {
            return new MethodLoading(this.methodDescription, new TypeCastingHandler.ForBridgeTarget(methodDescription));
        }

        protected interface TypeCastingHandler {
            StackManipulation ofIndex(TypeDescription typeDescription, int i);

            public enum NoOp implements TypeCastingHandler {
                INSTANCE;

                @Override // net.bytebuddy.implementation.bytecode.member.MethodVariableAccess.MethodLoading.TypeCastingHandler
                public StackManipulation ofIndex(TypeDescription typeDescription, int i) {
                    return StackManipulation.Trivial.INSTANCE;
                }
            }

            public static class ForBridgeTarget implements TypeCastingHandler {
                private final MethodDescription bridgeTarget;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForBridgeTarget;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForBridgeTarget)) {
                        return false;
                    }
                    ForBridgeTarget forBridgeTarget = (ForBridgeTarget) obj;
                    if (!forBridgeTarget.canEqual(this)) {
                        return false;
                    }
                    MethodDescription methodDescription = this.bridgeTarget;
                    MethodDescription methodDescription2 = forBridgeTarget.bridgeTarget;
                    return methodDescription != null ? methodDescription.equals(methodDescription2) : methodDescription2 == null;
                }

                public int hashCode() {
                    MethodDescription methodDescription = this.bridgeTarget;
                    return 59 + (methodDescription == null ? 43 : methodDescription.hashCode());
                }

                public ForBridgeTarget(MethodDescription methodDescription) {
                    this.bridgeTarget = methodDescription;
                }

                @Override // net.bytebuddy.implementation.bytecode.member.MethodVariableAccess.MethodLoading.TypeCastingHandler
                public StackManipulation ofIndex(TypeDescription typeDescription, int i) {
                    TypeDescription typeDescriptionAsErasure = ((ParameterDescription) this.bridgeTarget.getParameters().get(i)).getType().asErasure();
                    return typeDescription.equals(typeDescriptionAsErasure) ? StackManipulation.Trivial.INSTANCE : TypeCasting.to(typeDescriptionAsErasure);
                }
            }
        }
    }

    protected class OffsetLoading implements StackManipulation {
        private final int offset;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        protected OffsetLoading(int i) {
            this.offset = i;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitVarInsn(MethodVariableAccess.this.loadOpcode, this.offset);
            return MethodVariableAccess.this.size.toIncreasingSize();
        }

        private MethodVariableAccess getMethodVariableAccess() {
            return MethodVariableAccess.this;
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj != null && getClass() == obj.getClass()) {
                    OffsetLoading offsetLoading = (OffsetLoading) obj;
                    if (MethodVariableAccess.this != offsetLoading.getMethodVariableAccess() || this.offset != offsetLoading.offset) {
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return MethodVariableAccess.this.hashCode() + (this.offset * 31);
        }
    }

    protected class OffsetWriting implements StackManipulation {
        private final int offset;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        protected OffsetWriting(int i) {
            this.offset = i;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitVarInsn(MethodVariableAccess.this.storeOpcode, this.offset);
            return MethodVariableAccess.this.size.toDecreasingSize();
        }

        private MethodVariableAccess getMethodVariableAccess() {
            return MethodVariableAccess.this;
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj != null && getClass() == obj.getClass()) {
                    OffsetWriting offsetWriting = (OffsetWriting) obj;
                    if (MethodVariableAccess.this != offsetWriting.getMethodVariableAccess() || this.offset != offsetWriting.offset) {
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return MethodVariableAccess.this.hashCode() + (this.offset * 31);
        }
    }

    protected static class OffsetIncrementing implements StackManipulation {
        private final int offset;
        private final int value;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof OffsetIncrementing;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof OffsetIncrementing)) {
                return false;
            }
            OffsetIncrementing offsetIncrementing = (OffsetIncrementing) obj;
            return offsetIncrementing.canEqual(this) && this.offset == offsetIncrementing.offset && this.value == offsetIncrementing.value;
        }

        public int hashCode() {
            return ((this.offset + 59) * 59) + this.value;
        }

        protected OffsetIncrementing(int i, int i2) {
            this.offset = i;
            this.value = i2;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitIincInsn(this.offset, this.value);
            return new StackManipulation.Size(0, 0);
        }
    }
}
