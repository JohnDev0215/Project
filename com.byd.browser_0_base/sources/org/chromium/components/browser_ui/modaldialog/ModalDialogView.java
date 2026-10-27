package org.chromium.components.browser_ui.modaldialog;

import J.N;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.a8;
import defpackage.me0;
import defpackage.uh3;
import defpackage.yp;
import org.chromium.base.Callback;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;
import org.chromium.components.browser_ui.widget.BoundedLinearLayout;
import org.chromium.components.browser_ui.widget.FadingEdgeScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class ModalDialogView extends BoundedLinearLayout implements View.OnClickListener {
    public static final /* synthetic */ int D = 0;
    public TextView A;
    public long B;
    public long C;
    public FadingEdgeScrollView j;
    public ViewGroup k;
    public TextView l;
    public ImageView m;
    public TextView n;
    public TextView o;
    public ViewGroup p;
    public ViewGroup q;
    public View r;
    public Button s;
    public Button t;
    public Callback<Integer> u;
    public boolean v;
    public boolean w;
    public boolean x;
    public Runnable y;
    public ViewGroup z;

    public ModalDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.B = -1L;
    }

    public final Button a(int i) {
        if (i == 0) {
            return this.s;
        }
        if (i != 1) {
            return null;
        }
        return this.t;
    }

    public final void b() {
        boolean z = !TextUtils.isEmpty(this.s.getText());
        boolean z2 = !TextUtils.isEmpty(this.t.getText());
        boolean z3 = (z || z2) && !(this.q.getVisibility() == 0);
        this.s.setVisibility(z ? 0 : 8);
        this.t.setVisibility(z2 ? 0 : 8);
        this.r.setVisibility(z3 ? 0 : 8);
    }

    public final void c() {
        boolean z = !TextUtils.isEmpty(this.l.getText());
        boolean z2 = this.m.getDrawable() != null;
        boolean z3 = z || z2;
        boolean z4 = !TextUtils.isEmpty(this.n.getText());
        boolean z5 = !TextUtils.isEmpty(this.o.getText());
        boolean z6 = (this.v && z3) || z4 || z5;
        boolean zIsEmpty = true ^ TextUtils.isEmpty(this.A.getText());
        this.l.setVisibility(z ? 0 : 8);
        this.m.setVisibility(z2 ? 0 : 8);
        this.k.setVisibility(z3 ? 0 : 8);
        this.n.setVisibility(z4 ? 0 : 8);
        this.j.setVisibility(z6 ? 0 : 8);
        this.o.setVisibility(z5 ? 0 : 8);
        this.z.setVisibility(zIsEmpty ? 0 : 8);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if ((a8.Q() && N.Mu4YBtQj("ModalDialogButtonProtection")) && this.C != 0 && (this.B < 0 || SystemClock.elapsedRealtime() <= this.B + this.C)) {
            return;
        }
        if (view == this.s) {
            this.u.onResult(0);
        } else if (view == this.t) {
            this.u.onResult(1);
        } else if (view == this.m) {
            this.u.onResult(2);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.j = (FadingEdgeScrollView) findViewById(R$id.modal_dialog_scroll_view);
        ViewGroup viewGroup = (ViewGroup) findViewById(R$id.title_container);
        this.k = viewGroup;
        this.l = (TextView) viewGroup.findViewById(R$id.title);
        this.m = (ImageView) this.k.findViewById(R$id.title_icon);
        this.n = (TextView) findViewById(R$id.message_paragraph_1);
        this.o = (TextView) findViewById(R$id.message_paragraph_2);
        this.p = (ViewGroup) findViewById(R$id.custom);
        this.q = (ViewGroup) findViewById(R$id.custom_button_bar);
        this.r = findViewById(R$id.button_bar);
        this.s = (Button) findViewById(R$id.positive_button);
        this.t = (Button) findViewById(R$id.negative_button);
        this.z = (ViewGroup) findViewById(R$id.footer);
        this.A = (TextView) findViewById(R$id.footer_message);
        this.s.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.n.setMovementMethod(LinkMovementMethod.getInstance());
        this.A.setMovementMethod(LinkMovementMethod.getInstance());
        this.z.setBackgroundColor(yp.c(getContext(), R$dimen.default_elevation_1));
        c();
        b();
        this.j.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: ei1
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                int i9 = ModalDialogView.D;
                boolean z = true;
                if (!view.canScrollVertically(-1) && !view.canScrollVertically(1)) {
                    z = false;
                }
                view.setFocusable(z);
            }
        });
    }

    public void setButtonTapProtectionDurationMs(long j) {
        this.C = j;
    }

    public void setCustomButtonBar(View view) {
        if (this.q.getChildCount() > 0) {
            this.q.removeAllViews();
        }
        if (view != null) {
            uh3.l(view);
            this.q.addView(view);
            this.q.setVisibility(0);
        } else {
            this.q.setVisibility(8);
        }
        b();
    }

    public void setCustomView(View view) {
        if (this.p.getChildCount() > 0) {
            this.p.removeAllViews();
        }
        if (view == null) {
            this.p.setVisibility(8);
            return;
        }
        uh3.l(view);
        this.p.addView(view);
        this.p.setVisibility(0);
    }

    public void setFilterTouchForSecurity(boolean z) {
        if (this.w == z) {
            return;
        }
        this.w = z;
        if (z) {
            Button buttonA = a(0);
            Button buttonA2 = a(1);
            me0 me0Var = new me0(2, this);
            buttonA.setFilterTouchesWhenObscured(true);
            buttonA.setOnTouchListener(me0Var);
            buttonA2.setFilterTouchesWhenObscured(true);
            buttonA2.setOnTouchListener(me0Var);
        }
    }

    public void setFooterMessage(CharSequence charSequence) {
        this.A.setText(charSequence);
        c();
    }

    public void setMessageParagraph1(CharSequence charSequence) {
        this.n.setText(charSequence);
        c();
    }

    public void setMessageParagraph2(CharSequence charSequence) {
        this.o.setText(charSequence);
        c();
    }

    public void setOnButtonClickedCallback(Callback<Integer> callback) {
        this.u = callback;
    }

    public void setOnTouchFilteredCallback(Runnable runnable) {
        this.y = runnable;
    }

    public void setPositiveButtonIcon(Drawable drawable) {
        Button buttonA = a(0);
        buttonA.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        buttonA.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R$dimen.modal_dialog_button_with_icon_text_padding));
        buttonA.setPaddingRelative(getResources().getDimensionPixelSize(R$dimen.modal_dialog_button_with_icon_start_padding), buttonA.getPaddingTop(), buttonA.getPaddingEnd(), buttonA.getPaddingBottom());
        b();
    }

    public void setTitle(CharSequence charSequence) {
        this.l.setText(charSequence);
        c();
    }

    public void setTitleIcon(Drawable drawable) {
        this.m.setImageDrawable(drawable);
        c();
        if (drawable != null) {
            this.m.setOnClickListener(this);
        }
    }

    public void setTitleMaxLines(int i) {
        this.l.setMaxLines(i);
    }

    public void setTitleScrollable(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        CharSequence text = this.l.getText();
        Drawable drawable = this.m.getDrawable();
        this.k.setVisibility(8);
        ViewGroup viewGroup = (ViewGroup) findViewById(z ? R$id.scrollable_title_container : R$id.title_container);
        this.k = viewGroup;
        this.l = (TextView) viewGroup.findViewById(R$id.title);
        this.m = (ImageView) this.k.findViewById(R$id.title_icon);
        setTitle(text);
        setTitleIcon(drawable);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.p.getLayoutParams();
        if (z) {
            layoutParams.height = -2;
            layoutParams.weight = 0.0f;
            FadingEdgeScrollView fadingEdgeScrollView = this.j;
            fadingEdgeScrollView.h = 1;
            fadingEdgeScrollView.i = 1;
            fadingEdgeScrollView.invalidate();
        } else {
            layoutParams.height = 0;
            layoutParams.weight = 1.0f;
            FadingEdgeScrollView fadingEdgeScrollView2 = this.j;
            fadingEdgeScrollView2.h = 0;
            fadingEdgeScrollView2.i = 0;
            fadingEdgeScrollView2.invalidate();
        }
        this.p.setLayoutParams(layoutParams);
    }
}
