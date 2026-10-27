package com.amazon.music.views.library.binders;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ButtonModel;
import com.amazon.music.views.library.models.enums.HeaderButtonActionType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.providers.FollowProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.providers.PlaybackProvider;
import com.amazon.music.views.library.providers.ShareProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: ButtonBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001BQ\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0003H\u0016J\u0010\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\u0018\u0010 \u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0003H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/amazon/music/views/library/binders/ButtonBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/ui/foundations/views/BaseButton;", "Lcom/amazon/music/views/library/models/ButtonModel;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "uriClickListenerProvider", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "contextMenuListener", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "playbackProvider", "Lcom/amazon/music/views/library/providers/PlaybackProvider;", "shareProvider", "Lcom/amazon/music/views/library/providers/ShareProvider;", "followProvider", "Lcom/amazon/music/views/library/providers/FollowProvider;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/providers/PlaybackProvider;Lcom/amazon/music/views/library/providers/ShareProvider;Lcom/amazon/music/views/library/providers/FollowProvider;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createView", "handleStateChange", "payload", "", "setupAction", "setupViewIds", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ButtonBinder implements BaseStateChangeBinder<BaseButton, ButtonModel> {
    private final Context context;
    private final MetadataContextMenuProvider contextMenuListener;
    private final FollowProvider followProvider;
    private final PlaybackProvider playbackProvider;
    private final ShareProvider shareProvider;
    private final StyleSheet styleSheet;
    private final BrushUriClickListenerProvider uriClickListenerProvider;

    /* JADX INFO: compiled from: ButtonBinder.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HeaderButtonActionType.values().length];
            iArr[HeaderButtonActionType.FOLLOW.ordinal()] = 1;
            iArr[HeaderButtonActionType.NAVIGATE.ordinal()] = 2;
            iArr[HeaderButtonActionType.OVERFLOW.ordinal()] = 3;
            iArr[HeaderButtonActionType.PLAY.ordinal()] = 4;
            iArr[HeaderButtonActionType.SHARE.ordinal()] = 5;
            iArr[HeaderButtonActionType.SHUFFLE.ordinal()] = 6;
            iArr[HeaderButtonActionType.STATION.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<ButtonModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(ButtonModel.class);
    }

    public ButtonBinder(Context context, StyleSheet styleSheet, BrushUriClickListenerProvider brushUriClickListenerProvider, MetadataContextMenuProvider metadataContextMenuProvider, PlaybackProvider playbackProvider, ShareProvider shareProvider, FollowProvider followProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.context = context;
        this.styleSheet = styleSheet;
        this.uriClickListenerProvider = brushUriClickListenerProvider;
        this.contextMenuListener = metadataContextMenuProvider;
        this.playbackProvider = playbackProvider;
        this.shareProvider = shareProvider;
        this.followProvider = followProvider;
    }

    public /* synthetic */ ButtonBinder(Context context, StyleSheet styleSheet, BrushUriClickListenerProvider brushUriClickListenerProvider, MetadataContextMenuProvider metadataContextMenuProvider, PlaybackProvider playbackProvider, ShareProvider shareProvider, FollowProvider followProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, (i & 4) != 0 ? null : brushUriClickListenerProvider, (i & 8) != 0 ? null : metadataContextMenuProvider, (i & 16) != 0 ? null : playbackProvider, (i & 32) != 0 ? null : shareProvider, (i & 64) != 0 ? null : followProvider);
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(BaseButton view, ButtonModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 6)) {
            view.setActive(Intrinsics.areEqual(model.getIsActive(), Boolean.TRUE));
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public BaseButton createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new BaseButton(context, null, 0, 6, null);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(BaseButton view, ButtonModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        BaseButton.StyleBuilder iconBuilder = model.getText() == null ? this.styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.GLASS) : this.styleSheet.getButtonBuilder(ButtonSizeKey.LARGE_WITH_ICON, ButtonStyleKey.GLASS);
        if (iconBuilder != null) {
            Integer iconId = model.getIconId();
            if (iconId != null) {
                Drawable drawable = AppCompatResources.getDrawable(this.context, iconId.intValue());
                if (drawable != null) {
                    iconBuilder.withIcon(drawable);
                }
            }
            Integer activeIconId = model.getActiveIconId();
            if (activeIconId != null) {
                Drawable drawable2 = AppCompatResources.getDrawable(this.context, activeIconId.intValue());
                if (drawable2 != null) {
                    iconBuilder.withActiveIcon(drawable2);
                }
            }
            String text = model.getText();
            if (text != null) {
                iconBuilder.withText(text);
            }
            String activeText = model.getActiveText();
            if (activeText != null) {
                iconBuilder.withActiveText(activeText);
            }
            iconBuilder.applyStyle(view);
        }
        view.setActive(Intrinsics.areEqual(model.getIsActive(), Boolean.TRUE));
        setupViewIds(view, model);
        setupAction(view, model);
    }

    private final void setupViewIds(BaseButton view, ButtonModel model) {
        switch (WhenMappings.$EnumSwitchMapping$0[model.getActionType().ordinal()]) {
            case 1:
                view.setId(model.getText() != null ? R$id.large_follow_button : R$id.follow_icon);
                break;
            case 2:
                view.setId(R$id.navigation_icon);
                break;
            case 3:
                view.setId(R$id.overflow_icon);
                break;
            case 4:
                view.setId(R$id.play_icon);
                break;
            case 5:
                view.setId(R$id.share_icon);
                break;
            case 6:
                view.setId(R$id.shuffle_icon);
                break;
            case 7:
                view.setId(model.getText() != null ? R$id.large_station_button : R$id.station_icon);
                break;
        }
    }

    private final void setupAction(final BaseButton view, final ButtonModel model) {
        PlaybackProvider playbackProvider;
        switch (WhenMappings.$EnumSwitchMapping$0[model.getActionType().ordinal()]) {
            case 1:
                view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ButtonBinder$$ExternalSyntheticLambda6
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        ButtonBinder.m2273setupAction$lambda19(model, this, view2);
                    }
                });
                break;
            case 2:
                view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ButtonBinder$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        ButtonBinder.m2275setupAction$lambda9(this.f$0, model, view2);
                    }
                });
                break;
            case 3:
                if (model.getMetadata() != null) {
                    view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ButtonBinder$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            ButtonBinder.m2274setupAction$lambda8$lambda7(this.f$0, view, model, view2);
                        }
                    });
                    break;
                }
                break;
            case 4:
                view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ButtonBinder$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        ButtonBinder.m2269setupAction$lambda11(model, this, view2);
                    }
                });
                break;
            case 5:
                view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ButtonBinder$$ExternalSyntheticLambda5
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        ButtonBinder.m2272setupAction$lambda17(model, this, view2);
                    }
                });
                break;
            case 6:
                view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ButtonBinder$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        ButtonBinder.m2270setupAction$lambda13(model, this, view2);
                    }
                });
                break;
            case 7:
                view.setEnabled((model.getMetadata() == null || (playbackProvider = this.playbackProvider) == null) ? false : playbackProvider.shouldEnableStationPlayback(model.getMetadata()));
                view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ButtonBinder$$ExternalSyntheticLambda4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        ButtonBinder.m2271setupAction$lambda15(model, this, view2);
                    }
                });
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupAction$lambda-8$lambda-7, reason: not valid java name */
    public static final void m2274setupAction$lambda8$lambda7(ButtonBinder this$0, BaseButton view, ButtonModel model, View view2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(model, "$model");
        MetadataContextMenuProvider metadataContextMenuProvider = this$0.contextMenuListener;
        if (metadataContextMenuProvider == null) {
            return;
        }
        metadataContextMenuProvider.openContextMenu(view, model.getMetadata(), InteractionType.TAP);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupAction$lambda-9, reason: not valid java name */
    public static final void m2275setupAction$lambda9(ButtonBinder this$0, ButtonModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        BrushUriClickListenerProvider brushUriClickListenerProvider = this$0.uriClickListenerProvider;
        if (brushUriClickListenerProvider == null) {
            return;
        }
        brushUriClickListenerProvider.onClick(model.getTarget(), model.getViewId(), model.getTargetType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupAction$lambda-11, reason: not valid java name */
    public static final void m2269setupAction$lambda11(ButtonModel model, ButtonBinder this$0, View view) {
        PlaybackProvider playbackProvider;
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ContentMetadata metadata = model.getMetadata();
        if (metadata == null || (playbackProvider = this$0.playbackProvider) == null) {
            return;
        }
        PlaybackProvider.DefaultImpls.startPlayback$default(playbackProvider, metadata, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupAction$lambda-13, reason: not valid java name */
    public static final void m2270setupAction$lambda13(ButtonModel model, ButtonBinder this$0, View view) {
        PlaybackProvider playbackProvider;
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ContentMetadata metadata = model.getMetadata();
        if (metadata == null || (playbackProvider = this$0.playbackProvider) == null) {
            return;
        }
        playbackProvider.startPlayback(metadata, Boolean.TRUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupAction$lambda-15, reason: not valid java name */
    public static final void m2271setupAction$lambda15(ButtonModel model, ButtonBinder this$0, View view) {
        PlaybackProvider playbackProvider;
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ContentMetadata metadata = model.getMetadata();
        if (metadata == null || (playbackProvider = this$0.playbackProvider) == null) {
            return;
        }
        playbackProvider.startStationPlayback(metadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupAction$lambda-17, reason: not valid java name */
    public static final void m2272setupAction$lambda17(ButtonModel model, ButtonBinder this$0, View view) {
        ShareProvider shareProvider;
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ContentMetadata metadata = model.getMetadata();
        if (metadata == null || (shareProvider = this$0.shareProvider) == null) {
            return;
        }
        shareProvider.share(metadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupAction$lambda-19, reason: not valid java name */
    public static final void m2273setupAction$lambda19(ButtonModel model, ButtonBinder this$0, View view) {
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Boolean isActive = model.getIsActive();
        if (isActive == null) {
            return;
        }
        boolean zBooleanValue = isActive.booleanValue();
        FollowProvider followProvider = this$0.followProvider;
        if (zBooleanValue) {
            if (followProvider == null) {
                return;
            }
            followProvider.unfollow(model);
        } else {
            if (followProvider == null) {
                return;
            }
            followProvider.follow(model);
        }
    }
}
