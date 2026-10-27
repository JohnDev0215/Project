package com.byd.cleardata.h;

import android.annotation.SuppressLint;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c extends d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected long f2672c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected long f2673d = 0;
    private List<com.byd.cleardata.d.d> e;

    class a implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.a.g f2674a;

        a(c.a.g gVar) {
            this.f2674a = gVar;
        }

        @Override // com.byd.cleardata.h.g
        public void a(long j) {
            c cVar = c.this;
            long j2 = cVar.f2672c + j;
            cVar.f2672c = j2;
            this.f2674a.c(new m(j2, cVar.f2673d));
        }
    }

    public c(List<com.byd.cleardata.d.d> list) {
        this.e = list != null ? new ArrayList(list) : null;
    }

    private void p(File file) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    p(file2);
                } else {
                    file2.delete();
                }
            }
        }
        file.delete();
    }

    @SuppressLint({"NewApi"})
    private boolean q(File file, g gVar) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            p(file);
        } else if (!file.delete()) {
            com.byd.cleardata.i.c.b("delete file force");
            com.byd.cleardata.i.m.a("rm -rf " + file.getAbsolutePath());
        }
        gVar.a(1L);
        return true;
    }

    @SuppressLint({"NewApi"})
    private long r() {
        long jX = 0;
        for (com.byd.cleardata.d.d dVar : this.e) {
            if (o()) {
                return 0L;
            }
            jX += dVar.x();
        }
        return jX;
    }

    @Override // com.byd.cleardata.h.d
    public void m(c.a.g<? super m> gVar) {
        m mVar;
        com.byd.cleardata.i.c.b("delete start");
        List<com.byd.cleardata.d.d> list = this.e;
        boolean z = false;
        ArrayList arrayList = null;
        if (list != null && !list.isEmpty()) {
            this.f2673d = r();
            com.byd.cleardata.i.c.b("mTotal = " + this.f2673d);
            if (o()) {
                com.byd.cleardata.i.c.b("compress cancel");
                gVar.c(new m(3, (List<com.byd.cleardata.d.d>) null));
            }
            if (this.f2673d > 0) {
                arrayList = new ArrayList();
                Iterator<com.byd.cleardata.d.d> it = this.e.iterator();
                boolean z2 = false;
                while (true) {
                    if (!it.hasNext()) {
                        z = z2;
                        break;
                    }
                    com.byd.cleardata.d.d next = it.next();
                    if (!o()) {
                        boolean zQ = q(next.w(), new a(gVar));
                        if (!zQ) {
                            z = zQ;
                            break;
                        } else {
                            arrayList.add(next);
                            z2 = zQ;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        if (z) {
            com.byd.cleardata.i.m.b();
            mVar = new m(1, arrayList);
        } else if (o()) {
            com.byd.cleardata.i.c.b("delete cancel");
            mVar = new m(3, arrayList);
        } else {
            com.byd.cleardata.i.c.b("delete error or source is empty");
            mVar = new m(4, arrayList);
        }
        gVar.c(mVar);
        com.byd.cleardata.i.c.b("delete end");
    }
}
