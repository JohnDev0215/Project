package amazon.android.config;

import com.amazon.avod.util.DLog;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.common.base.MoreObjects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConfigurationValue<T> {
    protected final ConfigEditor mConfigEditor;
    protected final T mDefaultValue;
    protected final String mKey;
    protected final Class<T> mValueClazz;

    public abstract T getValue();

    public abstract T getValue(T t);

    public abstract void updateValue(T t);

    public abstract void updateValueString(String str);

    public ConfigurationValue(@Nonnull String str, @Nullable T t, @Nullable Class<T> cls, @Nonnull ConfigEditor configEditor) {
        this.mKey = str;
        this.mDefaultValue = t;
        this.mValueClazz = cls;
        this.mConfigEditor = configEditor;
    }

    public String getKey() {
        return this.mKey;
    }

    public T getDefaultValue() {
        return this.mDefaultValue;
    }

    public void updateValueObject(Object obj) {
        Class<T> cls = this.mValueClazz;
        if (cls == null) {
            throw new UnsupportedOperationException("Using deprecated constructor for this (" + this.mKey + ") ConfigurationValue; will not be able to update this value from a type-erased copy");
        }
        updateValue(cls.cast(obj));
    }

    public boolean hasConfiguration() {
        return this.mConfigEditor.contains(this.mKey);
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass().getSimpleName()).add("key", this.mKey).add("defaultValue", DLog.maskString(this.mDefaultValue)).add("currentValue", DLog.maskString(getValue())).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.mValueClazz.getSimpleName()).toString();
    }
}
