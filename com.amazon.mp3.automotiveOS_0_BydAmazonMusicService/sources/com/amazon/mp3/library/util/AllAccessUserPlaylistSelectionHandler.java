package com.amazon.mp3.library.util;

import android.content.Context;
import android.content.DialogInterface;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.FeaturedToggleViewModel;
import com.amazon.music.views.library.providers.FeaturedToggleListenerProvider;
import com.amazon.music.views.library.views.FeaturedToggleView;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AllAccessUserPlaylistSelectionHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bBC\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/library/util/AllAccessUserPlaylistSelectionHandler;", "", "context", "Landroid/content/Context;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", ContextMappingConstants.ENTITY_ID, "", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", "confirmSelectionCallback", "Lkotlin/Function0;", "", "(Landroid/content/Context;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;Lkotlin/jvm/functions/Function0;)V", "createConfirmSelectionDialog", "view", "Lcom/amazon/music/views/library/views/FeaturedToggleView;", "emitUiClickForAAUPToggle", Constants.ENABLE_DISABLE, "", "getAAUPToggleListenerProvider", "Lcom/amazon/music/views/library/providers/FeaturedToggleListenerProvider;", "getCancelSelectionListener", "Landroid/content/DialogInterface$OnClickListener;", "getConfirmSelectionListener", "getOnCancelListener", "Landroid/content/DialogInterface$OnCancelListener;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AllAccessUserPlaylistSelectionHandler {
    private static final String TAG = AllAccessUserPlaylistSelectionHandler.class.getSimpleName();
    private Function0<Unit> confirmSelectionCallback;
    private final Context context;
    private String entityId;
    private EntityIdType entityIdType;
    private PageType pageType;

    public AllAccessUserPlaylistSelectionHandler(Context context, PageType pageType, String str, EntityIdType entityIdType, Function0<Unit> confirmSelectionCallback) {
        Intrinsics.checkNotNullParameter(confirmSelectionCallback, "confirmSelectionCallback");
        this.context = context;
        this.pageType = pageType;
        this.entityId = str;
        this.entityIdType = entityIdType;
        this.confirmSelectionCallback = confirmSelectionCallback;
    }

    public final FeaturedToggleListenerProvider getAAUPToggleListenerProvider() {
        return new FeaturedToggleListenerProvider() { // from class: com.amazon.mp3.library.util.AllAccessUserPlaylistSelectionHandler.getAAUPToggleListenerProvider.1
            @Override // com.amazon.music.views.library.providers.FeaturedToggleListenerProvider
            public void onClick(FeaturedToggleViewModel model, FeaturedToggleView view) {
                Intrinsics.checkNotNullParameter(model, "model");
                Intrinsics.checkNotNullParameter(view, "view");
                view.updateToggle();
                if (model.getToggleType() != FeaturedToggleViewModel.ToggleType.AAUP_SELECTION) {
                    Log.warning(AllAccessUserPlaylistSelectionHandler.TAG, Intrinsics.stringPlus("Unexpected toggleType found: ", model.getToggleType()));
                    return;
                }
                AllAccessUserPlaylistSelectionHandler.this.emitUiClickForAAUPToggle(view.getIsToggleActive());
                if (view.getIsToggleActive()) {
                    AllAccessUserPlaylistSelectionHandler.this.createConfirmSelectionDialog(view);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createConfirmSelectionDialog(FeaturedToggleView view) {
        if (this.context == null) {
            return;
        }
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this.context);
        bauhausDialogBuilder.setTitle(R.string.dmusic_enable_all_access);
        bauhausDialogBuilder.setMessage(R.string.dmusic_enable_all_access_details);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_enable_all_access_confirm, getConfirmSelectionListener());
        bauhausDialogBuilder.setNegativeButton(R.string.dmusic_enable_all_access_cancel, getCancelSelectionListener(view));
        bauhausDialogBuilder.setOnCancelListener(getOnCancelListener(view));
        bauhausDialogBuilder.create().show();
        PageType pageType = PageType.AAUP_CONFIRM_SELECTION;
        String str = this.entityId;
        EntityIdType entityIdType = this.entityIdType;
        MetricsUtil.sendUiPageViewMetric(pageType, str, entityIdType == null ? null : entityIdType.getMetricsValue(), this.context);
    }

    private final DialogInterface.OnClickListener getConfirmSelectionListener() {
        return new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.util.AllAccessUserPlaylistSelectionHandler$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AllAccessUserPlaylistSelectionHandler.m1349getConfirmSelectionListener$lambda3(this.f$0, dialogInterface, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getConfirmSelectionListener$lambda-3, reason: not valid java name */
    public static final void m1349getConfirmSelectionListener$lambda3(AllAccessUserPlaylistSelectionHandler this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        final String str = this$0.entityId;
        if (str == null) {
            Log.error(TAG, "New user playlist selection's luid is missing. Cannot update on demand user playlists");
        } else if (str != null) {
            ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.library.util.AllAccessUserPlaylistSelectionHandler$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    AllAccessUserPlaylistSelectionHandler.m1350getConfirmSelectionListener$lambda3$lambda2$lambda1(str);
                }
            });
        }
        MetricsUtil.sendUiClickMetric(ActionType.CONFIRM_AAUP_SELECTION, InteractionType.TAP, this$0.pageType, this$0.entityId, this$0.entityIdType);
        this$0.confirmSelectionCallback.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getConfirmSelectionListener$lambda-3$lambda-2$lambda-1, reason: not valid java name */
    public static final void m1350getConfirmSelectionListener$lambda3$lambda2$lambda1(String it) {
        Intrinsics.checkNotNullParameter(it, "$it");
        GetOnDemandPlaylistsService.INSTANCE.get().updateOnDemandUserPlaylist(it);
    }

    private final DialogInterface.OnClickListener getCancelSelectionListener(final FeaturedToggleView view) {
        return new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.util.AllAccessUserPlaylistSelectionHandler$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AllAccessUserPlaylistSelectionHandler.m1348getCancelSelectionListener$lambda4(view, this, dialogInterface, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getCancelSelectionListener$lambda-4, reason: not valid java name */
    public static final void m1348getCancelSelectionListener$lambda4(FeaturedToggleView view, AllAccessUserPlaylistSelectionHandler this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        view.updateToggle();
        MetricsUtil.sendUiClickMetric(ActionType.CANCEL_AAUP_SELECTION, InteractionType.TAP, this$0.pageType, this$0.entityId, this$0.entityIdType);
    }

    private final DialogInterface.OnCancelListener getOnCancelListener(final FeaturedToggleView view) {
        return new DialogInterface.OnCancelListener() { // from class: com.amazon.mp3.library.util.AllAccessUserPlaylistSelectionHandler$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                AllAccessUserPlaylistSelectionHandler.m1351getOnCancelListener$lambda5(view, this, dialogInterface);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getOnCancelListener$lambda-5, reason: not valid java name */
    public static final void m1351getOnCancelListener$lambda5(FeaturedToggleView view, AllAccessUserPlaylistSelectionHandler this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        view.updateToggle();
        MetricsUtil.sendUiClickMetric(ActionType.CANCEL_AAUP_SELECTION, InteractionType.TAP, this$0.pageType, this$0.entityId, this$0.entityIdType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitUiClickForAAUPToggle(boolean isEnabled) {
        MetricsUtil.sendUiClickMetric(isEnabled ? ActionType.TOGGLE_AAUP_ON : ActionType.TOGGLE_AAUP_OFF, InteractionType.TOGGLE, this.pageType, this.entityId, this.entityIdType);
    }
}
