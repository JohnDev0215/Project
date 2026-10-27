package com.android.inputmethod.latin.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.preference.Preference;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.android.inputmethod.compat.InputMethodSubtypeCompatUtils;
import com.android.inputmethod.compat.ViewCompatUtils;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.RichInputMethodManager;
import com.android.inputmethod.latin.utils.AdditionalSubtypeUtils;
import com.android.inputmethod.latin.utils.SubtypeLocaleUtils;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes2.dex */
final class CustomInputStylePreference extends DialogPreference implements DialogInterface.OnCancelListener {
    private static final boolean DEBUG_SUBTYPE_ID = false;
    private static final String KEY_NEW_SUBTYPE = "subtype_pref_new";
    private static final String KEY_PREFIX = "subtype_pref_";
    private Spinner mKeyboardLayoutSetSpinner;
    private InputMethodSubtype mPreviousSubtype;
    private final Listener mProxy;
    private InputMethodSubtype mSubtype;
    private Spinner mSubtypeLocaleSpinner;

    interface Listener {
        KeyboardLayoutSetAdapter getKeyboardLayoutSetAdapter();

        SubtypeLocaleAdapter getSubtypeLocaleAdapter();

        void onAddCustomInputStyle(CustomInputStylePreference stylePref);

        void onRemoveCustomInputStyle(CustomInputStylePreference stylePref);

        void onSaveCustomInputStyle(CustomInputStylePreference stylePref);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Preference another) {
        return super.compareTo(another);
    }

    public static CustomInputStylePreference newIncompleteSubtypePreference(final Context context, final Listener proxy) {
        return new CustomInputStylePreference(context, null, proxy);
    }

    public CustomInputStylePreference(final Context context, final InputMethodSubtype subtype, final Listener proxy) {
        super(context, null);
        setDialogLayoutResource(R.layout.additional_subtype_dialog);
        setPersistent(false);
        this.mProxy = proxy;
        setSubtype(subtype);
    }

    public void show() {
        showDialog(null);
    }

    public final boolean isIncomplete() {
        return this.mSubtype == null;
    }

    public InputMethodSubtype getSubtype() {
        return this.mSubtype;
    }

    public void setSubtype(final InputMethodSubtype subtype) {
        this.mPreviousSubtype = this.mSubtype;
        this.mSubtype = subtype;
        if (isIncomplete()) {
            setTitle((CharSequence) null);
            setDialogTitle(R.string.add_style);
            setKey(KEY_NEW_SUBTYPE);
        } else {
            String subtypeDisplayNameInSystemLocale = SubtypeLocaleUtils.getSubtypeDisplayNameInSystemLocale(subtype);
            setTitle(subtypeDisplayNameInSystemLocale);
            setDialogTitle(subtypeDisplayNameInSystemLocale);
            setKey(KEY_PREFIX + subtype.getLocale() + "_" + SubtypeLocaleUtils.getKeyboardLayoutSetName(subtype));
        }
    }

    public void revert() {
        setSubtype(this.mPreviousSubtype);
    }

    public boolean hasBeenModified() {
        InputMethodSubtype inputMethodSubtype = this.mSubtype;
        return (inputMethodSubtype == null || inputMethodSubtype.equals(this.mPreviousSubtype)) ? false : true;
    }

    @Override // android.preference.DialogPreference
    protected View onCreateDialogView() {
        View viewOnCreateDialogView = super.onCreateDialogView();
        Spinner spinner = (Spinner) viewOnCreateDialogView.findViewById(R.id.subtype_locale_spinner);
        this.mSubtypeLocaleSpinner = spinner;
        spinner.setAdapter((SpinnerAdapter) this.mProxy.getSubtypeLocaleAdapter());
        Spinner spinner2 = (Spinner) viewOnCreateDialogView.findViewById(R.id.keyboard_layout_set_spinner);
        this.mKeyboardLayoutSetSpinner = spinner2;
        spinner2.setAdapter((SpinnerAdapter) this.mProxy.getKeyboardLayoutSetAdapter());
        ViewCompatUtils.setTextAlignment(this.mKeyboardLayoutSetSpinner, 5);
        return viewOnCreateDialogView;
    }

    @Override // android.preference.DialogPreference
    protected void onPrepareDialogBuilder(final AlertDialog.Builder builder) {
        builder.setCancelable(true).setOnCancelListener(this);
        if (isIncomplete()) {
            builder.setPositiveButton(R.string.add, this).setNegativeButton(android.R.string.cancel, this);
            return;
        }
        builder.setPositiveButton(R.string.save, this).setNeutralButton(android.R.string.cancel, this).setNegativeButton(R.string.remove, this);
        builder.setNegativeButton("", (DialogInterface.OnClickListener) null);
        SubtypeLocaleItem subtypeLocaleItem = new SubtypeLocaleItem(this.mSubtype);
        KeyboardLayoutSetItem keyboardLayoutSetItem = new KeyboardLayoutSetItem(this.mSubtype);
        setSpinnerPosition(this.mSubtypeLocaleSpinner, subtypeLocaleItem);
        setSpinnerPosition(this.mKeyboardLayoutSetSpinner, keyboardLayoutSetItem);
    }

