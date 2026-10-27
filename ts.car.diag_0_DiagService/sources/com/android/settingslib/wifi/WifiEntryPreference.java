package com.android.settingslib.wifi;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.preference.Preference;
import com.android.settingslib.R$attr;
import com.android.settingslib.R$drawable;
import com.android.settingslib.R$id;
import com.android.settingslib.R$layout;
import com.android.settingslib.R$string;
import com.android.wifitrackerlib.WifiEntry;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class WifiEntryPreference extends Preference implements WifiEntry.c, View.OnClickListener {
    private static final int[] W = {R$attr.state_encrypted};
    private static final int[] X = {R$attr.wifi_friction};
    private static final int[] Y = {R$string.accessibility_no_wifi, R$string.accessibility_wifi_one_bar, R$string.accessibility_wifi_two_bars, R$string.accessibility_wifi_three_bars, R$string.accessibility_wifi_signal_full};
    private final StateListDrawable P;
    private final a Q;
    private WifiEntry R;
    private int S;
    private boolean T;
    private CharSequence U;
    private b V;

    /* JADX INFO: compiled from: caradapter-bug */
    static class a {
        public abstract Drawable a(boolean z, int i);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface b {
        void a(WifiEntryPreference wifiEntryPreference);
    }

    @VisibleForTesting
    WifiEntryPreference(@NonNull Context context, @NonNull WifiEntry wifiEntry, @NonNull a aVar) {
        super(context);
        this.S = -1;
        m0(R$layout.preference_access_point);
        y0(R$layout.access_point_friction_widget);
        this.P = G0();
        this.R = wifiEntry;
        wifiEntry.K(this);
        this.Q = aVar;
        H0();
    }

    private void E0(ImageView imageView) {
        if (imageView == null || this.P == null) {
            return;
        }
        if (this.R.p() != 0 && this.R.p() != 4) {
            this.P.setState(W);
        }
        imageView.setImageDrawable(this.P.getCurrent());
    }

    private Drawable F0(@DrawableRes int i) {
        try {
            return j().getDrawable(i);
        } catch (Resources.NotFoundException unused) {
            return null;
        }
    }

    private StateListDrawable G0() {
        TypedArray typedArrayObtainStyledAttributes;
        try {
            typedArrayObtainStyledAttributes = j().getTheme().obtainStyledAttributes(X);
        } catch (Resources.NotFoundException unused) {
            typedArrayObtainStyledAttributes = null;
        }
        if (typedArrayObtainStyledAttributes != null) {
            return (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(0);
        }
        return null;
    }

    private void I0(boolean z, int i) {
        if (i == -1) {
            i0(null);
            return;
        }
        Drawable drawableA = this.Q.a(z, i);
        if (drawableA == null) {
            i0(null);
        } else {
            drawableA.setTintList(com.android.settingslib.c.c(j(), R.attr.colorControlNormal));
            i0(drawableA);
        }
    }

    public void H0() {
        x0(this.R.t());
        int iK = this.R.k();
        boolean zL = this.R.L();
        if (iK != this.S || zL != this.T) {
            this.S = iK;
            this.T = zL;
            I0(zL, iK);
            I();
        }
        u0(this.R.s(false));
        this.U = buildContentDescription();
    }

    @Override // androidx.preference.Preference
    public void M(androidx.preference.f fVar) {
        super.M(fVar);
        Drawable drawableM = m();
        if (drawableM != null) {
            drawableM.setLevel(this.S);
        }
        fVar.f1092a.setContentDescription(this.U);
        fVar.M(R$id.two_target_divider).setVisibility(4);
        ImageButton imageButton = (ImageButton) fVar.M(R$id.icon_button);
        ImageView imageView = (ImageView) fVar.M(R$id.friction_icon);
        if (this.R.i() == null || this.R.h() != 0) {
            imageButton.setVisibility(8);
            if (imageView != null) {
                imageView.setVisibility(0);
                E0(imageView);
                return;
            }
            return;
        }
        Drawable drawableF0 = F0(R$drawable.ic_help);
        drawableF0.setTintList(com.android.settingslib.c.c(j(), R.attr.colorControlNormal));
        imageButton.setImageDrawable(drawableF0);
        imageButton.setVisibility(0);
        imageButton.setOnClickListener(this);
        imageButton.setContentDescription(j().getText(R$string.help_label));
        if (imageView != null) {
            imageView.setVisibility(8);
        }
    }

    @VisibleForTesting
    CharSequence buildContentDescription() {
        Context contextJ = j();
        CharSequence charSequenceA = A();
        CharSequence charSequenceY = y();
        if (!TextUtils.isEmpty(charSequenceY)) {
            charSequenceA = TextUtils.concat(charSequenceA, ",", charSequenceY);
        }
        int iK = this.R.k();
        if (iK >= 0) {
            int[] iArr = Y;
            if (iK < iArr.length) {
                charSequenceA = TextUtils.concat(charSequenceA, ",", contextJ.getString(iArr[iK]));
            }
        }
        CharSequence[] charSequenceArr = new CharSequence[3];
        charSequenceArr[0] = charSequenceA;
        charSequenceArr[1] = ",";
        charSequenceArr[2] = this.R.p() == 0 ? contextJ.getString(R$string.accessibility_wifi_security_type_none) : contextJ.getString(R$string.accessibility_wifi_security_type_secured);
        return TextUtils.concat(charSequenceArr);
    }

    @Override // com.android.wifitrackerlib.WifiEntry.c
    public void c() {
        H0();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b bVar;
        if (view.getId() != R$id.icon_button || (bVar = this.V) == null) {
            return;
        }
        bVar.a(this);
    }
}
