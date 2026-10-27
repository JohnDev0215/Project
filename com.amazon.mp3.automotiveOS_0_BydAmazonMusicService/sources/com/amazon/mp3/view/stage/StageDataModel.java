package com.amazon.mp3.view.stage;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.subjects.BehaviorSubject;

/* JADX INFO: compiled from: StageDataModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0086\b\u0018\u00002\u00020\u0001:\u00014B\u0089\u0001\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\u0010\u001a\u00020\t\u0012\b\b\u0002\u0010\u0012\u001a\u00020\t\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\u000e\b\u0002\u0010&\u001a\b\u0012\u0004\u0012\u00020%0$\u0012\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020%0$\u0012\u000e\b\u0002\u0010.\u001a\b\u0012\u0004\u0012\u00020%0$\u0012\u000e\b\u0002\u00100\u001a\b\u0012\u0004\u0012\u00020%0$¢\u0006\u0004\b2\u00103J\u0006\u0010\u0003\u001a\u00020\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\u000b\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\f\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\"\u0010\u0012\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR$\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0013\u0010\u0019R$\u0010\u001a\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u0019R$\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R(\u0010&\u001a\b\u0012\u0004\u0012\u00020%0$8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001d\u0010,\u001a\b\u0012\u0004\u0012\u00020%0$8\u0006¢\u0006\f\n\u0004\b,\u0010'\u001a\u0004\b-\u0010)R\u001d\u0010.\u001a\b\u0012\u0004\u0012\u00020%0$8\u0006¢\u0006\f\n\u0004\b.\u0010'\u001a\u0004\b/\u0010)R\u001d\u00100\u001a\b\u0012\u0004\u0012\u00020%0$8\u0006¢\u0006\f\n\u0004\b0\u0010'\u001a\u0004\b1\u0010)¨\u00065"}, d2 = {"Lcom/amazon/mp3/view/stage/StageDataModel;", "", "", "reset", "", "toString", "", "hashCode", "other", "", "equals", "isLyrics", "Z", "()Z", "setLyrics", "(Z)V", "isXray", "setXray", "isArtistImage", "setArtistImage", "Landroid/graphics/drawable/Drawable;", "artistImage", "Landroid/graphics/drawable/Drawable;", "getArtistImage", "()Landroid/graphics/drawable/Drawable;", "(Landroid/graphics/drawable/Drawable;)V", "blurArtistImage", "getBlurArtistImage", "setBlurArtistImage", "Landroid/graphics/Bitmap;", "blurAlbumArt", "Landroid/graphics/Bitmap;", "getBlurAlbumArt", "()Landroid/graphics/Bitmap;", "setBlurAlbumArt", "(Landroid/graphics/Bitmap;)V", "Lrx/subjects/BehaviorSubject;", "Lcom/amazon/mp3/view/stage/StageDataModel$State;", "albumArtAvailability", "Lrx/subjects/BehaviorSubject;", "getAlbumArtAvailability", "()Lrx/subjects/BehaviorSubject;", "setAlbumArtAvailability", "(Lrx/subjects/BehaviorSubject;)V", "lyricsAvailability", "getLyricsAvailability", "xRayAvailability", "getXRayAvailability", "artistImageAvailability", "getArtistImageAvailability", "<init>", "(ZZZLandroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/Bitmap;Lrx/subjects/BehaviorSubject;Lrx/subjects/BehaviorSubject;Lrx/subjects/BehaviorSubject;Lrx/subjects/BehaviorSubject;)V", "State", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class StageDataModel {
    private BehaviorSubject<State> albumArtAvailability;
    private Drawable artistImage;
    private final BehaviorSubject<State> artistImageAvailability;
    private Bitmap blurAlbumArt;
    private Drawable blurArtistImage;
    private boolean isArtistImage;
    private boolean isLyrics;
    private boolean isXray;
    private final BehaviorSubject<State> lyricsAvailability;
    private final BehaviorSubject<State> xRayAvailability;

    /* JADX INFO: compiled from: StageDataModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/view/stage/StageDataModel$State;", "", "(Ljava/lang/String;I)V", "UNINITIALIZED", "AVAILABLE", "UNAVAILABLE", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum State {
        UNINITIALIZED,
        AVAILABLE,
        UNAVAILABLE
    }

    public StageDataModel() {
        this(false, false, false, null, null, null, null, null, null, null, 1023, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StageDataModel)) {
            return false;
        }
        StageDataModel stageDataModel = (StageDataModel) other;
        return this.isLyrics == stageDataModel.isLyrics && this.isXray == stageDataModel.isXray && this.isArtistImage == stageDataModel.isArtistImage && Intrinsics.areEqual(this.artistImage, stageDataModel.artistImage) && Intrinsics.areEqual(this.blurArtistImage, stageDataModel.blurArtistImage) && Intrinsics.areEqual(this.blurAlbumArt, stageDataModel.blurAlbumArt) && Intrinsics.areEqual(this.albumArtAvailability, stageDataModel.albumArtAvailability) && Intrinsics.areEqual(this.lyricsAvailability, stageDataModel.lyricsAvailability) && Intrinsics.areEqual(this.xRayAvailability, stageDataModel.xRayAvailability) && Intrinsics.areEqual(this.artistImageAvailability, stageDataModel.artistImageAvailability);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    public int hashCode() {
        boolean z = this.isLyrics;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.isXray;
        ?? r2 = z2;
        if (z2) {
            r2 = 1;
        }
        int i2 = (i + r2) * 31;
        boolean z3 = this.isArtistImage;
        int i3 = (i2 + (z3 ? 1 : z3)) * 31;
        Drawable drawable = this.artistImage;
        int iHashCode = (i3 + (drawable == null ? 0 : drawable.hashCode())) * 31;
        Drawable drawable2 = this.blurArtistImage;
        int iHashCode2 = (iHashCode + (drawable2 == null ? 0 : drawable2.hashCode())) * 31;
        Bitmap bitmap = this.blurAlbumArt;
        return ((((((((iHashCode2 + (bitmap != null ? bitmap.hashCode() : 0)) * 31) + this.albumArtAvailability.hashCode()) * 31) + this.lyricsAvailability.hashCode()) * 31) + this.xRayAvailability.hashCode()) * 31) + this.artistImageAvailability.hashCode();
    }

    public StageDataModel(boolean z, boolean z2, boolean z3, Drawable drawable, Drawable drawable2, Bitmap bitmap, BehaviorSubject<State> albumArtAvailability, BehaviorSubject<State> lyricsAvailability, BehaviorSubject<State> xRayAvailability, BehaviorSubject<State> artistImageAvailability) {
        Intrinsics.checkNotNullParameter(albumArtAvailability, "albumArtAvailability");
        Intrinsics.checkNotNullParameter(lyricsAvailability, "lyricsAvailability");
        Intrinsics.checkNotNullParameter(xRayAvailability, "xRayAvailability");
        Intrinsics.checkNotNullParameter(artistImageAvailability, "artistImageAvailability");
        this.isLyrics = z;
        this.isXray = z2;
        this.isArtistImage = z3;
        this.artistImage = drawable;
        this.blurArtistImage = drawable2;
        this.blurAlbumArt = bitmap;
        this.albumArtAvailability = albumArtAvailability;
        this.lyricsAvailability = lyricsAvailability;
        this.xRayAvailability = xRayAvailability;
        this.artistImageAvailability = artistImageAvailability;
    }

    /* JADX INFO: renamed from: isLyrics, reason: from getter */
    public final boolean getIsLyrics() {
        return this.isLyrics;
    }

    public final void setLyrics(boolean z) {
        this.isLyrics = z;
    }

    /* JADX INFO: renamed from: isXray, reason: from getter */
    public final boolean getIsXray() {
        return this.isXray;
    }

    public final void setXray(boolean z) {
        this.isXray = z;
    }

    /* JADX INFO: renamed from: isArtistImage, reason: from getter */
    public final boolean getIsArtistImage() {
        return this.isArtistImage;
    }

    public final void setArtistImage(boolean z) {
        this.isArtistImage = z;
    }

    public final void setArtistImage(Drawable drawable) {
        this.artistImage = drawable;
    }

    public final void setBlurArtistImage(Drawable drawable) {
        this.blurArtistImage = drawable;
    }

    public final Bitmap getBlurAlbumArt() {
        return this.blurAlbumArt;
    }

    public final void setBlurAlbumArt(Bitmap bitmap) {
        this.blurAlbumArt = bitmap;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ StageDataModel(boolean z, boolean z2, boolean z3, Drawable drawable, Drawable drawable2, Bitmap bitmap, BehaviorSubject behaviorSubject, BehaviorSubject behaviorSubject2, BehaviorSubject behaviorSubject3, BehaviorSubject behaviorSubject4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        BehaviorSubject behaviorSubjectCreate;
        BehaviorSubject behaviorSubjectCreate2;
        BehaviorSubject behaviorSubjectCreate3;
        BehaviorSubject behaviorSubjectCreate4;
        boolean z4 = (i & 1) != 0 ? false : z;
        boolean z5 = (i & 2) != 0 ? false : z2;
        boolean z6 = (i & 4) == 0 ? z3 : false;
        Drawable drawable3 = (i & 8) != 0 ? null : drawable;
        Drawable drawable4 = (i & 16) != 0 ? null : drawable2;
        Bitmap bitmap2 = (i & 32) == 0 ? bitmap : null;
        if ((i & 64) != 0) {
            behaviorSubjectCreate = BehaviorSubject.create(State.UNINITIALIZED);
            Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate, "create(State.UNINITIALIZED)");
        } else {
            behaviorSubjectCreate = behaviorSubject;
        }
        if ((i & 128) != 0) {
            behaviorSubjectCreate2 = BehaviorSubject.create(State.UNINITIALIZED);
            Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate2, "create(State.UNINITIALIZED)");
        } else {
            behaviorSubjectCreate2 = behaviorSubject2;
        }
        if ((i & 256) != 0) {
            behaviorSubjectCreate3 = BehaviorSubject.create(State.UNINITIALIZED);
            Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate3, "create(State.UNINITIALIZED)");
        } else {
            behaviorSubjectCreate3 = behaviorSubject3;
        }
        if ((i & 512) != 0) {
            behaviorSubjectCreate4 = BehaviorSubject.create(State.UNINITIALIZED);
            Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate4, "create(State.UNINITIALIZED)");
        } else {
            behaviorSubjectCreate4 = behaviorSubject4;
        }
        this(z4, z5, z6, drawable3, drawable4, bitmap2, behaviorSubjectCreate, behaviorSubjectCreate2, behaviorSubjectCreate3, behaviorSubjectCreate4);
    }

    public final BehaviorSubject<State> getAlbumArtAvailability() {
        return this.albumArtAvailability;
    }

    public final BehaviorSubject<State> getLyricsAvailability() {
        return this.lyricsAvailability;
    }

    public final BehaviorSubject<State> getXRayAvailability() {
        return this.xRayAvailability;
    }

    public final BehaviorSubject<State> getArtistImageAvailability() {
        return this.artistImageAvailability;
    }

    public final void reset() {
        this.isLyrics = false;
        this.isXray = false;
        this.isArtistImage = false;
        this.artistImage = null;
        this.blurArtistImage = null;
        this.blurAlbumArt = null;
        BehaviorSubject<State> behaviorSubject = this.lyricsAvailability;
        State state = State.UNINITIALIZED;
        behaviorSubject.onNext(state);
        this.xRayAvailability.onNext(state);
        this.artistImageAvailability.onNext(state);
        this.albumArtAvailability.onNext(state);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ isLyrics - ");
        sb.append(this.isLyrics);
        sb.append(", isXray - ");
        sb.append(this.isXray);
        sb.append(", isArtistImage - ");
        sb.append(this.isArtistImage);
        sb.append(", isBlurAlbumArt - ");
        sb.append(this.blurAlbumArt != null);
        sb.append(" ]");
        return sb.toString();
    }
}
