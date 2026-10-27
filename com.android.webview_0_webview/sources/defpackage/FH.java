package defpackage;

import J.N;
import android.net.Uri;
import android.util.Base64InputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.chromium.media.MediaPlayerBridge;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FH extends Su {
    public final String h;
    public File i;
    public final /* synthetic */ MediaPlayerBridge j;

    public FH(MediaPlayerBridge mediaPlayerBridge, String str) {
        this.j = mediaPlayerBridge;
        this.h = str;
    }

    @Override // defpackage.Su
    public Object c() throws Throwable {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                this.i = File.createTempFile("decoded", "mediadata");
                fileOutputStream = new FileOutputStream(this.i);
                try {
                    Base64InputStream base64InputStream = new Base64InputStream(new ByteArrayInputStream(AbstractC1623us.b(this.h)), 0);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = base64InputStream.read(bArr);
                        if (i == -1) {
                            base64InputStream.close();
                            Boolean bool = Boolean.TRUE;
                            AbstractC0628ct.a(fileOutputStream);
                            return bool;
                        }
                        fileOutputStream.write(bArr, 0, i);
                    }
                } catch (IOException unused) {
                    fileOutputStream2 = fileOutputStream;
                    Boolean bool2 = Boolean.FALSE;
                    AbstractC0628ct.a(fileOutputStream2);
                    return bool2;
                } catch (Throwable th2) {
                    th = th2;
                    AbstractC0628ct.a(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                fileOutputStream = fileOutputStream2;
                th = th3;
            }
        } catch (IOException unused2) {
        }
    }

    @Override // defpackage.Su
    public void n(Object obj) {
        Boolean bool = (Boolean) obj;
        if (j()) {
            p();
            return;
        }
        if (bool.booleanValue()) {
            try {
                this.j.b().setDataSource(Is.a, Uri.fromFile(this.i));
            } catch (IOException unused) {
                bool = Boolean.FALSE;
            }
        }
        p();
        MediaPlayerBridge mediaPlayerBridge = this.j;
        N.Mo4Rd8TE(mediaPlayerBridge.c, mediaPlayerBridge, bool.booleanValue());
    }

    public final void p() {
        File file = this.i;
        if (file == null || file.delete()) {
            return;
        }
        StringBuilder sbF = Z7.f("Failed to delete temporary file: ");
        sbF.append(this.i);
        Ts.a("media", sbF.toString(), new Object[0]);
    }
}
