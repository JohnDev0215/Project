package com.amazon.mp3.util.extensions;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: Rx.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u001an\u0010\r\u001a\u00020\t\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00020\u00032!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u00052%\b\u0002\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0005H\u0007¨\u0006\u000e"}, d2 = {"", "T", "Lio/reactivex/rxjava3/core/Observable;", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "compositeDisposable", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "t", "", "onSuccess", "", "onError", "subscribe", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0})
public final class RxKt {
    @JvmOverloads
    public static final <T> void subscribe(Observable<T> observable, final CompositeDisposable compositeDisposable, final Function1<? super T, Unit> onSuccess, final Function1<? super Throwable, Unit> function1) {
        Disposable disposable;
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(compositeDisposable, "compositeDisposable");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Observable<T> observableDoAfterTerminate = observable.doAfterTerminate(new Action() { // from class: com.amazon.mp3.util.extensions.RxKt$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                RxKt.m1662subscribe$lambda0(compositeDisposable, objectRef);
            }
        });
        Consumer<? super T> consumer = new Consumer() { // from class: com.amazon.mp3.util.extensions.RxKt$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                RxKt.m1663subscribe$lambda1(onSuccess, obj);
            }
        };
        if (function1 == null) {
            function1 = new Function1<Throwable, Unit>() { // from class: com.amazon.mp3.util.extensions.RxKt.subscribe.2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }
            };
        }
        T t = (T) observableDoAfterTerminate.subscribe(consumer, new Consumer() { // from class: com.amazon.mp3.util.extensions.RxKt$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                RxKt.m1664subscribe$lambda2(function1, (Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(t, "doAfterTerminate {\n     …onSuccess, onError ?: {})");
        objectRef.element = t;
        if (compositeDisposable.isDisposed()) {
            return;
        }
        T t2 = objectRef.element;
        if (t2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("disposable");
            disposable = null;
        } else {
            disposable = (Disposable) t2;
        }
        compositeDisposable.add(disposable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribe$lambda-0, reason: not valid java name */
    public static final void m1662subscribe$lambda0(CompositeDisposable compositeDisposable, Ref.ObjectRef disposable) {
        Disposable disposable2;
        Intrinsics.checkNotNullParameter(compositeDisposable, "$compositeDisposable");
        Intrinsics.checkNotNullParameter(disposable, "$disposable");
        if (compositeDisposable.isDisposed()) {
            return;
        }
        T t = disposable.element;
        if (t == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("disposable");
            disposable2 = null;
        } else {
            disposable2 = (Disposable) t;
        }
        compositeDisposable.remove(disposable2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribe$lambda-1, reason: not valid java name */
    public static final void m1663subscribe$lambda1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribe$lambda-2, reason: not valid java name */
    public static final void m1664subscribe$lambda2(Function1 tmp0, Throwable th) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(th);
    }
}
