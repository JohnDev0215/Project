package Q8;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Serializable {
    public final Pattern l;

    public g(String str) {
        Pattern patternCompile = Pattern.compile(str);
        A7.m.e(patternCompile, "compile(...)");
        this.l = patternCompile;
    }

    public static f a(g gVar, CharSequence charSequence) {
        gVar.getClass();
        A7.m.f(charSequence, "input");
        Matcher matcher = gVar.l.matcher(charSequence);
        A7.m.e(matcher, "matcher(...)");
        if (matcher.find(0)) {
            return new f(matcher, charSequence);
        }
        return null;
    }

    public final boolean b(String str) {
        A7.m.f(str, "input");
        return this.l.matcher(str).matches();
    }

    public final String c(CharSequence charSequence, InterfaceC2782k interfaceC2782k) {
        A7.m.f(charSequence, "input");
        f fVarA = a(this, charSequence);
        if (fVarA == null) {
            return charSequence.toString();
        }
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i7 = 0;
        do {
            sb.append(charSequence, i7, fVarA.a().l);
            sb.append((CharSequence) interfaceC2782k.b(fVarA));
            i7 = fVarA.a().f2712m + 1;
            Matcher matcher = fVarA.f7223a;
            int iEnd = matcher.end() + (matcher.end() != matcher.start() ? 0 : 1);
            CharSequence charSequence2 = fVarA.f7224b;
            f fVar = null;
            if (iEnd <= charSequence2.length()) {
                Matcher matcher2 = matcher.pattern().matcher(charSequence2);
                A7.m.e(matcher2, "matcher(...)");
                if (matcher2.find(iEnd)) {
                    fVar = new f(matcher2, charSequence2);
                }
            }
            fVarA = fVar;
            if (i7 >= length) {
                break;
            }
        } while (fVarA != null);
        if (i7 < length) {
            sb.append(charSequence, i7, length);
        }
        String string = sb.toString();
        A7.m.e(string, "toString(...)");
        return string;
    }

    public final String toString() {
        String string = this.l.toString();
        A7.m.e(string, "toString(...)");
        return string;
    }
}
