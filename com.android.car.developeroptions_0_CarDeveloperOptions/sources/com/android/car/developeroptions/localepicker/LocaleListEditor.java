package com.android.car.developeroptions.localepicker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.RestrictedSettingsFragment;
import com.android.internal.app.LocalePicker;
import com.android.internal.app.LocaleStore;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LocaleListEditor extends RestrictedSettingsFragment {
    private LocaleDragAndDropAdapter mAdapter;
    private View mAddLanguage;
    private boolean mIsUiRestricted;
    private Menu mMenu;
    private boolean mRemoveMode;
    private boolean mShowingRemoveDialog;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 344;
    }

    public LocaleListEditor() {
        super("no_config_locale");
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        LocaleStore.fillCache(getContext());
        this.mAdapter = new LocaleDragAndDropAdapter(getContext(), getUserLocaleList());
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        configureDragAndDrop(layoutInflater.inflate(R.layout.locale_order_list, (ViewGroup) viewOnCreateView));
        return viewOnCreateView;
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        boolean z = this.mIsUiRestricted;
        this.mIsUiRestricted = isUiRestricted();
        TextView emptyTextView = getEmptyTextView();
        if (this.mIsUiRestricted && !z) {
            emptyTextView.setText(R.string.language_empty_list_user_restricted);
            emptyTextView.setVisibility(0);
            updateVisibilityOfRemoveMenu();
        } else {
            if (this.mIsUiRestricted || !z) {
                return;
            }
            emptyTextView.setVisibility(8);
            updateVisibilityOfRemoveMenu();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            this.mRemoveMode = bundle.getBoolean("localeRemoveMode", false);
            this.mShowingRemoveDialog = bundle.getBoolean("showingLocaleRemoveDialog", false);
        }
        setRemoveMode(this.mRemoveMode);
        this.mAdapter.restoreState(bundle);
        if (this.mShowingRemoveDialog) {
            showRemoveLocaleWarningDialog();
        }
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("localeRemoveMode", this.mRemoveMode);
        bundle.putBoolean("showingLocaleRemoveDialog", this.mShowingRemoveDialog);
        this.mAdapter.saveState(bundle);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 2) {
            if (this.mRemoveMode) {
                showRemoveLocaleWarningDialog();
            } else {
                setRemoveMode(true);
            }
            return true;
        }
        if (itemId == 16908332 && this.mRemoveMode) {
            setRemoveMode(false);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 0 && i2 == -1 && intent != null) {
            this.mAdapter.addLocale(intent.getSerializableExtra("localeInfo"));
            updateVisibilityOfRemoveMenu();
        }
        super.onActivityResult(i, i2, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRemoveMode(boolean z) {
        this.mRemoveMode = z;
        this.mAdapter.setRemoveMode(z);
        this.mAddLanguage.setVisibility(z ? 4 : 0);
        updateVisibilityOfRemoveMenu();
    }

    private void showRemoveLocaleWarningDialog() {
        int checkedCount = this.mAdapter.getCheckedCount();
        if (checkedCount == 0) {
            setRemoveMode(!this.mRemoveMode);
            return;
        }
        if (checkedCount == this.mAdapter.getItemCount()) {
            this.mShowingRemoveDialog = true;
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.dlg_remove_locales_error_title);
            builder.setMessage(R.string.dlg_remove_locales_error_message);
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(this) { // from class: com.android.car.developeroptions.localepicker.LocaleListEditor.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.car.developeroptions.localepicker.LocaleListEditor.1
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    LocaleListEditor.this.mShowingRemoveDialog = false;
                }
            });
            builder.create().show();
            return;
        }
        String quantityString = getResources().getQuantityString(R.plurals.dlg_remove_locales_title, checkedCount);
        this.mShowingRemoveDialog = true;
        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
        builder2.setTitle(quantityString);
        builder2.setMessage(R.string.dlg_remove_locales_message);
        builder2.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.localepicker.LocaleListEditor.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LocaleListEditor.this.setRemoveMode(false);
            }
        });
        builder2.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.localepicker.LocaleListEditor.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LocaleListEditor.this.mRemoveMode = false;
                LocaleListEditor.this.mShowingRemoveDialog = false;
                LocaleListEditor.this.mAdapter.removeChecked();
                LocaleListEditor.this.setRemoveMode(false);
            }
        });
        builder2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.car.developeroptions.localepicker.LocaleListEditor.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                LocaleListEditor.this.mShowingRemoveDialog = false;
            }
        });
        builder2.create().show();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MenuItem menuItemAdd = menu.add(0, 2, 0, R.string.locale_remove_menu);
        menuItemAdd.setShowAsAction(4);
        menuItemAdd.setIcon(R.drawable.ic_delete);
        super.onCreateOptionsMenu(menu, menuInflater);
        this.mMenu = menu;
        updateVisibilityOfRemoveMenu();
    }

    private List<LocaleStore.LocaleInfo> getUserLocaleList() {
        ArrayList arrayList = new ArrayList();
        LocaleList locales = LocalePicker.getLocales();
        for (int i = 0; i < locales.size(); i++) {
            arrayList.add(LocaleStore.getLocaleInfo(locales.get(i)));
        }
        return arrayList;
    }

    private void configureDragAndDrop(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dragList);
        LocaleLinearLayoutManager localeLinearLayoutManager = new LocaleLinearLayoutManager(getContext(), this.mAdapter);
        localeLinearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(localeLinearLayoutManager);
        recyclerView.setHasFixedSize(true);
        this.mAdapter.setRecyclerView(recyclerView);
        recyclerView.setAdapter(this.mAdapter);
        View viewFindViewById = view.findViewById(R.id.add_language);
        this.mAddLanguage = viewFindViewById;
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.localepicker.LocaleListEditor.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                LocaleListEditor.this.startActivityForResult(new Intent(LocaleListEditor.this.getActivity(), (Class<?>) LocalePickerWithRegionActivity.class), 0);
            }
        });
    }

    private void updateVisibilityOfRemoveMenu() {
        Menu menu = this.mMenu;
        if (menu == null) {
            return;
        }
        MenuItem menuItemFindItem = menu.findItem(2);
        if (menuItemFindItem != null) {
            boolean z = false;
            menuItemFindItem.setShowAsAction(this.mRemoveMode ? 2 : 0);
            if ((this.mAdapter.getItemCount() > 1) && !this.mIsUiRestricted) {
                z = true;
            }
            menuItemFindItem.setVisible(z);
        }
    }
}
