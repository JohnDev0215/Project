package com.spotify.cosmos.cosmos;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class Request {
    public static final String DELETE = "DELETE";
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String SUB = "SUB";
    private String action;
    private byte[] body;
    private Map<String, String> headers;
    private String uri;
    public static final Companion Companion = new Companion(null);
    public static final byte[] EMPTY_BODY = new byte[0];

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Request() {
        setAction(null);
        setUri(null);
        setHeaders(null);
        setBody(null);
    }

    public String getAction() {
        return this.action;
    }

    public byte[] getBody() {
        return this.body;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public String getUri() {
        return this.uri;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setBody(byte[] bArr) {
        this.body = bArr;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    public void setUri(String str) {
        this.uri = str;
    }

    public String toString() {
        String str = String.format("Request{mAction='%s', mUri='%s'}", Arrays.copyOf(new Object[]{getAction(), getUri()}, 2));
        v03.i(str, "format(format, *args)");
        return str;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Request(String str, String str2) {
        this(str, str2, new LinkedHashMap(), EMPTY_BODY);
        v03.j(str, "action");
        v03.j(str2, "uri");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Request(String str, String str2, byte[] bArr) {
        this(str, str2, new LinkedHashMap(), bArr);
        v03.j(str, "action");
        v03.j(str2, "uri");
        v03.j(bArr, "body");
    }

    public Request(String str, String str2, Map<String, String> map, byte[] bArr) {
        if (str2 != null) {
            if (str2.length() != 0) {
                setAction(str);
                setUri(str2);
                setHeaders(map);
                setBody(bArr);
                return;
            }
            throw new IllegalArgumentException("URI cannot be empty");
        }
        throw new IllegalArgumentException("URI cannot be null");
    }
}
