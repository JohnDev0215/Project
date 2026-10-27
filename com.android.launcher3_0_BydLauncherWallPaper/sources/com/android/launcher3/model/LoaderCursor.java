package com.android.launcher3.model;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.UserHandle;
import android.provider.SettingsStringUtil;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.LongSparseArray;
import com.android.internal.content.NativeLibraryHelper;
import com.android.launcher3.AppInfo;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherSettings;
import com.android.launcher3.Utilities;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.icons.BitmapInfo;
import com.android.launcher3.icons.IconCache;
import com.android.launcher3.icons.LauncherIcons;
import com.android.launcher3.logging.FileLog;
import com.android.launcher3.util.ContentWriter;
import com.android.launcher3.util.GridOccupancy;
import com.android.launcher3.util.IntArray;
import com.android.launcher3.util.IntSparseArrayMap;
import java.net.URISyntaxException;
import java.security.InvalidParameterException;

/* JADX INFO: loaded from: classes4.dex */
public class LoaderCursor extends CursorWrapper {
    private static final String TAG = "LoaderCursor";
    public final LongSparseArray<UserHandle> allUsers;
    private final int cellXIndex;
    private final int cellYIndex;
    public int container;
    private final int containerIndex;
    private final int iconIndex;
    private final int iconPackageIndex;
    private final int iconResourceIndex;
    public int id;
    private final int idIndex;
    private final int intentIndex;
    public int itemType;
    private final int itemTypeIndex;
    private final IntArray itemsToRemove;
    private final Context mContext;
    private final InvariantDeviceProfile mIDP;
    private final IconCache mIconCache;
    private final PackageManager mPM;
    private final IntSparseArrayMap<GridOccupancy> occupied;
    private final int profileIdIndex;
    public int restoreFlag;
    private final int restoredIndex;
    private final IntArray restoredRows;
    private final int screenIndex;
    public long serialNumber;
    public final int titleIndex;
    public UserHandle user;

