package org.chromium.components.policy;

import J.N;
import android.content.SharedPreferences;
import android.util.Pair;
import defpackage.ku2;
import defpackage.xz;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.policy.a;

/* JADX INFO: loaded from: classes3.dex */
public class PolicyCacheUpdater {
    public static final List<Pair<String, a.EnumC0141a>> a;

    static {
        a.EnumC0141a enumC0141a = a.EnumC0141a.Integer;
        a.EnumC0141a enumC0141a2 = a.EnumC0141a.List;
        a = Arrays.asList(Pair.create("BrowserSignin", enumC0141a), Pair.create("CloudManagementEnrollmentToken", a.EnumC0141a.String), Pair.create("ChromeVariations", enumC0141a), Pair.create("URLAllowlist", enumC0141a2), Pair.create("URLBlocklist", enumC0141a2), Pair.create("FirstPartySetsEnabled", a.EnumC0141a.Boolean), Pair.create("FirstPartySetsOverrides", a.EnumC0141a.Dict));
    }

    @CalledByNative
    public static void cachePolicies(PolicyMap policyMap) {
        String strMKY_klU2;
        if (a.c == null) {
            a.c = new a();
        }
        a aVar = a.c;
        aVar.b.getClass();
        ku2 ku2VarT = ku2.t();
        try {
            SharedPreferences.Editor editorEdit = xz.a.getSharedPreferences("Components.Policy", 0).edit();
            ku2VarT.close();
            editorEdit.clear();
            Iterator<Pair<String, a.EnumC0141a>> it = a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    editorEdit.apply();
                    aVar.a = null;
                    return;
                }
                Pair<String, a.EnumC0141a> next = it.next();
                String str = (String) next.first;
                int iOrdinal = ((a.EnumC0141a) next.second).ordinal();
                if (iOrdinal == 0) {
                    long j = policyMap.a;
                    Integer numValueOf = N.MImr1kgV(j, policyMap, str) ? Integer.valueOf(N.MNIDvfzY(j, policyMap, str)) : null;
                    if (numValueOf != null) {
                        editorEdit.putInt(str, numValueOf.intValue());
                    }
                } else if (iOrdinal == 1) {
                    long j2 = policyMap.a;
                    Boolean boolValueOf = N.MImr1kgV(j2, policyMap, str) ? Boolean.valueOf(N.MG3FgY5r(j2, policyMap, str)) : null;
                    if (boolValueOf != null) {
                        editorEdit.putBoolean(str, boolValueOf.booleanValue());
                    }
                } else if (iOrdinal == 2) {
                    String strMEWWQoKk = N.MEWWQoKk(policyMap.a, policyMap, str);
                    if (strMEWWQoKk != null) {
                        editorEdit.putString(str, strMEWWQoKk);
                    }
                } else if (iOrdinal == 3) {
                    String strMQRev50_ = N.MQRev50_(policyMap.a, policyMap, str);
                    if (strMQRev50_ != null) {
                        editorEdit.putString(str, strMQRev50_);
                    }
                } else if (iOrdinal == 4 && (strMKY_klU2 = N.MKY_klU2(policyMap.a, policyMap, str)) != null) {
                    editorEdit.putString(str, strMKY_klU2);
                }
            }
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }
}
