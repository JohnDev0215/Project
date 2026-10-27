package com.amazon.mp3.playlist;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playlist.CreatePublicPlaylistWarningDialog;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CreatePublicPlaylistWarningDialog.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0004\u001a\u00020\u0002H&J\b\u0010\u0005\u001a\u00020\u0002H&J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\u000e\u001a\u00020\u0006H\u0002J\b\u0010\u000f\u001a\u00020\u0006H\u0002J\b\u0010\u0010\u001a\u00020\u0006H\u0002¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/playlist/CreatePublicPlaylistWarningDialog;", "", "", "onPositiveButtonClicked", "onNegativeButtonClicked", "onDismissed", "", "getNegativeButtonTextResId", "Landroid/content/Context;", "context", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/mp3/playlist/CreatePublicPlaylistWarningView;", "getNewCreatePublicPlaylistWarningView", "getPositiveButtonTextResId", "getSubtitleResId", "getTitleResId", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface CreatePublicPlaylistWarningDialog {
    void onDismissed();

    void onNegativeButtonClicked();

    void onPositiveButtonClicked();

    /* JADX INFO: compiled from: CreatePublicPlaylistWarningDialog.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        private static int getNegativeButtonTextResId(CreatePublicPlaylistWarningDialog createPublicPlaylistWarningDialog) {
            return R.string.dmusic_playlist_create_public_toggle_warning_negative_button;
        }

        private static int getPositiveButtonTextResId(CreatePublicPlaylistWarningDialog createPublicPlaylistWarningDialog) {
            return R.string.dmusic_playlist_create_public_toggle_warning_positive_button;
        }

        private static int getSubtitleResId(CreatePublicPlaylistWarningDialog createPublicPlaylistWarningDialog) {
            return R.string.dmusic_playlist_create_public_toggle_warning_description;
        }

        private static int getTitleResId(CreatePublicPlaylistWarningDialog createPublicPlaylistWarningDialog) {
            return R.string.dmusic_playlist_create_public_toggle_warning_title;
        }

        public static Dialog getDialog(final CreatePublicPlaylistWarningDialog createPublicPlaylistWarningDialog, Context context) {
            Intrinsics.checkNotNullParameter(createPublicPlaylistWarningDialog, "this");
            Intrinsics.checkNotNullParameter(context, "context");
            StyleSheet value = StyleSheetProvider.getStyleSheet().getValue();
            CreatePublicPlaylistWarningView newCreatePublicPlaylistWarningView = value == null ? null : getNewCreatePublicPlaylistWarningView(createPublicPlaylistWarningDialog, context, value);
            if (newCreatePublicPlaylistWarningView != null) {
                newCreatePublicPlaylistWarningView.setPositiveButtonEnabled(true);
            }
            if (newCreatePublicPlaylistWarningView != null) {
                newCreatePublicPlaylistWarningView.setNegativeButtonEnabled(true);
            }
            return new BauhausDialogBuilder(context).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.mp3.playlist.CreatePublicPlaylistWarningDialog$DefaultImpls$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    CreatePublicPlaylistWarningDialog.DefaultImpls.m1560getDialog$lambda1(createPublicPlaylistWarningDialog, dialogInterface);
                }
            }).setView(newCreatePublicPlaylistWarningView).create();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getDialog$lambda-1, reason: not valid java name */
        public static void m1560getDialog$lambda1(CreatePublicPlaylistWarningDialog this$0, DialogInterface dialogInterface) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.onDismissed();
        }

        private static CreatePublicPlaylistWarningView getNewCreatePublicPlaylistWarningView(final CreatePublicPlaylistWarningDialog createPublicPlaylistWarningDialog, Context context, StyleSheet styleSheet) {
            CreatePublicPlaylistWarningView createPublicPlaylistWarningView = new CreatePublicPlaylistWarningView(context, styleSheet);
            createPublicPlaylistWarningView.setTitle(getTitleResId(createPublicPlaylistWarningDialog));
            createPublicPlaylistWarningView.setSubtitle(getSubtitleResId(createPublicPlaylistWarningDialog));
            createPublicPlaylistWarningView.setPositiveButton(getPositiveButtonTextResId(createPublicPlaylistWarningDialog), new View.OnClickListener() { // from class: com.amazon.mp3.playlist.CreatePublicPlaylistWarningDialog$DefaultImpls$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CreatePublicPlaylistWarningDialog.DefaultImpls.m1561getNewCreatePublicPlaylistWarningView$lambda2(createPublicPlaylistWarningDialog, view);
                }
            });
            createPublicPlaylistWarningView.setNegativeButton(getNegativeButtonTextResId(createPublicPlaylistWarningDialog), new View.OnClickListener() { // from class: com.amazon.mp3.playlist.CreatePublicPlaylistWarningDialog$DefaultImpls$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CreatePublicPlaylistWarningDialog.DefaultImpls.m1562getNewCreatePublicPlaylistWarningView$lambda3(createPublicPlaylistWarningDialog, view);
                }
            });
            return createPublicPlaylistWarningView;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getNewCreatePublicPlaylistWarningView$lambda-2, reason: not valid java name */
        public static void m1561getNewCreatePublicPlaylistWarningView$lambda2(CreatePublicPlaylistWarningDialog this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.onPositiveButtonClicked();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getNewCreatePublicPlaylistWarningView$lambda-3, reason: not valid java name */
        public static void m1562getNewCreatePublicPlaylistWarningView$lambda3(CreatePublicPlaylistWarningDialog this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.onNegativeButtonClicked();
        }
    }
}
