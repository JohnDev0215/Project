package org.apache.http.params;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public abstract class AbstractHttpParams implements HttpParams {
    protected AbstractHttpParams() {
    }

    @Override // org.apache.http.params.HttpParams
    public long getLongParameter(String str, long j) {
        Object parameter = getParameter(str);
        return parameter == null ? j : ((Long) parameter).longValue();
    }

    @Override // org.apache.http.params.HttpParams
    public int getIntParameter(String str, int i) {
        Object parameter = getParameter(str);
        return parameter == null ? i : ((Integer) parameter).intValue();
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams setIntParameter(String str, int i) {
        setParameter(str, Integer.valueOf(i));
        return this;
    }

    @Override // org.apache.http.params.HttpParams
    public boolean getBooleanParameter(String str, boolean z) {
        Object parameter = getParameter(str);
        return parameter == null ? z : ((Boolean) parameter).booleanValue();
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams setBooleanParameter(String str, boolean z) {
        setParameter(str, z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }
}
