package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import defpackage.ii2;
import defpackage.oj2;
import defpackage.za3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
public class TabSelectionEditorToolbar extends SelectableListToolbar<Integer> {
    public static final List<Integer> I0 = Collections.emptyList();
    public final Context A0;
    public Button B0;
    public ChromeImageButton C0;
    public TabSelectionEditorActionViewLayout D0;
    public Integer E0;
    public int F0;
    public int G0;
    public a H0;

    public interface a {
        int a(ArrayList arrayList);
    }

    public TabSelectionEditorToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.G0 = 2;
        this.A0 = context;
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar
    public final void C() {
        F(I0, true);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar
    public final void F(List<Integer> list, boolean z) {
        super.F(list, z);
        int i = this.F0;
        if (i != 0) {
            setBackgroundColor(i);
        }
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar, org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        super.f(arrayList);
        int size = arrayList.size();
        boolean z = size >= this.G0;
        this.B0.setEnabled(z);
        this.B0.setContentDescription((!z || this.E0 == null) ? null : getContext().getResources().getQuantityString(this.E0.intValue(), size, Integer.valueOf(size)));
        a aVar = this.H0;
        if (aVar == null) {
            return;
        }
        this.k0.c(aVar.a(arrayList), true);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        za3 za3VarA = za3.a(getContext(), R$drawable.ic_arrow_back_white_24dp);
        za3VarA.setTint(ii2.d(getContext()));
        setNavigationIcon(za3VarA);
        setNavigationContentDescription(R$string.accessibility_tab_selection_editor_back_button);
        this.D0 = (TabSelectionEditorActionViewLayout) findViewById(R$id.action_view_layout);
        this.B0 = (Button) findViewById(R$id.action_button);
        this.C0 = (ChromeImageButton) findViewById(R$id.list_menu_button);
        this.k0.setStringForZero(R$string.tab_selection_editor_toolbar_select_tabs);
        if (oj2.E(this.A0)) {
            this.k0.setString(R$plurals.tab_selection_editor_tabs_count);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2, 0.0f);
        layoutParams.gravity = 16;
        ((ViewGroup) this.k0.getParent()).removeView(this.k0);
        this.D0.addView(this.k0, 0, layoutParams);
    }

    public void setActionButtonOnClickListener(View.OnClickListener onClickListener) {
        this.B0.setOnClickListener(onClickListener);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar
    public final void setNavigationButton(int i) {
    }
}
