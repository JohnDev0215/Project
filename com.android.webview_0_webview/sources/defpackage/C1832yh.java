package defpackage;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: yh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1832yh implements InterfaceC0222Mh {
    public static final InterfaceC0222Mh s = new C1832yh();

    @Override // defpackage.InterfaceC0222Mh
    public final Object get() {
        AbstractC0115Gh c0169Jh;
        BufferedReader bufferedReader;
        HashMap map;
        Context contextCreateDeviceProtectedStorageContext = AbstractC1667vh.g;
        C0061Dh c0061Dh = C0061Dh.s;
        String str = Build.TYPE;
        String str2 = Build.TAGS;
        String str3 = Build.HARDWARE;
        if (!((str.equals("eng") || str.equals("userdebug")) && (str3.equals("goldfish") || str3.equals("ranchu") || str3.equals("robolectric")) && (str2.contains("dev-keys") || str2.contains("test-keys")))) {
            return c0061Dh;
        }
        if (AbstractC0950ih.a() && !contextCreateDeviceProtectedStorageContext.isDeviceProtectedStorage()) {
            contextCreateDeviceProtectedStorageContext = contextCreateDeviceProtectedStorageContext.createDeviceProtectedStorageContext();
        }
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            StrictMode.allowThreadDiskWrites();
            try {
                File file = new File(contextCreateDeviceProtectedStorageContext.getDir("phenotype_hermetic", 0), "overrides.txt");
                c0169Jh = file.exists() ? new C0169Jh(file) : c0061Dh;
            } catch (RuntimeException e) {
                Log.e("HermeticFileOverrides", "no data dir", e);
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                c0169Jh = c0061Dh;
            }
            if (!c0169Jh.b()) {
                return c0061Dh;
            }
            File file2 = (File) c0169Jh.a();
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
                try {
                    map = new HashMap();
                } finally {
                }
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    String strValueOf = String.valueOf(file2);
                    StringBuilder sb = new StringBuilder(strValueOf.length() + 7);
                    sb.append("Parsed ");
                    sb.append(strValueOf);
                    Log.i("HermeticFileOverrides", sb.toString());
                    C1502sh c1502sh = new C1502sh(map);
                    bufferedReader.close();
                    return new C0169Jh(c1502sh);
                }
                String[] strArrSplit = line.split(" ", 3);
                if (strArrSplit.length != 3) {
                    Log.e("HermeticFileOverrides", line.length() != 0 ? "Invalid: ".concat(line) : new String("Invalid: "));
                } else {
                    String str4 = strArrSplit[0];
                    String strDecode = Uri.decode(strArrSplit[1]);
                    String strDecode2 = Uri.decode(strArrSplit[2]);
                    if (!map.containsKey(str4)) {
                        map.put(str4, new HashMap());
                    }
                    ((Map) map.get(str4)).put(strDecode, strDecode2);
                }
                throw new RuntimeException(e2);
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }
}
