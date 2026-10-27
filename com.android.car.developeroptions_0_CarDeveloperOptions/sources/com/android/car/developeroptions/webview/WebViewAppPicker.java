package com.android.car.developeroptions.webview;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.webkit.UserPackage;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.defaultapps.DefaultAppPickerFragment;
import com.android.settingslib.applications.DefaultAppInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WebViewAppPicker extends DefaultAppPickerFragment {
    private WebViewUpdateServiceWrapper mWebViewUpdateServiceWrapper;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 405;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.webview_app_settings;
    }

    private WebViewUpdateServiceWrapper getWebViewUpdateServiceWrapper() {
        if (this.mWebViewUpdateServiceWrapper == null) {
            setWebViewUpdateServiceWrapper(createDefaultWebViewUpdateServiceWrapper());
        }
        return this.mWebViewUpdateServiceWrapper;
    }

    @Override // com.android.car.developeroptions.applications.defaultapps.DefaultAppPickerFragment, com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.mUserManager.isAdminUser()) {
            return;
        }
        getActivity().finish();
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected List<DefaultAppInfo> getCandidates() {
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        WebViewUpdateServiceWrapper webViewUpdateServiceWrapper = getWebViewUpdateServiceWrapper();
        for (ApplicationInfo applicationInfo : webViewUpdateServiceWrapper.getValidWebViewApplicationInfos(context)) {
            arrayList.add(createDefaultAppInfo(context, this.mPm, applicationInfo, getDisabledReason(webViewUpdateServiceWrapper, context, applicationInfo.packageName)));
        }
        return arrayList;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected String getDefaultKey() {
        PackageInfo currentWebViewPackage = getWebViewUpdateServiceWrapper().getCurrentWebViewPackage();
        if (currentWebViewPackage == null) {
            return null;
        }
        return currentWebViewPackage.packageName;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected boolean setDefaultKey(String str) {
        return getWebViewUpdateServiceWrapper().setWebViewProvider(str);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected void onSelectionPerformed(boolean z) {
        if (z) {
            FragmentActivity activity = getActivity();
            Intent intent = activity == null ? null : activity.getIntent();
            if (intent == null || !"android.settings.WEBVIEW_SETTINGS".equals(intent.getAction())) {
                return;
            }
            getActivity().finish();
            return;
        }
        getWebViewUpdateServiceWrapper().showInvalidChoiceToast(getActivity());
        updateCandidates();
    }

    private WebViewUpdateServiceWrapper createDefaultWebViewUpdateServiceWrapper() {
        return new WebViewUpdateServiceWrapper();
    }

    void setWebViewUpdateServiceWrapper(WebViewUpdateServiceWrapper webViewUpdateServiceWrapper) {
        this.mWebViewUpdateServiceWrapper = webViewUpdateServiceWrapper;
    }

    private static class WebViewAppInfo extends DefaultAppInfo {
        public WebViewAppInfo(Context context, PackageManager packageManager, int i, PackageItemInfo packageItemInfo, String str, boolean z) {
            super(context, packageManager, i, packageItemInfo, str, z);
        }

        @Override // com.android.settingslib.applications.DefaultAppInfo, com.android.settingslib.widget.CandidateInfo
        public CharSequence loadLabel() {
            String str;
            try {
                str = this.mPm.getPackageInfo(this.packageItemInfo.packageName, 0).versionName;
            } catch (PackageManager.NameNotFoundException unused) {
                str = "";
            }
            return String.format("%s %s", super.loadLabel(), str);
        }
    }

    DefaultAppInfo createDefaultAppInfo(Context context, PackageManager packageManager, PackageItemInfo packageItemInfo, String str) {
        return new WebViewAppInfo(context, packageManager, this.mUserId, packageItemInfo, str, TextUtils.isEmpty(str));
    }

    String getDisabledReason(WebViewUpdateServiceWrapper webViewUpdateServiceWrapper, Context context, String str) {
        for (UserPackage userPackage : webViewUpdateServiceWrapper.getPackageInfosAllUsers(context, str)) {
            if (!userPackage.isInstalledPackage()) {
                return context.getString(R.string.webview_uninstalled_for_user, userPackage.getUserInfo().name);
            }
            if (!userPackage.isEnabledPackage()) {
                return context.getString(R.string.webview_disabled_for_user, userPackage.getUserInfo().name);
            }
        }
        return null;
    }
}
