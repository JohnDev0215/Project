package com.amazon.music.views.library.models;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StoryPageHeaderModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u00002\u00020\u0001B\u0093\u0001\u0012\b\u00107\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010 \u001a\u0004\u0018\u00010\u0002\u0012\f\u0010%\u001a\b\u0012\u0004\u0012\u00020$0#\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010+\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u000100¢\u0006\u0004\b8\u00109J\u0016\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR$\u0010\f\u001a\u0004\u0018\u00010\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\f\u0010\t\u001a\u0004\b\r\u0010\u000b\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\t\u001a\u0004\b\u0011\u0010\u000bR$\u0010\u0012\u001a\u0004\u0018\u00010\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\t\u001a\u0004\b\u0013\u0010\u000b\"\u0004\b\u0014\u0010\u000fR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\t\u001a\u0004\b\u0016\u0010\u000bR$\u0010\u0017\u001a\u0004\u0018\u00010\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\t\u001a\u0004\b\u0018\u0010\u000b\"\u0004\b\u0019\u0010\u000fR$\u0010\u001a\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010 \u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010\u001b\u001a\u0004\b!\u0010\u001d\"\u0004\b\"\u0010\u001fR(\u0010%\u001a\b\u0012\u0004\u0012\u00020$0#8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010,\u001a\u0004\u0018\u00010+8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R$\u00101\u001a\u0004\u0018\u0001008\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106¨\u0006:"}, d2 = {"Lcom/amazon/music/views/library/models/StoryPageHeaderModel;", "Lcom/amazon/music/views/library/models/PageHeaderModel;", "", "prev", "cur", "", "updatePagination", "", "labelText", "Ljava/lang/String;", "getLabelText", "()Ljava/lang/String;", "headlineTitle", "getHeadlineTitle", "setHeadlineTitle", "(Ljava/lang/String;)V", "primaryTitle", "getPrimaryTitle", "secondaryTitle", "getSecondaryTitle", "setSecondaryTitle", "tertiaryTitle", "getTertiaryTitle", "text4", "getText4", "setText4", "prevDotIndex", "Ljava/lang/Integer;", "getPrevDotIndex", "()Ljava/lang/Integer;", "setPrevDotIndex", "(Ljava/lang/Integer;)V", "curDotIndex", "getCurDotIndex", "setCurDotIndex", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel;", "actionIcons", "Ljava/util/List;", "getActionIcons", "()Ljava/util/List;", "setActionIcons", "(Ljava/util/List;)V", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getMetadata", "()Lcom/amazon/music/views/library/metadata/ContentMetadata;", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "downloadStateModel", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "getDownloadStateModel", "()Lcom/amazon/music/views/library/models/DownloadStateModel;", "setDownloadStateModel", "(Lcom/amazon/music/views/library/models/DownloadStateModel;)V", ContentUtils.KEY_VIEW_REFERENCE_ID, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Lcom/amazon/music/views/library/metadata/ContentMetadata;Lcom/amazon/music/views/library/models/DownloadStateModel;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public class StoryPageHeaderModel extends PageHeaderModel {
    private List<PageHeaderActionIconModel> actionIcons;
    private Integer curDotIndex;
    private DownloadStateModel downloadStateModel;
    private String headlineTitle;
    private final String labelText;
    private final ContentMetadata metadata;
    private Integer prevDotIndex;
    private final String primaryTitle;
    private String secondaryTitle;
    private final String tertiaryTitle;
    private String text4;

    public /* synthetic */ StoryPageHeaderModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, Integer num, Integer num2, List list, ContentMetadata contentMetadata, DownloadStateModel downloadStateModel, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7, num, num2, list, (i & 1024) != 0 ? null : contentMetadata, (i & 2048) != 0 ? null : downloadStateModel);
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
    public String getText4() {
        return this.text4;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public void setText4(String str) {
        this.text4 = str;
    }

    public final Integer getPrevDotIndex() {
        return this.prevDotIndex;
    }

    public final Integer getCurDotIndex() {
        return this.curDotIndex;
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StoryPageHeaderModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, Integer num, Integer num2, List<PageHeaderActionIconModel> actionIcons, ContentMetadata contentMetadata, DownloadStateModel downloadStateModel) {
        super(str);
        Intrinsics.checkNotNullParameter(actionIcons, "actionIcons");
        this.labelText = str2;
        this.headlineTitle = str3;
        this.primaryTitle = str4;
        this.secondaryTitle = str5;
        this.tertiaryTitle = str6;
        this.text4 = str7;
        this.prevDotIndex = num;
        this.curDotIndex = num2;
        this.actionIcons = actionIcons;
        this.metadata = contentMetadata;
        this.downloadStateModel = downloadStateModel;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public DownloadStateModel getDownloadStateModel() {
        return this.downloadStateModel;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public void setDownloadStateModel(DownloadStateModel downloadStateModel) {
        this.downloadStateModel = downloadStateModel;
    }

    public final void updatePagination(int prev, int cur) {
        this.prevDotIndex = Integer.valueOf(prev);
        this.curDotIndex = Integer.valueOf(cur);
        notifyChanged(17);
    }
}
