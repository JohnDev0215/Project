package p;

import android.database.Cursor;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public final class ck0 extends bk0 {
    public final i52 q;
    public final f90 r;

    public ck0(i52 i52Var) {
        this.q = i52Var;
        this.r = new f90(this, i52Var, 7);
    }

    @Override // p.bk0
    public final int B() {
        m52 m52VarI = m52.i(0, "SELECT 1 FROM EventSequenceNumbers LIMIT 1");
        i52 i52Var = this.q;
        i52Var.b();
        Cursor cursorM = i52Var.m(m52VarI, null);
        try {
            return cursorM.moveToFirst() ? cursorM.getInt(0) : 0;
        } finally {
            cursorM.close();
            m52VarI.q();
        }
    }

    @Override // p.bk0
    public final void E(ak0 ak0Var) {
        i52 i52Var = this.q;
        i52Var.b();
        i52Var.c();
        try {
            this.r.e(ak0Var);
            i52Var.o();
        } finally {
            i52Var.k();
        }
    }

    @Override // p.bk0
    public final void m(LinkedList linkedList) {
        i52 i52Var = this.q;
        i52Var.c();
        try {
            super.m(linkedList);
            i52Var.o();
        } finally {
            i52Var.k();
        }
    }

    @Override // p.bk0
    public final void n(AbstractList abstractList) {
        i52 i52Var = this.q;
        i52Var.b();
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM EventSequenceNumbers WHERE sequenceId IN (");
        ux2.b(abstractList.size(), sb);
        sb.append(")");
        th2 th2VarD = i52Var.d(sb.toString());
        Iterator it = abstractList.iterator();
        int i = 1;
        while (it.hasNext()) {
            byte[] bArr = (byte[]) it.next();
            if (bArr == null) {
                th2VarD.K(i);
            } else {
                th2VarD.B0(bArr, i);
            }
            i++;
        }
        i52Var.c();
        try {
            th2VarD.F();
            i52Var.o();
        } finally {
            i52Var.k();
        }
    }

    @Override // p.bk0
    public final long z(String str, byte[] bArr) {
        m52 m52VarI = m52.i(2, "SELECT sequenceNumberNext FROM EventSequenceNumbers WHERE eventName = ? AND sequenceId = ?");
        if (str == null) {
            m52VarI.K(1);
        } else {
            m52VarI.x(1, str);
        }
        if (bArr == null) {
            m52VarI.K(2);
        } else {
            m52VarI.B0(bArr, 2);
        }
        i52 i52Var = this.q;
        i52Var.b();
        Cursor cursorM = i52Var.m(m52VarI, null);
        try {
            return cursorM.moveToFirst() ? cursorM.getLong(0) : 0L;
        } finally {
            cursorM.close();
            m52VarI.q();
        }
    }
}
