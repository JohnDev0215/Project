package com.amazon.mp3.playlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
import androidx.appcompat.app.AlertDialog;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playlist.CreatePlaylistDialog;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CreatePlaylistDialog.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0004\u001a\u00020\u0002H&J\b\u0010\u0005\u001a\u00020\u0002H&J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH&J\b\u0010\f\u001a\u00020\u0001H&J\b\u0010\u000e\u001a\u00020\rH&J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\u0018\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\b\u0010\u001e\u001a\u00020\u0019H\u0002J\b\u0010\u001f\u001a\u00020\u0019H\u0002J\b\u0010 \u001a\u00020\u0019H\u0002J$\u0010#\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00020!H\u0002¨\u0006$"}, d2 = {"Lcom/amazon/mp3/playlist/CreatePlaylistDialog;", "", "", "onPositiveButtonClicked", "onNegativeButtonClicked", "onDismissed", "Landroid/widget/EditText;", "editText", "onEditTextInitialized", "Landroid/widget/ToggleButton;", "toggleButton", "onPublicToggleInitialized", "getAlertDialogBuilder", "", "isCreatePlaylist", "Landroid/content/Context;", "context", "Landroid/app/Dialog;", "getAlertDialog", "Landroid/app/AlertDialog$Builder;", "builder", "Landroidx/appcompat/app/AlertDialog$Builder;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "getBauhausDialog", "", "getNegativeButtonTextResId", "Lcom/amazon/mp3/playlist/CreatePlaylistView;", "getNewCreatePlaylistView", "getPositiveButtonTextResId", "getPublicToggleDescription", "getPublicToggleTitle", "getTitleResId", "Lkotlin/Function1;", "afterTextChanged", "initEditText", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface CreatePlaylistDialog {
    Object getAlertDialogBuilder();

    boolean isCreatePlaylist();

    void onDismissed();

    void onEditTextInitialized(EditText editText);

    void onNegativeButtonClicked();

    void onPositiveButtonClicked();

    void onPublicToggleInitialized(ToggleButton toggleButton);

    /* JADX INFO: compiled from: CreatePlaylistDialog.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        private static int getNegativeButtonTextResId(CreatePlaylistDialog createPlaylistDialog) {
            return R.string.dmusic_button_cancel;
        }

        private static int getPositiveButtonTextResId(CreatePlaylistDialog createPlaylistDialog) {
            return R.string.dmusic_button_save;
        }

        private static int getPublicToggleDescription(CreatePlaylistDialog createPlaylistDialog) {
            return R.string.dmusic_playlist_create_public_toggle_description;
        }

        private static int getPublicToggleTitle(CreatePlaylistDialog createPlaylistDialog) {
            return R.string.dmusic_playlist_create_public_toggle_title;
        }

        public static Dialog getDialog(CreatePlaylistDialog createPlaylistDialog, Context context) {
            Intrinsics.checkNotNullParameter(createPlaylistDialog, "this");
            Intrinsics.checkNotNullParameter(context, "context");
            StyleSheet value = StyleSheetProvider.getStyleSheet().getValue();
            if (BauhausDialogBuilder.INSTANCE.isBauhausDialogEnabled()) {
                boolean z = false;
                if (value != null && value.getIsInitCompleted()) {
                    z = true;
                }
                if (z) {
                    return getBauhausDialog(createPlaylistDialog, context, value);
                }
            }
            return getAlertDialog(createPlaylistDialog, context);
        }

        private static Dialog getBauhausDialog(final CreatePlaylistDialog createPlaylistDialog, Context context, StyleSheet styleSheet) {
            final CreatePlaylistView newCreatePlaylistView = getNewCreatePlaylistView(createPlaylistDialog, context, styleSheet);
            newCreatePlaylistView.setPositiveButtonEnabled(false);
            initEditText(createPlaylistDialog, newCreatePlaylistView.getEditText(), new Function1<Boolean, Unit>() { // from class: com.amazon.mp3.playlist.CreatePlaylistDialog$getBauhausDialog$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    newCreatePlaylistView.setPositiveButtonEnabled(z);
                }
            });
            createPlaylistDialog.onPublicToggleInitialized(newCreatePlaylistView.getPublicToggleSwitch());
            return new BauhausDialogBuilder(context).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.mp3.playlist.CreatePlaylistDialog$DefaultImpls$$ExternalSyntheticLambda4
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    CreatePlaylistDialog.DefaultImpls.m1558getBauhausDialog$lambda0(createPlaylistDialog, dialogInterface);
                }
            }).setView(newCreatePlaylistView).create();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getBauhausDialog$lambda-0, reason: not valid java name */
        public static void m1558getBauhausDialog$lambda0(CreatePlaylistDialog this$0, DialogInterface dialogInterface) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.onDismissed();
        }

        private static CreatePlaylistView getNewCreatePlaylistView(final CreatePlaylistDialog createPlaylistDialog, Context context, StyleSheet styleSheet) {
            CreatePlaylistView createPlaylistView = new CreatePlaylistView(context, styleSheet);
            createPlaylistView.setTitle(getTitleResId(createPlaylistDialog));
            createPlaylistView.setPublicToggleTitle(getPublicToggleTitle(createPlaylistDialog));
            createPlaylistView.setPublicToggleDescription(getPublicToggleDescription(createPlaylistDialog));
            createPlaylistView.setPositiveButton(getPositiveButtonTextResId(createPlaylistDialog), new View.OnClickListener() { // from class: com.amazon.mp3.playlist.CreatePlaylistDialog$DefaultImpls$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CreatePlaylistDialog.DefaultImpls.m1559getNewCreatePlaylistView$lambda1(createPlaylistDialog, view);
                }
            });
            return createPlaylistView;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getNewCreatePlaylistView$lambda-1, reason: not valid java name */
        public static void m1559getNewCreatePlaylistView$lambda1(CreatePlaylistDialog this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.onPositiveButtonClicked();
        }

        private static Dialog getAlertDialog(CreatePlaylistDialog createPlaylistDialog, Context context) {
            Object alertDialogBuilder = createPlaylistDialog.getAlertDialogBuilder();
            if (alertDialogBuilder instanceof AlertDialog.Builder) {
                return getAlertDialog(createPlaylistDialog, context, (AlertDialog.Builder) alertDialogBuilder);
            }
            if (alertDialogBuilder instanceof AlertDialog.Builder) {
                return getAlertDialog(createPlaylistDialog, context, (AlertDialog.Builder) alertDialogBuilder);
            }
            return getAlertDialog(createPlaylistDialog, context, new AlertDialog.Builder(context));
        }

        private static Dialog getAlertDialog(final CreatePlaylistDialog createPlaylistDialog, Context context, AlertDialog.Builder builder) {
            builder.setTitle(getTitleResId(createPlaylistDialog));
            builder.setPositiveButton(getPositiveButtonTextResId(createPlaylistDialog), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playlist.CreatePlaylistDialog$DefaultImpls$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    CreatePlaylistDialog.DefaultImpls.m1554getAlertDialog$lambda2(createPlaylistDialog, dialogInterface, i);
                }
            });
            builder.setNegativeButton(getNegativeButtonTextResId(createPlaylistDialog), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playlist.CreatePlaylistDialog$DefaultImpls$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    CreatePlaylistDialog.DefaultImpls.m1555getAlertDialog$lambda3(createPlaylistDialog, dialogInterface, i);
                }
            });
            View viewInflate = View.inflate(context, R.layout.library_name_playlist, null);
            builder.setView(viewInflate);
            final android.app.AlertDialog dialog = builder.create();
            View viewFindViewById = viewInflate.findViewById(R.id.PlaylistName);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "content.findViewById(R.id.PlaylistName)");
            initEditText(createPlaylistDialog, (EditText) viewFindViewById, new Function1<Boolean, Unit>() { // from class: com.amazon.mp3.playlist.CreatePlaylistDialog$getAlertDialog$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    Button button = dialog.getButton(-1);
                    if (button == null) {
                        return;
                    }
                    button.setEnabled(z);
                }
            });
            Intrinsics.checkNotNullExpressionValue(dialog, "dialog");
            return dialog;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getAlertDialog$lambda-2, reason: not valid java name */
        public static void m1554getAlertDialog$lambda2(CreatePlaylistDialog this$0, DialogInterface dialogInterface, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.onPositiveButtonClicked();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getAlertDialog$lambda-3, reason: not valid java name */
        public static void m1555getAlertDialog$lambda3(CreatePlaylistDialog this$0, DialogInterface dialogInterface, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.onNegativeButtonClicked();
        }

        private static Dialog getAlertDialog(final CreatePlaylistDialog createPlaylistDialog, Context context, AlertDialog.Builder builder) {
            builder.setTitle(getTitleResId(createPlaylistDialog));
            builder.setPositiveButton(getPositiveButtonTextResId(createPlaylistDialog), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playlist.CreatePlaylistDialog$DefaultImpls$$ExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    CreatePlaylistDialog.DefaultImpls.m1556getAlertDialog$lambda4(createPlaylistDialog, dialogInterface, i);
                }
            });
            builder.setNegativeButton(getNegativeButtonTextResId(createPlaylistDialog), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playlist.CreatePlaylistDialog$DefaultImpls$$ExternalSyntheticLambda3
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    CreatePlaylistDialog.DefaultImpls.m1557getAlertDialog$lambda5(createPlaylistDialog, dialogInterface, i);
                }
            });
            View viewInflate = View.inflate(context, R.layout.library_name_playlist, null);
            builder.setView(viewInflate);
            final androidx.appcompat.app.AlertDialog alertDialogCreate = builder.create();
            Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "builder.create()");
            View viewFindViewById = viewInflate.findViewById(R.id.PlaylistName);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "content.findViewById(R.id.PlaylistName)");
            initEditText(createPlaylistDialog, (EditText) viewFindViewById, new Function1<Boolean, Unit>() { // from class: com.amazon.mp3.playlist.CreatePlaylistDialog$getAlertDialog$6
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    Button button = alertDialogCreate.getButton(-1);
                    if (button == null) {
                        return;
                    }
                    button.setEnabled(z);
                }
            });
            return alertDialogCreate;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getAlertDialog$lambda-4, reason: not valid java name */
        public static void m1556getAlertDialog$lambda4(CreatePlaylistDialog this$0, DialogInterface dialogInterface, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.onPositiveButtonClicked();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getAlertDialog$lambda-5, reason: not valid java name */
        public static void m1557getAlertDialog$lambda5(CreatePlaylistDialog this$0, DialogInterface dialogInterface, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.onNegativeButtonClicked();
        }

        private static void initEditText(CreatePlaylistDialog createPlaylistDialog, EditText editText, final Function1<? super Boolean, Unit> function1) {
            editText.addTextChangedListener(new TextWatcher() { // from class: com.amazon.mp3.playlist.CreatePlaylistDialog$initEditText$1
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Intrinsics.checkNotNullParameter(charSequence, "charSequence");
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Intrinsics.checkNotNullParameter(charSequence, "charSequence");
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                    Intrinsics.checkNotNullParameter(editable, "editable");
                    Function1<Boolean, Unit> function12 = function1;
                    String string = editable.toString();
                    int length = string.length() - 1;
                    int i = 0;
                    boolean z = false;
                    while (i <= length) {
                        boolean z2 = Intrinsics.compare((int) string.charAt(!z ? i : length), 32) <= 0;
                        if (z) {
                            if (!z2) {
                                break;
                            } else {
                                length--;
                            }
                        } else if (z2) {
                            i++;
                        } else {
                            z = true;
                        }
                    }
                    function12.invoke(Boolean.valueOf(string.subSequence(i, length + 1).toString().length() > 0));
                }
            });
            editText.setHint(AmazonApplication.getCapabilities().isPlaylistCreationPublicToggleEnabled() ? R.string.dmusic_playlist_create_name_text : R.string.dmusic_playlist_create_edit_hint);
            createPlaylistDialog.onEditTextInitialized(editText);
        }

        private static int getTitleResId(CreatePlaylistDialog createPlaylistDialog) {
            return createPlaylistDialog.isCreatePlaylist() ? R.string.dmusic_playlist_create_playlist : R.string.dmusic_playlist_rename_playlist;
        }
    }
}
