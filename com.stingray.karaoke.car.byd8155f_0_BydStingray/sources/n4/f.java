package n4;

import android.os.Build;
import android.os.ParcelFileDescriptor;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class f implements e4.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f19938a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p f19939b;

    public /* synthetic */ f(p pVar, int i7) {
        this.f19938a = i7;
        this.f19939b = pVar;
    }

    @Override // e4.j
    public final g4.x a(Object obj, int i7, int i10, e4.h hVar) {
        switch (this.f19938a) {
            case 0:
                p pVar = this.f19939b;
                return pVar.a(new h3.y((ByteBuffer) obj, pVar.d, pVar.c, 11), i7, i10, hVar, p.f19957j);
            default:
                p pVar2 = this.f19939b;
                return pVar2.a(new h3.y((ParcelFileDescriptor) obj, pVar2.d, pVar2.c), i7, i10, hVar, p.f19957j);
        }
    }

    @Override // e4.j
    public final boolean b(Object obj, e4.h hVar) {
        switch (this.f19938a) {
            case 0:
                return true;
            default:
                ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) obj;
                String str = Build.MANUFACTURER;
                return (!("HUAWEI".equalsIgnoreCase(str) || "HONOR".equalsIgnoreCase(str)) || parcelFileDescriptor.getStatSize() <= 536870912) && !"robolectric".equals(Build.FINGERPRINT);
        }
    }
}
