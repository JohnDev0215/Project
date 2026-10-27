package com.byd.overseaappstore.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import androidx.core.app.ActivityCompat;
import com.byd.overseaappstore.ui.view.dialog.PermissionAlertDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import spi.ct;
import spi.r3;
import spi.yk;

/* JADX INFO: loaded from: classes.dex */
public final class e extends Lambda implements Function1<View, Unit> {
    public final /* synthetic */ PermissionAlertDialog a;
    public final /* synthetic */ MainActivity b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(PermissionAlertDialog permissionAlertDialog, MainActivity mainActivity) {
        super(1);
        this.a = permissionAlertDialog;
        this.b = mainActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Unit invoke(View view) {
        View it = view;
        Intrinsics.checkNotNullParameter(it, "it");
        ct.a("permissionWork permissionDialog positive NN toSetting permissionDialog.dismiss", new Object[0]);
        this.a.dismissAllowingStateLoss();
        r3 r3Var = r3.a;
        if (r3Var.f() || r3Var.d() || r3Var.e()) {
            ActivityCompat.requestPermissions(this.b, yk.a.b(), 1);
        } else {
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", this.b.getPackageName(), null));
            this.b.r.launch(intent);
        }
        return Unit.INSTANCE;
    }
}
