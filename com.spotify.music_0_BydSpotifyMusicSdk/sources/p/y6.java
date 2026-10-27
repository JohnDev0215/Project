package p;

import com.google.protobuf.InvalidProtocolBufferException;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class y6 implements kx {
    public final File a;
    public final File b;

    public y6(File file, File file2) {
        this.a = file;
        this.b = file2;
    }

    public static void a(File file) {
        if (file.delete()) {
            hk0 hk0VarA = qk2.a();
            file.toString();
            hk0VarA.getClass();
            hk0.t(new Object[0]);
            return;
        }
        hk0 hk0VarA2 = qk2.a();
        file.toString();
        hk0VarA2.getClass();
        hk0.u(new Object[0]);
    }

    public static m02 c(File file) {
        if (!file.exists() || file.length() == 0) {
            hk0 hk0VarA = qk2.a();
            file.toString();
            hk0VarA.getClass();
            hk0.A(new Object[0]);
            return null;
        }
        try {
            w01 w01VarA = v01.a(d(file));
            m02 m02Var = m02.c;
            return zb0.u(w01VarA);
        } catch (InvalidProtocolBufferException unused) {
            hk0 hk0VarA2 = qk2.a();
            file.toString();
            hk0VarA2.getClass();
            hk0.v(new Object[0]);
            return null;
        }
    }

    public static byte[] d(File file) {
        byte[] bArr = new byte[(int) file.length()];
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            try {
                dataInputStream.readFully(bArr);
                ux2.e(dataInputStream, null);
            } finally {
            }
        } catch (IOException unused) {
            hk0 hk0VarA = qk2.a();
            file.toString();
            hk0VarA.getClass();
            hk0.v(new Object[0]);
        }
        return bArr;
    }

    public final synchronized m02 b() {
        m02 m02VarC;
        m02 m02VarC2 = c(this.a);
        if (m02VarC2 != null) {
            return m02VarC2;
        }
        File file = this.b;
        return (file == null || (m02VarC = c(file)) == null) ? m02.c : m02VarC;
    }

    public final void e(byte[] bArr) {
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
