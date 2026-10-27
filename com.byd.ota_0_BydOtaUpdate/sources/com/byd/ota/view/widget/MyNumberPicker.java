package com.byd.ota.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Scroller;
import android.widget.TextView;
import com.byd.ota.R;
import java.util.Formatter;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class MyNumberPicker extends LinearLayout {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final l f3124c = new l();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final char[] f3125d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 1632, 1633, 1634, 1635, 1636, 1637, 1638, 1639, 1640, 1641, 1776, 1777, 1778, 1779, 1780, 1781, 1782, 1783, 1784, 1785, 2406, 2407, 2408, 2409, 2410, 2411, 2412, 2413, 2414, 2415, 2534, 2535, 2536, 2537, 2538, 2539, 2540, 2541, 2542, 2543, 3302, 3303, 3304, 3305, 3306, 3307, 3308, 3309, 3310, 3311};
    public boolean A4;
    public final j B4;
    public int C4;
    public boolean D4;
    public final EditText E3;
    public Context E4;
    public final int F3;
    public int F4;
    public final int G3;
    public int G4;
    public final int H3;
    public int H4;
    public final int I3;
    public int J3;
    public final boolean K3;
    public final int L3;
    public int M3;
    public String[] N3;
    public int O3;
    public int P3;
    public int Q3;
    public i R3;
    public h S3;
    public f T3;
    public long U3;
    public final SparseArray<String> V3;
    public final int[] W3;
    public final Paint X3;
    public final Drawable Y3;
    public int Z3;
    public int a4;
    public int b4;
    public final Scroller c4;
    public final Scroller d4;
    public int e4;
    public k f4;
    public e g4;
    public d h4;
    public float i4;
    public long j4;
    public float k4;
    public VelocityTracker l4;
    public int m4;
    public int n4;
    public int o4;
    public boolean p4;
    public final int q4;
    public final boolean r4;
    public boolean s;
    public final Drawable s4;
    public int t4;
    public int u4;
    public boolean v4;
    public boolean w4;
    public final ImageButton x;
    public int x4;
    public final ImageButton y;
    public int y4;
    public boolean z4;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MyNumberPicker.this.D();
            MyNumberPicker.this.E3.clearFocus();
            if (view.getId() == R.id.increment) {
                MyNumberPicker.this.u(true);
            } else {
                MyNumberPicker.this.u(false);
            }
        }
    }

    public class b implements View.OnLongClickListener {
        public b() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            MyNumberPicker.this.D();
            MyNumberPicker.this.E3.clearFocus();
            if (view.getId() == R.id.increment) {
                MyNumberPicker.this.O(true, 0L);
            } else {
                MyNumberPicker.this.O(false, 0L);
            }
            return true;
        }
    }

    public class c implements View.OnFocusChangeListener {
        public c() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                MyNumberPicker.this.E3.selectAll();
            } else {
                MyNumberPicker.this.E3.setSelection(0, 0);
                MyNumberPicker.this.Z(view);
            }
        }
    }

    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MyNumberPicker.this.performLongClick();
        }
    }

    public class e implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f3130c;

        public e() {
        }

        public final void b(boolean z) {
            this.f3130c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            MyNumberPicker.this.u(this.f3130c);
            MyNumberPicker myNumberPicker = MyNumberPicker.this;
            myNumberPicker.postDelayed(this, myNumberPicker.U3);
        }
    }

    public interface f {
        String format(int i2);
    }

    public class g extends NumberKeyListener {
        public g() {
        }

        @Override // android.text.method.NumberKeyListener, android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
            if (MyNumberPicker.this.f4 != null) {
                MyNumberPicker.this.f4.a();
            }
            if (MyNumberPicker.this.N3 == null) {
                CharSequence charSequenceFilter = super.filter(charSequence, i2, i3, spanned, i4, i5);
                if (charSequenceFilter == null) {
                    charSequenceFilter = charSequence.subSequence(i2, i3);
                }
                String str = String.valueOf(spanned.subSequence(0, i4)) + ((Object) charSequenceFilter) + ((Object) spanned.subSequence(i5, spanned.length()));
                return "".equals(str) ? str : (MyNumberPicker.this.B(str) > MyNumberPicker.this.P3 || str.length() > String.valueOf(MyNumberPicker.this.P3).length()) ? "" : charSequenceFilter;
            }
            String strValueOf = String.valueOf(charSequence.subSequence(i2, i3));
            if (TextUtils.isEmpty(strValueOf)) {
                return "";
            }
            String str2 = String.valueOf(spanned.subSequence(0, i4)) + ((Object) strValueOf) + ((Object) spanned.subSequence(i5, spanned.length()));
            String lowerCase = String.valueOf(str2).toLowerCase();
            for (String str3 : MyNumberPicker.this.N3) {
                if (str3.toLowerCase().startsWith(lowerCase)) {
                    MyNumberPicker.this.P(str2.length(), str3.length());
                    return str3.subSequence(i4, str3.length());
                }
            }
            return "";
        }

        @Override // android.text.method.NumberKeyListener
        public char[] getAcceptedChars() {
            return MyNumberPicker.f3125d;
        }

        @Override // android.text.method.KeyListener
        public int getInputType() {
            return 1;
        }
    }

    public interface h {
        void a(MyNumberPicker myNumberPicker, int i2);
    }

    public interface i {
        void a(MyNumberPicker myNumberPicker, int i2, int i3);
    }

    public class j implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f3133c = 1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f3134d = 2;
        public int s;
        public int x;

        public j() {
        }

        public void a(int i2) {
            c();
            this.x = 1;
            this.s = i2;
            MyNumberPicker.this.postDelayed(this, ViewConfiguration.getTapTimeout());
        }

        public void b(int i2) {
            c();
            this.x = 2;
            this.s = i2;
            MyNumberPicker.this.post(this);
        }

        public void c() {
            this.x = 0;
            this.s = 0;
            MyNumberPicker.this.removeCallbacks(this);
            if (MyNumberPicker.this.z4) {
                MyNumberPicker.this.z4 = false;
                MyNumberPicker myNumberPicker = MyNumberPicker.this;
                myNumberPicker.invalidate(0, myNumberPicker.y4, MyNumberPicker.this.getRight(), MyNumberPicker.this.getBottom());
            }
            MyNumberPicker.this.A4 = false;
            if (MyNumberPicker.this.A4) {
                MyNumberPicker myNumberPicker2 = MyNumberPicker.this;
                myNumberPicker2.invalidate(0, 0, myNumberPicker2.getRight(), MyNumberPicker.this.x4);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2 = this.x;
            if (i2 == 1) {
                int i3 = this.s;
                if (i3 == 1) {
                    MyNumberPicker.this.z4 = true;
                    MyNumberPicker myNumberPicker = MyNumberPicker.this;
                    myNumberPicker.invalidate(0, myNumberPicker.y4, MyNumberPicker.this.getRight(), MyNumberPicker.this.getBottom());
                    return;
                } else {
                    if (i3 != 2) {
                        return;
                    }
                    MyNumberPicker.this.A4 = true;
                    MyNumberPicker myNumberPicker2 = MyNumberPicker.this;
                    myNumberPicker2.invalidate(0, 0, myNumberPicker2.getRight(), MyNumberPicker.this.x4);
                    return;
                }
            }
            if (i2 != 2) {
                return;
            }
            int i4 = this.s;
            if (i4 == 1) {
                if (!MyNumberPicker.this.z4) {
                    MyNumberPicker.this.postDelayed(this, ViewConfiguration.getPressedStateDuration());
                }
                MyNumberPicker.g(MyNumberPicker.this, 1);
                MyNumberPicker myNumberPicker3 = MyNumberPicker.this;
                myNumberPicker3.invalidate(0, myNumberPicker3.y4, MyNumberPicker.this.getRight(), MyNumberPicker.this.getBottom());
                return;
            }
            if (i4 != 2) {
                return;
            }
            if (!MyNumberPicker.this.A4) {
                MyNumberPicker.this.postDelayed(this, ViewConfiguration.getPressedStateDuration());
            }
            MyNumberPicker.k(MyNumberPicker.this, 1);
            MyNumberPicker myNumberPicker4 = MyNumberPicker.this;
            myNumberPicker4.invalidate(0, 0, myNumberPicker4.getRight(), MyNumberPicker.this.x4);
        }
    }

    public static class k implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final EditText f3135c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3136d;
        public int s;
        public boolean x;

        public k(EditText editText) {
            this.f3135c = editText;
        }

        public void a() {
            if (this.x) {
                this.f3135c.removeCallbacks(this);
                this.x = false;
            }
        }

        public void b(int i2, int i3) {
            this.f3136d = i2;
            this.s = i3;
            if (this.x) {
                return;
            }
            this.f3135c.post(this);
            this.x = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.x = false;
            this.f3135c.setSelection(this.f3136d, this.s);
        }
    }

    public static class l implements NumberPicker.Formatter {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public char f3138b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Formatter f3139c;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final StringBuilder f3137a = new StringBuilder();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Object[] f3140d = new Object[1];

        public l() {
            c(Locale.getDefault());
        }

        public static char b(Locale locale) {
            return '0';
        }

        public final Formatter a(Locale locale) {
            return new Formatter(this.f3137a, locale);
        }

        public final void c(Locale locale) {
            this.f3139c = a(locale);
            this.f3138b = b(locale);
        }

        @Override // android.widget.NumberPicker.Formatter
        public String format(int i2) {
            Locale locale = Locale.getDefault();
            if (this.f3138b != b(locale)) {
                c(locale);
            }
            this.f3140d[0] = Integer.valueOf(i2);
            StringBuilder sb = this.f3137a;
            sb.delete(0, sb.length());
            this.f3139c.format("%02d", this.f3140d);
            return this.f3139c.toString();
        }
    }

    public MyNumberPicker(Context context) {
        this(context, null);
    }

    public static String A(int i2) {
        return String.format(Locale.getDefault(), "%d", Integer.valueOf(i2));
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [boolean, byte] */
    public static /* synthetic */ boolean g(MyNumberPicker myNumberPicker, int i2) {
        ?? r2 = (byte) (i2 ^ (myNumberPicker.z4 ? 1 : 0));
        myNumberPicker.z4 = r2;
        return r2;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [boolean, byte] */
    public static /* synthetic */ boolean k(MyNumberPicker myNumberPicker, int i2) {
        ?? r2 = (byte) (i2 ^ (myNumberPicker.A4 ? 1 : 0));
        myNumberPicker.A4 = r2;
        return r2;
    }

    public final int B(String str) {
        try {
            if (this.N3 == null) {
                return Integer.parseInt(str);
            }
            for (int i2 = 0; i2 < this.N3.length; i2++) {
                str = str.toLowerCase();
                if (this.N3[i2].toLowerCase().startsWith(str)) {
                    return this.O3 + i2;
                }
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return this.O3;
        }
    }

    public final int C(int i2) {
        int i3 = this.P3;
        if (i2 > i3) {
            int i4 = this.O3;
            return (i4 + ((i2 - i3) % (i3 - i4))) - 1;
        }
        int i5 = this.O3;
        return i2 < i5 ? (i3 - ((i5 - i2) % (i3 - i5))) + 1 : i2;
    }

    public final void D() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(InputMethodManager.class);
        if (inputMethodManager != null && inputMethodManager.isActive(this.E3)) {
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        }
        if (this.r4) {
            this.E3.setVisibility(4);
        }
    }

    public final void E(int[] iArr) {
        int i2 = 0;
        while (i2 < iArr.length - 1) {
            int i3 = i2 + 1;
            iArr[i2] = iArr[i3];
            i2 = i3;
        }
        int i4 = iArr[iArr.length - 2] + 1;
        if (this.p4 && i4 > this.P3) {
            i4 = this.O3;
        }
        iArr[iArr.length - 1] = i4;
        w(i4);
    }

    public final void F() {
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(((getBottom() - getTop()) - this.L3) / 2);
    }

    public final void G() {
        H();
        int[] iArr = this.W3;
        int bottom = (int) ((((getBottom() - getTop()) - (iArr.length * this.L3)) / iArr.length) + 0.5f);
        this.M3 = bottom;
        this.Z3 = this.L3 + bottom;
        int baseline = (this.E3.getBaseline() + this.E3.getTop()) - (this.Z3 * 1);
        this.a4 = baseline;
        this.b4 = baseline;
        X();
    }

    public final void H() {
        this.V3.clear();
        int[] iArr = this.W3;
        int value = getValue();
        for (int i2 = 0; i2 < this.W3.length; i2++) {
            int iC = (i2 - 1) + value;
            if (this.p4) {
                iC = C(iC);
            }
            iArr[i2] = iC;
            w(iArr[i2]);
        }
    }

    public final int I(int i2, int i3) {
        if (i3 == -1) {
            return i2;
        }
        int size = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        if (mode == 0) {
            return View.MeasureSpec.makeMeasureSpec(i3, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        if (mode == 1073741824) {
            return i2;
        }
        throw new IllegalArgumentException("Unknown measure mode: " + mode);
    }

    public final boolean J(Scroller scroller) {
        scroller.forceFinished(true);
        int finalY = scroller.getFinalY() - scroller.getCurrY();
        int i2 = this.a4 - ((this.b4 + finalY) % this.Z3);
        if (i2 == 0) {
            return false;
        }
        int iAbs = Math.abs(i2);
        int i3 = this.Z3;
        if (iAbs > i3 / 2) {
            i2 = i2 > 0 ? i2 - i3 : i2 + i3;
        }
        scrollBy(0, finalY + i2);
        return true;
    }

    public final void K(int i2, int i3) {
        i iVar = this.R3;
        if (iVar != null) {
            iVar.a(this, i2, this.Q3);
        }
    }

    public final void L(int i2) {
        if (this.u4 == i2) {
            return;
        }
        this.u4 = i2;
        h hVar = this.S3;
        if (hVar != null) {
            hVar.a(this, i2);
        }
    }

    public final void M(Scroller scroller) {
        if (scroller == this.c4) {
            x();
            X();
            L(0);
        } else if (this.u4 != 1) {
            X();
        }
    }

    public final void N() {
        d dVar = this.h4;
        if (dVar == null) {
            this.h4 = new d();
        } else {
            removeCallbacks(dVar);
        }
        postDelayed(this.h4, ViewConfiguration.getLongPressTimeout());
    }

    public final void O(boolean z, long j2) {
        e eVar = this.g4;
        if (eVar == null) {
            this.g4 = new e();
        } else {
            removeCallbacks(eVar);
        }
        this.g4.b(z);
        postDelayed(this.g4, j2);
    }

    public final void P(int i2, int i3) {
        if (this.f4 == null) {
            this.f4 = new k(this.E3);
        }
        this.f4.b(i2, i3);
    }

    public final void Q() {
        e eVar = this.g4;
        if (eVar != null) {
            removeCallbacks(eVar);
        }
        k kVar = this.f4;
        if (kVar != null) {
            kVar.a();
        }
        d dVar = this.h4;
        if (dVar != null) {
            removeCallbacks(dVar);
        }
        this.B4.c();
    }

    public final void R() {
        d dVar = this.h4;
        if (dVar != null) {
            removeCallbacks(dVar);
        }
    }

    public final void S() {
        e eVar = this.g4;
        if (eVar != null) {
            removeCallbacks(eVar);
        }
    }

    public final int T(int i2, int i3, int i4) {
        return i2 != -1 ? LinearLayout.resolveSizeAndState(Math.max(i2, i3), i4, 0) : i3;
    }

    public final void U(int i2, boolean z) {
        if (this.Q3 == i2) {
            return;
        }
        int iC = this.p4 ? C(i2) : Math.min(Math.max(i2, this.O3), this.P3);
        int i3 = this.Q3;
        this.Q3 = iC;
        if (this.u4 != 2) {
            X();
        }
        if (z) {
            K(i3, iC);
        }
        H();
        invalidate();
    }

    public final void V() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(InputMethodManager.class);
        if (inputMethodManager != null) {
            if (this.r4) {
                this.E3.setVisibility(0);
            }
            this.E3.requestFocus();
            inputMethodManager.showSoftInput(this.E3, 0);
        }
    }

    public final void W() {
        int i2;
        if (this.K3) {
            String[] strArr = this.N3;
            int i3 = 0;
            if (strArr == null) {
                float f2 = 0.0f;
                for (int i4 = 0; i4 <= 9; i4++) {
                    float fMeasureText = this.X3.measureText(A(i4));
                    if (fMeasureText > f2) {
                        f2 = fMeasureText;
                    }
                }
                for (int i5 = this.P3; i5 > 0; i5 /= 10) {
                    i3++;
                }
                i2 = (int) (i3 * f2);
            } else {
                int length = strArr.length;
                int i6 = 0;
                while (i3 < length) {
                    float fMeasureText2 = this.X3.measureText(this.N3[i3]);
                    if (fMeasureText2 > i6) {
                        i6 = (int) fMeasureText2;
                    }
                    i3++;
                }
                i2 = i6;
            }
            int paddingLeft = i2 + this.E3.getPaddingLeft() + this.E3.getPaddingRight();
            if (this.J3 != paddingLeft) {
                int i7 = this.I3;
                if (paddingLeft > i7) {
                    this.J3 = paddingLeft;
                } else {
                    this.J3 = i7;
                }
                invalidate();
            }
        }
    }

    public final boolean X() {
        String[] strArr = this.N3;
        String strZ = strArr == null ? z(this.Q3) : strArr[this.Q3 - this.O3];
        if (TextUtils.isEmpty(strZ) || strZ.equals(this.E3.getText().toString())) {
            return false;
        }
        this.E3.setText(strZ);
        return true;
    }

    public final void Y() {
        this.p4 = (this.P3 - this.O3 >= this.W3.length) && this.s;
    }

    public final void Z(View view) {
        String strValueOf = String.valueOf(((TextView) view).getText());
        if (TextUtils.isEmpty(strValueOf)) {
            X();
        } else {
            U(B(strValueOf), true);
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        Scroller scroller = this.c4;
        if (scroller.isFinished()) {
            scroller = this.d4;
            if (scroller.isFinished()) {
                return;
            }
        }
        scroller.computeScrollOffset();
        int currY = scroller.getCurrY();
        if (this.e4 == 0) {
            this.e4 = scroller.getStartY();
        }
        scrollBy(0, currY - this.e4);
        this.e4 = currY;
        if (scroller.isFinished()) {
            M(scroller);
        } else {
            invalidate();
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        return getHeight();
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        return this.b4;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        return ((this.P3 - this.O3) + 1) * this.Z3;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 19 || keyCode == 20) {
            if (this.r4) {
                int action = keyEvent.getAction();
                if (action == 0) {
                    if (!this.p4) {
                        if (keyCode == 20) {
                        }
                    }
                    requestFocus();
                    this.C4 = keyCode;
                    Q();
                    if (this.c4.isFinished()) {
                        u(keyCode == 20);
                    }
                    return true;
                }
                if (action == 1 && this.C4 == keyCode) {
                    this.C4 = -1;
                    return true;
                }
            }
        } else if (keyCode == 23 || keyCode == 66 || keyCode == 160) {
            Q();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            Q();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            Q();
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.s4;
        if (drawable != null && drawable.isStateful() && drawable.setState(getDrawableState())) {
            invalidateDrawable(drawable);
        }
    }

    @Override // android.view.View
    public float getBottomFadingEdgeStrength() {
        return 0.9f;
    }

    public CharSequence getDisplayedValueForCurrentSelection() {
        return this.V3.get(getValue());
    }

    public String[] getDisplayedValues() {
        return this.N3;
    }

    public int getMaxValue() {
        return this.P3;
    }

    public int getMinValue() {
        return this.O3;
    }

    public int getSelectionDividerHeight() {
        return this.t4;
    }

    @Override // android.view.View
    public int getSolidColor() {
        return this.q4;
    }

    public int getTextColor() {
        return this.X3.getColor();
    }

    public float getTextSize() {
        return this.X3.getTextSize();
    }

    @Override // android.view.View
    public float getTopFadingEdgeStrength() {
        return 0.9f;
    }

    public int getValue() {
        return this.Q3;
    }

    public boolean getWrapSelectorWheel() {
        return this.p4;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.s4;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Q();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        Drawable drawable;
        if (!this.r4) {
            super.onDraw(canvas);
            return;
        }
        boolean zHasFocus = this.D4 ? hasFocus() : true;
        float right = (getRight() - getLeft()) / 2;
        float f2 = this.b4;
        if (zHasFocus && (drawable = this.Y3) != null && this.u4 == 0) {
            if (this.A4) {
                drawable.setState(LinearLayout.PRESSED_STATE_SET);
                this.Y3.setBounds(0, 0, getRight(), this.x4);
                this.Y3.draw(canvas);
            }
            if (this.z4) {
                this.Y3.setState(LinearLayout.PRESSED_STATE_SET);
                this.Y3.setBounds(0, this.y4, getRight(), getBottom());
                this.Y3.draw(canvas);
            }
        }
        int[] iArr = this.W3;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            String str = this.V3.get(iArr[i2]);
            if (i2 == 1) {
                this.X3.setColor(this.F4);
            } else {
                this.X3.setColor(this.H4);
            }
            canvas.drawText(str, right, f2, this.X3);
            f2 += this.Z3;
        }
        this.X3.setColor(this.G4);
        canvas.drawRect(0.0f, this.x4, getRight(), this.t4 + r0, this.X3);
        canvas.drawRect(0.0f, r0 - this.t4, getRight(), this.y4, this.X3);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.r4 || !isEnabled() || motionEvent.getActionMasked() != 0) {
            return false;
        }
        Q();
        D();
        float y = motionEvent.getY();
        this.i4 = y;
        this.k4 = y;
        this.j4 = motionEvent.getEventTime();
        this.v4 = false;
        this.w4 = false;
        float f2 = this.i4;
        if (f2 < this.x4) {
            if (this.u4 == 0) {
                this.B4.a(2);
            }
        } else if (f2 > this.y4 && this.u4 == 0) {
            this.B4.a(1);
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        if (!this.c4.isFinished()) {
            this.c4.forceFinished(true);
            this.d4.forceFinished(true);
            M(this.c4);
            L(0);
        } else if (this.d4.isFinished()) {
            float f3 = this.i4;
            if (f3 < this.x4) {
                O(false, ViewConfiguration.getLongPressTimeout());
            } else if (f3 > this.y4) {
                O(true, ViewConfiguration.getLongPressTimeout());
            } else {
                this.w4 = true;
                N();
            }
        } else {
            this.c4.forceFinished(true);
            this.d4.forceFinished(true);
            M(this.d4);
        }
        return true;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (!this.r4) {
            super.onLayout(z, i2, i3, i4, i5);
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int measuredWidth2 = this.E3.getMeasuredWidth();
        int measuredHeight2 = this.E3.getMeasuredHeight();
        int i6 = (measuredWidth - measuredWidth2) / 2;
        int i7 = (measuredHeight - measuredHeight2) / 2;
        this.E3.layout(i6, i7, measuredWidth2 + i6, measuredHeight2 + i7);
        if (z) {
            G();
            F();
            int height = getHeight();
            int i8 = this.F3;
            int i9 = this.t4;
            int i10 = ((height - i8) / 2) - i9;
            this.x4 = i10;
            this.y4 = i10 + (i9 * 2) + i8;
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (!this.r4) {
            super.onMeasure(i2, i3);
        } else {
            super.onMeasure(I(i2, this.J3), I(i3, this.H3));
            setMeasuredDimension(T(this.I3, getMeasuredWidth(), i2), T(this.G3, getMeasuredHeight(), i3));
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || !this.r4) {
            return false;
        }
        if (this.l4 == null) {
            this.l4 = VelocityTracker.obtain();
        }
        this.l4.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            R();
            S();
            this.B4.c();
            VelocityTracker velocityTracker = this.l4;
            velocityTracker.computeCurrentVelocity(1000, this.o4);
            int yVelocity = (int) velocityTracker.getYVelocity();
            if (Math.abs(yVelocity) > this.n4) {
                y(yVelocity);
                L(2);
            } else {
                int y = (int) motionEvent.getY();
                int iAbs = (int) Math.abs(y - this.i4);
                long eventTime = motionEvent.getEventTime() - this.j4;
                if (iAbs > this.m4 || eventTime >= ViewConfiguration.getTapTimeout()) {
                    x();
                } else if (this.w4) {
                    this.w4 = false;
                    performClick();
                } else {
                    int i2 = (y / this.Z3) - 1;
                    if (i2 > 0) {
                        u(true);
                        this.B4.b(1);
                    } else if (i2 < 0) {
                        u(false);
                        this.B4.b(2);
                    }
                }
                L(0);
            }
            this.l4.recycle();
            this.l4 = null;
        } else if (actionMasked == 2 && !this.v4) {
            float y2 = motionEvent.getY();
            if (this.u4 == 1) {
                scrollBy(0, (int) (y2 - this.k4));
                invalidate();
            } else if (((int) Math.abs(y2 - this.i4)) > this.m4) {
                Q();
                L(1);
            }
            this.k4 = y2;
        }
        return true;
    }

    @Override // android.view.View
    public boolean performClick() {
        if (!this.r4) {
            return super.performClick();
        }
        if (super.performClick()) {
            return true;
        }
        V();
        return true;
    }

    @Override // android.view.View
    public boolean performLongClick() {
        if (!this.r4) {
            return super.performLongClick();
        }
        if (!super.performLongClick()) {
            V();
            this.v4 = true;
        }
        return true;
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        int i4;
        int[] iArr = this.W3;
        int i5 = this.b4;
        boolean z = this.p4;
        if (!z && i3 > 0 && iArr[1] <= this.O3) {
            this.b4 = this.a4;
            return;
        }
        if (!z && i3 < 0 && iArr[1] >= this.P3) {
            this.b4 = this.a4;
            return;
        }
        this.b4 = i3 + i5;
        while (true) {
            int i6 = this.b4;
            if (i6 - this.a4 <= this.M3) {
                break;
            }
            this.b4 = i6 - this.Z3;
            v(iArr);
            U(iArr[1], true);
            if (!this.p4 && iArr[1] <= this.O3) {
                this.b4 = this.a4;
            }
        }
        while (true) {
            i4 = this.b4;
            if (i4 - this.a4 >= (-this.M3)) {
                break;
            }
            this.b4 = i4 + this.Z3;
            E(iArr);
            U(iArr[1], true);
            if (!this.p4 && iArr[1] >= this.P3) {
                this.b4 = this.a4;
            }
        }
        if (i5 != i4) {
            onScrollChanged(0, i4, 0, i5);
        }
    }

    public void setDisplayedValues(String[] strArr) {
        if (this.N3 == strArr) {
            return;
        }
        this.N3 = strArr;
        if (strArr != null) {
            this.E3.setRawInputType(524289);
        } else {
            this.E3.setRawInputType(2);
        }
        X();
        H();
        W();
    }

    public void setDividerColor(int i2) {
        this.G4 = i2;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!this.r4) {
            this.x.setEnabled(z);
        }
        if (!this.r4) {
            this.y.setEnabled(z);
        }
        this.E3.setEnabled(z);
    }

    public void setFormatter(f fVar) {
        if (fVar == this.T3) {
            return;
        }
        this.T3 = fVar;
        H();
        X();
    }

    public void setMaxValue(int i2) {
        if (this.P3 == i2) {
            return;
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("maxValue must be >= 0");
        }
        this.P3 = i2;
        if (i2 < this.Q3) {
            this.Q3 = i2;
        }
        Y();
        H();
        X();
        W();
        invalidate();
    }

    public void setMinValue(int i2) {
        if (this.O3 == i2) {
            return;
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("minValue must be >= 0");
        }
        this.O3 = i2;
        if (i2 > this.Q3) {
            this.Q3 = i2;
        }
        Y();
        H();
        X();
        W();
        invalidate();
    }

    public void setNormalTextColor(int i2) {
        this.H4 = i2;
    }

    public void setOnLongPressUpdateInterval(long j2) {
        this.U3 = j2;
    }

    public void setOnScrollListener(h hVar) {
        this.S3 = hVar;
    }

    public void setOnValueChangedListener(i iVar) {
        this.R3 = iVar;
    }

    public void setSelectColor(int i2) {
        this.F4 = i2;
    }

    public void setTextColor(int i2) {
        this.X3.setColor(i2);
        this.E3.setTextColor(i2);
        invalidate();
    }

    public void setTextSize(float f2) {
        this.X3.setTextSize(f2);
        this.E3.setTextSize(0, f2);
        invalidate();
    }

    public void setValue(int i2) {
        U(i2, false);
    }

    public void setWrapSelectorWheel(boolean z) {
        this.s = z;
        Y();
    }

    public final void u(boolean z) {
        if (!this.r4) {
            if (z) {
                U(this.Q3 + 1, true);
                return;
            } else {
                U(this.Q3 - 1, true);
                return;
            }
        }
        D();
        if (!J(this.c4)) {
            J(this.d4);
        }
        this.e4 = 0;
        if (z) {
            this.c4.startScroll(0, 0, 0, -this.Z3, 300);
        } else {
            this.c4.startScroll(0, 0, 0, this.Z3, 300);
        }
        invalidate();
    }

    public final void v(int[] iArr) {
        for (int length = iArr.length - 1; length > 0; length--) {
            iArr[length] = iArr[length - 1];
        }
        int i2 = iArr[1] - 1;
        if (this.p4 && i2 < this.O3) {
            i2 = this.P3;
        }
        iArr[0] = i2;
        w(i2);
    }

    public final void w(int i2) {
        String strZ;
        SparseArray<String> sparseArray = this.V3;
        if (sparseArray.get(i2) != null) {
            return;
        }
        int i3 = this.O3;
        if (i2 < i3 || i2 > this.P3) {
            strZ = "";
        } else {
            String[] strArr = this.N3;
            strZ = strArr != null ? strArr[i2 - i3] : z(i2);
        }
        sparseArray.put(i2, strZ);
    }

    public final boolean x() {
        int i2 = this.a4 - this.b4;
        if (i2 == 0) {
            return false;
        }
        this.e4 = 0;
        int iAbs = Math.abs(i2);
        int i3 = this.Z3;
        if (iAbs > i3 / 2) {
            if (i2 > 0) {
                i3 = -i3;
            }
            i2 += i3;
        }
        this.d4.startScroll(0, 0, 0, i2, 800);
        invalidate();
        return true;
    }

    public final void y(int i2) {
        this.e4 = 0;
        if (i2 > 0) {
            this.c4.fling(0, 0, 0, i2, 0, 0, 0, Integer.MAX_VALUE);
        } else {
            this.c4.fling(0, Integer.MAX_VALUE, 0, i2, 0, 0, 0, Integer.MAX_VALUE);
        }
        invalidate();
    }

    public final String z(int i2) {
        f fVar = this.T3;
        return fVar != null ? fVar.format(i2) : A(i2);
    }

    public MyNumberPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.numberPickerStyle);
    }

    public MyNumberPicker(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public MyNumberPicker(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.s = true;
        this.U3 = 300L;
        this.V3 = new SparseArray<>();
        this.W3 = new int[3];
        this.a4 = Integer.MIN_VALUE;
        this.u4 = 0;
        this.C4 = -1;
        this.E4 = context;
        int[] iArr = c.a.d.c.Z;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, i3);
        saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, i2, i3);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, R.layout.number_picker);
        this.r4 = true;
        this.D4 = typedArrayObtainStyledAttributes.getBoolean(0, false);
        this.q4 = typedArrayObtainStyledAttributes.getColor(10, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(7);
        if (drawable != null) {
            drawable.setCallback(this);
            drawable.setLayoutDirection(getLayoutDirection());
            if (drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
        }
        this.s4 = this.E4.getDrawable(android.R.drawable.ic_input_add);
        this.t4 = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics()));
        this.F3 = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, -1);
        this.G3 = dimensionPixelSize;
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, -1);
        this.H3 = dimensionPixelSize2;
        if (dimensionPixelSize != -1 && dimensionPixelSize2 != -1 && dimensionPixelSize > dimensionPixelSize2) {
            throw new IllegalArgumentException("minHeight > maxHeight");
        }
        int dimensionPixelSize3 = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, -1);
        this.I3 = dimensionPixelSize3;
        int dimensionPixelSize4 = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, -1);
        this.J3 = dimensionPixelSize4;
        if (dimensionPixelSize3 != -1 && dimensionPixelSize4 != -1 && dimensionPixelSize3 > dimensionPixelSize4) {
            throw new IllegalArgumentException("minWidth > maxWidth");
        }
        this.K3 = dimensionPixelSize4 == -1;
        this.Y3 = typedArrayObtainStyledAttributes.getDrawable(11);
        typedArrayObtainStyledAttributes.recycle();
        this.B4 = new j();
        setWillNotDraw(!true);
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(resourceId, (ViewGroup) this, true);
        new a();
        new b();
        this.x = null;
        this.y = null;
        EditText editText = (EditText) findViewById(R.id.numberpicker_input);
        this.E3 = editText;
        editText.setOnFocusChangeListener(new c());
        editText.setFilters(new InputFilter[]{new g()});
        editText.setRawInputType(2);
        editText.setImeOptions(6);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.m4 = viewConfiguration.getScaledTouchSlop();
        this.n4 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.o4 = viewConfiguration.getScaledMaximumFlingVelocity() / 8;
        int textSize = (int) editText.getTextSize();
        this.L3 = textSize;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(textSize);
        paint.setTypeface(editText.getTypeface());
        paint.setColor(editText.getTextColors().getColorForState(LinearLayout.ENABLED_STATE_SET, -1));
        this.X3 = paint;
        this.c4 = new Scroller(getContext(), null, true);
        this.d4 = new Scroller(getContext(), new DecelerateInterpolator(2.5f));
        X();
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        if (getFocusable() == 16) {
            setFocusable(1);
            setFocusableInTouchMode(true);
        }
    }
}
