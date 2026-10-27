package com.amazon.mp3.fragment.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RedownloadOfflineMusicDialogModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\t\u001a\u00020\u0002\u0012\b\b\u0002\u0010\r\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0004¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0017\u0010\u0011\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR\u0017\u0010\u0013\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/fragment/model/RedownloadOfflineMusicDialogModel;", "", "", "toString", "", "hashCode", "other", "", "equals", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "message", "getMessage", "primaryButtonText", "getPrimaryButtonText", "secondaryButtonText", "getSecondaryButtonText", "closeButtonVisibility", "I", "getCloseButtonVisibility", "()I", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class RedownloadOfflineMusicDialogModel {
    private final int closeButtonVisibility;
    private final String message;
    private final String primaryButtonText;
    private final String secondaryButtonText;
    private final String title;

    public RedownloadOfflineMusicDialogModel() {
        this(null, null, null, null, 0, 31, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RedownloadOfflineMusicDialogModel)) {
            return false;
        }
        RedownloadOfflineMusicDialogModel redownloadOfflineMusicDialogModel = (RedownloadOfflineMusicDialogModel) other;
        return Intrinsics.areEqual(this.title, redownloadOfflineMusicDialogModel.title) && Intrinsics.areEqual(this.message, redownloadOfflineMusicDialogModel.message) && Intrinsics.areEqual(this.primaryButtonText, redownloadOfflineMusicDialogModel.primaryButtonText) && Intrinsics.areEqual(this.secondaryButtonText, redownloadOfflineMusicDialogModel.secondaryButtonText) && this.closeButtonVisibility == redownloadOfflineMusicDialogModel.closeButtonVisibility;
    }

    public int hashCode() {
        return (((((((this.title.hashCode() * 31) + this.message.hashCode()) * 31) + this.primaryButtonText.hashCode()) * 31) + this.secondaryButtonText.hashCode()) * 31) + Integer.hashCode(this.closeButtonVisibility);
    }

    public String toString() {
        return "RedownloadOfflineMusicDialogModel(title=" + this.title + ", message=" + this.message + ", primaryButtonText=" + this.primaryButtonText + ", secondaryButtonText=" + this.secondaryButtonText + ", closeButtonVisibility=" + this.closeButtonVisibility + ')';
    }

    public RedownloadOfflineMusicDialogModel(String title, String message, String primaryButtonText, String secondaryButtonText, int i) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(primaryButtonText, "primaryButtonText");
        Intrinsics.checkNotNullParameter(secondaryButtonText, "secondaryButtonText");
        this.title = title;
        this.message = message;
        this.primaryButtonText = primaryButtonText;
        this.secondaryButtonText = secondaryButtonText;
        this.closeButtonVisibility = i;
    }

    public /* synthetic */ RedownloadOfflineMusicDialogModel(String str, String str2, String str3, String str4, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) == 0 ? str4 : "", (i2 & 16) != 0 ? 0 : i);
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getPrimaryButtonText() {
        return this.primaryButtonText;
    }

    public final String getSecondaryButtonText() {
        return this.secondaryButtonText;
    }

    public final int getCloseButtonVisibility() {
        return this.closeButtonVisibility;
    }
}
