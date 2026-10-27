package org.apache.http.params;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public interface HttpParams {
    boolean getBooleanParameter(String str, boolean z);

    int getIntParameter(String str, int i);

    long getLongParameter(String str, long j);

    Object getParameter(String str);

    HttpParams setBooleanParameter(String str, boolean z);

    HttpParams setIntParameter(String str, int i);

    HttpParams setParameter(String str, Object obj);
}
