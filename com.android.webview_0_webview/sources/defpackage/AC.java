package defpackage;

import android.widget.NumberPicker;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AC implements NumberPicker.OnValueChangeListener {
    public final /* synthetic */ CC a;

    public AC(CC cc) {
        this.a = cc;
    }

    @Override // android.widget.NumberPicker.OnValueChangeListener
    public void onValueChange(NumberPicker numberPicker, int i, int i2) {
        int iB;
        int iG = this.a.g();
        int iF = this.a.f();
        CC cc = this.a;
        if (numberPicker == cc.s) {
            if (i == numberPicker.getMaxValue() && i2 == numberPicker.getMinValue()) {
                i2 = iG + 1;
                iB = this.a.d(i2);
            } else if (i == numberPicker.getMinValue() && i2 == numberPicker.getMaxValue()) {
                i2 = iG - 1;
                iB = this.a.b(i2);
            } else {
                iB = i2;
                i2 = iG;
            }
        } else {
            if (numberPicker != cc.t) {
                throw new IllegalArgumentException();
            }
            iB = iF;
        }
        this.a.i(i2, iB);
        this.a.j();
        CC cc2 = this.a;
        cc2.sendAccessibilityEvent(4);
        BC bc = cc2.u;
        if (bc != null) {
            int iG2 = cc2.g();
            int iF2 = cc2.f();
            CC cc3 = ((EC) bc).s;
            cc3.i(iG2, iF2);
            cc3.j();
            cc3.u = null;
        }
    }
}
