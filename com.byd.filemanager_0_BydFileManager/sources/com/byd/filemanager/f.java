package com.byd.filemanager;

import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.bean.FileState;
import com.byd.filemanager.k.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: FileContext.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile f f3552a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private FileState f3553b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<FileItem> f3554c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<FileItem> f3555d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3556e;
    private int f;
    private int g;
    private int h;
    private String i;
    private boolean k;
    private com.byd.filemanager.i.c.a[] j = new com.byd.filemanager.i.c.a[1];
    private boolean l = false;
    private boolean m = true;

    private f() {
    }

    private void d() {
        int iB = a.a.a.a(FileApplication.a()).b();
        Log.w("FileManager", "uiConfig = " + iB);
        if (iB == 0) {
            this.m = true;
        } else if (iB == 1) {
            this.m = false;
        } else if (iB == 2) {
            this.m = false;
        }
    }

    public static f h() {
        if (f3552a == null) {
            synchronized (f.class) {
                if (f3552a == null) {
                    f3552a = new f();
                }
            }
        }
        return f3552a;
    }

    private void q() {
        int i = SystemProperties.getInt("persist.vendor.byd.cfg.huc.steering.wheel.position", -1);
        Log.w("FileManager", "isRightRudder steer = " + i);
        if (1 == i) {
            this.l = true;
        } else {
            this.l = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void A() {
        this.f3556e = j.a(FileApplication.a(), "sort_index", 0);
        e.f().b();
        com.byd.filemanager.service.b.c().i();
    }

    public com.byd.filemanager.i.c.a B() {
        com.byd.filemanager.i.c.a[] aVarArr = this.j;
        com.byd.filemanager.i.c.a aVar = aVarArr[0];
        aVarArr[0] = null;
        return aVar;
    }

    public void C(com.byd.filemanager.i.c.a aVar) {
        this.j[0] = aVar;
    }

    public void D(String str) {
        this.i = str;
    }

    public void E(boolean z) {
        this.k = z;
    }

    public void F(List<FileItem> list) {
        this.f3554c = list;
    }

    public void G(FileState fileState) {
        com.byd.filemanager.k.b.b("fileState = " + fileState);
        this.f3553b = fileState;
        org.greenrobot.eventbus.c.c().k(new com.byd.music.f.d());
    }

    public void H(FileState fileState) {
        this.f3553b = fileState;
    }

    public void I(int i) {
        this.h = i;
        com.byd.filemanager.k.b.b("setMenuIndex mMenuIndex:" + i);
        if (i == 1) {
            D(e.f().k(this.g));
        } else if (i == 5) {
            D("/");
        } else {
            D(null);
        }
    }

    public void J() {
        if (this.f3553b == FileState.CHOOSING) {
            G(FileState.NORMAL);
            c();
        }
    }

    public void K() {
        G(FileState.NORMAL);
        c();
    }

    public void L(int i) {
        this.g = i;
        com.byd.filemanager.k.b.b("setRootIndex mRootIndex:" + this.g);
        if (this.h == 1) {
            D(e.f().k(this.g));
        }
    }

    public void M(int i) {
        this.f3556e = i;
    }

    public void N() {
        if (this.f == 0) {
            this.f = 1;
        } else {
            this.f = 0;
        }
    }

    public void a(List<FileItem> list) {
        this.f3555d.clear();
        if (list != null) {
            this.f3555d.addAll(list);
        }
    }

    public void b(FileItem fileItem) {
        if (!fileItem.J()) {
            this.f3555d.remove(fileItem);
        } else {
            if (this.f3555d.contains(fileItem)) {
                return;
            }
            this.f3555d.add(fileItem);
        }
    }

    public void c() {
        Iterator<FileItem> it = this.f3555d.iterator();
        while (it.hasNext()) {
            it.next().P(false);
        }
        this.f3555d.clear();
    }

    public String e() {
        return this.i;
    }

    public List<FileItem> f() {
        return this.f3554c;
    }

    public FileState g() {
        return this.f3553b;
    }

    public int i() {
        return this.f;
    }

    public int j() {
        return this.h;
    }

    public int k() {
        return this.g;
    }

    public List<FileItem> l() {
        return this.f3555d;
    }

    public int m() {
        return this.f3555d.size();
    }

    public FileItem n() {
        if (this.f3555d.size() == 1) {
            return this.f3555d.get(0);
        }
        return null;
    }

    public List<String> o() {
        ArrayList arrayList = new ArrayList();
        Iterator<FileItem> it = this.f3555d.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().E());
        }
        return arrayList;
    }

    public int p() {
        return this.f3556e;
    }

    public void r() {
        this.h = 1;
        this.g = 0;
        this.f3553b = FileState.NORMAL;
        this.f3554c = new ArrayList();
        this.f3555d = new ArrayList();
        this.f = 0;
        d();
        q();
        e.f().r();
        D(e.f().k(this.g));
        ThreadManager.h().k(new Runnable() { // from class: com.byd.filemanager.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f3547b.A();
            }
        });
    }

    public boolean s() {
        return this.f3553b == FileState.CHOOSING;
    }

    public boolean t() {
        return this.k;
    }

    public String toString() {
        return "FileContext{mFileState=" + this.f3553b + ", mSelectedFiles=" + this.f3555d + ", mRootIndex=" + this.g + ", mMenuIndex=" + this.h + ", mCurrPath='" + this.i + "'}";
    }

    public boolean u() {
        return this.h == 5;
    }

    public boolean v() {
        return this.m;
    }

    public boolean w() {
        return this.f3553b == FileState.NORMAL;
    }

    public boolean x() {
        return this.l;
    }

    public boolean y() {
        int layoutDirectionFromLocale = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault());
        com.byd.filemanager.k.b.b("isRtl layoutDirection:" + TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()));
        return layoutDirectionFromLocale == 1;
    }
}
