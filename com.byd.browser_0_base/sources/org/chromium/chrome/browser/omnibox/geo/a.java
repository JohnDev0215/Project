package org.chromium.chrome.browser.omnibox.geo;

import com.google.protobuf.z;
import defpackage.a92;
import defpackage.bh1;
import defpackage.wv1;

/* JADX INFO: compiled from: PartnerLocationDescriptor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends z<a, C0098a> implements bh1 {
    private static final a DEFAULT_INSTANCE;
    public static final int LATITUDE_E7_FIELD_NUMBER = 1;
    public static final int LONGITUDE_E7_FIELD_NUMBER = 2;
    private static volatile wv1<a> PARSER;
    private int bitField0_;
    private int latitudeE7_;
    private int longitudeE7_;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.omnibox.geo.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PartnerLocationDescriptor.java */
    public static final class C0098a extends z.a<a, C0098a> implements bh1 {
        public C0098a() {
            super(a.DEFAULT_INSTANCE);
        }
    }

    static {
        a aVar = new a();
        DEFAULT_INSTANCE = aVar;
        z.M(a.class, aVar);
    }

    public static void O(a aVar, int i) {
        aVar.bitField0_ |= 1;
        aVar.latitudeE7_ = i;
    }

    public static void P(a aVar, int i) {
        aVar.bitField0_ |= 2;
        aVar.longitudeE7_ = i;
    }

    public static C0098a S() {
        return (C0098a) ((z.a) DEFAULT_INSTANCE.D(z.f.NEW_BUILDER, null, null));
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဍ\u0000\u0002ဍ\u0001", new Object[]{"bitField0_", "latitudeE7_", "longitudeE7_"});
            case NEW_MUTABLE_INSTANCE:
                return new a();
            case NEW_BUILDER:
                return new C0098a();
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
}
