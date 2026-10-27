package com.amazon.mp3.init;

import androidx.annotation.NonNull;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class InitializationHandler {
    private final InitializationTask mInitializationTask;
    private final Action1<Void> mOnNextAction;
    private static final ConcurrentMap<String, Boolean> TASK_STARTED_MAP = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Boolean> TASK_COMPLETED_MAP = new ConcurrentHashMap();

    public InitializationHandler(@NonNull InitializationTask initializationTask) {
        this.mInitializationTask = initializationTask;
        this.mOnNextAction = initializationTask.getOnNextAction();
    }

    public void initializeOnNewThread() {
        Observable observableCache = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.init.InitializationHandler$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$initializeOnNewThread$0((Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).cache();
        Action1<Void> action1 = this.mOnNextAction;
        if (action1 != null) {
            observableCache.subscribe(action1);
        } else {
            observableCache.subscribe();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeOnNewThread$0(Subscriber subscriber) {
        if (Boolean.TRUE.equals(TASK_COMPLETED_MAP.get(this.mInitializationTask.getName()))) {
            subscriber.onNext(null);
            subscriber.onCompleted();
        } else {
            initialize();
            subscriber.onNext(null);
            subscriber.onCompleted();
        }
    }

    private void initialize() {
        ConcurrentMap<String, Boolean> concurrentMap = TASK_STARTED_MAP;
        synchronized (concurrentMap) {
            Boolean bool = Boolean.TRUE;
            if (bool.equals(concurrentMap.get(this.mInitializationTask.getName()))) {
                return;
            }
            concurrentMap.put(this.mInitializationTask.getName(), bool);
            this.mInitializationTask.initialize();
            TASK_COMPLETED_MAP.put(this.mInitializationTask.getName(), bool);
        }
    }
}
