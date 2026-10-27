package i0;

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
import c0.f;
import d0.k;
import i0.c;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import k0.h;

/* JADX INFO: compiled from: FontsContractCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final s.e<String, Typeface> f3230a = new s.e<>(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final i0.c f3231b = new i0.c("fonts", 10, 10000);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f3232c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final s.g<String, ArrayList<c.d<g>>> f3233d = new s.g<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Comparator<byte[]> f3234e = new d();

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class a implements Callable<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3235a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ i0.a f3236b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f3237c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f3238d;

        public a(Context context, i0.a aVar, int i3, String str) {
            this.f3235a = context;
            this.f3236b = aVar;
            this.f3237c = i3;
            this.f3238d = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public g call() throws Exception {
            g gVarF = b.f(this.f3235a, this.f3236b, this.f3237c);
            Typeface typeface = gVarF.f3249a;
            if (typeface != null) {
                b.f3230a.d(this.f3238d, typeface);
            }
            return gVarF;
        }
    }

    /* JADX INFO: renamed from: i0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class C0018b implements c.d<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ f.a f3239a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Handler f3240b;

        public C0018b(f.a aVar, Handler handler) {
            this.f3239a = aVar;
            this.f3240b = handler;
        }

        @Override // i0.c.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            if (gVar == null) {
                this.f3239a.a(1, this.f3240b);
                return;
            }
            int i3 = gVar.f3250b;
            if (i3 == 0) {
                this.f3239a.b(gVar.f3249a, this.f3240b);
            } else {
                this.f3239a.a(i3, this.f3240b);
            }
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class c implements c.d<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3241a;

        public c(String str) {
            this.f3241a = str;
        }

        @Override // i0.c.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            synchronized (b.f3232c) {
                s.g<String, ArrayList<c.d<g>>> gVar2 = b.f3233d;
                ArrayList<c.d<g>> arrayList = gVar2.get(this.f3241a);
                if (arrayList == null) {
                    return;
                }
                gVar2.remove(this.f3241a);
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    arrayList.get(i3).a(gVar);
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
                for (int i3 = 0; i3 < bArr.length; i3++) {
                    if (bArr[i3] != bArr2[i3]) {
                        length = bArr[i3];
                        length2 = bArr2[i3];
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
        public final int f3242a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final f[] f3243b;

        public e(int i3, f[] fVarArr) {
            this.f3242a = i3;
            this.f3243b = fVarArr;
        }

        public f[] a() {
            return this.f3243b;
        }

        public int b() {
            return this.f3242a;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Uri f3244a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f3245b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f3246c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f3247d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f3248e;

        public f(Uri uri, int i3, int i4, boolean z3, int i5) {
            this.f3244a = (Uri) h.c(uri);
            this.f3245b = i3;
            this.f3246c = i4;
            this.f3247d = z3;
            this.f3248e = i5;
        }

        public int a() {
            return this.f3248e;
        }

        public int b() {
            return this.f3245b;
        }

        public Uri c() {
            return this.f3244a;
        }

        public int d() {
            return this.f3246c;
        }

        public boolean e() {
            return this.f3247d;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Typeface f3249a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f3250b;

        public g(Typeface typeface, int i3) {
            this.f3249a = typeface;
            this.f3250b = i3;
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
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (!Arrays.equals(list.get(i3), list2.get(i3))) {
                return false;
            }
        }
        return true;
    }

    public static e c(Context context, CancellationSignal cancellationSignal, i0.a aVar) throws PackageManager.NameNotFoundException {
        ProviderInfo providerInfoH = h(context.getPackageManager(), aVar, context.getResources());
        return providerInfoH == null ? new e(1, null) : new e(0, e(context, aVar, providerInfoH.authority, cancellationSignal));
    }

    public static List<List<byte[]>> d(i0.a aVar, Resources resources) {
        return aVar.a() != null ? aVar.a() : c0.c.c(resources, aVar.b());
    }

    public static f[] e(Context context, i0.a aVar, String str, CancellationSignal cancellationSignal) {
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
                    int i3 = columnIndex != -1 ? cursorQuery.getInt(columnIndex) : 0;
                    arrayList2.add(new f(columnIndex3 == -1 ? ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2)) : ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3)), columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : 0, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, i3));
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

    public static g f(Context context, i0.a aVar, int i3) {
        try {
            e eVarC = c(context, null, aVar);
            if (eVarC.b() != 0) {
                return new g(null, eVarC.b() == 1 ? -2 : -3);
            }
            Typeface typefaceB = d0.d.b(context, null, eVarC.a(), i3);
            return new g(typefaceB, typefaceB != null ? 0 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new g(null, -1);
        }
    }

    public static Typeface g(Context context, i0.a aVar, f.a aVar2, Handler handler, boolean z3, int i3, int i4) {
        String str = aVar.c() + "-" + i4;
        Typeface typefaceC = f3230a.c(str);
        if (typefaceC != null) {
            if (aVar2 != null) {
                aVar2.d(typefaceC);
            }
            return typefaceC;
        }
        if (z3 && i3 == -1) {
            g gVarF = f(context, aVar, i4);
            if (aVar2 != null) {
                int i5 = gVarF.f3250b;
                if (i5 == 0) {
                    aVar2.b(gVarF.f3249a, handler);
                } else {
                    aVar2.a(i5, handler);
                }
            }
            return gVarF.f3249a;
        }
        a aVar3 = new a(context, aVar, i4, str);
        if (z3) {
            try {
                return ((g) f3231b.e(aVar3, i3)).f3249a;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        C0018b c0018b = aVar2 == null ? null : new C0018b(aVar2, handler);
        synchronized (f3232c) {
            s.g<String, ArrayList<c.d<g>>> gVar = f3233d;
            ArrayList<c.d<g>> arrayList = gVar.get(str);
            if (arrayList != null) {
                if (c0018b != null) {
                    arrayList.add(c0018b);
                }
                return null;
            }
            if (c0018b != null) {
                ArrayList<c.d<g>> arrayList2 = new ArrayList<>();
                arrayList2.add(c0018b);
                gVar.put(str, arrayList2);
            }
            f3231b.d(aVar3, new c(str));
            return null;
        }
    }

    public static ProviderInfo h(PackageManager packageManager, i0.a aVar, Resources resources) throws PackageManager.NameNotFoundException {
        String strD = aVar.d();
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(strD, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + strD);
        }
        if (!providerInfoResolveContentProvider.packageName.equals(aVar.e())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + strD + ", but package was not " + aVar.e());
        }
        List<byte[]> listA = a(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
        Collections.sort(listA, f3234e);
        List<List<byte[]>> listD = d(aVar, resources);
        for (int i3 = 0; i3 < listD.size(); i3++) {
            ArrayList arrayList = new ArrayList(listD.get(i3));
            Collections.sort(arrayList, f3234e);
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
