package com.android.permissioncontroller.permission.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.util.Log;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData;
import com.android.permissioncontroller.permission.data.UserSensitivityLiveData;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.UidSensitivityState;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: UserSensitiveFlagsUtils.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a,\u0010\b\u001a\u00020\u00032\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u0016\u0010\u000e\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"LOG_TAG", "", "updateUserSensitiveForUid", "", "uid", "", "callback", "Ljava/lang/Runnable;", "updateUserSensitiveForUidsInternal", "uidsUserSensitivity", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/UidSensitivityState;", "user", "Landroid/os/UserHandle;", "updateUserSensitiveForUser", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 2, mv = {1, 1, 16})
public final class UserSensitiveFlagsUtils {
    private static final String LOG_TAG = "UserSensitiveFlagsUtils";

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.utils.UserSensitiveFlagsUtils$updateUserSensitiveForUser$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: UserSensitiveFlagsUtils.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.utils.UserSensitiveFlagsUtils$updateUserSensitiveForUser$1", f = "UserSensitiveFlagsUtils.kt", i = {0}, l = {46}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C00591 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Runnable $callback;
        final /* synthetic */ UserHandle $user;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00591(UserHandle userHandle, Runnable runnable, Continuation continuation) {
            super(2, continuation);
            this.$user = userHandle;
            this.$callback = runnable;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C00591 c00591 = new C00591(this.$user, this.$callback, completion);
            c00591.p$ = (CoroutineScope) obj;
            return c00591;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00591) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws PackageManager.NameNotFoundException {
            Object initializedValue$default;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$launch = this.p$;
                UserSensitivityLiveData userSensitivityLiveData = UserSensitivityLiveData.INSTANCE.get(this.$user);
                this.L$0 = $this$launch;
                this.label = 1;
                initializedValue$default = SmartUpdateMediatorLiveData.getInitializedValue$default(userSensitivityLiveData, false, false, this, 3, null);
                if (initializedValue$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure($result);
                initializedValue$default = $result;
            }
            Map uidUserSensitivity = (Map) initializedValue$default;
            if (uidUserSensitivity != null) {
                UserSensitiveFlagsUtils.updateUserSensitiveForUidsInternal(uidUserSensitivity, this.$user, this.$callback);
                return Unit.INSTANCE;
            }
            this.$callback.run();
            throw new IllegalStateException("All uids sensitivity liveData should not be null if initialized");
        }
    }

    public static final void updateUserSensitiveForUser(UserHandle user, Runnable callback) {
        Intrinsics.checkParameterIsNotNull(user, "user");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, AndroidUtilsKt.getIPC(), null, new C00591(user, callback, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateUserSensitiveForUidsInternal(Map<Integer, UidSensitivityState> map, UserHandle user, Runnable callback) throws PackageManager.NameNotFoundException {
        Context userContext = Utils.getUserContext(PermissionControllerApplication.get(), user);
        Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(Per…rApplication.get(), user)");
        PackageManager pm = userContext.getPackageManager();
        for (Map.Entry<Integer, UidSensitivityState> entry : map.entrySet()) {
            int uid = entry.getKey().intValue();
            UidSensitivityState uidState = entry.getValue();
            for (LightPackageInfo pkg : uidState.getPackages()) {
                for (String perm : pkg.getRequestedPermissions()) {
                    Integer num = uidState.getPermStates().get(perm);
                    if (num != null) {
                        int flags = num.intValue();
                        try {
                            int oldFlags = pm.getPermissionFlags(perm, pkg.getPackageName(), user) & Utils.FLAGS_ALWAYS_USER_SENSITIVE;
                            if (flags != oldFlags) {
                                try {
                                    pm.updatePermissionFlags(perm, pkg.getPackageName(), Utils.FLAGS_ALWAYS_USER_SENSITIVE, flags, user);
                                } catch (IllegalArgumentException e) {
                                    e = e;
                                    String message = e.getMessage();
                                    if (message != null && !StringsKt.startsWith$default(message, "Unknown permission: ", false, 2, (Object) null)) {
                                        Log.e(LOG_TAG, "Unexpected exception while updating flags for " + pkg.getPackageName() + " (uid " + uid + ") permission " + perm, e);
                                    }
                                }
                            }
                        } catch (IllegalArgumentException e2) {
                            e = e2;
                        }
                    }
                }
            }
        }
        callback.run();
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.utils.UserSensitiveFlagsUtils$updateUserSensitiveForUid$1, reason: invalid class name */
    /* JADX INFO: compiled from: UserSensitiveFlagsUtils.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.utils.UserSensitiveFlagsUtils$updateUserSensitiveForUid$1", f = "UserSensitiveFlagsUtils.kt", i = {0}, l = {97}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Runnable $callback;
        final /* synthetic */ int $uid;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(int i, Runnable runnable, Continuation continuation) {
            super(2, continuation);
            this.$uid = i;
            this.$callback = runnable;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$uid, this.$callback, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws PackageManager.NameNotFoundException {
            Object initializedValue$default;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$launch = this.p$;
                UserSensitivityLiveData userSensitivityLiveData = UserSensitivityLiveData.INSTANCE.get(this.$uid);
                this.L$0 = $this$launch;
                this.label = 1;
                initializedValue$default = SmartUpdateMediatorLiveData.getInitializedValue$default(userSensitivityLiveData, false, false, this, 3, null);
                if (initializedValue$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure($result);
                initializedValue$default = $result;
            }
            Map uidSensitivityState = (Map) initializedValue$default;
            if (uidSensitivityState != null) {
                UserHandle userHandleForUid = UserHandle.getUserHandleForUid(this.$uid);
                Intrinsics.checkExpressionValueIsNotNull(userHandleForUid, "UserHandle.getUserHandleForUid(uid)");
                UserSensitiveFlagsUtils.updateUserSensitiveForUidsInternal(uidSensitivityState, userHandleForUid, this.$callback);
            } else {
                Log.e(UserSensitiveFlagsUtils.LOG_TAG, "No packages associated with uid " + this.$uid + ", not updating flags");
                this.$callback.run();
            }
            return Unit.INSTANCE;
        }
    }

    public static final void updateUserSensitiveForUid(int uid, Runnable callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, AndroidUtilsKt.getIPC(), null, new AnonymousClass1(uid, callback, null), 2, null);
    }
}
