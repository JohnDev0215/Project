package org.apache.http;

import org.apache.http.util.CharArrayBuffer;

/* JADX INFO: loaded from: classes7.dex */
public interface FormattedHeader extends Header {
    CharArrayBuffer getBuffer();

    int getValuePos();
}
