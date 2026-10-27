package rxdogtag2;

import rxdogtag2.RxDogTag;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements RxDogTag.NonCheckingConsumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ DogTagMaybeObserver b;

    public /* synthetic */ c(DogTagMaybeObserver dogTagMaybeObserver, int i) {
        this.a = i;
        this.b = dogTagMaybeObserver;
    }

    @Override // rxdogtag2.RxDogTag.NonCheckingConsumer
    public final void accept(Object obj) {
        int i = this.a;
        DogTagMaybeObserver dogTagMaybeObserver = this.b;
        Throwable th = (Throwable) obj;
        switch (i) {
            case 0:
                dogTagMaybeObserver.lambda$onSubscribe$0(th);
                break;
            case 1:
                dogTagMaybeObserver.lambda$onError$4(th);
                break;
            case 2:
                dogTagMaybeObserver.lambda$onComplete$6(th);
                break;
            default:
                dogTagMaybeObserver.lambda$onSuccess$2(th);
                break;
        }
    }
}
