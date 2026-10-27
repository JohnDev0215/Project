package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class de {

    /* JADX INFO: compiled from: DCP */
    public static final class b extends de {
        @Override // com.amazon.identity.auth.device.de
        public void a() {
        }

        @Override // com.amazon.identity.auth.device.de
        public void a(long j) {
        }

        @Override // com.amazon.identity.auth.device.de
        public void a(String str) {
        }

        @Override // com.amazon.identity.auth.device.de
        public void b() {
        }

        @Override // com.amazon.identity.auth.device.de
        public double c() {
            return -1.0d;
        }

        @Override // com.amazon.identity.auth.device.de
        public double d() {
            return -1.0d;
        }

        @Override // com.amazon.identity.auth.device.de
        public void e() {
        }
    }

    public static de a(ce ceVar, String str, String str2) {
        return ceVar != null ? new a(ceVar, str, str2) : new b();
    }

    public abstract void a();

    public abstract void a(long j);

    public abstract void a(String str);

    public abstract void b();

    public abstract double c();

    public abstract double d();

    public abstract void e();

    /* JADX INFO: compiled from: DCP */
    public static final class a extends de {
        public static final String g = "com.amazon.identity.auth.device.de$a";

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ce f285a;
        public final String b;
        public String c;
        public boolean f = false;
        public long d = -1;
        public long e = -1;

        public a(ce ceVar, String str, String str2) {
            this.f285a = ceVar;
            this.b = str;
            this.c = str2;
        }

        @Override // com.amazon.identity.auth.device.de
        public void a(long j) {
            if (TextUtils.isEmpty(this.c)) {
                ga.a(g);
                return;
            }
            if (this.f) {
                return;
            }
            ce ceVar = this.f285a;
            if (ceVar == null) {
                Log.w(ga.a(g), "Could not record timer because no collector was set");
            } else {
                ceVar.a(this.b, this.c, j);
            }
        }

        @Override // com.amazon.identity.auth.device.de
        public void b() {
            this.d = System.nanoTime();
        }

        @Override // com.amazon.identity.auth.device.de
        public double c() {
            if (TextUtils.isEmpty(this.c)) {
                ga.a(g);
                return -1.0d;
            }
            if (this.f) {
                return -1.0d;
            }
            long j = this.d;
            if (j < 0) {
                ga.a(g);
                return -1.0d;
            }
            long j2 = this.e;
            long jNanoTime = j2 > 0 ? (j2 - j) / 1000000 : (System.nanoTime() - this.d) / 1000000;
            this.d = -1L;
            this.e = -1L;
            ce ceVar = this.f285a;
            if (ceVar == null) {
                Log.w(ga.a(g), "Could not record timer because no collector was set");
                return -1.0d;
            }
            ceVar.a(this.b, this.c, jNanoTime);
            return jNanoTime;
        }

        @Override // com.amazon.identity.auth.device.de
        public double d() {
            double dC = c();
            this.f = true;
            return dC;
        }

        @Override // com.amazon.identity.auth.device.de
        public void e() {
            this.e = System.nanoTime();
        }

        @Override // com.amazon.identity.auth.device.de
        public void a() {
            this.f = true;
        }

        @Override // com.amazon.identity.auth.device.de
        public void a(String str) {
            this.c = str;
        }
    }
}
