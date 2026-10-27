package b.a.k;

import android.content.Context;
import com.byd.bean.FileBean;
import com.byd.util.j;
import com.byd.util.w;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class o extends com.byd.taskmanager.b.a<List<FileBean>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f577a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private r f578b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f579c;
    private a d;
    private int e;
    private String f;

    private class a implements s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        LinkedList<FileBean> f580a = new LinkedList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Map<String, FileBean> f581b = new HashMap();

        a() {
        }

        @Override // b.a.k.s
        public void a(FileBean fileBean) {
            if ((o.this.f579c != null && o.this.f579c.equals("/collect")) || !fileBean.getPath().startsWith(o.this.f)) {
                this.f580a.add(fileBean);
                return;
            }
            String parent = new File(fileBean.getPath()).getParent();
            if (this.f581b.containsKey(parent)) {
                FileBean fileBean2 = this.f581b.get(parent);
                fileBean2.setNum(fileBean2.getNum() + 1);
            } else {
                fileBean.setType(3);
                fileBean.setNum(1);
                this.f581b.put(parent, fileBean);
                this.f580a.add(fileBean);
            }
        }

        void b() {
            this.f580a.clear();
            this.f581b.clear();
        }
    }

    public o(Context context, String str, int i) {
        this.f577a = context;
        this.f579c = str;
        this.e = i;
        this.name = "load_" + System.nanoTime();
        this.f = com.byd.util.g.f(context);
        if (str == null) {
            this.f578b = i == 1 ? new i(context, false, 1) : new i(context, false, 2);
        }
        this.d = new a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.taskmanager.b.a
    public List<FileBean> doTask() throws InterruptedException {
        this.d.b();
        if (this.f579c == null) {
            try {
                this.f578b.c(this.d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            j.a aVarI = com.byd.util.j.e(this.f577a).i(this.f579c);
            w.c("check volume : " + this.f579c + " -> " + aVarI);
            if (aVarI != null) {
                h.e(this.f577a).b(aVarI, this.d);
            } else if (this.f579c.equals("/collect")) {
                com.byd.util.f.k(this.f577a).g(this.f577a, this.d);
            } else {
                com.byd.util.n.z(this.f577a, this.f579c, this.d);
            }
        }
        return this.d.f580a;
    }
}
