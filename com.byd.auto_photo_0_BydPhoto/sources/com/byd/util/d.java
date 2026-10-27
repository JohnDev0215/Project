package com.byd.util;

import android.media.MediaScannerConnection;
import com.byd.auto_photo.BurstshootBaseActivity;
import com.byd.auto_photo.R;
import com.byd.bean.BurstshootItem;
import com.byd.fragment.ProgressDialogFragment;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<BurstshootItem> f2941a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ArrayList<String> f2942b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private File f2943c;
    private BurstshootBaseActivity d;
    private ProgressDialogFragment e;
    private b f;

    class a implements com.byd.taskmanager.b.b<Boolean> {
        a() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, Boolean bool) {
            w.c("burst shoot save onComplete:" + bool);
            if (d.this.e != null) {
                d.this.e.dismissAllowingStateLoss();
            }
            d.this.e = null;
            if (d.this.f != null) {
                d.this.f.a(d.this.f2942b, bool.booleanValue());
            }
        }

        @Override // com.byd.taskmanager.b.b
        public void onCanceled(String str) {
            w.c("burst shoot save onCanceled:" + str);
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
            w.c("burst shoot save onStart");
        }
    }

    public interface b {
        void a(ArrayList<String> arrayList, boolean z);
    }

    private class c extends com.byd.taskmanager.b.a<Boolean> {
        public c(com.byd.taskmanager.b.b<Boolean> bVar) {
            super(bVar);
            setTaskName("burst_save_task" + System.currentTimeMillis());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Boolean doTask() {
            int size = d.this.f2941a.size();
            LinkedList linkedList = new LinkedList();
            String parent = null;
            for (int i = 0; i < size; i++) {
                String path = ((BurstshootItem) d.this.f2941a.get(i)).getPath();
                File file = new File(path);
                if (((BurstshootItem) d.this.f2941a.get(i)).isSelect()) {
                    String name = file.getName();
                    if (parent == null) {
                        File parentFile = file.getParentFile();
                        while (!"Camera".equals(parentFile.getParentFile().getName())) {
                            parentFile = parentFile.getParentFile();
                        }
                        parent = parentFile.getParent();
                        w.c("burst file save to : " + parent);
                    }
                    String str = parent + File.separator + name;
                    n.B(file.getPath(), str, null);
                    d.this.f2942b.add(str);
                    linkedList.add(file.getPath());
                } else {
                    file.delete();
                    linkedList.add(path);
                }
                b.a.h.l.m(d.this.d).k(path);
            }
            String[] strArr = new String[d.this.f2942b.size()];
            d.this.f2942b.toArray(strArr);
            try {
                MediaScannerConnection.scanFile(d.this.d, strArr, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            b.a.k.g.f(d.this.d).d(linkedList);
            n.f(d.this.f2943c);
            f.k(d.this.d).u(d.this.f2943c.getPath());
            w.c("burst save finish");
            return Boolean.TRUE;
        }
    }

    public void h() {
        ProgressDialogFragment progressDialogFragment = this.e;
        if (progressDialogFragment != null) {
            if (progressDialogFragment.isShowing()) {
                this.e.dismissAllowingStateLoss();
            }
            this.e = null;
        }
        this.d = null;
    }

    public void i(BurstshootBaseActivity burstshootBaseActivity, List<BurstshootItem> list, File file) {
        this.d = burstshootBaseActivity;
        this.f2941a = list;
        this.f2943c = file;
        this.f2942b = new ArrayList<>(list.size());
    }

    public void j(b bVar) {
        this.f = bVar;
    }

    public void k() {
        c cVar = new c(new a());
        if (this.e == null) {
            this.e = new ProgressDialogFragment(R.string.saving_tips);
        }
        this.e.show(this.d.getSupportFragmentManager(), "burst_saving");
        com.byd.taskmanager.a.e().d(cVar);
    }
}
