package defpackage;

import com.google.protobuf.b0;
import com.google.protobuf.c0;
import com.google.protobuf.i0;
import com.google.protobuf.y0;
import com.google.protobuf.z;

/* JADX INFO: compiled from: StudyOuterClass.java */
/* JADX INFO: loaded from: classes3.dex */
public final class mv2 extends z<mv2, Object> implements bh1 {
    public static final int ACTIVATION_TYPE_FIELD_NUMBER = 12;
    public static final int CONSISTENCY_FIELD_NUMBER = 7;
    public static final int DEFAULT_EXPERIMENT_NAME_FIELD_NUMBER = 8;
    private static final mv2 DEFAULT_INSTANCE;
    public static final int EXPERIMENT_FIELD_NUMBER = 9;
    public static final int EXPIRY_DATE_FIELD_NUMBER = 3;
    public static final int FILTER_FIELD_NUMBER = 10;
    public static final int LAYER_FIELD_NUMBER = 16;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile wv1<mv2> PARSER = null;
    public static final int RANDOMIZATION_SEED_FIELD_NUMBER = 11;
    private int activationType_;
    private int bitField0_;
    private int consistency_;
    private long expiryDate_;
    private b filter_;
    private w51 layer_;
    private int randomizationSeed_;
    private byte memoizedIsInitialized = 2;
    private String name_ = "";
    private String defaultExperimentName_ = "";
    private c0.e<a> experiment_ = y0.h;

    /* JADX INFO: compiled from: StudyOuterClass.java */
    public static final class a extends z<a, Object> implements bh1 {
        public static final int CHROME_SYNC_EXPERIMENT_ID_FIELD_NUMBER = 10;
        private static final a DEFAULT_INSTANCE;
        public static final int FEATURE_ASSOCIATION_FIELD_NUMBER = 12;
        public static final int FORCING_FLAG_FIELD_NUMBER = 5;
        public static final int GOOGLE_APP_EXPERIMENT_ID_FIELD_NUMBER = 15;
        public static final int GOOGLE_WEB_EXPERIMENT_ID_FIELD_NUMBER = 3;
        public static final int GOOGLE_WEB_TRIGGER_EXPERIMENT_ID_FIELD_NUMBER = 8;
        public static final int GOOGLE_WEB_VISIBILITY_FIELD_NUMBER = 16;
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int OVERRIDE_UI_STRING_FIELD_NUMBER = 9;
        public static final int PARAM_FIELD_NUMBER = 6;
        private static volatile wv1<a> PARSER = null;
        public static final int PROBABILITY_WEIGHT_FIELD_NUMBER = 2;
        public static final int TYPE_FIELD_NUMBER = 7;
        private int bitField0_;
        private long chromeSyncExperimentId_;
        private C0067a featureAssociation_;
        private long googleAppExperimentId_;
        private long googleWebExperimentId_;
        private long googleWebTriggerExperimentId_;
        private int googleWebVisibility_;
        private c0.e<b> overrideUiString_;
        private c0.e<c> param_;
        private int probabilityWeight_;
        private int type_;
        private byte memoizedIsInitialized = 2;
        private String name_ = "";
        private String forcingFlag_ = "";

        /* JADX INFO: renamed from: mv2$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: StudyOuterClass.java */
        public static final class C0067a extends z<C0067a, Object> implements bh1 {
            private static final C0067a DEFAULT_INSTANCE;
            public static final int DISABLE_FEATURE_FIELD_NUMBER = 2;
            public static final int ENABLE_FEATURE_FIELD_NUMBER = 1;
            public static final int FORCING_FEATURE_OFF_FIELD_NUMBER = 4;
            public static final int FORCING_FEATURE_ON_FIELD_NUMBER = 3;
            private static volatile wv1<C0067a> PARSER;
            private int bitField0_;
            private c0.e<String> disableFeature_;
            private c0.e<String> enableFeature_;
            private String forcingFeatureOff_;
            private String forcingFeatureOn_;

            static {
                C0067a c0067a = new C0067a();
                DEFAULT_INSTANCE = c0067a;
                z.M(C0067a.class, c0067a);
            }

