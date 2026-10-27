package com.android.car.developeroptions.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.core.PreferenceControllerListHelper;
import com.android.car.developeroptions.core.SettingsBaseActivity;
import com.android.car.developeroptions.dashboard.SummaryLoader;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.drawer.DashboardCategory;
import com.android.settingslib.drawer.Tile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public abstract class DashboardFragment extends SettingsPreferenceFragment implements SettingsBaseActivity.CategoryListener, SummaryLoader.SummaryConsumer, PreferenceGroup.OnExpandButtonClickListener, BasePreferenceController.UiBlockListener {
    UiBlockerController mBlockerController;
    private DashboardFeatureProvider mDashboardFeatureProvider;
    private boolean mListeningToCategoryChange;
    private DashboardTilePlaceholderPreferenceController mPlaceholderPreferenceController;
    private SummaryLoader mSummaryLoader;
    private final Map<Class, List<AbstractPreferenceController>> mPreferenceControllers = new ArrayMap();
    private final Set<String> mDashboardTilePrefKeys = new ArraySet();

    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return null;
    }

    protected boolean displayTile(Tile tile) {
        return true;
    }

    protected abstract String getLogTag();

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected abstract int getPreferenceScreenResId();

    protected boolean shouldForceRoundedIcon() {
        return false;
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mDashboardFeatureProvider = FeatureFactory.getFactory(context).getDashboardFeatureProvider(context);
        List<AbstractPreferenceController> arrayList = new ArrayList<>();
        List<AbstractPreferenceController> listCreatePreferenceControllers = createPreferenceControllers(context);
        List<BasePreferenceController> listFilterControllers = PreferenceControllerListHelper.filterControllers(PreferenceControllerListHelper.getPreferenceControllersFromXml(context, getPreferenceScreenResId()), listCreatePreferenceControllers);
        if (listCreatePreferenceControllers != null) {
            arrayList.addAll(listCreatePreferenceControllers);
        }
        arrayList.addAll(listFilterControllers);
        final Lifecycle settingsLifecycle = getSettingsLifecycle();
        listFilterControllers.stream().filter(new Predicate() { // from class: com.android.car.developeroptions.dashboard.-$$Lambda$DashboardFragment$5L9NEtNwyTKMXp9qR-TY5LLQniI
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DashboardFragment.lambda$onAttach$0((BasePreferenceController) obj);
            }
        }).forEach(new Consumer() { // from class: com.android.car.developeroptions.dashboard.-$$Lambda$DashboardFragment$k6KqNQbHpIwooqPRJgc_nGw7YzU
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                settingsLifecycle.addObserver((LifecycleObserver) ((BasePreferenceController) obj));
            }
        });
        DashboardTilePlaceholderPreferenceController dashboardTilePlaceholderPreferenceController = new DashboardTilePlaceholderPreferenceController(context);
        this.mPlaceholderPreferenceController = dashboardTilePlaceholderPreferenceController;
        arrayList.add(dashboardTilePlaceholderPreferenceController);
        Iterator<AbstractPreferenceController> it = arrayList.iterator();
        while (it.hasNext()) {
            addPreferenceController(it.next());
        }
        checkUiBlocker(arrayList);
    }

    static /* synthetic */ boolean lambda$onAttach$0(BasePreferenceController basePreferenceController) {
        return basePreferenceController instanceof LifecycleObserver;
    }

    void checkUiBlocker(List<AbstractPreferenceController> list) {
        final ArrayList arrayList = new ArrayList();
        list.stream().filter(new Predicate() { // from class: com.android.car.developeroptions.dashboard.-$$Lambda$DashboardFragment$lEBZWX0NLvWqw_l59kp64tiSX1U
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DashboardFragment.lambda$checkUiBlocker$2((AbstractPreferenceController) obj);
            }
        }).forEach(new Consumer() { // from class: com.android.car.developeroptions.dashboard.-$$Lambda$DashboardFragment$QDpgoMHbZ3IuHuJgpXYilN0mYIw
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$checkUiBlocker$3$DashboardFragment(arrayList, (AbstractPreferenceController) obj);
            }
        });
        if (arrayList.isEmpty()) {
            return;
        }
        UiBlockerController uiBlockerController = new UiBlockerController(arrayList);
        this.mBlockerController = uiBlockerController;
        uiBlockerController.start(new Runnable() { // from class: com.android.car.developeroptions.dashboard.-$$Lambda$DashboardFragment$mMO_Kroe5itX15uW7apsoC2nBzk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$checkUiBlocker$4$DashboardFragment();
            }
        });
    }

    static /* synthetic */ boolean lambda$checkUiBlocker$2(AbstractPreferenceController abstractPreferenceController) {
        return abstractPreferenceController instanceof BasePreferenceController.UiBlocker;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$checkUiBlocker$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$checkUiBlocker$3$DashboardFragment(List list, AbstractPreferenceController abstractPreferenceController) {
        ((BasePreferenceController) abstractPreferenceController).setUiBlockListener(this);
        list.add(abstractPreferenceController.getPreferenceKey());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$checkUiBlocker$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$checkUiBlocker$4$DashboardFragment() {
        updatePreferenceVisibility(this.mPreferenceControllers);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getPreferenceManager().setPreferenceComparisonCallback(new PreferenceManager.SimplePreferenceComparisonCallback());
        if (bundle != null) {
            updatePreferenceStates();
        }
    }

    @Override // com.android.car.developeroptions.core.SettingsBaseActivity.CategoryListener
    public void onCategoriesChanged() {
        if (this.mDashboardFeatureProvider.getTilesForCategory(getCategoryKey()) == null) {
            return;
        }
        refreshDashboardTiles(getLogTag());
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        refreshAllPreferences(getLogTag());
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.mDashboardFeatureProvider.getTilesForCategory(getCategoryKey()) == null) {
            return;
        }
        SummaryLoader summaryLoader = this.mSummaryLoader;
        if (summaryLoader != null) {
            summaryLoader.setListening(true);
        }
        FragmentActivity activity = getActivity();
        if (activity instanceof SettingsBaseActivity) {
            this.mListeningToCategoryChange = true;
            ((SettingsBaseActivity) activity).addCategoryListener(this);
        }
    }

    @Override // com.android.car.developeroptions.dashboard.SummaryLoader.SummaryConsumer
    public void notifySummaryChanged(Tile tile) {
        String dashboardKeyForTile = this.mDashboardFeatureProvider.getDashboardKeyForTile(tile);
        Preference preferenceFindPreference = getPreferenceScreen().findPreference(dashboardKeyForTile);
        if (preferenceFindPreference == null) {
            Log.d(getLogTag(), String.format("Can't find pref by key %s, skipping update summary %s", dashboardKeyForTile, tile.getDescription()));
        } else {
            preferenceFindPreference.setSummary(tile.getSummary(preferenceFindPreference.getContext()));
        }
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updatePreferenceStates();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        Collection<List<AbstractPreferenceController>> collectionValues = this.mPreferenceControllers.values();
        this.mMetricsFeatureProvider.logStartedIntent(preference.getIntent(), getMetricsCategory());
        Iterator<List<AbstractPreferenceController>> it = collectionValues.iterator();
        while (it.hasNext()) {
            Iterator<AbstractPreferenceController> it2 = it.next().iterator();
            while (it2.hasNext()) {
                if (it2.next().handlePreferenceTreeClick(preference)) {
                    return true;
                }
            }
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        SummaryLoader summaryLoader = this.mSummaryLoader;
        if (summaryLoader != null) {
            summaryLoader.setListening(false);
        }
        if (this.mListeningToCategoryChange) {
            FragmentActivity activity = getActivity();
            if (activity instanceof SettingsBaseActivity) {
                ((SettingsBaseActivity) activity).remCategoryListener(this);
            }
            this.mListeningToCategoryChange = false;
        }
    }

    @Override // androidx.preference.PreferenceGroup.OnExpandButtonClickListener
    public void onExpandButtonClick() {
        this.mMetricsFeatureProvider.action(0, 834, getMetricsCategory(), null, 0);
    }

    protected <T extends AbstractPreferenceController> T use(Class<T> cls) {
        List<AbstractPreferenceController> list = this.mPreferenceControllers.get(cls);
        if (list == null) {
            return null;
        }
        if (list.size() > 1) {
            Log.w("DashboardFragment", "Multiple controllers of Class " + cls.getSimpleName() + " found, returning first one.");
        }
        return (T) list.get(0);
    }

    protected void addPreferenceController(AbstractPreferenceController abstractPreferenceController) {
        if (this.mPreferenceControllers.get(abstractPreferenceController.getClass()) == null) {
            this.mPreferenceControllers.put(abstractPreferenceController.getClass(), new ArrayList());
        }
        this.mPreferenceControllers.get(abstractPreferenceController.getClass()).add(abstractPreferenceController);
    }

    public String getCategoryKey() {
        return DashboardFragmentRegistry.PARENT_TO_CATEGORY_KEY_MAP.get(getClass().getName());
    }

    private void displayResourceTiles() {
        int preferenceScreenResId = getPreferenceScreenResId();
        if (preferenceScreenResId <= 0) {
            return;
        }
        addPreferencesFromResource(preferenceScreenResId);
        final PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.setOnExpandButtonClickListener(this);
        this.mPreferenceControllers.values().stream().flatMap(new Function() { // from class: com.android.car.developeroptions.dashboard.-$$Lambda$seyL25CSW2NInOydsTbSDrNW6pM
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((List) obj).stream();
            }
        }).forEach(new Consumer() { // from class: com.android.car.developeroptions.dashboard.-$$Lambda$DashboardFragment$rql9dzaydZiwMU6wDcZPdWLURSg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractPreferenceController) obj).displayPreference(preferenceScreen);
            }
        });
    }

    protected void updatePreferenceStates() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        Iterator<List<AbstractPreferenceController>> it = this.mPreferenceControllers.values().iterator();
        while (it.hasNext()) {
            for (AbstractPreferenceController abstractPreferenceController : it.next()) {
                if (abstractPreferenceController.isAvailable()) {
                    String preferenceKey = abstractPreferenceController.getPreferenceKey();
                    if (TextUtils.isEmpty(preferenceKey)) {
                        Log.d("DashboardFragment", String.format("Preference key is %s in Controller %s", preferenceKey, abstractPreferenceController.getClass().getSimpleName()));
                    } else {
                        Preference preferenceFindPreference = preferenceScreen.findPreference(preferenceKey);
                        if (preferenceFindPreference == null) {
                            Log.d("DashboardFragment", String.format("Cannot find preference with key %s in Controller %s", preferenceKey, abstractPreferenceController.getClass().getSimpleName()));
                        } else {
                            abstractPreferenceController.updateState(preferenceFindPreference);
                        }
                    }
                }
            }
        }
    }

    private void refreshAllPreferences(String str) {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            preferenceScreen.removeAll();
        }
        displayResourceTiles();
        refreshDashboardTiles(str);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Log.d(str, "All preferences added, reporting fully drawn");
            activity.reportFullyDrawn();
        }
        updatePreferenceVisibility(this.mPreferenceControllers);
    }

    void updatePreferenceVisibility(Map<Class, List<AbstractPreferenceController>> map) {
        UiBlockerController uiBlockerController;
        if (getPreferenceScreen() == null || map == null || (uiBlockerController = this.mBlockerController) == null) {
            return;
        }
        boolean zIsBlockerFinished = uiBlockerController.isBlockerFinished();
        Iterator<List<AbstractPreferenceController>> it = map.values().iterator();
        while (it.hasNext()) {
            for (AbstractPreferenceController abstractPreferenceController : it.next()) {
                Preference preferenceFindPreference = findPreference(abstractPreferenceController.getPreferenceKey());
                if (preferenceFindPreference != null) {
                    preferenceFindPreference.setVisible(zIsBlockerFinished && abstractPreferenceController.isAvailable());
                }
            }
        }
    }

    void refreshDashboardTiles(String str) {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        DashboardCategory tilesForCategory = this.mDashboardFeatureProvider.getTilesForCategory(getCategoryKey());
        if (tilesForCategory == null) {
            Log.d(str, "NO dashboard tiles for " + str);
            return;
        }
        List<Tile> tiles = tilesForCategory.getTiles();
        if (tiles == null) {
            Log.d(str, "tile list is empty, skipping category " + tilesForCategory.key);
            return;
        }
        ArrayList<String> arrayList = new ArrayList(this.mDashboardTilePrefKeys);
        SummaryLoader summaryLoader = this.mSummaryLoader;
        if (summaryLoader != null) {
            summaryLoader.release();
        }
        getContext();
        SummaryLoader summaryLoader2 = new SummaryLoader(getActivity(), getCategoryKey());
        this.mSummaryLoader = summaryLoader2;
        summaryLoader2.setSummaryConsumer(this);
        boolean zShouldForceRoundedIcon = shouldForceRoundedIcon();
        for (Tile tile : tiles) {
            String dashboardKeyForTile = this.mDashboardFeatureProvider.getDashboardKeyForTile(tile);
            if (TextUtils.isEmpty(dashboardKeyForTile)) {
                Log.d(str, "tile does not contain a key, skipping " + tile);
            } else if (displayTile(tile)) {
                if (this.mDashboardTilePrefKeys.contains(dashboardKeyForTile)) {
                    this.mDashboardFeatureProvider.bindPreferenceToTile(getActivity(), zShouldForceRoundedIcon, getMetricsCategory(), preferenceScreen.findPreference(dashboardKeyForTile), tile, dashboardKeyForTile, this.mPlaceholderPreferenceController.getOrder());
                } else {
                    Preference preference = new Preference(getPrefContext());
                    this.mDashboardFeatureProvider.bindPreferenceToTile(getActivity(), zShouldForceRoundedIcon, getMetricsCategory(), preference, tile, dashboardKeyForTile, this.mPlaceholderPreferenceController.getOrder());
                    preferenceScreen.addPreference(preference);
                    this.mDashboardTilePrefKeys.add(dashboardKeyForTile);
                }
                arrayList.remove(dashboardKeyForTile);
            }
        }
        for (String str2 : arrayList) {
            this.mDashboardTilePrefKeys.remove(str2);
            Preference preferenceFindPreference = preferenceScreen.findPreference(str2);
            if (preferenceFindPreference != null) {
                preferenceScreen.removePreference(preferenceFindPreference);
            }
        }
        this.mSummaryLoader.setListening(true);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController.UiBlockListener
    public void onBlockerWorkFinished(BasePreferenceController basePreferenceController) {
        this.mBlockerController.countDown(basePreferenceController.getPreferenceKey());
    }
}
