package k6;

import V8.AbstractC0828q;
import V8.N;
import V8.f0;
import android.content.Context;
import java.util.Locale;

/* JADX INFO: renamed from: k6.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1705m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f18280a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public h2.r f18281b;
    public final f0 c;
    public final N d;

    public C1705m(Context context) {
        A7.m.f(context, "appContext");
        this.f18280a = context;
        f0 f0VarC = AbstractC0828q.c(Locale.getDefault().toLanguageTag());
        this.c = f0VarC;
        this.d = new N(f0VarC);
    }
}
