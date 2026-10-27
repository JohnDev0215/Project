package org.chromium.components.webxr;

import android.app.Activity;
import android.content.Context;

/* JADX INFO: compiled from: ArCoreShim.java */
/* JADX INFO: loaded from: classes3.dex */
public interface a {

    /* JADX INFO: renamed from: org.chromium.components.webxr.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArCoreShim.java */
    public static class C0146a extends Exception {
        public C0146a(Exception exc) {
            super(exc);
        }
    }

    /* JADX INFO: compiled from: ArCoreShim.java */
    public static class b extends Exception {
        public b(Exception exc) {
            super(exc);
        }
    }

    int a(Activity activity);

    int b(Context context);
}
