package com.amazon.music.views.library.models;

import androidx.core.app.FrameMetricsAggregator;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FeatureBarkerModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u00002\u00020\u0001Bo\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/views/library/models/FeatureBarkerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "viewId", "", "target", "mainText", "subText", "backgroundImageUrl16x9", "backgroundImageUrl4x1", "artworkFrameModel", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "altDescription", "insetHorizontally", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/models/ArtworkFrameModel;Ljava/lang/String;Z)V", "getAltDescription", "()Ljava/lang/String;", "getArtworkFrameModel", "()Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "getBackgroundImageUrl16x9", "getBackgroundImageUrl4x1", "getInsetHorizontally", "()Z", "getMainText", "getSubText", "getTarget", "getViewId", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeatureBarkerModel extends BaseViewModel {
    private final String altDescription;
    private final ArtworkFrameModel artworkFrameModel;
    private final String backgroundImageUrl16x9;
    private final String backgroundImageUrl4x1;
    private final boolean insetHorizontally;
    private final String mainText;
    private final String subText;
    private final String target;
    private final String viewId;

    public FeatureBarkerModel() {
        this(null, null, null, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    public /* synthetic */ FeatureBarkerModel(String str, String str2, String str3, String str4, String str5, String str6, ArtworkFrameModel artworkFrameModel, String str7, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : artworkFrameModel, (i & 128) == 0 ? str7 : null, (i & 256) != 0 ? false : z);
    }

    public final String getViewId() {
        return this.viewId;
    }

    public final String getTarget() {
        return this.target;
    }

    public final String getMainText() {
        return this.mainText;
    }

    public final String getSubText() {
        return this.subText;
    }

    public final String getBackgroundImageUrl16x9() {
        return this.backgroundImageUrl16x9;
    }

    public final String getBackgroundImageUrl4x1() {
        return this.backgroundImageUrl4x1;
    }

    public final ArtworkFrameModel getArtworkFrameModel() {
        return this.artworkFrameModel;
    }

    public final String getAltDescription() {
        return this.altDescription;
    }

    public FeatureBarkerModel(String str, String str2, String str3, String str4, String str5, String str6, ArtworkFrameModel artworkFrameModel, String str7, boolean z) {
        super(str, null, 2, 0 == true ? 1 : 0);
        this.viewId = str;
        this.target = str2;
        this.mainText = str3;
        this.subText = str4;
        this.backgroundImageUrl16x9 = str5;
        this.backgroundImageUrl4x1 = str6;
        this.artworkFrameModel = artworkFrameModel;
        this.altDescription = str7;
        this.insetHorizontally = z;
    }

    public final boolean getInsetHorizontally() {
        return this.insetHorizontally;
    }
}
