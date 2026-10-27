package com.android.car.developeroptions;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.CustomListPreference;
import com.android.car.developeroptions.RestrictedListPreference;
import com.android.car.developeroptions.core.InstrumentedPreferenceFragment;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.search.actionbar.SearchMenuController;
import com.android.car.developeroptions.support.actionbar.HelpMenuController;
import com.android.car.developeroptions.support.actionbar.HelpResourceProvider;
import com.android.car.developeroptions.widget.HighlightablePreferenceGroupAdapter;
import com.android.car.developeroptions.widget.LoadingViewController;
import com.android.settingslib.CustomDialogPreferenceCompat;
import com.android.settingslib.CustomEditTextPreferenceCompat;
import com.android.settingslib.widget.LayoutPreference;
import java.util.Iterator;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class SettingsPreferenceFragment extends InstrumentedPreferenceFragment implements DialogCreatable, HelpResourceProvider {
    public HighlightablePreferenceGroupAdapter mAdapter;
    private boolean mAnimationAllowed;
    private ViewGroup mButtonBar;
    private ContentResolver mContentResolver;
    private RecyclerView.Adapter mCurrentRootAdapter;
    private SettingsDialogFragment mDialogFragment;
    private View mEmptyView;
    private LayoutPreference mHeader;
    private LinearLayoutManager mLayoutManager;
    private ViewGroup mPinnedHeaderFrameLayout;
    private ArrayMap<String, Preference> mPreferenceCache;
    private boolean mIsDataSetObserverRegistered = false;
    private RecyclerView.AdapterDataObserver mDataSetObserver = new RecyclerView.AdapterDataObserver() { // from class: com.android.car.developeroptions.SettingsPreferenceFragment.1
        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            SettingsPreferenceFragment.this.onDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2) {
            SettingsPreferenceFragment.this.onDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2, Object obj) {
            SettingsPreferenceFragment.this.onDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i, int i2) {
            SettingsPreferenceFragment.this.onDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i, int i2) {
            SettingsPreferenceFragment.this.onDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i, int i2, int i3) {
            SettingsPreferenceFragment.this.onDataSetChanged();
        }
    };
    public boolean mPreferenceHighlighted = false;

    public int getDialogMetricsCategory(int i) {
        return 0;
    }

    public int getInitialExpandedChildCount() {
        return 0;
    }

    public Dialog onCreateDialog(int i) {
        return null;
    }

    public void onDialogShowing() {
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SearchMenuController.init(this);
        HelpMenuController.init(this);
        if (bundle != null) {
            this.mPreferenceHighlighted = bundle.getBoolean("android:preference_highlighted");
        }
        HighlightablePreferenceGroupAdapter.adjustInitialExpandedChildCount(this);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mPinnedHeaderFrameLayout = (ViewGroup) viewOnCreateView.findViewById(R.id.pinned_header);
        this.mButtonBar = (ViewGroup) viewOnCreateView.findViewById(R.id.button_bar);
        return viewOnCreateView;
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat
    public void addPreferencesFromResource(int i) {
        super.addPreferencesFromResource(i);
        checkAvailablePrefs(getPreferenceScreen());
    }

    /* JADX WARN: Multi-variable type inference failed */
    void checkAvailablePrefs(PreferenceGroup preferenceGroup) {
        if (preferenceGroup == null) {
            return;
        }
        for (int i = 0; i < preferenceGroup.getPreferenceCount(); i++) {
            Preference preference = preferenceGroup.getPreference(i);
            if ((preference instanceof SelfAvailablePreference) && !((SelfAvailablePreference) preference).isAvailable(getContext())) {
                preference.setVisible(false);
            } else if (preference instanceof PreferenceGroup) {
                checkAvailablePrefs((PreferenceGroup) preference);
            }
        }
    }

    public ViewGroup getButtonBar() {
        return this.mButtonBar;
    }

    public View setPinnedHeaderView(int i) {
        View viewInflate = getActivity().getLayoutInflater().inflate(i, this.mPinnedHeaderFrameLayout, false);
        setPinnedHeaderView(viewInflate);
        return viewInflate;
    }

    public void setPinnedHeaderView(View view) {
        this.mPinnedHeaderFrameLayout.addView(view);
        this.mPinnedHeaderFrameLayout.setVisibility(0);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        HighlightablePreferenceGroupAdapter highlightablePreferenceGroupAdapter = this.mAdapter;
        if (highlightablePreferenceGroupAdapter != null) {
            bundle.putBoolean("android:preference_highlighted", highlightablePreferenceGroupAdapter.isHighlightRequested());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        highlightPreferenceIfNeeded();
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    protected void onBindPreferences() {
        registerObserverIfNeeded();
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    protected void onUnbindPreferences() {
        unregisterObserverIfNeeded();
    }

    public void setLoading(boolean z, boolean z2) {
        LoadingViewController.handleLoadingContainer(getView().findViewById(R.id.loading_container), getListView(), !z, z2);
    }

    public void registerObserverIfNeeded() {
        if (this.mIsDataSetObserverRegistered) {
            return;
        }
        RecyclerView.Adapter adapter = this.mCurrentRootAdapter;
        if (adapter != null) {
            adapter.unregisterAdapterDataObserver(this.mDataSetObserver);
        }
        RecyclerView.Adapter adapter2 = getListView().getAdapter();
        this.mCurrentRootAdapter = adapter2;
        adapter2.registerAdapterDataObserver(this.mDataSetObserver);
        this.mIsDataSetObserverRegistered = true;
        onDataSetChanged();
    }

    public void unregisterObserverIfNeeded() {
        if (this.mIsDataSetObserverRegistered) {
            RecyclerView.Adapter adapter = this.mCurrentRootAdapter;
            if (adapter != null) {
                adapter.unregisterAdapterDataObserver(this.mDataSetObserver);
                this.mCurrentRootAdapter = null;
            }
            this.mIsDataSetObserverRegistered = false;
        }
    }

    public void highlightPreferenceIfNeeded() {
        HighlightablePreferenceGroupAdapter highlightablePreferenceGroupAdapter;
        if (isAdded() && (highlightablePreferenceGroupAdapter = this.mAdapter) != null) {
            highlightablePreferenceGroupAdapter.requestHighlight(getView(), getListView());
        }
    }

    protected void onDataSetChanged() {
        highlightPreferenceIfNeeded();
        updateEmptyView();
    }

    public LayoutPreference getHeaderView() {
        return this.mHeader;
    }

    protected void setHeaderView(int i) {
        LayoutPreference layoutPreference = new LayoutPreference(getPrefContext(), i);
        this.mHeader = layoutPreference;
        addPreferenceToTop(layoutPreference);
    }

    private void addPreferenceToTop(LayoutPreference layoutPreference) {
        layoutPreference.setOrder(-1);
        if (getPreferenceScreen() != null) {
            getPreferenceScreen().addPreference(layoutPreference);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat
    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        LayoutPreference layoutPreference;
        if (preferenceScreen != null && !preferenceScreen.isAttached()) {
            preferenceScreen.setShouldUseGeneratedIds(this.mAnimationAllowed);
        }
        super.setPreferenceScreen(preferenceScreen);
        if (preferenceScreen == null || (layoutPreference = this.mHeader) == null) {
            return;
        }
        preferenceScreen.addPreference(layoutPreference);
    }

    void updateEmptyView() {
        if (this.mEmptyView == null) {
            return;
        }
        if (getPreferenceScreen() != null) {
            View viewFindViewById = getActivity().findViewById(android.R.id.list_container);
            boolean z = true;
            if (getPreferenceScreen().getPreferenceCount() - (this.mHeader != null ? 1 : 0) > 0 && (viewFindViewById == null || viewFindViewById.getVisibility() == 0)) {
                z = false;
            }
            this.mEmptyView.setVisibility(z ? 0 : 8);
            return;
        }
        this.mEmptyView.setVisibility(0);
    }

    public void setEmptyView(View view) {
        View view2 = this.mEmptyView;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        this.mEmptyView = view;
        updateEmptyView();
    }

    public View getEmptyView() {
        return this.mEmptyView;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public RecyclerView.LayoutManager onCreateLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.mLayoutManager = linearLayoutManager;
        return linearLayoutManager;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    protected RecyclerView.Adapter onCreateAdapter(PreferenceScreen preferenceScreen) {
        Bundle arguments = getArguments();
        HighlightablePreferenceGroupAdapter highlightablePreferenceGroupAdapter = new HighlightablePreferenceGroupAdapter(preferenceScreen, arguments == null ? null : arguments.getString(":settings:fragment_args_key"), this.mPreferenceHighlighted);
        this.mAdapter = highlightablePreferenceGroupAdapter;
        return highlightablePreferenceGroupAdapter;
    }

    protected void setAnimationAllowed(boolean z) {
        this.mAnimationAllowed = z;
    }

    protected void cacheRemoveAllPrefs(PreferenceGroup preferenceGroup) {
        this.mPreferenceCache = new ArrayMap<>();
        int preferenceCount = preferenceGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceGroup.getPreference(i);
            if (!TextUtils.isEmpty(preference.getKey())) {
                this.mPreferenceCache.put(preference.getKey(), preference);
            }
        }
    }

    protected Preference getCachedPreference(String str) {
        ArrayMap<String, Preference> arrayMap = this.mPreferenceCache;
        if (arrayMap != null) {
            return arrayMap.remove(str);
        }
        return null;
    }

    protected void removeCachedPrefs(PreferenceGroup preferenceGroup) {
        Iterator<Preference> it = this.mPreferenceCache.values().iterator();
        while (it.hasNext()) {
            preferenceGroup.removePreference(it.next());
        }
        this.mPreferenceCache = null;
    }

    public boolean removePreference(String str) {
        return removePreference(getPreferenceScreen(), str);
    }

    boolean removePreference(PreferenceGroup preferenceGroup, String str) {
        int preferenceCount = preferenceGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceGroup.getPreference(i);
            if (TextUtils.equals(preference.getKey(), str)) {
                return preferenceGroup.removePreference(preference);
            }
            if ((preference instanceof PreferenceGroup) && removePreference((PreferenceGroup) preference, str)) {
                return true;
            }
        }
        return false;
    }

    public final void finishFragment() {
        getActivity().onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ContentResolver getContentResolver() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.mContentResolver = activity.getContentResolver();
        }
        return this.mContentResolver;
    }

    protected Object getSystemService(String str) {
        return getActivity().getSystemService(str);
    }

    protected PackageManager getPackageManager() {
        return getActivity().getPackageManager();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        SettingsDialogFragment settingsDialogFragment;
        if (isRemoving() && (settingsDialogFragment = this.mDialogFragment) != null) {
            settingsDialogFragment.dismiss();
            this.mDialogFragment = null;
        }
        super.onDetach();
    }

    protected void showDialog(int i) {
        if (this.mDialogFragment != null) {
            Log.e("SettingsPreference", "Old dialog fragment not null!");
        }
        SettingsDialogFragment settingsDialogFragmentNewInstance = SettingsDialogFragment.newInstance(this, i);
        this.mDialogFragment = settingsDialogFragmentNewInstance;
        settingsDialogFragmentNewInstance.show(getChildFragmentManager(), Integer.toString(i));
    }

    protected void removeDialog(int i) {
        SettingsDialogFragment settingsDialogFragment = this.mDialogFragment;
        if (settingsDialogFragment != null && settingsDialogFragment.getDialogId() == i) {
            this.mDialogFragment.dismissAllowingStateLoss();
        }
        this.mDialogFragment = null;
    }

    protected void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        SettingsDialogFragment settingsDialogFragment = this.mDialogFragment;
        if (settingsDialogFragment != null) {
            settingsDialogFragment.mOnDismissListener = onDismissListener;
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnDisplayPreferenceDialogListener
    public void onDisplayPreferenceDialog(Preference preference) {
        DialogFragment dialogFragmentNewInstance;
        if (preference.getKey() == null) {
            preference.setKey(UUID.randomUUID().toString());
        }
        if (preference instanceof RestrictedListPreference) {
            dialogFragmentNewInstance = RestrictedListPreference.RestrictedListPreferenceDialogFragment.newInstance(preference.getKey());
        } else if (preference instanceof CustomListPreference) {
            dialogFragmentNewInstance = CustomListPreference.CustomListPreferenceDialogFragment.newInstance(preference.getKey());
        } else if (preference instanceof CustomDialogPreferenceCompat) {
            dialogFragmentNewInstance = CustomDialogPreferenceCompat.CustomPreferenceDialogFragment.newInstance(preference.getKey());
        } else if (preference instanceof CustomEditTextPreferenceCompat) {
            dialogFragmentNewInstance = CustomEditTextPreferenceCompat.CustomPreferenceDialogFragment.newInstance(preference.getKey());
        } else {
            super.onDisplayPreferenceDialog(preference);
            return;
        }
        dialogFragmentNewInstance.setTargetFragment(this, 0);
        dialogFragmentNewInstance.show(getFragmentManager(), "dialog_preference");
        onDialogShowing();
    }

    public static class SettingsDialogFragment extends InstrumentedDialogFragment {
        private DialogInterface.OnCancelListener mOnCancelListener;
        private DialogInterface.OnDismissListener mOnDismissListener;
        private Fragment mParentFragment;

        public static SettingsDialogFragment newInstance(DialogCreatable dialogCreatable, int i) {
            if (!(dialogCreatable instanceof Fragment)) {
                throw new IllegalArgumentException("fragment argument must be an instance of " + Fragment.class.getName());
            }
            SettingsDialogFragment settingsDialogFragment = new SettingsDialogFragment();
            settingsDialogFragment.setParentFragment(dialogCreatable);
            settingsDialogFragment.setDialogId(i);
            return settingsDialogFragment;
        }

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            LifecycleOwner lifecycleOwner = this.mParentFragment;
            if (lifecycleOwner == null) {
                return 0;
            }
            int dialogMetricsCategory = ((DialogCreatable) lifecycleOwner).getDialogMetricsCategory(this.mDialogId);
            if (dialogMetricsCategory > 0) {
                return dialogMetricsCategory;
            }
            throw new IllegalStateException("Dialog must provide a metrics category");
        }

        @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            if (this.mParentFragment != null) {
                bundle.putInt("key_dialog_id", this.mDialogId);
                bundle.putInt("key_parent_fragment_id", this.mParentFragment.getId());
            }
        }

        @Override // com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public void onStart() {
            super.onStart();
            Fragment fragment = this.mParentFragment;
            if (fragment == null || !(fragment instanceof SettingsPreferenceFragment)) {
                return;
            }
            ((SettingsPreferenceFragment) fragment).onDialogShowing();
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            Object objValueOf;
            if (bundle != null) {
                this.mDialogId = bundle.getInt("key_dialog_id", 0);
                this.mParentFragment = getParentFragment();
                int i = bundle.getInt("key_parent_fragment_id", -1);
                if (this.mParentFragment == null) {
                    this.mParentFragment = getFragmentManager().findFragmentById(i);
                }
                Fragment fragment = this.mParentFragment;
                if (!(fragment instanceof DialogCreatable)) {
                    StringBuilder sb = new StringBuilder();
                    Fragment fragment2 = this.mParentFragment;
                    if (fragment2 != null) {
                        objValueOf = fragment2.getClass().getName();
                    } else {
                        objValueOf = Integer.valueOf(i);
                    }
                    sb.append(objValueOf);
                    sb.append(" must implement ");
                    sb.append(DialogCreatable.class.getName());
                    throw new IllegalArgumentException(sb.toString());
                }
                if (fragment instanceof SettingsPreferenceFragment) {
                    ((SettingsPreferenceFragment) fragment).mDialogFragment = this;
                }
            }
            return ((DialogCreatable) this.mParentFragment).onCreateDialog(this.mDialogId);
        }

        @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            super.onCancel(dialogInterface);
            DialogInterface.OnCancelListener onCancelListener = this.mOnCancelListener;
            if (onCancelListener != null) {
                onCancelListener.onCancel(dialogInterface);
            }
        }

        @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
            DialogInterface.OnDismissListener onDismissListener = this.mOnDismissListener;
            if (onDismissListener != null) {
                onDismissListener.onDismiss(dialogInterface);
            }
        }

        public int getDialogId() {
            return this.mDialogId;
        }

        @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public void onDetach() {
            super.onDetach();
            Fragment fragment = this.mParentFragment;
            if ((fragment instanceof SettingsPreferenceFragment) && ((SettingsPreferenceFragment) fragment).mDialogFragment == this) {
                ((SettingsPreferenceFragment) this.mParentFragment).mDialogFragment = null;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void setParentFragment(DialogCreatable dialogCreatable) {
            this.mParentFragment = (Fragment) dialogCreatable;
        }

        private void setDialogId(int i) {
            this.mDialogId = i;
        }
    }

    protected boolean hasNextButton() {
        return ((ButtonBarHandler) getActivity()).hasNextButton();
    }

    protected Button getNextButton() {
        return ((ButtonBarHandler) getActivity()).getNextButton();
    }

    public void finish() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            activity.finish();
        }
    }

    protected Intent getIntent() {
        if (getActivity() == null) {
            return null;
        }
        return getActivity().getIntent();
    }

    protected void setResult(int i) {
        if (getActivity() == null) {
            return;
        }
        getActivity().setResult(i);
    }
}
