package com.amazon.music.views.library.models.base;

import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryAddableModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001BW\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!\u0012\u0006\u0010#\u001a\u00020\u000f\u0012\u0006\u0010%\u001a\u00020$\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0002¢\u0006\u0004\b&\u0010'J!\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\b\u001a\u00020\u0005J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\tH\u0016J\u0013\u0010\u000e\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0016R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0003\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\"\u0010\u001c\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u0016\u001a\u0004\b\u001d\u0010\u0017\"\u0004\b\u001e\u0010\u0019R\"\u0010\u001f\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010\u0016\u001a\u0004\b\u001f\u0010\u0017\"\u0004\b \u0010\u0019¨\u0006("}, d2 = {"Lcom/amazon/music/views/library/models/base/LibraryAddableModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "", "isInLibrary", "shouldNotify", "", "updateLibraryState", "(ZLjava/lang/Boolean;)V", "notifyChangedLibraryState", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "newModelState", "checkForChanges", "", "other", "equals", "", "hashCode", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "Z", "()Z", "setInLibrary", "(Z)V", "isSuggestion", "setSuggestion", "enableAdd", "getEnableAdd", "setEnableAdd", "isLiked", "setLiked", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "artworkFrameModel", "contentType", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "<init>", "(Ljava/lang/String;Lcom/amazon/music/views/library/models/ArtworkFrameModel;ILcom/amazon/music/views/library/metadata/ContentMetadata;ZZZZ)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public abstract class LibraryAddableModel extends BaseViewContentModel {
    private boolean enableAdd;
    private boolean isInLibrary;
    private boolean isLiked;
    private boolean isSuggestion;
    private final String viewId;

    public /* synthetic */ LibraryAddableModel(String str, ArtworkFrameModel artworkFrameModel, int i, ContentMetadata contentMetadata, boolean z, boolean z2, boolean z3, boolean z4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : artworkFrameModel, i, contentMetadata, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? false : z2, (i2 & 64) != 0 ? false : z3, (i2 & 128) != 0 ? false : z4);
    }

    public final String getViewId() {
        return this.viewId;
    }

    /* JADX INFO: renamed from: isInLibrary, reason: from getter */
    public final boolean getIsInLibrary() {
        return this.isInLibrary;
    }

    /* JADX INFO: renamed from: isSuggestion, reason: from getter */
    public final boolean getIsSuggestion() {
        return this.isSuggestion;
    }

    public final boolean getEnableAdd() {
        return this.enableAdd;
    }

    public final void setEnableAdd(boolean z) {
        this.enableAdd = z;
    }

    /* JADX INFO: renamed from: isLiked, reason: from getter */
    public final boolean getIsLiked() {
        return this.isLiked;
    }

    public final void setLiked(boolean z) {
        this.isLiked = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LibraryAddableModel(String str, ArtworkFrameModel artworkFrameModel, int i, ContentMetadata metadata, boolean z, boolean z2, boolean z3, boolean z4) {
        super(str, metadata, i, artworkFrameModel, null, 16, null);
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.viewId = str;
        this.isInLibrary = z;
        this.isSuggestion = z2;
        this.enableAdd = z3;
        this.isLiked = z4;
    }

    public static /* synthetic */ void updateLibraryState$default(LibraryAddableModel libraryAddableModel, boolean z, Boolean bool, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateLibraryState");
        }
        if ((i & 2) != 0) {
            bool = Boolean.TRUE;
        }
        libraryAddableModel.updateLibraryState(z, bool);
    }

    public final void updateLibraryState(boolean isInLibrary, Boolean shouldNotify) {
        if (this.isInLibrary != isInLibrary) {
            this.isInLibrary = isInLibrary;
            ContentMetadata metadata = getMetadata();
            if (metadata instanceof TrackMetadata) {
                ((TrackMetadata) getMetadata()).setInLibrary(Boolean.valueOf(isInLibrary));
            } else if (metadata instanceof AlbumMetadata) {
                ((AlbumMetadata) getMetadata()).setInLibrary(Boolean.valueOf(isInLibrary));
            } else if (metadata instanceof PlaylistMetadata) {
                ((PlaylistMetadata) getMetadata()).setInLibrary(Boolean.valueOf(isInLibrary));
            }
            if (Intrinsics.areEqual(shouldNotify, Boolean.TRUE)) {
                notifyChanged(0);
            }
        }
    }

    public final void notifyChangedLibraryState() {
        notifyChanged(0);
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewContentModel, com.amazon.music.views.library.models.base.BaseViewModel
    public void checkForChanges(BaseViewModel newModelState) {
        Intrinsics.checkNotNullParameter(newModelState, "newModelState");
        super.checkForChanges(newModelState);
        if (newModelState instanceof LibraryAddableModel) {
            updateLibraryState$default(this, ((LibraryAddableModel) newModelState).isInLibrary, null, 2, null);
        }
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewContentModel, com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other == null ? null : other.getClass()) || !super.equals(other)) {
            return false;
        }
        if (other != null) {
            LibraryAddableModel libraryAddableModel = (LibraryAddableModel) other;
            return this.isInLibrary == libraryAddableModel.isInLibrary && this.enableAdd == libraryAddableModel.enableAdd;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.base.LibraryAddableModel");
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewContentModel, com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        return (((super.hashCode() * 31) + Boolean.hashCode(this.isInLibrary)) * 31) + Boolean.hashCode(this.enableAdd);
    }
}
