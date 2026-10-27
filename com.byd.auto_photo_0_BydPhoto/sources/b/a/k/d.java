package b.a.k;

import android.content.Context;
import com.byd.bean.FileBean;
import com.byd.dilink.hardware.watermark.DiLinkFontUtilsConstants;
import com.byd.util.w;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f537a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f538b;

    class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.isDirectory();
        }
    }

    class b implements FilenameFilter {
        b() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.endsWith(".jpg") || str.endsWith(DiLinkFontUtilsConstants.FILE_PNG_SUFFIX);
        }
    }

    private class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f541a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private s f542b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Context f543c;
        private String d;
        private boolean e;

        private c(String str, s sVar, Context context) {
            this.f541a = str;
            this.f542b = sVar;
            this.f543c = context;
        }

        /* synthetic */ c(d dVar, String str, s sVar, Context context, a aVar) {
            this(str, sVar, context);
        }

        public void a(String str) {
            this.d = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.e = false;
            w.c("loadFromCamera task " + this.d);
            try {
                com.byd.util.n.y(this.f543c, this.f541a, d.this.f537a, this.f542b);
            } catch (Exception e) {
                e.printStackTrace();
                w.c("load from folder exception : " + e.getMessage());
            }
            w.c("task finish :" + this.d);
            this.e = true;
        }
    }

    private void b(Context context, s sVar) throws InterruptedException {
        int length;
        File[] fileArrListFiles = new File(com.byd.util.g.f(context)).listFiles(new a());
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            return;
        }
        for (File file : fileArrListFiles) {
            Thread.sleep(0L);
            File[] fileArrListFiles2 = file.listFiles(new b());
            if (fileArrListFiles2 != null) {
                Arrays.sort(fileArrListFiles2, com.byd.util.n.f2983a);
                length = fileArrListFiles2.length;
            } else {
                length = 0;
            }
            if (length == 0) {
                com.byd.util.n.f(file);
            } else {
                File file2 = fileArrListFiles2[0];
                FileBean fileBean = new FileBean(file.getName(), file2.getPath(), 2, 3, file2.lastModified());
                fileBean.setNum(length);
                sVar.a(fileBean);
            }
        }
    }

    public void c(Context context, s sVar) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        w.c("loadFromCamera start");
        c cVar = new c(this, com.byd.util.g.g(context), sVar, context, null);
        cVar.a("l0");
        cVar.run();
        if (!this.f538b) {
            c cVar2 = new c(this, com.byd.util.g.k(context), sVar, context, null);
            cVar2.a("l2");
            cVar2.run();
        }
        try {
            if (this.f537a != 2) {
                b(context, sVar);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        w.c("loadFromCamera finish " + (System.currentTimeMillis() - jCurrentTimeMillis));
    }

    public void d(boolean z) {
        this.f538b = z;
    }

    public void e(int i) {
        this.f537a = i;
    }
}
