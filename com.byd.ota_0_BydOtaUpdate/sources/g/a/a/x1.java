package g.a.a;

/* JADX INFO: loaded from: classes.dex */
public class x1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4168a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4169b = 0;

    public x1(String str) {
        this.f4168a = str;
    }

    public boolean a() {
        return this.f4169b != -1;
    }

    public String b() {
        int i2 = this.f4169b;
        if (i2 == -1) {
            return null;
        }
        int iIndexOf = this.f4168a.indexOf(46, i2);
        if (iIndexOf == -1) {
            String strSubstring = this.f4168a.substring(this.f4169b);
            this.f4169b = -1;
            return strSubstring;
        }
        String strSubstring2 = this.f4168a.substring(this.f4169b, iIndexOf);
        this.f4169b = iIndexOf + 1;
        return strSubstring2;
    }
}
