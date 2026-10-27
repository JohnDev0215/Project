package spi;

import com.byd.overseaappstore.module.ability.data.uistate.FeatureThemGroupUiState;
import com.byd.overseaappstore.module.ability.data.uistate.SoftwareThemeUiState;
import com.byd.overseaappstore.module.ability.data.upper.FeatureThemTopicGroup;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.ui.vm.FeatureViewModel;
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
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.FeatureViewModel$downloadPoolRegister$1$1", f = "FeatureViewModel.kt", i = {}, l = {246}, m = "invokeSuspend", n = {}, s = {})
public final class mb extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ MutableStateFlow<SoftwareStateData> b;
    public final /* synthetic */ String c;
    public final /* synthetic */ FeatureViewModel d;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ String a;
        public final /* synthetic */ FeatureViewModel b;

        public a(String str, FeatureViewModel featureViewModel) {
            this.a = str;
            this.b = featureViewModel;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            FeatureThemTopicGroup featureThemTopicGroup;
            String picUrl;
            FeatureThemTopicGroup featureThemTopicGroup2;
            String title;
            FeatureThemTopicGroup featureThemTopicGroup3;
            SoftwareStateData softwareStateData = (SoftwareStateData) obj;
            int state = softwareStateData.getState();
            int percentCount = softwareStateData.getPercentCount();
            String str = this.a;
            StringBuilder sbL = e.l("operate vm forEach collect state:", state, " percentCount:", percentCount, " pkn:");
            sbL.append(str);
            int i = 0;
            ct.a(sbL.toString(), new Object[0]);
            FeatureViewModel.a(this.b, this.a, softwareStateData.getState());
            List<FeatureThemTopicGroup> data = this.b.p.getValue().getData();
            List mutableList = data != null ? CollectionsKt.toMutableList((Collection) data) : null;
            if (mutableList != null) {
                FeatureViewModel featureViewModel = this.b;
                String str2 = this.a;
                int i2 = 0;
                for (T t : mutableList) {
                    int i3 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    List<Software> softwareList = ((FeatureThemTopicGroup) t).getSoftwareList();
                    List mutableList2 = softwareList != null ? CollectionsKt.toMutableList((Collection) softwareList) : null;
                    if (mutableList2 != null) {
                        for (T t2 : mutableList2) {
                            int i4 = i2 + 1;
                            if (i2 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            Software software = (Software) t2;
                            if (Intrinsics.areEqual(software.getPackageName(), str2)) {
                                mutableList2.set(i2, software.newSoftwareDownloaded(softwareStateData.getPercentCount(), softwareStateData.getState()));
                            }
                            i2 = i4;
                        }
                    }
                    List<FeatureThemTopicGroup> data2 = featureViewModel.p.getValue().getData();
                    int id = (data2 == null || (featureThemTopicGroup3 = data2.get(i)) == null) ? 0 : featureThemTopicGroup3.getId();
                    List<FeatureThemTopicGroup> data3 = featureViewModel.p.getValue().getData();
                    String str3 = (data3 == null || (featureThemTopicGroup2 = data3.get(i)) == null || (title = featureThemTopicGroup2.getTitle()) == null) ? "" : title;
                    List<FeatureThemTopicGroup> data4 = featureViewModel.p.getValue().getData();
                    mutableList.set(i, new FeatureThemTopicGroup(id, str3, (data4 == null || (featureThemTopicGroup = data4.get(i)) == null || (picUrl = featureThemTopicGroup.getPicUrl()) == null) ? "" : picUrl, null, mutableList2, 8, null));
                    i2 = 0;
                    i = i3;
                }
            }
            if (mutableList != null) {
                FeatureViewModel featureViewModel2 = this.b;
                featureViewModel2.o.setValue(new FeatureThemGroupUiState(false, mutableList, featureViewModel2.p.getValue().getVisible(), 1, null));
            }
            List<? extends Software> data5 = this.b.n.getValue().getData();
            List mutableList3 = data5 != null ? CollectionsKt.toMutableList((Collection) data5) : null;
            if (data5 != null) {
                String str4 = this.a;
                int i5 = 0;
                for (T t3 : data5) {
                    int i6 = i5 + 1;
                    if (i5 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    Software software2 = (Software) t3;
                    if (Intrinsics.areEqual(software2.getPackageName(), str4) && mutableList3 != null) {
                    }
                    i5 = i6;
                }
            }
            if (mutableList3 != null) {
                FeatureViewModel featureViewModel3 = this.b;
                featureViewModel3.m.setValue(new SoftwareThemeUiState(false, mutableList3, featureViewModel3.n.getValue().getVisible(), 1, null));
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public mb(MutableStateFlow<SoftwareStateData> mutableStateFlow, String str, FeatureViewModel featureViewModel, Continuation<? super mb> continuation) {
        super(2, continuation);
        this.b = mutableStateFlow;
        this.c = str;
        this.d = featureViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new mb(this.b, this.c, this.d, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((mb) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow<SoftwareStateData> mutableStateFlow = this.b;
            a aVar = new a(this.c, this.d);
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
