package com.amazon.mp3.playback.view.multiminiplayer;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.digitalmusicxp.types.ActiveDevicePlayback;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.contextmenu.ContextMenuProvider;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.optional.Optional;
import com.amazon.music.ui.model.castingtile.CastingTileModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CloudQueueCastingContextMenuProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u001e\u001a\u00020\u0018¢\u0006\u0004\b-\u0010.J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH\u0002J\b\u0010\u000b\u001a\u00020\u0003H\u0002J\b\u0010\r\u001a\u00020\fH\u0014J8\u0010\u0017\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0014J0\u0010\u001d\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0016R\u0017\u0010\u001e\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u00158\u0002X\u0082D¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010'\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010*R\u0016\u0010,\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010*¨\u0006/"}, d2 = {"Lcom/amazon/mp3/playback/view/multiminiplayer/CloudQueueCastingContextMenuProvider;", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuProvider;", "Lcom/amazon/mp3/playback/view/multiminiplayer/ActiveDeviceMetadata;", "", "override", "", "cast", "pullQueue", "Lcom/amazon/digitalmusicxp/types/ActiveDevicePlayback;", "activeDevicePlayback", "showConfirmDialog", "checkAlwaysMoveStream", "", "getContextMenuLayoutId", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/view/ContextMenu;", "menu", "", ParserUtil.TAG_QUERY_PARAM_NAME, "position", "", "sourceID", "updateContextMenuItems", "Landroidx/fragment/app/FragmentActivity;", "Landroid/view/MenuItem;", "item", "Landroid/widget/AdapterView$AdapterContextMenuInfo;", "menuInfo", "onContextMenuItemSelected", "fragmentActivity", "Landroidx/fragment/app/FragmentActivity;", "getFragmentActivity", "()Landroidx/fragment/app/FragmentActivity;", "DIALOG_NAME", "Ljava/lang/String;", "Lcom/amazon/music/casting/session/CastingSessionManager;", "castingManager", "Lcom/amazon/music/casting/session/CastingSessionManager;", "activeDeviceMetadata", "Lcom/amazon/mp3/playback/view/multiminiplayer/ActiveDeviceMetadata;", "isCastable", "Z", "isActive", "isPlaying", "<init>", "(Landroidx/fragment/app/FragmentActivity;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CloudQueueCastingContextMenuProvider extends ContextMenuProvider<ActiveDeviceMetadata> {
    private final String DIALOG_NAME;
    private ActiveDeviceMetadata activeDeviceMetadata;
    private final CastingSessionManager castingManager;
    private final FragmentActivity fragmentActivity;
    private boolean isActive;
    private boolean isCastable;
    private boolean isPlaying;

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.cloudqueue_casting_menu;
    }

    public CloudQueueCastingContextMenuProvider(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        this.fragmentActivity = fragmentActivity;
        this.DIALOG_NAME = "CloudQueuePullConfirmDialog";
        CastingSessionManager castingSessionManager = CastingSessionManager.getInstance();
        Intrinsics.checkNotNullExpressionValue(castingSessionManager, "getInstance()");
        this.castingManager = castingSessionManager;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        Boolean isCastable;
        Boolean isActive;
        Boolean isPlaying;
        if (tag != null) {
            ActiveDeviceMetadata activeDeviceMetadata = (ActiveDeviceMetadata) tag;
            this.activeDeviceMetadata = activeDeviceMetadata;
            ActiveDevicePlayback activeDevicePlayback = activeDeviceMetadata.getActiveDevicePlayback();
            if (menu != null) {
                menu.setHeaderTitle(activeDevicePlayback == null ? null : activeDevicePlayback.getDeviceName());
            }
            ActiveDeviceMetadata activeDeviceMetadata2 = this.activeDeviceMetadata;
            boolean z = false;
            this.isCastable = (activeDeviceMetadata2 == null || (isCastable = activeDeviceMetadata2.getIsCastable()) == null) ? false : isCastable.booleanValue();
            ActiveDeviceMetadata activeDeviceMetadata3 = this.activeDeviceMetadata;
            this.isActive = (activeDeviceMetadata3 == null || (isActive = activeDeviceMetadata3.getIsActive()) == null) ? false : isActive.booleanValue();
            ActiveDeviceMetadata activeDeviceMetadata4 = this.activeDeviceMetadata;
            this.isPlaying = (activeDeviceMetadata4 == null || (isPlaying = activeDeviceMetadata4.getIsPlaying()) == null) ? false : isPlaying.booleanValue();
            MenuItem menuItemFindItem = menu == null ? null : menu.findItem(R.id.MenuControlPlayback);
            if (menuItemFindItem != null) {
                if (this.isCastable && this.isPlaying) {
                    z = true;
                }
                menuItemFindItem.setVisible(z);
            }
            MenuItem menuItemFindItem2 = menu == null ? null : menu.findItem(R.id.MenuConnectToPlay);
            if (menuItemFindItem2 != null) {
                menuItemFindItem2.setVisible(this.isCastable);
            }
            MenuItem menuItemFindItem3 = menu != null ? menu.findItem(R.id.MenuTransfer) : null;
            if (menuItemFindItem3 == null) {
                return;
            }
            menuItemFindItem3.setVisible(this.isActive);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.playback.view.multiminiplayer.ActiveDeviceMetadata");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        if (item == null) {
            return false;
        }
        switch (item.getItemId()) {
            case R.id.MenuConnectToPlay /* 2131427572 */:
                cast(true);
                return true;
            case R.id.MenuControlPlayback /* 2131427573 */:
                cast(false);
                return true;
            case R.id.MenuTransfer /* 2131427669 */:
                pullQueue();
                return true;
            default:
                return true;
        }
    }

    private final void cast(boolean override) {
        Optional<String> optional;
        Optional<String> optional2;
        Optional<String> optional3;
        Optional<String> optional4;
        Optional<String> optional5;
        Optional<Boolean> optional6;
        if (this.castingManager.isConnected()) {
            return;
        }
        ActiveDeviceMetadata activeDeviceMetadata = this.activeDeviceMetadata;
        String str = null;
        CastingTileModel castingTileModel = activeDeviceMetadata == null ? null : activeDeviceMetadata.getCastingTileModel();
        boolean zIsPresent = false;
        if (castingTileModel != null && (optional6 = castingTileModel.isPlaying) != null) {
            zIsPresent = optional6.isPresent();
        }
        CastingDevice.Builder builderWithIsPlaying = new CastingDevice.Builder(CastingCategory.fromString((castingTileModel == null || (optional = castingTileModel.category) == null) ? null : optional.get()), (castingTileModel == null || (optional2 = castingTileModel.targetId) == null) ? null : optional2.get()).withDeviceTypeId((castingTileModel == null || (optional3 = castingTileModel.deviceTypeId) == null) ? null : optional3.get()).withName((castingTileModel == null || (optional4 = castingTileModel.title) == null) ? null : optional4.get()).withIsPlaying(zIsPresent);
        if (castingTileModel != null && (optional5 = castingTileModel.imageUrl) != null) {
            str = optional5.get();
        }
        CastingDevice.Builder builderWithImageUrl = builderWithIsPlaying.withImageUrl(str);
        builderWithImageUrl.withOverridePlayback(override);
        this.castingManager.connect(builderWithImageUrl.build());
    }

    private final void pullQueue() {
        ActiveDevicePlayback activeDevicePlayback;
        ActiveDeviceMetadata activeDeviceMetadata = this.activeDeviceMetadata;
        if (activeDeviceMetadata == null || (activeDevicePlayback = activeDeviceMetadata.getActiveDevicePlayback()) == null) {
            return;
        }
        showConfirmDialog(activeDevicePlayback);
    }

    private final void showConfirmDialog(ActiveDevicePlayback activeDevicePlayback) {
        if (checkAlwaysMoveStream()) {
            CloudQueueMiniPlayerController.INSTANCE.callInitiateQueue(activeDevicePlayback, this.fragmentActivity);
            return;
        }
        CloudQueuePullConfirmDialog cloudQueuePullConfirmDialog = new CloudQueuePullConfirmDialog(activeDevicePlayback);
        FragmentTransaction fragmentTransactionBeginTransaction = this.fragmentActivity.getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "fragmentActivity.support…anager.beginTransaction()");
        fragmentTransactionBeginTransaction.add(cloudQueuePullConfirmDialog, this.DIALOG_NAME);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    private final boolean checkAlwaysMoveStream() {
        return this.fragmentActivity.getPreferences(0).getBoolean(CloudQueueMiniPlayerController.INSTANCE.getALWAYS_MOVE_STREAM(), false);
    }
}
