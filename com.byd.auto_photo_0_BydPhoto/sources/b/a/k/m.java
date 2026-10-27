package b.a.k;

import android.content.Context;
import com.byd.bean.FileBean;
import com.byd.util.w;
import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes2.dex */
public class m extends com.byd.taskmanager.b.a<List<FileBean>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f569a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f570b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b f571c;
    private Context d;
    private String e;
    private d f;
    private int g;
    private boolean h;

    class a implements FilenameFilter {
        a() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            String strA = com.byd.util.m.a(str.toLowerCase());
            return com.byd.util.m.b(strA) || com.byd.util.m.d(strA) || (m.this.h && file.isDirectory());
        }
    }

    static class b implements s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private TreeSet<FileBean> f573a = new TreeSet<>();

        b() {
        }

        @Override // b.a.k.s
        public void a(FileBean fileBean) {
            synchronized (this) {
                this.f573a.add(fileBean);
            }
        }

        public void c() {
            this.f573a.clear();
        }

        public void d() {
            this.f573a.clear();
        }
    }

    public m(Context context, String str, boolean z) {
        this.d = context;
        this.f569a = str;
        this.h = z;
        this.name = "load_img_show";
        this.f571c = new b();
        this.e = com.byd.util.g.g(context);
        this.f = new d();
    }

    public int c() {
        return this.f570b;
    }

    protected void d(int i) {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.taskmanager.b.a
    public List<FileBean> doTask() throws InterruptedException {
        String[] list;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.f570b = -1;
        this.f571c.c();
        String parent = new File(this.f569a).getParent();
        w.c("LoadImageShowInFolderTask load path : " + parent);
        if (parent != null && (list = new File(parent).list(new a())) != null) {
            d(list.length);
        }
        if (this.f569a.startsWith(this.e)) {
            this.f.e(this.g);
            this.f.d(true);
            this.f.c(this.d, this.f571c);
        } else {
            com.byd.util.n.y(this.d, parent, this.g, this.f571c);
        }
        int size = this.f571c.f573a.size();
        w.c("LoadImageShowInFolderTask load size : " + size);
        LinkedList linkedList = null;
        if (size > 0) {
            linkedList = new LinkedList();
            boolean zStartsWith = this.f569a.startsWith(com.byd.util.g.f(this.d));
            int i = 0;
            for (FileBean fileBean : this.f571c.f573a) {
                linkedList.add(fileBean);
                String path = fileBean.getPath();
                if (this.f570b == -1 && path.equals(this.f569a)) {
                    this.f570b = i;
                }
                if (zStartsWith && this.f570b == -1 && new File(path).getParent().equals(parent)) {
                    this.f570b = i;
                }
                i++;
            }
        }
        if (this.f570b == -1) {
            this.f570b = 0;
            if (linkedList == null) {
                linkedList = new LinkedList();
            }
            File file = new File(this.f569a);
            if (file.exists() && file.length() > 0) {
                linkedList.add(0, new FileBean(file.getName(), file.getPath(), 2, 1, file.lastModified()));
            }
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        if (jCurrentTimeMillis2 - jCurrentTimeMillis < 500) {
            Thread.sleep((500 - jCurrentTimeMillis2) + jCurrentTimeMillis);
        }
        return linkedList;
    }

    public void e(int i) {
        this.g = i;
    }

    @Override // com.byd.taskmanager.b.a
    public void release() {
        super.release();
        this.f571c.d();
        this.f571c = null;
        this.f569a = null;
    }
}
