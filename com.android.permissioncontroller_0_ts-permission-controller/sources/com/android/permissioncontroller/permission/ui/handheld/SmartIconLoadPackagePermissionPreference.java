package com.android.permissioncontroller.permission.ui.handheld;

import android.R;
import android.app.Application;
import android.content.Context;
import android.os.UserHandle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SmartIconLoadPackagePermissionPreference.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/handheld/SmartIconLoadPackagePermissionPreference;", "Landroidx/preference/Preference;", "app", "Landroid/app/Application;", "packageName", "", "user", "Landroid/os/UserHandle;", "context", "Landroid/content/Context;", "(Landroid/app/Application;Ljava/lang/String;Landroid/os/UserHandle;Landroid/content/Context;)V", "titleContentDescription", "", "onBindViewHolder", "", "holder", "Landroidx/preference/PreferenceViewHolder;", "setTitleContentDescription", "contentDescription", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public class SmartIconLoadPackagePermissionPreference extends Preference {
    private final Application app;
    private final String packageName;
    private CharSequence titleContentDescription;
    private final UserHandle user;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmartIconLoadPackagePermissionPreference(Application app, String packageName, UserHandle user, Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.app = app;
        this.packageName = packageName;
        this.user = user;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        super.onBindViewHolder(holder);
        View viewFindViewById = holder.findViewById(R.id.title);
        if (viewFindViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        TextView title = (TextView) viewFindViewById;
        title.setMaxLines(1);
        title.setEllipsize(TextUtils.TruncateAt.END);
        View viewFindViewById2 = holder.findViewById(R.id.icon);
        if (viewFindViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        ImageView imageView = (ImageView) viewFindViewById2;
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        imageView.setMaxWidth(context.getResources().getDimensionPixelSize(com.android.permissioncontroller.R.dimen.secondary_app_icon_size));
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        imageView.setMaxHeight(context2.getResources().getDimensionPixelSize(com.android.permissioncontroller.R.dimen.secondary_app_icon_size));
        imageView.setImageDrawable(KotlinUtils.INSTANCE.getBadgedPackageIcon(this.app, this.packageName, this.user));
        imageView.setVisibility(0);
        View imageFrame = holder.findViewById(com.android.permissioncontroller.R.id.icon_frame);
        if (imageFrame == null) {
            imageFrame = holder.findViewById(16908350);
        }
        if (imageFrame != null) {
            imageFrame.setVisibility(0);
        }
        View it = holder.findViewById(R.id.title);
        if (it != null) {
            it.setContentDescription(this.titleContentDescription);
        }
    }

    public final void setTitleContentDescription(CharSequence contentDescription) {
        Intrinsics.checkParameterIsNotNull(contentDescription, "contentDescription");
        this.titleContentDescription = contentDescription;
    }
}
