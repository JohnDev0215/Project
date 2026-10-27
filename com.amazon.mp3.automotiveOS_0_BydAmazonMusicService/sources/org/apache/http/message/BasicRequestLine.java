package org.apache.http.message;

import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import java.io.Serializable;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
public class BasicRequestLine implements RequestLine, Cloneable, Serializable {
    private final String method;
    private final ProtocolVersion protoversion;
    private final String uri;

    public BasicRequestLine(String str, String str2, ProtocolVersion protocolVersion) {
        this.method = (String) Args.notNull(str, "Method");
        this.uri = (String) Args.notNull(str2, Contexts.OpenSeeMore.uri);
        this.protoversion = (ProtocolVersion) Args.notNull(protocolVersion, JsonDocumentFields.VERSION);
    }

    @Override // org.apache.http.RequestLine
    public String getMethod() {
        return this.method;
    }

    @Override // org.apache.http.RequestLine
    public ProtocolVersion getProtocolVersion() {
        return this.protoversion;
    }

    @Override // org.apache.http.RequestLine
    public String getUri() {
        return this.uri;
    }

    public String toString() {
        return BasicLineFormatter.INSTANCE.formatRequestLine(null, this).toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
