package com.amazon.bolthttp.internal;

import com.amazon.bolthttp.Response;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface ResponseHandler<T> {
    void handleResponse(@Nonnull Response<T> response);
}
