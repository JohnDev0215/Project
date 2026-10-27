package com.android.car.settings.common;

import android.car.drivingstate.CarUxRestrictions;
import android.car.drivingstate.CarUxRestrictionsManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.settings.R;
import com.android.car.ui.core.CarUi;
import com.android.car.ui.preference.PreferenceFragment;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.toolbar.MenuItem;
import com.android.car.ui.toolbar.Toolbar;
import com.android.car.ui.toolbar.ToolbarController;
import com.android.car.ui.utils.CarUiUtils;
import com.android.settingslib.search.Indexable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: classes5.dex */
public abstract class SettingsFragment extends PreferenceFragment implements CarUxRestrictionsManager.OnUxRestrictionsChangedListener, FragmentController, Indexable {
    static final String DIALOG_FRAGMENT_TAG = "com.android.car.settings.common.SettingsFragment.DIALOG";
    private static final int MAX_NUM_PENDING_ACTIVITY_RESULT_CALLBACKS = 254;
    private CarUxRestrictions mUxRestrictions;
    private final Map<Class, List<PreferenceController>> mPreferenceControllersLookup = new ArrayMap();
    private final List<PreferenceController> mPreferenceControllers = new ArrayList();
    private final SparseArray<ActivityResultCallback> mActivityResultCallbackMap = new SparseArray<>();
    private int mCurrentRequestIndex = 0;

    protected abstract int getPreferenceScreenResId();

    protected ToolbarController getToolbar() {
        return CarUi.requireToolbar(requireActivity());
    }

    protected List<MenuItem> getToolbarMenuItems() {
        return null;
    }

    protected <T extends PreferenceController> T use(Class<T> clazz, int preferenceKeyResId) {
        List<PreferenceController> controllerList = this.mPreferenceControllersLookup.get(clazz);
        if (controllerList != null) {
            String preferenceKey = getString(preferenceKeyResId);
            Iterator<PreferenceController> it = controllerList.iterator();
            while (it.hasNext()) {
                T t = (T) it.next();
                if (t.getPreferenceKey().equals(preferenceKey)) {
                    return t;
                }
            }
            return null;
        }
        return null;
    }

