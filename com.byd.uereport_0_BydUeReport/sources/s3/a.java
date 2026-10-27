package s3;

import android.annotation.SuppressLint;
import android.content.Context;
import com.byd.displaymode.bean.DisplaySwitchBean;
import p3.e;
import q3.k;

/* JADX INFO: compiled from: DisplayViewPresent.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f7896a = new byte[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    public static volatile b f7897b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final k f7898c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final DisplaySwitchBean f7899d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public e f7900e;

    public a(Context context) {
        DisplaySwitchBean displaySwitchBean = new DisplaySwitchBean();
        this.f7899d = displaySwitchBean;
        this.f7898c = new k(context, displaySwitchBean, this);
    }

    public static b h(Context context) {
        if (f7897b == null) {
            synchronized (f7896a) {
                if (f7897b == null) {
                    f7897b = new a(context);
                }
            }
        }
        return f7897b;
    }

    @Override // s3.b
    public void a() {
        e eVar = this.f7900e;
        if (eVar != null) {
            eVar.a();
        }
    }

    @Override // s3.b
    public int b() {
        k kVar = this.f7898c;
        if (kVar != null) {
            return kVar.f();
        }
        return 0;
    }

    @Override // s3.b
    public void c(e eVar) {
        this.f7900e = eVar;
    }

    @Override // s3.b
    public boolean d() {
        k kVar = this.f7898c;
        return kVar != null && kVar.j();
    }

    @Override // s3.b
    public void e(int i8) {
        e eVar = this.f7900e;
        if (eVar != null) {
            eVar.b(i8);
        }
    }

    @Override // s3.b
    public void f(int i8) {
        e eVar = this.f7900e;
        if (eVar == null || i8 <= 0) {
            return;
        }
        eVar.showCarModelChange(i8);
    }

    @Override // s3.b
    public void g(int i8) {
        e eVar = this.f7900e;
        if (eVar != null) {
            eVar.c(i8);
        }
    }
}
