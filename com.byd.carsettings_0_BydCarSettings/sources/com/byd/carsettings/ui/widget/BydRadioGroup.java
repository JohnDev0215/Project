package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.IdRes;
import androidx.annotation.RequiresApi;
import b.b.g.q.h.b;

/* JADX INFO: loaded from: classes2.dex */
public class BydRadioGroup extends LinearLayout implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3367a = BydRadioGroup.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3368b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a f3369c;

    public interface a {
        void onCheckedChanged(BydRadioGroup bydRadioGroup, @IdRes int i);
    }

    public BydRadioGroup(Context context) {
        super(context);
        this.f3368b = -1;
        setOrientation(1);
        c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(@IdRes int i) {
        int childCount = getChildCount();
        int i2 = -1;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt instanceof b) {
                b bVar = (b) childAt;
                if (i == childAt.getId()) {
                    bVar.setChecked(true);
                    a aVar = this.f3369c;
                    if (aVar != null) {
                        aVar.onCheckedChanged(this, childAt.getId());
                    }
                    i2 = i3;
                } else {
                    bVar.setChecked(false);
                }
            }
        }
        if (i2 != -1) {
            this.f3368b = i;
        } else {
            this.f3368b = -1;
        }
    }

    public void b() {
        a(-1);
    }

    public final void c() {
    }

    public int getCheckedItemId() {
        return this.f3368b;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        a(view.getId());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).setOnClickListener(null);
            }
        }
        super.onDetachedFromWindow();
        this.f3369c = null;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            getChildAt(i5).setOnClickListener(this);
        }
    }

    public void setOnCheckedChangeListener(a aVar) {
        this.f3369c = aVar;
    }

    @RequiresApi(api = 26)
    public BydRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3368b = -1;
        c();
    }
}
