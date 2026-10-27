package p;

import android.util.Base64;
import com.google.android.datatransport.Priority;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class wi {
    public final String a;
    public final byte[] b;
    public final Priority c;

    public wi(String str, byte[] bArr, Priority priority) {
        this.a = str;
        this.b = bArr;
        this.c = priority;
    }

    public static v4 a() {
        v4 v4Var = new v4(10);
        v4Var.N(Priority.c);
        return v4Var;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final String toString() {
        Object[] objArr = new Object[3];
        objArr[0] = this.a;
        objArr[1] = this.c;
        byte[] bArr = this.b;
        objArr[2] = bArr == null ? "" : Base64.encodeToString(bArr, 2);
        return String.format("TransportContext(%s, %s, %s)", objArr);
    }

    public final wi c(Priority priority) {
        v4 v4VarA = a();
        v4VarA.M(this.a);
        v4VarA.N(priority);
        v4VarA.k = this.b;
        return v4VarA.e();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof wi)) {
            return false;
        }
        wi wiVar = (wi) obj;
        return this.a.equals(wiVar.a) && Arrays.equals(this.b, wiVar.b) && this.c.equals(wiVar.c);
    }

    public final int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.b)) * 1000003) ^ this.c.hashCode();
    }
}
