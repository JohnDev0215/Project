package b.a.k;

import android.content.Context;
import com.byd.bean.FileBean;
import com.byd.dilink.hardware.watermark.DiLinkFontUtilsConstants;
import com.byd.util.w;
import com.byd.util.x;
import com.byd.util.y;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public abstract class t<T> extends com.byd.taskmanager.b.a<List<T>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String[] f595a = {"年", "月", "日"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f596b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected u f597c;
    private t<T>.b d;
    private y<T> e;
    private String f;
    private String g;
    private int h;
    protected int i;
    private Pattern j;
    private Pattern k;
    private int l;
    private int m;
    private Map<String, FileBean> n;

    private class b implements s {

        class a implements FilenameFilter {
            a() {
            }

            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return str.endsWith(DiLinkFontUtilsConstants.FILE_PNG_SUFFIX) || str.endsWith(".jpg");
            }
        }

        private b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.a.k.s
        public void a(FileBean fileBean) {
            if (t.this.e == null) {
                t.this.e = new y();
            }
            if (t.this.h == 1 && fileBean.getPath().startsWith(t.this.g)) {
                String parent = new File(fileBean.getPath()).getParent();
                if (t.this.n.containsKey(parent)) {
                    return;
                }
                fileBean.setType(3);
                fileBean.setNum(new File(parent).listFiles(new a()).length);
                t.this.n.put(parent, fileBean);
            }
            t.this.e.a(t.this.i(fileBean));
        }
    }

    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f600a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f601b;

        c(String str) {
            a(str);
        }

        c a(String str) {
            this.f600a = str;
            this.f601b = Integer.parseInt(str);
            return this;
        }

        public String toString() {
            return this.f600a;
        }
    }

    public t(Context context, com.byd.taskmanager.b.b<List<T>> bVar) {
        super(bVar);
        this.n = new HashMap();
        this.f596b = context;
        this.j = Pattern.compile("^([0-9]{1,4}[-/_])+([0-9]{1,2})?$");
        this.k = Pattern.compile("[0-9]+$");
        this.f597c = new u(context);
        this.d = new b();
        int i = Calendar.getInstance().get(1);
        this.l = i;
        this.m = i % 100;
        this.g = com.byd.util.g.f(context);
    }

    private String h(String str) {
        StringBuilder sb = new StringBuilder();
        String[] strArr = f595a;
        if (str.contains(strArr[0]) || str.contains(strArr[1]) || str.contains(strArr[2])) {
            m(m(m(str, sb, strArr[0]), sb, strArr[1]), sb, strArr[2]);
        } else if (p(str)) {
            int i = 0;
            for (String str2 : str.split("[_/-]")) {
                if (str2 != null) {
                    String strTrim = str2.trim();
                    if (!"".equals(strTrim) && r(strTrim)) {
                        int i2 = Integer.parseInt(strTrim);
                        sb.append(i2 < 10 ? "0" + i2 : String.valueOf(i2));
                        String[] strArr2 = f595a;
                        sb.append(strArr2[i]);
                        i++;
                        if (i == strArr2.length) {
                            break;
                        }
                    }
                }
            }
        } else if (r(str)) {
            l(str, sb);
        }
        w.c("date ==> " + ((Object) sb));
        int length = sb.length();
        if (length <= 0 || length >= 12) {
            return null;
        }
        String string = sb.toString();
        String[] strArr3 = f595a;
        return string.replaceAll(strArr3[0], "-").replaceAll(strArr3[1], "-").replaceAll(strArr3[2], "");
    }

    /* JADX WARN: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0204  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void l(java.lang.String r12, java.lang.StringBuilder r13) {
        /*
            Method dump skipped, instruction units count: 593
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.k.t.l(java.lang.String, java.lang.StringBuilder):void");
    }

    private String m(String str, StringBuilder sb, String str2) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (str.contains(str2)) {
            int iIndexOf = str.indexOf(str2);
            String strSubstring = str.substring(0, iIndexOf);
            if (!"".equals(strSubstring) && r(strSubstring)) {
                int i = Integer.parseInt(strSubstring);
                if (i < 10) {
                    sb.append("0");
                }
                sb.append(i);
                sb.append(str2);
            }
            if (iIndexOf < str.length()) {
                str = str.substring(iIndexOf + 1);
                if (r(str)) {
                    if (Integer.parseInt(str) < 10) {
                        sb.append("0");
                    }
                    sb.append(str);
                    return null;
                }
            }
        }
        return str;
    }

    private boolean p(String str) {
        return this.j.matcher(str).matches();
    }

    private void q(c cVar) {
        String str;
        int i = cVar.f601b;
        if (i <= 0) {
            str = null;
        } else {
            if (i >= 10) {
                return;
            }
            str = "0" + cVar.f601b;
        }
        cVar.f600a = str;
    }

    private boolean r(String str) {
        return this.k.matcher(str).matches();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.taskmanager.b.a
    public List<T> doTask() {
        this.i = 0;
        LinkedList<T> linkedList = new LinkedList<>();
        this.n.clear();
        try {
            k(linkedList);
            if (this.f597c.n() != null) {
                this.e = null;
                this.f597c.c(this.d);
                if (this.e != null) {
                    w.c("name search result :" + this.e.b());
                    T tJ = j(this.f);
                    if (tJ != null) {
                        linkedList.add(tJ);
                    }
                    this.e.e();
                    while (this.e.c()) {
                        T tD = this.e.d();
                        if (tD != null) {
                            linkedList.add(tD);
                            this.i++;
                        }
                    }
                    this.e = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            w.c("stop search task");
        }
        if (this.i == 0) {
            return null;
        }
        return linkedList;
    }

    protected abstract void g(String str);

    protected abstract T i(FileBean fileBean);

    protected abstract T j(String str);

    protected void k(LinkedList<T> linkedList) {
    }

    public int n() {
        return this.i;
    }

    public void o(String str, int i, int i2) {
        w.c("SearchTask initSearch keyWord:" + str + " type:" + i2);
        this.f = str;
        this.h = i;
        this.f597c.r(i);
        this.f597c.i(i2);
        g(h(str));
        if (x.o(str)) {
            this.f597c.q(null);
        } else {
            this.f597c.q(str);
        }
    }
}
