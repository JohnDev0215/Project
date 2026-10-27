package defpackage;

import android.graphics.RectF;
import android.view.MotionEvent;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.EventForwarder;

/* JADX INFO: compiled from: EventOffsetHandler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class di0 {
    public final a a;

    /* JADX INFO: compiled from: EventOffsetHandler.java */
    public interface a {
    }

    public di0(CompositorViewHolder.a aVar) {
        this.a = aVar;
    }

    public final void a(MotionEvent motionEvent, boolean z) {
        int iT = a8.t(motionEvent.getActionMasked());
        if (iT == 0 || iT == 9 || iT == 7) {
            CompositorViewHolder.a aVar = (CompositorViewHolder.a) this.a;
            h61 h61Var = CompositorViewHolder.this.l;
            RectF rectF = aVar.a;
            if (h61Var != null) {
                h61Var.K(rectF);
            }
            b(-rectF.top);
            return;
        }
        if (z) {
            if (iT == 1 || iT == 3 || iT == 10) {
                b(0.0f);
            }
        }
    }

    public final void b(float f) {
        WebContents webContentsC;
        Tab tab = CompositorViewHolder.this.C;
        if (tab == null || (webContentsC = tab.c()) == null) {
            return;
        }
        EventForwarder eventForwarderH = webContentsC.H();
        eventForwarderH.c = 0.0f;
        eventForwarderH.d = f;
    }
}
