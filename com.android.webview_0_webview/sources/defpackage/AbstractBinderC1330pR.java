package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.AndroidRuntimeException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.components.crash.browser.ChildProcessCrashObserver;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: pR, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC1330pR extends Binder implements IInterface {
    public AbstractBinderC1330pR() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.ICrashReporterController");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        JSONObject jSONObject;
        if (i == 1) {
            parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ICrashReporterController");
            InterfaceC1494sR interfaceC1494sRC = AbstractBinderC1439rR.c(parcel.readStrongBinder());
            BinderC1108lQ binderC1108lQ = (BinderC1108lQ) this;
            AbstractC1054kS.a();
            binderC1108lQ.a = interfaceC1494sRC;
            if (binderC1108lQ.c) {
                binderC1108lQ.i();
            }
            C0996jQ c0996jQ = new C0996jQ(binderC1108lQ);
            ThreadUtils.b();
            ChildProcessCrashObserver.a = c0996jQ;
            parcel2.writeNoException();
            return true;
        }
        if (i == 2) {
            parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ICrashReporterController");
            final BinderC1108lQ binderC1108lQ2 = (BinderC1108lQ) this;
            AbstractC1054kS.a();
            ((Pu) Su.f).execute(new Runnable(binderC1108lQ2) { // from class: hQ
                public final BinderC1108lQ s;

                {
                    this.s = binderC1108lQ2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    BinderC1108lQ binderC1108lQ3 = this.s;
                    Objects.requireNonNull(binderC1108lQ3);
                    try {
                        ((C1385qR) binderC1108lQ3.a).i(binderC1108lQ3.f());
                    } catch (RemoteException e) {
                        throw new AndroidRuntimeException(e);
                    }
                }
            });
            parcel2.writeNoException();
            return true;
        }
        if (i != 3) {
            if (i == 4) {
                parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ICrashReporterController");
                final String string = parcel.readString();
                final BinderC1108lQ binderC1108lQ3 = (BinderC1108lQ) this;
                AbstractC1054kS.a();
                ((Pu) Su.f).execute(new Runnable(binderC1108lQ3, string) { // from class: fQ
                    public final BinderC1108lQ s;
                    public final String t;

                    {
                        this.s = binderC1108lQ3;
                        this.t = string;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        BinderC1108lQ binderC1108lQ4 = this.s;
                        String str = this.t;
                        File fileF = binderC1108lQ4.c().f(str);
                        File fileU = binderC1108lQ4.u(str);
                        if (fileF != null) {
                            Px.c(fileF);
                        }
                        if (fileU != null) {
                            Px.c(fileU);
                        }
                        try {
                            ((C1385qR) binderC1108lQ4.a).c(str);
                        } catch (RemoteException e) {
                            throw new AndroidRuntimeException(e);
                        }
                    }
                });
                parcel2.writeNoException();
                return true;
            }
            if (i != 5) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("org.chromium.weblayer_private.interfaces.ICrashReporterController");
                return true;
            }
            parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ICrashReporterController");
            final String string2 = parcel.readString();
            final BinderC1108lQ binderC1108lQ4 = (BinderC1108lQ) this;
            AbstractC1054kS.a();
            ((Pu) Su.f).execute(new Runnable(binderC1108lQ4, string2) { // from class: gQ
                public final BinderC1108lQ s;
                public final String t;

                {
                    this.s = binderC1108lQ4;
                    this.t = string2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    BinderC1108lQ binderC1108lQ5 = this.s;
                    String str = this.t;
                    File fileF = binderC1108lQ5.c().f(str);
                    Yx yxD = new Zx().d(fileF);
                    if (yxD.b()) {
                        Px.n(fileF, ".up");
                    } else {
                        Px.o(fileF);
                    }
                    try {
                        if (yxD.b()) {
                            ((C1385qR) binderC1108lQ5.a).f(str, yxD.b);
                        } else {
                            ((C1385qR) binderC1108lQ5.a).d(str, yxD.b);
                        }
                    } catch (RemoteException e) {
                        throw new AndroidRuntimeException(e);
                    }
                }
            });
            parcel2.writeNoException();
            return true;
        }
        parcel.enforceInterface("org.chromium.weblayer_private.interfaces.ICrashReporterController");
        String string3 = parcel.readString();
        AbstractC1054kS.a();
        File fileU = ((BinderC1108lQ) this).u(string3);
        Bundle bundle = null;
        if (fileU == null) {
            jSONObject = null;
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(fileU);
                try {
                    int length = (int) fileU.length();
                    byte[] bArr = new byte[length];
                    int i3 = 0;
                    while (i3 < length) {
                        int i4 = fileInputStream.read(bArr, i3, length - i3);
                        if (i4 <= 0) {
                            break;
                        }
                        i3 += i4;
                    }
                    jSONObject = new JSONObject(new String(bArr, "UTF-8"));
                    fileInputStream.close();
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        AbstractC0279Pk.a.a(th, th2);
                    }
                    throw th;
                }
            } catch (IOException | JSONException unused) {
                jSONObject = null;
            }
        }
        if (jSONObject != null) {
            bundle = new Bundle();
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                try {
                    bundle.putCharSequence(next, jSONObject.getString(next));
                } catch (JSONException unused2) {
                }
            }
        }
        parcel2.writeNoException();
        if (bundle != null) {
            parcel2.writeInt(1);
            bundle.writeToParcel(parcel2, 1);
        } else {
            parcel2.writeInt(0);
        }
        return true;
    }
}
