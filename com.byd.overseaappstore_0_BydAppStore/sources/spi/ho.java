package spi;

import com.byd.overseaappstore.module.ability.data.uistate.SearchResultItemUiState;
import com.byd.overseaappstore.module.ability.data.upper.SearchResult;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.ui.vm.SearchActiveViewModel;
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
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SearchActiveViewModel$downloadTriggerRegister$1$1$1", f = "SearchActiveViewModel.kt", i = {}, l = {186}, m = "invokeSuspend", n = {}, s = {})
public final class ho extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ SearchActiveViewModel b;
    public final /* synthetic */ String c;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ String a;
        public final /* synthetic */ SearchActiveViewModel b;

        public a(String str, SearchActiveViewModel searchActiveViewModel) {
            this.a = str;
            this.b = searchActiveViewModel;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            List<Software> list;
            List<Software> list2;
            SoftwareStateData softwareStateData = (SoftwareStateData) obj;
            int i = 0;
            ct.a(hi.f(e.l("operate vm new task find state:", softwareStateData.getState(), " percentCount:", softwareStateData.getPercentCount(), " pkn:"), this.a, " "), new Object[0]);
            SearchResult data = this.b.j.getValue().getData();
            List mutableList = (data == null || (list2 = data.getList()) == null) ? null : CollectionsKt.toMutableList((Collection) list2);
            if (data != null && (list = data.getList()) != null) {
                String str = this.a;
                for (T t : list) {
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
            if (mutableList != null) {
                MutableStateFlow<SearchResultItemUiState> mutableStateFlow = this.b.i;
                int size = data.getSize();
                String sizeTip = data.getSizeTip();
                if (sizeTip == null) {
                    sizeTip = "";
                }
                mutableStateFlow.setValue(new SearchResultItemUiState(this.b.j.getValue().getVisible(), new SearchResult(mutableList, size, sizeTip)));
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ho(SearchActiveViewModel searchActiveViewModel, String str, Continuation<? super ho> continuation) {
        super(2, continuation);
        this.b = searchActiveViewModel;
        this.c = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ho(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ho) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
