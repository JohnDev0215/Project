package o0;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ResourceCursorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c extends a {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4447j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f4448k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public LayoutInflater f4449l;

    @Deprecated
    public c(Context context, int i3, Cursor cursor, boolean z3) {
        super(context, cursor, z3);
        this.f4448k = i3;
        this.f4447j = i3;
        this.f4449l = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // o0.a
    public View c(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f4449l.inflate(this.f4448k, viewGroup, false);
    }

    @Override // o0.a
    public View d(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f4449l.inflate(this.f4447j, viewGroup, false);
    }
}
