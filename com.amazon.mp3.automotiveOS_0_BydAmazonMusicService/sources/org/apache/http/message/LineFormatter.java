package org.apache.http.message;

import org.apache.http.Header;
import org.apache.http.RequestLine;
import org.apache.http.util.CharArrayBuffer;

/* JADX INFO: loaded from: classes7.dex */
public interface LineFormatter {
    CharArrayBuffer formatHeader(CharArrayBuffer charArrayBuffer, Header header);

    CharArrayBuffer formatRequestLine(CharArrayBuffer charArrayBuffer, RequestLine requestLine);
}
