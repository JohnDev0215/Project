package com.amazon.avod.json;

import com.amazon.avod.http.Parser;
import com.amazon.avod.util.json.JacksonJsonStreamParser;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class StreamableJsonParser<T> implements Parser<T> {
    protected final JsonFactory mJsonFactory = JacksonJsonFactoryCache.JSON_FACTORY;
    private final JacksonJsonStreamParser<T> mParser;

    public StreamableJsonParser(@Nonnull JacksonJsonStreamParser<T> jacksonJsonStreamParser) {
        this.mParser = jacksonJsonStreamParser;
    }

    public final T parse(@Nonnull byte[] bArr) throws Exception {
        return parse(this.mJsonFactory.createParser(bArr));
    }

    private T parse(@Nonnull JsonParser jsonParser) throws Exception {
        try {
            jsonParser.nextToken();
            return this.mParser.parse(jsonParser);
        } finally {
            jsonParser.close();
        }
    }
}
