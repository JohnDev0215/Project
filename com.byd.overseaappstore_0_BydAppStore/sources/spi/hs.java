package spi;

import android.util.Log;
import androidx.annotation.NonNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class hs implements f8<InputStream> {
    public final m0 a;

    public hs(m0 m0Var) {
        this.a = m0Var;
    }

    @Override // spi.f8
    public final boolean b(@NonNull InputStream inputStream, @NonNull File file, @NonNull lk lkVar) throws Throwable {
        FileOutputStream fileOutputStream;
        InputStream inputStream2 = inputStream;
        byte[] bArr = (byte[]) this.a.d(65536, byte[].class);
        boolean z = false;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                }
            } catch (IOException e) {
                e = e;
            }
        } catch (IOException unused) {
        }
        while (true) {
            try {
                int i = inputStream2.read(bArr);
                if (i == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, i);
            } catch (IOException e2) {
                e = e2;
                fileOutputStream2 = fileOutputStream;
                if (Log.isLoggable("StreamEncoder", 3)) {
                    Log.d("StreamEncoder", "Failed to encode data onto the OutputStream", e);
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                this.a.c(bArr);
                return z;
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                this.a.c(bArr);
                throw th;
            }
            this.a.c(bArr);
            return z;
        }
        fileOutputStream.close();
        z = true;
        fileOutputStream.close();
        this.a.c(bArr);
        return z;
    }
}
