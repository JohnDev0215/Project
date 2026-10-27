package k1;

/* JADX INFO: loaded from: classes.dex */
public final class i extends AbstractC1598c {
    public static i s(String str) {
        i iVar = new i(str.toCharArray());
        iVar.f17620m = 0L;
        iVar.r(str.length() - 1);
        return iVar;
    }

    @Override // k1.AbstractC1598c
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof i) && d().equals(((i) obj).d())) {
            return true;
        }
        return super.equals(obj);
    }
}
