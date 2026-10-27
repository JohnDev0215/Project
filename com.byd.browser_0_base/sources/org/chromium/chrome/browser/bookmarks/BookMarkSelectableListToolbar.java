package org.chromium.chrome.browser.bookmarks;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import defpackage.aq1;
import defpackage.bz;
import defpackage.db0;
import defpackage.in3;
import defpackage.om3;
import defpackage.qh3;
import defpackage.t41;
import defpackage.uh3;
import java.util.ArrayList;
import java.util.WeakHashMap;
import org.chromium.components.browser_ui.widget.R$color;
import org.chromium.components.browser_ui.widget.R$dimen;
import org.chromium.components.browser_ui.widget.R$drawable;
import org.chromium.components.browser_ui.widget.R$string;
import org.chromium.components.browser_ui.widget.R$style;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListLayout;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar;
import org.chromium.components.browser_ui.widget.selectable_list.a;

/* JADX INFO: loaded from: classes.dex */
public class BookMarkSelectableListToolbar<E> extends Toolbar implements a.InterfaceC0128a<E>, View.OnClickListener, TextView.OnEditorActionListener, db0 {
    public boolean b0;
    public org.chromium.components.browser_ui.widget.selectable_list.a<E> c0;
    public final aq1<Boolean> d0;
    public boolean e0;
    public ViewGroup f0;
    public EditText g0;
    public ImageButton h0;
    public SelectableListToolbar.b i0;
    public Drawable j0;
    public int k0;
    public int l0;
    public int m0;
    public ColorStateList n0;
    public int o0;
    public int p0;
    public int q0;
    public boolean r0;
    public int s0;

