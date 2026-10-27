package com.amazon.mp3.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.music.destination.parser.ParserUtil;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoNetworkEmptyPageDialog.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0002\u0015\u0016B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\u0006J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001a\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/dialog/NoNetworkEmptyPageDialog;", "Landroidx/fragment/app/DialogFragment;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/mp3/dialog/NoNetworkEmptyPageDialog$Listener;", "(Lcom/amazon/mp3/dialog/NoNetworkEmptyPageDialog$Listener;)V", "clickOccurred", "", "isShowing", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onDismiss", "", "dialogInterface", "Landroid/content/DialogInterface;", "show", "manager", "Landroidx/fragment/app/FragmentManager;", ParserUtil.TAG_QUERY_PARAM_NAME, "", "Companion", "Listener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NoNetworkEmptyPageDialog extends DialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean clickOccurred;
    private final Listener listener;

    /* JADX INFO: compiled from: NoNetworkEmptyPageDialog.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/dialog/NoNetworkEmptyPageDialog$Listener;", "", "goOfflineClickAction", "", "onNoNetworkDialogCanceled", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Listener {
        void goOfflineClickAction();

        void onNoNetworkDialogCanceled();
    }

    public NoNetworkEmptyPageDialog() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public NoNetworkEmptyPageDialog(Listener listener) {
        this.listener = listener;
    }

    public /* synthetic */ NoNetworkEmptyPageDialog(Listener listener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : listener);
    }

    /* JADX INFO: compiled from: NoNetworkEmptyPageDialog.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/dialog/NoNetworkEmptyPageDialog$Companion;", "", "()V", "FRAGMENT_TAG", "", "defaultGoOfflineAction", "", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void defaultGoOfflineAction(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intentPutExtra = MusicHomeActivity.getStartIntent(context).putExtra("com.amazon.mp3.fragment.extra", MusicHomeActivity.getMyLibraryHomeFragmentName()).putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus-local");
            Intrinsics.checkNotNullExpressionValue(intentPutExtra, "getStartIntent(context)\n…rrusMediaSource.ID_LOCAL)");
            context.startActivity(intentPutExtra);
        }
    }

    public final boolean isShowing() {
        Dialog dialog = getDialog();
        return dialog != null && dialog.isShowing();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager manager, String tag) {
        Intrinsics.checkNotNullParameter(manager, "manager");
        this.clickOccurred = false;
        if (!isAdded()) {
            super.show(manager, tag);
            return;
        }
        Dialog dialog = getDialog();
        if (dialog == null) {
            return;
        }
        dialog.show();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        if (this.clickOccurred) {
            Dialog dialog = getDialog();
            if (dialog == null) {
                return;
            }
            dialog.hide();
            return;
        }
        super.onDismiss(dialogInterface);
        Listener listener = this.listener;
        if (listener == null) {
            return;
        }
        listener.onNoNetworkDialogCanceled();
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context it = getContext();
        if (it == null) {
            it = AmazonApplication.getApplication().getApplicationContext();
        }
        Intrinsics.checkNotNullExpressionValue(it, "it");
        return new BauhausDialogBuilder(it).setTitle(R.string.dmusic_no_connection_title).setMessage(R.string.dmusic_no_connection_desc_generic).setPositiveButton(R.string.dmusic_no_network_go_to_downloads, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.dialog.NoNetworkEmptyPageDialog$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                NoNetworkEmptyPageDialog.m899onCreateDialog$lambda2$lambda1(this.f$0, it, dialogInterface, i);
            }
        }).create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateDialog$lambda-2$lambda-1, reason: not valid java name */
    public static final void m899onCreateDialog$lambda2$lambda1(NoNetworkEmptyPageDialog this$0, Context it, DialogInterface dialogInterface, int i) {
        Unit unit;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.clickOccurred = true;
        Listener listener = this$0.listener;
        if (listener == null) {
            unit = null;
        } else {
            listener.goOfflineClickAction();
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Companion companion = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            companion.defaultGoOfflineAction(it);
        }
    }
}
