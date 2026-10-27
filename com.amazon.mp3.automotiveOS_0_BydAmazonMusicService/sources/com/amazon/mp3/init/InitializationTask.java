package com.amazon.mp3.init;

import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public interface InitializationTask {
    String getName();

    Action1 getOnNextAction();

    void initialize();
}
