package com.spotify.player.esperanto.proto;

import com.google.common.collect.ImmutableSet;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import java.util.List;
import p.a2;
import p.ds1;
import p.gg0;
import p.i01;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsPlayOrigin$PlayOrigin extends i implements yj1 {
    private static final EsPlayOrigin$PlayOrigin DEFAULT_INSTANCE;
    public static final int DEVICE_IDENTIFIER_FIELD_NUMBER = 6;
    public static final int EXTERNAL_REFERRER_FIELD_NUMBER = 4;
    public static final int FEATURE_CLASSES_FIELD_NUMBER = 7;
    public static final int FEATURE_IDENTIFIER_FIELD_NUMBER = 1;
    public static final int FEATURE_VERSION_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int REFERRER_IDENTIFIER_FIELD_NUMBER = 5;
    public static final int RESTRICTION_IDENTIFIER_FIELD_NUMBER = 8;
    public static final int VIEW_URI_FIELD_NUMBER = 3;
    private String featureIdentifier_ = "";
    private String featureVersion_ = "";
    private String viewUri_ = "";
    private String externalReferrer_ = "";
    private String referrerIdentifier_ = "";
    private String deviceIdentifier_ = "";
    private v51 featureClasses_ = i.emptyProtobufList();
    private String restrictionIdentifier_ = "";

    static {
        EsPlayOrigin$PlayOrigin esPlayOrigin$PlayOrigin = new EsPlayOrigin$PlayOrigin();
        DEFAULT_INSTANCE = esPlayOrigin$PlayOrigin;
        i.registerDefaultInstance(EsPlayOrigin$PlayOrigin.class, esPlayOrigin$PlayOrigin);
    }

    private EsPlayOrigin$PlayOrigin() {
    }

    public static void e(EsPlayOrigin$PlayOrigin esPlayOrigin$PlayOrigin, String str) {
        esPlayOrigin$PlayOrigin.getClass();
        str.getClass();
        esPlayOrigin$PlayOrigin.featureIdentifier_ = str;
    }

    public static void f(EsPlayOrigin$PlayOrigin esPlayOrigin$PlayOrigin, String str) {
        esPlayOrigin$PlayOrigin.getClass();
        str.getClass();
        esPlayOrigin$PlayOrigin.externalReferrer_ = str;
    }

    public static void g(EsPlayOrigin$PlayOrigin esPlayOrigin$PlayOrigin, String str) {
        esPlayOrigin$PlayOrigin.getClass();
        str.getClass();
        esPlayOrigin$PlayOrigin.referrerIdentifier_ = str;
    }

    public static void h(EsPlayOrigin$PlayOrigin esPlayOrigin$PlayOrigin, String str) {
        esPlayOrigin$PlayOrigin.getClass();
        str.getClass();
        esPlayOrigin$PlayOrigin.deviceIdentifier_ = str;
    }

    public static void i(EsPlayOrigin$PlayOrigin esPlayOrigin$PlayOrigin, ImmutableSet immutableSet) {
        v51 v51Var = esPlayOrigin$PlayOrigin.featureClasses_;
        if (!((a2) v51Var).c) {
            esPlayOrigin$PlayOrigin.featureClasses_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esPlayOrigin$PlayOrigin.featureClasses_);
    }

    public static void j(EsPlayOrigin$PlayOrigin esPlayOrigin$PlayOrigin, String str) {
        esPlayOrigin$PlayOrigin.getClass();
        str.getClass();
        esPlayOrigin$PlayOrigin.restrictionIdentifier_ = str;
    }

    public static void k(EsPlayOrigin$PlayOrigin esPlayOrigin$PlayOrigin, String str) {
        esPlayOrigin$PlayOrigin.getClass();
        str.getClass();
        esPlayOrigin$PlayOrigin.featureVersion_ = str;
    }

    public static void l(EsPlayOrigin$PlayOrigin esPlayOrigin$PlayOrigin, String str) {
        esPlayOrigin$PlayOrigin.getClass();
        str.getClass();
        esPlayOrigin$PlayOrigin.viewUri_ = str;
    }

    public static EsPlayOrigin$PlayOrigin m() {
        return DEFAULT_INSTANCE;
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    public static gg0 v() {
        return (gg0) DEFAULT_INSTANCE.createBuilder();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\b\u0000\u0000\u0001\b\b\u0000\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007Ț\bȈ", new Object[]{"featureIdentifier_", "featureVersion_", "viewUri_", "externalReferrer_", "referrerIdentifier_", "deviceIdentifier_", "featureClasses_", "restrictionIdentifier_"});
            case 3:
                return new EsPlayOrigin$PlayOrigin();
            case 4:
                return new gg0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsPlayOrigin$PlayOrigin.class) {
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

    public final String n() {
        return this.deviceIdentifier_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public final String o() {
        return this.externalReferrer_;
    }

    public final v51 p() {
        return this.featureClasses_;
    }

    public final String q() {
        return this.featureIdentifier_;
    }

    public final String r() {
        return this.featureVersion_;
    }

    public final String s() {
        return this.referrerIdentifier_;
    }

    public final String t() {
        return this.restrictionIdentifier_;
    }

    public final String u() {
        return this.viewUri_;
    }
}
