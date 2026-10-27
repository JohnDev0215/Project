package b.g.a;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ResourceCursorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c extends a {
    public int H3;
    public int I3;
    public LayoutInflater J3;

    @Deprecated
    public c(Context context, int i2, Cursor cursor, boolean z) {
        super(context, cursor, z);
        this.I3 = i2;
        this.H3 = i2;
        this.J3 = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // b.g.a.a
    public View c(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.J3.inflate(this.I3, viewGroup, false);
    }

    @Override // b.g.a.a
    public View d(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.J3.inflate(this.H3, viewGroup, false);
    }
}
