package org.chromium.content_public.browser;

import android.view.KeyEvent;
import android.view.MotionEvent;

/* JADX INFO: compiled from: ViewEventSink.java */
/* JADX INFO: loaded from: classes3.dex */
public interface a {

    /* JADX INFO: renamed from: org.chromium.content_public.browser.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ViewEventSink.java */
    public interface InterfaceC0153a {
        boolean a(KeyEvent keyEvent);

        boolean b(MotionEvent motionEvent);

        boolean c(int i, KeyEvent keyEvent);

        void onScrollChanged(int i, int i2, int i3, int i4);
    }
}
