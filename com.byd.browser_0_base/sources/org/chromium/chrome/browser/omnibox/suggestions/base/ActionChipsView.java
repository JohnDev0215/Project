package org.chromium.chrome.browser.omnibox.suggestions.base;

import android.content.Context;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.b2;
import defpackage.n3;
import org.chromium.chrome.R$dimen;

/* JADX INFO: loaded from: classes2.dex */
public class ActionChipsView extends RecyclerView {
    public b2 P0;

    public class a extends RecyclerView.l {
        public final /* synthetic */ int a;

        public a(int i) {
            this.a = i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l
        public final void g(Rect rect, View view, RecyclerView recyclerView, RecyclerView.w wVar) {
            int i = this.a;
            rect.right = i / 2;
            rect.left = i / 2;
        }
    }

    public ActionChipsView(Context context) {
        super(context);
        setItemAnimator(null);
        setLayoutManager(new LinearLayoutManager(0));
        setMinimumHeight(getResources().getDimensionPixelSize(R$dimen.omnibox_action_chips_container_height));
        g(new a(getResources().getDimensionPixelSize(R$dimen.omnibox_action_chip_spacing)));
        setPaddingRelative(0, 0, 0, getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_content_padding));
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.P0 == null) {
            return false;
        }
        if (keyEvent.getKeyCode() != 61) {
            if (n3.I0(keyEvent)) {
                b2 b2Var = this.P0;
                View viewU = b2Var.k.u(b2Var.j);
                if (viewU != null) {
                    return viewU.performClick();
                }
            }
            return super.onKeyDown(i, keyEvent);
        }
        if (keyEvent.isShiftPressed()) {
            b2 b2Var2 = this.P0;
            int i2 = b2Var2.j;
            int iK = i2 > 1 ? i2 - 1 : -1;
            if (i2 == -1) {
                iK = b2Var2.k() - 1;
            }
            b2Var2.I(iK);
        } else {
            b2 b2Var3 = this.P0;
            int i3 = b2Var3.j;
            int i4 = i3 != -1 ? i3 + 1 : 1;
            b2Var3.I(i4 < b2Var3.k() ? i4 : -1);
        }
        return true;
    }

    public void setAdapter(b2 b2Var) {
        super.setAdapter((RecyclerView.e) b2Var);
        this.P0 = b2Var;
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        b2 b2Var = this.P0;
        if (b2Var != null) {
            b2Var.I(-1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public b2 getAdapter() {
        return this.P0;
    }
}
