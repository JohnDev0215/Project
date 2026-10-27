package com.android.settingslib.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class BarView extends LinearLayout {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f1385c;
    private ImageView d;
    private TextView e;
    private TextView f;

    public BarView(Context context) {
        super(context);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R$layout.settings_bar_view, this);
        setOrientation(1);
        setGravity(81);
        this.f1385c = findViewById(R$id.bar_view);
        this.d = (ImageView) findViewById(R$id.icon_view);
        this.e = (TextView) findViewById(R$id.bar_title);
        this.f = (TextView) findViewById(R$id.bar_summary);
    }

    void b(d dVar) {
        setOnClickListener(dVar.a());
        this.f1385c.getLayoutParams().height = dVar.e();
        this.d.setImageDrawable(dVar.d());
        this.e.setText(dVar.g());
        this.f.setText(dVar.f());
        CharSequence charSequenceB = dVar.b();
        if (TextUtils.isEmpty(charSequenceB) || TextUtils.equals(dVar.g(), charSequenceB)) {
            return;
        }
        this.d.setContentDescription(dVar.b());
    }

    @VisibleForTesting
    CharSequence getSummary() {
        return this.f.getText();
    }

    @VisibleForTesting
    CharSequence getTitle() {
        return this.e.getText();
    }

    public BarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
        int color = context.obtainStyledAttributes(new int[]{R.attr.colorAccent}).getColor(0, 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SettingsBarView);
        int color2 = typedArrayObtainStyledAttributes.getColor(R$styleable.SettingsBarView_barColor, color);
        typedArrayObtainStyledAttributes.recycle();
        this.f1385c.setBackgroundColor(color2);
    }
}
