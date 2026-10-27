package org.apache.http.impl.cookie;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieSpec;

/* JADX INFO: loaded from: classes7.dex */
public abstract class AbstractCookieSpec implements CookieSpec {
    private final Map<String, CookieAttributeHandler> attribHandlerMap;

    @Override // org.apache.http.cookie.CookieSpec
    public abstract /* synthetic */ int getVersion();

    public AbstractCookieSpec() {
        this.attribHandlerMap = new ConcurrentHashMap(10);
    }

    protected AbstractCookieSpec(CommonCookieAttributeHandler... commonCookieAttributeHandlerArr) {
        this.attribHandlerMap = new ConcurrentHashMap(commonCookieAttributeHandlerArr.length);
        for (CommonCookieAttributeHandler commonCookieAttributeHandler : commonCookieAttributeHandlerArr) {
            this.attribHandlerMap.put(commonCookieAttributeHandler.getAttributeName(), commonCookieAttributeHandler);
        }
    }

    protected CookieAttributeHandler findAttribHandler(String str) {
        return this.attribHandlerMap.get(str);
    }

    protected Collection<CookieAttributeHandler> getAttribHandlers() {
        return this.attribHandlerMap.values();
    }
}
