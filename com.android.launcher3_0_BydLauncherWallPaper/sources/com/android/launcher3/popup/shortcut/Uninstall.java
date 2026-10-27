package com.android.launcher3.popup.shortcut;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.net.Uri;
import android.os.UserHandle;
import android.view.View;
import android.widget.Toast;
import com.android.launcher3.BaseDraggingActivity;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.R;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.popup.SystemShortcut;
import com.android.launcher3.util.Logger;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: classes4.dex */
public class Uninstall extends SystemShortcut {
    private static final String TAG = "Uninstall";

    public Uninstall() {
        super(R.drawable.ic_uninstall_shadow, R.string.uninstall_drop_target_label);
    }

    @Override // com.android.launcher3.popup.SystemShortcut
    public View.OnClickListener getOnClickListener(final BaseDraggingActivity activity, final ItemInfo itemInfo) {
        if (getUninstallTarget(activity, itemInfo) == null) {
            return null;
        }
        return new View.OnClickListener() { // from class: com.android.launcher3.popup.shortcut.-$$Lambda$Uninstall$1X1FIiET15-LchBghMIYtLe4_o8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$getOnClickListener$0$Uninstall(activity, itemInfo, view);
            }
        };
    }

    public /* synthetic */ void lambda$getOnClickListener$0$Uninstall(BaseDraggingActivity baseDraggingActivity, ItemInfo itemInfo, View view) {
        dismissTaskMenuView(baseDraggingActivity);
        uninstallApp(baseDraggingActivity, itemInfo);
    }

    private void uninstallApp(BaseDraggingActivity activity, ItemInfo itemInfo) {
        ComponentName uninstallTarget = getUninstallTarget(activity, itemInfo);
        if (uninstallTarget == null) {
            Toast.makeText(activity, R.string.uninstall_system_app_text, 0).show();
            return;
        }
        try {
            activity.startActivity(Intent.parseUri(activity.getString(R.string.delete_package_intent), 0).setData(Uri.fromParts("package", uninstallTarget.getPackageName(), uninstallTarget.getClassName())).putExtra(Intent.EXTRA_USER, itemInfo.user));
        } catch (URISyntaxException unused) {
            Logger.e(TAG, "Failed to parse intent to start uninstall activity for item=" + itemInfo);
        }
    }

    private static ComponentName getUninstallTarget(Activity activity, ItemInfo item) {
        UserHandle userHandle;
        Intent intent;
        LauncherActivityInfo launcherActivityInfoResolveActivity;
        if (item == null || item.itemType != 0) {
            userHandle = null;
            intent = null;
        } else {
            intent = item.getIntent();
            userHandle = item.user;
        }
        if (intent == null || (launcherActivityInfoResolveActivity = LauncherAppsCompat.getInstance(activity).resolveActivity(intent, userHandle)) == null || (launcherActivityInfoResolveActivity.getApplicationInfo().flags & 1) != 0) {
            return null;
        }
        return launcherActivityInfoResolveActivity.getComponentName();
    }
}
