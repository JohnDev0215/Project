package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class StringMapConfigurationValue extends CachingConfigurationValue<Map<String, String>> {
    private static final Class<Map<String, String>> VALUE_CLAZZ = Map.class;
    private final String mElementSplitter;
    private final String mKeyValueSplitter;
    private final Joiner.MapJoiner mMapJoiner;

    public StringMapConfigurationValue(String str, String str2, ConfigEditor configEditor, String str3, String str4) {
        super(str, splitStringToMap(str2, str3, str4), VALUE_CLAZZ, configEditor);
        this.mElementSplitter = str3;
        this.mKeyValueSplitter = str4;
        this.mMapJoiner = Joiner.on(str3).withKeyValueSeparator(str4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nullable
    public Map<String, String> parse(@Nonnull String str) {
        return splitStringToMap(str, this.mElementSplitter, this.mKeyValueSplitter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nonnull
    public String asString(@Nonnull Map<String, String> map) {
        return this.mMapJoiner.join(map);
    }

    protected static final Map<String, String> splitStringToMap(String str, String str2, String str3) {
        if (Strings.isNullOrEmpty(str)) {
            return ImmutableMap.of();
        }
        return ImmutableMap.copyOf((Map) Splitter.on(str2).withKeyValueSeparator(str3).split(str));
    }
}
