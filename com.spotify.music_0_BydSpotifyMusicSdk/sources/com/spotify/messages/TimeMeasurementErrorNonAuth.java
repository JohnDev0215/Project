package com.spotify.messages;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import p.al2;
import p.bl2;
import p.cl2;
import p.dl2;
import p.ds1;
import p.el2;
import p.fl2;
import p.gl2;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;
import p.yk2;
import p.zk2;

/* JADX INFO: loaded from: classes.dex */
public final class TimeMeasurementErrorNonAuth extends i implements yj1 {
    public static final int CATEGORY_FIELD_NUMBER = 5;
    private static final TimeMeasurementErrorNonAuth DEFAULT_INSTANCE;
    public static final int DIMENSIONS_FIELD_NUMBER = 6;
    public static final int ERROR_CODE_FIELD_NUMBER = 1;
    public static final int ERROR_DATA_FIELD_NUMBER = 2;
    public static final int FEATURE_ID_FIELD_NUMBER = 10;
    public static final int MEASUREMENT_ID_FIELD_NUMBER = 11;
    public static final int METADATA_FIELD_NUMBER = 14;
    public static final int ONGOING_POINT_FEATURE_IDS_FIELD_NUMBER = 4;
    public static final int ONGOING_POINT_TIMESTAMPS_FIELD_NUMBER = 3;
    public static final int PARENT_EPOCH_OFFSET_FIELD_NUMBER = 13;
    public static final int PARENT_MEASUREMENT_ID_FIELD_NUMBER = 12;
    private static volatile ds1 PARSER = null;
    public static final int POINT_DURATIONS_FIELD_NUMBER = 8;
    public static final int POINT_FEATURE_IDS_FIELD_NUMBER = 9;
    public static final int POINT_TIMESTAMPS_FIELD_NUMBER = 7;
    private int bitField0_;
    private String category_;
    private MapFieldLite<String, String> dimensions_;
    private int errorCode_;
    private MapFieldLite<String, String> errorData_;
    private String featureId_;
    private String measurementId_;
    private MapFieldLite<String, String> metadata_;
    private MapFieldLite<String, String> ongoingPointFeatureIds_;
    private MapFieldLite<String, Long> ongoingPointTimestamps_;
    private long parentEpochOffset_;
    private String parentMeasurementId_;
    private MapFieldLite<String, Long> pointDurations_;
    private MapFieldLite<String, String> pointFeatureIds_;
    private MapFieldLite<String, Long> pointTimestamps_;

    static {
        TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth = new TimeMeasurementErrorNonAuth();
        DEFAULT_INSTANCE = timeMeasurementErrorNonAuth;
        i.registerDefaultInstance(TimeMeasurementErrorNonAuth.class, timeMeasurementErrorNonAuth);
    }

