package com.byd.filemanager.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.filemanager.R;
import com.byd.filemanager.g;
import com.byd.filemanager.k.b;
import com.byd.filemanager.k.c;

/* JADX INFO: loaded from: classes.dex */
public class RadioView extends RelativeLayout implements Checkable, View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3877b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3878c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3879d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private View f3880e;

    public RadioView(Context context) {
        this(context, null);
    }

    private int a(int i) {
        int iB = (int) c.b(40.0f);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? iB : size : Math.min(iB, size);
    }

    private int b(int i) {
        int iB = (int) c.b(100.0f);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? iB : size : Math.min(iB, size);
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f3879d;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        toggle();
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        b.b("onMeasure");
        this.f3877b = b(i);
        this.f3878c = a(i2);
        b.b("mWidth = " + this.f3877b + ", mHeight = " + this.f3878c);
        setMeasuredDimension(this.f3877b, this.f3878c);
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        this.f3879d = z;
        this.f3880e.setVisibility(z ? 0 : 4);
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.f3879d);
    }

    public RadioView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadioView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.Q0);
        String string = typedArrayObtainStyledAttributes.getString(2);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(1, false);
        LayoutInflater.from(context).inflate(R.layout.layout_tab_item, (ViewGroup) this, true);
        TextView textView = (TextView) findViewById(R.id.tv_without_icon);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.ly_with_icon);
        ((ImageView) findViewById(R.id.iv_icon)).setImageDrawable(drawable);
        TextView textView2 = (TextView) findViewById(R.id.tv_with_icon);
        if (z) {
            textView2.setText(string);
            relativeLayout.setVisibility(0);
            textView.setVisibility(8);
        } else {
            textView.setText(string);
            relativeLayout.setVisibility(8);
            textView.setVisibility(0);
        }
        this.f3880e = findViewById(R.id.line);
    }
}
