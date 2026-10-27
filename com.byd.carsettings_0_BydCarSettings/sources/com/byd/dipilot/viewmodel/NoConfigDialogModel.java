package com.byd.dipilot.viewmodel;

import androidx.lifecycle.ViewModel;
import b.b.k.a.a;
import com.byd.base.view.dialog.LeakDialogFragment;
import com.byd.dipilot.bpresenter.R$string;
import com.byd.dipilot.model.DriverAssistModel;

/* JADX INFO: loaded from: classes2.dex */
public class NoConfigDialogModel extends ViewModel {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3634a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3635b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3636c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f3637d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public DriverAssistModel f3638e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public LeakDialogFragment f3639f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public byte k;

    public a a() {
        return this.f3637d;
    }

    public int b() {
        return this.f3635b == R$string.adas_itac_tip ? R$string.adas_itac_exit_confirm : R$string.dipilot_common_dialog_confirm;
    }

    public int c() {
        return this.f3635b;
    }

    public byte d() {
        return this.k;
    }

    public LeakDialogFragment e() {
        return this.f3639f;
    }

    public DriverAssistModel f() {
        return this.f3638e;
    }

    public int g() {
        return this.f3636c;
    }

    public int h() {
        return this.f3634a;
    }

    public void i(int i, int i2, int i3, a aVar, LeakDialogFragment leakDialogFragment, DriverAssistModel driverAssistModel) {
        this.f3634a = i;
        this.f3635b = i2;
        this.f3636c = i3;
        this.f3637d = aVar;
        this.f3638e = driverAssistModel;
        this.f3639f = leakDialogFragment;
    }

    public boolean j() {
        return this.j;
    }

    public boolean k() {
        return this.i;
    }

    public boolean l() {
        return this.g;
    }

    public boolean m() {
        return this.h;
    }

    public void n(boolean z) {
        if (this.f3639f == null) {
            return;
        }
        a aVar = this.f3637d;
        if (aVar != null) {
            aVar.f(z);
        }
        o();
    }

    public void o() {
        this.f3637d = null;
        this.f3638e = null;
        LeakDialogFragment leakDialogFragment = this.f3639f;
        if (leakDialogFragment != null) {
            leakDialogFragment.dismiss();
            this.f3639f = null;
            this.f3634a = 0;
            this.f3635b = 0;
            this.f3636c = 0;
            this.k = (byte) 0;
        }
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        if (this.f3639f != null) {
            this.f3639f = null;
        }
        this.f3637d = null;
        this.f3638e = null;
    }

    public void p(boolean z) {
        this.j = z;
    }

    public void q(boolean z) {
        this.i = z;
    }

    public void r(boolean z) {
        this.g = z;
    }

    public void s(byte b2) {
        this.k = b2;
    }

    public void t(LeakDialogFragment leakDialogFragment) {
        this.f3639f = leakDialogFragment;
    }

    public void u(boolean z) {
        this.h = z;
    }
}
