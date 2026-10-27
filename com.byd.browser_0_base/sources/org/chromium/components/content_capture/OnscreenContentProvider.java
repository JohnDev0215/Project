package org.chromium.components.content_capture;

import J.N;
import android.content.Context;
import android.view.View;
import defpackage.fa1;
import defpackage.iv;
import defpackage.iy;
import defpackage.jq0;
import defpackage.n12;
import defpackage.oi0;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes2.dex */
public class OnscreenContentProvider {
    public static Boolean d;
    public long a;
    public final ArrayList<iy> b;
    public WeakReference<WebContents> c;

    public OnscreenContentProvider(Context context, View view, WebContents webContents) {
        WebContents webContents2;
        ArrayList<iy> arrayList = new ArrayList<>();
        this.b = arrayList;
        this.c = new WeakReference<>(webContents);
        if (d == null) {
            d = Boolean.valueOf(iv.e().g("dump-captured-content-to-logcat-for-testing"));
        }
        if (PlatformContentCaptureController.d == null) {
            PlatformContentCaptureController.d = new PlatformContentCaptureController(context.getApplicationContext());
        }
        n12 n12Var = !PlatformContentCaptureController.d.a ? null : new n12(view);
        if (n12Var != null) {
            arrayList.add(n12Var);
        }
        if (N.MxGt0EOk()) {
            arrayList.add(new oi0());
        }
        if (arrayList.isEmpty() || (webContents2 = this.c.get()) == null) {
            return;
        }
        this.a = N.M87a3iHr(this, webContents2);
    }

    public static String[] a(jq0 jq0Var, ContentCaptureFrame contentCaptureFrame) {
        ArrayList arrayList = new ArrayList();
        if (jq0Var != null) {
            Iterator<ContentCaptureFrame> it = jq0Var.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().d);
            }
        }
        if (contentCaptureFrame != null) {
            arrayList.add(contentCaptureFrame.d);
        }
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }

    public static jq0 b(Object[] objArr) {
        jq0 jq0Var = new jq0(objArr.length);
        for (Object obj : objArr) {
            jq0Var.add((ContentCaptureFrame) obj);
        }
        return jq0Var;
    }

    @CalledByNative
    public final void didCaptureContent(Object[] objArr, ContentCaptureFrame contentCaptureFrame) {
        jq0 jq0VarB = b(objArr);
        String[] strArrA = a(jq0VarB, contentCaptureFrame);
        for (iy iyVar : this.b) {
            if (iyVar.f(strArrA)) {
                iyVar.a(jq0VarB, contentCaptureFrame);
            }
        }
        if (d.booleanValue()) {
            fa1.g("ContentCapture", "Captured Content: %s", contentCaptureFrame);
        }
    }

    @CalledByNative
    public final void didRemoveContent(Object[] objArr, long[] jArr) {
        jq0 jq0VarB = b(objArr);
        String[] strArrA = a(jq0VarB, null);
        for (iy iyVar : this.b) {
            if (iyVar.f(strArrA)) {
                iyVar.g(jq0VarB, jArr);
            }
        }
        if (d.booleanValue()) {
            fa1.g("ContentCapture", "Removed Content: %s", jq0VarB.get(0) + " " + Arrays.toString(jArr));
        }
    }

    @CalledByNative
    public final void didRemoveSession(Object[] objArr) {
        jq0 jq0VarB = b(objArr);
        String[] strArrA = a(jq0VarB, null);
        for (iy iyVar : this.b) {
            if (iyVar.f(strArrA)) {
                iyVar.e(jq0VarB);
            }
        }
        if (d.booleanValue()) {
            fa1.g("ContentCapture", "Removed Session: %s", jq0VarB.get(0));
        }
    }

    @CalledByNative
    public final void didUpdateContent(Object[] objArr, ContentCaptureFrame contentCaptureFrame) {
        jq0 jq0VarB = b(objArr);
        String[] strArrA = a(jq0VarB, contentCaptureFrame);
        for (iy iyVar : this.b) {
            if (iyVar.f(strArrA)) {
                iyVar.b(jq0VarB, contentCaptureFrame);
            }
        }
        if (d.booleanValue()) {
            fa1.g("ContentCapture", "Updated Content: %s", contentCaptureFrame);
        }
    }

    @CalledByNative
    public final void didUpdateFavicon(ContentCaptureFrame contentCaptureFrame) {
        String[] strArrA = a(null, contentCaptureFrame);
        for (iy iyVar : this.b) {
            if (iyVar.f(strArrA)) {
                iyVar.c(contentCaptureFrame);
            }
        }
        if (d.booleanValue()) {
            fa1.g("ContentCapture", "Updated Favicon: %s", contentCaptureFrame.f);
        }
    }

    @CalledByNative
    public final void didUpdateTitle(ContentCaptureFrame contentCaptureFrame) {
        String[] strArrA = a(null, contentCaptureFrame);
        for (iy iyVar : this.b) {
            if (iyVar.f(strArrA)) {
                iyVar.d(contentCaptureFrame);
            }
        }
        if (d.booleanValue()) {
            fa1.g("ContentCapture", "Updated Title: %s", contentCaptureFrame.e);
        }
    }

    @CalledByNative
    public final int getOffsetY(WebContents webContents) {
        return (int) Math.floor(((WebContentsImpl) webContents).l.k);
    }

    @CalledByNative
    public final boolean shouldCapture(String str) {
        String[] strArr = {str};
        Iterator<iy> it = this.b.iterator();
        while (it.hasNext()) {
            if (it.next().f(strArr)) {
                return true;
            }
        }
        return false;
    }
}
