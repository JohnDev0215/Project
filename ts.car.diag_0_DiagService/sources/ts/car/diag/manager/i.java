package ts.car.diag.manager;

import android.content.Context;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public abstract class i {
    public static i h() {
        return GwBtManagerImpl.w();
    }

    public abstract byte a(String str, byte b2);

    public abstract void b(Context context);

    public abstract byte c(byte b2);

    public abstract ArrayList<Byte> d(byte b2);

    public abstract byte e(byte b2, String str, String str2);

    public abstract byte[] f();

    public abstract byte[] g();

    public abstract String i();

    public abstract String j();

    public abstract byte k(byte[] bArr);
}
