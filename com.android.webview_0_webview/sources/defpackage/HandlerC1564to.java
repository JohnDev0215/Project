package defpackage;

import android.graphics.Picture;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.webkit.WebResourceResponse;
import java.util.Objects;
import java.util.concurrent.Callable;
import org.chromium.android_webview.AwWebResourceResponse;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;

/* JADX INFO: renamed from: to, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HandlerC1564to extends Handler {
    public final /* synthetic */ C1839yo a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC1564to(C1839yo c1839yo, Looper looper, AbstractC1290oo abstractC1290oo) {
        super(looper);
        this.a = c1839yo;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i = Build.VERSION.SDK_INT;
        InterfaceC1345po interfaceC1345po = this.a.e;
        if (interfaceC1345po != null && ((C1179mn) interfaceC1345po).a.t(0)) {
            removeCallbacksAndMessages(null);
            return;
        }
        switch (message.what) {
            case 1:
                this.a.c.a((String) message.obj);
                return;
            case 2:
                this.a.c.c((String) message.obj);
                return;
            case 3:
                C1454ro c1454ro = (C1454ro) message.obj;
                AbstractC0511ao abstractC0511ao = this.a.c;
                String str = c1454ro.a;
                String str2 = c1454ro.b;
                String str3 = c1454ro.c;
                String str4 = c1454ro.d;
                long j = c1454ro.e;
                C0252Ob c0252Ob = (C0252Ob) abstractC0511ao;
                Objects.requireNonNull(c0252Ob);
                try {
                    TraceEvent.x("WebViewContentsClientAdapter.onDownloadStart", null);
                    if (c0252Ob.o != null) {
                        c0252Ob.o.onDownloadStart(str, str2, str3, str4, j);
                        break;
                    }
                    return;
                } finally {
                    TraceEvent.A("WebViewContentsClientAdapter.onDownloadStart");
                }
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                C1509so c1509so = (C1509so) message.obj;
                AbstractC0511ao abstractC0511ao2 = this.a.c;
                String str5 = c1509so.a;
                String str6 = c1509so.b;
                String str7 = c1509so.c;
                C0252Ob c0252Ob2 = (C0252Ob) abstractC0511ao2;
                Objects.requireNonNull(c0252Ob2);
                try {
                    TraceEvent.x("WebViewContentsClientAdapter.onReceivedLoginRequest", null);
                    c0252Ob2.h.onReceivedLoginRequest(c0252Ob2.e, str5, str6, str7);
                    return;
                } finally {
                    TraceEvent.A("WebViewContentsClientAdapter.onReceivedLoginRequest");
                }
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                C1674vo c1674vo = (C1674vo) message.obj;
                AbstractC0511ao abstractC0511ao3 = this.a.c;
                C0426Xn c0426Xn = c1674vo.a;
                C0408Wn c0408Wn = c1674vo.b;
                Objects.requireNonNull(abstractC0511ao3);
                if (c0426Xn.b) {
                    int i2 = c0408Wn.a;
                    String strK = c0408Wn.b;
                    String str8 = c0426Xn.a;
                    U8 u8 = (U8) abstractC0511ao3;
                    if (i < 23 && !u8.i.a("RECEIVE_WEB_RESOURCE_ERROR")) {
                        try {
                            TraceEvent.x("WebViewContentsClientAdapter.onReceivedError", null);
                            if (strK == null || strK.isEmpty()) {
                                strK = u8.f.k(u8.g, i2);
                            }
                            u8.h.onReceivedError(u8.e, i2, strK, str8);
                        } finally {
                        }
                    }
                }
                U8 u82 = (U8) abstractC0511ao3;
                try {
                    TraceEvent.x("WebViewContentsClientAdapter.onReceivedError", null);
                    if (c0408Wn.b == null || c0408Wn.b.isEmpty()) {
                        c0408Wn.b = u82.f.k(u82.g, c0408Wn.a);
                    }
                    if (u82.i.a("RECEIVE_WEB_RESOURCE_ERROR")) {
                        u82.i.a.onReceivedError(u82.e, new C0645d9(c0426Xn), JM.c(new LM(c0408Wn)));
                    } else if (i >= 23) {
                        C1034k8.a(u82.h, u82.e, c0426Xn, c0408Wn);
                    }
                    TraceEvent.A("WebViewContentsClientAdapter.onReceivedError");
                    Lt.a.a("Android.WebView.onReceivedError.ErrorCode", c0408Wn.a);
                    return;
                } finally {
                }
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                try {
                    Picture picture = message.obj != null ? (Picture) ((Callable) message.obj).call() : null;
                    C0252Ob c0252Ob3 = (C0252Ob) this.a.c;
                    Objects.requireNonNull(c0252Ob3);
                    try {
                        TraceEvent.x("WebViewContentsClientAdapter.onNewPicture", null);
                        if (c0252Ob3.m != null) {
                            c0252Ob3.m.onNewPicture(c0252Ob3.e, picture);
                            break;
                        }
                        TraceEvent.A("WebViewContentsClientAdapter.onNewPicture");
                        this.a.a = SystemClock.uptimeMillis();
                        this.a.b = false;
                        return;
                    } catch (Throwable th) {
                        TraceEvent.A("WebViewContentsClientAdapter.onNewPicture");
                        throw th;
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Error getting picture", e);
                }
                break;
            case 7:
                float fIntBitsToFloat = Float.intBitsToFloat(message.arg1);
                float fIntBitsToFloat2 = Float.intBitsToFloat(message.arg2);
                C0252Ob c0252Ob4 = (C0252Ob) this.a.c;
                Objects.requireNonNull(c0252Ob4);
                try {
                    TraceEvent.x("WebViewContentsClientAdapter.onScaleChangedScaled", null);
                    c0252Ob4.h.onScaleChanged(c0252Ob4.e, fIntBitsToFloat, fIntBitsToFloat2);
                    return;
                } finally {
                    TraceEvent.A("WebViewContentsClientAdapter.onScaleChangedScaled");
                }
            case 8:
                C1729wo c1729wo = (C1729wo) message.obj;
                AbstractC0511ao abstractC0511ao4 = this.a.c;
                C0426Xn c0426Xn2 = c1729wo.a;
                AwWebResourceResponse awWebResourceResponse = c1729wo.b;
                U8 u83 = (U8) abstractC0511ao4;
                Objects.requireNonNull(u83);
                try {
                    TraceEvent.x("WebViewContentsClientAdapter.onReceivedHttpError", null);
                    if (u83.i.a("RECEIVE_HTTP_ERROR")) {
                        String reasonPhrase = awWebResourceResponse.getReasonPhrase();
                        if (reasonPhrase == null || reasonPhrase.isEmpty()) {
                            reasonPhrase = "UNKNOWN";
                        }
                        u83.i.a.onReceivedHttpError(u83.e, new C0645d9(c0426Xn2), new WebResourceResponse(awWebResourceResponse.getMimeType(), awWebResourceResponse.getCharset(), awWebResourceResponse.getStatusCode(), reasonPhrase, awWebResourceResponse.f, awWebResourceResponse.getData()));
                    } else if (i >= 23) {
                        C1034k8.b(u83.h, u83.e, c0426Xn2, awWebResourceResponse);
                    }
                    return;
                } finally {
                    TraceEvent.A("WebViewContentsClientAdapter.onReceivedHttpError");
                }
            case 9:
                this.a.c.b((String) message.obj);
                return;
            case 10:
                this.a.c.e((String) message.obj);
                return;
            case 11:
                this.a.c.d(message.arg1);
                return;
            case 12:
                String str9 = (String) message.obj;
                this.a.c.c(str9);
                this.a.c.a(str9);
                this.a.c.d(100);
                this.a.c.b(str9);
                return;
            case 13:
                C1400qo c1400qo = (C1400qo) message.obj;
                AbstractC0511ao abstractC0511ao5 = this.a.c;
                String str10 = c1400qo.a;
                boolean z = c1400qo.b;
                C0252Ob c0252Ob5 = (C0252Ob) abstractC0511ao5;
                Objects.requireNonNull(c0252Ob5);
                try {
                    TraceEvent.x("WebViewContentsClientAdapter.doUpdateVisitedHistory", null);
                    c0252Ob5.h.doUpdateVisitedHistory(c0252Ob5.e, str10, z);
                    return;
                } finally {
                    TraceEvent.A("WebViewContentsClientAdapter.doUpdateVisitedHistory");
                }
            case 14:
                C1619uo c1619uo = (C1619uo) message.obj;
                AbstractC0511ao abstractC0511ao6 = this.a.c;
                Message message2 = c1619uo.a;
                Message message3 = c1619uo.b;
                C0252Ob c0252Ob6 = (C0252Ob) abstractC0511ao6;
                Objects.requireNonNull(c0252Ob6);
                try {
                    TraceEvent.x("WebViewContentsClientAdapter.onFormResubmission", null);
                    c0252Ob6.h.onFormResubmission(c0252Ob6.e, message2, message3);
                    return;
                } finally {
                    TraceEvent.A("WebViewContentsClientAdapter.onFormResubmission");
                }
            case 15:
                C1784xo c1784xo = (C1784xo) message.obj;
                AbstractC0511ao abstractC0511ao7 = this.a.c;
                C0426Xn c0426Xn3 = c1784xo.a;
                int i3 = c1784xo.b;
                Callback callback = c1784xo.c;
                U8 u84 = (U8) abstractC0511ao7;
                Objects.requireNonNull(u84);
                try {
                    TraceEvent.x("WebViewContentsClientAdapter.onSafeBrowsingHit", null);
                    if (u84.i.a("SAFE_BROWSING_HIT")) {
                        u84.i.a.onSafeBrowsingHit(u84.e, new C0645d9(c0426Xn3), i3, JM.c(new KM(callback)));
                    } else if (i >= 27) {
                        C1256o8.a(u84.h, u84.e, c0426Xn3, i3, callback);
                    } else {
                        callback.a(new C0627cs(0, true));
                    }
                    return;
                } finally {
                    TraceEvent.A("WebViewContentsClientAdapter.onSafeBrowsingHit");
                }
            default:
                StringBuilder sbF = Z7.f("AwContentsClientCallbackHelper: unhandled message ");
                sbF.append(message.what);
                throw new IllegalStateException(sbF.toString());
        }
    }
}
