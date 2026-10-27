package com.amazon.music.authentication;

import com.amazon.music.platform.data.PCAProfile;
import com.amazon.music.platform.providers.ActorAuthenticationProvider;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.ProfileProvider;
import com.visualon.OSMPUtils.voOSType;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: ProfileManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B-\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\u0004\b#\u0010$J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\u001b\u0010\t\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\b*\u00020\u0007H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\n\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0016R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001aR\u001c\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010\u001dR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"Lcom/amazon/music/authentication/ProfileManager;", "Lcom/amazon/music/platform/providers/ProfileProvider;", "", "value", "publishOAuthIfMutexUnlocked", "Lcom/amazon/music/platform/data/PCAProfile;", "currentProfile", "", "T", "getProfileSwitchNotificationPublisher", "()Ljava/lang/Object;", "getAccessToken", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccessTokenBlocking", "Lcom/amazon/music/platform/providers/ActorAuthenticationProvider;", "actorAuthenticationProvider", "Lcom/amazon/music/platform/providers/ActorAuthenticationProvider;", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "accountAuthenticationProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "Lkotlin/Function0;", "", "isPCAEnabled", "Lkotlin/jvm/functions/Function0;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "authTokenMutableStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlinx/coroutines/flow/StateFlow;", "authTokenStateFlow", "Lkotlinx/coroutines/flow/StateFlow;", "profileChangedMutableStateFlow", "profileChangedStateFlow", "Lkotlinx/coroutines/sync/Mutex;", "mutexLock", "Lkotlinx/coroutines/sync/Mutex;", "<init>", "(Lcom/amazon/music/platform/providers/ActorAuthenticationProvider;Lcom/amazon/music/platform/providers/AuthenticationProvider;Lkotlin/jvm/functions/Function0;)V", "DMMAuthentication_release"}, k = 1, mv = {1, 6, 0})
public final class ProfileManager implements ProfileProvider {
    private final AuthenticationProvider accountAuthenticationProvider;
    private final ActorAuthenticationProvider actorAuthenticationProvider;
    private final MutableStateFlow<String> authTokenMutableStateFlow;
    private final StateFlow<String> authTokenStateFlow;
    private final Function0<Boolean> isPCAEnabled;
    private final Mutex mutexLock;
    private final MutableStateFlow<String> profileChangedMutableStateFlow;
    private final StateFlow<String> profileChangedStateFlow;

    /* JADX INFO: renamed from: com.amazon.music.authentication.ProfileManager$getAccessToken$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProfileManager.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.authentication.ProfileManager", f = "ProfileManager.kt", i = {0, 1}, l = {122, WorkQueueKt.MASK}, m = "getAccessToken", n = {"this", "this"}, s = {"L$0", "L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ProfileManager.this.getAccessToken(this);
        }
    }

    @Override // com.amazon.music.platform.providers.ProfileProvider
    public PCAProfile currentProfile() {
        return null;
    }

    public ProfileManager(ActorAuthenticationProvider actorAuthenticationProvider, AuthenticationProvider authenticationProvider, Function0<Boolean> isPCAEnabled) {
        Intrinsics.checkNotNullParameter(isPCAEnabled, "isPCAEnabled");
        this.actorAuthenticationProvider = actorAuthenticationProvider;
        this.accountAuthenticationProvider = authenticationProvider;
        this.isPCAEnabled = isPCAEnabled;
        MutableStateFlow<String> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this.authTokenMutableStateFlow = MutableStateFlow;
        this.authTokenStateFlow = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow<String> MutableStateFlow2 = StateFlowKt.MutableStateFlow(null);
        this.profileChangedMutableStateFlow = MutableStateFlow2;
        this.profileChangedStateFlow = FlowKt.asStateFlow(MutableStateFlow2);
        this.mutexLock = MutexKt.Mutex$default(false, 1, null);
    }

    @Override // com.amazon.music.platform.providers.ProfileProvider
    public <T> T getProfileSwitchNotificationPublisher() {
        T t = (T) this.profileChangedStateFlow;
        if (t != null) {
            return t;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.amazon.music.platform.providers.ProfileProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getAccessToken(kotlin.coroutines.Continuation<? super java.lang.String> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.music.authentication.ProfileManager.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.music.authentication.ProfileManager$getAccessToken$1 r0 = (com.amazon.music.authentication.ProfileManager.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.authentication.ProfileManager$getAccessToken$1 r0 = new com.amazon.music.authentication.ProfileManager$getAccessToken$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            if (r2 == 0) goto L47
            r1 = 1
            if (r2 == r1) goto L39
            if (r2 != r4) goto L31
            java.lang.Object r0 = r0.L$0
            com.amazon.music.authentication.ProfileManager r0 = (com.amazon.music.authentication.ProfileManager) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L6d
        L31:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L39:
            java.lang.Object r0 = r0.L$0
            com.amazon.music.authentication.ProfileManager r0 = (com.amazon.music.authentication.ProfileManager) r0
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r6 = r0.publishOAuthIfMutexUnlocked(r6)
            return r6
        L47:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.jvm.functions.Function0<java.lang.Boolean> r6 = r5.isPCAEnabled
            java.lang.Object r6 = r6.invoke()
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L5a
            com.amazon.music.platform.providers.ActorAuthenticationProvider r6 = r5.actorAuthenticationProvider
        L5a:
            com.amazon.music.platform.providers.AuthenticationProvider r6 = r5.accountAuthenticationProvider
            if (r6 != 0) goto L61
            r0 = r5
            r6 = r3
            goto L6f
        L61:
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r6.getOAuthInfo(r0)
            if (r6 != r1) goto L6c
            return r1
        L6c:
            r0 = r5
        L6d:
            com.amazon.music.platform.Outcome r6 = (com.amazon.music.platform.Outcome) r6
        L6f:
            boolean r1 = r6 instanceof com.amazon.music.platform.Outcome.Success
            if (r1 == 0) goto L84
            com.amazon.music.platform.Outcome$Success r6 = (com.amazon.music.platform.Outcome.Success) r6
            java.lang.Object r6 = r6.getValue()
            com.amazon.music.platform.data.OAuthInfo r6 = (com.amazon.music.platform.data.OAuthInfo) r6
            java.lang.String r6 = r6.getAccessToken()
            java.lang.String r6 = r0.publishOAuthIfMutexUnlocked(r6)
            return r6
        L84:
            java.lang.String r6 = r0.publishOAuthIfMutexUnlocked(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.authentication.ProfileManager.getAccessToken(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.amazon.music.authentication.ProfileManager$getAccessTokenBlocking$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProfileManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.authentication.ProfileManager$getAccessTokenBlocking$1", f = "ProfileManager.kt", i = {}, l = {voOSType.VOOSMP_PID_ANALYTICS_EXPORT_GET_PACKET}, m = "invokeSuspend", n = {}, s = {})
    static final class C04231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        int label;

        C04231(Continuation<? super C04231> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ProfileManager.this.new C04231(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((C04231) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProfileManager profileManager = ProfileManager.this;
                this.label = 1;
                obj = profileManager.getAccessToken(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    @Override // com.amazon.music.platform.providers.ProfileProvider
    public String getAccessTokenBlocking() {
        return (String) BuildersKt__BuildersKt.runBlocking$default(null, new C04231(null), 1, null);
    }

    private final String publishOAuthIfMutexUnlocked(String value) {
        if (!this.mutexLock.isLocked()) {
            this.authTokenMutableStateFlow.tryEmit(value);
        }
        return value;
    }
}
