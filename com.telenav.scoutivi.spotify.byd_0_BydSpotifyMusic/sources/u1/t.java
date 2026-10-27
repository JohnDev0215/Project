package u1;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: StreamEncoder.java */
/* JADX INFO: loaded from: classes.dex */
public class t implements n1.d<InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final r1.b f9644a;

    public t(r1.b bVar) {
        this.f9644a = bVar;
    }

    @Override // n1.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean b(InputStream inputStream, File file, n1.i iVar) throws Throwable {
        FileOutputStream fileOutputStream;
        byte[] bArr = (byte[]) this.f9644a.e(65536, byte[].class);
        boolean z6 = false;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e7) {
                e = e7;
            }
        } catch (IOException unused) {
        }
        while (true) {
            try {
                int i7 = inputStream.read(bArr);
                if (i7 == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, i7);
            } catch (IOException e8) {
                e = e8;
                fileOutputStream2 = fileOutputStream;
                if (Log.isLoggable("StreamEncoder", 3)) {
                    Log.d("StreamEncoder", "Failed to encode data onto the OutputStream", e);
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                this.f9644a.d(bArr);
                return z6;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException unused2) {
                    }
                }
                this.f9644a.d(bArr);
                throw th;
            }
            this.f9644a.d(bArr);
            return z6;
        }
        fileOutputStream.close();
        z6 = true;
        fileOutputStream.close();
        this.f9644a.d(bArr);
        return z6;
    }
}
