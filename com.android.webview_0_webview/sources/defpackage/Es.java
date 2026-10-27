package defpackage;

import android.util.Log;
import com.android.webview.R;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Es {
    public static final AtomicReference a = new AtomicReference();

    public Es(Bs bs) {
    }

    public static Es e() {
        return (Es) a.get();
    }

    public static void i(String[] strArr) {
        Es es = (Es) a.getAndSet(new Cs(strArr));
        if (es != null) {
            es.c();
        }
    }

    public static void j(String str) {
        char[] cArrCopyOfRange;
        File file = new File(str);
        String[] strArr = null;
        try {
            FileReader fileReader = new FileReader(file);
            try {
                char[] cArr = new char[(int) file.length()];
                cArrCopyOfRange = Arrays.copyOfRange(cArr, 0, fileReader.read(cArr));
                fileReader.close();
            } finally {
            }
        } catch (IOException unused) {
            cArrCopyOfRange = null;
        }
        if (cArrCopyOfRange != null) {
            if (cArrCopyOfRange.length > R.anim.abc_fade_in) {
                StringBuilder sbF = Z7.f("Flags file too big: ");
                sbF.append(cArrCopyOfRange.length);
                throw new RuntimeException(sbF.toString());
            }
            ArrayList arrayList = new ArrayList();
            char c = 0;
            StringBuilder sb = null;
            for (char c2 : cArrCopyOfRange) {
                if ((c == 0 && (c2 == '\'' || c2 == '\"')) || c2 == c) {
                    if (sb == null || sb.length() <= 0 || sb.charAt(sb.length() - 1) != '\\') {
                        c = c == 0 ? c2 : (char) 0;
                    } else {
                        sb.setCharAt(sb.length() - 1, c2);
                    }
                } else if (c != 0 || !Character.isWhitespace(c2)) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    sb.append(c2);
                } else if (sb != null) {
                    arrayList.add(sb.toString());
                    sb = null;
                }
            }
            if (sb != null) {
                if (c != 0) {
                    Log.w("CommandLine", "Unterminated quoted string: " + ((Object) sb));
                }
                arrayList.add(sb.toString());
            }
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        i(strArr);
    }

    public abstract void a(String str);

    public abstract void b(String str, String str2);

    public void c() {
    }

    public abstract String[] d();

    public abstract String f(String str);

    public abstract Map g();

    public abstract boolean h(String str);

    public abstract void k(String str);
}
