package com.bydux.api.common.views.window;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import androidx.fragment.app.FragmentActivity;
import d.a.a.f.c.a.b.b;
import d.a.a.f.c.a.c.c;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class BydFragmentActivity extends FragmentActivity implements b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3615b = getClass().getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private d.a.a.f.c.a.c.b f3616c;

    protected String B() {
        return null;
    }

    @Override // d.a.a.f.c.a.b.b
    public void o() {
        d.a.a.f.b.b.a.l(this.f3615b, "onSkinUpdate", "skin=" + c.n().r());
        this.f3616c.a();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Field declaredField = LayoutInflater.class.getDeclaredField("mFactorySet");
            declaredField.setAccessible(true);
            declaredField.setBoolean(getLayoutInflater(), false);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        } catch (NoSuchFieldException e4) {
            e4.printStackTrace();
        }
        this.f3616c = new d.a.a.f.c.a.c.b();
        getLayoutInflater().setFactory(this.f3616c);
        c.n().g(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        c.n().i(this);
        this.f3616c.b();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        TextUtils.isEmpty(B());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        TextUtils.isEmpty(B());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
    }
}
