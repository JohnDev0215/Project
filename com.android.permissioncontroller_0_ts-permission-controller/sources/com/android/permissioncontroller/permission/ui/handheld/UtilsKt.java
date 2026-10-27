package com.android.permissioncontroller.permission.ui.handheld;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.FragmentKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Utils.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"pressBack", "", "Landroidx/fragment/app/Fragment;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 2, mv = {1, 1, 16})
public final class UtilsKt {
    public static final void pressBack(Fragment pressBack) {
        FragmentActivity it;
        Intrinsics.checkParameterIsNotNull(pressBack, "$this$pressBack");
        boolean wasBackExecuted = FragmentKt.findNavController(pressBack).popBackStack();
        if (wasBackExecuted || (it = pressBack.getActivity()) == null) {
            return;
        }
        it.finish();
    }
}
