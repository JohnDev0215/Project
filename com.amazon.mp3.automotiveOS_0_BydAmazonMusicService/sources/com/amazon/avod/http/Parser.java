package com.amazon.avod.http;

import com.amazon.bolthttp.Request;
import javax.annotation.Nonnull;
import okhttp3.Headers;

/* JADX INFO: loaded from: classes2.dex */
public interface Parser<T> {
    T parse(@Nonnull Request<T> request, @Nonnull Headers headers, @Nonnull byte[] bArr) throws Exception;
}
