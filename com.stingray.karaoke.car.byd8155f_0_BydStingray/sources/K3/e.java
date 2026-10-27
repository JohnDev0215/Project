package K3;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class e extends j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Drawable f3526a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i f3527b;
    public final Throwable c;

    public e(Drawable drawable, i iVar, Throwable th) {
        this.f3526a = drawable;
        this.f3527b = iVar;
        this.c = th;
    }

    @Override // K3.j
    public final Drawable a() {
        return this.f3526a;
    }

    @Override // K3.j
    public final i b() {
        return this.f3527b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            if (A7.m.a(this.f3526a, eVar.f3526a)) {
                if (A7.m.a(this.f3527b, eVar.f3527b) && A7.m.a(this.c, eVar.c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        Drawable drawable = this.f3526a;
        int iHashCode = drawable != null ? drawable.hashCode() : 0;
        return this.c.hashCode() + ((this.f3527b.hashCode() + (iHashCode * 31)) * 31);
    }
}
