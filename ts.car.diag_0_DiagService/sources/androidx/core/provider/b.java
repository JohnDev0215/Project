package androidx.core.provider;

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
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.content.res.e;
import androidx.core.graphics.k;
import androidx.core.provider.c;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final b.a.f<String, Typeface> f510a = new b.a.f<>(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final androidx.core.provider.c f511b = new androidx.core.provider.c("fonts", 10, 10000);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Object f512c = new Object();

    @GuardedBy("sLock")
    static final b.a.g<String, ArrayList<c.d<g>>> d = new b.a.g<>();
    private static final Comparator<byte[]> e = new d();

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Callable<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f513a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ androidx.core.provider.a f514b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f515c;
        final /* synthetic */ String d;

        a(Context context, androidx.core.provider.a aVar, int i, String str) {
            this.f513a = context;
            this.f514b = aVar;
            this.f515c = i;
            this.d = str;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public g call() {
            g gVarE = b.e(this.f513a, this.f514b, this.f515c);
            Typeface typeface = gVarE.f524a;
            if (typeface != null) {
                b.f510a.d(this.d, typeface);
            }
            return gVarE;
        }
    }

    /* JADX INFO: renamed from: androidx.core.provider.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class C0013b implements c.d<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ e.b f516a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Handler f517b;

        C0013b(e.b bVar, Handler handler) {
            this.f516a = bVar;
            this.f517b = handler;
        }

        @Override // androidx.core.provider.c.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            if (gVar == null) {
                this.f516a.a(1, this.f517b);
                return;
            }
            int i = gVar.f525b;
            if (i == 0) {
                this.f516a.b(gVar.f524a, this.f517b);
            } else {
                this.f516a.a(i, this.f517b);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements c.d<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f518a;

        c(String str) {
            this.f518a = str;
        }

        @Override // androidx.core.provider.c.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(g gVar) {
            synchronized (b.f512c) {
                ArrayList<c.d<g>> arrayList = b.d.get(this.f518a);
                if (arrayList == null) {
                    return;
                }
                b.d.remove(this.f518a);
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).a(gVar);
                }
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
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

    /* JADX INFO: compiled from: caradapter-bug */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f519a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final f[] f520b;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public e(int i, @Nullable f[] fVarArr) {
            this.f519a = i;
            this.f520b = fVarArr;
        }

        public f[] a() {
            return this.f520b;
        }

        public int b() {
            return this.f519a;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Uri f521a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f522b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f523c;
        private final boolean d;
        private final int e;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public f(@NonNull Uri uri, @IntRange(from = 0) int i, @IntRange(from = 1, to = 1000) int i2, boolean z, int i3) {
            androidx.core.d.e.b(uri);
            this.f521a = uri;
            this.f522b = i;
            this.f523c = i2;
            this.d = z;
            this.e = i3;
        }

        public int a() {
            return this.e;
        }

        @IntRange(from = 0)
        public int b() {
            return this.f522b;
        }

        @NonNull
        public Uri c() {
            return this.f521a;
        }

        @IntRange(from = 1, to = 1000)
        public int d() {
            return this.f523c;
        }

        public boolean e() {
            return this.d;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Typeface f524a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f525b;

        g(@Nullable Typeface typeface, int i) {
            this.f524a = typeface;
            this.f525b = i;
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

    @NonNull
    public static e c(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull androidx.core.provider.a aVar) throws PackageManager.NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), aVar, context.getResources());
        return provider == null ? new e(1, null) : new e(0, getFontFromProvider(context, aVar, provider.authority, cancellationSignal));
    }

    private static List<List<byte[]>> d(androidx.core.provider.a aVar, Resources resources) {
        return aVar.a() != null ? aVar.a() : FontResourcesParserCompat.c(resources, aVar.b());
    }

    @NonNull
    static g e(Context context, androidx.core.provider.a aVar, int i) {
        try {
            e eVarC = c(context, null, aVar);
            if (eVarC.b() != 0) {
                return new g(null, eVarC.b() == 1 ? -2 : -3);
            }
            Typeface typefaceB = androidx.core.graphics.d.b(context, null, eVarC.a(), i);
            return new g(typefaceB, typefaceB != null ? 0 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new g(null, -1);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Typeface f(Context context, androidx.core.provider.a aVar, @Nullable e.b bVar, @Nullable Handler handler, boolean z, int i, int i2) {
        String str = aVar.c() + "-" + i2;
        Typeface typefaceC = f510a.c(str);
        if (typefaceC != null) {
            if (bVar != null) {
                bVar.d(typefaceC);
            }
            return typefaceC;
        }
        if (z && i == -1) {
            g gVarE = e(context, aVar, i2);
            if (bVar != null) {
                int i3 = gVarE.f525b;
                if (i3 == 0) {
                    bVar.b(gVarE.f524a, handler);
                } else {
                    bVar.a(i3, handler);
                }
            }
            return gVarE.f524a;
        }
        a aVar2 = new a(context, aVar, i2, str);
        if (z) {
            try {
                return ((g) f511b.e(aVar2, i)).f524a;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        C0013b c0013b = bVar == null ? null : new C0013b(bVar, handler);
        synchronized (f512c) {
            ArrayList<c.d<g>> arrayList = d.get(str);
            if (arrayList != null) {
                if (c0013b != null) {
                    arrayList.add(c0013b);
                }
                return null;
            }
            if (c0013b != null) {
                ArrayList<c.d<g>> arrayList2 = new ArrayList<>();
                arrayList2.add(c0013b);
                d.put(str, arrayList2);
            }
            f511b.d(aVar2, new c(str));
            return null;
        }
    }

    @RequiresApi(19)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Map<Uri, ByteBuffer> g(Context context, f[] fVarArr, CancellationSignal cancellationSignal) {
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

    @NonNull
    @VisibleForTesting
    static f[] getFontFromProvider(Context context, androidx.core.provider.a aVar, String str, CancellationSignal cancellationSignal) {
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

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @VisibleForTesting
    public static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull androidx.core.provider.a aVar, @Nullable Resources resources) throws PackageManager.NameNotFoundException {
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
}
