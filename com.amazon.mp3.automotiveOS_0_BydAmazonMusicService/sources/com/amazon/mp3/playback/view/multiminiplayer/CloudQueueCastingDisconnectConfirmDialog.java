package com.amazon.mp3.playback.view.multiminiplayer;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.amazon.digitalmusicxp.types.ActiveDevicePlayback;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.view.multiminiplayer.CloudQueueMiniPlayerController;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.views.library.models.CastingListItemHorizontalTileModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.views.CastingListItemHorizontalTileView;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CloudQueueCastingDisconnectConfirmDialog.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b6\u00107J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\u0012\u0010\t\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016J\u0016\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012R\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001d\u001a\n \u001c*\u0004\u0018\u00010\u001b0\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\"\u0010\u001f\u001a\u00020\u000e8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R#\u0010*\u001a\n \u001c*\u0004\u0018\u00010%0%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R#\u0010-\u001a\n \u001c*\u0004\u0018\u00010%0%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b+\u0010'\u001a\u0004\b,\u0010)R#\u00102\u001a\n \u001c*\u0004\u0018\u00010.0.8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b/\u0010'\u001a\u0004\b0\u00101R#\u00105\u001a\n \u001c*\u0004\u0018\u00010.0.8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b3\u0010'\u001a\u0004\b4\u00101¨\u00068"}, d2 = {"Lcom/amazon/mp3/playback/view/multiminiplayer/CloudQueueCastingDisconnectConfirmDialog;", "Landroidx/fragment/app/DialogFragment;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "init", "startPullQueue", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "Lcom/amazon/music/views/library/views/CastingListItemHorizontalTileView;", "view", "Lcom/amazon/music/views/library/models/CastingListItemHorizontalTileModel;", "model", "disconnect", "Lcom/amazon/music/views/library/views/CastingListItemHorizontalTileView;", "getView", "()Lcom/amazon/music/views/library/views/CastingListItemHorizontalTileView;", "Lcom/amazon/music/views/library/models/CastingListItemHorizontalTileModel;", "getModel", "()Lcom/amazon/music/views/library/models/CastingListItemHorizontalTileModel;", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "Landroid/widget/TextView;", "titleTextView$delegate", "Lkotlin/Lazy;", "getTitleTextView", "()Landroid/widget/TextView;", "titleTextView", "descriptionTextView$delegate", "getDescriptionTextView", "descriptionTextView", "Lcom/amazon/ui/foundations/views/BaseButton;", "playButton$delegate", "getPlayButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "playButton", "cancelButton$delegate", "getCancelButton", "cancelButton", "<init>", "(Lcom/amazon/music/views/library/views/CastingListItemHorizontalTileView;Lcom/amazon/music/views/library/models/CastingListItemHorizontalTileModel;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CloudQueueCastingDisconnectConfirmDialog extends DialogFragment {
    private final String TAG;

    /* JADX INFO: renamed from: cancelButton$delegate, reason: from kotlin metadata */
    private final Lazy cancelButton;

    /* JADX INFO: renamed from: descriptionTextView$delegate, reason: from kotlin metadata */
    private final Lazy descriptionTextView;
    private final CastingListItemHorizontalTileModel model;

    /* JADX INFO: renamed from: playButton$delegate, reason: from kotlin metadata */
    private final Lazy playButton;
    public View rootView;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;
    private final CastingListItemHorizontalTileView view;

    public CloudQueueCastingDisconnectConfirmDialog(CastingListItemHorizontalTileView view, CastingListItemHorizontalTileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        this.view = view;
        this.model = model;
        this.TAG = CloudQueuePullConfirmDialog.class.getSimpleName();
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueueCastingDisconnectConfirmDialog$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.getRootView().findViewById(R.id.title);
            }
        });
        this.descriptionTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueueCastingDisconnectConfirmDialog$descriptionTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.getRootView().findViewById(R.id.description);
            }
        });
        this.playButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueueCastingDisconnectConfirmDialog$playButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.getRootView().findViewById(R.id.play_button);
            }
        });
        this.cancelButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueueCastingDisconnectConfirmDialog$cancelButton$2
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
        View viewInflate = inflater.inflate(R.layout.cloud_queue_disconnect_dialog, container);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…onnect_dialog, container)");
        setRootView(viewInflate);
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueueCastingDisconnectConfirmDialog$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CloudQueueCastingDisconnectConfirmDialog.m1542onCreateView$lambda0(this.f$0, (StyleSheet) obj);
            }
        });
        return getRootView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-0, reason: not valid java name */
    public static final void m1542onCreateView$lambda0(CloudQueueCastingDisconnectConfirmDialog this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        this$0.init(styleSheet);
    }

    private final void init(StyleSheet styleSheet) {
        Window window;
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.SOLID);
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.GLASS);
        String name = BluetoothAdapter.getDefaultAdapter().getName();
        if (buttonBuilder != null) {
            String string = getString(R.string.cloudqueue_disconnect_dialog_confirm_button, name);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.cloud…nfirm_button, deviceName)");
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
        }
        ActiveDevicePlayback activeDevicePlayback = ((ActiveDeviceMetadata) this.model.getMetadata()).getActiveDevicePlayback();
        TextView descriptionTextView2 = getDescriptionTextView();
        Object[] objArr = new Object[2];
        objArr[0] = name;
        objArr[1] = activeDevicePlayback == null ? null : activeDevicePlayback.getDeviceName();
        descriptionTextView2.setText(getString(R.string.cloudqueue_disconnect_dialog_description, objArr));
        getDescriptionTextView().setTextAlignment(4);
        getTitleTextView().setText(getString(R.string.cloudqueue_disconnect_dialog_title, name));
        Drawable drawable2 = getResources().getDrawable(R.drawable.bauhaus_gradient_background);
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setBackgroundDrawable(drawable2);
        }
        getPlayButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueueCastingDisconnectConfirmDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CloudQueueCastingDisconnectConfirmDialog.m1540init$lambda3(this.f$0, view);
            }
        });
        getCancelButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.multiminiplayer.CloudQueueCastingDisconnectConfirmDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CloudQueueCastingDisconnectConfirmDialog.m1541init$lambda4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: init$lambda-3, reason: not valid java name */
    public static final void m1540init$lambda3(CloudQueueCastingDisconnectConfirmDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startPullQueue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: init$lambda-4, reason: not valid java name */
    public static final void m1541init$lambda4(CloudQueueCastingDisconnectConfirmDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
        this$0.disconnect(this$0.view, this$0.model);
    }

    private final void startPullQueue() {
        dismiss();
        ActiveDevicePlayback activeDevicePlayback = ((ActiveDeviceMetadata) this.model.getMetadata()).getActiveDevicePlayback();
        if (activeDevicePlayback != null) {
            CloudQueueMiniPlayerController.Companion companion = CloudQueueMiniPlayerController.INSTANCE;
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
            companion.callInitiateQueue(activeDevicePlayback, fragmentActivityRequireActivity);
        }
    }

    public final void disconnect(CastingListItemHorizontalTileView view, CastingListItemHorizontalTileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        CastingSessionManager.getInstance().disconnect(!Intrinsics.areEqual(CastingCategory.CHROMECAST.getName(), model.getCategory()) ? TerminationReason.USER_DISCONNECT : null);
        view.showSpinner(true);
    }
}
