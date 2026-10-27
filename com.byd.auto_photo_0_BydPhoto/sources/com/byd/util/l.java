package com.byd.util;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import androidx.fragment.app.FragmentActivity;
import com.byd.auto_photo.R;
import com.byd.bean.FileBean;
import com.byd.fragment.BaseProgressDialogFragment;
import com.byd.fragment.ProgressDialogFragment;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2971a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<FileBean> f2972b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LinkedList<String> f2973c;
    private List<String> d;
    private FragmentActivity e;
    private boolean h;
    private String i;
    private long j;
    private ProgressDialogFragment k;
    private i p;
    private volatile HashMap<String, h> l = new HashMap<>();
    private List<String> m = new LinkedList();
    private com.byd.taskmanager.b.b n = new d();
    private FilenameFilter o = new e();
    private f f = new f(this);
    private String[] g = {Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()};

    class a implements BaseProgressDialogFragment.OnDismissListener {
        a() {
        }

        @Override // com.byd.fragment.BaseProgressDialogFragment.OnDismissListener
        public void onDismiss(BaseProgressDialogFragment baseProgressDialogFragment) {
        }
    }

    class b implements BaseProgressDialogFragment.onCancelListener {
        b() {
        }

        @Override // com.byd.fragment.BaseProgressDialogFragment.onCancelListener
        public void onCanceled() {
            w.c("delete progress dialog onCancel : " + l.this.l.size());
            if (l.this.l.size() == 0) {
                return;
            }
            l.this.h = true;
            l.this.s();
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (l.this.k != null) {
                l.this.k.dismissAllowingStateLoss();
            }
            l.this.k = null;
            w.c("dismiss FileDeleteDialog");
            if (l.this.p != null) {
                if (l.this.h) {
                    l.this.p.onDeleteCancel();
                } else {
                    l.this.p.onDeleteComplete(l.this.m);
                }
            }
        }
    }

    class d implements com.byd.taskmanager.b.b<String> {
        d() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, String str2) {
            w.c("delete task onComplete:" + str);
            l.this.f.obtainMessage(1, str).sendToTarget();
        }

        @Override // com.byd.taskmanager.b.b
        public void onCanceled(String str) {
            w.c("delete task onCanceled:" + str);
            l.this.f.obtainMessage(1, str).sendToTarget();
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    class e implements FilenameFilter {
        e() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return !str.startsWith(".") && (m.e(str) || m.c(str));
        }
    }

    private static class f extends b.a.d<l> {
        public f(l lVar) {
            super(lVar);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(l lVar, Message message) {
            if (message.what != 1) {
                return;
            }
            lVar.v((String) message.obj);
        }
    }

    private class g extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private b.a.h.l f2979a;

        public g(Context context) {
            this.f2979a = b.a.h.l.m(context.getApplicationContext());
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            for (int size = l.this.f2973c.size(); size > 0; size--) {
                try {
                    this.f2979a.k((String) l.this.f2973c.removeFirst());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class h extends com.byd.taskmanager.b.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private List<FileBean> f2981a;

        private h(List<FileBean> list, com.byd.taskmanager.b.b bVar) {
            super(bVar);
            this.f2981a = list;
        }

        /* synthetic */ h(l lVar, List list, com.byd.taskmanager.b.b bVar, a aVar) {
            this(list, bVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public String doTask() throws InterruptedException {
            File file;
            int size = this.f2981a.size();
            w.c("run del task :" + this.name + " del size:" + size);
            for (int i = 0; i < size; i++) {
                if (l.this.h) {
                    w.c("task cancel by user !");
                    throw new InterruptedException("cancel");
                }
                FileBean fileBean = this.f2981a.get(i);
                if (fileBean.getType() == 3) {
                    String parent = new File(fileBean.getPath()).getParent();
                    file = new File(parent);
                    l.this.u(file, false);
                    l.this.m.add(fileBean.getPath());
                    com.byd.util.f.k(l.this.e).u(parent);
                } else {
                    String path = fileBean.getPath();
                    file = new File(path);
                    if (!file.exists()) {
                        l.this.t(path);
                    } else if (file.isFile()) {
                        w.c("del " + path + "->" + file.delete() + " " + file.exists());
                        l.this.t(path);
                        l.this.p(file);
                    } else {
                        l.this.u(file, true);
                    }
                }
                l.this.m.add(file.getPath());
            }
            return this.name;
        }
    }

    public interface i {
        void onDeleteCancel();

        void onDeleteComplete(List<String> list);
    }

    public l(FragmentActivity fragmentActivity, int i2) {
        this.e = fragmentActivity;
        this.f2971a = i2;
    }

    private void C() {
        LinkedList<String> linkedList = this.f2973c;
        if (linkedList == null || linkedList.size() <= 0) {
            return;
        }
        new g(this.e.getApplicationContext()).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(File file) {
        Uri uriJ;
        FragmentActivity fragmentActivity = this.e;
        if (fragmentActivity == null || (uriJ = n.j(fragmentActivity, file)) == null || !uriJ.toString().startsWith("content://")) {
            return;
        }
        try {
            com.byd.util.e.b(this.e, file.getPath(), 1, String.valueOf(ContentUris.parseId(uriJ)));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean r(File file) {
        if (this.g == null) {
            return true;
        }
        String path = file.getPath();
        for (String str : this.g) {
            if (path.equals(str)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        try {
            if (this.l == null || this.l.size() <= 0) {
                return;
            }
            Iterator<Map.Entry<String, h>> it = this.l.entrySet().iterator();
            while (it.hasNext()) {
                com.byd.taskmanager.a.e().a(it.next().getValue());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(String str) {
        this.d.add(str);
        com.byd.util.f.k(this.e).u(str);
        this.f2973c.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void u(java.io.File r8, boolean r9) throws java.lang.InterruptedException {
        /*
            r7 = this;
            java.lang.String r0 = r8.getPath()
            java.util.Stack r1 = new java.util.Stack
            r1.<init>()
            r1.push(r8)
        Lc:
            boolean r8 = r1.isEmpty()
            if (r8 != 0) goto L9f
            boolean r8 = r7.h
            if (r8 != 0) goto L97
            if (r9 == 0) goto L25
            java.lang.Object r8 = r1.pop()
            java.io.File r8 = (java.io.File) r8
            java.io.FilenameFilter r2 = r7.o
            java.io.File[] r2 = r8.listFiles(r2)
            goto L2f
        L25:
            java.lang.Object r8 = r1.peek()
            java.io.File r8 = (java.io.File) r8
            java.io.File[] r2 = r8.listFiles()
        L2f:
            if (r2 == 0) goto L7e
            r3 = 0
        L32:
            int r4 = r2.length
            if (r3 >= r4) goto L7e
            boolean r4 = r7.h
            if (r4 != 0) goto L76
            r4 = r2[r3]
            if (r9 != 0) goto L47
            boolean r5 = r4.isDirectory()
            if (r5 == 0) goto L47
            r1.push(r4)
            goto L73
        L47:
            if (r9 == 0) goto L5f
        L49:
            java.util.LinkedList<java.lang.String> r5 = r7.f2973c
            java.lang.String r6 = r4.getPath()
            r5.add(r6)
            java.util.List<java.lang.String> r5 = r7.d
            java.lang.String r6 = r4.getPath()
            r5.add(r6)
            r7.p(r4)
            goto L70
        L5f:
            java.lang.String r5 = r4.getName()
            boolean r6 = com.byd.util.m.e(r5)
            if (r6 != 0) goto L49
            boolean r5 = com.byd.util.m.c(r5)
            if (r5 == 0) goto L70
            goto L49
        L70:
            r4.delete()
        L73:
            int r3 = r3 + 1
            goto L32
        L76:
            java.lang.InterruptedException r8 = new java.lang.InterruptedException
            java.lang.String r9 = "cancel directory delete 111"
            r8.<init>(r9)
            throw r8
        L7e:
            java.io.File[] r2 = r8.listFiles()
            if (r2 == 0) goto L87
            int r2 = r2.length
            if (r2 != 0) goto Lc
        L87:
            boolean r2 = r7.r(r8)
            if (r2 == 0) goto L90
            r8.delete()
        L90:
            if (r9 != 0) goto Lc
            r1.pop()
            goto Lc
        L97:
            java.lang.InterruptedException r8 = new java.lang.InterruptedException
            java.lang.String r9 = "cancel directory delete"
            r8.<init>(r9)
            throw r8
        L9f:
            androidx.fragment.app.FragmentActivity r8 = r7.e
            com.byd.util.f r8 = com.byd.util.f.k(r8)
            boolean r8 = r8.p(r0)
            if (r8 == 0) goto Lb4
            androidx.fragment.app.FragmentActivity r8 = r7.e
            com.byd.util.f r8 = com.byd.util.f.k(r8)
            r8.t(r0)
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.util.l.u(java.io.File, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void v(String str) {
        this.l.remove(str);
        w.c("file delete task end : " + this.l.size());
        if (this.l.isEmpty()) {
            this.f2972b = null;
            if (this.d.size() > 0) {
                b.a.k.g.f(this.e).d(this.d);
            }
            w.c("del finish " + (System.currentTimeMillis() - this.j));
            this.e.runOnUiThread(new c());
            C();
        }
    }

    public void A(List<FileBean> list) {
        List<FileBean> list2 = this.f2972b;
        if (list2 != null) {
            list2.clear();
        } else {
            this.f2972b = new LinkedList();
        }
        this.f2972b.addAll(list);
    }

    public void B(i iVar) {
        this.p = iVar;
    }

    public void D() {
        this.f2973c = new LinkedList<>();
        this.d = new LinkedList();
        int i2 = 0;
        this.h = false;
        if (this.k == null) {
            ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment(R.string.deleting);
            this.k = progressDialogFragment;
            progressDialogFragment.setUiVisibility(this.f2971a);
            this.k.setOnDismissListener(new a());
            this.k.setOnCancelListener(new b());
        }
        this.k.show(this.e.getSupportFragmentManager(), "del_ing_dialog");
        this.m.clear();
        this.l.clear();
        this.j = System.currentTimeMillis();
        int size = this.f2972b.size();
        w.c("del total size:" + size);
        a aVar = null;
        if (size <= 5) {
            h hVar = new h(this, this.f2972b, this.n, aVar);
            hVar.setTaskName("del_0");
            this.l.put(hVar.getTaskName(), hVar);
            com.byd.taskmanager.a.e().d(hVar);
            return;
        }
        int i3 = size / 5;
        w.c("single thread del num:" + i3);
        while (i2 < 5) {
            h hVar2 = new h(this, i2 == 4 ? this.f2972b.subList(i2 * i3, size) : this.f2972b.subList(i2 * i3, (i2 + 1) * i3), this.n, aVar);
            hVar2.setTaskName("del_" + i2);
            this.l.put(hVar2.getTaskName(), hVar2);
            com.byd.taskmanager.a.e().d(hVar2);
            i2++;
        }
    }

    public void q(String str) {
        if (this.f2972b == null) {
            this.f2972b = new LinkedList();
        }
        File file = new File(str);
        this.f2972b.add(new FileBean(file.getName(), file.getPath(), 0, 0, 0L));
    }

    public String w() {
        return this.i;
    }

    public void x() {
        ProgressDialogFragment progressDialogFragment = this.k;
        if (progressDialogFragment != null) {
            if (progressDialogFragment.isShowing()) {
                this.k.dismissAllowingStateLoss();
            }
            this.k.setOnCancelListener(null);
            this.k.setOnDismissListener(null);
        }
        this.e = null;
        f fVar = this.f;
        if (fVar != null) {
            fVar.release();
            this.f = null;
        }
    }

    public void y(String str) {
        this.i = str;
    }

    public void z(FileBean fileBean) {
        List<FileBean> list = this.f2972b;
        if (list == null) {
            this.f2972b = new ArrayList(1);
        } else {
            list.clear();
        }
        this.f2972b.add(fileBean);
    }
}
