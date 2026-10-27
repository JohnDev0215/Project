package r3;

import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import com.bydux.api.common.tools.log.BydLogHelper;
import p3.f;

/* JADX INFO: compiled from: GearListener.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends AbsBYDAutoGearboxListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public s3.b f7798a;

    public d(s3.b bVar) {
        this.f7798a = bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(int i8) {
        this.f7798a.e(i8);
    }

    public final void c(int i8) {
        s3.b bVar = this.f7798a;
        if (bVar == null || i8 == 1) {
            return;
        }
        final int iB = bVar.b();
        if (iB > 0 && iB < 3) {
            f.getInstance().a(8, AbsBYDAutoGearboxListener.TAG, "showGearChange", new f.a() { // from class: r3.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f7791d.b(iB);
                }
            });
            return;
        }
        BydLogHelper.logI(AbsBYDAutoGearboxListener.TAG, "showCarGearChange", "gearValue =" + i8 + ",displayModel =" + iB);
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onGearChanged(int i8) {
        s3.b bVar = this.f7798a;
        if (bVar == null || bVar.d()) {
            return;
        }
        c(i8);
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onGearboxAutoModeLevelChanged(int i8) {
        super.onGearboxManualModeLevelChanged(i8);
        s3.b bVar = this.f7798a;
        if (bVar == null || !bVar.d()) {
            return;
        }
        c(i8);
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onGearboxAutoModeTypeChanged(int i8) {
        super.onGearboxAutoModeTypeChanged(i8);
    }
}
