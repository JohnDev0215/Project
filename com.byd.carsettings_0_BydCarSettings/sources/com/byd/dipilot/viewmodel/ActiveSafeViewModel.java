package com.byd.dipilot.viewmodel;

import android.app.Application;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.byd.base.hilt.Router;
import com.byd.base.hilt.impl.BodyDeviceImpl;
import com.byd.base.hilt.inter.IStudyConfig;
import com.byd.dipilot.bpresenter.R$string;
import com.byd.dipilot.model.ActiveSafeModel;
import com.byd.dipilot.model.ActiveSafeStateModel;
import com.byd.dipilot.model.DriverAssistModel;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ActiveSafeViewModel extends BaseAdasViewModel<ActiveSafeModel, b.b.k.b.a> {
    public MutableLiveData<Byte> A;
    public MutableLiveData<Byte> B;
    public MutableLiveData<Byte> D;
    public MutableLiveData<Byte> E;
    public boolean F;
    public ContentObserver G;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f3624f;
    public ActiveSafeModel g;
    public DriverAssistModel h;
    public ActiveSafeModel i;
    public DriverAssistModel j;
    public DriverAssistModel k;
    public DriverAssistModel l;
    public ActiveSafeStateModel m;
    public DriverAssistModel n;
    public DriverAssistModel o;
    public DriverAssistModel p;
    public DriverAssistModel q;
    public DriverAssistModel r;
    public ActiveSafeStateModel s;
    public DriverAssistModel t;
    public DriverAssistModel u;
    public ActiveSafeModel v;
    public ActiveSafeModel w;
    public ActiveSafeModel x;
    public DriverAssistModel y;
    public ActiveSafeModel z;

    public class a extends ContentObserver {
        public a(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            ActiveSafeViewModel.this.q0();
        }
    }

    public ActiveSafeViewModel(@NonNull Application application) {
        super(application);
        this.f3624f = "persist.mobile.isa.switch";
        this.A = new MutableLiveData<>((byte) 3);
    }

    public ActiveSafeModel A() {
        return this.x;
    }

    public ActiveSafeModel B() {
        return this.i;
    }

    public MutableLiveData<Byte> C() {
        MutableLiveData<Byte> mutableLiveData = this.D;
        return mutableLiveData == null ? new MutableLiveData<>((byte) 4) : mutableLiveData;
    }

    public DriverAssistModel D() {
        return this.u;
    }

    public MutableLiveData<Byte> E() {
        return this.A;
    }

    public ActiveSafeModel F() {
        return this.z;
    }

    public DriverAssistModel G() {
        return this.r;
    }

    public DriverAssistModel H() {
        return this.l;
    }

    public DriverAssistModel I() {
        return this.q;
    }

    public DriverAssistModel J() {
        return this.n;
    }

    public DriverAssistModel K() {
        return this.t;
    }

    public ActiveSafeStateModel L() {
        return this.s;
    }

    public MutableLiveData<Byte> M() {
        MutableLiveData<Byte> mutableLiveData = this.B;
        return mutableLiveData == null ? new MutableLiveData<>((byte) 3) : mutableLiveData;
    }

    public boolean N() {
        return this.F;
    }

    public boolean O() {
        int tag;
        ActiveSafeModel activeSafeModel = this.i;
        if (activeSafeModel == null || (tag = activeSafeModel.getTag()) == 0) {
            return false;
        }
        return tag == 1 || tag % 2 == 0;
    }

    public boolean P(int i) {
        IStudyConfig iStudyConfigF = Router.b().f();
        boolean zC = iStudyConfigF.c(31);
        BydLogHelper.logD("needDoubleConfirm, need " + zC);
        if (zC) {
            return true;
        }
        BYDAutoStudy bYDAutoStudyF = iStudyConfigF.f();
        if (bYDAutoStudyF == null) {
            BydLogHelper.logW(this.f3629a, "needDoubleConfirm, study null");
            return false;
        }
        try {
            if (i == 0) {
                return bYDAutoStudyF.getELKADoubleConfirm() == 1;
            }
            if (i == 11) {
                return bYDAutoStudyF.getLDADoubleConfirm() == 1;
            }
            if (i == 13) {
                return bYDAutoStudyF.getAEBDoubleConfirm() == 1;
            }
            if (i == 15 && bYDAutoStudyF.getFCWDoubleConfirm() == 1) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            BydLogHelper.logE(this.f3629a, "needDoubleConfirm e = " + th);
            return false;
        }
    }

    public void Q(DriverAssistModel driverAssistModel) {
        ActiveSafeModel activeSafeModel = this.i;
        if (activeSafeModel != null) {
            driverAssistModel.normal = activeSafeModel.normal;
        }
        this.j = driverAssistModel;
    }

    public void R(DriverAssistModel driverAssistModel) {
        driverAssistModel.normal = Router.b().d().h(13);
        this.k = driverAssistModel;
    }

    public void S(ActiveSafeModel activeSafeModel) {
        this.g = activeSafeModel;
    }

    public void T(DriverAssistModel driverAssistModel) {
        driverAssistModel.normal = Router.b().d().h(26);
        this.y = driverAssistModel;
    }

    public void U(DriverAssistModel driverAssistModel) {
        this.h = driverAssistModel;
    }

    public void V(boolean z) {
        this.F = z;
    }

    public void W(ActiveSafeStateModel activeSafeStateModel) {
        activeSafeStateModel.normal = Router.b().d().h(15);
        this.m = activeSafeStateModel;
    }

    public void X(DriverAssistModel driverAssistModel) {
        driverAssistModel.normal = Router.b().d().h(17);
        this.p = driverAssistModel;
    }

    public void Y(DriverAssistModel driverAssistModel) {
        driverAssistModel.normal = Router.b().d().h(16);
        this.o = driverAssistModel;
    }

    public void Z(byte b2) {
        MutableLiveData<Byte> mutableLiveData = this.E;
        if (mutableLiveData == null) {
            this.E = new MutableLiveData<>(Byte.valueOf(b2));
        } else {
            mutableLiveData.setValue(Byte.valueOf(b2));
        }
    }

    public void a0(ActiveSafeModel activeSafeModel) {
        activeSafeModel.normal = Router.b().d().h(22);
        this.w = activeSafeModel;
    }

    public void b0(ActiveSafeModel activeSafeModel) {
        activeSafeModel.normal = Router.b().d().h(24);
        this.v = activeSafeModel;
    }

    public void c0(ActiveSafeModel activeSafeModel) {
        activeSafeModel.normal = Router.b().d().h(23);
        this.x = activeSafeModel;
    }

    @Override // com.byd.dipilot.viewmodel.BaseAdasViewModel
    public List<ActiveSafeModel> d() {
        return this.f3630b;
    }

    public void d0(int i, int i2, ActiveSafeModel activeSafeModel) {
        byte bC = Router.b().d().c();
        BydLogHelper.logD(this.f3629a, "setIntelligentStatus 2b4 1.0-1.2 = " + i + " 316 1.0-1.2 = " + i2 + " 2b4 1.5-1.7 = " + ((int) bC));
        int i3 = ((bC << 3) | i) == 63 ? i2 : i;
        boolean z = i3 >= 1 && i3 <= 3;
        boolean z2 = (i3 == 4 || i3 == 5) ? false : true;
        if (activeSafeModel == null) {
            a(new ActiveSafeModel(z, z2, R$string.dipilot_high_beam_title, i2, (byte) i));
        } else {
            if (i3 > 5) {
                return;
            }
            activeSafeModel.setAssistState(z);
            activeSafeModel.setEnable(z2);
            activeSafeModel.setAssistPicId(i2);
            activeSafeModel.pos = (byte) i;
        }
    }

    public void e0(ActiveSafeModel activeSafeModel, boolean z) {
        activeSafeModel.normal = z;
        this.i = activeSafeModel;
    }

    public void f0(boolean z) {
        DriverAssistModel driverAssistModel = this.j;
        if (driverAssistModel != null && driverAssistModel.normal != z) {
            driverAssistModel.setNormal(z);
        }
        ActiveSafeModel activeSafeModel = this.i;
        if (activeSafeModel == null || activeSafeModel.normal == z) {
            return;
        }
        activeSafeModel.setNormal(z);
    }

    public void g0(byte b2) {
        MutableLiveData<Byte> mutableLiveData = this.D;
        if (mutableLiveData == null) {
            this.D = new MutableLiveData<>(Byte.valueOf(b2));
        } else {
            mutableLiveData.setValue(Byte.valueOf(b2));
        }
    }

    @Override // com.byd.dipilot.viewmodel.BaseAdasViewModel
    public b.b.k.b.a h() {
        return this.f3631c;
    }

    public void h0(DriverAssistModel driverAssistModel) {
        driverAssistModel.normal = Router.b().d().h(21);
        this.u = driverAssistModel;
    }

    public void i0(byte b2) {
        this.A.postValue(Byte.valueOf(b2));
    }

    public void j0(ActiveSafeModel activeSafeModel) {
        activeSafeModel.normal = Router.b().d().h(27);
        this.z = activeSafeModel;
    }

    public void k0(DriverAssistModel driverAssistModel) {
        driverAssistModel.normal = Router.b().d().h(19);
        this.r = driverAssistModel;
    }

    public void l0(DriverAssistModel driverAssistModel) {
        driverAssistModel.normal = Router.b().d().h(14);
        this.l = driverAssistModel;
    }

    @Override // com.byd.dipilot.viewmodel.BaseAdasViewModel
    public void m(List<ActiveSafeModel> list) {
        this.f3630b = list;
    }

    public void m0(DriverAssistModel driverAssistModel) {
        driverAssistModel.normal = Router.b().d().h(18);
        this.q = driverAssistModel;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.dipilot.viewmodel.BaseAdasViewModel
    public void n(b.b.k.b.a aVar) {
        this.f3631c = aVar;
    }

    public void n0(DriverAssistModel driverAssistModel) {
        this.n = driverAssistModel;
    }

    public void o0(DriverAssistModel driverAssistModel) {
        this.t = driverAssistModel;
    }

    @Override // com.byd.dipilot.viewmodel.BaseAdasViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.D = null;
        this.E = null;
        this.t = null;
        this.n = null;
        ContentResolver contentResolver = getApplication().getContentResolver();
        ContentObserver contentObserver = this.G;
        if (contentObserver == null || contentResolver == null) {
            return;
        }
        contentResolver.unregisterContentObserver(contentObserver);
        this.G = null;
    }

    public DriverAssistModel p() {
        return this.j;
    }

    public final void p0(ActiveSafeStateModel activeSafeStateModel) {
        activeSafeStateModel.normal = Router.b().d().h(20);
        this.s = activeSafeStateModel;
    }

    public DriverAssistModel q() {
        return this.k;
    }

    public void q0() {
        ContentResolver contentResolver = getApplication().getContentResolver();
        if (contentResolver == null) {
            return;
        }
        boolean z = this.B == null;
        String string = Settings.Global.getString(contentResolver, "persist.mobile.isa.switch");
        byte b2 = TextUtils.isEmpty(string) ? (byte) 0 : Byte.parseByte(string);
        if (z) {
            this.B = new MutableLiveData<>(Byte.valueOf(b2));
        } else {
            this.B.setValue(Byte.valueOf(b2));
        }
        BydLogHelper.logD("SafeViewModel", "initTsrFlow", "value =" + ((int) b2));
        if (z) {
            this.G = new a(new Handler());
            contentResolver.registerContentObserver(Settings.Global.getUriFor("persist.mobile.isa.switch"), false, this.G);
        }
    }

    public ActiveSafeModel r() {
        return this.g;
    }

    public void r0() {
        MutableLiveData<Byte> mutableLiveData;
        ContentResolver contentResolver = getApplication().getContentResolver();
        if (contentResolver == null || (mutableLiveData = this.B) == null) {
            return;
        }
        Settings.Global.putInt(contentResolver, "persist.mobile.isa.switch", mutableLiveData.getValue().byteValue() == 0 ? 1 : 0);
    }

    public DriverAssistModel s() {
        return this.y;
    }

    public void s0(boolean z) {
        DriverAssistModel driverAssistModel = this.j;
        if (driverAssistModel == null) {
            return;
        }
        if (!z) {
            driverAssistModel.setEnable(false);
            return;
        }
        ActiveSafeModel activeSafeModel = this.i;
        if (activeSafeModel == null || activeSafeModel.getTag() <= 0) {
            return;
        }
        if (4 == this.i.getTag()) {
            this.j.setEnable(false);
            return;
        }
        int assistPicId = this.i.getAssistPicId();
        if (assistPicId != 0) {
            if (assistPicId == 1) {
                this.j.setEnable(true);
                return;
            }
            if (assistPicId != 2) {
                if (assistPicId != 3) {
                    return;
                }
                if (N()) {
                    this.j.setEnable(true);
                    return;
                } else {
                    this.j.setEnable(false);
                    return;
                }
            }
        }
        this.j.setEnable(false);
    }

    public DriverAssistModel t() {
        return this.h;
    }

    public void t0(@NonNull DriverAssistModel driverAssistModel, int i, boolean z) {
        driverAssistModel.setBtnEnable(driverAssistModel.isDefaultChecked(i), z && 3 != i);
        driverAssistModel.pos = (byte) i;
    }

    public ActiveSafeStateModel u() {
        return this.m;
    }

    public DriverAssistModel u0(int i, DriverAssistModel driverAssistModel) {
        boolean z = i == 1;
        boolean z2 = i == 3;
        if (driverAssistModel == null) {
            return new ActiveSafeModel(z2, z, 0, 0, (byte) i);
        }
        if (z || z2) {
            driverAssistModel.setBtnEnable(z2, z);
        }
        return driverAssistModel;
    }

    public DriverAssistModel v() {
        return this.p;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.byd.dipilot.model.ActiveSafeModel v0(int r11, int r12, boolean r13, com.byd.dipilot.model.ActiveSafeModel r14) {
        /*
            r10 = this;
            r0 = 4
            r1 = 3
            r2 = 0
            r3 = 1
            if (r11 != r3) goto L17
            if (r12 == r3) goto L13
            boolean r4 = r10.N()
            if (r4 == 0) goto L11
            if (r12 != r1) goto L11
            goto L13
        L11:
            r1 = r2
            goto L14
        L13:
            r1 = r3
        L14:
            r5 = r1
            r9 = r2
            goto L40
        L17:
            r4 = 2
            if (r11 != r0) goto L1d
            if (r12 != r4) goto L11
            goto L13
        L1d:
            if (r12 == 0) goto L35
            if (r12 == r3) goto L25
            if (r12 == r4) goto L32
            if (r12 == r1) goto L28
        L25:
            r5 = r2
            r9 = r5
            goto L40
        L28:
            boolean r1 = r10.N()
            if (r1 == 0) goto L2f
            goto L30
        L2f:
            r4 = r3
        L30:
            byte r1 = (byte) r4
            goto L3e
        L32:
            r5 = r2
            r9 = r3
            goto L40
        L35:
            boolean r5 = r10.N()
            if (r5 == 0) goto L3c
            goto L3d
        L3c:
            r1 = r4
        L3d:
            byte r1 = (byte) r1
        L3e:
            r9 = r1
            r5 = r2
        L40:
            if (r14 != 0) goto L4c
            com.byd.dipilot.model.ActiveSafeModel r14 = new com.byd.dipilot.model.ActiveSafeModel
            r4 = r14
            r6 = r13
            r7 = r11
            r8 = r12
            r4.<init>(r5, r6, r7, r8, r9)
            return r14
        L4c:
            r14.setAssistPicId(r12)
            r14.setEnable(r13)
            if (r11 == r3) goto L5b
            if (r11 != r0) goto L57
            goto L5b
        L57:
            r14.setPos(r9)
            goto L5e
        L5b:
            r14.setAssistState(r5)
        L5e:
            r10.s0(r13)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.dipilot.viewmodel.ActiveSafeViewModel.v0(int, int, boolean, com.byd.dipilot.model.ActiveSafeModel):com.byd.dipilot.model.ActiveSafeModel");
    }

    public DriverAssistModel w() {
        return this.o;
    }

    public void w0(byte b2, byte b3, int i, ActiveSafeStateModel activeSafeStateModel) {
        boolean z;
        boolean z2;
        boolean z3 = activeSafeStateModel != null && activeSafeStateModel.getAssistState();
        boolean z4 = activeSafeStateModel != null && activeSafeStateModel.getEnable();
        BodyDeviceImpl bodyDeviceImplE = Router.b().e();
        if (bodyDeviceImplE == null) {
            BydLogHelper.logW(this.f3629a, "updateTrafficSignRecognizeState deviceImpl is null!");
        }
        if (bodyDeviceImplE == null || !bodyDeviceImplE.j(141)) {
            byte b4 = (bodyDeviceImplE == null || !bodyDeviceImplE.j(137)) ? (byte) i : b3;
            if (b4 >= 1 && b4 < 4) {
                z3 = true;
            } else if (b4 == 0) {
                z3 = false;
            }
            boolean z5 = b4 != 4;
            BydLogHelper.logD(this.f3629a, "updateTrafficSignRecognizeState 2b4 or 316!");
            z = z5;
            z2 = z3;
        } else {
            if (b2 <= 6) {
                if (b2 >= 1 && b2 <= 5) {
                    z3 = true;
                } else if (b2 == 0) {
                    z3 = false;
                }
                z4 = b2 != 6;
            }
            BydLogHelper.logD(this.f3629a, "updateTrafficSignRecognizeState 2d5!");
            z2 = z3;
            z = z4;
        }
        if (activeSafeStateModel == null) {
            p0(new ActiveSafeStateModel(z2, z, 0, i, b2, b3));
        } else {
            activeSafeStateModel.setBtnEnable(z2, z);
            activeSafeStateModel.updateState(b2, b3, i);
        }
    }

    public MutableLiveData<Byte> x() {
        return this.E;
    }

    public ActiveSafeModel y() {
        return this.w;
    }

    public ActiveSafeModel z() {
        return this.v;
    }
}
