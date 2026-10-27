package com.amazon.music.views.library.models;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CollectionDetailHeaderModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001Bß\u0001\u0012\b\u0010C\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0002\u0012\u000e\b\u0002\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u00106\u001a\u000205\u0012\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010<\u0012\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\bD\u0010ER\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R$\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006\"\u0004\b\t\u0010\nR\u001c\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0004\u001a\u0004\b\u0011\u0010\u0006\"\u0004\b\u0012\u0010\nR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u000b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0013\u0010\r\u001a\u0004\b\u0014\u0010\u000fR$\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0004\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\nR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0004\u001a\u0004\b\u0019\u0010\u0006R$\u0010\u001a\u001a\u0004\u0018\u00010\u00028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0004\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\nR(\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010&\u001a\u0004\u0018\u00010%8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R$\u0010+\u001a\u0004\u0018\u00010*8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0019\u00101\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b1\u0010\u0004\u001a\u0004\b2\u0010\u0006R\u0019\u00103\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b3\u0010\u0004\u001a\u0004\b4\u0010\u0006R\u0017\u00106\u001a\u0002058\u0006¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\u001c\u0010:\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b:\u0010\u0004\u001a\u0004\b;\u0010\u0006R\u001c\u0010=\u001a\u0004\u0018\u00010<8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R\u001c\u0010A\u001a\u0004\u0018\u00010\u000b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bA\u0010\r\u001a\u0004\bB\u0010\u000f¨\u0006F"}, d2 = {"Lcom/amazon/music/views/library/models/CollectionDetailHeaderModel;", "Lcom/amazon/music/views/library/models/PageHeaderModel;", "", "labelText", "Ljava/lang/String;", "getLabelText", "()Ljava/lang/String;", "headlineTitle", "getHeadlineTitle", "setHeadlineTitle", "(Ljava/lang/String;)V", "", "headlineTitleId", "Ljava/lang/Integer;", "getHeadlineTitleId", "()Ljava/lang/Integer;", "primaryTitle", "getPrimaryTitle", "setPrimaryTitle", "primaryTitleViewId", "getPrimaryTitleViewId", "secondaryTitle", "getSecondaryTitle", "setSecondaryTitle", "tertiaryTitle", "getTertiaryTitle", "text4", "getText4", "setText4", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel;", "actionIcons", "Ljava/util/List;", "getActionIcons", "()Ljava/util/List;", "setActionIcons", "(Ljava/util/List;)V", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getMetadata", "()Lcom/amazon/music/views/library/metadata/ContentMetadata;", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "downloadStateModel", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "getDownloadStateModel", "()Lcom/amazon/music/views/library/models/DownloadStateModel;", "setDownloadStateModel", "(Lcom/amazon/music/views/library/models/DownloadStateModel;)V", "artworkUrl", "getArtworkUrl", "curatedByProfileId", "getCuratedByProfileId", "", "showDownloadTextButotn", "Z", "getShowDownloadTextButotn", "()Z", "labelBadgeText", "getLabelBadgeText", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "fontStyle", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "getFontStyle", "()Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "badgeBackground", "getBadgeBackground", ContentUtils.KEY_VIEW_REFERENCE_ID, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/amazon/music/views/library/metadata/ContentMetadata;Lcom/amazon/music/views/library/models/DownloadStateModel;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Lcom/amazon/music/views/library/styles/keys/FontStyleKey;Ljava/lang/Integer;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class CollectionDetailHeaderModel extends PageHeaderModel {
    private List<PageHeaderActionIconModel> actionIcons;
    private final String artworkUrl;
    private final Integer badgeBackground;
    private final String curatedByProfileId;
    private DownloadStateModel downloadStateModel;
    private final FontStyleKey fontStyle;
    private String headlineTitle;
    private final Integer headlineTitleId;
    private final String labelBadgeText;
    private final String labelText;
    private final ContentMetadata metadata;
    private String primaryTitle;
    private final Integer primaryTitleViewId;
    private String secondaryTitle;
    private final boolean showDownloadTextButotn;
    private final String tertiaryTitle;
    private String text4;

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public String getLabelText() {
        return this.labelText;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public String getHeadlineTitle() {
        return this.headlineTitle;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public void setHeadlineTitle(String str) {
        this.headlineTitle = str;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public Integer getHeadlineTitleId() {
        return this.headlineTitleId;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public String getPrimaryTitle() {
        return this.primaryTitle;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public Integer getPrimaryTitleViewId() {
        return this.primaryTitleViewId;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public String getSecondaryTitle() {
        return this.secondaryTitle;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public void setSecondaryTitle(String str) {
        this.secondaryTitle = str;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public String getTertiaryTitle() {
        return this.tertiaryTitle;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public String getText4() {
        return this.text4;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public void setText4(String str) {
        this.text4 = str;
    }

    public /* synthetic */ CollectionDetailHeaderModel(String str, String str2, String str3, Integer num, String str4, Integer num2, String str5, String str6, String str7, List list, ContentMetadata contentMetadata, DownloadStateModel downloadStateModel, String str8, String str9, boolean z, String str10, FontStyleKey fontStyleKey, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : num2, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : str7, (i & 512) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, (i & 1024) != 0 ? null : contentMetadata, (i & 2048) != 0 ? null : downloadStateModel, (i & 4096) != 0 ? null : str8, (i & 8192) != 0 ? null : str9, (i & 16384) != 0 ? false : z, (i & 32768) != 0 ? null : str10, (i & 65536) != 0 ? null : fontStyleKey, (i & 131072) == 0 ? num3 : null);
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public List<PageHeaderActionIconModel> getActionIcons() {
        return this.actionIcons;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public void setActionIcons(List<PageHeaderActionIconModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.actionIcons = list;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public ContentMetadata getMetadata() {
        return this.metadata;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public DownloadStateModel getDownloadStateModel() {
        return this.downloadStateModel;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public void setDownloadStateModel(DownloadStateModel downloadStateModel) {
        this.downloadStateModel = downloadStateModel;
    }

    public final String getArtworkUrl() {
        return this.artworkUrl;
    }

    public final String getCuratedByProfileId() {
        return this.curatedByProfileId;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public String getLabelBadgeText() {
        return this.labelBadgeText;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public FontStyleKey getFontStyle() {
        return this.fontStyle;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public Integer getBadgeBackground() {
        return this.badgeBackground;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionDetailHeaderModel(String str, String str2, String str3, Integer num, String str4, Integer num2, String str5, String str6, String str7, List<PageHeaderActionIconModel> actionIcons, ContentMetadata contentMetadata, DownloadStateModel downloadStateModel, String str8, String str9, boolean z, String str10, FontStyleKey fontStyleKey, Integer num3) {
        super(str);
        Intrinsics.checkNotNullParameter(actionIcons, "actionIcons");
        this.labelText = str2;
        this.headlineTitle = str3;
        this.headlineTitleId = num;
        this.primaryTitle = str4;
        this.primaryTitleViewId = num2;
        this.secondaryTitle = str5;
        this.tertiaryTitle = str6;
        this.text4 = str7;
        this.actionIcons = actionIcons;
        this.metadata = contentMetadata;
        this.downloadStateModel = downloadStateModel;
        this.artworkUrl = str8;
        this.curatedByProfileId = str9;
        this.showDownloadTextButotn = z;
        this.labelBadgeText = str10;
        this.fontStyle = fontStyleKey;
        this.badgeBackground = num3;
    }
}
