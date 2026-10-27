package org.chromium.chrome.browser.keyboard_accessory;

import J.N;
import android.app.Activity;
import android.util.SparseArray;
import defpackage.aj3;
import defpackage.ko0;
import defpackage.l41;
import defpackage.m41;
import defpackage.n41;
import defpackage.o41;
import defpackage.p41;
import defpackage.pq2;
import defpackage.tb1;
import defpackage.ub1;
import defpackage.vb1;
import defpackage.x62;
import defpackage.z7;
import defpackage.zp1;
import java.security.InvalidParameterException;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.keyboard_accessory.ManualFillingComponentBridge;
import org.chromium.chrome.browser.keyboard_accessory.a;
import org.chromium.chrome.browser.keyboard_accessory.data.UserInfoField;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class ManualFillingComponentBridge {
    public x62<m41[]> b;
    public final WindowAndroid c;
    public final WebContents d;
    public long e;
    public final SparseArray<x62<l41>> a = new SparseArray<>();
    public final ub1 f = new a.InterfaceC0088a() { // from class: ub1
        @Override // org.chromium.chrome.browser.keyboard_accessory.a.InterfaceC0088a
        public final void onDestroy() {
            ManualFillingComponentBridge manualFillingComponentBridge = this.a;
            long j = manualFillingComponentBridge.e;
            if (j != 0) {
                N.MVOJP_9v(j, manualFillingComponentBridge);
            }
        }
    };

    /* JADX WARN: Type inference failed for: r0v1, types: [ub1] */
    public ManualFillingComponentBridge(long j, WindowAndroid windowAndroid, WebContents webContents) {
        this.e = j;
        this.c = windowAndroid;
        this.d = webContents;
    }

    @CalledByNative
    public static ManualFillingComponentBridge create(long j, WindowAndroid windowAndroid, WebContents webContents) {
        return new ManualFillingComponentBridge(j, windowAndroid, webContents);
    }

    @CalledByNative
    public static Object createAccessorySheetData(int i, String str, String str2) {
        return new l41(i, str, str2);
    }

    public final a a() {
        aj3<vb1> aj3Var = vb1.j;
        zp1 zp1Var = (zp1) vb1.j.e(this.c.r);
        if (zp1Var == null) {
            return null;
        }
        a aVar = (a) zp1Var.get();
        if (aVar != null) {
            aVar.Q0(this.f);
        }
        return aVar;
    }

    @CalledByNative
    public final void addFieldToUserInfo(Object obj, final int i, String str, String str2, String str3, String str4, boolean z, boolean z2) {
        ((p41) obj).a.add(new UserInfoField(str, str2, str3, str4, z, z2 ? new Callback() { // from class: wx1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v3, types: [int] */
            /* JADX WARN: Type inference failed for: r1v5 */
            /* JADX WARN: Type inference failed for: r1v6 */
            /* JADX WARN: Type inference failed for: r1v8 */
            /* JADX WARN: Type inference failed for: r1v9 */
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj2) {
                ManualFillingComponentBridge manualFillingComponentBridge = (ManualFillingComponentBridge) this;
                UserInfoField userInfoField = (UserInfoField) obj2;
                manualFillingComponentBridge.getClass();
                userInfoField.a = z7.q(userInfoField.a);
                boolean zIsObfuscated = userInfoField.isObfuscated();
                int i2 = i;
                if (i2 == 0) {
                    throw new InvalidParameterException(gq2.e("Unable to handle tabType: ", i2));
                }
                ?? r1 = zIsObfuscated;
                if (i2 != 1) {
                    r1 = 2;
                    if (i2 != 2) {
                        r1 = 3;
                        if (i2 != 3) {
                            if (i2 == 4) {
                                throw new InvalidParameterException(gq2.e("Obsolete tabType: ", i2));
                            }
                            r1 = 5;
                        }
                    }
                }
                oj2.U(r1, 5, z7.u(0, "KeyboardAccessory.AccessorySheetSuggestionsSelected"));
                oj2.U(r1, 5, z7.u(i2, "KeyboardAccessory.AccessorySheetSuggestionsSelected"));
                N.M6ME2$pd(manualFillingComponentBridge.e, manualFillingComponentBridge, i2, userInfoField);
            }
        } : null));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [sb1] */
    @CalledByNative
    public final void addFooterCommandToAccessorySheetData(Object obj, String str, final int i) {
        ((l41) obj).d.add(new n41(new Callback() { // from class: sb1
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj2) {
                ManualFillingComponentBridge manualFillingComponentBridge = this.e;
                N.MmIaCnPe(manualFillingComponentBridge.e, manualFillingComponentBridge, i);
            }
        }));
    }

    @CalledByNative
    public final void addOptionToggleToAccessorySheetData(Object obj, String str, boolean z, int i) {
        new o41(str, z, i, new tb1(i, 0, this));
        ((l41) obj).getClass();
    }

    @CalledByNative
    public final void addPromoCodeInfoToAccessorySheetData(Object obj, int i, String str, String str2, String str3, String str4, boolean z, String str5) {
        ((l41) obj).c.add(new z7());
    }

    @CalledByNative
    public final Object addUserInfoToAccessorySheetData(Object obj, String str, boolean z, GURL gurl) {
        p41 p41Var = new p41();
        ((l41) obj).b.add(p41Var);
        return p41Var;
    }

    @CalledByNative
    public final void closeAccessorySheet() {
        if (a() != null) {
            a().b0();
        }
    }

    @CalledByNative
    public final void destroy() {
        if (a() != null) {
            a().J(this.f);
        }
        int i = 0;
        while (true) {
            SparseArray<x62<l41>> sparseArray = this.a;
            if (i >= sparseArray.size()) {
                this.e = 0L;
                return;
            } else {
                sparseArray.valueAt(i).b(null);
                i++;
            }
        }
    }

    @CalledByNative
    public void hide() {
        if (a() != null) {
            a().b();
        }
    }

    @CalledByNative
    public final void onAutomaticGenerationStatusChanged(boolean z) {
        Activity activity = (Activity) this.c.k().get();
        m41[] m41VarArr = (!z || activity == null) ? new m41[0] : new m41[]{new m41(activity.getString(R$string.password_generation_accessory_button), new ko0(11, this))};
        if (this.b == null && a() != null) {
            this.b = new x62<>(0);
            a().s1(this.d, this.b);
        }
        x62<m41[]> x62Var = this.b;
        if (x62Var != null) {
            x62Var.b(m41VarArr);
        }
    }

    @CalledByNative
    public final void onItemsAvailable(Object obj) {
        Object obj2 = ThreadUtils.a;
        l41 l41Var = (l41) obj;
        int i = l41Var.a;
        SparseArray<x62<l41>> sparseArray = this.a;
        x62<l41> x62Var = sparseArray.get(i);
        if (x62Var == null) {
            x62<l41> x62Var2 = null;
            if (a() != null) {
                WebContents webContents = this.d;
                if (!webContents.isDestroyed()) {
                    if (sparseArray.size() == 0) {
                        a().W0(webContents, new pq2(17, this));
                    }
                    x62Var2 = new x62<>();
                    sparseArray.put(i, x62Var2);
                    a().a0(webContents, i, x62Var2);
                }
            }
            x62Var = x62Var2;
        }
        if (x62Var != null) {
            x62Var.b(l41Var);
        }
    }

    @CalledByNative
    public void show(boolean z) {
        if (a() != null) {
            a().c(z);
        }
    }

    @CalledByNative
    public final void showAccessorySheetTab(int i) {
        if (a() != null) {
            a().d0();
        }
    }

    @CalledByNative
    public final void swapSheetWithKeyboard() {
        if (a() != null) {
            a().m0();
        }
    }
}
