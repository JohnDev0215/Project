package B9;

import A7.m;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ThreadLocal f823a = new ThreadLocal();

    public void a(Exception exc) {
        d(exc, null, new Object[0]);
    }

    public void b(String str, Object... objArr) {
        m.f(objArr, "args");
        d(null, str, Arrays.copyOf(objArr, objArr.length));
    }

    public abstract void c(String str, String str2);

    public final void d(Exception exc, String str, Object... objArr) {
        ThreadLocal threadLocal = this.f823a;
        String str2 = (String) threadLocal.get();
        if (str2 != null) {
            threadLocal.remove();
        }
        if (str != null && str.length() != 0) {
            if (objArr.length != 0) {
                Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
                str = String.format(str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
            }
            if (exc != null) {
                StringBuilder sb = new StringBuilder();
                sb.append((Object) str);
                sb.append('\n');
                StringWriter stringWriter = new StringWriter(256);
                PrintWriter printWriter = new PrintWriter((Writer) stringWriter, false);
                exc.printStackTrace(printWriter);
                printWriter.flush();
                String string = stringWriter.toString();
                m.e(string, "sw.toString()");
                sb.append(string);
                str = sb.toString();
            }
        } else {
            if (exc == null) {
                return;
            }
            StringWriter stringWriter2 = new StringWriter(256);
            PrintWriter printWriter2 = new PrintWriter((Writer) stringWriter2, false);
            exc.printStackTrace(printWriter2);
            printWriter2.flush();
            str = stringWriter2.toString();
            m.e(str, "sw.toString()");
        }
        c(str2, str);
    }
}
