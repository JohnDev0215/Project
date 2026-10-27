package com.byd.filemanager.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.filemanager.R;
import com.byd.filemanager.g;

/* JADX INFO: loaded from: classes.dex */
public class TextIcon extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Drawable f3885b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3886c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f3887d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f3888e;
    private float f;
    private float g;
    private float h;
    private int i;

    public TextIcon(Context context) {
        this(context, null);
    }

    private void a() {
        ImageView imageView = (ImageView) findViewById(R.id.iv_icon);
        imageView.setImageDrawable(this.f3885b);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        float f = this.f3887d;
        if (f != 0.0f) {
            layoutParams.width = (int) f;
        }
        float f2 = this.f3888e;
        if (f2 != 0.0f) {
            layoutParams.height = (int) f2;
        }
        imageView.setLayoutParams(layoutParams);
        TextView textView = (TextView) findViewById(R.id.tv_icon);
        textView.setText(this.f3886c);
        float f3 = this.h;
        if (f3 != 0.0f) {
            textView.setTextSize(0, f3);
        }
        if (this.g != 0.0f) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) textView.getLayoutParams();
            layoutParams2.topMargin = (int) this.g;
            textView.setLayoutParams(layoutParams2);
        }
    }

    public TextIcon(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextIcon(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.layout_texticon, (ViewGroup) this, true);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.n1);
        this.f3885b = typedArrayObtainStyledAttributes.getDrawable(6);
        this.f3886c = typedArrayObtainStyledAttributes.getString(7);
        this.f3887d = typedArrayObtainStyledAttributes.getDimension(1, 0.0f);
        this.f3888e = typedArrayObtainStyledAttributes.getDimension(0, 0.0f);
        this.f = typedArrayObtainStyledAttributes.getDimension(4, 0.0f);
        this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, 0);
        this.i = typedArrayObtainStyledAttributes.getColor(8, -16777216);
        a();
    }
}
