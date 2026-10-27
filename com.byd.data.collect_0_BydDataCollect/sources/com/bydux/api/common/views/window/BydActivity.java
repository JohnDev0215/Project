package com.bydux.api.common.views.window;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;
import d.a.a.f.c.a.b.b;
import d.a.a.f.c.a.c.c;

/* JADX INFO: loaded from: classes.dex */
public class BydActivity extends AppCompatActivity implements b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3613b = getClass().getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private d.a.a.f.c.a.c.b f3614c;

    protected String M() {
        return null;
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(d.a.a.a.anim_byd_activity_close_enter, d.a.a.a.anim_byd_activity_close_exit);
    }

    @Override // d.a.a.f.c.a.b.b
    public void o() {
        d.a.a.f.b.b.a.l(this.f3613b, "onSkinUpdate", "skin=" + c.n().r());
        this.f3614c.a();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.f3614c = new d.a.a.f.c.a.c.b();
        getLayoutInflater().setFactory(this.f3614c);
        overridePendingTransition(d.a.a.a.anim_byd_activity_open_enter, d.a.a.a.anim_byd_activity_open_exit);
        super.onCreate(bundle);
        c.n().g(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        c.n().i(this);
        this.f3614c.b();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        TextUtils.isEmpty(M());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        TextUtils.isEmpty(M());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
    }
}
