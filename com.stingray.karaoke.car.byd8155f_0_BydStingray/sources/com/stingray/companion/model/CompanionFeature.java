package com.stingray.companion.model;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/stingray/companion/model/CompanionFeature;", "", "<init>", "(Ljava/lang/String;I)V", "SCORING", "companion_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CompanionFeature {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ CompanionFeature[] $VALUES;
    public static final CompanionFeature SCORING = new CompanionFeature("SCORING", 0);

    private static final /* synthetic */ CompanionFeature[] $values() {
        return new CompanionFeature[]{SCORING};
    }

    static {
        CompanionFeature[] companionFeatureArr$values = $values();
        $VALUES = companionFeatureArr$values;
        $ENTRIES = r.e(companionFeatureArr$values);
    }

    private CompanionFeature(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static CompanionFeature valueOf(String str) {
        return (CompanionFeature) Enum.valueOf(CompanionFeature.class, str);
    }

    public static CompanionFeature[] values() {
        return (CompanionFeature[]) $VALUES.clone();
    }
}
