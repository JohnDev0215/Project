package s0;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ResourceCursorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c extends a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f7880l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f7881m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public LayoutInflater f7882n;

    @Deprecated
    public c(Context context, int i8, Cursor cursor, boolean z7) {
        super(context, cursor, z7);
        this.f7881m = i8;
        this.f7880l = i8;
        this.f7882n = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // s0.a
    public View c(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f7882n.inflate(this.f7881m, viewGroup, false);
    }

    @Override // s0.a
    public View d(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f7882n.inflate(this.f7880l, viewGroup, false);
    }
}
