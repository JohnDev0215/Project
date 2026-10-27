package j6;

import com.stingray.kca.model.AssetTypeV2;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f17032a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f17033b;
    public final AssetTypeV2 c;

    public a(AssetTypeV2 assetTypeV2, String str, boolean z6) {
        A7.m.f(assetTypeV2, "assetType");
        this.f17032a = z6;
        this.f17033b = str;
        this.c = assetTypeV2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f17032a == aVar.f17032a && A7.m.a(this.f17033b, aVar.f17033b) && this.c == aVar.c;
    }

    public final int hashCode() {
        int iHashCode = Boolean.hashCode(this.f17032a) * 31;
        String str = this.f17033b;
        return this.c.hashCode() + ((iHashCode + (str == null ? 0 : str.hashCode())) * 31);
    }

    public final String toString() {
        return "FavoriteStatus(isFavorite=" + this.f17032a + ", assetId=" + this.f17033b + ", assetType=" + this.c + ")";
    }
}
