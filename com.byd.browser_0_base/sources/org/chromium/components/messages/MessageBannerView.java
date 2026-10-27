package org.chromium.components.messages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.bz;
import defpackage.dx2;
import defpackage.gs;
import defpackage.l81;
import defpackage.qc2;
import org.chromium.base.SysUtils;
import org.chromium.components.browser_ui.widget.BoundedLinearLayout;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;
import org.chromium.components.browser_ui.widget.text.TextViewWithCompoundDrawables;

/* JADX INFO: loaded from: classes3.dex */
public class MessageBannerView extends BoundedLinearLayout {
    public ImageView j;
    public TextView k;
    public TextViewWithCompoundDrawables l;
    public int m;
    public TextView n;
    public View o;
    public ListMenuButton p;
    public View q;
    public String r;
    public Runnable s;
    public l81 t;
    public a u;
    public Runnable v;
    public int w;
    public ListMenuButton.a x;
    public Drawable y;

    public class a extends dx2 {
        public a(Context context, dx2.a aVar) {
            super(context, aVar);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onDown(MotionEvent motionEvent) {
            return true;
        }
    }

    public MessageBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = 0;
        this.w = -1;
    }

    public final void a(boolean z) {
        if (this.y != null) {
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R$dimen.message_description_icon_size);
            if (z) {
                this.l.setDrawableWidth((this.y.getIntrinsicWidth() * dimensionPixelOffset) / this.y.getIntrinsicHeight());
            } else {
                this.l.setDrawableWidth(dimensionPixelOffset);
            }
            this.l.setCompoundDrawablesRelative(this.y, null, null, null);
        }
    }

    public final void b() {
        TextView textView = this.n;
        textView.setVisibility((this.m != 0 || TextUtils.isEmpty(textView.getText())) ? 8 : 0);
        this.o.setVisibility(this.m != 1 ? 8 : 0);
    }

    public int getDescriptionHeightForAnimation() {
        return this.l.getHeight();
    }

    public int getDescriptionMeasuredHeightForAnimation() {
        return this.l.getMeasuredHeight();
    }

    public int getPrimaryButtonLineCountForAnimation() {
        return this.n.getLineCount();
    }

    public int getTitleHeightForAnimation() {
        return this.k.getHeight();
    }

    public int getTitleMeasuredHeightForAnimation() {
        return this.k.getMeasuredHeight();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.k = (TextView) findViewById(R$id.message_title);
        this.l = (TextViewWithCompoundDrawables) findViewById(R$id.message_description);
        this.n = (TextView) findViewById(R$id.message_primary_button);
        this.o = findViewById(R$id.message_primary_progress_spinner);
        this.j = (ImageView) findViewById(R$id.message_icon);
        this.p = (ListMenuButton) findViewById(R$id.message_secondary_button);
        this.q = findViewById(R$id.message_divider);
        this.p.setOnClickListener(new gs(18, this));
        ((LinearLayout) findViewById(R$id.message_main_content)).getLayoutTransition().enableTransitionType(4);
        if (SysUtils.isLowEndDevice()) {
            setBackgroundResource(R$drawable.popup_bg);
        }
    }

    @Override // org.chromium.components.browser_ui.widget.BoundedLinearLayout, android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        this.n.setMinHeight(0);
        super.onMeasure(i, i2);
        int measuredHeight = getMeasuredHeight();
        this.n.measure(View.MeasureSpec.makeMeasureSpec(this.n.getMeasuredWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.n.setMinHeight(Math.max(this.n.getMeasuredHeight(), measuredHeight));
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        a aVar = this.u;
        return aVar != null ? aVar.a(motionEvent) || super.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
    }

    public void setDescriptionIcon(Drawable drawable) {
        this.l.setVisibility(drawable == null ? 8 : 0);
        this.y = drawable;
        this.l.setDrawableTintColor(bz.b(getContext(), R$color.default_icon_color_secondary_tint_list));
        this.l.setCompoundDrawablesRelative(drawable, null, null, null);
    }

    public void setDescriptionMaxLines(int i) {
        this.l.setMaxLines(i);
        this.l.setEllipsize(TextUtils.TruncateAt.END);
    }

    public void setDescriptionText(CharSequence charSequence) {
        this.l.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        this.l.setText(charSequence);
    }

    public void setIcon(Drawable drawable) {
        this.j.setImageDrawable(drawable);
        int i = this.w;
        if (i >= 0) {
            setIconCornerRadius(i);
        }
    }

    public void setIconCornerRadius(int i) {
        this.w = i;
        if (this.j.getDrawable() instanceof BitmapDrawable) {
            qc2 qc2Var = new qc2(getResources(), ((BitmapDrawable) this.j.getDrawable()).getBitmap());
            qc2Var.b(i);
            this.j.setImageDrawable(qc2Var);
        }
    }

    public void setIconTint(int i) {
        if (i == 0) {
            this.j.setImageTintList(null);
        } else {
            this.j.setImageTintList(ColorStateList.valueOf(i));
        }
    }

    public void setMarginTop(int i) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.topMargin = i;
        setLayoutParams(layoutParams);
    }

    public void setOnTitleChanged(Runnable runnable) {
        this.v = runnable;
    }

    public void setPopupMenuShownListener(ListMenuButton.a aVar) {
        this.x = aVar;
    }

    public void setPrimaryButtonClickListener(View.OnClickListener onClickListener) {
        this.n.setOnClickListener(onClickListener);
    }

    public void setPrimaryButtonText(String str) {
        this.n.setText(str);
        b();
    }

    public void setPrimaryWidgetAppearance(int i) {
        this.m = i;
        b();
    }

    public void setSecondaryActionCallback(Runnable runnable) {
        this.p.b();
        this.s = runnable;
    }

    public void setSecondaryButtonMenuText(String str) {
        this.p.b();
        this.r = str;
    }

    public void setSecondaryIcon(Drawable drawable) {
        this.p.setImageDrawable(drawable);
        this.p.setVisibility(0);
        this.q.setVisibility(0);
    }

    public void setSecondaryIconContentDescription(String str) {
        this.p.setContentDescription(str);
    }

    public void setSecondaryMenuButtonDelegate(l81 l81Var) {
        this.p.b();
        this.t = l81Var;
    }

    public void setSecondaryMenuMaxSize(int i) {
        int i2 = R$dimen.message_secondary_menu_max_size_small;
        if (i == 1) {
            i2 = R$dimen.message_secondary_menu_max_size_medium;
        } else if (i == 2) {
            i2 = R$dimen.message_secondary_menu_max_size_large;
        }
        this.p.setMenuMaxWidth(getResources().getDimensionPixelSize(i2));
    }

    public void setSwipeHandler(dx2.a aVar) {
        this.u = new a(getContext(), aVar);
    }

    public void setTitle(String str) {
        this.k.setText(str);
        Runnable runnable = this.v;
        if (runnable != null) {
            runnable.run();
        }
    }

    public void setTitleContentDescription(String str) {
        this.k.setContentDescription(str);
    }
}
