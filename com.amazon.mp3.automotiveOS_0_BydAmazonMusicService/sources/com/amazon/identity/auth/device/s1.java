package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.attributes.CORPFMResponse;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class s1 {
    public static final long g;
    public static final long h;
    public static final String i;
    public static s1 j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f535a;
    public final u7 b;
    public final q1 c;
    public final Map<String, a> d;
    public final w5 e;
    public final Random f;

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final CountDownLatch f536a = new CountDownLatch(1);
        public CORPFMResponse b;
    }

    static {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        g = wa.a(1L, timeUnit);
        h = wa.b(1L, timeUnit);
        i = s1.class.getName();
    }

    public s1(Context context) {
        t5 t5VarA = t5.a(context);
        this.f535a = t5VarA;
        this.b = t5VarA.a();
        this.c = new q1(t5VarA);
        this.d = new HashMap();
        this.e = new w5();
        this.f = new Random();
    }

    public static synchronized s1 a(Context context) {
        if (j == null || xa.a()) {
            j = new s1(context.getApplicationContext());
        }
        return j;
    }

    public final String b(String str) {
        try {
            return Settings.Secure.getString(this.f535a.getContentResolver(), str);
        } catch (Exception e) {
            Log.w(ga.a(i), "Error calling Secure Settings for resource " + str, e);
            return null;
        }
    }

    public CORPFMResponse a(String str, y5 y5Var) throws AuthenticatedURLConnection.AccountNeedsRecoveryException {
        a aVar;
        boolean z;
        synchronized (this.d) {
            aVar = this.d.get(str);
            if (aVar == null) {
                aVar = new a();
                this.d.put(str, aVar);
                z = true;
            } else {
                z = false;
            }
        }
        CORPFMResponse cORPFMResponseA = null;
        if (z) {
            Log.i(ga.a(i), "No outstanding request to fetch cor/pfm. Calling DCAS.");
            try {
                cORPFMResponseA = new p1(this.f535a, str, y5Var).a();
                a(str, cORPFMResponseA);
                aVar.b = cORPFMResponseA;
                aVar.f536a.countDown();
                synchronized (this.d) {
                    this.d.remove(str);
                }
            } catch (Throwable th) {
                aVar.b = cORPFMResponseA;
                aVar.f536a.countDown();
                synchronized (this.d) {
                    this.d.remove(str);
                    throw th;
                }
            }
        } else {
            String str2 = i;
            ga.a(str2);
            try {
                if (aVar.f536a.await(5L, TimeUnit.SECONDS)) {
                    cORPFMResponseA = aVar.b;
                } else {
                    Log.e(ga.a(str2), "Timed out waiting for cor/pfm response");
                }
            } catch (InterruptedException e) {
                Log.e(ga.a(i), "Interrupted waiting for cor/pfm response", e);
            }
        }
        return cORPFMResponseA;
    }

    public final void a(String str, CORPFMResponse cORPFMResponse) {
        if (cORPFMResponse == null) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        boolean zA = a(str, cORPFMResponse, linkedHashMap);
        if (str != null) {
            u7 u7Var = this.b;
            u7Var.getClass();
            u7Var.a(new o7(str, linkedHashMap, null, null));
        }
        if (zA) {
            String str2 = i;
            Log.i(ga.a(str2), "COR/PFM value has changed. Sending notifications.");
            x0 x0VarA = y0.a(this.f535a);
            ga.a(str2);
            Intent intentA = e.a(CustomerAttributeStore.COR_PFM_CHANGED_INTENT_ACTION);
            intentA.putExtra(CustomerAttributeStore.KEY_DIRECTED_ID, str);
            x0VarA.a(str, intentA, AccountConstants.PERMISSION_MANAGE_COR_PFM);
            return;
        }
        Log.i(ga.a(i), "COR/PFM values are not different. Not firing the changed broadcast");
    }

    public final boolean a(String str, CORPFMResponse cORPFMResponse, Map<String, String> map) {
        String str2 = i;
        Object[] objArr = new Object[3];
        objArr[0] = cORPFMResponse.f156a;
        boolean z = true;
        objArr[1] = cORPFMResponse.b;
        CORPFMResponse.ComputationConfidenceValue computationConfidenceValue = cORPFMResponse.d;
        objArr[2] = computationConfidenceValue != null ? computationConfidenceValue.toString() : null;
        Log.i(ga.a(str2), String.format("Trying to save COR/PFM response : %nCoR: %s %nPFM:%s %n Computation Confidence Value: %s", objArr));
        int iOrdinal = cORPFMResponse.d.ordinal();
        if (iOrdinal == 0) {
            Log.i(ga.a(str2), "Saving user backed COR/PFM");
            if (str != null) {
                if (!this.b.a(str)) {
                    Log.w(ga.a(str2), "Could not save COR/PFM values because the given account does not exist");
                } else if (cORPFMResponse.equals(a(str))) {
                    Log.i(ga.a(str2), "User COR PFM has not changed.");
                }
                z = false;
            }
            map.put(AccountConstants.KEY_COR, cORPFMResponse.f156a);
            map.put(AccountConstants.KEY_PFM, cORPFMResponse.b);
        } else if (iOrdinal == 1) {
            z = false;
        } else if (iOrdinal == 2) {
            Log.i(ga.a(str2), "Saving device defaults COR/PFM");
            q1 q1Var = this.c;
            CORPFMResponse cORPFMResponse2 = new CORPFMResponse(q1Var.a(), q1Var.b(), CORPFMResponse.ComputationConfidenceValue.DEVICE_BASED_GUESS, (Long) (-1L));
            f8 f8VarC = this.c.c();
            f8VarC.a("default.cor", cORPFMResponse.f156a);
            f8VarC.a("default.pfm", cORPFMResponse.b);
            if (cORPFMResponse.equals(cORPFMResponse2)) {
                Log.i(ga.a(str2), "Default COR/PFM has not changed.");
                z = false;
            }
        } else {
            throw new IllegalStateException(String.format("Cor Pfm value type %s is not supported", cORPFMResponse.d.name()));
        }
        Long l = cORPFMResponse.c;
        if (l != null) {
            map.put("last_updated_cor_pfm", String.valueOf(l));
        }
        map.put("is_cor_pfm_set", "true");
        if (TextUtils.isEmpty(cORPFMResponse.f156a)) {
            Log.i(ga.a(str2), "COR is empty.");
            yd.b("CorIsEmpty", new String[0]);
        } else {
            Log.i(ga.a(str2), "COR is not empty.");
        }
        if (TextUtils.isEmpty(cORPFMResponse.b)) {
            Log.i(ga.a(str2), "PFM is empty.");
            yd.b("PfmIsEmpty", new String[0]);
        } else {
            Log.i(ga.a(str2), "PFM is not empty.");
        }
        return z;
    }

    public final CORPFMResponse a(String str) {
        String strD = this.b.d(str, AccountConstants.KEY_COR);
        String strD2 = this.b.d(str, AccountConstants.KEY_PFM);
        Log.i(ga.a(i), String.format("COR is empty: %b, PFM is empty: %b", Boolean.valueOf(TextUtils.isEmpty(strD)), Boolean.valueOf(TextUtils.isEmpty(strD2))));
        return new CORPFMResponse(strD, strD2, CORPFMResponse.ComputationConfidenceValue.CUSTOMER_PROVIDED, e.k(this.b.d(str, "last_updated_cor_pfm")));
    }
}
