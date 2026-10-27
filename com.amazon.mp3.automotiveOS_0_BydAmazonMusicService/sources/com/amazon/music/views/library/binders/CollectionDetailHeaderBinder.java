package com.amazon.music.views.library.binders;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.models.CollectionDetailHeaderModel;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.providers.CachedArtworkProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.providers.UserPlaylistDetailProfileLinkProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.CollectionDetailHeaderView;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: CollectionDetailHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001Bu\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010*\u001a\u00020)\u0012\u0014\b\u0002\u0010.\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020-0+\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010/\u0012\u0006\u00102\u001a\u000201\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0007¢\u0006\u0004\b3\u00104J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0016J\u0018\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0016R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\u00020\u00078\u0014@\u0014X\u0094\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001a\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010\u001f\u001a\u00020\u00078\u0014@\u0014X\u0094\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010\u001b\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u00065"}, d2 = {"Lcom/amazon/music/views/library/binders/CollectionDetailHeaderBinder;", "Lcom/amazon/music/views/library/binders/PageHeaderBinder;", "Lcom/amazon/music/views/library/views/CollectionDetailHeaderView;", "Lcom/amazon/music/views/library/models/CollectionDetailHeaderModel;", "model", "Landroid/text/SpannableString;", "setupCuratedByLink", "", "isCuratedByProfile", "Landroid/content/Context;", "context", "createView", "view", "", "bind", "Lcom/amazon/music/views/library/providers/CachedArtworkProvider;", "cachedArtworkProvider", "Lcom/amazon/music/views/library/providers/CachedArtworkProvider;", "Lcom/amazon/music/imageloader/ImageLoader;", "imageLoader", "Lcom/amazon/music/imageloader/ImageLoader;", "Lcom/amazon/music/views/library/providers/UserPlaylistDetailProfileLinkProvider;", "playlistProfileLinkProvider", "Lcom/amazon/music/views/library/providers/UserPlaylistDetailProfileLinkProvider;", "getPlaylistProfileLinkProvider", "()Lcom/amazon/music/views/library/providers/UserPlaylistDetailProfileLinkProvider;", "isSonicRushEnabled", "Z", "()Z", "setSonicRushEnabled", "(Z)V", "shouldShowDownloadTextButton", "getShouldShowDownloadTextButton", "setShouldShowDownloadTextButton", "", "CURATED_BY_STRING", "Ljava/lang/String;", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "onClickListenerMap", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "contextMenuListener", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "contentEnabilityProvider", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Ljava/util/Map;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;Lcom/amazon/music/views/library/providers/CachedArtworkProvider;Lcom/amazon/music/imageloader/ImageLoader;Lcom/amazon/music/views/library/providers/UserPlaylistDetailProfileLinkProvider;ZZ)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class CollectionDetailHeaderBinder extends PageHeaderBinder<CollectionDetailHeaderView, CollectionDetailHeaderModel> {
    private final String CURATED_BY_STRING;
    private final CachedArtworkProvider cachedArtworkProvider;
    private final ImageLoader imageLoader;
    private boolean isSonicRushEnabled;
    private final UserPlaylistDetailProfileLinkProvider playlistProfileLinkProvider;
    private boolean shouldShowDownloadTextButton;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<CollectionDetailHeaderModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(CollectionDetailHeaderModel.class);
    }

    public final UserPlaylistDetailProfileLinkProvider getPlaylistProfileLinkProvider() {
        return this.playlistProfileLinkProvider;
    }

    @Override // com.amazon.music.views.library.binders.PageHeaderBinder
    /* JADX INFO: renamed from: isSonicRushEnabled, reason: from getter */
    protected boolean getIsSonicRushEnabled() {
        return this.isSonicRushEnabled;
    }

    protected boolean getShouldShowDownloadTextButton() {
        return this.shouldShowDownloadTextButton;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionDetailHeaderBinder(Context context, StyleSheet styleSheet, Map<PageHeaderActionIconModel.ActionType, ? extends ActionIconOnClickListener> onClickListenerMap, MetadataContextMenuProvider metadataContextMenuProvider, ContentEnabilityProvider contentEnabilityProvider, CachedArtworkProvider cachedArtworkProvider, ImageLoader imageLoader, UserPlaylistDetailProfileLinkProvider userPlaylistDetailProfileLinkProvider, boolean z, boolean z2) {
        super(context, styleSheet, onClickListenerMap, metadataContextMenuProvider, contentEnabilityProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(onClickListenerMap, "onClickListenerMap");
        Intrinsics.checkNotNullParameter(contentEnabilityProvider, "contentEnabilityProvider");
        Intrinsics.checkNotNullParameter(imageLoader, "imageLoader");
        this.cachedArtworkProvider = cachedArtworkProvider;
        this.imageLoader = imageLoader;
        this.playlistProfileLinkProvider = userPlaylistDetailProfileLinkProvider;
        this.isSonicRushEnabled = z;
        this.shouldShowDownloadTextButton = z2;
        String string = context.getResources().getString(R$string.dmusic_library_playlists_curated);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ibrary_playlists_curated)");
        this.CURATED_BY_STRING = string;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public CollectionDetailHeaderView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new CollectionDetailHeaderView(context, getStyleSheet(), getIsSonicRushEnabled(), getShouldShowDownloadTextButton());
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(CollectionDetailHeaderView view, CollectionDetailHeaderModel model) {
        Unit unit;
        List<String> contentTypes;
        CachedArtworkProvider cachedArtworkProvider;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        super.setSonicRushEnabled(getIsSonicRushEnabled());
        super.setShouldShowDownloadTextButton(getShouldShowDownloadTextButton());
        super.bind(view, model);
        String artworkUrl = model.getArtworkUrl();
        if (artworkUrl == null) {
            unit = null;
        } else {
            this.imageLoader.loadImage(artworkUrl, view.getImageLoaderCallback());
            unit = Unit.INSTANCE;
        }
        if (unit == null && model.getMetadata() != null && (cachedArtworkProvider = this.cachedArtworkProvider) != null) {
            cachedArtworkProvider.loadCachedArtwork(view.getImageLoaderCallback(), view.getArtworkImageView(), model.getMetadata());
        }
        ContentMetadata metadata = model.getMetadata();
        PlaylistMetadata playlistMetadata = metadata instanceof PlaylistMetadata ? (PlaylistMetadata) metadata : null;
        view.setIs16x9Tile((playlistMetadata == null || (contentTypes = playlistMetadata.getContentTypes()) == null || !contentTypes.contains("VIDEO")) ? false : true);
        if (isCuratedByProfile(model)) {
            view.setCuratedByLink(setupCuratedByLink(model));
        }
    }

    private final SpannableString setupCuratedByLink(final CollectionDetailHeaderModel model) {
        SpannableString spannableString = new SpannableString(model.getPrimaryTitle());
        Context context = getContext();
        final int iIntValue = (context == null ? null : Integer.valueOf(ContextCompat.getColor(context, R$color.blue_accent))).intValue();
        Context context2 = getContext();
        final int iIntValue2 = (context2 != null ? Integer.valueOf(ContextCompat.getColor(context2, R$color.primary)) : null).intValue();
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.amazon.music.views.library.binders.CollectionDetailHeaderBinder$setupCuratedByLink$profileNameClickableSpan$1
            @Override // android.text.style.ClickableSpan
            public void onClick(View textView) {
                Intrinsics.checkNotNullParameter(textView, "textView");
                UserPlaylistDetailProfileLinkProvider playlistProfileLinkProvider = this.this$0.getPlaylistProfileLinkProvider();
                if (playlistProfileLinkProvider == null) {
                    return;
                }
                playlistProfileLinkProvider.navigateToProfile(model.getCuratedByProfileId());
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                super.updateDrawState(ds);
                ds.setColor(iIntValue);
                ds.setUnderlineText(false);
            }
        };
        ClickableSpan clickableSpan2 = new ClickableSpan() { // from class: com.amazon.music.views.library.binders.CollectionDetailHeaderBinder$setupCuratedByLink$curatedByClickableSpan$1
            @Override // android.text.style.ClickableSpan
            public void onClick(View textView) {
                Intrinsics.checkNotNullParameter(textView, "textView");
                UserPlaylistDetailProfileLinkProvider playlistProfileLinkProvider = this.this$0.getPlaylistProfileLinkProvider();
                if (playlistProfileLinkProvider == null) {
                    return;
                }
                playlistProfileLinkProvider.navigateToProfile(model.getCuratedByProfileId());
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                super.updateDrawState(ds);
                ds.setColor(iIntValue2);
                ds.setUnderlineText(false);
            }
        };
        int length = spannableString.length() - (spannableString.length() - this.CURATED_BY_STRING.length());
        spannableString.setSpan(clickableSpan, length, spannableString.length(), 33);
        spannableString.setSpan(clickableSpan2, 0, length, 33);
        return spannableString;
    }

    private final boolean isCuratedByProfile(CollectionDetailHeaderModel model) {
        String curatedByProfileId = model.getCuratedByProfileId();
        if (curatedByProfileId == null || curatedByProfileId.length() == 0) {
            return false;
        }
        String primaryTitle = model.getPrimaryTitle();
        return ((primaryTitle == null || primaryTitle.length() == 0) || this.playlistProfileLinkProvider == null) ? false : true;
    }
}
