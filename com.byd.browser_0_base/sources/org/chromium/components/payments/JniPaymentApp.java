package org.chromium.components.payments;

import J.N;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import defpackage.a9;
import defpackage.d5;
import defpackage.e70;
import defpackage.gq0;
import defpackage.kz1;
import defpackage.lz1;
import defpackage.nh;
import defpackage.nz1;
import defpackage.rz1;
import defpackage.ty1;
import defpackage.vz1;
import defpackage.wz1;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.payments.PaymentApp;

/* JADX INFO: loaded from: classes3.dex */
public class JniPaymentApp extends PaymentApp {
    public final Handler k;
    public final int l;
    public long m;
    public PaymentApp.a n;
    public PaymentApp.b o;

    @CalledByNative
    public JniPaymentApp(String str, String str2, String str3, Bitmap bitmap, int i, long j) {
        super(new BitmapDrawable(bitmap), str, str2, str3);
        this.k = new Handler();
        this.l = i;
        this.m = j;
    }

    @CalledByNative
    public static PayerData createPayerData(String str, String str2, String str3, Address address, String str4) {
        return new PayerData(str, str2, str3, address, str4);
    }

    @CalledByNative
    public static Address createShippingAddress(String str, String[] strArr, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        return new Address(str, strArr, str2, str3, str4, str5, str6, str7, str8, str9);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final void e(e eVar) {
        this.n = eVar;
        N.Mix09tOZ(this.m, this);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final boolean f() {
        return N.MOoH91qV(this.m);
    }

    public final void finalize() throws Throwable {
        g();
        super.finalize();
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final void g() {
        long j = this.m;
        if (j == 0) {
            return;
        }
        N.MvY3Yqx_(j);
        this.m = 0L;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final String h() {
        return N.MJ23g7SX(this.m);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final Set<String> i() {
        return new HashSet(Arrays.asList(N.MMxfB3ye(this.m)));
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final Set<String> j() {
        return new HashSet(Arrays.asList(N.McL$JgEC(this.m)));
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final int k() {
        return this.l;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final long l() {
        return N.MNEirz5D(this.m);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final boolean m() {
        return N.MxH2M7Qu(this.m);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final boolean n() {
        return N.MFs5Lo5_(this.m);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final boolean o() {
        return N.Mz9bB0kb(this.m);
    }

    @CalledByNative
    public void onAbortResult(boolean z) {
        this.k.post(new d5(this, z, 1));
    }

    @CalledByNative
    public void onInvokeError(String str) {
        this.k.post(new a9(this, 29, str));
    }

    @CalledByNative
    public void onInvokeResult(String str, String str2, PayerData payerData) {
        this.k.post(new nh(this, str, str2, payerData, 2));
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final boolean p() {
        return N.McrEaHZb(this.m);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final boolean q() {
        return N.M3$kStIs(this.m);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final void r(String str, String str2, String str3, String str4, byte[][] bArr, Map<String, lz1> map, kz1 kz1Var, List<kz1> list, Map<String, ty1> map2, nz1 nz1Var, List<wz1> list2, PaymentApp.b bVar) {
        this.o = bVar;
        N.MdDxV11A(this.m, this);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final boolean t(String str, lz1 lz1Var) {
        return N.MRag5HOD(this.m, str, lz1Var != null ? lz1Var.b() : null);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final boolean u() {
        return N.MY9Q_PcC(this.m);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final void v() {
        N.M1KlGngz(this.m);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final vz1 w(vz1 vz1Var) {
        return vz1.d(new e70(new gq0(ByteBuffer.wrap(N.MpldTTna(this.m, vz1Var.b())), new ArrayList())));
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final void x(PaymentHandlerHost paymentHandlerHost) {
        N.M_McFosm(this.m, paymentHandlerHost);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final void y(rz1 rz1Var) {
        N.MKIICwOk(this.m, rz1Var.b());
    }
}
