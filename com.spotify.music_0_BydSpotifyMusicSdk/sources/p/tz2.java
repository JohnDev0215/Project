package p;

import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class tz2 {
    public final /* synthetic */ int a;
    public final String b;

    public tz2(String str, int i) {
        this.a = i;
        if (i == 1) {
            str.getClass();
            this.b = str;
            return;
        }
        if (i == 3) {
            this.b = str;
            return;
        }
        int iMyUid = Process.myUid();
        int iMyPid = Process.myPid();
        StringBuilder sb = new StringBuilder(39);
        sb.append("UID: [");
        sb.append(iMyUid);
        sb.append("]  PID: [");
        sb.append(iMyPid);
        sb.append("] ");
        String string = sb.toString();
        String strValueOf = String.valueOf(str);
        this.b = strValueOf.length() != 0 ? string.concat(strValueOf) : new String(string);
    }

    public static String g(String str, String str2, Object... objArr) {
        if (objArr.length > 0) {
            try {
                str2 = String.format(Locale.US, str2, objArr);
            } catch (IllegalFormatException e) {
                String strValueOf = String.valueOf(str2);
                Log.e("PlayCore", strValueOf.length() != 0 ? "Unable to format ".concat(strValueOf) : new String("Unable to format "), e);
                String strJoin = TextUtils.join(", ", objArr);
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 3 + String.valueOf(strJoin).length());
                sb.append(str2);
                sb.append(" [");
                sb.append(strJoin);
                sb.append("]");
                str2 = sb.toString();
            }
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(str2).length());
        sb2.append(str);
        sb2.append(" : ");
        sb2.append(str2);
        return sb2.toString();
    }

    public final String a(Iterable iterable) {
        Iterator it = iterable.iterator();
        StringBuilder sb = new StringBuilder();
        try {
            if (it.hasNext()) {
                Object next = it.next();
                Objects.requireNonNull(next);
                sb.append(next instanceof CharSequence ? (CharSequence) next : next.toString());
                while (it.hasNext()) {
                    sb.append((CharSequence) this.b);
                    Object next2 = it.next();
                    Objects.requireNonNull(next2);
                    sb.append(next2 instanceof CharSequence ? (CharSequence) next2 : next2.toString());
                }
            }
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final void b(String str, Object... objArr) {
        if (Log.isLoggable("PlayCore", 3)) {
            g(this.b, str, objArr);
        }
    }

    public final void c(String str, Object... objArr) {
        if (Log.isLoggable("PlayCore", 6)) {
            Log.e("PlayCore", g(this.b, str, objArr));
        }
    }

    public final void d(Exception exc, String str, Object... objArr) {
        if (Log.isLoggable("PlayCore", 6)) {
            Log.e("PlayCore", g(this.b, str, objArr), exc);
        }
    }

    public final void e(String str, Object... objArr) {
        if (Log.isLoggable("PlayCore", 4)) {
            g(this.b, str, objArr);
        }
    }

    public final void f(String str, Object... objArr) {
        if (Log.isLoggable("PlayCore", 5)) {
            g(this.b, str, objArr);
        }
    }

    public final String toString() {
        switch (this.a) {
            case 3:
                return hf2.d(new StringBuilder("<"), this.b, '>');
            default:
                return super.toString();
        }
    }
}
