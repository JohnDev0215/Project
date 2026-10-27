package com.amazon.mp3.playback.view.multiminiplayer;

import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.digitalmusicxp.types.ActiveDevicePlayback;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.ui.model.castingtile.CastingTileModel;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: ActiveDeviceMetadata.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001R\"\u0010\u0003\u001a\u00020\u00028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\n\u001a\u0004\u0018\u00010\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR*\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u000e8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0016\u0010\u0018R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00158\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0017\u001a\u0004\b\u0019\u0010\u0018R\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00158\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u0017\u001a\u0004\b\u001a\u0010\u0018R\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0019\u0010!\u001a\u0004\u0018\u00010 8\u0006¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010&\u001a\u00020%8\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)¨\u0006*"}, d2 = {"Lcom/amazon/mp3/playback/view/multiminiplayer/ActiveDeviceMetadata;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "", "tags", "Ljava/util/List;", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "", "isCastable", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "isActive", "isPlaying", "Lcom/amazon/music/ui/model/castingtile/CastingTileModel;", "castingTileModel", "Lcom/amazon/music/ui/model/castingtile/CastingTileModel;", "getCastingTileModel", "()Lcom/amazon/music/ui/model/castingtile/CastingTileModel;", "Lcom/amazon/digitalmusicxp/types/ActiveDevicePlayback;", "activeDevicePlayback", "Lcom/amazon/digitalmusicxp/types/ActiveDevicePlayback;", "getActiveDevicePlayback", "()Lcom/amazon/digitalmusicxp/types/ActiveDevicePlayback;", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ActiveDeviceMetadata implements ContentMetadata {
    private final ActiveDevicePlayback activeDevicePlayback;
    private final FragmentActivity activity;
    private final CastingTileModel castingTileModel;
    private final Boolean isActive;
    private final Boolean isCastable;
    private final Boolean isPlaying;
    private List<String> tags;
    private Uri uri;
    private final String viewId;

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public Uri getUri() {
        return this.uri;
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public String getViewId() {
        return this.viewId;
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public List<String> getTags() {
        return this.tags;
    }

    /* JADX INFO: renamed from: isCastable, reason: from getter */
    public final Boolean getIsCastable() {
        return this.isCastable;
    }

    /* JADX INFO: renamed from: isActive, reason: from getter */
    public final Boolean getIsActive() {
        return this.isActive;
    }

    /* JADX INFO: renamed from: isPlaying, reason: from getter */
    public final Boolean getIsPlaying() {
        return this.isPlaying;
    }

    public final CastingTileModel getCastingTileModel() {
        return this.castingTileModel;
    }

    public final ActiveDevicePlayback getActiveDevicePlayback() {
        return this.activeDevicePlayback;
    }

    public final FragmentActivity getActivity() {
        return this.activity;
    }
}
