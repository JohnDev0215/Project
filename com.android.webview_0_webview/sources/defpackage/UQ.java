package defpackage;

import android.view.View;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class UQ {
    public HD a;
    public JD b;
    public final TQ c;
    public boolean d;
    public boolean e;
    public View f;

    public UQ(View view, WebContents webContents, TQ tq) {
        this.c = tq;
        this.a = GestureListenerManagerImpl.O(webContents);
        this.f = view;
        view.setOnTouchListener(new RQ(this));
        SQ sq = new SQ(this);
        this.b = sq;
        ((GestureListenerManagerImpl) this.a).t.f(sq);
    }

    public boolean a() {
        return this.e || this.d;
    }
}
