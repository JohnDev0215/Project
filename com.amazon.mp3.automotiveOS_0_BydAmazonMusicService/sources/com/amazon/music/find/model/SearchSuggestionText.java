package com.amazon.music.find.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchSuggestionText.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/find/model/SearchSuggestionText;", "Lcom/amazon/music/find/model/SearchSuggestion;", "title", "", "blockRef", "id", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBlockRef", "()Ljava/lang/String;", "getId", "getTitle", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchSuggestionText extends SearchSuggestion {
    private final String blockRef;
    private final String id;
    private final String title;

    public static /* synthetic */ SearchSuggestionText copy$default(SearchSuggestionText searchSuggestionText, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = searchSuggestionText.getTitle();
        }
        if ((i & 2) != 0) {
            str2 = searchSuggestionText.getBlockRef();
        }
        if ((i & 4) != 0) {
            str3 = searchSuggestionText.id;
        }
        return searchSuggestionText.copy(str, str2, str3);
    }

    public final String component1() {
        return getTitle();
    }

    public final String component2() {
        return getBlockRef();
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final SearchSuggestionText copy(String title, String blockRef, String id) {
        Intrinsics.checkNotNullParameter(title, "title");
        return new SearchSuggestionText(title, blockRef, id);
    }

    @Override // com.amazon.music.find.model.SearchSuggestion
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchSuggestionText)) {
            return false;
        }
        SearchSuggestionText searchSuggestionText = (SearchSuggestionText) other;
        return Intrinsics.areEqual(getTitle(), searchSuggestionText.getTitle()) && Intrinsics.areEqual(getBlockRef(), searchSuggestionText.getBlockRef()) && Intrinsics.areEqual(this.id, searchSuggestionText.id);
    }

    public int hashCode() {
        int iHashCode = ((getTitle().hashCode() * 31) + (getBlockRef() == null ? 0 : getBlockRef().hashCode())) * 31;
        String str = this.id;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "SearchSuggestionText(title=" + getTitle() + ", blockRef=" + ((Object) getBlockRef()) + ", id=" + ((Object) this.id) + ')';
    }

    public /* synthetic */ SearchSuggestionText(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    @Override // com.amazon.music.find.model.SearchSuggestion
    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.music.find.model.SearchSuggestion
    public String getBlockRef() {
        return this.blockRef;
    }

    public final String getId() {
        return this.id;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchSuggestionText(String title, String str, String str2) {
        super(title, str);
        Intrinsics.checkNotNullParameter(title, "title");
        this.title = title;
        this.blockRef = str;
        this.id = str2;
    }
}
