package p;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ey1 {
    public final File a;

    public ey1(File file) {
        this.a = file;
    }

    public final void a(byte[] bArr) {
        File file = this.a;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                hk0 hk0VarA = qk2.a();
                Objects.toString(file);
                hk0VarA.getClass();
                hk0.t(new Object[0]);
                fileOutputStream.write(bArr);
                ux2.e(fileOutputStream, null);
            } finally {
            }
        } catch (IOException unused) {
            hk0 hk0VarA2 = qk2.a();
            Objects.toString(file);
            hk0VarA2.getClass();
            hk0.v(new Object[0]);
        }
    }
}
