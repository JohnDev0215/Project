package defpackage;

import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: TabGestureStateListener.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lz2 extends m53 {
    public final Tab f;
    public a g;

    /* JADX INFO: compiled from: TabGestureStateListener.java */
    public class a extends os0 {
        public final /* synthetic */ ms0 a;

        public a(GestureListenerManagerImpl gestureListenerManagerImpl) {
            this.a = gestureListenerManagerImpl;
        }

        @Override // defpackage.os0
        public final void a(int i, int i2) {
            h();
        }

        @Override // defpackage.os0
        public final void b(int i, int i2) {
            h();
        }

        @Override // defpackage.os0
        public final void c(int i, int i2) {
            h();
        }

        @Override // defpackage.os0
        public final void e(int i, int i2, boolean z) {
            h();
        }

        public final void h() {
            ms0 ms0Var = this.a;
            boolean zIsScrollInProgress = ms0Var != null ? ms0Var.isScrollInProgress() : false;
            b.a aVarN = ((TabImpl) lz2.this.f).N();
            while (aVarN.hasNext()) {
                ((n23) aVarN.next()).e(zIsScrollInProgress);
            }
        }
    }

    public lz2(Tab tab) {
        super(tab);
        this.f = tab;
    }

    @Override // defpackage.m53
    public final void a(WebContents webContents) {
        GestureListenerManagerImpl gestureListenerManagerImplE;
        if (webContents != null && (gestureListenerManagerImplE = GestureListenerManagerImpl.e(webContents)) != null) {
            gestureListenerManagerImplE.h(this.g);
        }
        this.g = null;
    }

    @Override // defpackage.m53
    public final void c(WebContents webContents) {
        GestureListenerManagerImpl gestureListenerManagerImplE = GestureListenerManagerImpl.e(webContents);
        a aVar = new a(gestureListenerManagerImplE);
        this.g = aVar;
        gestureListenerManagerImplE.d(aVar, 0);
    }
}
