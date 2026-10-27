package com.spotify.cosmos.cosmonautatoms;

import com.spotify.cosmos.cosmos.Request;
import java.lang.reflect.Method;
import java.util.Map;
import kotlin.text.b;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class RequestAtom {
    private final String action;
    private final BodyAtom bodyAtom;
    private final HeadersAtom headersAtom;
    private final PathAtom pathAtom;
    private final QueryAtom queryAtom;

    public RequestAtom(Method method, RequestTransformers requestTransformers) {
        v03.j(method, "method");
        v03.j(requestTransformers, "requestTransformers");
        this.action = ReflectionUtil.Companion.findAction(method);
        this.pathAtom = new PathAtom(method);
        this.queryAtom = new QueryAtom(method);
        this.headersAtom = new HeadersAtom(method);
        this.bodyAtom = new BodyAtom(method, requestTransformers);
    }

    public final Request call(Object[] objArr) {
        return new Request(this.action, pathAndQuery(this.pathAtom.call(objArr), this.queryAtom.call(objArr)), this.headersAtom.call(objArr), this.bodyAtom.call(objArr));
    }

    public final String pathAndQuery(String str, Map<String, String> map) {
        v03.j(str, "uri");
        v03.j(map, "queryParams");
        if (map.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        int iN0 = b.n0(str, '?', 0, false, 6);
        if (iN0 < 0) {
            sb.append('?');
        } else if (iN0 < sb.length() - 1) {
            sb.append('&');
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            sb.append('&');
        }
        String string = sb.delete(sb.length() - 1, sb.length()).toString();
        v03.i(string, "sb.delete(sb.length - 1, sb.length).toString()");
        return string;
    }
}
