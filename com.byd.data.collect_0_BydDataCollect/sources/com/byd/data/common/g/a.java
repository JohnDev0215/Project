package com.byd.data.common.g;

import android.content.Context;
import com.byd.data.common.c.c.b;
import com.byd.data.common.i.f;
import com.byd.data.common.i.g;
import com.byd.data.common.server.CloudCanService;
import com.byd.data.common.tbox.TBoxDevice;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: compiled from: Router.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f3391a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3392b = "Router";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3393c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3394d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TBoxDevice f3395e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b f3396f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private com.byd.data.common.server.e.a f3397g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private CloudCanService f3398h;

    private a() {
        k(com.byd.data.common.server.b.e().f());
    }

    private File b(int i2, boolean z, boolean z2) {
        String str;
        String str2 = this.f3394d;
        String str3 = File.separator;
        CharSequence charSequenceSubSequence = str2.subSequence(0, str2.indexOf(str3) + 1);
        String str4 = i2 == 1 ? "cloud" : i2 == 2 ? "bms" : "exact";
        File file = new File(((Object) charSequenceSubSequence) + str4 + str3);
        f.g("Router", str4 + " , exists : " + file.exists() + ", mkdirs name : " + file.getName() + " , path : " + file.getPath());
        if (file.exists()) {
            try {
                f.g("Router", str4 + file.getPath() + " set 777 ");
                Runtime.getRuntime().exec("chmod -R 777 " + file);
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        } else {
            boolean zMkdirs = file.mkdirs();
            f.g("Router", str4 + ", mkdirs ret =" + zMkdirs + " , name : " + file.getName() + " , path : " + file.getPath());
            if (zMkdirs) {
                try {
                    f.g("Router", str4 + file.getPath() + " set 777 ");
                    Runtime.getRuntime().exec("chmod -R 777 " + file);
                } catch (IOException e3) {
                    throw new RuntimeException(e3);
                }
            }
        }
        com.byd.data.common.server.b bVarE = com.byd.data.common.server.b.e();
        if (i2 == 1) {
            Objects.requireNonNull(bVarE);
            str = "CanCollectEventConfig.conf";
        } else if (i2 == 2) {
            Objects.requireNonNull(bVarE);
            str = "BmsConfig.conf";
        } else {
            str = "Exact.conf";
        }
        File file2 = new File(file, str);
        f.g("Router", "config createNewFile isDel =" + z + " , exists : " + file2.exists() + " , isCreteFile : " + z2);
        if (!z && !file2.exists() && z2) {
            try {
                f.g("Router", "config createNewFile ret =" + file2.createNewFile() + " , name : " + file2.getName() + " , path : " + file2.getPath());
                Runtime runtime = Runtime.getRuntime();
                StringBuilder sb = new StringBuilder();
                sb.append("chmod -R 755 ");
                sb.append(file2);
                runtime.exec(sb.toString());
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
        return file2;
    }

    public static a i() {
        if (f3391a == null) {
            synchronized (a.class) {
                if (f3391a == null) {
                    f3391a = new a();
                }
            }
        }
        return f3391a;
    }

    private void k(Context context) {
        if (this.f3393c != null) {
            return;
        }
        this.f3393c = context;
        this.f3394d = context.getDataDir().getPath() + File.separator;
        TBoxDevice tBoxDevice = new TBoxDevice();
        this.f3395e = tBoxDevice;
        tBoxDevice.C();
    }

    public File a(int i2, boolean z) {
        return b(i2, false, z);
    }

    public boolean c(int i2, String str) {
        File fileB = b(i2, true, false);
        if (!fileB.isFile() || !fileB.exists()) {
            return true;
        }
        boolean zDelete = fileB.delete();
        if (zDelete) {
            g.f(str);
        }
        return zDelete;
    }

    public Context d() {
        return this.f3393c;
    }

    public CloudCanService e() {
        return this.f3398h;
    }

    public com.byd.data.common.server.e.a f() {
        return this.f3397g;
    }

    public b g() {
        return this.f3396f;
    }

    public String h() {
        return this.f3394d;
    }

    public TBoxDevice j() {
        return this.f3395e;
    }

    public void l() {
        this.f3393c = null;
        f3391a = null;
        this.f3394d = null;
        TBoxDevice tBoxDevice = this.f3395e;
        if (tBoxDevice != null) {
            tBoxDevice.D();
            this.f3395e = null;
        }
        b bVar = this.f3396f;
        if (bVar != null) {
            bVar.d();
        }
        com.byd.data.common.server.e.a aVar = this.f3397g;
        if (aVar != null) {
            aVar.d();
        }
        this.f3397g = null;
        this.f3396f = null;
        this.f3398h = null;
    }

    public void m(CloudCanService cloudCanService) {
        if (this.f3398h != null || cloudCanService == null) {
            return;
        }
        this.f3398h = cloudCanService;
    }

    public void n(com.byd.data.common.server.e.a aVar, b bVar) {
        if (this.f3397g != null) {
            return;
        }
        this.f3397g = aVar;
        this.f3396f = bVar;
    }
}
