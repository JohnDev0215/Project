package com.byd.cleardata.h;

import android.database.Cursor;
import android.net.Uri;
import com.byd.cleardata.FileApplication;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class h extends d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2678c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected String f2679d;

    protected h() {
    }

    public h(int i, int i2) {
        this.f2678c = i;
        this.f2679d = com.byd.cleardata.a.f().k(i2);
        com.byd.cleardata.i.c.b("mRootPath " + this.f2679d);
    }

    private com.byd.cleardata.d.f u(String str) {
        int i = this.f2678c;
        return i != 3 ? i != 4 ? i != 5 ? com.byd.cleardata.i.i.e(str) : com.byd.cleardata.d.f.IMAGE : com.byd.cleardata.d.f.VIDEO : com.byd.cleardata.d.f.AUDIO;
    }

    @Override // com.byd.cleardata.h.d
    public void m(c.a.g<? super m> gVar) {
        m mVar;
        l lVarT = t();
        com.byd.cleardata.i.c.b("request = " + lVarT);
        if (lVarT == null) {
            gVar.c(new m(4, (List<com.byd.cleardata.d.d>) null));
            return;
        }
        List<com.byd.cleardata.d.d> listS = s(lVarT);
        if (o()) {
            mVar = new m(3, listS);
        } else {
            if (listS == null) {
                gVar.c(new m(4, listS));
                return;
            }
            mVar = new m(1, listS);
        }
        gVar.c(mVar);
    }

    protected long p(Cursor cursor, String str) {
        try {
            return cursor.getLong(cursor.getColumnIndexOrThrow(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return 0L;
        }
    }

    protected String q(Cursor cursor, String str) {
        try {
            return cursor.getString(cursor.getColumnIndexOrThrow(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    protected Cursor r(Uri uri, String str, String[] strArr, String str2) {
        try {
            return FileApplication.a().getContentResolver().query(uri, com.byd.cleardata.g.c.f2642a, str, strArr, str2);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    protected List<com.byd.cleardata.d.d> s(l lVar) {
        Cursor cursorR;
        try {
            cursorR = r(lVar.p(), lVar.m(), lVar.n(), lVar.k());
        } catch (Exception e) {
            com.byd.cleardata.i.c.d("cursor error", e);
        }
        if (cursorR == null) {
            if (cursorR != null) {
                cursorR.close();
            }
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            while (cursorR.moveToNext()) {
                if (o()) {
                    cursorR.close();
                    return null;
                }
                String strQ = q(cursorR, "_data");
                String strQ2 = q(cursorR, "mime_type");
                long jP = p(cursorR, "date_modified");
                long jP2 = p(cursorR, "_size");
                com.byd.cleardata.i.c.f("path = %s, mineType = %s, date = %s, size = %s", strQ, strQ2, Long.valueOf(jP), Long.valueOf(jP2));
                if (com.byd.cleardata.i.e.i(strQ) && !com.byd.cleardata.i.e.G(strQ)) {
                    com.byd.cleardata.d.d dVarZ = com.byd.cleardata.d.d.z(strQ);
                    dVarZ.X(u(strQ2));
                    dVarZ.S(jP);
                    dVarZ.T(jP2);
                    arrayList.add(dVarZ);
                }
            }
            com.byd.cleardata.i.c.b("size = " + arrayList.size());
            cursorR.close();
            return arrayList;
        } finally {
        }
    }

    protected l t() {
        int i = this.f2678c;
        if (i == 1) {
            return l.l(this.f2679d);
        }
        if (i == 3) {
            return l.f(this.f2679d);
        }
        if (i == 4) {
            return l.q(this.f2679d);
        }
        if (i != 5) {
            return null;
        }
        return l.i(this.f2679d);
    }
}
