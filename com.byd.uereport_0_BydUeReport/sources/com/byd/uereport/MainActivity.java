package com.byd.uereport;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import com.byd.uereport.dialog.DisChargeDialog;
import com.byd.uereport.dialog.DisChargeStatusDialog;
import com.byd.uereport.dialog.OilMaintenanceDialog;
import com.byd.uereport.dialog.OverhaulEPBDialog;
import com.byd.uereport.dialog.PlanChargeDialog;
import com.byd.uereport.dialog.TimeSetDialog;
import r4.a;

/* JADX INFO: loaded from: classes2.dex */
public class MainActivity extends Activity {
    public final boolean a() {
        return Settings.canDrawOverlays(this);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        a.d(this);
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        if (!a()) {
            startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())));
            return;
        }
        new TimeSetDialog(this).show();
        new PlanChargeDialog(this).show();
        new OverhaulEPBDialog(this).show();
        new OilMaintenanceDialog(this, 1).show();
        new DisChargeStatusDialog(this).show();
        new DisChargeDialog(this).show();
    }
}
