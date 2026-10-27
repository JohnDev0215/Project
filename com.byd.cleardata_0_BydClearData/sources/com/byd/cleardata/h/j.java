package com.byd.cleardata.h;

import android.text.TextUtils;
import com.byd.cleardata.i.o;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class j extends d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2680c;

    public j(String str) {
        this.f2680c = str;
    }

    private List<com.byd.cleardata.d.d> p() {
        File[] fileArrListFiles;
        com.byd.cleardata.i.c.b("queryFiles path = " + this.f2680c);
        ArrayList arrayList = new ArrayList();
        try {
            if (!TextUtils.isEmpty(this.f2680c) && (fileArrListFiles = new File(this.f2680c).listFiles()) != null && fileArrListFiles.length > 0) {
                for (File file : fileArrListFiles) {
                    if (o()) {
                        break;
                    }
                    com.byd.cleardata.d.d dVar = new com.byd.cleardata.d.d(file);
                    dVar.I();
                    arrayList.add(dVar);
                }
                o.e(arrayList);
            }
        } catch (Throwable th) {
            com.byd.cleardata.i.c.d("queryFiles error", th);
        }
        return arrayList;
    }

    @Override // com.byd.cleardata.h.d
    public void m(c.a.g<? super m> gVar) {
        List<com.byd.cleardata.d.d> listP;
        try {
            listP = p();
            try {
                com.byd.cleardata.i.c.b("queryFiles size = " + listP.size());
                gVar.c(o() ? new m(3, listP) : new m(1, listP));
            } catch (Throwable th) {
                th = th;
                com.byd.cleardata.i.c.d("queryFiles error", th);
                gVar.c(new m(4, listP));
            }
        } catch (Throwable th2) {
            th = th2;
            listP = null;
        }
    }
}
