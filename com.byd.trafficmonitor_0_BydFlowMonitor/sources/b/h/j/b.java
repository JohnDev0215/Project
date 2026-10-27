package b.h.j;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import b.h.e.c.f;
import b.h.f.k;
import b.h.j.c;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: FontsContractCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.e.e<String, Typeface> f2488a = new b.e.e<>(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b.h.j.c f2489b = new b.h.j.c("fonts", 10, 10000);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f2490c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b.e.g<String, ArrayList<c.d<g>>> f2491d = new b.e.g<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Comparator<byte[]> f2492e = new d();

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class a implements Callable<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f2493a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ b.h.j.a f2494b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f2495c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f2496d;

        public a(Context context, b.h.j.a aVar, int i2, String str) {
            this.f2493a = context;
            this.f2494b = aVar;
            this.f2495c = i2;
            this.f2496d = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public g call() throws Exception {
            g gVarF = b.f(this.f2493a, this.f2494b, this.f2495c);
            Typeface typeface = gVarF.f2507a;
            if (typeface != null) {
                b.f2488a.d(this.f2496d, typeface);
            }
            return gVarF;
        }
    }

    /* JADX INFO: renamed from: b.h.j.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class C0037b implements c.d<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ f.a f2497a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Handler f2498b;

        public C0037b(f.a aVar, Handler handler) {
            this.f2497a = aVar;
            this.f2498b = handler;
        }

        @Override // b.h.j.c.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            if (gVar == null) {
                this.f2497a.a(1, this.f2498b);
                return;
            }
            int i2 = gVar.f2508b;
            if (i2 == 0) {
                this.f2497a.b(gVar.f2507a, this.f2498b);
            } else {
                this.f2497a.a(i2, this.f2498b);
            }
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class c implements c.d<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2499a;

        public c(String str) {
            this.f2499a = str;
        }

        @Override // b.h.j.c.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            synchronized (b.f2490c) {
                b.e.g<String, ArrayList<c.d<g>>> gVar2 = b.f2491d;
                ArrayList<c.d<g>> arrayList = gVar2.get(this.f2499a);
                if (arrayList == null) {
                    return;
                }
                gVar2.remove(this.f2499a);
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    arrayList.get(i2).a(gVar);
                }
            }
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class d implements Comparator<byte[]> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(byte[] bArr, byte[] bArr2) {
            int length;
            int length2;
            if (bArr.length == bArr2.length) {
                for (int i2 = 0; i2 < bArr.length; i2++) {
                    if (bArr[i2] != bArr2[i2]) {
                        length = bArr[i2];
                        length2 = bArr2[i2];
                    }
                }
                return 0;
            }
            length = bArr.length;
            length2 = bArr2.length;
            return length - length2;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2500a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final f[] f2501b;

        public e(int i2, f[] fVarArr) {
            this.f2500a = i2;
            this.f2501b = fVarArr;
        }

        public f[] a() {
            return this.f2501b;
        }

        public int b() {
            return this.f2500a;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Uri f2502a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f2503b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f2504c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f2505d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f2506e;

        public f(Uri uri, int i2, int i3, boolean z, int i4) {
            this.f2502a = (Uri) b.h.l.g.c(uri);
            this.f2503b = i2;
            this.f2504c = i3;
            this.f2505d = z;
            this.f2506e = i4;
        }

        public int a() {
            return this.f2506e;
        }

        public int b() {
            return this.f2503b;
        }

        public Uri c() {
            return this.f2502a;
        }

        public int d() {
            return this.f2504c;
        }

        public boolean e() {
            return this.f2505d;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Typeface f2507a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f2508b;

        public g(Typeface typeface, int i2) {
            this.f2507a = typeface;
            this.f2508b = i2;
        }
    }

    public static List<byte[]> a(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    public static boolean b(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (!Arrays.equals(list.get(i2), list2.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public static e c(Context context, CancellationSignal cancellationSignal, b.h.j.a aVar) throws PackageManager.NameNotFoundException {
        ProviderInfo providerInfoH = h(context.getPackageManager(), aVar, context.getResources());
        return providerInfoH == null ? new e(1, null) : new e(0, e(context, aVar, providerInfoH.authority, cancellationSignal));
    }

    public static List<List<byte[]>> d(b.h.j.a aVar, Resources resources) {
        return aVar.a() != null ? aVar.a() : b.h.e.c.c.c(resources, aVar.b());
    }

    public static f[] e(Context context, b.h.j.a aVar, String str, CancellationSignal cancellationSignal) {
        ArrayList arrayList = new ArrayList();
        Uri uriBuild = new Uri.Builder().scheme("content").authority(str).build();
        Uri uriBuild2 = new Uri.Builder().scheme("content").authority(str).appendPath("file").build();
        Cursor cursorQuery = null;
        try {
            cursorQuery = Build.VERSION.SDK_INT > 16 ? context.getContentResolver().query(uriBuild, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{aVar.f()}, null, cancellationSignal) : context.getContentResolver().query(uriBuild, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{aVar.f()}, null);
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                int columnIndex = cursorQuery.getColumnIndex("result_code");
                ArrayList arrayList2 = new ArrayList();
                int columnIndex2 = cursorQuery.getColumnIndex("_id");
                int columnIndex3 = cursorQuery.getColumnIndex("file_id");
                int columnIndex4 = cursorQuery.getColumnIndex("font_ttc_index");
                int columnIndex5 = cursorQuery.getColumnIndex("font_weight");
                int columnIndex6 = cursorQuery.getColumnIndex("font_italic");
                while (cursorQuery.moveToNext()) {
                    int i2 = columnIndex != -1 ? cursorQuery.getInt(columnIndex) : 0;
                    arrayList2.add(new f(columnIndex3 == -1 ? ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2)) : ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3)), columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : 0, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, i2));
                }
                arrayList = arrayList2;
            }
            return (f[]) arrayList.toArray(new f[0]);
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    public static g f(Context context, b.h.j.a aVar, int i2) {
        try {
            e eVarC = c(context, null, aVar);
            if (eVarC.b() != 0) {
                return new g(null, eVarC.b() == 1 ? -2 : -3);
            }
            Typeface typefaceB = b.h.f.d.b(context, null, eVarC.a(), i2);
            return new g(typefaceB, typefaceB != null ? 0 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new g(null, -1);
        }
    }

    public static Typeface g(Context context, b.h.j.a aVar, f.a aVar2, Handler handler, boolean z, int i2, int i3) {
        String str = aVar.c() + "-" + i3;
        Typeface typefaceC = f2488a.c(str);
        if (typefaceC != null) {
            if (aVar2 != null) {
                aVar2.d(typefaceC);
            }
            return typefaceC;
        }
        if (z && i2 == -1) {
            g gVarF = f(context, aVar, i3);
            if (aVar2 != null) {
                int i4 = gVarF.f2508b;
                if (i4 == 0) {
                    aVar2.b(gVarF.f2507a, handler);
                } else {
                    aVar2.a(i4, handler);
                }
            }
            return gVarF.f2507a;
        }
        a aVar3 = new a(context, aVar, i3, str);
        if (z) {
            try {
                return ((g) f2489b.e(aVar3, i2)).f2507a;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        C0037b c0037b = aVar2 == null ? null : new C0037b(aVar2, handler);
        synchronized (f2490c) {
            b.e.g<String, ArrayList<c.d<g>>> gVar = f2491d;
            ArrayList<c.d<g>> arrayList = gVar.get(str);
            if (arrayList != null) {
                if (c0037b != null) {
                    arrayList.add(c0037b);
                }
                return null;
            }
            if (c0037b != null) {
                ArrayList<c.d<g>> arrayList2 = new ArrayList<>();
                arrayList2.add(c0037b);
                gVar.put(str, arrayList2);
            }
            f2489b.d(aVar3, new c(str));
            return null;
        }
    }

    public static ProviderInfo h(PackageManager packageManager, b.h.j.a aVar, Resources resources) throws PackageManager.NameNotFoundException {
        String strD = aVar.d();
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(strD, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + strD);
        }
        if (!providerInfoResolveContentProvider.packageName.equals(aVar.e())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + strD + ", but package was not " + aVar.e());
        }
        List<byte[]> listA = a(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
        Collections.sort(listA, f2492e);
        List<List<byte[]>> listD = d(aVar, resources);
        for (int i2 = 0; i2 < listD.size(); i2++) {
            ArrayList arrayList = new ArrayList(listD.get(i2));
            Collections.sort(arrayList, f2492e);
            if (b(listA, arrayList)) {
                return providerInfoResolveContentProvider;
            }
        }
        return null;
    }

    public static Map<Uri, ByteBuffer> i(Context context, f[] fVarArr, CancellationSignal cancellationSignal) {
        HashMap map = new HashMap();
        for (f fVar : fVarArr) {
            if (fVar.a() == 0) {
                Uri uriC = fVar.c();
                if (!map.containsKey(uriC)) {
                    map.put(uriC, k.f(context, cancellationSignal, uriC));
                }
            }
        }
        return Collections.unmodifiableMap(map);
    }
}
