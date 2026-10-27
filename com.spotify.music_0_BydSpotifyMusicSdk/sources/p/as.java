package p;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class as extends vr2 {
    public final /* synthetic */ int n = 0;
    public final Object o;

    public as(vr2 vr2Var) {
        vr2Var.getClass();
        this.o = vr2Var;
    }

    @Override // p.uw1
    /* JADX INFO: renamed from: apply */
    public final boolean mo348apply(Object obj) {
        switch (this.n) {
        }
        return m(((Character) obj).charValue());
    }

    @Override // p.vr2
    public final boolean m(char c) {
        int i = this.n;
        Object obj = this.o;
        switch (i) {
            case 0:
                return Arrays.binarySearch((char[]) obj, c) >= 0;
            default:
                return !((vr2) obj).m(c);
        }
    }

    public final String toString() {
        int i = this.n;
        Object obj = this.o;
        switch (i) {
            case 0:
                StringBuilder sb = new StringBuilder("CharMatcher.anyOf(\"");
                for (char c : (char[]) obj) {
                    sb.append(vr2.a(c));
                }
                sb.append("\")");
                return sb.toString();
            default:
                return ((vr2) obj) + ".negate()";
        }
    }

    public final vr2 z() {
        switch (this.n) {
            case 1:
                return (vr2) this.o;
            default:
                return new as(this);
        }
    }

    public as() {
        char[] charArray = "0123456789.".toString().toCharArray();
        this.o = charArray;
        Arrays.sort(charArray);
    }
}
