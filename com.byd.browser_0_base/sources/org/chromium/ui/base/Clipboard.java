package org.chromium.ui.base;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.net.Uri;
import android.util.Log;
import defpackage.fu;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class Clipboard {

    @SuppressLint({"StaticFieldLeak"})
    public static Clipboard b;
    public long a;

    public interface a {

        /* JADX INFO: renamed from: org.chromium.ui.base.Clipboard$a$a, reason: collision with other inner class name */
        public static class C0157a {
            public final Uri a;
            public final long b;

            public C0157a(Uri uri, long j) {
                this.a = uri;
                this.b = j;
            }
        }
    }

    @CalledByNative
    public static Clipboard getInstance() {
        if (b == null) {
            ClipboardManager clipboardManager = (ClipboardManager) xz.a.getSystemService("clipboard");
            if (clipboardManager != null) {
                b = new b(clipboardManager);
            } else {
                b = new Clipboard();
            }
        }
        return b;
    }

    public boolean a() {
        return false;
    }

    public Uri c() {
        return null;
    }

    public void e(fu fuVar) {
        Log.w("cr_Clipboard", "setImageFileProvider is a no-op because Clipboard service isn't available");
    }

    public void f(Uri uri) {
        Log.w("cr_Clipboard", "setImageUri is a no-op because Clipboard service isn't available");
    }

    public void g(String str) {
        Log.w("cr_Clipboard", "setPassword is a no-op because Clipboard service isn't available");
    }

    @CalledByNative
    public String getCoercedText() {
        return null;
    }

    @CalledByNative
    public String getHTMLText() {
        return null;
    }

    @CalledByNative
    public String getImageUriString() {
        return null;
    }

    @CalledByNative
    public byte[] getPng() {
        return null;
    }

    @CalledByNative
    public String getUrl() {
        return null;
    }

    public void h(String str, String str2) {
        Log.w("cr_Clipboard", "setText is a no-op because Clipboard service isn't available");
    }

    @CalledByNative
    public boolean hasCoercedText() {
        return false;
    }

    @CalledByNative
    public boolean hasHTMLOrStyledText() {
        return false;
    }

    @CalledByNative
    public boolean hasImage() {
        return false;
    }

    @CalledByNative
    public boolean hasUrl() {
        return false;
    }

    public void i(String str) {
        Log.w("cr_Clipboard", "setTextAndNotify is a no-op because Clipboard service isn't available");
    }

    @CalledByNative
    public void setHTMLText(String str, String str2) {
        Log.w("cr_Clipboard", "setHTMLText is a no-op because Clipboard service isn't available");
    }

    @CalledByNative
    public void setImage(byte[] bArr, String str) {
        Log.w("cr_Clipboard", "setImage is a no-op because Clipboard service isn't available");
    }

    @CalledByNative
    public final void setNativePtr(long j) {
        this.a = j;
    }

    @CalledByNative
    public void setText(String str) {
        Log.w("cr_Clipboard", "setText is a no-op because Clipboard service isn't available");
    }

    @CalledByNative
    public void clear() {
    }

    public void b(GURL gurl) {
    }

    public void d(boolean z) {
    }
}
