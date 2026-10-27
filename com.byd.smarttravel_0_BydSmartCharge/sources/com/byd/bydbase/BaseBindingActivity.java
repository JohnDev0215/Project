package com.byd.bydbase;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import com.bydux.api.common.views.window.BydActivity;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseBindingActivity extends BydActivity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient Activity f1949b;

    public abstract int e();

    public abstract void f();

    public abstract void g();

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f1949b = this;
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1792);
        if (e() > 0) {
            setContentView(e());
        }
        g();
        f();
    }
}
