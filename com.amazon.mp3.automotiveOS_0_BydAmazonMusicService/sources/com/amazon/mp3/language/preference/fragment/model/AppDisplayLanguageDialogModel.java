package com.amazon.mp3.language.preference.fragment.model;

import com.amazon.mp3.activity.TwoLinesListPreferenceModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppDisplayLanguageDialogModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\n\u001a\u0004\b\u0019\u0010\fR\u0017\u0010\u001a\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\n\u001a\u0004\b\u001b\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/language/preference/fragment/model/AppDisplayLanguageDialogModel;", "", "", "toString", "", "hashCode", "other", "", "equals", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "", "Lcom/amazon/mp3/activity/TwoLinesListPreferenceModel;", "showAppDisplayLanguageList", "Ljava/util/List;", "getShowAppDisplayLanguageList", "()Ljava/util/List;", "", "message", "Ljava/lang/CharSequence;", "getMessage", "()Ljava/lang/CharSequence;", "saveAndRestartButtonText", "getSaveAndRestartButtonText", "cancelButtonText", "getCancelButtonText", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/CharSequence;Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AppDisplayLanguageDialogModel {
    private final String cancelButtonText;
    private final CharSequence message;
    private final String saveAndRestartButtonText;
    private final List<TwoLinesListPreferenceModel> showAppDisplayLanguageList;
    private final String title;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppDisplayLanguageDialogModel)) {
            return false;
        }
        AppDisplayLanguageDialogModel appDisplayLanguageDialogModel = (AppDisplayLanguageDialogModel) other;
        return Intrinsics.areEqual(this.title, appDisplayLanguageDialogModel.title) && Intrinsics.areEqual(this.showAppDisplayLanguageList, appDisplayLanguageDialogModel.showAppDisplayLanguageList) && Intrinsics.areEqual(this.message, appDisplayLanguageDialogModel.message) && Intrinsics.areEqual(this.saveAndRestartButtonText, appDisplayLanguageDialogModel.saveAndRestartButtonText) && Intrinsics.areEqual(this.cancelButtonText, appDisplayLanguageDialogModel.cancelButtonText);
    }

    public int hashCode() {
        int iHashCode = ((this.title.hashCode() * 31) + this.showAppDisplayLanguageList.hashCode()) * 31;
        CharSequence charSequence = this.message;
        return ((((iHashCode + (charSequence == null ? 0 : charSequence.hashCode())) * 31) + this.saveAndRestartButtonText.hashCode()) * 31) + this.cancelButtonText.hashCode();
    }

    public String toString() {
        return "AppDisplayLanguageDialogModel(title=" + this.title + ", showAppDisplayLanguageList=" + this.showAppDisplayLanguageList + ", message=" + ((Object) this.message) + ", saveAndRestartButtonText=" + this.saveAndRestartButtonText + ", cancelButtonText=" + this.cancelButtonText + ')';
    }

    public AppDisplayLanguageDialogModel(String title, List<TwoLinesListPreferenceModel> showAppDisplayLanguageList, CharSequence charSequence, String saveAndRestartButtonText, String cancelButtonText) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(showAppDisplayLanguageList, "showAppDisplayLanguageList");
        Intrinsics.checkNotNullParameter(saveAndRestartButtonText, "saveAndRestartButtonText");
        Intrinsics.checkNotNullParameter(cancelButtonText, "cancelButtonText");
        this.title = title;
        this.showAppDisplayLanguageList = showAppDisplayLanguageList;
        this.message = charSequence;
        this.saveAndRestartButtonText = saveAndRestartButtonText;
        this.cancelButtonText = cancelButtonText;
    }

    public final List<TwoLinesListPreferenceModel> getShowAppDisplayLanguageList() {
        return this.showAppDisplayLanguageList;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSaveAndRestartButtonText() {
        return this.saveAndRestartButtonText;
    }

    public final String getCancelButtonText() {
        return this.cancelButtonText;
    }
}
