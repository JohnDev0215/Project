package com.amazon.mp3.playback.view.multiminiplayer;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.amazon.digitalmusicxp.types.ActiveDevicePlayback;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.view.multiminiplayer.CloudQueueMiniPlayerController;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CloudQueuePullConfirmDialog.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b;\u0010<J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016J&\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u001a\u001a\n \u0019*\u0004\u0018\u00010\u00180\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\"\u0010\u001c\u001a\u00020\u00118\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R#\u0010'\u001a\n \u0019*\u0004\u0018\u00010\"0\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R#\u0010*\u001a\n \u0019*\u0004\u0018\u00010\"0\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010$\u001a\u0004\b)\u0010&R#\u0010-\u001a\n \u0019*\u0004\u0018\u00010\"0\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b+\u0010$\u001a\u0004\b,\u0010&R#\u00102\u001a\n \u0019*\u0004\u0018\u00010.0.8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b/\u0010$\u001a\u0004\b0\u00101R#\u00107\u001a\n \u0019*\u0004\u0018\u000103038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b4\u0010$\u001a\u0004\b5\u00106R#\u0010:\u001a\n \u0019*\u0004\u0018\u000103038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b8\u0010$\u001a\u0004\b9\u00106¨\u0006="}, d2 = {"Lcom/amazon/mp3/playback/view/multiminiplayer/CloudQueuePullConfirmDialog;", "Landroidx/fragment/app/DialogFragment;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "init", "startPullQueue", "", "isChecked", "updatePreference", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "Lcom/amazon/digitalmusicxp/types/ActiveDevicePlayback;", "activeDevicePlayback", "Lcom/amazon/digitalmusicxp/types/ActiveDevicePlayback;", "getActiveDevicePlayback", "()Lcom/amazon/digitalmusicxp/types/ActiveDevicePlayback;", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "Landroid/widget/TextView;", "titleTextView$delegate", "Lkotlin/Lazy;", "getTitleTextView", "()Landroid/widget/TextView;", "titleTextView", "descriptionTextView$delegate", "getDescriptionTextView", "descriptionTextView", "moveStreamTextView$delegate", "getMoveStreamTextView", "moveStreamTextView", "Landroid/widget/CheckBox;", "checkBox$delegate", "getCheckBox", "()Landroid/widget/CheckBox;", "checkBox", "Lcom/amazon/ui/foundations/views/BaseButton;", "playButton$delegate", "getPlayButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "playButton", "cancelButton$delegate", "getCancelButton", "cancelButton", "<init>", "(Lcom/amazon/digitalmusicxp/types/ActiveDevicePlayback;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CloudQueuePullConfirmDialog extends DialogFragment {
    private final String TAG;
    private final ActiveDevicePlayback activeDevicePlayback;

    /* JADX INFO: renamed from: cancelButton$delegate, reason: from kotlin metadata */
    private final Lazy cancelButton;

    /* JADX INFO: renamed from: checkBox$delegate, reason: from kotlin metadata */
    private final Lazy checkBox;

    /* JADX INFO: renamed from: descriptionTextView$delegate, reason: from kotlin metadata */
    private final Lazy descriptionTextView;

    /* JADX INFO: renamed from: moveStreamTextView$delegate, reason: from kotlin metadata */
    private final Lazy moveStreamTextView;

    /* JADX INFO: renamed from: playButton$delegate, reason: from kotlin metadata */
    private final Lazy playButton;
    public View rootView;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    public CloudQueuePullConfirmDialog(ActiveDevicePlayback activeDevicePlayback) {
        Intrinsics.checkNotNullParameter(activeDevicePlayback, "activeDevicePlayback");
        this.activeDevicePlayback = activeDevicePlayback;
        this.TAG = CloudQueuePullConfirmDialog.class.getSimpleName();
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueuePullConfirmDialog$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.getRootView().findViewById(R.id.title);
            }
        });
        this.descriptionTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueuePullConfirmDialog$descriptionTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.getRootView().findViewById(R.id.description);
            }
        });
        this.moveStreamTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueuePullConfirmDialog$moveStreamTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.getRootView().findViewById(R.id.move_stream_checkbox_text);
            }
        });
        this.checkBox = LazyKt__LazyJVMKt.lazy(new Function0<CheckBox>() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueuePullConfirmDialog$checkBox$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CheckBox invoke() {
                return (CheckBox) this.this$0.getRootView().findViewById(R.id.move_stream_checkbox);
            }
        });
        this.playButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueuePullConfirmDialog$playButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.getRootView().findViewById(R.id.play_button);
            }
        });
        this.cancelButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueuePullConfirmDialog$cancelButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.getRootView().findViewById(R.id.cancel_button);
            }
        });
    }

    public final View getRootView() {
        View view = this.rootView;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("rootView");
        return null;
    }

    public final void setRootView(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.rootView = view;
    }

    private final TextView getTitleTextView() {
        return (TextView) this.titleTextView.getValue();
    }

    private final TextView getDescriptionTextView() {
        return (TextView) this.descriptionTextView.getValue();
    }

    private final TextView getMoveStreamTextView() {
        return (TextView) this.moveStreamTextView.getValue();
    }

    private final CheckBox getCheckBox() {
        return (CheckBox) this.checkBox.getValue();
    }

    private final BaseButton getPlayButton() {
        return (BaseButton) this.playButton.getValue();
    }

    private final BaseButton getCancelButton() {
        return (BaseButton) this.cancelButton.getValue();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, android.R.style.Theme.Translucent.NoTitleBar.Fullscreen);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.cloud_queue_pull_dialog, container);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…e_pull_dialog, container)");
        setRootView(viewInflate);
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueuePullConfirmDialog$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CloudQueuePullConfirmDialog.m1552onCreateView$lambda0(this.f$0, (StyleSheet) obj);
            }
        });
        return getRootView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-0, reason: not valid java name */
    public static final void m1552onCreateView$lambda0(CloudQueuePullConfirmDialog this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        this$0.init(styleSheet);
    }

    private final void init(StyleSheet styleSheet) {
        Window window;
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.SOLID);
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.GLASS);
        if (buttonBuilder != null) {
            String string = getString(R.string.play);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.play)");
            BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(string);
            if (styleBuilderWithText != null) {
                BaseButton playButton = getPlayButton();
                Intrinsics.checkNotNullExpressionValue(playButton, "playButton");
                styleBuilderWithText.applyStyle(playButton);
            }
        }
        if (iconBuilder != null) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_action_cancel);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.ic_action_cancel)");
            BaseButton.StyleBuilder styleBuilderWithIcon = iconBuilder.withIcon(drawable);
            if (styleBuilderWithIcon != null) {
                BaseButton cancelButton = getCancelButton();
                Intrinsics.checkNotNullExpressionValue(cancelButton, "cancelButton");
                styleBuilderWithIcon.applyStyle(cancelButton);
            }
        }
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4);
        if (fontStyle != null) {
            FontUtil fontUtil = FontUtil.INSTANCE;
            TextView titleTextView = getTitleTextView();
            Intrinsics.checkNotNullExpressionValue(titleTextView, "titleTextView");
            fontUtil.applyFontStyle(titleTextView, fontStyle);
        }
        FontStyle fontStyle2 = styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle2 != null) {
            FontUtil fontUtil2 = FontUtil.INSTANCE;
            TextView descriptionTextView = getDescriptionTextView();
            Intrinsics.checkNotNullExpressionValue(descriptionTextView, "descriptionTextView");
            fontUtil2.applyFontStyle(descriptionTextView, fontStyle2);
            TextView moveStreamTextView = getMoveStreamTextView();
            Intrinsics.checkNotNullExpressionValue(moveStreamTextView, "moveStreamTextView");
            fontUtil2.applyFontStyle(moveStreamTextView, fontStyle2);
        }
        getDescriptionTextView().setText(getString(R.string.cloudqueue_pull_dialog_description, this.activeDevicePlayback.getDeviceName()));
        getDescriptionTextView().setTextAlignment(4);
        Drawable drawable2 = getResources().getDrawable(R.drawable.bauhaus_gradient_background);
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setBackgroundDrawable(drawable2);
        }
        getPlayButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueuePullConfirmDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CloudQueuePullConfirmDialog.m1550init$lambda3(this.f$0, view);
            }
        });
        getCancelButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueuePullConfirmDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CloudQueuePullConfirmDialog.m1551init$lambda4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: init$lambda-3, reason: not valid java name */
    public static final void m1550init$lambda3(CloudQueuePullConfirmDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updatePreference(this$0.getCheckBox().isChecked());
        this$0.startPullQueue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: init$lambda-4, reason: not valid java name */
    public static final void m1551init$lambda4(CloudQueuePullConfirmDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    private final void startPullQueue() {
        dismiss();
        CloudQueueMiniPlayerController.Companion companion = CloudQueueMiniPlayerController.INSTANCE;
        ActiveDevicePlayback activeDevicePlayback = this.activeDevicePlayback;
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        companion.callInitiateQueue(activeDevicePlayback, fragmentActivityRequireActivity);
    }

    private final void updatePreference(boolean isChecked) {
        FragmentActivity activity = getActivity();
        SharedPreferences preferences = activity == null ? null : activity.getPreferences(0);
        if (preferences == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = preferences.edit();
        editorEdit.putBoolean(CloudQueueMiniPlayerController.INSTANCE.getALWAYS_MOVE_STREAM(), isChecked);
        editorEdit.commit();
    }
}
