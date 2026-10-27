package org.chromium.components.webxr;

import android.app.Activity;
import android.content.Context;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;
import defpackage.ku2;
import org.chromium.build.annotations.UsedByReflection;
import org.chromium.components.webxr.a;

/* JADX INFO: loaded from: classes3.dex */
@UsedByReflection
public class ArCoreShimImpl implements org.chromium.components.webxr.a {

    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[ArCoreApk.Availability.values().length];
            b = iArr;
            try {
                iArr[ArCoreApk.Availability.SUPPORTED_APK_TOO_OLD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[ArCoreApk.Availability.SUPPORTED_INSTALLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[ArCoreApk.Availability.SUPPORTED_NOT_INSTALLED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[ArCoreApk.Availability.UNKNOWN_CHECKING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[ArCoreApk.Availability.UNKNOWN_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[ArCoreApk.Availability.UNKNOWN_TIMED_OUT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[ArCoreApk.Availability.UNSUPPORTED_DEVICE_NOT_CAPABLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[ArCoreApk.InstallStatus.values().length];
            a = iArr2;
            try {
                iArr2[ArCoreApk.InstallStatus.INSTALLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[ArCoreApk.InstallStatus.INSTALL_REQUESTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    @UsedByReflection
    public ArCoreShimImpl() {
    }

    public static int c(ArCoreApk.Availability availability) {
        switch (a.b[availability.ordinal()]) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
            default:
                throw new RuntimeException(String.format("Unknown value of Availability: %s", availability));
        }
    }

    public static int d(ArCoreApk.InstallStatus installStatus) {
        int i = a.a[installStatus.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        throw new RuntimeException(String.format("Unknown value of InstallStatus: %s", installStatus));
    }

    @Override // org.chromium.components.webxr.a
    public final int a(Activity activity) throws a.b, a.C0146a {
        try {
            return d(ArCoreApk.getInstance().requestInstall(activity, true));
        } catch (UnavailableUserDeclinedInstallationException e) {
            throw new a.b(e);
        } catch (UnavailableDeviceNotCompatibleException e2) {
            throw new a.C0146a(e2);
        }
    }

    @Override // org.chromium.components.webxr.a
    public final int b(Context context) {
        ku2 ku2VarT = ku2.t();
        try {
            ku2 ku2VarC = ku2.C();
            try {
                int iC = c(ArCoreApk.getInstance().checkAvailability(context));
                ku2VarC.close();
                ku2VarT.close();
                return iC;
            } catch (Throwable th) {
                try {
                    ku2VarC.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            try {
                ku2VarT.close();
            } catch (Throwable unused2) {
            }
            throw th2;
        }
    }
}
