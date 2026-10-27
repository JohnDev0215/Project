package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.NumberPicker;

/* JADX INFO: renamed from: zC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AlertDialogC1864zC extends AlertDialog implements DialogInterface.OnClickListener {
    public final boolean A;
    public final NumberPicker s;
    public final NumberPicker t;
    public final NumberPicker u;
    public final NumberPicker v;
    public final NumberPicker w;
    public final InterfaceC1809yC x;
    public final int y;
    public final int z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Removed duplicated region for block: B:42:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01df A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0235 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x026c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AlertDialogC1864zC(android.content.Context r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, boolean r27, defpackage.InterfaceC1809yC r28) {
        /*
            Method dump skipped, instruction units count: 659
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AlertDialogC1864zC.<init>(android.content.Context, int, int, int, int, int, int, int, int, boolean, yC):void");
    }

    public final int a(NumberPicker numberPicker) {
        numberPicker.clearFocus();
        return numberPicker.getValue();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        int iA = a(this.s);
        int iA2 = a(this.t);
        int iA3 = a(this.u);
        int iA4 = (a(this.v) * this.y) + this.z;
        if (!this.A) {
            int iA5 = a(this.w);
            if (iA == 12) {
                iA = 0;
            }
            iA += iA5 * 12;
        }
        int i2 = iA;
        C1370qC c1370qC = (C1370qC) this.x;
        c1370qC.b.b(c1370qC.a, 0, 0, 0, i2, iA2, iA3, iA4, 0);
    }
}
