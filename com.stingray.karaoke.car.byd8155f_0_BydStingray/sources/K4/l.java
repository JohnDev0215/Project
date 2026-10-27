package k4;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class l implements com.bumptech.glide.load.data.e {
    public static final String[] o = {"_data"};
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f18199m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f18200n;

    public /* synthetic */ l(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f18199m = obj;
        this.f18200n = obj2;
    }

    private final void c() {
    }

    private final void d() {
    }

    private final void g() {
    }

    private final void h() {
    }

    @Override // com.bumptech.glide.load.data.e
    public final Class a() {
        switch (this.l) {
            case 0:
                return File.class;
            default:
                return ((z) this.f18200n).b();
        }
    }

    @Override // com.bumptech.glide.load.data.e
    public final void b() {
        int i7 = this.l;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void cancel() {
        int i7 = this.l;
    }

    @Override // com.bumptech.glide.load.data.e
    public final int e() {
        switch (this.l) {
        }
        return 1;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void f(com.bumptech.glide.f fVar, com.bumptech.glide.load.data.d dVar) {
        Object objWrap;
        switch (this.l) {
            case 0:
                Cursor cursorQuery = ((Context) this.f18199m).getContentResolver().query((Uri) this.f18200n, o, null, null, null);
                if (cursorQuery != null) {
                    try {
                        string = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data")) : null;
                    } finally {
                        cursorQuery.close();
                    }
                    break;
                }
                if (!TextUtils.isEmpty(string)) {
                    dVar.d(new File(string));
                    return;
                }
                dVar.c(new FileNotFoundException("Failed to find file path for: " + ((Uri) this.f18200n)));
                return;
            default:
                z zVar = (z) this.f18200n;
                byte[] bArr = (byte[]) this.f18199m;
                switch (zVar.l) {
                    case 1:
                        objWrap = ByteBuffer.wrap(bArr);
                        break;
                    default:
                        objWrap = new ByteArrayInputStream(bArr);
                        break;
                }
                dVar.d(objWrap);
                return;
        }
    }
}
