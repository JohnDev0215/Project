package amazon.android.config;

import amazon.android.config.internal.BooleanConfigurationValue;
import amazon.android.config.internal.EnumConfigurationValue;
import amazon.android.config.internal.FloatConfigurationValue;
import amazon.android.config.internal.IntegerConfigurationValue;
import amazon.android.config.internal.JSONObjectConfigurationValue;
import amazon.android.config.internal.JacksonJsonConfigurationValue;
import amazon.android.config.internal.LocaleConfigurationValue;
import amazon.android.config.internal.LocaleListConfigurationValue;
import amazon.android.config.internal.LongConfigurationValue;
import amazon.android.config.internal.StringConfigurationValue;
import amazon.android.config.internal.StringListConfigurationValue;
import amazon.android.config.internal.StringMapConfigurationValue;
import amazon.android.config.internal.StringSetConfigurationValue;
import amazon.android.config.internal.UrlConfigurationValue;
import com.amazon.avod.util.json.JacksonJsonParser;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConfigBase {
    private final ConfigRegistry mConfigRegistry;
    private final Map<String, ConfigurationValue<?>> mConfigurationValues;
    private final String mNamespace;

    protected ConfigBase(@Nullable String str) {
        this(str, ConfigRegistry.getInstance());
    }

    ConfigBase(String str, ConfigRegistry configRegistry) {
        this.mNamespace = str == null ? "" : str;
        this.mConfigurationValues = Maps.newHashMap();
        this.mConfigRegistry = configRegistry;
    }

    @Nonnull
    protected ConfigEditor getConfigEditor(ConfigType configType) {
        return this.mConfigRegistry.getConfigEditor(configType);
    }

    protected final ConfigurationValue<String> newStringConfigValue(String str, String str2, ConfigType configType) {
        String configKey = getConfigKey(str, configType);
        StringConfigurationValue stringConfigurationValue = new StringConfigurationValue(configKey, str2, this.mConfigRegistry.getConfigEditor(configType));
        this.mConfigurationValues.put(configKey, stringConfigurationValue);
        return stringConfigurationValue;
    }

    protected final ConfigurationValue<Integer> newIntConfigValue(String str, int i, ConfigType configType) {
        String configKey = getConfigKey(str, configType);
        IntegerConfigurationValue integerConfigurationValue = new IntegerConfigurationValue(configKey, Integer.valueOf(i), this.mConfigRegistry.getConfigEditor(configType));
        this.mConfigurationValues.put(configKey, integerConfigurationValue);
        return integerConfigurationValue;
    }

    protected final ConfigurationValue<Long> newLongConfigValue(String str, long j, ConfigType configType) {
        String configKey = getConfigKey(str, configType);
        LongConfigurationValue longConfigurationValue = new LongConfigurationValue(configKey, Long.valueOf(j), this.mConfigRegistry.getConfigEditor(configType));
        this.mConfigurationValues.put(configKey, longConfigurationValue);
        return longConfigurationValue;
    }

    protected final ConfigurationValue<Float> newFloatConfigValue(String str, float f, ConfigType configType) {
        String configKey = getConfigKey(str, configType);
        FloatConfigurationValue floatConfigurationValue = new FloatConfigurationValue(configKey, Float.valueOf(f), this.mConfigRegistry.getConfigEditor(configType));
        this.mConfigurationValues.put(configKey, floatConfigurationValue);
        return floatConfigurationValue;
    }

    protected final ConfigurationValue<Boolean> newBooleanConfigValue(String str, boolean z, ConfigType configType) {
        String configKey = getConfigKey(str, configType);
        BooleanConfigurationValue booleanConfigurationValue = new BooleanConfigurationValue(configKey, Boolean.valueOf(z), this.mConfigRegistry.getConfigEditor(configType));
        this.mConfigurationValues.put(configKey, booleanConfigurationValue);
        return booleanConfigurationValue;
    }

    protected final ConfigurationValue<Set<String>> newStringSetConfigValue(String str, String str2, String str3, ConfigType configType) {
        String configKey = getConfigKey(str, configType);
        StringSetConfigurationValue stringSetConfigurationValue = new StringSetConfigurationValue(configKey, str2, this.mConfigRegistry.getConfigEditor(configType), str3);
        this.mConfigurationValues.put(configKey, stringSetConfigurationValue);
        return stringSetConfigurationValue;
    }

    protected final ConfigurationValue<Map<String, String>> newStringMapConfigValue(String str, String str2, String str3, String str4, ConfigType configType) {
        String configKey = getConfigKey(str, configType);
        StringMapConfigurationValue stringMapConfigurationValue = new StringMapConfigurationValue(configKey, str2, this.mConfigRegistry.getConfigEditor(configType), str3, str4);
        this.mConfigurationValues.put(configKey, stringMapConfigurationValue);
        return stringMapConfigurationValue;
    }

    protected final ConfigurationValue<List<String>> newStringListConfigValue(String str, String str2, String str3, ConfigType configType) {
        String configKey = getConfigKey(str, configType);
        StringListConfigurationValue stringListConfigurationValue = new StringListConfigurationValue(configKey, str2, this.mConfigRegistry.getConfigEditor(configType), str3);
        this.mConfigurationValues.put(configKey, stringListConfigurationValue);
        return stringListConfigurationValue;
    }

    protected final <T extends Enum<T>> ConfigurationValue<T> newEnumConfigValue(String str, T t, Class<T> cls, ConfigType configType) {
        String configKey = getConfigKey(str, configType);
        EnumConfigurationValue enumConfigurationValue = new EnumConfigurationValue(configKey, t, cls, this.mConfigRegistry.getConfigEditor(configType));
        this.mConfigurationValues.put(configKey, enumConfigurationValue);
        return enumConfigurationValue;
    }

    @Deprecated
    protected final ConfigurationValue<JSONObject> newJSONObjectConfigValue(String str, JSONObject jSONObject, ConfigType configType) {
        String configKey = getConfigKey(str, configType);
        JSONObjectConfigurationValue jSONObjectConfigurationValue = new JSONObjectConfigurationValue(configKey, jSONObject, this.mConfigRegistry.getConfigEditor(configType));
        this.mConfigurationValues.put(configKey, jSONObjectConfigurationValue);
        return jSONObjectConfigurationValue;
    }

    protected final <T> ConfigurationValue<T> newJacksonJsonConfigValue(@Nonnull String str, @Nullable T t, @Nonnull ConfigType configType, @Nonnull JacksonJsonParser<T> jacksonJsonParser) {
        Preconditions.checkNotNull(str, "key");
        Preconditions.checkNotNull(configType, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        Preconditions.checkNotNull(jacksonJsonParser, "jacksonJsonParser");
        String configKey = getConfigKey(str, configType);
        JacksonJsonConfigurationValue jacksonJsonConfigurationValue = new JacksonJsonConfigurationValue(configKey, t, this.mConfigRegistry.getConfigEditor(configType), jacksonJsonParser);
        this.mConfigurationValues.put(configKey, jacksonJsonConfigurationValue);
        return jacksonJsonConfigurationValue;
    }

    @Nonnull
    protected final ConfigurationValue<URL> newUrlConfigValue(@Nonnull String str, @Nullable URL url, @Nonnull ConfigType configType) {
        String configKey = getConfigKey(str, configType);
        UrlConfigurationValue urlConfigurationValue = new UrlConfigurationValue(configKey, url, this.mConfigRegistry.getConfigEditor(configType));
        this.mConfigurationValues.put(configKey, urlConfigurationValue);
        return urlConfigurationValue;
    }

    protected final ConfigurationValue<Locale> newLocaleConfigValue(@Nonnull String str, @Nullable Locale locale, @Nonnull ConfigType configType) {
        String configKey = getConfigKey(str, configType);
        LocaleConfigurationValue localeConfigurationValue = new LocaleConfigurationValue(configKey, locale, this.mConfigRegistry.getConfigEditor(configType));
        this.mConfigurationValues.put(configKey, localeConfigurationValue);
        return localeConfigurationValue;
    }

    protected final ConfigurationValue<ImmutableSet<Locale>> newLocaleSetConfigValue(@Nonnull String str, @Nullable ImmutableSet<Locale> immutableSet, @Nonnull ConfigType configType) {
        String configKey = getConfigKey(str, configType);
        LocaleListConfigurationValue localeListConfigurationValue = new LocaleListConfigurationValue(configKey, immutableSet, this.mConfigRegistry.getConfigEditor(configType));
        this.mConfigurationValues.put(configKey, localeListConfigurationValue);
        return localeListConfigurationValue;
    }

    protected final void removeInternalConfigValue(String str) {
        ConfigType configType = ConfigType.INTERNAL;
        String configKey = getConfigKey(str, configType);
        this.mConfigurationValues.remove(configKey);
        this.mConfigRegistry.getConfigEditor(configType).remove(configKey);
    }

    final String getConfigKey(String str, ConfigType configType) {
        return configType == ConfigType.INTERNAL ? String.format(Locale.US, "%s:%s", this.mNamespace, str) : str;
    }

    public final Object getValue(String str) {
        ConfigurationValue configurationValue = getConfigurationValue(str);
        if (configurationValue != null) {
            return configurationValue.getValue();
        }
        return null;
    }

    protected final ConfigurationValue getConfigurationValue(String str) {
        return this.mConfigurationValues.get(str);
    }

    protected void updateEntry(String str, String str2) {
        ConfigurationValue<?> configurationValue = this.mConfigurationValues.get(str);
        if (configurationValue != null) {
            configurationValue.updateValueString(str2);
        }
    }

    public void restore(ImmutableMap<String, Object> immutableMap) {
        for (ConfigurationValue<?> configurationValue : this.mConfigurationValues.values()) {
            if (immutableMap.containsKey(configurationValue.getKey())) {
                configurationValue.updateValueObject(immutableMap.get(configurationValue.getKey()));
            }
        }
        for (ConfigType configType : ConfigType.values()) {
            ConfigRegistry.getInstance().getConfigEditor(configType).getSharedPreferences().edit().commit();
        }
    }

    @Nonnull
    public ImmutableMap<String, Object> copy() {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (Map.Entry<String, ConfigurationValue<?>> entry : this.mConfigurationValues.entrySet()) {
            if (entry.getValue().getValue() != null) {
                builder.put(entry.getKey(), entry.getValue().getValue());
            }
        }
        return builder.build();
    }

    @Nonnull
    protected <T extends Enum<T>> Set<T> translateStringSetToEnumSet(@Nonnull Class<T> cls, @Nonnull ConfigurationValue<Set<String>> configurationValue) {
        Set set = (Set) ((ConfigurationValue) Preconditions.checkNotNull(configurationValue, "set")).getValue();
        ImmutableSet.Builder builder = ImmutableSet.builder();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Enum enumValueOf = Enum.valueOf(cls, (String) it.next());
            if (enumValueOf != null) {
                builder.add(enumValueOf);
            }
        }
        return builder.build();
    }
}
