package com.bumptech.glide.load.data.o;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.data.g;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class c implements com.bumptech.glide.load.data.d<InputStream> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Uri f1991b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e f1992c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private InputStream f1993d;

    static class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final String[] f1994a = {"_data"};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ContentResolver f1995b;

        a(ContentResolver contentResolver) {
            this.f1995b = contentResolver;
        }

        @Override // com.bumptech.glide.load.data.o.d
        public Cursor a(Uri uri) {
            return this.f1995b.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, f1994a, "kind = 1 AND image_id = ?", new String[]{uri.getLastPathSegment()}, null);
        }
    }

    static class b implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final String[] f1996a = {"_data"};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ContentResolver f1997b;

        b(ContentResolver contentResolver) {
            this.f1997b = contentResolver;
        }

        @Override // com.bumptech.glide.load.data.o.d
        public Cursor a(Uri uri) {
            return this.f1997b.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, f1996a, "kind = 1 AND video_id = ?", new String[]{uri.getLastPathSegment()}, null);
        }
    }

    c(Uri uri, e eVar) {
        this.f1991b = uri;
        this.f1992c = eVar;
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
        InputStream inputStreamD = this.f1992c.d(this.f1991b);
        int iA = inputStreamD != null ? this.f1992c.a(this.f1991b) : -1;
        return iA != -1 ? new g(inputStreamD, iA) : inputStreamD;
    }

    @Override // com.bumptech.glide.load.data.d
    public Class<InputStream> a() {
        return InputStream.class;
    }

    @Override // com.bumptech.glide.load.data.d
    public void b() {
        InputStream inputStream = this.f1993d;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // com.bumptech.glide.load.data.d
    public com.bumptech.glide.load.a c() {
        return com.bumptech.glide.load.a.LOCAL;
    }

    @Override // com.bumptech.glide.load.data.d
    public void cancel() {
    }

    @Override // com.bumptech.glide.load.data.d
    public void e(com.bumptech.glide.g gVar, d.a<? super InputStream> aVar) throws Throwable {
        try {
            InputStream inputStreamH = h();
            this.f1993d = inputStreamH;
            aVar.f(inputStreamH);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable("MediaStoreThumbFetcher", 3)) {
                Log.d("MediaStoreThumbFetcher", "Failed to find thumbnail file", e);
            }
            aVar.d(e);
        }
    }
}
