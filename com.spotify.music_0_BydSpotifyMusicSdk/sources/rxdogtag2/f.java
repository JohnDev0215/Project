package rxdogtag2;

import rxdogtag2.RxDogTag;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f implements RxDogTag.NonCheckingConsumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ DogTagSubscriber b;

    public /* synthetic */ f(DogTagSubscriber dogTagSubscriber, int i) {
        this.a = i;
        this.b = dogTagSubscriber;
    }

    @Override // rxdogtag2.RxDogTag.NonCheckingConsumer
    public final void accept(Object obj) {
        int i = this.a;
        DogTagSubscriber dogTagSubscriber = this.b;
        Throwable th = (Throwable) obj;
        switch (i) {
            case 0:
                dogTagSubscriber.lambda$onComplete$6(th);
                break;
            case 1:
                dogTagSubscriber.lambda$onNext$2(th);
                break;
            case 2:
                dogTagSubscriber.lambda$onSubscribe$0(th);
                break;
            default:
                dogTagSubscriber.lambda$onError$4(th);
                break;
        }
    }
}
