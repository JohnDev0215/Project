package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import b.b.m.g.f;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class LeftRightItem extends FrameLayout implements b.b.a.j.c.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f3412a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f3413b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f3414c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f3415d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f3416e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3417f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public RelayoutState t;
    public boolean u;
    public boolean v;
    public ViewTreeObserver.OnGlobalLayoutListener w;

    public enum RelayoutState {
        INITIAL(0),
        RELAYOUT(1),
        DO_NOTHING(2),
        CHANGE_WIDTH_L(3),
        CHANGE_HEIGHT_R(4),
        CHANGE_HEIGHT_R_3(5),
        BEFORE_COMPLETED(6),
        LAYOUT_COMPLETED(7);

        public int value;

        RelayoutState(int i) {
            this.value = i;
        }
    }

    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (LeftRightItem.this.isShown()) {
                LeftRightItem.this.i();
            }
        }
    }

    public LeftRightItem(Context context) {
        super(context);
        this.f3417f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = true;
        this.q = true;
        this.r = true;
        this.s = false;
        this.u = false;
        this.v = true;
        this.w = new a();
        h(context);
    }

    public final void d(TextView textView) {
        if (textView == null) {
            this.t = RelayoutState.DO_NOTHING;
            return;
        }
        int lineCount = textView.getLineCount();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (lineCount <= 2) {
            int dimension = (int) textView.getResources().getDimension(R.dimen.dp_60);
            if (layoutParams.height > dimension) {
                layoutParams.height = dimension;
            }
            this.t = RelayoutState.CHANGE_HEIGHT_R;
        } else {
            b.b.m.c.a.a("LeftRightItem", "---checkAndChangeParentHeight---ItemTag: " + getItemTag() + ", LineCount: " + textView.getLineCount());
            layoutParams.height = (textView.getLineHeight() * lineCount) + (((int) textView.getResources().getDimension(R.dimen.dp_8)) * 2);
            this.t = RelayoutState.CHANGE_HEIGHT_R_3;
        }
        if (layoutParams.height > 0) {
            setLayoutParams(layoutParams);
        }
        this.t = RelayoutState.BEFORE_COMPLETED;
    }

    public final boolean e(TextView textView) {
        if (textView == null) {
            return false;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        ViewGroup viewGroup = (ViewGroup) textView.getParent();
        int measuredWidth = textView.getMeasuredWidth();
        float measuredWidth2 = viewGroup.getMeasuredWidth();
        if (measuredWidth / (1.0f * measuredWidth2) < 0.538f) {
            this.t = RelayoutState.DO_NOTHING;
            return false;
        }
        this.t = RelayoutState.CHANGE_WIDTH_L;
        layoutParams.width = (int) (measuredWidth2 * 0.538f);
        textView.setSingleLine(false);
        textView.setLayoutParams(layoutParams);
        return true;
    }

    public final void f(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.linear_item);
        this.f3417f = typedArrayObtainStyledAttributes.getResourceId(6, this.f3417f);
        this.g = typedArrayObtainStyledAttributes.getResourceId(9, this.g);
        this.p = typedArrayObtainStyledAttributes.getBoolean(5, this.p);
        this.q = typedArrayObtainStyledAttributes.getBoolean(4, this.q);
        this.h = typedArrayObtainStyledAttributes.getResourceId(7, this.h);
        this.i = typedArrayObtainStyledAttributes.getResourceId(10, this.i);
        this.j = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, this.j);
        this.k = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, this.k);
        this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, this.l);
        this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.m);
        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.n);
        this.o = typedArrayObtainStyledAttributes.getResourceId(0, this.o);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void g() {
        View view = this.f3412a;
        if (view == null) {
            return;
        }
        this.f3413b = (TextView) view.findViewById(R.id.tv_left_item_left_right);
        this.f3414c = (TextView) this.f3412a.findViewById(R.id.tv_right_item_left_right);
        this.f3415d = this.f3412a.findViewById(R.id.ll_right_item_left_right);
        this.f3416e = this.f3412a.findViewById(R.id.v_line_botoom_item_left_right);
    }

    public String getItemTag() {
        StringBuilder sb = new StringBuilder();
        TextView textView = this.f3413b;
        if (textView != null) {
            sb.append(textView.getText());
        }
        if (!sb.toString().isEmpty()) {
            sb.append("-->");
        }
        TextView textView2 = this.f3414c;
        if (textView2 != null) {
            String string = textView2.getText().toString();
            if (this.s) {
                string = f.g(string);
            }
            if (!this.v) {
                string = "";
            }
            sb.append(string);
        }
        return sb.toString();
    }

    public int getLayoutId() {
        return getContext().getApplicationContext().getResources().getConfiguration().getLayoutDirection() == 1 ? R.layout.item_left_right_rtl : R.layout.item_left_right;
    }

    public TextView getTvRight() {
        return this.f3414c;
    }

    public final void h(Context context) {
        this.f3412a = LayoutInflater.from(context).inflate(getLayoutId(), (ViewGroup) null);
        g();
        Resources resources = getResources();
        int i = this.f3417f;
        if (i != 0) {
            this.f3413b.setText(resources.getString(i));
        }
        int i2 = this.g;
        if (i2 != 0) {
            this.f3414c.setText(resources.getString(i2));
        }
        int i3 = this.h;
        if (i3 != 0) {
            this.f3413b.setTextColor(resources.getColor(i3));
        }
        int i4 = this.i;
        if (i4 != 0) {
            this.f3414c.setTextColor(resources.getColor(i4));
        }
        int i5 = this.j;
        if (i5 != 0) {
            this.f3413b.setTextSize(0, i5);
        }
        int i6 = this.k;
        if (i6 != 0) {
            this.f3414c.setTextSize(0, i6);
        }
        int i7 = this.l;
        if (i7 != 0) {
            j(this.f3413b, i7, -1);
        }
        int i8 = this.m;
        if (i8 != 0) {
            j(this.f3415d, -1, i8);
        }
        int i9 = this.n;
        if (i9 != 0) {
            j(this.f3416e, i9, i9);
        }
        int i10 = this.o;
        if (i10 != 0) {
            this.f3416e.setBackgroundColor(resources.getColor(i10));
        }
        this.f3416e.setVisibility(this.q ? 0 : 8);
        addView(this.f3412a);
        this.t = RelayoutState.INITIAL;
        getViewTreeObserver().addOnGlobalLayoutListener(this.w);
    }

    public void i() {
        RelayoutState relayoutState;
        b.b.m.c.a.a("LeftRightItem", "---multilingualStrategy---mState: " + this.t + ", ItemTag: " + getItemTag());
        RelayoutState relayoutState2 = this.t;
        if (relayoutState2 == RelayoutState.DO_NOTHING || relayoutState2 == (relayoutState = RelayoutState.LAYOUT_COMPLETED)) {
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.removeOnGlobalLayoutListener(this.w);
                return;
            }
            return;
        }
        e(this.f3413b);
        d(this.f3414c);
        if (this.t == RelayoutState.BEFORE_COMPLETED) {
            this.t = relayoutState;
        }
    }

    public final void j(View view, int i, int i2) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (i >= 0) {
                marginLayoutParams.setMarginStart(i);
            }
            if (i2 >= 0) {
                marginLayoutParams.setMarginEnd(i2);
            }
            view.setLayoutParams(marginLayoutParams);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeOnGlobalLayoutListener(this.w);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z) {
        super.setInvalid(z);
    }

    @Override // b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z, boolean z2) {
        super.setInvalid(z, z2);
    }

    public void setLeftText(String str) {
        TextView textView = this.f3413b;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setLogCipher(boolean z) {
        this.s = z;
    }

    public void setRightMaxLine(int i) {
        TextView textView = this.f3414c;
        if (textView != null) {
            textView.setSingleLine(false);
            this.f3414c.setMaxLines(i);
        }
    }

    public void setRightText(String str) {
        TextView textView;
        if (str == null || (textView = this.f3414c) == null || str.equals(textView.getText().toString().trim())) {
            return;
        }
        this.f3414c.setText(str);
        b.b.m.c.a.a("LeftRightItem", "---setRightText---do relayout.");
        this.t = RelayoutState.RELAYOUT;
        getViewTreeObserver().addOnGlobalLayoutListener(this.w);
        d(this.f3414c);
    }

    public void setRightTextSingleLine() {
        TextView textView = this.f3414c;
        if (textView != null) {
            textView.setSingleLine(true);
            this.f3414c.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        }
    }

    public void setRightTextType(boolean z) {
        TextView textView = this.f3414c;
        if (textView == null) {
            return;
        }
        this.r = z;
        if (z) {
            textView.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            textView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    public void setShowOnLog(boolean z) {
        this.v = z;
    }

    public void setView(View view) {
        if (view == null) {
            return;
        }
        removeAllViews();
        addView(view);
    }

    public LeftRightItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3417f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = true;
        this.q = true;
        this.r = true;
        this.s = false;
        this.u = false;
        this.v = true;
        this.w = new a();
        f(context, attributeSet);
        h(context);
    }

    public LeftRightItem(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3417f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = true;
        this.q = true;
        this.r = true;
        this.s = false;
        this.u = false;
        this.v = true;
        this.w = new a();
        f(context, attributeSet);
        h(context);
    }
}
