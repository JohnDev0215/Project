package com.byd.ui;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.byd.taskmanager.b.c;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseActivity extends FragmentActivity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b f2923a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected boolean f2924b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f2925c;

    protected void A() {
        this.f2923a.g(this);
    }

    protected void B() {
        this.f2923a.i();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f2923a = new b();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.f2923a.h();
        super.onDestroy();
        this.f2923a = null;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.f2925c = true;
        if (this.f2924b) {
            A();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.f2925c = false;
        if (this.f2924b) {
            B();
        }
    }

    protected <T> BaseActivity v(int i, com.byd.taskmanager.b.a<T> aVar, c<T> cVar, boolean z) {
        this.f2923a.b(this, i, aVar, cVar, z);
        return this;
    }

    public void w(int i) {
        this.f2923a.d(i);
    }

    protected <T extends View> T x(int i) {
        return (T) findViewById(i);
    }

    public com.byd.taskmanager.b.a y(int i) {
        return this.f2923a.e(i);
    }

    public boolean z(int i) {
        return this.f2923a.f(i);
    }
}
