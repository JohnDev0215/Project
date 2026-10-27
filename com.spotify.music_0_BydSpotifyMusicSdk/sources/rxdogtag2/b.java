package rxdogtag2;

import io.reactivex.rxjava3.disposables.Disposable;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ io.reactivex.rxjava3.observers.b e;
    public final /* synthetic */ Object k;

    public /* synthetic */ b(io.reactivex.rxjava3.observers.b bVar, Object obj, int i) {
        this.c = i;
        this.e = bVar;
        this.k = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        Object obj = this.k;
        io.reactivex.rxjava3.observers.b bVar = this.e;
        switch (i) {
            case 0:
                ((DogTagCompletableObserver) bVar).lambda$onError$3((Throwable) obj);
                break;
            case 1:
                ((DogTagCompletableObserver) bVar).lambda$onSubscribe$1((Disposable) obj);
                break;
            case 2:
                ((DogTagMaybeObserver) bVar).lambda$onSubscribe$1((Disposable) obj);
                break;
            case 3:
                ((DogTagMaybeObserver) bVar).lambda$onError$5((Throwable) obj);
                break;
            case 4:
                ((DogTagMaybeObserver) bVar).lambda$onSuccess$3(obj);
                break;
            case 5:
                ((DogTagObserver) bVar).lambda$onError$5((Throwable) obj);
                break;
            case 6:
                ((DogTagObserver) bVar).lambda$onSubscribe$1((Disposable) obj);
                break;
            case 7:
                ((DogTagObserver) bVar).lambda$onNext$3(obj);
                break;
            case 8:
                ((DogTagSingleObserver) bVar).lambda$onSuccess$3(obj);
                break;
            case 9:
                ((DogTagSingleObserver) bVar).lambda$onError$5((Throwable) obj);
                break;
            case 10:
                ((DogTagSingleObserver) bVar).lambda$onSubscribe$1((Disposable) obj);
                break;
            case 11:
                ((DogTagSubscriber) bVar).lambda$onNext$3(obj);
                break;
            case 12:
                ((DogTagSubscriber) bVar).lambda$onSubscribe$1((tg2) obj);
                break;
            default:
                ((DogTagSubscriber) bVar).lambda$onError$5((Throwable) obj);
                break;
        }
    }
}
