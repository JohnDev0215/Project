package com.amazon.mp3.fragment.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioStatsRowModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/fragment/model/AudioStatsRowModel;", "", "", "toString", "", "hashCode", "other", "", "equals", "propertyTitle", "Ljava/lang/String;", "getPropertyTitle", "()Ljava/lang/String;", "propertyValue", "getPropertyValue", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AudioStatsRowModel {
    private final String propertyTitle;
    private final String propertyValue;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AudioStatsRowModel)) {
            return false;
        }
        AudioStatsRowModel audioStatsRowModel = (AudioStatsRowModel) other;
        return Intrinsics.areEqual(this.propertyTitle, audioStatsRowModel.propertyTitle) && Intrinsics.areEqual(this.propertyValue, audioStatsRowModel.propertyValue);
    }

    public int hashCode() {
        String str = this.propertyTitle;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.propertyValue;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "AudioStatsRowModel(propertyTitle=" + ((Object) this.propertyTitle) + ", propertyValue=" + ((Object) this.propertyValue) + ')';
    }

    public AudioStatsRowModel(String str, String str2) {
        this.propertyTitle = str;
        this.propertyValue = str2;
    }

    public final String getPropertyTitle() {
        return this.propertyTitle;
    }

    public final String getPropertyValue() {
        return this.propertyValue;
    }
}
