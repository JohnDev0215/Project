package com.amazon.identity.platform.weblab;

import com.amazon.identity.auth.device.v5;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'a' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class Weblab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Weblab f715a;
    public static final Weblab b;
    public static final String c;
    public static final /* synthetic */ Weblab[] d;
    private final Treatment mDefaultTreatment;
    private final String mName;
    private Treatment mOverrideTreatment;
    private v5 mSystemPropertiesWrapper = new v5();
    private MAPWeblabClient mWeblabClient;

    /* JADX INFO: compiled from: DCP */
    public enum Treatment {
        C_Default,
        C,
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10
    }

    static {
        Treatment treatment = Treatment.C_Default;
        Weblab weblab = new Weblab("MAP_ANDROID_SECONDARY_PANDA_93840", 0, "MAP_ANDROID_SECONDARY_PANDA_93840", treatment);
        f715a = weblab;
        Weblab weblab2 = new Weblab("MAP_DUMMY_WEBLAB_FOR_UNIT_TESTS", 1, "MAP_DUMMY_WEBLAB_FOR_UNIT_TESTS", treatment);
        b = weblab2;
        d = new Weblab[]{weblab, weblab2};
        c = Weblab.class.getName();
    }

    public Weblab(String str, int i, String str2, Treatment treatment) {
        this.mName = str2;
        this.mDefaultTreatment = treatment;
    }

    public static Weblab[] values() {
        return (Weblab[]) d.clone();
    }

    public Treatment a() {
        return this.mDefaultTreatment;
    }

    public String b() {
        return this.mName;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x012d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.identity.platform.weblab.Weblab.Treatment a(android.content.Context r15) {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.platform.weblab.Weblab.a(android.content.Context):com.amazon.identity.platform.weblab.Weblab$Treatment");
    }
}
