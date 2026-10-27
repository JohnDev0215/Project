package spi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public interface yb {

    @JvmField
    @NotNull
    public static final a.C0145a a;

    public static final class a {

        /* JADX INFO: renamed from: spi.yb$a$a, reason: collision with other inner class name */
        public static final class C0145a implements yb {
            @Override // spi.yb
            @NotNull
            public final vr a(@NotNull File file) throws FileNotFoundException {
                Intrinsics.checkNotNullParameter(file, "file");
                return b.l(file);
            }

            @Override // spi.yb
            @NotNull
            public final rp b(@NotNull File file) throws FileNotFoundException {
                Intrinsics.checkNotNullParameter(file, "file");
                try {
                    return b.k(file);
                } catch (FileNotFoundException unused) {
                    file.getParentFile().mkdirs();
                    return b.k(file);
                }
            }

            @Override // spi.yb
            public final void c(@NotNull File directory) throws IOException {
                Intrinsics.checkNotNullParameter(directory, "directory");
                File[] fileArrListFiles = directory.listFiles();
                if (fileArrListFiles == null) {
                    throw new IOException("not a readable directory: " + directory);
                }
                for (File file : fileArrListFiles) {
                    Intrinsics.checkNotNullExpressionValue(file, "file");
                    if (file.isDirectory()) {
                        c(file);
                    }
                    if (!file.delete()) {
                        throw new IOException("failed to delete " + file);
                    }
                }
            }

            @Override // spi.yb
            public final boolean d(@NotNull File file) {
                Intrinsics.checkNotNullParameter(file, "file");
                return file.exists();
            }

            @Override // spi.yb
            public final void e(@NotNull File from, @NotNull File to) throws IOException {
                Intrinsics.checkNotNullParameter(from, "from");
                Intrinsics.checkNotNullParameter(to, "to");
                f(to);
                if (from.renameTo(to)) {
                    return;
                }
                throw new IOException("failed to rename " + from + " to " + to);
            }

            @Override // spi.yb
            public final void f(@NotNull File file) throws IOException {
                Intrinsics.checkNotNullParameter(file, "file");
                if (file.delete() || !file.exists()) {
                    return;
                }
                throw new IOException("failed to delete " + file);
            }

            @Override // spi.yb
            @NotNull
            public final rp g(@NotNull File file) throws FileNotFoundException {
                Intrinsics.checkNotNullParameter(file, "file");
                try {
                    return b.a(file);
                } catch (FileNotFoundException unused) {
                    file.getParentFile().mkdirs();
                    return b.a(file);
                }
            }

            @Override // spi.yb
            public final long h(@NotNull File file) {
                Intrinsics.checkNotNullParameter(file, "file");
                return file.length();
            }

            @NotNull
            public final String toString() {
                return "FileSystem.SYSTEM";
            }
        }

        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        new a(null);
        a = new a.C0145a();
    }

    @NotNull
    vr a(@NotNull File file) throws FileNotFoundException;

    @NotNull
    rp b(@NotNull File file) throws FileNotFoundException;

    void c(@NotNull File file) throws IOException;

    boolean d(@NotNull File file);

    void e(@NotNull File file, @NotNull File file2) throws IOException;

    void f(@NotNull File file) throws IOException;

    @NotNull
    rp g(@NotNull File file) throws FileNotFoundException;

    long h(@NotNull File file);
}
