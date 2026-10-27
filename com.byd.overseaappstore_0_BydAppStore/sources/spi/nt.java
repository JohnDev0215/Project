package spi;

import android.text.TextUtils;
import com.byd.overseaappstore.ui.vm.TopicDetailViewModel;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.TopicDetailViewModel$downloadTriggerRegister$1", f = "TopicDetailViewModel.kt", i = {}, l = {114}, m = "invokeSuspend", n = {}, s = {})
public final class nt extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public /* synthetic */ Object b;
    public final /* synthetic */ TopicDetailViewModel c;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ CoroutineScope a;
        public final /* synthetic */ TopicDetailViewModel b;

        public a(CoroutineScope coroutineScope, TopicDetailViewModel topicDetailViewModel) {
            this.a = coroutineScope;
            this.b = topicDetailViewModel;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            String str = (String) obj;
            if (TextUtils.isEmpty(str)) {
                ct.a("operate vm new task no pkn ", new Object[0]);
            } else {
                ct.a(e.d("operate vm new task pkn:", str, " "), new Object[0]);
                Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(this.a, null, null, new mt(this.b, str, null), 3, null);
                if (jobLaunch$default == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return jobLaunch$default;
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nt(TopicDetailViewModel topicDetailViewModel, Continuation<? super nt> continuation) {
        super(2, continuation);
        this.c = topicDetailViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        nt ntVar = new nt(this.c, continuation);
        ntVar.b = obj;
        return ntVar;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((nt) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.b;
            SharedFlow<String> taskTriggerFlow = this.c.e.getTaskTriggerFlow();
            a aVar = new a(coroutineScope, this.c);
            this.a = 1;
            if (taskTriggerFlow.collect(aVar, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        throw new KotlinNothingValueException();
    }
}
