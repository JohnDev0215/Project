package q3;

import h6.m;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: renamed from: q3.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2182i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f21247a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f21248b;
    public final long c;
    public final long d;

    public C2182i(int i7, int i10, long j3, long j10) {
        this.f21247a = i7;
        this.f21248b = i10;
        this.c = j3;
        this.d = j10;
    }

    public static C2182i a(File file) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(h6.j.h(new FileInputStream(file), file));
        try {
            C2182i c2182i = new C2182i(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readLong());
            dataInputStream.close();
            return c2182i;
        } catch (Throwable th) {
            try {
                dataInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final void b(File file) throws IOException {
        file.delete();
        DataOutputStream dataOutputStream = new DataOutputStream(m.m(new FileOutputStream(file), file));
        try {
            dataOutputStream.writeInt(this.f21247a);
            dataOutputStream.writeInt(this.f21248b);
            dataOutputStream.writeLong(this.c);
            dataOutputStream.writeLong(this.d);
            dataOutputStream.close();
        } catch (Throwable th) {
            try {
                dataOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C2182i)) {
            return false;
        }
        C2182i c2182i = (C2182i) obj;
        return this.f21248b == c2182i.f21248b && this.c == c2182i.c && this.f21247a == c2182i.f21247a && this.d == c2182i.d;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.f21248b), Long.valueOf(this.c), Integer.valueOf(this.f21247a), Long.valueOf(this.d));
    }
}
