package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import kotlin.Metadata;

/* JADX INFO: compiled from: DescriptiveTextItemModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\f\u0010\rR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/views/library/models/DescriptiveTextItemModel;", "", "", ContextMappingConstants.PRIMARY_TEXT, "Ljava/lang/String;", "getPrimaryText", "()Ljava/lang/String;", "setPrimaryText", "(Ljava/lang/String;)V", ContextMappingConstants.SECONDARY_TEXT, "getSecondaryText", "setSecondaryText", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class DescriptiveTextItemModel {
    private String primaryText;
    private String secondaryText;

    public DescriptiveTextItemModel(String str, String str2) {
        this.primaryText = str;
        this.secondaryText = str2;
    }

    public final String getPrimaryText() {
        return this.primaryText;
    }

    public final String getSecondaryText() {
        return this.secondaryText;
    }
}
