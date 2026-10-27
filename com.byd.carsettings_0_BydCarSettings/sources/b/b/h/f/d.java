package b.b.h.f;

import android.text.TextUtils;
import b.b.h.f.e;
import com.byd.db.ex.DbException;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import java.util.List;
import net.sqlcipher.Cursor;

/* JADX INFO: compiled from: DbModelSelector.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String[] f1872a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f1873b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.h.f.h.c f1874c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e<?> f1875d;

    public d(e<?> eVar, String[] strArr) {
        this.f1875d = eVar;
        this.f1872a = strArr;
    }

    public List<b.b.h.f.i.c> a() throws DbException {
        b.b.h.f.i.d<?> dVarG = this.f1875d.g();
        ArrayList arrayList = null;
        if (!dVarG.m()) {
            return null;
        }
        Cursor cursorD = dVarG.e().d(toString());
        if (cursorD != null) {
            try {
                arrayList = new ArrayList();
                while (cursorD.moveToNext()) {
                    arrayList.add(a.a(cursorD));
                }
            } finally {
            }
        }
        return arrayList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        String[] strArr = this.f1872a;
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                sb.append(str);
                sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
            }
            sb.deleteCharAt(sb.length() - 1);
        } else if (TextUtils.isEmpty(this.f1873b)) {
            sb.append("*");
        } else {
            sb.append(this.f1873b);
        }
        sb.append(" FROM ");
        sb.append(this.f1875d.g().h());
        b.b.h.f.h.c cVarH = this.f1875d.h();
        if (cVarH != null && cVarH.e() > 0) {
            sb.append(" WHERE ");
            sb.append(cVarH.toString());
        }
        if (!TextUtils.isEmpty(this.f1873b)) {
            sb.append(" GROUP BY ");
            sb.append(this.f1873b);
            b.b.h.f.h.c cVar = this.f1874c;
            if (cVar != null && cVar.e() > 0) {
                sb.append(" HAVING ");
                sb.append(this.f1874c.toString());
            }
        }
        List<e.a> listF = this.f1875d.f();
        if (listF != null && listF.size() > 0) {
            for (int i = 0; i < listF.size(); i++) {
                sb.append(" ORDER BY ");
                sb.append(listF.get(i).toString());
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        if (this.f1875d.d() > 0) {
            sb.append(" LIMIT ");
            sb.append(this.f1875d.d());
            sb.append(" OFFSET ");
            sb.append(this.f1875d.e());
        }
        return sb.toString();
    }
}
