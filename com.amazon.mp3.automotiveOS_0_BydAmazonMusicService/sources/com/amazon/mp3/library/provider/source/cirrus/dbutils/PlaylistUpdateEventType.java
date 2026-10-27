package com.amazon.mp3.library.provider.source.cirrus.dbutils;

import kotlin.Metadata;

/* JADX INFO: compiled from: PlaylistRepositoryEvents.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/PlaylistUpdateEventType;", "", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEventType;", "(Ljava/lang/String;I)V", "FOLLOWED", "UNFOLLOWED", "PRIVACY_STATUS_CHANGED", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum PlaylistUpdateEventType implements RepositoryEventType {
    FOLLOWED,
    UNFOLLOWED,
    PRIVACY_STATUS_CHANGED
}
