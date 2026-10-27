package defpackage;

import android.content.Context;
import android.view.LayoutInflater;

/* JADX INFO: compiled from: ResourceCursorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class qb2 extends j20 {
    public final int l;
    public final int m;
    public final LayoutInflater n;

    @Deprecated
    public qb2(Context context, int i) {
        super(context);
        this.m = i;
        this.l = i;
        this.n = (LayoutInflater) context.getSystemService("layout_inflater");
    }
}
