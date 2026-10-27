package dagger.android;

import android.app.Service;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DaggerService extends Service {
    @Override // android.app.Service
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }
}
