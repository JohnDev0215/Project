package com.sun.xml.bind.v2.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import javax.xml.transform.stream.StreamSource;

/* JADX INFO: loaded from: classes3.dex */
public final class DataSourceSource extends StreamSource {
    private final String charset;
    private InputStream is;
    private Reader r;
    private final DataSource source;

    public DataSourceSource(DataHandler dataHandler) throws MimeTypeParseException {
        this(dataHandler.getDataSource());
    }

    public DataSourceSource(DataSource dataSource) throws MimeTypeParseException {
        this.source = dataSource;
        String contentType = dataSource.getContentType();
        if (contentType == null) {
            this.charset = null;
        } else {
            this.charset = new MimeType(contentType).getParameter("charset");
        }
    }

    @Override // javax.xml.transform.stream.StreamSource
    public void setReader(Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override // javax.xml.transform.stream.StreamSource
    public void setInputStream(InputStream inputStream) {
        throw new UnsupportedOperationException();
    }

    @Override // javax.xml.transform.stream.StreamSource
    public Reader getReader() {
        try {
            if (this.charset == null) {
                return null;
            }
            if (this.r == null) {
                this.r = new InputStreamReader(this.source.getInputStream(), this.charset);
            }
            return this.r;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // javax.xml.transform.stream.StreamSource
    public InputStream getInputStream() {
        try {
            if (this.charset != null) {
                return null;
            }
            if (this.is == null) {
                this.is = this.source.getInputStream();
            }
            return this.is;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DataSource getDataSource() {
        return this.source;
    }
}
