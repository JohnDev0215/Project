package p;

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

/* JADX INFO: loaded from: classes.dex */
public class c81 extends hu0 {
    @Override // p.hu0
    public final pd2 a(os1 os1Var) {
        File fileE = os1Var.e();
        Logger logger = jp1.a;
        return new sc(new FileOutputStream(fileE, true), new tl2());
    }

    @Override // p.hu0
    public void b(os1 os1Var, os1 os1Var2) throws IOException {
        v03.j(os1Var, "source");
        v03.j(os1Var2, "target");
        if (os1Var.e().renameTo(os1Var2.e())) {
            return;
        }
        throw new IOException("failed to move " + os1Var + " to " + os1Var2);
    }

    @Override // p.hu0
    public final void c(os1 os1Var) throws IOException {
        if (os1Var.e().mkdir()) {
            return;
        }
        x50 x50VarI = i(os1Var);
        if (x50VarI == null || !x50VarI.c) {
            throw new IOException("failed to create directory: " + os1Var);
        }
    }

    @Override // p.hu0
    public final void d(os1 os1Var) throws IOException {
        v03.j(os1Var, "path");
        if (Thread.interrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        File fileE = os1Var.e();
        if (fileE.delete() || !fileE.exists()) {
            return;
        }
        throw new IOException("failed to delete " + os1Var);
    }

    @Override // p.hu0
    public final List g(os1 os1Var) throws IOException {
        v03.j(os1Var, "dir");
        File fileE = os1Var.e();
        String[] list = fileE.list();
        if (list == null) {
            if (fileE.exists()) {
                throw new IOException("failed to list " + os1Var);
            }
            throw new FileNotFoundException("no such file: " + os1Var);
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            v03.h(str);
            arrayList.add(os1Var.d(str));
        }
        if (arrayList.size() > 1) {
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    @Override // p.hu0
    public x50 i(os1 os1Var) {
        v03.j(os1Var, "path");
        File fileE = os1Var.e();
        boolean zIsFile = fileE.isFile();
        boolean zIsDirectory = fileE.isDirectory();
        long jLastModified = fileE.lastModified();
        long length = fileE.length();
        if (zIsFile || zIsDirectory || jLastModified != 0 || length != 0 || fileE.exists()) {
            return new x50(zIsFile, zIsDirectory, null, Long.valueOf(length), null, Long.valueOf(jLastModified), null);
        }
        return null;
    }

    @Override // p.hu0
    public final b81 j(os1 os1Var) {
        v03.j(os1Var, "file");
        return new b81(new RandomAccessFile(os1Var.e(), "r"));
    }

    @Override // p.hu0
    public final pd2 k(os1 os1Var) {
        v03.j(os1Var, "file");
        File fileE = os1Var.e();
        Logger logger = jp1.a;
        return new sc(new FileOutputStream(fileE, false), new tl2());
    }

    @Override // p.hu0
    public final de2 l(os1 os1Var) {
        v03.j(os1Var, "file");
        return v03.K(os1Var.e());
    }

    public String toString() {
        return "JvmSystemFileSystem";
    }
}
