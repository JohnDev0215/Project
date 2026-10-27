package com.amazon.mp3.audioquality.nerdstats.models;

import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsOutputQuality, reason: from toString */
/* JADX INFO: compiled from: NerdStatsDataModels.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0004¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\"\u0010\r\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f\"\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"com/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsOutputQuality", "", "", "toString", "", "hashCode", "other", "", "equals", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", MediaTrack.ROLE_SUBTITLE, "getSubtitle", "setSubtitle", "(Ljava/lang/String;)V", "imageId", "I", "getImageId", "()I", "<init>", "(Ljava/lang/String;Ljava/lang/String;I)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class NerdStatsOutputQuality {
    private final int imageId;
    private String subtitle;
    private final String title;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NerdStatsOutputQuality)) {
            return false;
        }
        NerdStatsOutputQuality nerdStatsOutputQuality = (NerdStatsOutputQuality) other;
        return Intrinsics.areEqual(this.title, nerdStatsOutputQuality.title) && Intrinsics.areEqual(this.subtitle, nerdStatsOutputQuality.subtitle) && this.imageId == nerdStatsOutputQuality.imageId;
    }

    public int hashCode() {
        return (((this.title.hashCode() * 31) + this.subtitle.hashCode()) * 31) + Integer.hashCode(this.imageId);
    }

    public String toString() {
        return "NerdStatsOutputQuality(title=" + this.title + ", subtitle=" + this.subtitle + ", imageId=" + this.imageId + ')';
    }

    public NerdStatsOutputQuality(String title, String subtitle, int i) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        this.title = title;
        this.subtitle = subtitle;
        this.imageId = i;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final void setSubtitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.subtitle = str;
    }

    public final int getImageId() {
        return this.imageId;
    }
}
