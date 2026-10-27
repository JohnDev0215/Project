package com.android.providers.media;

import android.app.Application;
import com.android.providers.media.util.Logging;

/* JADX INFO: loaded from: classes.dex */
public class MediaApplication extends Application {
    static {
        System.loadLibrary("fuse_jni");
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Logging.initPersistent(getDir("logs", 0));
    }
}
