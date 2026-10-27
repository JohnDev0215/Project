package com.amazon.music.platform.graphql.generated.fragment;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ParentalSettings.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\r\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/ParentalSettings;", "", "", "toString", "", "hashCode", "other", "", "equals", "hasExplicitLanguage", "Ljava/lang/Boolean;", "getHasExplicitLanguage", "()Ljava/lang/Boolean;", "isKidFriendly", "<init>", "(Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ParentalSettings {
    private final Boolean hasExplicitLanguage;
    private final Boolean isKidFriendly;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ParentalSettings)) {
            return false;
        }
        ParentalSettings parentalSettings = (ParentalSettings) other;
        return Intrinsics.areEqual(this.hasExplicitLanguage, parentalSettings.hasExplicitLanguage) && Intrinsics.areEqual(this.isKidFriendly, parentalSettings.isKidFriendly);
    }

    public int hashCode() {
        Boolean bool = this.hasExplicitLanguage;
        int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Boolean bool2 = this.isKidFriendly;
        return iHashCode + (bool2 != null ? bool2.hashCode() : 0);
    }

    public String toString() {
        return "ParentalSettings(hasExplicitLanguage=" + this.hasExplicitLanguage + ", isKidFriendly=" + this.isKidFriendly + ')';
    }

    public ParentalSettings(Boolean bool, Boolean bool2) {
        this.hasExplicitLanguage = bool;
        this.isKidFriendly = bool2;
    }

    public final Boolean getHasExplicitLanguage() {
        return this.hasExplicitLanguage;
    }

    /* JADX INFO: renamed from: isKidFriendly, reason: from getter */
    public final Boolean getIsKidFriendly() {
        return this.isKidFriendly;
    }
}
