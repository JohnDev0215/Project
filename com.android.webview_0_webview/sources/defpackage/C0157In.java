package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebResourceResponse;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwContentsBackgroundThreadClient;
import org.chromium.android_webview.AwWebResourceResponse;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;

/* JADX INFO: renamed from: In, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0157In extends AwContentsBackgroundThreadClient {
    public final /* synthetic */ AwContents a;

    public C0157In(AwContents awContents, C1838yn c1838yn) {
        this.a = awContents;
    }

    @Override // org.chromium.android_webview.AwContentsBackgroundThreadClient
    public AwWebResourceResponse a(C0426Xn c0426Xn) {
        AwWebResourceResponse awWebResourceResponse;
        String str = c0426Xn.a;
        C0338Sp c0338Sp = this.a.j0;
        AwWebResourceResponse awWebResourceResponse2 = null;
        if (c0338Sp.a.equals(str)) {
            try {
                final AbstractC0511ao abstractC0511ao = c0338Sp.b;
                PipedInputStream pipedInputStream = new PipedInputStream();
                final PipedOutputStream pipedOutputStream = new PipedOutputStream(pipedInputStream);
                PostTask.b(AbstractC0538bE.a, new Runnable(abstractC0511ao, pipedOutputStream) { // from class: Qp
                    public final AbstractC0511ao s;
                    public final PipedOutputStream t;

                    {
                        this.s = abstractC0511ao;
                        this.t = pipedOutputStream;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        AbstractC0511ao abstractC0511ao2 = this.s;
                        final PipedOutputStream pipedOutputStream2 = this.t;
                        C0252Ob c0252Ob = (C0252Ob) abstractC0511ao2;
                        Objects.requireNonNull(c0252Ob);
                        try {
                            TraceEvent.x("WebViewContentsClientAdapter.getDefaultVideoPoster", null);
                            final Bitmap defaultVideoPoster = c0252Ob.k != null ? c0252Ob.k.getDefaultVideoPoster() : null;
                            if (defaultVideoPoster == null) {
                                Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(c0252Ob.g.getResources(), AbstractC0317Rm.q1);
                                defaultVideoPoster = Bitmap.createBitmap(bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight(), bitmapDecodeResource.getConfig());
                                defaultVideoPoster.eraseColor(-7829368);
                                new Canvas(defaultVideoPoster).drawBitmap(bitmapDecodeResource, 0.0f, 0.0f, (Paint) null);
                            }
                            TraceEvent.A("WebViewContentsClientAdapter.getDefaultVideoPoster");
                            PostTask.a(C1242nv.h, new Runnable(defaultVideoPoster, pipedOutputStream2) { // from class: Rp
                                public final Bitmap s;
                                public final PipedOutputStream t;

                                {
                                    this.s = defaultVideoPoster;
                                    this.t = pipedOutputStream2;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    Bitmap bitmap = this.s;
                                    PipedOutputStream pipedOutputStream3 = this.t;
                                    try {
                                        try {
                                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, pipedOutputStream3);
                                            pipedOutputStream3.flush();
                                        } catch (IOException e) {
                                            Log.e("DefaultVideoPosterRequestHandler", null, e);
                                        }
                                    } finally {
                                        C0338Sp.a(pipedOutputStream3);
                                    }
                                }
                            }, 0L);
                        } catch (Throwable th) {
                            TraceEvent.A("WebViewContentsClientAdapter.getDefaultVideoPoster");
                            throw th;
                        }
                    }
                });
                awWebResourceResponse = new AwWebResourceResponse("image/png", null, pipedInputStream);
            } catch (IOException e) {
                Log.e("DefaultVideoPosterRequestHandler", null, e);
                awWebResourceResponse = null;
            }
        } else {
            awWebResourceResponse = null;
        }
        if (awWebResourceResponse != null) {
            return awWebResourceResponse;
        }
        C0252Ob c0252Ob = (C0252Ob) this.a.E;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.shouldInterceptRequest", null);
            WebResourceResponse webResourceResponseShouldInterceptRequest = c0252Ob.h.shouldInterceptRequest(c0252Ob.e, new C0645d9(c0426Xn));
            if (webResourceResponseShouldInterceptRequest != null) {
                Map<String, String> responseHeaders = webResourceResponseShouldInterceptRequest.getResponseHeaders();
                if (responseHeaders == null) {
                    responseHeaders = new HashMap<>();
                }
                AwWebResourceResponse awWebResourceResponse3 = new AwWebResourceResponse(webResourceResponseShouldInterceptRequest.getMimeType(), webResourceResponseShouldInterceptRequest.getEncoding(), webResourceResponseShouldInterceptRequest.getData(), webResourceResponseShouldInterceptRequest.getStatusCode(), webResourceResponseShouldInterceptRequest.getReasonPhrase(), responseHeaders);
                TraceEvent.A("WebViewContentsClientAdapter.shouldInterceptRequest");
                awWebResourceResponse2 = awWebResourceResponse3;
            }
            if (awWebResourceResponse2 == null) {
                Handler handler = this.a.E.a.d;
                handler.sendMessage(handler.obtainMessage(1, str));
            }
            if (awWebResourceResponse2 != null) {
                if (awWebResourceResponse2.getMimeType() == null) {
                    AbstractC0229Mo.b(8);
                } else {
                    AbstractC0229Mo.b(9);
                }
            }
            if (awWebResourceResponse2 != null && awWebResourceResponse2.getData() == null) {
                C1839yo c1839yo = this.a.E.a;
                C1674vo c1674vo = new C1674vo(c0426Xn, new C0408Wn());
                Handler handler2 = c1839yo.d;
                handler2.sendMessage(handler2.obtainMessage(5, c1674vo));
            }
            return awWebResourceResponse2;
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.shouldInterceptRequest");
        }
    }
}
