package com.king.zxing;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes.dex */
public class CaptureActivity extends AppCompatActivity implements m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SurfaceView f4530b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ViewfinderView f4531c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private View f4532d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private h f4533e;

    @Override // com.king.zxing.m
    public boolean h(String str) {
        return false;
    }

    public int i() {
        return p.f4648d;
    }

    public int j() {
        return q.f4653a;
    }

    public int k() {
        return p.f4651g;
    }

    public int l() {
        return p.f4652h;
    }

    public void m() {
        this.f4530b = (SurfaceView) findViewById(k());
        this.f4531c = (ViewfinderView) findViewById(l());
        int i = i();
        if (i != 0) {
            View viewFindViewById = findViewById(i);
            this.f4532d = viewFindViewById;
            viewFindViewById.setVisibility(4);
        }
        h hVar = new h(this, this.f4530b, this.f4531c, this.f4532d);
        this.f4533e = hVar;
        hVar.w(this);
        this.f4533e.o();
    }

    public boolean n(int i) {
        return true;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int iJ = j();
        if (n(iJ)) {
            setContentView(iJ);
        }
        m();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f4533e.p();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f4533e.q();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.f4533e.t();
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f4533e.u(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
}
