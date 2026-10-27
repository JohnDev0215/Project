package rxdogtag2;

import rxdogtag2.RxDogTag;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements RxDogTag.NonCheckingConsumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ DogTagCompletableObserver b;

    public /* synthetic */ a(DogTagCompletableObserver dogTagCompletableObserver, int i) {
        this.a = i;
        this.b = dogTagCompletableObserver;
    }

    @Override // rxdogtag2.RxDogTag.NonCheckingConsumer
    public final void accept(Object obj) {
        int i = this.a;
        DogTagCompletableObserver dogTagCompletableObserver = this.b;
        Throwable th = (Throwable) obj;
        switch (i) {
            case 0:
                dogTagCompletableObserver.lambda$onError$2(th);
                break;
            case 1:
                dogTagCompletableObserver.lambda$onComplete$4(th);
                break;
            default:
                dogTagCompletableObserver.lambda$onSubscribe$0(th);
                break;
        }
    }
}
