package com.amazon.mp3.view.model;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BadgeModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0086\b\u0018\u00002\u00020\u0001B3\b\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0002\u0012\b\b\u0002\u0010\r\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/view/model/BadgeModel;", "", "", "toString", "", "hashCode", "other", "", "equals", "badgeText", "Ljava/lang/String;", "getBadgeText", "()Ljava/lang/String;", "contentDescription", "getContentDescription", "badgeVisibility", "I", "getBadgeVisibility", "()I", "badgeIcon", "Ljava/lang/Integer;", "getBadgeIcon", "()Ljava/lang/Integer;", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class BadgeModel {
    private final Integer badgeIcon;
    private final String badgeText;
    private final int badgeVisibility;
    private final String contentDescription;

    @JvmOverloads
    public BadgeModel() {
        this(null, null, 0, null, 15, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BadgeModel(String badgeText, String contentDescription, int i) {
        this(badgeText, contentDescription, i, null, 8, null);
        Intrinsics.checkNotNullParameter(badgeText, "badgeText");
        Intrinsics.checkNotNullParameter(contentDescription, "contentDescription");
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BadgeModel)) {
            return false;
        }
        BadgeModel badgeModel = (BadgeModel) other;
        return Intrinsics.areEqual(this.badgeText, badgeModel.badgeText) && Intrinsics.areEqual(this.contentDescription, badgeModel.contentDescription) && this.badgeVisibility == badgeModel.badgeVisibility && Intrinsics.areEqual(this.badgeIcon, badgeModel.badgeIcon);
    }

    public int hashCode() {
        int iHashCode = ((((this.badgeText.hashCode() * 31) + this.contentDescription.hashCode()) * 31) + Integer.hashCode(this.badgeVisibility)) * 31;
        Integer num = this.badgeIcon;
        return iHashCode + (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        return "BadgeModel(badgeText=" + this.badgeText + ", contentDescription=" + this.contentDescription + ", badgeVisibility=" + this.badgeVisibility + ", badgeIcon=" + this.badgeIcon + ')';
    }

    @JvmOverloads
    public BadgeModel(String badgeText, String contentDescription, int i, Integer num) {
        Intrinsics.checkNotNullParameter(badgeText, "badgeText");
        Intrinsics.checkNotNullParameter(contentDescription, "contentDescription");
        this.badgeText = badgeText;
        this.contentDescription = contentDescription;
        this.badgeVisibility = i;
        this.badgeIcon = num;
    }

    public /* synthetic */ BadgeModel(String str, String str2, int i, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? 8 : i, (i2 & 8) != 0 ? null : num);
    }

    public final String getBadgeText() {
        return this.badgeText;
    }

    public final int getBadgeVisibility() {
        return this.badgeVisibility;
    }

    public final Integer getBadgeIcon() {
        return this.badgeIcon;
    }
}
