package com.amazon.mp3.customerprofile;

import android.content.Context;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.google.android.gms.wearable.WearableStatusCodes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CustomerProfileService.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \u00132\u00020\u0001:\u0004\u0012\u0013\u0014\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JH\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/customerprofile/CustomerProfileService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "update", "", "avatarUri", "Landroid/net/Uri;", "name", "", "handle", NotificationCompat.CATEGORY_STATUS, "callback", "Lcom/amazon/mp3/customerprofile/CustomerProfileService$Callback;", "imageRotation", "", "shareListeningHistory", "Callback", "Companion", "UpdateAvatarCallback", "UpdateException", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileService {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOG;
    private final Context context;

    /* JADX INFO: compiled from: CustomerProfileService.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/customerprofile/CustomerProfileService$Callback;", "", "onError", "", "exception", "Lcom/amazon/mp3/customerprofile/CustomerProfileService$UpdateException;", "onSuccess", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Callback {
        void onError(UpdateException exception);

        void onSuccess();
    }

    public CustomerProfileService(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.customerprofile.CustomerProfileService$update$1, reason: invalid class name */
    /* JADX INFO: compiled from: CustomerProfileService.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.customerprofile.CustomerProfileService$update$1", f = "CustomerProfileService.kt", i = {0, 0, 1, 1, 1}, l = {35, 40}, m = "invokeSuspend", n = {"socialProfileService", "isShareListeningHistoryUpdated", "updateSuccess", "profileService", "isShareListeningHistoryUpdated"}, s = {"L$0", "I$0", "L$0", "L$1", "I$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Uri $avatarUri;
        final /* synthetic */ Callback $callback;
        final /* synthetic */ String $handle;
        final /* synthetic */ int $imageRotation;
        final /* synthetic */ boolean $isNameUpdated;
        final /* synthetic */ boolean $isPictureUpdated;
        final /* synthetic */ boolean $isStatusUpdated;
        final /* synthetic */ String $name;
        final /* synthetic */ String $shareListeningHistory;
        final /* synthetic */ String $status;
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ CustomerProfileService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, String str2, String str3, Uri uri, String str4, boolean z, CustomerProfileService customerProfileService, boolean z2, boolean z3, Callback callback, int i, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$shareListeningHistory = str;
            this.$name = str2;
            this.$status = str3;
            this.$avatarUri = uri;
            this.$handle = str4;
            this.$isPictureUpdated = z;
            this.this$0 = customerProfileService;
            this.$isNameUpdated = z2;
            this.$isStatusUpdated = z3;
            this.$callback = callback;
            this.$imageRotation = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$shareListeningHistory, this.$name, this.$status, this.$avatarUri, this.$handle, this.$isPictureUpdated, this.this$0, this.$isNameUpdated, this.$isStatusUpdated, this.$callback, this.$imageRotation, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x009e  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00bc  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                Method dump skipped, instruction units count: 308
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.customerprofile.CustomerProfileService.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void update(Uri avatarUri, String name, String handle, String status, Callback callback, int imageRotation, String shareListeningHistory) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        boolean z = avatarUri != null;
        boolean z2 = name != null;
        boolean z3 = handle != null;
        boolean z4 = status != null;
        if (z2 || z3 || z4 || z) {
            try {
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new AnonymousClass1(shareListeningHistory, name, status, avatarUri, handle, z, this, z2, z4, callback, imageRotation, null), 3, null);
            } catch (Exception e) {
                callback.onError(new UpdateException(WearableStatusCodes.DATA_ITEM_TOO_LARGE, e, false, false, false, false, 60, null));
            }
        }
    }

    /* JADX INFO: compiled from: CustomerProfileService.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/customerprofile/CustomerProfileService$UpdateAvatarCallback;", "Lcom/amazon/mp3/customerprofile/CustomerProfileService$Callback;", "callback", "(Lcom/amazon/mp3/customerprofile/CustomerProfileService;Lcom/amazon/mp3/customerprofile/CustomerProfileService$Callback;)V", "onError", "", "exception", "Lcom/amazon/mp3/customerprofile/CustomerProfileService$UpdateException;", "onSuccess", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class UpdateAvatarCallback implements Callback {
        private final Callback callback;
        final /* synthetic */ CustomerProfileService this$0;

        public UpdateAvatarCallback(CustomerProfileService this$0, Callback callback) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.this$0 = this$0;
            this.callback = callback;
        }

        @Override // com.amazon.mp3.customerprofile.CustomerProfileService.Callback
        public void onError(UpdateException exception) {
            Intrinsics.checkNotNullParameter(exception, "exception");
            int code = exception.getCode();
            if (code == 4001 || code == 4002 || code == 5000 || code == 5001) {
                FlexEvent flexEventBuild = FlexEvent.builder("customerProfile").withFlexStr1("profile.update.photo.error").withFlexStr2(Intrinsics.stringPlus("Profile photo upload failed with error code: ", Integer.valueOf(code))).build();
                Intrinsics.checkNotNullExpressionValue(flexEventBuild, "builder(BrushFragment.PR…                 .build()");
                MetricsLogger.sendEvent(flexEventBuild);
            }
            this.callback.onError(exception);
        }

        @Override // com.amazon.mp3.customerprofile.CustomerProfileService.Callback
        public void onSuccess() {
            this.callback.onSuccess();
        }
    }

    /* JADX INFO: compiled from: CustomerProfileService.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00060\u0001j\u0002`\u0002BI\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\u0010\b\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0014\u0010\u0015B1\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\f\u0012\u0006\u0010\u0010\u001a\u00020\f\u0012\u0006\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0014\u0010\u0016R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u001f\u0010\b\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0012\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/customerprofile/CustomerProfileService$UpdateException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "", "code", "I", "getCode", "()I", "originalException", "Ljava/lang/Exception;", "getOriginalException", "()Ljava/lang/Exception;", "", "avatarUpdated", "Z", "nameUpdated", "statusUpdated", "shareListeningHistoryUpdated", "isInvalidNameException", "()Z", "<init>", "(ILjava/lang/Exception;ZZZZ)V", "(IZZZZ)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class UpdateException extends Exception {
        private final boolean avatarUpdated;
        private final int code;
        private final boolean nameUpdated;
        private final Exception originalException;
        private final boolean shareListeningHistoryUpdated;
        private final boolean statusUpdated;

        @JvmOverloads
        public UpdateException(int i, Exception exc, boolean z, boolean z2, boolean z3, boolean z4) {
            this.code = i;
            this.originalException = exc;
            this.avatarUpdated = z;
            this.nameUpdated = z2;
            this.statusUpdated = z3;
            this.shareListeningHistoryUpdated = z4;
        }

        public /* synthetic */ UpdateException(int i, Exception exc, boolean z, boolean z2, boolean z3, boolean z4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, exc, (i2 & 4) != 0 ? false : z, (i2 & 8) != 0 ? false : z2, (i2 & 16) != 0 ? false : z3, (i2 & 32) != 0 ? false : z4);
        }

        public final int getCode() {
            return this.code;
        }

        public UpdateException(int i, boolean z, boolean z2, boolean z3, boolean z4) {
            this(i, null, z, z2, z3, z4);
        }

        public final boolean isInvalidNameException() {
            return this.code == 4000;
        }
    }

    /* JADX INFO: compiled from: CustomerProfileService.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/customerprofile/CustomerProfileService$Companion;", "", "()V", "CLIENT_ERROR_GENERAL", "", "CLIENT_ERROR_IMAGE_READ", "CLIENT_ERROR_IMAGE_UPLOAD", "CLIENT_ERROR_INVALID_NAME", "LOG", "Lorg/slf4j/Logger;", "getLOG", "()Lorg/slf4j/Logger;", "SERVER_ERROR_IMAGE_UPLOAD", "SERVER_ERROR_IMAGE_UPLOAD_AUTHENTICATION", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Logger getLOG() {
            return CustomerProfileService.LOG;
        }
    }

    static {
        Logger logger = LoggerFactory.getLogger((Class<?>) CustomerProfileService.class);
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(CustomerProfileService::class.java)");
        LOG = logger;
    }
}
