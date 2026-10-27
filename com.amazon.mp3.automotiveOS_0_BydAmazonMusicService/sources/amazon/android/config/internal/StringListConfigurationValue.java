package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class StringListConfigurationValue extends CachingConfigurationValue<List<String>> {
    private static final Class<List<String>> VALUE_CLAZZ = List.class;
    private final Joiner mJoiner;
    private final String mSplitter;

    public StringListConfigurationValue(String str, String str2, ConfigEditor configEditor, String str3) {
        super(str, splitStringToList(str2, str3), VALUE_CLAZZ, configEditor);
        this.mSplitter = str3;
        this.mJoiner = Joiner.on(str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nullable
    public List<String> parse(@Nonnull String str) {
        return splitStringToList(str, this.mSplitter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nonnull
    public String asString(@Nonnull List<String> list) {
        return this.mJoiner.join(list);
    }

    protected static final List<String> splitStringToList(String str, String str2) {
        if (str == null) {
            return null;
        }
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        for (String str3 : str.split(str2)) {
            String strTrim = str3.trim();
            if (!"".equals(strTrim)) {
                linkedListNewLinkedList.add(strTrim);
            }
        }
        return linkedListNewLinkedList;
    }
}
