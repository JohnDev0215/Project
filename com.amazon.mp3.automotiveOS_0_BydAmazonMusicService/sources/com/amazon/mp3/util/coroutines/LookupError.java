package com.amazon.mp3.util.coroutines;

import android.app.Activity;
import android.content.DialogInterface;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.music.media.playback.util.AndroidUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LookupError.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0007J\u001e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0002J\u001e\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0002\u0082\u0001\u0002\r\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/util/coroutines/LookupError;", "", "()V", "handleError", "", "retry", "Lkotlin/Function0;", "showErrorDialog", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "showNetworkErrorDialog", "NetworkError", "UnknownError", "Lcom/amazon/mp3/util/coroutines/LookupError$NetworkError;", "Lcom/amazon/mp3/util/coroutines/LookupError$UnknownError;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class LookupError {
    public /* synthetic */ LookupError(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showErrorDialog$lambda-0, reason: not valid java name */
    public static final void m1654showErrorDialog$lambda0(DialogInterface dialogInterface, int i) {
    }

    @JvmOverloads
    public final void handleError() {
        handleError$default(this, null, 1, null);
    }

    /* JADX INFO: compiled from: LookupError.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/util/coroutines/LookupError$NetworkError;", "Lcom/amazon/mp3/util/coroutines/LookupError;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class NetworkError extends LookupError {
        public static final NetworkError INSTANCE = new NetworkError();

        private NetworkError() {
            super(null);
        }
    }

    private LookupError() {
    }

    /* JADX INFO: compiled from: LookupError.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/util/coroutines/LookupError$UnknownError;", "Lcom/amazon/mp3/util/coroutines/LookupError;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class UnknownError extends LookupError {
        public static final UnknownError INSTANCE = new UnknownError();

        private UnknownError() {
            super(null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void handleError$default(LookupError lookupError, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: handleError");
        }
        if ((i & 1) != 0) {
            function0 = new Function0<Unit>() { // from class: com.amazon.mp3.util.coroutines.LookupError.handleError.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        lookupError.handleError(function0);
    }

    @JvmOverloads
    public final void handleError(Function0<Unit> retry) {
        Intrinsics.checkNotNullParameter(retry, "retry");
        Activity activity = AndroidUtils.getCurrentActivity();
        if (ActivityKt.isAvailable(activity)) {
            if (this instanceof NetworkError) {
                Intrinsics.checkNotNullExpressionValue(activity, "activity");
                showNetworkErrorDialog(activity, retry);
            } else {
                Intrinsics.checkNotNullExpressionValue(activity, "activity");
                showErrorDialog(activity, retry);
            }
        }
    }

    private final void showErrorDialog(Activity activity, final Function0<Unit> retry) {
        new BauhausDialogBuilder(activity).setTitle(R.string.dmusic_streaming_device_exception_notification_title).setMessage(R.string.dmusic_recent_playback_error).setNegativeButton(R.string.dmusic_button_cancel, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.util.coroutines.LookupError$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                LookupError.m1654showErrorDialog$lambda0(dialogInterface, i);
            }
        }).setPositiveButton(R.string.dmusic_button_retry, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.util.coroutines.LookupError$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                LookupError.m1655showErrorDialog$lambda1(retry, dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showErrorDialog$lambda-1, reason: not valid java name */
    public static final void m1655showErrorDialog$lambda1(Function0 retry, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(retry, "$retry");
        retry.invoke();
    }

    private final void showNetworkErrorDialog(Activity activity, final Function0<Unit> retry) {
        NetworkErrorDialog.create(activity, new NetworkErrorDialog.INetworkErrorDialogHandler() { // from class: com.amazon.mp3.util.coroutines.LookupError.showNetworkErrorDialog.1
            @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
            public void onConnectionErrorDialogCancel() {
            }

            @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
            public void onDialogDismiss() {
            }

            @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
            public void onRetryConnection() {
                retry.invoke();
            }
        }).show();
    }
}
