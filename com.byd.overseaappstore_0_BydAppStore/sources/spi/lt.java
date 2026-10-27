package spi;

import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.ui.vm.TopicDetailViewModel;
import java.util.ArrayList;
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
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.TopicDetailViewModel$downloadPoolRegister$1$1", f = "TopicDetailViewModel.kt", i = {}, l = {96}, m = "invokeSuspend", n = {}, s = {})
public final class lt extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ MutableStateFlow<SoftwareStateData> b;
    public final /* synthetic */ TopicDetailViewModel c;
    public final /* synthetic */ String d;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ TopicDetailViewModel a;
        public final /* synthetic */ MutableStateFlow<SoftwareStateData> b;
        public final /* synthetic */ String c;

        public a(TopicDetailViewModel topicDetailViewModel, MutableStateFlow<SoftwareStateData> mutableStateFlow, String str) {
            this.a = topicDetailViewModel;
            this.b = mutableStateFlow;
            this.c = str;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            SoftwareStateData softwareStateData = (SoftwareStateData) obj;
            List<Software> data = this.a.j.getValue().getData();
            ArrayList arrayList = null;
            List mutableList = data != null ? CollectionsKt.toMutableList((Collection) data) : null;
            int state = this.b.getValue().getState();
            int percentCount = this.b.getValue().getPercentCount();
            String str = this.c;
            StringBuilder sbL = e.l("operate vm forEach collect state:", state, " percentCount:", percentCount, " pkn:");
            sbL.append(str);
            int i = 0;
            ct.a(sbL.toString(), new Object[0]);
            if (data != null) {
                String str2 = this.c;
                TopicDetailViewModel topicDetailViewModel = this.a;
                ArrayList arrayList2 = new ArrayList();
                for (T t : data) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    Software software = (Software) t;
                    if (Intrinsics.areEqual(str2, software.getPackageName())) {
                        if (mutableList != null) {
                        }
                        TopicDetailViewModel.a(topicDetailViewModel, mutableList);
                    }
                    if (Intrinsics.areEqual(str2, software.getPackageName())) {
                        arrayList2.add(t);
                    }
                    i = i2;
                }
                arrayList = arrayList2;
            }
            return arrayList == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? arrayList : Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public lt(MutableStateFlow<SoftwareStateData> mutableStateFlow, TopicDetailViewModel topicDetailViewModel, String str, Continuation<? super lt> continuation) {
        super(2, continuation);
        this.b = mutableStateFlow;
        this.c = topicDetailViewModel;
        this.d = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new lt(this.b, this.c, this.d, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((lt) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow<SoftwareStateData> mutableStateFlow = this.b;
            a aVar = new a(this.c, mutableStateFlow, this.d);
            this.a = 1;
            if (mutableStateFlow.collect(aVar, this) == coroutine_suspended) {
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
