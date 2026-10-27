package com.amazon.bolthttp;

import com.google.common.base.Preconditions;
import java.net.URL;
import java.net.UnknownHostException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AivUnknownHostException extends UnknownHostException {
    private final URL mUrl;

    public AivUnknownHostException(@Nonnull UnknownHostException unknownHostException, @Nonnull URL url) {
        super(((UnknownHostException) Preconditions.checkNotNull(unknownHostException, "exception")).getMessage());
        this.mUrl = (URL) Preconditions.checkNotNull(url, "host");
    }
}
