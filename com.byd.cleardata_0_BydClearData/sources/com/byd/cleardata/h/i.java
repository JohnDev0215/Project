package com.byd.cleardata.h;

import com.byd.cleardata.FileApplication;
import com.byd.cleardata.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class i extends h {
    private static final com.byd.cleardata.d.f[] e = {com.byd.cleardata.d.f.DOCUMENT, com.byd.cleardata.d.f.AUDIO, com.byd.cleardata.d.f.VIDEO, com.byd.cleardata.d.f.IMAGE, com.byd.cleardata.d.f.COMPRESS, com.byd.cleardata.d.f.APK};
    private com.byd.cleardata.d.f f;

    public i(int i, com.byd.cleardata.d.f fVar) {
        this.f = fVar;
        this.f2679d = com.byd.cleardata.a.f().k(0);
    }

    private List<com.byd.cleardata.d.d> v() {
        ArrayList arrayList = new ArrayList();
        String[] stringArray = FileApplication.a().getResources().getStringArray(R.array.downloads);
        for (int i = 0; i < stringArray.length; i++) {
            com.byd.cleardata.d.d dVarY = com.byd.cleardata.d.d.y(stringArray[i]);
            dVarY.V("/" + stringArray[i]);
            dVarY.X(com.byd.cleardata.d.f.DOWNLOAD_DIRECTORY);
            dVarY.Q(e[i]);
            arrayList.add(dVarY);
        }
        return arrayList;
    }

    @Override // com.byd.cleardata.h.h, com.byd.cleardata.h.d
    public void m(c.a.g<? super m> gVar) {
        m mVar;
        l lVarT = t();
        if (lVarT != null) {
            List<com.byd.cleardata.d.d> listS = s(lVarT);
            if (o()) {
                mVar = new m(3, (List<com.byd.cleardata.d.d>) null);
            } else {
                if (listS != null) {
                    if (this.f == com.byd.cleardata.d.f.OTHER) {
                        List<com.byd.cleardata.d.d> listV = v();
                        listV.addAll(listS);
                        listS = listV;
                    }
                    gVar.c(new m(1, listS));
                    return;
                }
                mVar = new m(4, (List<com.byd.cleardata.d.d>) null);
            }
        } else {
            mVar = new m(4, (List<com.byd.cleardata.d.d>) null);
        }
        gVar.c(mVar);
    }

    @Override // com.byd.cleardata.h.h
    protected l t() {
        return l.g(this.f2679d, this.f);
    }
}
