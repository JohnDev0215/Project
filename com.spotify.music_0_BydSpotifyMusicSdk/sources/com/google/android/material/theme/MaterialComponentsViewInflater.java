package com.google.android.material.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatButton;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.spotify.music.R;
import p.ci1;
import p.f60;
import p.f9;
import p.fa;
import p.iw;
import p.kf1;
import p.nf1;
import p.pa;
import p.u7;
import p.v03;
import p.x7;
import p.xz1;
import p.ye1;

/* JADX INFO: loaded from: classes.dex */
public class MaterialComponentsViewInflater extends pa {
    @Override // p.pa
    public final u7 a(Context context, AttributeSet attributeSet) {
        return new ye1(context, attributeSet);
    }

    @Override // p.pa
    public final AppCompatButton b(Context context, AttributeSet attributeSet) {
        return new MaterialButton(context, attributeSet);
    }

    @Override // p.pa
    public final x7 c(Context context, AttributeSet attributeSet) {
        kf1 kf1Var = new kf1(f60.S(context, attributeSet, R.attr.checkboxStyle, R.style.Widget_MaterialComponents_CompoundButton_CheckBox), attributeSet, R.attr.checkboxStyle);
        Context context2 = kf1Var.getContext();
        TypedArray typedArrayE = v03.E(context2, attributeSet, xz1.o, R.attr.checkboxStyle, R.style.Widget_MaterialComponents_CompoundButton_CheckBox, new int[0]);
        if (typedArrayE.hasValue(0)) {
            iw.c(kf1Var, ci1.v(context2, typedArrayE, 0));
        }
        kf1Var.n = typedArrayE.getBoolean(1, false);
        typedArrayE.recycle();
        return kf1Var;
    }

    @Override // p.pa
    public final f9 d(Context context, AttributeSet attributeSet) {
        nf1 nf1Var = new nf1(f60.S(context, attributeSet, R.attr.radioButtonStyle, R.style.Widget_MaterialComponents_CompoundButton_RadioButton), attributeSet);
        Context context2 = nf1Var.getContext();
        TypedArray typedArrayE = v03.E(context2, attributeSet, xz1.f209p, R.attr.radioButtonStyle, R.style.Widget_MaterialComponents_CompoundButton_RadioButton, new int[0]);
        if (typedArrayE.hasValue(0)) {
            iw.c(nf1Var, ci1.v(context2, typedArrayE, 0));
        }
        nf1Var.n = typedArrayE.getBoolean(1, false);
        typedArrayE.recycle();
        return nf1Var;
    }

    @Override // p.pa
    public final fa e(Context context, AttributeSet attributeSet) {
        return new MaterialTextView(context, attributeSet);
    }
}
