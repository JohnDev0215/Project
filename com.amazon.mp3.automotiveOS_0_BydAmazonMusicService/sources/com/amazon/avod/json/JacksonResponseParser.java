package com.amazon.avod.json;

import com.amazon.avod.http.Parser;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.Request;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import okhttp3.Headers;

/* JADX INFO: loaded from: classes2.dex */
public class JacksonResponseParser<T> implements Parser<T> {
    private final ObjectReader mObjectReader;
    private final Class<T> mTClass;

    public JacksonResponseParser(@Nonnull Class<T> cls) {
        Class<T> cls2 = (Class) Preconditions.checkNotNull(cls);
        this.mTClass = cls2;
        this.mObjectReader = JacksonCache.OBJECT_MAPPER.readerFor((Class<?>) cls2);
    }

    @Override // com.amazon.avod.http.Parser
    public T parse(@Nonnull Request<T> request, @Nonnull Headers headers, @Nonnull byte[] bArr) throws Exception {
        DLog.devf("parsed response, uri: %s, body: %s ", request.getUrl().getPath(), new String(bArr, Charsets.UTF_8));
        return (T) this.mObjectReader.readValue((byte[]) Preconditions.checkNotNull(bArr, "body"));
    }
}
