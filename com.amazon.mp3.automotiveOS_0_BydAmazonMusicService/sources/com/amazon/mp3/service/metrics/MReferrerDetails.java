package com.amazon.mp3.service.metrics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MReferrerDetails.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\r\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/service/metrics/MReferrerDetails;", "", "rawReferrer", "", "clickTS", "", "installBeginTS", "(Ljava/lang/String;JJ)V", "getClickTS", "()J", "setClickTS", "(J)V", "getInstallBeginTS", "setInstallBeginTS", "getRawReferrer", "()Ljava/lang/String;", "setRawReferrer", "(Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MReferrerDetails {
    private long clickTS;
    private long installBeginTS;
    private String rawReferrer;

    public MReferrerDetails() {
        this(null, 0L, 0L, 7, null);
    }

    public MReferrerDetails(String rawReferrer, long j, long j2) {
        Intrinsics.checkNotNullParameter(rawReferrer, "rawReferrer");
        this.rawReferrer = rawReferrer;
        this.clickTS = j;
        this.installBeginTS = j2;
    }

    public /* synthetic */ MReferrerDetails(String str, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? 0L : j, (i & 4) != 0 ? 0L : j2);
    }

    public final long getClickTS() {
        return this.clickTS;
    }

    public final long getInstallBeginTS() {
        return this.installBeginTS;
    }

    public final String getRawReferrer() {
        return this.rawReferrer;
    }

    public final void setClickTS(long j) {
        this.clickTS = j;
    }

    public final void setInstallBeginTS(long j) {
        this.installBeginTS = j;
    }

    public final void setRawReferrer(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.rawReferrer = str;
    }
}
