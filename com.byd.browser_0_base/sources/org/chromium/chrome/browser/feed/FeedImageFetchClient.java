package org.chromium.chrome.browser.feed;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.xsurface.ImageFetchClient$HttpResponseConsumer;

/* JADX INFO: loaded from: classes.dex */
public class FeedImageFetchClient {
    @CalledByNative
    public static void onHttpResponse(ImageFetchClient$HttpResponseConsumer imageFetchClient$HttpResponseConsumer, int i, byte[] bArr) {
        imageFetchClient$HttpResponseConsumer.getClass();
    }
}
