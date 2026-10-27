package org.apache.http;

/* JADX INFO: loaded from: classes7.dex */
public interface HttpResponse extends HttpMessage {
    HttpEntity getEntity();

    StatusLine getStatusLine();

    void setEntity(HttpEntity httpEntity);

    void setStatusCode(int i) throws IllegalStateException;
}
