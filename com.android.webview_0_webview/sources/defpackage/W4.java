package defpackage;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class W4 extends S4 {
    public final I4 a;
    public final V4 b;

    public W4(I4 i4, Q4 q4) {
        this.a = i4;
        P4 p4 = V4.b;
        String canonicalName = V4.class.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        String strC = Z7.c("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
        Object v4 = (O4) q4.a.get(strC);
        if (!V4.class.isInstance(v4)) {
            v4 = new V4();
            O4 o4 = (O4) q4.a.put(strC, v4);
            if (o4 != null) {
                o4.a();
            }
        }
        this.b = (V4) v4;
    }

    @Override // defpackage.S4
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        V4 v4 = this.b;
        if (v4.a.i() > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            if (v4.a.i() <= 0) {
                return;
            }
            printWriter.print(str);
            printWriter.print("  #");
            printWriter.print(v4.a.f(0));
            printWriter.print(": ");
            throw null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Y2.a(this.a, sb);
        sb.append("}}");
        return sb.toString();
    }
}
