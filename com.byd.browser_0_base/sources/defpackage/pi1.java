package defpackage;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: MostVisitedSitesMetadataUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pi1 {
    public static final Object c = new Object();
    public static final Object d = new Object();
    public static File e;
    public Runnable a;
    public l91 b;

    /* JADX INFO: compiled from: MostVisitedSitesMetadataUtils.java */
    public static class a {
        public static final pi1 a = new pi1();
    }

    public static void a(File file, byte[] bArr) {
        FileOutputStream fileOutputStreamF;
        synchronized (d) {
            File file2 = new File(file, "top_sites");
            j9 j9Var = new j9(file2);
            try {
                fileOutputStreamF = j9Var.f();
            } catch (IOException unused) {
                fileOutputStreamF = null;
            }
            try {
                fileOutputStreamF.write(bArr, 0, bArr.length);
                j9Var.b(fileOutputStreamF);
                Log.i("cr_TopSites", "Finished saving top sites list to file:" + file2.getAbsolutePath());
            } catch (IOException unused2) {
                if (fileOutputStreamF != null) {
                    j9Var.a(fileOutputStreamF);
                }
                Log.e("cr_TopSites", "Fail to write file: " + file2.getAbsolutePath());
            }
        }
    }

    public static byte[] b(List list) throws IOException {
        int size = list.size();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeInt(size);
        for (int i = 0; i < size; i++) {
            dataOutputStream.writeInt(1);
            dataOutputStream.writeInt(((w93) list.get(i)).b);
            ao2 ao2Var = ((w93) list.get(i)).a;
            dataOutputStream.writeUTF(ao2Var.a);
            dataOutputStream.writeUTF(ao2Var.b.m());
            dataOutputStream.writeUTF("");
            dataOutputStream.writeInt(ao2Var.c);
            dataOutputStream.writeInt(ao2Var.d);
            dataOutputStream.writeInt(ao2Var.e);
        }
        dataOutputStream.close();
        Log.i("cr_TopSites", "Serializing top sites lists finished; count: " + size);
        return byteArrayOutputStream.toByteArray();
    }

    public static ArrayList c(byte[] bArr) throws IOException {
        if (bArr.length == 0) {
            return null;
        }
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        int i = dataInputStream.readInt();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            if (dataInputStream.readInt() > 1) {
                throw new IOException("Cache version not supported.");
            }
            int i3 = dataInputStream.readInt();
            String utf = dataInputStream.readUTF();
            GURL gurlA = GURL.a(dataInputStream.readUTF());
            if (gurlA.k()) {
                throw new IOException("GURL deserialization failed.");
            }
            dataInputStream.readUTF();
            arrayList.add(new w93(new ao2(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt(), utf, gurlA), i3));
        }
        Log.i("cr_TopSites", "Deserializing top sites lists finished");
        return arrayList;
    }

    public static File d() {
        ku2 ku2VarC = ku2.C();
        try {
            synchronized (c) {
                if (e == null) {
                    e = xz.a.getDir("top_sites", 0);
                }
            }
            File file = e;
            ku2VarC.close();
            return file;
        } catch (Throwable th) {
            try {
                ku2VarC.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public static ArrayList e() throws IOException {
        try {
            File file = new File(d(), "top_sites");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[(int) file.length()];
            fileInputStream.read(bArr);
            Log.i("cr_TopSites", "Finished fetching top sites list.");
            a8.s(fileInputStream);
            return c(bArr);
        } catch (IOException e2) {
            d().delete();
            throw e2;
        }
    }
}
