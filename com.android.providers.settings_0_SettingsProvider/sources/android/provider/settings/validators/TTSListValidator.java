package android.provider.settings.validators;

/* JADX INFO: loaded from: classes.dex */
final class TTSListValidator extends ListValidator {
    TTSListValidator() {
        super(",");
    }

    @Override // android.provider.settings.validators.ListValidator
    protected boolean isEntryValid(String str) {
        return str != null && str.length() > 0;
    }

    @Override // android.provider.settings.validators.ListValidator
    protected boolean isItemValid(String str) {
        String[] strArrSplit = str.split(":");
        return strArrSplit.length == 2 && strArrSplit[0].length() > 0 && SettingsValidators.ANY_STRING_VALIDATOR.validate(strArrSplit[0]) && SettingsValidators.LOCALE_VALIDATOR.validate(strArrSplit[1].replace('-', '_'));
    }
}
