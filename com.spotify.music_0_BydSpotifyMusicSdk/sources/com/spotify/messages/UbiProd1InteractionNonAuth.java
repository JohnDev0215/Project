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
import p.yj1;
import p.zp2;

/* JADX INFO: loaded from: classes.dex */
public final class UbiProd1InteractionNonAuth extends i implements yj1 {
    public static final int ACTION_NAME_FIELD_NUMBER = 4;
    public static final int ACTION_PARAMETER_NAMES_FIELD_NUMBER = 6;
    public static final int ACTION_PARAMETER_VALUES_FIELD_NUMBER = 7;
    public static final int ACTION_VERSION_FIELD_NUMBER = 5;
    public static final int ANNOTATOR_CONFIGURATION_VERSION_FIELD_NUMBER = 21;
    public static final int ANNOTATOR_VERSION_FIELD_NUMBER = 20;
    public static final int APP_FIELD_NUMBER = 2;
    private static final UbiProd1InteractionNonAuth DEFAULT_INSTANCE;
    public static final int ELEMENT_PATH_IDS_FIELD_NUMBER = 10;
    public static final int ELEMENT_PATH_NAMES_FIELD_NUMBER = 8;
    public static final int ELEMENT_PATH_POS_FIELD_NUMBER = 9;
    public static final int ELEMENT_PATH_REASONS_FIELD_NUMBER = 12;
    public static final int ELEMENT_PATH_URIS_FIELD_NUMBER = 11;
    public static final int ERRORS_FIELD_NUMBER = 13;
    public static final int FLOW_ID_FIELD_NUMBER = 31;
    public static final int GENERATOR_VERSION_FIELD_NUMBER = 1;
    public static final int IMPRESSION_ID_FIELD_NUMBER = 19;
    public static final int INTERACTION_ID_FIELD_NUMBER = 14;
    public static final int INTERACTION_TYPE_FIELD_NUMBER = 3;
    public static final int PAGE_INSTANCE_ID_FIELD_NUMBER = 15;
    public static final int PAGE_URI_FIELD_NUMBER = 29;
    public static final int PARENT_MODES_FIELD_NUMBER = 28;
    public static final int PARENT_PATH_IDS_FIELD_NUMBER = 24;
    public static final int PARENT_PATH_NAMES_FIELD_NUMBER = 22;
    public static final int PARENT_PATH_POS_FIELD_NUMBER = 23;
    public static final int PARENT_PATH_REASONS_FIELD_NUMBER = 26;
    public static final int PARENT_PATH_URIS_FIELD_NUMBER = 25;
    public static final int PARENT_SPECIFICATION_VERSIONS_FIELD_NUMBER = 27;
    private static volatile ds1 PARSER = null;
    public static final int PLAYBACK_ID_FIELD_NUMBER = 18;
    public static final int PLAY_CONTEXT_URI_FIELD_NUMBER = 30;
    public static final int SPECIFICATION_MODE_FIELD_NUMBER = 17;
    public static final int SPECIFICATION_VERSION_FIELD_NUMBER = 16;
    private int actionVersion_;
    private int bitField0_;
    private String generatorVersion_ = "";
    private String app_ = "";
    private String interactionType_ = "";
    private String actionName_ = "";
    private v51 actionParameterNames_ = i.emptyProtobufList();
    private v51 actionParameterValues_ = i.emptyProtobufList();
    private v51 elementPathNames_ = i.emptyProtobufList();
    private v51 elementPathPos_ = i.emptyProtobufList();
    private v51 elementPathIds_ = i.emptyProtobufList();
    private v51 elementPathUris_ = i.emptyProtobufList();
    private v51 elementPathReasons_ = i.emptyProtobufList();
    private v51 errors_ = i.emptyProtobufList();
    private String interactionId_ = "";
    private String pageInstanceId_ = "";
    private String specificationVersion_ = "";
    private String specificationMode_ = "";
    private String playbackId_ = "";
    private String impressionId_ = "";
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
        UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth = new UbiProd1InteractionNonAuth();
        DEFAULT_INSTANCE = ubiProd1InteractionNonAuth;
        i.registerDefaultInstance(UbiProd1InteractionNonAuth.class, ubiProd1InteractionNonAuth);
    }

    private UbiProd1InteractionNonAuth() {
    }

    public static void A(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        str.getClass();
        ubiProd1InteractionNonAuth.bitField0_ |= 4;
        ubiProd1InteractionNonAuth.interactionType_ = str;
    }

    public static void B(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        str.getClass();
        ubiProd1InteractionNonAuth.bitField0_ |= 2048;
        ubiProd1InteractionNonAuth.annotatorVersion_ = str;
    }

    public static void C(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        str.getClass();
        ubiProd1InteractionNonAuth.bitField0_ |= 4096;
        ubiProd1InteractionNonAuth.annotatorConfigurationVersion_ = str;
    }

    public static void D(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        str.getClass();
        v51 v51Var = ubiProd1InteractionNonAuth.parentPathNames_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.parentPathNames_ = i.mutableCopy(v51Var);
        }
        ubiProd1InteractionNonAuth.parentPathNames_.add(str);
    }

    public static void E(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        v51 v51Var = ubiProd1InteractionNonAuth.parentPathPos_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.parentPathPos_ = i.mutableCopy(v51Var);
        }
        ubiProd1InteractionNonAuth.parentPathPos_.add(str);
    }

    public static void F(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        v51 v51Var = ubiProd1InteractionNonAuth.parentPathIds_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.parentPathIds_ = i.mutableCopy(v51Var);
        }
        ubiProd1InteractionNonAuth.parentPathIds_.add(str);
    }

    public static void G(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        v51 v51Var = ubiProd1InteractionNonAuth.parentPathUris_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.parentPathUris_ = i.mutableCopy(v51Var);
        }
        ubiProd1InteractionNonAuth.parentPathUris_.add(str);
    }

    public static zp2 H() {
        return (zp2) DEFAULT_INSTANCE.createBuilder();
    }

    public static void e(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        str.getClass();
        ubiProd1InteractionNonAuth.bitField0_ |= 1;
        ubiProd1InteractionNonAuth.generatorVersion_ = str;
    }

    public static void f(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        str.getClass();
        ubiProd1InteractionNonAuth.bitField0_ |= 8;
        ubiProd1InteractionNonAuth.actionName_ = str;
    }

    public static void g(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        v51 v51Var = ubiProd1InteractionNonAuth.parentPathReasons_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.parentPathReasons_ = i.mutableCopy(v51Var);
        }
        ubiProd1InteractionNonAuth.parentPathReasons_.add(str);
    }

    public static void h(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        str.getClass();
        v51 v51Var = ubiProd1InteractionNonAuth.parentSpecificationVersions_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.parentSpecificationVersions_ = i.mutableCopy(v51Var);
        }
        ubiProd1InteractionNonAuth.parentSpecificationVersions_.add(str);
    }

    public static void i(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        v51 v51Var = ubiProd1InteractionNonAuth.parentModes_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.parentModes_ = i.mutableCopy(v51Var);
        }
        ubiProd1InteractionNonAuth.parentModes_.add(str);
    }

    public static void j(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        ubiProd1InteractionNonAuth.bitField0_ |= 8192;
        ubiProd1InteractionNonAuth.pageUri_ = str;
    }

    public static void k(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        ubiProd1InteractionNonAuth.bitField0_ |= 16384;
        ubiProd1InteractionNonAuth.playContextUri_ = str;
    }

    public static void l(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, int i) {
        ubiProd1InteractionNonAuth.bitField0_ |= 16;
        ubiProd1InteractionNonAuth.actionVersion_ = i;
    }

    public static void m(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        v51 v51Var = ubiProd1InteractionNonAuth.actionParameterNames_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.actionParameterNames_ = i.mutableCopy(v51Var);
        }
        ubiProd1InteractionNonAuth.actionParameterNames_.add(str);
    }

    public static void n(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        v51 v51Var = ubiProd1InteractionNonAuth.actionParameterValues_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.actionParameterValues_ = i.mutableCopy(v51Var);
        }
        ubiProd1InteractionNonAuth.actionParameterValues_.add(str);
    }

    public static void o(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        str.getClass();
        v51 v51Var = ubiProd1InteractionNonAuth.elementPathNames_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.elementPathNames_ = i.mutableCopy(v51Var);
        }
        ubiProd1InteractionNonAuth.elementPathNames_.add(str);
    }

    public static void p(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        v51 v51Var = ubiProd1InteractionNonAuth.elementPathPos_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.elementPathPos_ = i.mutableCopy(v51Var);
        }
        ubiProd1InteractionNonAuth.elementPathPos_.add(str);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    public static void q(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        v51 v51Var = ubiProd1InteractionNonAuth.elementPathIds_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.elementPathIds_ = i.mutableCopy(v51Var);
        }
        ubiProd1InteractionNonAuth.elementPathIds_.add(str);
    }

    public static void r(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        str.getClass();
        ubiProd1InteractionNonAuth.bitField0_ |= 2;
        ubiProd1InteractionNonAuth.app_ = str;
    }

    public static void s(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        v51 v51Var = ubiProd1InteractionNonAuth.elementPathUris_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.elementPathUris_ = i.mutableCopy(v51Var);
        }
        ubiProd1InteractionNonAuth.elementPathUris_.add(str);
    }

    public static void t(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        v51 v51Var = ubiProd1InteractionNonAuth.elementPathReasons_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.elementPathReasons_ = i.mutableCopy(v51Var);
        }
        ubiProd1InteractionNonAuth.elementPathReasons_.add(str);
    }

    public static void u(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, List list) {
        v51 v51Var = ubiProd1InteractionNonAuth.errors_;
        if (!((a2) v51Var).c) {
            ubiProd1InteractionNonAuth.errors_ = i.mutableCopy(v51Var);
        }
        a.addAll((Iterable) list, (List) ubiProd1InteractionNonAuth.errors_);
    }

    public static void v(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        ubiProd1InteractionNonAuth.bitField0_ |= 32;
        ubiProd1InteractionNonAuth.interactionId_ = str;
    }

    public static void w(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        ubiProd1InteractionNonAuth.bitField0_ |= 64;
        ubiProd1InteractionNonAuth.pageInstanceId_ = str;
    }

    public static void x(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        str.getClass();
        ubiProd1InteractionNonAuth.bitField0_ |= 128;
        ubiProd1InteractionNonAuth.specificationVersion_ = str;
    }

    public static void y(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth) {
        ubiProd1InteractionNonAuth.getClass();
        ubiProd1InteractionNonAuth.bitField0_ |= 256;
        ubiProd1InteractionNonAuth.specificationMode_ = "default";
    }

    public static void z(UbiProd1InteractionNonAuth ubiProd1InteractionNonAuth, String str) {
        ubiProd1InteractionNonAuth.getClass();
        ubiProd1InteractionNonAuth.bitField0_ |= 512;
        ubiProd1InteractionNonAuth.playbackId_ = str;
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u001f\u0000\u0001\u0001\u001f\u001f\u0000\u000f\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005င\u0004\u0006\u001a\u0007\u001a\b\u001a\t\u001a\n\u001a\u000b\u001a\f\u001a\r\u001a\u000eဈ\u0005\u000fဈ\u0006\u0010ဈ\u0007\u0011ဈ\b\u0012ဈ\t\u0013ဈ\n\u0014ဈ\u000b\u0015ဈ\f\u0016\u001a\u0017\u001a\u0018\u001a\u0019\u001a\u001a\u001a\u001b\u001a\u001c\u001a\u001dဈ\r\u001eဈ\u000e\u001fဈ\u000f", new Object[]{"bitField0_", "generatorVersion_", "app_", "interactionType_", "actionName_", "actionVersion_", "actionParameterNames_", "actionParameterValues_", "elementPathNames_", "elementPathPos_", "elementPathIds_", "elementPathUris_", "elementPathReasons_", "errors_", "interactionId_", "pageInstanceId_", "specificationVersion_", "specificationMode_", "playbackId_", "impressionId_", "annotatorVersion_", "annotatorConfigurationVersion_", "parentPathNames_", "parentPathPos_", "parentPathIds_", "parentPathUris_", "parentPathReasons_", "parentSpecificationVersions_", "parentModes_", "pageUri_", "playContextUri_", "flowId_"});
            case 3:
                return new UbiProd1InteractionNonAuth();
            case 4:
                return new zp2(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (UbiProd1InteractionNonAuth.class) {
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
