package com.amazon.mp3.curate.dagger;

import com.amazon.music.curate.data.PlaylistIntentReceiverService;
import dagger.android.AndroidInjector;

/* JADX INFO: loaded from: classes3.dex */
public interface CurateAndroidComponentsModule_PlaylistIntentReceiverService$PlaylistIntentReceiverServiceSubcomponent extends AndroidInjector<PlaylistIntentReceiverService> {

    public interface Factory extends AndroidInjector.Factory<PlaylistIntentReceiverService> {
        @Override // dagger.android.AndroidInjector.Factory
        /* synthetic */ AndroidInjector<PlaylistIntentReceiverService> create(PlaylistIntentReceiverService playlistIntentReceiverService);
    }
}
