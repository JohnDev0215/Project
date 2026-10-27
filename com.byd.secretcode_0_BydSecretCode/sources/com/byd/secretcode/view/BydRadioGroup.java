package com.byd.secretcode.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import defpackage.vg;

/* JADX INFO: loaded from: classes.dex */
public class BydRadioGroup extends LinearLayout implements View.OnClickListener {
    public static final String b = BydRadioGroup.class.getSimpleName();
    public int c;
    public a d;

    public interface a {
        void a(BydRadioGroup bydRadioGroup, int i);
    }

    public BydRadioGroup(Context context) {
        super(context);
        this.c = -1;
        setOrientation(1);
        b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(int i) {
        int childCount = getChildCount();
        int i2 = -1;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt instanceof vg) {
                vg vgVar = (vg) childAt;
                if (i == childAt.getId()) {
                    vgVar.setChecked(true);
                    a aVar = this.d;
                    if (aVar != null) {
                        aVar.a(this, childAt.getId());
                    }
                    i2 = i3;
                } else {
                    vgVar.setChecked(false);
                }
            }
        }
        if (i2 != -1) {
            this.c = i;
        } else {
            this.c = -1;
        }
    }

    public final void b() {
    }

    public int getCheckedItemId() {
        return this.c;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        a(view.getId());
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
        this.d = aVar;
    }

    public BydRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = -1;
        b();
    }
}
