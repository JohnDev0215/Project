package com.amazon.music.views.library.models;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultPageHeaderModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0016\u0018\u00002\u00020\u0001B}\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012R \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0016\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001c\"\u0004\b'\u0010\u001eR\u0016\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001c¨\u0006)"}, d2 = {"Lcom/amazon/music/views/library/models/DefaultPageHeaderModel;", "Lcom/amazon/music/views/library/models/PageHeaderModel;", ContentUtils.KEY_VIEW_REFERENCE_ID, "", "labelText", "headlineTitle", "headlineTitleId", "", "primaryTitle", "secondaryTitle", "tertiaryTitle", "actionIcons", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel;", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "downloadStateModel", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/amazon/music/views/library/metadata/ContentMetadata;Lcom/amazon/music/views/library/models/DownloadStateModel;)V", "getActionIcons", "()Ljava/util/List;", "setActionIcons", "(Ljava/util/List;)V", "getDownloadStateModel", "()Lcom/amazon/music/views/library/models/DownloadStateModel;", "setDownloadStateModel", "(Lcom/amazon/music/views/library/models/DownloadStateModel;)V", "getHeadlineTitle", "()Ljava/lang/String;", "setHeadlineTitle", "(Ljava/lang/String;)V", "getHeadlineTitleId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLabelText", "getMetadata", "()Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getPrimaryTitle", "getSecondaryTitle", "setSecondaryTitle", "getTertiaryTitle", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DefaultPageHeaderModel extends PageHeaderModel {
    private List<PageHeaderActionIconModel> actionIcons;
    private DownloadStateModel downloadStateModel;
    private String headlineTitle;
    private final Integer headlineTitleId;
    private final String labelText;
    private final ContentMetadata metadata;
    private final String primaryTitle;
    private String secondaryTitle;
    private final String tertiaryTitle;

    public /* synthetic */ DefaultPageHeaderModel(String str, String str2, String str3, Integer num, String str4, String str5, String str6, List list, ContentMetadata contentMetadata, DownloadStateModel downloadStateModel, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : str6, list, (i & 256) != 0 ? null : contentMetadata, (i & 512) != 0 ? null : downloadStateModel);
    }

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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultPageHeaderModel(String str, String str2, String str3, Integer num, String str4, String str5, String str6, List<PageHeaderActionIconModel> actionIcons, ContentMetadata contentMetadata, DownloadStateModel downloadStateModel) {
        super(str);
        Intrinsics.checkNotNullParameter(actionIcons, "actionIcons");
        this.labelText = str2;
        this.headlineTitle = str3;
        this.headlineTitleId = num;
        this.primaryTitle = str4;
        this.secondaryTitle = str5;
        this.tertiaryTitle = str6;
        this.actionIcons = actionIcons;
        this.metadata = contentMetadata;
        this.downloadStateModel = downloadStateModel;
    }
}
