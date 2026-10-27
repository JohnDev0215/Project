package b.a.h;

import android.graphics.Bitmap;
import com.byd.util.w;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
class d extends b {
    d(f fVar, k kVar, j jVar) {
        super(fVar, kVar, jVar);
    }

    private byte[] c(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    @Override // b.a.h.b, java.lang.Runnable
    public void run() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f476a.f486b).openConnection();
            if (httpURLConnection.getResponseCode() == 200) {
                Bitmap bitmapB = null;
                InputStream inputStream = httpURLConnection.getInputStream();
                String strA = this.f477b.a(this.f476a.j);
                if (httpURLConnection.getContentLength() >= 1048576 || this.f476a.f486b.endsWith("gif") || this.f476a.f486b.endsWith("GIF")) {
                    FileOutputStream fileOutputStream = new FileOutputStream(strA);
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int i = inputStream.read(bArr);
                        if (i == -1) {
                            break;
                        } else {
                            fileOutputStream.write(bArr, 0, i);
                        }
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    w.c("download and save finish : " + strA);
                    f fVar = this.f476a;
                    Bitmap bitmapH = a.h(strA, fVar.f487c, fVar.d, fVar.m, fVar.i, fVar.l);
                    if (bitmapH != null) {
                        float f = this.f476a.h;
                        bitmapB = f > 0.0f ? this.f477b.b(bitmapH, f) : bitmapH;
                    }
                } else {
                    byte[] bArrC = c(inputStream);
                    f fVar2 = this.f476a;
                    Bitmap bitmapI = a.i(bArrC, fVar2.f487c, fVar2.d, fVar2.m, fVar2.i, fVar2.l);
                    if (bitmapI != null) {
                        float f2 = this.f476a.h;
                        bitmapB = f2 > 0.0f ? this.f477b.b(bitmapI, f2) : bitmapI;
                    }
                    boolean zR = a.r(bitmapB, strA, this.f476a.i);
                    w.c("download and create thumb finish : " + strA + " w:" + bitmapB.getWidth() + " h:" + bitmapB.getHeight() + " maxWidth:" + this.f476a.f487c + " maxHeight:" + this.f476a.d + " save result : " + zR);
                    File file = new File(strA);
                    if (zR) {
                        k kVar = this.f478c;
                        String str = this.f476a.f486b;
                        String name = file.getName();
                        f fVar3 = this.f476a;
                        kVar.b(str, name, fVar3.f487c, fVar3.d, fVar3.l, fVar3.g);
                    } else if (file.exists()) {
                        file.delete();
                    }
                }
                if (bitmapB != null) {
                    this.f477b.d(this.f476a.j, bitmapB);
                    a(1, bitmapB).sendToTarget();
                } else {
                    b(0);
                }
                inputStream.close();
            } else {
                b(0);
            }
            httpURLConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            b(0);
        }
    }
}
