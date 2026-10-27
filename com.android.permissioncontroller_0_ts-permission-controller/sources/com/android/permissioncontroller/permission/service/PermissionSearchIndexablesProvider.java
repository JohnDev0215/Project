package com.android.permissioncontroller.permission.service;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.SearchIndexablesContract;
import android.util.Log;
import com.android.car.ui.core.SearchResultsProvider;
import com.android.launcher3.icons.cache.BaseIconCache;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class PermissionSearchIndexablesProvider extends BaseSearchIndexablesProvider {
    public static final String ACTION_MANAGE_PERMISSION_APPS = "com.android.permissioncontroller.settingssearch.action.MANAGE_PERMISSION_APPS";
    private static final String LOG_TAG = PermissionSearchIndexablesProvider.class.getSimpleName();

    public Cursor queryRawData(String[] projection) {
        Context context = getContext();
        PackageManager pm = context.getPackageManager();
        List<String> permissionGroupNames = Utils.getPlatformPermissionGroups();
        MatrixCursor cursor = new MatrixCursor(SearchIndexablesContract.INDEXABLES_RAW_COLUMNS);
        int numPermissionGroups = permissionGroupNames.size();
        for (int i = 0; i < numPermissionGroups; i++) {
            String groupName = permissionGroupNames.get(i);
            CharSequence label = getPermissionGroupLabel(groupName, pm);
            cursor.newRow().add("rank", 0).add(SearchResultsProvider.TITLE, label).add(BaseIconCache.IconDB.COLUMN_KEYWORDS, ((Object) label) + ", " + context.getString(R.string.permission_search_keyword)).add("key", createRawDataKey(groupName, context)).add("intentAction", ACTION_MANAGE_PERMISSION_APPS);
        }
        return cursor;
    }

    private CharSequence getPermissionGroupLabel(String groupName, PackageManager pm) {
        try {
            return pm.getPermissionGroupInfo(groupName, 0).loadLabel(pm);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(LOG_TAG, "Cannot find group label for " + groupName, e);
            return null;
        }
    }
}
