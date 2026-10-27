package com.amazon.music.media.playback.access;

/* JADX INFO: loaded from: classes4.dex */
public interface MediaAccessPolicy {
    String getContentSubscriptionMode();

    MediaAccess getMediaAccess(MediaAccessInfo mediaAccessInfo);

    String getSubContentSubscriptionMode();

    boolean isExplicitAllowed();
}
