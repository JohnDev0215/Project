package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.IETFUtils;
import com.amazon.avod.util.LocaleFormattingException;
import com.amazon.avod.util.Preconditions2;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class LocaleConfigurationValue extends CachingConfigurationValue<Locale> {
    public LocaleConfigurationValue(@Nonnull String str, @Nullable Locale locale, @Nonnull ConfigEditor configEditor) {
        super(str, locale, Locale.class, configEditor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nullable
    public Locale parse(@Nonnull String str) {
        try {
            return IETFUtils.fromAmazonLocaleString(str);
        } catch (LocaleFormattingException e) {
            DLog.exceptionf(e, "Locale String is illegal", new Object[0]);
            Preconditions2.failWeakly("Locale String (%s) is illegal", str);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nonnull
    public String asString(@Nonnull Locale locale) {
        return IETFUtils.toAmazonLocaleString(locale);
    }
}
