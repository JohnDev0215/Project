package org.chromium.components.browser_ui.widget.selectable_list;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import defpackage.aq1;
import defpackage.bz;
import defpackage.db0;
import defpackage.ii2;
import defpackage.in3;
import defpackage.om3;
import defpackage.qh3;
import defpackage.t41;
import defpackage.uh3;
import defpackage.z3;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import org.chromium.components.browser_ui.widget.NumberRollView;
import org.chromium.components.browser_ui.widget.R$color;
import org.chromium.components.browser_ui.widget.R$dimen;
import org.chromium.components.browser_ui.widget.R$drawable;
import org.chromium.components.browser_ui.widget.R$id;
import org.chromium.components.browser_ui.widget.R$layout;
import org.chromium.components.browser_ui.widget.R$plurals;
import org.chromium.components.browser_ui.widget.R$string;
import org.chromium.components.browser_ui.widget.R$style;
import org.chromium.components.browser_ui.widget.selectable_list.a;

/* JADX INFO: loaded from: classes2.dex */
public class SelectableListToolbar<E> extends Toolbar implements a.InterfaceC0128a<E>, View.OnClickListener, TextView.OnEditorActionListener, db0 {
    public boolean b0;
    public org.chromium.components.browser_ui.widget.selectable_list.a<E> c0;
    public final aq1<Boolean> d0;
    public boolean e0;
    public LinearLayout f0;
    public EditText g0;
    public ImageButton h0;
    public b i0;
    public boolean j0;
    public NumberRollView k0;
    public Drawable l0;
    public int m0;
    public int n0;
    public int o0;
    public int p0;
    public int q0;
    public int r0;
    public ColorStateList s0;
    public qh3 t0;
    public int u0;
    public int v0;
    public int w0;
    public int x0;
    public boolean y0;
    public int z0;

    public interface b {
        void k();

        void r(String str);
    }

