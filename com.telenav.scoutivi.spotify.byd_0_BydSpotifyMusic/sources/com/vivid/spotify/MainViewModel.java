package com.vivid.spotify;

import android.app.Application;
import android.util.Log;
import com.vivid.spotify.common.base.BaseViewModel;

/* JADX INFO: loaded from: classes.dex */
public class MainViewModel extends BaseViewModel {
    private static final String TAG = "MainViewModel";
    private v4.d<Throwable> mConsumer;
    private final i3.m mediaPlayController;

    class a implements v4.d<Throwable> {
        a() {
        }

        @Override // v4.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Throwable th) {
            if (th instanceof u4.e) {
                Log.e(MainViewModel.TAG, "setupRxJava2GlobalErrorHandler UndeliverableException:" + th.getCause());
                return;
            }
            Log.e(MainViewModel.TAG, "setupRxJava2GlobalErrorHandler Attention! Potential bugs found:" + th.toString());
            Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
    }

    public MainViewModel(Application application, i3.m mVar) {
        super(application);
        this.mediaPlayController = mVar;
        initRxJava2GlobalErrorHandler();
        h3.i.b(TAG).b("MainViewModel is created", new Object[0]);
    }

    public void clearJavaHandler() {
        this.mConsumer = null;
    }

    public void initRxJava2GlobalErrorHandler() {
        if (this.mConsumer == null) {
            this.mConsumer = new a();
        }
        d5.a.p(this.mConsumer);
    }

    @Override // com.vivid.spotify.common.base.BaseViewModel, androidx.lifecycle.a0
    protected void onCleared() {
        clearJavaHandler();
        super.onCleared();
    }
}
