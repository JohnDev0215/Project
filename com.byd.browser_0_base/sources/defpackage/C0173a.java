package defpackage;

import android.hardware.bydauto.Log;
import android.hardware.bydauto_test.TestACManagerImplCallback;
import android.os.HandlerThread;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import kotlinx.coroutines.android.AndroidDispatcherFactory;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: renamed from: a, reason: case insensitive filesystem */
/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0173a implements yd3 {
    public static final void a(int i) {
        n3.X0("Autofill.FastCheckout.".concat(d(i)));
    }

    public static /* synthetic */ boolean b(int i) {
        if (i == 1) {
            return false;
        }
        if (i == 2 || i == 3) {
            return true;
        }
        if (i == 4) {
            return false;
        }
        throw null;
    }

    public static /* synthetic */ String d(int i) {
        if (i == 1) {
            return "Initialized";
        }
        if (i == 2) {
            return "Accepted";
        }
        if (i == 3) {
            return "NavigatedBackHome";
        }
        if (i == 4) {
            return "NavigatedToAddresses";
        }
        if (i == 5) {
            return "NavigatedToCreditCards";
        }
        if (i == 6) {
            return "NavigatedToAddressesSettingsViaIcon";
        }
        if (i == 7) {
            return "NavigatedToCreditCardsSettingsViaIcon";
        }
        if (i == 8) {
            return "NavigatedToAddressesSettingsViaFooter";
        }
        if (i == 9) {
            return "NavigatedToCreditCardsSettingsViaFooter";
        }
        if (i == 10) {
            return "SelectedDifferentAddress";
        }
        if (i == 11) {
            return "SelectedDifferentCreditCard";
        }
        if (i == 12) {
            return "SelectedSameAddress";
        }
        if (i == 13) {
            return "SelectedSameCreditCard";
        }
        if (i == 14) {
            return "Dismissed";
        }
        if (i == 15) {
            return "Destroyed";
        }
        throw null;
    }

    public static /* synthetic */ int e(int i) {
        if (i == 1) {
            return 1;
        }
        throw null;
    }

    public static float f(float f, float f2, float f3, float f4) {
        return ((f - f2) * f3) + f4;
    }

    public static e70 g(int i, int i2, int i3, e70 e70Var, boolean z) {
        return e70Var.t((i * i2) + i3, z);
    }

    public static HandlerThread h(String str) {
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        return handlerThread;
    }

    public static StringBuilder i(TestACManagerImplCallback testACManagerImplCallback, int i, int i2, String str) {
        testACManagerImplCallback.enqueueMessage(i, i2);
        return new StringBuilder(str);
    }

    public static StringBuilder j(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static StringBuilder k(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        return sb;
    }

    public static /* synthetic */ Iterator l() {
        try {
            return Arrays.asList(new AndroidDispatcherFactory()).iterator();
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }

    public static void m(String str, int i, String str2) {
        Log.i(str2, str + i);
    }

    public static void n(StringBuilder sb, int i, String str) {
        sb.append(i);
        Log.i(str, sb.toString());
    }

    public static void o(StringBuilder sb, int i, String str, String str2) {
        sb.append(i);
        sb.append(str);
        Log.v(str2, sb.toString());
    }

    public static void p(StringBuilder sb, int i, String str, String str2) {
        sb.append(i);
        sb.append(str);
        Log.d(str2, sb.toString());
    }

    public static void q(StringBuilder sb, int i, String str, String str2) {
        sb.append(i);
        sb.append(str);
        android.util.Log.e(str2, sb.toString());
    }

    public static /* synthetic */ String r(int i) {
        return i == 1 ? "unknown" : i == 2 ? "proto2" : i == 3 ? "proto3" : "null";
    }

    public static /* synthetic */ String s(int i) {
        return i == 1 ? "NONE" : i == 2 ? "ADDING" : i == 3 ? "REMOVING" : "null";
    }

    @Override // defpackage.yd3
    public void c(boolean z) {
    }
}
