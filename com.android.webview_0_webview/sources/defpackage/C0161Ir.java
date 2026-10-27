package defpackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: Ir, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0161Ir extends AbstractC0125Gr {
    public File a;

    public C0161Ir(File file) {
        this.a = file;
    }

    @Override // defpackage.AbstractC0125Gr
    public List a() {
        ArrayList arrayList = new ArrayList();
        if (this.a.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(this.a));
                for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                    C0249Nq c0249NqB = b(line);
                    if (c0249NqB != null) {
                        arrayList.add(c0249NqB);
                    }
                }
                bufferedReader.close();
            } catch (IOException unused) {
            }
        }
        return arrayList;
    }

    public final C0249Nq b(String str) {
        String[] strArrSplit = str.split(",");
        if (strArrSplit.length == 3 && !strArrSplit[0].isEmpty() && !strArrSplit[1].isEmpty() && !strArrSplit[2].isEmpty()) {
            C0249Nq c0249Nq = new C0249Nq(strArrSplit[2]);
            c0249Nq.a = EnumC0231Mq.UPLOADED;
            try {
                c0249Nq.g = TimeUnit.SECONDS.toMillis(Long.parseLong(strArrSplit[0]));
                c0249Nq.f = strArrSplit[1];
                return c0249Nq;
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }
}
