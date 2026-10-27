package org.apache.http.protocol;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
public class BasicHttpContext implements HttpContext {
    private final Map<String, Object> map;
    private final HttpContext parentContext;

    public BasicHttpContext() {
        this(null);
    }

    public BasicHttpContext(HttpContext httpContext) {
        this.map = new ConcurrentHashMap();
        this.parentContext = httpContext;
    }

    @Override // org.apache.http.protocol.HttpContext
    public Object getAttribute(String str) {
        HttpContext httpContext;
        Args.notNull(str, JsonDocumentFields.POLICY_ID);
        Object obj = this.map.get(str);
        return (obj != null || (httpContext = this.parentContext) == null) ? obj : httpContext.getAttribute(str);
    }

    @Override // org.apache.http.protocol.HttpContext
    public void setAttribute(String str, Object obj) {
        Args.notNull(str, JsonDocumentFields.POLICY_ID);
        if (obj != null) {
            this.map.put(str, obj);
        } else {
            this.map.remove(str);
        }
    }

    public String toString() {
        return this.map.toString();
    }
}
