package defpackage;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class S4 {
    public static S4 b(I4 i4) {
        return new W4(i4, ((R4) i4).a());
    }

    @Deprecated
    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);
}
