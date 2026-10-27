package b.a.k;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import com.byd.bean.FileBean;
import com.byd.dilink.provider.DiLinkBluetoothDeviceConstants;
import com.byd.util.w;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class i extends r {
    public static final Uri d = MediaStore.Files.getContentUri("external");
    protected int e;
    protected String f;
    protected boolean g;

    public i(Context context, boolean z, int i) {
        super(context);
        this.g = z;
        this.e = i;
        e();
    }

    private long h(String str, Cursor cursor) {
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
        return j == 0 ? cursor.getLong(cursor.getColumnIndexOrThrow("date_added")) * 1000 : j;
    }

    @Override // b.a.k.r
    protected String[] a() {
        if (this.f594c == null) {
            List<String> listD = d();
            String[] strArr = new String[listD.size()];
            this.f594c = strArr;
            listD.toArray(strArr);
        }
        return this.f594c;
    }

    @Override // b.a.k.r
    protected Uri b() {
        return d;
    }

    @Override // b.a.k.r
    public void c(s sVar) {
        super.c(sVar);
        long jCurrentTimeMillis = System.currentTimeMillis();
        w.c("start load : " + this.f593b);
        Cursor cursorQuery = this.f592a.getContentResolver().query(this.f593b, this.f594c, this.f, null, "date_modified desc");
        w.c("load from media store query ,cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + " " + cursorQuery.getCount());
        while (cursorQuery.moveToNext()) {
            try {
                Thread.sleep(0L);
                f(cursorQuery, sVar);
            } catch (Exception e) {
                e.printStackTrace();
                w.c("ImageAndVideoLoader interrupt : " + e.getMessage());
                cursorQuery.close();
                throw new Exception("interrupt");
            }
        }
        cursorQuery.close();
        w.c("load from media store finish ,cost " + (System.currentTimeMillis() - jCurrentTimeMillis));
    }

    protected List<String> d() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("_display_name");
        linkedList.add("_data");
        int i = Build.VERSION.SDK_INT;
        linkedList.add(i >= 29 ? "datetaken" : "date_modified");
        linkedList.add(DiLinkBluetoothDeviceConstants._ID);
        linkedList.add("media_type");
        linkedList.add("mime_type");
        linkedList.add("duration");
        if (i < 29) {
            linkedList.add("latitude");
            linkedList.add("longitude");
            linkedList.add("description");
            if (i < 28) {
                linkedList.add("storage_id");
            }
        }
        linkedList.add("title");
        linkedList.add("date_added");
        return linkedList;
    }

    protected void e() {
        String str;
        int i = this.e;
        if (i == 0) {
            str = "(media_type=1 or media_type=3 or mime_type like 'video/%')";
        } else if (i == 1) {
            str = "media_type=1";
        } else if (i != 2) {
            return;
        } else {
            str = "(media_type=3 or mime_type like 'video/%')";
        }
        this.f = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void f(android.database.Cursor r20, b.a.k.s r21) {
        /*
            r19 = this;
            r0 = r20
            r1 = 4
            int r8 = r0.getInt(r1)
            if (r8 != 0) goto La
            return
        La:
            r1 = 1
            java.lang.String r4 = r0.getString(r1)
            java.io.File r1 = com.byd.util.x.j(r4)
            if (r1 != 0) goto L16
            return
        L16:
            r1 = 0
            java.lang.String r1 = r0.getString(r1)
            if (r1 != 0) goto L21
            java.lang.String r1 = com.byd.util.n.n(r4)
        L21:
            r3 = r1
            r1 = 3
            r5 = -1
            if (r8 != r1) goto L37
            r2 = 6
            long r9 = r0.getLong(r2)
            r11 = 0
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 != 0) goto L33
            goto L37
        L33:
            r13 = r19
            r14 = r9
            goto L3a
        L37:
            r13 = r19
            r14 = r5
        L3a:
            long r5 = r13.h(r4, r0)
            int r7 = r0.getInt(r1)
            r1 = 0
            int r2 = android.os.Build.VERSION.SDK_INT
            r9 = 28
            r10 = 0
            if (r2 >= r9) goto L5f
            r1 = 7
            double r1 = r0.getDouble(r1)
            r9 = 8
            double r9 = r0.getDouble(r9)
            r11 = 9
            java.lang.String r0 = r0.getString(r11)
            r11 = r9
            r9 = r1
            goto L65
        L5f:
            r0 = r1
            r17 = r10
            r11 = r17
            r9 = r11
        L65:
            r2 = r19
            r13 = r0
            r16 = r21
            r2.g(r3, r4, r5, r7, r8, r9, r11, r13, r14, r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.k.i.f(android.database.Cursor, b.a.k.s):void");
    }

    protected void g(String str, String str2, long j, int i, int i2, double d2, double d3, String str3, long j2, s sVar) {
        FileBean fileBean = new FileBean(str, str2, 1, i2 != 1 ? 2 : 1, j, d2, d3, false);
        if (str3 != null && !"".equals(str3)) {
            fileBean.setAddress(str3);
        }
        fileBean.setVideoDuration(j2);
        sVar.a(fileBean);
    }

    public void i(int i) {
        this.e = i;
    }
}
