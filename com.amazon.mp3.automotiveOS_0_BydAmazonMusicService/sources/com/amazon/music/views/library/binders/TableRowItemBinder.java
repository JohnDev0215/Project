package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.models.TableRowItemModel;
import com.amazon.music.views.library.providers.ContentEligibilityProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.providers.SDCardMissingProvider;
import com.amazon.music.views.library.providers.TrackDownloadButtonProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.utils.StringExtensionsKt;
import com.amazon.music.views.library.views.TableRowItemView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TableRowItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0004*\u00020\u00032\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005B3\u0012\u0006\u0010\"\u001a\u00020!\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\bC\u0010DJ\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0001H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0001H\u0002¢\u0006\u0004\b\u000b\u0010\nJ\u001f\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u00012\u0006\u0010\u0015\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J'\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\u0018H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u001c\u0010\nJ'\u0010\u001f\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010%\u001a\u0004\u0018\u00010$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0016\u0010(\u001a\u0004\u0018\u00010'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u001c\u0010/\u001a\u00020*8&@&X¦\u000e¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0014\u00103\u001a\u0002008&X¦\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0016\u00106\u001a\u0004\u0018\u00010\u00148&X¦\u0004¢\u0006\u0006\u001a\u0004\b4\u00105R\u0016\u0010:\u001a\u0004\u0018\u0001078&X¦\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u0016\u0010>\u001a\u0004\u0018\u00010;8&X¦\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00108&X¦\u0004¢\u0006\u0006\u001a\u0004\b?\u0010@R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u00188&X¦\u0004¢\u0006\u0006\u001a\u0004\bA\u0010B¨\u0006E"}, d2 = {"Lcom/amazon/music/views/library/binders/TableRowItemBinder;", "Lcom/amazon/music/views/library/views/TableRowItemView;", "V", "Lcom/amazon/music/views/library/models/TableRowItemModel;", "M", "Lcom/amazon/music/views/library/binders/LibraryAddableBinder;", "view", "model", "", "bindEnabledState", "(Lcom/amazon/music/views/library/views/TableRowItemView;Lcom/amazon/music/views/library/models/TableRowItemModel;)V", "bindText", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "downloadStateModel", "updateDownloadIndicator", "(Lcom/amazon/music/views/library/views/TableRowItemView;Lcom/amazon/music/views/library/models/DownloadStateModel;)V", "Lcom/amazon/music/views/library/providers/SDCardMissingProvider;", "missingSDCardProvider", "updateSDCardIcon", "(Lcom/amazon/music/views/library/views/TableRowItemView;Lcom/amazon/music/views/library/models/TableRowItemModel;Lcom/amazon/music/views/library/providers/SDCardMissingProvider;)V", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "menuProvider", "bindContextMenu", "(Lcom/amazon/music/views/library/views/TableRowItemView;Lcom/amazon/music/views/library/models/TableRowItemModel;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;)V", "Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "trackDownloadButtonProvider", "bindDownloadButton", "(Lcom/amazon/music/views/library/views/TableRowItemView;Lcom/amazon/music/views/library/models/TableRowItemModel;Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;)V", "bind", "", "payload", "handleStateChange", "(Lcom/amazon/music/views/library/views/TableRowItemView;Lcom/amazon/music/views/library/models/TableRowItemModel;Ljava/lang/Object;)V", "Lcom/amazon/music/subscription/UserSubscription;", "subscription", "Lcom/amazon/music/subscription/UserSubscription;", "Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;", "contentEligibilityProvider", "Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "rowButtonOnClickProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "context", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "getContextMenuProvider", "()Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "contextMenuProvider", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "getClickListenerFactory", "()Lcom/amazon/music/views/library/click/ClickListenerFactory;", "clickListenerFactory", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContentEnabilityProvider", "()Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "contentEnabilityProvider", "getMissingSDCardProvider", "()Lcom/amazon/music/views/library/providers/SDCardMissingProvider;", "getTrackDownloadButtonProvider", "()Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "<init>", "(Lcom/amazon/music/subscription/UserSubscription;Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;Lcom/amazon/music/views/library/providers/SDCardMissingProvider;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public abstract class TableRowItemBinder<V extends TableRowItemView, M extends TableRowItemModel> extends LibraryAddableBinder<V, M> {
    private final ContentEligibilityProvider contentEligibilityProvider;
    private final RowButtonOnClickProvider rowButtonOnClickProvider;
    private final UserSubscription subscription;

    public abstract ClickListenerFactory getClickListenerFactory();

    public abstract ContentEnabilityProvider getContentEnabilityProvider();

    public abstract Context getContext();

    public abstract MetadataContextMenuProvider getContextMenuProvider();

    public abstract SDCardMissingProvider getMissingSDCardProvider();

    public abstract StyleSheet getStyleSheet();

    public abstract TrackDownloadButtonProvider getTrackDownloadButtonProvider();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TableRowItemBinder(UserSubscription subscription, ContentEligibilityProvider contentEligibilityProvider, RowButtonOnClickProvider rowButtonOnClickProvider, SDCardMissingProvider sDCardMissingProvider) {
        super(subscription, contentEligibilityProvider, rowButtonOnClickProvider, null);
        Intrinsics.checkNotNullParameter(subscription, "subscription");
        this.subscription = subscription;
        this.contentEligibilityProvider = contentEligibilityProvider;
        this.rowButtonOnClickProvider = rowButtonOnClickProvider;
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void bind(V r6, M r7) {
        /*
            Method dump skipped, instruction units count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.views.library.binders.TableRowItemBinder.bind(com.amazon.music.views.library.views.TableRowItemView, com.amazon.music.views.library.models.TableRowItemModel):void");
    }

    private final void bindEnabledState(V view, M model) {
        ContentEnabilityProvider contentEnabilityProvider = getContentEnabilityProvider();
        if (contentEnabilityProvider == null) {
            return;
        }
        contentEnabilityProvider.setContentEnabled(view, model);
    }

    private final void bindText(V view, M model) {
        Integer rowNumber = model.getRowNumber();
        view.setText(rowNumber == null ? null : Integer.valueOf(rowNumber.intValue() + 1).toString(), Intrinsics.areEqual(model.getShowExplicitBadge(), Boolean.TRUE) ? StringExtensionsKt.removeExplicitTitle(model.getPrimaryText()) : model.getPrimaryText(), model.getSecondaryText1(), model.getSecondaryText2(), model.getDate(), model.getTime());
    }

    public void handleStateChange(V view, M model, Object payload) {
        Unit unit;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        super.handleStateChange(view, model, payload);
        if (Intrinsics.areEqual(payload, (Object) 12)) {
            DownloadStateModel downloadStateModel = model.getDownloadStateModel();
            if (downloadStateModel != null) {
                updateDownloadIndicator(view, downloadStateModel);
            }
            TrackDownloadButtonProvider trackDownloadButtonProvider = getTrackDownloadButtonProvider();
            if (trackDownloadButtonProvider != null) {
                view.showDownloadButton(trackDownloadButtonProvider.shouldShowDownloadIcon(model));
            }
        }
        if (Intrinsics.areEqual(payload, (Object) 14)) {
            SDCardMissingProvider missingSDCardProvider = getMissingSDCardProvider();
            if (missingSDCardProvider == null) {
                unit = null;
            } else {
                updateSDCardIcon(view, model, missingSDCardProvider);
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                view.showSDCardIcon(false);
            }
        }
        if (Intrinsics.areEqual(payload, (Object) 16)) {
            bindText(view, model);
        }
        if (Intrinsics.areEqual(payload, (Object) 1)) {
            view.setDisplayState(model.getDisplayState());
        }
        if (Intrinsics.areEqual(payload, (Object) 19)) {
            bindEnabledState(view, model);
        }
    }

    private final void updateDownloadIndicator(V view, DownloadStateModel downloadStateModel) {
        new DownloadIndicatorBinder(getStyleSheet()).bind(view.getDownloadIndicatorView(), downloadStateModel);
        view.initDownloadIndicator(downloadStateModel.getDownloadState());
    }

    private final void updateSDCardIcon(V view, final M model, final SDCardMissingProvider missingSDCardProvider) {
        boolean z = model.getShowSDCardIcon() && !this.subscription.isNightwingOnly();
        view.showSDCardIcon(z);
        if (z) {
            view.setMissingSDCardIconClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.TableRowItemBinder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    TableRowItemBinder.m2370updateSDCardIcon$lambda8(missingSDCardProvider, model, view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSDCardIcon$lambda-8, reason: not valid java name */
    public static final void m2370updateSDCardIcon$lambda8(SDCardMissingProvider missingSDCardProvider, TableRowItemModel model, View view) {
        Intrinsics.checkNotNullParameter(missingSDCardProvider, "$missingSDCardProvider");
        Intrinsics.checkNotNullParameter(model, "$model");
        missingSDCardProvider.onSDCardMissingIconClicked(model.getMetadata());
    }

    private final void bindContextMenu(final V view, M model, final MetadataContextMenuProvider menuProvider) {
        final ContentMetadata metadata = model.getMetadata();
        boolean zShouldDisplayContextMenu = menuProvider.shouldDisplayContextMenu(metadata);
        view.showOverflowButton(zShouldDisplayContextMenu);
        if (zShouldDisplayContextMenu) {
            view.setOverflowOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.TableRowItemBinder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    TableRowItemBinder.m2368bindContextMenu$lambda9(menuProvider, view, metadata, view2);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.amazon.music.views.library.binders.TableRowItemBinder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view2) {
                    return TableRowItemBinder.m2367bindContextMenu$lambda10(menuProvider, view, metadata, view2);
                }
            });
            menuProvider.updateStateProvider(metadata);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindContextMenu$lambda-9, reason: not valid java name */
    public static final void m2368bindContextMenu$lambda9(MetadataContextMenuProvider menuProvider, TableRowItemView view, ContentMetadata metadata, View view2) {
        Intrinsics.checkNotNullParameter(menuProvider, "$menuProvider");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(metadata, "$metadata");
        menuProvider.openContextMenu(view, metadata, InteractionType.TAP);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindContextMenu$lambda-10, reason: not valid java name */
    public static final boolean m2367bindContextMenu$lambda10(MetadataContextMenuProvider menuProvider, TableRowItemView view, ContentMetadata metadata, View view2) {
        Intrinsics.checkNotNullParameter(menuProvider, "$menuProvider");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(metadata, "$metadata");
        menuProvider.openContextMenu(view, metadata, InteractionType.LONG_PRESS);
        return true;
    }

    private final void bindDownloadButton(V view, final M model, final TrackDownloadButtonProvider trackDownloadButtonProvider) {
        if (trackDownloadButtonProvider.shouldShowDownloadIcon(model)) {
            view.showRowButton(false);
            view.showDownloadButton(true);
            view.setDownloadButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.TableRowItemBinder$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    TableRowItemBinder.m2369bindDownloadButton$lambda11(trackDownloadButtonProvider, model, view2);
                }
            });
            return;
        }
        view.showDownloadButton(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindDownloadButton$lambda-11, reason: not valid java name */
    public static final void m2369bindDownloadButton$lambda11(TrackDownloadButtonProvider trackDownloadButtonProvider, TableRowItemModel model, View view) {
        Intrinsics.checkNotNullParameter(trackDownloadButtonProvider, "$trackDownloadButtonProvider");
        Intrinsics.checkNotNullParameter(model, "$model");
        trackDownloadButtonProvider.onDownload(model);
    }
}