    protected void enableRotaryScroll() {
        CarUiRecyclerView recyclerView = (CarUiRecyclerView) getView().findViewById(R.id.recycler_view);
        if (recyclerView != null) {
            CarUiUtils.setRotaryScrollEnabled(recyclerView, true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(getActivity() instanceof UxRestrictionsProvider)) {
            throw new IllegalStateException("Must attach to a UxRestrictionsProvider");
        }
        if (!(getActivity() instanceof FragmentHost)) {
            throw new IllegalStateException("Must attach to a FragmentHost");
        }
        TypedValue tv = new TypedValue();
        getActivity().getTheme().resolveAttribute(androidx.preference.R.attr.preferenceTheme, tv, true);
        int theme = tv.resourceId;
        if (theme == 0) {
            throw new IllegalStateException("Must specify preferenceTheme in theme");
        }
        Context styledContext = new ContextThemeWrapper(getActivity(), theme);
        this.mUxRestrictions = ((UxRestrictionsProvider) requireActivity()).getCarUxRestrictions();
        this.mPreferenceControllers.clear();
        this.mPreferenceControllers.addAll(PreferenceControllerListHelper.getPreferenceControllersFromXml(styledContext, getPreferenceScreenResId(), this, this.mUxRestrictions));
        final Lifecycle lifecycle = getLifecycle();
        this.mPreferenceControllers.forEach(new Consumer() { // from class: com.android.car.settings.common.-$$Lambda$SettingsFragment$WFKBgj2vj2tCdkk8ybBpabUt82U
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onAttach$1$SettingsFragment(lifecycle, (PreferenceController) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onAttach$1$SettingsFragment(Lifecycle lifecycle, PreferenceController controller) {
        lifecycle.addObserver(controller);
        this.mPreferenceControllersLookup.computeIfAbsent(controller.getClass(), new Function() { // from class: com.android.car.settings.common.-$$Lambda$SettingsFragment$aUinEglqfJBaOm5WyMLVWMdLMb0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SettingsFragment.lambda$onAttach$0((Class) obj);
            }
        }).add(controller);
    }

    static /* synthetic */ List lambda$onAttach$0(Class k) {
        return new ArrayList(1);
    }

    @Override // com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        onCarUiInsetsChanged(CarUi.requireInsets(requireActivity()));
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        int resId = getPreferenceScreenResId();
        if (resId <= 0) {
            throw new IllegalStateException("Fragment must specify a preference screen resource ID");
        }
        addPreferencesFromResource(resId);
        PreferenceScreen screen = getPreferenceScreen();
        for (PreferenceController controller : this.mPreferenceControllers) {
            Preference pref = screen.findPreference(controller.getPreferenceKey());
            controller.setPreference(pref);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ToolbarController toolbar = getToolbar();
        if (toolbar != null) {
            List<MenuItem> items = getToolbarMenuItems();
            if (items != null) {
                if (items.size() == 1) {
                    items.get(0).setId(R.id.toolbar_menu_item_0);
                } else if (items.size() == 2) {
                    items.get(0).setId(R.id.toolbar_menu_item_0);
                    items.get(1).setId(R.id.toolbar_menu_item_1);
                }
            }
            toolbar.setTitle(getPreferenceScreen().getTitle());
            toolbar.setMenuItems(items);
            toolbar.setNavButtonMode(Toolbar.NavButtonMode.BACK);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        final Lifecycle lifecycle = getLifecycle();
        List<PreferenceController> list = this.mPreferenceControllers;
        Objects.requireNonNull(lifecycle);
        list.forEach(new Consumer() { // from class: com.android.car.settings.common.-$$Lambda$vkisNm33ZNPzErzl8_G3jOgXxXo
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                lifecycle.removeObserver((PreferenceController) obj);
            }
        });
        this.mActivityResultCallbackMap.clear();
    }

    @Override // android.car.drivingstate.CarUxRestrictionsManager.OnUxRestrictionsChangedListener
    public void onUxRestrictionsChanged(CarUxRestrictions uxRestrictions) {
        if (!uxRestrictions.isSameRestrictions(this.mUxRestrictions)) {
            this.mUxRestrictions = uxRestrictions;
            for (PreferenceController controller : this.mPreferenceControllers) {
                controller.onUxRestrictionsChanged(uxRestrictions);
            }
        }
    }

    @Override // com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnDisplayPreferenceDialogListener
    public void onDisplayPreferenceDialog(Preference preference) {
        DialogFragment dialogFragment;
        if (findDialogByTag(DIALOG_FRAGMENT_TAG) != null) {
            return;
        }
        if (preference instanceof ValidatedEditTextPreference) {
            if (preference instanceof PasswordEditTextPreference) {
                dialogFragment = PasswordEditTextPreferenceDialogFragment.newInstance(preference.getKey());
            } else {
                dialogFragment = ValidatedEditTextPreferenceDialogFragment.newInstance(preference.getKey());
            }
            dialogFragment.setTargetFragment(this, 0);
            showDialog(dialogFragment, DIALOG_FRAGMENT_TAG);
            return;
        }
        super.onDisplayPreferenceDialog(preference);
    }

    @Override // com.android.car.settings.common.FragmentController
    public void launchFragment(Fragment fragment) {
        getFragmentHost().launchFragment(fragment);
    }

    @Override // com.android.car.settings.common.FragmentController
    public void goBack() {
        getFragmentHost().goBack();
    }

    @Override // com.android.car.settings.common.FragmentController
    public void showDialog(DialogFragment dialogFragment, String tag) {
        dialogFragment.show(getFragmentManager(), tag);
    }

    @Override // com.android.car.settings.common.FragmentController
    public DialogFragment findDialogByTag(String tag) {
        Fragment fragment = getFragmentManager().findFragmentByTag(tag);
        if (fragment instanceof DialogFragment) {
            return (DialogFragment) fragment;
        }
        return null;
    }

    @Override // com.android.car.settings.common.FragmentController
    public void startActivityForResult(Intent intent, int requestCode, ActivityResultCallback callback) {
        validateRequestCodeForPreferenceController(requestCode);
        int requestIndex = allocateRequestIndex(callback);
        super.startActivityForResult(intent, ((requestIndex + 1) << 8) + (requestCode & 255));
    }

    @Override // com.android.car.settings.common.FragmentController
    public void startIntentSenderForResult(IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, Bundle options, ActivityResultCallback callback) throws IntentSender.SendIntentException {
        validateRequestCodeForPreferenceController(requestCode);
        int requestIndex = allocateRequestIndex(callback);
        super.startIntentSenderForResult(intent, ((requestIndex + 1) << 8) + (requestCode & 255), fillInIntent, flagsMask, flagsValues, 0, options);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int requestIndex = (requestCode >> 8) & 255;
        if (requestIndex != 0) {
            int requestIndex2 = requestIndex - 1;
            ActivityResultCallback callback = this.mActivityResultCallbackMap.get(requestIndex2);
            this.mActivityResultCallbackMap.remove(requestIndex2);
            if (callback != null) {
                callback.processActivityResult(requestCode & 255, resultCode, data);
            }
        }
    }

    private int allocateRequestIndex(ActivityResultCallback callback) {
        if (this.mActivityResultCallbackMap.size() >= MAX_NUM_PENDING_ACTIVITY_RESULT_CALLBACKS) {
            throw new IllegalStateException("Too many pending activity result callbacks.");
        }
        while (this.mActivityResultCallbackMap.indexOfKey(this.mCurrentRequestIndex) >= 0) {
            this.mCurrentRequestIndex = (this.mCurrentRequestIndex + 1) % MAX_NUM_PENDING_ACTIVITY_RESULT_CALLBACKS;
        }
        this.mActivityResultCallbackMap.put(this.mCurrentRequestIndex, callback);
        return this.mCurrentRequestIndex;
    }

    private static void validateRequestCodeForPreferenceController(int requestCode) {
        if ((65280 & requestCode) != 0) {
            throw new IllegalArgumentException("Can only use lower 8 bits for requestCode");
        }
    }

    private FragmentHost getFragmentHost() {
        return (FragmentHost) requireActivity();
    }
}
