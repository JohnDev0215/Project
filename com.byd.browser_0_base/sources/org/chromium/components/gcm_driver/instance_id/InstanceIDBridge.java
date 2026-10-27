package org.chromium.components.gcm_driver.instance_id;

import J.N;
import defpackage.i9;
import java.util.concurrent.ExecutionException;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class InstanceIDBridge {
    public static boolean b;
    public long a;

    public class a extends f<String> {
        public final /* synthetic */ int b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(int i) {
            super();
            this.b = i;
        }

        @Override // org.chromium.components.gcm_driver.instance_id.InstanceIDBridge.f
        public final /* bridge */ /* synthetic */ String a() {
            return "";
        }

        @Override // org.chromium.components.gcm_driver.instance_id.InstanceIDBridge.f
        public final void c(String str) {
            InstanceIDBridge instanceIDBridge = InstanceIDBridge.this;
            N.MdjzqME2(instanceIDBridge.a, instanceIDBridge, this.b, str);
        }
    }

    public class b extends f<Long> {
        public final /* synthetic */ int b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(int i) {
            super();
            this.b = i;
        }

        @Override // org.chromium.components.gcm_driver.instance_id.InstanceIDBridge.f
        public final Long a() {
            return 0L;
        }

        @Override // org.chromium.components.gcm_driver.instance_id.InstanceIDBridge.f
        public final void c(Long l) {
            InstanceIDBridge instanceIDBridge = InstanceIDBridge.this;
            N.M1_H2CVT(instanceIDBridge.a, instanceIDBridge, this.b, l.longValue());
        }
    }

    public class c extends f<String> {
        public final /* synthetic */ int b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(int i) {
            super();
            this.b = i;
        }

        @Override // org.chromium.components.gcm_driver.instance_id.InstanceIDBridge.f
        public final /* bridge */ /* synthetic */ String a() {
            return "";
        }

        @Override // org.chromium.components.gcm_driver.instance_id.InstanceIDBridge.f
        public final void c(String str) {
            InstanceIDBridge instanceIDBridge = InstanceIDBridge.this;
            N.MgpbhGOm(instanceIDBridge.a, instanceIDBridge, this.b, str);
        }
    }

    public class d extends f<Boolean> {
        public final /* synthetic */ int b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(int i) {
            super();
            this.b = i;
        }

        @Override // org.chromium.components.gcm_driver.instance_id.InstanceIDBridge.f
        public final Boolean a() {
            return Boolean.FALSE;
        }

        @Override // org.chromium.components.gcm_driver.instance_id.InstanceIDBridge.f
        public final void c(Boolean bool) {
            InstanceIDBridge instanceIDBridge = InstanceIDBridge.this;
            N.MWbugtkA(instanceIDBridge.a, instanceIDBridge, this.b, bool.booleanValue());
        }
    }

    public class e extends f<Boolean> {
        public final /* synthetic */ int b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(int i) {
            super();
            this.b = i;
        }

        @Override // org.chromium.components.gcm_driver.instance_id.InstanceIDBridge.f
        public final Boolean a() {
            return Boolean.TRUE;
        }

        @Override // org.chromium.components.gcm_driver.instance_id.InstanceIDBridge.f
        public final void c(Boolean bool) {
            InstanceIDBridge instanceIDBridge = InstanceIDBridge.this;
            N.MB$4Dsst(instanceIDBridge.a, instanceIDBridge, this.b, bool.booleanValue());
        }
    }

    public abstract class f<Result> {

        public class a extends i9<Result> {
            public a() {
            }

            @Override // defpackage.i9
            public final Result b() {
                return (Result) f.this.a();
            }

            @Override // defpackage.i9
            public final void l(Result result) {
                if (InstanceIDBridge.b) {
                    return;
                }
                f fVar = f.this;
                if (InstanceIDBridge.this.a != 0) {
                    fVar.c(result);
                }
            }
        }

        public f() {
        }

        public abstract Result a();

        public final void b() {
            a aVar = new a();
            aVar.c(i9.e);
            if (InstanceIDBridge.b) {
                try {
                    c(aVar.g());
                } catch (InterruptedException | ExecutionException e) {
                    throw new IllegalStateException(e);
                }
            }
        }

        public abstract void c(Result result);
    }

    public InstanceIDBridge(long j) {
        this.a = j;
    }

    @CalledByNative
    public static InstanceIDBridge create(long j, String str) {
        return new InstanceIDBridge(j);
    }

    @CalledByNative
    public static boolean setBlockOnAsyncTasksForTesting(boolean z) {
        boolean z2 = b;
        b = z;
        return z2;
    }

    @CalledByNative
    public final void deleteInstanceID(int i) {
        new e(i).b();
    }

    @CalledByNative
    public final void deleteToken(int i, String str, String str2) {
        new d(i).b();
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
    }

    @CalledByNative
    public void getCreationTime(int i) {
        new b(i).b();
    }

    @CalledByNative
    public void getId(int i) {
        new a(i).b();
    }

    @CalledByNative
    public final void getToken(int i, String str, String str2, int i2) {
        new c(i).b();
    }
}
