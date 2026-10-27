package org.chromium.android_webview.nonembedded;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import defpackage.AbstractC1077kw;
import defpackage.Ts;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class LicenseActivity extends Activity {
    @Override // android.content.ContextWrapper, android.content.Context
    public Context createConfigurationContext(Configuration configuration) {
        AbstractC1077kw.c();
        return super.createConfigurationContext(configuration);
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        AbstractC1077kw.c();
        return super.getAssets();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        AbstractC1077kw.c();
        return super.getResources();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        AbstractC1077kw.c();
        return super.getTheme();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String packageName = getPackageName();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.parse(String.format("content://%s.%s", packageName, "LicenseContentProvider/webview_licenses")), "text/html");
        intent.addCategory("android.intent.category.DEFAULT");
        int iB = AbstractC1077kw.b(getResources(), "license_activity_title", "string", packageName);
        if (iB != 0) {
            intent.putExtra("android.intent.extra.TITLE", getString(iB));
        }
        intent.setPackage("com.android.htmlviewer");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Ts.a("WebView", "Failed to find viewer", e);
        }
        finish();
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        AbstractC1077kw.c();
        super.setTheme(i);
    }
}
