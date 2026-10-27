package defpackage;

import android.graphics.Bitmap;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.OfflineItemVisuals;
import org.chromium.components.offline_items_collection.VisualsCallback;
import org.chromium.components.offline_items_collection.a;

/* JADX INFO: compiled from: ThumbnailRequestGlue.java */
/* JADX INFO: loaded from: classes.dex */
public final class u93 implements s93 {
    public final a a;
    public final OfflineItem b;
    public final int c;
    public final int d;
    public final VisualsCallback e;

    public u93(a aVar, OfflineItem offlineItem, int i, int i2, zo1 zo1Var) {
        this.a = aVar;
        this.b = offlineItem;
        this.c = i;
        this.d = i2;
        this.e = zo1Var;
    }

    @Override // defpackage.s93
    public final void a(Bitmap bitmap) {
        OfflineItemVisuals offlineItemVisuals;
        if (bitmap != null) {
            offlineItemVisuals = new OfflineItemVisuals();
            offlineItemVisuals.a = bitmap;
        } else {
            offlineItemVisuals = null;
        }
        this.e.g(this.b.e, offlineItemVisuals);
    }

    @Override // defpackage.s93
    public final int b() {
        return this.c;
    }

    @Override // defpackage.s93
    public final String c() {
        return this.b.e.b;
    }

    @Override // defpackage.s93
    public final boolean d(n62 n62Var) {
        this.a.g(this.b.e, new zo1(this, 1, n62Var));
        return true;
    }

    @Override // defpackage.s93
    public final String e() {
        return this.b.u;
    }

    @Override // defpackage.s93
    public final String f() {
        return this.b.v;
    }
}
