package D6;

import A7.m;
import java.util.List;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1194a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f1195b;
    public List c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1196e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f1197f;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return m.a(this.f1194a, aVar.f1194a) && m.a(this.f1195b, aVar.f1195b) && m.a(this.c, aVar.c) && this.d == aVar.d && this.f1196e == aVar.f1196e && m.a(this.f1197f, aVar.f1197f);
    }

    public final int hashCode() {
        return this.f1197f.hashCode() + AbstractC2641j.b(this.f1196e, AbstractC2641j.b(this.d, (this.c.hashCode() + M5.b.c(this.f1195b, this.f1194a.hashCode() * 31, 31)) * 31, 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AssetLogInfo(assetId=");
        sb.append(this.f1194a);
        sb.append(", assetType=");
        sb.append(this.f1195b);
        sb.append(", genres=");
        sb.append(this.c);
        sb.append(", position=");
        sb.append(this.d);
        sb.append(", totalLength=");
        sb.append(this.f1196e);
        sb.append(", videoBackgroundUrl=");
        return android.hardware.bydauto.ac.tsimpl.a.l(sb, this.f1197f, ")");
    }
}
