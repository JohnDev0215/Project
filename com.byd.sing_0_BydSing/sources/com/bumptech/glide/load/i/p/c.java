package com.bumptech.glide.load.i.p;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.i.d;
import com.bumptech.glide.load.i.g;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: ThumbFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements com.bumptech.glide.load.i.d<InputStream> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Uri f2941b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e f2942c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private InputStream f2943d;

    /* JADX INFO: compiled from: ThumbFetcher.java */
    static class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final String[] f2944a = {"_data"};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ContentResolver f2945b;

        a(ContentResolver contentResolver) {
            this.f2945b = contentResolver;
        }

        @Override // com.bumptech.glide.load.i.p.d
        public Cursor a(Uri uri) {
            return this.f2945b.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, f2944a, "kind = 1 AND image_id = ?", new String[]{uri.getLastPathSegment()}, null);
        }
    }

    /* JADX INFO: compiled from: ThumbFetcher.java */
    static class b implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final String[] f2946a = {"_data"};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ContentResolver f2947b;

        b(ContentResolver contentResolver) {
            this.f2947b = contentResolver;
        }

        @Override // com.bumptech.glide.load.i.p.d
        public Cursor a(Uri uri) {
            return this.f2947b.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, f2946a, "kind = 1 AND video_id = ?", new String[]{uri.getLastPathSegment()}, null);
        }
    }

    c(Uri uri, e eVar) {
        this.f2941b = uri;
        this.f2942c = eVar;
    }

    private static c c(Context context, Uri uri, d dVar) {
        return new c(uri, new e(com.bumptech.glide.b.c(context).j().g(), dVar, com.bumptech.glide.b.c(context).e(), context.getContentResolver()));
    }

    public static c d(Context context, Uri uri) {
        return c(context, uri, new a(context.getContentResolver()));
    }

    public static c g(Context context, Uri uri) {
        return c(context, uri, new b(context.getContentResolver()));
    }

    private InputStream h() throws Throwable {
        InputStream inputStreamD = this.f2942c.d(this.f2941b);
        int iA = inputStreamD != null ? this.f2942c.a(this.f2941b) : -1;
        return iA != -1 ? new g(inputStreamD, iA) : inputStreamD;
    }

    @Override // com.bumptech.glide.load.i.d
    public Class<InputStream> a() {
        return InputStream.class;
    }

    @Override // com.bumptech.glide.load.i.d
    public void b() {
        InputStream inputStream = this.f2943d;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // com.bumptech.glide.load.i.d
    public void cancel() {
    }

    @Override // com.bumptech.glide.load.i.d
    public DataSource e() {
        return DataSource.LOCAL;
    }

    @Override // com.bumptech.glide.load.i.d
    public void f(Priority priority, d.a<? super InputStream> aVar) throws Throwable {
        try {
            InputStream inputStreamH = h();
            this.f2943d = inputStreamH;
            aVar.d(inputStreamH);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable("MediaStoreThumbFetcher", 3)) {
                Log.d("MediaStoreThumbFetcher", "Failed to find thumbnail file", e);
            }
            aVar.c(e);
        }
    }
}
