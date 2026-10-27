package f4;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.f;
import com.bumptech.glide.load.data.e;
import h6.j;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import k4.z;

/* JADX INFO: renamed from: f4.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1257b implements e {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Comparable f14593m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f14594n;
    public Object o;

    public /* synthetic */ C1257b(int i7, Comparable comparable, Object obj) {
        this.l = i7;
        this.f14593m = comparable;
        this.f14594n = obj;
    }

    public static C1257b c(Context context, Uri uri, InterfaceC1258c interfaceC1258c) {
        return new C1257b(0, uri, new C1259d(com.bumptech.glide.b.a(context).f12667n.a().e(), interfaceC1258c, com.bumptech.glide.b.a(context).o, context.getContentResolver()));
    }

    private final void d() {
    }

    private final void g() {
    }

    private final void h() {
    }

    @Override // com.bumptech.glide.load.data.e
    public final Class a() {
        switch (this.l) {
            case 0:
                return InputStream.class;
            case 1:
                ((z) this.f14594n).getClass();
                return InputStream.class;
            default:
                return ((z) this.f14594n).b();
        }
    }

    @Override // com.bumptech.glide.load.data.e
    public final void b() {
        switch (this.l) {
            case 0:
                InputStream inputStream = (InputStream) this.o;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                        return;
                    }
                }
                break;
            case 1:
                try {
                    ((ByteArrayInputStream) this.o).close();
                } catch (IOException unused2) {
                    return;
                }
                break;
            default:
                Object obj = this.o;
                if (obj != null) {
                    try {
                        switch (((z) this.f14594n).l) {
                            case 8:
                                ((ParcelFileDescriptor) obj).close();
                                break;
                            default:
                                ((InputStream) obj).close();
                                break;
                        }
                    } catch (IOException unused3) {
                        return;
                    }
                }
                break;
        }
    }

    @Override // com.bumptech.glide.load.data.e
    public final void cancel() {
        int i7 = this.l;
    }

    @Override // com.bumptech.glide.load.data.e
    public final int e() {
        switch (this.l) {
        }
        return 1;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void f(f fVar, com.bumptech.glide.load.data.d dVar) throws Throwable {
        Object objOpen;
        switch (this.l) {
            case 0:
                try {
                    InputStream inputStreamI = i();
                    this.o = inputStreamI;
                    dVar.d(inputStreamI);
                } catch (FileNotFoundException e10) {
                    if (Log.isLoggable("MediaStoreThumbFetcher", 3)) {
                        Log.d("MediaStoreThumbFetcher", "Failed to find thumbnail file", e10);
                    }
                    dVar.c(e10);
                }
                break;
            case 1:
                try {
                    ByteArrayInputStream byteArrayInputStreamA = z.a((String) this.f14593m);
                    this.o = byteArrayInputStreamA;
                    dVar.d(byteArrayInputStreamA);
                } catch (IllegalArgumentException e11) {
                    dVar.c(e11);
                    return;
                }
                break;
            default:
                try {
                    z zVar = (z) this.f14594n;
                    File file = (File) this.f14593m;
                    switch (zVar.l) {
                        case 8:
                            objOpen = ParcelFileDescriptor.open(file, 268435456);
                            break;
                        default:
                            objOpen = j.h(new FileInputStream(file), file);
                            break;
                    }
                    this.o = objOpen;
                    dVar.d(objOpen);
                } catch (FileNotFoundException e12) {
                    if (Log.isLoggable("FileLoader", 3)) {
                        Log.d("FileLoader", "Failed to open file", e12);
                    }
                    dVar.c(e12);
                    return;
                }
                break;
        }
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x0026: MOVE (r4 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:10:0x0026 */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:81:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.io.InputStream i() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f4.C1257b.i():java.io.InputStream");
    }
}
