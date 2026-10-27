package org.chromium.chrome.browser.autofill.settings;

import android.graphics.Bitmap;
import java.util.LinkedList;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.autofill.g;

/* JADX INFO: loaded from: classes.dex */
public class VirtualCardEnrollmentFields {
    public final LinkedList<g> a = new LinkedList<>();
    public final LinkedList<g> b = new LinkedList<>();
    public final Bitmap c;
    public final String d;

    public VirtualCardEnrollmentFields(Bitmap bitmap, String str) {
        this.d = str;
        this.c = bitmap;
    }

    @CalledByNative
    public static VirtualCardEnrollmentFields create(String str, Bitmap bitmap) {
        return new VirtualCardEnrollmentFields(bitmap, str);
    }

    @CalledByNative
    public final void addGoogleLegalMessageLine(String str) {
        this.a.add(new g(str));
    }

    @CalledByNative
    public final void addIssuerLegalMessageLine(String str) {
        this.b.add(new g(str));
    }

    @CalledByNative
    public final void addLinkToLastGoogleLegalMessageLine(int i, int i2, String str) {
        this.a.getLast().b.add(new g.a(i, i2, str));
    }

    @CalledByNative
    public final void addLinkToLastIssuerLegalMessageLine(int i, int i2, String str) {
        this.b.getLast().b.add(new g.a(i, i2, str));
    }
}
