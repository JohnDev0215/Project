package defpackage;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* JADX INFO: compiled from: QrCodePageAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x72 extends kv1 {
    public final ArrayList<View> b;

    public x72(ArrayList<View> arrayList) {
        this.b = arrayList;
    }

    @Override // defpackage.kv1
    public final void a(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // defpackage.kv1
    public final int b() {
        return this.b.size();
    }

    @Override // defpackage.kv1
    public final Object d(int i, ViewGroup viewGroup) {
        View view = this.b.get(i);
        viewGroup.addView(view);
        return view;
    }

    @Override // defpackage.kv1
    public final boolean e(View view, Object obj) {
        return view == obj;
    }
}
