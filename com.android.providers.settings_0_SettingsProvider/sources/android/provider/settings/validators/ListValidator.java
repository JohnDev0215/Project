package android.provider.settings.validators;

/* JADX INFO: loaded from: classes.dex */
abstract class ListValidator implements Validator {
    private String mListSplitRegex;

    protected abstract boolean isEntryValid(String str);

    protected abstract boolean isItemValid(String str);

    ListValidator(String str) {
        this.mListSplitRegex = str;
    }

    @Override // android.provider.settings.validators.Validator
    public boolean validate(String str) {
        if (!isEntryValid(str)) {
            return false;
        }
        for (String str2 : str.split(this.mListSplitRegex)) {
            if (!isItemValid(str2)) {
                return false;
            }
        }
        return true;
    }
}
