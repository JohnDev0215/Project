package spi;

import com.byd.overseaappstore.ui.MainActivity;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.MainActivity", f = "MainActivity.kt", i = {0}, l = {519}, m = "comprehensiveWork", n = {"this"}, s = {"L$0"})
public final class jh extends ContinuationImpl {
    public MainActivity a;
    public /* synthetic */ Object b;
    public final /* synthetic */ MainActivity c;
    public int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public jh(MainActivity mainActivity, Continuation<? super jh> continuation) {
        super(continuation);
        this.c = mainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.b = obj;
        this.d |= Integer.MIN_VALUE;
        return MainActivity.n(this.c, this);
    }
}
