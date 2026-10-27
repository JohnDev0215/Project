package android.provider.settings.validators;

import com.android.internal.util.ArrayUtils;

/* JADX INFO: loaded from: classes.dex */
public final class DiscreteValueValidator implements Validator {
    private final String[] mValues;

    public DiscreteValueValidator(String[] strArr) {
        this.mValues = strArr;
    }

    @Override // android.provider.settings.validators.Validator
    public boolean validate(String str) {
        return ArrayUtils.contains(this.mValues, str);
    }
}
