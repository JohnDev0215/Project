package com.spotify.messages;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import p.ds1;
import p.hl2;
import p.i01;
import p.il2;
import p.jl2;
import p.kl2;
import p.ll2;
import p.ml2;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class TimeMeasurementNonAuth extends i implements yj1 {
    public static final int CATEGORY_FIELD_NUMBER = 1;
    private static final TimeMeasurementNonAuth DEFAULT_INSTANCE;
    public static final int DIMENSIONS_FIELD_NUMBER = 2;
    public static final int FEATURE_ID_FIELD_NUMBER = 6;
    public static final int MEASUREMENT_ID_FIELD_NUMBER = 7;
    public static final int METADATA_FIELD_NUMBER = 10;
    public static final int PARENT_EPOCH_OFFSET_FIELD_NUMBER = 9;
    public static final int PARENT_MEASUREMENT_ID_FIELD_NUMBER = 8;
    private static volatile ds1 PARSER = null;
    public static final int POINT_DURATIONS_FIELD_NUMBER = 4;
    public static final int POINT_FEATURE_IDS_FIELD_NUMBER = 5;
    public static final int POINT_TIMESTAMPS_FIELD_NUMBER = 3;
    private int bitField0_;
    private String category_;
    private MapFieldLite<String, String> dimensions_;
    private String featureId_;
    private String measurementId_;
    private MapFieldLite<String, String> metadata_;
    private long parentEpochOffset_;
    private String parentMeasurementId_;
    private MapFieldLite<String, Long> pointDurations_;
    private MapFieldLite<String, String> pointFeatureIds_;
    private MapFieldLite<String, Long> pointTimestamps_;

    static {
        TimeMeasurementNonAuth timeMeasurementNonAuth = new TimeMeasurementNonAuth();
        DEFAULT_INSTANCE = timeMeasurementNonAuth;
        i.registerDefaultInstance(TimeMeasurementNonAuth.class, timeMeasurementNonAuth);
    }

    private TimeMeasurementNonAuth() {
        MapFieldLite mapFieldLite = MapFieldLite.c;
        this.dimensions_ = mapFieldLite;
        this.pointTimestamps_ = mapFieldLite;
        this.pointDurations_ = mapFieldLite;
        this.pointFeatureIds_ = mapFieldLite;
        this.metadata_ = mapFieldLite;
        this.category_ = "";
        this.featureId_ = "";
        this.measurementId_ = "";
        this.parentMeasurementId_ = "";
    }

    public static void e(TimeMeasurementNonAuth timeMeasurementNonAuth, String str) {
        timeMeasurementNonAuth.getClass();
        str.getClass();
        timeMeasurementNonAuth.bitField0_ |= 1;
        timeMeasurementNonAuth.category_ = str;
    }

    public static void f(TimeMeasurementNonAuth timeMeasurementNonAuth, String str) {
        timeMeasurementNonAuth.getClass();
        str.getClass();
        timeMeasurementNonAuth.bitField0_ |= 4;
        timeMeasurementNonAuth.measurementId_ = str;
    }

    public static MapFieldLite g(TimeMeasurementNonAuth timeMeasurementNonAuth) {
        if (!timeMeasurementNonAuth.metadata_.e()) {
            timeMeasurementNonAuth.metadata_ = timeMeasurementNonAuth.metadata_.g();
        }
        return timeMeasurementNonAuth.metadata_;
    }

    public static MapFieldLite h(TimeMeasurementNonAuth timeMeasurementNonAuth) {
        if (!timeMeasurementNonAuth.dimensions_.e()) {
            timeMeasurementNonAuth.dimensions_ = timeMeasurementNonAuth.dimensions_.g();
        }
        return timeMeasurementNonAuth.dimensions_;
    }

    public static MapFieldLite i(TimeMeasurementNonAuth timeMeasurementNonAuth) {
        if (!timeMeasurementNonAuth.pointTimestamps_.e()) {
            timeMeasurementNonAuth.pointTimestamps_ = timeMeasurementNonAuth.pointTimestamps_.g();
        }
        return timeMeasurementNonAuth.pointTimestamps_;
    }

    public static MapFieldLite j(TimeMeasurementNonAuth timeMeasurementNonAuth) {
        if (!timeMeasurementNonAuth.pointDurations_.e()) {
            timeMeasurementNonAuth.pointDurations_ = timeMeasurementNonAuth.pointDurations_.g();
        }
        return timeMeasurementNonAuth.pointDurations_;
    }

    public static MapFieldLite k(TimeMeasurementNonAuth timeMeasurementNonAuth) {
        if (!timeMeasurementNonAuth.pointFeatureIds_.e()) {
            timeMeasurementNonAuth.pointFeatureIds_ = timeMeasurementNonAuth.pointFeatureIds_.g();
        }
        return timeMeasurementNonAuth.pointFeatureIds_;
    }

    public static void l(TimeMeasurementNonAuth timeMeasurementNonAuth, String str) {
        timeMeasurementNonAuth.getClass();
        timeMeasurementNonAuth.bitField0_ |= 2;
        timeMeasurementNonAuth.featureId_ = str;
    }

    public static hl2 m() {
        return (hl2) DEFAULT_INSTANCE.createBuilder();
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\n\u0000\u0001\u0001\n\n\u0005\u0000\u0000\u0001ဈ\u0000\u00022\u00032\u00042\u00052\u0006ဈ\u0001\u0007ဈ\u0002\bဈ\u0003\tဂ\u0004\n2", new Object[]{"bitField0_", "category_", "dimensions_", il2.a, "pointTimestamps_", ml2.a, "pointDurations_", kl2.a, "pointFeatureIds_", ll2.a, "featureId_", "measurementId_", "parentMeasurementId_", "parentEpochOffset_", "metadata_", jl2.a});
            case 3:
                return new TimeMeasurementNonAuth();
            case 4:
                return new hl2(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (TimeMeasurementNonAuth.class) {
                        try {
                            i01Var = PARSER;
                            if (i01Var == null) {
                                i01Var = new i01(DEFAULT_INSTANCE);
                                PARSER = i01Var;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return i01Var;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
