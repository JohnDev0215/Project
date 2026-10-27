package org.chromium.android_webview;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WebMessageListenerInfo {
    public String a;
    public String[] b;
    public WebMessageListenerHolder c;

    public WebMessageListenerInfo(String str, String[] strArr, WebMessageListenerHolder webMessageListenerHolder) {
        this.a = str;
        this.b = strArr;
        this.c = webMessageListenerHolder;
    }

    public static WebMessageListenerInfo create(String str, String[] strArr, WebMessageListenerHolder webMessageListenerHolder) {
        return new WebMessageListenerInfo(str, strArr, webMessageListenerHolder);
    }
}
