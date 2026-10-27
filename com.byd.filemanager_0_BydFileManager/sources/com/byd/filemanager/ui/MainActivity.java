package com.byd.filemanager.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.g;
import androidx.lifecycle.p;
import com.byd.filemanager.FileApplication;
import com.byd.filemanager.R;
import com.byd.filemanager.e;
import com.byd.filemanager.f;
import com.byd.filemanager.i.c.b;
import com.byd.filemanager.k.c;
import com.byd.filemanager.ui.fragment.ContentFragment;
import com.byd.filemanager.ui.fragment.FooterFragment;
import com.byd.filemanager.ui.fragment.HeaderFragment;
import com.byd.filemanager.ui.fragment.MenuFragment;
import com.byd.music.f.h;
import com.byd.music.f.i;
import com.byd.music.f.j;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends BaseActivity<b> {
    private ContentFragment A;
    private FooterFragment B;
    private int C;
    private g D;
    private String E;
    private final String x = "FileMainActivity";
    private MenuFragment y;
    private HeaderFragment z;

    private void T(int i, Fragment fragment) {
        this.D.a().l(i, fragment, fragment.getClass().getSimpleName()).g();
    }

    private boolean U() {
        return androidx.core.content.a.a(this, "android.permission.READ_EXTERNAL_STORAGE") == 0 && androidx.core.content.a.a(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    private void V(Intent intent) {
        String stringExtra = intent.getStringExtra("root_path");
        int i = TextUtils.equals("sdcard", stringExtra) ? 1 : TextUtils.equals("usbotg", stringExtra) ? 2 : 0;
        int intExtra = intent.getIntExtra("file_category", 1);
        f.h().L(i);
        f.h().I(intExtra);
        this.E = intent.getStringExtra("target_path");
        com.byd.filemanager.k.b.b("index:" + i + " category:" + intExtra + " target_path:" + this.E);
        if (!TextUtils.isEmpty(this.E)) {
            f.h().D(this.E);
        }
        intent.setAction(null);
        intent.removeExtra("root_path");
        intent.removeExtra("file_category");
        intent.removeExtra("target_path");
    }

    private Fragment X(String str) {
        return this.D.d(str);
    }

    private int Y() {
        return f.h().v() ? f.h().x() ? R.layout.activity_main_rh : R.layout.activity_main : f.h().x() ? R.layout.activity_main_denza_rh : R.layout.activity_main_denza;
    }

    private void a0() {
        ContentFragment contentFragment = (ContentFragment) X(ContentFragment.class.getSimpleName());
        this.A = contentFragment;
        if (contentFragment == null) {
            ContentFragment contentFragment2 = new ContentFragment();
            this.A = contentFragment2;
            T(R.id.fragment_content, contentFragment2);
        }
        ContentFragment contentFragment3 = this.A;
        if (contentFragment3.t0 == null) {
            contentFragment3.a2(this.z);
        }
    }

    private void b0() {
        this.y = (MenuFragment) X(MenuFragment.class.getSimpleName());
        this.z = (HeaderFragment) X(HeaderFragment.class.getSimpleName());
        this.B = (FooterFragment) X(FooterFragment.class.getSimpleName());
        if (this.y == null) {
            this.y = new MenuFragment();
            this.z = new HeaderFragment();
            this.B = new FooterFragment();
            T(R.id.fragment_menu, this.y);
            T(R.id.fragment_header, this.z);
            T(R.id.fragment_footer, this.B);
        }
    }

    private void c0() {
        androidx.core.app.a.k(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.filemanager.ui.BaseActivity
    /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
    public b P() {
        return null;
    }

    public String Z() {
        return this.E;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1) {
            if (!f.h().w()) {
                f.h().K();
                return true;
            }
            HeaderFragment headerFragment = this.z;
            if (headerFragment != null && headerFragment.V1()) {
                return true;
            }
            finish();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            com.byd.filemanager.k.b.b("uri = " + intent.getData().toString());
        }
    }

    @Override // com.byd.filemanager.ui.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        StringBuilder sb = new StringBuilder();
        sb.append("onConfigurationChanged orientation = ");
        sb.append(configuration.orientation == 1 ? "portrait" : "landscape");
        com.byd.filemanager.k.b.b(sb.toString());
        com.byd.filemanager.k.b.f("config = %s, newConfig = %s", Integer.valueOf(this.C), Integer.valueOf(configuration.orientation));
        int i = this.C;
        int i2 = configuration.orientation;
        if (i == i2) {
            return;
        }
        this.C = i2;
    }

    @Override // com.byd.filemanager.ui.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        int i;
        Log.i("FileMainActivity", "onCreate is begin app =" + (System.currentTimeMillis() - FileApplication.a().f3515c));
        super.onCreate(bundle);
        this.C = c.c(this);
        setContentView(Y());
        Log.i("FileMainActivity", "onCreate viewModel =" + ((com.byd.filemanager.h.a) new p(this, new p.a(getApplication())).a(com.byd.filemanager.h.a.class)).hashCode());
        Intent intent = getIntent();
        if (TextUtils.equals(intent.getAction(), "com.byd.filemanager.action.OPEN_PATH")) {
            V(intent);
        } else {
            Bundle extras = getIntent().getExtras();
            if (extras != null && (i = extras.getInt("root_path", 0)) != 0) {
                f.h().L(i);
            }
        }
        this.D = x();
        b0();
        if (U()) {
            a0();
        } else {
            c0();
        }
    }

    @Override // com.byd.filemanager.ui.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        Log.d("FileMainActivity", "onDestroy is begin");
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        int i;
        super.onNewIntent(intent);
        com.byd.filemanager.k.b.b("onNewIntent");
        if (TextUtils.equals(intent.getAction(), "com.byd.filemanager.action.OPEN_PATH")) {
            V(intent);
            org.greenrobot.eventbus.c.c().k(new i(15));
            org.greenrobot.eventbus.c.c().k(new h(14));
            return;
        }
        this.E = "";
        File file = (File) intent.getSerializableExtra("extra_file");
        if (file != null) {
            com.byd.filemanager.k.b.b("path = " + file.getPath());
            f.h().I(1);
            f.h().L(e.f().i(file.getPath()));
            f.h().D(e.e(file.getPath()));
            org.greenrobot.eventbus.c.c().k(new i(15));
            org.greenrobot.eventbus.c.c().k(new h(14));
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras == null || (i = extras.getInt("root_path", 0)) == 0) {
            return;
        }
        com.byd.filemanager.k.b.b("rootIndex = " + i);
        f.h().L(i);
        f.h().I(1);
        org.greenrobot.eventbus.c.c().k(new j());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.a.b
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1) {
            if (iArr.length > 0 && iArr[0] == 0) {
                a0();
                return;
            }
            if (androidx.core.app.a.l(this, "android.permission.READ_EXTERNAL_STORAGE")) {
                return;
            }
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getPackageName(), null));
            startActivity(intent);
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        com.byd.filemanager.k.b.b("onRestoreInstanceState");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        com.byd.filemanager.k.b.b("onSaveInstanceState");
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }
}
