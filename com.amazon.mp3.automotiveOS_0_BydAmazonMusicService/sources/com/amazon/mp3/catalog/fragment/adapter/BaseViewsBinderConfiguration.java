package com.amazon.mp3.catalog.fragment.adapter;

import android.content.Context;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.providers.CachedArtworkProvider;
import com.amazon.music.views.library.providers.ContentEligibilityProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.providers.SDCardMissingProvider;
import com.amazon.music.views.library.providers.TrackDownloadButtonProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewsBinderConfiguration.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b/\b\u0086\b\u0018\u00002\u00020\u0001B\u0091\u0001\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\bS\u0010TJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u009d\u0001\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001dHÆ\u0001J\t\u0010!\u001a\u00020 HÖ\u0001J\t\u0010#\u001a\u00020\"HÖ\u0001J\u0013\u0010'\u001a\u00020&2\b\u0010%\u001a\u0004\u0018\u00010$HÖ\u0003R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010(\u001a\u0004\b)\u0010*R$\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u00100\u001a\u0004\b1\u00102R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u00103\u001a\u0004\b4\u00105R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u00106\u001a\u0004\b7\u00108R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u00109\u001a\u0004\b:\u0010;R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010<\u001a\u0004\b=\u0010>R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010?\u001a\u0004\b@\u0010AR\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010B\u001a\u0004\bC\u0010DR$\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010E\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010J\u001a\u0004\bK\u0010LR\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010M\u001a\u0004\bN\u0010OR\u0019\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010P\u001a\u0004\bQ\u0010R¨\u0006U"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Landroidx/lifecycle/LifecycleOwner;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "", "onDestroy", "Landroid/content/Context;", "context", "lifecycleOwner", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/imageloader/ImageLoader;", "imageLoader", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "adapterProvider", "Lcom/amazon/music/views/library/providers/CachedArtworkProvider;", "artworkProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;", "contentEligibilityProvider", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "contextMenuProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "rowButtonOnClickProvider", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "metadataOnClickListener", "Lcom/amazon/music/views/library/providers/SDCardMissingProvider;", "missingSDCardProvider", "Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "trackDownloadButtonProvider", "copy", "", "toString", "", "hashCode", "", "other", "", "equals", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Landroidx/lifecycle/LifecycleOwner;", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "setLifecycleOwner", "(Landroidx/lifecycle/LifecycleOwner;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/imageloader/ImageLoader;", "getImageLoader", "()Lcom/amazon/music/imageloader/ImageLoader;", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "getAdapterProvider", "()Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "Lcom/amazon/music/views/library/providers/CachedArtworkProvider;", "getArtworkProvider", "()Lcom/amazon/music/views/library/providers/CachedArtworkProvider;", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContentEnabilityProvider", "()Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;", "getContentEligibilityProvider", "()Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "getContextMenuProvider", "()Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "getRowButtonOnClickProvider", "()Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "setRowButtonOnClickProvider", "(Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;)V", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "getMetadataOnClickListener", "()Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "Lcom/amazon/music/views/library/providers/SDCardMissingProvider;", "getMissingSDCardProvider", "()Lcom/amazon/music/views/library/providers/SDCardMissingProvider;", "Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "getTrackDownloadButtonProvider", "()Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "<init>", "(Landroid/content/Context;Landroidx/lifecycle/LifecycleOwner;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/imageloader/ImageLoader;Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;Lcom/amazon/music/views/library/providers/CachedArtworkProvider;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;Lcom/amazon/music/views/library/providers/SDCardMissingProvider;Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class BaseViewsBinderConfiguration implements DefaultLifecycleObserver {
    private final BaseViewModelAdapterProvider adapterProvider;
    private final CachedArtworkProvider artworkProvider;
    private final ContentEligibilityProvider contentEligibilityProvider;
    private final ContentEnabilityProvider contentEnabilityProvider;
    private final Context context;
    private final MetadataContextMenuProvider contextMenuProvider;
    private final ImageLoader imageLoader;
    private LifecycleOwner lifecycleOwner;
    private final ContentMetadataOnClickListener metadataOnClickListener;
    private final SDCardMissingProvider missingSDCardProvider;
    private RowButtonOnClickProvider rowButtonOnClickProvider;
    private final StyleSheet styleSheet;
    private final TrackDownloadButtonProvider trackDownloadButtonProvider;

    public final BaseViewsBinderConfiguration copy(Context context, LifecycleOwner lifecycleOwner, StyleSheet styleSheet, ImageLoader imageLoader, BaseViewModelAdapterProvider adapterProvider, CachedArtworkProvider artworkProvider, ContentEnabilityProvider contentEnabilityProvider, ContentEligibilityProvider contentEligibilityProvider, MetadataContextMenuProvider contextMenuProvider, RowButtonOnClickProvider rowButtonOnClickProvider, ContentMetadataOnClickListener metadataOnClickListener, SDCardMissingProvider missingSDCardProvider, TrackDownloadButtonProvider trackDownloadButtonProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(imageLoader, "imageLoader");
        Intrinsics.checkNotNullParameter(adapterProvider, "adapterProvider");
        return new BaseViewsBinderConfiguration(context, lifecycleOwner, styleSheet, imageLoader, adapterProvider, artworkProvider, contentEnabilityProvider, contentEligibilityProvider, contextMenuProvider, rowButtonOnClickProvider, metadataOnClickListener, missingSDCardProvider, trackDownloadButtonProvider);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BaseViewsBinderConfiguration)) {
            return false;
        }
        BaseViewsBinderConfiguration baseViewsBinderConfiguration = (BaseViewsBinderConfiguration) other;
        return Intrinsics.areEqual(this.context, baseViewsBinderConfiguration.context) && Intrinsics.areEqual(this.lifecycleOwner, baseViewsBinderConfiguration.lifecycleOwner) && Intrinsics.areEqual(this.styleSheet, baseViewsBinderConfiguration.styleSheet) && Intrinsics.areEqual(this.imageLoader, baseViewsBinderConfiguration.imageLoader) && Intrinsics.areEqual(this.adapterProvider, baseViewsBinderConfiguration.adapterProvider) && Intrinsics.areEqual(this.artworkProvider, baseViewsBinderConfiguration.artworkProvider) && Intrinsics.areEqual(this.contentEnabilityProvider, baseViewsBinderConfiguration.contentEnabilityProvider) && Intrinsics.areEqual(this.contentEligibilityProvider, baseViewsBinderConfiguration.contentEligibilityProvider) && Intrinsics.areEqual(this.contextMenuProvider, baseViewsBinderConfiguration.contextMenuProvider) && Intrinsics.areEqual(this.rowButtonOnClickProvider, baseViewsBinderConfiguration.rowButtonOnClickProvider) && Intrinsics.areEqual(this.metadataOnClickListener, baseViewsBinderConfiguration.metadataOnClickListener) && Intrinsics.areEqual(this.missingSDCardProvider, baseViewsBinderConfiguration.missingSDCardProvider) && Intrinsics.areEqual(this.trackDownloadButtonProvider, baseViewsBinderConfiguration.trackDownloadButtonProvider);
    }

    public int hashCode() {
        int iHashCode = this.context.hashCode() * 31;
        LifecycleOwner lifecycleOwner = this.lifecycleOwner;
        int iHashCode2 = (((((((iHashCode + (lifecycleOwner == null ? 0 : lifecycleOwner.hashCode())) * 31) + this.styleSheet.hashCode()) * 31) + this.imageLoader.hashCode()) * 31) + this.adapterProvider.hashCode()) * 31;
        CachedArtworkProvider cachedArtworkProvider = this.artworkProvider;
        int iHashCode3 = (iHashCode2 + (cachedArtworkProvider == null ? 0 : cachedArtworkProvider.hashCode())) * 31;
        ContentEnabilityProvider contentEnabilityProvider = this.contentEnabilityProvider;
        int iHashCode4 = (iHashCode3 + (contentEnabilityProvider == null ? 0 : contentEnabilityProvider.hashCode())) * 31;
        ContentEligibilityProvider contentEligibilityProvider = this.contentEligibilityProvider;
        int iHashCode5 = (iHashCode4 + (contentEligibilityProvider == null ? 0 : contentEligibilityProvider.hashCode())) * 31;
        MetadataContextMenuProvider metadataContextMenuProvider = this.contextMenuProvider;
        int iHashCode6 = (iHashCode5 + (metadataContextMenuProvider == null ? 0 : metadataContextMenuProvider.hashCode())) * 31;
        RowButtonOnClickProvider rowButtonOnClickProvider = this.rowButtonOnClickProvider;
        int iHashCode7 = (iHashCode6 + (rowButtonOnClickProvider == null ? 0 : rowButtonOnClickProvider.hashCode())) * 31;
        ContentMetadataOnClickListener contentMetadataOnClickListener = this.metadataOnClickListener;
        int iHashCode8 = (iHashCode7 + (contentMetadataOnClickListener == null ? 0 : contentMetadataOnClickListener.hashCode())) * 31;
        SDCardMissingProvider sDCardMissingProvider = this.missingSDCardProvider;
        int iHashCode9 = (iHashCode8 + (sDCardMissingProvider == null ? 0 : sDCardMissingProvider.hashCode())) * 31;
        TrackDownloadButtonProvider trackDownloadButtonProvider = this.trackDownloadButtonProvider;
        return iHashCode9 + (trackDownloadButtonProvider != null ? trackDownloadButtonProvider.hashCode() : 0);
    }

    public String toString() {
        return "BaseViewsBinderConfiguration(context=" + this.context + ", lifecycleOwner=" + this.lifecycleOwner + ", styleSheet=" + this.styleSheet + ", imageLoader=" + this.imageLoader + ", adapterProvider=" + this.adapterProvider + ", artworkProvider=" + this.artworkProvider + ", contentEnabilityProvider=" + this.contentEnabilityProvider + ", contentEligibilityProvider=" + this.contentEligibilityProvider + ", contextMenuProvider=" + this.contextMenuProvider + ", rowButtonOnClickProvider=" + this.rowButtonOnClickProvider + ", metadataOnClickListener=" + this.metadataOnClickListener + ", missingSDCardProvider=" + this.missingSDCardProvider + ", trackDownloadButtonProvider=" + this.trackDownloadButtonProvider + ')';
    }

    public BaseViewsBinderConfiguration(Context context, LifecycleOwner lifecycleOwner, StyleSheet styleSheet, ImageLoader imageLoader, BaseViewModelAdapterProvider adapterProvider, CachedArtworkProvider cachedArtworkProvider, ContentEnabilityProvider contentEnabilityProvider, ContentEligibilityProvider contentEligibilityProvider, MetadataContextMenuProvider metadataContextMenuProvider, RowButtonOnClickProvider rowButtonOnClickProvider, ContentMetadataOnClickListener contentMetadataOnClickListener, SDCardMissingProvider sDCardMissingProvider, TrackDownloadButtonProvider trackDownloadButtonProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(imageLoader, "imageLoader");
        Intrinsics.checkNotNullParameter(adapterProvider, "adapterProvider");
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        this.styleSheet = styleSheet;
        this.imageLoader = imageLoader;
        this.adapterProvider = adapterProvider;
        this.artworkProvider = cachedArtworkProvider;
        this.contentEnabilityProvider = contentEnabilityProvider;
        this.contentEligibilityProvider = contentEligibilityProvider;
        this.contextMenuProvider = metadataContextMenuProvider;
        this.rowButtonOnClickProvider = rowButtonOnClickProvider;
        this.metadataOnClickListener = contentMetadataOnClickListener;
        this.missingSDCardProvider = sDCardMissingProvider;
        this.trackDownloadButtonProvider = trackDownloadButtonProvider;
    }

    public final Context getContext() {
        return this.context;
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final ImageLoader getImageLoader() {
        return this.imageLoader;
    }

    public final BaseViewModelAdapterProvider getAdapterProvider() {
        return this.adapterProvider;
    }

    public final CachedArtworkProvider getArtworkProvider() {
        return this.artworkProvider;
    }

    public final ContentEnabilityProvider getContentEnabilityProvider() {
        return this.contentEnabilityProvider;
    }

    public final ContentEligibilityProvider getContentEligibilityProvider() {
        return this.contentEligibilityProvider;
    }

    public final MetadataContextMenuProvider getContextMenuProvider() {
        return this.contextMenuProvider;
    }

    public final RowButtonOnClickProvider getRowButtonOnClickProvider() {
        return this.rowButtonOnClickProvider;
    }

    public final void setRowButtonOnClickProvider(RowButtonOnClickProvider rowButtonOnClickProvider) {
        this.rowButtonOnClickProvider = rowButtonOnClickProvider;
    }

    public final ContentMetadataOnClickListener getMetadataOnClickListener() {
        return this.metadataOnClickListener;
    }

    public final SDCardMissingProvider getMissingSDCardProvider() {
        return this.missingSDCardProvider;
    }

    public final TrackDownloadButtonProvider getTrackDownloadButtonProvider() {
        return this.trackDownloadButtonProvider;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onDestroy(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        super.onDestroy(owner);
        this.lifecycleOwner = null;
    }
}
