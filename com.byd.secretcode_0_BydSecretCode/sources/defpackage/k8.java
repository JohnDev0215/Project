package defpackage;

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
import defpackage.l7;
import defpackage.l8;
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
public class k8 {
    public static final e4<String, Typeface> a = new e4<>(16);
    public static final l8 b = new l8("fonts", 10, 10000);
    public static final Object c = new Object();
    public static final g4<String, ArrayList<l8.d<g>>> d = new g4<>();
    public static final Comparator<byte[]> e = new d();

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class a implements Callable<g> {
        public final /* synthetic */ Context a;
        public final /* synthetic */ j8 b;
        public final /* synthetic */ int c;
        public final /* synthetic */ String d;

        public a(Context context, j8 j8Var, int i, String str) {
            this.a = context;
            this.b = j8Var;
            this.c = i;
            this.d = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public g call() throws Exception {
            g gVarF = k8.f(this.a, this.b, this.c);
            Typeface typeface = gVarF.a;
            if (typeface != null) {
                k8.a.d(this.d, typeface);
            }
            return gVarF;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class b implements l8.d<g> {
        public final /* synthetic */ l7.a a;
        public final /* synthetic */ Handler b;

        public b(l7.a aVar, Handler handler) {
            this.a = aVar;
            this.b = handler;
        }

        @Override // l8.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            if (gVar == null) {
                this.a.a(1, this.b);
                return;
            }
            int i = gVar.b;
            if (i == 0) {
                this.a.b(gVar.a, this.b);
            } else {
                this.a.a(i, this.b);
            }
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public class c implements l8.d<g> {
        public final /* synthetic */ String a;

        public c(String str) {
            this.a = str;
        }

        @Override // l8.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            synchronized (k8.c) {
                g4<String, ArrayList<l8.d<g>>> g4Var = k8.d;
                ArrayList<l8.d<g>> arrayList = g4Var.get(this.a);
                if (arrayList == null) {
                    return;
                }
                g4Var.remove(this.a);
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).a(gVar);
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
        public final int a;
        public final f[] b;

        public e(int i, f[] fVarArr) {
            this.a = i;
            this.b = fVarArr;
        }

        public f[] a() {
            return this.b;
        }

        public int b() {
            return this.a;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class f {
        public final Uri a;
        public final int b;
        public final int c;
        public final boolean d;
        public final int e;

        public f(Uri uri, int i, int i2, boolean z, int i3) {
            this.a = (Uri) z8.c(uri);
            this.b = i;
            this.c = i2;
            this.d = z;
            this.e = i3;
        }

        public int a() {
            return this.e;
        }

        public int b() {
            return this.b;
        }

        public Uri c() {
            return this.a;
        }

        public int d() {
            return this.c;
        }

        public boolean e() {
            return this.d;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static final class g {
        public final Typeface a;
        public final int b;

        public g(Typeface typeface, int i) {
            this.a = typeface;
            this.b = i;
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
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static e c(Context context, CancellationSignal cancellationSignal, j8 j8Var) throws PackageManager.NameNotFoundException {
        ProviderInfo providerInfoH = h(context.getPackageManager(), j8Var, context.getResources());
        return providerInfoH == null ? new e(1, null) : new e(0, e(context, j8Var, providerInfoH.authority, cancellationSignal));
    }

    public static List<List<byte[]>> d(j8 j8Var, Resources resources) {
        return j8Var.a() != null ? j8Var.a() : i7.c(resources, j8Var.b());
    }

    public static f[] e(Context context, j8 j8Var, String str, CancellationSignal cancellationSignal) {
        ArrayList arrayList = new ArrayList();
        Uri uriBuild = new Uri.Builder().scheme("content").authority(str).build();
        Uri uriBuild2 = new Uri.Builder().scheme("content").authority(str).appendPath("file").build();
        Cursor cursorQuery = null;
        try {
            cursorQuery = Build.VERSION.SDK_INT > 16 ? context.getContentResolver().query(uriBuild, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{j8Var.f()}, null, cancellationSignal) : context.getContentResolver().query(uriBuild, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{j8Var.f()}, null);
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

    public static g f(Context context, j8 j8Var, int i) {
        try {
            e eVarC = c(context, null, j8Var);
            if (eVarC.b() != 0) {
                return new g(null, eVarC.b() == 1 ? -2 : -3);
            }
            Typeface typefaceB = q7.b(context, null, eVarC.a(), i);
            return new g(typefaceB, typefaceB != null ? 0 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new g(null, -1);
        }
    }

    public static Typeface g(Context context, j8 j8Var, l7.a aVar, Handler handler, boolean z, int i, int i2) {
        String str = j8Var.c() + "-" + i2;
        Typeface typefaceC = a.c(str);
        if (typefaceC != null) {
            if (aVar != null) {
                aVar.d(typefaceC);
            }
            return typefaceC;
        }
        if (z && i == -1) {
            g gVarF = f(context, j8Var, i2);
            if (aVar != null) {
                int i3 = gVarF.b;
                if (i3 == 0) {
                    aVar.b(gVarF.a, handler);
                } else {
                    aVar.a(i3, handler);
                }
            }
            return gVarF.a;
        }
        a aVar2 = new a(context, j8Var, i2, str);
        if (z) {
            try {
                return ((g) b.e(aVar2, i)).a;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        b bVar = aVar == null ? null : new b(aVar, handler);
        synchronized (c) {
            g4<String, ArrayList<l8.d<g>>> g4Var = d;
            ArrayList<l8.d<g>> arrayList = g4Var.get(str);
            if (arrayList != null) {
                if (bVar != null) {
                    arrayList.add(bVar);
                }
                return null;
            }
            if (bVar != null) {
                ArrayList<l8.d<g>> arrayList2 = new ArrayList<>();
                arrayList2.add(bVar);
                g4Var.put(str, arrayList2);
            }
            b.d(aVar2, new c(str));
            return null;
        }
    }

    public static ProviderInfo h(PackageManager packageManager, j8 j8Var, Resources resources) throws PackageManager.NameNotFoundException {
        String strD = j8Var.d();
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(strD, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + strD);
        }
        if (!providerInfoResolveContentProvider.packageName.equals(j8Var.e())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + strD + ", but package was not " + j8Var.e());
        }
        List<byte[]> listA = a(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
        Collections.sort(listA, e);
        List<List<byte[]>> listD = d(j8Var, resources);
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
                    map.put(uriC, x7.f(context, cancellationSignal, uriC));
                }
            }
        }
        return Collections.unmodifiableMap(map);
    }
}
