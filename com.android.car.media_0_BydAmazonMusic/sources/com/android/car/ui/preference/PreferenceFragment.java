package com.android.car.ui.preference;

import android.R;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.preference.DialogPreference;
import androidx.preference.DropDownPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import androidx.preference.TwoStatePreference;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R$id;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.core.BaseLayoutController;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.rotary.FocusArea;
import com.android.car.ui.toolbar.j;
import java.util.Arrays;
import java.util.List;
import spi.ab;
import spi.b2;
import spi.c;
import spi.fc;
import spi.z4;

/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(28)
public abstract class PreferenceFragment extends PreferenceFragmentCompat implements z4 {
    public static final List<Pair<Class<? extends Preference>, Class<? extends Preference>>> d = Arrays.asList(new Pair(DropDownPreference.class, CarUiDropDownPreference.class), new Pair(ListPreference.class, CarUiListPreference.class), new Pair(MultiSelectListPreference.class, CarUiMultiSelectListPreference.class), new Pair(EditTextPreference.class, CarUiEditTextPreference.class), new Pair(SwitchPreference.class, CarUiSwitchPreference.class), new Pair(Preference.class, CarUiPreference.class));

    @NonNull
    public CarUiRecyclerView a;

    @Nullable
    public String b;
    public int c;

    public class a implements RecyclerView.OnChildAttachStateChangeListener {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
        public final void onChildViewAttachedToWindow(View view) {
            if (PreferenceFragment.this.a.getChildLayoutPosition(view) == PreferenceFragment.this.c) {
                view.requestFocus();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
        public final void onChildViewDetachedFromWindow(View view) {
        }
    }

    public interface b {
        RecyclerView getRecyclerView();
    }

    public static Preference c(Preference preference, Preference preference2) {
        preference2.setTitle(preference.getTitle());
        preference2.setOnPreferenceClickListener(preference.getOnPreferenceClickListener());
        preference2.setOnPreferenceChangeListener(preference.getOnPreferenceChangeListener());
        preference2.setIcon(preference.getIcon());
        preference2.setFragment(preference.getFragment());
        preference2.setIntent(preference.getIntent());
        preference2.setKey(preference.getKey());
        preference2.setOrder(preference.getOrder());
        preference2.setSelectable(preference.isSelectable());
        preference2.setPersistent(preference.isPersistent());
        preference2.setIconSpaceReserved(preference.isIconSpaceReserved());
        preference2.setWidgetLayoutResource(preference.getWidgetLayoutResource());
        preference2.setPreferenceDataStore(preference.getPreferenceDataStore());
        preference2.setSingleLineTitle(preference.isSingleLineTitle());
        preference2.setVisible(preference.isVisible());
        preference2.setLayoutResource(preference.getLayoutResource());
        preference2.setCopyingEnabled(preference.isCopyingEnabled());
        if (!(preference2 instanceof fc)) {
            preference2.setShouldDisableView(preference.getShouldDisableView());
        }
        if (preference.getSummaryProvider() != null) {
            preference2.setSummaryProvider(preference.getSummaryProvider());
        } else {
            preference2.setSummary(preference.getSummary());
        }
        if (preference.peekExtras() != null) {
            preference2.getExtras().putAll(preference.peekExtras());
        }
        if (preference instanceof DialogPreference) {
            DialogPreference dialogPreference = (DialogPreference) preference;
            DialogPreference dialogPreference2 = (DialogPreference) preference2;
            dialogPreference2.setDialogTitle(dialogPreference.getDialogTitle());
            dialogPreference2.setDialogIcon(dialogPreference.getDialogIcon());
            dialogPreference2.setDialogMessage(dialogPreference.getDialogMessage());
            dialogPreference2.setDialogLayoutResource(dialogPreference.getDialogLayoutResource());
            dialogPreference2.setNegativeButtonText(dialogPreference.getNegativeButtonText());
            dialogPreference2.setPositiveButtonText(dialogPreference.getPositiveButtonText());
        }
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            ListPreference listPreference2 = (ListPreference) preference2;
            listPreference2.setEntries(listPreference.getEntries());
            listPreference2.setEntryValues(listPreference.getEntryValues());
            listPreference2.setValue(listPreference.getValue());
        } else if (preference instanceof EditTextPreference) {
            ((EditTextPreference) preference2).setText(((EditTextPreference) preference).getText());
        } else if (preference instanceof MultiSelectListPreference) {
            MultiSelectListPreference multiSelectListPreference = (MultiSelectListPreference) preference;
            MultiSelectListPreference multiSelectListPreference2 = (MultiSelectListPreference) preference2;
            multiSelectListPreference2.setEntries(multiSelectListPreference.getEntries());
            multiSelectListPreference2.setEntryValues(multiSelectListPreference.getEntryValues());
            multiSelectListPreference2.setValues(multiSelectListPreference.getValues());
        } else if (preference instanceof TwoStatePreference) {
            TwoStatePreference twoStatePreference = (TwoStatePreference) preference;
            TwoStatePreference twoStatePreference2 = (TwoStatePreference) preference2;
            twoStatePreference2.setSummaryOn(twoStatePreference.getSummaryOn());
            twoStatePreference2.setSummaryOff(twoStatePreference.getSummaryOff());
            if (preference instanceof SwitchPreference) {
                SwitchPreference switchPreference = (SwitchPreference) preference;
                SwitchPreference switchPreference2 = (SwitchPreference) preference2;
                switchPreference2.setSwitchTextOn(switchPreference.getSwitchTextOn());
                switchPreference2.setSwitchTextOff(switchPreference.getSwitchTextOff());
            }
        }
        return preference2;
    }

