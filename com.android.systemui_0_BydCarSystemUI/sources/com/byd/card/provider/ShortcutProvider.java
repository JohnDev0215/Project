package com.byd.card.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.sqlite.db.SupportSQLiteQueryBuilder;
import com.byd.card.bean.Constant;
import com.byd.card.bean.ShortcutInfo;
import com.byd.card.db.CardDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ShortcutProvider extends ContentProvider {
    public static final String AUTHORITY = "com.byd.shortcut";
    public static final String PATH = "shortcut_info";
    public static final String SCHEME = "content";
    public static final int SHORTCUT = 1;
    public static final List<String> SHORTCUT_WHITELIST;
    private static final String TAG = "ShortcutProvider";
    private static final UriMatcher mMatcher;

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    static {
        ArrayList arrayList = new ArrayList();
        SHORTCUT_WHITELIST = arrayList;
        UriMatcher uriMatcher = new UriMatcher(-1);
        mMatcher = uriMatcher;
        uriMatcher.addURI(AUTHORITY, "shortcut_info", 1);
        arrayList.add("com.gpack.agent");
        arrayList.add(Constant.PackageName.PACKAGE_BROWSER);
    }

    public static Uri getUri(String tableName) {
        return new Uri.Builder().authority(AUTHORITY).path(tableName).scheme(SCHEME).build();
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Log.d(TAG, "onCreate!");
        return true;
    }

    private String genExclusiveSelection(String selection, String[] selectionArgs, List<String> outSelectionArgs) {
        String str;
        String[] strArr;
        if (TextUtils.isEmpty(selection)) {
            strArr = new String[]{getCallingPackage()};
            str = "origin=?";
        } else {
            String str2 = "origin=? and " + selection;
            if (selectionArgs == null || selectionArgs.length <= 0) {
                str = str2;
                strArr = new String[]{getCallingPackage()};
            } else {
                String[] strArr2 = new String[selectionArgs.length + 1];
                strArr2[0] = getCallingPackage();
                System.arraycopy(selectionArgs, 0, strArr2, 1, selectionArgs.length);
                str = str2;
                strArr = strArr2;
            }
        }
        if (outSelectionArgs != null) {
            outSelectionArgs.addAll(Arrays.asList(strArr));
        }
        return str;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        try {
            if (!checkPermission(uri, "query")) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            String strGenExclusiveSelection = genExclusiveSelection(selection, selectionArgs, arrayList);
            String[] strArr = (String[]) arrayList.toArray(new String[0]);
            String str = TAG;
            Log.d(str, "query selection = " + selection + ";  selectionArgs = " + (selectionArgs == null ? null : new ArrayList(Arrays.asList(selectionArgs))));
            Log.d(str, "query newSelection = " + strGenExclusiveSelection + ";  newSelectionArgs = " + arrayList);
            return CardDatabase.getInstance().getOpenHelper().getReadableDatabase().query(SupportSQLiteQueryBuilder.builder("shortcut_info").selection(strGenExclusiveSelection, strArr).columns(projection).orderBy(sortOrder).create());
        } catch (Exception e) {
            Log.e(TAG, "query exception = " + e);
            return null;
        }
    }

    private boolean checkPermission(Uri uri, String action) {
        String callingPackage = getCallingPackage();
        if (TextUtils.isEmpty(callingPackage) || !SHORTCUT_WHITELIST.contains(callingPackage)) {
            Log.w(TAG, action + ", not whiteList! reject " + callingPackage);
            return false;
        }
        if (mMatcher.match(uri) == 1) {
            return true;
        }
        Log.w(TAG, action + ", not shortcut! reject " + callingPackage);
        return false;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        try {
        } catch (Exception e) {
            Log.e(TAG, "insert exception = " + e);
        }
        if (!checkPermission(uri, "insert")) {
            return uri;
        }
        ShortcutInfo shortcutInfo = new ShortcutInfo(values.getAsString(ShortcutInfo.Column.COLUMN_IDENTIFY), values.getAsString(ShortcutInfo.Column.COLUMN_TITLE), getCallingPackage(), values.getAsByteArray(ShortcutInfo.Column.COLUMN_ICON), values.getAsString(ShortcutInfo.Column.COLUMN_INTENT), values.containsKey(ShortcutInfo.Column.COLUMN_P_GEAR_FLAG) ? values.getAsInteger(ShortcutInfo.Column.COLUMN_P_GEAR_FLAG).intValue() : 0, values.getAsString(ShortcutInfo.Column.COLUMN_RESERVE1), values.getAsString(ShortcutInfo.Column.COLUMN_RESERVE2), values.getAsString(ShortcutInfo.Column.COLUMN_RESERVE3));
        if (!TextUtils.isEmpty(shortcutInfo.getIdentify()) && !TextUtils.isEmpty(shortcutInfo.getTitle()) && !TextUtils.isEmpty(shortcutInfo.getIntent()) && shortcutInfo.getIcon() != null) {
            Log.d(TAG, "insert info = " + shortcutInfo);
            CardDatabase.getInstance().getShortcutInfoDao().insert(shortcutInfo);
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;
        }
        Log.w(TAG, "insert fail! info invalid! info = " + shortcutInfo);
        return uri;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        try {
            if (!checkPermission(uri, "delete")) {
                return 0;
            }
            ArrayList arrayList = new ArrayList();
            String strGenExclusiveSelection = genExclusiveSelection(selection, selectionArgs, arrayList);
            int iDelete = CardDatabase.getInstance().getOpenHelper().getWritableDatabase().delete("shortcut_info", strGenExclusiveSelection, (String[]) arrayList.toArray(new String[0]));
            ArrayList arrayList2 = null;
            if (iDelete > 0) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            String str = TAG;
            StringBuilder sbAppend = new StringBuilder().append("delete selection = ").append(selection).append(";  selectionArgs = ");
            if (selectionArgs != null) {
                arrayList2 = new ArrayList(Arrays.asList(selectionArgs));
            }
            Log.d(str, sbAppend.append(arrayList2).toString());
            Log.d(str, "delete newSelection = " + strGenExclusiveSelection + ";  newSelectionArgs = " + arrayList + "  num = " + iDelete);
            return iDelete;
        } catch (Exception e) {
            Log.e(TAG, "delete exception = " + e);
            return 0;
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        try {
            if (!checkPermission(uri, "update")) {
                return 0;
            }
            ArrayList arrayList = new ArrayList();
            String strGenExclusiveSelection = genExclusiveSelection(selection, selectionArgs, arrayList);
            int iUpdate = CardDatabase.getInstance().getOpenHelper().getWritableDatabase().update("shortcut_info", 5, values, strGenExclusiveSelection, (String[]) arrayList.toArray(new String[0]));
            ArrayList arrayList2 = null;
            if (iUpdate > 0) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            String str = TAG;
            StringBuilder sbAppend = new StringBuilder().append("update selection = ").append(selection).append(";  selectionArgs = ");
            if (selectionArgs != null) {
                arrayList2 = new ArrayList(Arrays.asList(selectionArgs));
            }
            Log.d(str, sbAppend.append(arrayList2).toString());
            Log.d(str, "update newSelection = " + strGenExclusiveSelection + ";  newSelectionArgs = " + arrayList + " num = " + iUpdate);
            return iUpdate;
        } catch (Exception e) {
            Log.e(TAG, "update exception = " + e);
            return 0;
        }
    }
}
