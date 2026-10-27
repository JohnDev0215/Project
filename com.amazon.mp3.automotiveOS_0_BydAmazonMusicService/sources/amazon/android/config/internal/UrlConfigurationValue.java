package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import com.amazon.avod.util.DLog;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class UrlConfigurationValue extends CachingConfigurationValue<URL> {
    public UrlConfigurationValue(@Nonnull String str, @Nullable URL url, @Nonnull ConfigEditor configEditor) {
        super(str, url, URL.class, configEditor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nullable
    public URL parse(@Nonnull String str) {
        try {
            return new URL(str);
        } catch (RuntimeException | MalformedURLException unused) {
            DLog.warnf("Couldn't resolve current value for [%s]; falling back to default", this);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nonnull
    public String asString(@Nonnull URL url) {
        return url.toString();
    }
}
