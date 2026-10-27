package com.android.settingslib;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.preference.Preference;
import androidx.preference.f;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class TwoTargetPreference extends Preference {
    private int P;
    private int Q;
    private int R;

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface IconSize {
    }

    public TwoTargetPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        F0(context);
    }

    private void F0(Context context) {
        m0(R$layout.preference_two_target);
        this.Q = context.getResources().getDimensionPixelSize(R$dimen.two_target_pref_small_icon_size);
        this.R = context.getResources().getDimensionPixelSize(R$dimen.two_target_pref_medium_icon_size);
        int iE0 = E0();
        if (iE0 != 0) {
            y0(iE0);
        }
    }

    protected int E0() {
        return 0;
    }

    protected boolean G0() {
        return E0() == 0;
    }

    @Override // androidx.preference.Preference
    public void M(f fVar) {
        super.M(fVar);
        ImageView imageView = (ImageView) fVar.f1092a.findViewById(R.id.icon);
        int i = this.P;
        if (i == 1) {
            int i2 = this.R;
            imageView.setLayoutParams(new LinearLayout.LayoutParams(i2, i2));
        } else if (i == 2) {
            int i3 = this.Q;
            imageView.setLayoutParams(new LinearLayout.LayoutParams(i3, i3));
        }
        View viewM = fVar.M(R$id.two_target_divider);
        View viewM2 = fVar.M(R.id.widget_frame);
        boolean zG0 = G0();
        if (viewM != null) {
            viewM.setVisibility(zG0 ? 8 : 0);
        }
        if (viewM2 != null) {
            viewM2.setVisibility(zG0 ? 8 : 0);
        }
    }

    public TwoTargetPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        F0(context);
    }
}
