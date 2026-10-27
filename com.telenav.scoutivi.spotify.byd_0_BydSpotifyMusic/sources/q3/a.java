package q3;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;

/* JADX INFO: compiled from: Config.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f9144a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f9145b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f9146c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f9147d;

    /* JADX INFO: renamed from: q3.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Config.java */
    public static final class C0141a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Context f9148a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f9149b = "";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f9150c = true;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f9151d = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f9152e = 2;

        public C0141a(Context context) {
            this.f9148a = context.getApplicationContext();
        }

        private void f() {
            this.f9149b = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + this.f9148a.getPackageName() + File.separatorChar + "logger";
        }

        public a e() {
            return new a(this);
        }

        public C0141a g(boolean z6) {
            this.f9150c = z6;
            return this;
        }

        public C0141a h(boolean z6) {
            this.f9151d = z6;
            if (z6 && TextUtils.isEmpty(this.f9149b)) {
                f();
            }
            return this;
        }

        public C0141a i(int i7) {
            this.f9152e = i7;
            return this;
        }
    }

    a(C0141a c0141a) {
        this.f9144a = "";
        this.f9145b = true;
        this.f9146c = false;
        this.f9147d = 2;
        this.f9144a = c0141a.f9149b;
        this.f9145b = c0141a.f9150c;
        this.f9146c = c0141a.f9151d;
        this.f9147d = c0141a.f9152e;
    }

    public String a() {
        return this.f9144a;
    }

    public int b() {
        return this.f9147d;
    }

    public boolean c() {
        return this.f9145b;
    }

    public boolean d() {
        return this.f9146c;
    }
}
