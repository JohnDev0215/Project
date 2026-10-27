package skin.support.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatSpinner;
import defpackage.go2;
import defpackage.ro2;
import defpackage.vd1;
import defpackage.xo2;

/* JADX INFO: loaded from: classes3.dex */
public class SkinCompatSpinner extends AppCompatSpinner implements ro2 {
    public static final int[] p = {R.attr.spinnerMode};
    public final go2 n;
    public int o;

    public SkinCompatSpinner(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        go2 go2Var = this.n;
        if (go2Var != null) {
            go2Var.J();
        }
        int iB = vd1.b(this.o);
        this.o = iB;
        if (iB != 0) {
            setPopupBackgroundDrawable(xo2.a(getContext(), this.o));
        }
    }

    @Override // androidx.appcompat.widget.AppCompatSpinner, android.widget.Spinner
    public void setPopupBackgroundResource(int i) {
        super.setPopupBackgroundResource(i);
        this.o = i;
        int iB = vd1.b(i);
        this.o = iB;
        if (iB != 0) {
            setPopupBackgroundDrawable(xo2.a(getContext(), this.o));
        }
    }

    public SkinCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.appcompat.R.attr.spinnerStyle);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public SkinCompatSpinner(android.content.Context r7, android.util.AttributeSet r8, int r9) {
        /*
            r6 = this;
            r6.<init>(r9, r7, r8)
            r0 = 0
            r6.o = r0
            int[] r1 = androidx.appcompat.R.styleable.Spinner
            android.content.res.TypedArray r1 = r7.obtainStyledAttributes(r8, r1, r9, r0)
            android.content.Context r2 = r6.getPopupContext()
            if (r2 == 0) goto L54
            r2 = -1
            r3 = 0
            int[] r4 = skin.support.widget.SkinCompatSpinner.p     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
            android.content.res.TypedArray r3 = r7.obtainStyledAttributes(r8, r4, r9, r0)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
            boolean r7 = r3.hasValue(r0)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
            if (r7 == 0) goto L32
            int r7 = r3.getInt(r0, r0)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
            r2 = r7
            goto L32
        L26:
            r6 = move-exception
            goto L4e
        L28:
            r7 = move-exception
            java.lang.String r4 = "SkinCompatSpinner"
            java.lang.String r5 = "Could not read android:spinnerMode"
            android.util.Log.i(r4, r5, r7)     // Catch: java.lang.Throwable -> L26
            if (r3 == 0) goto L35
        L32:
            r3.recycle()
        L35:
            r7 = 1
            if (r2 != r7) goto L54
            android.content.Context r7 = r6.getPopupContext()
            int[] r2 = androidx.appcompat.R.styleable.Spinner
            android.content.res.TypedArray r7 = r7.obtainStyledAttributes(r8, r2, r9, r0)
            int r2 = androidx.appcompat.R.styleable.Spinner_android_popupBackground
            int r0 = r7.getResourceId(r2, r0)
            r6.o = r0
            r7.recycle()
            goto L54
        L4e:
            if (r3 == 0) goto L53
            r3.recycle()
        L53:
            throw r6
        L54:
            r1.recycle()
            go2 r7 = new go2
            r7.<init>(r6)
            r6.n = r7
            r7.K(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: skin.support.widget.SkinCompatSpinner.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }
}
