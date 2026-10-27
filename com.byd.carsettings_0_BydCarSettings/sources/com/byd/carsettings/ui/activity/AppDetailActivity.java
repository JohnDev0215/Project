package com.byd.carsettings.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import b.b.a.h.j;
import b.b.g.q.f.b.d;
import b.b.m.e.g;
import com.byd.carsettings.R;
import com.byd.mvp.mvp.DialogActivity;

/* JADX INFO: loaded from: classes2.dex */
public class AppDetailActivity extends DialogActivity {
    public b.b.g.q.f.b.c u;
    public String v;
    public String w;
    public c x;
    public String y;
    public b z;

    public class b extends BroadcastReceiver {
        public b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && j.c(AppDetailActivity.this.y)) {
                b.b.m.c.a.a(AppDetailActivity.this.f3705c, "finish Activity");
                if ("com.byd.card.close.app.detail".equals(intent.getAction())) {
                    AppDetailActivity.this.finish();
                    return;
                }
                return;
            }
            b.b.m.c.a.a(AppDetailActivity.this.f3705c, "jumpFrom:" + AppDetailActivity.this.y + ",not finish Activity");
        }
    }

    public class c extends BroadcastReceiver {
        public c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
                String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                Log.d("UninstallReceiver", "Package removed: " + schemeSpecificPart);
                if (AppDetailActivity.this.v == null || !AppDetailActivity.this.v.equals(schemeSpecificPart)) {
                    return;
                }
                Intent intent2 = new Intent();
                intent2.putExtra("uninstall_pkg", AppDetailActivity.this.v);
                AppDetailActivity.this.setResult(2, intent2);
                AppDetailActivity.this.finish();
            }
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
        if (getIntent() == null) {
            return null;
        }
        U();
        if (!"jump_permission".equals(this.w)) {
            this.u = new b.b.g.q.f.b.c(this.f3704b);
            if (!TextUtils.isEmpty(this.v)) {
                this.u.K(this.v);
            }
            this.u.H(this);
            return this.u.getRootView();
        }
        d dVar = new d(this.f3704b, this);
        dVar.p(this.v);
        int dimension = (int) getResources().getDimension(R.dimen.dp_560);
        int dimension2 = (int) getResources().getDimension(R.dimen.dp_544);
        View rootView = dVar.getRootView();
        rootView.setLayoutParams(new LinearLayout.LayoutParams(dimension, dimension2));
        return rootView;
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int M(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_560);
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public void N(Context context) {
    }

    public void U() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.v = extras.getString("app_package");
            this.w = extras.getString("jump_to");
            b.b.m.c.a.a(this.f3705c, "AppDetailActivity packageName:" + this.v + ",jump:" + this.w);
            String string = extras.getString("jump_from");
            this.y = string;
            if ("app_list".equals(string)) {
                P();
            } else {
                this.s.setBackgroundColor(getResources().getColor(R.color.bg_dialog_outside));
            }
        }
        this.x = new c();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        registerReceiver(this.x, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter("com.byd.card.close.app.detail");
        b bVar = new b();
        this.z = bVar;
        registerReceiver(bVar, intentFilter2);
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

    @Override // com.byd.mvp.mvp.DialogActivity, com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        b.b.g.q.f.b.c cVar = this.u;
        if (cVar != null) {
            cVar.onDestroy();
        }
        unregisterReceiver(this.x);
        unregisterReceiver(this.z);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        U();
        if (this.u != null && !TextUtils.isEmpty(this.v)) {
            this.u.K(this.v);
        }
        b.b.m.c.a.a(this.f3705c, "AppDetailActivity onNewIntent");
    }
}
