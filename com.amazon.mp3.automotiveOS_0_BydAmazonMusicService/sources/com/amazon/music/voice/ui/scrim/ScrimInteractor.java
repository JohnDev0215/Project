package com.amazon.music.voice.ui.scrim;

import com.amazon.music.voice.ui.internal.util.Properties;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public final class ScrimInteractor implements ScrimContract$Interactor {
    private final ScrimContract$Mediator mediator;
    private final ScrimProvider provider;

    public ScrimInteractor(ScrimProvider scrimProvider, ScrimContract$Mediator scrimContract$Mediator) {
        this.provider = scrimProvider;
        this.mediator = scrimContract$Mediator;
    }

    @Override // com.amazon.music.voice.ui.scrim.ScrimContract$Interactor
    public Observable<Float> getSoundLevel() {
        return Properties.toObservable(this.provider.soundLevel);
    }
}
