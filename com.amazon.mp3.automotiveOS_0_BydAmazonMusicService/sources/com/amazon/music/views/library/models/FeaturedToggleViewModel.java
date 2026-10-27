package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.StateModel;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturedToggleViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u00012\u00020\u0002:\u0002%&Bs\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000f\u0012$\b\u0002\u0010\u0018\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0016j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0017\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b#\u0010$R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\b\u0010\u0005\u001a\u0004\b\t\u0010\u0007R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R3\u0010\u0018\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0016j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\"\u0010\u001d\u001a\u00020\u001c8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001d\u0010\u001f\"\u0004\b \u0010!¨\u0006'"}, d2 = {"Lcom/amazon/music/views/library/models/FeaturedToggleViewModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lcom/amazon/music/views/library/models/base/StateModel;", "", "labelText", "Ljava/lang/String;", "getLabelText", "()Ljava/lang/String;", ContextMappingConstants.PRIMARY_TEXT, "getPrimaryText", "Lcom/amazon/music/views/library/models/FeaturedToggleViewModel$ToggleType;", "toggleType", "Lcom/amazon/music/views/library/models/FeaturedToggleViewModel$ToggleType;", "getToggleType", "()Lcom/amazon/music/views/library/models/FeaturedToggleViewModel$ToggleType;", "Lcom/amazon/music/views/library/models/FeaturedToggleViewModel$ImageModel;", "primaryImage", "Lcom/amazon/music/views/library/models/FeaturedToggleViewModel$ImageModel;", "getPrimaryImage", "()Lcom/amazon/music/views/library/models/FeaturedToggleViewModel$ImageModel;", "secondaryImage", "getSecondaryImage", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "supplementaryContentInfo", "Ljava/util/HashMap;", "getSupplementaryContentInfo", "()Ljava/util/HashMap;", "", "isActive", "Z", "()Z", "setActive", "(Z)V", "viewId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/models/FeaturedToggleViewModel$ToggleType;Lcom/amazon/music/views/library/models/FeaturedToggleViewModel$ImageModel;Lcom/amazon/music/views/library/models/FeaturedToggleViewModel$ImageModel;Ljava/util/HashMap;Z)V", "ImageModel", "ToggleType", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class FeaturedToggleViewModel extends BaseViewModel implements StateModel {
    private boolean isActive;
    private final String labelText;
    private final ImageModel primaryImage;
    private final String primaryText;
    private final ImageModel secondaryImage;
    private final HashMap<String, String> supplementaryContentInfo;
    private final ToggleType toggleType;

    public final String getLabelText() {
        return this.labelText;
    }

    public final String getPrimaryText() {
        return this.primaryText;
    }

    public final ToggleType getToggleType() {
        return this.toggleType;
    }

    public final ImageModel getPrimaryImage() {
        return this.primaryImage;
    }

    public final ImageModel getSecondaryImage() {
        return this.secondaryImage;
    }

    public /* synthetic */ FeaturedToggleViewModel(String str, String str2, String str3, ToggleType toggleType, ImageModel imageModel, ImageModel imageModel2, HashMap map, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, str2, str3, toggleType, (i & 16) != 0 ? null : imageModel, (i & 32) != 0 ? null : imageModel2, (i & 64) != 0 ? new HashMap() : map, (i & 128) != 0 ? false : z);
    }

    public final HashMap<String, String> getSupplementaryContentInfo() {
        return this.supplementaryContentInfo;
    }

    /* JADX INFO: compiled from: FeaturedToggleViewModel.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/amazon/music/views/library/models/FeaturedToggleViewModel$ToggleType;", "", "identifier", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getIdentifier", "()Ljava/lang/String;", "ARTIST_COMMENTARY", "AAUP_SELECTION", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ToggleType {
        ARTIST_COMMENTARY("commentaryToggle"),
        AAUP_SELECTION("aaupSelectionToggle");

        private final String identifier;

        ToggleType(String str) {
            this.identifier = str;
        }

        public final String getIdentifier() {
            return this.identifier;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeaturedToggleViewModel(String str, String labelText, String primaryText, ToggleType toggleType, ImageModel imageModel, ImageModel imageModel2, HashMap<String, String> supplementaryContentInfo, boolean z) {
        super(str, null, 2, null);
        Intrinsics.checkNotNullParameter(labelText, "labelText");
        Intrinsics.checkNotNullParameter(primaryText, "primaryText");
        Intrinsics.checkNotNullParameter(toggleType, "toggleType");
        Intrinsics.checkNotNullParameter(supplementaryContentInfo, "supplementaryContentInfo");
        this.labelText = labelText;
        this.primaryText = primaryText;
        this.toggleType = toggleType;
        this.primaryImage = imageModel;
        this.secondaryImage = imageModel2;
        this.supplementaryContentInfo = supplementaryContentInfo;
        this.isActive = z;
    }

    @Override // com.amazon.music.views.library.models.base.StateModel
    public void setActive(boolean z) {
        this.isActive = z;
    }

    /* JADX INFO: compiled from: FeaturedToggleViewModel.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/amazon/music/views/library/models/FeaturedToggleViewModel$ImageModel;", "", ImagesContract.URL, "", "contentType", "", "(Ljava/lang/String;I)V", "getContentType", "()I", "placeholderResId", "getPlaceholderResId", "getUrl", "()Ljava/lang/String;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ImageModel {
        private final int contentType;
        private final String url;

        public ImageModel(String url, int i) {
            Intrinsics.checkNotNullParameter(url, "url");
            this.url = url;
            this.contentType = i;
        }

        public final String getUrl() {
            return this.url;
        }

        public final int getContentType() {
            return this.contentType;
        }

        public final int getPlaceholderResId() {
            int i = this.contentType;
            if (i == 0) {
                return R$drawable.album_placeholder;
            }
            if (i == 4) {
                return R$drawable.artist_placeholder;
            }
            return R$drawable.placeholder;
        }
    }
}
