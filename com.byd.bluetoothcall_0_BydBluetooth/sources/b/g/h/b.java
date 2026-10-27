package b.g.h;

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
import androidx.core.content.c.f;
import b.g.d.k;
import b.g.h.c;
import b.g.j.h;
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

    /* JADX INFO: renamed from: a */
    static final b.d.e<String, Typeface> f2213a = new b.d.e<>(16);

    /* JADX INFO: renamed from: b */
    private static final b.g.h.c f2214b = new b.g.h.c("fonts", 10, 10000);

    /* JADX INFO: renamed from: c */
    static final Object f2215c = new Object();

    /* JADX INFO: renamed from: d */
    static final b.d.g<String, ArrayList<c.d<g>>> f2216d = new b.d.g<>();

    /* JADX INFO: renamed from: e */
    private static final Comparator<byte[]> f2217e = new d();

    /* JADX INFO: compiled from: FontsContractCompat.java */
    class a implements Callable<g> {

        /* JADX INFO: renamed from: b */
        final /* synthetic */ Context f2218b;

        /* JADX INFO: renamed from: c */
        final /* synthetic */ b.g.h.a f2219c;

        /* JADX INFO: renamed from: d */
        final /* synthetic */ int f2220d;

        /* JADX INFO: renamed from: e */
        final /* synthetic */ String f2221e;

        a(Context context, b.g.h.a aVar, int i, String str) {
            this.f2218b = context;
            this.f2219c = aVar;
            this.f2220d = i;
            this.f2221e = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a */
        public g call() throws Exception {
            g gVarF = b.f(this.f2218b, this.f2219c, this.f2220d);
            Typeface typeface = gVarF.f2232a;
            if (typeface != null) {
                b.f2213a.d(this.f2221e, typeface);
            }
            return gVarF;
        }
    }

    /* JADX INFO: renamed from: b.g.h.b$b */
    /* JADX INFO: compiled from: FontsContractCompat.java */
    class C0047b implements c.d<g> {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ f.a f2222a;

        /* JADX INFO: renamed from: b */
        final /* synthetic */ Handler f2223b;

        C0047b(f.a aVar, Handler handler) {
            this.f2222a = aVar;
            this.f2223b = handler;
        }

        @Override // b.g.h.c.d
        /* JADX INFO: renamed from: b */
        public void a(g gVar) {
            if (gVar == null) {
                this.f2222a.a(1, this.f2223b);
                return;
            }
            int i = gVar.f2233b;
            if (i == 0) {
                this.f2222a.b(gVar.f2232a, this.f2223b);
            } else {
                this.f2222a.a(i, this.f2223b);
            }
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    class c implements c.d<g> {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ String f2224a;

        c(String str) {
            this.f2224a = str;
        }

        @Override // b.g.h.c.d
        /* JADX INFO: renamed from: b */
        public void a(g gVar) {
            synchronized (b.f2215c) {
                b.d.g<String, ArrayList<c.d<g>>> gVar2 = b.f2216d;
                ArrayList<c.d<g>> arrayList = gVar2.get(this.f2224a);
                if (arrayList == null) {
                    return;
                }
                gVar2.remove(this.f2224a);
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).a(gVar);
                }
            }
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    class d implements Comparator<byte[]> {
        d() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a */
        public int compare(byte[] bArr, byte[] bArr2) {
            int length;
            int length2;
            if (bArr.length == bArr2.length) {
                for (int i = 0; i < bArr.length; i++) {
                    if (bArr[i] != bArr2[i]) {
                        length = bArr[i];
                        length2 = bArr2[i];
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

        /* JADX INFO: renamed from: a */
        private final int f2225a;

        /* JADX INFO: renamed from: b */
        private final f[] f2226b;

        public e(int i, f[] fVarArr) {
            this.f2225a = i;
            this.f2226b = fVarArr;
        }

        public f[] a() {
            return this.f2226b;
        }

        public int b() {
            return this.f2225a;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class f {

        /* JADX INFO: renamed from: a */
        private final Uri f2227a;

        /* JADX INFO: renamed from: b */
        private final int f2228b;

        /* JADX INFO: renamed from: c */
        private final int f2229c;

        /* JADX INFO: renamed from: d */
        private final boolean f2230d;

        /* JADX INFO: renamed from: e */
        private final int f2231e;

        public f(Uri uri, int i, int i2, boolean z, int i3) {
            this.f2227a = (Uri) h.c(uri);
            this.f2228b = i;
            this.f2229c = i2;
            this.f2230d = z;
            this.f2231e = i3;
        }

        public int a() {
            return this.f2231e;
        }

        public int b() {
            return this.f2228b;
        }

        public Uri c() {
            return this.f2227a;
        }

        public int d() {
            return this.f2229c;
        }

        public boolean e() {
            return this.f2230d;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    private static final class g {

        /* JADX INFO: renamed from: a */
        final Typeface f2232a;

        /* JADX INFO: renamed from: b */
        final int f2233b;

        g(Typeface typeface, int i) {
            this.f2232a = typeface;
            this.f2233b = i;
        }
    }

    private static List<byte[]> a(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    private static boolean b(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static e c(Context context, CancellationSignal cancellationSignal, b.g.h.a aVar) throws PackageManager.NameNotFoundException {
        ProviderInfo providerInfoH = h(context.getPackageManager(), aVar, context.getResources());
        return providerInfoH == null ? new e(1, null) : new e(0, e(context, aVar, providerInfoH.authority, cancellationSignal));
    }

    private static List<List<byte[]>> d(b.g.h.a aVar, Resources resources) {
        return aVar.a() != null ? aVar.a() : androidx.core.content.c.c.c(resources, aVar.b());
    }

    static f[] e(Context context, b.g.h.a aVar, String str, CancellationSignal cancellationSignal) {
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
                    int i = columnIndex != -1 ? cursorQuery.getInt(columnIndex) : 0;
                    arrayList2.add(new f(columnIndex3 == -1 ? ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2)) : ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3)), columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : 0, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, i));
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

    static g f(Context context, b.g.h.a aVar, int i) {
        try {
            e eVarC = c(context, null, aVar);
            if (eVarC.b() != 0) {
                return new g(null, eVarC.b() == 1 ? -2 : -3);
            }
            Typeface typefaceB = b.g.d.d.b(context, null, eVarC.a(), i);
            return new g(typefaceB, typefaceB != null ? 0 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new g(null, -1);
        }
    }

    public static Typeface g(Context context, b.g.h.a aVar, f.a aVar2, Handler handler, boolean z, int i, int i2) {
        String str = aVar.c() + "-" + i2;
        Typeface typefaceC = f2213a.c(str);
        if (typefaceC != null) {
            if (aVar2 != null) {
                aVar2.d(typefaceC);
            }
            return typefaceC;
        }
        if (z && i == -1) {
            g gVarF = f(context, aVar, i2);
            if (aVar2 != null) {
                int i3 = gVarF.f2233b;
                if (i3 == 0) {
                    aVar2.b(gVarF.f2232a, handler);
                } else {
                    aVar2.a(i3, handler);
                }
            }
            return gVarF.f2232a;
        }
        a aVar3 = new a(context, aVar, i2, str);
        if (z) {
            try {
                return ((g) f2214b.e(aVar3, i)).f2232a;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        C0047b c0047b = aVar2 == null ? null : new C0047b(aVar2, handler);
        synchronized (f2215c) {
            b.d.g<String, ArrayList<c.d<g>>> gVar = f2216d;
            ArrayList<c.d<g>> arrayList = gVar.get(str);
            if (arrayList != null) {
                if (c0047b != null) {
                    arrayList.add(c0047b);
                }
                return null;
            }
            if (c0047b != null) {
                ArrayList<c.d<g>> arrayList2 = new ArrayList<>();
                arrayList2.add(c0047b);
                gVar.put(str, arrayList2);
            }
            f2214b.d(aVar3, new c(str));
            return null;
        }
    }

    public static ProviderInfo h(PackageManager packageManager, b.g.h.a aVar, Resources resources) throws PackageManager.NameNotFoundException {
        String strD = aVar.d();
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(strD, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + strD);
        }
        if (!providerInfoResolveContentProvider.packageName.equals(aVar.e())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + strD + ", but package was not " + aVar.e());
        }
        List<byte[]> listA = a(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
        Collections.sort(listA, f2217e);
        List<List<byte[]>> listD = d(aVar, resources);
        for (int i = 0; i < listD.size(); i++) {
            ArrayList arrayList = new ArrayList(listD.get(i));
            Collections.sort(arrayList, f2217e);
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
