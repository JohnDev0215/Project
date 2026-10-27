package spi;

import androidx.fragment.app.FragmentManager;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.module.ability.util.notify.router.storage.StorageState;
import com.byd.overseaappstore.ui.MainActivity;
import com.byd.overseaappstore.ui.view.dialog.StorageLeakDialog;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.MainActivity$comprehensiveWork$3", f = "MainActivity.kt", i = {}, l = {691}, m = "invokeSuspend", n = {}, s = {})
public final class lh extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ MainActivity b;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.MainActivity$comprehensiveWork$3$1", f = "MainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<StorageState, Continuation<? super Unit>, Object> {
        public /* synthetic */ Object a;
        public final /* synthetic */ MainActivity b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(MainActivity mainActivity, Continuation<? super a> continuation) {
            super(2, continuation);
            this.b = mainActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            a aVar = new a(this.b, continuation);
            aVar.a = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(StorageState storageState, Continuation<? super Unit> continuation) {
            return ((a) create(storageState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            StringBuilder sb;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            byte state = ((StorageState) this.a).getState();
            if (state == 0) {
                ct.a("storageLeak state:0 do nothing", new Object[0]);
            } else {
                if (state == 1) {
                    boolean zB = sc.a.b(this.b, StorageLeakDialog.class.getName());
                    if (zB) {
                        sb = new StringBuilder();
                        sb.append("storageLeak showDialog Dialog already add do nothing state:0 ret:");
                        sb.append(zB);
                        ct.a(sb.toString(), new Object[0]);
                    } else {
                        sb = new StringBuilder();
                        sb.append("storageLeak showDialog state:0 isAdd:");
                        sb.append(zB);
                        ct.a(sb.toString(), new Object[0]);
                        StorageLeakDialog storageLeakDialog = new StorageLeakDialog();
                        MainActivity mainActivity = this.b;
                        vm vmVar = vm.a;
                        String title = vmVar.e(mainActivity, R.string.no_storage_dialog_title);
                        Intrinsics.checkNotNullParameter(title, "title");
                        storageLeakDialog.e = title;
                        String buttonText = vmVar.e(mainActivity, R.string.no_storage_dialog_button);
                        Intrinsics.checkNotNullParameter(buttonText, "buttonText");
                        storageLeakDialog.f = buttonText;
                        FragmentManager supportFragmentManager = this.b.getSupportFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "this@MainActivity.supportFragmentManager");
                        storageLeakDialog.show(supportFragmentManager, StorageLeakDialog.class.getName());
                    }
                    return Unit.INSTANCE;
                }
                ct.a("storageLeak other state:0 do nothing", new Object[0]);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public lh(MainActivity mainActivity, Continuation<? super lh> continuation) {
        super(2, continuation);
        this.b = mainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new lh(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((lh) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MainActivity mainActivity = this.b;
            int i2 = MainActivity.s;
            MutableStateFlow<StorageState> mutableStateFlow = mainActivity.s().o;
            a aVar = new a(this.b, null);
            this.a = 1;
            if (FlowKt.collectLatest(mutableStateFlow, aVar, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
