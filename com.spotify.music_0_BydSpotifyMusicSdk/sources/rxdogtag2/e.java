package rxdogtag2;

import rxdogtag2.RxDogTag;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements RxDogTag.NonCheckingConsumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ DogTagSingleObserver b;

    public /* synthetic */ e(DogTagSingleObserver dogTagSingleObserver, int i) {
        this.a = i;
        this.b = dogTagSingleObserver;
    }

    @Override // rxdogtag2.RxDogTag.NonCheckingConsumer
    public final void accept(Object obj) {
        int i = this.a;
        DogTagSingleObserver dogTagSingleObserver = this.b;
        Throwable th = (Throwable) obj;
        switch (i) {
            case 0:
                dogTagSingleObserver.lambda$onSuccess$2(th);
                break;
            case 1:
                dogTagSingleObserver.lambda$onError$4(th);
                break;
            default:
                dogTagSingleObserver.lambda$onSubscribe$0(th);
                break;
        }
    }
}
