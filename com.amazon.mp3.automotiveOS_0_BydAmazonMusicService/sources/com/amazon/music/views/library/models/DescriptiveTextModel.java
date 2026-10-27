package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DescriptiveTextModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u0000 $2\u00020\u0001:\u0001$BO\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u0014\u001a\u00020\n\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\"\u0010#R(\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\"\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u0017\u0010\u0014\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0015\u0010\u000eR\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0018\u001a\u0004\b\u001c\u0010\u001aR\u0017\u0010\u001e\u001a\u00020\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006%"}, d2 = {"Lcom/amazon/music/views/library/models/DescriptiveTextModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "Lcom/amazon/music/views/library/models/DescriptiveTextItemModel;", "text", "Ljava/util/List;", "getText", "()Ljava/util/List;", "setText", "(Ljava/util/List;)V", "", "footer", "Ljava/lang/String;", "getFooter", "()Ljava/lang/String;", "setFooter", "(Ljava/lang/String;)V", "separator", "getSeparator", "setSeparator", "ellipsisText", "getEllipsisText", "", "maxLineCount", "I", "getMaxLineCount", "()I", "maxItemLength", "getMaxItemLength", "", "alwaysDisplayFooterText", "Z", "getAlwaysDisplayFooterText", "()Z", "<init>", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIZ)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class DescriptiveTextModel extends BaseViewModel {
    private final boolean alwaysDisplayFooterText;
    private final String ellipsisText;
    private String footer;
    private final int maxItemLength;
    private final int maxLineCount;
    private String separator;
    private List<DescriptiveTextItemModel> text;

    public /* synthetic */ DescriptiveTextModel(List list, String str, String str2, String str3, int i, int i2, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, str, str2, (i3 & 8) != 0 ? "..." : str3, (i3 & 16) != 0 ? 4 : i, (i3 & 32) != 0 ? 50 : i2, (i3 & 64) != 0 ? true : z);
    }

    public final List<DescriptiveTextItemModel> getText() {
        return this.text;
    }

    public final String getFooter() {
        return this.footer;
    }

    public final String getSeparator() {
        return this.separator;
    }

    public final String getEllipsisText() {
        return this.ellipsisText;
    }

    public final int getMaxLineCount() {
        return this.maxLineCount;
    }

    public final int getMaxItemLength() {
        return this.maxItemLength;
    }

    public final boolean getAlwaysDisplayFooterText() {
        return this.alwaysDisplayFooterText;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptiveTextModel(List<DescriptiveTextItemModel> text, String footer, String str, String ellipsisText, int i, int i2, boolean z) {
        super(null, null, 2, null);
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(footer, "footer");
        Intrinsics.checkNotNullParameter(ellipsisText, "ellipsisText");
        this.text = text;
        this.footer = footer;
        this.separator = str;
        this.ellipsisText = ellipsisText;
        this.maxLineCount = i;
        this.maxItemLength = i2;
        this.alwaysDisplayFooterText = z;
    }
}
