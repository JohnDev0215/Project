package l0;

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
import f0.f;
import g0.k;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import l0.c;
import n0.h;

/* JADX INFO: compiled from: FontsContractCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final v.e<String, Typeface> f6024a = new v.e<>(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final l0.c f6025b = new l0.c("fonts", 10, 10000);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f6026c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final v.g<String, ArrayList<c.d<g>>> f6027d = new v.g<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Comparator<byte[]> f6028e = new d();

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class a implements Callable<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f6029a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ l0.a f6030b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f6031c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f6032d;

        public a(Context context, l0.a aVar, int i8, String str) {
            this.f6029a = context;
            this.f6030b = aVar;
            this.f6031c = i8;
            this.f6032d = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public g call() throws Exception {
            g gVarF = b.f(this.f6029a, this.f6030b, this.f6031c);
            Typeface typeface = gVarF.f6043a;
            if (typeface != null) {
                b.f6024a.d(this.f6032d, typeface);
            }
            return gVarF;
        }
    }

    /* JADX INFO: renamed from: l0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class C0077b implements c.d<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ f.a f6033a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Handler f6034b;

        public C0077b(f.a aVar, Handler handler) {
            this.f6033a = aVar;
            this.f6034b = handler;
        }

        @Override // l0.c.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            if (gVar == null) {
                this.f6033a.a(1, this.f6034b);
                return;
            }
            int i8 = gVar.f6044b;
            if (i8 == 0) {
                this.f6033a.b(gVar.f6043a, this.f6034b);
            } else {
                this.f6033a.a(i8, this.f6034b);
            }
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class c implements c.d<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6035a;

        public c(String str) {
            this.f6035a = str;
        }

        @Override // l0.c.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            synchronized (b.f6026c) {
                v.g<String, ArrayList<c.d<g>>> gVar2 = b.f6027d;
                ArrayList<c.d<g>> arrayList = gVar2.get(this.f6035a);
                if (arrayList == null) {
                    return;
                }
                gVar2.remove(this.f6035a);
                for (int i8 = 0; i8 < arrayList.size(); i8++) {
                    arrayList.get(i8).a(gVar);
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
                for (int i8 = 0; i8 < bArr.length; i8++) {
                    if (bArr[i8] != bArr2[i8]) {
                        length = bArr[i8];
                        length2 = bArr2[i8];
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
        public final int f6036a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final f[] f6037b;

        public e(int i8, f[] fVarArr) {
            this.f6036a = i8;
            this.f6037b = fVarArr;
        }

        public f[] a() {
            return this.f6037b;
        }

        public int b() {
            return this.f6036a;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Uri f6038a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f6039b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f6040c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f6041d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f6042e;

        public f(Uri uri, int i8, int i9, boolean z7, int i10) {
            this.f6038a = (Uri) h.e(uri);
            this.f6039b = i8;
            this.f6040c = i9;
            this.f6041d = z7;
            this.f6042e = i10;
        }

        public int a() {
            return this.f6042e;
        }

        public int b() {
            return this.f6039b;
        }

        public Uri c() {
            return this.f6038a;
        }

        public int d() {
            return this.f6040c;
        }

        public boolean e() {
            return this.f6041d;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Typeface f6043a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f6044b;

        public g(Typeface typeface, int i8) {
            this.f6043a = typeface;
            this.f6044b = i8;
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
        for (int i8 = 0; i8 < list.size(); i8++) {
            if (!Arrays.equals(list.get(i8), list2.get(i8))) {
                return false;
            }
        }
        return true;
    }

    public static e c(Context context, CancellationSignal cancellationSignal, l0.a aVar) throws PackageManager.NameNotFoundException {
        ProviderInfo providerInfoH = h(context.getPackageManager(), aVar, context.getResources());
        return providerInfoH == null ? new e(1, null) : new e(0, e(context, aVar, providerInfoH.authority, cancellationSignal));
    }

    public static List<List<byte[]>> d(l0.a aVar, Resources resources) {
        return aVar.a() != null ? aVar.a() : f0.c.c(resources, aVar.b());
    }

    public static f[] e(Context context, l0.a aVar, String str, CancellationSignal cancellationSignal) {
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
                    int i8 = columnIndex != -1 ? cursorQuery.getInt(columnIndex) : 0;
                    arrayList2.add(new f(columnIndex3 == -1 ? ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2)) : ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3)), columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : 0, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, i8));
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

    public static g f(Context context, l0.a aVar, int i8) {
        try {
            e eVarC = c(context, null, aVar);
            if (eVarC.b() != 0) {
                return new g(null, eVarC.b() == 1 ? -2 : -3);
            }
            Typeface typefaceB = g0.d.b(context, null, eVarC.a(), i8);
            return new g(typefaceB, typefaceB != null ? 0 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new g(null, -1);
        }
    }

    public static Typeface g(Context context, l0.a aVar, f.a aVar2, Handler handler, boolean z7, int i8, int i9) {
        String str = aVar.c() + "-" + i9;
        Typeface typefaceC = f6024a.c(str);
        if (typefaceC != null) {
            if (aVar2 != null) {
                aVar2.d(typefaceC);
            }
            return typefaceC;
        }
        if (z7 && i8 == -1) {
            g gVarF = f(context, aVar, i9);
            if (aVar2 != null) {
                int i10 = gVarF.f6044b;
                if (i10 == 0) {
                    aVar2.b(gVarF.f6043a, handler);
                } else {
                    aVar2.a(i10, handler);
                }
            }
            return gVarF.f6043a;
        }
        a aVar3 = new a(context, aVar, i9, str);
        if (z7) {
            try {
                return ((g) f6025b.e(aVar3, i8)).f6043a;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        C0077b c0077b = aVar2 == null ? null : new C0077b(aVar2, handler);
        synchronized (f6026c) {
            v.g<String, ArrayList<c.d<g>>> gVar = f6027d;
            ArrayList<c.d<g>> arrayList = gVar.get(str);
            if (arrayList != null) {
                if (c0077b != null) {
                    arrayList.add(c0077b);
                }
                return null;
            }
            if (c0077b != null) {
                ArrayList<c.d<g>> arrayList2 = new ArrayList<>();
                arrayList2.add(c0077b);
                gVar.put(str, arrayList2);
            }
            f6025b.d(aVar3, new c(str));
            return null;
        }
    }

    public static ProviderInfo h(PackageManager packageManager, l0.a aVar, Resources resources) throws PackageManager.NameNotFoundException {
        String strD = aVar.d();
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(strD, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + strD);
        }
        if (!providerInfoResolveContentProvider.packageName.equals(aVar.e())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + strD + ", but package was not " + aVar.e());
        }
        List<byte[]> listA = a(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
        Collections.sort(listA, f6028e);
        List<List<byte[]>> listD = d(aVar, resources);
        for (int i8 = 0; i8 < listD.size(); i8++) {
            ArrayList arrayList = new ArrayList(listD.get(i8));
            Collections.sort(arrayList, f6028e);
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
