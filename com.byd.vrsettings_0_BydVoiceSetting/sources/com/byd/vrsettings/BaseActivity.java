package com.byd.vrsettings;

import android.content.Context;
import android.content.res.Configuration;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.vrsettings.eventbus.BackPressedEvent;
import com.byd.vrsettings.eventbus.OnConfigChangeEvent;
import com.byd.vrsettings.eventbus.SwitchEvent;
import com.byd.vrsettings.service.OtaDataManager;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseActivity extends AppCompatActivity {
    protected abstract void onConfigChangeUI(Configuration configuration);

    protected abstract void reloadData();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void switchLanguage(SwitchEvent switchEvent) {
        OtaDataManager.switchAppLanguage(this);
        reloadData();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        OtaDataManager.attachBaseContext(context);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(new BackPressedEvent());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnConfigChangeEvent(OnConfigChangeEvent onConfigChangeEvent) {
        onConfigChangeUI(onConfigChangeEvent.getNewConfig());
    }
}
