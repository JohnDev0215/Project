package com.google.protobuf;

import com.google.protobuf.g;
import com.google.protobuf.r0;
import defpackage.lk;
import defpackage.si3;
import defpackage.wv1;

/* JADX INFO: compiled from: AbstractParser.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c<MessageType extends r0> implements wv1<MessageType> {
    public static final s a = s.a();

    public static r0 c(r0 r0Var) {
        if (r0Var == null || r0Var.isInitialized()) {
            return r0Var;
        }
        si3 si3VarZ = r0Var instanceof b ? ((b) r0Var).z() : new si3();
        si3VarZ.getClass();
        d0 d0Var = new d0(si3VarZ.getMessage());
        d0Var.e = r0Var;
        throw d0Var;
    }

    @Override // defpackage.wv1
    public final r0 a(lk lkVar, s sVar) throws d0 {
        try {
            g gVarT = lkVar.t();
            MessageType messagetypeB = b(gVarT, sVar);
            try {
                gVarT.a(0);
                c(messagetypeB);
                return messagetypeB;
            } catch (d0 e) {
                e.e = messagetypeB;
                throw e;
            }
        } catch (d0 e2) {
            throw e2;
        }
    }

    public r0 d(byte[] bArr, int i, s sVar) {
        try {
            g.a aVarH = g.h(bArr, 0, i, false);
            MessageType messagetypeB = b(aVarH, sVar);
            try {
                aVarH.a(0);
                return messagetypeB;
            } catch (d0 e) {
                e.e = messagetypeB;
                throw e;
            }
        } catch (d0 e2) {
            throw e2;
        }
    }
}
