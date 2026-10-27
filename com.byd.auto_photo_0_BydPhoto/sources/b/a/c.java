package b.a;

import android.content.Context;
import android.media.MediaScannerConnection;
import com.byd.util.w;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class c extends MediaScannerConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    LinkedList<String> f377a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f378b;

    public c(Context context, b bVar) {
        super(context, bVar);
        this.f377a = new LinkedList<>();
        bVar.a(this);
    }

    public synchronized void a(String str, String str2) {
        this.f377a.add(str);
        this.f378b = str2;
        if (!isConnected()) {
            connect();
        }
    }

    public int b() {
        LinkedList<String> linkedList = this.f377a;
        if (linkedList == null) {
            return 0;
        }
        return linkedList.size();
    }

    public synchronized void c() {
        if (this.f377a.size() > 0) {
            try {
                String strRemoveFirst = this.f377a.removeFirst();
                w.c("scanFile:" + strRemoveFirst);
                super.scanFile(strRemoveFirst, this.f378b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
