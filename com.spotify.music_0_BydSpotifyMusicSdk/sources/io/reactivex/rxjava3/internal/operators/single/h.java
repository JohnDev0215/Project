package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import p.he1;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public final class h extends Single {
    public final /* synthetic */ int c;
    public final Object e;
    public final Object k;

    public /* synthetic */ h(Object obj, int i, Object obj2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        int length;
        Disposable disposable = EmptyDisposable.c;
        int i = this.c;
        int i2 = 0;
        Object obj = this.e;
        Object obj2 = this.k;
        int i3 = 1;
        switch (i) {
            case 0:
                ((CompletableSource) obj2).subscribe(new SingleDelayWithCompletable$OtherObserver(singleObserver, (SingleSource) obj));
                break;
            case 1:
                ((ObservableSource) obj2).subscribe(new SingleDelayWithObservable$OtherSubscriber(singleObserver, (SingleSource) obj));
                break;
            case 2:
                SingleSource[] singleSourceArr = (SingleSource[]) obj;
                int length2 = singleSourceArr.length;
                if (length2 == 1) {
                    singleSourceArr[0].subscribe(new c(singleObserver, new o0(this, i2)));
                } else {
                    SingleZipArray$ZipCoordinator singleZipArray$ZipCoordinator = new SingleZipArray$ZipCoordinator(singleObserver, length2, (io.reactivex.rxjava3.functions.o) obj2);
                    singleObserver.onSubscribe(singleZipArray$ZipCoordinator);
                    while (i2 < length2 && !singleZipArray$ZipCoordinator.isDisposed()) {
                        SingleSource singleSource = singleSourceArr[i2];
                        if (singleSource == null) {
                            singleZipArray$ZipCoordinator.a(i2, new NullPointerException("One of the sources is null"));
                        } else {
                            singleSource.subscribe(singleZipArray$ZipCoordinator.observers[i2]);
                            i2++;
                        }
                    }
                }
                break;
            case 3:
                SingleSource[] singleSourceArr2 = new SingleSource[8];
                try {
                    int i4 = 0;
                    for (SingleSource singleSource2 : (Iterable) obj) {
                        if (singleSource2 == null) {
                            Throwable nullPointerException = new NullPointerException("One of the sources is null");
                            singleObserver.onSubscribe(disposable);
                            singleObserver.onError(nullPointerException);
                        } else {
                            if (i4 == singleSourceArr2.length) {
                                singleSourceArr2 = (SingleSource[]) Arrays.copyOf(singleSourceArr2, (i4 >> 2) + i4);
                            }
                            int i5 = i4 + 1;
                            singleSourceArr2[i4] = singleSource2;
                            i4 = i5;
                        }
                        break;
                    }
                    if (i4 == 0) {
                        Throwable noSuchElementException = new NoSuchElementException();
                        singleObserver.onSubscribe(disposable);
                        singleObserver.onError(noSuchElementException);
                    } else if (i4 == 1) {
                        singleSourceArr2[0].subscribe(new c(singleObserver, new o0(this, i3)));
                    } else {
                        SingleZipArray$ZipCoordinator singleZipArray$ZipCoordinator2 = new SingleZipArray$ZipCoordinator(singleObserver, i4, (io.reactivex.rxjava3.functions.o) obj2);
                        singleObserver.onSubscribe(singleZipArray$ZipCoordinator2);
                        while (i2 < i4 && !singleZipArray$ZipCoordinator2.isDisposed()) {
                            singleSourceArr2[i2].subscribe(singleZipArray$ZipCoordinator2.observers[i2]);
                            i2++;
                        }
                    }
                } catch (Throwable th) {
                    he1.R(th);
                    singleObserver.onSubscribe(disposable);
                    singleObserver.onError(th);
                    return;
                }
                break;
            case 4:
                SingleSource[] singleSourceArr3 = (SingleSource[]) obj;
                if (singleSourceArr3 == null) {
                    SingleSource[] singleSourceArr4 = new SingleSource[8];
                    try {
                        singleSourceArr3 = singleSourceArr4;
                        length = 0;
                        for (SingleSource singleSource3 : (Iterable) obj2) {
                            if (singleSource3 == null) {
                                Throwable nullPointerException2 = new NullPointerException("One of the sources is null");
                                singleObserver.onSubscribe(disposable);
                                singleObserver.onError(nullPointerException2);
                            } else {
                                if (length == singleSourceArr3.length) {
                                    SingleSource[] singleSourceArr5 = new SingleSource[(length >> 2) + length];
                                    System.arraycopy(singleSourceArr3, 0, singleSourceArr5, 0, length);
                                    singleSourceArr3 = singleSourceArr5;
                                }
                                int i6 = length + 1;
                                singleSourceArr3[length] = singleSource3;
                                length = i6;
                            }
                        }
                    } catch (Throwable th2) {
                        he1.R(th2);
                        singleObserver.onSubscribe(disposable);
                        singleObserver.onError(th2);
                        return;
                    }
                } else {
                    length = singleSourceArr3.length;
                }
                AtomicBoolean atomicBoolean = new AtomicBoolean();
                io.reactivex.rxjava3.disposables.a aVar = new io.reactivex.rxjava3.disposables.a();
                singleObserver.onSubscribe(aVar);
                for (int i7 = 0; i7 < length; i7++) {
                    SingleSource singleSource4 = singleSourceArr3[i7];
                    if (!aVar.isDisposed()) {
                        if (singleSource4 == null) {
                            aVar.dispose();
                            Throwable nullPointerException3 = new NullPointerException("One of the sources is null");
                            if (atomicBoolean.compareAndSet(false, true)) {
                                singleObserver.onError(nullPointerException3);
                            } else {
                                RxJavaPlugins.b(nullPointerException3);
                            }
                        } else {
                            singleSource4.subscribe(new a(singleObserver, aVar, atomicBoolean));
                        }
                    }
                    break;
                }
                break;
            case 5:
                ((SingleSource) obj).subscribe(new c(this, singleObserver, 2));
                break;
            default:
                try {
                    u4.z(obj2);
                    throw null;
                } catch (Throwable th3) {
                    he1.R(th3);
                    singleObserver.onSubscribe(disposable);
                    singleObserver.onError(th3);
                    return;
                }
        }
    }
}
