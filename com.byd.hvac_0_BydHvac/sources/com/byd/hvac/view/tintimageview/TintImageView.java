package com.byd.hvac.view.tintimageview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import b.a.a.f.g1;
import com.byd.hvac.R;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class TintImageView extends AppCompatImageView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1081a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1082b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1083c;
    public int d;
    public int e;

    public TintImageView(@NonNull Context context) {
        super(context);
        this.f1081a = 0;
        this.f1082b = 0;
        this.f1083c = 0;
        this.d = 0;
        this.e = 0;
    }

    public void a(int i) {
        this.f1082b = i;
        if (i == 0) {
            if (g1.e().t() == 1) {
                setImageTintList(ColorStateList.valueOf(getContext().getColor(R.color.button_text_unselect_denza)));
                return;
            } else {
                setImageTintList(ColorStateList.valueOf(getContext().getColor(R.color.text_tem_white)));
                return;
            }
        }
        if (i != 1) {
            return;
        }
        if (g1.e().t() == 1) {
            setImageTintList(ColorStateList.valueOf(getContext().getColor(R.color.button_text_select_denza)));
        } else {
            setImageTintList(ColorStateList.valueOf(getContext().getColor(R.color.text_tem_select)));
        }
    }

    public final void b(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.tint_imageview);
        this.f1083c = typedArrayObtainStyledAttributes.getResourceId(2, this.f1083c);
        this.d = typedArrayObtainStyledAttributes.getResourceId(0, this.d);
        this.e = typedArrayObtainStyledAttributes.getResourceId(1, this.e);
        this.f1081a = typedArrayObtainStyledAttributes.getResourceId(5, this.f1081a);
        typedArrayObtainStyledAttributes.recycle();
    }

    public int getCurrentState() {
        return this.f1082b;
    }

    public TintImageView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1081a = 0;
        this.f1082b = 0;
        this.f1083c = 0;
        this.d = 0;
        this.e = 0;
        b(context, attributeSet);
    }

    public TintImageView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1081a = 0;
        this.f1082b = 0;
        this.f1083c = 0;
        this.d = 0;
        this.e = 0;
        b(context, attributeSet);
    }
}
