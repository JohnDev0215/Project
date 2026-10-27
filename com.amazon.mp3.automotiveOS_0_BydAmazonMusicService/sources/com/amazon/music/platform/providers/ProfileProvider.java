package com.amazon.music.platform.providers;

import com.amazon.music.platform.data.PCAProfile;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: ProfileProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0004H&J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H¦@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\n\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\t*\u00020\bH&¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lcom/amazon/music/platform/providers/ProfileProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "Lcom/amazon/music/platform/data/PCAProfile;", "currentProfile", "", "getAccessTokenBlocking", "getAccessToken", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "T", "getProfileSwitchNotificationPublisher", "()Ljava/lang/Object;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface ProfileProvider extends InterfaceProvider {
    PCAProfile currentProfile();

    Object getAccessToken(Continuation<? super String> continuation);

    String getAccessTokenBlocking();

    <T> T getProfileSwitchNotificationPublisher();
}
