package com.byd.cleardata.h;

import android.database.Cursor;

/* JADX INFO: loaded from: classes.dex */
public class f extends h {
    private String e;

    public f(String str) {
        this.e = str;
    }

    @Override // com.byd.cleardata.h.h, com.byd.cleardata.h.d
    public void m(c.a.g<? super m> gVar) {
        v(t());
    }

    @Override // com.byd.cleardata.h.h
    protected l t() {
        return l.h(this.e);
    }

    protected void v(l lVar) {
        try {
            Cursor cursorR = r(lVar.p(), lVar.m(), lVar.n(), lVar.k());
            if (cursorR != null) {
                boolean z = false;
                while (cursorR.moveToNext()) {
                    try {
                        if (o()) {
                            this.f2676a.c(new m(3, (com.byd.cleardata.d.d) null));
                            cursorR.close();
                            return;
                        }
                        String strQ = q(cursorR, "_data");
                        if (com.byd.cleardata.i.e.i(strQ)) {
                            com.byd.cleardata.d.d dVarZ = com.byd.cleardata.d.d.z(strQ);
                            dVarZ.W(com.byd.cleardata.a.f().u(dVarZ.E()));
                            this.f2676a.c(new m(2, dVarZ));
                            z = true;
                        }
                    } finally {
                    }
                }
                if (z) {
                    com.byd.cleardata.i.g.a(this.e);
                    com.byd.cleardata.i.g.g();
                }
                this.f2676a.c(new m(1, (com.byd.cleardata.d.d) null));
            }
            if (cursorR != null) {
                cursorR.close();
            }
        } catch (Exception e) {
            com.byd.cleardata.i.c.d("cursor error", e);
            this.f2676a.c(new m(4, (com.byd.cleardata.d.d) null));
        }
    }
}
