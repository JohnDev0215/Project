package com.amazon.avod.media.guice;

import com.amazon.avod.media.playback.FirstMatchVideoPresentationFactory;
import com.amazon.avod.media.playback.VideoPresentationFactory;
import dagger.internal.Factory;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvideVideoPresentationFactoryFactory implements Factory<VideoPresentationFactory> {
    public static VideoPresentationFactory proxyProvideVideoPresentationFactory(MediaModule_Dagger mediaModule_Dagger, FirstMatchVideoPresentationFactory firstMatchVideoPresentationFactory) {
        return mediaModule_Dagger.provideVideoPresentationFactory(firstMatchVideoPresentationFactory);
    }
}
