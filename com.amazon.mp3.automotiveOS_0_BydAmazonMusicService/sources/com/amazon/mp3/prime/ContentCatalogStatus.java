package com.amazon.mp3.prime;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.music.ContentAccessType;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public enum ContentCatalogStatus {
    UNKNOWN(-1),
    NON_CATALOG(150),
    HAWKFIRE(200),
    SONIC_RUSH(220),
    PRIME(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION),
    BOP(400),
    PREVIOUSLY_CATALOG(450);

    public static final int BOP_OR_NOT_BOUNDARY = 375;
    public static final String CATALOG_NOT_OWNED_WHERE_CLAUSE = "prime_status>? AND ownership_status>?";
    public static final String NOT_CATALOG_OR_IS_OWNED_WHERE_CLAUSE = "prime_status<? OR ownership_status<?";
    public static final int PREV_CATALOG_OR_NOT_BOUNDARY = 400;
    public static final int PRIME_OR_NOT_BOUNDARY = 225;
    public static final int SONIC_RUSH_OR_NOT_BOUNDARY = 210;
    private int mValue;
    public static final int HAWKFIRE_OR_NOT_BOUNDARY = 175;
    public static final String[] NOT_CATALOG_OR_IS_OWNED_ARGS = {String.valueOf(HAWKFIRE_OR_NOT_BOUNDARY), String.valueOf(200)};
    public static final String[] CATALOG_NOT_OWNED_WHERE_ARGS = {String.valueOf(HAWKFIRE_OR_NOT_BOUNDARY), String.valueOf(200)};

    ContentCatalogStatus(int value) {
        this.mValue = value;
    }

    public int getValue() {
        return this.mValue;
    }

    public static ContentCatalogStatus fromValue(int value) {
        for (ContentCatalogStatus contentCatalogStatus : values()) {
            if (contentCatalogStatus.mValue == value) {
                return contentCatalogStatus;
            }
        }
        return UNKNOWN;
    }

    public static ContentCatalogStatus fromValues(final Set<SubscriptionType> contentTiers, boolean isVideo) {
        return fromValues(contentTiers.contains(SubscriptionType.MUSIC_UNLIMITED), contentTiers.contains(SubscriptionType.PRIME), contentTiers.contains(SubscriptionType.SONIC_RUSH), contentTiers.contains(SubscriptionType.FREE) && isVideo, false);
    }

    public static ContentCatalogStatus fromValues(final CatalogStatusTiers tiers, final boolean isOwned, final boolean isVideo) {
        if (tiers != null) {
            return fromValues(tiers.getIsMusicSubscription(), tiers.getIsPrime(), tiers.getIsSonicRush(), tiers.getIsFree() && isVideo, isOwned);
        }
        if (isOwned) {
            return NON_CATALOG;
        }
        return UNKNOWN;
    }

    public static ContentCatalogStatus fromValues(boolean isHawkfire, boolean isPrime, boolean isSonicRush, boolean isBOP, final boolean isOwned) {
        if (isOwned) {
            return NON_CATALOG;
        }
        if (isBOP) {
            return BOP;
        }
        if (isPrime) {
            return PRIME;
        }
        if (isSonicRush && Feature.sonic_rush.isEnabled(true)) {
            return SONIC_RUSH;
        }
        if (isHawkfire) {
            return HAWKFIRE;
        }
        return UNKNOWN;
    }

    public boolean isHawkfire() {
        return UNKNOWN != this && 175 < this.mValue;
    }

    public boolean isPrime() {
        return UNKNOWN != this && 225 < this.mValue;
    }

    public boolean isSonicRush() {
        return UNKNOWN != this && 210 < this.mValue;
    }

    public boolean isBOP() {
        return UNKNOWN != this && 375 < this.mValue;
    }

    public boolean isPreviouslyCatalog() {
        return UNKNOWN != this && 400 < this.mValue;
    }

    public boolean isUnknown() {
        return UNKNOWN == this;
    }

    public boolean isOwned() {
        return NON_CATALOG == this;
    }

    public static class PrimeContentCatalogStatus {
        @Deprecated
        public static ContentCatalogStatus getMinContentCatalogStatus(final boolean isPrime, final boolean isHawkfire) {
            return getContentCatalogStatus(false, isPrime, isHawkfire, false, false, false);
        }

        public static ContentCatalogStatus getMinContentCatalogStatus(final CatalogStatusTiers tiers, final boolean isVideo) {
            if (tiers == null) {
                return ContentCatalogStatus.UNKNOWN;
            }
            return getContentCatalogStatus(false, tiers.getIsPrime(), tiers.getIsMusicSubscription(), tiers.getIsSonicRush(), tiers.getIsFree(), isVideo);
        }

        @Deprecated
        public static ContentCatalogStatus getMaxContentCatalogStatus(final boolean isPrime, final boolean isHawkfire) {
            return getContentCatalogStatus(true, isPrime, isHawkfire, false, false, false);
        }

        public static ContentCatalogStatus getMaxContentCatalogStatus(final CatalogStatusTiers tiers, final boolean isVideo) {
            if (tiers == null) {
                return ContentCatalogStatus.UNKNOWN;
            }
            return getContentCatalogStatus(true, tiers.getIsPrime(), tiers.getIsMusicSubscription(), tiers.getIsSonicRush(), tiers.getIsFree(), isVideo);
        }

        private static ContentCatalogStatus getContentCatalogStatus(final boolean useMaxStatus, final boolean isPrime, final boolean isHawkfire, final boolean isSonicRush, final boolean isFree, final boolean isVideo) {
            ContentCatalogStatus contentCatalogStatus = ContentCatalogStatus.UNKNOWN;
            ContentCatalogStatus contentCatalogStatus2 = ContentCatalogStatus.PREVIOUSLY_CATALOG;
            boolean z = false;
            boolean z2 = isSonicRush && Feature.sonic_rush.isEnabled(true);
            ContentCatalogStatus contentCatalogStatus3 = ContentCatalogStatus.HAWKFIRE;
            ContentCatalogStatus contentCatalogStatusUpdateMaxStatus = ContentCatalogStatus.updateMaxStatus(isHawkfire, contentCatalogStatus, contentCatalogStatus3);
            ContentCatalogStatus contentCatalogStatusUpdateMinStatus = ContentCatalogStatus.updateMinStatus(isHawkfire, contentCatalogStatus2, contentCatalogStatus3);
            ContentCatalogStatus contentCatalogStatus4 = ContentCatalogStatus.SONIC_RUSH;
            ContentCatalogStatus contentCatalogStatusUpdateMaxStatus2 = ContentCatalogStatus.updateMaxStatus(z2, contentCatalogStatusUpdateMaxStatus, contentCatalogStatus4);
            ContentCatalogStatus contentCatalogStatusUpdateMinStatus2 = ContentCatalogStatus.updateMinStatus(z2, contentCatalogStatusUpdateMinStatus, contentCatalogStatus4);
            ContentCatalogStatus contentCatalogStatus5 = ContentCatalogStatus.PRIME;
            ContentCatalogStatus contentCatalogStatusUpdateMaxStatus3 = ContentCatalogStatus.updateMaxStatus(isPrime, contentCatalogStatusUpdateMaxStatus2, contentCatalogStatus5);
            ContentCatalogStatus contentCatalogStatusUpdateMinStatus3 = ContentCatalogStatus.updateMinStatus(isPrime, contentCatalogStatusUpdateMinStatus2, contentCatalogStatus5);
            boolean z3 = isFree && isVideo;
            ContentCatalogStatus contentCatalogStatus6 = ContentCatalogStatus.BOP;
            ContentCatalogStatus contentCatalogStatusUpdateMaxStatus4 = ContentCatalogStatus.updateMaxStatus(z3, contentCatalogStatusUpdateMaxStatus3, contentCatalogStatus6);
            if (isFree && isVideo) {
                z = true;
            }
            ContentCatalogStatus contentCatalogStatusUpdateMinStatus4 = ContentCatalogStatus.updateMinStatus(z, contentCatalogStatusUpdateMinStatus3, contentCatalogStatus6);
            return useMaxStatus ? contentCatalogStatusUpdateMaxStatus4 : contentCatalogStatusUpdateMinStatus4 != contentCatalogStatus2 ? contentCatalogStatusUpdateMinStatus4 : contentCatalogStatus;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ContentCatalogStatus updateMaxStatus(boolean flag, ContentCatalogStatus current, ContentCatalogStatus target) {
        return (!flag || target.getValue() <= current.getValue()) ? current : target;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ContentCatalogStatus updateMinStatus(boolean flag, ContentCatalogStatus current, ContentCatalogStatus target) {
        return (!flag || target.getValue() >= current.getValue()) ? current : target;
    }

    public Set<ContentAccessType> getSupportedTiers() {
        if (isPreviouslyCatalog() || isUnknown()) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        if (isOwned()) {
            hashSet.add(ContentAccessType.OWNED);
        }
        if (isHawkfire()) {
            hashSet.add(ContentAccessType.HAWKFIRE);
        }
        if (isPrime()) {
            hashSet.add(ContentAccessType.PRIME);
        }
        if (isSonicRush()) {
            hashSet.add(ContentAccessType.SONIC_RUSH);
        }
        if (isBOP()) {
            hashSet.add(ContentAccessType.NIGHTWING);
            hashSet.add(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE);
        }
        return hashSet;
    }
}
