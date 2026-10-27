package b.a.h;

import com.byd.util.w;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
class h implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f488a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f489b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private k f490c;

    public h(String str, String str2, k kVar) {
        this.f488a = str;
        this.f489b = str2;
        this.f490c = kVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            List<String> listI = this.f490c.i(this.f488a);
            if (listI != null && listI.size() >= 1) {
                Iterator<String> it = listI.iterator();
                while (it.hasNext()) {
                    new File(this.f489b + File.separator + it.next()).delete();
                }
                w.c("RemoveThumbRecordInFolder : " + this.f488a);
            }
        } catch (Exception e) {
            e.printStackTrace();
            w.c("RemoveThumbRecordInFolder exception : " + e.getMessage());
        }
    }
}
