package com.amazon.mp3.lyrics.item;

import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsFeedbackOperationsMetadata.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/lyrics/item/LyricsFeedbackOperationsMetadata;", "Lcom/amazon/mp3/lyrics/item/LyricsOperationsMetadata;", "", "toString", "", "hashCode", "", "other", "", "equals", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "Ljava/lang/String;", "getTrackAsin", "()Ljava/lang/String;", "trackTitle", "getTrackTitle", "", "reportTimeStamp", "J", "getReportTimeStamp", "()J", "<init>", "(Ljava/lang/String;Ljava/lang/String;J)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class LyricsFeedbackOperationsMetadata implements LyricsOperationsMetadata {
    private final long reportTimeStamp;
    private final String trackAsin;
    private final String trackTitle;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LyricsFeedbackOperationsMetadata)) {
            return false;
        }
        LyricsFeedbackOperationsMetadata lyricsFeedbackOperationsMetadata = (LyricsFeedbackOperationsMetadata) other;
        return Intrinsics.areEqual(this.trackAsin, lyricsFeedbackOperationsMetadata.trackAsin) && Intrinsics.areEqual(this.trackTitle, lyricsFeedbackOperationsMetadata.trackTitle) && this.reportTimeStamp == lyricsFeedbackOperationsMetadata.reportTimeStamp;
    }

    public int hashCode() {
        return (((this.trackAsin.hashCode() * 31) + this.trackTitle.hashCode()) * 31) + Long.hashCode(this.reportTimeStamp);
    }

    public String toString() {
        return "LyricsFeedbackOperationsMetadata(trackAsin=" + this.trackAsin + ", trackTitle=" + this.trackTitle + ", reportTimeStamp=" + this.reportTimeStamp + ')';
    }

    public LyricsFeedbackOperationsMetadata(String trackAsin, String trackTitle, long j) {
        Intrinsics.checkNotNullParameter(trackAsin, "trackAsin");
        Intrinsics.checkNotNullParameter(trackTitle, "trackTitle");
        this.trackAsin = trackAsin;
        this.trackTitle = trackTitle;
        this.reportTimeStamp = j;
    }

    public final String getTrackAsin() {
        return this.trackAsin;
    }

    public final String getTrackTitle() {
        return this.trackTitle;
    }

    public final long getReportTimeStamp() {
        return this.reportTimeStamp;
    }
}
