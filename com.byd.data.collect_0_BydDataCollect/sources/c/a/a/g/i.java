package c.a.a.g;

import java.util.Set;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: ReUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Set<Character> f3079a = c.a.a.b.e.a('$', '(', ')', '*', '+', '.', '[', ']', '?', '\\', '^', '{', '}', '|');

    public static boolean a(Pattern pattern, CharSequence charSequence) {
        if (charSequence == null || pattern == null) {
            return false;
        }
        return pattern.matcher(charSequence).matches();
    }
}
