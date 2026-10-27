package b.a.k;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.byd.bean.FileBean;
import com.byd.util.w;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class u extends i {
    private String h;
    private SimpleDateFormat i;
    private String j;
    private int k;
    private d l;

    class a implements s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ s f602a;

        a(s sVar) {
            this.f602a = sVar;
        }

        @Override // b.a.k.s
        public void a(FileBean fileBean) {
            synchronized (this) {
                int i = u.this.e;
                if (i != 0) {
                    if (i == 1 && !com.byd.util.m.c(fileBean.getName())) {
                        return;
                    }
                    if (u.this.e == 2 && !com.byd.util.m.e(fileBean.getName())) {
                        return;
                    }
                }
                if (!"".equals(u.this.j) && (u.this.j == null || !fileBean.getName().toLowerCase().contains(u.this.j))) {
                    if (u.this.h != null) {
                        if (!u.this.i.format(new Date(fileBean.getLastModifiedTime())).contains(u.this.h)) {
                            return;
                        } else {
                            this.f602a.a(fileBean);
                        }
                    }
                    return;
                }
                this.f602a.a(fileBean);
            }
        }
    }

    public u(Context context) {
        super(context, false, 0);
        this.i = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        this.l = new d();
    }

    private String m(String str, String str2, String str3, String str4, String str5) {
        if (Build.VERSION.SDK_INT >= 30) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        int i = 0;
        if (str3 != null) {
            sb.append(str);
            sb.append(" like '%");
            sb.append(str3);
            sb.append("%'");
            i = 1;
        }
        if (str5 != null) {
            if (i != 0) {
                sb.append(" or ");
            }
            sb.append("(select date(" + str4 + ", 'unixepoch', 'localtime')) like '%");
            sb.append(str5);
            sb.append("%'");
            i++;
        }
        if (str2 != null) {
            if (i != 0) {
                sb.append(" or ");
            }
            sb.append(str2);
            sb.append(" like '%");
            sb.append(str3);
            sb.append("%'");
        }
        sb.append(")");
        return sb.toString();
    }

    private void o(s sVar) {
        this.l.c(this.f592a, new a(sVar));
    }

    @Override // b.a.k.i, b.a.k.r
    protected String[] a() {
        return super.a();
    }

    @Override // b.a.k.i, b.a.k.r
    protected Uri b() {
        return super.b();
    }

    @Override // b.a.k.i, b.a.k.r
    public void c(s sVar) {
        w.c("===startLoad name:" + this.j + " date:" + this.h);
        if (TextUtils.isEmpty(this.j) && TextUtils.isEmpty(this.h)) {
            return;
        }
        int i = this.k;
        if (i == 0) {
            o(sVar);
        } else {
            if (i != 1) {
                return;
            }
            super.c(sVar);
        }
    }

    @Override // b.a.k.i
    protected void f(Cursor cursor, s sVar) {
        if (Build.VERSION.SDK_INT < 30) {
            super.f(cursor, sVar);
            return;
        }
        String string = cursor.getString(0);
        String str = this.j;
        if (str != null && string.contains(str)) {
            super.f(cursor, sVar);
            return;
        }
        String str2 = this.i.format(new Date(cursor.getLong(2) * 1000));
        String str3 = this.h;
        if (str3 == null || !str2.contains(str3)) {
            return;
        }
        super.f(cursor, sVar);
    }

    public String n() {
        return this.j;
    }

    public void p(String str) {
        this.h = str;
        w.c("==setDateKeyWord==>" + str);
    }

    public void q(String str) {
        w.c("setNameKeyWord=>" + str + " searchSource:" + this.k);
        if (str == null) {
            w.c("name key word is null -> search end");
            this.j = null;
            return;
        }
        this.j = str.toLowerCase();
        if (this.k == 1) {
            strM = "".equals(str) ? null : m("_display_name", null, str, "date_modified", this.h);
            e();
        }
        if (strM != null) {
            this.f += " and " + strM;
        }
        w.c("search selection : " + this.f);
    }

    public void r(int i) {
        this.k = i;
    }
}
