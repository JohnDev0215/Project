package com.amazon.music.media.playback.access;

import com.amazon.music.ContentAccessType;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class MediaAccessInfo {
    private final boolean allowedOnline;
    private final boolean availableOffline;
    private final boolean castable;
    private final EnumSet<ContentAccessType> contentAccessTypes;
    private final boolean explicit;
    private static final Map<MediaAccessInfo, MediaAccessInfo> CANONICAL_MAP = new HashMap();
    private static final MediaAccessInfo FULL_ACCESS = forContentAccessTypes(false, true, true, true, EnumSet.allOf(ContentAccessType.class));
    private static final MediaAccessInfo FULL_OFFLINE_ACCESS = forContentAccessTypes(false, true, true, false, EnumSet.allOf(ContentAccessType.class));
    private static final MediaAccessInfo FULL_ONLINE_ACCESS = forContentAccessTypes(false, true, false, true, EnumSet.allOf(ContentAccessType.class));
    private static final MediaAccessInfo FULL_CASTING_ONLY_ACCESS = forContentAccessTypes(false, true, false, false, EnumSet.allOf(ContentAccessType.class));

    public static final MediaAccessInfo fullAccess(boolean z, boolean z2) {
        if (z && z2) {
            return FULL_ACCESS;
        }
        if (z) {
            return FULL_OFFLINE_ACCESS;
        }
        if (z2) {
            return FULL_ONLINE_ACCESS;
        }
        return FULL_CASTING_ONLY_ACCESS;
    }

    public static MediaAccessInfo forEligibility(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9) {
        HashSet hashSet = new HashSet(5);
        if (z5) {
            hashSet.add(ContentAccessType.OWNED);
        }
        if (z6) {
            hashSet.add(ContentAccessType.PRIME);
        }
        if (z7) {
            hashSet.add(ContentAccessType.SONIC_RUSH);
        }
        if (z8) {
            hashSet.add(ContentAccessType.HAWKFIRE);
        }
        if (z9) {
            hashSet.add(ContentAccessType.BOP);
        }
        return new MediaAccessInfo(z, z2, z3, z4, hashSet).canonicalize();
    }

    public static MediaAccessInfo forContentAccessTypes(boolean z, boolean z2, boolean z3, boolean z4, Iterable<? extends ContentAccessType> iterable) {
        return new MediaAccessInfo(z, z2, z3, z4, iterable).canonicalize();
    }

    private MediaAccessInfo(boolean z, boolean z2, boolean z3, boolean z4, Iterable<? extends ContentAccessType> iterable) {
        this.explicit = z;
        this.castable = z2;
        this.availableOffline = z3;
        this.allowedOnline = z4;
        this.contentAccessTypes = EnumSet.noneOf(ContentAccessType.class);
        if (iterable != null) {
            Iterator<? extends ContentAccessType> it = iterable.iterator();
            while (it.hasNext()) {
                this.contentAccessTypes.add(it.next());
            }
        }
    }

    private MediaAccessInfo canonicalize() {
        Map<MediaAccessInfo, MediaAccessInfo> map = CANONICAL_MAP;
        synchronized (map) {
            MediaAccessInfo mediaAccessInfo = map.get(this);
            if (mediaAccessInfo != null) {
                return mediaAccessInfo;
            }
            map.put(this, this);
            return this;
        }
    }

    public Set<ContentAccessType> getContentAccessTypes() {
        return this.contentAccessTypes;
    }

    public boolean isExplicit() {
        return this.explicit;
    }

    public boolean isCastable() {
        return this.castable;
    }

    public boolean isAvailableOffline() {
        return this.availableOffline;
    }

    public boolean isAllowedOnline() {
        return this.allowedOnline;
    }

    public boolean isOwned() {
        return this.contentAccessTypes.contains(ContentAccessType.OWNED);
    }

    public boolean isPrime() {
        return this.contentAccessTypes.contains(ContentAccessType.PRIME);
    }

    public boolean isSonicRush() {
        return this.contentAccessTypes.contains(ContentAccessType.SONIC_RUSH);
    }

    public boolean isSonicRushOnDemandPlayable() {
        return this.contentAccessTypes.contains(ContentAccessType.SONIC_RUSH_ONDEMAND_PLAYABLE);
    }

    public boolean isSonicRushGolden() {
        return this.contentAccessTypes.contains(ContentAccessType.SONIC_RUSH_GOLDEN);
    }

    public boolean isHawkfire() {
        return this.contentAccessTypes.contains(ContentAccessType.HAWKFIRE);
    }

    public boolean isFree() {
        return this.contentAccessTypes.contains(ContentAccessType.NIGHTWING);
    }

    public boolean isBOP() {
        return this.contentAccessTypes.contains(ContentAccessType.BOP);
    }

    public boolean isFreeOnDemandPlayable() {
        return this.contentAccessTypes.contains(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaAccessInfo mediaAccessInfo = (MediaAccessInfo) obj;
        if (this.explicit == mediaAccessInfo.explicit && this.castable == mediaAccessInfo.castable && this.availableOffline == mediaAccessInfo.availableOffline && this.allowedOnline == mediaAccessInfo.allowedOnline) {
            return this.contentAccessTypes.equals(mediaAccessInfo.contentAccessTypes);
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.contentAccessTypes.hashCode() * 31) + (this.explicit ? 1 : 0)) * 41) + (this.castable ? 1 : 0)) * 41) + (this.availableOffline ? 1 : 0)) * 41) + (this.allowedOnline ? 1 : 0);
    }

    public String toString() {
        return "MediaAccessInfo{contentAccessTypes=" + this.contentAccessTypes + ", explicit=" + this.explicit + ", castable=" + this.castable + ", availableOffline=" + this.availableOffline + ", allowedOnline=" + this.allowedOnline + '}';
    }

    public static class Builder {
        private boolean allowedOnline;
        private boolean availableOffline;
        private boolean castable;
        private final Set<ContentAccessType> contentAccessTypes;
        private boolean explicit;

        public Builder() {
            this.contentAccessTypes = EnumSet.noneOf(ContentAccessType.class);
        }

        public Builder(MediaAccessInfo mediaAccessInfo) {
            if (mediaAccessInfo != null) {
                this.explicit = mediaAccessInfo.explicit;
                this.castable = mediaAccessInfo.castable;
                this.availableOffline = mediaAccessInfo.availableOffline;
                this.allowedOnline = mediaAccessInfo.allowedOnline;
                this.contentAccessTypes = mediaAccessInfo.contentAccessTypes.clone();
                return;
            }
            this.contentAccessTypes = EnumSet.noneOf(ContentAccessType.class);
        }

        public Builder withExplicit(boolean z) {
            this.explicit = z;
            return this;
        }

        public Builder withCastable(boolean z) {
            this.castable = z;
            return this;
        }

        public Builder withAvailableOffline(boolean z) {
            this.availableOffline = z;
            return this;
        }

        public Builder withAllowedOnline(boolean z) {
            this.allowedOnline = z;
            return this;
        }

        public Builder withContentAccessType(ContentAccessType contentAccessType) {
            this.contentAccessTypes.add(contentAccessType);
            return this;
        }

        public MediaAccessInfo build() {
            return new MediaAccessInfo(this.explicit, this.castable, this.availableOffline, this.allowedOnline, this.contentAccessTypes);
        }
    }
}
