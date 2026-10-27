package com.amazon.avod.json;

import com.amazon.avod.config.ServiceDebugConfig;
import com.amazon.avod.util.json.JacksonJsonStreamParser;
import com.amazon.bolthttp.Request;
import javax.annotation.Nonnull;
import okhttp3.Headers;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ServiceResponseParser<T> extends StreamableJsonParser<T> {
    private final ServiceDebugConfig mDebugConfig;

    @Nonnull
    protected abstract String getSaveFilename(@Nonnull Request<T> request);

    public ServiceResponseParser(@Nonnull JacksonJsonStreamParser<T> jacksonJsonStreamParser) {
        super(jacksonJsonStreamParser);
        this.mDebugConfig = ServiceDebugConfig.getInstance();
    }

    @Override // com.amazon.avod.http.Parser
    public final T parse(@Nonnull Request<T> request, @Nonnull Headers headers, @Nonnull byte[] bArr) throws Exception {
        if (this.mDebugConfig.shouldWriteServerResponseToDisk()) {
            ServiceResponsePersistence.persistToDisk(bArr, getSaveFilename(request));
        }
        return parse(bArr);
    }
}
