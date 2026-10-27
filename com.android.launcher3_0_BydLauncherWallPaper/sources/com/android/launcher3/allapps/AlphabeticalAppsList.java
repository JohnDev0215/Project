package com.android.launcher3.allapps;

import android.Manifest;
import android.content.Context;
import android.util.Log;
import com.android.launcher3.AppInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.Utilities;
import com.android.launcher3.allapps.AllAppsStore;
import com.android.launcher3.compat.AlphabeticIndexCompat;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.testing.TestProtocol;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.ItemInfoMatcher;
import com.android.launcher3.util.LabelComparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes4.dex */
public class AlphabeticalAppsList implements AllAppsStore.OnUpdateListener {
    private static final int FAST_SCROLL_FRACTION_DISTRIBUTE_BY_NUM_SECTIONS = 1;
    private static final int FAST_SCROLL_FRACTION_DISTRIBUTE_BY_ROWS_FRACTION = 0;
    public static final String TAG = "AlphabeticalAppsList";
    private AllAppsGridAdapter mAdapter;
    private final AllAppsStore mAllAppsStore;
    private AppInfoComparator mAppNameComparator;
    private AlphabeticIndexCompat mIndexer;
    private final boolean mIsWork;
    private ItemInfoMatcher mItemFilter;
    private final Launcher mLauncher;
    private int mNumAppRowsInAdapter;
    private final int mNumAppsPerRow;
    private ArrayList<ComponentKey> mSearchResults;
    private final int mFastScrollDistributionMode = 1;
    private final List<AppInfo> mApps = new ArrayList();
    private final List<AppInfo> mFilteredApps = new ArrayList();
    private final ArrayList<AdapterItem> mAdapterItems = new ArrayList<>();
    private final List<FastScrollSectionInfo> mFastScrollerSections = new ArrayList();
    private HashMap<CharSequence, String> mCachedSectionNames = new HashMap<>();

    public static class FastScrollSectionInfo {
        public AdapterItem fastScrollToItem;
        public String sectionName;
        public float touchFraction;

        public FastScrollSectionInfo(String sectionName) {
            this.sectionName = sectionName;
        }
    }

    public static class AdapterItem {
        public int position;
        public int rowAppIndex;
        public int rowIndex;
        public int viewType;
        public String sectionName = null;
        public AppInfo appInfo = null;
        public int appIndex = -1;

        public static AdapterItem asApp(int pos, String sectionName, AppInfo appInfo, int appIndex) {
            AdapterItem adapterItem = new AdapterItem();
            adapterItem.viewType = 2;
            adapterItem.position = pos;
            adapterItem.sectionName = sectionName;
            adapterItem.appInfo = appInfo;
            adapterItem.appIndex = appIndex;
            return adapterItem;
        }

        public static AdapterItem asEmptySearch(int pos) {
            AdapterItem adapterItem = new AdapterItem();
            adapterItem.viewType = 4;
            adapterItem.position = pos;
            return adapterItem;
        }

        public static AdapterItem asAllAppsDivider(int pos) {
            AdapterItem adapterItem = new AdapterItem();
            adapterItem.viewType = 16;
            adapterItem.position = pos;
            return adapterItem;
        }

        public static AdapterItem asMarketSearch(int pos) {
            AdapterItem adapterItem = new AdapterItem();
            adapterItem.viewType = 8;
            adapterItem.position = pos;
            return adapterItem;
        }

        public static AdapterItem asWorkTabFooter(int pos) {
            AdapterItem adapterItem = new AdapterItem();
            adapterItem.viewType = 32;
            adapterItem.position = pos;
            return adapterItem;
        }
    }

    public AlphabeticalAppsList(Context context, AllAppsStore appsStore, boolean isWork) {
        this.mAllAppsStore = appsStore;
        Launcher launcher = Launcher.getLauncher(context);
        this.mLauncher = launcher;
        this.mIndexer = new AlphabeticIndexCompat(context);
        this.mAppNameComparator = new AppInfoComparator(context);
        this.mIsWork = isWork;
        this.mNumAppsPerRow = launcher.getDeviceProfile().inv.numColumns;
        appsStore.addUpdateListener(this);
    }

