package androidx.gridlayout.widget;

import androidx.gridlayout.widget.GridLayout;
import java.util.Arrays;

/* JADX INFO: compiled from: GridLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public final GridLayout.i[] a;
    public int b;
    public final GridLayout.i[][] c;
    public final int[] d;
    public final /* synthetic */ GridLayout.k e;

    public b(GridLayout.k kVar, GridLayout.i[] iVarArr) {
        this.e = kVar;
        int length = iVarArr.length;
        this.a = new GridLayout.i[length];
        this.b = length - 1;
        int iF = kVar.f() + 1;
        GridLayout.i[][] iVarArr2 = new GridLayout.i[iF][];
        int[] iArr = new int[iF];
        for (GridLayout.i iVar : iVarArr) {
            int i = iVar.a.a;
            iArr[i] = iArr[i] + 1;
        }
        for (int i2 = 0; i2 < iF; i2++) {
            iVarArr2[i2] = new GridLayout.i[iArr[i2]];
        }
        Arrays.fill(iArr, 0);
        for (GridLayout.i iVar2 : iVarArr) {
            int i3 = iVar2.a.a;
            GridLayout.i[] iVarArr3 = iVarArr2[i3];
            int i4 = iArr[i3];
            iArr[i3] = i4 + 1;
            iVarArr3[i4] = iVar2;
        }
        this.c = iVarArr2;
        this.d = new int[this.e.f() + 1];
    }

    public final void a(int i) {
        int[] iArr = this.d;
        if (iArr[i] != 0) {
            return;
        }
        iArr[i] = 1;
        for (GridLayout.i iVar : this.c[i]) {
            a(iVar.a.b);
            int i2 = this.b;
            this.b = i2 - 1;
            this.a[i2] = iVar;
        }
        iArr[i] = 2;
    }
}
