package b.a.k;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import com.byd.bean.FileBean;
import com.byd.util.j;
import com.byd.util.w;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile h f556a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String[] f557b;
    private Context d;
    private boolean e;
    private boolean f;
    private Handler h;
    private LinkedList<b> g = new LinkedList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Uri f558c = MediaStore.Files.getContentUri("external");

    class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f559a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f560b;

        a(String str, boolean z) {
            this.f559a = str;
            this.f560b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = h.this.g.iterator();
            while (it.hasNext()) {
                ((b) it.next()).onExternalEject(this.f559a, this.f560b);
            }
        }
    }

    public interface b {
        void onExternalEject(String str, boolean z);
    }

    private h(Context context) {
        this.d = context.getApplicationContext();
        HandlerThread handlerThread = new HandlerThread("external_loader");
        handlerThread.start();
        this.h = new Handler(handlerThread.getLooper());
    }

    private long d(String str, Cursor cursor) {
        long j;
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                j = Long.parseLong(cursor.getString(2));
            } catch (Exception unused) {
                j = 0;
            }
        } else {
            j = cursor.getLong(2) * 1000;
        }
        if (j != 0) {
            return j;
        }
        long j2 = cursor.getLong(cursor.getColumnIndexOrThrow("date_added")) * 1000;
        w.c("external file time is 0 : " + str + " read date_add : " + j2);
        return j2;
    }

    public static h e(Context context) {
        if (f556a == null) {
            synchronized (h.class) {
                if (f556a == null) {
                    f556a = new h(context);
                }
            }
        }
        return f556a;
    }

    private boolean h(String str) {
        boolean z = true;
        try {
            Cursor cursorQuery = this.d.getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{str}, "media_type=1", null, null);
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        return z;
    }

    private boolean j(File file) {
        return file.exists() && file.length() != 0;
    }

    public void b(j.a aVar, s sVar) throws InterruptedException {
        k(f(aVar), sVar);
    }

    public int c(j.a aVar) {
        String string;
        Cursor cursorQuery = this.d.getContentResolver().query(this.f558c, this.f557b, f(aVar), null, "date_modified desc");
        int i = 0;
        if (cursorQuery != null) {
            w.c("external->" + aVar.d() + " cursor count :" + cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                if (cursorQuery.getInt(3) != 0 && (string = cursorQuery.getString(1)) != null) {
                    File file = new File(string);
                    if (j(file) && !file.getName().startsWith(".")) {
                        i++;
                    }
                }
            }
            cursorQuery.close();
        }
        w.c("external->" + aVar.d() + " count:" + i);
        return i;
    }

    public String f(j.a aVar) {
        return g(aVar, "(media_type=1 or media_type=3 or mime_type like 'video/%')");
    }

    public String g(j.a aVar, String str) {
        StringBuilder sb = new StringBuilder(str);
        int i = Build.VERSION.SDK_INT;
        if (i >= 28 && this.e) {
            sb.append(" and ");
            sb.append("volume_name");
            sb.append("='");
            sb.append(aVar.d());
            sb.append("'");
        } else if (i >= 28 || !this.f) {
            boolean zE = aVar.e();
            sb.append(" and (lower(");
            sb.append("_data");
            sb.append(") like '/storage/");
            sb.append(aVar.d());
            sb.append("/%' or ");
            sb.append("lower(");
            sb.append("_data");
            sb.append(") like '");
            sb.append(!zE ? "/storage/usbotg" : "/storage/sdcard");
            sb.append("/%')");
        } else {
            sb.append(" and ");
            sb.append("storage_id");
            sb.append("=");
            sb.append(aVar.a());
        }
        String string = sb.toString();
        w.c("external selections : " + string);
        return string;
    }

    public void i() {
        if (this.f557b != null) {
            return;
        }
        LinkedList linkedList = new LinkedList();
        linkedList.add("_display_name");
        linkedList.add("_data");
        int i = Build.VERSION.SDK_INT;
        linkedList.add(i >= 29 ? "datetaken" : "date_modified");
        linkedList.add("media_type");
        linkedList.add("duration");
        boolean zH = h("volume_name");
        this.e = zH;
        if (i >= 28 && zH) {
            linkedList.add("volume_name");
        } else if (i < 28) {
            boolean zH2 = h("storage_id");
            this.f = zH2;
            if (zH2) {
                linkedList.add("storage_id");
            }
        }
        linkedList.add("date_added");
        String[] strArr = new String[linkedList.size()];
        this.f557b = strArr;
        linkedList.toArray(strArr);
    }

    public void k(String str, s sVar) throws InterruptedException {
        Cursor cursorQuery = this.d.getContentResolver().query(this.f558c, this.f557b, str, null, "date_modified desc");
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                int i = cursorQuery.getInt(3);
                if (i != 0) {
                    Thread.sleep(0L);
                    String string = cursorQuery.getString(1);
                    File file = new File(string);
                    if (j(file)) {
                        String name = file.getName();
                        if (!name.startsWith(".")) {
                            int i2 = i != 1 ? 2 : 1;
                            long jD = d(string, cursorQuery);
                            if (jD == 0) {
                                jD = file.lastModified();
                            }
                            FileBean fileBean = new FileBean(name, string, 4, i2, jD);
                            if (i2 == 2) {
                                long j = cursorQuery.getLong(4);
                                if (j == 0) {
                                    j = -1;
                                }
                                fileBean.setVideoDuration(j);
                            }
                            sVar.a(fileBean);
                        }
                    } else {
                        w.c("not available : " + file.getPath());
                    }
                }
            }
            cursorQuery.close();
        }
    }

    public void l(String str, boolean z) {
        this.h.post(new a(str, z));
    }

    public void m(b bVar) {
        if (this.g.contains(bVar)) {
            return;
        }
        this.g.addFirst(bVar);
    }

    public void n() {
        if (this.f557b == null) {
            i();
        }
        if (com.byd.util.j.e(this.d).q() == 0) {
            com.byd.util.j.e(this.d).r();
        }
    }

    public void o(b bVar) {
        this.g.remove(bVar);
    }
}
