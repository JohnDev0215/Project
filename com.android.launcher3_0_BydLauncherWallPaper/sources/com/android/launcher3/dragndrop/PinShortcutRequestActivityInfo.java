package com.android.launcher3.dragndrop;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.os.Process;
import com.android.launcher3.FastBitmapDrawable;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.R;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.compat.LauncherAppsCompatVO;
import com.android.launcher3.compat.ShortcutConfigActivityInfo;
import com.android.launcher3.icons.IconCache;

/* JADX INFO: loaded from: classes4.dex */
class PinShortcutRequestActivityInfo extends ShortcutConfigActivityInfo {
    private static final String DUMMY_COMPONENT_CLASS = "pinned-shortcut";
    private final Context mContext;
    private final ShortcutInfo mInfo;
    private final LauncherApps.PinItemRequest mRequest;

    @Override // com.android.launcher3.compat.ShortcutConfigActivityInfo
    public int getItemType() {
        return 6;
    }

    @Override // com.android.launcher3.compat.ShortcutConfigActivityInfo
    public boolean isPersistable() {
        return false;
    }

    @Override // com.android.launcher3.compat.ShortcutConfigActivityInfo
    public boolean startConfigActivity(Activity activity, int requestCode) {
        return false;
    }

    public PinShortcutRequestActivityInfo(LauncherApps.PinItemRequest request, Context context) {
        super(new ComponentName(request.getShortcutInfo().getPackage(), DUMMY_COMPONENT_CLASS), request.getShortcutInfo().getUserHandle());
        this.mRequest = request;
        this.mInfo = request.getShortcutInfo();
        this.mContext = context;
    }

    @Override // com.android.launcher3.icons.ComponentWithLabel
    public CharSequence getLabel(PackageManager pm) {
        return this.mInfo.getShortLabel();
    }

    @Override // com.android.launcher3.compat.ShortcutConfigActivityInfo
    public Drawable getFullResIcon(IconCache cache) {
        Drawable shortcutIconDrawable = ((LauncherApps) this.mContext.getSystemService(LauncherApps.class)).getShortcutIconDrawable(this.mInfo, LauncherAppState.getIDP(this.mContext).fillResIconDpi);
        return shortcutIconDrawable == null ? new FastBitmapDrawable(cache.getDefaultIcon(Process.myUserHandle())) : shortcutIconDrawable;
    }

    @Override // com.android.launcher3.compat.ShortcutConfigActivityInfo
    public WorkspaceItemInfo createWorkspaceItemInfo() {
        return LauncherAppsCompatVO.createWorkspaceItemFromPinItemRequest(this.mContext, this.mRequest, this.mContext.getResources().getInteger(R.integer.config_dropAnimMaxDuration) + 500 + 150);
    }
}
