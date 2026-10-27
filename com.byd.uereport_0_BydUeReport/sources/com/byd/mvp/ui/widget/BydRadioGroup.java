package com.byd.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class BydRadioGroup extends LinearLayout implements View.OnClickListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f2859d = BydRadioGroup.class.getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2860e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f2861f;

    public interface a {
        void onCheckedChanged(BydRadioGroup bydRadioGroup, int i8);
    }

    public BydRadioGroup(Context context) {
        super(context);
        this.f2860e = -1;
        setOrientation(1);
        c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(int i8) {
        int childCount = getChildCount();
        int i9 = -1;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (childAt instanceof g4.a) {
                g4.a aVar = (g4.a) childAt;
                if (i8 == childAt.getId()) {
                    aVar.setChecked(true);
                    a aVar2 = this.f2861f;
                    if (aVar2 != null) {
                        aVar2.onCheckedChanged(this, childAt.getId());
                    }
                    i9 = i10;
                } else {
                    aVar.setChecked(false);
                }
            }
        }
        if (i9 != -1) {
            this.f2860e = i8;
        } else {
            this.f2860e = -1;
        }
    }

    public void b() {
        a(-1);
    }

    public final void c() {
    }

    public int getCheckedItemId() {
        return this.f2860e;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        a(view.getId());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i8 = 0; i8 < childCount; i8++) {
                getChildAt(i8).setOnClickListener(null);
            }
        }
        super.onDetachedFromWindow();
        this.f2861f = null;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        int childCount = getChildCount();
        for (int i12 = 0; i12 < childCount; i12++) {
            getChildAt(i12).setOnClickListener(this);
        }
    }

    public void setOnCheckedChangeListener(a aVar) {
        this.f2861f = aVar;
    }

    public BydRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2860e = -1;
        c();
    }
}