    @Override // spi.z4
    public final void b(@NonNull Insets insets) {
        View viewRequireView = requireView();
        FocusArea focusArea = (FocusArea) b2.n(viewRequireView, R$id.car_ui_focus_area);
        focusArea.b(0, insets.getTop(), 0, insets.getBottom());
        focusArea.a(0, insets.getTop(), 0, insets.getBottom());
        this.a.setPadding(0, insets.getTop(), 0, insets.getBottom());
        viewRequireView.setPadding(insets.getLeft(), 0, insets.getRight(), 0);
    }

    @Nullable
    public final Insets d() {
        BaseLayoutController baseLayoutController = BaseLayoutController.getBaseLayoutController(getActivity());
        if (baseLayoutController != null) {
            return baseLayoutController.getInsets();
        }
        return null;
    }

    @Nullable
    public final j e() {
        BaseLayoutController baseLayoutController = BaseLayoutController.getBaseLayoutController(getActivity());
        if (baseLayoutController != null) {
            return baseLayoutController.getToolbarController();
        }
        return null;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public final RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CarUiRecyclerView carUiRecyclerView = (CarUiRecyclerView) b2.n(viewGroup, R$id.recycler_view);
        this.a = carUiRecyclerView;
        RecyclerView recyclerView = carUiRecyclerView instanceof b ? ((b) carUiRecyclerView).getRecyclerView() : null;
        if (recyclerView == null) {
            recyclerView = super.onCreateRecyclerView(layoutInflater, viewGroup, bundle);
        }
        CarUiRecyclerView carUiRecyclerView2 = this.a;
        if (carUiRecyclerView2 != null) {
            carUiRecyclerView2.addOnChildAttachStateChangeListener(new a());
        }
        return recyclerView;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnDisplayPreferenceDialogListener
    public final void onDisplayPreferenceDialog(Preference preference) {
        Fragment seekbarPreferenceDialogFragment;
        if (!((getActivity() instanceof PreferenceFragmentCompat.OnPreferenceDisplayDialogCallback) && ((PreferenceFragmentCompat.OnPreferenceDisplayDialogCallback) getActivity()).onPreferenceDisplayDialog(this, preference)) && requireFragmentManager().findFragmentByTag("com.android.car.ui.PreferenceFragment.DIALOG") == null) {
            if (preference instanceof EditTextPreference) {
                String key = preference.getKey();
                seekbarPreferenceDialogFragment = new EditTextPreferenceDialogFragment();
                Bundle bundle = new Bundle(1);
                bundle.putString("key", key);
                seekbarPreferenceDialogFragment.setArguments(bundle);
            } else if (preference instanceof ListPreference) {
                String key2 = preference.getKey();
                seekbarPreferenceDialogFragment = new ListPreferenceFragment();
                Bundle bundle2 = new Bundle(1);
                bundle2.putString("key", key2);
                seekbarPreferenceDialogFragment.setArguments(bundle2);
            } else if (preference instanceof MultiSelectListPreference) {
                String key3 = preference.getKey();
                seekbarPreferenceDialogFragment = new MultiSelectListPreferenceFragment();
                Bundle bundle3 = new Bundle(1);
                bundle3.putString("key", key3);
                seekbarPreferenceDialogFragment.setArguments(bundle3);
            } else {
                if (!(preference instanceof CarUiSeekBarDialogPreference)) {
                    StringBuilder sbK = c.k("Cannot display dialog for an unknown Preference type: ");
                    sbK.append(preference.getClass().getSimpleName());
                    sbK.append(". Make sure to implement onPreferenceDisplayDialog() to handle displaying a custom dialog for this Preference.");
                    throw new IllegalArgumentException(sbK.toString());
                }
                String key4 = preference.getKey();
                seekbarPreferenceDialogFragment = new SeekbarPreferenceDialogFragment();
                Bundle bundle4 = new Bundle(1);
                bundle4.putString("key", key4);
                seekbarPreferenceDialogFragment.setArguments(bundle4);
            }
            seekbarPreferenceDialogFragment.setTargetFragment(this, 0);
            if (seekbarPreferenceDialogFragment instanceof DialogFragment) {
                ((DialogFragment) seekbarPreferenceDialogFragment).show(getParentFragmentManager(), "com.android.car.ui.PreferenceFragment.DIALOG");
            } else {
                if (getActivity() == null) {
                    throw new IllegalStateException("Preference fragment is not attached to an Activity.");
                }
                if (getView() == null) {
                    throw new IllegalStateException("Preference fragment must have a layout.");
                }
                Context context = getContext();
                getParentFragmentManager().beginTransaction().setCustomAnimations(b2.f(context, R.attr.fragmentOpenEnterAnimation), b2.f(context, R.attr.fragmentOpenExitAnimation), b2.f(context, R.attr.fragmentCloseEnterAnimation), b2.f(context, R.attr.fragmentCloseExitAnimation)).replace(((ViewGroup) getView().getParent()).getId(), seekbarPreferenceDialogFragment).addToBackStack(null).commit();
            }
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public final boolean onPreferenceTreeClick(Preference preference) {
        this.b = preference.getKey();
        if (getView() != null) {
            this.c = this.a.getChildLayoutPosition(getView().findFocus());
        } else {
            Log.e("PreferenceFragment", "onPreferenceTreeClick getView() == null");
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        String str = this.b;
        if (str != null) {
            scrollToPreference(str);
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public final void onStart() {
        super.onStart();
        Insets insetsD = d();
        if (insetsD != null) {
            b(insetsD);
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public final void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        j jVarE = e();
        if (jVarE != null) {
            jVarE.setNavButtonMode(ab.BACK);
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            if (preferenceScreen != null) {
                jVarE.setTitle(preferenceScreen.getTitle());
            } else {
                jVarE.setTitle("");
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0070, code lost:
    
        if (r7 != r10) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0073, code lost:
    
        r7 = (androidx.preference.Preference) r9.getDeclaredConstructor(android.content.Context.class).newInstance(r6.getContext());
        c(r6, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x008f, code lost:
    
        r12 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0095, code lost:
    
        throw new java.lang.RuntimeException(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0096, code lost:
    
        if (r7 == r9) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x009a, code lost:
    
        if (r10 != androidx.preference.Preference.class) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009d, code lost:
    
        r7 = spi.c.k("Subclass of ");
        r7.append(r10.getSimpleName());
        r7.append(" was used, preventing us from substituting it with ");
        r7.append(r9.getSimpleName());
        android.util.Log.w("CarUiPreferenceFragment", r7.toString());
     */
    @Override // androidx.preference.PreferenceFragmentCompat
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setPreferenceScreen(androidx.preference.PreferenceScreen r13) {
        /*
            Method dump skipped, instruction units count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.ui.preference.PreferenceFragment.setPreferenceScreen(androidx.preference.PreferenceScreen):void");
    }
}