            public C0067a() {
                y0<Object> y0Var = y0.h;
                this.enableFeature_ = y0Var;
                this.disableFeature_ = y0Var;
                this.forcingFeatureOn_ = "";
                this.forcingFeatureOff_ = "";
            }

            @Override // com.google.protobuf.z
            public final Object D(z.f fVar, Object obj, Object obj2) {
                switch (fVar) {
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    case BUILD_MESSAGE_INFO:
                        return new a92(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001\u001a\u0002\u001a\u0003ဈ\u0000\u0004ဈ\u0001", new Object[]{"bitField0_", "enableFeature_", "disableFeature_", "forcingFeatureOn_", "forcingFeatureOff_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new C0067a();
                    case NEW_BUILDER:
                        return new z.a(DEFAULT_INSTANCE);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        wv1<C0067a> bVar = PARSER;
                        if (bVar == null) {
                            synchronized (C0067a.class) {
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

        /* JADX INFO: compiled from: StudyOuterClass.java */
        public static final class b extends z<b, Object> implements bh1 {
            private static final b DEFAULT_INSTANCE;
            public static final int NAME_HASH_FIELD_NUMBER = 1;
            private static volatile wv1<b> PARSER = null;
            public static final int VALUE_FIELD_NUMBER = 2;
            private int bitField0_;
            private int nameHash_;
            private String value_ = "";

            static {
                b bVar = new b();
                DEFAULT_INSTANCE = bVar;
                z.M(b.class, bVar);
            }

            @Override // com.google.protobuf.z
            public final Object D(z.f fVar, Object obj, Object obj2) {
                switch (fVar) {
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    case BUILD_MESSAGE_INFO:
                        return new a92(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဆ\u0000\u0002ဈ\u0001", new Object[]{"bitField0_", "nameHash_", "value_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new b();
                    case NEW_BUILDER:
                        return new z.a(DEFAULT_INSTANCE);
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

        /* JADX INFO: compiled from: StudyOuterClass.java */
        public static final class c extends z<c, Object> implements bh1 {
            private static final c DEFAULT_INSTANCE;
            public static final int NAME_FIELD_NUMBER = 1;
            private static volatile wv1<c> PARSER = null;
            public static final int VALUE_FIELD_NUMBER = 2;
            private int bitField0_;
            private String name_ = "";
            private String value_ = "";

            static {
                c cVar = new c();
                DEFAULT_INSTANCE = cVar;
                z.M(c.class, cVar);
            }

            @Override // com.google.protobuf.z
            public final Object D(z.f fVar, Object obj, Object obj2) {
                switch (fVar) {
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    case BUILD_MESSAGE_INFO:
                        return new a92(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"bitField0_", "name_", "value_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new c();
                    case NEW_BUILDER:
                        return new z.a(DEFAULT_INSTANCE);
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

        static {
            a aVar = new a();
            DEFAULT_INSTANCE = aVar;
            z.M(a.class, aVar);
        }

        public a() {
            y0<Object> y0Var = y0.h;
            this.param_ = y0Var;
            this.overrideUiString_ = y0Var;
        }

        @Override // com.google.protobuf.z
        public final Object D(z.f fVar, Object obj, Object obj2) {
            switch (fVar) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return Byte.valueOf(this.memoizedIsInitialized);
                case SET_MEMOIZED_IS_INITIALIZED:
                    this.memoizedIsInitialized = (byte) (obj == null ? 0 : 1);
                    return null;
                case BUILD_MESSAGE_INFO:
                    return new a92(DEFAULT_INSTANCE, "\u0001\f\u0000\u0001\u0001\u0010\f\u0000\u0002\u0002\u0001ᔈ\u0000\u0002ᔋ\u0001\u0003ဃ\u0002\u0005ဈ\b\u0006\u001b\u0007ဌ\t\bဃ\u0003\t\u001b\nဃ\u0005\fဉ\u0007\u000fဃ\u0006\u0010ဌ\u0004", new Object[]{"bitField0_", "name_", "probabilityWeight_", "googleWebExperimentId_", "forcingFlag_", "param_", c.class, "type_", nv2.a, "googleWebTriggerExperimentId_", "overrideUiString_", b.class, "chromeSyncExperimentId_", "featureAssociation_", "googleAppExperimentId_", "googleWebVisibility_", pv2.a});
                case NEW_MUTABLE_INSTANCE:
                    return new a();
                case NEW_BUILDER:
                    return new z.a(DEFAULT_INSTANCE);
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

    /* JADX INFO: compiled from: StudyOuterClass.java */
    public static final class b extends z<b, Object> implements bh1 {
        public static final int CHANNEL_FIELD_NUMBER = 4;
        public static final int COUNTRY_FIELD_NUMBER = 10;
        public static final int CPU_ARCHITECTURE_FIELD_NUMBER = 20;
        private static final b DEFAULT_INSTANCE;
        public static final int END_DATE_FIELD_NUMBER = 13;
        public static final int EXCLUDE_COUNTRY_FIELD_NUMBER = 11;
        public static final int EXCLUDE_CPU_ARCHITECTURE_FIELD_NUMBER = 21;
        public static final int EXCLUDE_FORM_FACTOR_FIELD_NUMBER = 14;
        public static final int EXCLUDE_GOOGLE_GROUP_FIELD_NUMBER = 23;
        public static final int EXCLUDE_HARDWARE_CLASS_FIELD_NUMBER = 9;
        public static final int EXCLUDE_LOCALE_FIELD_NUMBER = 12;
        public static final int FORM_FACTOR_FIELD_NUMBER = 7;
        public static final int GOOGLE_GROUP_FIELD_NUMBER = 22;
        public static final int HARDWARE_CLASS_FIELD_NUMBER = 8;
        public static final int IS_ENTERPRISE_FIELD_NUMBER = 18;
        public static final int IS_LOW_END_DEVICE_FIELD_NUMBER = 15;
        public static final int LOCALE_FIELD_NUMBER = 6;
        public static final int MAX_OS_VERSION_FIELD_NUMBER = 17;
        public static final int MAX_VERSION_FIELD_NUMBER = 3;
        public static final int MIN_OS_VERSION_FIELD_NUMBER = 16;
        public static final int MIN_VERSION_FIELD_NUMBER = 2;
        private static volatile wv1<b> PARSER = null;
        public static final int PLATFORM_FIELD_NUMBER = 5;
        public static final int POLICY_RESTRICTION_FIELD_NUMBER = 19;
        public static final int START_DATE_FIELD_NUMBER = 1;
        private int bitField0_;
        private c0.c channel_;
        private c0.e<String> country_;
        private c0.c cpuArchitecture_;
        private long endDate_;
        private c0.e<String> excludeCountry_;
        private c0.c excludeCpuArchitecture_;
        private c0.c excludeFormFactor_;
        private c0.d excludeGoogleGroup_;
        private c0.e<String> excludeHardwareClass_;
        private c0.e<String> excludeLocale_;
        private c0.c formFactor_;
        private c0.d googleGroup_;
        private c0.e<String> hardwareClass_;
        private boolean isEnterprise_;
        private boolean isLowEndDevice_;
        private c0.e<String> locale_;
        private c0.c platform_;
        private int policyRestriction_;
        private long startDate_;
        private static final g21<Integer, Object> channel_converter_ = new a();
        private static final g21<Integer, Object> platform_converter_ = new C0068b();
        private static final g21<Integer, Object> formFactor_converter_ = new c();
        private static final g21<Integer, Object> excludeFormFactor_converter_ = new d();
        private static final g21<Integer, Object> cpuArchitecture_converter_ = new e();
        private static final g21<Integer, Object> excludeCpuArchitecture_converter_ = new f();
        private int googleGroupMemoizedSerializedSize = -1;
        private int excludeGoogleGroupMemoizedSerializedSize = -1;
        private String minVersion_ = "";
        private String maxVersion_ = "";
        private String minOsVersion_ = "";
        private String maxOsVersion_ = "";

        /* JADX INFO: compiled from: StudyOuterClass.java */
        public class a implements g21<Integer, Object> {
        }

        /* JADX INFO: renamed from: mv2$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: StudyOuterClass.java */
        public class C0068b implements g21<Integer, Object> {
        }

        /* JADX INFO: compiled from: StudyOuterClass.java */
        public class c implements g21<Integer, Object> {
        }

        /* JADX INFO: compiled from: StudyOuterClass.java */
        public class d implements g21<Integer, Object> {
        }

        /* JADX INFO: compiled from: StudyOuterClass.java */
        public class e implements g21<Integer, Object> {
        }

        /* JADX INFO: compiled from: StudyOuterClass.java */
        public class f implements g21<Integer, Object> {
        }

        static {
            b bVar = new b();
            DEFAULT_INSTANCE = bVar;
            z.M(b.class, bVar);
        }

        public b() {
            b0 b0Var = b0.h;
            this.channel_ = b0Var;
            this.platform_ = b0Var;
            y0<Object> y0Var = y0.h;
            this.locale_ = y0Var;
            this.excludeLocale_ = y0Var;
            this.formFactor_ = b0Var;
            this.excludeFormFactor_ = b0Var;
            this.hardwareClass_ = y0Var;
            this.excludeHardwareClass_ = y0Var;
            this.country_ = y0Var;
            this.excludeCountry_ = y0Var;
            this.cpuArchitecture_ = b0Var;
            this.excludeCpuArchitecture_ = b0Var;
            i0 i0Var = i0.h;
            this.googleGroup_ = i0Var;
            this.excludeGoogleGroup_ = i0Var;
        }

        @Override // com.google.protobuf.z
        public final Object D(z.f fVar, Object obj, Object obj2) {
            switch (fVar) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    b bVar = DEFAULT_INSTANCE;
                    ov2 ov2Var = ov2.a;
                    lv2 lv2Var = lv2.a;
                    return new a92(bVar, "\u0001\u0017\u0000\u0001\u0001\u0017\u0017\u0000\u000e\u0000\u0001ဂ\u0000\u0002ဈ\u0002\u0003ဈ\u0003\u0004\u001e\u0005\u001e\u0006\u001a\u0007\u001e\b\u001a\t\u001a\n\u001a\u000b\u001a\f\u001a\rဂ\u0001\u000e\u001e\u000fဇ\u0006\u0010ဈ\u0004\u0011ဈ\u0005\u0012ဇ\u0007\u0013ဌ\b\u0014\u001e\u0015\u001e\u0016%\u0017%", new Object[]{"bitField0_", "startDate_", "minVersion_", "maxVersion_", "channel_", jv2.a, "platform_", qv2.a, "locale_", "formFactor_", ov2Var, "hardwareClass_", "excludeHardwareClass_", "country_", "excludeCountry_", "excludeLocale_", "endDate_", "excludeFormFactor_", ov2Var, "isLowEndDevice_", "minOsVersion_", "maxOsVersion_", "isEnterprise_", "policyRestriction_", rv2.a, "cpuArchitecture_", lv2Var, "excludeCpuArchitecture_", lv2Var, "googleGroup_", "excludeGoogleGroup_"});
                case NEW_MUTABLE_INSTANCE:
                    return new b();
                case NEW_BUILDER:
                    return new z.a(DEFAULT_INSTANCE);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    wv1<b> bVar2 = PARSER;
                    if (bVar2 == null) {
                        synchronized (b.class) {
                            bVar2 = PARSER;
                            if (bVar2 == null) {
                                bVar2 = new z.b<>(DEFAULT_INSTANCE);
                                PARSER = bVar2;
                            }
                            break;
                        }
                    }
                    return bVar2;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    static {
        mv2 mv2Var = new mv2();
        DEFAULT_INSTANCE = mv2Var;
        z.M(mv2.class, mv2Var);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return Byte.valueOf(this.memoizedIsInitialized);
            case SET_MEMOIZED_IS_INITIALIZED:
                this.memoizedIsInitialized = (byte) (obj == null ? 0 : 1);
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\t\u0000\u0001\u0001\u0010\t\u0000\u0001\u0002\u0001ᔈ\u0000\u0003ဂ\u0001\u0007ဌ\u0002\bဈ\u0004\tЛ\nဉ\u0005\u000bဋ\u0006\fဌ\u0007\u0010ဉ\u0003", new Object[]{"bitField0_", "name_", "expiryDate_", "consistency_", kv2.a, "defaultExperimentName_", "experiment_", a.class, "filter_", "randomizationSeed_", "activationType_", iv2.a, "layer_"});
            case NEW_MUTABLE_INSTANCE:
                return new mv2();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<mv2> bVar = PARSER;
                if (bVar == null) {
                    synchronized (mv2.class) {
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
