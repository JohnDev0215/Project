package com.amazon.mp3.playback.view.multiminiplayer;

import androidx.fragment.app.FragmentTransaction;
import com.amazon.digitalmusicxp.types.ActiveDevicePlayback;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.views.library.models.CastingListItemHorizontalTileModel;
import com.amazon.music.views.library.providers.CastingTileListenerProvider;
import com.amazon.music.views.library.views.CastingListItemHorizontalTileView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CloudQueueCastingListListenerProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0016\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0016\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/playback/view/multiminiplayer/CloudQueueCastingListListenerProvider;", "Lcom/amazon/music/views/library/providers/CastingTileListenerProvider;", "()V", "DIALOG_NAME", "", "castingManager", "Lcom/amazon/music/casting/session/CastingSessionManager;", "isLoading", "", "clickListener", "", "view", "Lcom/amazon/music/views/library/views/CastingListItemHorizontalTileView;", "model", "Lcom/amazon/music/views/library/models/CastingListItemHorizontalTileModel;", "disconnect", "disconnectListener", "isCasting", "showDialog", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CloudQueueCastingListListenerProvider implements CastingTileListenerProvider {
    private final String DIALOG_NAME;
    private CastingSessionManager castingManager;
    private boolean isLoading;

    public CloudQueueCastingListListenerProvider() {
        CastingSessionManager castingSessionManager = CastingSessionManager.getInstance();
        Intrinsics.checkNotNullExpressionValue(castingSessionManager, "getInstance()");
        this.castingManager = castingSessionManager;
        this.DIALOG_NAME = "CloudQueueDisconnectConfirmDialog";
    }

    @Override // com.amazon.music.views.library.providers.CastingTileListenerProvider
    public void clickListener(CastingListItemHorizontalTileView view, CastingListItemHorizontalTileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        if (getIsLoading() || this.castingManager.isConnected()) {
            return;
        }
        Boolean isPlaying = model.getIsPlaying();
        CastingDevice.Builder builderWithImageUrl = new CastingDevice.Builder(CastingCategory.fromString(model.getCategory()), model.getTargetId()).withDeviceTypeId(model.getDeviceTypeId()).withName(model.getTitle()).withIsPlaying(isPlaying == null ? false : isPlaying.booleanValue()).withImageUrl(model.getImageUrl());
        builderWithImageUrl.withOverridePlayback(false);
        this.castingManager.connect(builderWithImageUrl.build());
        view.showSpinner(true);
    }

    @Override // com.amazon.music.views.library.providers.CastingTileListenerProvider
    public void disconnectListener(CastingListItemHorizontalTileView view, CastingListItemHorizontalTileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        if (getIsLoading()) {
            return;
        }
        ActiveDevicePlayback activeDevicePlayback = ((ActiveDeviceMetadata) model.getMetadata()).getActiveDevicePlayback();
        String queueId = activeDevicePlayback == null ? null : activeDevicePlayback.getQueueId();
        if (queueId == null || queueId.length() == 0) {
            disconnect(view, model);
        } else {
            showDialog(view, model);
        }
    }

    @Override // com.amazon.music.views.library.providers.CastingTileListenerProvider
    public boolean isCasting(CastingListItemHorizontalTileModel model) {
        CastingDevice castingDevice;
        Intrinsics.checkNotNullParameter(model, "model");
        return this.castingManager.isConnected() && (castingDevice = this.castingManager.getCastingDevice()) != null && Intrinsics.areEqual(castingDevice.getTargetId(), model.getTargetId());
    }

    public final void disconnect(CastingListItemHorizontalTileView view, CastingListItemHorizontalTileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        this.castingManager.disconnect(!Intrinsics.areEqual(CastingCategory.CHROMECAST.getName(), model.getCategory()) ? TerminationReason.USER_DISCONNECT : null);
        view.showSpinner(true);
    }

    public final void showDialog(CastingListItemHorizontalTileView view, CastingListItemHorizontalTileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        CloudQueueCastingDisconnectConfirmDialog cloudQueueCastingDisconnectConfirmDialog = new CloudQueueCastingDisconnectConfirmDialog(view, model);
        FragmentTransaction fragmentTransactionBeginTransaction = ((ActiveDeviceMetadata) model.getMetadata()).getActivity().getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "model.metadata as Active…anager.beginTransaction()");
        fragmentTransactionBeginTransaction.add(cloudQueueCastingDisconnectConfirmDialog, this.DIALOG_NAME);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    /* JADX INFO: renamed from: isLoading, reason: from getter */
    private final boolean getIsLoading() {
        return this.isLoading;
    }
}
