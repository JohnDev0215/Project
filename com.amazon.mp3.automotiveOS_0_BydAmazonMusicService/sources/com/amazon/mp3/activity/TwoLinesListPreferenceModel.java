package com.amazon.mp3.activity;

import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TwoLinesListPreferenceModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\"\u0010\u0011\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0011\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/activity/TwoLinesListPreferenceModel;", "", "", "toString", "", "hashCode", "other", "", "equals", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "entryValue", "getEntryValue", MediaTrack.ROLE_DESCRIPTION, "getDescription", "isChecked", "Z", "()Z", "setChecked", "(Z)V", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class TwoLinesListPreferenceModel {
    private final String description;
    private final String entryValue;
    private boolean isChecked;
    private final String title;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TwoLinesListPreferenceModel)) {
            return false;
        }
        TwoLinesListPreferenceModel twoLinesListPreferenceModel = (TwoLinesListPreferenceModel) other;
        return Intrinsics.areEqual(this.title, twoLinesListPreferenceModel.title) && Intrinsics.areEqual(this.entryValue, twoLinesListPreferenceModel.entryValue) && Intrinsics.areEqual(this.description, twoLinesListPreferenceModel.description) && this.isChecked == twoLinesListPreferenceModel.isChecked;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    public int hashCode() {
        int iHashCode = this.title.hashCode() * 31;
        String str = this.entryValue;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.description;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z = this.isChecked;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode3 + r1;
    }

    public String toString() {
        return "TwoLinesListPreferenceModel(title=" + this.title + ", entryValue=" + ((Object) this.entryValue) + ", description=" + ((Object) this.description) + ", isChecked=" + this.isChecked + ')';
    }

    public TwoLinesListPreferenceModel(String title, String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.title = title;
        this.entryValue = str;
        this.description = str2;
        this.isChecked = z;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getEntryValue() {
        return this.entryValue;
    }

    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: isChecked, reason: from getter */
    public final boolean getIsChecked() {
        return this.isChecked;
    }

    public final void setChecked(boolean z) {
        this.isChecked = z;
    }
}
