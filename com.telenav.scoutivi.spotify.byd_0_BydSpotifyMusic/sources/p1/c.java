package p1;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import o1.d;
import o1.g;

/* JADX INFO: compiled from: ThumbFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements o1.d<InputStream> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Uri f8807e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final e f8808g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private InputStream f8809h;

    /* JADX INFO: compiled from: ThumbFetcher.java */
    static class a implements d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final String[] f8810b = {"_data"};

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ContentResolver f8811a;

        a(ContentResolver contentResolver) {
            this.f8811a = contentResolver;
        }

        @Override // p1.d
        public Cursor a(Uri uri) {
            return this.f8811a.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, f8810b, "kind = 1 AND image_id = ?", new String[]{uri.getLastPathSegment()}, null);
        }
    }

    /* JADX INFO: compiled from: ThumbFetcher.java */
    static class b implements d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final String[] f8812b = {"_data"};

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ContentResolver f8813a;

        b(ContentResolver contentResolver) {
            this.f8813a = contentResolver;
        }

        @Override // p1.d
        public Cursor a(Uri uri) {
            return this.f8813a.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, f8812b, "kind = 1 AND video_id = ?", new String[]{uri.getLastPathSegment()}, null);
        }
    }

    c(Uri uri, e eVar) {
        this.f8807e = uri;
        this.f8808g = eVar;
    }

    private static c d(Context context, Uri uri, d dVar) {
        return new c(uri, new e(com.bumptech.glide.c.c(context).j().g(), dVar, com.bumptech.glide.c.c(context).e(), context.getContentResolver()));
    }

    public static c f(Context context, Uri uri) {
        return d(context, uri, new a(context.getContentResolver()));
    }

    public static c g(Context context, Uri uri) {
        return d(context, uri, new b(context.getContentResolver()));
    }

    private InputStream h() throws Throwable {
        InputStream inputStreamD = this.f8808g.d(this.f8807e);
        int iA = inputStreamD != null ? this.f8808g.a(this.f8807e) : -1;
        return iA != -1 ? new g(inputStreamD, iA) : inputStreamD;
    }

    @Override // o1.d
    public Class<InputStream> a() {
        return InputStream.class;
    }

    @Override // o1.d
    public void b() {
        InputStream inputStream = this.f8809h;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // o1.d
    public void c(com.bumptech.glide.g gVar, d.a<? super InputStream> aVar) throws Throwable {
        try {
            InputStream inputStreamH = h();
            this.f8809h = inputStreamH;
            aVar.f(inputStreamH);
        } catch (FileNotFoundException e7) {
            if (Log.isLoggable("MediaStoreThumbFetcher", 3)) {
                Log.d("MediaStoreThumbFetcher", "Failed to find thumbnail file", e7);
            }
            aVar.d(e7);
        }
    }

    @Override // o1.d
    public void cancel() {
    }

    @Override // o1.d
    public n1.a e() {
        return n1.a.LOCAL;
    }
}
