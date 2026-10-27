package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import com.google.common.base.Joiner;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class StringSetConfigurationValue extends CachingConfigurationValue<Set<String>> {
    private static final Class<Set<String>> VALUE_CLAZZ = Set.class;
    private final Joiner mJoiner;
    private final String mSplitter;

    public StringSetConfigurationValue(String str, String str2, ConfigEditor configEditor, String str3) {
        super(str, splitStringToSet(str2, str3), VALUE_CLAZZ, configEditor);
        this.mSplitter = str3;
        this.mJoiner = Joiner.on(str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nullable
    public Set<String> parse(@Nonnull String str) {
        return splitStringToSet(str, this.mSplitter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nonnull
    public String asString(@Nonnull Set<String> set) {
        return this.mJoiner.join(set);
    }

    protected static final Set<String> splitStringToSet(String str, String str2) {
        if (str == null) {
            return null;
        }
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        for (String str3 : str.split(str2)) {
            String strTrim = str3.trim();
            if (!"".equals(strTrim)) {
                treeSet.add(strTrim);
            }
        }
        return treeSet;
    }
}
