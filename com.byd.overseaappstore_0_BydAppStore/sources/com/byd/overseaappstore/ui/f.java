package com.byd.overseaappstore.ui;

import android.view.View;
import com.byd.overseaappstore.ui.view.dialog.PermissionAlertDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import spi.ct;

/* JADX INFO: loaded from: classes.dex */
public final class f extends Lambda implements Function1<View, Unit> {
    public final /* synthetic */ PermissionAlertDialog a;
    public final /* synthetic */ MainActivity b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(PermissionAlertDialog permissionAlertDialog, MainActivity mainActivity) {
        super(1);
        this.a = permissionAlertDialog;
        this.b = mainActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Unit invoke(View view) {
        View it = view;
        Intrinsics.checkNotNullParameter(it, "it");
        ct.a("permissionWork permissionDialog exit NN finish permissionDialog.dismiss", new Object[0]);
        this.a.dismissAllowingStateLoss();
        this.b.finish();
        return Unit.INSTANCE;
    }
}
