package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ViewGroupOverlayApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class ge extends le implements ie {
    public ge(Context context, ViewGroup viewGroup, View view) {
        super(context, viewGroup, view);
    }

    public static ge g(ViewGroup viewGroup) {
        return (ge) le.e(viewGroup);
    }

    @Override // defpackage.ie
    public void c(View view) {
        this.a.b(view);
    }

    @Override // defpackage.ie
    public void d(View view) {
        this.a.g(view);
    }
}
