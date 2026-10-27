package com.byd.logtools.activity;

import android.app.ActivityManager;
import android.os.Bundle;
import com.byd.logtools.application.BydLogApplication;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.BydActivity;

/* JADX INFO: loaded from: classes.dex */
public class BydBaseActivity extends BydActivity {
    public boolean i(String str) {
        boolean zIsUserAMonkey = ActivityManager.isUserAMonkey();
        if (zIsUserAMonkey) {
            BydLogHelper.logE("checkIsMonkeyTest TAG = " + str);
        }
        return zIsUserAMonkey;
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BydLogApplication.d().a(this);
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        BydLogApplication.d().g(this);
    }
}