    public LoaderCursor(Cursor c, LauncherAppState app) {
        super(c);
        this.allUsers = new LongSparseArray<>();
        this.itemsToRemove = new IntArray();
        this.restoredRows = new IntArray();
        this.occupied = new IntSparseArrayMap<>();
        Context context = app.getContext();
        this.mContext = context;
        this.mIconCache = app.getIconCache();
        this.mIDP = app.getInvariantDeviceProfile();
        this.mPM = context.getPackageManager();
        this.iconIndex = getColumnIndexOrThrow("icon");
        this.iconPackageIndex = getColumnIndexOrThrow(LauncherSettings.Favorites.ICON_PACKAGE);
        this.iconResourceIndex = getColumnIndexOrThrow(LauncherSettings.Favorites.ICON_RESOURCE);
        this.titleIndex = getColumnIndexOrThrow("title");
        this.idIndex = getColumnIndexOrThrow("_id");
        this.containerIndex = getColumnIndexOrThrow(LauncherSettings.Favorites.CONTAINER);
        this.itemTypeIndex = getColumnIndexOrThrow(LauncherSettings.Favorites.ITEM_TYPE);
        this.screenIndex = getColumnIndexOrThrow(LauncherSettings.Favorites.SCREEN);
        this.cellXIndex = getColumnIndexOrThrow(LauncherSettings.Favorites.CELLX);
        this.cellYIndex = getColumnIndexOrThrow(LauncherSettings.Favorites.CELLY);
        this.profileIdIndex = getColumnIndexOrThrow("profileId");
        this.restoredIndex = getColumnIndexOrThrow("restored");
        this.intentIndex = getColumnIndexOrThrow("intent");
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public boolean moveToNext() {
        boolean zMoveToNext = super.moveToNext();
        if (zMoveToNext) {
            this.itemType = getInt(this.itemTypeIndex);
            this.container = getInt(this.containerIndex);
            this.id = getInt(this.idIndex);
            long j = getInt(this.profileIdIndex);
            this.serialNumber = j;
            this.user = this.allUsers.get(j);
            this.restoreFlag = getInt(this.restoredIndex);
        }
        return zMoveToNext;
    }

    public Intent parseIntent() {
        String string = getString(this.intentIndex);
        try {
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            return Intent.parseUri(string, 0);
        } catch (URISyntaxException unused) {
            Log.e(TAG, "Error parsing Intent");
            return null;
        }
    }

    public WorkspaceItemInfo loadSimpleWorkspaceItem() {
        WorkspaceItemInfo workspaceItemInfo = new WorkspaceItemInfo();
        workspaceItemInfo.user = this.user;
        workspaceItemInfo.itemType = this.itemType;
        workspaceItemInfo.title = getTitle();
        if (!loadIcon(workspaceItemInfo)) {
            workspaceItemInfo.applyFrom(this.mIconCache.getDefaultIcon(workspaceItemInfo.user));
        }
        return workspaceItemInfo;
    }

    protected boolean loadIcon(WorkspaceItemInfo info) {
        LauncherIcons launcherIconsObtain = LauncherIcons.obtain(this.mContext);
        try {
            boolean zLoadIcon = loadIcon(info, launcherIconsObtain);
            if (launcherIconsObtain != null) {
                launcherIconsObtain.close();
            }
            return zLoadIcon;
        } catch (Throwable th) {
            if (launcherIconsObtain != null) {
                try {
                    launcherIconsObtain.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    protected boolean loadIcon(WorkspaceItemInfo info, LauncherIcons li) {
        if (this.itemType == 1) {
            String string = getString(this.iconPackageIndex);
            String string2 = getString(this.iconResourceIndex);
            if (!TextUtils.isEmpty(string) || !TextUtils.isEmpty(string2)) {
                info.iconResource = new Intent.ShortcutIconResource();
                info.iconResource.packageName = string;
                info.iconResource.resourceName = string2;
                BitmapInfo bitmapInfoCreateIconBitmap = li.createIconBitmap(info.iconResource);
                if (bitmapInfoCreateIconBitmap != null) {
                    info.applyFrom(bitmapInfoCreateIconBitmap);
                    return true;
                }
            }
        }
        byte[] blob = getBlob(this.iconIndex);
        try {
            info.applyFrom(li.createIconBitmap(BitmapFactory.decodeByteArray(blob, 0, blob.length)));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Failed to decode byte array for info " + info, e);
            return false;
        }
    }

    private String getTitle() {
        String string = getString(this.titleIndex);
        return TextUtils.isEmpty(string) ? "" : Utilities.trim(string);
    }

    public WorkspaceItemInfo getRestoredItemInfo(Intent intent) {
        WorkspaceItemInfo workspaceItemInfo = new WorkspaceItemInfo();
        workspaceItemInfo.user = this.user;
        workspaceItemInfo.intent = intent;
        if (!loadIcon(workspaceItemInfo)) {
            this.mIconCache.getTitleAndIcon(workspaceItemInfo, false);
        }
        if (hasRestoreFlag(1)) {
            String title = getTitle();
            if (!TextUtils.isEmpty(title)) {
                workspaceItemInfo.title = Utilities.trim(title);
            }
        } else if (hasRestoreFlag(2)) {
            if (TextUtils.isEmpty(workspaceItemInfo.title)) {
                workspaceItemInfo.title = getTitle();
            }
        } else {
            throw new InvalidParameterException("Invalid restoreType " + this.restoreFlag);
        }
        workspaceItemInfo.contentDescription = this.mPM.getUserBadgedLabel(workspaceItemInfo.title, workspaceItemInfo.user);
        workspaceItemInfo.itemType = this.itemType;
        workspaceItemInfo.status = this.restoreFlag;
        return workspaceItemInfo;
    }

    public WorkspaceItemInfo getAppShortcutInfo(Intent intent, boolean allowMissingTarget, boolean useLowResIcon) {
        if (this.user == null) {
            Log.d(TAG, "Null user found in getShortcutInfo");
            return null;
        }
        ComponentName component = intent.getComponent();
        if (component == null) {
            Log.d(TAG, "Missing component found in getShortcutInfo");
            return null;
        }
        Intent intent2 = new Intent(Intent.ACTION_MAIN, (Uri) null);
        intent2.addCategory(Intent.CATEGORY_LAUNCHER);
        intent2.setComponent(component);
        LauncherActivityInfo launcherActivityInfoResolveActivity = LauncherAppsCompat.getInstance(this.mContext).resolveActivity(intent2, this.user);
        if (launcherActivityInfoResolveActivity == null && !allowMissingTarget) {
            Log.d(TAG, "Missing activity found in getShortcutInfo: " + component);
            return null;
        }
        WorkspaceItemInfo workspaceItemInfo = new WorkspaceItemInfo();
        workspaceItemInfo.itemType = 0;
        workspaceItemInfo.user = this.user;
        workspaceItemInfo.intent = intent2;
        this.mIconCache.getTitleAndIcon(workspaceItemInfo, launcherActivityInfoResolveActivity, useLowResIcon);
        if (this.mIconCache.isDefaultIcon(workspaceItemInfo.iconBitmap, this.user)) {
            loadIcon(workspaceItemInfo);
        }
        if (launcherActivityInfoResolveActivity != null) {
            AppInfo.updateRuntimeFlagsForActivityTarget(workspaceItemInfo, launcherActivityInfoResolveActivity);
        }
        if (TextUtils.isEmpty(workspaceItemInfo.title)) {
            workspaceItemInfo.title = getTitle();
        }
        if (workspaceItemInfo.title == null) {
            workspaceItemInfo.title = component.getClassName();
        }
        workspaceItemInfo.contentDescription = this.mPM.getUserBadgedLabel(workspaceItemInfo.title, workspaceItemInfo.user);
        return workspaceItemInfo;
    }

    public ContentWriter updater() {
        return new ContentWriter(this.mContext, new ContentWriter.CommitParams("_id= ?", new String[]{Integer.toString(this.id)}));
    }

    public void markDeleted(String reason) {
        FileLog.e(TAG, reason);
        this.itemsToRemove.add(this.id);
    }

    public boolean commitDeleted() {
        if (this.itemsToRemove.size() <= 0) {
            return false;
        }
        this.mContext.getContentResolver().delete(LauncherSettings.Favorites.CONTENT_URI, Utilities.createDbSelectionQuery("_id", this.itemsToRemove), null);
        return true;
    }

    public void markRestored() {
        if (this.restoreFlag != 0) {
            this.restoredRows.add(this.id);
            this.restoreFlag = 0;
        }
    }

    public boolean hasRestoreFlag(int flagMask) {
        return (this.restoreFlag & flagMask) != 0;
    }

    public void commitRestoredItems() {
        if (this.restoredRows.size() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("restored", (Integer) 0);
            this.mContext.getContentResolver().update(LauncherSettings.Favorites.CONTENT_URI, contentValues, Utilities.createDbSelectionQuery("_id", this.restoredRows), null);
        }
    }

    public boolean isOnWorkspaceOrHotseat() {
        int i = this.container;
        return i == -100 || i == -101;
    }

    public void applyCommonProperties(ItemInfo info) {
        info.id = this.id;
        info.container = this.container;
        info.screenId = getInt(this.screenIndex);
        info.cellX = getInt(this.cellXIndex);
        info.cellY = getInt(this.cellYIndex);
    }

    public void checkAndAddItem(ItemInfo info, BgDataModel dataModel) {
        if (checkItemPlacement(info)) {
            dataModel.addItem(this.mContext, info, false);
        } else {
            markDeleted("Item position overlap");
        }
    }

    protected boolean checkItemPlacement(ItemInfo item) {
        int i = item.screenId;
        if (item.container == -101) {
            GridOccupancy gridOccupancy = this.occupied.get(-101);
            if (item.screenId >= this.mIDP.numHotseatIcons) {
                Log.e(TAG, "Error loading shortcut " + item + " into hotseat position " + item.screenId + ", position out of bounds: (0 to " + (this.mIDP.numHotseatIcons - 1) + ")");
                return false;
            }
            if (gridOccupancy != null) {
                if (gridOccupancy.cells[item.screenId][0]) {
                    Log.e(TAG, "Error loading shortcut into hotseat " + item + " into position (" + item.screenId + SettingsStringUtil.DELIMITER + item.cellX + SmsManager.REGEX_PREFIX_DELIMITER + item.cellY + ") already occupied");
                    return false;
                }
                gridOccupancy.cells[item.screenId][0] = true;
                return true;
            }
            GridOccupancy gridOccupancy2 = new GridOccupancy(this.mIDP.numHotseatIcons, 1);
            gridOccupancy2.cells[item.screenId][0] = true;
            this.occupied.put(-101, gridOccupancy2);
            return true;
        }
        if (item.container != -100) {
            return true;
        }
        int i2 = this.mIDP.numColumns;
        int i3 = this.mIDP.numRows;
        if ((item.container == -100 && item.cellX < 0) || item.cellY < 0 || item.cellX + item.spanX > i2 || item.cellY + item.spanY > i3) {
            Log.e(TAG, "Error loading shortcut " + item + " into cell (" + i + NativeLibraryHelper.CLEAR_ABI_OVERRIDE + item.screenId + SettingsStringUtil.DELIMITER + item.cellX + SmsManager.REGEX_PREFIX_DELIMITER + item.cellY + ") out of screen bounds ( " + i2 + "x" + i3 + ")");
            return false;
        }
        if (!this.occupied.containsKey(item.screenId)) {
            int i4 = i2 + 1;
            GridOccupancy gridOccupancy3 = new GridOccupancy(i4, i3 + 1);
            if (item.screenId == 0) {
                gridOccupancy3.markCells(0, 0, i4, 1, false);
            }
            this.occupied.put(item.screenId, gridOccupancy3);
        }
        GridOccupancy gridOccupancy4 = this.occupied.get(item.screenId);
        if (gridOccupancy4.isRegionVacant(item.cellX, item.cellY, item.spanX, item.spanY)) {
            gridOccupancy4.markCells(item, true);
            return true;
        }
        Log.e(TAG, "Error loading shortcut " + item + " into cell (" + i + NativeLibraryHelper.CLEAR_ABI_OVERRIDE + item.screenId + SettingsStringUtil.DELIMITER + item.cellX + SmsManager.REGEX_PREFIX_DELIMITER + item.cellX + SmsManager.REGEX_PREFIX_DELIMITER + item.spanX + SmsManager.REGEX_PREFIX_DELIMITER + item.spanY + ") already occupied");
        return false;
    }
}
