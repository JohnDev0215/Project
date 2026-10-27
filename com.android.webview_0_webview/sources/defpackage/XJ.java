package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class XJ {
    public final Object a;
    public final Object b;

    public XJ(Object obj, Object obj2) {
        this.a = obj;
        this.b = obj2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof XJ)) {
            return false;
        }
        XJ xj = (XJ) obj;
        Object obj2 = this.a;
        Object obj3 = xj.a;
        if (!(obj2 == null ? obj3 == null : obj2.equals(obj3))) {
            return false;
        }
        Object obj4 = this.b;
        Object obj5 = xj.b;
        return obj4 == null ? obj5 == null : obj4.equals(obj5);
    }

    public int hashCode() {
        Object obj = this.a;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.b;
        return iHashCode ^ (obj2 != null ? obj2.hashCode() : 0);
    }
}
