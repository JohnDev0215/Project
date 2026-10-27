package com.android.settingslib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.content.res.f;
import androidx.preference.Preference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class LayoutPreference extends Preference {
    private final View.OnClickListener P;
    private boolean Q;
    private boolean R;
    private View S;

    public LayoutPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.P = new View.OnClickListener() { // from class: com.android.settingslib.widget.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f1386c.G0(view);
            }
        };
        E0(context, attributeSet, 0);
    }

    private void E0(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Preference);
        int i2 = R$styleable.Preference_allowDividerAbove;
        this.Q = f.b(typedArrayObtainStyledAttributes, i2, i2, false);
        int i3 = R$styleable.Preference_allowDividerBelow;
        this.R = f.b(typedArrayObtainStyledAttributes, i3, i3, false);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, i, 0);
        int resourceId = typedArrayObtainStyledAttributes2.getResourceId(R$styleable.Preference_android_layout, 0);
        if (resourceId == 0) {
            throw new IllegalArgumentException("LayoutPreference requires a layout to be defined");
        }
        typedArrayObtainStyledAttributes2.recycle();
        H0(LayoutInflater.from(j()).inflate(resourceId, (ViewGroup) null, false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: F0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void G0(View view) {
        W(view);
    }

    private void H0(View view) {
        m0(R$layout.layout_preference_frame);
        this.S = view;
        t0(false);
    }

    @Override // androidx.preference.Preference
    public void M(androidx.preference.f fVar) {
        fVar.f1092a.setOnClickListener(this.P);
        boolean zG = G();
        fVar.f1092a.setFocusable(zG);
        fVar.f1092a.setClickable(zG);
        fVar.Q(this.Q);
        fVar.R(this.R);
        FrameLayout frameLayout = (FrameLayout) fVar.f1092a;
        frameLayout.removeAllViews();
        ViewGroup viewGroup = (ViewGroup) this.S.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this.S);
        }
        frameLayout.addView(this.S);
    }
}
