package android.provider.settings.validators;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class AccessibilityShortcutTargetListValidator extends ListValidator {
    public AccessibilityShortcutTargetListValidator() {
        super(":");
    }

    @Override // android.provider.settings.validators.ListValidator
    protected boolean isEntryValid(String str) {
        return !TextUtils.isEmpty(str);
    }

    @Override // android.provider.settings.validators.ListValidator
    protected boolean isItemValid(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return SettingsValidators.COMPONENT_NAME_VALIDATOR.validate(str) || SettingsValidators.PACKAGE_NAME_VALIDATOR.validate(str);
    }
}
