package org.apache.http.params;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public class BasicHttpParams extends AbstractHttpParams implements Serializable, Cloneable {
    private final Map<String, Object> parameters = new ConcurrentHashMap();

    @Override // org.apache.http.params.HttpParams
    public Object getParameter(String str) {
        return this.parameters.get(str);
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams setParameter(String str, Object obj) {
        if (str == null) {
            return this;
        }
        if (obj != null) {
            this.parameters.put(str, obj);
        } else {
            this.parameters.remove(str);
        }
        return this;
    }

    public Object clone() throws CloneNotSupportedException {
        BasicHttpParams basicHttpParams = (BasicHttpParams) super.clone();
        copyParams(basicHttpParams);
        return basicHttpParams;
    }

    public void copyParams(HttpParams httpParams) {
        for (Map.Entry<String, Object> entry : this.parameters.entrySet()) {
            httpParams.setParameter(entry.getKey(), entry.getValue());
        }
    }

    public String toString() {
        return "[parameters=" + this.parameters + "]";
    }
}
