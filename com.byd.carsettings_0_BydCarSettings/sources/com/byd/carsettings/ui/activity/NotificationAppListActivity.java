package com.byd.carsettings.ui.activity;

import android.content.Context;
import android.view.View;
import b.b.g.q.f.b.v;
import b.b.m.e.d;
import b.b.m.e.g;
import com.byd.carsettings.R;
import com.byd.mvp.mvp.DialogActivity;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationAppListActivity extends DialogActivity {

    public class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ v f2924a;

        public a(v vVar) {
            this.f2924a = vVar;
        }

        @Override // b.b.m.e.d
        public void onBackPress() {
            this.f2924a.finish();
            NotificationAppListActivity.this.finish();
        }
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int J(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_544);
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int K() {
        return -1;
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public View L() {
        v vVar = new v(this.f3704b);
        vVar.setOnBackPressCallback(new a(vVar));
        return vVar.getRootView();
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int M(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_560);
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public void N(Context context) {
    }

    @Override // b.b.m.a.b
    public boolean a() {
        return true;
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void k(Context context) {
    }

    @Override // b.b.m.e.h
    public g newP() {
        return null;
    }
}
