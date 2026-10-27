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
import p.yp2;

/* JADX INFO: loaded from: classes.dex */
public final class UbiProd1Interaction extends i implements yj1 {
    public static final int ACTION_NAME_FIELD_NUMBER = 4;
    public static final int ACTION_PARAMETER_NAMES_FIELD_NUMBER = 6;
    public static final int ACTION_PARAMETER_VALUES_FIELD_NUMBER = 7;
    public static final int ACTION_VERSION_FIELD_NUMBER = 5;
    public static final int ANNOTATOR_CONFIGURATION_VERSION_FIELD_NUMBER = 21;
    public static final int ANNOTATOR_VERSION_FIELD_NUMBER = 20;
    public static final int APP_FIELD_NUMBER = 2;
    private static final UbiProd1Interaction DEFAULT_INSTANCE;
    public static final int ELEMENT_PATH_IDS_FIELD_NUMBER = 10;
    public static final int ELEMENT_PATH_NAMES_FIELD_NUMBER = 8;
    public static final int ELEMENT_PATH_POS_FIELD_NUMBER = 9;
    public static final int ELEMENT_PATH_REASONS_FIELD_NUMBER = 12;
    public static final int ELEMENT_PATH_URIS_FIELD_NUMBER = 11;
    public static final int ERRORS_FIELD_NUMBER = 13;
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
    public static final int REPORTED_CLIENT_TIMESTAMP_FIELD_NUMBER = 31;
    public static final int SPECIFICATION_MODE_FIELD_NUMBER = 17;
    public static final int SPECIFICATION_VERSION_FIELD_NUMBER = 16;
    private int actionVersion_;
    private int bitField0_;
    private long reportedClientTimestamp_;
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

    static {
        UbiProd1Interaction ubiProd1Interaction = new UbiProd1Interaction();
        DEFAULT_INSTANCE = ubiProd1Interaction;
        i.registerDefaultInstance(UbiProd1Interaction.class, ubiProd1Interaction);
    }

    private UbiProd1Interaction() {
    }

    public static void A(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        str.getClass();
        ubiProd1Interaction.bitField0_ |= 4;
        ubiProd1Interaction.interactionType_ = str;
    }

    public static void B(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        str.getClass();
        ubiProd1Interaction.bitField0_ |= 2048;
        ubiProd1Interaction.annotatorVersion_ = str;
    }

    public static void C(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        str.getClass();
        ubiProd1Interaction.bitField0_ |= 4096;
        ubiProd1Interaction.annotatorConfigurationVersion_ = str;
    }

