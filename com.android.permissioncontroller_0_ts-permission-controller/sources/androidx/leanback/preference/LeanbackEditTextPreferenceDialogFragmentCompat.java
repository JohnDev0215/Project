package androidx.leanback.preference;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.preference.DialogPreference;
import androidx.preference.EditTextPreference;

/* JADX INFO: loaded from: classes3.dex */
public class LeanbackEditTextPreferenceDialogFragmentCompat extends LeanbackPreferenceDialogFragmentCompat {
    private static final int DEFAULT_IME_OPTIONS = 2;
    private static final int DEFAULT_INPUT_TYPE = 1;
    public static final String EXTRA_IME_OPTIONS = "ime_option";
    public static final String EXTRA_INPUT_TYPE = "input_type";
    private static final String SAVE_STATE_IME_OPTIONS = "LeanbackEditPreferenceDialog.imeOptions";
    private static final String SAVE_STATE_INPUT_TYPE = "LeanbackEditPreferenceDialog.inputType";
    private static final String SAVE_STATE_MESSAGE = "LeanbackEditPreferenceDialog.message";
    private static final String SAVE_STATE_TEXT = "LeanbackEditPreferenceDialog.text";
    private static final String SAVE_STATE_TITLE = "LeanbackEditPreferenceDialog.title";
    private CharSequence mDialogMessage;
    private CharSequence mDialogTitle;
    private int mImeOptions;
    private int mInputType;
    private CharSequence mText;

    public static LeanbackEditTextPreferenceDialogFragmentCompat newInstance(String key) {
        Bundle args = new Bundle(1);
        args.putString("key", key);
        LeanbackEditTextPreferenceDialogFragmentCompat fragment = new LeanbackEditTextPreferenceDialogFragmentCompat();
        fragment.setArguments(args);
        return fragment;
    }

    @Override // androidx.leanback.preference.LeanbackPreferenceDialogFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            DialogPreference preference = getPreference();
            this.mDialogTitle = preference.getDialogTitle();
            this.mDialogMessage = preference.getDialogMessage();
            if (preference instanceof EditTextPreference) {
                this.mDialogTitle = preference.getDialogTitle();
                this.mDialogMessage = preference.getDialogMessage();
                this.mText = ((EditTextPreference) preference).getText();
                this.mInputType = preference.getExtras().getInt(EXTRA_INPUT_TYPE, 1);
                this.mImeOptions = preference.getExtras().getInt(EXTRA_IME_OPTIONS, 2);
                return;
            }
            throw new IllegalArgumentException("Preference must be a EditTextPreference");
        }
        this.mDialogTitle = savedInstanceState.getCharSequence(SAVE_STATE_TITLE);
        this.mDialogMessage = savedInstanceState.getCharSequence(SAVE_STATE_MESSAGE);
        this.mText = savedInstanceState.getCharSequence(SAVE_STATE_TEXT);
        this.mInputType = savedInstanceState.getInt(SAVE_STATE_INPUT_TYPE, 1);
        this.mImeOptions = savedInstanceState.getInt(SAVE_STATE_IME_OPTIONS, 2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(SAVE_STATE_TITLE, this.mDialogTitle);
        outState.putCharSequence(SAVE_STATE_MESSAGE, this.mDialogMessage);
        outState.putCharSequence(SAVE_STATE_TEXT, this.mText);
        outState.putInt(SAVE_STATE_INPUT_TYPE, this.mInputType);
        outState.putInt(SAVE_STATE_IME_OPTIONS, this.mImeOptions);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TypedValue tv = new TypedValue();
        getActivity().getTheme().resolveAttribute(androidx.preference.R.attr.preferenceTheme, tv, true);
        int theme = tv.resourceId;
        if (theme == 0) {
            theme = R.style.PreferenceThemeOverlayLeanback;
        }
        Context styledContext = new ContextThemeWrapper(getActivity(), theme);
        LayoutInflater styledInflater = inflater.cloneInContext(styledContext);
        View view = styledInflater.inflate(R.layout.leanback_edit_preference_fragment, container, false);
        if (!TextUtils.isEmpty(this.mDialogTitle)) {
            TextView titleView = (TextView) view.findViewById(R.id.decor_title);
            titleView.setText(this.mDialogTitle);
        }
        if (!TextUtils.isEmpty(this.mDialogMessage)) {
            TextView messageView = (TextView) view.findViewById(android.R.id.message);
            messageView.setVisibility(0);
            messageView.setText(this.mDialogMessage);
        }
        EditText editText = (EditText) view.findViewById(android.R.id.edit);
        editText.setInputType(this.mInputType);
        editText.setImeOptions(this.mImeOptions);
        if (!TextUtils.isEmpty(this.mText)) {
            editText.setText(this.mText);
        }
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: androidx.leanback.preference.LeanbackEditTextPreferenceDialogFragmentCompat.1
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if (actionId != 6 && actionId != 2 && actionId != 3 && actionId != 5 && actionId != 4) {
                    return false;
                }
                InputMethodManager imm = (InputMethodManager) LeanbackEditTextPreferenceDialogFragmentCompat.this.getActivity().getSystemService("input_method");
                imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                EditTextPreference preference = (EditTextPreference) LeanbackEditTextPreferenceDialogFragmentCompat.this.getPreference();
                preference.setText(textView.getText().toString());
                LeanbackEditTextPreferenceDialogFragmentCompat.this.getFragmentManager().popBackStack();
                return true;
            }
        });
        return view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        EditText editText = (EditText) getView().findViewById(android.R.id.edit);
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService("input_method");
        editText.requestFocus();
        imm.showSoftInput(editText, 0);
    }
}
