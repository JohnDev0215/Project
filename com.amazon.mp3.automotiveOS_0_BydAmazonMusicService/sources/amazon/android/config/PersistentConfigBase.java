package amazon.android.config;

/* JADX INFO: loaded from: classes.dex */
public abstract class PersistentConfigBase extends ConfigBase {
    protected PersistentConfigBase() {
        super(null);
    }

    protected final ConfigurationValue<Boolean> newBooleanConfigValue(String str, boolean z) {
        return newBooleanConfigValue(str, z, ConfigType.PERSISTENT);
    }

    protected final <T extends Enum<T>> ConfigurationValue<T> newEnumConfigValue(String str, T t, Class<T> cls) {
        return newEnumConfigValue(str, t, cls, ConfigType.PERSISTENT);
    }
}
