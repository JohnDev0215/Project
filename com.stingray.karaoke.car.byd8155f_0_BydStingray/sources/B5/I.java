package B5;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.HandlerThread;
import android.util.JsonWriter;
import android.util.Log;
import io.sentry.C1489n1;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class I extends C5.g {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final E f658n = new E();
    public static final Charset o = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f659a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final B3.m f660b;
    public final io.sentry.internal.debugmeta.c c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C1489n1 f661e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final H f662f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C1.j f663g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Map f664h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final m f665i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final D5.d f666j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final String f667k;
    public final Object l = new Object();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final m f668m;

    public I(Context context, io.sentry.internal.debugmeta.c cVar, m mVar, D5.d dVar, B3.m mVar2, C1489n1 c1489n1, Map map, long j3, int i7, C1.j jVar, m mVar3, String str) {
        this.f659a = context;
        this.c = cVar;
        this.f666j = dVar;
        this.f660b = mVar2;
        this.f661e = c1489n1;
        this.f663g = jVar;
        this.f664h = map;
        this.f665i = mVar;
        this.d = i7;
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, new C1.m(1));
        this.f668m = mVar3;
        this.f667k = str;
        HandlerThread handlerThread = new HandlerThread("Segment-SegmentDispatcher", 10);
        handlerThread.start();
        this.f662f = new H(handlerThread.getLooper(), this, 0);
        scheduledExecutorServiceNewScheduledThreadPool.scheduleAtFixedRate(new F(this, 0), mVar2.q() >= i7 ? 0L : j3, j3, TimeUnit.MILLISECONDS);
    }

    public static D a(File file, String str) throws IOException {
        if (!file.exists() && !file.mkdirs() && !file.isDirectory()) {
            throw new IOException("Could not create directory at " + file);
        }
        File file2 = new File(file, str);
        try {
            return new D(file2);
        } catch (IOException unused) {
            if (file2.delete()) {
                return new D(file2);
            }
            throw new IOException("Could not create queue file (" + str + ") in " + file + ".");
        }
    }

    public final void b(C5.d dVar) {
        H h10 = this.f662f;
        h10.sendMessage(h10.obtainMessage(0, dVar));
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void c() {
        int i7;
        io.sentry.internal.debugmeta.c cVar = this.c;
        B3.m mVar = this.f660b;
        if (!d()) {
            return;
        }
        C1.j jVar = this.f663g;
        jVar.t("Uploading payloads in queue to Segment.", new Object[0]);
        n nVarU = null;
        try {
            try {
                try {
                    nVarU = cVar.U(this.f667k);
                    G g7 = new G(nVarU.f727n);
                    ((JsonWriter) g7.f655n).beginObject();
                    g7.b();
                    A2.g gVar = new A2.g(g7, this.f668m);
                    mVar.k(gVar);
                    g7.g();
                    g7.i((String) cVar.f16570n);
                    g7.close();
                    i7 = gVar.f320n;
                    try {
                        nVarU.close();
                        D5.g.c(nVarU);
                        try {
                            mVar.o(i7);
                            jVar.t("Uploaded %s payloads. %s remain in the queue.", Integer.valueOf(i7), Integer.valueOf(mVar.q()));
                            H h10 = (H) this.f661e.f16624m;
                            h10.sendMessage(h10.obtainMessage(1, i7, 0));
                            if (mVar.q() > 0) {
                                c();
                            }
                        } catch (IOException e10) {
                            jVar.l(e10, A7.k.g("Unable to remove ", i7, " payload(s) from queue."), new Object[0]);
                        }
                    } catch (o e11) {
                        e = e11;
                        int i10 = e.l;
                        if (i10 < 400 || i10 >= 500 || i10 == 429) {
                            jVar.l(e, "Error while uploading payloads", new Object[0]);
                            D5.g.c(nVarU);
                            return;
                        }
                        jVar.l(e, "Payloads were rejected by server. Marked for removal.", new Object[0]);
                        try {
                            mVar.o(i7);
                        } catch (IOException unused) {
                            jVar.l(e, "Unable to remove " + i7 + " payload(s) from queue.", new Object[0]);
                        }
                        D5.g.c(nVarU);
                    }
                } catch (IOException e12) {
                    jVar.l(e12, "Error while uploading payloads", new Object[0]);
                    D5.g.c(nVarU);
                }
            } catch (o e13) {
                e = e13;
                i7 = 0;
            }
        } catch (Throwable th) {
            D5.g.c(nVarU);
            throw th;
        }
    }

    public final boolean d() {
        NetworkInfo activeNetworkInfo;
        if (this.f660b.q() <= 0) {
            return false;
        }
        Context context = this.f659a;
        return !D5.g.f(0, context, "android.permission.ACCESS_NETWORK_STATE") || ((activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) != null && activeNetworkInfo.isConnectedOrConnecting());
    }

    public final void e() {
        if (d()) {
            D5.d dVar = this.f666j;
            if (!dVar.isShutdown()) {
                dVar.submit(new F(this, 1));
                return;
            }
            C1.j jVar = this.f663g;
            if (jVar.s(2)) {
                Log.i((String) jVar.f944m, "A call to flush() was made after shutdown() has been called.  In-flight events may not be uploaded right away.");
            }
        }
    }
}
