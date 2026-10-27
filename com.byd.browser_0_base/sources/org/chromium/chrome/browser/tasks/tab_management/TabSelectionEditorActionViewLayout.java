package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import defpackage.k33;
import defpackage.l81;
import defpackage.q8;
import java.util.ArrayList;
import org.chromium.chrome.R$id;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* JADX INFO: loaded from: classes2.dex */
public class TabSelectionEditorActionViewLayout extends LinearLayout {
    public final ArrayList<k33> e;
    public final q8 f;
    public ListMenuButton g;
    public final LinearLayout.LayoutParams h;
    public final Context i;
    public a j;
    public boolean k;

    public interface a {
    }

    public TabSelectionEditorActionViewLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new ArrayList<>();
        this.f = new q8(0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2, 0.0f);
        this.h = layoutParams;
        layoutParams.gravity = 16;
        this.i = context;
    }

    public final void a() {
        for (k33 k33Var : this.e) {
            if (this == k33Var.c.getParent()) {
                removeView(k33Var.c);
            }
        }
    }

    public ListMenuButton getListMenuButtonForTesting() {
        return this.g;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        ListMenuButton listMenuButton = (ListMenuButton) findViewById(R$id.list_menu_button);
        this.g = listMenuButton;
        listMenuButton.n = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x010c  */
    @Override // android.widget.LinearLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tasks.tab_management.TabSelectionEditorActionViewLayout.onMeasure(int, int):void");
    }

    public void setActionViewLayoutDelegate(a aVar) {
        this.j = aVar;
    }

    public void setHasMenuOnlyItems(boolean z) {
        this.k = z;
        measure(TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED, Integer.MIN_VALUE);
    }

    public void setListMenuButtonDelegate(l81 l81Var) {
        this.g.setDelegate(l81Var);
    }
}
