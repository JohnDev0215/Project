package com.byd.digitalkey.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import b.b.a.f.f;
import b.b.a.h.j;
import b.b.j.h.a;
import com.byd.base.view.BaseWebViewActivity;
import com.byd.digitalkey.R$string;

/* JADX INFO: loaded from: classes2.dex */
public class UserHelpActivity extends BaseWebViewActivity {
    public static String x(String str, String str2, String str3) {
        return str.replaceAll("(&" + str2 + "=[^&]*)", str3);
    }

    @Override // com.byd.base.view.BaseWebViewActivity, com.byd.base.view.BaseTitleActivity
    public int f() {
        return R$string.nfc_use_tips;
    }

    @Override // com.byd.base.view.BaseTitleActivity
    public void l() {
        if (this.i.canGoBack()) {
            this.i.goBack();
        } else {
            super.l();
        }
    }

    @Override // com.byd.base.view.BaseWebViewActivity, com.byd.base.view.BaseTitleActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.i.setBackgroundColor(0);
        this.i.getBackground().setAlpha(0);
        String strB = a.b(this);
        if (bundle == null) {
            this.i.loadUrl(strB);
            return;
        }
        int i = (getResources().getConfiguration().uiMode & 48) == 32 ? 2 : 1;
        String string = bundle.getString("last_load_url");
        if (!j.c(string)) {
            strB = string;
        }
        WebView webView = this.i;
        String strX = x(strB, "modelType", "&modelType=" + i);
        webView.loadUrl(strX);
        f.b("UserHelpActivity", "savedInstanceState:" + strX, new Object[0]);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.i.canGoBack()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.i.goBack();
        return true;
    }
}
