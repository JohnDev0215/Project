package r9;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import p1.C2078f;

/* JADX INFO: loaded from: classes.dex */
public class v extends o {
    @Override // r9.o
    public final void b(z zVar) throws IOException {
        if (zVar.f().mkdir()) {
            return;
        }
        C2078f c2078fH = h(zVar);
        if (c2078fH == null || !c2078fH.c) {
            throw new IOException("failed to create directory: " + zVar);
        }
    }

    @Override // r9.o
    public final void c(z zVar) throws IOException {
        A7.m.f(zVar, "path");
        if (Thread.interrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        File fileF = zVar.f();
        if (fileF.delete() || !fileF.exists()) {
            return;
        }
        throw new IOException("failed to delete " + zVar);
    }

    @Override // r9.o
    public final List f(z zVar) throws IOException {
        A7.m.f(zVar, "dir");
        File fileF = zVar.f();
        String[] list = fileF.list();
        if (list == null) {
            if (fileF.exists()) {
                throw new IOException("failed to list " + zVar);
            }
            throw new FileNotFoundException("no such file: " + zVar);
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            A7.m.c(str);
            arrayList.add(zVar.e(str));
        }
        if (arrayList.size() > 1) {
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    @Override // r9.o
    public C2078f h(z zVar) {
        A7.m.f(zVar, "path");
        File fileF = zVar.f();
        boolean zIsFile = fileF.isFile();
        boolean zIsDirectory = fileF.isDirectory();
        long jLastModified = fileF.lastModified();
        long length = fileF.length();
        if (!zIsFile && !zIsDirectory && jLastModified == 0 && length == 0 && !fileF.exists()) {
            return null;
        }
        return new C2078f(zIsFile, zIsDirectory, null, Long.valueOf(length), null, Long.valueOf(jLastModified), null);
    }

    @Override // r9.o
    public final u i(z zVar) {
        return new u(false, new RandomAccessFile(zVar.f(), "r"));
    }

    @Override // r9.o
    public final H j(z zVar) {
        A7.m.f(zVar, "file");
        File fileF = zVar.f();
        Logger logger = x.f21949a;
        return new C2313c(h6.m.n(new FileOutputStream(fileF, false), fileF, false), 1, new L());
    }

    @Override // r9.o
    public final J k(z zVar) {
        A7.m.f(zVar, "file");
        return AbstractC2312b.i(zVar.f());
    }

    public void l(z zVar, z zVar2) throws IOException {
        A7.m.f(zVar, BYDAutoMultimediaDevice.PARAM_SOURCE);
        A7.m.f(zVar2, "target");
        if (zVar.f().renameTo(zVar2.f())) {
            return;
        }
        throw new IOException("failed to move " + zVar + " to " + zVar2);
    }

    public String toString() {
        return "JvmSystemFileSystem";
    }
}
