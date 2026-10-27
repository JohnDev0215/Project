package defpackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Qx implements Callable {
    public final File a;
    public final File b;
    public final InterfaceC0577by c;
    public final Zx d;

    public Qx(File file, File file2, InterfaceC0577by interfaceC0577by) {
        Zx zx = new Zx();
        this.a = file;
        this.b = file2;
        this.d = zx;
        this.c = interfaceC0577by;
    }

    public final void a(String str, String str2) {
        FileWriter fileWriter = new FileWriter(this.b, true);
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() / 1000);
        sb.append(",");
        sb.append(str2);
        if (str != null) {
            sb.append(",");
            sb.append(str);
        }
        sb.append('\n');
        try {
            fileWriter.write(sb.toString());
        } finally {
            fileWriter.close();
        }
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer call() {
        Objects.requireNonNull((C0738es) this.c);
        if (Es.e().h("enable-crash-reporter-for-testing")) {
            Ts.d("MDUploadCallable", "Minidump upload enabled for tests, skipping other checks.", new Object[0]);
        } else if (!Px.j(this.a)) {
            C0738es c0738es = (C0738es) this.c;
            C0850gs c0850gs = c0738es.a;
            if (!c0850gs.b) {
                Ts.d("MDUploadCallable", "Minidump upload is not permitted by user. Marking file as skipped for cleanup to prevent future uploads.", new Object[0]);
                Px.n(this.a, ".skipped");
                return 2;
            }
            Objects.requireNonNull((C0682ds) c0850gs.c);
            Objects.requireNonNull((C0682ds) c0738es.a.c);
            if (!(((C0682ds) c0738es.a.c).a.nextInt(100) < 1)) {
                Ts.d("MDUploadCallable", "Minidump upload skipped due to sampling.  Marking file as skipped for cleanup to prevent future uploads.", new Object[0]);
                Px.n(this.a, ".skipped");
                return 3;
            }
            if (!AbstractC0744ey.a(((C0738es) this.c).a.a)) {
                Ts.d("MDUploadCallable", "Minidump cannot currently be uploaded due to network constraints.", new Object[0]);
                return 1;
            }
        }
        Yx yxD = this.d.d(this.a);
        if (!yxD.b()) {
            if (yxD.a > 0) {
                Ts.d("MDUploadCallable", String.format(Locale.US, "Failed to upload %s with code: %d (%s).", this.a.getName(), Integer.valueOf(yxD.a), yxD.b), new Object[0]);
            } else {
                StringBuilder sbF = Z7.f("Local error while uploading ");
                sbF.append(this.a.getName());
                sbF.append(": ");
                sbF.append(yxD.b);
                Ts.a("MDUploadCallable", sbF.toString(), new Object[0]);
            }
            return 1;
        }
        String str = yxD.b;
        String name = this.a.getName();
        Ts.d("MDUploadCallable", "Minidump " + name + " uploaded successfully, id: " + str, new Object[0]);
        Px.n(this.a, ".up");
        try {
            a(Px.g(name), str);
        } catch (IOException unused) {
            Ts.a("MDUploadCallable", "Fail to write uploaded entry to log file", new Object[0]);
        }
        return 0;
    }
}
