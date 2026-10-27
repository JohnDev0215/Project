package com.amazon.mp3.account.signintasks;

import android.content.Context;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.account.map.DeviceAttributesQuery;
import com.amazon.mp3.net.soprano.SopranoExceptions;
import com.amazon.mp3.net.soprano.SopranoRequest;
import com.amazon.mp3.util.Log;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.json.JSONObject;

/* JADX INFO: compiled from: AddHawkFireBenefitsForChildUser.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u000e2\u00020\u0001:\u0003\r\u000e\u000fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0011\u0010\u000b\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/account/signintasks/AddHawkFireBenefitsForChildUser;", "", "mContext", "Landroid/content/Context;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/mp3/account/signintasks/AddHawkFireBenefitsForChildUser$AddHawkFireListener;", "(Landroid/content/Context;Lcom/amazon/mp3/account/signintasks/AddHawkFireBenefitsForChildUser$AddHawkFireListener;)V", "execute", "", "getDeviceId", "", "subscribeBenefits", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "AddHawkFireListener", "Companion", "HawkfireBenefitStatus", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AddHawkFireBenefitsForChildUser {
    private static final String TAG = AddHawkFireBenefitsForChildUser.class.getSimpleName();
    private final AddHawkFireListener listener;
    private final Context mContext;

    /* JADX INFO: compiled from: AddHawkFireBenefitsForChildUser.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/account/signintasks/AddHawkFireBenefitsForChildUser$AddHawkFireListener;", "", "onFailure", "", "isAccountFull", "", "onSuccess", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface AddHawkFireListener {
        void onFailure(boolean isAccountFull);

        void onSuccess();
    }

    /* JADX INFO: compiled from: AddHawkFireBenefitsForChildUser.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/account/signintasks/AddHawkFireBenefitsForChildUser$HawkfireBenefitStatus;", "", "(Ljava/lang/String;I)V", "SUBSCRIPTION_NOT_REQUIRED", "SUBSCRIBING", "SUBSCRIPTION_FAILED_WITH_ERROR", "SUBSCRIPTION_SUCCESS", "SUBSCRIPTION_REQUIRED", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum HawkfireBenefitStatus {
        SUBSCRIPTION_NOT_REQUIRED,
        SUBSCRIBING,
        SUBSCRIPTION_FAILED_WITH_ERROR,
        SUBSCRIPTION_SUCCESS,
        SUBSCRIPTION_REQUIRED
    }

    public AddHawkFireBenefitsForChildUser(Context mContext, AddHawkFireListener listener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mContext = mContext;
        this.listener = listener;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.account.signintasks.AddHawkFireBenefitsForChildUser$execute$1, reason: invalid class name */
    /* JADX INFO: compiled from: AddHawkFireBenefitsForChildUser.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.account.signintasks.AddHawkFireBenefitsForChildUser$execute$1", f = "AddHawkFireBenefitsForChildUser.kt", i = {}, l = {25, 26, 37}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AddHawkFireBenefitsForChildUser.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
            } catch (Exception e) {
                if (e instanceof SopranoExceptions.ChildAlreadySubscribedException) {
                    Log.warning(AddHawkFireBenefitsForChildUser.TAG, Intrinsics.stringPlus("Not a real exception. We do not want to block child user for this + ", e));
                    MainCoroutineDispatcher main2 = Dispatchers.getMain();
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(AddHawkFireBenefitsForChildUser.this, null);
                    this.label = 3;
                    if (BuildersKt.withContext(main2, anonymousClass2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (e instanceof SopranoExceptions.SharingIneligibleException) {
                    Log.error(AddHawkFireBenefitsForChildUser.TAG, Intrinsics.stringPlus("Could not apply HawkFire benefits to child user + ", e));
                    AddHawkFireBenefitsForChildUser.this.listener.onFailure(true);
                } else {
                    Log.error(AddHawkFireBenefitsForChildUser.TAG, Intrinsics.stringPlus("Could not apply HawkFire benefits to child user + ", e));
                    AddHawkFireBenefitsForChildUser.this.listener.onFailure(false);
                }
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AddHawkFireBenefitsForChildUser addHawkFireBenefitsForChildUser = AddHawkFireBenefitsForChildUser.this;
                this.label = 1;
                if (addHawkFireBenefitsForChildUser.subscribeBenefits(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        Log.verbose(AddHawkFireBenefitsForChildUser.TAG, "Hawkfire benefits have been applied for the user.");
                        return Unit.INSTANCE;
                    }
                    if (i == 3) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            MainCoroutineDispatcher main3 = Dispatchers.getMain();
            C00671 c00671 = new C00671(AddHawkFireBenefitsForChildUser.this, null);
            this.label = 2;
            if (BuildersKt.withContext(main3, c00671, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            Log.verbose(AddHawkFireBenefitsForChildUser.TAG, "Hawkfire benefits have been applied for the user.");
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.amazon.mp3.account.signintasks.AddHawkFireBenefitsForChildUser$execute$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: AddHawkFireBenefitsForChildUser.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.mp3.account.signintasks.AddHawkFireBenefitsForChildUser$execute$1$1", f = "AddHawkFireBenefitsForChildUser.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ AddHawkFireBenefitsForChildUser this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00671(AddHawkFireBenefitsForChildUser addHawkFireBenefitsForChildUser, Continuation<? super C00671> continuation) {
                super(2, continuation);
                this.this$0 = addHawkFireBenefitsForChildUser;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00671(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.listener.onSuccess();
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: com.amazon.mp3.account.signintasks.AddHawkFireBenefitsForChildUser$execute$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: AddHawkFireBenefitsForChildUser.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.mp3.account.signintasks.AddHawkFireBenefitsForChildUser$execute$1$2", f = "AddHawkFireBenefitsForChildUser.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ AddHawkFireBenefitsForChildUser this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(AddHawkFireBenefitsForChildUser addHawkFireBenefitsForChildUser, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.this$0 = addHawkFireBenefitsForChildUser;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.listener.onSuccess();
                return Unit.INSTANCE;
            }
        }
    }

    public final void execute() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass1(null), 2, null);
    }

    public final Object subscribeBenefits(Continuation<? super Unit> continuation) throws Throwable {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("planCategory", "HAWKFIRE");
        jSONObject.put(Splash.PARAMS_DEVICE_TYPE, AccountCredentialStorage.get(this.mContext).getDeviceType());
        jSONObject.put(Splash.PARAMS_DEVICE_ID, getDeviceId());
        jSONObject.put(Splash.PARAMS_MUSIC_TERRITORY, AccountDetailUtil.getMusicTerritoryOfResidence());
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(jSONObject, null), continuation);
        return objWithContext == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.account.signintasks.AddHawkFireBenefitsForChildUser$subscribeBenefits$2, reason: invalid class name */
    /* JADX INFO: compiled from: AddHawkFireBenefitsForChildUser.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lorg/json/JSONObject;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.account.signintasks.AddHawkFireBenefitsForChildUser$subscribeBenefits$2", f = "AddHawkFireBenefitsForChildUser.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super JSONObject>, Object> {
        final /* synthetic */ JSONObject $requestParams;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(JSONObject jSONObject, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$requestParams = jSONObject;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$requestParams, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super JSONObject> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Exception {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                return new SopranoRequest("ShareMusicForChild").execute(this.$requestParams);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    private final String getDeviceId() {
        return new DeviceAttributesQuery(this.mContext).fetchDeviceSerialNumber();
    }
}
