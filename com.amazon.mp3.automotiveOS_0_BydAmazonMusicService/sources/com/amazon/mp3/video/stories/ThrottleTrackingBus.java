package com.amazon.mp3.video.stories;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/* JADX INFO: compiled from: ThrottleTrackingBus.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00172\u00020\u0001:\u0002\u0017\u0018B)\u0012\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0007\u0012\u0010\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0007¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0006\u0010\u0006\u001a\u00020\u0004R\u001f\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR!\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\r\u0010\t\u001a\u0004\b\u000e\u0010\u000bR \u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/video/stories/ThrottleTrackingBus;", "", "Lcom/amazon/mp3/video/stories/ThrottleTrackingBus$VisibleState;", "visibleState", "", "postViewEvent", "unsubscribe", "Lrx/functions/Action1;", "onSuccess", "Lrx/functions/Action1;", "getOnSuccess", "()Lrx/functions/Action1;", "", "onError", "getOnError", "Lrx/subjects/Subject;", "publishSubject", "Lrx/subjects/Subject;", "Lrx/Subscription;", "subscription", "Lrx/Subscription;", "<init>", "(Lrx/functions/Action1;Lrx/functions/Action1;)V", "Companion", "VisibleState", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ThrottleTrackingBus {
    private final Action1<Throwable> onError;
    private final Action1<VisibleState> onSuccess;
    private final Subject<VisibleState, VisibleState> publishSubject;
    private Subscription subscription;

    public ThrottleTrackingBus(Action1<VisibleState> onSuccess, Action1<Throwable> action1) {
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        this.onSuccess = onSuccess;
        this.onError = action1;
        PublishSubject publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create()");
        this.publishSubject = publishSubjectCreate;
        this.subscription = publishSubjectCreate.distinctUntilChanged().throttleWithTimeout(250L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onSuccess, action1);
    }

    public final void postViewEvent(VisibleState visibleState) {
        this.publishSubject.onNext(visibleState);
    }

    public final void unsubscribe() {
        Subscription subscription = this.subscription;
        if (subscription == null) {
            return;
        }
        subscription.unsubscribe();
    }

    /* JADX INFO: compiled from: ThrottleTrackingBus.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/video/stories/ThrottleTrackingBus$VisibleState;", "", "firstCompletelyVisible", "", "lastCompletelyVisible", "impressionTime", "", "(IILjava/lang/String;)V", "getFirstCompletelyVisible", "()I", "getImpressionTime", "()Ljava/lang/String;", "getLastCompletelyVisible", "equals", "", "o", "hashCode", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class VisibleState {
        private final int firstCompletelyVisible;
        private final String impressionTime;
        private final int lastCompletelyVisible;

        public VisibleState(int i, int i2, String str) {
            this.firstCompletelyVisible = i;
            this.lastCompletelyVisible = i2;
            this.impressionTime = str;
        }

        public /* synthetic */ VisibleState(int i, int i2, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, (i3 & 4) != 0 ? null : str);
        }

        public final int getFirstCompletelyVisible() {
            return this.firstCompletelyVisible;
        }

        public final String getImpressionTime() {
            return this.impressionTime;
        }

        public final int getLastCompletelyVisible() {
            return this.lastCompletelyVisible;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || !Intrinsics.areEqual(VisibleState.class, o.getClass())) {
                return false;
            }
            VisibleState visibleState = (VisibleState) o;
            return this.firstCompletelyVisible == visibleState.firstCompletelyVisible && this.lastCompletelyVisible == visibleState.lastCompletelyVisible;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.firstCompletelyVisible), Integer.valueOf(this.lastCompletelyVisible), this.impressionTime);
        }
    }
}
