package com.amazon.mp3.fragment.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FragmentPushTopicsModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\"\u0010\u0012\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/fragment/model/FragmentPushTopicsModel;", "", "", "toString", "", "hashCode", "other", "", "equals", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "saveButtonText", "getSaveButtonText", "setSaveButtonText", "cancelButtonText", "getCancelButtonText", "setCancelButtonText", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class FragmentPushTopicsModel {
    private String cancelButtonText;
    private String saveButtonText;
    private String title;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FragmentPushTopicsModel)) {
            return false;
        }
        FragmentPushTopicsModel fragmentPushTopicsModel = (FragmentPushTopicsModel) other;
        return Intrinsics.areEqual(this.title, fragmentPushTopicsModel.title) && Intrinsics.areEqual(this.saveButtonText, fragmentPushTopicsModel.saveButtonText) && Intrinsics.areEqual(this.cancelButtonText, fragmentPushTopicsModel.cancelButtonText);
    }

    public int hashCode() {
        return (((this.title.hashCode() * 31) + this.saveButtonText.hashCode()) * 31) + this.cancelButtonText.hashCode();
    }

    public String toString() {
        return "FragmentPushTopicsModel(title=" + this.title + ", saveButtonText=" + this.saveButtonText + ", cancelButtonText=" + this.cancelButtonText + ')';
    }

    public FragmentPushTopicsModel(String title, String saveButtonText, String cancelButtonText) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(saveButtonText, "saveButtonText");
        Intrinsics.checkNotNullParameter(cancelButtonText, "cancelButtonText");
        this.title = title;
        this.saveButtonText = saveButtonText;
        this.cancelButtonText = cancelButtonText;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSaveButtonText() {
        return this.saveButtonText;
    }

    public final String getCancelButtonText() {
        return this.cancelButtonText;
    }
}
