package org.apache.http;

/* JADX INFO: loaded from: classes7.dex */
public interface HttpEntityEnclosingRequest extends HttpRequest {
    boolean expectContinue();

    HttpEntity getEntity();
}
