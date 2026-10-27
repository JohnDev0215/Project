package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.json.JacksonJsonParser;
import com.amazon.avod.util.json.JsonContractException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class JacksonJsonConfigurationValue<T> extends CachingConfigurationValue<T> {

    @Nonnull
    private final JacksonJsonParser<T> mJacksonJsonParser;

    private static class FactoryHolder {
        static final JsonFactory JSON_FACTORY = new JsonFactory();

        private FactoryHolder() {
        }
    }

    @Deprecated
    public JacksonJsonConfigurationValue(@Nonnull String str, @Nullable T t, @Nonnull ConfigEditor configEditor, @Nonnull JacksonJsonParser<T> jacksonJsonParser) {
        this(str, t, null, configEditor, jacksonJsonParser);
    }

    public JacksonJsonConfigurationValue(@Nonnull String str, @Nullable T t, @Nullable Class<T> cls, @Nonnull ConfigEditor configEditor, @Nonnull JacksonJsonParser<T> jacksonJsonParser) {
        super(str, t, cls, configEditor);
        this.mJacksonJsonParser = (JacksonJsonParser) Preconditions.checkNotNull(jacksonJsonParser, "jacksonJsonParser");
    }

    @Override // amazon.android.config.internal.CachingConfigurationValue, amazon.android.config.ConfigurationValue
    public void updateValueString(String str) {
        throw new UnsupportedOperationException("Illegal to update a json configuration value");
    }

    @Override // amazon.android.config.internal.CachingConfigurationValue, amazon.android.config.ConfigurationValue
    public void updateValue(T t) {
        throw new UnsupportedOperationException("Illegal to update a json configuration value");
    }

    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nullable
    protected T parse(@Nonnull String str) {
        try {
            JsonParser jsonParserCreateJsonParser = FactoryHolder.JSON_FACTORY.createJsonParser(str);
            jsonParserCreateJsonParser.nextToken();
            return this.mJacksonJsonParser.parse(jsonParserCreateJsonParser);
        } catch (JsonContractException e) {
            return handleParsingExceptionAndReturnNull(e, str);
        } catch (JsonParseException e2) {
            return handleParsingExceptionAndReturnNull(e2, str);
        } catch (IOException e3) {
            return handleParsingExceptionAndReturnNull(e3, str);
        }
    }

    @Nullable
    private T handleParsingExceptionAndReturnNull(@Nonnull Exception exc, @Nonnull String str) {
        DLog.exceptionf(exc, "Exception parsing JSON (key = %s, source = %s)", this.mKey, str);
        return null;
    }

    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nonnull
    protected String asString(@Nonnull T t) {
        throw new UnsupportedOperationException();
    }
}
