package com.android.launcher3;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.text.TextUtils;
import com.android.launcher3.LauncherSettings;
import com.android.launcher3.shortcuts.ShortcutKey;
import com.android.launcher3.util.ContentWriter;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class WorkspaceItemInfo extends ItemInfoWithIcon {
    public static final int DEFAULT = 0;
    public static final int FLAG_AUTOINSTALL_ICON = 2;
    public static final int FLAG_INSTALL_SESSION_ACTIVE = 4;
    public static final int FLAG_RESTORED_ICON = 1;
    public static final int FLAG_RESTORE_STARTED = 8;
    public static final int FLAG_SUPPORTS_WEB_UI = 16;
    public CharSequence disabledMessage;
    public Intent.ShortcutIconResource iconResource;
    public Intent intent;
    private int mInstallProgress;
    public int status;

    public WorkspaceItemInfo() {
        this.itemType = 1;
    }

    public WorkspaceItemInfo(WorkspaceItemInfo info) {
        super(info);
        this.title = info.title;
        this.intent = new Intent(info.intent);
        this.iconResource = info.iconResource;
        this.status = info.status;
        this.mInstallProgress = info.mInstallProgress;
    }

    public WorkspaceItemInfo(AppInfo info) {
        super(info);
        this.title = Utilities.trim(info.title);
        this.intent = new Intent(info.intent);
    }

    public WorkspaceItemInfo(ShortcutInfo shortcutInfo, Context context) {
        this.user = shortcutInfo.getUserHandle();
        this.itemType = 6;
        updateFromDeepShortcutInfo(shortcutInfo, context);
    }

    @Override // com.android.launcher3.ItemInfo
    public void onAddToDatabase(ContentWriter writer) {
        super.onAddToDatabase(writer);
        writer.put("title", this.title).put("intent", getIntent()).put("restored", Integer.valueOf(this.status));
        if (!usingLowResIcon()) {
            writer.putIcon(this.iconBitmap, this.user);
        }
        Intent.ShortcutIconResource shortcutIconResource = this.iconResource;
        if (shortcutIconResource != null) {
            writer.put(LauncherSettings.Favorites.ICON_PACKAGE, shortcutIconResource.packageName).put(LauncherSettings.Favorites.ICON_RESOURCE, this.iconResource.resourceName);
        }
    }

    @Override // com.android.launcher3.ItemInfo
    public Intent getIntent() {
        return this.intent;
    }

    public boolean hasStatusFlag(int flag) {
        return (this.status & flag) != 0;
    }

    public final boolean isPromise() {
        return hasStatusFlag(3);
    }

    public boolean hasPromiseIconUi() {
        return isPromise() && !hasStatusFlag(16);
    }

    public int getInstallProgress() {
        return this.mInstallProgress;
    }

    public void setInstallProgress(int progress) {
        this.mInstallProgress = progress;
        this.status |= 4;
    }

    public void updateFromDeepShortcutInfo(ShortcutInfo shortcutInfo, Context context) {
        this.intent = ShortcutKey.makeIntent(shortcutInfo);
        this.title = shortcutInfo.getShortLabel();
        CharSequence longLabel = shortcutInfo.getLongLabel();
        if (TextUtils.isEmpty(longLabel)) {
            longLabel = shortcutInfo.getShortLabel();
        }
        this.contentDescription = context.getPackageManager().getUserBadgedLabel(longLabel, this.user);
        if (shortcutInfo.isEnabled()) {
            this.runtimeStatusFlags &= -17;
        } else {
            this.runtimeStatusFlags |= 16;
        }
        this.disabledMessage = shortcutInfo.getDisabledMessage();
    }

    public String getDeepShortcutId() {
        if (this.itemType == 6) {
            return getIntent().getStringExtra(ShortcutKey.EXTRA_SHORTCUT_ID);
        }
        return null;
    }

    @Override // com.android.launcher3.ItemInfo
    public ComponentName getTargetComponent() {
        ComponentName targetComponent = super.getTargetComponent();
        if (targetComponent != null || (this.itemType != 1 && !hasStatusFlag(16))) {
            return targetComponent;
        }
        String str = this.intent.getPackage();
        if (str == null) {
            return null;
        }
        return new ComponentName(str, ".");
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o != null && getClass() == o.getClass() && this.id == ((WorkspaceItemInfo) o).id;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.id));
    }
}
