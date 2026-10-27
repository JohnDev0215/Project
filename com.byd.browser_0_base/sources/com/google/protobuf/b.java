package com.google.protobuf;

import com.google.protobuf.b;
import com.google.protobuf.b.a;
import com.google.protobuf.r0;
import defpackage.lk;
import defpackage.nu;
import defpackage.si3;
import java.io.IOException;

/* JADX INFO: compiled from: AbstractMessageLite.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<MessageType extends b<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> implements r0 {
    protected int memoizedHashCode = 0;

    /* JADX INFO: compiled from: AbstractMessageLite.java */
    public static abstract class a<MessageType extends b<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> implements r0.a {
        @Override // com.google.protobuf.r0.a
        public final r0.a G(r0 r0Var) {
            if (b().getClass().isInstance(r0Var)) {
                return i((b) r0Var);
            }
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }

        public final String g(String str) {
            return "Reading " + getClass().getName() + " from a " + str + " threw an IOException (should never happen).";
        }

        public abstract BuilderType i(MessageType messagetype);

        @Override // com.google.protobuf.r0.a
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public BuilderType t(byte[] bArr) {
            return (BuilderType) r(bArr, bArr.length);
        }

        public abstract a r(byte[] bArr, int i);
    }

    public void A(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.protobuf.r0
    public final lk.f l() {
        try {
            int iM = m();
            lk.f fVar = lk.f;
            byte[] bArr = new byte[iM];
            boolean z = nu.l;
            nu.a aVar = new nu.a(bArr, iM);
            p(aVar);
            if (aVar.R0() == 0) {
                return new lk.f(bArr);
            }
            throw new IllegalStateException("Did not write as much data as expected.");
        } catch (IOException e) {
            throw new RuntimeException(y("ByteString"), e);
        }
    }

    @Override // com.google.protobuf.r0
    public final byte[] q() {
        try {
            int iM = m();
            byte[] bArr = new byte[iM];
            boolean z = nu.l;
            nu.a aVar = new nu.a(bArr, iM);
            p(aVar);
            if (aVar.R0() == 0) {
                return bArr;
            }
            throw new IllegalStateException("Did not write as much data as expected.");
        } catch (IOException e) {
            throw new RuntimeException(y("byte array"), e);
        }
    }

    public int x() {
        throw new UnsupportedOperationException();
    }

    public final String y(String str) {
        return "Serializing " + getClass().getName() + " to a " + str + " threw an IOException (should never happen).";
    }

    public si3 z() {
        return new si3();
    }
}
