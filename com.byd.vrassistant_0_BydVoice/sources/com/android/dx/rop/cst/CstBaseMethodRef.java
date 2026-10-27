package com.android.dx.rop.cst;

import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.Type;

/* JADX INFO: loaded from: classes.dex */
public abstract class CstBaseMethodRef extends CstMemberRef {
    private Prototype instancePrototype;
    private final Prototype prototype;

    CstBaseMethodRef(CstType cstType, CstNat cstNat) {
        super(cstType, cstNat);
        this.prototype = Prototype.intern(getNat().getDescriptor().getString());
        this.instancePrototype = null;
    }

    public final Prototype getPrototype() {
        return this.prototype;
    }

    public final Prototype getPrototype(boolean z) {
        if (z) {
            return this.prototype;
        }
        if (this.instancePrototype == null) {
            this.instancePrototype = this.prototype.withFirstParameter(getDefiningClass().getClassType());
        }
        return this.instancePrototype;
    }

    @Override // com.android.dx.rop.cst.CstMemberRef, com.android.dx.rop.cst.Constant
    protected final int compareTo0(Constant constant) {
        int iCompareTo0 = super.compareTo0(constant);
        return iCompareTo0 != 0 ? iCompareTo0 : this.prototype.compareTo(((CstBaseMethodRef) constant).prototype);
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public final Type getType() {
        return this.prototype.getReturnType();
    }

    public final int getParameterWordCount(boolean z) {
        return getPrototype(z).getParameterTypes().getWordCount();
    }

    public final boolean isInstanceInit() {
        return getNat().isInstanceInit();
    }

    public final boolean isClassInit() {
        return getNat().isClassInit();
    }
}
