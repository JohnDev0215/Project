package com.byd.digitalkey.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import androidx.fragment.app.Fragment;
import b.b.a.f.f;
import b.b.a.h.g;
import com.byd.digitalkey.R$id;
import com.byd.digitalkey.R$layout;
import com.byd.digitalkey.fragment.KeyActivateFragment;
import com.bydux.api.common.views.window.BydActivity;
import com.bydux.api.common.views.window.BydFragment;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DigitalKeyActivateNewActivity extends BydActivity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3518a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public KeyActivateFragment f3519b;

    public final void b(boolean z) {
        if (z) {
            getWindow().getDecorView().setSystemUiVisibility(1024);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(2050);
        }
    }

    public void e() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1792);
    }

    public void f(BydFragment bydFragment, String str) {
        List<Fragment> listD = g.d(getSupportFragmentManager());
        Fragment fragmentC = g.c(getSupportFragmentManager(), str);
        if (fragmentC != null) {
            listD.remove(fragmentC);
            g.n(fragmentC, listD);
        } else {
            Iterator<Fragment> it = listD.iterator();
            while (it.hasNext()) {
                g.e(it.next());
            }
            g.a(getSupportFragmentManager(), bydFragment, R$id.digital_key_activate_fl, str);
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Uri data;
        super.onCreate(bundle);
        e();
        setContentView(R$layout.activity_digital_key_activate_new);
        if (getIntent() != null) {
            data = getIntent().getData();
            if (data != null) {
                f.b("DigitalKeyActivateNewActivity", "uri:" + data.toString(), new Object[0]);
                try {
                    this.f3518a = Integer.parseInt(data.getQueryParameter("pair_state"));
                    f.b("DigitalKeyActivateNewActivity", "mPairState:" + this.f3518a, new Object[0]);
                } catch (Exception e2) {
                    f.a("DigitalKeyActivateNewActivity", "Exception:" + e2, new Object[0]);
                }
            }
        } else {
            data = null;
        }
        KeyActivateFragment keyActivateFragmentP = KeyActivateFragment.P(this.f3518a, data != null);
        this.f3519b = keyActivateFragmentP;
        f(keyActivateFragmentP, keyActivateFragmentP.M());
        b(false);
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        b(true);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        f.b("DigitalKeyActivateNewActivity", "onNewIntent", new Object[0]);
        setIntent(intent);
        this.f3519b.R();
    }
}