    public SelectableListToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        aq1<Boolean> aq1Var = new aq1<>();
        this.d0 = aq1Var;
        aq1Var.r(Boolean.FALSE);
    }

    public final boolean A() {
        return this.d0.f.booleanValue();
    }

    public void B() {
        if (this.e0 && A()) {
            x();
        }
    }

    public void C() {
        n();
        this.z0 = 0;
        getMenu().setGroupVisible(this.p0, true);
        getMenu().setGroupVisible(this.q0, false);
        if (this.e0) {
            this.f0.setVisibility(8);
            G();
        }
        setNavigationButton(0);
        setBackgroundColor(this.r0);
        int i = this.n0;
        if (i != 0) {
            setTitle(i);
        }
        this.k0.setVisibility(8);
        this.k0.c(0, false);
        qh3 qh3Var = this.t0;
        if (qh3Var != null) {
            d(qh3Var.a);
        }
    }

    public final void D(boolean z) {
        this.d0.r(Boolean.TRUE);
        this.c0.b();
        E();
        this.g0.requestFocus();
        if (z) {
            t41.f.i(this.g0);
        }
        setTitle((CharSequence) null);
    }

    public final void E() {
        this.z0 = 2;
        getMenu().setGroupVisible(this.p0, false);
        getMenu().setGroupVisible(this.q0, false);
        this.k0.setVisibility(8);
        this.f0.setVisibility(0);
        setNavigationButton(1);
        setBackgroundResource(R$drawable.search_toolbar_modern_bg);
        qh3 qh3Var = this.t0;
        if (qh3Var != null) {
            d(qh3Var.a);
        }
    }

    public void F(List<E> list, boolean z) {
        this.z0 = 1;
        getMenu().setGroupVisible(this.p0, false);
        getMenu().setGroupVisible(this.q0, true);
        getMenu().setGroupEnabled(this.q0, !list.isEmpty());
        if (this.e0) {
            this.f0.setVisibility(8);
        }
        setNavigationButton(2);
        setBackgroundColor(this.r0);
        setTitle((CharSequence) null);
        this.k0.setVisibility(0);
        if (!z) {
            this.k0.c(0, false);
        }
        this.k0.c(list.size(), true);
        if (A()) {
            t41.f.e(this.g0);
        }
        qh3 qh3Var = this.t0;
        if (qh3Var != null) {
            d(qh3Var.a);
        }
    }

    public final void G() {
        MenuItem menuItemFindItem;
        if (this.e0 && (menuItemFindItem = getMenu().findItem(this.o0)) != null) {
            menuItemFindItem.setVisible((!this.j0 || this.b0 || A()) ? false : true);
        }
    }

    @Override // defpackage.db0
    public final void d(qh3.b bVar) {
        int iH = SelectableListLayout.h(bVar, getResources());
        boolean z = A() && !this.b0;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        int i = bVar.a;
        int i2 = (i != 2 || A() || this.b0 || this.m0 != 0) ? 0 : this.u0;
        if (i == 2 && z) {
            marginLayoutParams.setMargins(iH, marginLayoutParams.topMargin, iH, marginLayoutParams.bottomMargin);
            iH = 0;
        } else {
            marginLayoutParams.setMargins(0, marginLayoutParams.topMargin, 0, marginLayoutParams.bottomMargin);
        }
        setLayoutParams(marginLayoutParams);
        int i3 = this.m0 != 0 ? this.v0 : 0;
        int i4 = this.b0 ? this.w0 : this.x0;
        int i5 = i2 + iH + i3;
        int paddingTop = getPaddingTop();
        int i6 = iH + i4;
        int paddingBottom = getPaddingBottom();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(this, i5, paddingTop, i6, paddingBottom);
    }

    public void f(ArrayList arrayList) {
        boolean z = this.b0;
        this.b0 = this.c0.e();
        if (this.k0 == null) {
            this.k0 = (NumberRollView) findViewById(R$id.selection_mode_number);
        }
        if (this.b0) {
            F(arrayList, z);
        } else if (A()) {
            E();
        } else {
            C();
        }
        if (this.b0) {
            announceForAccessibility(getContext().getString(z ? R$string.accessibility_toolbar_multi_select : R$string.accessibility_toolbar_screen_position, Integer.toString(arrayList.size())));
        }
    }

    public int getCurrentViewType() {
        return this.z0;
    }

    public int getNavigationButtonForTests() {
        return this.m0;
    }

    public View getSearchViewForTests() {
        return this.f0;
    }

    public org.chromium.components.browser_ui.widget.selectable_list.a<E> getSelectionDelegate() {
        return this.c0;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i;
        if (this.y0 || (i = this.m0) == 0) {
            return;
        }
        if (i == 1) {
            B();
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
        if (this.y0) {
            return;
        }
        org.chromium.components.browser_ui.widget.selectable_list.a<E> aVar = this.c0;
        if (aVar != null) {
            aVar.b();
        }
        if (A()) {
            x();
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
        LayoutInflater.from(getContext()).inflate(R$layout.number_roll_view, this);
        NumberRollView numberRollView = (NumberRollView) findViewById(R$id.selection_mode_number);
        this.k0 = numberRollView;
        numberRollView.setString(R$plurals.selected_items);
        this.k0.setStringForZero(R$string.select_items);
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
            r1.m0 = r2
            r1.setNavigationOnClickListener(r1)
            int r2 = r1.m0
            if (r2 == 0) goto L24
            r0 = 1
            if (r2 == r0) goto L1a
            r0 = 2
            if (r2 == r0) goto L10
            goto L24
        L10:
            android.graphics.drawable.Drawable r2 = r1.l0
            android.content.res.ColorStateList r0 = r1.s0
            r2.setTintList(r0)
            int r2 = org.chromium.components.browser_ui.widget.R$string.accessibility_cancel_selection
            goto L25
        L1a:
            android.graphics.drawable.Drawable r2 = r1.l0
            android.content.res.ColorStateList r0 = r1.s0
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
            android.graphics.drawable.Drawable r0 = r1.l0
        L2b:
            r1.setNavigationIcon(r0)
            r1.setNavigationContentDescription(r2)
            qh3 r2 = r1.t0
            if (r2 == 0) goto L3a
            qh3$b r2 = r2.a
            r1.d(r2)
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar.setNavigationButton(int):void");
    }

    public void setSearchEnabled(boolean z) {
        if (this.e0) {
            this.j0 = z;
            G();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
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

    public final void x() {
        if (A()) {
            this.d0.r(Boolean.FALSE);
            this.g0.setText("");
            t41.f.e(this.g0);
            C();
            this.i0.k();
        }
    }

    public void y(org.chromium.components.browser_ui.widget.selectable_list.a<E> aVar, int i, int i2, int i3, boolean z) {
        this.n0 = i;
        this.p0 = i2;
        this.q0 = i3;
        this.c0 = aVar;
        aVar.a(this);
        this.v0 = getResources().getDimensionPixelSize(R$dimen.selectable_list_toolbar_nav_button_start_offset);
        this.w0 = getResources().getDimensionPixelSize(R$dimen.selectable_list_action_bar_end_padding);
        this.x0 = getResources().getDimensionPixelSize(R$dimen.selectable_list_search_icon_end_padding);
        int iA = ii2.a(getContext());
        this.r0 = iA;
        setBackgroundColor(iA);
        Context context = getContext();
        int i4 = R$color.default_icon_color_tint_list;
        this.s0 = bz.b(context, i4);
        Context context2 = getContext();
        int i5 = R$style.TextAppearance_Headline_Primary;
        this.p = i5;
        AppCompatTextView appCompatTextView = this.f;
        if (appCompatTextView != null) {
            appCompatTextView.setTextAppearance(context2, i5);
        }
        int i6 = this.n0;
        if (i6 != 0) {
            setTitle(i6);
        }
        setOverflowIcon(uh3.e(getContext(), R$drawable.ic_more_vert_24dp, R$color.default_icon_color_secondary_tint_list));
        this.l0 = uh3.e(getContext(), R$drawable.ic_arrow_back_white_24dp, i4);
    }

    public void z(b bVar, int i, int i2) {
        this.e0 = true;
        this.i0 = bVar;
        this.o0 = i2;
        LayoutInflater.from(getContext()).inflate(R$layout.search_toolbar, this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.search_view);
        this.f0 = linearLayout;
        EditText editText = (EditText) linearLayout.findViewById(R$id.search_text);
        this.g0 = editText;
        editText.setHint(i);
        this.g0.setOnEditorActionListener(this);
        this.g0.addTextChangedListener(new a());
        ImageButton imageButton = (ImageButton) findViewById(R$id.clear_text_button);
        this.h0 = imageButton;
        imageButton.setOnClickListener(new z3(1, this));
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            SelectableListToolbar selectableListToolbar = SelectableListToolbar.this;
            selectableListToolbar.h0.setVisibility(TextUtils.isEmpty(charSequence) ? 4 : 0);
            if (selectableListToolbar.A()) {
                selectableListToolbar.i0.r(charSequence.toString());
            }
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public void setInfoMenuItem(int i) {
    }
}
