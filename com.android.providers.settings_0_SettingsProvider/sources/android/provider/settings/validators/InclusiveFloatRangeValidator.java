package android.provider.settings.validators;

/* JADX INFO: loaded from: classes.dex */
final class InclusiveFloatRangeValidator implements Validator {
    private final float mMax;
    private final float mMin;

    InclusiveFloatRangeValidator(float f, float f2) {
        this.mMin = f;
        this.mMax = f2;
    }

    @Override // android.provider.settings.validators.Validator
    public boolean validate(String str) {
        try {
            float f = Float.parseFloat(str);
            if (f >= this.mMin) {
                return f <= this.mMax;
            }
            return false;
        } catch (NullPointerException | NumberFormatException unused) {
            return false;
        }
    }
}
