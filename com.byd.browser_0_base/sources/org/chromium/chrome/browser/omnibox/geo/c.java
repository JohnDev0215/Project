package org.chromium.chrome.browser.omnibox.geo;

import com.google.protobuf.z;
import defpackage.a92;
import defpackage.bh1;
import defpackage.wv1;

/* JADX INFO: compiled from: PartnerLocationDescriptor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c extends z<c, a> implements bh1 {
    public static final int CELL_FIELD_NUMBER = 2;
    public static final int CONNECTED_FIELD_NUMBER = 3;
    private static final c DEFAULT_INSTANCE;
    private static volatile wv1<c> PARSER = null;
    public static final int TIMESTAMP_MS_FIELD_NUMBER = 4;
    public static final int WIFI_FIELD_NUMBER = 1;
    private int bitField0_;
    private boolean connected_;
    private long timestampMs_;
    private int typeCase_ = 0;
    private Object type_;

    /* JADX INFO: compiled from: PartnerLocationDescriptor.java */
    public static final class a extends z.a<c, a> implements bh1 {
        public a() {
            super(c.DEFAULT_INSTANCE);
        }
    }

    /* JADX INFO: compiled from: PartnerLocationDescriptor.java */
    public static final class b extends z<b, a> implements bh1 {
        public static final int CELL_ID_FIELD_NUMBER = 2;
        private static final b DEFAULT_INSTANCE;
        public static final int LOCATION_AREA_CODE_FIELD_NUMBER = 3;
        public static final int MOBILE_COUNTRY_CODE_FIELD_NUMBER = 4;
        public static final int MOBILE_NETWORK_CODE_FIELD_NUMBER = 5;
        private static volatile wv1<b> PARSER = null;
        public static final int PHYSICAL_CELL_ID_FIELD_NUMBER = 7;
        public static final int PRIMARY_SCRAMBLING_CODE_FIELD_NUMBER = 6;
        public static final int TRACKING_AREA_CODE_FIELD_NUMBER = 8;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int bitField0_;
        private int cellId_;
        private int locationAreaCode_;
        private int mobileCountryCode_;
        private int mobileNetworkCode_;
        private int physicalCellId_;
        private int primaryScramblingCode_;
        private int trackingAreaCode_;
        private int type_;

        /* JADX INFO: compiled from: PartnerLocationDescriptor.java */
        public static final class a extends z.a<b, a> implements bh1 {
            public a() {
                super(b.DEFAULT_INSTANCE);
            }

            public final void B(int i) {
                y();
                b.W((b) this.f, i);
            }
        }

        static {
            b bVar = new b();
            DEFAULT_INSTANCE = bVar;
            z.M(b.class, bVar);
        }

        public static void O(b bVar, int i) {
            bVar.bitField0_ |= 2;
            bVar.cellId_ = i;
        }

        public static void P(b bVar, int i) {
            bVar.bitField0_ |= 4;
            bVar.locationAreaCode_ = i;
        }

        public static void Q(b bVar, int i) {
            bVar.bitField0_ |= 8;
            bVar.mobileCountryCode_ = i;
        }

        public static void S(b bVar, int i) {
            bVar.bitField0_ |= 16;
            bVar.mobileNetworkCode_ = i;
        }

        public static void T(b bVar, int i) {
            bVar.bitField0_ |= 64;
            bVar.physicalCellId_ = i;
        }

        public static void U(b bVar, int i) {
            bVar.bitField0_ |= 32;
            bVar.primaryScramblingCode_ = i;
        }

        public static void V(b bVar, int i) {
            bVar.bitField0_ |= 128;
            bVar.trackingAreaCode_ = i;
        }

        public static void W(b bVar, int i) {
            bVar.getClass();
            if (i == 0) {
                throw null;
            }
            bVar.type_ = i - 1;
            bVar.bitField0_ |= 1;
        }

        public static a Y() {
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
                    return new a92(DEFAULT_INSTANCE, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဌ\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007င\u0006\bင\u0007", new Object[]{"bitField0_", "type_", d.a, "cellId_", "locationAreaCode_", "mobileCountryCode_", "mobileNetworkCode_", "primaryScramblingCode_", "physicalCellId_", "trackingAreaCode_"});
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

    /* JADX INFO: renamed from: org.chromium.chrome.browser.omnibox.geo.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PartnerLocationDescriptor.java */
    public static final class C0099c extends z<C0099c, a> implements bh1 {
        public static final int BSSID_FIELD_NUMBER = 1;
        private static final C0099c DEFAULT_INSTANCE;
        public static final int LEVEL_DBM_FIELD_NUMBER = 2;
        private static volatile wv1<C0099c> PARSER;
        private int bitField0_;
        private String bssid_ = "";
        private int levelDbm_;

        /* JADX INFO: renamed from: org.chromium.chrome.browser.omnibox.geo.c$c$a */
        /* JADX INFO: compiled from: PartnerLocationDescriptor.java */
        public static final class a extends z.a<C0099c, a> implements bh1 {
            public a() {
                super(C0099c.DEFAULT_INSTANCE);
            }
        }

        static {
            C0099c c0099c = new C0099c();
            DEFAULT_INSTANCE = c0099c;
            z.M(C0099c.class, c0099c);
        }

        public static void O(C0099c c0099c, String str) {
            c0099c.getClass();
            str.getClass();
            c0099c.bitField0_ |= 1;
            c0099c.bssid_ = str;
        }

        public static void P(C0099c c0099c, int i) {
            c0099c.bitField0_ |= 2;
            c0099c.levelDbm_ = i;
        }

        public static a S() {
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
                    return new a92(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002င\u0001", new Object[]{"bitField0_", "bssid_", "levelDbm_"});
                case NEW_MUTABLE_INSTANCE:
                    return new C0099c();
                case NEW_BUILDER:
                    return new a();
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    wv1<C0099c> bVar = PARSER;
                    if (bVar == null) {
                        synchronized (C0099c.class) {
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

    static {
        c cVar = new c();
        DEFAULT_INSTANCE = cVar;
        z.M(c.class, cVar);
    }

    public static void O(c cVar, b bVar) {
        cVar.getClass();
        cVar.type_ = bVar;
        cVar.typeCase_ = 2;
    }

    public static void P(c cVar, boolean z) {
        cVar.bitField0_ |= 4;
        cVar.connected_ = z;
    }

    public static void Q(c cVar, long j) {
        cVar.bitField0_ |= 8;
        cVar.timestampMs_ = j;
    }

    public static void S(c cVar, C0099c c0099c) {
        cVar.getClass();
        cVar.type_ = c0099c;
        cVar.typeCase_ = 1;
    }

    public static a U() {
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
                return new a92(DEFAULT_INSTANCE, "\u0001\u0004\u0001\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ြ\u0000\u0002ြ\u0000\u0003ဇ\u0002\u0004ဂ\u0003", new Object[]{"type_", "typeCase_", "bitField0_", C0099c.class, b.class, "connected_", "timestampMs_"});
            case NEW_MUTABLE_INSTANCE:
                return new c();
            case NEW_BUILDER:
                return new a();
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<c> bVar = PARSER;
                if (bVar == null) {
                    synchronized (c.class) {
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
