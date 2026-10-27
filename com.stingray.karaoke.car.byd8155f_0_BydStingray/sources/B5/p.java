package B5;

import B5.C0087k;
import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Pair;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
public final class p extends AsyncTask {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0087k f728a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CountDownLatch f729b;
    public final C1.j c;

    public p(C0087k c0087k, CountDownLatch countDownLatch, C1.j jVar) {
        this.f728a = c0087k;
        this.f729b = countDownLatch;
        this.c = jVar;
    }

    public final Pair a(Context context) throws IllegalAccessException, InvocationTargetException {
        Object objInvoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", Context.class).invoke(null, context);
        if (!((Boolean) objInvoke.getClass().getMethod("isLimitAdTrackingEnabled", null).invoke(objInvoke, null)).booleanValue()) {
            return Pair.create((String) objInvoke.getClass().getMethod("getId", null).invoke(objInvoke, null), Boolean.TRUE);
        }
        this.c.k("Not collecting advertising ID because isLimitAdTrackingEnabled (Google Play Services) is true.", new Object[0]);
        return Pair.create(null, Boolean.FALSE);
    }

    /* JADX WARN: Multi-variable type inference failed */
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
    @Override // android.os.AsyncTask
    public final Object doInBackground(Object[] objArr) {
        Context context = ((Context[]) objArr)[0];
        try {
            return a(context);
        } catch (Exception e10) {
            C1.j jVarCreate = this.c;
            jVarCreate.l(e10, "Unable to collect advertising ID from Google Play Services.", new Object[0]);
            try {
                ContentResolver contentResolver = context.getContentResolver();
                if (Settings.Secure.getInt(contentResolver, "limit_ad_tracking") != 0) {
                    jVarCreate.k("Not collecting advertising ID because limit_ad_tracking (Amazon Fire OS) is true.", new Object[0]);
                    jVarCreate = Pair.create(null, Boolean.FALSE);
                } else {
                    jVarCreate = Pair.create(Settings.Secure.getString(contentResolver, "advertising_id"), Boolean.TRUE);
                }
                return jVarCreate;
            } catch (Exception e11) {
                jVarCreate.l(e11, "Unable to collect advertising ID from Amazon Fire OS.", new Object[0]);
                jVarCreate.k("Unable to collect advertising ID from Amazon Fire OS and Google Play Services.", new Object[0]);
                return null;
            }
        }
    }

    @Override // android.os.AsyncTask
    public final void onPostExecute(Object obj) {
        Pair pair = (Pair) obj;
        super.onPostExecute(pair);
        CountDownLatch countDownLatch = this.f729b;
        if (pair != null) {
            try {
                C0087k.a aVar = (C0087k.a) this.f728a.d(C0087k.a.class, "device");
                if (aVar == null) {
                    this.c.k("Not collecting advertising ID because context.device is null.", new Object[0]);
                } else {
                    String str = (String) pair.first;
                    Boolean bool = (Boolean) pair.second;
                    if (bool.booleanValue() && !D5.g.g(str)) {
                        aVar.f(str, "advertisingId");
                    }
                    aVar.f(bool, "adTrackingEnabled");
                }
            } catch (Throwable th) {
                countDownLatch.countDown();
                throw th;
            }
        }
        countDownLatch.countDown();
    }
}
