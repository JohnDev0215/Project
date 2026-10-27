package org.chromium.components.embedder_support.util;

import java.io.InputStream;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class WebResourceResponseInfo {
    @CalledByNative
    public String getCharset() {
        return null;
    }

    @CalledByNative
    public InputStream getData() {
        return null;
    }

    @CalledByNative
    public String getMimeType() {
        return null;
    }

    @CalledByNative
    public String getReasonPhrase() {
        return null;
    }

    @CalledByNative
    public final String[] getResponseHeaderNames() {
        return null;
    }

    @CalledByNative
    public final String[] getResponseHeaderValues() {
        return null;
    }

    @CalledByNative
    public int getStatusCode() {
        return 0;
    }
}
