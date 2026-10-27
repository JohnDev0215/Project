package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import amazon.android.config.ConfigurationValue;
import java.lang.Enum;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class EnumConfigurationValue<T extends Enum<T>> extends ConfigurationValue<T> {
    private final Class<T> mValueClazz;

    public EnumConfigurationValue(String str, T t, Class<T> cls, ConfigEditor configEditor) {
        super(str, t, cls, configEditor);
        this.mValueClazz = cls;
    }

    @Override // amazon.android.config.ConfigurationValue
    public T getValue() {
        ConfigEditor configEditor = this.mConfigEditor;
        String str = this.mKey;
        T t = this.mDefaultValue;
        String stringConfig = configEditor.getStringConfig(str, t != null ? t.name() : null);
        if (stringConfig == null) {
            return null;
        }
        try {
            return (T) Enum.valueOf(this.mValueClazz, stringConfig.toUpperCase(Locale.US));
        } catch (IllegalArgumentException unused) {
            return this.mDefaultValue;
        }
    }

    @Override // amazon.android.config.ConfigurationValue
    public T getValue(T t) {
        try {
            return (T) Enum.valueOf(this.mValueClazz, this.mConfigEditor.getStringConfig(this.mKey, t.name()).toUpperCase(Locale.US));
        } catch (IllegalArgumentException unused) {
            return t;
        }
    }

    @Override // amazon.android.config.ConfigurationValue
    public void updateValueString(String str) {
        this.mConfigEditor.setStringConfig(this.mKey, str);
    }

    @Override // amazon.android.config.ConfigurationValue
    public void updateValue(T t) {
        this.mConfigEditor.setStringConfig(this.mKey, t == null ? null : t.name());
    }
}
