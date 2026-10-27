package com.android.inputmethodcommon;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
class InputMethodSettingsImpl implements InputMethodSettingsInterface {
    private InputMethodInfo mImi;
    private InputMethodManager mImm;
    private CharSequence mInputMethodSettingsCategoryTitle;
    private int mInputMethodSettingsCategoryTitleRes;
    private Drawable mSubtypeEnablerIcon;
    private int mSubtypeEnablerIconRes;
    private Preference mSubtypeEnablerPreference;
    private CharSequence mSubtypeEnablerTitle;
    private int mSubtypeEnablerTitleRes;

    InputMethodSettingsImpl() {
    }

    public boolean init(final Context context, final PreferenceScreen prefScreen) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        this.mImm = inputMethodManager;
        InputMethodInfo myImi = getMyImi(context, inputMethodManager);
        this.mImi = myImi;
        if (myImi == null || myImi.getSubtypeCount() <= 1) {
            return false;
        }
        Intent intent = new Intent("android.settings.INPUT_METHOD_SUBTYPE_SETTINGS");
        intent.putExtra("input_method_id", this.mImi.getId());
        intent.setFlags(337641472);
        Preference preference = new Preference(context);
        this.mSubtypeEnablerPreference = preference;
        preference.setIntent(intent);
        updateSubtypeEnabler();
        return true;
    }

    private static InputMethodInfo getMyImi(Context context, InputMethodManager imm) {
        List<InputMethodInfo> inputMethodList = imm.getInputMethodList();
        for (int i = 0; i < inputMethodList.size(); i++) {
            InputMethodInfo inputMethodInfo = inputMethodList.get(i);
            if (inputMethodList.get(i).getPackageName().equals(context.getPackageName())) {
                return inputMethodInfo;
            }
        }
        return null;
    }

    private static String getEnabledSubtypesLabel(Context context, InputMethodManager imm, InputMethodInfo imi) {
        if (context == null || imm == null || imi == null) {
            return null;
        }
        List<InputMethodSubtype> enabledInputMethodSubtypeList = imm.getEnabledInputMethodSubtypeList(imi, true);
        StringBuilder sb = new StringBuilder();
        int size = enabledInputMethodSubtypeList.size();
        for (int i = 0; i < size; i++) {
            InputMethodSubtype inputMethodSubtype = enabledInputMethodSubtypeList.get(i);
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(inputMethodSubtype.getDisplayName(context, imi.getPackageName(), imi.getServiceInfo().applicationInfo));
        }
        return sb.toString();
    }

    @Override // com.android.inputmethodcommon.InputMethodSettingsInterface
    public void setInputMethodSettingsCategoryTitle(int resId) {
        this.mInputMethodSettingsCategoryTitleRes = resId;
        updateSubtypeEnabler();
    }

    @Override // com.android.inputmethodcommon.InputMethodSettingsInterface
    public void setInputMethodSettingsCategoryTitle(CharSequence title) {
        this.mInputMethodSettingsCategoryTitleRes = 0;
        this.mInputMethodSettingsCategoryTitle = title;
        updateSubtypeEnabler();
    }

    @Override // com.android.inputmethodcommon.InputMethodSettingsInterface
    public void setSubtypeEnablerTitle(int resId) {
        this.mSubtypeEnablerTitleRes = resId;
        updateSubtypeEnabler();
    }

    @Override // com.android.inputmethodcommon.InputMethodSettingsInterface
    public void setSubtypeEnablerTitle(CharSequence title) {
        this.mSubtypeEnablerTitleRes = 0;
        this.mSubtypeEnablerTitle = title;
        updateSubtypeEnabler();
    }

    @Override // com.android.inputmethodcommon.InputMethodSettingsInterface
    public void setSubtypeEnablerIcon(int resId) {
        this.mSubtypeEnablerIconRes = resId;
        updateSubtypeEnabler();
    }

    @Override // com.android.inputmethodcommon.InputMethodSettingsInterface
    public void setSubtypeEnablerIcon(Drawable drawable) {
        this.mSubtypeEnablerIconRes = 0;
        this.mSubtypeEnablerIcon = drawable;
        updateSubtypeEnabler();
    }

    public void updateSubtypeEnabler() {
        CharSequence string;
        Preference preference = this.mSubtypeEnablerPreference;
        if (preference == null) {
            return;
        }
        Context context = preference.getContext();
        int i = this.mSubtypeEnablerTitleRes;
        if (i != 0) {
            string = context.getString(i);
        } else {
            string = this.mSubtypeEnablerTitle;
        }
        preference.setTitle(string);
        Intent intent = preference.getIntent();
        if (intent != null) {
            intent.putExtra("android.intent.extra.TITLE", string);
        }
        String enabledSubtypesLabel = getEnabledSubtypesLabel(context, this.mImm, this.mImi);
        if (!TextUtils.isEmpty(enabledSubtypesLabel)) {
            preference.setSummary(enabledSubtypesLabel);
        }
        int i2 = this.mSubtypeEnablerIconRes;
        if (i2 != 0) {
            preference.setIcon(i2);
        } else {
            preference.setIcon(this.mSubtypeEnablerIcon);
        }
    }
}
