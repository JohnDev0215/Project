package com.ts.appservice.power.scene;

import android.content.Context;
import android.view.View;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ExtCarPowerSceneTransitionManager {
    private static final String TAG = ExtCarPowerSceneTransitionManager.class.getSimpleName();
    private static volatile ExtCarPowerSceneTransitionManager sInstance;
    private View mBlackView;
    private Context mContext;

    public static ExtCarPowerSceneTransitionManager getInstance(Context context, View view) {
        LogUtil.info(TAG, "getInstance");
        if (sInstance == null) {
            synchronized (ExtCarPowerSceneTransitionManager.class) {
                if (sInstance == null) {
                    sInstance = new ExtCarPowerSceneTransitionManager(context, view);
                }
            }
        }
        return sInstance;
    }

    private ExtCarPowerSceneTransitionManager(Context context, View view) {
        LogUtil.info(TAG, "TsCarPowerSceneTransitionManager");
        this.mContext = context;
        this.mBlackView = view;
        initManager();
    }

    private void initManager() {
        LogUtil.info(TAG, "initManager");
    }

    public void viewShow() {
        LogUtil.info(TAG, "viewShow");
    }

    public void viewDismissed() {
        LogUtil.info(TAG, "viewDismissed");
    }
}
