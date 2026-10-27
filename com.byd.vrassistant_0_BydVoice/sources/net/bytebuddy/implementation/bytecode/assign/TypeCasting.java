package net.bytebuddy.implementation.bytecode.assign;

import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public class TypeCasting implements StackManipulation {
    private final TypeDescription typeDescription;

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof TypeCasting;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TypeCasting)) {
            return false;
        }
        TypeCasting typeCasting = (TypeCasting) obj;
        if (!typeCasting.canEqual(this)) {
            return false;
        }
        TypeDescription typeDescription = this.typeDescription;
        TypeDescription typeDescription2 = typeCasting.typeDescription;
        return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
    }

    public int hashCode() {
        TypeDescription typeDescription = this.typeDescription;
        return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
    }

    protected TypeCasting(TypeDescription typeDescription) {
        this.typeDescription = typeDescription;
    }

    public static StackManipulation to(TypeDefinition typeDefinition) {
        if (typeDefinition.isPrimitive()) {
            throw new IllegalArgumentException("Cannot cast to primitive type: " + typeDefinition);
        }
        return new TypeCasting(typeDefinition.asErasure());
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitTypeInsn(192, this.typeDescription.getInternalName());
        return StackSize.ZERO.toIncreasingSize();
    }
}
