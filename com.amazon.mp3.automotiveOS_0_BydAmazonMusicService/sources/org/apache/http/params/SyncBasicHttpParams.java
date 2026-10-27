package org.apache.http.params;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public class SyncBasicHttpParams extends BasicHttpParams {
    @Override // org.apache.http.params.BasicHttpParams, org.apache.http.params.HttpParams
    public synchronized HttpParams setParameter(String str, Object obj) {
        return super.setParameter(str, obj);
    }

    @Override // org.apache.http.params.BasicHttpParams, org.apache.http.params.HttpParams
    public synchronized Object getParameter(String str) {
        return super.getParameter(str);
    }

    @Override // org.apache.http.params.BasicHttpParams
    public synchronized Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
