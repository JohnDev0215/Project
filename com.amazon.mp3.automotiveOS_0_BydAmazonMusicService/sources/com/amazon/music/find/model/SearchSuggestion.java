package com.amazon.music.find.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchSuggestion.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/amazon/music/find/model/SearchSuggestion;", "", "title", "", "blockRef", "(Ljava/lang/String;Ljava/lang/String;)V", "getBlockRef", "()Ljava/lang/String;", "getTitle", "equals", "", "other", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class SearchSuggestion {
    private final String blockRef;
    private final String title;

    public SearchSuggestion(String title, String str) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.title = title;
        this.blockRef = str;
    }

    public String getTitle() {
        return this.title;
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public boolean equals(Object other) {
        if (other == null || !(other instanceof SearchSuggestion)) {
            return false;
        }
        return Intrinsics.areEqual(getTitle(), ((SearchSuggestion) other).getTitle());
    }
}
