package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.views.library.models.ArtistDetailHeaderModel;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.providers.ArtistClickListenerProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.ArtistDetailHeaderView;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: ArtistDetailHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B{\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010,\u001a\u00020+\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-\u0012\u0006\u00100\u001a\u00020/\u0012\u0006\u0010\u001d\u001a\u00020\u0012\u0012\u0006\u0010\u001f\u001a\u00020\u0012\u0012\b\b\u0002\u0010 \u001a\u00020\u0012\u0012\b\b\u0002\u0010!\u001a\u00020\u0012\u0012\b\b\u0002\u0010\"\u001a\u00020\u0012¢\u0006\u0004\b1\u00102J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u0002J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u0002J\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u0002J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u0002J \u0010\r\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u0016J \u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0012H\u0016R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R \u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010\u001eR\u0014\u0010 \u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010\u001eR\u0014\u0010!\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\u001eR\"\u0010\"\u001a\u00020\u00128\u0014@\u0014X\u0094\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u001e\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0017\u0010&\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b&\u0010\u001e\u001a\u0004\b&\u0010#R\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u00063"}, d2 = {"Lcom/amazon/music/views/library/binders/ArtistDetailHeaderBinder;", "Lcom/amazon/music/views/library/binders/PageHeaderBinder;", "Lcom/amazon/music/views/library/views/ArtistDetailHeaderView;", "Lcom/amazon/music/views/library/models/ArtistDetailHeaderModel;", "view", "model", "", "setOverFlowIconState", "setFollowIconState", "setFollowIcon", "setShareIconState", "", "payload", "handleStateChange", "Landroid/content/Context;", "context", "createView", "bind", "", "isAvailable", "setActionIconsState", "Lcom/amazon/music/views/library/providers/ArtistClickListenerProvider;", "artistClickListenerProvider", "Lcom/amazon/music/views/library/providers/ArtistClickListenerProvider;", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "libraryOnClickListenerMap", "Ljava/util/Map;", "isNightwingOnly", "Z", "isArtistFollowSupported", "isLibrary", "isAllOwnedContent", "isSonicRushEnabled", "()Z", "setSonicRushEnabled", "(Z)V", "isCatalogNightwingOnly", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "contextMenuListener", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "contentEnabilityProvider", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/ArtistClickListenerProvider;Ljava/util/Map;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;ZZZZZ)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class ArtistDetailHeaderBinder extends PageHeaderBinder<ArtistDetailHeaderView, ArtistDetailHeaderModel> {
    private final ArtistClickListenerProvider artistClickListenerProvider;
    private final boolean isAllOwnedContent;
    private final boolean isArtistFollowSupported;
    private final boolean isCatalogNightwingOnly;
    private final boolean isLibrary;
    private final boolean isNightwingOnly;
    private boolean isSonicRushEnabled;
    private final Map<PageHeaderActionIconModel.ActionType, ActionIconOnClickListener> libraryOnClickListenerMap;

    /* JADX INFO: compiled from: ArtistDetailHeaderBinder.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PageHeaderActionIconModel.ActionType.values().length];
            iArr[PageHeaderActionIconModel.ActionType.PLAY.ordinal()] = 1;
            iArr[PageHeaderActionIconModel.ActionType.STATION.ordinal()] = 2;
            iArr[PageHeaderActionIconModel.ActionType.SHUFFLE.ordinal()] = 3;
            iArr[PageHeaderActionIconModel.ActionType.OVERFLOW.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<ArtistDetailHeaderModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(ArtistDetailHeaderModel.class);
    }

    public /* synthetic */ ArtistDetailHeaderBinder(Context context, StyleSheet styleSheet, ArtistClickListenerProvider artistClickListenerProvider, Map map, MetadataContextMenuProvider metadataContextMenuProvider, ContentEnabilityProvider contentEnabilityProvider, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, (i & 4) != 0 ? null : artistClickListenerProvider, (i & 8) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i & 16) != 0 ? null : metadataContextMenuProvider, contentEnabilityProvider, z, z2, (i & 256) != 0 ? false : z3, (i & 512) != 0 ? false : z4, (i & 1024) != 0 ? false : z5);
    }

    @Override // com.amazon.music.views.library.binders.PageHeaderBinder
    /* JADX INFO: renamed from: isSonicRushEnabled, reason: from getter */
    protected boolean getIsSonicRushEnabled() {
        return this.isSonicRushEnabled;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ArtistDetailHeaderBinder(Context context, StyleSheet styleSheet, ArtistClickListenerProvider artistClickListenerProvider, Map<PageHeaderActionIconModel.ActionType, ? extends ActionIconOnClickListener> libraryOnClickListenerMap, MetadataContextMenuProvider metadataContextMenuProvider, ContentEnabilityProvider contentEnabilityProvider, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        super(context, styleSheet, null, metadataContextMenuProvider, contentEnabilityProvider, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(libraryOnClickListenerMap, "libraryOnClickListenerMap");
        Intrinsics.checkNotNullParameter(contentEnabilityProvider, "contentEnabilityProvider");
        this.artistClickListenerProvider = artistClickListenerProvider;
        this.libraryOnClickListenerMap = libraryOnClickListenerMap;
        this.isNightwingOnly = z;
        this.isArtistFollowSupported = z2;
        this.isLibrary = z3;
        this.isAllOwnedContent = z4;
        this.isSonicRushEnabled = z5;
        this.isCatalogNightwingOnly = z && !z3;
    }

    @Override // com.amazon.music.views.library.binders.PageHeaderBinder
    public void handleStateChange(ArtistDetailHeaderView view, ArtistDetailHeaderModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        super.handleStateChange(view, model, payload);
        if (Intrinsics.areEqual(payload, (Object) 2) && this.isArtistFollowSupported) {
            setFollowIcon(view, model);
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public ArtistDetailHeaderView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ArtistDetailHeaderView(context, getStyleSheet(), getIsSonicRushEnabled());
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(ArtistDetailHeaderView view, ArtistDetailHeaderModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        super.bind(view, model);
        view.showImageSpace(model.getHeroImageUrl() != null);
        view.setIsFollowed(model.getIsFollowing(), this.isCatalogNightwingOnly);
        setFollowIconState(view, model);
        if (this.isCatalogNightwingOnly) {
            setShareIconState(view, model);
        } else {
            if (getIsSonicRushEnabled()) {
                return;
            }
            setOverFlowIconState(view, model);
        }
    }

    @Override // com.amazon.music.views.library.binders.PageHeaderBinder
    public void setActionIconsState(ArtistDetailHeaderView view, final ArtistDetailHeaderModel model, boolean isAvailable) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        if (isAvailable) {
            for (PageHeaderActionIconModel pageHeaderActionIconModel : model.getActionIcons()) {
                final ActionIconOnClickListener actionIconOnClickListener = this.libraryOnClickListenerMap.get(pageHeaderActionIconModel.getActionType());
                boolean z = !this.isNightwingOnly || this.isAllOwnedContent;
                int i = WhenMappings.$EnumSwitchMapping$0[pageHeaderActionIconModel.getActionType().ordinal()];
                if (i != 1) {
                    if (i == 2) {
                        view.showStationIcon(true, this.isCatalogNightwingOnly);
                        view.setStationIconClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ArtistDetailHeaderBinder$$ExternalSyntheticLambda1
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view2) {
                                ArtistDetailHeaderBinder.m2259setActionIconsState$lambda1(this.f$0, model, view2);
                            }
                        }, this.isCatalogNightwingOnly);
                        view.setStationEligibility(getContentEnabilityProvider().shouldEnableContent(model.getMetadata()));
                    } else if (i != 3) {
                        if (i == 4) {
                            setOverFlowIconState(view, model);
                        }
                    } else if (!this.isCatalogNightwingOnly) {
                        view.showShuffleIcon(true, z);
                        view.setShuffleIconClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ArtistDetailHeaderBinder$$ExternalSyntheticLambda2
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view2) {
                                ArtistDetailHeaderBinder.m2260setActionIconsState$lambda2(this.f$0, actionIconOnClickListener, model, view2);
                            }
                        });
                    }
                } else if (!this.isCatalogNightwingOnly) {
                    view.showPlayIcon(true, z);
                    view.setPlayIconClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ArtistDetailHeaderBinder$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            ArtistDetailHeaderBinder.m2258setActionIconsState$lambda0(this.f$0, actionIconOnClickListener, model, view2);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setActionIconsState$lambda-0, reason: not valid java name */
    public static final void m2258setActionIconsState$lambda0(ArtistDetailHeaderBinder this$0, ActionIconOnClickListener actionIconOnClickListener, ArtistDetailHeaderModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        if (this$0.isLibrary) {
            if (actionIconOnClickListener == null) {
                return;
            }
            actionIconOnClickListener.onClick(this$0.getContext(), null);
        } else {
            ArtistClickListenerProvider artistClickListenerProvider = this$0.artistClickListenerProvider;
            if (artistClickListenerProvider == null) {
                return;
            }
            artistClickListenerProvider.onPlayActionIconClick(this$0.getContext(), model.getMetadata(), model.getMetadataList());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setActionIconsState$lambda-1, reason: not valid java name */
    public static final void m2259setActionIconsState$lambda1(ArtistDetailHeaderBinder this$0, ArtistDetailHeaderModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        ArtistClickListenerProvider artistClickListenerProvider = this$0.artistClickListenerProvider;
        if (artistClickListenerProvider == null) {
            return;
        }
        artistClickListenerProvider.onStationActionIconClick(this$0.getContext(), model.getMetadata());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setActionIconsState$lambda-2, reason: not valid java name */
    public static final void m2260setActionIconsState$lambda2(ArtistDetailHeaderBinder this$0, ActionIconOnClickListener actionIconOnClickListener, ArtistDetailHeaderModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        if (this$0.isLibrary) {
            if (actionIconOnClickListener == null) {
                return;
            }
            actionIconOnClickListener.onClick(this$0.getContext(), null);
        } else {
            ArtistClickListenerProvider artistClickListenerProvider = this$0.artistClickListenerProvider;
            if (artistClickListenerProvider == null) {
                return;
            }
            artistClickListenerProvider.onShuffleActionIconClick(this$0.getContext(), model.getMetadata(), model.getMetadataList());
        }
    }

    private final void setOverFlowIconState(ArtistDetailHeaderView view, final ArtistDetailHeaderModel model) {
        view.showOverflowMenu(true);
        view.setOverflowOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ArtistDetailHeaderBinder$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ArtistDetailHeaderBinder.m2262setOverFlowIconState$lambda3(this.f$0, model, view2);
            }
        });
        MetadataContextMenuProvider contextMenuListener = getContextMenuListener();
        if (contextMenuListener == null) {
            return;
        }
        contextMenuListener.updateStateProvider(model.getMetadata());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setOverFlowIconState$lambda-3, reason: not valid java name */
    public static final void m2262setOverFlowIconState$lambda3(ArtistDetailHeaderBinder this$0, ArtistDetailHeaderModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        MetadataContextMenuProvider contextMenuListener = this$0.getContextMenuListener();
        if (contextMenuListener == null) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(view, "view");
        contextMenuListener.openContextMenu(view, model.getMetadata(), InteractionType.TAP);
    }

    private final void setFollowIconState(ArtistDetailHeaderView view, final ArtistDetailHeaderModel model) {
        if (this.isArtistFollowSupported) {
            view.showFollowIcon(true, !this.isNightwingOnly);
            setFollowIcon(view, model);
            view.setFollowIconClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ArtistDetailHeaderBinder$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ArtistDetailHeaderBinder.m2261setFollowIconState$lambda4(this.f$0, model, view2);
                }
            }, !this.isCatalogNightwingOnly);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setFollowIconState$lambda-4, reason: not valid java name */
    public static final void m2261setFollowIconState$lambda4(ArtistDetailHeaderBinder this$0, ArtistDetailHeaderModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        ArtistClickListenerProvider artistClickListenerProvider = this$0.artistClickListenerProvider;
        if (artistClickListenerProvider == null) {
            return;
        }
        artistClickListenerProvider.onFollowClick(model);
    }

    private final void setFollowIcon(ArtistDetailHeaderView view, ArtistDetailHeaderModel model) {
        view.setIsFollowed(model.getIsFollowing(), !this.isCatalogNightwingOnly);
    }

    private final void setShareIconState(ArtistDetailHeaderView view, final ArtistDetailHeaderModel model) {
        view.showShareIcon(true);
        view.setShareIconClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ArtistDetailHeaderBinder$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ArtistDetailHeaderBinder.m2263setShareIconState$lambda5(this.f$0, model, view2);
            }
        });
        view.setOnLongClickListener(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setShareIconState$lambda-5, reason: not valid java name */
    public static final void m2263setShareIconState$lambda5(ArtistDetailHeaderBinder this$0, ArtistDetailHeaderModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        ArtistClickListenerProvider artistClickListenerProvider = this$0.artistClickListenerProvider;
        if (artistClickListenerProvider == null) {
            return;
        }
        artistClickListenerProvider.onShareClick(model.getMetadata());
    }
}
