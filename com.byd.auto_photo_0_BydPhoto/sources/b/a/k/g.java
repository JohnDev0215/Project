package b.a.k;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import com.byd.util.w;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile g f547a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f549c;
    private com.byd.taskmanager.b.b<String> d = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile HashMap<String, com.byd.taskmanager.b.a> f548b = new HashMap<>(5);

    class a implements com.byd.taskmanager.b.b<String> {
        a() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, String str2) {
            if (str == null) {
                return;
            }
            g.this.e(str);
        }

        @Override // com.byd.taskmanager.b.b
        public void onCanceled(String str) {
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    private class b extends com.byd.taskmanager.b.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private List<String> f551a;

        private b(List<String> list) {
            super(g.this.d);
            this.f551a = list;
        }

        /* synthetic */ b(g gVar, List list, a aVar) {
            this(list);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public String doTask() {
            w.c("delete from mediaStore start");
            a aVar = null;
            try {
                int size = this.f551a.size();
                String[] strArr = new String[size];
                this.f551a.toArray(strArr);
                MediaScannerConnection.scanFile(g.this.f549c, strArr, null, new c(g.this, size, this.name, aVar));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class c implements MediaScannerConnection.OnScanCompletedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f553a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f554b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f555c;

        private c(int i, String str) {
            this.f555c = i;
            this.f553a = str;
        }

        /* synthetic */ c(g gVar, int i, String str, a aVar) {
            this(i, str);
        }

        @Override // android.media.MediaScannerConnection.OnScanCompletedListener
        public synchronized void onScanCompleted(String str, Uri uri) {
            int i = this.f554b + 1;
            this.f554b = i;
            if (i >= this.f555c) {
                g.this.e(this.f553a);
            }
        }
    }

    private g(Context context) {
        this.f549c = context.getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void e(String str) {
        w.c("doTaskComplete : " + str);
        if (this.f548b.remove(str) != null) {
            com.byd.taskmanager.a.e().h(str);
            if (this.f548b.size() == 0) {
                w.c("delete complete!!!");
            }
        }
    }

    public static g f(Context context) {
        synchronized (g.class) {
            if (f547a == null) {
                f547a = new g(context);
            }
        }
        return f547a;
    }

    public void d(List<String> list) {
        try {
            b bVar = new b(this, new LinkedList(list), null);
            bVar.setTaskName("del_media_" + System.currentTimeMillis());
            this.f548b.put(bVar.getTaskName(), bVar);
            com.byd.taskmanager.a.e().d(bVar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean g() {
        return this.f548b.size() > 0;
    }
}
