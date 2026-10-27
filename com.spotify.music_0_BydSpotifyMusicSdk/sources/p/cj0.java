package p;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: classes.dex */
public interface cj0 {
    default void a(Throwable th) {
        v03.j(th, "error");
    }

    String b();

    void c(FileInputStream fileInputStream);

    default void d(Throwable th) {
        v03.j(th, "error");
    }

    void e(FileOutputStream fileOutputStream);
}