    public BookMarkSelectableListToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        aq1<Boolean> aq1Var = new aq1<>();
        this.d0 = aq1Var;
        aq1Var.r(Boolean.FALSE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void A(org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar.b r5, int r6, android.view.View.OnFocusChangeListener r7) {
        /*
            r4 = this;
            r0 = 1
            r4.e0 = r0
            r4.i0 = r5
            android.content.Context r5 = r4.getContext()
            android.view.LayoutInflater r5 = android.view.LayoutInflater.from(r5)
            java.lang.String r1 = defpackage.kp2.a
            int r2 = r1.hashCode()
            r3 = 1307376552(0x4decfba8, float:4.9698944E8)
            if (r2 == r3) goto L27
            r3 = 1418153572(0x54874e64, float:4.649086E12)
            if (r2 == r3) goto L1e
            goto L31
        L1e:
            java.lang.String r2 = "8155f_dynasty_def"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L31
            goto L32
        L27:
            java.lang.String r0 = "8155f_denza_def"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L31
            r0 = 0
            goto L32
        L31:
            r0 = -1
        L32:
            if (r0 == 0) goto L37
            int r0 = org.chromium.chrome.R$layout.search_toolbar_bookmark_8155f_dynasty_def
            goto L39
        L37:
            int r0 = org.chromium.chrome.R$layout.search_toolbar_bookmark_denza
        L39:
            r5.inflate(r0, r4)
            int r5 = org.chromium.components.browser_ui.widget.R$id.search_view
            android.view.View r5 = r4.findViewById(r5)
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            r4.f0 = r5
            int r0 = org.chromium.components.browser_ui.widget.R$id.search_text
            android.view.View r5 = r5.findViewById(r0)
            android.widget.EditText r5 = (android.widget.EditText) r5
            r4.g0 = r5
            r5.setHint(r6)
            android.widget.EditText r5 = r4.g0
            r5.setOnEditorActionListener(r4)
            android.widget.EditText r5 = r4.g0
            mf r6 = new mf
            r6.<init>(r4)
            r5.addTextChangedListener(r6)
            android.widget.EditText r5 = r4.g0
            r5.setOnFocusChangeListener(r7)
            int r5 = org.chromium.components.browser_ui.widget.R$id.clear_text_button
            android.view.View r5 = r4.findViewById(r5)
            android.widget.ImageButton r5 = (android.widget.ImageButton) r5
            r4.h0 = r5
            by1 r6 = new by1
            r7 = 5
            r6.<init>(r7, r4)
            r5.setOnClickListener(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar.A(org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar$b, int, android.view.View$OnFocusChangeListener):void");
    }

    public final boolean B() {
        return this.d0.f.booleanValue();
    }

    public final void C() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof TextView) {
                childAt.setFocusable(true);
                if (!(childAt instanceof Button)) {
                    childAt.setFocusableInTouchMode(true);
                }
            }
        }
    }

    public void D() {
        if (this.e0 && B()) {
            y(true);
        }
    }

    public void E() {
        n();
        this.s0 = 0;
        setNavigationButton(0);
        setBackgroundColor(this.m0);
        int i = this.l0;
        if (i != 0) {
            setTitle(i);
        }
    }

    public final void F(boolean z) {
        this.d0.r(Boolean.TRUE);
        this.c0.b();
        this.s0 = 2;
        this.f0.setVisibility(0);
        t41.f.i(this.g0);
        setTitle((CharSequence) null);
    }

    @Override // defpackage.db0
    public final void d(qh3.b bVar) {
        SelectableListLayout.h(bVar, getResources());
        boolean z = B() && !this.b0;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        int i = bVar.a;
        if (i == 2) {
            B();
        }
        if (i != 2 || z) {
            marginLayoutParams.setMargins(0, marginLayoutParams.topMargin, 0, marginLayoutParams.bottomMargin);
        } else {
            marginLayoutParams.setMargins(0, marginLayoutParams.topMargin, 0, marginLayoutParams.bottomMargin);
        }
        setLayoutParams(marginLayoutParams);
        int i2 = this.k0 != 0 ? this.o0 : 0;
        int i3 = this.b0 ? this.p0 : this.q0;
        int paddingBottom = getPaddingBottom();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(this, i2 + 0, getPaddingTop(), i3 + 0, paddingBottom);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public void f(ArrayList arrayList) {
        boolean z = this.b0;
        boolean zE = this.c0.e();
        this.b0 = zE;
        if (zE) {
            this.s0 = 1;
            setNavigationButton(2);
            setBackgroundColor(this.m0);
            setTitle((CharSequence) null);
            if (B()) {
                x();
            }
        } else if (B()) {
            this.s0 = 2;
            this.f0.setVisibility(0);
        } else {
            E();
        }
        if (this.b0) {
            announceForAccessibility(getContext().getString(z ? R$string.accessibility_toolbar_multi_select : R$string.accessibility_toolbar_screen_position, Integer.toString(arrayList.size())));
        }
    }

    public int getCurrentViewType() {
        return this.s0;
    }

    public int getNavigationButtonForTests() {
        return this.k0;
    }

    public View getSearchViewForTests() {
        return this.f0;
    }

    org.chromium.components.browser_ui.widget.selectable_list.a<E> getSelectionDelegate() {
        return this.c0;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i;
        if (this.r0 || (i = this.k0) == 0) {
            return;
        }
        if (i == 1) {
            D();
        } else {
            if (i != 2) {
                return;
            }
            this.c0.b();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.r0) {
            return;
        }
        org.chromium.components.browser_ui.widget.selectable_list.a<E> aVar = this.c0;
        if (aVar != null) {
            aVar.b();
        }
        if (B()) {
            y(true);
        }
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 3) {
            return false;
        }
        t41.f.e(textView);
        return false;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setNavigationButton(int r2) {
        /*
            r1 = this;
            r1.k0 = r2
            r1.setNavigationOnClickListener(r1)
            int r2 = r1.k0
            if (r2 == 0) goto L24
            r0 = 1
            if (r2 == r0) goto L1a
            r0 = 2
            if (r2 == r0) goto L10
            goto L24
        L10:
            android.graphics.drawable.Drawable r2 = r1.j0
            android.content.res.ColorStateList r0 = r1.n0
            r2.setTintList(r0)
            int r2 = org.chromium.components.browser_ui.widget.R$string.accessibility_cancel_selection
            goto L25
        L1a:
            android.graphics.drawable.Drawable r2 = r1.j0
            android.content.res.ColorStateList r0 = r1.n0
            r2.setTintList(r0)
            int r2 = org.chromium.components.browser_ui.widget.R$string.accessibility_toolbar_btn_back
            goto L25
        L24:
            r2 = 0
        L25:
            if (r2 != 0) goto L29
            r0 = 0
            goto L2b
        L29:
            android.graphics.drawable.Drawable r0 = r1.j0
        L2b:
            r1.setNavigationIcon(r0)
            r1.setNavigationContentDescription(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar.setNavigationButton(int):void");
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        C();
    }

    public final void x() {
        if (this.g0.hasFocus()) {
            this.g0.clearFocus();
            t41.f.e(this.g0);
        }
    }

    public final void y(boolean z) {
        if (B()) {
            this.d0.r(Boolean.FALSE);
            this.g0.setText("");
            x();
            E();
            if (z) {
                this.i0.k();
            }
        }
    }

    public void z(org.chromium.components.browser_ui.widget.selectable_list.a<E> aVar, int i, int i2, int i3, boolean z) {
        this.l0 = 0;
        this.c0 = aVar;
        aVar.a(this);
        this.o0 = getResources().getDimensionPixelSize(R$dimen.selectable_list_toolbar_nav_button_start_offset);
        this.p0 = getResources().getDimensionPixelSize(R$dimen.selectable_list_action_bar_end_padding);
        this.q0 = getResources().getDimensionPixelSize(R$dimen.selectable_list_search_icon_end_padding);
        this.m0 = R.color.transparent;
        setBackgroundColor(R.color.transparent);
        this.n0 = bz.b(getContext(), R$color.default_icon_color_tint_list);
        Context context = getContext();
        int i4 = R$style.TextAppearance_Headline_Primary;
        this.p = i4;
        AppCompatTextView appCompatTextView = this.f;
        if (appCompatTextView != null) {
            appCompatTextView.setTextAppearance(context, i4);
        }
        int i5 = this.l0;
        if (i5 != 0) {
            setTitle(i5);
        }
        setOverflowIcon(uh3.e(getContext(), R$drawable.ic_more_vert_24dp, R$color.default_icon_color_secondary_tint_list));
        this.j0 = uh3.f(getContext(), org.chromium.chrome.R$drawable.ic_back, this.n0);
    }

    public void setInfoMenuItem(int i) {
    }

    public void setSearchEnabled(boolean z) {
    }
}
