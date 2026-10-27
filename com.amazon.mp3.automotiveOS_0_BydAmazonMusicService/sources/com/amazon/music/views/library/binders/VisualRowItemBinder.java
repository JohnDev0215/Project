package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.R$fraction;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.providers.ContentEligibilityProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.providers.SDCardMissingProvider;
import com.amazon.music.views.library.providers.TrackDownloadButtonProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.music.views.library.views.VisualRowItemView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: VisualRowItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B{\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 \u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010/\u0012\u0006\u00105\u001a\u000204\u0012\n\b\u0002\u0010A\u001a\u0004\u0018\u00010@\u0012\n\b\u0002\u0010C\u001a\u0004\u0018\u00010B¢\u0006\u0004\bD\u0010EJ\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J \u0010\r\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000bH\u0016R\"\u0010\u0005\u001a\u00020\u00048\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0014\u001a\u00020\u00138\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010!\u001a\u0004\u0018\u00010 8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u001c\u0010&\u001a\u0004\u0018\u00010%8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u001c\u0010+\u001a\u0004\u0018\u00010*8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u001c\u00100\u001a\u0004\u0018\u00010/8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R\u0019\u00108\u001a\u0004\u0018\u0001078\u0006¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;R\u001a\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00030<8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>¨\u0006F"}, d2 = {"Lcom/amazon/music/views/library/binders/VisualRowItemBinder;", "Lcom/amazon/music/views/library/binders/TableRowItemBinder;", "Lcom/amazon/music/views/library/views/VisualRowItemView;", "Lcom/amazon/music/views/library/models/VisualRowItemModel;", "Landroid/content/Context;", "context", "createView", "view", "model", "", "bind", "", "payload", "handleStateChange", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "artworkFrameBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "contextMenuProvider", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "getContextMenuProvider", "()Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "getClickListenerFactory", "()Lcom/amazon/music/views/library/click/ClickListenerFactory;", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContentEnabilityProvider", "()Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "Lcom/amazon/music/views/library/providers/SDCardMissingProvider;", "missingSDCardProvider", "Lcom/amazon/music/views/library/providers/SDCardMissingProvider;", "getMissingSDCardProvider", "()Lcom/amazon/music/views/library/providers/SDCardMissingProvider;", "Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "trackDownloadButtonProvider", "Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "getTrackDownloadButtonProvider", "()Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "Lcom/amazon/music/subscription/UserSubscription;", "subscription", "Lcom/amazon/music/subscription/UserSubscription;", "", "TAG", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;", "contentEligibilityProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "rowButtonOnClickProvider", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;Lcom/amazon/music/views/library/providers/SDCardMissingProvider;Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;Lcom/amazon/music/subscription/UserSubscription;Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class VisualRowItemBinder extends TableRowItemBinder<VisualRowItemView, VisualRowItemModel> {
    private final String TAG;
    private final ArtworkFrameBinder artworkFrameBinder;
    private final ClickListenerFactory clickListenerFactory;
    private final ContentEnabilityProvider contentEnabilityProvider;
    private Context context;
    private final MetadataContextMenuProvider contextMenuProvider;
    private final SDCardMissingProvider missingSDCardProvider;
    private final StyleSheet styleSheet;
    private final UserSubscription subscription;
    private final TrackDownloadButtonProvider trackDownloadButtonProvider;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<VisualRowItemModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(VisualRowItemModel.class);
    }

    public /* synthetic */ VisualRowItemBinder(Context context, StyleSheet styleSheet, ArtworkFrameBinder artworkFrameBinder, MetadataContextMenuProvider metadataContextMenuProvider, ClickListenerFactory clickListenerFactory, ContentEnabilityProvider contentEnabilityProvider, SDCardMissingProvider sDCardMissingProvider, TrackDownloadButtonProvider trackDownloadButtonProvider, UserSubscription userSubscription, ContentEligibilityProvider contentEligibilityProvider, RowButtonOnClickProvider rowButtonOnClickProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, artworkFrameBinder, (i & 8) != 0 ? null : metadataContextMenuProvider, (i & 16) != 0 ? null : clickListenerFactory, (i & 32) != 0 ? null : contentEnabilityProvider, (i & 64) != 0 ? null : sDCardMissingProvider, (i & 128) != 0 ? null : trackDownloadButtonProvider, userSubscription, (i & 512) != 0 ? null : contentEligibilityProvider, (i & 1024) != 0 ? null : rowButtonOnClickProvider);
    }

    @Override // com.amazon.music.views.library.binders.TableRowItemBinder
    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.context = context;
    }

    @Override // com.amazon.music.views.library.binders.TableRowItemBinder
    public StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    @Override // com.amazon.music.views.library.binders.TableRowItemBinder
    public MetadataContextMenuProvider getContextMenuProvider() {
        return this.contextMenuProvider;
    }

    @Override // com.amazon.music.views.library.binders.TableRowItemBinder
    public ClickListenerFactory getClickListenerFactory() {
        return this.clickListenerFactory;
    }

    @Override // com.amazon.music.views.library.binders.TableRowItemBinder
    public ContentEnabilityProvider getContentEnabilityProvider() {
        return this.contentEnabilityProvider;
    }

    @Override // com.amazon.music.views.library.binders.TableRowItemBinder
    public SDCardMissingProvider getMissingSDCardProvider() {
        return this.missingSDCardProvider;
    }

    @Override // com.amazon.music.views.library.binders.TableRowItemBinder
    public TrackDownloadButtonProvider getTrackDownloadButtonProvider() {
        return this.trackDownloadButtonProvider;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VisualRowItemBinder(Context context, StyleSheet styleSheet, ArtworkFrameBinder artworkFrameBinder, MetadataContextMenuProvider metadataContextMenuProvider, ClickListenerFactory clickListenerFactory, ContentEnabilityProvider contentEnabilityProvider, SDCardMissingProvider sDCardMissingProvider, TrackDownloadButtonProvider trackDownloadButtonProvider, UserSubscription subscription, ContentEligibilityProvider contentEligibilityProvider, RowButtonOnClickProvider rowButtonOnClickProvider) {
        super(subscription, contentEligibilityProvider, rowButtonOnClickProvider, sDCardMissingProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(artworkFrameBinder, "artworkFrameBinder");
        Intrinsics.checkNotNullParameter(subscription, "subscription");
        this.context = context;
        this.styleSheet = styleSheet;
        this.artworkFrameBinder = artworkFrameBinder;
        this.contextMenuProvider = metadataContextMenuProvider;
        this.clickListenerFactory = clickListenerFactory;
        this.contentEnabilityProvider = contentEnabilityProvider;
        this.missingSDCardProvider = sDCardMissingProvider;
        this.trackDownloadButtonProvider = trackDownloadButtonProvider;
        this.subscription = subscription;
        this.TAG = Reflection.getOrCreateKotlinClass(VisualRowItemBinder.class).getSimpleName();
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public VisualRowItemView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        setContext(context);
        return new VisualRowItemView(context, getStyleSheet());
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(VisualRowItemView view, VisualRowItemModel model) {
        float fraction;
        View.OnClickListener onClickListenerCreate;
        View.OnClickListener onClickListenerCreate2;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.cleanViews();
        super.bind(view, model);
        Integer primaryTextId = model.getPrimaryTextId();
        if (primaryTextId != null) {
            view.setPrimaryTextId(primaryTextId.intValue());
        }
        model.getRowImage();
        ArtworkFrameView rowImage = view.getRowImage();
        Integer rowImageId = model.getRowImageId();
        if (rowImageId != null) {
            rowImageId.intValue();
            rowImage.setId(model.getRowImageId().intValue());
        }
        this.artworkFrameBinder.bind(rowImage, model.getRowImage());
        Boolean is16x9Tile = model.getIs16x9Tile();
        Boolean bool = Boolean.TRUE;
        boolean z = true;
        if (Intrinsics.areEqual(is16x9Tile, bool)) {
            fraction = rowImage.getContext().getResources().getFraction(R$fraction.aspect_ratio_16_9, 1, 1);
        } else {
            fraction = rowImage.getContext().getResources().getFraction(R$fraction.aspect_ratio_1_1, 1, 1);
        }
        rowImage.getArtworkImageView().setScalingFactor(fraction);
        rowImage.getArtworkImageView().getImage().setScaleType(ImageView.ScaleType.FIT_CENTER);
        if (Intrinsics.areEqual(model.getShowShuffleIcon(), bool)) {
            view.showShuffleIcon();
        } else if (Intrinsics.areEqual(model.getShowStationIcon(), bool)) {
            view.showStationIcon();
        }
        view.setCanDrag(Intrinsics.areEqual(model.getCanDrag(), bool));
        if (model.getRowNumber() == null) {
            view.hideRowNumber();
        }
        if (model.getDate() == null) {
            view.setDateVisibility(8);
        }
        if (model.getIsSuggestion()) {
            view.hideForSuggestion();
            view.showRowButton(true);
            view.updateRowIconForSuggestions();
            ClickListenerFactory clickListenerFactory = getClickListenerFactory();
            if (clickListenerFactory != null && (onClickListenerCreate2 = clickListenerFactory.create(model.getMetadata())) != null) {
                view.setRowButtonOnClickListener(onClickListenerCreate2);
            }
            view.setOnLongClickListener(null);
            view.setWasOriginallySuggestionView(true);
        } else if (view.getWasOriginallySuggestionView()) {
            view.resetRowButton();
        }
        ClickListenerFactory clickListenerFactory2 = getClickListenerFactory();
        if (clickListenerFactory2 != null && (onClickListenerCreate = clickListenerFactory2.create(model.getMetadata())) != null) {
            view.setStationOnClickedListener(onClickListenerCreate);
        }
        String secondaryText1 = model.getSecondaryText1();
        if (secondaryText1 == null || StringsKt__StringsJVMKt.isBlank(secondaryText1)) {
            String secondaryText2 = model.getSecondaryText2();
            if ((secondaryText2 == null || StringsKt__StringsJVMKt.isBlank(secondaryText2)) && model.getDownloadStateModel() == null) {
                z = false;
            }
        }
        view.setSecondaryTextContainerVisibility(z);
    }

    @Override // com.amazon.music.views.library.binders.LibraryAddableBinder
    public void handleStateChange(VisualRowItemView view, VisualRowItemModel model, Object payload) {
        ArtworkFrameModel artworkFrameModel;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 1) && (artworkFrameModel = model.getArtworkFrameModel()) != null) {
            this.artworkFrameBinder.handleStateChange(view.getRowImage(), artworkFrameModel, payload);
        }
        super.handleStateChange(view, model, payload);
    }
}
