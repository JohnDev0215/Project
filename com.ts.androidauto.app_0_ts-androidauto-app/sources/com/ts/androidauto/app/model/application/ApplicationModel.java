package com.ts.androidauto.app.model.application;

import android.content.Context;
import android.os.Looper;
import com.ts.androidauto.app.common.IModel;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager;

/* JADX INFO: loaded from: classes2.dex */
public class ApplicationModel extends IModel {
    private static final String TAG = "AAP" + ApplicationModel.class.getSimpleName();
    private AndroidAutoRemoteUiManager mAndroidAutoRemoteUiManager;
    private Context mContext;

    public ApplicationModel(Looper looper) {
        super(looper);
    }

    @Override // com.ts.androidauto.app.common.IModel
    public void init(Context context) {
        LogUtil.info(TAG, "init");
        this.mContext = context;
        this.mAndroidAutoRemoteUiManager = AndroidAutoRemoteUiManager.getInstance();
    }

    @Override // com.ts.androidauto.app.common.IModel
    public void uninit() {
        LogUtil.info(TAG, "uninit");
        removeCallbacksAndMessages(null);
    }
}
