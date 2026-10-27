package com.byd.uereport.dialog;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import b5.g;
import com.byd.uereport.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialogCarTrailer extends g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f3174d;

    public DialogCarTrailer(Context context) {
        super(context);
        this.f3174d = context;
    }

    public final void c() {
        PackageManager packageManager = this.mContext.getApplicationContext().getPackageManager();
        BydLogHelper.logD("schemebyd_pad://byd_car_settings:2935/launcher?action=trailer");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2935/launcher?action=trailer"));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        Log.e("----", "---startCarSettingTrailer---resolveInfos = " + listQueryIntentServices);
        packageManager.queryIntentServices(intent, 0);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        this.mContext.startService(intent);
        dismiss();
    }

    @Override // b5.g, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
    }

    @Override // b5.g
    public void initContent(View view) {
        view.findViewById(R.id.carsetting_dialog_btn_confirm).setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_trailer_instructions;
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.carsetting_dialog_btn_confirm) {
            return;
        }
        c();
    }

    @Override // b5.g
    public void setWidthAndHeight(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_544;
    }
}
