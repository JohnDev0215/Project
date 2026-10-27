package spi;

import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import com.byd.overseaappstore.ui.vm.SettingViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SettingViewModel$pauseAllDownloadingTasks$1", f = "SettingViewModel.kt", i = {}, l = {87, 87}, m = "invokeSuspend", n = {}, s = {})
public final class hp extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ SettingViewModel b;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ SettingViewModel a;

        public a(SettingViewModel settingViewModel) {
            this.a = settingViewModel;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            ArrayList arrayList;
            List list = (List) ((SourceDataBody) obj).getApiData();
            if (list != null) {
                SettingViewModel settingViewModel = this.a;
                arrayList = new ArrayList(CollectionsKt.b(list));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(settingViewModel.e.parseToSoftwareOptParam((qq) it.next()));
                }
            } else {
                arrayList = null;
            }
            if (arrayList != null) {
                ct.a(e.c("operate work pauseAllDownloadingTasks optParamList.size:", arrayList.size()), new Object[0]);
                Object objB = s9.b(this.a.c, arrayList, continuation);
                if (objB == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return objB;
                }
            } else {
                ct.a("operate work pauseAllDownloadingTasks optParamList is null", new Object[0]);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public hp(SettingViewModel settingViewModel, Continuation<? super hp> continuation) {
        super(2, continuation);
        this.b = settingViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new hp(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((hp) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SoftwareRepository softwareRepository = this.b.b;
            this.a = 1;
            obj = softwareRepository.c();
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
        }
        a aVar = new a(this.b);
        this.a = 2;
        if (((Flow) obj).collect(aVar, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
