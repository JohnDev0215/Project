package defpackage;

import android.content.Context;
import android.os.Parcel;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.internal.zzbh;
import com.google.android.gms.location.internal.zzbj;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: Gi, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0116Gi {
    public final InterfaceC0241Ni a;
    public boolean b = false;
    public final Map c = new HashMap();
    public final Map d = new HashMap();
    public final Map e = new HashMap();

    public C0116Gi(Context context, InterfaceC0241Ni interfaceC0241Ni) {
        this.a = interfaceC0241Ni;
    }

    public final void a() {
        synchronized (this.c) {
            for (BinderC0170Ji binderC0170Ji : this.c.values()) {
                if (binderC0170Ji != null) {
                    InterfaceC0080Ei interfaceC0080Ei = (InterfaceC0080Ei) ((C0277Pi) this.a).b();
                    zzbj zzbjVarB = zzbj.b(binderC0170Ji, null);
                    C0062Di c0062Di = (C0062Di) interfaceC0080Ei;
                    Parcel parcelF = c0062Di.f();
                    AbstractC0383Vg.d(parcelF, zzbjVarB);
                    c0062Di.d(59, parcelF);
                }
            }
            this.c.clear();
        }
        synchronized (this.e) {
            Iterator it = this.e.values().iterator();
            while (it.hasNext()) {
                if (((AbstractBinderC0098Fi) it.next()) != null) {
                    throw null;
                }
            }
            this.e.clear();
        }
        synchronized (this.d) {
            Iterator it2 = this.d.values().iterator();
            while (it2.hasNext()) {
                if (((AbstractBinderC0134Hi) it2.next()) != null) {
                    throw null;
                }
            }
            this.d.clear();
        }
    }

    public final void b(LocationRequest locationRequest, C0835gd c0835gd, InterfaceC0008Ai interfaceC0008Ai) {
        BinderC0170Ji binderC0170Ji;
        ((C0277Pi) this.a).a();
        C0668dd c0668dd = c0835gd.c;
        if (c0668dd == null) {
            binderC0170Ji = null;
        } else {
            synchronized (this.c) {
                binderC0170Ji = (BinderC0170Ji) this.c.get(c0668dd);
                if (binderC0170Ji == null) {
                    binderC0170Ji = new BinderC0170Ji(c0835gd);
                }
                this.c.put(c0668dd, binderC0170Ji);
            }
        }
        if (binderC0170Ji == null) {
            return;
        }
        InterfaceC0080Ei interfaceC0080Ei = (InterfaceC0080Ei) ((C0277Pi) this.a).b();
        zzbj zzbjVar = new zzbj(1, new zzbh(locationRequest, zzbh.B, null, false, false, false, null, false, false), binderC0170Ji.asBinder(), null, null, interfaceC0008Ai != null ? interfaceC0008Ai.asBinder() : null);
        C0062Di c0062Di = (C0062Di) interfaceC0080Ei;
        Parcel parcelF = c0062Di.f();
        AbstractC0383Vg.d(parcelF, zzbjVar);
        c0062Di.d(59, parcelF);
    }

    public final void c() {
        if (this.b) {
            ((C0277Pi) this.a).a();
            C0062Di c0062Di = (C0062Di) ((InterfaceC0080Ei) ((C0277Pi) this.a).b());
            Parcel parcelF = c0062Di.f();
            AbstractC0383Vg.a(parcelF, false);
            c0062Di.d(12, parcelF);
            this.b = false;
        }
    }
}
