package org.apache.http.client.methods;

import java.net.URI;
import org.apache.http.HttpRequest;

/* JADX INFO: loaded from: classes7.dex */
public interface HttpUriRequest extends HttpRequest {
    String getMethod();

    URI getURI();

    boolean isAborted();
}
