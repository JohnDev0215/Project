package com.amazon.music.metrics.mts.event;

import com.amazon.music.metrics.mts.MTSEvent;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ThrottleDistinctEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\t¢\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/metrics/mts/event/ThrottleDistinctEvent;", "", "Lcom/amazon/music/metrics/mts/MTSEvent;", "mtsEvent", "", "postEvent", "", "throttleThreshold", "J", "Lio/reactivex/rxjava3/functions/Consumer;", "onSuccess", "Lio/reactivex/rxjava3/functions/Consumer;", "Lio/reactivex/rxjava3/subjects/Subject;", "publishSubject", "Lio/reactivex/rxjava3/subjects/Subject;", "Lio/reactivex/rxjava3/disposables/Disposable;", "disposable", "Lio/reactivex/rxjava3/disposables/Disposable;", "<init>", "(JLio/reactivex/rxjava3/functions/Consumer;)V", "DMMMetricsMTSEvents_release"}, k = 1, mv = {1, 6, 0})
public class ThrottleDistinctEvent {
    private Disposable disposable;
    private final Consumer<MTSEvent> onSuccess;
    private final Subject<MTSEvent> publishSubject;
    private final long throttleThreshold;

    public ThrottleDistinctEvent(long j, Consumer<MTSEvent> onSuccess) {
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        this.throttleThreshold = j;
        this.onSuccess = onSuccess;
        PublishSubject publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create()");
        this.publishSubject = publishSubjectCreate;
        this.disposable = publishSubjectCreate.distinctUntilChanged().throttleWithTimeout(j, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(onSuccess);
    }

    public void postEvent(MTSEvent mtsEvent) {
        if (mtsEvent == null) {
            return;
        }
        this.publishSubject.onNext(mtsEvent);
    }
}
