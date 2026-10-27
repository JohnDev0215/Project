package C1;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f935a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ String f936b;
    public final /* synthetic */ Context c;
    public final /* synthetic */ int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f937e;

    public /* synthetic */ f(String str, Context context, Object obj, int i7, int i10) {
        this.f935a = i10;
        this.f936b = str;
        this.c = context;
        this.f937e = obj;
        this.d = i7;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f935a) {
            case 0:
                Object[] objArr = {(e) this.f937e};
                ArrayList arrayList = new ArrayList(1);
                Object obj = objArr[0];
                Objects.requireNonNull(obj);
                arrayList.add(obj);
                return i.b(this.f936b, this.c, Collections.unmodifiableList(arrayList), this.d);
            default:
                try {
                    return i.b(this.f936b, this.c, (List) this.f937e, this.d);
                } catch (Throwable unused) {
                    return new h(-3);
                }
        }
    }
}
