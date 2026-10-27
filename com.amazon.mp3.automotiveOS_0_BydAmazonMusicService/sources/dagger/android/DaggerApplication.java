package dagger.android;

import android.app.Application;

/* JADX INFO: loaded from: classes7.dex */
public abstract class DaggerApplication extends Application implements HasAndroidInjector {
    volatile DispatchingAndroidInjector<Object> androidInjector;

    protected abstract AndroidInjector<? extends DaggerApplication> applicationInjector();

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        injectIfNecessary();
    }

    private void injectIfNecessary() {
        if (this.androidInjector == null) {
            synchronized (this) {
                if (this.androidInjector == null) {
                    applicationInjector().inject(this);
                    if (this.androidInjector == null) {
                        throw new IllegalStateException("The AndroidInjector returned from applicationInjector() did not inject the DaggerApplication");
                    }
                }
            }
        }
    }

    @Override // dagger.android.HasAndroidInjector
    public AndroidInjector<Object> androidInjector() {
        injectIfNecessary();
        return this.androidInjector;
    }
}
