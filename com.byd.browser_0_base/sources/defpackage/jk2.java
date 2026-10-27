package defpackage;

import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: ShareImageFileUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jk2 {

    /* JADX INFO: compiled from: ShareImageFileUtils.java */
    public static class c implements Callback<String> {
        public final Callback<Uri> e;

        public c(yo1 yo1Var) {
            this.e = yo1Var;
        }

        @Override // org.chromium.base.Callback
        public final void onResult(String str) {
            String str2 = str;
            if (TextUtils.isEmpty(str2)) {
                this.e.onResult(null);
            } else {
                new nk2(this, str2).c(i9.e);
            }
        }
    }

    /* JADX INFO: compiled from: ShareImageFileUtils.java */
    public interface d {
        void s(FileOutputStream fileOutputStream, sl0 sl0Var);
    }

    /* JADX INFO: compiled from: ShareImageFileUtils.java */
    public interface e {
        void a(Uri uri);

        void b();
    }

    public static File a(String str, String str2, boolean z) throws IOException {
        File fileD = d();
        if (!fileD.exists() && !fileD.mkdir()) {
            return null;
        }
        if (z) {
            return File.createTempFile(str, str2, fileD);
        }
        File file = new File("", gq2.f(str, str2));
        int i = 0;
        while (file.exists()) {
            StringBuilder sbJ = C0173a.j(str);
            i++;
            sbJ.append(String.format(Locale.getDefault(), " (%d)", Integer.valueOf(i)));
            sbJ.append(str2);
            file = new File("", sbJ.toString());
        }
        file.createNewFile();
        return file;
    }

    public static void b(String str, Bitmap bitmap, Callback<Uri> callback) {
        e(str, new b(callback), new gc3(bitmap), bitmap.hasAlpha() ? ".png" : ".jpg");
    }

    public static void c(byte[] bArr, String str, Callback<Uri> callback) {
        if (bArr.length == 0) {
            Log.w("cr_share", "Share failed -- Received image contains no data.");
        } else {
            e(String.valueOf(System.currentTimeMillis()), new a(callback), new gx(28, bArr), str);
        }
    }

    public static File d() {
        return new File(uh3.c(xz.a), "screenshot");
    }

    public static void e(String str, e eVar, d dVar, String str2) {
        n62 n62Var = new n62(eVar, 22, str);
        PostTask.c(d73.g, new mk2(str, str2, dVar, new qt(n62Var), n62Var));
    }

    /* JADX INFO: compiled from: ShareImageFileUtils.java */
    public class a implements e {
        public final /* synthetic */ Callback a;

        public a(Callback callback) {
            this.a = callback;
        }

        @Override // jk2.e
        public final void a(Uri uri) {
            this.a.onResult(uri);
        }

        @Override // jk2.e
        public final void b() {
        }
    }

    /* JADX INFO: compiled from: ShareImageFileUtils.java */
    public class b implements e {
        public final /* synthetic */ Callback a;

        public b(Callback callback) {
            this.a = callback;
        }

        @Override // jk2.e
        public final void a(Uri uri) {
            this.a.onResult(uri);
        }

        @Override // jk2.e
        public final void b() {
        }
    }
}
