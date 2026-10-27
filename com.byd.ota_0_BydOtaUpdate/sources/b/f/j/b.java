package b.f.j;

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
import b.f.f.c.f;
import b.f.g.k;
import b.f.j.c;
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
    public static final b.d.e<String, Typeface> f1682a = new b.d.e<>(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b.f.j.c f1683b = new b.f.j.c("fonts", 10, 10000);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f1684c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b.d.g<String, ArrayList<c.d<g>>> f1685d = new b.d.g<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Comparator<byte[]> f1686e = new d();

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class a implements Callable<g> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Context f1687c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ b.f.j.a f1688d;
        public final /* synthetic */ int s;
        public final /* synthetic */ String x;

        public a(Context context, b.f.j.a aVar, int i2, String str) {
            this.f1687c = context;
            this.f1688d = aVar;
            this.s = i2;
            this.x = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public g call() throws Exception {
            g gVarF = b.f(this.f1687c, this.f1688d, this.s);
            Typeface typeface = gVarF.f1699a;
            if (typeface != null) {
                b.f1682a.d(this.x, typeface);
            }
            return gVarF;
        }
    }

    /* JADX INFO: renamed from: b.f.j.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class C0024b implements c.d<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ f.a f1689a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Handler f1690b;

        public C0024b(f.a aVar, Handler handler) {
            this.f1689a = aVar;
            this.f1690b = handler;
        }

        @Override // b.f.j.c.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            if (gVar == null) {
                this.f1689a.a(1, this.f1690b);
                return;
            }
            int i2 = gVar.f1700b;
            if (i2 == 0) {
                this.f1689a.b(gVar.f1699a, this.f1690b);
            } else {
                this.f1689a.a(i2, this.f1690b);
            }
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class c implements c.d<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f1691a;

        public c(String str) {
            this.f1691a = str;
        }

        @Override // b.f.j.c.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            synchronized (b.f1684c) {
                b.d.g<String, ArrayList<c.d<g>>> gVar2 = b.f1685d;
                ArrayList<c.d<g>> arrayList = gVar2.get(this.f1691a);
                if (arrayList == null) {
                    return;
                }
                gVar2.remove(this.f1691a);
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
        public final int f1692a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final f[] f1693b;

        public e(int i2, f[] fVarArr) {
            this.f1692a = i2;
            this.f1693b = fVarArr;
        }

        public f[] a() {
            return this.f1693b;
        }

        public int b() {
            return this.f1692a;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Uri f1694a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1695b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1696c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f1697d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f1698e;

        public f(Uri uri, int i2, int i3, boolean z, int i4) {
            this.f1694a = (Uri) b.f.l.f.b(uri);
            this.f1695b = i2;
            this.f1696c = i3;
            this.f1697d = z;
            this.f1698e = i4;
        }

        public int a() {
            return this.f1698e;
        }

        public int b() {
            return this.f1695b;
        }

        public Uri c() {
            return this.f1694a;
        }

        public int d() {
            return this.f1696c;
        }

        public boolean e() {
            return this.f1697d;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Typeface f1699a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1700b;

        public g(Typeface typeface, int i2) {
            this.f1699a = typeface;
            this.f1700b = i2;
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

    public static e c(Context context, CancellationSignal cancellationSignal, b.f.j.a aVar) throws PackageManager.NameNotFoundException {
        ProviderInfo providerInfoH = h(context.getPackageManager(), aVar, context.getResources());
        return providerInfoH == null ? new e(1, null) : new e(0, e(context, aVar, providerInfoH.authority, cancellationSignal));
    }

    public static List<List<byte[]>> d(b.f.j.a aVar, Resources resources) {
        return aVar.a() != null ? aVar.a() : b.f.f.c.c.c(resources, aVar.b());
    }

    public static f[] e(Context context, b.f.j.a aVar, String str, CancellationSignal cancellationSignal) {
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

    public static g f(Context context, b.f.j.a aVar, int i2) {
        try {
            e eVarC = c(context, null, aVar);
            if (eVarC.b() != 0) {
                return new g(null, eVarC.b() == 1 ? -2 : -3);
            }
            Typeface typefaceB = b.f.g.d.b(context, null, eVarC.a(), i2);
            return new g(typefaceB, typefaceB != null ? 0 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new g(null, -1);
        }
    }

    public static Typeface g(Context context, b.f.j.a aVar, f.a aVar2, Handler handler, boolean z, int i2, int i3) {
        String str = aVar.c() + "-" + i3;
        Typeface typefaceC = f1682a.c(str);
        if (typefaceC != null) {
            if (aVar2 != null) {
                aVar2.d(typefaceC);
            }
            return typefaceC;
        }
        if (z && i2 == -1) {
            g gVarF = f(context, aVar, i3);
            if (aVar2 != null) {
                int i4 = gVarF.f1700b;
                if (i4 == 0) {
                    aVar2.b(gVarF.f1699a, handler);
                } else {
                    aVar2.a(i4, handler);
                }
            }
            return gVarF.f1699a;
        }
        a aVar3 = new a(context, aVar, i3, str);
        if (z) {
            try {
                return ((g) f1683b.e(aVar3, i2)).f1699a;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        C0024b c0024b = aVar2 == null ? null : new C0024b(aVar2, handler);
        synchronized (f1684c) {
            b.d.g<String, ArrayList<c.d<g>>> gVar = f1685d;
            ArrayList<c.d<g>> arrayList = gVar.get(str);
            if (arrayList != null) {
                if (c0024b != null) {
                    arrayList.add(c0024b);
                }
                return null;
            }
            if (c0024b != null) {
                ArrayList<c.d<g>> arrayList2 = new ArrayList<>();
                arrayList2.add(c0024b);
                gVar.put(str, arrayList2);
            }
            f1683b.d(aVar3, new c(str));
            return null;
        }
    }

    public static ProviderInfo h(PackageManager packageManager, b.f.j.a aVar, Resources resources) throws PackageManager.NameNotFoundException {
        String strD = aVar.d();
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(strD, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + strD);
        }
        if (!providerInfoResolveContentProvider.packageName.equals(aVar.e())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + strD + ", but package was not " + aVar.e());
        }
        List<byte[]> listA = a(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
        Collections.sort(listA, f1686e);
        List<List<byte[]>> listD = d(aVar, resources);
        for (int i2 = 0; i2 < listD.size(); i2++) {
            ArrayList arrayList = new ArrayList(listD.get(i2));
            Collections.sort(arrayList, f1686e);
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
