package OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import com.loostone.libtuning.channel.byd.upgrade.UpdateHelper;
import com.loostone.libtuning.process.dongle.LocalDongleManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Result;
import kotlin.d;
import kotlin.g;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.j;
import kotlin.p.c;
import kotlin.reflect.i;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0O0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ i<Object>[] f506a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f507b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LocalDongleManager f508c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final kotlin.a f509d;
    public final kotlin.a e;
    public OooO00o f;
    public final kotlin.a g;
    public final c h;

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0O0$OooO0O0, reason: collision with other inner class name */
    public static final class C0019OooO0O0 extends Lambda implements kotlin.jvm.b.a<Map<String, byte[]>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final C0019OooO0O0 f510OooO00o = new C0019OooO0O0();

        public C0019OooO0O0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final Map<String, byte[]> invoke() {
            return new LinkedHashMap();
        }
    }

    public static final class OooO0OO extends Lambda implements kotlin.jvm.b.a<OooO00o.OooO00o.OooO0OO.OooO0O0.OooO0o> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0OO f511OooO00o = new OooO0OO();

        public OooO0OO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o.OooO00o.OooO0OO.OooO0O0.OooO0o invoke() {
            return OooO00o.OooO00o.OooO0OO.OooO0O0.OooO0o.f54a.a();
        }
    }

    public static final class OooO0o extends Lambda implements kotlin.jvm.b.a<ArrayList<OooO00o>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0o f512OooO00o = new OooO0o();

        public OooO0o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final ArrayList<OooO00o> invoke() {
            return new ArrayList<>();
        }
    }

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    public static final class b extends kotlin.p.b<Boolean> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Object f513b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ OooO0O0 f514c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Object obj, Object obj2, OooO0O0 oooO0O0) {
            super(obj2);
            this.f513b = obj;
            this.f514c = oooO0O0;
        }

        @Override // kotlin.p.b
        public void c(i<?> iVar, Boolean bool, Boolean bool2) {
            Object objM23constructorimpl;
            h.d(iVar, "property");
            boolean zBooleanValue = bool2.booleanValue();
            boolean zBooleanValue2 = bool.booleanValue();
            OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
            bVar.b("CachedDongleDeviceManager", "screenLiftRiseStatus: old=" + zBooleanValue2 + " -> new=" + zBooleanValue);
            try {
                OooO00o oooO00oF = this.f514c.f();
                Integer numValueOf = null;
                if (oooO00oF != null) {
                    OooO0O0 oooO0O0 = this.f514c;
                    if (!(oooO00oF.B() && (this.f514c.k().isEmpty() ^ true))) {
                        oooO0O0 = null;
                    }
                    if (oooO0O0 != null) {
                        OooO0O0 oooO0O02 = this.f514c;
                        if (!(zBooleanValue2 != zBooleanValue)) {
                            oooO0O02 = null;
                        }
                        if (oooO0O02 != null) {
                            UpdateHelper updateHelper = new UpdateHelper();
                            updateHelper.init(oooO00oF.w());
                            if (zBooleanValue) {
                                byte[] bArr = (byte[]) this.f514c.k().get("HardEffectTvRise");
                                if (bArr != null) {
                                    String string = Arrays.toString(bArr);
                                    h.c(string, "java.util.Arrays.toString(this)");
                                    bVar.b("CachedDongleDeviceManager", h.i("switch hardEffectTvRise = ", string));
                                    updateHelper.sendHardEffect(bArr);
                                }
                                byte[] bArr2 = (byte[]) this.f514c.k().get("HardEffectVolumeTvRise");
                                if (bArr2 != null) {
                                    String string2 = Arrays.toString(bArr2);
                                    h.c(string2, "java.util.Arrays.toString(this)");
                                    bVar.b("CachedDongleDeviceManager", h.i("switch hardEffectVolumeTvRise = ", string2));
                                    updateHelper.sendHardEffect(bArr2);
                                }
                            } else {
                                byte[] bArr3 = (byte[]) this.f514c.k().get("HardEffectNormal");
                                if (bArr3 != null) {
                                    String string3 = Arrays.toString(bArr3);
                                    h.c(string3, "java.util.Arrays.toString(this)");
                                    bVar.b("CachedDongleDeviceManager", h.i("switch hardEffect = ", string3));
                                    updateHelper.sendHardEffect(bArr3);
                                }
                                byte[] bArr4 = (byte[]) this.f514c.k().get("HardEffectVolumeNormal");
                                if (bArr4 != null) {
                                    String string4 = Arrays.toString(bArr4);
                                    h.c(string4, "java.util.Arrays.toString(this)");
                                    bVar.b("CachedDongleDeviceManager", h.i("switch hardEffectVolume = ", string4));
                                    updateHelper.sendHardEffect(bArr4);
                                }
                            }
                            numValueOf = Integer.valueOf(updateHelper.release());
                        }
                    }
                }
                objM23constructorimpl = Result.m23constructorimpl(numValueOf);
            } catch (Throwable th) {
                objM23constructorimpl = Result.m23constructorimpl(g.a(th));
            }
            Throwable thM26exceptionOrNullimpl = Result.m26exceptionOrNullimpl(objM23constructorimpl);
            if (thM26exceptionOrNullimpl != null) {
                OooO00o.OooO00o.OooO0O0.b.f41a.e("CachedDongleDeviceManager", h.i("switch config for screen lift rise error :: ", thM26exceptionOrNullimpl));
                thM26exceptionOrNullimpl.printStackTrace();
            }
        }
    }

    static {
        i<Object>[] iVarArr = new i[4];
        iVarArr[3] = j.c(new MutablePropertyReference1Impl(j.a(OooO0O0.class), "screenLiftRiseStatus", "getScreenLiftRiseStatus()Z"));
        f506a = iVarArr;
        new a(null);
    }

    public OooO0O0(Context context, LocalDongleManager localDongleManager) {
        h.d(context, "mContext");
        h.d(localDongleManager, "localDongleManager");
        this.f507b = context;
        this.f508c = localDongleManager;
        this.f509d = d.b(OooO0o.f512OooO00o);
        this.e = d.b(OooO0OO.f511OooO00o);
        this.g = d.b(C0019OooO0O0.f510OooO00o);
        kotlin.p.a aVar = kotlin.p.a.f4085a;
        Boolean bool = Boolean.FALSE;
        this.h = new b(bool, bool, this);
    }

    public final int a() {
        return l().size() > 0 ? 2 : 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x030d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:239:0x030b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0257 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0255 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0252  */
    /* JADX WARN: Type inference failed for: r10v30 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v19 */
    /* JADX WARN: Type inference failed for: r12v20, types: [byte[], java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v21 */
    /* JADX WARN: Type inference failed for: r12v24 */
    /* JADX WARN: Type inference failed for: r12v25 */
    /* JADX WARN: Type inference failed for: r12v26 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r4v9, types: [OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO.OooO00o] */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v27 */
    /* JADX WARN: Type inference failed for: r6v28 */
    /* JADX WARN: Type inference failed for: r6v29 */
    /* JADX WARN: Type inference failed for: r6v32 */
    /* JADX WARN: Type inference failed for: r6v33 */
    /* JADX WARN: Type inference failed for: r6v36 */
    /* JADX WARN: Type inference failed for: r6v37 */
    /* JADX WARN: Type inference failed for: r6v39 */
    /* JADX WARN: Type inference failed for: r6v40 */
    /* JADX WARN: Type inference failed for: r6v41 */
    /* JADX WARN: Type inference failed for: r6v43 */
    /* JADX WARN: Type inference failed for: r6v44 */
    /* JADX WARN: Type inference failed for: r6v45 */
    /* JADX WARN: Type inference failed for: r6v46, types: [byte[], java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v57 */
    /* JADX WARN: Type inference failed for: r6v60 */
    /* JADX WARN: Type inference failed for: r6v65 */
    /* JADX WARN: Type inference failed for: r6v66 */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v34 */
    /* JADX WARN: Type inference failed for: r7v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r8v19, types: [com.loostone.libtuning.channel.byd.upgrade.UpdateHelper] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o b(android.hardware.usb.UsbDevice r18) {
        /*
            Method dump skipped, instruction units count: 1318
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0O0.b(android.hardware.usb.UsbDevice):OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o");
    }

    public final void d(OooO00o oooO00o) {
        this.f = oooO00o;
    }

    public final void e(boolean z) {
        this.h.a(this, f506a[3], Boolean.valueOf(z));
    }

    public final OooO00o f() {
        return this.f;
    }

    public final OooO00o g(UsbDevice usbDevice) {
        if (usbDevice == null) {
            return null;
        }
        for (OooO00o oooO00o : l()) {
            if (oooO00o.w() != null && h.a(oooO00o.w(), usbDevice)) {
                return oooO00o;
            }
        }
        return null;
    }

    public final void h(boolean z) {
        e(z);
    }

    public final List<OooO00o> i() {
        return new ArrayList(l());
    }

    public final void j(UsbDevice usbDevice) {
        if (usbDevice != null && OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a.j(usbDevice)) {
            OooO00o oooO00o = null;
            for (OooO00o oooO00o2 : l()) {
                if (oooO00o2.w() != null && h.a(oooO00o2.w(), usbDevice)) {
                    oooO00o = oooO00o2;
                }
            }
            if (oooO00o == null) {
                return;
            }
            l().remove(oooO00o);
            this.f508c.getMEventManager().v(oooO00o);
            d(null);
        }
    }

    public final Map<String, byte[]> k() {
        return (Map) this.g.getValue();
    }

    public final List<OooO00o> l() {
        return (List) this.f509d.getValue();
    }

    public final OooO00o.OooO00o.OooO0OO.OooO0O0.OooO0o m() {
        return (OooO00o.OooO00o.OooO0OO.OooO0O0.OooO0o) this.e.getValue();
    }

    public final boolean n() {
        return ((Boolean) this.h.b(this, f506a[3])).booleanValue();
    }

    public final void o() {
        this.f = null;
        e(false);
        k().clear();
        l().clear();
    }
}
