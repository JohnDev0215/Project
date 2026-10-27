package l0;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ResourceCursorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c extends a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f7891n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f7892o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private LayoutInflater f7893p;

    @Deprecated
    public c(Context context, int i7, Cursor cursor, boolean z6) {
        super(context, cursor, z6);
        this.f7892o = i7;
        this.f7891n = i7;
        this.f7893p = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // l0.a
    public View g(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f7893p.inflate(this.f7892o, viewGroup, false);
    }

    @Override // l0.a
    public View h(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f7893p.inflate(this.f7891n, viewGroup, false);
    }
}
