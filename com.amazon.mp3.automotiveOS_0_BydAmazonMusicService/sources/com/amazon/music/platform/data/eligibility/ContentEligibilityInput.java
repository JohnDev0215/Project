package com.amazon.music.platform.data.eligibility;

import com.amazon.music.platform.data.eligibility.factory.EligibilityInput;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u000b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013B\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u000b\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput;", "Lcom/amazon/music/platform/data/eligibility/factory/EligibilityInput;", "", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "IsAvailable", "IsDownloaded", "IsFollowable", "IsFree", "IsFreeOnDemandPlayable", "IsMusicUnlimited", "IsOnDemandByService", "IsOwned", "IsPrime", "IsSonicRush", "IsSonicRushOnDemandPlayable", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsAvailable;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsDownloaded;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsFollowable;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsFree;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsFreeOnDemandPlayable;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsMusicUnlimited;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsOnDemandByService;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsOwned;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsPrime;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsSonicRush;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsSonicRushOnDemandPlayable;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public abstract class ContentEligibilityInput implements EligibilityInput {
    private final String name;

    public /* synthetic */ ContentEligibilityInput(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    private ContentEligibilityInput(String str) {
        this.name = str;
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsAvailable;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsAvailable extends ContentEligibilityInput {
        public static final IsAvailable INSTANCE = new IsAvailable();

        private IsAvailable() {
            super("isAvailable", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsOwned;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsOwned extends ContentEligibilityInput {
        public static final IsOwned INSTANCE = new IsOwned();

        private IsOwned() {
            super("isOwned", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsFree;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsFree extends ContentEligibilityInput {
        public static final IsFree INSTANCE = new IsFree();

        private IsFree() {
            super("isFree", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsPrime;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsPrime extends ContentEligibilityInput {
        public static final IsPrime INSTANCE = new IsPrime();

        private IsPrime() {
            super("isPrime", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsSonicRush;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsSonicRush extends ContentEligibilityInput {
        public static final IsSonicRush INSTANCE = new IsSonicRush();

        private IsSonicRush() {
            super("isSonicRush", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsMusicUnlimited;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsMusicUnlimited extends ContentEligibilityInput {
        public static final IsMusicUnlimited INSTANCE = new IsMusicUnlimited();

        private IsMusicUnlimited() {
            super("isMusicUnlimited", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsFreeOnDemandPlayable;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsFreeOnDemandPlayable extends ContentEligibilityInput {
        public static final IsFreeOnDemandPlayable INSTANCE = new IsFreeOnDemandPlayable();

        private IsFreeOnDemandPlayable() {
            super("isFreeOnDemandPlayable", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsSonicRushOnDemandPlayable;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsSonicRushOnDemandPlayable extends ContentEligibilityInput {
        public static final IsSonicRushOnDemandPlayable INSTANCE = new IsSonicRushOnDemandPlayable();

        private IsSonicRushOnDemandPlayable() {
            super("isSonicRushOnDemandPlayable", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsDownloaded;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsDownloaded extends ContentEligibilityInput {
        public static final IsDownloaded INSTANCE = new IsDownloaded();

        private IsDownloaded() {
            super("isDownloaded", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsOnDemandByService;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsOnDemandByService extends ContentEligibilityInput {
        public static final IsOnDemandByService INSTANCE = new IsOnDemandByService();

        private IsOnDemandByService() {
            super("isOnDemandByService", null);
        }
    }

    /* JADX INFO: compiled from: ContentEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput$IsFollowable;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityInput;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsFollowable extends ContentEligibilityInput {
        public static final IsFollowable INSTANCE = new IsFollowable();

        private IsFollowable() {
            super("isFollowable", null);
        }
    }
}
