package com.byd.vrsettings;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import com.byd.common.countrycode.CountryCodeUtil;
import com.byd.vrsettings.eventbus.OnConfigChangeEvent;
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.Constant;
import com.byd.vrsettings.utils.LogUtils;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.RLog;
import com.byd.vrsettings.utils.SharedPreferencesUtils;
import com.google.gson.Gson;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class BydVoiceSettingApplication extends Application implements ViewModelStoreOwner {
    private static final String TAG = "NEU_Setting_BydVoice";
    private static BydVoiceSettingApplication sApplication;
    ViewModelStore viewModelStore;
    private boolean isBind = false;
    private BindServiceRunnable bindServiceRunnable = new BindServiceRunnable();
    private final long BIND_SERVICE_RETRY_INTERVAL_DOWNLOAD = 3000;
    private final String BUSINESS_SERVICE_ACTION = "com.byd.vrdownload.downloadservice";
    private ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.byd.vrsettings.BydVoiceSettingApplication.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtils.i(BydVoiceSettingApplication.TAG, "onServiceDisconnected ... name");
            BydVoiceSettingApplication.this.isBind = false;
            BydVoiceSettingApplication.this.bindServiceRunnable.run();
        }
    };

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        RLog.i(TAG, "BydVoiceSettingApplication onCreate");
        sApplication = this;
        clearInstallState();
        hookWebView();
        this.viewModelStore = new ViewModelStore();
        CountryCodeUtil.getInstance().initCountryData(this);
        RLog.e(TAG, "countryCode:" + CountryCodeUtil.getInstance().getCountryCode(this));
    }

    private void clearInstallState() {
        Iterator<String> it = OtaDataManager.mOtaUseLanguages.iterator();
        while (it.hasNext()) {
            SharedPreferencesUtils.setParam(this, it.next().replace("data_", com.chad.library.BuildConfig.FLAVOR), false);
        }
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        RLog.e(TAG, "onConfigurationChanged:" + new Gson().toJson(configuration.getLocales()));
        Constant.application_OnconfigChange = true;
        Constant.application_OnconfigChangeforSkill = true;
        Constant.application_OnconfigWebviewChange = true;
        Common.createLanguageContext(this);
        if (ModeUtils.getNightMode(getContext())) {
            Constant.isNightMode = Constant.UIMode.NIGHT;
        } else {
            Constant.isNightMode = Constant.UIMode.WHITE;
        }
        RLog.i(TAG, "onConfigurationChanged: Constant.isNightMode==" + Constant.isNightMode);
        EventBus.getDefault().post(new OnConfigChangeEvent(configuration));
    }

    public static BydVoiceSettingApplication getContext() {
        return sApplication;
    }

    private void hookWebView() {
        try {
            Class<?> cls = Class.forName("android.webkit.WebViewFactory");
            Field declaredField = cls.getDeclaredField("sProviderInstance");
            declaredField.setAccessible(true);
            Object objInvoke = declaredField.get(null);
            if (objInvoke != null) {
                RLog.i("NEU_Common", "sProviderInstance isn't null");
                return;
            }
            Method declaredMethod = cls.getDeclaredMethod("getProviderClass", new Class[0]);
            declaredMethod.setAccessible(true);
            Class cls2 = (Class) declaredMethod.invoke(cls, new Object[0]);
            Class<?> cls3 = Class.forName("android.webkit.WebViewDelegate");
            Constructor<?> declaredConstructor = cls3.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            Field declaredField2 = cls.getDeclaredField("CHROMIUM_WEBVIEW_FACTORY_METHOD");
            declaredField2.setAccessible(true);
            String str = (String) declaredField2.get(null);
            if (str == null) {
                str = "create";
            }
            Method method = cls2.getMethod(str, cls3);
            if (method != null) {
                objInvoke = method.invoke(null, declaredConstructor.newInstance(new Object[0]));
            }
            if (objInvoke != null) {
                declaredField.set("sProviderInstance", objInvoke);
                RLog.i("NEU_Common", "Hook success!");
            } else {
                RLog.i("NEU_Common", "Hook failed!");
            }
        } catch (Throwable th) {
            RLog.i("NEU_Common", th);
        }
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        return this.viewModelStore;
    }

    private class BindServiceRunnable implements Runnable {
        private BindServiceRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LogUtils.i(BydVoiceSettingApplication.TAG, "BindServiceRunnable ... run isBind:" + BydVoiceSettingApplication.this.isBind);
            Intent intent = new Intent("com.byd.vrdownload.downloadservice");
            intent.setPackage("com.byd.vrdownload");
            BydVoiceSettingApplication.this.startService(intent);
            BydVoiceSettingApplication bydVoiceSettingApplication = BydVoiceSettingApplication.this;
            bydVoiceSettingApplication.isBind = bydVoiceSettingApplication.bindService(intent, bydVoiceSettingApplication.serviceConnection, 1);
            if (BydVoiceSettingApplication.this.isBind) {
                return;
            }
            LogUtils.i(BydVoiceSettingApplication.TAG, "BindServiceRunnable ... run");
            new Handler().postDelayed(BydVoiceSettingApplication.this.bindServiceRunnable, 3000L);
        }
    }

    public static String getCountryCode() {
        return CountryCodeUtil.getInstance().getCountryCode(getContext());
    }
}
