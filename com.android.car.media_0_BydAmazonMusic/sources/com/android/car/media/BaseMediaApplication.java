package com.android.car.media;

import android.app.Application;
import android.car.IXCarApplicationStub;
import android.car.XCarRoute;
import android.car.test.ITestViewControl;
import android.car.utils.Logs;
import android.content.res.Configuration;
import android.util.Log;
import androidx.annotation.NonNull;
import com.alibaba.android.arouter.launcher.ARouter;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseMediaApplication extends Application {
    public static BaseMediaApplication b;
    public transient IXCarApplicationStub a;

    public abstract void a();

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        IXCarApplicationStub iXCarApplicationStub = this.a;
        if (iXCarApplicationStub != null) {
            iXCarApplicationStub.onConfigurationChanged(configuration);
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        b = this;
        super.onCreate();
        Logs.init(this);
        Logs.e("MediaApplication", "platform: dilink100F");
        ARouter.init(this);
        IXCarApplicationStub iXCarApplicationStub = (IXCarApplicationStub) ARouter.getInstance().build(XCarRoute.CAR_APPLICATION_STUB).navigation();
        this.a = iXCarApplicationStub;
        if (iXCarApplicationStub != null) {
            iXCarApplicationStub.onCreate(this);
        } else {
            Log.e("MediaApplication", " iXCarApplicationStub is null.");
        }
        ITestViewControl iTestViewControl = (ITestViewControl) ARouter.getInstance().build(XCarRoute.CAR_TEST_SETTING).navigation();
        if (iTestViewControl != null) {
            iTestViewControl.onCreate(this);
        } else {
            Log.e("MediaApplication", " iTestViewControl is null.");
        }
    }
}
