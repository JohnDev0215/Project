package com.byd.vrassistant.livedata;

import android.util.Log;
import androidx.lifecycle.LiveData;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class RxLiveData<T> extends LiveData<T> {
    CompositeDisposable disposables = new CompositeDisposable();
    Observable<T> observable;

    public RxLiveData(Observable<T> observable) {
        this.observable = observable;
    }

    @Override // androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        this.disposables.add(this.observable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.byd.vrassistant.livedata.RxLiveData$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                this.f$0.setValue(obj);
            }
        }, new Consumer() { // from class: com.byd.vrassistant.livedata.RxLiveData$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Log.e("NEU_GLOBAL_LOG", "toLiveData: ", (Throwable) obj);
            }
        }));
    }

    @Override // androidx.lifecycle.LiveData
    protected void onInactive() {
        super.onInactive();
        this.disposables.clear();
    }
}
