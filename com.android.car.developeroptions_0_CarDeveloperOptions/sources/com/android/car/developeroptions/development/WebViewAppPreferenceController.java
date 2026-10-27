package com.android.car.developeroptions.development;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.webview.WebViewUpdateServiceWrapper;
import com.android.settingslib.applications.DefaultAppInfo;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class WebViewAppPreferenceController extends DeveloperOptionsPreferenceController implements PreferenceControllerMixin {
    private final PackageManager mPackageManager;
    private final WebViewUpdateServiceWrapper mWebViewUpdateServiceWrapper;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "select_webview_provider";
    }

    public WebViewAppPreferenceController(Context context) {
        super(context);
        this.mPackageManager = context.getPackageManager();
        this.mWebViewUpdateServiceWrapper = new WebViewUpdateServiceWrapper();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        CharSequence defaultAppLabel = getDefaultAppLabel();
        if (!TextUtils.isEmpty(defaultAppLabel)) {
            this.mPreference.setSummary(defaultAppLabel);
        } else {
            Log.d("WebViewAppPrefCtrl", "No default app");
            this.mPreference.setSummary(R.string.app_list_preference_none);
        }
    }

    DefaultAppInfo getDefaultAppInfo() {
        PackageInfo currentWebViewPackage = this.mWebViewUpdateServiceWrapper.getCurrentWebViewPackage();
        return new DefaultAppInfo(this.mContext, this.mPackageManager, UserHandle.myUserId(), currentWebViewPackage == null ? null : currentWebViewPackage.applicationInfo);
    }

    private CharSequence getDefaultAppLabel() {
        return getDefaultAppInfo().loadLabel();
    }
}
