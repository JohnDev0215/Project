package defpackage;

import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.c;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: AtomicFile.java */
/* JADX INFO: loaded from: classes.dex */
public final class j9 {
    public final Object a;
    public final Object b;
    public final Object c;

    public /* synthetic */ j9(ViewPager2 viewPager2, c cVar, RecyclerView recyclerView) {
        this.a = viewPager2;
        this.b = cVar;
        this.c = recyclerView;
    }

    public static void e(File file, File file2) {
        if (file2.isDirectory() && !file2.delete()) {
            Log.e("AtomicFile", "Failed to delete file which is a directory " + file2);
        }
        if (file.renameTo(file2)) {
            return;
        }
        Log.e("AtomicFile", "Failed to rename " + file + " to " + file2);
    }

    public final void a(FileOutputStream fileOutputStream) {
        boolean z;
        if (fileOutputStream == null) {
            return;
        }
        try {
            fileOutputStream.getFD().sync();
            z = true;
        } catch (IOException unused) {
            z = false;
        }
        if (!z) {
            Log.e("AtomicFile", "Failed to sync file output stream");
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            Log.e("AtomicFile", "Failed to close file output stream", e);
        }
        File file = (File) this.b;
        if (file.delete()) {
            return;
        }
        Log.e("AtomicFile", "Failed to delete new file " + file);
    }

    public final void b(FileOutputStream fileOutputStream) {
        boolean z;
        if (fileOutputStream == null) {
            return;
        }
        try {
            fileOutputStream.getFD().sync();
            z = true;
        } catch (IOException unused) {
            z = false;
        }
        if (!z) {
            Log.e("AtomicFile", "Failed to sync file output stream");
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            Log.e("AtomicFile", "Failed to close file output stream", e);
        }
        e((File) this.b, (File) this.a);
    }

    public final FileInputStream c() {
        Object obj = this.c;
        boolean zExists = ((File) obj).exists();
        Object obj2 = this.a;
        if (zExists) {
            e((File) obj, (File) obj2);
        }
        Object obj3 = this.b;
        if (((File) obj3).exists() && ((File) obj2).exists() && !((File) obj3).delete()) {
            Log.e("AtomicFile", "Failed to delete outdated new file " + ((File) obj3));
        }
        return new FileInputStream((File) obj2);
    }

    public final byte[] d() throws IOException {
        FileInputStream fileInputStreamC = c();
        try {
            byte[] bArr = new byte[fileInputStreamC.available()];
            int i = 0;
            while (true) {
                int i2 = fileInputStreamC.read(bArr, i, bArr.length - i);
                if (i2 <= 0) {
                    return bArr;
                }
                i += i2;
                int iAvailable = fileInputStreamC.available();
                if (iAvailable > bArr.length - i) {
                    byte[] bArr2 = new byte[iAvailable + i];
                    System.arraycopy(bArr, 0, bArr2, 0, i);
                    bArr = bArr2;
                }
            }
        } finally {
            fileInputStreamC.close();
        }
    }

    public final FileOutputStream f() throws IOException {
        Object obj = this.b;
        Object obj2 = this.c;
        if (((File) obj2).exists()) {
            e((File) obj2, (File) this.a);
        }
        try {
            return new FileOutputStream((File) obj);
        } catch (FileNotFoundException unused) {
            if (!((File) obj).getParentFile().mkdirs()) {
                throw new IOException("Failed to create directory for " + ((File) obj));
            }
            try {
                return new FileOutputStream((File) obj);
            } catch (FileNotFoundException e) {
                throw new IOException("Failed to create new file " + ((File) obj), e);
            }
        }
    }

    public /* synthetic */ j9(File file) {
        this.a = file;
        this.b = new File(file.getPath() + ".new");
        this.c = new File(file.getPath() + ".bak");
    }
}
