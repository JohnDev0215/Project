package android.provider.settings.validators;

/* JADX INFO: loaded from: classes.dex */
public final class ComponentNameListValidator extends ListValidator {
    @Override // android.provider.settings.validators.ListValidator
    protected boolean isEntryValid(String str) {
        return str != null;
    }

    public ComponentNameListValidator(String str) {
        super(str);
    }

    @Override // android.provider.settings.validators.ListValidator
    protected boolean isItemValid(String str) {
        return SettingsValidators.COMPONENT_NAME_VALIDATOR.validate(str);
    }
}
