package androidx.appcompat.widget;

import J.N;
import android.graphics.drawable.TransitionDrawable;
import android.os.SystemClock;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.emoji2.text.g;
import androidx.mediarouter.media.a;
import defpackage.aw1;
import defpackage.c73;
import defpackage.cv2;
import defpackage.d73;
import defpackage.ej2;
import defpackage.ev1;
import defpackage.fx2;
import defpackage.fy2;
import defpackage.gg;
import defpackage.h61;
import defpackage.i7;
import defpackage.ji;
import defpackage.jz;
import defpackage.md3;
import defpackage.mh;
import defpackage.oj2;
import defpackage.qf;
import defpackage.rs0;
import defpackage.sq2;
import defpackage.t90;
import defpackage.xe1;
import defpackage.xm;
import defpackage.yv1;
import java.util.Iterator;
import org.chromium.base.MemoryPressureListener;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.autofill.CardUnmaskBridge;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.content_creation.notes.NoteCreationDialog;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ a(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        View viewD;
        int width;
        switch (this.e) {
            case 0:
                Toolbar.ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = ((Toolbar) this.f).Q;
                MenuItemImpl menuItemImpl = expandedActionViewMenuPresenter != null ? expandedActionViewMenuPresenter.f : null;
                if (menuItemImpl != null) {
                    menuItemImpl.collapseActionView();
                }
                break;
            case 1:
                ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) this.f;
                int i = ContentLoadingProgressBar.g;
                contentLoadingProgressBar.getClass();
                System.currentTimeMillis();
                contentLoadingProgressBar.setVisibility(0);
                break;
            case 2:
                ((DrawerLayout) this.f).c(false);
                break;
            case 3:
                DrawerLayout.e eVar = (DrawerLayout.e) this.f;
                int i2 = eVar.b.o;
                boolean z = eVar.a == 3;
                DrawerLayout drawerLayout = DrawerLayout.this;
                if (z) {
                    viewD = drawerLayout.d(3);
                    width = (viewD != null ? -viewD.getWidth() : 0) + i2;
                } else {
                    viewD = drawerLayout.d(5);
                    width = drawerLayout.getWidth() - i2;
                }
                if (viewD != null) {
                    if (((z && viewD.getLeft() < width) || (!z && viewD.getLeft() > width)) && drawerLayout.g(viewD) == 0) {
                        DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) viewD.getLayoutParams();
                        eVar.b.v(viewD, width, viewD.getTop());
                        layoutParams.c = true;
                        drawerLayout.invalidate();
                        int i3 = eVar.a == 3 ? 5 : 3;
                        DrawerLayout drawerLayout2 = DrawerLayout.this;
                        View viewD2 = drawerLayout2.d(i3);
                        if (viewD2 != null) {
                            drawerLayout2.b(viewD2);
                        }
                        if (!drawerLayout.w) {
                            long jUptimeMillis = SystemClock.uptimeMillis();
                            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                            int childCount = drawerLayout.getChildCount();
                            for (int i4 = 0; i4 < childCount; i4++) {
                                drawerLayout.getChildAt(i4).dispatchTouchEvent(motionEventObtain);
                            }
                            motionEventObtain.recycle();
                            drawerLayout.w = true;
                        }
                        break;
                    }
                }
                break;
            case 4:
                ((g.b) this.f).c();
                break;
            case 5:
                ((a.c) this.f).n = -1;
                break;
            case 6:
                xe1 xe1Var = (xe1) this.f;
                xe1Var.g = false;
                if (xe1Var.f != -1) {
                    long jElapsedRealtime = SystemClock.elapsedRealtime() - xe1Var.f;
                    if (jElapsedRealtime >= 240000) {
                        MemoryPressureListener.a(2);
                        break;
                    } else {
                        long j = 240000 - jElapsedRealtime;
                        Object obj = ThreadUtils.a;
                        if (!xe1Var.g) {
                            ThreadUtils.c().postDelayed(new a(6, xe1Var), j);
                            xe1Var.g = true;
                            break;
                        }
                    }
                }
                break;
            case 7:
                xm xmVar = (xm) this.f;
                Iterator<Pair<d73, Runnable>> it = xmVar.a.iterator();
                while (it.hasNext()) {
                    ((Runnable) it.next().second).run();
                    if (xmVar.b) {
                        break;
                    }
                }
                break;
            case 8:
                ((c73) this.f).g();
                break;
            case 9:
                fx2 fx2Var = (fx2) this.f;
                fx2Var.k = null;
                fx2Var.f();
                break;
            case 10:
                ((i7) ((ev1) this.f).a).c(Integer.valueOf(R$id.page_zoom_id));
                break;
            case 11:
                ((org.chromium.chrome.browser.autofill.b) this.f).l = null;
                break;
            case 12:
                CardUnmaskBridge cardUnmaskBridge = (CardUnmaskBridge) this.f;
                N.Mek0Fv7c(cardUnmaskBridge.a, cardUnmaskBridge);
                break;
            case 13:
                ((gg) this.f).B(true);
                break;
            case 14:
                ((qf) this.f).j();
                break;
            case 15:
                mh.b bVar = (mh.b) this.f;
                bVar.getClass();
                fy2.a().a.c(bVar);
                break;
            case 16:
                rs0.a((t90.b) this.f);
                break;
            case 17:
                TraceEvent.F(((sq2) this.f).hashCode(), "WebappSplashScreen.visible");
                break;
            case 18:
                CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.f;
                ViewGroup viewGroup = (ViewGroup) compositorViewHolder.x;
                compositorViewHolder.m.setBackgroundResource(0);
                if (viewGroup != null) {
                    viewGroup.setBackgroundResource(0);
                }
                compositorViewHolder.e = null;
                break;
            case 19:
                ji jiVar = ((EphemeralTabCoordinator) this.f).j;
                int iJ = jiVar.j();
                if (iJ == 1) {
                    jiVar.l();
                } else if (iJ == 3) {
                    jiVar.k();
                }
                break;
            case 20:
                ((h61) this.f).T(null);
                break;
            case 21:
                cv2 cv2Var = (cv2) this.f;
                cv2Var.e.f.l(cv2Var);
                break;
            case 22:
                org.chromium.chrome.browser.content_creation.notes.a aVar = (org.chromium.chrome.browser.content_creation.notes.a) ((md3) this.f);
                aVar.getClass();
                long jCurrentTimeMillis = System.currentTimeMillis() - aVar.h;
                NoteCreationDialog noteCreationDialog = aVar.d;
                int i5 = noteCreationDialog.v0;
                oj2.Y(jCurrentTimeMillis, "NoteCreation.TimeTo.DismissCreationDialog");
                oj2.O("NoteCreation.CreationStatus", false);
                oj2.Q(i5, "NoteCreation.NumberOfTemplateChanges");
                noteCreationDialog.I0(false, false);
                break;
            case 23:
                ((jz) this.f).a();
                break;
            case 24:
                ((yv1) this.f).I();
                break;
            case 25:
                ((aw1) this.f).B(-1);
                break;
            case 26:
                ej2 ej2Var = (ej2) this.f;
                int i6 = ej2.f;
                ej2Var.a(1);
                break;
            case 27:
                ((TransitionDrawable) this.f).startTransition(250);
                break;
            default:
                ((ImageButton) this.f).callOnClick();
                break;
        }
    }
}
