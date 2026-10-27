package com.android.car.developeroptions.dashboard;

import android.content.ComponentName;
import android.content.Context;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import com.android.settingslib.applications.InterestingConfigChanges;
import com.android.settingslib.drawer.CategoryKey;
import com.android.settingslib.drawer.DashboardCategory;
import com.android.settingslib.drawer.Tile;
import com.android.settingslib.drawer.TileUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class CategoryManager {
    private static CategoryManager sInstance;
    private List<DashboardCategory> mCategories;
    private final InterestingConfigChanges mInterestingConfigChanges;
    private final Map<Pair<String, String>, Tile> mTileByComponentCache = new ArrayMap();
    private final Map<String, DashboardCategory> mCategoryByKeyMap = new ArrayMap();

    public static CategoryManager get(Context context) {
        if (sInstance == null) {
            sInstance = new CategoryManager(context);
        }
        return sInstance;
    }

    CategoryManager(Context context) {
        InterestingConfigChanges interestingConfigChanges = new InterestingConfigChanges();
        this.mInterestingConfigChanges = interestingConfigChanges;
        interestingConfigChanges.applyNewConfig(context.getResources());
    }

    public synchronized DashboardCategory getTilesByCategory(Context context, String str) {
        tryInitCategories(context);
        return this.mCategoryByKeyMap.get(str);
    }

    public synchronized List<DashboardCategory> getCategories(Context context) {
        tryInitCategories(context);
        return this.mCategories;
    }

    public synchronized void reloadAllCategories(Context context) {
        boolean zApplyNewConfig = this.mInterestingConfigChanges.applyNewConfig(context.getResources());
        this.mCategories = null;
        tryInitCategories(context, zApplyNewConfig);
    }

    public synchronized void updateCategoryFromBlacklist(Set<ComponentName> set) {
        if (this.mCategories == null) {
            Log.w("CategoryManager", "Category is null, skipping blacklist update");
        }
        for (int i = 0; i < this.mCategories.size(); i++) {
            DashboardCategory dashboardCategory = this.mCategories.get(i);
            int i2 = 0;
            while (i2 < dashboardCategory.getTilesCount()) {
                if (set.contains(dashboardCategory.getTile(i2).getIntent().getComponent())) {
                    int i3 = i2 - 1;
                    dashboardCategory.removeTile(i2);
                    i2 = i3;
                }
                i2++;
            }
        }
    }

    private synchronized void tryInitCategories(Context context) {
        tryInitCategories(context, false);
    }

    private synchronized void tryInitCategories(Context context, boolean z) {
        if (this.mCategories == null) {
            if (z) {
                this.mTileByComponentCache.clear();
            }
            this.mCategoryByKeyMap.clear();
            List<DashboardCategory> categories = TileUtils.getCategories(context, this.mTileByComponentCache);
            this.mCategories = categories;
            for (DashboardCategory dashboardCategory : categories) {
                this.mCategoryByKeyMap.put(dashboardCategory.key, dashboardCategory);
            }
            backwardCompatCleanupForCategory(this.mTileByComponentCache, this.mCategoryByKeyMap);
            sortCategories(context, this.mCategoryByKeyMap);
            filterDuplicateTiles(this.mCategoryByKeyMap);
        }
    }

    synchronized void backwardCompatCleanupForCategory(Map<Pair<String, String>, Tile> map, Map<String, DashboardCategory> map2) {
        HashMap map3 = new HashMap();
        for (Map.Entry<Pair<String, String>, Tile> entry : map.entrySet()) {
            String str = (String) entry.getKey().first;
            List arrayList = (List) map3.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList();
                map3.put(str, arrayList);
            }
            arrayList.add(entry.getValue());
        }
        Iterator it = map3.entrySet().iterator();
        while (it.hasNext()) {
            List<Tile> list = (List) ((Map.Entry) it.next()).getValue();
            Iterator it2 = list.iterator();
            boolean z = true;
            boolean z2 = false;
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                if (!CategoryKey.KEY_COMPAT_MAP.containsKey(((Tile) it2.next()).getCategory())) {
                    break;
                } else {
                    z2 = true;
                }
            }
            if (z2 && !z) {
                for (Tile tile : list) {
                    String str2 = CategoryKey.KEY_COMPAT_MAP.get(tile.getCategory());
                    tile.setCategory(str2);
                    DashboardCategory dashboardCategory = map2.get(str2);
                    if (dashboardCategory == null) {
                        dashboardCategory = new DashboardCategory(str2);
                        map2.put(str2, dashboardCategory);
                    }
                    dashboardCategory.addTile(tile);
                }
            }
        }
    }

    synchronized void sortCategories(Context context, Map<String, DashboardCategory> map) {
        Iterator<Map.Entry<String, DashboardCategory>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().sortTiles(context.getPackageName());
        }
    }

    synchronized void filterDuplicateTiles(Map<String, DashboardCategory> map) {
        Iterator<Map.Entry<String, DashboardCategory>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            DashboardCategory value = it.next().getValue();
            int tilesCount = value.getTilesCount();
            ArraySet arraySet = new ArraySet();
            for (int i = tilesCount - 1; i >= 0; i--) {
                ComponentName component = value.getTile(i).getIntent().getComponent();
                if (arraySet.contains(component)) {
                    value.removeTile(i);
                } else {
                    arraySet.add(component);
                }
            }
        }
    }
}
