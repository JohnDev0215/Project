package a.g.h;

import a.g.d.k;
import a.g.h.c;
import a.g.j.h;
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
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final a.d.e<String, Typeface> f298a = new a.d.e<>(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final a.g.h.c f299b = new a.g.h.c("fonts", 10, 10000);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Object f300c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final a.d.g<String, ArrayList<c.d<g>>> f301d = new a.d.g<>();
    private static final Comparator<byte[]> e = new d();

    class a implements Callable<g> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f302b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ a.g.h.a f303c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f304d;
        final /* synthetic */ String e;

        a(Context context, a.g.h.a aVar, int i, String str) {
            this.f302b = context;
            this.f303c = aVar;
            this.f304d = i;
            this.e = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public g call() {
            g gVarF = b.f(this.f302b, this.f303c, this.f304d);
            Typeface typeface = gVarF.f314a;
            if (typeface != null) {
                b.f298a.d(this.e, typeface);
            }
            return gVarF;
        }
    }

    /* JADX INFO: renamed from: a.g.h.b$b, reason: collision with other inner class name */
    class C0007b implements c.d<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ f.a f305a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Handler f306b;

        C0007b(f.a aVar, Handler handler) {
            this.f305a = aVar;
            this.f306b = handler;
        }

        @Override // a.g.h.c.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            int i;
            f.a aVar;
            if (gVar == null) {
                aVar = this.f305a;
                i = 1;
            } else {
                i = gVar.f315b;
                if (i == 0) {
                    this.f305a.b(gVar.f314a, this.f306b);
                    return;
                }
                aVar = this.f305a;
            }
            aVar.a(i, this.f306b);
        }
    }

    class c implements c.d<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f307a;

        c(String str) {
            this.f307a = str;
        }

        @Override // a.g.h.c.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            synchronized (b.f300c) {
                a.d.g<String, ArrayList<c.d<g>>> gVar2 = b.f301d;
                ArrayList<c.d<g>> arrayList = gVar2.get(this.f307a);
                if (arrayList == null) {
                    return;
                }
                gVar2.remove(this.f307a);
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).a(gVar);
                }
            }
        }
    }

    class d implements Comparator<byte[]> {
        d() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
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

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f308a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final f[] f309b;

        public e(int i, f[] fVarArr) {
            this.f308a = i;
            this.f309b = fVarArr;
        }

        public f[] a() {
            return this.f309b;
        }

        public int b() {
            return this.f308a;
        }
    }

    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Uri f310a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f311b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f312c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f313d;
        private final int e;

        public f(Uri uri, int i, int i2, boolean z, int i3) {
            this.f310a = (Uri) h.c(uri);
            this.f311b = i;
            this.f312c = i2;
            this.f313d = z;
            this.e = i3;
        }

        public int a() {
            return this.e;
        }

        public int b() {
            return this.f311b;
        }

        public Uri c() {
            return this.f310a;
        }

        public int d() {
            return this.f312c;
        }

        public boolean e() {
            return this.f313d;
        }
    }

    private static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Typeface f314a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f315b;

        g(Typeface typeface, int i) {
            this.f314a = typeface;
            this.f315b = i;
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

    public static e c(Context context, CancellationSignal cancellationSignal, a.g.h.a aVar) throws PackageManager.NameNotFoundException {
        ProviderInfo providerInfoH = h(context.getPackageManager(), aVar, context.getResources());
        return providerInfoH == null ? new e(1, null) : new e(0, e(context, aVar, providerInfoH.authority, cancellationSignal));
    }

    private static List<List<byte[]>> d(a.g.h.a aVar, Resources resources) {
        return aVar.a() != null ? aVar.a() : androidx.core.content.c.c.c(resources, aVar.b());
    }

    static f[] e(Context context, a.g.h.a aVar, String str, CancellationSignal cancellationSignal) {
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

    static g f(Context context, a.g.h.a aVar, int i) {
        try {
            e eVarC = c(context, null, aVar);
            if (eVarC.b() != 0) {
                return new g(null, eVarC.b() == 1 ? -2 : -3);
            }
            Typeface typefaceB = a.g.d.d.b(context, null, eVarC.a(), i);
            return new g(typefaceB, typefaceB != null ? 0 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new g(null, -1);
        }
    }

    public static Typeface g(Context context, a.g.h.a aVar, f.a aVar2, Handler handler, boolean z, int i, int i2) {
        String str = aVar.c() + "-" + i2;
        Typeface typefaceC = f298a.c(str);
        if (typefaceC != null) {
            if (aVar2 != null) {
                aVar2.d(typefaceC);
            }
            return typefaceC;
        }
        if (z && i == -1) {
            g gVarF = f(context, aVar, i2);
            if (aVar2 != null) {
                int i3 = gVarF.f315b;
                if (i3 == 0) {
                    aVar2.b(gVarF.f314a, handler);
                } else {
                    aVar2.a(i3, handler);
                }
            }
            return gVarF.f314a;
        }
        a aVar3 = new a(context, aVar, i2, str);
        if (z) {
            try {
                return ((g) f299b.e(aVar3, i)).f314a;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        C0007b c0007b = aVar2 == null ? null : new C0007b(aVar2, handler);
        synchronized (f300c) {
            a.d.g<String, ArrayList<c.d<g>>> gVar = f301d;
            ArrayList<c.d<g>> arrayList = gVar.get(str);
            if (arrayList != null) {
                if (c0007b != null) {
                    arrayList.add(c0007b);
                }
                return null;
            }
            if (c0007b != null) {
                ArrayList<c.d<g>> arrayList2 = new ArrayList<>();
                arrayList2.add(c0007b);
                gVar.put(str, arrayList2);
            }
            f299b.d(aVar3, new c(str));
            return null;
        }
    }

    public static ProviderInfo h(PackageManager packageManager, a.g.h.a aVar, Resources resources) throws PackageManager.NameNotFoundException {
        String strD = aVar.d();
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(strD, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + strD);
        }
        if (!providerInfoResolveContentProvider.packageName.equals(aVar.e())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + strD + ", but package was not " + aVar.e());
        }
        List<byte[]> listA = a(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
        Collections.sort(listA, e);
        List<List<byte[]>> listD = d(aVar, resources);
        for (int i = 0; i < listD.size(); i++) {
            ArrayList arrayList = new ArrayList(listD.get(i));
            Collections.sort(arrayList, e);
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
