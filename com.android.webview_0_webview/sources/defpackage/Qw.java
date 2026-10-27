package defpackage;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Qw extends AbstractC1491sO {
    public final Context c;
    public ViewGroup d;
    public ModalDialogView e;
    public MO f;
    public boolean g;
    public int h = 200;

    public Qw(Context context) {
        this.c = context;
    }

    @Override // defpackage.AbstractC1491sO
    public void b(JO jo) {
        C0683dt c0683dtX;
        if (this.d == null) {
            YQ yq = (YQ) this;
            c0683dtX = C0683dt.x();
            try {
                FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(yq.j).inflate(AbstractC0425Xm.p0, (ViewGroup) null);
                c0683dtX.close();
                frameLayout.setVisibility(8);
                frameLayout.setClickable(true);
                yq.i.d.addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
                this.d = frameLayout;
            } catch (Throwable th) {
                throw th;
            }
        }
        int i = jo.b(AbstractC1711wO.p) ? AbstractC0622cn.E7 : AbstractC0622cn.F7;
        c0683dtX = C0683dt.x();
        try {
            ModalDialogView modalDialogView = (ModalDialogView) LayoutInflater.from(new ContextThemeWrapper(this.c, i)).inflate(AbstractC0425Xm.r0, (ViewGroup) null);
            c0683dtX.close();
            this.e = modalDialogView;
            this.f = new MO(jo, modalDialogView, new Pw(this, null), true);
            g(true);
            YQ yq2 = (YQ) this;
            yq2.d.animate().cancel();
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 17);
            yq2.e.setBackgroundResource(AbstractC0317Rm.Z1);
            yq2.d.addView(yq2.e, layoutParams);
            yq2.d.setAlpha(0.0f);
            yq2.d.setVisibility(0);
            yq2.d.animate().setDuration(200).alpha(1.0f).setInterpolator(InterpolatorC1217nO.i).setListener(new Mw(yq2)).start();
        } finally {
            try {
                c0683dtX.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
        }
    }

    @Override // defpackage.AbstractC1491sO
    public void e(JO jo) {
        g(false);
        if (AbstractC1251o3.t(this.e)) {
            ModalDialogView modalDialogView = this.e;
            modalDialogView.clearFocus();
            this.d.animate().cancel();
            this.d.animate().setDuration(200).alpha(0.0f).setInterpolator(InterpolatorC1217nO.h).setListener(new Nw(this, modalDialogView)).start();
        }
        MO mo = this.f;
        if (mo != null) {
            mo.b.a.g(mo.d);
            this.f = null;
        }
        this.e = null;
    }

    public void f(WebContents webContents, boolean z) {
        if (!z) {
            if (this.g) {
                this.g = false;
                SelectionPopupControllerImpl.t(webContents).N(true);
                return;
            }
            return;
        }
        SelectionPopupControllerImpl selectionPopupControllerImplT = SelectionPopupControllerImpl.t(webContents);
        selectionPopupControllerImplT.W = true;
        webContents.D().getContainerView().clearFocus();
        selectionPopupControllerImplT.N(false);
        this.g = true;
    }

    public abstract void g(boolean z);
}
