package com.amazon.mp3.library.provider.source.local;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.provider.source.local.DeleteListener;
import com.amazon.mp3.util.Log;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class DeleteTracksPresenter implements DeleteTracksContract$Presenter {
    private static final String TAG = "DeleteTracksPresenter";
    private DeleteTracksContract$View view;

    public DeleteTracksPresenter(DeleteTracksContract$View view) {
        this.view = view;
    }

    @Override // com.amazon.mp3.library.provider.source.local.DeleteTracksContract$Presenter
    public void detachView() {
        this.view = null;
    }

    @Override // com.amazon.mp3.library.provider.source.local.DeleteTracksContract$Presenter
    public void executeDeletionOnCurrentThread(final String tracksUri) {
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.provider.source.local.DeleteTracksPresenter$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DeleteTracksPresenter.lambda$executeDeletionOnCurrentThread$0(tracksUri, (Subscriber) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.library.provider.source.local.DeleteTracksPresenter$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$executeDeletionOnCurrentThread$1((DeleteListener.DeletionResult) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.library.provider.source.local.DeleteTracksPresenter$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DeleteTracksPresenter.lambda$executeDeletionOnCurrentThread$2((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$executeDeletionOnCurrentThread$0(String str, Subscriber subscriber) {
        subscriber.onNext(DeleteUtil.executeDeletionOnCurrentThread(AmazonApplication.getContext(), Uri.parse(str), SDCardUtil.hasSDCardPermission(AmazonApplication.getContext())));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executeDeletionOnCurrentThread$1(DeleteListener.DeletionResult deletionResult) {
        DeleteTracksContract$View deleteTracksContract$View = this.view;
        if (deleteTracksContract$View != null) {
            deleteTracksContract$View.showTracksDeletionResult(deletionResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$executeDeletionOnCurrentThread$2(Throwable th) {
        th.printStackTrace();
        Log.error(TAG, th.getMessage());
    }
}
