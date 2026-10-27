package android.provider.settings.validators;

/* JADX INFO: loaded from: classes.dex */
final class PackageNameListValidator extends ListValidator {
    @Override // android.provider.settings.validators.ListValidator
    protected boolean isEntryValid(String str) {
        return str != null;
    }

    PackageNameListValidator(String str) {
        super(str);
    }

    @Override // android.provider.settings.validators.ListValidator
    protected boolean isItemValid(String str) {
        return SettingsValidators.PACKAGE_NAME_VALIDATOR.validate(str);
    }
}
