package f4;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/* JADX INFO: renamed from: f4.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1256a implements InterfaceC1258c {
    public static final String[] c = {"_data"};
    public static final String[] d = {"_data"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14591a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ContentResolver f14592b;

    public /* synthetic */ C1256a(ContentResolver contentResolver, int i7) {
        this.f14591a = i7;
        this.f14592b = contentResolver;
    }

    @Override // f4.InterfaceC1258c
    public final Cursor a(Uri uri) {
        switch (this.f14591a) {
            case 0:
                String lastPathSegment = uri.getLastPathSegment();
                return this.f14592b.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, c, "kind = 1 AND image_id = ?", new String[]{lastPathSegment}, null);
            default:
                String lastPathSegment2 = uri.getLastPathSegment();
                return this.f14592b.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, d, "kind = 1 AND video_id = ?", new String[]{lastPathSegment2}, null);
        }
    }
}
