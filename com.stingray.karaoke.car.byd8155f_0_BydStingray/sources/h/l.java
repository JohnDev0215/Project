package h;

import android.content.Intent;
import android.content.IntentSender;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import h8.AbstractC1386c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import k.C1581a;
import k.C1584d;
import k.C1588h;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f15104a = new LinkedHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f15105b = new LinkedHashMap();
    public final LinkedHashMap c = new LinkedHashMap();
    public final ArrayList d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final transient LinkedHashMap f15106e = new LinkedHashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LinkedHashMap f15107f = new LinkedHashMap();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Bundle f15108g = new Bundle();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ n f15109h;

    public l(n nVar) {
        this.f15109h = nVar;
    }

    public final boolean a(int i7, int i10, Intent intent) {
        String str = (String) this.f15104a.get(Integer.valueOf(i7));
        if (str == null) {
            return false;
        }
        C1584d c1584d = (C1584d) this.f15106e.get(str);
        if ((c1584d != null ? c1584d.f17603a : null) != null) {
            ArrayList arrayList = this.d;
            if (arrayList.contains(str)) {
                c1584d.f17603a.l(c1584d.f17604b.F(intent, i10));
                arrayList.remove(str);
                return true;
            }
        }
        this.f15107f.remove(str);
        this.f15108g.putParcelable(str, new C1581a(intent, i10));
        return true;
    }

    public final void b(int i7, Y4.e eVar) {
        n nVar = this.f15109h;
        Bundle bundleExtra = null;
        e7.f fVar = AbstractC1386c.c(nVar, BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON) == 0 ? new e7.f(Boolean.TRUE, 27) : null;
        if (fVar != null) {
            new Handler(Looper.getMainLooper()).post(new k(this, i7, fVar, 0));
            return;
        }
        Intent intentPutExtra = new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", new String[]{BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON});
        A7.m.e(intentPutExtra, "Intent(ACTION_REQUEST_PE…EXTRA_PERMISSIONS, input)");
        if (intentPutExtra.getExtras() != null) {
            Bundle extras = intentPutExtra.getExtras();
            A7.m.c(extras);
            if (extras.getClassLoader() == null) {
                intentPutExtra.setExtrasClassLoader(nVar.getClassLoader());
            }
        }
        if (intentPutExtra.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) {
            bundleExtra = intentPutExtra.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
            intentPutExtra.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
        }
        Bundle bundle = bundleExtra;
        if (!"androidx.activity.result.contract.action.REQUEST_PERMISSIONS".equals(intentPutExtra.getAction())) {
            if (!"androidx.activity.result.contract.action.INTENT_SENDER_REQUEST".equals(intentPutExtra.getAction())) {
                nVar.startActivityForResult(intentPutExtra, i7, bundle);
                return;
            }
            C1588h c1588h = (C1588h) intentPutExtra.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
            try {
                A7.m.c(c1588h);
                nVar.startIntentSenderForResult(c1588h.l, i7, c1588h.f17610m, c1588h.f17611n, c1588h.o, 0, bundle);
                return;
            } catch (IntentSender.SendIntentException e10) {
                new Handler(Looper.getMainLooper()).post(new k(this, i7, e10, 1));
                return;
            }
        }
        String[] stringArrayExtra = intentPutExtra.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
        if (stringArrayExtra == null) {
            stringArrayExtra = new String[0];
        }
        HashSet hashSet = new HashSet();
        for (int i10 = 0; i10 < stringArrayExtra.length; i10++) {
            if (TextUtils.isEmpty(stringArrayExtra[i10])) {
                throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder("Permission request for permissions "), Arrays.toString(stringArrayExtra), " must not contain null or empty values"));
            }
            if (Build.VERSION.SDK_INT < 33 && TextUtils.equals(stringArrayExtra[i10], "android.permission.POST_NOTIFICATIONS")) {
                hashSet.add(Integer.valueOf(i10));
            }
        }
        int size = hashSet.size();
        String[] strArr = size > 0 ? new String[stringArrayExtra.length - size] : stringArrayExtra;
        if (size > 0) {
            if (size == stringArrayExtra.length) {
                return;
            }
            int i11 = 0;
            for (int i12 = 0; i12 < stringArrayExtra.length; i12++) {
                if (!hashSet.contains(Integer.valueOf(i12))) {
                    strArr[i11] = stringArrayExtra[i12];
                    i11++;
                }
            }
        }
        nVar.requestPermissions(stringArrayExtra, i7);
    }
}
