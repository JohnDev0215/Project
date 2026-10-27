package defpackage;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: StartCompoundLayout.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ViewConstructor"})
public final class jr2 extends LinearLayout {
    public final TextInputLayout e;
    public final AppCompatTextView f;
    public CharSequence g;
    public final CheckableImageButton h;
    public ColorStateList i;
    public PorterDuff.Mode j;
    public View.OnLongClickListener k;
    public boolean l;

    public jr2(TextInputLayout textInputLayout, TintTypedArray tintTypedArray) {
        CharSequence charSequenceK;
        super(textInputLayout.getContext());
        this.e = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        CheckableImageButton checkableImageButton = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R$layout.design_text_input_start_icon, (ViewGroup) this, false);
        this.h = checkableImageButton;
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.f = appCompatTextView;
        if (rc1.e(getContext())) {
            ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).setMarginEnd(0);
        }
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        int i = R$styleable.TextInputLayout_startIconTint;
        if (tintTypedArray.l(i)) {
            this.i = rc1.b(getContext(), tintTypedArray, i);
        }
        int i2 = R$styleable.TextInputLayout_startIconTintMode;
        if (tintTypedArray.l(i2)) {
            this.j = yn3.e(tintTypedArray.h(i2, -1), null);
        }
        int i3 = R$styleable.TextInputLayout_startIconDrawable;
        if (tintTypedArray.l(i3)) {
            a(tintTypedArray.e(i3));
            int i4 = R$styleable.TextInputLayout_startIconContentDescription;
            if (tintTypedArray.l(i4) && checkableImageButton.getContentDescription() != (charSequenceK = tintTypedArray.k(i4))) {
                checkableImageButton.setContentDescription(charSequenceK);
            }
            checkableImageButton.setCheckable(tintTypedArray.a(R$styleable.TextInputLayout_startIconCheckable, true));
        }
        appCompatTextView.setVisibility(8);
        appCompatTextView.setId(R$id.textinput_prefix_text);
        appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.g.e(appCompatTextView, 1);
        appCompatTextView.setTextAppearance(tintTypedArray.i(R$styleable.TextInputLayout_prefixTextAppearance, 0));
        int i5 = R$styleable.TextInputLayout_prefixTextColor;
        if (tintTypedArray.l(i5)) {
            appCompatTextView.setTextColor(tintTypedArray.b(i5));
        }
        CharSequence charSequenceK2 = tintTypedArray.k(R$styleable.TextInputLayout_prefixText);
        this.g = TextUtils.isEmpty(charSequenceK2) ? null : charSequenceK2;
        appCompatTextView.setText(charSequenceK2);
        d();
        addView(checkableImageButton);
        addView(appCompatTextView);
    }

    public final void a(Drawable drawable) {
        CheckableImageButton checkableImageButton = this.h;
        checkableImageButton.setImageDrawable(drawable);
        if (drawable != null) {
            ColorStateList colorStateList = this.i;
            PorterDuff.Mode mode = this.j;
            TextInputLayout textInputLayout = this.e;
            rw0.a(textInputLayout, checkableImageButton, colorStateList, mode);
            b(true);
            rw0.b(textInputLayout, checkableImageButton, this.i);
            return;
        }
        b(false);
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        if (checkableImageButton.getContentDescription() != null) {
            checkableImageButton.setContentDescription(null);
        }
    }

    public final void b(boolean z) {
        CheckableImageButton checkableImageButton = this.h;
        if ((checkableImageButton.getVisibility() == 0) != z) {
            checkableImageButton.setVisibility(z ? 0 : 8);
            c();
            d();
        }
    }

    public final void c() {
        EditText editText = this.e.h;
        if (editText == null) {
            return;
        }
        int iE = 0;
        if (!(this.h.getVisibility() == 0)) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            iE = om3.e.e(editText);
        }
        int compoundPaddingTop = editText.getCompoundPaddingTop();
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R$dimen.material_input_text_to_prefix_suffix_padding);
        int compoundPaddingBottom = editText.getCompoundPaddingBottom();
        WeakHashMap<View, in3> weakHashMap2 = om3.a;
        om3.e.i(this.f, iE, compoundPaddingTop, dimensionPixelSize, compoundPaddingBottom);
    }

    public final void d() {
        int i = (this.g == null || this.l) ? 8 : 0;
        setVisibility(this.h.getVisibility() == 0 || i == 0 ? 0 : 8);
        this.f.setVisibility(i);
        this.e.p();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        c();
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.k;
        CheckableImageButton checkableImageButton = this.h;
        checkableImageButton.setOnClickListener(onClickListener);
        rw0.c(checkableImageButton, onLongClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.k = onLongClickListener;
        CheckableImageButton checkableImageButton = this.h;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        rw0.c(checkableImageButton, onLongClickListener);
    }
}
