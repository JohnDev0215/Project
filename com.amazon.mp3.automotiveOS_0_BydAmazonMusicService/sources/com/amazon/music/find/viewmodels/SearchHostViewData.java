package com.amazon.music.find.viewmodels;

import android.os.Bundle;
import com.amazon.music.views.library.models.PageGridViewModelContext;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchHostViewData.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0017\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\bHÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u000eHÆ\u0003Js\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020,HÖ\u0001J\t\u0010-\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001f\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017¨\u0006."}, d2 = {"Lcom/amazon/music/find/viewmodels/SearchHostViewData;", "", "fragmentType", "Lcom/amazon/music/find/viewmodels/ChildFragmentType;", "skillPath", "", "fragmentName", "queryParams", "", "extras", "Landroid/os/Bundle;", "title", "subTitle", "context", "Lcom/amazon/music/views/library/models/PageGridViewModelContext;", "(Lcom/amazon/music/find/viewmodels/ChildFragmentType;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/models/PageGridViewModelContext;)V", "getContext", "()Lcom/amazon/music/views/library/models/PageGridViewModelContext;", "setContext", "(Lcom/amazon/music/views/library/models/PageGridViewModelContext;)V", "getExtras", "()Landroid/os/Bundle;", "getFragmentName", "()Ljava/lang/String;", "getFragmentType", "()Lcom/amazon/music/find/viewmodels/ChildFragmentType;", "getQueryParams", "()Ljava/util/Map;", "getSkillPath", "getSubTitle", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchHostViewData {
    private PageGridViewModelContext context;
    private final Bundle extras;
    private final String fragmentName;
    private final ChildFragmentType fragmentType;
    private final Map<String, String> queryParams;
    private final String skillPath;
    private final String subTitle;
    private final String title;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ChildFragmentType getFragmentType() {
        return this.fragmentType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSkillPath() {
        return this.skillPath;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getFragmentName() {
        return this.fragmentName;
    }

    public final Map<String, String> component4() {
        return this.queryParams;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Bundle getExtras() {
        return this.extras;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getSubTitle() {
        return this.subTitle;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final PageGridViewModelContext getContext() {
        return this.context;
    }

    public final SearchHostViewData copy(ChildFragmentType fragmentType, String skillPath, String fragmentName, Map<String, String> queryParams, Bundle extras, String title, String subTitle, PageGridViewModelContext context) {
        Intrinsics.checkNotNullParameter(fragmentType, "fragmentType");
        return new SearchHostViewData(fragmentType, skillPath, fragmentName, queryParams, extras, title, subTitle, context);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchHostViewData)) {
            return false;
        }
        SearchHostViewData searchHostViewData = (SearchHostViewData) other;
        return this.fragmentType == searchHostViewData.fragmentType && Intrinsics.areEqual(this.skillPath, searchHostViewData.skillPath) && Intrinsics.areEqual(this.fragmentName, searchHostViewData.fragmentName) && Intrinsics.areEqual(this.queryParams, searchHostViewData.queryParams) && Intrinsics.areEqual(this.extras, searchHostViewData.extras) && Intrinsics.areEqual(this.title, searchHostViewData.title) && Intrinsics.areEqual(this.subTitle, searchHostViewData.subTitle) && Intrinsics.areEqual(this.context, searchHostViewData.context);
    }

    public int hashCode() {
        int iHashCode = this.fragmentType.hashCode() * 31;
        String str = this.skillPath;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.fragmentName;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Map<String, String> map = this.queryParams;
        int iHashCode4 = (iHashCode3 + (map == null ? 0 : map.hashCode())) * 31;
        Bundle bundle = this.extras;
        int iHashCode5 = (iHashCode4 + (bundle == null ? 0 : bundle.hashCode())) * 31;
        String str3 = this.title;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.subTitle;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        PageGridViewModelContext pageGridViewModelContext = this.context;
        return iHashCode7 + (pageGridViewModelContext != null ? pageGridViewModelContext.hashCode() : 0);
    }

    public String toString() {
        return "SearchHostViewData(fragmentType=" + this.fragmentType + ", skillPath=" + ((Object) this.skillPath) + ", fragmentName=" + ((Object) this.fragmentName) + ", queryParams=" + this.queryParams + ", extras=" + this.extras + ", title=" + ((Object) this.title) + ", subTitle=" + ((Object) this.subTitle) + ", context=" + this.context + ')';
    }

    public SearchHostViewData(ChildFragmentType fragmentType, String str, String str2, Map<String, String> map, Bundle bundle, String str3, String str4, PageGridViewModelContext pageGridViewModelContext) {
        Intrinsics.checkNotNullParameter(fragmentType, "fragmentType");
        this.fragmentType = fragmentType;
        this.skillPath = str;
        this.fragmentName = str2;
        this.queryParams = map;
        this.extras = bundle;
        this.title = str3;
        this.subTitle = str4;
        this.context = pageGridViewModelContext;
    }

    public /* synthetic */ SearchHostViewData(ChildFragmentType childFragmentType, String str, String str2, Map map, Bundle bundle, String str3, String str4, PageGridViewModelContext pageGridViewModelContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(childFragmentType, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : map, (i & 16) != 0 ? null : bundle, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : str4, (i & 128) == 0 ? pageGridViewModelContext : null);
    }

    public final ChildFragmentType getFragmentType() {
        return this.fragmentType;
    }

    public final String getSkillPath() {
        return this.skillPath;
    }

    public final String getFragmentName() {
        return this.fragmentName;
    }

    public final Map<String, String> getQueryParams() {
        return this.queryParams;
    }

    public final Bundle getExtras() {
        return this.extras;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final PageGridViewModelContext getContext() {
        return this.context;
    }

    public final void setContext(PageGridViewModelContext pageGridViewModelContext) {
        this.context = pageGridViewModelContext;
    }
}