    private TimeMeasurementErrorNonAuth() {
        MapFieldLite mapFieldLite = MapFieldLite.c;
        this.errorData_ = mapFieldLite;
        this.ongoingPointTimestamps_ = mapFieldLite;
        this.ongoingPointFeatureIds_ = mapFieldLite;
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

    public static void e(TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth, int i) {
        timeMeasurementErrorNonAuth.bitField0_ |= 1;
        timeMeasurementErrorNonAuth.errorCode_ = i;
    }

    public static MapFieldLite f(TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth) {
        if (!timeMeasurementErrorNonAuth.pointTimestamps_.e()) {
            timeMeasurementErrorNonAuth.pointTimestamps_ = timeMeasurementErrorNonAuth.pointTimestamps_.g();
        }
        return timeMeasurementErrorNonAuth.pointTimestamps_;
    }

    public static MapFieldLite g(TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth) {
        if (!timeMeasurementErrorNonAuth.pointDurations_.e()) {
            timeMeasurementErrorNonAuth.pointDurations_ = timeMeasurementErrorNonAuth.pointDurations_.g();
        }
        return timeMeasurementErrorNonAuth.pointDurations_;
    }

    public static MapFieldLite h(TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth) {
        if (!timeMeasurementErrorNonAuth.pointFeatureIds_.e()) {
            timeMeasurementErrorNonAuth.pointFeatureIds_ = timeMeasurementErrorNonAuth.pointFeatureIds_.g();
        }
        return timeMeasurementErrorNonAuth.pointFeatureIds_;
    }

    public static void i(TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth, String str) {
        timeMeasurementErrorNonAuth.getClass();
        timeMeasurementErrorNonAuth.bitField0_ |= 4;
        timeMeasurementErrorNonAuth.featureId_ = str;
    }

    public static void j(TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth, String str) {
        timeMeasurementErrorNonAuth.getClass();
        str.getClass();
        timeMeasurementErrorNonAuth.bitField0_ |= 8;
        timeMeasurementErrorNonAuth.measurementId_ = str;
    }

    public static void k(TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth, String str) {
        timeMeasurementErrorNonAuth.getClass();
        str.getClass();
        timeMeasurementErrorNonAuth.bitField0_ |= 16;
        timeMeasurementErrorNonAuth.parentMeasurementId_ = str;
    }

    public static void l(TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth, long j) {
        timeMeasurementErrorNonAuth.bitField0_ |= 32;
        timeMeasurementErrorNonAuth.parentEpochOffset_ = j;
    }

    public static MapFieldLite m(TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth) {
        if (!timeMeasurementErrorNonAuth.metadata_.e()) {
            timeMeasurementErrorNonAuth.metadata_ = timeMeasurementErrorNonAuth.metadata_.g();
        }
        return timeMeasurementErrorNonAuth.metadata_;
    }

    public static MapFieldLite n(TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth) {
        if (!timeMeasurementErrorNonAuth.errorData_.e()) {
            timeMeasurementErrorNonAuth.errorData_ = timeMeasurementErrorNonAuth.errorData_.g();
        }
        return timeMeasurementErrorNonAuth.errorData_;
    }

    public static MapFieldLite o(TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth) {
        if (!timeMeasurementErrorNonAuth.ongoingPointTimestamps_.e()) {
            timeMeasurementErrorNonAuth.ongoingPointTimestamps_ = timeMeasurementErrorNonAuth.ongoingPointTimestamps_.g();
        }
        return timeMeasurementErrorNonAuth.ongoingPointTimestamps_;
    }

    public static MapFieldLite p(TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth) {
        if (!timeMeasurementErrorNonAuth.ongoingPointFeatureIds_.e()) {
            timeMeasurementErrorNonAuth.ongoingPointFeatureIds_ = timeMeasurementErrorNonAuth.ongoingPointFeatureIds_.g();
        }
        return timeMeasurementErrorNonAuth.ongoingPointFeatureIds_;
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    public static void q(TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth, String str) {
        timeMeasurementErrorNonAuth.getClass();
        str.getClass();
        timeMeasurementErrorNonAuth.bitField0_ |= 2;
        timeMeasurementErrorNonAuth.category_ = str;
    }

    public static MapFieldLite r(TimeMeasurementErrorNonAuth timeMeasurementErrorNonAuth) {
        if (!timeMeasurementErrorNonAuth.dimensions_.e()) {
            timeMeasurementErrorNonAuth.dimensions_ = timeMeasurementErrorNonAuth.dimensions_.g();
        }
        return timeMeasurementErrorNonAuth.dimensions_;
    }

    public static yk2 s() {
        return (yk2) DEFAULT_INSTANCE.createBuilder();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u000e\u0000\u0001\u0001\u000e\u000e\b\u0000\u0000\u0001င\u0000\u00022\u00032\u00042\u0005ဈ\u0001\u00062\u00072\b2\t2\nဈ\u0002\u000bဈ\u0003\fဈ\u0004\rဂ\u0005\u000e2", new Object[]{"bitField0_", "errorCode_", "errorData_", al2.a, "ongoingPointTimestamps_", dl2.a, "ongoingPointFeatureIds_", cl2.a, "category_", "dimensions_", zk2.a, "pointTimestamps_", gl2.a, "pointDurations_", el2.a, "pointFeatureIds_", fl2.a, "featureId_", "measurementId_", "parentMeasurementId_", "parentEpochOffset_", "metadata_", bl2.a});
            case 3:
                return new TimeMeasurementErrorNonAuth();
            case 4:
                return new yk2(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (TimeMeasurementErrorNonAuth.class) {
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
