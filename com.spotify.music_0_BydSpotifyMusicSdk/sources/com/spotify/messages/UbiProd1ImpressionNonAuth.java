package com.spotify.messages;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import java.util.List;
import p.a2;
import p.ds1;
import p.i01;
import p.v51;
import p.vj1;
import p.wj1;
import p.xp2;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class UbiProd1ImpressionNonAuth extends i implements yj1 {
    public static final int ANNOTATOR_CONFIGURATION_VERSION_FIELD_NUMBER = 15;
    public static final int ANNOTATOR_VERSION_FIELD_NUMBER = 14;
    public static final int APP_FIELD_NUMBER = 2;
    private static final UbiProd1ImpressionNonAuth DEFAULT_INSTANCE;
    public static final int DWELL_TIME_MS_FIELD_NUMBER = 25;
    public static final int DWELL_TIME_START_TIMESTAMP_FIELD_NUMBER = 26;
    public static final int ELEMENT_PATH_IDS_FIELD_NUMBER = 5;
    public static final int ELEMENT_PATH_NAMES_FIELD_NUMBER = 3;
    public static final int ELEMENT_PATH_POS_FIELD_NUMBER = 4;
    public static final int ELEMENT_PATH_REASONS_FIELD_NUMBER = 7;
    public static final int ELEMENT_PATH_URIS_FIELD_NUMBER = 6;
    public static final int ERRORS_FIELD_NUMBER = 8;
    public static final int FLOW_ID_FIELD_NUMBER = 27;
    public static final int GENERATOR_VERSION_FIELD_NUMBER = 1;
    public static final int IMPRESSION_ID_FIELD_NUMBER = 9;
    public static final int PAGE_INSTANCE_ID_FIELD_NUMBER = 10;
    public static final int PAGE_URI_FIELD_NUMBER = 23;
    public static final int PARENT_MODES_FIELD_NUMBER = 22;
    public static final int PARENT_PATH_IDS_FIELD_NUMBER = 18;
    public static final int PARENT_PATH_NAMES_FIELD_NUMBER = 16;
    public static final int PARENT_PATH_POS_FIELD_NUMBER = 17;
    public static final int PARENT_PATH_REASONS_FIELD_NUMBER = 20;
    public static final int PARENT_PATH_URIS_FIELD_NUMBER = 19;
    public static final int PARENT_SPECIFICATION_VERSIONS_FIELD_NUMBER = 21;
    private static volatile ds1 PARSER = null;
    public static final int PLAYBACK_ID_FIELD_NUMBER = 13;
    public static final int PLAY_CONTEXT_URI_FIELD_NUMBER = 24;
    public static final int SPECIFICATION_MODE_FIELD_NUMBER = 12;
    public static final int SPECIFICATION_VERSION_FIELD_NUMBER = 11;
    private int bitField0_;
    private long dwellTimeMs_;
    private long dwellTimeStartTimestamp_;
    private String generatorVersion_ = "";
    private String app_ = "";
    private v51 elementPathNames_ = i.emptyProtobufList();
    private v51 elementPathPos_ = i.emptyProtobufList();
    private v51 elementPathIds_ = i.emptyProtobufList();
    private v51 elementPathUris_ = i.emptyProtobufList();
    private v51 elementPathReasons_ = i.emptyProtobufList();
    private v51 errors_ = i.emptyProtobufList();
    private String impressionId_ = "";
    private String pageInstanceId_ = "";
    private String specificationVersion_ = "";
    private String specificationMode_ = "";
    private String playbackId_ = "";
    private String annotatorVersion_ = "";
    private String annotatorConfigurationVersion_ = "";
    private v51 parentPathNames_ = i.emptyProtobufList();
    private v51 parentPathPos_ = i.emptyProtobufList();
    private v51 parentPathIds_ = i.emptyProtobufList();
    private v51 parentPathUris_ = i.emptyProtobufList();
    private v51 parentPathReasons_ = i.emptyProtobufList();
    private v51 parentSpecificationVersions_ = i.emptyProtobufList();
    private v51 parentModes_ = i.emptyProtobufList();
    private String pageUri_ = "";
    private String playContextUri_ = "";
    private String flowId_ = "";

    static {
        UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth = new UbiProd1ImpressionNonAuth();
        DEFAULT_INSTANCE = ubiProd1ImpressionNonAuth;
        i.registerDefaultInstance(UbiProd1ImpressionNonAuth.class, ubiProd1ImpressionNonAuth);
    }

    private UbiProd1ImpressionNonAuth() {
    }

    public static void A(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        ubiProd1ImpressionNonAuth.bitField0_ |= 512;
        ubiProd1ImpressionNonAuth.pageUri_ = str;
    }

    public static void B(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        ubiProd1ImpressionNonAuth.bitField0_ |= 1024;
        ubiProd1ImpressionNonAuth.playContextUri_ = str;
    }

    public static xp2 C() {
        return (xp2) DEFAULT_INSTANCE.createBuilder();
    }

    public static void e(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        str.getClass();
        ubiProd1ImpressionNonAuth.bitField0_ |= 1;
        ubiProd1ImpressionNonAuth.generatorVersion_ = str;
    }

    public static void f(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        v51 v51Var = ubiProd1ImpressionNonAuth.elementPathPos_;
        if (!((a2) v51Var).c) {
            ubiProd1ImpressionNonAuth.elementPathPos_ = i.mutableCopy(v51Var);
        }
        ubiProd1ImpressionNonAuth.elementPathPos_.add(str);
    }

    public static void g(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        v51 v51Var = ubiProd1ImpressionNonAuth.elementPathIds_;
        if (!((a2) v51Var).c) {
            ubiProd1ImpressionNonAuth.elementPathIds_ = i.mutableCopy(v51Var);
        }
        ubiProd1ImpressionNonAuth.elementPathIds_.add(str);
    }

    public static void h(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        v51 v51Var = ubiProd1ImpressionNonAuth.elementPathUris_;
        if (!((a2) v51Var).c) {
            ubiProd1ImpressionNonAuth.elementPathUris_ = i.mutableCopy(v51Var);
        }
        ubiProd1ImpressionNonAuth.elementPathUris_.add(str);
    }

    public static void i(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        v51 v51Var = ubiProd1ImpressionNonAuth.elementPathReasons_;
        if (!((a2) v51Var).c) {
            ubiProd1ImpressionNonAuth.elementPathReasons_ = i.mutableCopy(v51Var);
        }
        ubiProd1ImpressionNonAuth.elementPathReasons_.add(str);
    }

    public static void j(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, List list) {
        v51 v51Var = ubiProd1ImpressionNonAuth.errors_;
        if (!((a2) v51Var).c) {
            ubiProd1ImpressionNonAuth.errors_ = i.mutableCopy(v51Var);
        }
        a.addAll((Iterable) list, (List) ubiProd1ImpressionNonAuth.errors_);
    }

    public static void k(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        ubiProd1ImpressionNonAuth.bitField0_ |= 4;
        ubiProd1ImpressionNonAuth.impressionId_ = str;
    }

    public static void l(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        str.getClass();
        ubiProd1ImpressionNonAuth.bitField0_ |= 2;
        ubiProd1ImpressionNonAuth.app_ = str;
    }

    public static void m(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        ubiProd1ImpressionNonAuth.bitField0_ |= 8;
        ubiProd1ImpressionNonAuth.pageInstanceId_ = str;
    }

    public static void n(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        str.getClass();
        ubiProd1ImpressionNonAuth.bitField0_ |= 16;
        ubiProd1ImpressionNonAuth.specificationVersion_ = str;
    }

    public static void o(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth) {
        ubiProd1ImpressionNonAuth.getClass();
        ubiProd1ImpressionNonAuth.bitField0_ |= 32;
        ubiProd1ImpressionNonAuth.specificationMode_ = "default";
    }

    public static void p(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        ubiProd1ImpressionNonAuth.bitField0_ |= 64;
        ubiProd1ImpressionNonAuth.playbackId_ = str;
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    public static void q(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        str.getClass();
        ubiProd1ImpressionNonAuth.bitField0_ |= 128;
        ubiProd1ImpressionNonAuth.annotatorVersion_ = str;
    }

    public static void r(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        str.getClass();
        ubiProd1ImpressionNonAuth.bitField0_ |= 256;
        ubiProd1ImpressionNonAuth.annotatorConfigurationVersion_ = str;
    }

    public static void s(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        str.getClass();
        v51 v51Var = ubiProd1ImpressionNonAuth.parentPathNames_;
        if (!((a2) v51Var).c) {
            ubiProd1ImpressionNonAuth.parentPathNames_ = i.mutableCopy(v51Var);
        }
        ubiProd1ImpressionNonAuth.parentPathNames_.add(str);
    }

    public static void t(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        v51 v51Var = ubiProd1ImpressionNonAuth.parentPathPos_;
        if (!((a2) v51Var).c) {
            ubiProd1ImpressionNonAuth.parentPathPos_ = i.mutableCopy(v51Var);
        }
        ubiProd1ImpressionNonAuth.parentPathPos_.add(str);
    }

    public static void u(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        v51 v51Var = ubiProd1ImpressionNonAuth.parentPathIds_;
        if (!((a2) v51Var).c) {
            ubiProd1ImpressionNonAuth.parentPathIds_ = i.mutableCopy(v51Var);
        }
        ubiProd1ImpressionNonAuth.parentPathIds_.add(str);
    }

    public static void v(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        v51 v51Var = ubiProd1ImpressionNonAuth.parentPathUris_;
        if (!((a2) v51Var).c) {
            ubiProd1ImpressionNonAuth.parentPathUris_ = i.mutableCopy(v51Var);
        }
        ubiProd1ImpressionNonAuth.parentPathUris_.add(str);
    }

    public static void w(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        v51 v51Var = ubiProd1ImpressionNonAuth.parentPathReasons_;
        if (!((a2) v51Var).c) {
            ubiProd1ImpressionNonAuth.parentPathReasons_ = i.mutableCopy(v51Var);
        }
        ubiProd1ImpressionNonAuth.parentPathReasons_.add(str);
    }

    public static void x(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        str.getClass();
        v51 v51Var = ubiProd1ImpressionNonAuth.elementPathNames_;
        if (!((a2) v51Var).c) {
            ubiProd1ImpressionNonAuth.elementPathNames_ = i.mutableCopy(v51Var);
        }
        ubiProd1ImpressionNonAuth.elementPathNames_.add(str);
    }

    public static void y(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        str.getClass();
        v51 v51Var = ubiProd1ImpressionNonAuth.parentSpecificationVersions_;
        if (!((a2) v51Var).c) {
            ubiProd1ImpressionNonAuth.parentSpecificationVersions_ = i.mutableCopy(v51Var);
        }
        ubiProd1ImpressionNonAuth.parentSpecificationVersions_.add(str);
    }

    public static void z(UbiProd1ImpressionNonAuth ubiProd1ImpressionNonAuth, String str) {
        ubiProd1ImpressionNonAuth.getClass();
        v51 v51Var = ubiProd1ImpressionNonAuth.parentModes_;
        if (!((a2) v51Var).c) {
            ubiProd1ImpressionNonAuth.parentModes_ = i.mutableCopy(v51Var);
        }
        ubiProd1ImpressionNonAuth.parentModes_.add(str);
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u001b\u0000\u0001\u0001\u001b\u001b\u0000\r\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003\u001a\u0004\u001a\u0005\u001a\u0006\u001a\u0007\u001a\b\u001a\tဈ\u0002\nဈ\u0003\u000bဈ\u0004\fဈ\u0005\rဈ\u0006\u000eဈ\u0007\u000fဈ\b\u0010\u001a\u0011\u001a\u0012\u001a\u0013\u001a\u0014\u001a\u0015\u001a\u0016\u001a\u0017ဈ\t\u0018ဈ\n\u0019ဂ\u000b\u001aဂ\f\u001bဈ\r", new Object[]{"bitField0_", "generatorVersion_", "app_", "elementPathNames_", "elementPathPos_", "elementPathIds_", "elementPathUris_", "elementPathReasons_", "errors_", "impressionId_", "pageInstanceId_", "specificationVersion_", "specificationMode_", "playbackId_", "annotatorVersion_", "annotatorConfigurationVersion_", "parentPathNames_", "parentPathPos_", "parentPathIds_", "parentPathUris_", "parentPathReasons_", "parentSpecificationVersions_", "parentModes_", "pageUri_", "playContextUri_", "dwellTimeMs_", "dwellTimeStartTimestamp_", "flowId_"});
            case 3:
                return new UbiProd1ImpressionNonAuth();
            case 4:
                return new xp2(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (UbiProd1ImpressionNonAuth.class) {
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
