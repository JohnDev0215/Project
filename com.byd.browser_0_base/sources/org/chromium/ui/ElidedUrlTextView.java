package org.chromium.ui;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* JADX INFO: loaded from: classes3.dex */
public class ElidedUrlTextView extends AppCompatTextView {
    public Integer k;
    public Integer l;
    public boolean m;
    public int n;
    public int o;

    public ElidedUrlTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = true;
        this.n = -1;
        this.o = Integer.MAX_VALUE;
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        setMaxLines(Integer.MAX_VALUE);
        super.onMeasure(i, i2);
        String string = getText().toString();
        int i3 = this.n;
        Layout layout = getLayout();
        boolean z = false;
        int i4 = 0;
        while (i4 < layout.getLineCount() && layout.getLineEnd(i4) < i3) {
            i4++;
        }
        this.k = Integer.valueOf(i4 + 1 + 1);
        int iIndexOf = string.indexOf(35);
        if (iIndexOf == -1) {
            iIndexOf = string.length();
        }
        Layout layout2 = getLayout();
        int i5 = 0;
        while (i5 < layout2.getLineCount() && layout2.getLineEnd(i5) < iIndexOf) {
            i5++;
        }
        Integer numValueOf = Integer.valueOf(i5 + 1 + 1);
        this.l = numValueOf;
        if (numValueOf.intValue() < this.k.intValue()) {
            this.k = this.l;
        }
        int iIntValue = this.l.intValue();
        if (this.m) {
            iIntValue = this.k.intValue();
        }
        if (iIntValue != this.o) {
            setMaxLines(iIntValue);
            z = true;
        }
        if (z) {
            super.onMeasure(i, i2);
        }
    }

    public final void q(int i, CharSequence charSequence) {
        setText(charSequence);
        this.n = i;
    }

    public final void r() {
        boolean z = true;
        this.m = !this.m;
        Integer num = this.l;
        if (num != null) {
            int iIntValue = num.intValue();
            if (this.m) {
                iIntValue = this.k.intValue();
            }
            if (iIntValue != this.o) {
                setMaxLines(iIntValue);
            } else {
                z = false;
            }
            if (z) {
                announceForAccessibility(getResources().getString(this.m ? R$string.elided_url_text_view_url_truncated : R$string.elided_url_text_view_url_expanded));
            }
        }
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i) {
        super.setMaxLines(i);
        this.o = i;
    }
}
