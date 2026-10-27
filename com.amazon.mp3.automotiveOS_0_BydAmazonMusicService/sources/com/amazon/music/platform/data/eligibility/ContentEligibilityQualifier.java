package com.amazon.music.platform.data.eligibility;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0007\t\n\u000b\f\r\u000e\u000fB\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0007\u0010\u0011\u0012\u0013\u0014\u0015\u0016¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier;", "", "", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "IsDownloaded", "IsFollowable", "IsFreeOnDemandPlayable", "IsOnDemandByService", "IsOwned", "IsSonicRushOnDemandPlayable", "IsUnavailable", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier$IsDownloaded;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier$IsFollowable;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier$IsFreeOnDemandPlayable;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier$IsOnDemandByService;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier$IsOwned;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier$IsSonicRushOnDemandPlayable;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier$IsUnavailable;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public abstract class ContentEligibilityQualifier {
    private final String name;

    public /* synthetic */ ContentEligibilityQualifier(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    private ContentEligibilityQualifier(String str) {
        this.name = str;
    }

    public final String getName() {
        return this.name;
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier$IsUnavailable;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsUnavailable extends ContentEligibilityQualifier {
        public static final IsUnavailable INSTANCE = new IsUnavailable();

        private IsUnavailable() {
            super("isUnavailable", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier$IsOwned;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsOwned extends ContentEligibilityQualifier {
        public static final IsOwned INSTANCE = new IsOwned();

        private IsOwned() {
            super("isOwned", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier$IsDownloaded;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsDownloaded extends ContentEligibilityQualifier {
        public static final IsDownloaded INSTANCE = new IsDownloaded();

        private IsDownloaded() {
            super("isDownloaded", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier$IsFreeOnDemandPlayable;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsFreeOnDemandPlayable extends ContentEligibilityQualifier {
        public static final IsFreeOnDemandPlayable INSTANCE = new IsFreeOnDemandPlayable();

        private IsFreeOnDemandPlayable() {
            super("IsFreeOnDemandPlayable", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier$IsSonicRushOnDemandPlayable;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsSonicRushOnDemandPlayable extends ContentEligibilityQualifier {
        public static final IsSonicRushOnDemandPlayable INSTANCE = new IsSonicRushOnDemandPlayable();

        private IsSonicRushOnDemandPlayable() {
            super("IsSonicRushOnDemandPlayable", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier$IsOnDemandByService;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsOnDemandByService extends ContentEligibilityQualifier {
        public static final IsOnDemandByService INSTANCE = new IsOnDemandByService();

        private IsOnDemandByService() {
            super("IsOnDemandByService", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier$IsFollowable;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsFollowable extends ContentEligibilityQualifier {
        public static final IsFollowable INSTANCE = new IsFollowable();

        private IsFollowable() {
            super("isFollowable", null);
        }
    }
}
