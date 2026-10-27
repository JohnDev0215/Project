package b.a.a.k;

/* JADX INFO: compiled from: SerialContext.java */
/* JADX INFO: loaded from: classes.dex */
public class y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y0 f412a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f413b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f414c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f415d;

    public y0(y0 y0Var, Object obj, Object obj2, int i, int i2) {
        this.f412a = y0Var;
        this.f413b = obj;
        this.f414c = obj2;
        this.f415d = i;
    }

    public void a(StringBuilder sb) {
        boolean z;
        y0 y0Var = this.f412a;
        if (y0Var == null) {
            sb.append('$');
            return;
        }
        y0Var.a(sb);
        Object obj = this.f414c;
        if (obj == null) {
            sb.append(".null");
            return;
        }
        if (obj instanceof Integer) {
            sb.append('[');
            sb.append(((Integer) this.f414c).intValue());
            sb.append(']');
            return;
        }
        sb.append('.');
        String string = this.f414c.toString();
        int i = 0;
        while (true) {
            if (i >= string.length()) {
                z = false;
                break;
            }
            char cCharAt = string.charAt(i);
            if ((cCharAt < '0' || cCharAt > '9') && ((cCharAt < 'A' || cCharAt > 'Z') && ((cCharAt < 'a' || cCharAt > 'z') && cCharAt <= 128))) {
                z = true;
                break;
            }
            i++;
        }
        if (!z) {
            sb.append(string);
            return;
        }
        for (int i2 = 0; i2 < string.length(); i2++) {
            char cCharAt2 = string.charAt(i2);
            if (cCharAt2 == '\\') {
                sb.append('\\');
                sb.append('\\');
                sb.append('\\');
            } else if ((cCharAt2 >= '0' && cCharAt2 <= '9') || ((cCharAt2 >= 'A' && cCharAt2 <= 'Z') || ((cCharAt2 >= 'a' && cCharAt2 <= 'z') || cCharAt2 > 128))) {
                sb.append(cCharAt2);
            } else if (cCharAt2 == '\"') {
                sb.append('\\');
                sb.append('\\');
                sb.append('\\');
            } else {
                sb.append('\\');
                sb.append('\\');
            }
            sb.append(cCharAt2);
        }
    }

    public String toString() {
        if (this.f412a == null) {
            return "$";
        }
        StringBuilder sb = new StringBuilder();
        a(sb);
        return sb.toString();
    }
}
