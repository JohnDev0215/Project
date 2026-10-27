package com.amazon.mp3.fragment.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioStatsDialogModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0006\u0010\u001c\u001a\u00020\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0013\u0010\fR\u0017\u0010\u0014\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0015\u0010\fR\u001d\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\n\u001a\u0004\b\u001d\u0010\f¨\u0006 "}, d2 = {"Lcom/amazon/mp3/fragment/model/AudioStatsDialogModel;", "", "", "toString", "", "hashCode", "other", "", "equals", "pageTitle", "Ljava/lang/String;", "getPageTitle", "()Ljava/lang/String;", "", "message", "Ljava/lang/CharSequence;", "getMessage", "()Ljava/lang/CharSequence;", "immersiveDescription", "getImmersiveDescription", "updateAudioSettingsText", "getUpdateAudioSettingsText", "", "Lcom/amazon/mp3/fragment/model/AudioStatsRowModel;", "audioStatsList", "Ljava/util/List;", "getAudioStatsList", "()Ljava/util/List;", "gotItButtonText", "getGotItButtonText", "<init>", "(Ljava/lang/String;Ljava/lang/CharSequence;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AudioStatsDialogModel {
    private final List<AudioStatsRowModel> audioStatsList;
    private final String gotItButtonText;
    private final String immersiveDescription;
    private final CharSequence message;
    private final String pageTitle;
    private final String updateAudioSettingsText;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AudioStatsDialogModel)) {
            return false;
        }
        AudioStatsDialogModel audioStatsDialogModel = (AudioStatsDialogModel) other;
        return Intrinsics.areEqual(this.pageTitle, audioStatsDialogModel.pageTitle) && Intrinsics.areEqual(this.message, audioStatsDialogModel.message) && Intrinsics.areEqual(this.immersiveDescription, audioStatsDialogModel.immersiveDescription) && Intrinsics.areEqual(this.updateAudioSettingsText, audioStatsDialogModel.updateAudioSettingsText) && Intrinsics.areEqual(this.audioStatsList, audioStatsDialogModel.audioStatsList) && Intrinsics.areEqual(this.gotItButtonText, audioStatsDialogModel.gotItButtonText);
    }

    public int hashCode() {
        return (((((((((this.pageTitle.hashCode() * 31) + this.message.hashCode()) * 31) + this.immersiveDescription.hashCode()) * 31) + this.updateAudioSettingsText.hashCode()) * 31) + this.audioStatsList.hashCode()) * 31) + this.gotItButtonText.hashCode();
    }

    public String toString() {
        return "AudioStatsDialogModel(pageTitle=" + this.pageTitle + ", message=" + ((Object) this.message) + ", immersiveDescription=" + this.immersiveDescription + ", updateAudioSettingsText=" + this.updateAudioSettingsText + ", audioStatsList=" + this.audioStatsList + ", gotItButtonText=" + this.gotItButtonText + ')';
    }

    public AudioStatsDialogModel(String pageTitle, CharSequence message, String immersiveDescription, String updateAudioSettingsText, List<AudioStatsRowModel> audioStatsList, String gotItButtonText) {
        Intrinsics.checkNotNullParameter(pageTitle, "pageTitle");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(immersiveDescription, "immersiveDescription");
        Intrinsics.checkNotNullParameter(updateAudioSettingsText, "updateAudioSettingsText");
        Intrinsics.checkNotNullParameter(audioStatsList, "audioStatsList");
        Intrinsics.checkNotNullParameter(gotItButtonText, "gotItButtonText");
        this.pageTitle = pageTitle;
        this.message = message;
        this.immersiveDescription = immersiveDescription;
        this.updateAudioSettingsText = updateAudioSettingsText;
        this.audioStatsList = audioStatsList;
        this.gotItButtonText = gotItButtonText;
    }

    public final String getPageTitle() {
        return this.pageTitle;
    }

    public final CharSequence getMessage() {
        return this.message;
    }

    public final String getImmersiveDescription() {
        return this.immersiveDescription;
    }

    public final String getUpdateAudioSettingsText() {
        return this.updateAudioSettingsText;
    }

    public final List<AudioStatsRowModel> getAudioStatsList() {
        return this.audioStatsList;
    }

    public final String getGotItButtonText() {
        return this.gotItButtonText;
    }
}
