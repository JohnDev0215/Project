package m5;

import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: loaded from: classes.dex */
public class N extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        if (c2297a.Q() == 9) {
            c2297a.M();
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(c2297a.O(), "_");
        String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
        String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
        String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
        return (strNextToken2 == null && strNextToken3 == null) ? new Locale(strNextToken) : strNextToken3 == null ? new Locale(strNextToken, strNextToken2) : new Locale(strNextToken, strNextToken2, strNextToken3);
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        Locale locale = (Locale) obj;
        c2298b.L(locale == null ? null : locale.toString());
    }
}
