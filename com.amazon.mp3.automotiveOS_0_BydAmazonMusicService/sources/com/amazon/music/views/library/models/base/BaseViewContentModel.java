package com.amazon.music.views.library.models.base;

import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.EligibilityMetadata;
import com.amazon.music.views.library.metadata.UpdatableContentMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewContentModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B;\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010+\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0018\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b-\u0010.J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0001H\u0016J\u0013\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0004H\u0016J\u0012\u0010\u0012\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R$\u0010!\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0016\u0010*\u001a\u0004\u0018\u00010'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006/"}, d2 = {"Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lcom/amazon/mp3/library/refinement/Refinable;", "Lcom/amazon/music/views/library/models/base/Eligible;", "", "newPlayState", "", "updatePlayState", "Lcom/amazon/music/views/library/models/base/ContentEligibilityData;", "eligibilityData", "updateEligibilityData", "newModelState", "checkForChanges", "", "other", "", "equals", "hashCode", "areItemsTheSame", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getMetadata", "()Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentType", "I", "getContentType", "()I", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "artworkFrameModel", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "getArtworkFrameModel", "()Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "playState", "Ljava/lang/Integer;", "getPlayState", "()Ljava/lang/Integer;", "setPlayState", "(Ljava/lang/Integer;)V", "Lcom/amazon/music/views/library/metadata/EligibilityMetadata;", "getEligibilityMetadata", "()Lcom/amazon/music/views/library/metadata/EligibilityMetadata;", "eligibilityMetadata", "", "viewId", "<init>", "(Ljava/lang/String;Lcom/amazon/music/views/library/metadata/ContentMetadata;ILcom/amazon/music/views/library/models/ArtworkFrameModel;Ljava/lang/Integer;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public abstract class BaseViewContentModel extends BaseViewModel implements Refinable, Eligible {
    private final ArtworkFrameModel artworkFrameModel;
    private final int contentType;
    private final ContentMetadata metadata;
    private Integer playState;

    public /* synthetic */ BaseViewContentModel(String str, ContentMetadata contentMetadata, int i, ArtworkFrameModel artworkFrameModel, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, contentMetadata, i, (i2 & 8) != 0 ? null : artworkFrameModel, (i2 & 16) != 0 ? null : num);
    }

    public final ContentMetadata getMetadata() {
        return this.metadata;
    }

    public final int getContentType() {
        return this.contentType;
    }

    public final ArtworkFrameModel getArtworkFrameModel() {
        return this.artworkFrameModel;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseViewContentModel(String str, ContentMetadata metadata, int i, ArtworkFrameModel artworkFrameModel, Integer num) {
        super(str, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.metadata = metadata;
        this.contentType = i;
        this.artworkFrameModel = artworkFrameModel;
        this.playState = num;
    }

    public final Integer getPlayState() {
        return this.playState;
    }

    public void updatePlayState(int newPlayState) {
        Integer num = this.playState;
        if (num != null && num.intValue() == newPlayState) {
            ArtworkFrameModel artworkFrameModel = this.artworkFrameModel;
            if (artworkFrameModel == null) {
                return;
            }
            Integer playState = artworkFrameModel.getPlayState();
            if (playState != null && playState.intValue() == newPlayState) {
                return;
            }
        }
        this.playState = Integer.valueOf(newPlayState);
        ArtworkFrameModel artworkFrameModel2 = this.artworkFrameModel;
        if (artworkFrameModel2 != null) {
            artworkFrameModel2.updatePlayState$DMMViewLibrary_release(newPlayState);
        }
        notifyChanged(1);
    }

    @Override // com.amazon.music.views.library.models.base.Eligible
    public EligibilityMetadata getEligibilityMetadata() {
        ContentMetadata contentMetadata = this.metadata;
        if (contentMetadata instanceof EligibilityMetadata) {
            return (EligibilityMetadata) contentMetadata;
        }
        return null;
    }

    @Override // com.amazon.music.views.library.models.base.Eligible
    public void updateEligibilityData(ContentEligibilityData eligibilityData) {
        Integer numUpdateEligibilityData;
        Intrinsics.checkNotNullParameter(eligibilityData, "eligibilityData");
        EligibilityMetadata eligibilityMetadata = getEligibilityMetadata();
        if (eligibilityMetadata == null || (numUpdateEligibilityData = eligibilityMetadata.updateEligibilityData(eligibilityData)) == null) {
            return;
        }
        numUpdateEligibilityData.intValue();
        notifyChanged(numUpdateEligibilityData.intValue());
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public void checkForChanges(BaseViewModel newModelState) {
        Intrinsics.checkNotNullParameter(newModelState, "newModelState");
        super.checkForChanges(newModelState);
        BaseViewContentModel baseViewContentModel = newModelState instanceof BaseViewContentModel ? (BaseViewContentModel) newModelState : null;
        if (baseViewContentModel == null) {
            return;
        }
        Integer playState = baseViewContentModel.getPlayState();
        if (playState != null) {
            updatePlayState(playState.intValue());
        }
        if (getMetadata() instanceof UpdatableContentMetadata) {
            ((UpdatableContentMetadata) getMetadata()).update(baseViewContentModel.getMetadata());
        }
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other == null ? null : other.getClass()) || !super.equals(other)) {
            return false;
        }
        if (other != null) {
            BaseViewContentModel baseViewContentModel = (BaseViewContentModel) other;
            return Intrinsics.areEqual(this.metadata, baseViewContentModel.metadata) && this.contentType == baseViewContentModel.contentType && Intrinsics.areEqual(this.artworkFrameModel, baseViewContentModel.artworkFrameModel) && Intrinsics.areEqual(this.playState, baseViewContentModel.playState);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.base.BaseViewContentModel");
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        return (super.hashCode() * 31) + this.metadata.hashCode();
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean areItemsTheSame(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            BaseViewContentModel baseViewContentModel = (BaseViewContentModel) other;
            ContentMetadata contentMetadata = this.metadata;
            if (!(contentMetadata instanceof UpdatableContentMetadata)) {
                return Intrinsics.areEqual(contentMetadata.getUri(), baseViewContentModel.metadata.getUri()) && Intrinsics.areEqual(this.metadata.getViewId(), baseViewContentModel.metadata.getViewId());
            }
            UpdatableContentMetadata updatableContentMetadata = contentMetadata instanceof UpdatableContentMetadata ? (UpdatableContentMetadata) contentMetadata : null;
            return updatableContentMetadata != null && updatableContentMetadata.areItemsTheSame(baseViewContentModel.metadata);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.base.BaseViewContentModel");
    }
}
