package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import amazon.android.config.ConfigurationValue;
import com.google.common.base.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
public abstract class CachingConfigurationValue<T> extends ConfigurationValue<T> {

    @GuardedBy("mMutex")
    private T mComputedValue;

    @GuardedBy("mMutex")
    private String mLastValue;
    private final Object mMutex;

    @Nonnull
    protected abstract String asString(@Nonnull T t);

    @Nullable
    protected abstract T parse(@Nonnull String str);

    public CachingConfigurationValue(@Nonnull String str, @Nullable T t, @Nullable Class<T> cls, @Nonnull ConfigEditor configEditor) {
        super(str, t, cls, configEditor);
        this.mMutex = new Object();
    }

    @Override // amazon.android.config.ConfigurationValue
    public T getValue() {
        return getValue(this.mDefaultValue);
    }

    @Override // amazon.android.config.ConfigurationValue
    public T getValue(T t) {
        T t2 = null;
        String stringConfig = this.mConfigEditor.getStringConfig(this.mKey, null);
        synchronized (this.mMutex) {
            if (!Objects.equal(stringConfig, this.mLastValue)) {
                this.mLastValue = stringConfig;
                if (stringConfig != null) {
                    t2 = parse(stringConfig);
                }
                this.mComputedValue = t2;
            }
            T t3 = this.mComputedValue;
            if (t3 != null) {
                t = t3;
            }
        }
        return t;
    }

    @Override // amazon.android.config.ConfigurationValue
    public void updateValue(T t) {
        updateValueString(t == null ? null : asString(t));
    }

    @Override // amazon.android.config.ConfigurationValue
    public void updateValueString(String str) {
        this.mConfigEditor.setStringConfig(this.mKey, str);
    }
}
