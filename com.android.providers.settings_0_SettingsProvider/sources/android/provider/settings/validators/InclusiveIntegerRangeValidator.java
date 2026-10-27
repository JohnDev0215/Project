package android.provider.settings.validators;

/* JADX INFO: loaded from: classes.dex */
final class InclusiveIntegerRangeValidator implements Validator {
    private final int mMax;
    private final int mMin;

    InclusiveIntegerRangeValidator(int i, int i2) {
        this.mMin = i;
        this.mMax = i2;
    }

    @Override // android.provider.settings.validators.Validator
    public boolean validate(String str) {
        try {
            int i = Integer.parseInt(str);
            if (i >= this.mMin) {
                return i <= this.mMax;
            }
            return false;
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
