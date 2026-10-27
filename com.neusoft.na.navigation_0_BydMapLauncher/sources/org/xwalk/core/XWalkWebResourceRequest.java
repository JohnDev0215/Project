package org.xwalk.core;

import android.net.Uri;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public interface XWalkWebResourceRequest {
    String getMethod();

    Map<String, String> getRequestHeaders();

    Uri getUrl();

    boolean hasGesture();

    boolean isForMainFrame();
}
