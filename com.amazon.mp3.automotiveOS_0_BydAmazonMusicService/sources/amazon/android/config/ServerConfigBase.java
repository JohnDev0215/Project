package amazon.android.config;

import com.amazon.avod.util.json.JacksonJsonParser;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class ServerConfigBase extends ConfigBase {
    protected ServerConfigBase() {
        super(null);
    }

    protected final ConfigurationValue<String> newStringConfigValue(String str, String str2) {
        return newStringConfigValue(str, str2, ConfigType.SERVER);
    }

    protected final ConfigurationValue<Integer> newIntConfigValue(String str, int i) {
        return newIntConfigValue(str, i, ConfigType.SERVER);
    }

    protected final ConfigurationValue<Long> newLongConfigValue(String str, long j) {
        return newLongConfigValue(str, j, ConfigType.SERVER);
    }

    protected final ConfigurationValue<Float> newFloatConfigValue(String str, float f) {
        return newFloatConfigValue(str, f, ConfigType.SERVER);
    }

    protected final ConfigurationValue<Boolean> newBooleanConfigValue(String str, boolean z) {
        return newBooleanConfigValue(str, z, ConfigType.SERVER);
    }

    protected final ConfigurationValue<Set<String>> newStringSetConfigValue(String str, String str2, String str3) {
        return newStringSetConfigValue(str, str2, str3, ConfigType.SERVER);
    }

    protected final ConfigurationValue<List<String>> newStringListConfigValue(String str, String str2, String str3) {
        return newStringListConfigValue(str, str2, str3, ConfigType.SERVER);
    }

    protected final <T extends Enum<T>> ConfigurationValue<T> newEnumConfigValue(String str, T t, Class<T> cls) {
        return newEnumConfigValue(str, t, cls, ConfigType.SERVER);
    }

    @Deprecated
    protected final ConfigurationValue<JSONObject> newJSONObjectConfigValue(String str, JSONObject jSONObject) {
        return newJSONObjectConfigValue(str, jSONObject, ConfigType.SERVER);
    }

    protected final <T> ConfigurationValue<T> newJacksonJsonConfigValue(String str, @Nullable T t, @Nonnull JacksonJsonParser<T> jacksonJsonParser) {
        return newJacksonJsonConfigValue(str, t, ConfigType.SERVER, jacksonJsonParser);
    }
}
