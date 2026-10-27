package com.amazon.music.platform.graphql.generated.fragment;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Eligibility.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001cBU\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\u0010\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0010\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u001a\u0010\u001bJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\t\u0010\u000bR\u0019\u0010\f\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\f\u0010\n\u001a\u0004\b\f\u0010\u000bR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\r\u0010\u000bR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u000e\u0010\n\u001a\u0004\b\u000e\u0010\u000bR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u000f\u0010\u000bR!\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "", "", "toString", "", "hashCode", "other", "", "equals", "isDownloadable", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "isPreviewable", "isPrimeEligible", "isPurchaseable", "isPreorderable", "", "playbackSubscriptionTiers", "Ljava/util/List;", "getPlaybackSubscriptionTiers", "()Ljava/util/List;", "Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility$Playback;", "playback", "Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility$Playback;", "getPlayback", "()Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility$Playback;", "<init>", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/List;Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility$Playback;)V", "Playback", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class Eligibility {
    private final Boolean isDownloadable;
    private final Boolean isPreorderable;
    private final Boolean isPreviewable;
    private final Boolean isPrimeEligible;
    private final Boolean isPurchaseable;
    private final Playback playback;
    private final List<String> playbackSubscriptionTiers;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Eligibility)) {
            return false;
        }
        Eligibility eligibility = (Eligibility) other;
        return Intrinsics.areEqual(this.isDownloadable, eligibility.isDownloadable) && Intrinsics.areEqual(this.isPreviewable, eligibility.isPreviewable) && Intrinsics.areEqual(this.isPrimeEligible, eligibility.isPrimeEligible) && Intrinsics.areEqual(this.isPurchaseable, eligibility.isPurchaseable) && Intrinsics.areEqual(this.isPreorderable, eligibility.isPreorderable) && Intrinsics.areEqual(this.playbackSubscriptionTiers, eligibility.playbackSubscriptionTiers) && Intrinsics.areEqual(this.playback, eligibility.playback);
    }

    public int hashCode() {
        Boolean bool = this.isDownloadable;
        int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Boolean bool2 = this.isPreviewable;
        int iHashCode2 = (iHashCode + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.isPrimeEligible;
        int iHashCode3 = (iHashCode2 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Boolean bool4 = this.isPurchaseable;
        int iHashCode4 = (iHashCode3 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        Boolean bool5 = this.isPreorderable;
        int iHashCode5 = (iHashCode4 + (bool5 == null ? 0 : bool5.hashCode())) * 31;
        List<String> list = this.playbackSubscriptionTiers;
        int iHashCode6 = (iHashCode5 + (list == null ? 0 : list.hashCode())) * 31;
        Playback playback = this.playback;
        return iHashCode6 + (playback != null ? playback.hashCode() : 0);
    }

    public String toString() {
        return "Eligibility(isDownloadable=" + this.isDownloadable + ", isPreviewable=" + this.isPreviewable + ", isPrimeEligible=" + this.isPrimeEligible + ", isPurchaseable=" + this.isPurchaseable + ", isPreorderable=" + this.isPreorderable + ", playbackSubscriptionTiers=" + this.playbackSubscriptionTiers + ", playback=" + this.playback + ')';
    }

    public Eligibility(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, List<String> list, Playback playback) {
        this.isDownloadable = bool;
        this.isPreviewable = bool2;
        this.isPrimeEligible = bool3;
        this.isPurchaseable = bool4;
        this.isPreorderable = bool5;
        this.playbackSubscriptionTiers = list;
        this.playback = playback;
    }

    /* JADX INFO: renamed from: isDownloadable, reason: from getter */
    public final Boolean getIsDownloadable() {
        return this.isDownloadable;
    }

    /* JADX INFO: renamed from: isPreviewable, reason: from getter */
    public final Boolean getIsPreviewable() {
        return this.isPreviewable;
    }

    /* JADX INFO: renamed from: isPrimeEligible, reason: from getter */
    public final Boolean getIsPrimeEligible() {
        return this.isPrimeEligible;
    }

    /* JADX INFO: renamed from: isPurchaseable, reason: from getter */
    public final Boolean getIsPurchaseable() {
        return this.isPurchaseable;
    }

    /* JADX INFO: renamed from: isPreorderable, reason: from getter */
    public final Boolean getIsPreorderable() {
        return this.isPreorderable;
    }

    public final List<String> getPlaybackSubscriptionTiers() {
        return this.playbackSubscriptionTiers;
    }

    public final Playback getPlayback() {
        return this.playback;
    }

    /* JADX INFO: compiled from: Eligibility.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility$Playback;", "", "", "toString", "", "hashCode", "other", "", "equals", "shuffleType", "Ljava/lang/String;", "getShuffleType", "()Ljava/lang/String;", "canPlayOnDemand", "Ljava/lang/Boolean;", "getCanPlayOnDemand", "()Ljava/lang/Boolean;", "<init>", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Playback {
        private final Boolean canPlayOnDemand;
        private final String shuffleType;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Playback)) {
                return false;
            }
            Playback playback = (Playback) other;
            return Intrinsics.areEqual(this.shuffleType, playback.shuffleType) && Intrinsics.areEqual(this.canPlayOnDemand, playback.canPlayOnDemand);
        }

        public int hashCode() {
            String str = this.shuffleType;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Boolean bool = this.canPlayOnDemand;
            return iHashCode + (bool != null ? bool.hashCode() : 0);
        }

        public String toString() {
            return "Playback(shuffleType=" + this.shuffleType + ", canPlayOnDemand=" + this.canPlayOnDemand + ')';
        }

        public Playback(String str, Boolean bool) {
            this.shuffleType = str;
            this.canPlayOnDemand = bool;
        }

        public final String getShuffleType() {
            return this.shuffleType;
        }

        public final Boolean getCanPlayOnDemand() {
            return this.canPlayOnDemand;
        }
    }
}