    public void updateItemFilter(ItemInfoMatcher itemFilter) {
        this.mItemFilter = itemFilter;
        onAppsUpdated();
    }

    public void setAdapter(AllAppsGridAdapter adapter) {
        this.mAdapter = adapter;
    }

    public List<AppInfo> getApps() {
        return this.mApps;
    }

    public List<FastScrollSectionInfo> getFastScrollerSections() {
        return this.mFastScrollerSections;
    }

    public List<AdapterItem> getAdapterItems() {
        return this.mAdapterItems;
    }

    public int getNumAppRows() {
        return this.mNumAppRowsInAdapter;
    }

    public int getNumFilteredApps() {
        return this.mFilteredApps.size();
    }

    public boolean hasFilter() {
        return this.mSearchResults != null;
    }

    public boolean hasNoFilteredResults() {
        return this.mSearchResults != null && this.mFilteredApps.isEmpty();
    }

    public boolean setOrderedFilter(ArrayList<ComponentKey> f) {
        ArrayList<ComponentKey> arrayList = this.mSearchResults;
        boolean z = false;
        if (arrayList == f) {
            return false;
        }
        if (arrayList != null && arrayList.equals(f)) {
            z = true;
        }
        this.mSearchResults = f;
        onAppsUpdated();
        return !z;
    }

