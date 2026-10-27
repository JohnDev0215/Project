package androidx.lifecycle;

import android.annotation.SuppressLint;
import android.app.Application;

/* JADX INFO: compiled from: AndroidViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends a0 {

    @SuppressLint({"StaticFieldLeak"})
    private Application mApplication;

    public b(Application application) {
        this.mApplication = application;
    }

    public <T extends Application> T getApplication() {
        return (T) this.mApplication;
    }
}
