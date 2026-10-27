package com.amazon.mp3.alps.dagger;

import com.amazon.music.views.library.styles.StyleSheet;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import rx.Observable;

/* JADX INFO: loaded from: classes3.dex */
public final class ALPSModule_ProvideStyleSheetFactory implements Factory<Observable<StyleSheet>> {
    private final ALPSModule module;

    public ALPSModule_ProvideStyleSheetFactory(ALPSModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public Observable<StyleSheet> get() {
        return provideStyleSheet(this.module);
    }

    public static ALPSModule_ProvideStyleSheetFactory create(ALPSModule module) {
        return new ALPSModule_ProvideStyleSheetFactory(module);
    }

    public static Observable<StyleSheet> provideStyleSheet(ALPSModule instance) {
        return (Observable) Preconditions.checkNotNullFromProvides(instance.provideStyleSheet());
    }
}
