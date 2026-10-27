package com.google.android.material.textfield;

import a.b.p.e0;
import a.b.p.z;
import a.h.k.q;
import android.R;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import b.d.a.a.h0.m;
import b.d.a.a.h0.n;
import b.d.a.a.i;
import b.d.a.a.j;
import com.google.android.material.internal.CheckableImageButton;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class TextInputLayout extends LinearLayout {
    public static final int J0 = j.Widget_Design_TextInputLayout;
    public boolean A;
    public int A0;
    public CharSequence B;
    public int B0;
    public boolean C;
    public boolean C0;
    public b.d.a.a.e0.g D;
    public final b.d.a.a.z.c D0;
    public b.d.a.a.e0.g E;
    public boolean E0;
    public b.d.a.a.e0.j F;
    public boolean F0;
    public final int G;
    public ValueAnimator G0;
    public int H;
    public boolean H0;
    public int I;
    public boolean I0;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public final Rect P;
    public final Rect Q;
    public final RectF R;
    public Typeface S;
    public final CheckableImageButton T;
    public ColorStateList U;
    public boolean V;
    public PorterDuff.Mode W;
    public boolean X;
    public Drawable Y;
    public int Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final FrameLayout f2466a;
    public View.OnLongClickListener a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinearLayout f2467b;
    public final LinkedHashSet<f> b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinearLayout f2468c;
    public int c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final FrameLayout f2469d;
    public final SparseArray<m> d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public EditText f2470e;
    public final CheckableImageButton e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f2471f;
    public final LinkedHashSet<g> f0;
    public int g;
    public ColorStateList g0;
    public int h;
    public boolean h0;
    public final n i;
    public PorterDuff.Mode i0;
    public boolean j;
    public boolean j0;
    public int k;
    public Drawable k0;
    public boolean l;
    public int l0;
    public TextView m;
    public Drawable m0;
    public int n;
    public View.OnLongClickListener n0;
    public int o;
    public View.OnLongClickListener o0;
    public CharSequence p;
    public final CheckableImageButton p0;
    public boolean q;
    public ColorStateList q0;
    public TextView r;
    public ColorStateList r0;
    public ColorStateList s;
    public ColorStateList s0;
    public int t;
    public int t0;
    public ColorStateList u;
    public int u0;
    public ColorStateList v;
    public int v0;
    public CharSequence w;
    public ColorStateList w0;
    public final TextView x;
    public int x0;
    public CharSequence y;
    public int y0;
    public final TextView z;
    public int z0;

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            TextInputLayout.this.y(!r0.I0, false);
            TextInputLayout textInputLayout = TextInputLayout.this;
            if (textInputLayout.j) {
                textInputLayout.t(editable.length());
            }
            TextInputLayout textInputLayout2 = TextInputLayout.this;
            if (textInputLayout2.q) {
                textInputLayout2.z(editable.length());
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.e0.performClick();
            TextInputLayout.this.e0.jumpDrawablesToCurrentState();
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.this.f2470e.requestLayout();
        }
    }

    public class d implements ValueAnimator.AnimatorUpdateListener {
        public d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TextInputLayout.this.D0.o(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public static class e extends a.h.k.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final TextInputLayout f2476d;

        public e(TextInputLayout textInputLayout) {
            this.f2476d = textInputLayout;
        }

        @Override // a.h.k.a
        public void d(View view, a.h.k.x.b bVar) {
            this.f807a.onInitializeAccessibilityNodeInfo(view, bVar.f856a);
            EditText editText = this.f2476d.getEditText();
            Editable text = editText != null ? editText.getText() : null;
            CharSequence hint = this.f2476d.getHint();
            CharSequence error = this.f2476d.getError();
            CharSequence placeholderText = this.f2476d.getPlaceholderText();
            int counterMaxLength = this.f2476d.getCounterMaxLength();
            CharSequence counterOverflowDescription = this.f2476d.getCounterOverflowDescription();
            boolean z = !TextUtils.isEmpty(text);
            boolean z2 = !TextUtils.isEmpty(hint);
            boolean z3 = !this.f2476d.C0;
            boolean z4 = !TextUtils.isEmpty(error);
            boolean z5 = z4 || !TextUtils.isEmpty(counterOverflowDescription);
            String string = z2 ? hint.toString() : "";
            if (z) {
                bVar.f856a.setText(text);
            } else if (!TextUtils.isEmpty(string)) {
                bVar.f856a.setText(string);
                if (z3 && placeholderText != null) {
                    bVar.f856a.setText(string + ", " + ((Object) placeholderText));
                }
            } else if (placeholderText != null) {
                bVar.f856a.setText(placeholderText);
            }
            if (!TextUtils.isEmpty(string)) {
                bVar.f856a.setHintText(string);
                bVar.f856a.setShowingHintText(!z);
            }
            if (text == null || text.length() != counterMaxLength) {
                counterMaxLength = -1;
            }
            bVar.f856a.setMaxTextLength(counterMaxLength);
            if (z5) {
                if (!z4) {
                    error = counterOverflowDescription;
                }
                bVar.f856a.setError(error);
            }
            if (editText != null) {
                editText.setLabelFor(b.d.a.a.f.textinput_helper_text);
            }
        }
    }

    public interface f {
        void a(TextInputLayout textInputLayout);
    }

    public interface g {
        void a(TextInputLayout textInputLayout, int i);
    }

    public static class h extends a.j.a.a {
        public static final Parcelable.Creator<h> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public CharSequence f2477c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f2478d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public CharSequence f2479e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public CharSequence f2480f;
        public CharSequence g;

        public static class a implements Parcelable.ClassLoaderCreator<h> {
            @Override // android.os.Parcelable.Creator
            public Object createFromParcel(Parcel parcel) {
                return new h(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            public Object[] newArray(int i) {
                return new h[i];
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public h createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new h(parcel, classLoader);
            }
        }

        public h(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2477c = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f2478d = parcel.readInt() == 1;
            this.f2479e = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f2480f = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }

        public h(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder sbD = b.a.a.a.a.d("TextInputLayout.SavedState{");
            sbD.append(Integer.toHexString(System.identityHashCode(this)));
            sbD.append(" error=");
            sbD.append((Object) this.f2477c);
            sbD.append(" hint=");
            sbD.append((Object) this.f2479e);
            sbD.append(" helperText=");
            sbD.append((Object) this.f2480f);
            sbD.append(" placeholderText=");
            sbD.append((Object) this.g);
            sbD.append("}");
            return sbD.toString();
        }

        @Override // a.j.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f892a, i);
            TextUtils.writeToParcel(this.f2477c, parcel, i);
            parcel.writeInt(this.f2478d ? 1 : 0);
            TextUtils.writeToParcel(this.f2479e, parcel, i);
            TextUtils.writeToParcel(this.f2480f, parcel, i);
            TextUtils.writeToParcel(this.g, parcel, i);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Removed duplicated region for block: B:98:0x054f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public TextInputLayout(android.content.Context r21, android.util.AttributeSet r22) {
        /*
            Method dump skipped, instruction units count: 1690
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    private m getEndIconDelegate() {
        m mVar = this.d0.get(this.c0);
        return mVar != null ? mVar : this.d0.get(0);
    }

    private CheckableImageButton getEndIconToUpdateDummyDrawable() {
        if (this.p0.getVisibility() == 0) {
            return this.p0;
        }
        if (j() && k()) {
            return this.e0;
        }
        return null;
    }

    public static void n(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                n((ViewGroup) childAt, z);
            }
        }
    }

    public static void q(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        boolean zY = q.y(checkableImageButton);
        boolean z = onLongClickListener != null;
        boolean z2 = zY || z;
        checkableImageButton.setFocusable(z2);
        checkableImageButton.setClickable(zY);
        checkableImageButton.setPressable(zY);
        checkableImageButton.setLongClickable(z);
        checkableImageButton.setImportantForAccessibility(z2 ? 1 : 2);
    }

    private void setEditText(EditText editText) {
        if (this.f2470e != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (this.c0 != 3 && !(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.f2470e = editText;
        setMinWidth(this.g);
        setMaxWidth(this.h);
        l();
        setTextInputAccessibilityDelegate(new e(this));
        this.D0.q(this.f2470e.getTypeface());
        b.d.a.a.z.c cVar = this.D0;
        float textSize = this.f2470e.getTextSize();
        if (cVar.j != textSize) {
            cVar.j = textSize;
            cVar.k();
        }
        int gravity = this.f2470e.getGravity();
        this.D0.n((gravity & (-113)) | 48);
        b.d.a.a.z.c cVar2 = this.D0;
        if (cVar2.h != gravity) {
            cVar2.h = gravity;
            cVar2.k();
        }
        this.f2470e.addTextChangedListener(new a());
        if (this.r0 == null) {
            this.r0 = this.f2470e.getHintTextColors();
        }
        if (this.A) {
            if (TextUtils.isEmpty(this.B)) {
                CharSequence hint = this.f2470e.getHint();
                this.f2471f = hint;
                setHint(hint);
                this.f2470e.setHint((CharSequence) null);
            }
            this.C = true;
        }
        if (this.m != null) {
            t(this.f2470e.getText().length());
        }
        w();
        this.i.b();
        this.f2467b.bringToFront();
        this.f2468c.bringToFront();
        this.f2469d.bringToFront();
        this.p0.bringToFront();
        Iterator<f> it = this.b0.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
        A();
        D();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        y(false, true);
    }

    private void setErrorIconVisible(boolean z) {
        this.p0.setVisibility(z ? 0 : 8);
        this.f2469d.setVisibility(z ? 8 : 0);
        D();
        if (j()) {
            return;
        }
        v();
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.B)) {
            return;
        }
        this.B = charSequence;
        b.d.a.a.z.c cVar = this.D0;
        if (charSequence == null || !TextUtils.equals(cVar.x, charSequence)) {
            cVar.x = charSequence;
            cVar.y = null;
            Bitmap bitmap = cVar.C;
            if (bitmap != null) {
                bitmap.recycle();
                cVar.C = null;
            }
            cVar.k();
        }
        if (this.C0) {
            return;
        }
        m();
    }

    private void setPlaceholderTextEnabled(boolean z) {
        if (this.q == z) {
            return;
        }
        if (z) {
            z zVar = new z(getContext(), null);
            this.r = zVar;
            zVar.setId(b.d.a.a.f.textinput_placeholder);
            q.W(this.r, 1);
            setPlaceholderTextAppearance(this.t);
            setPlaceholderTextColor(this.s);
            TextView textView = this.r;
            if (textView != null) {
                this.f2466a.addView(textView);
                this.r.setVisibility(0);
            }
        } else {
            TextView textView2 = this.r;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            this.r = null;
        }
        this.q = z;
    }

    public final void A() {
        if (this.f2470e == null) {
            return;
        }
        q.d0(this.x, this.T.getVisibility() == 0 ? 0 : q.t(this.f2470e), this.f2470e.getCompoundPaddingTop(), getContext().getResources().getDimensionPixelSize(b.d.a.a.d.material_input_text_to_prefix_suffix_padding), this.f2470e.getCompoundPaddingBottom());
    }

    public final void B() {
        this.x.setVisibility((this.w == null || this.C0) ? 8 : 0);
        v();
    }

    public final void C(boolean z, boolean z2) {
        int defaultColor = this.w0.getDefaultColor();
        int colorForState = this.w0.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.w0.getColorForState(new int[]{R.attr.state_activated, R.attr.state_enabled}, defaultColor);
        if (z) {
            this.N = colorForState2;
        } else if (z2) {
            this.N = colorForState;
        } else {
            this.N = defaultColor;
        }
    }

    public final void D() {
        if (this.f2470e == null) {
            return;
        }
        int iS = 0;
        if (!k()) {
            if (!(this.p0.getVisibility() == 0)) {
                iS = q.s(this.f2470e);
            }
        }
        q.d0(this.z, getContext().getResources().getDimensionPixelSize(b.d.a.a.d.material_input_text_to_prefix_suffix_padding), this.f2470e.getPaddingTop(), iS, this.f2470e.getPaddingBottom());
    }

    public final void E() {
        int visibility = this.z.getVisibility();
        boolean z = (this.y == null || this.C0) ? false : true;
        this.z.setVisibility(z ? 0 : 8);
        if (visibility != this.z.getVisibility()) {
            getEndIconDelegate().c(z);
        }
        v();
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0106  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void F() {
        /*
            Method dump skipped, instruction units count: 291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.F():void");
    }

    public void a(f fVar) {
        this.b0.add(fVar);
        if (this.f2470e != null) {
            fVar.a(this);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        this.f2466a.addView(view, layoutParams2);
        this.f2466a.setLayoutParams(layoutParams);
        x();
        setEditText((EditText) view);
    }

    public void b(float f2) {
        if (this.D0.f2164c == f2) {
            return;
        }
        if (this.G0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.G0 = valueAnimator;
            valueAnimator.setInterpolator(b.d.a.a.l.a.f1967b);
            this.G0.setDuration(167L);
            this.G0.addUpdateListener(new d());
        }
        this.G0.setFloatValues(this.D0.f2164c, f2);
        this.G0.start();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c() {
        /*
            r6 = this;
            b.d.a.a.e0.g r0 = r6.D
            if (r0 != 0) goto L5
            return
        L5:
            b.d.a.a.e0.j r1 = r6.F
            r0.setShapeAppearanceModel(r1)
            int r0 = r6.I
            r1 = 2
            r2 = -1
            r3 = 0
            r4 = 1
            if (r0 != r1) goto L21
            int r0 = r6.K
            if (r0 <= r2) goto L1c
            int r0 = r6.N
            if (r0 == 0) goto L1c
            r0 = r4
            goto L1d
        L1c:
            r0 = r3
        L1d:
            if (r0 == 0) goto L21
            r0 = r4
            goto L22
        L21:
            r0 = r3
        L22:
            if (r0 == 0) goto L2e
            b.d.a.a.e0.g r0 = r6.D
            int r1 = r6.K
            float r1 = (float) r1
            int r5 = r6.N
            r0.s(r1, r5)
        L2e:
            int r0 = r6.O
            int r1 = r6.I
            if (r1 != r4) goto L44
            int r0 = b.d.a.a.b.colorSurface
            android.content.Context r1 = r6.getContext()
            int r0 = a.b.k.l.i.w(r1, r0, r3)
            int r1 = r6.O
            int r0 = a.h.e.a.a(r1, r0)
        L44:
            r6.O = r0
            b.d.a.a.e0.g r1 = r6.D
            android.content.res.ColorStateList r0 = android.content.res.ColorStateList.valueOf(r0)
            r1.q(r0)
            int r0 = r6.c0
            r1 = 3
            if (r0 != r1) goto L5d
            android.widget.EditText r0 = r6.f2470e
            android.graphics.drawable.Drawable r0 = r0.getBackground()
            r0.invalidateSelf()
        L5d:
            b.d.a.a.e0.g r0 = r6.E
            if (r0 != 0) goto L62
            goto L7b
        L62:
            int r0 = r6.K
            if (r0 <= r2) goto L6b
            int r0 = r6.N
            if (r0 == 0) goto L6b
            r3 = r4
        L6b:
            if (r3 == 0) goto L78
            b.d.a.a.e0.g r0 = r6.E
            int r1 = r6.N
            android.content.res.ColorStateList r1 = android.content.res.ColorStateList.valueOf(r1)
            r0.q(r1)
        L78:
            r6.invalidate()
        L7b:
            r6.invalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.c():void");
    }

    public final void d() {
        e(this.e0, this.h0, this.g0, this.j0, this.i0);
    }

    @Override // android.view.ViewGroup, android.view.View
    @TargetApi(26)
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        EditText editText = this.f2470e;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i);
            return;
        }
        if (this.f2471f != null) {
            boolean z = this.C;
            this.C = false;
            CharSequence hint = editText.getHint();
            this.f2470e.setHint(this.f2471f);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i);
                return;
            } finally {
                this.f2470e.setHint(hint);
                this.C = z;
            }
        }
        viewStructure.setAutofillId(getAutofillId());
        onProvideAutofillStructure(viewStructure, i);
        onProvideAutofillVirtualStructure(viewStructure, i);
        viewStructure.setChildCount(this.f2466a.getChildCount());
        for (int i2 = 0; i2 < this.f2466a.getChildCount(); i2++) {
            View childAt = this.f2466a.getChildAt(i2);
            ViewStructure viewStructureNewChild = viewStructure.newChild(i2);
            childAt.dispatchProvideAutofillStructure(viewStructureNewChild, i);
            if (childAt == this.f2470e) {
                viewStructureNewChild.setHint(getHint());
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.I0 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.I0 = false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.A) {
            b.d.a.a.z.c cVar = this.D0;
            if (cVar == null) {
                throw null;
            }
            int iSave = canvas.save();
            if (cVar.y != null && cVar.f2163b) {
                float lineStart = (cVar.r + (cVar.W > 1 ? cVar.R.getLineStart(0) : cVar.R.getLineLeft(0))) - (cVar.U * 2.0f);
                cVar.I.setTextSize(cVar.F);
                float f2 = cVar.r;
                float f3 = cVar.s;
                boolean z = cVar.B && cVar.C != null;
                float f4 = cVar.E;
                if (f4 != 1.0f) {
                    canvas.scale(f4, f4, f2, f3);
                }
                if (z) {
                    canvas.drawBitmap(cVar.C, f2, f3, cVar.D);
                } else if (cVar.r()) {
                    int alpha = cVar.I.getAlpha();
                    canvas.translate(lineStart, f3);
                    float f5 = alpha;
                    cVar.I.setAlpha((int) (cVar.T * f5));
                    cVar.R.draw(canvas);
                    cVar.I.setAlpha((int) (cVar.S * f5));
                    int lineBaseline = cVar.R.getLineBaseline(0);
                    CharSequence charSequence = cVar.V;
                    float f6 = lineBaseline;
                    canvas.drawText(charSequence, 0, charSequence.length(), 0.0f, f6, cVar.I);
                    String strTrim = cVar.V.toString().trim();
                    if (strTrim.endsWith("…")) {
                        strTrim = strTrim.substring(0, strTrim.length() - 1);
                    }
                    String str = strTrim;
                    cVar.I.setAlpha(alpha);
                    canvas.drawText(str, 0, Math.min(cVar.R.getLineEnd(0), str.length()), 0.0f, f6, (Paint) cVar.I);
                } else {
                    canvas.translate(f2, f3);
                    cVar.R.draw(canvas);
                }
                canvas.restoreToCount(iSave);
            }
        }
        b.d.a.a.e0.g gVar = this.E;
        if (gVar != null) {
            Rect bounds = gVar.getBounds();
            bounds.top = bounds.bottom - this.K;
            this.E.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        boolean z;
        ColorStateList colorStateList;
        boolean z2;
        if (this.H0) {
            return;
        }
        this.H0 = true;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        b.d.a.a.z.c cVar = this.D0;
        if (cVar != null) {
            cVar.G = drawableState;
            ColorStateList colorStateList2 = cVar.m;
            if ((colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = cVar.l) != null && colorStateList.isStateful())) {
                cVar.k();
                z2 = true;
            } else {
                z2 = false;
            }
            z = z2 | false;
        } else {
            z = false;
        }
        if (this.f2470e != null) {
            y(q.C(this) && isEnabled(), false);
        }
        w();
        F();
        if (z) {
            invalidate();
        }
        this.H0 = false;
    }

    public final void e(CheckableImageButton checkableImageButton, boolean z, ColorStateList colorStateList, boolean z2, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z || z2)) {
            drawable = drawable.mutate();
            if (z) {
                drawable.setTintList(colorStateList);
            }
            if (z2) {
                drawable.setTintMode(mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public final int f() {
        float f2;
        if (!this.A) {
            return 0;
        }
        int i = this.I;
        if (i == 0 || i == 1) {
            f2 = this.D0.f();
        } else {
            if (i != 2) {
                return 0;
            }
            f2 = this.D0.f() / 2.0f;
        }
        return (int) f2;
    }

    public final boolean g() {
        return this.A && !TextUtils.isEmpty(this.B) && (this.D instanceof b.d.a.a.h0.g);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.f2470e;
        if (editText == null) {
            return super.getBaseline();
        }
        return f() + getPaddingTop() + editText.getBaseline();
    }

    public b.d.a.a.e0.g getBoxBackground() {
        int i = this.I;
        if (i == 1 || i == 2) {
            return this.D;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.O;
    }

    public int getBoxBackgroundMode() {
        return this.I;
    }

    public float getBoxCornerRadiusBottomEnd() {
        b.d.a.a.e0.g gVar = this.D;
        return gVar.f1828a.f1835a.h.a(gVar.i());
    }

    public float getBoxCornerRadiusBottomStart() {
        b.d.a.a.e0.g gVar = this.D;
        return gVar.f1828a.f1835a.g.a(gVar.i());
    }

    public float getBoxCornerRadiusTopEnd() {
        b.d.a.a.e0.g gVar = this.D;
        return gVar.f1828a.f1835a.f1848f.a(gVar.i());
    }

    public float getBoxCornerRadiusTopStart() {
        return this.D.m();
    }

    public int getBoxStrokeColor() {
        return this.v0;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.w0;
    }

    public int getBoxStrokeWidth() {
        return this.L;
    }

    public int getBoxStrokeWidthFocused() {
        return this.M;
    }

    public int getCounterMaxLength() {
        return this.k;
    }

    public CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (this.j && this.l && (textView = this.m) != null) {
            return textView.getContentDescription();
        }
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.u;
    }

    public ColorStateList getCounterTextColor() {
        return this.u;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.r0;
    }

    public EditText getEditText() {
        return this.f2470e;
    }

    public CharSequence getEndIconContentDescription() {
        return this.e0.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.e0.getDrawable();
    }

    public int getEndIconMode() {
        return this.c0;
    }

    public CheckableImageButton getEndIconView() {
        return this.e0;
    }

    public CharSequence getError() {
        n nVar = this.i;
        if (nVar.k) {
            return nVar.j;
        }
        return null;
    }

    public CharSequence getErrorContentDescription() {
        return this.i.m;
    }

    public int getErrorCurrentTextColors() {
        return this.i.g();
    }

    public Drawable getErrorIconDrawable() {
        return this.p0.getDrawable();
    }

    public final int getErrorTextCurrentColor() {
        return this.i.g();
    }

    public CharSequence getHelperText() {
        n nVar = this.i;
        if (nVar.q) {
            return nVar.p;
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        TextView textView = this.i.r;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public CharSequence getHint() {
        if (this.A) {
            return this.B;
        }
        return null;
    }

    public final float getHintCollapsedTextHeight() {
        return this.D0.f();
    }

    public final int getHintCurrentCollapsedTextColor() {
        return this.D0.g();
    }

    public ColorStateList getHintTextColor() {
        return this.s0;
    }

    public int getMaxWidth() {
        return this.h;
    }

    public int getMinWidth() {
        return this.g;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.e0.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.e0.getDrawable();
    }

    public CharSequence getPlaceholderText() {
        if (this.q) {
            return this.p;
        }
        return null;
    }

    public int getPlaceholderTextAppearance() {
        return this.t;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.s;
    }

    public CharSequence getPrefixText() {
        return this.w;
    }

    public ColorStateList getPrefixTextColor() {
        return this.x.getTextColors();
    }

    public TextView getPrefixTextView() {
        return this.x;
    }

    public CharSequence getStartIconContentDescription() {
        return this.T.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.T.getDrawable();
    }

    public CharSequence getSuffixText() {
        return this.y;
    }

    public ColorStateList getSuffixTextColor() {
        return this.z.getTextColors();
    }

    public TextView getSuffixTextView() {
        return this.z;
    }

    public Typeface getTypeface() {
        return this.S;
    }

    public final int h(int i, boolean z) {
        int compoundPaddingLeft = this.f2470e.getCompoundPaddingLeft() + i;
        return (this.w == null || z) ? compoundPaddingLeft : (compoundPaddingLeft - this.x.getMeasuredWidth()) + this.x.getPaddingLeft();
    }

    public final int i(int i, boolean z) {
        int compoundPaddingRight = i - this.f2470e.getCompoundPaddingRight();
        return (this.w == null || !z) ? compoundPaddingRight : compoundPaddingRight + (this.x.getMeasuredWidth() - this.x.getPaddingRight());
    }

    public final boolean j() {
        return this.c0 != 0;
    }

    public boolean k() {
        return this.f2469d.getVisibility() == 0 && this.e0.getVisibility() == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l() {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.l():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m() {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.m():void");
    }

    public void o() {
        p(this.e0, this.g0);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0177  */
    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        /*
            Method dump skipped, instruction units count: 383
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        EditText editText;
        int iMax;
        super.onMeasure(i, i2);
        boolean z = false;
        if (this.f2470e != null && this.f2470e.getMeasuredHeight() < (iMax = Math.max(this.f2468c.getMeasuredHeight(), this.f2467b.getMeasuredHeight()))) {
            this.f2470e.setMinimumHeight(iMax);
            z = true;
        }
        boolean zV = v();
        if (z || zV) {
            this.f2470e.post(new c());
        }
        if (this.r != null && (editText = this.f2470e) != null) {
            this.r.setGravity(editText.getGravity());
            this.r.setPadding(this.f2470e.getCompoundPaddingLeft(), this.f2470e.getCompoundPaddingTop(), this.f2470e.getCompoundPaddingRight(), this.f2470e.getCompoundPaddingBottom());
        }
        A();
        D();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof h)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        h hVar = (h) parcelable;
        super.onRestoreInstanceState(hVar.f892a);
        setError(hVar.f2477c);
        if (hVar.f2478d) {
            this.e0.post(new b());
        }
        setHint(hVar.f2479e);
        setHelperText(hVar.f2480f);
        setPlaceholderText(hVar.g);
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        h hVar = new h(super.onSaveInstanceState());
        if (this.i.e()) {
            hVar.f2477c = getError();
        }
        hVar.f2478d = j() && this.e0.isChecked();
        hVar.f2479e = getHint();
        hVar.f2480f = getHelperText();
        hVar.g = getPlaceholderText();
        return hVar;
    }

    public final void p(CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() == null || colorStateList == null || !colorStateList.isStateful()) {
            return;
        }
        int[] drawableState = getDrawableState();
        int[] drawableState2 = checkableImageButton.getDrawableState();
        int length = drawableState.length;
        int[] iArrCopyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
        System.arraycopy(drawableState2, 0, iArrCopyOf, length, drawableState2.length);
        int colorForState = colorStateList.getColorForState(iArrCopyOf, colorStateList.getDefaultColor());
        Drawable drawableMutate = drawable.mutate();
        drawableMutate.setTintList(ColorStateList.valueOf(colorForState));
        checkableImageButton.setImageDrawable(drawableMutate);
    }

    public void r(TextView textView, int i) {
        boolean z = true;
        try {
            textView.setTextAppearance(i);
            if (textView.getTextColors().getDefaultColor() != -65281) {
                z = false;
            }
        } catch (Exception unused) {
        }
        if (z) {
            textView.setTextAppearance(j.TextAppearance_AppCompat_Caption);
            textView.setTextColor(a.h.d.a.a(getContext(), b.d.a.a.c.design_error));
        }
    }

    public final void s() {
        if (this.m != null) {
            EditText editText = this.f2470e;
            t(editText == null ? 0 : editText.getText().length());
        }
    }

    public void setBoxBackgroundColor(int i) {
        if (this.O != i) {
            this.O = i;
            this.x0 = i;
            this.z0 = i;
            this.A0 = i;
            c();
        }
    }

    public void setBoxBackgroundColorResource(int i) {
        setBoxBackgroundColor(a.h.d.a.a(getContext(), i));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.x0 = defaultColor;
        this.O = defaultColor;
        this.y0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.z0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        this.A0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
        c();
    }

    public void setBoxBackgroundMode(int i) {
        if (i == this.I) {
            return;
        }
        this.I = i;
        if (this.f2470e != null) {
            l();
        }
    }

    public void setBoxStrokeColor(int i) {
        if (this.v0 != i) {
            this.v0 = i;
            F();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        int defaultColor;
        if (!colorStateList.isStateful()) {
            if (this.v0 != colorStateList.getDefaultColor()) {
                defaultColor = colorStateList.getDefaultColor();
            }
            F();
        } else {
            this.t0 = colorStateList.getDefaultColor();
            this.B0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.u0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            defaultColor = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        }
        this.v0 = defaultColor;
        F();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.w0 != colorStateList) {
            this.w0 = colorStateList;
            F();
        }
    }

    public void setBoxStrokeWidth(int i) {
        this.L = i;
        F();
    }

    public void setBoxStrokeWidthFocused(int i) {
        this.M = i;
        F();
    }

    public void setBoxStrokeWidthFocusedResource(int i) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i));
    }

    public void setBoxStrokeWidthResource(int i) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i));
    }

    public void setCounterEnabled(boolean z) {
        if (this.j != z) {
            if (z) {
                z zVar = new z(getContext(), null);
                this.m = zVar;
                zVar.setId(b.d.a.a.f.textinput_counter);
                Typeface typeface = this.S;
                if (typeface != null) {
                    this.m.setTypeface(typeface);
                }
                this.m.setMaxLines(1);
                this.i.a(this.m, 2);
                ((ViewGroup.MarginLayoutParams) this.m.getLayoutParams()).setMarginStart(getResources().getDimensionPixelOffset(b.d.a.a.d.mtrl_textinput_counter_margin_start));
                u();
                s();
            } else {
                this.i.j(this.m, 2);
                this.m = null;
            }
            this.j = z;
        }
    }

    public void setCounterMaxLength(int i) {
        if (this.k != i) {
            if (i <= 0) {
                i = -1;
            }
            this.k = i;
            if (this.j) {
                s();
            }
        }
    }

    public void setCounterOverflowTextAppearance(int i) {
        if (this.n != i) {
            this.n = i;
            u();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.v != colorStateList) {
            this.v = colorStateList;
            u();
        }
    }

    public void setCounterTextAppearance(int i) {
        if (this.o != i) {
            this.o = i;
            u();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.u != colorStateList) {
            this.u = colorStateList;
            u();
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.r0 = colorStateList;
        this.s0 = colorStateList;
        if (this.f2470e != null) {
            y(false, false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        n(this, z);
        super.setEnabled(z);
    }

    public void setEndIconActivated(boolean z) {
        this.e0.setActivated(z);
    }

    public void setEndIconCheckable(boolean z) {
        this.e0.setCheckable(z);
    }

    public void setEndIconContentDescription(int i) {
        setEndIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        if (getEndIconContentDescription() != charSequence) {
            this.e0.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(int i) {
        setEndIconDrawable(i != 0 ? a.b.l.a.a.b(getContext(), i) : null);
    }

    public void setEndIconDrawable(Drawable drawable) {
        this.e0.setImageDrawable(drawable);
        o();
    }

    public void setEndIconMode(int i) {
        int i2 = this.c0;
        this.c0 = i;
        Iterator<g> it = this.f0.iterator();
        while (it.hasNext()) {
            it.next().a(this, i2);
        }
        setEndIconVisible(i != 0);
        if (getEndIconDelegate().b(this.I)) {
            getEndIconDelegate().a();
            d();
        } else {
            StringBuilder sbD = b.a.a.a.a.d("The current box background mode ");
            sbD.append(this.I);
            sbD.append(" is not supported by the end icon mode ");
            sbD.append(i);
            throw new IllegalStateException(sbD.toString());
        }
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        CheckableImageButton checkableImageButton = this.e0;
        View.OnLongClickListener onLongClickListener = this.n0;
        checkableImageButton.setOnClickListener(onClickListener);
        q(checkableImageButton, onLongClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.n0 = onLongClickListener;
        CheckableImageButton checkableImageButton = this.e0;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        q(checkableImageButton, onLongClickListener);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        if (this.g0 != colorStateList) {
            this.g0 = colorStateList;
            this.h0 = true;
            d();
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        if (this.i0 != mode) {
            this.i0 = mode;
            this.j0 = true;
            d();
        }
    }

    public void setEndIconVisible(boolean z) {
        if (k() != z) {
            this.e0.setVisibility(z ? 0 : 8);
            D();
            v();
        }
    }

    public void setError(CharSequence charSequence) {
        if (!this.i.k) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        boolean zIsEmpty = TextUtils.isEmpty(charSequence);
        n nVar = this.i;
        if (zIsEmpty) {
            nVar.i();
            return;
        }
        nVar.c();
        nVar.j = charSequence;
        nVar.l.setText(charSequence);
        if (nVar.h != 1) {
            nVar.i = 1;
        }
        nVar.l(nVar.h, nVar.i, nVar.k(nVar.l, charSequence));
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        n nVar = this.i;
        nVar.m = charSequence;
        TextView textView = nVar.l;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    public void setErrorEnabled(boolean z) {
        n nVar = this.i;
        if (nVar.k == z) {
            return;
        }
        nVar.c();
        if (z) {
            z zVar = new z(nVar.f1930a, null);
            nVar.l = zVar;
            zVar.setId(b.d.a.a.f.textinput_error);
            nVar.l.setTextAlignment(5);
            Typeface typeface = nVar.u;
            if (typeface != null) {
                nVar.l.setTypeface(typeface);
            }
            int i = nVar.n;
            nVar.n = i;
            TextView textView = nVar.l;
            if (textView != null) {
                nVar.f1931b.r(textView, i);
            }
            ColorStateList colorStateList = nVar.o;
            nVar.o = colorStateList;
            TextView textView2 = nVar.l;
            if (textView2 != null && colorStateList != null) {
                textView2.setTextColor(colorStateList);
            }
            CharSequence charSequence = nVar.m;
            nVar.m = charSequence;
            TextView textView3 = nVar.l;
            if (textView3 != null) {
                textView3.setContentDescription(charSequence);
            }
            nVar.l.setVisibility(4);
            q.W(nVar.l, 1);
            nVar.a(nVar.l, 0);
        } else {
            nVar.i();
            nVar.j(nVar.l, 0);
            nVar.l = null;
            nVar.f1931b.w();
            nVar.f1931b.F();
        }
        nVar.k = z;
    }

    public void setErrorIconDrawable(int i) {
        setErrorIconDrawable(i != 0 ? a.b.l.a.a.b(getContext(), i) : null);
        p(this.p0, this.q0);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        CheckableImageButton checkableImageButton = this.p0;
        View.OnLongClickListener onLongClickListener = this.o0;
        checkableImageButton.setOnClickListener(onClickListener);
        q(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.o0 = onLongClickListener;
        CheckableImageButton checkableImageButton = this.p0;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        q(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        this.q0 = colorStateList;
        Drawable drawable = this.p0.getDrawable();
        if (drawable != null) {
            drawable = drawable.mutate();
            drawable.setTintList(colorStateList);
        }
        if (this.p0.getDrawable() != drawable) {
            this.p0.setImageDrawable(drawable);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.p0.getDrawable();
        if (drawable != null) {
            drawable = drawable.mutate();
            drawable.setTintMode(mode);
        }
        if (this.p0.getDrawable() != drawable) {
            this.p0.setImageDrawable(drawable);
        }
    }

    public void setErrorTextAppearance(int i) {
        n nVar = this.i;
        nVar.n = i;
        TextView textView = nVar.l;
        if (textView != null) {
            nVar.f1931b.r(textView, i);
        }
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        n nVar = this.i;
        nVar.o = colorStateList;
        TextView textView = nVar.l;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    public void setExpandedHintEnabled(boolean z) {
        if (this.E0 != z) {
            this.E0 = z;
            y(false, false);
        }
    }

    public void setHelperText(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            if (this.i.q) {
                setHelperTextEnabled(false);
                return;
            }
            return;
        }
        if (!this.i.q) {
            setHelperTextEnabled(true);
        }
        n nVar = this.i;
        nVar.c();
        nVar.p = charSequence;
        nVar.r.setText(charSequence);
        if (nVar.h != 2) {
            nVar.i = 2;
        }
        nVar.l(nVar.h, nVar.i, nVar.k(nVar.r, charSequence));
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        n nVar = this.i;
        nVar.t = colorStateList;
        TextView textView = nVar.r;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    public void setHelperTextEnabled(boolean z) {
        n nVar = this.i;
        if (nVar.q == z) {
            return;
        }
        nVar.c();
        if (z) {
            z zVar = new z(nVar.f1930a, null);
            nVar.r = zVar;
            zVar.setId(b.d.a.a.f.textinput_helper_text);
            nVar.r.setTextAlignment(5);
            Typeface typeface = nVar.u;
            if (typeface != null) {
                nVar.r.setTypeface(typeface);
            }
            nVar.r.setVisibility(4);
            q.W(nVar.r, 1);
            int i = nVar.s;
            nVar.s = i;
            TextView textView = nVar.r;
            if (textView != null) {
                textView.setTextAppearance(i);
            }
            ColorStateList colorStateList = nVar.t;
            nVar.t = colorStateList;
            TextView textView2 = nVar.r;
            if (textView2 != null && colorStateList != null) {
                textView2.setTextColor(colorStateList);
            }
            nVar.a(nVar.r, 1);
        } else {
            nVar.c();
            if (nVar.h == 2) {
                nVar.i = 0;
            }
            nVar.l(nVar.h, nVar.i, nVar.k(nVar.r, null));
            nVar.j(nVar.r, 1);
            nVar.r = null;
            nVar.f1931b.w();
            nVar.f1931b.F();
        }
        nVar.q = z;
    }

    public void setHelperTextTextAppearance(int i) {
        n nVar = this.i;
        nVar.s = i;
        TextView textView = nVar.r;
        if (textView != null) {
            textView.setTextAppearance(i);
        }
    }

    public void setHint(int i) {
        setHint(i != 0 ? getResources().getText(i) : null);
    }

    public void setHint(CharSequence charSequence) {
        if (this.A) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z) {
        this.F0 = z;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.A) {
            this.A = z;
            if (z) {
                CharSequence hint = this.f2470e.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.B)) {
                        setHint(hint);
                    }
                    this.f2470e.setHint((CharSequence) null);
                }
                this.C = true;
            } else {
                this.C = false;
                if (!TextUtils.isEmpty(this.B) && TextUtils.isEmpty(this.f2470e.getHint())) {
                    this.f2470e.setHint(this.B);
                }
                setHintInternal(null);
            }
            if (this.f2470e != null) {
                x();
            }
        }
    }

    public void setHintTextAppearance(int i) {
        b.d.a.a.z.c cVar = this.D0;
        b.d.a.a.b0.b bVar = new b.d.a.a.b0.b(cVar.f2162a.getContext(), i);
        ColorStateList colorStateList = bVar.f1804a;
        if (colorStateList != null) {
            cVar.m = colorStateList;
        }
        float f2 = bVar.k;
        if (f2 != 0.0f) {
            cVar.k = f2;
        }
        ColorStateList colorStateList2 = bVar.f1805b;
        if (colorStateList2 != null) {
            cVar.P = colorStateList2;
        }
        cVar.N = bVar.f1809f;
        cVar.O = bVar.g;
        cVar.M = bVar.h;
        cVar.Q = bVar.j;
        b.d.a.a.b0.a aVar = cVar.w;
        if (aVar != null) {
            aVar.f1803c = true;
        }
        b.d.a.a.z.b bVar2 = new b.d.a.a.z.b(cVar);
        bVar.a();
        cVar.w = new b.d.a.a.b0.a(bVar2, bVar.n);
        bVar.c(cVar.f2162a.getContext(), cVar.w);
        cVar.k();
        this.s0 = this.D0.m;
        if (this.f2470e != null) {
            y(false, false);
            x();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.s0 != colorStateList) {
            if (this.r0 == null) {
                b.d.a.a.z.c cVar = this.D0;
                if (cVar.m != colorStateList) {
                    cVar.m = colorStateList;
                    cVar.k();
                }
            }
            this.s0 = colorStateList;
            if (this.f2470e != null) {
                y(false, false);
            }
        }
    }

    public void setMaxWidth(int i) {
        this.h = i;
        EditText editText = this.f2470e;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMaxWidth(i);
    }

    public void setMaxWidthResource(int i) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(i));
    }

    public void setMinWidth(int i) {
        this.g = i;
        EditText editText = this.f2470e;
        if (editText == null || i == -1) {
            return;
        }
        editText.setMinWidth(i);
    }

    public void setMinWidthResource(int i) {
        setMinWidth(getContext().getResources().getDimensionPixelSize(i));
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int i) {
        setPasswordVisibilityToggleContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.e0.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i) {
        setPasswordVisibilityToggleDrawable(i != 0 ? a.b.l.a.a.b(getContext(), i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.e0.setImageDrawable(drawable);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z) {
        if (z && this.c0 != 1) {
            setEndIconMode(1);
        } else {
            if (z) {
                return;
            }
            setEndIconMode(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        this.g0 = colorStateList;
        this.h0 = true;
        d();
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        this.i0 = mode;
        this.j0 = true;
        d();
    }

    public void setPlaceholderText(CharSequence charSequence) {
        if (this.q && TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.q) {
                setPlaceholderTextEnabled(true);
            }
            this.p = charSequence;
        }
        EditText editText = this.f2470e;
        z(editText != null ? editText.getText().length() : 0);
    }

    public void setPlaceholderTextAppearance(int i) {
        this.t = i;
        TextView textView = this.r;
        if (textView != null) {
            textView.setTextAppearance(i);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.s != colorStateList) {
            this.s = colorStateList;
            TextView textView = this.r;
            if (textView == null || colorStateList == null) {
                return;
            }
            textView.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(CharSequence charSequence) {
        this.w = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.x.setText(charSequence);
        B();
    }

    public void setPrefixTextAppearance(int i) {
        this.x.setTextAppearance(i);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.x.setTextColor(colorStateList);
    }

    public void setStartIconCheckable(boolean z) {
        this.T.setCheckable(z);
    }

    public void setStartIconContentDescription(int i) {
        setStartIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        if (getStartIconContentDescription() != charSequence) {
            this.T.setContentDescription(charSequence);
        }
    }

    public void setStartIconDrawable(int i) {
        setStartIconDrawable(i != 0 ? a.b.l.a.a.b(getContext(), i) : null);
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.T.setImageDrawable(drawable);
        if (drawable != null) {
            setStartIconVisible(true);
            p(this.T, this.U);
        } else {
            setStartIconVisible(false);
            setStartIconOnClickListener(null);
            setStartIconOnLongClickListener(null);
            setStartIconContentDescription((CharSequence) null);
        }
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        CheckableImageButton checkableImageButton = this.T;
        View.OnLongClickListener onLongClickListener = this.a0;
        checkableImageButton.setOnClickListener(onClickListener);
        q(checkableImageButton, onLongClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.a0 = onLongClickListener;
        CheckableImageButton checkableImageButton = this.T;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        q(checkableImageButton, onLongClickListener);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        if (this.U != colorStateList) {
            this.U = colorStateList;
            this.V = true;
            e(this.T, true, colorStateList, this.X, this.W);
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        if (this.W != mode) {
            this.W = mode;
            this.X = true;
            e(this.T, this.V, this.U, true, mode);
        }
    }

    public void setStartIconVisible(boolean z) {
        if ((this.T.getVisibility() == 0) != z) {
            this.T.setVisibility(z ? 0 : 8);
            A();
            v();
        }
    }

    public void setSuffixText(CharSequence charSequence) {
        this.y = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.z.setText(charSequence);
        E();
    }

    public void setSuffixTextAppearance(int i) {
        this.z.setTextAppearance(i);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.z.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(e eVar) {
        EditText editText = this.f2470e;
        if (editText != null) {
            q.U(editText, eVar);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.S) {
            this.S = typeface;
            this.D0.q(typeface);
            n nVar = this.i;
            if (typeface != nVar.u) {
                nVar.u = typeface;
                TextView textView = nVar.l;
                if (textView != null) {
                    textView.setTypeface(typeface);
                }
                TextView textView2 = nVar.r;
                if (textView2 != null) {
                    textView2.setTypeface(typeface);
                }
            }
            TextView textView3 = this.m;
            if (textView3 != null) {
                textView3.setTypeface(typeface);
            }
        }
    }

    public void t(int i) {
        boolean z = this.l;
        int i2 = this.k;
        if (i2 == -1) {
            this.m.setText(String.valueOf(i));
            this.m.setContentDescription(null);
            this.l = false;
        } else {
            this.l = i > i2;
            Context context = getContext();
            this.m.setContentDescription(context.getString(this.l ? i.character_counter_overflowed_content_description : i.character_counter_content_description, Integer.valueOf(i), Integer.valueOf(this.k)));
            if (z != this.l) {
                u();
            }
            a.h.i.a aVarC = a.h.i.a.c();
            TextView textView = this.m;
            String string = getContext().getString(i.character_counter_pattern, Integer.valueOf(i), Integer.valueOf(this.k));
            textView.setText(string != null ? aVarC.d(string, aVarC.f780c, true).toString() : null);
        }
        if (this.f2470e == null || z == this.l) {
            return;
        }
        y(false, false);
        F();
        w();
    }

    public final void u() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.m;
        if (textView != null) {
            r(textView, this.l ? this.n : this.o);
            if (!this.l && (colorStateList2 = this.u) != null) {
                this.m.setTextColor(colorStateList2);
            }
            if (!this.l || (colorStateList = this.v) == null) {
                return;
            }
            this.m.setTextColor(colorStateList);
        }
    }

    public final boolean v() {
        boolean z;
        if (this.f2470e == null) {
            return false;
        }
        boolean z2 = true;
        if (!(getStartIconDrawable() == null && this.w == null) && this.f2467b.getMeasuredWidth() > 0) {
            int measuredWidth = this.f2467b.getMeasuredWidth() - this.f2470e.getPaddingLeft();
            if (this.Y == null || this.Z != measuredWidth) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.Y = colorDrawable;
                this.Z = measuredWidth;
                colorDrawable.setBounds(0, 0, measuredWidth, 1);
            }
            Drawable[] compoundDrawablesRelative = this.f2470e.getCompoundDrawablesRelative();
            Drawable drawable = compoundDrawablesRelative[0];
            Drawable drawable2 = this.Y;
            if (drawable != drawable2) {
                this.f2470e.setCompoundDrawablesRelative(drawable2, compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
                z = true;
            }
            z = false;
        } else {
            if (this.Y != null) {
                Drawable[] compoundDrawablesRelative2 = this.f2470e.getCompoundDrawablesRelative();
                this.f2470e.setCompoundDrawablesRelative(null, compoundDrawablesRelative2[1], compoundDrawablesRelative2[2], compoundDrawablesRelative2[3]);
                this.Y = null;
                z = true;
            }
            z = false;
        }
        if ((this.p0.getVisibility() == 0 || ((j() && k()) || this.y != null)) && this.f2468c.getMeasuredWidth() > 0) {
            int measuredWidth2 = this.z.getMeasuredWidth() - this.f2470e.getPaddingRight();
            CheckableImageButton endIconToUpdateDummyDrawable = getEndIconToUpdateDummyDrawable();
            if (endIconToUpdateDummyDrawable != null) {
                measuredWidth2 = ((ViewGroup.MarginLayoutParams) endIconToUpdateDummyDrawable.getLayoutParams()).getMarginStart() + endIconToUpdateDummyDrawable.getMeasuredWidth() + measuredWidth2;
            }
            Drawable[] compoundDrawablesRelative3 = this.f2470e.getCompoundDrawablesRelative();
            Drawable drawable3 = this.k0;
            if (drawable3 == null || this.l0 == measuredWidth2) {
                if (this.k0 == null) {
                    ColorDrawable colorDrawable2 = new ColorDrawable();
                    this.k0 = colorDrawable2;
                    this.l0 = measuredWidth2;
                    colorDrawable2.setBounds(0, 0, measuredWidth2, 1);
                }
                Drawable drawable4 = compoundDrawablesRelative3[2];
                Drawable drawable5 = this.k0;
                if (drawable4 != drawable5) {
                    this.m0 = compoundDrawablesRelative3[2];
                    this.f2470e.setCompoundDrawablesRelative(compoundDrawablesRelative3[0], compoundDrawablesRelative3[1], drawable5, compoundDrawablesRelative3[3]);
                } else {
                    z2 = z;
                }
            } else {
                this.l0 = measuredWidth2;
                drawable3.setBounds(0, 0, measuredWidth2, 1);
                this.f2470e.setCompoundDrawablesRelative(compoundDrawablesRelative3[0], compoundDrawablesRelative3[1], this.k0, compoundDrawablesRelative3[3]);
            }
        } else {
            if (this.k0 == null) {
                return z;
            }
            Drawable[] compoundDrawablesRelative4 = this.f2470e.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative4[2] == this.k0) {
                this.f2470e.setCompoundDrawablesRelative(compoundDrawablesRelative4[0], compoundDrawablesRelative4[1], this.m0, compoundDrawablesRelative4[3]);
            } else {
                z2 = z;
            }
            this.k0 = null;
        }
        return z2;
    }

    public void w() {
        Drawable background;
        TextView textView;
        int currentTextColor;
        EditText editText = this.f2470e;
        if (editText == null || this.I != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        if (e0.a(background)) {
            background = background.mutate();
        }
        if (this.i.e()) {
            currentTextColor = this.i.g();
        } else {
            if (!this.l || (textView = this.m) == null) {
                background.clearColorFilter();
                this.f2470e.refreshDrawableState();
                return;
            }
            currentTextColor = textView.getCurrentTextColor();
        }
        background.setColorFilter(a.b.p.j.c(currentTextColor, PorterDuff.Mode.SRC_IN));
    }

    public final void x() {
        if (this.I != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f2466a.getLayoutParams();
            int iF = f();
            if (iF != layoutParams.topMargin) {
                layoutParams.topMargin = iF;
                this.f2466a.requestLayout();
            }
        }
    }

    public final void y(boolean z, boolean z2) {
        ColorStateList textColors;
        b.d.a.a.z.c cVar;
        TextView textView;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.f2470e;
        boolean z3 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.f2470e;
        boolean z4 = editText2 != null && editText2.hasFocus();
        boolean zE = this.i.e();
        ColorStateList colorStateList = this.r0;
        if (colorStateList != null) {
            b.d.a.a.z.c cVar2 = this.D0;
            if (cVar2.m != colorStateList) {
                cVar2.m = colorStateList;
                cVar2.k();
            }
            b.d.a.a.z.c cVar3 = this.D0;
            ColorStateList colorStateList2 = this.r0;
            if (cVar3.l != colorStateList2) {
                cVar3.l = colorStateList2;
                cVar3.k();
            }
        }
        if (!zIsEnabled) {
            ColorStateList colorStateList3 = this.r0;
            int colorForState = colorStateList3 != null ? colorStateList3.getColorForState(new int[]{-16842910}, this.B0) : this.B0;
            this.D0.m(ColorStateList.valueOf(colorForState));
            b.d.a.a.z.c cVar4 = this.D0;
            ColorStateList colorStateListValueOf = ColorStateList.valueOf(colorForState);
            if (cVar4.l != colorStateListValueOf) {
                cVar4.l = colorStateListValueOf;
                cVar4.k();
            }
        } else if (zE) {
            b.d.a.a.z.c cVar5 = this.D0;
            TextView textView2 = this.i.l;
            cVar5.m(textView2 != null ? textView2.getTextColors() : null);
        } else {
            if (this.l && (textView = this.m) != null) {
                cVar = this.D0;
                textColors = textView.getTextColors();
            } else if (z4 && (textColors = this.s0) != null) {
                cVar = this.D0;
            }
            cVar.m(textColors);
        }
        if (z3 || !this.E0 || (isEnabled() && z4)) {
            if (z2 || this.C0) {
                ValueAnimator valueAnimator = this.G0;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.G0.cancel();
                }
                if (z && this.F0) {
                    b(1.0f);
                } else {
                    this.D0.o(1.0f);
                }
                this.C0 = false;
                if (g()) {
                    m();
                }
                EditText editText3 = this.f2470e;
                z(editText3 != null ? editText3.getText().length() : 0);
                B();
                E();
                return;
            }
            return;
        }
        if (z2 || !this.C0) {
            ValueAnimator valueAnimator2 = this.G0;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.G0.cancel();
            }
            if (z && this.F0) {
                b(0.0f);
            } else {
                this.D0.o(0.0f);
            }
            if (g() && (!((b.d.a.a.h0.g) this.D).z.isEmpty()) && g()) {
                ((b.d.a.a.h0.g) this.D).y(0.0f, 0.0f, 0.0f, 0.0f);
            }
            this.C0 = true;
            TextView textView3 = this.r;
            if (textView3 != null && this.q) {
                textView3.setText((CharSequence) null);
                this.r.setVisibility(4);
            }
            B();
            E();
        }
    }

    public final void z(int i) {
        if (i != 0 || this.C0) {
            TextView textView = this.r;
            if (textView == null || !this.q) {
                return;
            }
            textView.setText((CharSequence) null);
            this.r.setVisibility(4);
            return;
        }
        TextView textView2 = this.r;
        if (textView2 == null || !this.q) {
            return;
        }
        textView2.setText(this.p);
        this.r.setVisibility(0);
        this.r.bringToFront();
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.p0.setImageDrawable(drawable);
        setErrorIconVisible(drawable != null && this.i.k);
    }
}
