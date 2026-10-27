package org.chromium.components.omnibox;

import com.google.protobuf.k0;
import com.google.protobuf.m0;
import com.google.protobuf.n1;
import com.google.protobuf.z;
import defpackage.a92;
import defpackage.bh1;
import defpackage.pt0;
import defpackage.wv1;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: compiled from: GroupsProto.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a extends z<a, C0135a> implements bh1 {
    private static final a DEFAULT_INSTANCE;
    public static final int GROUP_CONFIGS_FIELD_NUMBER = 1;
    private static volatile wv1<a> PARSER;
    private m0<Integer, pt0> groupConfigs_ = m0.f;

    /* JADX INFO: renamed from: org.chromium.components.omnibox.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GroupsProto.java */
    public static final class C0135a extends z.a<a, C0135a> implements bh1 {
        public C0135a() {
            super(a.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: compiled from: GroupsProto.java */
    public static final class b {
        public static final k0<Integer, pt0> a = new k0<>(n1.a.i, 0, n1.a.h, pt0.O());
    }

    static {
        a aVar = new a();
        DEFAULT_INSTANCE = aVar;
        z.M(a.class, aVar);
    }

    public static C0135a Q() {
        return (C0135a) ((z.a) DEFAULT_INSTANCE.D(z.f.NEW_BUILDER, null, null));
    }

    public static a S(byte[] bArr) {
        return (a) z.I(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"groupConfigs_", b.a});
            case NEW_MUTABLE_INSTANCE:
                return new a();
            case NEW_BUILDER:
                return new C0135a();
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<a> bVar = PARSER;
                if (bVar == null) {
                    synchronized (a.class) {
                        bVar = PARSER;
                        if (bVar == null) {
                            bVar = new z.b<>(DEFAULT_INSTANCE);
                            PARSER = bVar;
                        }
                        break;
                    }
                }
                return bVar;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final Map<Integer, pt0> P() {
        return Collections.unmodifiableMap(this.groupConfigs_);
    }
}
