package com.amazon.music.platform.lyricReport.data;

import com.amazon.music.platform.PlatformProviderUtilKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsReportResponse.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/platform/lyricReport/data/LyricsReportResponse;", "", "", "toString", "", "hashCode", "other", "", "equals", "reportStatus", "Ljava/lang/Boolean;", "getReportStatus", "()Ljava/lang/Boolean;", "details", "Ljava/lang/String;", "getDetails", "()Ljava/lang/String;", "<init>", "(Ljava/lang/Boolean;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class LyricsReportResponse {
    private final String details;
    private final Boolean reportStatus;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LyricsReportResponse)) {
            return false;
        }
        LyricsReportResponse lyricsReportResponse = (LyricsReportResponse) other;
        return Intrinsics.areEqual(this.reportStatus, lyricsReportResponse.reportStatus) && Intrinsics.areEqual(this.details, lyricsReportResponse.details);
    }

    public int hashCode() {
        Boolean bool = this.reportStatus;
        int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        String str = this.details;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "LyricsReportResponse(reportStatus=" + this.reportStatus + ", details=" + this.details + ')';
    }

    public LyricsReportResponse(Boolean bool, String str) {
        this.reportStatus = bool;
        this.details = str;
        PlatformProviderUtilKt.freeze(this);
    }

    public final Boolean getReportStatus() {
        return this.reportStatus;
    }
}
