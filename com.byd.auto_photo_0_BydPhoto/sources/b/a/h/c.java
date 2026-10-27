package b.a.h;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Message;
import androidx.annotation.NonNull;
import com.byd.util.w;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class c extends b {
    private List<String> e;

    c(f fVar, k kVar, j jVar, List<String> list) {
        super(fVar, kVar, jVar);
        this.e = list;
    }

    @Override // b.a.h.b, android.os.Handler.Callback
    public /* bridge */ /* synthetic */ boolean handleMessage(@NonNull Message message) {
        return super.handleMessage(message);
    }

    @Override // b.a.h.b, java.lang.Runnable
    public void run() throws IOException {
        Bitmap bitmapCreateScaledBitmap;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(new FileInputStream(this.f476a.f486b).getFD());
                bitmapCreateScaledBitmap = mediaMetadataRetriever.getFrameAtTime();
            } catch (Exception e) {
                e.printStackTrace();
                mediaMetadataRetriever.release();
                bitmapCreateScaledBitmap = null;
            }
            if (bitmapCreateScaledBitmap != null) {
                f fVar = this.f476a;
                int i = fVar.f487c;
                int i2 = fVar.d;
                if (i > 0 && i2 > 0) {
                    int width = bitmapCreateScaledBitmap.getWidth();
                    int height = bitmapCreateScaledBitmap.getHeight();
                    if (width > i || height > i2) {
                        float f = width;
                        float f2 = i / f;
                        float f3 = height;
                        float f4 = i2 / f3;
                        if (f2 < f4) {
                            i2 = (int) (f3 * f2);
                        } else {
                            i = (int) (f * f4);
                        }
                        bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCreateScaledBitmap, i, i2, true);
                        float f5 = this.f476a.h;
                        if (f5 > 0.0f) {
                            bitmapCreateScaledBitmap = this.f477b.b(bitmapCreateScaledBitmap, f5);
                        }
                    }
                }
                this.f477b.d(this.f476a.j, bitmapCreateScaledBitmap);
                a(1, bitmapCreateScaledBitmap).sendToTarget();
                w.c(this.f476a.f486b + " create thumb ok");
                String strA = this.f477b.a(this.f476a.j);
                boolean zR = a.r(bitmapCreateScaledBitmap, strA, Bitmap.Config.RGB_565);
                w.c("video thumb save local : " + zR);
                File file = new File(strA);
                if (!zR) {
                    if (file.exists()) {
                        file.delete();
                        return;
                    }
                    return;
                }
                k kVar = this.f478c;
                String str = this.f476a.f486b;
                String name = file.getName();
                f fVar2 = this.f476a;
                kVar.b(str, name, fVar2.f487c, fVar2.d, fVar2.l, fVar2.g);
                w.c(this.f476a.f486b + " create thumb success");
            } else {
                Object objG = this.f477b.g(this.f476a);
                if (objG != null) {
                    w.c(this.f476a.f486b + " create thumb fail , use def thumb");
                    a(1, objG).sendToTarget();
                    return;
                }
                w.c(this.f476a.f486b + " create thumb fail");
                b(0);
            }
            this.e.remove(this.f476a.f486b);
        } finally {
            mediaMetadataRetriever.release();
        }
    }
}
