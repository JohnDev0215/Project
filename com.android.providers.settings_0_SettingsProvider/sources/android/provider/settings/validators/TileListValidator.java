package android.provider.settings.validators;

/* JADX INFO: loaded from: classes.dex */
final class TileListValidator extends ListValidator {
    @Override // android.provider.settings.validators.ListValidator
    protected boolean isEntryValid(String str) {
        return str != null;
    }

    TileListValidator() {
        super(",");
    }

    @Override // android.provider.settings.validators.ListValidator
    protected boolean isItemValid(String str) {
        return str.length() > 0 && SettingsValidators.ANY_STRING_VALIDATOR.validate(str);
    }
}
