package okhttp3.internal.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import p.jp1;
import p.sc;
import p.tc;
import p.tl2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public interface FileSystem {
    public static final FileSystem a;

    public static final class Companion {
        public static final /* synthetic */ int a = 0;

        public static final class SystemFileSystem implements FileSystem {
            @Override // okhttp3.internal.io.FileSystem
            public final void a(File file) throws IOException {
                v03.j(file, "file");
                if (file.delete() || !file.exists()) {
                    return;
                }
                throw new IOException("failed to delete " + file);
            }

            @Override // okhttp3.internal.io.FileSystem
            public final tc b(File file) {
                v03.j(file, "file");
                return v03.K(file);
            }

            @Override // okhttp3.internal.io.FileSystem
            public final sc c(File file) {
                v03.j(file, "file");
                try {
                    Logger logger = jp1.a;
                    return new sc(new FileOutputStream(file, false), new tl2());
                } catch (FileNotFoundException unused) {
                    file.getParentFile().mkdirs();
                    Logger logger2 = jp1.a;
                    return new sc(new FileOutputStream(file, false), new tl2());
                }
            }

            @Override // okhttp3.internal.io.FileSystem
            public final void d(File file) throws IOException {
                v03.j(file, "directory");
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles == null) {
                    throw new IOException("not a readable directory: " + file);
                }
                for (File file2 : fileArrListFiles) {
                    if (file2.isDirectory()) {
                        d(file2);
                    }
                    if (!file2.delete()) {
                        throw new IOException("failed to delete " + file2);
                    }
                }
            }

            @Override // okhttp3.internal.io.FileSystem
            public final sc e(File file) {
                v03.j(file, "file");
                try {
                    Logger logger = jp1.a;
                    return new sc(new FileOutputStream(file, true), new tl2());
                } catch (FileNotFoundException unused) {
                    file.getParentFile().mkdirs();
                    Logger logger2 = jp1.a;
                    return new sc(new FileOutputStream(file, true), new tl2());
                }
            }

            @Override // okhttp3.internal.io.FileSystem
            public final boolean f(File file) {
                v03.j(file, "file");
                return file.exists();
            }

            @Override // okhttp3.internal.io.FileSystem
            public final void g(File file, File file2) throws IOException {
                v03.j(file, "from");
                v03.j(file2, "to");
                a(file2);
                if (file.renameTo(file2)) {
                    return;
                }
                throw new IOException("failed to rename " + file + " to " + file2);
            }

            @Override // okhttp3.internal.io.FileSystem
            public final long h(File file) {
                v03.j(file, "file");
                return file.length();
            }

            public final String toString() {
                return "FileSystem.SYSTEM";
            }
        }

        static {
            new Companion();
        }

        private Companion() {
        }
    }

    static {
        int i = Companion.a;
        a = new Companion.SystemFileSystem();
    }

    void a(File file);

    tc b(File file);

    sc c(File file);

    void d(File file);

    sc e(File file);

    boolean f(File file);

    void g(File file, File file2);

    long h(File file);
}
