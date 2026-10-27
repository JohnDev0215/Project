package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.IETFUtils;
import com.amazon.avod.util.LocaleFormattingException;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class LocaleListConfigurationValue extends CachingConfigurationValue<ImmutableSet<Locale>> {
    private static final Class<ImmutableSet<Locale>> VALUE_CLAZZ = Set.class;
    private final Joiner mJoiner;
    private final Function<Locale, String> mTransform;

    public LocaleListConfigurationValue(@Nonnull String str, @Nullable ImmutableSet<Locale> immutableSet, @Nonnull ConfigEditor configEditor) {
        super(str, immutableSet, VALUE_CLAZZ, configEditor);
        this.mJoiner = Joiner.on(",");
        this.mTransform = new Function<Locale, String>() { // from class: amazon.android.config.internal.LocaleListConfigurationValue.1
            @Override // com.google.common.base.Function
            @Nullable
            public String apply(@Nullable Locale locale) {
                if (locale == null) {
                    return null;
                }
                return IETFUtils.toAmazonLocaleString(locale);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nullable
    public ImmutableSet<Locale> parse(@Nonnull String str) {
        return splitStringToList(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nonnull
    public String asString(@Nonnull ImmutableSet<Locale> immutableSet) {
        return this.mJoiner.join(Lists.transform(immutableSet.asList(), this.mTransform));
    }

    private static ImmutableSet<Locale> splitStringToList(String str) {
        if (str == null) {
            return null;
        }
        ImmutableSet.Builder builder = ImmutableSet.builder();
        for (String str2 : str.split(",")) {
            String strTrim = str2.trim();
            if (!Strings.isNullOrEmpty(strTrim)) {
                try {
                    builder.add(IETFUtils.fromAmazonLocaleString(strTrim));
                } catch (LocaleFormattingException e) {
                    DLog.exceptionf(e, "Locale String is illegal", new Object[0]);
                    Preconditions2.failWeakly("Locale String (%s) is illegal", strTrim);
                }
            }
        }
        return builder.build();
    }
}
