package com.android.settingslib.wifi;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import androidx.preference.Preference;
import com.android.settingslib.R$attr;
import com.android.settingslib.R$dimen;
import com.android.settingslib.R$id;
import com.android.settingslib.R$layout;
import com.android.settingslib.R$string;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class AccessPointPreference extends Preference {
    private static final int[] Y = {R$attr.state_encrypted};
    private static final int[] Z = {R$attr.state_metered};
    private static final int[] a0 = {R$string.accessibility_no_wifi, R$string.accessibility_wifi_one_bar, R$string.accessibility_wifi_two_bars, R$string.accessibility_wifi_three_bars, R$string.accessibility_wifi_signal_full};
    private final StateListDrawable P;
    private final int Q;
    private TextView R;
    private boolean S;
    private AccessPoint T;
    private Drawable U;
    private int V;
    private CharSequence W;
    private final Runnable X;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AccessPointPreference.this.I();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class b {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class c {
    }

    public AccessPointPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.X = new a();
        this.P = null;
        this.Q = 0;
    }

    private void E0(ImageView imageView) {
        if (imageView == null || this.P == null) {
            return;
        }
        if (this.T.x() != 0 && this.T.x() != 4) {
            this.P.setState(Y);
        } else if (this.T.I()) {
            this.P.setState(Z);
        }
        imageView.setImageDrawable(this.P.getCurrent());
    }

    private void G0() {
        TextView textView = this.R;
        if (textView != null) {
            textView.post(this.X);
        }
    }

    @VisibleForTesting
    static CharSequence buildContentDescription(Context context, Preference preference, AccessPoint accessPoint) {
        CharSequence charSequenceA = preference.A();
        CharSequence charSequenceY = preference.y();
        if (!TextUtils.isEmpty(charSequenceY)) {
            charSequenceA = TextUtils.concat(charSequenceA, ",", charSequenceY);
        }
        int iV = accessPoint.v();
        if (iV >= 0) {
            int[] iArr = a0;
            if (iV < iArr.length) {
                charSequenceA = TextUtils.concat(charSequenceA, ",", context.getString(iArr[iV]));
            }
        }
        CharSequence[] charSequenceArr = new CharSequence[3];
        charSequenceArr[0] = charSequenceA;
        charSequenceArr[1] = ",";
        charSequenceArr[2] = accessPoint.x() == 0 ? context.getString(R$string.accessibility_wifi_security_type_none) : context.getString(R$string.accessibility_wifi_security_type_secured);
        return TextUtils.concat(charSequenceArr);
    }

    @VisibleForTesting
    static void setTitle(AccessPointPreference accessPointPreference, AccessPoint accessPoint) {
        accessPointPreference.x0(accessPoint.C());
    }

    protected int F0() {
        return R$layout.access_point_friction_widget;
    }

    public boolean H0() {
        return this.S;
    }

    @Override // androidx.preference.Preference
    protected void I() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            G0();
        } else {
            super.I();
        }
    }

    @Override // androidx.preference.Preference
    public void M(androidx.preference.f fVar) {
        super.M(fVar);
        if (this.T == null) {
            return;
        }
        Drawable drawableM = m();
        if (drawableM != null) {
            drawableM.setLevel(this.V);
        }
        TextView textView = (TextView) fVar.M(R.id.title);
        this.R = textView;
        if (textView != null) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable) null, (Drawable) null, this.U, (Drawable) null);
            this.R.setCompoundDrawablePadding(this.Q);
        }
        fVar.f1092a.setContentDescription(this.W);
        E0((ImageView) fVar.M(R$id.friction_icon));
        fVar.M(R$id.two_target_divider).setVisibility(H0() ? 0 : 4);
    }

    @VisibleForTesting
    AccessPointPreference(AccessPoint accessPoint, Context context, c cVar, int i, boolean z, StateListDrawable stateListDrawable, int i2, b bVar) {
        super(context);
        this.X = new a();
        m0(R$layout.preference_access_point);
        y0(F0());
        this.T = accessPoint;
        accessPoint.l0(this);
        this.V = i2;
        this.P = stateListDrawable;
        this.Q = context.getResources().getDimensionPixelSize(R$dimen.wifi_preference_badge_padding);
    }
}
