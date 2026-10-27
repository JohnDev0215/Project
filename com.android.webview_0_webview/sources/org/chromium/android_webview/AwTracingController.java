package org.chromium.android_webview;

import J.N;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwTracingController {
    public static final List c = new ArrayList(Arrays.asList("*"));
    public static final List d = new ArrayList(Arrays.asList("android_webview", "Java", "toplevel"));
    public static final List e = new ArrayList(Arrays.asList("blink", "cc", "netlog", "renderer.scheduler", "toplevel", "v8"));
    public static final List f = new ArrayList(Arrays.asList("benchmark", "input", "evdev", "renderer.scheduler", "toplevel"));
    public static final List g = new ArrayList(Arrays.asList("blink", "cc", "gpu", "toplevel"));
    public static final List h = new ArrayList(Arrays.asList("blink", "cc", "gpu", "renderer.scheduler", "v8", "toplevel"));
    public static final List i = new ArrayList(Arrays.asList("blink", "cc", "gpu", "renderer.scheduler", "v8", "toplevel", "disabled-by-default-cc.debug", "disabled-by-default-cc.debug.picture", "disabled-by-default-cc.debug.display_items"));
    public static final List j = new ArrayList(Arrays.asList(c, d, e, f, g, h, i));
    public OutputStream a;
    public long b = N.MJiQrQ3Z(this);

    private void onTraceDataChunkReceived(byte[] bArr) throws IOException {
        OutputStream outputStream = this.a;
        if (outputStream != null) {
            outputStream.write(bArr);
        }
    }

    private void onTraceDataComplete() throws IOException {
        OutputStream outputStream = this.a;
        if (outputStream != null) {
            outputStream.close();
        }
    }

    public boolean a() {
        return N.MI9fwsnY(this.b, this);
    }
}
