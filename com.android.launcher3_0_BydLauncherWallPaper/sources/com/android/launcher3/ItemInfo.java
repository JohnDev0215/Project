package com.android.launcher3;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Process;
import android.os.UserHandle;
import android.telephony.SmsManager;
import com.android.launcher3.LauncherSettings;
import com.android.launcher3.util.ContentWriter;

/* JADX INFO: loaded from: classes4.dex */
public class ItemInfo {
    public static final int FOLDER_ADD_APP_ID = -2;
    public static final int NO_ID = -1;
    public int cellX;
    public int cellY;
    public int container;
    public CharSequence contentDescription;
    public int id;
    public int itemType;
    public int minSpanX;
    public int minSpanY;
    public int rank;
    public int screenId;
    public int spanX;
    public int spanY;
    public CharSequence title;
    public UserHandle user;

    public Intent getIntent() {
        return null;
    }

    public boolean isDisabled() {
        return false;
    }

    public ItemInfo() {
        this.id = -1;
        this.container = -1;
        this.screenId = -1;
        this.cellX = -1;
        this.cellY = -1;
        this.spanX = 1;
        this.spanY = 1;
        this.minSpanX = 1;
        this.minSpanY = 1;
        this.rank = 0;
        this.user = Process.myUserHandle();
    }

    ItemInfo(ItemInfo info) {
        this.id = -1;
        this.container = -1;
        this.screenId = -1;
        this.cellX = -1;
        this.cellY = -1;
        this.spanX = 1;
        this.spanY = 1;
        this.minSpanX = 1;
        this.minSpanY = 1;
        this.rank = 0;
        copyFrom(info);
    }

    public void copyFrom(ItemInfo info) {
        this.id = info.id;
        this.cellX = info.cellX;
        this.cellY = info.cellY;
        this.spanX = info.spanX;
        this.spanY = info.spanY;
        this.rank = info.rank;
        this.screenId = info.screenId;
        this.itemType = info.itemType;
        this.container = info.container;
        this.user = info.user;
        this.contentDescription = info.contentDescription;
    }

    public ComponentName getTargetComponent() {
        Intent intent = getIntent();
        if (intent != null) {
            return intent.getComponent();
        }
        return null;
    }

    public void writeToValues(ContentWriter writer) {
        writer.put(LauncherSettings.Favorites.ITEM_TYPE, Integer.valueOf(this.itemType)).put(LauncherSettings.Favorites.CONTAINER, Integer.valueOf(this.container)).put(LauncherSettings.Favorites.SCREEN, Integer.valueOf(this.screenId)).put(LauncherSettings.Favorites.CELLX, Integer.valueOf(this.cellX)).put(LauncherSettings.Favorites.CELLY, Integer.valueOf(this.cellY)).put(LauncherSettings.Favorites.SPANX, Integer.valueOf(this.spanX)).put(LauncherSettings.Favorites.SPANY, Integer.valueOf(this.spanY)).put("rank", Integer.valueOf(this.rank));
    }

    public void readFromValues(ContentValues values) {
        this.itemType = values.getAsInteger(LauncherSettings.Favorites.ITEM_TYPE).intValue();
        this.container = values.getAsInteger(LauncherSettings.Favorites.CONTAINER).intValue();
        this.screenId = values.getAsInteger(LauncherSettings.Favorites.SCREEN).intValue();
        this.cellX = values.getAsInteger(LauncherSettings.Favorites.CELLX).intValue();
        this.cellY = values.getAsInteger(LauncherSettings.Favorites.CELLY).intValue();
        this.spanX = values.getAsInteger(LauncherSettings.Favorites.SPANX).intValue();
        this.spanY = values.getAsInteger(LauncherSettings.Favorites.SPANY).intValue();
        this.rank = values.getAsInteger("rank").intValue();
    }

    public void onAddToDatabase(ContentWriter writer) {
        if (this.screenId == -201) {
            throw new RuntimeException("Screen id should not be EXTRA_EMPTY_SCREEN_ID");
        }
        writeToValues(writer);
        writer.put("profileId", this.user);
    }

    public final String toString() {
        return getClass().getSimpleName() + "(" + dumpProperties() + ")";
    }

    protected String dumpProperties() {
        return "id=" + this.id + " type=" + LauncherSettings.Favorites.itemTypeToString(this.itemType) + " container=" + LauncherSettings.Favorites.containerToString(this.container) + " screen=" + this.screenId + " cell(" + this.cellX + SmsManager.REGEX_PREFIX_DELIMITER + this.cellY + ") span(" + this.spanX + SmsManager.REGEX_PREFIX_DELIMITER + this.spanY + ") minSpan(" + this.minSpanX + SmsManager.REGEX_PREFIX_DELIMITER + this.minSpanY + ") rank=" + this.rank + " user=" + this.user + " title=" + ((Object) this.title);
    }

    public int getViewId() {
        return this.id;
    }
}
