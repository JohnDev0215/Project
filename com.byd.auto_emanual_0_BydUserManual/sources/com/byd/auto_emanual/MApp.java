package com.byd.auto_emanual;

import android.app.Application;
import android.content.Context;
import com.byd.auto_emanual.update.UpdateCheckUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.Utils;
import com.byd.car.DiCar;
import com.byd.car.DiCarConfig;

/* JADX INFO: loaded from: classes.dex */
public class MApp extends Application {
    private static final String TAG = "MApp";
    public static Context context;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        MLog.v(TAG, "onCreate");
        Context applicationContext = getApplicationContext();
        context = applicationContext;
        if (!Utils.notShowUpdate(applicationContext)) {
            UpdateCheckUtil.getInstance().init(context);
        }
        initDiCarConfig();
    }

    public void initDiCarConfig() {
        DiCar.init(new DiCarConfig.Builder(this).build());
    }
}
