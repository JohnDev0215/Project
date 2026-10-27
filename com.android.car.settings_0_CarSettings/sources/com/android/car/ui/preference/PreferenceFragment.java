package com.android.car.ui.preference;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.preference.DialogPreference;
import androidx.preference.DropDownPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import androidx.preference.TwoStatePreference;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.FocusArea;
import com.android.car.ui.R;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.baselayout.InsetsChangedListener;
import com.android.car.ui.core.CarUi;
import com.android.car.ui.toolbar.Toolbar;
import com.android.car.ui.toolbar.ToolbarController;
import com.android.car.ui.utils.CarUiUtils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public abstract class PreferenceFragment extends PreferenceFragmentCompat implements InsetsChangedListener {
    private static final String DIALOG_FRAGMENT_TAG = "com.android.car.ui.PreferenceFragment.DIALOG";
    private static final String TAG = "CarUiPreferenceFragment";
    private static final List<Pair<Class<? extends Preference>, Class<? extends Preference>>> sPreferenceMapping = Arrays.asList(new Pair(DropDownPreference.class, CarUiDropDownPreference.class), new Pair(ListPreference.class, CarUiListPreference.class), new Pair(MultiSelectListPreference.class, CarUiMultiSelectListPreference.class), new Pair(EditTextPreference.class, CarUiEditTextPreference.class), new Pair(SwitchPreference.class, CarUiSwitchPreference.class), new Pair(Preference.class, CarUiPreference.class));

    protected boolean isFullScreenFragment() {
        return true;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        ToolbarController baseLayoutToolbar;
        super.onViewCreated(view, savedInstanceState);
        if (isFullScreenFragment() && (baseLayoutToolbar = CarUi.getToolbar(getActivity())) != null) {
            baseLayoutToolbar.setState(Toolbar.State.SUBPAGE);
            if (getPreferenceScreen() != null) {
                baseLayoutToolbar.setTitle(getPreferenceScreen().getTitle());
            }
        }
        final RecyclerView recyclerView = (RecyclerView) CarUiUtils.findViewByRefId(view, R.id.recycler_view);
        Toolbar toolbar = (Toolbar) CarUiUtils.findViewByRefId(view, R.id.toolbar);
        if (recyclerView == null || toolbar == null) {
            return;
        }
        recyclerView.setPadding(0, toolbar.getHeight(), 0, 0);
        toolbar.registerToolbarHeightChangeListener(new Toolbar.OnHeightChangedListener() { // from class: com.android.car.ui.preference.-$$Lambda$PreferenceFragment$XyJZvjb5nY6_25MN5od6qlvvtVM
            @Override // com.android.car.ui.toolbar.Toolbar.OnHeightChangedListener
            public final void onHeightChanged(int i) {
                PreferenceFragment.lambda$onViewCreated$0(recyclerView, view, i);
            }
        });
        recyclerView.setClipToPadding(false);
        if (getPreferenceScreen() != null) {
            toolbar.setTitle(getPreferenceScreen().getTitle());
        }
    }

    static /* synthetic */ void lambda$onViewCreated$0(RecyclerView recyclerView, View view, int newHeight) {
        if (recyclerView.getPaddingTop() == newHeight) {
            return;
        }
        int oldHeight = recyclerView.getPaddingTop();
        recyclerView.setPadding(0, newHeight, 0, 0);
        recyclerView.scrollBy(0, oldHeight - newHeight);
        FocusArea focusArea = (FocusArea) CarUiUtils.requireViewByRefId(view, R.id.car_ui_focus_area);
        focusArea.setHighlightPadding(0, newHeight, 0, 0);
        focusArea.setBoundsOffset(0, newHeight, 0, 0);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Insets insets = CarUi.getInsets(getActivity());
        if (insets != null) {
            onCarUiInsetsChanged(insets);
        }
    }

    @Override // com.android.car.ui.baselayout.InsetsChangedListener
    public void onCarUiInsetsChanged(Insets insets) {
        if (!isFullScreenFragment()) {
            return;
        }
        View view = requireView();
        FocusArea focusArea = (FocusArea) CarUiUtils.requireViewByRefId(view, R.id.car_ui_focus_area);
        focusArea.setHighlightPadding(0, insets.getTop(), 0, insets.getBottom());
        focusArea.setBoundsOffset(0, insets.getTop(), 0, insets.getBottom());
        CarUiUtils.requireViewByRefId(view, R.id.recycler_view).setPadding(0, insets.getTop(), 0, insets.getBottom());
        view.setPadding(insets.getLeft(), 0, insets.getRight(), 0);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnDisplayPreferenceDialogListener
    public void onDisplayPreferenceDialog(Preference preference) {
        Fragment f;
        if (((getActivity() instanceof PreferenceFragmentCompat.OnPreferenceDisplayDialogCallback) && ((PreferenceFragmentCompat.OnPreferenceDisplayDialogCallback) getActivity()).onPreferenceDisplayDialog(this, preference)) || requireFragmentManager().findFragmentByTag(DIALOG_FRAGMENT_TAG) != null) {
            return;
        }
        if (preference instanceof EditTextPreference) {
            f = EditTextPreferenceDialogFragment.newInstance(preference.getKey());
        } else if (preference instanceof ListPreference) {
            f = ListPreferenceFragment.newInstance(preference.getKey(), isFullScreenFragment());
        } else if (preference instanceof MultiSelectListPreference) {
            f = MultiSelectListPreferenceFragment.newInstance(preference.getKey(), isFullScreenFragment());
        } else if (preference instanceof CarUiSeekBarDialogPreference) {
            f = SeekbarPreferenceDialogFragment.newInstance(preference.getKey());
        } else {
            throw new IllegalArgumentException("Cannot display dialog for an unknown Preference type: " + preference.getClass().getSimpleName() + ". Make sure to implement onPreferenceDisplayDialog() to handle displaying a custom dialog for this Preference.");
        }
        f.setTargetFragment(this, 0);
        if (f instanceof DialogFragment) {
            ((DialogFragment) f).show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
        } else {
            if (getActivity() == null) {
                throw new IllegalStateException("Preference fragment is not attached to an Activity.");
            }
            if (getView() == null) {
                throw new IllegalStateException("Preference fragment must have a layout.");
            }
            Context context = getContext();
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(CarUiUtils.getAttrResourceId(context, android.R.attr.fragmentOpenEnterAnimation), CarUiUtils.getAttrResourceId(context, android.R.attr.fragmentOpenExitAnimation), CarUiUtils.getAttrResourceId(context, android.R.attr.fragmentCloseEnterAnimation), CarUiUtils.getAttrResourceId(context, android.R.attr.fragmentCloseExitAnimation)).replace(((ViewGroup) getView().getParent()).getId(), f).addToBackStack(null).commit();
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        Map<Preference, String> dependencies = new HashMap<>();
        List<Preference> children = new ArrayList<>();
        Deque<Preference> stack = new ArrayDeque<>();
        stack.addFirst(preferenceScreen);
        while (!stack.isEmpty()) {
            Preference preference = stack.removeFirst();
            if (preference instanceof PreferenceGroup) {
                PreferenceGroup pg = (PreferenceGroup) preference;
                children.clear();
                for (int i = 0; i < pg.getPreferenceCount(); i++) {
                    children.add(pg.getPreference(i));
                }
                pg.removeAll();
                for (Preference child : children) {
                    Preference replacement = getReplacementFor(child);
                    dependencies.put(replacement, child.getDependency());
                    pg.addPreference(replacement);
                    stack.addFirst(replacement);
                }
            }
        }
        super.setPreferenceScreen(preferenceScreen);
        for (Map.Entry<Preference, String> entry : dependencies.entrySet()) {
            entry.getKey().setDependency(entry.getValue());
        }
    }

    private static Preference getReplacementFor(Preference preference) {
        Class<?> cls = preference.getClass();
        for (Pair<Class<? extends Preference>, Class<? extends Preference>> replacement : sPreferenceMapping) {
            Class<? extends Preference> source = (Class) replacement.first;
            Class<? extends Preference> target = (Class) replacement.second;
            if (source.isAssignableFrom(cls)) {
                if (cls == source) {
                    try {
                        return copyPreference(preference, (Preference) target.getDeclaredConstructor(Context.class).newInstance(preference.getContext()));
                    } catch (ReflectiveOperationException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (cls == target || source == Preference.class) {
                    return preference;
                }
                Log.w(TAG, "Subclass of " + source.getSimpleName() + " was used, preventing us from substituting it with " + target.getSimpleName());
                return preference;
            }
        }
        return preference;
    }

    private static Preference copyPreference(Preference from, Preference to) {
        to.setTitle(from.getTitle());
        to.setOnPreferenceClickListener(from.getOnPreferenceClickListener());
        to.setOnPreferenceChangeListener(from.getOnPreferenceChangeListener());
        to.setIcon(from.getIcon());
        to.setFragment(from.getFragment());
        to.setIntent(from.getIntent());
        to.setKey(from.getKey());
        to.setOrder(from.getOrder());
        to.setSelectable(from.isSelectable());
        to.setPersistent(from.isPersistent());
        to.setIconSpaceReserved(from.isIconSpaceReserved());
        to.setWidgetLayoutResource(from.getWidgetLayoutResource());
        to.setPreferenceDataStore(from.getPreferenceDataStore());
        to.setShouldDisableView(from.getShouldDisableView());
        to.setSingleLineTitle(from.isSingleLineTitle());
        to.setVisible(from.isVisible());
        to.setLayoutResource(from.getLayoutResource());
        to.setCopyingEnabled(from.isCopyingEnabled());
        if (from.getSummaryProvider() != null) {
            to.setSummaryProvider(from.getSummaryProvider());
        } else {
            to.setSummary(from.getSummary());
        }
        if (from.peekExtras() != null) {
            to.getExtras().putAll(from.peekExtras());
        }
        if (from instanceof DialogPreference) {
            DialogPreference fromDialog = (DialogPreference) from;
            DialogPreference toDialog = (DialogPreference) to;
            toDialog.setDialogTitle(fromDialog.getDialogTitle());
            toDialog.setDialogIcon(fromDialog.getDialogIcon());
            toDialog.setDialogMessage(fromDialog.getDialogMessage());
            toDialog.setDialogLayoutResource(fromDialog.getDialogLayoutResource());
            toDialog.setNegativeButtonText(fromDialog.getNegativeButtonText());
            toDialog.setPositiveButtonText(fromDialog.getPositiveButtonText());
        }
        if (from instanceof ListPreference) {
            ListPreference fromList = (ListPreference) from;
            ListPreference toList = (ListPreference) to;
            toList.setEntries(fromList.getEntries());
            toList.setEntryValues(fromList.getEntryValues());
            toList.setValue(fromList.getValue());
        } else if (from instanceof EditTextPreference) {
            EditTextPreference fromText = (EditTextPreference) from;
            EditTextPreference toText = (EditTextPreference) to;
            toText.setText(fromText.getText());
        } else if (from instanceof MultiSelectListPreference) {
            MultiSelectListPreference fromMulti = (MultiSelectListPreference) from;
            MultiSelectListPreference toMulti = (MultiSelectListPreference) to;
            toMulti.setEntries(fromMulti.getEntries());
            toMulti.setEntryValues(fromMulti.getEntryValues());
            toMulti.setValues(fromMulti.getValues());
        } else if (from instanceof TwoStatePreference) {
            TwoStatePreference fromTwoState = (TwoStatePreference) from;
            TwoStatePreference toTwoState = (TwoStatePreference) to;
            toTwoState.setSummaryOn(fromTwoState.getSummaryOn());
            toTwoState.setSummaryOff(fromTwoState.getSummaryOff());
            if (from instanceof SwitchPreference) {
                SwitchPreference fromSwitch = (SwitchPreference) from;
                SwitchPreference toSwitch = (SwitchPreference) to;
                toSwitch.setSwitchTextOn(fromSwitch.getSwitchTextOn());
                toSwitch.setSwitchTextOff(fromSwitch.getSwitchTextOff());
            }
        }
        return to;
    }
}
