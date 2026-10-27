package defpackage;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: Tile.java */
/* JADX INFO: loaded from: classes2.dex */
public final class w93 implements dq1 {
    public final ao2 a;
    public final int b;
    public int c = 0;
    public int d = 0;
    public Drawable e;
    public ColorStateList f;
    public Long g;

    public w93(ao2 ao2Var, int i) {
        this.a = ao2Var;
        this.b = i;
    }

    @Override // defpackage.dq1
    public final Long b() {
        return this.g;
    }

    @Override // defpackage.dq1
    public final GURL getUrl() {
        return this.a.b;
    }

    @Override // defpackage.dq1
    public final void a() {
    }
}