    public static void D(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        str.getClass();
        v51 v51Var = ubiProd1Interaction.parentPathNames_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.parentPathNames_ = i.mutableCopy(v51Var);
        }
        ubiProd1Interaction.parentPathNames_.add(str);
    }

    public static void E(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        v51 v51Var = ubiProd1Interaction.parentPathPos_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.parentPathPos_ = i.mutableCopy(v51Var);
        }
        ubiProd1Interaction.parentPathPos_.add(str);
    }

    public static void F(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        v51 v51Var = ubiProd1Interaction.parentPathIds_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.parentPathIds_ = i.mutableCopy(v51Var);
        }
        ubiProd1Interaction.parentPathIds_.add(str);
    }

    public static void G(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        v51 v51Var = ubiProd1Interaction.parentPathUris_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.parentPathUris_ = i.mutableCopy(v51Var);
        }
        ubiProd1Interaction.parentPathUris_.add(str);
    }

    public static yp2 H() {
        return (yp2) DEFAULT_INSTANCE.createBuilder();
    }

    public static void e(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        str.getClass();
        ubiProd1Interaction.bitField0_ |= 1;
        ubiProd1Interaction.generatorVersion_ = str;
    }

    public static void f(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        str.getClass();
        ubiProd1Interaction.bitField0_ |= 8;
        ubiProd1Interaction.actionName_ = str;
    }

    public static void g(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        v51 v51Var = ubiProd1Interaction.parentPathReasons_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.parentPathReasons_ = i.mutableCopy(v51Var);
        }
        ubiProd1Interaction.parentPathReasons_.add(str);
    }

    public static void h(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        str.getClass();
        v51 v51Var = ubiProd1Interaction.parentSpecificationVersions_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.parentSpecificationVersions_ = i.mutableCopy(v51Var);
        }
        ubiProd1Interaction.parentSpecificationVersions_.add(str);
    }

    public static void i(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        v51 v51Var = ubiProd1Interaction.parentModes_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.parentModes_ = i.mutableCopy(v51Var);
        }
        ubiProd1Interaction.parentModes_.add(str);
    }

    public static void j(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        ubiProd1Interaction.bitField0_ |= 8192;
        ubiProd1Interaction.pageUri_ = str;
    }

    public static void k(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        ubiProd1Interaction.bitField0_ |= 16384;
        ubiProd1Interaction.playContextUri_ = str;
    }

    public static void l(UbiProd1Interaction ubiProd1Interaction, int i) {
        ubiProd1Interaction.bitField0_ |= 16;
        ubiProd1Interaction.actionVersion_ = i;
    }

    public static void m(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        v51 v51Var = ubiProd1Interaction.actionParameterNames_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.actionParameterNames_ = i.mutableCopy(v51Var);
        }
        ubiProd1Interaction.actionParameterNames_.add(str);
    }

    public static void n(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        v51 v51Var = ubiProd1Interaction.actionParameterValues_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.actionParameterValues_ = i.mutableCopy(v51Var);
        }
        ubiProd1Interaction.actionParameterValues_.add(str);
    }

    public static void o(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        str.getClass();
        v51 v51Var = ubiProd1Interaction.elementPathNames_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.elementPathNames_ = i.mutableCopy(v51Var);
        }
        ubiProd1Interaction.elementPathNames_.add(str);
    }

    public static void p(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        v51 v51Var = ubiProd1Interaction.elementPathPos_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.elementPathPos_ = i.mutableCopy(v51Var);
        }
        ubiProd1Interaction.elementPathPos_.add(str);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    public static void q(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        v51 v51Var = ubiProd1Interaction.elementPathIds_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.elementPathIds_ = i.mutableCopy(v51Var);
        }
        ubiProd1Interaction.elementPathIds_.add(str);
    }

    public static void r(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        str.getClass();
        ubiProd1Interaction.bitField0_ |= 2;
        ubiProd1Interaction.app_ = str;
    }

    public static void s(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        v51 v51Var = ubiProd1Interaction.elementPathUris_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.elementPathUris_ = i.mutableCopy(v51Var);
        }
        ubiProd1Interaction.elementPathUris_.add(str);
    }

    public static void t(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        v51 v51Var = ubiProd1Interaction.elementPathReasons_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.elementPathReasons_ = i.mutableCopy(v51Var);
        }
        ubiProd1Interaction.elementPathReasons_.add(str);
    }

    public static void u(UbiProd1Interaction ubiProd1Interaction, List list) {
        v51 v51Var = ubiProd1Interaction.errors_;
        if (!((a2) v51Var).c) {
            ubiProd1Interaction.errors_ = i.mutableCopy(v51Var);
        }
        a.addAll((Iterable) list, (List) ubiProd1Interaction.errors_);
    }

    public static void v(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        ubiProd1Interaction.bitField0_ |= 32;
        ubiProd1Interaction.interactionId_ = str;
    }

    public static void w(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        ubiProd1Interaction.bitField0_ |= 64;
        ubiProd1Interaction.pageInstanceId_ = str;
    }

    public static void x(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        str.getClass();
        ubiProd1Interaction.bitField0_ |= 128;
        ubiProd1Interaction.specificationVersion_ = str;
    }

    public static void y(UbiProd1Interaction ubiProd1Interaction) {
        ubiProd1Interaction.getClass();
        ubiProd1Interaction.bitField0_ |= 256;
        ubiProd1Interaction.specificationMode_ = "default";
    }

    public static void z(UbiProd1Interaction ubiProd1Interaction, String str) {
        ubiProd1Interaction.getClass();
        ubiProd1Interaction.bitField0_ |= 512;
        ubiProd1Interaction.playbackId_ = str;
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u001f\u0000\u0001\u0001\u001f\u001f\u0000\u000f\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005င\u0004\u0006\u001a\u0007\u001a\b\u001a\t\u001a\n\u001a\u000b\u001a\f\u001a\r\u001a\u000eဈ\u0005\u000fဈ\u0006\u0010ဈ\u0007\u0011ဈ\b\u0012ဈ\t\u0013ဈ\n\u0014ဈ\u000b\u0015ဈ\f\u0016\u001a\u0017\u001a\u0018\u001a\u0019\u001a\u001a\u001a\u001b\u001a\u001c\u001a\u001dဈ\r\u001eဈ\u000e\u001fဂ\u000f", new Object[]{"bitField0_", "generatorVersion_", "app_", "interactionType_", "actionName_", "actionVersion_", "actionParameterNames_", "actionParameterValues_", "elementPathNames_", "elementPathPos_", "elementPathIds_", "elementPathUris_", "elementPathReasons_", "errors_", "interactionId_", "pageInstanceId_", "specificationVersion_", "specificationMode_", "playbackId_", "impressionId_", "annotatorVersion_", "annotatorConfigurationVersion_", "parentPathNames_", "parentPathPos_", "parentPathIds_", "parentPathUris_", "parentPathReasons_", "parentSpecificationVersions_", "parentModes_", "pageUri_", "playContextUri_", "reportedClientTimestamp_"});
            case 3:
                return new UbiProd1Interaction();
            case 4:
                return new yp2(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (UbiProd1Interaction.class) {
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
