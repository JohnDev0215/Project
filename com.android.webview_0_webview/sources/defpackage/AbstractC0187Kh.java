package defpackage;

import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: renamed from: Kh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0187Kh {
    public static String a(String str, Object... objArr) {
        int iIndexOf;
        String string;
        int i = 0;
        for (int i2 = 0; i2 < objArr.length; i2++) {
            Object obj = objArr[i2];
            try {
                string = String.valueOf(obj);
            } catch (Exception e) {
                String name = obj.getClass().getName();
                String hexString = Integer.toHexString(System.identityHashCode(obj));
                StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + name.length() + 1);
                sb.append(name);
                sb.append('@');
                sb.append(hexString);
                String string2 = sb.toString();
                Logger logger = Logger.getLogger("com.google.common.base.Strings");
                Level level = Level.WARNING;
                String strValueOf = String.valueOf(string2);
                logger.logp(level, "com.google.common.base.Strings", "lenientToString", strValueOf.length() != 0 ? "Exception during lenientFormat for ".concat(strValueOf) : new String("Exception during lenientFormat for "), (Throwable) e);
                String name2 = e.getClass().getName();
                StringBuilder sb2 = new StringBuilder(name2.length() + String.valueOf(string2).length() + 9);
                sb2.append("<");
                sb2.append(string2);
                sb2.append(" threw ");
                sb2.append(name2);
                sb2.append(">");
                string = sb2.toString();
            }
            objArr[i2] = string;
        }
        StringBuilder sb3 = new StringBuilder((objArr.length * 16) + str.length());
        int i3 = 0;
        while (i < objArr.length && (iIndexOf = str.indexOf("%s", i3)) != -1) {
            sb3.append((CharSequence) str, i3, iIndexOf);
            sb3.append(objArr[i]);
            i3 = iIndexOf + 2;
            i++;
        }
        sb3.append((CharSequence) str, i3, str.length());
        if (i < objArr.length) {
            sb3.append(" [");
            sb3.append(objArr[i]);
            for (int i4 = i + 1; i4 < objArr.length; i4++) {
                sb3.append(", ");
                sb3.append(objArr[i4]);
            }
            sb3.append(']');
        }
        return sb3.toString();
    }
}
