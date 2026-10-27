package com.amazon.mp3.activity;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StreamingPreferenceModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\u0006\u0010\u001b\u001a\u00020\u0002\u0012\u0006\u0010\u001d\u001a\u00020\u0002¢\u0006\u0004\b\u001f\u0010 J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u000b\u001a\u0004\b\u001c\u0010\rR\u001a\u0010\u001d\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\r¨\u0006!"}, d2 = {"Lcom/amazon/mp3/activity/StreamingPreferenceModel;", "Lcom/amazon/mp3/activity/KatanaPreferencesModel;", "", "toString", "", "hashCode", "", "other", "", "equals", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "", "Lcom/amazon/mp3/activity/TwoLinesListPreferenceModel;", "wifiPreferenceList", "Ljava/util/List;", "getWifiPreferenceList", "()Ljava/util/List;", "otaPreferenceList", "getOtaPreferenceList", "", "message", "Ljava/lang/CharSequence;", "getMessage", "()Ljava/lang/CharSequence;", "saveButtonText", "getSaveButtonText", "cancelButtonText", "getCancelButtonText", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/CharSequence;Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class StreamingPreferenceModel extends KatanaPreferencesModel {
    private final String cancelButtonText;
    private final CharSequence message;
    private final List<TwoLinesListPreferenceModel> otaPreferenceList;
    private final String saveButtonText;
    private final String title;
    private final List<TwoLinesListPreferenceModel> wifiPreferenceList;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamingPreferenceModel)) {
            return false;
        }
        StreamingPreferenceModel streamingPreferenceModel = (StreamingPreferenceModel) other;
        return Intrinsics.areEqual(getTitle(), streamingPreferenceModel.getTitle()) && Intrinsics.areEqual(this.wifiPreferenceList, streamingPreferenceModel.wifiPreferenceList) && Intrinsics.areEqual(this.otaPreferenceList, streamingPreferenceModel.otaPreferenceList) && Intrinsics.areEqual(getMessage(), streamingPreferenceModel.getMessage()) && Intrinsics.areEqual(getSaveButtonText(), streamingPreferenceModel.getSaveButtonText()) && Intrinsics.areEqual(getCancelButtonText(), streamingPreferenceModel.getCancelButtonText());
    }

    public int hashCode() {
        return (((((((((getTitle().hashCode() * 31) + this.wifiPreferenceList.hashCode()) * 31) + this.otaPreferenceList.hashCode()) * 31) + (getMessage() == null ? 0 : getMessage().hashCode())) * 31) + getSaveButtonText().hashCode()) * 31) + getCancelButtonText().hashCode();
    }

    public String toString() {
        return "StreamingPreferenceModel(title=" + getTitle() + ", wifiPreferenceList=" + this.wifiPreferenceList + ", otaPreferenceList=" + this.otaPreferenceList + ", message=" + ((Object) getMessage()) + ", saveButtonText=" + getSaveButtonText() + ", cancelButtonText=" + getCancelButtonText() + ')';
    }

    public String getTitle() {
        return this.title;
    }

    public final List<TwoLinesListPreferenceModel> getWifiPreferenceList() {
        return this.wifiPreferenceList;
    }

    public final List<TwoLinesListPreferenceModel> getOtaPreferenceList() {
        return this.otaPreferenceList;
    }

    public CharSequence getMessage() {
        return this.message;
    }

    public String getSaveButtonText() {
        return this.saveButtonText;
    }

    public StreamingPreferenceModel(String title, List<TwoLinesListPreferenceModel> wifiPreferenceList, List<TwoLinesListPreferenceModel> otaPreferenceList, CharSequence charSequence, String saveButtonText, String cancelButtonText) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(wifiPreferenceList, "wifiPreferenceList");
        Intrinsics.checkNotNullParameter(otaPreferenceList, "otaPreferenceList");
        Intrinsics.checkNotNullParameter(saveButtonText, "saveButtonText");
        Intrinsics.checkNotNullParameter(cancelButtonText, "cancelButtonText");
        this.title = title;
        this.wifiPreferenceList = wifiPreferenceList;
        this.otaPreferenceList = otaPreferenceList;
        this.message = charSequence;
        this.saveButtonText = saveButtonText;
        this.cancelButtonText = cancelButtonText;
    }

    public String getCancelButtonText() {
        return this.cancelButtonText;
    }
}
