package k9;

import A7.m;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import r9.C2313c;
import r9.L;
import r9.x;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f18322a = new a();

    public final void a(File file) {
        m.f(file, "file");
        if (file.delete() || !file.exists()) {
            return;
        }
        throw new IOException("failed to delete " + file);
    }

    public final void b(File file) throws IOException {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                b(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete " + file2);
            }
        }
    }

    public final boolean c(File file) {
        m.f(file, "file");
        return file.exists();
    }

    public final void d(File file, File file2) throws IOException {
        m.f(file, "from");
        m.f(file2, "to");
        a(file2);
        if (file.renameTo(file2)) {
            return;
        }
        throw new IOException("failed to rename " + file + " to " + file2);
    }

    public final C2313c e(File file) {
        m.f(file, "file");
        try {
            Logger logger = x.f21949a;
            return new C2313c(h6.m.n(new FileOutputStream(file, false), file, false), 1, new L());
        } catch (FileNotFoundException unused) {
            file.getParentFile().mkdirs();
            Logger logger2 = x.f21949a;
            return new C2313c(h6.m.n(new FileOutputStream(file, false), file, false), 1, new L());
        }
    }

    public final String toString() {
        return "FileSystem.SYSTEM";
    }
}
