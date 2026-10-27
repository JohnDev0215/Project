package com.amazon.music.voice.ui.scrim;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes4.dex */
public final class ScrimPresenter implements ScrimContract$Presenter {
    private final CompositeSubscription composite = new CompositeSubscription();
    private final ScrimContract$Interactor interactor;
    private final ScrimContract$View view;

    public ScrimPresenter(ScrimContract$View scrimContract$View, ScrimContract$Interactor scrimContract$Interactor) {
        this.view = scrimContract$View;
        this.interactor = scrimContract$Interactor;
    }

    @Override // com.amazon.music.voice.ui.scrim.ScrimContract$Presenter
    public void start() {
        this.composite.add(this.interactor.getSoundLevel().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Float>() { // from class: com.amazon.music.voice.ui.scrim.ScrimPresenter.1
            @Override // rx.functions.Action1
            public void call(Float f) {
                ScrimPresenter.this.view.setChromeLevel(f.floatValue());
            }
        }));
    }

    @Override // com.amazon.music.voice.ui.scrim.ScrimContract$Presenter
    public void stop() {
        this.composite.unsubscribe();
    }
}
