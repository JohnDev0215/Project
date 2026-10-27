package com.stingray.kca.model;

import E5.o;
import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\r\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000f"}, d2 = {"Lcom/stingray/kca/model/SupportedFeatures;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "Profile", "Banners", "Scoring", "Camera", "UnknownDefaultOpenApi", "toString", "Companion", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SupportedFeatures {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ SupportedFeatures[] $VALUES;
    private final String value;

    @o(name = "Profile")
    public static final SupportedFeatures Profile = new SupportedFeatures("Profile", 0, "Profile");

    @o(name = "Banners")
    public static final SupportedFeatures Banners = new SupportedFeatures("Banners", 1, "Banners");

    @o(name = "Scoring")
    public static final SupportedFeatures Scoring = new SupportedFeatures("Scoring", 2, "Scoring");

    @o(name = "Camera")
    public static final SupportedFeatures Camera = new SupportedFeatures("Camera", 3, "Camera");

    @o(name = "unknown_default_open_api")
    public static final SupportedFeatures UnknownDefaultOpenApi = new SupportedFeatures("UnknownDefaultOpenApi", 4, "unknown_default_open_api");

    private static final /* synthetic */ SupportedFeatures[] $values() {
        return new SupportedFeatures[]{Profile, Banners, Scoring, Camera, UnknownDefaultOpenApi};
    }

    static {
        SupportedFeatures[] supportedFeaturesArr$values = $values();
        $VALUES = supportedFeaturesArr$values;
        $ENTRIES = r.e(supportedFeaturesArr$values);
        INSTANCE = new Companion(0);
    }

    private SupportedFeatures(String str, int i7, String str2) {
        this.value = str2;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static SupportedFeatures valueOf(String str) {
        return (SupportedFeatures) Enum.valueOf(SupportedFeatures.class, str);
    }

    public static SupportedFeatures[] values() {
        return (SupportedFeatures[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
