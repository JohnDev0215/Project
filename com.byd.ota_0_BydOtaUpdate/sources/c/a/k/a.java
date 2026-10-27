package c.a.k;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;

/* JADX INFO: compiled from: FileUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final File[] f2882a = new File[0];

    public static void a(File file, File file2) throws IOException {
        Files.copy(file.toPath(), file2.toPath(), new CopyOption[0]);
    }
}
