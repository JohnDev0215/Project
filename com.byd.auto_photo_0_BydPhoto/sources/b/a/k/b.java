package b.a.k;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import com.byd.bean.AlbumItem;
import com.byd.dilink.provider.DiLinkBluetoothDeviceConstants;
import com.byd.util.w;
import com.byd.util.x;
import java.io.File;
import java.io.FileFilter;

/* JADX INFO: loaded from: classes2.dex */
public class b extends com.byd.taskmanager.b.a<Integer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AlbumItem f526a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f527b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f528c;
    private String d;
    private String[] e;

    class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            if (!file.isFile()) {
                return false;
            }
            String name = file.getName();
            if (name.startsWith(".")) {
                return false;
            }
            String strA = com.byd.util.m.a(name);
            return com.byd.util.m.b(strA) || com.byd.util.m.d(strA);
        }
    }

    public b(Context context, AlbumItem albumItem, int i, com.byd.taskmanager.b.b<Integer> bVar) {
        super((com.byd.taskmanager.b.b) bVar, true);
        this.d = "Camera/Burstshoot";
        this.f527b = context.getApplicationContext();
        this.f526a = albumItem;
        this.f528c = i;
        this.name = "AlbumChildCountTask" + i;
        if (Build.VERSION.SDK_INT >= 29) {
            this.e = new String[]{"_data", "media_type", "datetaken", "date_added"};
        } else {
            this.e = new String[]{"_data", "media_type", "date_modified", "date_added"};
        }
    }

    private void b(AlbumItem albumItem) {
        int i;
        boolean z;
        String path = null;
        albumItem.setCoverPath(null);
        String path2 = albumItem.getUri().getPath();
        File file = new File(path2);
        ContentResolver contentResolver = this.f527b.getContentResolver();
        Uri uri = i.d;
        Cursor cursorQuery = contentResolver.query(uri, new String[]{DiLinkBluetoothDeviceConstants._ID}, "_data=?", new String[]{path2}, null);
        if (cursorQuery != null) {
            if (cursorQuery.moveToFirst()) {
                i = cursorQuery.getInt(0);
                z = true;
            } else {
                i = 0;
                z = false;
            }
            cursorQuery.close();
        } else {
            i = 0;
            z = false;
        }
        if (!z) {
            c(file);
            return;
        }
        if (!x.k(this.f527b, file, i)) {
            c(file);
            return;
        }
        w.c("calc by content : " + path2 + " | parentId : " + i);
        Cursor cursorQuery2 = this.f527b.getContentResolver().query(uri, this.e, "parent=" + i + " and (media_type=1 or media_type=3 or mime_type like 'video/%')", null, "date_modified desc");
        long j = 0L;
        int i2 = 0;
        while (cursorQuery2.moveToNext()) {
            if (cursorQuery2.getInt(1) != 0) {
                File file2 = new File(cursorQuery2.getString(0));
                if (file2.exists() && file2.length() != 0 && !file2.getName().startsWith(".")) {
                    i2++;
                    long jE = e(cursorQuery2);
                    if (j < jE || path == null) {
                        path = file2.getPath();
                        j = jE;
                    }
                }
            }
        }
        cursorQuery2.close();
        albumItem.setCoverPath(path);
        albumItem.setNum(i2);
        w.c("folder child count by content : " + path2 + " total : " + i2 + " " + albumItem.getCoverPath());
    }

    private void c(File file) {
        File[] fileArrListFiles = file.listFiles(new a());
        int i = 0;
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            int length = fileArrListFiles.length;
            File file2 = null;
            long jLastModified = 0;
            while (i < fileArrListFiles.length) {
                if (!fileArrListFiles[i].exists() || fileArrListFiles[i].length() == 0) {
                    length--;
                } else if (file2 == null) {
                    file2 = fileArrListFiles[i];
                    jLastModified = file2.lastModified();
                } else {
                    long jLastModified2 = fileArrListFiles[i].lastModified();
                    if (jLastModified2 - jLastModified > 0) {
                        file2 = fileArrListFiles[i];
                        jLastModified = jLastModified2;
                    }
                }
                i++;
            }
            if (file2 != null && (this.f526a.getCoverPath() == null || !new File(this.f526a.getCoverPath()).exists())) {
                this.f526a.setCoverPath(file2.getPath());
            }
            i = length;
        }
        w.c("folder : " + file.getName() + " total : " + i + " " + this.f526a.getCoverPath());
        this.f526a.setNum(i);
    }

    private long e(Cursor cursor) {
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
        return j == 0 ? cursor.getLong(3) * 1000 : j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0133  */
    @Override // com.byd.taskmanager.b.a
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Integer doTask() {
        /*
            Method dump skipped, instruction units count: 375
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.k.b.doTask():java.lang.Integer");
    }
}