    @Override // com.android.launcher3.allapps.AllAppsStore.OnUpdateListener
    public void onAppsUpdated() {
        this.mApps.clear();
        for (AppInfo appInfo : this.mAllAppsStore.getApps()) {
            ItemInfoMatcher itemInfoMatcher = this.mItemFilter;
            if (itemInfoMatcher == null || itemInfoMatcher.matches(appInfo, null) || hasFilter()) {
                this.mApps.add(appInfo);
            }
        }
        Collections.sort(this.mApps, this.mAppNameComparator);
        if (this.mLauncher.getResources().getConfiguration().locale.equals(Locale.SIMPLIFIED_CHINESE)) {
            TreeMap treeMap = new TreeMap(new LabelComparator());
            for (AppInfo appInfo2 : this.mApps) {
                String andUpdateCachedSectionName = getAndUpdateCachedSectionName(appInfo2.title);
                ArrayList arrayList = (ArrayList) treeMap.get(andUpdateCachedSectionName);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    treeMap.put(andUpdateCachedSectionName, arrayList);
                }
                arrayList.add(appInfo2);
            }
            this.mApps.clear();
            Iterator it = treeMap.entrySet().iterator();
            while (it.hasNext()) {
                this.mApps.addAll((Collection) ((Map.Entry) it.next()).getValue());
            }
        } else {
            Iterator<AppInfo> it2 = this.mApps.iterator();
            while (it2.hasNext()) {
                getAndUpdateCachedSectionName(it2.next().title);
            }
        }
        updateAdapterItems();
    }

    private void updateAdapterItems() {
        refillAdapterItems();
        refreshRecyclerView();
    }

    private void refreshRecyclerView() {
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_START_TAG, "refreshRecyclerView @ " + Log.getStackTraceString(new Throwable()));
        }
        AllAppsGridAdapter allAppsGridAdapter = this.mAdapter;
        if (allAppsGridAdapter != null) {
            allAppsGridAdapter.notifyDataSetChanged();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.lang.String] */
    private void refillAdapterItems() {
        int i;
        this.mFilteredApps.clear();
        this.mFastScrollerSections.clear();
        this.mAdapterItems.clear();
        ?? r1 = 0;
        FastScrollSectionInfo fastScrollSectionInfo = null;
        int i2 = 0;
        int i3 = 0;
        for (AppInfo appInfo : getFiltersAppInfos()) {
            ?? andUpdateCachedSectionName = getAndUpdateCachedSectionName(appInfo.title);
            boolean zEquals = andUpdateCachedSectionName.equals(r1);
            ?? r12 = r1;
            if (!zEquals) {
                FastScrollSectionInfo fastScrollSectionInfo2 = new FastScrollSectionInfo(andUpdateCachedSectionName);
                this.mFastScrollerSections.add(fastScrollSectionInfo2);
                fastScrollSectionInfo = fastScrollSectionInfo2;
                r12 = andUpdateCachedSectionName;
            }
            int i4 = i2 + 1;
            int i5 = i3 + 1;
            AdapterItem adapterItemAsApp = AdapterItem.asApp(i2, andUpdateCachedSectionName, appInfo, i3);
            if (fastScrollSectionInfo.fastScrollToItem == null) {
                fastScrollSectionInfo.fastScrollToItem = adapterItemAsApp;
            }
            this.mAdapterItems.add(adapterItemAsApp);
            this.mFilteredApps.add(appInfo);
            i2 = i4;
            i3 = i5;
            r1 = r12;
        }
        if (hasFilter()) {
            if (hasNoFilteredResults()) {
                i = i2 + 1;
                this.mAdapterItems.add(AdapterItem.asEmptySearch(i2));
            } else {
                i = i2 + 1;
                this.mAdapterItems.add(AdapterItem.asAllAppsDivider(i2));
            }
            i2 = i + 1;
            this.mAdapterItems.add(AdapterItem.asMarketSearch(i));
        }
        if (this.mNumAppsPerRow != 0) {
            int i6 = -1;
            int i7 = 0;
            int i8 = 0;
            for (AdapterItem adapterItem : this.mAdapterItems) {
                adapterItem.rowIndex = 0;
                if (AllAppsGridAdapter.isDividerViewType(adapterItem.viewType)) {
                    i7 = 0;
                } else if (AllAppsGridAdapter.isIconViewType(adapterItem.viewType)) {
                    if (i7 % this.mNumAppsPerRow == 0) {
                        i6++;
                        i8 = 0;
                    }
                    adapterItem.rowIndex = i6;
                    adapterItem.rowAppIndex = i8;
                    i7++;
                    i8++;
                }
            }
            this.mNumAppRowsInAdapter = i6 + 1;
            float size = 1.0f / this.mFastScrollerSections.size();
            float f = 0.0f;
            for (FastScrollSectionInfo fastScrollSectionInfo3 : this.mFastScrollerSections) {
                if (!AllAppsGridAdapter.isIconViewType(fastScrollSectionInfo3.fastScrollToItem.viewType)) {
                    fastScrollSectionInfo3.touchFraction = 0.0f;
                } else {
                    fastScrollSectionInfo3.touchFraction = f;
                    f += size;
                }
            }
        }
        if (shouldShowWorkFooter()) {
            this.mAdapterItems.add(AdapterItem.asWorkTabFooter(i2));
        }
    }

    private boolean shouldShowWorkFooter() {
        return this.mIsWork && Utilities.ATLEAST_P && (DeepShortcutManager.getInstance(this.mLauncher).hasHostPermission() || this.mLauncher.checkSelfPermission(Manifest.permission.MODIFY_QUIET_MODE) == 0);
    }

    private List<AppInfo> getFiltersAppInfos() {
        if (this.mSearchResults == null) {
            return this.mApps;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<ComponentKey> it = this.mSearchResults.iterator();
        while (it.hasNext()) {
            AppInfo app = this.mAllAppsStore.getApp(it.next());
            if (app != null) {
                arrayList.add(app);
            }
        }
        return arrayList;
    }

    private String getAndUpdateCachedSectionName(CharSequence title) {
        String str = this.mCachedSectionNames.get(title);
        if (str != null) {
            return str;
        }
        String strComputeSectionName = this.mIndexer.computeSectionName(title);
        this.mCachedSectionNames.put(title, strComputeSectionName);
        return strComputeSectionName;
    }
}
