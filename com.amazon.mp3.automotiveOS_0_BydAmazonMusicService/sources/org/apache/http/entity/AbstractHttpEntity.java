package org.apache.http.entity;

import com.amazonaws.http.HttpHeader;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

/* JADX INFO: loaded from: classes7.dex */
public abstract class AbstractHttpEntity implements HttpEntity {
    protected boolean chunked;
    protected Header contentEncoding;
    protected Header contentType;

    @Override // org.apache.http.HttpEntity
    @Deprecated
    public void consumeContent() throws IOException {
    }

    protected AbstractHttpEntity() {
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentType() {
        return this.contentType;
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentEncoding() {
        return this.contentEncoding;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isChunked() {
        return this.chunked;
    }

    public void setContentType(Header header) {
        this.contentType = header;
    }

    public void setContentType(String str) {
        setContentType(str != null ? new BasicHeader(HttpHeader.CONTENT_TYPE, str) : null);
    }

    public void setContentEncoding(Header header) {
        this.contentEncoding = header;
    }

    public void setContentEncoding(String str) {
        setContentEncoding(str != null ? new BasicHeader("Content-Encoding", str) : null);
    }

    public void setChunked(boolean z) {
        this.chunked = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (this.contentType != null) {
            sb.append("Content-Type: ");
            sb.append(this.contentType.getValue());
            sb.append(',');
        }
        if (this.contentEncoding != null) {
            sb.append("Content-Encoding: ");
            sb.append(this.contentEncoding.getValue());
            sb.append(',');
        }
        long contentLength = getContentLength();
        if (contentLength >= 0) {
            sb.append("Content-Length: ");
            sb.append(contentLength);
            sb.append(',');
        }
        sb.append("Chunked: ");
        sb.append(this.chunked);
        sb.append(']');
        return sb.toString();
    }
}
