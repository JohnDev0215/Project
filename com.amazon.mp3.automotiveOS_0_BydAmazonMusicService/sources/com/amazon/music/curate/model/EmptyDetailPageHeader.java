package com.amazon.music.curate.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EmptyDetailPageHeader.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/curate/model/EmptyDetailPageHeader;", "Lcom/amazon/music/curate/model/VisualModel;", "title", "", "subTitle", "(Ljava/lang/String;Ljava/lang/String;)V", "getSubTitle", "()Ljava/lang/String;", "getTitle", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class EmptyDetailPageHeader implements VisualModel {
    private final String subTitle;
    private final String title;

    public static /* synthetic */ EmptyDetailPageHeader copy$default(EmptyDetailPageHeader emptyDetailPageHeader, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = emptyDetailPageHeader.title;
        }
        if ((i & 2) != 0) {
            str2 = emptyDetailPageHeader.subTitle;
        }
        return emptyDetailPageHeader.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSubTitle() {
        return this.subTitle;
    }

    public final EmptyDetailPageHeader copy(String title, String subTitle) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(subTitle, "subTitle");
        return new EmptyDetailPageHeader(title, subTitle);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EmptyDetailPageHeader)) {
            return false;
        }
        EmptyDetailPageHeader emptyDetailPageHeader = (EmptyDetailPageHeader) other;
        return Intrinsics.areEqual(this.title, emptyDetailPageHeader.title) && Intrinsics.areEqual(this.subTitle, emptyDetailPageHeader.subTitle);
    }

    public int hashCode() {
        return (this.title.hashCode() * 31) + this.subTitle.hashCode();
    }

    public String toString() {
        return "EmptyDetailPageHeader(title=" + this.title + ", subTitle=" + this.subTitle + ')';
    }

    public EmptyDetailPageHeader(String title, String subTitle) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(subTitle, "subTitle");
        this.title = title;
        this.subTitle = subTitle;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }
}
