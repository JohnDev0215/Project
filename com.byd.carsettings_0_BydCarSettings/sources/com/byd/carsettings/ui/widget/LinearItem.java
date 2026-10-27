package com.byd.carsettings.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.Layout;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import b.b.m.g.f;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class LinearItem extends FrameLayout implements b.b.a.j.c.a {
    public boolean A;
    public ViewTreeObserver.OnGlobalLayoutListener B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f3426a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f3427b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f3428c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ImageView f3429d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f3430e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f3431f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public c v;
    public c w;
    public RelayoutState x;
    public boolean y;
    public int z;

    public enum RelayoutState {
        INITIAL(0),
        RELAYOUT(1),
        DO_NOTHING_CHECKED_L(2),
        SINGLE_LINE(3),
        SINGLE_LINE_COMPLETED(4),
        MUTI_LINE_L(5),
        WRAP_LINE_WIDTH_L(6),
        DO_NOTHING_CHECKED_R(7),
        MUTI_LINE_R(8),
        WRAP_LINE_WIDTH_R(9),
        LAYOUT_COMPLETED(10);

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
            LinearItem linearItem;
            int i;
            if (!LinearItem.this.isShown() || (i = (linearItem = LinearItem.this).z) > 10) {
                return;
            }
            linearItem.z = i + 1;
            linearItem.m();
        }
    }

    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3434a;

        static {
            int[] iArr = new int[RelayoutState.values().length];
            f3434a = iArr;
            try {
                iArr[RelayoutState.INITIAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3434a[RelayoutState.RELAYOUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3434a[RelayoutState.SINGLE_LINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3434a[RelayoutState.MUTI_LINE_L.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3434a[RelayoutState.WRAP_LINE_WIDTH_L.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3434a[RelayoutState.MUTI_LINE_R.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public LinearItem(Context context) {
        super(context);
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = true;
        this.r = false;
        this.s = true;
        this.t = false;
        this.u = false;
        a aVar = null;
        this.v = new c(this, aVar);
        this.w = new c(this, aVar);
        this.y = false;
        this.z = 0;
        this.A = true;
        this.B = new a();
        k(context);
    }

    @SuppressLint({"NewApi"})
    public final void d(TextView textView, int i) {
        if (textView == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        if (textView == this.f3427b) {
            this.x = RelayoutState.MUTI_LINE_L;
        } else {
            this.x = RelayoutState.MUTI_LINE_R;
        }
        textView.setSingleLine(false);
        int measuredWidth = (int) (i * 0.389f);
        TextView textView2 = this.f3427b;
        if (textView != textView2) {
            measuredWidth = (measuredWidth * 2) - textView2.getMeasuredWidth();
        }
        layoutParams.width = measuredWidth;
        textView.setLayoutParams(layoutParams);
    }

    public final void e(TextView textView, TextView textView2) {
        if (textView == null || textView2 == null) {
            return;
        }
        textView.setSingleLine(true);
        textView2.setSingleLine(true);
        ViewGroup viewGroup = (ViewGroup) textView.getParent();
        float fMeasureText = textView.getPaint().measureText(textView.getText().toString());
        float fMeasureText2 = textView2.getPaint().measureText(textView2.getText().toString());
        float measuredWidth = textView2.getMeasuredWidth();
        int measuredWidth2 = viewGroup.getMeasuredWidth();
        float f2 = measuredWidth2 * 1.0f;
        if ((Math.max(measuredWidth, fMeasureText2) + fMeasureText) / f2 < 0.778f) {
            this.x = RelayoutState.DO_NOTHING_CHECKED_L;
            return;
        }
        if (measuredWidth > fMeasureText2) {
            b.b.m.c.a.a("LinearItem", "---checkAndChangeWidth---ItemTag: " + getItemTag() + ", viewWidthR: " + measuredWidth + ", measuredWidthR: " + fMeasureText2);
            n(this.f3427b, this.f3428c);
            return;
        }
        float f3 = fMeasureText / f2;
        b.b.m.c.a.a("LinearItem", "---checkAndChangeWidth---rateL: " + f3);
        if (f3 > 0.389f) {
            d(textView, measuredWidth2);
        } else {
            f(this.f3427b, this.f3428c);
        }
    }

    public final void f(TextView textView, TextView textView2) {
        if (textView == null || textView2 == null) {
            return;
        }
        textView2.setSingleLine(true);
        ViewGroup viewGroup = (ViewGroup) textView.getParent();
        float f2 = this.v.f3435a;
        float fMeasureText = textView2.getPaint().measureText(textView2.getText().toString());
        int measuredWidth = viewGroup.getMeasuredWidth();
        float f3 = measuredWidth * 1.0f;
        if ((f2 + fMeasureText) / f3 < 0.778f) {
            this.x = RelayoutState.DO_NOTHING_CHECKED_R;
            return;
        }
        float f4 = fMeasureText / f3;
        b.b.m.c.a.a("LinearItem", "---checkAndChangeWidthR---rateR: " + f4);
        if (f4 > 0.389f) {
            d(textView2, measuredWidth);
        }
    }

    public final void g(TextView textView, c cVar) {
        if (textView == null || textView.getLayout() == null) {
            return;
        }
        Layout layout = textView.getLayout();
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        int lineCount = layout.getLineCount();
        if (lineCount > 1) {
            for (int i = 0; i < lineCount; i++) {
                float lineWidth = textView.getLayout().getLineWidth(i);
                if (cVar.f3435a < lineWidth || lineWidth <= 0.0f) {
                    cVar.f3435a = lineWidth;
                }
            }
            layoutParams.width = (int) cVar.f3435a;
        } else {
            layoutParams.width = -2;
        }
        textView.setLayoutParams(layoutParams);
        if (textView == this.f3427b) {
            this.x = RelayoutState.WRAP_LINE_WIDTH_L;
        } else {
            this.x = RelayoutState.WRAP_LINE_WIDTH_R;
        }
    }

    public String getItemTag() {
        StringBuilder sb = new StringBuilder();
        TextView textView = this.f3427b;
        if (textView != null) {
            sb.append(textView.getText());
        }
        if (!sb.toString().isEmpty()) {
            sb.append("-->");
        }
        if (this.t) {
            return sb.toString();
        }
        TextView textView2 = this.f3428c;
        if (textView2 != null) {
            String string = textView2.getText().toString();
            if (this.u) {
                string = f.g(string);
            }
            sb.append(string);
        }
        return sb.toString();
    }

    public int getLayoutId() {
        return R.layout.item_linear;
    }

    public TextView getTvRight() {
        return this.f3428c;
    }

    public boolean h() {
        RelayoutState relayoutState = this.x;
        if (relayoutState != RelayoutState.DO_NOTHING_CHECKED_L && relayoutState != RelayoutState.DO_NOTHING_CHECKED_R && relayoutState != RelayoutState.SINGLE_LINE_COMPLETED && relayoutState != RelayoutState.WRAP_LINE_WIDTH_R && relayoutState != RelayoutState.LAYOUT_COMPLETED) {
            return false;
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver == null) {
            return true;
        }
        viewTreeObserver.removeOnGlobalLayoutListener(this.B);
        return true;
    }

    public final void i(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.linear_item);
        this.g = typedArrayObtainStyledAttributes.getResourceId(6, this.g);
        this.h = typedArrayObtainStyledAttributes.getResourceId(9, this.h);
        this.q = typedArrayObtainStyledAttributes.getBoolean(5, this.q);
        this.r = typedArrayObtainStyledAttributes.getBoolean(4, this.r);
        this.i = typedArrayObtainStyledAttributes.getResourceId(7, this.i);
        this.j = typedArrayObtainStyledAttributes.getResourceId(10, this.j);
        this.k = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, this.k);
        this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, this.l);
        this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, this.m);
        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.n);
        this.o = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.o);
        this.p = typedArrayObtainStyledAttributes.getResourceId(0, this.p);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void j() {
        View view = this.f3426a;
        if (view == null) {
            return;
        }
        this.f3427b = (TextView) view.findViewById(R.id.tv_left_item_linear);
        this.f3428c = (TextView) this.f3426a.findViewById(R.id.tv_right_item_linear);
        this.f3429d = (ImageView) this.f3426a.findViewById(R.id.iv_right_item_linear);
        this.f3430e = this.f3426a.findViewById(R.id.ll_right_item_linear);
        this.f3431f = this.f3426a.findViewById(R.id.v_line_botoom_item_linear);
    }

    public final void k(Context context) {
        this.f3426a = LayoutInflater.from(context).inflate(getLayoutId(), (ViewGroup) null);
        j();
        Resources resources = getResources();
        int i = this.g;
        if (i != 0) {
            this.f3427b.setText(resources.getString(i));
        }
        int i2 = this.h;
        if (i2 != 0) {
            this.f3428c.setText(resources.getString(i2));
        }
        int i3 = this.i;
        if (i3 != 0) {
            this.f3427b.setTextColor(resources.getColor(i3));
        }
        int i4 = this.j;
        if (i4 != 0) {
            this.f3428c.setTextColor(resources.getColor(i4));
        }
        int i5 = this.k;
        if (i5 != 0) {
            this.f3427b.setTextSize(0, i5);
        }
        int i6 = this.l;
        if (i6 != 0) {
            this.f3428c.setTextSize(0, i6);
        }
        int i7 = this.m;
        if (i7 != 0) {
            o(this.f3427b, i7, -1);
        }
        int i8 = this.n;
        if (i8 != 0) {
            o(this.f3430e, -1, i8);
        }
        int i9 = this.o;
        if (i9 != 0) {
            o(this.f3431f, i9, i9);
        }
        int i10 = this.p;
        if (i10 != 0) {
            this.f3431f.setBackgroundColor(resources.getColor(i10));
        }
        this.f3429d.setVisibility(this.q ? 0 : 8);
        this.f3431f.setVisibility(this.r ? 0 : 8);
        addView(this.f3426a);
        this.x = RelayoutState.INITIAL;
        getViewTreeObserver().addOnGlobalLayoutListener(this.B);
    }

    public boolean l() {
        return this.s;
    }

    public void m() {
        b.b.m.c.a.a("LinearItem", "---multilingualStrategy---mState: " + this.x + ", strategyExecuteCount: " + this.z + ", ItemTag: " + getItemTag());
        if (h()) {
            return;
        }
        switch (b.f3434a[this.x.ordinal()]) {
            case 1:
            case 2:
            case 3:
                e(this.f3427b, this.f3428c);
                break;
            case 4:
                g(this.f3427b, this.v);
                break;
            case 5:
                f(this.f3427b, this.f3428c);
                break;
            case 6:
                g(this.f3428c, this.w);
                break;
        }
        this.y = p(this.f3428c.getText().toString());
        if (this.x == RelayoutState.SINGLE_LINE) {
            this.x = RelayoutState.SINGLE_LINE_COMPLETED;
        }
        if (this.x == RelayoutState.WRAP_LINE_WIDTH_R) {
            this.x = RelayoutState.LAYOUT_COMPLETED;
        }
    }

    public final void n(TextView textView, TextView textView2) {
        this.x = RelayoutState.SINGLE_LINE;
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = -2;
        textView.setSingleLine(true);
        ViewGroup.LayoutParams layoutParams2 = textView2.getLayoutParams();
        layoutParams2.width = -2;
        textView2.setSingleLine(true);
        textView.setLayoutParams(layoutParams);
        textView2.setLayoutParams(layoutParams2);
    }

    public final void o(View view, int i, int i2) {
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
            viewTreeObserver.removeOnGlobalLayoutListener(this.B);
        }
        this.z = 0;
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public final boolean p(String str) {
        int marginStart;
        int width = ((ViewGroup) this.f3427b.getParent()).getWidth();
        float fMeasureText = this.f3427b.getPaint().measureText(this.f3427b.getText().toString()) + this.f3428c.getPaint().measureText(str);
        ViewGroup.LayoutParams layoutParams = this.f3427b.getLayoutParams();
        int marginStart2 = layoutParams instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams).getMarginStart() : 0;
        ViewGroup.LayoutParams layoutParams2 = this.f3430e.getLayoutParams();
        if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams2;
            marginStart = marginLayoutParams.getMarginStart() + marginLayoutParams.getMarginEnd();
        } else {
            marginStart = 0;
        }
        return fMeasureText < ((float) (width - ((this.f3429d.getWidth() + marginStart2) + marginStart)));
    }

    public /* bridge */ /* synthetic */ void setInvalid(boolean z) {
        super.setInvalid(z);
    }

    public /* bridge */ /* synthetic */ void setInvalid(boolean z, boolean z2) {
        super.setInvalid(z, z2);
    }

    public void setLeftText(String str) {
        TextView textView = this.f3427b;
        if (textView == null || str == null) {
            return;
        }
        if (str.trim().equals(textView.getText().toString().trim())) {
            return;
        }
        this.z = 0;
        this.f3427b.setText(str);
        this.x = RelayoutState.RELAYOUT;
        requestLayout();
    }

    public void setLogCipher(boolean z) {
        this.u = z;
    }

    public void setPwdRight(boolean z) {
        this.t = z;
    }

    public void setRightMaxLine(int i) {
        TextView textView = this.f3428c;
        if (textView != null) {
            textView.setSingleLine(false);
            this.f3428c.setMaxLines(i);
        }
    }

    public void setRightText(String str) {
        TextView textView = this.f3428c;
        if (textView == null || str == null || str.trim().equals(textView.getText().toString().trim())) {
            return;
        }
        this.z = 0;
        this.f3428c.setText(str);
        boolean zP = p(str);
        b.b.m.c.a.a("LinearItem", "---setRightText---curSingleLine: " + this.y + ", singleLine: " + zP + ", ItemTag: " + getItemTag());
        if (this.y != zP) {
            this.x = RelayoutState.RELAYOUT;
            getViewTreeObserver().addOnGlobalLayoutListener(this.B);
            requestLayout();
        }
    }

    public void setRightTextSingleLine() {
        TextView textView = this.f3428c;
        if (textView != null) {
            textView.setSingleLine(true);
            this.f3428c.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        }
    }

    public void setRightTextType(boolean z) {
        TextView textView = this.f3428c;
        if (textView == null) {
            return;
        }
        this.s = z;
        if (z) {
            textView.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            textView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    public void setRightTextV2(String str) {
        TextView textView = this.f3428c;
        if (textView == null || str == null) {
            b.b.m.c.a.c("LinearItem", "tvRight == null || text == null");
        } else {
            textView.setText(str);
        }
    }

    public void setShowOnLog(boolean z) {
        this.A = z;
    }

    public void setView(View view) {
        if (view == null) {
            return;
        }
        removeAllViews();
        addView(view);
    }

    public class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f3435a;

        public c() {
            this.f3435a = 0.0f;
        }

        public /* synthetic */ c(LinearItem linearItem, a aVar) {
            this();
        }
    }

    public LinearItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = true;
        this.r = false;
        this.s = true;
        this.t = false;
        this.u = false;
        a aVar = null;
        this.v = new c(this, aVar);
        this.w = new c(this, aVar);
        this.y = false;
        this.z = 0;
        this.A = true;
        this.B = new a();
        i(context, attributeSet);
        k(context);
    }

    public LinearItem(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = true;
        this.r = false;
        this.s = true;
        this.t = false;
        this.u = false;
        a aVar = null;
        this.v = new c(this, aVar);
        this.w = new c(this, aVar);
        this.y = false;
        this.z = 0;
        this.A = true;
        this.B = new a();
        i(context, attributeSet);
        k(context);
    }
}