    private static void setSpinnerPosition(final Spinner spinner, final Object itemToSelect) {
        int count = spinner.getAdapter().getCount();
        for (int i = 0; i < count; i++) {
            if (spinner.getItemAtPosition(i).equals(itemToSelect)) {
                spinner.setSelection(i);
                return;
            }
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(final DialogInterface dialog) {
        if (isIncomplete()) {
            this.mProxy.onRemoveCustomInputStyle(this);
        }
    }

    @Override // android.preference.DialogPreference, android.content.DialogInterface.OnClickListener
    public void onClick(final DialogInterface dialog, final int which) {
        super.onClick(dialog, which);
        if (which == -2) {
            this.mProxy.onRemoveCustomInputStyle(this);
            return;
        }
        if (which != -1) {
            return;
        }
        boolean z = !isIncomplete();
        setSubtype(AdditionalSubtypeUtils.createAsciiEmojiCapableAdditionalSubtype(((SubtypeLocaleItem) this.mSubtypeLocaleSpinner.getSelectedItem()).mLocaleString, ((KeyboardLayoutSetItem) this.mKeyboardLayoutSetSpinner.getSelectedItem()).mLayoutName));
        notifyChanged();
        if (z) {
            this.mProxy.onSaveCustomInputStyle(this);
        } else {
            this.mProxy.onAddCustomInputStyle(this);
        }
    }

    @Override // android.preference.DialogPreference, android.preference.Preference
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState();
        Dialog dialog = getDialog();
        if (dialog == null || !dialog.isShowing()) {
            return parcelableOnSaveInstanceState;
        }
        SavedState savedState = new SavedState(parcelableOnSaveInstanceState);
        savedState.mSubtype = this.mSubtype;
        return savedState;
    }

    @Override // android.preference.DialogPreference, android.preference.Preference
    protected void onRestoreInstanceState(final Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        setSubtype(savedState.mSubtype);
    }

    static final class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.android.inputmethod.latin.settings.CustomInputStylePreference.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(final Parcel source) {
                return new SavedState(source);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(final int size) {
                return new SavedState[size];
            }
        };
        InputMethodSubtype mSubtype;

        public SavedState(final Parcelable superState) {
            super(superState);
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(final Parcel dest, final int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(this.mSubtype, 0);
        }

        public SavedState(final Parcel source) {
            super(source);
            this.mSubtype = (InputMethodSubtype) source.readParcelable(null);
        }
    }

    static final class SubtypeLocaleItem implements Comparable<SubtypeLocaleItem> {
        private final String mDisplayName;
        public final String mLocaleString;

        public SubtypeLocaleItem(final InputMethodSubtype subtype) {
            String locale = subtype.getLocale();
            this.mLocaleString = locale;
            this.mDisplayName = SubtypeLocaleUtils.getSubtypeLocaleDisplayNameInSystemLocale(locale);
        }

        public String toString() {
            return this.mDisplayName;
        }

        @Override // java.lang.Comparable
        public int compareTo(final SubtypeLocaleItem o) {
            return this.mLocaleString.compareTo(o.mLocaleString);
        }
    }

    static final class SubtypeLocaleAdapter extends ArrayAdapter<SubtypeLocaleItem> {
        private static final String TAG_SUBTYPE = "SubtypeLocaleAdapter";

        public SubtypeLocaleAdapter(final Context context) {
            super(context, android.R.layout.simple_spinner_item);
            setDropDownViewResource(R.layout.byd_simple_spinner_dropdown_item);
            TreeSet treeSet = new TreeSet();
            InputMethodInfo inputMethodInfoOfThisIme = RichInputMethodManager.getInstance().getInputMethodInfoOfThisIme();
            int subtypeCount = inputMethodInfoOfThisIme.getSubtypeCount();
            for (int i = 0; i < subtypeCount; i++) {
                InputMethodSubtype subtypeAt = inputMethodInfoOfThisIme.getSubtypeAt(i);
                if (InputMethodSubtypeCompatUtils.isAsciiCapable(subtypeAt)) {
                    treeSet.add(new SubtypeLocaleItem(subtypeAt));
                }
            }
            addAll(treeSet);
        }
    }

    static final class KeyboardLayoutSetItem {
        private final String mDisplayName;
        public final String mLayoutName;

        public KeyboardLayoutSetItem(final InputMethodSubtype subtype) {
            this.mLayoutName = SubtypeLocaleUtils.getKeyboardLayoutSetName(subtype);
            this.mDisplayName = SubtypeLocaleUtils.getKeyboardLayoutSetDisplayName(subtype);
        }

        public String toString() {
            return this.mDisplayName;
        }
    }

    static final class KeyboardLayoutSetAdapter extends ArrayAdapter<KeyboardLayoutSetItem> {
        public KeyboardLayoutSetAdapter(final Context context) {
            super(context, android.R.layout.simple_spinner_item);
            setDropDownViewResource(R.layout.byd_simple_spinner_dropdown_item);
            for (String str : context.getResources().getStringArray(R.array.predefined_layouts)) {
                add(new KeyboardLayoutSetItem(AdditionalSubtypeUtils.createDummyAdditionalSubtype(SubtypeLocaleUtils.NO_LANGUAGE, str)));
            }
        }
    }
}
