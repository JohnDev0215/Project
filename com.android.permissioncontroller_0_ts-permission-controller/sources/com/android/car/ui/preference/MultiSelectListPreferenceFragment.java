package com.android.car.ui.preference;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.preference.DialogPreference;
import androidx.preference.Preference;
import com.android.car.ui.FocusArea;
import com.android.car.ui.R;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.baselayout.InsetsChangedListener;
import com.android.car.ui.core.CarUi;
import com.android.car.ui.recyclerview.CarUiContentListItem;
import com.android.car.ui.recyclerview.CarUiListItem;
import com.android.car.ui.recyclerview.CarUiListItemAdapter;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.toolbar.Toolbar;
import com.android.car.ui.toolbar.ToolbarController;
import com.android.car.ui.utils.CarUiUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes7.dex */
public class MultiSelectListPreferenceFragment extends Fragment implements InsetsChangedListener {
    private static final String ARG_FULLSCREEN = "fullscreen";
    private boolean mFullScreen;
    private Set<String> mNewValues;
    private CarUiMultiSelectListPreference mPreference;
    private ToolbarController mToolbar;
    private boolean mUseInstantPreferenceChangeCallback;

    static MultiSelectListPreferenceFragment newInstance(String key, boolean fullScreen) {
        MultiSelectListPreferenceFragment fragment = new MultiSelectListPreferenceFragment();
        Bundle b = new Bundle(1);
        b.putString("key", key);
        b.putBoolean(ARG_FULLSCREEN, fullScreen);
        fragment.setArguments(b);
        return fragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (CarUi.getToolbar(getActivity()) == null) {
            return inflater.inflate(R.layout.car_ui_list_preference_with_toolbar, container, false);
        }
        return inflater.inflate(R.layout.car_ui_list_preference, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final CarUiRecyclerView recyclerView = (CarUiRecyclerView) CarUiUtils.requireViewByRefId(view, R.id.list);
        this.mFullScreen = requireArguments().getBoolean(ARG_FULLSCREEN, true);
        this.mUseInstantPreferenceChangeCallback = getResources().getBoolean(R.bool.car_ui_preference_list_instant_change_callback);
        ToolbarController toolbar = this.mFullScreen ? CarUi.getToolbar(requireActivity()) : null;
        this.mToolbar = toolbar;
        if (toolbar == null) {
            Toolbar toolbarView = (Toolbar) CarUiUtils.findViewByRefId(view, R.id.toolbar);
            this.mToolbar = toolbarView;
            if (toolbarView != null) {
                recyclerView.setPadding(0, toolbarView.getHeight(), 0, 0);
                toolbarView.registerToolbarHeightChangeListener(new Toolbar.OnHeightChangedListener() { // from class: com.android.car.ui.preference.-$$Lambda$MultiSelectListPreferenceFragment$2pkVfg6YNXu7IADFdL2cnseBNTc
                    @Override // com.android.car.ui.toolbar.Toolbar.OnHeightChangedListener
                    public final void onHeightChanged(int i) {
                        MultiSelectListPreferenceFragment.lambda$onViewCreated$0(recyclerView, view, i);
                    }
                });
            }
        }
        this.mPreference = getPreference();
        recyclerView.setClipToPadding(false);
        ToolbarController toolbarController = this.mToolbar;
        if (toolbarController != null) {
            toolbarController.setTitle(this.mPreference.getTitle());
            this.mToolbar.setState(Toolbar.State.SUBPAGE);
        }
        this.mNewValues = new HashSet(this.mPreference.getValues());
        CharSequence[] entries = this.mPreference.getEntries();
        CharSequence[] entryValues = this.mPreference.getEntryValues();
        if (entries == null || entryValues == null) {
            throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
        }
        if (entries.length != entryValues.length) {
            throw new IllegalStateException("MultiSelectListPreference entries array length does not match entryValues array length.");
        }
        List<CarUiListItem> listItems = new ArrayList<>();
        boolean[] selectedItems = this.mPreference.getSelectedItems();
        for (int i = 0; i < entries.length; i++) {
            String entry = entries[i].toString();
            final String entryValue = entryValues[i].toString();
            CarUiContentListItem item = new CarUiContentListItem(CarUiContentListItem.Action.CHECK_BOX);
            item.setTitle(entry);
            item.setChecked(selectedItems[i]);
            item.setOnCheckedChangeListener(new CarUiContentListItem.OnCheckedChangeListener() { // from class: com.android.car.ui.preference.-$$Lambda$MultiSelectListPreferenceFragment$YnepC-0onrBWA20eSGEJPNKYYWk
                @Override // com.android.car.ui.recyclerview.CarUiContentListItem.OnCheckedChangeListener
                public final void onCheckedChanged(CarUiContentListItem carUiContentListItem, boolean z) {
                    this.f$0.lambda$onViewCreated$1$MultiSelectListPreferenceFragment(entryValue, carUiContentListItem, z);
                }
            });
            listItems.add(item);
        }
        CarUiListItemAdapter adapter = new CarUiListItemAdapter(listItems);
        recyclerView.setAdapter(adapter);
    }

    static /* synthetic */ void lambda$onViewCreated$0(CarUiRecyclerView recyclerView, View view, int newHeight) {
        if (recyclerView.getPaddingTop() == newHeight) {
            return;
        }
        int oldHeight = recyclerView.getPaddingTop();
        recyclerView.setPadding(0, newHeight, 0, 0);
        recyclerView.scrollBy(0, oldHeight - newHeight);
        FocusArea focusArea = (FocusArea) view.findViewById(R.id.car_ui_focus_area);
        if (focusArea != null) {
            focusArea.setHighlightPadding(0, newHeight, 0, 0);
            focusArea.setBoundsOffset(0, newHeight, 0, 0);
        }
    }

    public /* synthetic */ void lambda$onViewCreated$1$MultiSelectListPreferenceFragment(String entryValue, CarUiContentListItem listItem, boolean isChecked) {
        if (isChecked) {
            this.mNewValues.add(entryValue);
        } else {
            this.mNewValues.remove(entryValue);
        }
        if (this.mUseInstantPreferenceChangeCallback) {
            updatePreference();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Insets insets = CarUi.getInsets(getActivity());
        if (insets != null) {
            onCarUiInsetsChanged(insets);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (!this.mUseInstantPreferenceChangeCallback) {
            updatePreference();
        }
    }

    private void updatePreference() {
        if (this.mPreference.callChangeListener(this.mNewValues)) {
            this.mPreference.setValues(this.mNewValues);
        }
    }

    private CarUiMultiSelectListPreference getPreference() {
        if (getArguments() == null) {
            throw new IllegalStateException("Preference arguments cannot be null");
        }
        String key = getArguments().getString("key");
        DialogPreference.TargetFragment fragment = (DialogPreference.TargetFragment) getTargetFragment();
        if (key == null) {
            throw new IllegalStateException("MultiSelectListPreference key not found in Fragment arguments");
        }
        if (fragment == null) {
            throw new IllegalStateException("Target fragment must be registered before displaying MultiSelectListPreference screen.");
        }
        Preference preference = fragment.findPreference(key);
        if (!(preference instanceof CarUiMultiSelectListPreference)) {
            throw new IllegalStateException("Cannot use MultiSelectListPreferenceFragment with a preference that is not of type CarUiMultiSelectListPreference");
        }
        return (CarUiMultiSelectListPreference) preference;
    }

    @Override // com.android.car.ui.baselayout.InsetsChangedListener
    public void onCarUiInsetsChanged(Insets insets) {
        if (!this.mFullScreen) {
            return;
        }
        View view = requireView();
        CarUiUtils.requireViewByRefId(view, R.id.list).setPadding(0, insets.getTop(), 0, insets.getBottom());
        view.setPadding(insets.getLeft(), 0, insets.getRight(), 0);
        FocusArea focusArea = (FocusArea) view.findViewById(R.id.car_ui_focus_area);
        if (focusArea != null) {
            focusArea.setHighlightPadding(0, insets.getTop(), 0, insets.getBottom());
            focusArea.setBoundsOffset(0, insets.getTop(), 0, insets.getBottom());
        }
    }
}
