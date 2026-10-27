package com.android.permissioncontroller;

import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

/* JADX INFO: loaded from: classes8.dex */
public abstract class AsyncTaskLiveData<T> extends LiveData<T> {
    protected abstract T loadValueInBackground();

    public /* synthetic */ void lambda$loadValue$0$AsyncTaskLiveData() {
        postValue(loadValueInBackground());
    }

    public void loadValue() {
        AsyncTask.execute(new Runnable() { // from class: com.android.permissioncontroller.-$$Lambda$AsyncTaskLiveData$yC9vB_aMbI221LUzuH_c5Ad0q0s
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$loadValue$0$AsyncTaskLiveData();
            }
        });
    }
}
