package l4;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.bumptech.glide.f;
import com.bumptech.glide.load.data.e;
import e4.h;
import java.io.File;
import java.io.FileNotFoundException;
import k4.p;
import k4.q;

/* JADX INFO: renamed from: l4.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1772c implements e {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final String[] f18622v = {"_data"};
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final q f18623m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final q f18624n;
    public final Uri o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f18625p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f18626q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final h f18627r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Class f18628s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public volatile boolean f18629t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public volatile e f18630u;

    public C1772c(Context context, q qVar, q qVar2, Uri uri, int i7, int i10, h hVar, Class cls) {
        this.l = context.getApplicationContext();
        this.f18623m = qVar;
        this.f18624n = qVar2;
        this.o = uri;
        this.f18625p = i7;
        this.f18626q = i10;
        this.f18627r = hVar;
        this.f18628s = cls;
    }

    @Override // com.bumptech.glide.load.data.e
    public final Class a() {
        return this.f18628s;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void b() {
        e eVar = this.f18630u;
        if (eVar != null) {
            eVar.b();
        }
    }

    public final e c() throws Throwable {
        p pVarA;
        boolean zIsExternalStorageLegacy = Environment.isExternalStorageLegacy();
        Cursor cursor = null;
        Context context = this.l;
        h hVar = this.f18627r;
        int i7 = this.f18626q;
        int i10 = this.f18625p;
        if (zIsExternalStorageLegacy) {
            Uri uri = this.o;
            try {
                Cursor cursorQuery = context.getContentResolver().query(uri, f18622v, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                            if (TextUtils.isEmpty(string)) {
                                throw new FileNotFoundException("File path was empty in media store for: " + uri);
                            }
                            File file = new File(string);
                            cursorQuery.close();
                            pVarA = this.f18623m.a(file, i10, i7, hVar);
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                throw new FileNotFoundException("Failed to media store entry for: " + uri);
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            Uri requireOriginal = this.o;
            boolean zF = com.bumptech.glide.d.F(requireOriginal);
            q qVar = this.f18624n;
            if (zF && requireOriginal.getPathSegments().contains("picker")) {
                pVarA = qVar.a(requireOriginal, i10, i7, hVar);
            } else {
                if (context.checkSelfPermission("android.permission.ACCESS_MEDIA_LOCATION") == 0) {
                    requireOriginal = MediaStore.setRequireOriginal(requireOriginal);
                }
                pVarA = qVar.a(requireOriginal, i10, i7, hVar);
            }
        }
        if (pVarA != null) {
            return pVarA.c;
        }
        return null;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void cancel() {
        this.f18629t = true;
        e eVar = this.f18630u;
        if (eVar != null) {
            eVar.cancel();
        }
    }

    @Override // com.bumptech.glide.load.data.e
    public final int e() {
        return 1;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void f(f fVar, com.bumptech.glide.load.data.d dVar) throws Throwable {
        try {
            e eVarC = c();
            if (eVarC == null) {
                dVar.c(new IllegalArgumentException("Failed to build fetcher for: " + this.o));
            } else {
                this.f18630u = eVarC;
                if (this.f18629t) {
                    cancel();
                } else {
                    eVarC.f(fVar, dVar);
                }
            }
        } catch (FileNotFoundException e10) {
            dVar.c(e10);
        }
    }
}
