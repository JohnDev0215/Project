package b.a.k;

import android.content.Context;
import com.byd.bean.FileBean;
import com.byd.bean.FileBeanInGroup;
import com.byd.dilink.hardware.watermark.DiLinkFontUtilsConstants;
import com.byd.util.w;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes2.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f585a;
    private Context d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f587c = "Camera/Burstshoot";
    private FilenameFilter e = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    b f586b = new b();

    class a implements FilenameFilter {
        a() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.endsWith(".jpg") || str.endsWith(DiLinkFontUtilsConstants.FILE_PNG_SUFFIX);
        }
    }

    public class b implements s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private TreeSet<FileBeanInGroup> f589a = new TreeSet<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private FileBeanInGroup f590b;

        b() {
        }

        @Override // b.a.k.s
        public void a(FileBean fileBean) {
            FileBeanInGroup fileBeanInGroup = this.f590b;
            if (fileBeanInGroup != null) {
                FileBean fileBean2 = fileBeanInGroup.getFileBean();
                File file = new File(fileBean2.getPath());
                if (file.getParentFile().getParent().endsWith(q.this.f587c) && file.getParent().equals(new File(fileBean.getPath()).getParent())) {
                    if (fileBean2.getNum() <= 0) {
                        fileBean2.setNum(new File(fileBean.getPath()).getParentFile().listFiles(q.this.e).length);
                        fileBean2.setType(3);
                        fileBean2.setName(file.getParentFile().getName());
                    }
                    if (fileBean2.getLastModifiedTime() - file.lastModified() < 0) {
                        fileBean2.setPath(file.getPath());
                        fileBean2.setLastModifiedTime(file.lastModified());
                        return;
                    }
                    return;
                }
            }
            FileBeanInGroup fileBeanInGroup2 = new FileBeanInGroup(fileBean);
            this.f589a.add(fileBeanInGroup2);
            this.f590b = fileBeanInGroup2;
        }
    }

    public q(Context context) {
        this.d = context.getApplicationContext();
    }

    public void c() {
        this.f586b.f589a.clear();
        this.f586b.f590b = null;
    }

    public void d(List<String> list) {
        if (this.f586b.f589a.size() == 0) {
            return;
        }
        LinkedList linkedList = new LinkedList(list);
        Iterator it = this.f586b.f589a.iterator();
        while (it.hasNext()) {
            try {
                FileBeanInGroup fileBeanInGroup = (FileBeanInGroup) it.next();
                int size = linkedList.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        break;
                    }
                    if (fileBeanInGroup.getFileBean().getPath().equals(linkedList.get(i))) {
                        it.remove();
                        linkedList.remove(i);
                        break;
                    }
                    i++;
                }
            } catch (Exception unused) {
            }
            if (linkedList.isEmpty()) {
                return;
            }
        }
    }

    public int e() {
        return this.f586b.f589a.size();
    }

    public List<FileBeanInGroup> f() {
        return g(this.f585a);
    }

    public List<FileBeanInGroup> g(boolean z) {
        int size = this.f586b.f589a.size();
        FileBeanInGroup fileBeanInGroup = null;
        if (size == 0) {
            return null;
        }
        if (this.f585a != z) {
            this.f585a = z;
        }
        TreeSet treeSet = this.f586b.f589a;
        FileBean fileBean = ((FileBeanInGroup) (z ? treeSet.last() : treeSet.first())).getFileBean();
        FileBeanInGroup fileBeanInGroup2 = new FileBeanInGroup(fileBean.getDate());
        fileBeanInGroup2.setNewestBean(this.d, fileBean);
        LinkedList linkedList = new LinkedList();
        linkedList.add(fileBeanInGroup2);
        TreeSet treeSet2 = this.f586b.f589a;
        Iterator itDescendingIterator = z ? treeSet2.descendingIterator() : treeSet2.iterator();
        w.c("==start iterator==" + size + " asc:" + z);
        int i = 0;
        while (itDescendingIterator.hasNext()) {
            try {
                try {
                    Thread.sleep(0L);
                    FileBeanInGroup fileBeanInGroup3 = (FileBeanInGroup) itDescendingIterator.next();
                    if (fileBeanInGroup != null && fileBeanInGroup.getDate() != fileBeanInGroup3.getDate()) {
                        FileBeanInGroup fileBeanInGroup4 = new FileBeanInGroup(fileBeanInGroup3.getDate());
                        linkedList.add(fileBeanInGroup4);
                        fileBeanInGroup4.setNewestBean(this.d, fileBeanInGroup3.getFileBean());
                    }
                    linkedList.add(fileBeanInGroup3);
                    i++;
                    if (i >= size) {
                        break;
                    }
                    fileBeanInGroup = fileBeanInGroup3;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            } finally {
                w.c("iterator counts : " + i + " total list size : " + linkedList.size());
            }
        }
        return linkedList;
    }
}
