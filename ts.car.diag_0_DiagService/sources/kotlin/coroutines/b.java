package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.CoroutineContext;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
public interface b extends CoroutineContext.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f1489b = a.f1490a;

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0012¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lkotlin/coroutines/b$a;", "Lkotlin/coroutines/CoroutineContext$b;", "<init>", "()V", "a", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public static final class a implements CoroutineContext.b<b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ a f1490a = new a();

        private a() {
        }
    }

    void a(@NotNull kotlin.coroutines.a<?> aVar);

    @NotNull
    <T> kotlin.coroutines.a<T> b(@NotNull kotlin.coroutines.a<? super T> aVar);
}
