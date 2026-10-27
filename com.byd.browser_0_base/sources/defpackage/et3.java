package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: WidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class et3 extends qx {
    public ArrayList<qx> w0 = new ArrayList<>();

    @Override // defpackage.qx
    public void G() {
        this.w0.clear();
        super.G();
    }

    @Override // defpackage.qx
    public final void J(pk pkVar) {
        super.J(pkVar);
        int size = this.w0.size();
        for (int i = 0; i < size; i++) {
            this.w0.get(i).J(pkVar);
        }
    }

    public void U() {
        ArrayList<qx> arrayList = this.w0;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            qx qxVar = this.w0.get(i);
            if (qxVar instanceof et3) {
                ((et3) qxVar).U();
            }
        }
    }
}
