package com.byd.filemanager.task;

import android.database.Cursor;
import android.net.Uri;
import com.byd.filemanager.FileApplication;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.bean.FileType;
import com.byd.filemanager.e;
import com.byd.filemanager.j.c;
import com.byd.filemanager.k.b;
import com.byd.filemanager.k.d;
import com.byd.filemanager.k.h;
import com.byd.filemanager.k.l;
import d.a.i;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LoadContentProviderTask extends FileOperateTask {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3667e;
    protected String f;

    protected LoadContentProviderTask() {
    }

    private FileType w(String str) {
        int i = this.f3667e;
        return i != 2 ? i != 3 ? i != 4 ? h.e(str) : FileType.IMAGE : FileType.VIDEO : FileType.AUDIO;
    }

    @Override // com.byd.filemanager.task.FileOperateTask
    public void n(i<? super Response> iVar) {
        Request requestV = v();
        b.b("request = " + requestV);
        if (requestV == null) {
            iVar.onNext(new Response(4, (List<FileItem>) null));
            return;
        }
        List<FileItem> listU = u(requestV);
        if (p()) {
            iVar.onNext(new Response(3, listU));
        } else if (listU != null) {
            iVar.onNext(new Response(1, listU));
        } else {
            iVar.onNext(new Response(4, listU));
        }
    }

    protected long r(Cursor cursor, String str) {
        try {
            return cursor.getLong(cursor.getColumnIndexOrThrow(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return 0L;
        }
    }

    protected String s(Cursor cursor, String str) {
        try {
            return cursor.getString(cursor.getColumnIndexOrThrow(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    protected Cursor t(Uri uri, String str, String[] strArr, String str2) {
        try {
            return FileApplication.a().getContentResolver().query(uri, c.f3598a, str, strArr, str2);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    protected List<FileItem> u(Request request) {
        Cursor cursorT;
        try {
            cursorT = t(request.p(), request.m(), request.n(), request.k());
        } catch (Exception e2) {
            b.d("cursor error", e2);
        }
        if (cursorT == null) {
            if (cursorT != null) {
                cursorT.close();
            }
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            while (cursorT.moveToNext()) {
                if (p()) {
                    cursorT.close();
                    return null;
                }
                String strS = s(cursorT, "_data");
                String strS2 = s(cursorT, "mime_type");
                long jR = r(cursorT, "date_modified");
                long jR2 = r(cursorT, "_size");
                if (d.h(strS) && !d.G(strS)) {
                    FileItem fileItemZ = FileItem.z(strS);
                    fileItemZ.X(w(strS2));
                    fileItemZ.S(jR);
                    fileItemZ.T(jR2);
                    arrayList.add(fileItemZ);
                }
            }
            if (this.f3667e != 0 && arrayList.size() > 0) {
                l.e(arrayList);
            }
            b.b("size = " + arrayList.size() + ",mMenuIndex:" + this.f3667e);
            cursorT.close();
            return arrayList;
        } finally {
        }
    }

    protected Request v() {
        int i = this.f3667e;
        if (i == 0) {
            return Request.l(this.f);
        }
        if (i == 2) {
            return Request.f(this.f);
        }
        if (i == 3) {
            return Request.q(this.f);
        }
        if (i != 4) {
            return null;
        }
        return Request.i(this.f);
    }

    public LoadContentProviderTask(int i, int i2) {
        this.f3667e = i;
        this.f = e.f().l(i2);
        b.b("mRootPath " + this.f);
    }
}
