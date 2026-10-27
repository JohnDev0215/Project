package com.android.car.settings.common;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IContentProvider;
import android.content.Intent;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Pair;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.apps.common.util.Themes;
import com.android.car.settings.R;
import com.android.car.settings.common.ExtraSettingsPreferenceController;
import com.android.settingslib.drawer.SwitchesProvider;
import com.android.settingslib.drawer.TileUtils;
import com.android.settingslib.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes5.dex */
public class ExtraSettingsPreferenceController extends PreferenceController<PreferenceGroup> {
    private static final Logger LOG = new Logger((Class<?>) ExtraSettingsPreferenceController.class);
    static final String META_DATA_DISTRACTION_OPTIMIZED = "distractionOptimized";
    private ContentResolver mContentResolver;
    private Context mContext;
    private ExtraSettingsLoader mExtraSettingsLoader;
    List<DynamicDataObserver> mObservers;
    private boolean mSettingsLoaded;

    public ExtraSettingsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions restrictionInfo) {
        super(context, preferenceKey, fragmentController, restrictionInfo);
        this.mObservers = new ArrayList();
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        this.mExtraSettingsLoader = new ExtraSettingsLoader(context);
    }

    public void setExtraSettingsLoader(ExtraSettingsLoader extraSettingsLoader) {
        this.mExtraSettingsLoader = extraSettingsLoader;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onApplyUxRestrictions(CarUxRestrictions uxRestrictions) {
        for (int i = 0; i < getPreference().getPreferenceCount(); i++) {
            boolean restricted = false;
            Preference preference = getPreference().getPreference(i);
            if (uxRestrictions.isRequiresDistractionOptimization() && !preference.getExtras().getBoolean(META_DATA_DISTRACTION_OPTIMIZED) && getAvailabilityStatus() != 4) {
                restricted = true;
            }
            preference.setEnabled(getAvailabilityStatus() != 4);
            restrictPreference(preference, restricted);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preference) {
        Map<Preference, Bundle> preferenceBundleMap = this.mExtraSettingsLoader.loadPreferences(preference.getIntent());
        if (!this.mSettingsLoaded) {
            addExtraSettings(preferenceBundleMap);
            this.mSettingsLoaded = true;
        }
        preference.setVisible(preference.getPreferenceCount() > 0);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mObservers.forEach(new Consumer() { // from class: com.android.car.settings.common.-$$Lambda$ExtraSettingsPreferenceController$_FBi7N-qC7uU_lwRiW0-MUAwX9c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onStartInternal$0$ExtraSettingsPreferenceController((ExtraSettingsPreferenceController.DynamicDataObserver) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onStartInternal$0$ExtraSettingsPreferenceController(DynamicDataObserver observer) {
        observer.register(this.mContentResolver, true);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mObservers.forEach(new Consumer() { // from class: com.android.car.settings.common.-$$Lambda$ExtraSettingsPreferenceController$KpH34faDf3mQFNfLqPGuOcLb4-8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onStopInternal$1$ExtraSettingsPreferenceController((ExtraSettingsPreferenceController.DynamicDataObserver) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onStopInternal$1$ExtraSettingsPreferenceController(DynamicDataObserver observer) {
        observer.register(this.mContentResolver, false);
    }

    protected void addExtraSettings(Map<Preference, Bundle> preferenceBundleMap) {
        for (Preference setting : preferenceBundleMap.keySet()) {
            Bundle metaData = preferenceBundleMap.get(setting);
            boolean distractionOptimized = false;
            if (metaData.containsKey(META_DATA_DISTRACTION_OPTIMIZED)) {
                distractionOptimized = metaData.getBoolean(META_DATA_DISTRACTION_OPTIMIZED);
            }
            setting.getExtras().putBoolean(META_DATA_DISTRACTION_OPTIMIZED, distractionOptimized);
            setting.getExtras().putBoolean(TileUtils.META_DATA_PREFERENCE_ICON_TINTABLE, ExtraSettingsUtil.isIconTintable(metaData));
            getDynamicData(setting, metaData);
            getPreference().addPreference(setting);
        }
    }

    protected void getDynamicData(Preference preference, Bundle metaData) {
        if (metaData.containsKey(TileUtils.META_DATA_PREFERENCE_TITLE_URI)) {
            preference.setTitle(R.string.empty_placeholder);
            Uri uri = ExtraSettingsUtil.getCompleteUri(metaData, TileUtils.META_DATA_PREFERENCE_TITLE_URI, SwitchesProvider.METHOD_GET_DYNAMIC_TITLE);
            refreshTitle(uri, preference);
            this.mObservers.add(new DynamicDataObserver(SwitchesProvider.METHOD_GET_DYNAMIC_TITLE, uri, preference));
        }
        if (metaData.containsKey(TileUtils.META_DATA_PREFERENCE_SUMMARY_URI)) {
            preference.setSummary(R.string.empty_placeholder);
            Uri uri2 = ExtraSettingsUtil.getCompleteUri(metaData, TileUtils.META_DATA_PREFERENCE_SUMMARY_URI, SwitchesProvider.METHOD_GET_DYNAMIC_SUMMARY);
            refreshSummary(uri2, preference);
            this.mObservers.add(new DynamicDataObserver(SwitchesProvider.METHOD_GET_DYNAMIC_SUMMARY, uri2, preference));
        }
        if (metaData.containsKey(TileUtils.META_DATA_PREFERENCE_ICON_URI)) {
            preference.setIcon(R.drawable.ic_placeholder);
            Uri uri3 = ExtraSettingsUtil.getCompleteUri(metaData, TileUtils.META_DATA_PREFERENCE_ICON_URI, SwitchesProvider.METHOD_GET_PROVIDER_ICON);
            refreshIcon(uri3, preference);
            this.mObservers.add(new DynamicDataObserver(SwitchesProvider.METHOD_GET_PROVIDER_ICON, uri3, preference));
        }
    }

    void executeBackgroundTask(Runnable r) {
        ThreadUtils.postOnBackgroundThread(r);
    }

    void executeUiTask(Runnable r) {
        ThreadUtils.postOnMainThread(r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshTitle(final Uri uri, final Preference preference) {
        executeBackgroundTask(new Runnable() { // from class: com.android.car.settings.common.-$$Lambda$ExtraSettingsPreferenceController$8kiq_TBbCR8wJzKpwRYl6CGZFwY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$refreshTitle$3$ExtraSettingsPreferenceController(uri, preference);
            }
        });
    }

    public /* synthetic */ void lambda$refreshTitle$3$ExtraSettingsPreferenceController(Uri uri, final Preference preference) {
        Map<String, IContentProvider> providerMap = new ArrayMap<>();
        final String titleFromUri = TileUtils.getTextFromUri(this.mContext, uri, providerMap, TileUtils.META_DATA_PREFERENCE_TITLE);
        if (!TextUtils.equals(titleFromUri, preference.getTitle())) {
            executeUiTask(new Runnable() { // from class: com.android.car.settings.common.-$$Lambda$ExtraSettingsPreferenceController$F6SooFNU180loB3eqrHxkSTdVGM
                @Override // java.lang.Runnable
                public final void run() {
                    preference.setTitle(titleFromUri);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshSummary(final Uri uri, final Preference preference) {
        executeBackgroundTask(new Runnable() { // from class: com.android.car.settings.common.-$$Lambda$ExtraSettingsPreferenceController$hR_zePLfxQ-vyNHbQRGQC2uiKt0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$refreshSummary$5$ExtraSettingsPreferenceController(uri, preference);
            }
        });
    }

    public /* synthetic */ void lambda$refreshSummary$5$ExtraSettingsPreferenceController(Uri uri, final Preference preference) {
        Map<String, IContentProvider> providerMap = new ArrayMap<>();
        final String summaryFromUri = TileUtils.getTextFromUri(this.mContext, uri, providerMap, TileUtils.META_DATA_PREFERENCE_SUMMARY);
        if (!TextUtils.equals(summaryFromUri, preference.getSummary())) {
            executeUiTask(new Runnable() { // from class: com.android.car.settings.common.-$$Lambda$ExtraSettingsPreferenceController$WdwEN5IDgQrYdKP-E0mHG0yGnME
                @Override // java.lang.Runnable
                public final void run() {
                    preference.setSummary(summaryFromUri);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshIcon(final Uri uri, final Preference preference) {
        executeBackgroundTask(new Runnable() { // from class: com.android.car.settings.common.-$$Lambda$ExtraSettingsPreferenceController$2HUhcS3SCscbvpnKPGuyxwYa0ok
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$refreshIcon$7$ExtraSettingsPreferenceController(preference, uri);
            }
        });
    }

    public /* synthetic */ void lambda$refreshIcon$7$ExtraSettingsPreferenceController(final Preference preference, Uri uri) {
        final Drawable icon;
        Intent intent = preference.getIntent();
        String packageName = null;
        if (!TextUtils.isEmpty(intent.getPackage())) {
            packageName = intent.getPackage();
        } else if (intent.getComponent() != null) {
            packageName = intent.getComponent().getPackageName();
        }
        Map<String, IContentProvider> providerMap = new ArrayMap<>();
        Pair<String, Integer> iconInfo = TileUtils.getIconFromUri(this.mContext, packageName, uri, providerMap);
        if (iconInfo != null) {
            icon = ExtraSettingsUtil.loadDrawableFromPackage(this.mContext, (String) iconInfo.first, ((Integer) iconInfo.second).intValue());
        } else {
            LOG.w("Failed to get icon from uri " + uri);
            icon = this.mContext.getDrawable(R.drawable.ic_settings_gear);
            LOG.d("use default icon.");
        }
        if (icon != null) {
            executeUiTask(new Runnable() { // from class: com.android.car.settings.common.-$$Lambda$ExtraSettingsPreferenceController$fDQzlYmNAoJrkpSjyexQtBqfOWQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$refreshIcon$6$ExtraSettingsPreferenceController(preference, icon);
                }
            });
        }
    }

    public /* synthetic */ void lambda$refreshIcon$6$ExtraSettingsPreferenceController(Preference preference, Drawable icon) {
        preference.setIcon(icon);
        if (preference.getExtras().getBoolean(TileUtils.META_DATA_PREFERENCE_ICON_TINTABLE)) {
            preference.getIcon().setTintList(Themes.getAttrColorStateList(this.mContext, R.attr.iconColor));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class DynamicDataObserver extends ContentObserver {
        private final String mMethod;
        private final Preference mPreference;
        private final Uri mUri;

        DynamicDataObserver(String method, Uri uri, Preference preference) {
            super(new Handler(Looper.getMainLooper()));
            this.mMethod = method;
            this.mUri = uri;
            this.mPreference = preference;
        }

        void register(ContentResolver cr, boolean register) {
            if (register) {
                cr.registerContentObserver(this.mUri, false, this);
            } else {
                cr.unregisterContentObserver(this);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0036  */
        @Override // android.database.ContentObserver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onChange(boolean r6) {
            /*
                r5 = this;
                java.lang.String r0 = r5.mMethod
                int r1 = r0.hashCode()
                r2 = -2097433649(0xffffffff82fbb3cf, float:-3.698434E-37)
                r3 = 2
                r4 = 1
                if (r1 == r2) goto L2c
                r2 = -1844463779(0xffffffff920fb75d, float:-4.5348856E-28)
                if (r1 == r2) goto L22
                r2 = 1207101568(0x47f2e880, float:124369.0)
                if (r1 == r2) goto L18
            L17:
                goto L36
            L18:
                java.lang.String r1 = "getProviderIcon"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L17
                r0 = r3
                goto L37
            L22:
                java.lang.String r1 = "getDynamicSummary"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L17
                r0 = r4
                goto L37
            L2c:
                java.lang.String r1 = "getDynamicTitle"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L17
                r0 = 0
                goto L37
            L36:
                r0 = -1
            L37:
                if (r0 == 0) goto L52
                if (r0 == r4) goto L48
                if (r0 == r3) goto L3e
                goto L5c
            L3e:
                com.android.car.settings.common.ExtraSettingsPreferenceController r0 = com.android.car.settings.common.ExtraSettingsPreferenceController.this
                android.net.Uri r1 = r5.mUri
                androidx.preference.Preference r2 = r5.mPreference
                com.android.car.settings.common.ExtraSettingsPreferenceController.access$200(r0, r1, r2)
                goto L5c
            L48:
                com.android.car.settings.common.ExtraSettingsPreferenceController r0 = com.android.car.settings.common.ExtraSettingsPreferenceController.this
                android.net.Uri r1 = r5.mUri
                androidx.preference.Preference r2 = r5.mPreference
                com.android.car.settings.common.ExtraSettingsPreferenceController.access$100(r0, r1, r2)
                goto L5c
            L52:
                com.android.car.settings.common.ExtraSettingsPreferenceController r0 = com.android.car.settings.common.ExtraSettingsPreferenceController.this
                android.net.Uri r1 = r5.mUri
                androidx.preference.Preference r2 = r5.mPreference
                com.android.car.settings.common.ExtraSettingsPreferenceController.access$000(r0, r1, r2)
            L5c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.car.settings.common.ExtraSettingsPreferenceController.DynamicDataObserver.onChange(boolean):void");
        }
    }
}
