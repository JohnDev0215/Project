package defpackage;

import J.N;

/* JADX INFO: compiled from: PostNativeFlag.java */
/* JADX INFO: loaded from: classes.dex */
public final class b32 extends ln0 {
    public Boolean c;

    public b32() {
        super("ThumbnailCacheRefactor");
    }

    public final boolean a() {
        Boolean bool = this.c;
        if (bool != null) {
            return bool.booleanValue();
        }
        sk skVar = bq.a;
        Boolean boolValueOf = Boolean.valueOf(N.M09VlOh_(this.a));
        this.c = boolValueOf;
        return boolValueOf.booleanValue();
    }
}
