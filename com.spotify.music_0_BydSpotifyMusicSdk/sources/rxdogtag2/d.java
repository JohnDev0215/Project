package rxdogtag2;

import rxdogtag2.RxDogTag;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d implements RxDogTag.NonCheckingConsumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ DogTagObserver b;

    public /* synthetic */ d(DogTagObserver dogTagObserver, int i) {
        this.a = i;
        this.b = dogTagObserver;
    }

    @Override // rxdogtag2.RxDogTag.NonCheckingConsumer
    public final void accept(Object obj) {
        int i = this.a;
        DogTagObserver dogTagObserver = this.b;
        Throwable th = (Throwable) obj;
        switch (i) {
            case 0:
                dogTagObserver.lambda$onError$4(th);
                break;
            case 1:
                dogTagObserver.lambda$onComplete$6(th);
                break;
            case 2:
                dogTagObserver.lambda$onSubscribe$0(th);
                break;
            default:
                dogTagObserver.lambda$onNext$2(th);
                break;
        }
    }
}
