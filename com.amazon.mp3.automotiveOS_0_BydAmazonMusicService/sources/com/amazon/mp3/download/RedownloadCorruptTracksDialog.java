package com.amazon.mp3.download;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: RedownloadCorruptTracksDialog.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\u0003\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/download/RedownloadCorruptTracksDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "onDismiss", "Lkotlin/Function0;", "", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "dialog", "Landroid/content/DialogInterface;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RedownloadCorruptTracksDialog extends DialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;
    private Function0<Unit> onDismiss;

    /* JADX INFO: compiled from: RedownloadCorruptTracksDialog.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/download/RedownloadCorruptTracksDialog$Companion;", "", "()V", "ON_DISMISS_DIALOG", "", "ON_QUEUE_DOWNLOAD", "TAG", "newInstance", "Lcom/amazon/mp3/download/RedownloadCorruptTracksDialog;", "onQueueDownload", "Lkotlin/Function0;", "", "onDismiss", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RedownloadCorruptTracksDialog newInstance(Function0<Unit> onQueueDownload, Function0<Unit> onDismiss) {
            Intrinsics.checkNotNullParameter(onQueueDownload, "onQueueDownload");
            Intrinsics.checkNotNullParameter(onDismiss, "onDismiss");
            RedownloadCorruptTracksDialog redownloadCorruptTracksDialog = new RedownloadCorruptTracksDialog();
            Bundle bundle = new Bundle();
            bundle.putSerializable("onQueuedDownload", onQueueDownload instanceof Serializable ? (Serializable) onQueueDownload : null);
            bundle.putSerializable("onDismissDialog", onDismiss instanceof Serializable ? (Serializable) onDismiss : null);
            redownloadCorruptTracksDialog.setArguments(bundle);
            return redownloadCorruptTracksDialog;
        }
    }

    static {
        String simpleName = RedownloadCorruptTracksDialog.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "RedownloadCorruptTracksD…og::class.java.simpleName");
        TAG = simpleName;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        Object obj = arguments == null ? null : arguments.get("onQueuedDownload");
        final Function0 function0 = TypeIntrinsics.isFunctionOfArity(obj, 0) ? (Function0) obj : null;
        Bundle arguments2 = getArguments();
        Object obj2 = arguments2 == null ? null : arguments2.get("onDismissDialog");
        this.onDismiss = TypeIntrinsics.isFunctionOfArity(obj2, 0) ? (Function0) obj2 : null;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        return new BauhausDialogBuilder(contextRequireContext).setMessage(R.string.corrupted_download_dialog_message).setTitle(R.string.corrupted_download_dialog_title).setNegativeButton(R.string.corrupted_download_dialog_ignore, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.download.RedownloadCorruptTracksDialog$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                RedownloadCorruptTracksDialog.m958onCreateDialog$lambda0(this.f$0, dialogInterface, i);
            }
        }).setPositiveButton(R.string.corrupted_download_dialog_queue, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.download.RedownloadCorruptTracksDialog$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                RedownloadCorruptTracksDialog.m959onCreateDialog$lambda1(function0, dialogInterface, i);
            }
        }).create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateDialog$lambda-0, reason: not valid java name */
    public static final void m958onCreateDialog$lambda0(RedownloadCorruptTracksDialog this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateDialog$lambda-1, reason: not valid java name */
    public static final void m959onCreateDialog$lambda1(Function0 function0, DialogInterface dialogInterface, int i) {
        if (function0 == null) {
            return;
        }
        function0.invoke();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        Function0<Unit> function0 = this.onDismiss;
        if (function0 == null) {
            return;
        }
        function0.invoke();
    }
}
