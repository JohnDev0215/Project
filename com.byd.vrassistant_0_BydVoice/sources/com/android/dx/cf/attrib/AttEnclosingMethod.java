package com.android.dx.cf.attrib;

import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstType;

/* JADX INFO: loaded from: classes.dex */
public final class AttEnclosingMethod extends BaseAttribute {
    public static final String ATTRIBUTE_NAME = "EnclosingMethod";
    private final CstNat method;
    private final CstType type;

    @Override // com.android.dx.cf.iface.Attribute
    public int byteLength() {
        return 10;
    }

    public AttEnclosingMethod(CstType cstType, CstNat cstNat) {
        super("EnclosingMethod");
        if (cstType == null) {
            throw new NullPointerException("type == null");
        }
        this.type = cstType;
        this.method = cstNat;
    }

    public CstType getEnclosingClass() {
        return this.type;
    }

    public CstNat getMethod() {
        return this.method;
    }
}
