package com.amazon.music.social.rx;

import com.amazon.music.social.SocialException;
import com.amazon.music.social.SocialServiceController;
import com.amazon.music.social.model.RelationsInputRequest;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes4.dex */
public class RxSocialServiceController {
    private final SocialServiceController mController;

    public RxSocialServiceController(SocialServiceController socialServiceController) {
        this.mController = socialServiceController;
    }

    public Observable<Void> addRelation(final RelationsInputRequest relationsInputRequest) {
        return Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.music.social.rx.RxSocialServiceController.1
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Void> subscriber) {
                try {
                    RxSocialServiceController.this.mController.addRelation(relationsInputRequest);
                    subscriber.onNext(null);
                    subscriber.onCompleted();
                } catch (SocialException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<Void> removeRelation(final RelationsInputRequest relationsInputRequest) {
        return Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.music.social.rx.RxSocialServiceController.2
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Void> subscriber) {
                try {
                    RxSocialServiceController.this.mController.removeRelation(relationsInputRequest);
                    subscriber.onNext(null);
                    subscriber.onCompleted();
                } catch (SocialException e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
