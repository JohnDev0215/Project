package com.android.permissioncontroller.role.service;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Binder;
import android.provider.SearchIndexablesContract;
import android.util.ArrayMap;
import com.android.car.ui.core.SearchResultsProvider;
import com.android.launcher3.icons.cache.BaseIconCache;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.service.BaseSearchIndexablesProvider;
import com.android.permissioncontroller.role.model.Role;
import com.android.permissioncontroller.role.model.Roles;

/* JADX INFO: loaded from: classes8.dex */
public class RoleSearchIndexablesProvider extends BaseSearchIndexablesProvider {
    public static final String ACTION_MANAGE_DEFAULT_APP = "com.android.permissioncontroller.settingssearch.action.MANAGE_DEFAULT_APP";
    public static final String ACTION_MANAGE_SPECIAL_APP_ACCESS = "com.android.permissioncontroller.settingssearch.action.MANAGE_SPECIAL_APP_ACCESS";

    public Cursor queryRawData(String[] projection) {
        int i;
        MatrixCursor cursor = new MatrixCursor(SearchIndexablesContract.INDEXABLES_RAW_COLUMNS);
        Context context = getContext();
        ArrayMap<String, Role> roles = Roles.get(context);
        int rolesSize = roles.size();
        for (int i2 = 0; i2 < rolesSize; i2++) {
            Role role = roles.valueAt(i2);
            long token = Binder.clearCallingIdentity();
            try {
                if (role.isAvailable(context) && role.isVisible(context)) {
                    Binder.restoreCallingIdentity(token);
                    String label = context.getString(role.getLabelResource());
                    int searchKeywordsResource = role.getSearchKeywordsResource();
                    boolean isExclusive = role.isExclusive();
                    MatrixCursor.RowBuilder rowBuilderAdd = cursor.newRow().add("rank", 0).add(SearchResultsProvider.TITLE, label);
                    StringBuilder sb = new StringBuilder();
                    sb.append(label);
                    sb.append(searchKeywordsResource != 0 ? ", " + context.getString(searchKeywordsResource) : "");
                    sb.append(", ");
                    if (isExclusive) {
                        i = R.string.default_app_search_keyword;
                    } else {
                        i = R.string.special_app_access_search_keyword;
                    }
                    sb.append(context.getString(i));
                    rowBuilderAdd.add(BaseIconCache.IconDB.COLUMN_KEYWORDS, sb.toString()).add("key", createRawDataKey(role.getName(), context)).add("intentAction", isExclusive ? ACTION_MANAGE_DEFAULT_APP : ACTION_MANAGE_SPECIAL_APP_ACCESS);
                }
            } finally {
                Binder.restoreCallingIdentity(token);
            }
        }
        return cursor;
    }
}
