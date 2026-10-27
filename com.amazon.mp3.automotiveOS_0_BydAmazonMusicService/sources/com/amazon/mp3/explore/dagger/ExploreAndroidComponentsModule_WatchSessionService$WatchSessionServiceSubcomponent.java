package com.amazon.mp3.explore.dagger;

import com.amazon.mp3.watch.WatchSessionService;
import dagger.android.AndroidInjector;

/* JADX INFO: loaded from: classes3.dex */
public interface ExploreAndroidComponentsModule_WatchSessionService$WatchSessionServiceSubcomponent extends AndroidInjector<WatchSessionService> {

    public interface Factory extends AndroidInjector.Factory<WatchSessionService> {
        @Override // dagger.android.AndroidInjector.Factory
        /* synthetic */ AndroidInjector<WatchSessionService> create(WatchSessionService watchSessionService);
    }
}
