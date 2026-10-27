package b.a.k;

import android.content.Context;
import com.byd.bean.FileBeanInGroup;
import com.byd.util.j;
import com.byd.util.w;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class n extends com.byd.taskmanager.b.a<List<FileBeanInGroup>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f574a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f575b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f576c;
    private d d;
    private int e;
    private r f;
    private q g;

    public n(Context context, String str, int i, q qVar) {
        this.f574a = context;
        this.f576c = str;
        this.e = i;
        this.g = qVar;
        this.name = "g_load_" + System.nanoTime();
        this.d = new d();
        if (str == null) {
            this.f = i == 1 ? new i(context, false, 1) : new i(context, false, 2);
        }
    }

    public int b() {
        return this.f575b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.taskmanager.b.a
    public List<FileBeanInGroup> doTask() throws InterruptedException {
        String str;
        this.g.c();
        this.f575b = 0;
        w.c("load data : " + this.e + " " + this.f576c);
        if (this.f576c == null) {
            int i = this.e;
            if (i == 1 || i == 2) {
                try {
                    this.f.c(this.g.f586b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                this.d.c(this.f574a, this.g.f586b);
                str = "after loadFromCamera";
                w.c(str);
            }
        } else {
            j.a aVarI = com.byd.util.j.e(this.f574a).i(this.f576c);
            w.c("check volume : " + this.f576c + " -> " + aVarI);
            if (aVarI != null) {
                h.e(this.f574a).b(aVarI, this.g.f586b);
            } else if (this.f576c.equals("/collect")) {
                com.byd.util.f.k(this.f574a).g(this.f574a, this.g.f586b);
            } else {
                com.byd.util.n.z(this.f574a, this.f576c, this.g.f586b);
                str = "after load from normal folder";
                w.c(str);
            }
        }
        this.f575b = this.g.e();
        w.c("datasNum:" + this.f575b);
        q qVar = this.g;
        return qVar.g(qVar.f585a);
    }
}
