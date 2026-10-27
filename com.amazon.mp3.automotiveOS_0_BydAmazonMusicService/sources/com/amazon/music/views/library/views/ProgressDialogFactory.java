package com.amazon.music.views.library.views;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$layout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProgressDialogFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/views/library/views/ProgressDialogFactory;", "", "()V", "createProgressDialog", "Landroid/app/ProgressDialog;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ProgressDialogFactory {
    public static final ProgressDialogFactory INSTANCE = new ProgressDialogFactory();

    private ProgressDialogFactory() {
    }

    public final ProgressDialog createProgressDialog(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Window window = progressDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(activity.getColor(R$color.transparent)));
        }
        progressDialog.setContentView(R$layout.dmmviewlibrary_dialog_loader);
        return progressDialog;
    }
}
