package b.a.k;

import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;

/* JADX INFO: loaded from: classes2.dex */
public class p extends com.byd.taskmanager.b.a<Point> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f583a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f584b;

    public p(String str, int i, com.byd.taskmanager.b.c<Point> cVar) {
        super(cVar);
        this.f583a = str;
        this.f584b = i;
        this.name = "resolution:" + str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.taskmanager.b.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Point doTask() {
        int i = this.f584b;
        if (i == 1) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(this.f583a, options);
            return new Point(options.outWidth, options.outHeight);
        }
        if (i != 2) {
            return null;
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(this.f583a);
                Point point = new Point(Integer.parseInt(mediaMetadataRetriever.extractMetadata(18)), Integer.parseInt(mediaMetadataRetriever.extractMetadata(19)));
                try {
                    mediaMetadataRetriever.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return point;
            } catch (Exception unused) {
                mediaMetadataRetriever.release();
                return null;
            } catch (Throwable th) {
                try {
                    mediaMetadataRetriever.release();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                throw th;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
