package com.amazon.music.curate.skyfire.views.galleryTemplate;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import Touch.WidgetsInterface.v1_0.AllAccessBadgeItemElement;
import Touch.WidgetsInterface.v1_0.BadgeItemElement;
import Touch.WidgetsInterface.v1_0.CircleVerticalItemElement;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.account.SonicRushFeature;
import com.amazon.music.curate.Handlers;
import com.amazon.music.curate.R;
import com.amazon.music.curate.featuregating.CurateFeatureGating;
import com.amazon.music.curate.provider.EntityType;
import com.amazon.music.curate.provider.MusicItem;
import com.amazon.music.curate.provider.PlaybackProvider;
import com.amazon.music.curate.skyfire.AudioContentEncodingUtil;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.curate.skyfire.local.InvokeLocalSkillMethod;
import com.amazon.music.curate.view.RoundedCornersTransformation;
import com.amazon.music.picassoimageloader.CircleTransformation;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.IndicatorSizeKey;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.music.views.library.views.PlayNotificationIcon;
import com.amazon.music.views.library.views.VerticalTileView;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.views.BaseButton;
import com.amazon.ui.foundations.views.BaseImage;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
final class VerticalItemViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "VerticalItemViewHolder";
    private static final Logger logger = LoggerFactory.getLogger(VerticalItemViewHolder.class.getSimpleName());
    private final ArtworkFrameView artworkFrameView;
    private ImageView artworkImageView;
    private final Context context;
    private final CurateBootstrapProviders curateBootstrapProviders;
    private final int imageDimension;
    private final LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private BaseButton playIcon;
    private final PlaybackProvider playbackProvider;
    private PlayNotificationIcon playstateIcon;
    private final Resources resources;
    private StyleSheet styleSheet;
    private VerticalItemEntity verticalItemEntity;
    private final VerticalTileView verticalTileView;

    public VerticalItemViewHolder(final VerticalTileView verticalTileView, final String ownerId, final MethodsDispatcher methodsDispatcher, final int imageDimension, final LifecycleOwner lifecycleOwner, final Context context, final Resources resources, final CurateBootstrapProviders curateBootstrapProviders) {
        super(verticalTileView);
        this.verticalTileView = verticalTileView;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.imageDimension = imageDimension;
        this.lifecycleOwner = lifecycleOwner;
        this.context = context;
        this.resources = resources;
        this.curateBootstrapProviders = curateBootstrapProviders;
        this.playbackProvider = curateBootstrapProviders.getPlaybackProvider();
        ArtworkFrameView artworkFrameView = verticalTileView.getArtworkFrameView();
        this.artworkFrameView = artworkFrameView;
        this.playIcon = (BaseButton) artworkFrameView.findViewById(R.id.play_icon);
        this.verticalItemEntity = new VerticalItemEntity(context.getApplicationContext(), curateBootstrapProviders);
        updateLayouts();
        initViewStyling();
    }

    private void initViewStyling() {
        CurateBootstrapProviders curateBootstrapProviders;
        if (this.curateBootstrapProviders.isAtLeastOneProviderNull()) {
            return;
        }
        if (this.styleSheet == null && (curateBootstrapProviders = this.curateBootstrapProviders) != null && curateBootstrapProviders.getStyleSheetProvider() != null) {
            this.curateBootstrapProviders.getStyleSheetProvider().getStyleSheet().observe(this.lifecycleOwner, new Observer() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemViewHolder$$ExternalSyntheticLambda1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.lambda$initViewStyling$0((StyleSheet) obj);
                }
            });
        }
        if (this.styleSheet != null) {
            if (new SonicRushFeature().isEnabled()) {
                this.artworkFrameView.initStyle(this.styleSheet, IconStyleKey.SONIC_RUSH_OVERLAY);
            } else {
                this.artworkFrameView.initStyle(this.styleSheet);
            }
            this.artworkFrameView.setIndicatorSize(this.styleSheet, IndicatorSizeKey.LARGE);
            ButtonSize buttonSize = this.styleSheet.getButtonSize(ButtonSizeKey.LARGE);
            Integer height = buttonSize != null ? buttonSize.getHeight() : null;
            Integer color = this.styleSheet.getColor(ColorKey.COLOR_ACCENT);
            if (height == null || color == null || this.playstateIcon == null) {
                return;
            }
            this.artworkFrameView.setPlayNotificationIconStyle(height.intValue(), color.intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViewStyling$0(StyleSheet styleSheet) {
        this.styleSheet = styleSheet;
        initViewStyling();
    }

    public void bind(final VerticalItemElement element) {
        if (StringUtils.isNotBlank(element.label())) {
            this.verticalTileView.setLabel(element.label());
        }
        if (StringUtils.isNotBlank(element.primaryText())) {
            this.verticalTileView.setTitle(element.primaryText());
        }
        if (StringUtils.isNotBlank(element.secondaryText())) {
            this.verticalTileView.setSubtitle(element.secondaryText());
        }
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (VerticalItemViewHolder.this.shouldShowOneClickButtonPlaylistWidget()) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(element.onItemSelected().get(0));
                    VerticalItemViewHolder.this.methodsDispatcher.dispatchMethods(VerticalItemViewHolder.this.ownerId, arrayList);
                    return;
                }
                VerticalItemViewHolder.this.methodsDispatcher.dispatchMethods(VerticalItemViewHolder.this.ownerId, element.onItemSelected());
            }
        });
        bindBottomBadges(element);
        bindImage(element);
        bindPlayIcon(element);
        bindPlaybackNotificationIcon(element);
    }

    private void updateLayouts() {
        if (this.curateBootstrapProviders.isAtLeastOneProviderNull()) {
            return;
        }
        updateArtworkLayout();
        updatePlayIconLayout();
    }

    private void updateArtworkLayout() {
        ArtworkFrameView artworkFrameView = this.artworkFrameView;
        if (artworkFrameView != null) {
            this.artworkImageView = ((BaseImage) artworkFrameView.findViewById(R.id.artwork)).getImage();
            this.artworkFrameView.getLayoutParams().height = this.imageDimension;
            this.artworkFrameView.getLayoutParams().width = this.imageDimension;
            this.playstateIcon = (PlayNotificationIcon) this.artworkFrameView.findViewById(R.id.PlaystateIcon);
        }
    }

    private void updatePlayIconLayout() {
        if (this.playIcon != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 8388691);
            int dimension = (int) this.resources.getDimension(R.dimen.spacer_8);
            layoutParams.setMargins(dimension, layoutParams.topMargin, layoutParams.rightMargin, dimension);
            this.playIcon.setLayoutParams(layoutParams);
        }
    }

    private void bindBottomBadges(final VerticalItemElement element) {
        List<BadgeItemElement> listBottomBadges;
        if (element == null || (listBottomBadges = element.bottomBadges()) == null || listBottomBadges.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (BadgeItemElement badgeItemElement : listBottomBadges) {
            String strDescription = badgeItemElement.description();
            if (!StringUtils.isBlank(strDescription)) {
                if (badgeItemElement instanceof AllAccessBadgeItemElement) {
                    this.verticalTileView.showAllAccessLabel(strDescription);
                } else {
                    arrayList.add(strDescription);
                }
            }
        }
        this.verticalTileView.setAudioQualityText(AudioContentEncodingUtil.getAudioQualityText(this.context, arrayList));
        this.verticalTileView.setImmersiveBadgeText(AudioContentEncodingUtil.getImmersiveBadgeText(this.context, arrayList, this.curateBootstrapProviders.getSubscriptionProvider().getUserSubscription()));
        if (getEntityType(element) == EntityType.ALBUM) {
            this.verticalTileView.setExtraAtmosBadgeText(AudioContentEncodingUtil.containsBothAtmosAnd360(arrayList, this.curateBootstrapProviders.getSubscriptionProvider().getUserSubscription()));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0015 A[Catch: Exception -> 0x0051, TRY_LEAVE, TryCatch #0 {Exception -> 0x0051, blocks: (B:7:0x000b, B:9:0x0011, B:10:0x0015), top: B:16:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void bindImage(final Touch.WidgetsInterface.v1_0.VerticalItemElement r5) {
        /*
            r4 = this;
            com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders r0 = r4.curateBootstrapProviders
            if (r0 != 0) goto L5
            return
        L5:
            com.amazon.music.curate.provider.NetworkProvider r0 = r0.getNetworkProvider()
            if (r0 == 0) goto L15
            boolean r0 = r0.isConnected()     // Catch: java.lang.Exception -> L51
            if (r0 == 0) goto L15
            r4.loadImageFromRemote(r5)     // Catch: java.lang.Exception -> L51
            goto L59
        L15:
            com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemEntity r0 = r4.verticalItemEntity     // Catch: java.lang.Exception -> L51
            int r0 = r0.getPlaceholderId(r5)     // Catch: java.lang.Exception -> L51
            com.squareup.picasso.Picasso r1 = com.squareup.picasso.Picasso.get()     // Catch: java.lang.Exception -> L51
            java.lang.String r2 = r5.image()     // Catch: java.lang.Exception -> L51
            com.squareup.picasso.RequestCreator r1 = r1.load(r2)     // Catch: java.lang.Exception -> L51
            com.squareup.picasso.NetworkPolicy r2 = com.squareup.picasso.NetworkPolicy.OFFLINE     // Catch: java.lang.Exception -> L51
            r3 = 0
            com.squareup.picasso.NetworkPolicy[] r3 = new com.squareup.picasso.NetworkPolicy[r3]     // Catch: java.lang.Exception -> L51
            com.squareup.picasso.RequestCreator r1 = r1.networkPolicy(r2, r3)     // Catch: java.lang.Exception -> L51
            com.squareup.picasso.RequestCreator r1 = r1.placeholder(r0)     // Catch: java.lang.Exception -> L51
            com.squareup.picasso.RequestCreator r0 = r1.error(r0)     // Catch: java.lang.Exception -> L51
            com.squareup.picasso.Transformation r1 = r4.getTransformation(r5)     // Catch: java.lang.Exception -> L51
            com.squareup.picasso.RequestCreator r0 = r0.transform(r1)     // Catch: java.lang.Exception -> L51
            int r1 = r4.imageDimension     // Catch: java.lang.Exception -> L51
            com.squareup.picasso.RequestCreator r0 = r0.resize(r1, r1)     // Catch: java.lang.Exception -> L51
            android.widget.ImageView r1 = r4.artworkImageView     // Catch: java.lang.Exception -> L51
            com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemViewHolder$2 r2 = new com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemViewHolder$2     // Catch: java.lang.Exception -> L51
            r2.<init>()     // Catch: java.lang.Exception -> L51
            r0.into(r1, r2)     // Catch: java.lang.Exception -> L51
            goto L59
        L51:
            r5 = move-exception
            org.slf4j.Logger r0 = com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemViewHolder.logger
            java.lang.String r1 = "Failed to load image"
            r0.error(r1, r5)
        L59:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemViewHolder.bindImage(Touch.WidgetsInterface.v1_0.VerticalItemElement):void");
    }

    private void bindPlaybackNotificationIcon(final VerticalItemElement element) {
        if (element.onItemSelected().isEmpty()) {
            return;
        }
        for (Method method : element.onItemSelected()) {
            if (method instanceof InvokeLocalSkillMethod) {
                MusicItem musicItem = this.verticalItemEntity.getMusicItem(((InvokeLocalSkillMethod) method).getSkillContext());
                bindPlaybackNotificationIcon(element, musicItem);
                updatePlayState(element, musicItem);
            } else if (method instanceof TriggerExternalActionMethod) {
                MusicItem musicItem2 = this.verticalItemEntity.getMusicItem((TriggerExternalActionMethod) method);
                bindPlaybackNotificationIcon(element, musicItem2);
                updatePlayState(element, musicItem2);
            }
        }
    }

    private void updatePlayState(final VerticalItemElement element, final MusicItem musicItem) {
        PlaybackProvider playbackProvider = this.playbackProvider;
        if (playbackProvider == null) {
            return;
        }
        playbackProvider.isPlayStateChanged().observe(this.lifecycleOwner, new Observer<Boolean>() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemViewHolder.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Boolean playstateChanged) {
                if (playstateChanged.booleanValue()) {
                    Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemViewHolder.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                            VerticalItemViewHolder.this.bindPlaybackNotificationIcon(element, musicItem);
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindPlaybackNotificationIcon(final VerticalItemElement element, final MusicItem musicItem) {
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemViewHolder$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$bindPlaybackNotificationIcon$1(musicItem, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new rx.Observer<Boolean>() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemViewHolder.4
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
                VerticalItemViewHolder.logger.error("failed to get playback state", throwable);
            }

            @Override // rx.Observer
            public void onNext(final Boolean isPlaying) {
                VerticalItemViewHolder.logger.debug("Play state of {} , isPlaying: {}", musicItem.getEntityId(), isPlaying);
                if (isPlaying != null) {
                    VerticalItemViewHolder.this.artworkFrameView.setPlayState(Integer.valueOf(isPlaying.booleanValue() ? 2 : 1));
                    VerticalItemViewHolder.this.artworkFrameView.setPlayIcon((Integer) null);
                } else {
                    VerticalItemViewHolder.this.artworkFrameView.setPlayState(0);
                    VerticalItemViewHolder.this.bindPlayIcon(element);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bindPlaybackNotificationIcon$1(MusicItem musicItem, Subscriber subscriber) {
        try {
            subscriber.onNext(this.playbackProvider.isPlaying(this.context, musicItem));
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindPlayIcon(final VerticalItemElement element) {
        if (element.onItemSelected().isEmpty()) {
            return;
        }
        for (Method method : element.onItemSelected()) {
            this.playIcon.setVisibility(4);
            EntityType playableEntityType = null;
            this.playIcon.setOnClickListener(null);
            if (method instanceof InvokeLocalSkillMethod) {
                playableEntityType = this.verticalItemEntity.getPlayableEntityType(((InvokeLocalSkillMethod) method).getSkillContext());
            } else if (method instanceof TriggerExternalActionMethod) {
                playableEntityType = this.verticalItemEntity.getPlayableEntityType((TriggerExternalActionMethod) method);
            }
            if (playableEntityType != null && this.playIcon != null) {
                this.artworkFrameView.setPlayIcon(Integer.valueOf(this.verticalItemEntity.getPlayIcon(playableEntityType, element)));
                bindPlayableContentDescription(playableEntityType, element);
                setPlayIconOnClickListener(element);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemViewHolder$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$curate$provider$EntityType;

        static {
            int[] iArr = new int[EntityType.values().length];
            $SwitchMap$com$amazon$music$curate$provider$EntityType = iArr;
            try {
                iArr[EntityType.ALBUM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$provider$EntityType[EntityType.ARTIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$provider$EntityType[EntityType.STATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$provider$EntityType[EntityType.USER_PLAYLIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$provider$EntityType[EntityType.CATALOG_PLAYLIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private void bindPlayableContentDescription(final EntityType entityType, final VerticalItemElement element) {
        String string;
        int i = AnonymousClass6.$SwitchMap$com$amazon$music$curate$provider$EntityType[entityType.ordinal()];
        if (i == 1) {
            string = this.context.getString(R.string.album_label);
        } else if (i == 2) {
            string = this.context.getString(R.string.artist_label);
        } else if (i == 3) {
            string = this.context.getString(R.string.station_label);
        } else {
            string = (i == 4 || i == 5) ? this.context.getString(R.string.playlist_label) : null;
        }
        if (StringUtils.isNotBlank(string)) {
            this.itemView.setContentDescription(this.context.getString(R.string.content_description_play_button) + string + element.primaryText());
        }
    }

    private void setPlayIconOnClickListener(final VerticalItemElement element) {
        this.playIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.VerticalItemViewHolder.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VerticalItemViewHolder.this.methodsDispatcher.dispatchMethods(VerticalItemViewHolder.this.ownerId, element.onItemSelected());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadImageFromRemote(final VerticalItemElement element) {
        int placeholderId = this.verticalItemEntity.getPlaceholderId(element);
        RequestCreator requestCreatorTransform = Picasso.get().load(element.image()).placeholder(placeholderId).error(placeholderId).transform(getTransformation(element));
        int i = this.imageDimension;
        requestCreatorTransform.resize(i, i).into(this.artworkImageView);
    }

    private Transformation getTransformation(final VerticalItemElement element) {
        if (element instanceof CircleVerticalItemElement) {
            return new CircleTransformation();
        }
        return new RoundedCornersTransformation(this.resources.getDimensionPixelSize(R.dimen.vertical_item_image_corner_radius));
    }

    private EntityType getEntityType(VerticalItemElement element) {
        EntityType playableEntityType = null;
        if (!element.onItemSelected().isEmpty()) {
            for (Method method : element.onItemSelected()) {
                if (method instanceof InvokeLocalSkillMethod) {
                    playableEntityType = this.verticalItemEntity.getPlayableEntityType(((InvokeLocalSkillMethod) method).getSkillContext());
                } else if (method instanceof TriggerExternalActionMethod) {
                    playableEntityType = this.verticalItemEntity.getPlayableEntityType((TriggerExternalActionMethod) method);
                }
            }
        }
        return playableEntityType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldShowOneClickButtonPlaylistWidget() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(CurateFeatureGating.SHOW_ONE_CLICK_BUTTON_PLAYLIST);
    }
}
