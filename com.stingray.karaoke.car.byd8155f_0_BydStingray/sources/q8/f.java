package Q8;

import java.util.regex.Matcher;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Matcher f7223a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CharSequence f7224b;
    public e c;

    public f(Matcher matcher, CharSequence charSequence) {
        A7.m.f(charSequence, "input");
        this.f7223a = matcher;
        this.f7224b = charSequence;
    }

    public final G7.d a() {
        Matcher matcher = this.f7223a;
        return AbstractC2673a.b0(matcher.start(), matcher.end());
    }
}
