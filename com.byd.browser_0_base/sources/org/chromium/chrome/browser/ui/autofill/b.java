package org.chromium.chrome.browser.ui.autofill;

import J.N;
import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.bi1;
import defpackage.di1;
import defpackage.s9;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ui.autofill.a;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AuthenticatorSelectionDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements a.b {
    public final a a = new a();
    public final Context b;
    public final InterfaceC0118b c;
    public final bi1 d;
    public View e;
    public View f;
    public RecyclerView g;
    public PropertyModel h;
    public s9 i;

    /* JADX INFO: compiled from: AuthenticatorSelectionDialog.java */
    public class a implements di1.a {
        public a() {
        }

        @Override // di1.a
        public final void b(int i) {
            N.MJ3HnZb0(((AuthenticatorSelectionDialogBridge) b.this.c).a);
        }

        @Override // di1.a
        public final void c(int i, PropertyModel propertyModel) {
            b bVar = b.this;
            if (i != 0) {
                if (i != 1) {
                    return;
                }
                bVar.d.b(2, propertyModel);
                return;
            }
            InterfaceC0118b interfaceC0118b = bVar.c;
            N.MBwHQwOl(((AuthenticatorSelectionDialogBridge) interfaceC0118b).a, bVar.i.b);
            bVar.e.setVisibility(0);
            bVar.e.setAlpha(0.0f);
            bVar.e.animate().alpha(1.0f).setDuration(250L);
            bVar.f.animate().alpha(0.0f).setDuration(250L);
            bVar.h.l(di1.l, true);
        }
    }

    /* JADX INFO: renamed from: org.chromium.chrome.browser.ui.autofill.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AuthenticatorSelectionDialog.java */
    public interface InterfaceC0118b {
    }

    public b(Context context, InterfaceC0118b interfaceC0118b, bi1 bi1Var) {
        this.b = context;
        this.c = interfaceC0118b;
        this.d = bi1Var;
    }

    public final String a(int i) {
        if (i == 0) {
            return "";
        }
        Context context = this.b;
        return i != 1 ? i != 2 ? "" : context.getResources().getString(R$string.autofill_card_unmask_authentication_selection_dialog_ok_button_label_continue) : context.getResources().getString(R$string.autofill_card_unmask_authentication_selection_dialog_ok_button_label_send);
    }
}
