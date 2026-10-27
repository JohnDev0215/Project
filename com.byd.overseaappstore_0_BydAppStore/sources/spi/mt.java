package spi;

import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.ui.vm.TopicDetailViewModel;
import java.util.Collection;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.TopicDetailViewModel$downloadTriggerRegister$1$1$1", f = "TopicDetailViewModel.kt", i = {}, l = {121}, m = "invokeSuspend", n = {}, s = {})
public final class mt extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ TopicDetailViewModel b;
    public final /* synthetic */ String c;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ String a;
        public final /* synthetic */ TopicDetailViewModel b;

        public a(String str, TopicDetailViewModel topicDetailViewModel) {
            this.a = str;
            this.b = topicDetailViewModel;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            SoftwareStateData softwareStateData = (SoftwareStateData) obj;
            String strF = hi.f(e.l("operate vm new task find state:", softwareStateData.getState(), " percentCount:", softwareStateData.getPercentCount(), " pkn:"), this.a, " ");
            int i = 0;
            ct.a(strF, new Object[0]);
            List<Software> data = this.b.j.getValue().getData();
            List mutableList = data != null ? CollectionsKt.toMutableList((Collection) data) : null;
            if (data != null) {
                String str = this.a;
                for (T t : data) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    Software software = (Software) t;
                    if (Intrinsics.areEqual(software.getPackageName(), str) && mutableList != null) {
                    }
                    i = i2;
                }
            }
            TopicDetailViewModel.a(this.b, mutableList);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public mt(TopicDetailViewModel topicDetailViewModel, String str, Continuation<? super mt> continuation) {
        super(2, continuation);
        this.b = topicDetailViewModel;
        this.c = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new mt(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((mt) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow<SoftwareStateData> mutableStateFlowD = this.b.f.d(this.c);
            if (mutableStateFlowD == null) {
                return Unit.INSTANCE;
            }
            a aVar = new a(this.c, this.b);
            this.a = 1;
            if (mutableStateFlowD.collect(aVar, this) == coroutine_suspended) {
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
