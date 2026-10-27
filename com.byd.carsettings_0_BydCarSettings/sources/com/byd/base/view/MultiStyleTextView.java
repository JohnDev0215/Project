package com.byd.base.view;

import android.animation.AnimatorInflater;
import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import b.b.a.i.a;
import com.byd.base.R$color;
import com.byd.base.R$drawable;

/* JADX INFO: loaded from: classes.dex */
public class MultiStyleTextView extends AppCompatTextView {
    public MultiStyleTextView(Context context) {
        this(context, null);
    }

    public final void a(Context context) {
        setStateListAnimator(AnimatorInflater.loadStateListAnimator(context, R$drawable.anim_btn_scale_alpha_enable));
    }

    public final void b() {
        if (a.f533c == 1) {
            setTextColor(getResources().getColor(R$color.base_btn_text_selector_denz));
            setBackgroundResource(R$drawable.base_selector_white_denz);
        } else {
            setTextColor(getResources().getColor(R$color.base_btn_text_selector));
            setBackgroundResource(R$drawable.base_selector_dialog_blue);
        }
    }

    public MultiStyleTextView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MultiStyleTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
        b();
    }
}
