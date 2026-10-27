package com.byd.util;

import android.os.Handler;
import com.byd.bean.FileBean;
import com.byd.bean.FileBeanInGroup;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile r f2993a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.byd.taskmanager.b.a f2994b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<FileBean> f2995c = new LinkedList();
    private int d;
    private Handler e;

    class a implements com.byd.taskmanager.b.b<List<FileBean>> {
        a() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, List<FileBean> list) {
            r.this.f2995c = list;
            r.this.l();
        }

        @Override // com.byd.taskmanager.b.b
        public void onCanceled(String str) {
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    class b implements com.byd.taskmanager.b.b<Integer> {
        b() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, Integer num) {
            r.this.d = num.intValue();
            r.this.l();
        }

        @Override // com.byd.taskmanager.b.b
        public void onCanceled(String str) {
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    class c implements com.byd.taskmanager.b.b<List<FileBean>> {
        c() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, List<FileBean> list) {
            r.this.f2995c = list;
            r.this.l();
        }

        @Override // com.byd.taskmanager.b.b
        public void onCanceled(String str) {
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    private class d extends com.byd.taskmanager.b.a<Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private FileBean f2999a;

        d(com.byd.taskmanager.b.b bVar) {
            super(bVar);
            this.name = "calc_index";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Integer doTask() {
            int size = r.this.f2995c.size();
            for (int i = 0; i < size; i++) {
                if (this.f2999a.equals((FileBean) r.this.f2995c.get(i))) {
                    return Integer.valueOf(i);
                }
            }
            return 0;
        }

        public void c(FileBean fileBean) {
            this.f2999a = fileBean;
        }
    }

    private class e extends com.byd.taskmanager.b.a<List<FileBean>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private List<FileBeanInGroup> f3001a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private FileBean f3002b;

        e(com.byd.taskmanager.b.b<List<FileBean>> bVar) {
            super(bVar);
            this.name = "change_data";
        }

        public void b(List<FileBeanInGroup> list, FileBean fileBean) {
            this.f3001a = list;
            this.f3002b = fileBean;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        public List<FileBean> doTask() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            w.c("MediaDataChange task start");
            LinkedList linkedList = new LinkedList();
            r.this.d = -1;
            int size = this.f3001a.size();
            boolean z = false;
            for (int i = 0; i < size; i++) {
                FileBeanInGroup fileBeanInGroup = this.f3001a.get(i);
                if (fileBeanInGroup.getType() == 2) {
                    linkedList.add(fileBeanInGroup.getFileBean());
                    if (!z) {
                        r.e(r.this);
                        if (this.f3002b.equals(fileBeanInGroup.getFileBean())) {
                            z = true;
                        }
                    }
                }
            }
            w.c("MediaDataChange task end : " + (System.currentTimeMillis() - jCurrentTimeMillis));
            return linkedList;
        }
    }

    private class f extends com.byd.taskmanager.b.a<List<FileBean>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private List<FileBean> f3004a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private FileBean f3005b;

        f(com.byd.taskmanager.b.b bVar) {
            super(bVar);
            this.name = "video_datas";
        }

        public void b(List<FileBean> list, FileBean fileBean) {
            this.f3004a = list;
            this.f3005b = fileBean;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        public List<FileBean> doTask() {
            LinkedList linkedList = new LinkedList();
            r.this.d = -1;
            int size = this.f3004a.size();
            boolean z = false;
            for (int i = 0; i < size; i++) {
                FileBean fileBean = this.f3004a.get(i);
                if (fileBean.getType() == 2) {
                    linkedList.add(fileBean);
                    if (!z) {
                        r.e(r.this);
                        if (this.f3005b.equals(fileBean)) {
                            z = true;
                        }
                    }
                }
            }
            return linkedList;
        }
    }

    private r() {
    }

    static /* synthetic */ int e(r rVar) {
        int i = rVar.d;
        rVar.d = i + 1;
        return i;
    }

    public static r h() {
        synchronized (r.class) {
            if (f2993a == null) {
                f2993a = new r();
            }
        }
        return f2993a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        Handler handler = this.e;
        if (handler != null) {
            handler.obtainMessage(101, this.f2995c).sendToTarget();
        }
    }

    public List<FileBean> f() {
        return this.f2995c;
    }

    public int g() {
        int i = this.d;
        if (i == -1) {
            return 0;
        }
        return i;
    }

    public boolean i() {
        int iF;
        return this.f2994b == null || (iF = com.byd.taskmanager.a.e().f(this.f2994b.getTaskName())) == 1 || iF == -1;
    }

    public void j() {
        List<FileBean> list;
        if (this.f2994b != null) {
            if (com.byd.taskmanager.a.e().f(this.f2994b.getTaskName()) == 0) {
                w.c("cancelChange , cancel task");
                com.byd.taskmanager.a.e().a(this.f2994b);
            }
            if ((this.f2994b instanceof e) && (list = this.f2995c) != null) {
                list.clear();
            }
        }
        Handler handler = this.e;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            Handler handler2 = this.e;
            if (handler2 instanceof b.a.d) {
                ((b.a.d) handler2).release();
            }
            this.e = null;
        }
        f2993a = null;
    }

    public void k(Handler handler) {
        this.e = handler;
    }

    public void m(List<FileBean> list, FileBean fileBean) {
        this.f2995c = list;
        if (this.f2994b != null) {
            w.c("startCalcFirstIndex , cancel task");
            com.byd.taskmanager.a.e().a(this.f2994b);
        }
        com.byd.taskmanager.b.a aVar = this.f2994b;
        if (aVar == null || !(aVar instanceof d)) {
            this.f2994b = new d(new b());
        }
        ((d) this.f2994b).c(fileBean);
        com.byd.taskmanager.a.e().d(this.f2994b);
    }

    public void n(List<FileBean> list, FileBean fileBean) {
        if (this.f2994b != null) {
            com.byd.taskmanager.a.e().a(this.f2994b);
        }
        com.byd.taskmanager.b.a aVar = this.f2994b;
        if (aVar == null || !(aVar instanceof f)) {
            this.f2994b = new f(new c());
        }
        ((f) this.f2994b).b(list, fileBean);
        com.byd.taskmanager.a.e().d(this.f2994b);
    }

    public void o(List<FileBeanInGroup> list, FileBean fileBean) {
        if (this.f2994b != null) {
            w.c("startChangeData , cancel task");
            com.byd.taskmanager.a.e().a(this.f2994b);
        }
        com.byd.taskmanager.b.a aVar = this.f2994b;
        if (aVar == null || !(aVar instanceof e)) {
            this.f2994b = new e(new a());
        }
        ((e) this.f2994b).b(list, fileBean);
        com.byd.taskmanager.a.e().d(this.f2994b);
    }
}
