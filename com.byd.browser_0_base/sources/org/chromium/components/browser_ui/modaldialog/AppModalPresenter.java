package org.chromium.components.browser_ui.modaldialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.view.WindowManager;
import androidx.activity.ComponentDialog;
import androidx.activity.OnBackPressedDispatcher;
import defpackage.bi1;
import defpackage.di1;
import defpackage.fi1;
import defpackage.i21;
import defpackage.ku2;
import defpackage.v62;
import defpackage.xr1;
import defpackage.z7;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: loaded from: classes2.dex */
public class AppModalPresenter extends bi1.b {
    public final Context g;
    public ComponentDialog h;
    public j<PropertyModel, ModalDialogView, v62> i;

    public class a extends fi1 {
        public a() {
        }

        @Override // defpackage.fi1
        /* JADX INFO: renamed from: a */
        public final void b(PropertyModel propertyModel, ModalDialogView modalDialogView, v62 v62Var) {
            PropertyModel.n nVar = di1.q;
            AppModalPresenter appModalPresenter = AppModalPresenter.this;
            if (nVar == v62Var) {
                appModalPresenter.h.setCanceledOnTouchOutside(propertyModel.k(nVar));
                return;
            }
            PropertyModel.r<xr1> rVar = di1.z;
            if (rVar != v62Var) {
                super.b(propertyModel, modalDialogView, v62Var);
                return;
            }
            OnBackPressedDispatcher onBackPressedDispatcher = appModalPresenter.h.g;
            xr1 xr1Var = (xr1) propertyModel.j(rVar);
            onBackPressedDispatcher.getClass();
            i21.e(xr1Var, "onBackPressedCallback");
            onBackPressedDispatcher.b(xr1Var);
        }
    }

    public AppModalPresenter(Context context) {
        this.g = context;
    }

    @Override // bi1.b
    public final void b(PropertyModel propertyModel) {
        int[][] iArr = {new int[]{R$style.ThemeOverlay_BrowserUI_ModalDialog_TextPrimaryButton, R$style.ThemeOverlay_BrowserUI_ModalDialog_TextPrimaryButton_Fullscreen, R$style.ThemeOverlay_BrowserUI_ModalDialog_TextPrimaryButton_DialogWhenLarge}, new int[]{R$style.ThemeOverlay_BrowserUI_ModalDialog_FilledPrimaryButton, R$style.ThemeOverlay_BrowserUI_ModalDialog_FilledPrimaryButton_Fullscreen, R$style.ThemeOverlay_BrowserUI_ModalDialog_FilledPrimaryButton_DialogWhenLarge}, new int[]{R$style.ThemeOverlay_BrowserUI_ModalDialog_FilledNegativeButton, R$style.ThemeOverlay_BrowserUI_ModalDialog_FilledNegativeButton_Fullscreen, R$style.ThemeOverlay_BrowserUI_ModalDialog_FilledNegativeButton_DialogWhenLarge}};
        char c = propertyModel.k(di1.v) ? (char) 1 : propertyModel.k(di1.w) ? (char) 2 : (char) 0;
        int iH = propertyModel.h(di1.u);
        ComponentDialog componentDialog = new ComponentDialog(this.g, iArr[iH != 1 ? iH == 2 ? (char) 2 : (char) 0 : (char) 1][c]);
        this.h = componentDialog;
        componentDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: s7
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.e.d(5);
            }
        });
        this.h.setCanceledOnTouchOutside(false);
        final ModalDialogView modalDialogView = (ModalDialogView) z7.J(R$layout.modal_dialog_view, this.h.getContext());
        this.i = j.b(propertyModel, modalDialogView, new a());
        ku2 ku2VarT = ku2.t();
        try {
            this.h.setContentView(modalDialogView);
            ku2VarT.close();
            this.h.setOnShowListener(new DialogInterface.OnShowListener() { // from class: t7
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    ModalDialogView modalDialogView2 = modalDialogView;
                    modalDialogView2.getClass();
                    modalDialogView2.B = SystemClock.elapsedRealtime() + 100;
                }
            });
            try {
                this.h.show();
                String str = (String) propertyModel.j(di1.b);
                if (str == null) {
                    str = (String) propertyModel.j(di1.c);
                }
                modalDialogView.announceForAccessibility(str);
            } catch (WindowManager.BadTokenException unused) {
                d(9);
            }
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused2) {
            }
            throw th;
        }
    }

    @Override // bi1.b
    public final void e(PropertyModel propertyModel) {
        j<PropertyModel, ModalDialogView, v62> jVar = this.i;
        if (jVar != null) {
            jVar.c();
            this.i = null;
        }
        ComponentDialog componentDialog = this.h;
        if (componentDialog != null) {
            componentDialog.dismiss();
            this.h = null;
        }
    }
}
