package com.spotify.webapi.search;

import p.c71;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class WebApiSearchResults {
    public final String a;
    public final WebApiSearchModel$Response b;

    public WebApiSearchResults(@z61(name = "search_term") String str, @z61(name = "results") WebApiSearchModel$Response webApiSearchModel$Response) {
        v03.j(str, "searchTerm");
        v03.j(webApiSearchModel$Response, "results");
        this.a = str;
        this.b = webApiSearchModel$Response;
    }

    public final WebApiSearchResults copy(@z61(name = "search_term") String str, @z61(name = "results") WebApiSearchModel$Response webApiSearchModel$Response) {
        v03.j(str, "searchTerm");
        v03.j(webApiSearchModel$Response, "results");
        return new WebApiSearchResults(str, webApiSearchModel$Response);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WebApiSearchResults)) {
            return false;
        }
        WebApiSearchResults webApiSearchResults = (WebApiSearchResults) obj;
        return v03.b(this.a, webApiSearchResults.a) && v03.b(this.b, webApiSearchResults.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "WebApiSearchResults(searchTerm=" + this.a + ", results=" + this.b + ')';
    }
}
