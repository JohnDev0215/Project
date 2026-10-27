package defpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Lr extends AbstractC0125Gr {
    public File a;

    public Lr(File file) {
        this.a = file;
    }

    public static String b(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            byte[] bArr = new byte[(int) file.length()];
            fileInputStream.read(bArr);
            String str = new String(bArr);
            fileInputStream.close();
            return str;
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }

    @Override // defpackage.AbstractC0125Gr
    public List a() {
        ArrayList arrayList = new ArrayList();
        if (this.a.exists() && this.a.isDirectory()) {
            for (File file : this.a.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    try {
                        C0249Nq c0249NqA = C0249Nq.a(b(file));
                        if (c0249NqA.b != null) {
                            arrayList.add(c0249NqA);
                        }
                    } catch (IOException e) {
                        Ts.a("WebViewCrashUI", "Error while reading log file", e);
                    } catch (JSONException e2) {
                        Ts.a("WebViewCrashUI", "Error while reading JSON", e2);
                    }
                }
            }
        }
        return arrayList;
    }
}
