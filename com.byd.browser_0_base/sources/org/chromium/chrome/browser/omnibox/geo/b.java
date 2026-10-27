package org.chromium.chrome.browser.omnibox.geo;

import com.google.protobuf.c0;
import com.google.protobuf.y0;
import com.google.protobuf.z;
import defpackage.C0175b;
import defpackage.a92;
import defpackage.bh1;
import defpackage.gq2;
import defpackage.uw1;
import defpackage.vw1;
import defpackage.wv1;

/* JADX INFO: compiled from: PartnerLocationDescriptor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b extends z<b, a> implements bh1 {
    private static final b DEFAULT_INSTANCE;
    public static final int LATLNG_FIELD_NUMBER = 5;
    private static volatile wv1<b> PARSER = null;
    public static final int PRODUCER_FIELD_NUMBER = 2;
    public static final int RADIUS_FIELD_NUMBER = 7;
    public static final int ROLE_FIELD_NUMBER = 1;
    public static final int TIMESTAMP_FIELD_NUMBER = 3;
    public static final int VISIBLE_NETWORK_FIELD_NUMBER = 23;
    private int bitField0_;
    private org.chromium.chrome.browser.omnibox.geo.a latlng_;
    private int producer_;
    private float radius_;
    private int role_;
    private long timestamp_;
    private c0.e<c> visibleNetwork_ = y0.h;

    /* JADX INFO: compiled from: PartnerLocationDescriptor.java */
    public static final class a extends z.a<b, a> implements bh1 {
        public a() {
            super(b.DEFAULT_INSTANCE);
        }
    }

    static {
        b bVar = new b();
        DEFAULT_INSTANCE = bVar;
        z.M(b.class, bVar);
    }

    public static void O(b bVar, c cVar) {
        bVar.getClass();
        c0.e<c> eVar = bVar.visibleNetwork_;
        if (!eVar.o()) {
            int size = eVar.size();
            bVar.visibleNetwork_ = eVar.h(size == 0 ? 10 : size * 2);
        }
        bVar.visibleNetwork_.add(cVar);
    }

    public static void P(b bVar, org.chromium.chrome.browser.omnibox.geo.a aVar) {
        bVar.getClass();
        bVar.latlng_ = aVar;
        bVar.bitField0_ |= 8;
    }

    public static void Q(b bVar) {
        bVar.getClass();
        bVar.producer_ = C0175b.c(2);
        bVar.bitField0_ = 2 | bVar.bitField0_;
    }

    public static void S(b bVar, float f) {
        bVar.bitField0_ |= 16;
        bVar.radius_ = f;
    }

    public static void T(b bVar) {
        bVar.getClass();
        bVar.role_ = gq2.m(2);
        bVar.bitField0_ |= 1;
    }

    public static void U(b bVar, long j) {
        bVar.bitField0_ |= 4;
        bVar.timestamp_ = j;
    }

    public static a W() {
        return (a) ((z.a) DEFAULT_INSTANCE.D(z.f.NEW_BUILDER, null, null));
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0017\u0006\u0000\u0001\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဂ\u0002\u0005ဉ\u0003\u0007ခ\u0004\u0017\u001b", new Object[]{"bitField0_", "role_", vw1.a, "producer_", uw1.a, "timestamp_", "latlng_", "radius_", "visibleNetwork_", c.class});
            case NEW_MUTABLE_INSTANCE:
                return new b();
            case NEW_BUILDER:
                return new a();
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<b> bVar = PARSER;
                if (bVar == null) {
                    synchronized (b.class) {
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
