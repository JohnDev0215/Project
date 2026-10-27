package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.auth.device.dependency.PandaServiceAccessor;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class j2 {
    public static Set<String> a(Context context) {
        y5 y5Var = new y5("AuthoritySignature");
        k2 k2Var = new k2(context, q2.a(context, context.getPackageName()).iterator().next());
        l2 l2Var = new l2(context, new PandaServiceAccessor(context));
        HashSet hashSet = new HashSet();
        int i = 0;
        try {
            PandaServiceAccessor.a aVarA = l2Var.b.a(k2Var, y5Var);
            JSONArray jSONArray = aVarA.b.getJSONArray("authoritySignatures");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                hashSet.add(jSONArray.getString(i2));
            }
            new f8(l2Var.f416a, "authority.signature.expiry.store", 0).a("authority.signature.expiry.key", aVarA.a().getTime());
        } catch (PandaServiceAccessor.PandaServiceException e) {
            Log.e(ga.a(l2.c), "service exception when calling panda", e);
        } catch (IOException e2) {
            Log.e(ga.a(l2.c), "i/o exception when calling panda", e2);
        } catch (JSONException e3) {
            Log.e(ga.a(l2.c), "Json exception when calling panda", e3);
        }
        f8 f8Var = new f8(context, "bootstrap.sso.authority.signature.store", 0);
        f8Var.a();
        f8Var.a("bootstrap.sso.authority.signature.array.size", hashSet.size());
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            f8Var.a("bootstrap.sso.authority.signature.key." + i, (String) it.next());
            i++;
        }
        return hashSet;
    }
}
