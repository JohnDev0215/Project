package n0;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;
import android.os.Trace;
import android.util.Log;
import android.view.Surface;
import h2.AbstractC1343A;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import l8.C1789g;
import q.InterfaceC2156m;
import q.MenuC2151h;
import q3.InterfaceC2177d;
import r9.C2319i;
import r9.C2322l;
import x.C2654u;

/* JADX INFO: renamed from: n0.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1886o implements n4.C, InterfaceC2177d, InterfaceC2156m, s2.j, z2.u {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static volatile C1886o f19443m;
    public final /* synthetic */ int l;

    public /* synthetic */ C1886o(int i7) {
        this.l = i7;
    }

    public static final float[] c() {
        float[] fArr = C2654u.f23617s;
        if (fArr != null) {
            return fArr;
        }
        float[] fArr2 = new float[91];
        C2654u.f23617s = fArr2;
        return fArr2;
    }

    public static MediaCodec h(H0.b bVar) throws IOException {
        s2.n nVar = (s2.n) bVar.f2765m;
        StringBuilder sb = new StringBuilder("createCodec:");
        String str = nVar.f22095a;
        sb.append(str);
        Trace.beginSection(sb.toString());
        MediaCodec mediaCodecCreateByCodecName = MediaCodec.createByCodecName(str);
        Trace.endSection();
        return mediaCodecCreateByCodecName;
    }

    public static r9.z i(String str, boolean z6) {
        A7.m.f(str, "<this>");
        C2322l c2322l = s9.c.f22382a;
        C2319i c2319i = new C2319i();
        c2319i.c0(str);
        return s9.c.d(c2319i, z6);
    }

    public static r9.z j(File file) {
        String str = r9.z.f21951m;
        String string = file.toString();
        A7.m.e(string, "toString(...)");
        return i(string, false);
    }

    public static C1853C k(m7.i[] iVarArr) {
        return l((m7.i[]) Arrays.copyOf(iVarArr, iVarArr.length), h6.e.a(0.0f, 0.0f), h6.e.a(Float.POSITIVE_INFINITY, 0.0f), 0);
    }

    public static C1853C l(m7.i[] iVarArr, long j3, long j10, int i7) {
        ArrayList arrayList = new ArrayList(iVarArr.length);
        for (m7.i iVar : iVarArr) {
            arrayList.add(new C1891t(((C1891t) iVar.f19259m).f19453a));
        }
        ArrayList arrayList2 = new ArrayList(iVarArr.length);
        for (m7.i iVar2 : iVarArr) {
            arrayList2.add(Float.valueOf(((Number) iVar2.l).floatValue()));
        }
        return new C1853C(arrayList, arrayList2, j3, j10, i7);
    }

    public static C1853C m(List list) {
        return new C1853C(list, null, 0L, 9187343241974906880L, 0);
    }

    public static C1853C n(m7.i[] iVarArr) {
        return l((m7.i[]) Arrays.copyOf(iVarArr, iVarArr.length), h6.e.a(0.0f, 0.0f), h6.e.a(0.0f, Float.POSITIVE_INFINITY), 0);
    }

    @Override // n4.C
    public void A(MediaExtractor mediaExtractor, Object obj) throws IOException {
        switch (this.l) {
            case 2:
                AssetFileDescriptor assetFileDescriptor = (AssetFileDescriptor) obj;
                mediaExtractor.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                break;
            default:
                mediaExtractor.setDataSource(((ParcelFileDescriptor) obj).getFileDescriptor());
                break;
        }
    }

    @Override // q.InterfaceC2156m
    public void a(MenuC2151h menuC2151h, boolean z6) {
    }

    @Override // s2.j
    public s2.k b(H0.b bVar) {
        MediaCodec mediaCodecH = null;
        try {
            mediaCodecH = h(bVar);
            Trace.beginSection("configureCodec");
            Surface surface = (Surface) bVar.f2767p;
            mediaCodecH.configure((MediaFormat) bVar.f2766n, surface, (MediaCrypto) bVar.f2768q, (surface == null && ((s2.n) bVar.f2765m).f22100h && AbstractC1343A.f15195a >= 35) ? 8 : 0);
            Trace.endSection();
            Trace.beginSection("startCodec");
            mediaCodecH.start();
            Trace.endSection();
            return new C1789g(mediaCodecH, (h3.y) bVar.f2769r);
        } catch (IOException | RuntimeException e10) {
            if (mediaCodecH != null) {
                mediaCodecH.release();
            }
            throw e10;
        }
    }

    @Override // z2.u
    public void d() {
    }

    @Override // z2.u
    public void e() {
    }

    @Override // q3.InterfaceC2177d
    public void f(int i7, Serializable serializable) {
        String str;
        switch (i7) {
            case 1:
                str = "RESULT_INSTALL_SUCCESS";
                break;
            case 2:
                str = "RESULT_ALREADY_INSTALLED";
                break;
            case 3:
                str = "RESULT_UNSUPPORTED_ART_VERSION";
                break;
            case 4:
                str = "RESULT_NOT_WRITABLE";
                break;
            case 5:
                str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                break;
            case 6:
                str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                break;
            case 7:
                str = "RESULT_IO_EXCEPTION";
                break;
            case 8:
                str = "RESULT_PARSE_EXCEPTION";
                break;
            case 9:
            default:
                str = "";
                break;
            case 10:
                str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                break;
            case 11:
                str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                break;
        }
        if (i7 == 6 || i7 == 7 || i7 == 8) {
            h6.n.i("ProfileInstaller", str, (Throwable) serializable);
        } else {
            Log.d("ProfileInstaller", str);
        }
    }

    public void g(t4.b bVar) {
        bVar.J();
    }

    @Override // n4.C
    public void q(MediaMetadataRetriever mediaMetadataRetriever, Object obj) {
        switch (this.l) {
            case 2:
                AssetFileDescriptor assetFileDescriptor = (AssetFileDescriptor) obj;
                mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                break;
            default:
                mediaMetadataRetriever.setDataSource(((ParcelFileDescriptor) obj).getFileDescriptor());
                break;
        }
    }

    @Override // q3.InterfaceC2177d
    public void s() {
        Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
    }

    @Override // q.InterfaceC2156m
    public boolean v(MenuC2151h menuC2151h) {
        return false;
    }

    public C1886o(E8.o oVar) {
        this.l = 15;
        A7.m.f(oVar, "storageManager");
        String str = E8.l.d;
        new ConcurrentHashMap(3, 1.0f, 2);
    }

    public C1886o(Context context) {
        this.l = 1;
        context.getApplicationContext();
    }
}
