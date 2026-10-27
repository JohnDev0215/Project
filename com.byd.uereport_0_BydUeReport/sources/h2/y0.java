package h2;

/* JADX INFO: compiled from: SerialContext.java */
/* JADX INFO: loaded from: classes.dex */
public class y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y0 f5315a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f5316b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f5317c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5318d;

    public y0(y0 y0Var, Object obj, Object obj2, int i8, int i9) {
        this.f5315a = y0Var;
        this.f5316b = obj;
        this.f5317c = obj2;
        this.f5318d = i8;
    }

    public void a(StringBuilder sb) {
        boolean z7;
        y0 y0Var = this.f5315a;
        if (y0Var == null) {
            sb.append('$');
            return;
        }
        y0Var.a(sb);
        Object obj = this.f5317c;
        if (obj == null) {
            sb.append(".null");
            return;
        }
        if (obj instanceof Integer) {
            sb.append('[');
            sb.append(((Integer) this.f5317c).intValue());
            sb.append(']');
            return;
        }
        sb.append('.');
        String string = this.f5317c.toString();
        int i8 = 0;
        while (true) {
            if (i8 >= string.length()) {
                z7 = false;
                break;
            }
            char cCharAt = string.charAt(i8);
            if ((cCharAt < '0' || cCharAt > '9') && ((cCharAt < 'A' || cCharAt > 'Z') && ((cCharAt < 'a' || cCharAt > 'z') && cCharAt <= 128))) {
                z7 = true;
                break;
            }
            i8++;
        }
        if (!z7) {
            sb.append(string);
            return;
        }
        for (int i9 = 0; i9 < string.length(); i9++) {
            char cCharAt2 = string.charAt(i9);
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
        if (this.f5315a == null) {
            return "$";
        }
        StringBuilder sb = new StringBuilder();
        a(sb);
        return sb.toString();
    }
}
