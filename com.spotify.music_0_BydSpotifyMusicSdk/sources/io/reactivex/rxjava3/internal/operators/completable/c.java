package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class c extends Completable {
    public final /* synthetic */ int c;
    public final CompletableSource[] e;

    public /* synthetic */ c(CompletableSource[] completableSourceArr, int i) {
        this.c = i;
        this.e = completableSourceArr;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public final void d(CompletableObserver completableObserver) {
        int i = this.c;
        int i2 = 0;
        CompletableSource[] completableSourceArr = this.e;
        switch (i) {
            case 0:
                CompletableConcatArray$ConcatInnerObserver completableConcatArray$ConcatInnerObserver = new CompletableConcatArray$ConcatInnerObserver(completableObserver, completableSourceArr);
                completableObserver.onSubscribe(completableConcatArray$ConcatInnerObserver.sd);
                completableConcatArray$ConcatInnerObserver.a();
                break;
            case 1:
                io.reactivex.rxjava3.disposables.a aVar = new io.reactivex.rxjava3.disposables.a();
                CompletableMergeArray$InnerCompletableObserver completableMergeArray$InnerCompletableObserver = new CompletableMergeArray$InnerCompletableObserver(completableObserver, new AtomicBoolean(), aVar, completableSourceArr.length + 1);
                completableObserver.onSubscribe(completableMergeArray$InnerCompletableObserver);
                int length = completableSourceArr.length;
                while (i2 < length) {
                    CompletableSource completableSource = completableSourceArr[i2];
                    if (!aVar.isDisposed()) {
                        if (completableSource == null) {
                            aVar.dispose();
                            completableMergeArray$InnerCompletableObserver.onError(new NullPointerException("A completable source is null"));
                        } else {
                            completableSource.subscribe(completableMergeArray$InnerCompletableObserver);
                            i2++;
                        }
                    }
                    break;
                }
                completableMergeArray$InnerCompletableObserver.onComplete();
                break;
            default:
                io.reactivex.rxjava3.disposables.a aVar2 = new io.reactivex.rxjava3.disposables.a();
                AtomicInteger atomicInteger = new AtomicInteger(completableSourceArr.length + 1);
                AtomicThrowable atomicThrowable = new AtomicThrowable();
                aVar2.c(new i(atomicThrowable));
                completableObserver.onSubscribe(aVar2);
                int length2 = completableSourceArr.length;
                while (i2 < length2) {
                    CompletableSource completableSource2 = completableSourceArr[i2];
                    if (!aVar2.isDisposed()) {
                        if (completableSource2 == null) {
                            atomicThrowable.a(new NullPointerException("A completable source is null"));
                            atomicInteger.decrementAndGet();
                        } else {
                            completableSource2.subscribe(new h(completableObserver, aVar2, atomicThrowable, atomicInteger));
                        }
                        i2++;
                    }
                    break;
                }
                if (atomicInteger.decrementAndGet() == 0) {
                    atomicThrowable.c(completableObserver);
                }
                break;
        }
    }
}
