package com.android.launcher3;

import android.graphics.Bitmap;
import com.android.launcher3.icons.BitmapInfo;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ItemInfoWithIcon extends ItemInfo {
    public static final int FLAG_ADAPTIVE_ICON = 256;
    public static final int FLAG_DISABLED_BY_PUBLISHER = 16;
    public static final int FLAG_DISABLED_LOCKED_USER = 32;
    public static final int FLAG_DISABLED_MASK = 63;
    public static final int FLAG_DISABLED_NOT_AVAILABLE = 2;
    public static final int FLAG_DISABLED_QUIET_USER = 8;
    public static final int FLAG_DISABLED_SAFEMODE = 1;
    public static final int FLAG_DISABLED_SUSPENDED = 4;
    public static final int FLAG_ICON_BADGED = 512;
    public static final int FLAG_SYSTEM_MASK = 192;
    public static final int FLAG_SYSTEM_NO = 128;
    public static final int FLAG_SYSTEM_YES = 64;
    public Bitmap iconBitmap;
    public int iconColor;
    public int runtimeStatusFlags;

    protected ItemInfoWithIcon() {
        this.runtimeStatusFlags = 0;
    }

    protected ItemInfoWithIcon(ItemInfoWithIcon info) {
        super(info);
        this.runtimeStatusFlags = 0;
        this.iconBitmap = info.iconBitmap;
        this.iconColor = info.iconColor;
        this.runtimeStatusFlags = info.runtimeStatusFlags;
    }

    @Override // com.android.launcher3.ItemInfo
    public boolean isDisabled() {
        return (this.runtimeStatusFlags & 63) != 0;
    }

    public boolean usingLowResIcon() {
        return this.iconBitmap == BitmapInfo.LOW_RES_ICON;
    }

    public void applyFrom(BitmapInfo info) {
        this.iconBitmap = info.icon;
        this.iconColor = info.color;
    }
}
