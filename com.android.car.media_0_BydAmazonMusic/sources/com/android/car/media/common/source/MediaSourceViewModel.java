package com.android.car.media.common.source;

import android.app.Application;
import android.car.XCar;
import android.car.media.XCarMediaManager;
import android.content.ComponentName;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.android.car.media.common.source.a;
import java.util.Objects;
import spi.b7;
import spi.e6;
import spi.q6;

/* JADX INFO: loaded from: classes.dex */
public class MediaSourceViewModel extends AndroidViewModel {
    public static MediaSourceViewModel[] k = new MediaSourceViewModel[2];
    public final XCar a;
    public XCarMediaManager b;
    public final MutableLiveData<b7> c;
    public final MutableLiveData<a.c> d;
    public final Handler e;
    public final q6 f;
    public final int g;
    public final b h;
    public final com.android.car.media.common.source.a i;
    public final q6 j;

    public class a implements b {
        public final /* synthetic */ Application a;

        public a(Application application) {
            this.a = application;
        }
    }

    @VisibleForTesting
    public interface b {
    }

    private MediaSourceViewModel(@NonNull Application application, int i) {
        this(application, i, new a(application));
    }

    public static MediaSourceViewModel a(@NonNull Application application, int i) {
        MediaSourceViewModel[] mediaSourceViewModelArr = k;
        if (mediaSourceViewModelArr[i] == null) {
            mediaSourceViewModelArr[i] = new MediaSourceViewModel(application, i);
        }
        return mediaSourceViewModelArr[i];
    }

    public final void b(b7 b7Var) {
        if (Objects.equals(this.c.getValue(), b7Var)) {
            return;
        }
        this.c.setValue(b7Var);
        if (b7Var != null) {
            this.i.b(b7Var);
        }
    }

    @Override // androidx.lifecycle.ViewModel
    public final void onCleared() {
        super.onCleared();
        this.a.disconnect();
    }

    @VisibleForTesting
    public MediaSourceViewModel(@NonNull Application application, int i, @NonNull b bVar) {
        super(application);
        b7 b7VarA = null;
        this.c = e6.a(null);
        MutableLiveData<a.c> mutableLiveDataA = e6.a(null);
        this.d = mutableLiveDataA;
        q6 q6Var = new q6(mutableLiveDataA);
        this.j = q6Var;
        this.g = i;
        this.h = bVar;
        XCar xCarCreateCar = XCar.createCar(((a) bVar).a);
        this.a = xCarCreateCar;
        this.i = new com.android.car.media.common.source.a(application, q6Var);
        this.e = new Handler(application.getMainLooper());
        q6 q6Var2 = new q6(this);
        this.f = q6Var2;
        try {
            Objects.requireNonNull((a) bVar);
            XCarMediaManager xCarMediaManager = (XCarMediaManager) xCarCreateCar.getCarManager(XCar.CAR_MEDIA_SERVICE);
            this.b = xCarMediaManager;
            xCarMediaManager.addMediaSourceListener(q6Var2, i);
            ComponentName mediaSource = this.b.getMediaSource(i);
            a aVar = (a) bVar;
            Objects.requireNonNull(aVar);
            if (mediaSource != null) {
                b7VarA = b7.a(aVar.a, mediaSource);
            }
            if (Log.isLoggable("MediaSourceViewModel", 4)) {
                Log.i("MediaSourceViewModel", "Initializing with " + b7VarA);
            }
            b(b7VarA);
        } catch (Exception e) {
            Log.e("MediaSourceViewModel", "Car not connected", e);
        }
    }
}
