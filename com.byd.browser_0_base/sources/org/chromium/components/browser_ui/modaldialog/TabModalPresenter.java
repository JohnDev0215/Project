package org.chromium.components.browser_ui.modaldialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.SystemClock;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import defpackage.bi1;
import defpackage.di1;
import defpackage.fi1;
import defpackage.gs;
import defpackage.in3;
import defpackage.n13;
import defpackage.om3;
import defpackage.pc;
import defpackage.v62;
import defpackage.z7;
import java.util.WeakHashMap;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: loaded from: classes2.dex */
public abstract class TabModalPresenter extends bi1.b {
    public final Context g;
    public ViewGroup h;
    public ModalDialogView i;
    public j<PropertyModel, ModalDialogView, v62> j;
    public boolean k;
    public boolean l;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            TabModalPresenter.this.m(true);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            ModalDialogView modalDialogView = TabModalPresenter.this.i;
            long duration = animator.getDuration();
            modalDialogView.getClass();
            modalDialogView.B = (duration / 2) + SystemClock.elapsedRealtime();
        }
    }

    public class b extends fi1 {
        public b() {
        }

        @Override // defpackage.fi1
        /* JADX INFO: renamed from: a */
        public final void b(PropertyModel propertyModel, ModalDialogView modalDialogView, v62 v62Var) {
            PropertyModel.n nVar = di1.q;
            TabModalPresenter tabModalPresenter = TabModalPresenter.this;
            if (nVar == v62Var) {
                if (propertyModel.k(nVar)) {
                    tabModalPresenter.h.setOnClickListener(new gs(15, this));
                    return;
                } else {
                    tabModalPresenter.h.setOnClickListener(null);
                    return;
                }
            }
            PropertyModel.n nVar2 = di1.x;
            if (nVar2 != v62Var) {
                super.b(propertyModel, modalDialogView, v62Var);
            } else if (propertyModel.k(nVar2)) {
                tabModalPresenter.l = true;
            }
        }
    }

    public TabModalPresenter(Context context) {
        this.g = context;
    }

    @Override // bi1.b
    public final void b(PropertyModel propertyModel) {
        if (this.h == null) {
            this.h = f();
        }
        int i = R$style.ThemeOverlay_BrowserUI_ModalDialog_TextPrimaryButton;
        int iH = propertyModel.h(di1.u);
        if (iH == 1) {
            i = R$style.ThemeOverlay_BrowserUI_ModalDialog_FilledPrimaryButton;
        } else if (iH == 2) {
            i = R$style.ThemeOverlay_BrowserUI_ModalDialog_FilledNegativeButton;
        }
        ModalDialogView modalDialogView = (ModalDialogView) z7.J(R$layout.modal_dialog_view, new ContextThemeWrapper(this.g, i));
        this.i = modalDialogView;
        this.j = j.b(propertyModel, modalDialogView, new b());
        i(true);
        k();
    }

    @Override // bi1.b
    public void e(PropertyModel propertyModel) {
        i(false);
        ModalDialogView modalDialogView = this.i;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (om3.g.b(modalDialogView)) {
            ModalDialogView modalDialogView2 = this.i;
            modalDialogView2.clearFocus();
            this.h.animate().cancel();
            this.h.animate().setDuration(200L).alpha(0.0f).setInterpolator(pc.d).setListener(new n13(this, modalDialogView2)).start();
        } else {
            this.h.animate().cancel();
        }
        j<PropertyModel, ModalDialogView, v62> jVar = this.j;
        if (jVar != null) {
            jVar.c();
            this.j = null;
        }
        this.i = null;
    }

    public abstract ViewGroup f();

    public final void g() {
        this.h.animate().cancel();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 17);
        this.i.setBackgroundResource(R$drawable.dialog_bg_no_shadow);
        this.h.addView(this.i, layoutParams);
        this.h.setAlpha(0.0f);
        this.h.setVisibility(0);
        this.h.animate().setDuration(200L).alpha(1.0f).setInterpolator(pc.e).setListener(new a()).start();
    }

    public final void h(WebContents webContents, boolean z) {
        if (!z) {
            if (this.k) {
                this.k = false;
                SelectionPopupControllerImpl.j(webContents).x(true);
                return;
            }
            return;
        }
        SelectionPopupControllerImpl selectionPopupControllerImplJ = SelectionPopupControllerImpl.j(webContents);
        selectionPopupControllerImplJ.f90J = true;
        webContents.u().getContainerView().clearFocus();
        selectionPopupControllerImplJ.x(false);
        this.k = true;
    }

    public abstract void i(boolean z);

    public abstract void k();

    public void m(boolean z) {
        if (!z) {
            this.i.clearFocus();
            this.i.setImportantForAccessibility(4);
            return;
        }
        ModalDialogView modalDialogView = this.i;
        PropertyModel propertyModel = this.f;
        String str = (String) propertyModel.j(di1.b);
        if (str == null) {
            str = (String) propertyModel.j(di1.c);
        }
        modalDialogView.announceForAccessibility(str);
        this.i.setImportantForAccessibility(1);
        this.i.requestFocus();
        if (this.l) {
            this.i.sendAccessibilityEvent(8);
        }
    }
}
