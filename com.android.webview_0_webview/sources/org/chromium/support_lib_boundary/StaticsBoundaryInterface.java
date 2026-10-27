package org.chromium.support_lib_boundary;

import android.content.Context;
import android.net.Uri;
import android.webkit.ValueCallback;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public interface StaticsBoundaryInterface {
    Uri getSafeBrowsingPrivacyPolicyUrl();

    void initSafeBrowsing(Context context, ValueCallback valueCallback);

    boolean isMultiProcessEnabled();

    void setSafeBrowsingWhitelist(List list, ValueCallback valueCallback);
}
