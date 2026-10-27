package org.apache.http;

/* JADX INFO: loaded from: classes7.dex */
public interface RequestLine {
    String getMethod();

    ProtocolVersion getProtocolVersion();

    String getUri();
}
