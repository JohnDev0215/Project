package org.apache.http.impl.client;

import org.apache.http.params.AbstractHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public class ClientParamsStack extends AbstractHttpParams {
    protected final HttpParams applicationParams;
    protected final HttpParams clientParams;
    protected final HttpParams overrideParams;
    protected final HttpParams requestParams;

    public ClientParamsStack(HttpParams httpParams, HttpParams httpParams2, HttpParams httpParams3, HttpParams httpParams4) {
        this.applicationParams = httpParams;
        this.clientParams = httpParams2;
        this.requestParams = httpParams3;
        this.overrideParams = httpParams4;
    }

    @Override // org.apache.http.params.HttpParams
    public Object getParameter(String str) {
        HttpParams httpParams;
        HttpParams httpParams2;
        HttpParams httpParams3;
        Args.notNull(str, "Parameter name");
        HttpParams httpParams4 = this.overrideParams;
        Object parameter = httpParams4 != null ? httpParams4.getParameter(str) : null;
        if (parameter == null && (httpParams3 = this.requestParams) != null) {
            parameter = httpParams3.getParameter(str);
        }
        if (parameter == null && (httpParams2 = this.clientParams) != null) {
            parameter = httpParams2.getParameter(str);
        }
        return (parameter != null || (httpParams = this.applicationParams) == null) ? parameter : httpParams.getParameter(str);
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams setParameter(String str, Object obj) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Setting parameters in a stack is not supported.");
    }
}
