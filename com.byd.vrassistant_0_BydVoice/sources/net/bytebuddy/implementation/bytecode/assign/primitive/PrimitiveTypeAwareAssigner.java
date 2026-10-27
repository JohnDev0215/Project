package net.bytebuddy.implementation.bytecode.assign.primitive;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

/* JADX INFO: loaded from: classes3.dex */
public class PrimitiveTypeAwareAssigner implements Assigner {
    private final Assigner referenceTypeAwareAssigner;

    protected boolean canEqual(Object obj) {
        return obj instanceof PrimitiveTypeAwareAssigner;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PrimitiveTypeAwareAssigner)) {
            return false;
        }
        PrimitiveTypeAwareAssigner primitiveTypeAwareAssigner = (PrimitiveTypeAwareAssigner) obj;
        if (!primitiveTypeAwareAssigner.canEqual(this)) {
            return false;
        }
        Assigner assigner = this.referenceTypeAwareAssigner;
        Assigner assigner2 = primitiveTypeAwareAssigner.referenceTypeAwareAssigner;
        return assigner != null ? assigner.equals(assigner2) : assigner2 == null;
    }

    public int hashCode() {
        Assigner assigner = this.referenceTypeAwareAssigner;
        return 59 + (assigner == null ? 43 : assigner.hashCode());
    }

    public PrimitiveTypeAwareAssigner(Assigner assigner) {
        this.referenceTypeAwareAssigner = assigner;
    }

    @Override // net.bytebuddy.implementation.bytecode.assign.Assigner
    public StackManipulation assign(TypeDescription.Generic generic, TypeDescription.Generic generic2, Assigner.Typing typing) {
        if (generic.isPrimitive() && generic2.isPrimitive()) {
            return PrimitiveWideningDelegate.forPrimitive(generic).widenTo(generic2);
        }
        if (generic.isPrimitive()) {
            return PrimitiveBoxingDelegate.forPrimitive(generic).assignBoxedTo(generic2, this.referenceTypeAwareAssigner, typing);
        }
        if (generic2.isPrimitive()) {
            return PrimitiveUnboxingDelegate.forReferenceType(generic).assignUnboxedTo(generic2, this.referenceTypeAwareAssigner, typing);
        }
        return this.referenceTypeAwareAssigner.assign(generic, generic2, typing);
    }
}
