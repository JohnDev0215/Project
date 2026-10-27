package com.android.car.settings.search;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.SearchIndexableResource;
import android.provider.SearchIndexablesContract;
import android.provider.SearchIndexablesProvider;
import com.android.car.settings.common.Logger;
import com.android.settingslib.search.Indexable;
import com.android.settingslib.search.SearchIndexableRaw;
import com.android.settingslib.search.SearchIndexableResources;
import com.android.settingslib.search.SearchIndexableResourcesAuto;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: classes5.dex */
public class CarSettingsSearchIndexablesProvider extends SearchIndexablesProvider {
    private static final Logger LOG = new Logger((Class<?>) CarSettingsSearchIndexablesProvider.class);
    private SearchIndexableResources mSearchIndexableResources;

    public Cursor queryXmlResources(String[] projection) {
        final MatrixCursor cursor = new MatrixCursor(SearchIndexablesContract.INDEXABLES_XML_RES_COLUMNS);
        getResources().getProviderValues().stream().map($$Lambda$_spiwCrcbSeMlBfhGo4fvFwkk0.INSTANCE).map(new Function() { // from class: com.android.car.settings.search.-$$Lambda$CarSettingsSearchIndexablesProvider$IyQmhYpP0uFJzUABs35bNeu0MAA
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$queryXmlResources$0$CarSettingsSearchIndexablesProvider((Indexable.SearchIndexProvider) obj);
            }
        }).filter($$Lambda$wXXh49TgHwGJpKCtXLpXf0v4kts.INSTANCE).forEach(new Consumer() { // from class: com.android.car.settings.search.-$$Lambda$CarSettingsSearchIndexablesProvider$_ohBvEg28z-VJXJbWBaNckIE1DQ
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((List) obj).forEach(new Consumer() { // from class: com.android.car.settings.search.-$$Lambda$CarSettingsSearchIndexablesProvider$DSz-Jm8-lppshOOCDbWRj6p7Fbg
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        CarSettingsSearchIndexablesProvider.lambda$queryXmlResources$1(matrixCursor, (SearchIndexableResource) obj2);
                    }
                });
            }
        });
        return cursor;
    }

    public /* synthetic */ List lambda$queryXmlResources$0$CarSettingsSearchIndexablesProvider(Indexable.SearchIndexProvider i) {
        return i.getXmlResourcesToIndex(getContext(), true);
    }

    static /* synthetic */ void lambda$queryXmlResources$1(MatrixCursor cursor, SearchIndexableResource val) {
        Object[] ref = new Object[SearchIndexablesContract.INDEXABLES_XML_RES_COLUMNS.length];
        ref[0] = Integer.valueOf(val.rank);
        ref[1] = Integer.valueOf(val.xmlResId);
        ref[2] = val.className;
        ref[3] = Integer.valueOf(val.iconResId);
        ref[4] = val.intentAction;
        ref[5] = val.intentTargetPackage;
        ref[6] = val.intentTargetClass;
        cursor.addRow(ref);
    }

    public Cursor queryRawData(String[] projection) {
        final MatrixCursor cursor = new MatrixCursor(SearchIndexablesContract.INDEXABLES_RAW_COLUMNS);
        getResources().getProviderValues().stream().map($$Lambda$_spiwCrcbSeMlBfhGo4fvFwkk0.INSTANCE).map(new Function() { // from class: com.android.car.settings.search.-$$Lambda$CarSettingsSearchIndexablesProvider$hMjgc-P_sGrDeo4Fy0dpLNcjxow
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$queryRawData$3$CarSettingsSearchIndexablesProvider((Indexable.SearchIndexProvider) obj);
            }
        }).filter($$Lambda$wXXh49TgHwGJpKCtXLpXf0v4kts.INSTANCE).forEach(new Consumer() { // from class: com.android.car.settings.search.-$$Lambda$CarSettingsSearchIndexablesProvider$WaQ_2yJZso9xXgJ2JRyjYTBSEkU
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((List) obj).forEach(new Consumer() { // from class: com.android.car.settings.search.-$$Lambda$CarSettingsSearchIndexablesProvider$qQNgJw13-stxH9QAGdBo6tIYQvM
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        matrixCursor.addRow(CarSettingsSearchIndexablesProvider.createIndexableRawColumnObjects((SearchIndexableRaw) obj2));
                    }
                });
            }
        });
        return cursor;
    }

    public /* synthetic */ List lambda$queryRawData$3$CarSettingsSearchIndexablesProvider(Indexable.SearchIndexProvider p) {
        return p.getRawDataToIndex(getContext(), true);
    }

    private static Object[] createIndexableRawColumnObjects(SearchIndexableRaw raw) {
        Object[] ref = new Object[SearchIndexablesContract.INDEXABLES_RAW_COLUMNS.length];
        ref[1] = raw.title;
        ref[2] = raw.summaryOn;
        ref[3] = raw.summaryOff;
        ref[4] = raw.entries;
        ref[5] = raw.keywords;
        ref[6] = raw.screenTitle;
        ref[7] = raw.className;
        ref[8] = Integer.valueOf(raw.iconResId);
        ref[9] = raw.intentAction;
        ref[10] = raw.intentTargetPackage;
        ref[11] = raw.intentTargetClass;
        ref[12] = raw.key;
        ref[13] = Integer.valueOf(raw.userId);
        return ref;
    }

    public Cursor queryNonIndexableKeys(String[] projection) {
        final MatrixCursor cursor = new MatrixCursor(SearchIndexablesContract.NON_INDEXABLES_KEYS_COLUMNS);
        getResources().getProviderValues().stream().map($$Lambda$_spiwCrcbSeMlBfhGo4fvFwkk0.INSTANCE).map(new Function() { // from class: com.android.car.settings.search.-$$Lambda$CarSettingsSearchIndexablesProvider$wBs3m5prRxopmX6Q-WNm1uM5BwU
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$queryNonIndexableKeys$6$CarSettingsSearchIndexablesProvider((Indexable.SearchIndexProvider) obj);
            }
        }).filter($$Lambda$wXXh49TgHwGJpKCtXLpXf0v4kts.INSTANCE).forEach(new Consumer() { // from class: com.android.car.settings.search.-$$Lambda$CarSettingsSearchIndexablesProvider$L8f_tWhpJuHWHUjX-gQ_oz6kuss
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((List) obj).forEach(new Consumer() { // from class: com.android.car.settings.search.-$$Lambda$CarSettingsSearchIndexablesProvider$9s3RomHfeZEzC5DCECTJ-h0lB18
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        CarSettingsSearchIndexablesProvider.lambda$queryNonIndexableKeys$7(matrixCursor, (String) obj2);
                    }
                });
            }
        });
        return cursor;
    }

    public /* synthetic */ List lambda$queryNonIndexableKeys$6$CarSettingsSearchIndexablesProvider(Indexable.SearchIndexProvider provider) {
        try {
            return provider.getNonIndexableKeys(getContext());
        } catch (Exception e) {
            LOG.w("Could not get keys for provider " + provider.toString());
            return null;
        }
    }

    static /* synthetic */ void lambda$queryNonIndexableKeys$7(MatrixCursor cursor, String key) {
        Object[] ref = new Object[SearchIndexablesContract.NON_INDEXABLES_KEYS_COLUMNS.length];
        ref[0] = key;
        cursor.addRow(ref);
    }

    public boolean onCreate() {
        return true;
    }

    private SearchIndexableResources getResources() {
        if (this.mSearchIndexableResources == null) {
            this.mSearchIndexableResources = new SearchIndexableResourcesAuto();
        }
        return this.mSearchIndexableResources;
    }

    void setResources(SearchIndexableResources resources) {
        this.mSearchIndexableResources = resources;
    }
}
