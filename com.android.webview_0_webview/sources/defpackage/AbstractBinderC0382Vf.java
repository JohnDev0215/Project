package defpackage;

import android.os.RemoteException;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/* JADX INFO: renamed from: Vf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0382Vf extends AbstractBinderC0131Hf {
    public int a;

    public AbstractBinderC0382Vf(byte[] bArr) {
        Cif.a(bArr.length == 25);
        this.a = Arrays.hashCode(bArr);
    }

    public static byte[] w(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof InterfaceC0113Gf)) {
            try {
                InterfaceC0113Gf interfaceC0113Gf = (InterfaceC0113Gf) obj;
                if (((AbstractBinderC0382Vf) interfaceC0113Gf).a != this.a) {
                    return false;
                }
                return Arrays.equals(v(), (byte[]) BinderC0615cg.w(new BinderC0615cg(((AbstractBinderC0382Vf) interfaceC0113Gf).v())));
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            }
        }
        return false;
    }

    public int hashCode() {
        return this.a;
    }

    public abstract byte[] v();
}
