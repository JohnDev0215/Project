package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.DescriptiveRowItemModel;
import com.amazon.music.views.library.models.DisplayState;
import com.amazon.music.views.library.providers.ContentEligibilityProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.providers.SDCardMissingProvider;
import com.amazon.music.views.library.providers.TrackDownloadButtonProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.DescriptiveRowItemView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: DescriptiveRowItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001By\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u0012\b\u0010!\u001a\u0004\u0018\u00010 \u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010/\u0012\u0006\u0010<\u001a\u00020;\u0012\n\b\u0002\u0010>\u001a\u0004\u0018\u00010=\u0012\n\b\u0002\u0010@\u001a\u0004\u0018\u00010?\u0012\b\b\u0002\u00105\u001a\u000204¢\u0006\u0004\bA\u0010BJ\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J \u0010\u0010\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u000eH\u0016R\"\u0010\t\u001a\u00020\b8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0017\u001a\u00020\u00168\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010!\u001a\u0004\u0018\u00010 8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u001c\u0010&\u001a\u0004\u0018\u00010%8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u001c\u0010+\u001a\u0004\u0018\u00010*8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u001c\u00100\u001a\u0004\u0018\u00010/8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R\u001a\u0010:\u001a\b\u0012\u0004\u0012\u00020\u0003078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b8\u00109¨\u0006C"}, d2 = {"Lcom/amazon/music/views/library/binders/DescriptiveRowItemBinder;", "Lcom/amazon/music/views/library/binders/TableRowItemBinder;", "Lcom/amazon/music/views/library/views/DescriptiveRowItemView;", "Lcom/amazon/music/views/library/models/DescriptiveRowItemModel;", "model", "", "getPopularity", "(Lcom/amazon/music/views/library/models/DescriptiveRowItemModel;)Ljava/lang/Double;", "Landroid/content/Context;", "context", "createView", "view", "", "bind", "", "payload", "handleStateChange", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "contextMenuProvider", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "getContextMenuProvider", "()Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "getClickListenerFactory", "()Lcom/amazon/music/views/library/click/ClickListenerFactory;", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContentEnabilityProvider", "()Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "Lcom/amazon/music/views/library/providers/SDCardMissingProvider;", "missingSDCardProvider", "Lcom/amazon/music/views/library/providers/SDCardMissingProvider;", "getMissingSDCardProvider", "()Lcom/amazon/music/views/library/providers/SDCardMissingProvider;", "Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "trackDownloadButtonProvider", "Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "getTrackDownloadButtonProvider", "()Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "", "isSonicRushEnabled", "Z", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "Lcom/amazon/music/subscription/UserSubscription;", "userSubscription", "Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;", "contentEligibilityProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "rowButtonOnClickProvider", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;Lcom/amazon/music/views/library/providers/SDCardMissingProvider;Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;Lcom/amazon/music/subscription/UserSubscription;Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;Z)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class DescriptiveRowItemBinder extends TableRowItemBinder<DescriptiveRowItemView, DescriptiveRowItemModel> {
    private final ClickListenerFactory clickListenerFactory;
    private final ContentEnabilityProvider contentEnabilityProvider;
    private Context context;
    private final MetadataContextMenuProvider contextMenuProvider;
    private final boolean isSonicRushEnabled;
    private final SDCardMissingProvider missingSDCardProvider;
    private final StyleSheet styleSheet;
    private final TrackDownloadButtonProvider trackDownloadButtonProvider;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<DescriptiveRowItemModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(DescriptiveRowItemModel.class);
    }

    @Override // com.amazon.music.views.library.binders.TableRowItemBinder
    public Context getContext() {
        return this.context;
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
    public DescriptiveRowItemBinder(Context context, StyleSheet styleSheet, MetadataContextMenuProvider metadataContextMenuProvider, ClickListenerFactory clickListenerFactory, ContentEnabilityProvider contentEnabilityProvider, SDCardMissingProvider sDCardMissingProvider, TrackDownloadButtonProvider trackDownloadButtonProvider, UserSubscription userSubscription, ContentEligibilityProvider contentEligibilityProvider, RowButtonOnClickProvider rowButtonOnClickProvider, boolean z) {
        super(userSubscription, contentEligibilityProvider, rowButtonOnClickProvider, sDCardMissingProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(userSubscription, "userSubscription");
        this.context = context;
        this.styleSheet = styleSheet;
        this.contextMenuProvider = metadataContextMenuProvider;
        this.clickListenerFactory = clickListenerFactory;
        this.contentEnabilityProvider = contentEnabilityProvider;
        this.missingSDCardProvider = sDCardMissingProvider;
        this.trackDownloadButtonProvider = trackDownloadButtonProvider;
        this.isSonicRushEnabled = z;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public DescriptiveRowItemView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new DescriptiveRowItemView(context, getStyleSheet(), this.isSonicRushEnabled);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(DescriptiveRowItemView view, DescriptiveRowItemModel model) {
        View.OnClickListener onClickListenerCreate;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.cleanViews();
        view.setSlotNumber(model.getSlotNumber());
        super.bind(view, model);
        view.showPopularity(getPopularity(model));
        view.setEnabled(model.getDisplayState() != DisplayState.DISABLED);
        view.displayStationButton(model.getShowStationIcon());
        view.setRowCta(getStyleSheet(), model.getShowRowCta());
        view.showCommentary(model.getCommentaryText(), model.getCommentaryVisible());
        view.showBadges(!model.getCommentaryVisible());
        ClickListenerFactory clickListenerFactory = getClickListenerFactory();
        if (clickListenerFactory == null || (onClickListenerCreate = clickListenerFactory.create(model.getMetadata())) == null) {
            return;
        }
        view.setStationOnClickedListener(onClickListenerCreate);
        view.setRowCtaOnClickedListener(onClickListenerCreate);
    }

    private final Double getPopularity(DescriptiveRowItemModel model) {
        if (model.getCommentaryVisible()) {
            return null;
        }
        ContentMetadata metadata = model.getMetadata();
        TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
        if (trackMetadata == null) {
            return null;
        }
        return trackMetadata.getPopularity();
    }

    @Override // com.amazon.music.views.library.binders.LibraryAddableBinder
    public void handleStateChange(DescriptiveRowItemView view, DescriptiveRowItemModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 20)) {
            view.showPopularity(getPopularity(model));
            view.showBadges(!model.getCommentaryVisible());
            view.showCommentary(model.getCommentaryText(), model.getCommentaryVisible());
        }
        super.handleStateChange(view, model, payload);
    }
}
