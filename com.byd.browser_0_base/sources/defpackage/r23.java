package defpackage;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* JADX INFO: compiled from: TabPersistentStore.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r23 extends ac<DataInputStream> {
    public final /* synthetic */ String h;
    public final /* synthetic */ z23 i;

    public r23(z23 z23Var, String str) {
        this.i = z23Var;
        this.h = str;
    }

    @Override // defpackage.i9
    public final Object b() throws Throwable {
        IOException e;
        FileInputStream fileInputStream;
        Object obj = z23.A;
        File file = new File(this.i.g(), this.h);
        FileInputStream fileInputStream2 = null;
        if (!file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                try {
                    byte[] bArr = new byte[(int) file.length()];
                    fileInputStream.read(bArr);
                    a8.s(fileInputStream);
                    return new DataInputStream(new ByteArrayInputStream(bArr));
                } catch (IOException e2) {
                    e = e2;
                    Log.e("cr_tabmodel", "Could not read state file.", e);
                    a8.s(fileInputStream);
                    return null;
                }
            } catch (Throwable th) {
                fileInputStream2 = fileInputStream;
                th = th;
                a8.s(fileInputStream2);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            a8.s(fileInputStream2);
            throw th;
        }
    }
}
