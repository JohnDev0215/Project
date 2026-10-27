package net.bytebuddy.implementation.bytecode.assign.primitive;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;

/* JADX INFO: loaded from: classes3.dex */
public class VoidAwareAssigner implements Assigner {
    private final Assigner chained;

    protected boolean canEqual(Object obj) {
        return obj instanceof VoidAwareAssigner;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VoidAwareAssigner)) {
            return false;
        }
        VoidAwareAssigner voidAwareAssigner = (VoidAwareAssigner) obj;
        if (!voidAwareAssigner.canEqual(this)) {
            return false;
        }
        Assigner assigner = this.chained;
        Assigner assigner2 = voidAwareAssigner.chained;
        return assigner != null ? assigner.equals(assigner2) : assigner2 == null;
    }

    public int hashCode() {
        Assigner assigner = this.chained;
        return 59 + (assigner == null ? 43 : assigner.hashCode());
    }

    public VoidAwareAssigner(Assigner assigner) {
        this.chained = assigner;
    }

    @Override // net.bytebuddy.implementation.bytecode.assign.Assigner
    public StackManipulation assign(TypeDescription.Generic generic, TypeDescription.Generic generic2, Assigner.Typing typing) {
        if (generic.represents(Void.TYPE) && generic2.represents(Void.TYPE)) {
            return StackManipulation.Trivial.INSTANCE;
        }
        if (generic.represents(Void.TYPE)) {
            return typing.isDynamic() ? DefaultValue.of(generic2) : StackManipulation.Illegal.INSTANCE;
        }
        if (generic2.represents(Void.TYPE)) {
            return Removal.of(generic);
        }
        return this.chained.assign(generic, generic2, typing);
    }
}
