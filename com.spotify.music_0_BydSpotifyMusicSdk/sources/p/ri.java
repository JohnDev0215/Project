package p;

/* JADX INFO: loaded from: classes.dex */
public final class ri {
    public final si a;
    public final ui b;
    public final ti c;

    public ri(si siVar, ui uiVar, ti tiVar) {
        this.a = siVar;
        this.b = uiVar;
        this.c = tiVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ri)) {
            return false;
        }
        ri riVar = (ri) obj;
        return this.a.equals(riVar.a) && this.b.equals(riVar.b) && this.c.equals(riVar.c);
    }

    public final int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public final String toString() {
        return "StaticSessionData{appData=" + this.a + ", osData=" + this.b + ", deviceData=" + this.c + "}";
    }
}
