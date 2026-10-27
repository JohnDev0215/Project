package com.byd.autoservice.function;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.byd.autoservice.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class MaskActivity extends Activity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BroadcastReceiver f1853b = new a();

    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !intent.getAction().equals("ACTION_MASK_CLOSE")) {
                return;
            }
            MaskActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d(View view) {
        finish();
    }

    public final void a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ACTION_MASK_CLOSE");
        registerReceiver(this.f1853b, intentFilter);
        BydLogHelper.logD("MaskActivity", "broadcastReceiver register");
    }

    public final void b() {
        if (getActionBar() != null) {
            getActionBar().hide();
        }
        ((LinearLayout) findViewById(R.id.mask_ll)).setOnClickListener(new View.OnClickListener() { // from class: e1.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2801b.d(view);
            }
        });
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BydLogHelper.logD("MaskActivity", "onCreate");
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_mask);
        b();
        a();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        BroadcastReceiver broadcastReceiver = this.f1853b;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            BydLogHelper.logD("MaskActivity", "broadcastReceiver unregister");
        }
    }
}
