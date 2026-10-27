package com.android.permissioncontroller.permission.ui.handheld;

import android.app.Application;
import android.content.Context;
import android.os.UserHandle;
import android.view.View;
import android.widget.ImageButton;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.R;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoRevokePermissionPreference.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u000e@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0018"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/handheld/AutoRevokePermissionPreference;", "Lcom/android/permissioncontroller/permission/ui/handheld/SmartIconLoadPackagePermissionPreference;", "app", "Landroid/app/Application;", "packageName", "", "user", "Landroid/os/UserHandle;", "context", "Landroid/content/Context;", "(Landroid/app/Application;Ljava/lang/String;Landroid/os/UserHandle;Landroid/content/Context;)V", "removeButton", "Landroid/widget/ImageButton;", "listener", "Landroid/view/View$OnClickListener;", "removeClickListener", "getRemoveClickListener", "()Landroid/view/View$OnClickListener;", "setRemoveClickListener", "(Landroid/view/View$OnClickListener;)V", "onBindViewHolder", "", "holder", "Landroidx/preference/PreferenceViewHolder;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AutoRevokePermissionPreference extends SmartIconLoadPackagePermissionPreference {
    private ImageButton removeButton;
    private View.OnClickListener removeClickListener;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoRevokePermissionPreference(Application app, String packageName, UserHandle user, Context context) {
        super(app, packageName, user, context);
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        Intrinsics.checkParameterIsNotNull(context, "context");
        setWidgetLayoutResource(R.xml.uninstall_button_preference_widget);
    }

    public final View.OnClickListener getRemoveClickListener() {
        return this.removeClickListener;
    }

    public final void setRemoveClickListener(View.OnClickListener listener) {
        ImageButton imageButton = this.removeButton;
        if (imageButton != null) {
            imageButton.setOnClickListener(listener);
        }
        this.removeClickListener = listener;
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.SmartIconLoadPackagePermissionPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        super.onBindViewHolder(holder);
        View viewFindViewById = holder.findViewById(R.id.uninstall_button);
        if (viewFindViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageButton");
        }
        ImageButton imageButton = (ImageButton) viewFindViewById;
        this.removeButton = imageButton;
        if (imageButton != null) {
            imageButton.setOnClickListener(this.removeClickListener);
        }
    }
}
