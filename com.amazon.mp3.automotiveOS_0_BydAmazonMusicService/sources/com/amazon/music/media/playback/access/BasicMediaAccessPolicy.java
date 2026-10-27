package com.amazon.music.media.playback.access;

import androidx.annotation.NonNull;
import com.amazon.music.ContentAccessType;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class BasicMediaAccessPolicy implements MediaAccessPolicy {
    private final boolean allowExplicit;
    private final String contentSubscriptionMode;
    private final Set<ContentAccessType> ghostListenTypes;
    private final boolean isConnectedToChromecast;
    private final Set<ContentAccessType> playTypes;
    private final String subContentSubscriptionMode;

    public BasicMediaAccessPolicy(@NonNull String str, String str2, boolean z, @NonNull Collection<ContentAccessType> collection, @NonNull Collection<ContentAccessType> collection2, @NonNull boolean z2) {
        this.contentSubscriptionMode = (String) Validate.notNull(str);
        this.subContentSubscriptionMode = str2;
        this.allowExplicit = z;
        EnumSet enumSetNoneOf = EnumSet.noneOf(ContentAccessType.class);
        this.playTypes = enumSetNoneOf;
        enumSetNoneOf.addAll(collection);
        EnumSet enumSetNoneOf2 = EnumSet.noneOf(ContentAccessType.class);
        this.ghostListenTypes = enumSetNoneOf2;
        enumSetNoneOf2.addAll(collection2);
        this.isConnectedToChromecast = z2;
    }

    @Override // com.amazon.music.media.playback.access.MediaAccessPolicy
    public String getContentSubscriptionMode() {
        return this.contentSubscriptionMode;
    }

    @Override // com.amazon.music.media.playback.access.MediaAccessPolicy
    public String getSubContentSubscriptionMode() {
        return this.subContentSubscriptionMode;
    }

    @Override // com.amazon.music.media.playback.access.MediaAccessPolicy
    public MediaAccess getMediaAccess(MediaAccessInfo mediaAccessInfo) {
        if (this.isConnectedToChromecast && !mediaAccessInfo.isCastable()) {
            return BasicMediaAccess.NONE;
        }
        if (!this.allowExplicit && mediaAccessInfo.isExplicit()) {
            return BasicMediaAccess.NONE;
        }
        if (!intersects(this.playTypes, mediaAccessInfo.getContentAccessTypes())) {
            return BasicMediaAccess.NONE;
        }
        if (!mediaAccessInfo.isAvailableOffline() && !mediaAccessInfo.isAllowedOnline() && !this.isConnectedToChromecast) {
            return BasicMediaAccess.NONE;
        }
        if (intersects(this.ghostListenTypes, mediaAccessInfo.getContentAccessTypes())) {
            return BasicMediaAccess.CAN_PLAY_FOREVER;
        }
        return BasicMediaAccess.CAN_PLAY;
    }

    @Override // com.amazon.music.media.playback.access.MediaAccessPolicy
    public boolean isExplicitAllowed() {
        return this.allowExplicit;
    }

    private boolean intersects(Set<?> set, Set<?> set2) {
        Iterator<?> it = set.iterator();
        while (it.hasNext()) {
            if (set2.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BasicMediaAccessPolicy basicMediaAccessPolicy = (BasicMediaAccessPolicy) obj;
        if (this.allowExplicit == basicMediaAccessPolicy.allowExplicit && this.contentSubscriptionMode == basicMediaAccessPolicy.contentSubscriptionMode && this.playTypes.equals(basicMediaAccessPolicy.playTypes) && this.isConnectedToChromecast == basicMediaAccessPolicy.isConnectedToChromecast) {
            return this.ghostListenTypes.equals(basicMediaAccessPolicy.ghostListenTypes);
        }
        return false;
    }

    public int hashCode() {
        return (((this.contentSubscriptionMode.hashCode() * 31) + (this.allowExplicit ? 1 : 0)) * 41) + (this.isConnectedToChromecast ? 1 : 0);
    }

    public String toString() {
        return "BasicMediaAccessPolicy{allowExplicit=" + this.allowExplicit + ", contentSubscriptionMode=" + this.contentSubscriptionMode + ", subContentSubscriptionMode=" + this.subContentSubscriptionMode + ", playTypes=" + this.playTypes + ", ghostListenTypes=" + this.ghostListenTypes + ", isConnectedToChromecast=" + this.isConnectedToChromecast + '}';
    }
}
