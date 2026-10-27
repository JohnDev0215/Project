package com.amazon.mp3.activity;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeFabPreferenceModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001b\u001a\u00020\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u000b\u001a\u0004\b\u001a\u0010\rR\u001a\u0010\u001b\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u000b\u001a\u0004\b\u001c\u0010\r¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/activity/CarModeFabPreferenceModel;", "Lcom/amazon/mp3/activity/KatanaPreferencesModel;", "", "toString", "", "hashCode", "", "other", "", "equals", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "", "Lcom/amazon/mp3/activity/TwoLinesListPreferenceModel;", "showCarModePreferenceList", "Ljava/util/List;", "getShowCarModePreferenceList", "()Ljava/util/List;", "", "message", "Ljava/lang/CharSequence;", "getMessage", "()Ljava/lang/CharSequence;", "saveButtonText", "getSaveButtonText", "cancelButtonText", "getCancelButtonText", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/CharSequence;Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class CarModeFabPreferenceModel extends KatanaPreferencesModel {
    private final String cancelButtonText;
    private final CharSequence message;
    private final String saveButtonText;
    private final List<TwoLinesListPreferenceModel> showCarModePreferenceList;
    private final String title;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CarModeFabPreferenceModel)) {
            return false;
        }
        CarModeFabPreferenceModel carModeFabPreferenceModel = (CarModeFabPreferenceModel) other;
        return Intrinsics.areEqual(getTitle(), carModeFabPreferenceModel.getTitle()) && Intrinsics.areEqual(this.showCarModePreferenceList, carModeFabPreferenceModel.showCarModePreferenceList) && Intrinsics.areEqual(getMessage(), carModeFabPreferenceModel.getMessage()) && Intrinsics.areEqual(getSaveButtonText(), carModeFabPreferenceModel.getSaveButtonText()) && Intrinsics.areEqual(getCancelButtonText(), carModeFabPreferenceModel.getCancelButtonText());
    }

    public int hashCode() {
        return (((((((getTitle().hashCode() * 31) + this.showCarModePreferenceList.hashCode()) * 31) + (getMessage() == null ? 0 : getMessage().hashCode())) * 31) + getSaveButtonText().hashCode()) * 31) + getCancelButtonText().hashCode();
    }

    public String toString() {
        return "CarModeFabPreferenceModel(title=" + getTitle() + ", showCarModePreferenceList=" + this.showCarModePreferenceList + ", message=" + ((Object) getMessage()) + ", saveButtonText=" + getSaveButtonText() + ", cancelButtonText=" + getCancelButtonText() + ')';
    }

    public String getTitle() {
        return this.title;
    }

    public final List<TwoLinesListPreferenceModel> getShowCarModePreferenceList() {
        return this.showCarModePreferenceList;
    }

    public CharSequence getMessage() {
        return this.message;
    }

    public String getSaveButtonText() {
        return this.saveButtonText;
    }

    public CarModeFabPreferenceModel(String title, List<TwoLinesListPreferenceModel> showCarModePreferenceList, CharSequence charSequence, String saveButtonText, String cancelButtonText) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(showCarModePreferenceList, "showCarModePreferenceList");
        Intrinsics.checkNotNullParameter(saveButtonText, "saveButtonText");
        Intrinsics.checkNotNullParameter(cancelButtonText, "cancelButtonText");
        this.title = title;
        this.showCarModePreferenceList = showCarModePreferenceList;
        this.message = charSequence;
        this.saveButtonText = saveButtonText;
        this.cancelButtonText = cancelButtonText;
    }

    public String getCancelButtonText() {
        return this.cancelButtonText;
    }
}
