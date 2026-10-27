package com.amazon.mp3.util;

import android.net.Uri;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class UriBuilderExtensions {
    public Uri.Builder removeQueryParameter(final Uri.Builder uriBuilder, final String paramToRemove) {
        Uri uriBuild = uriBuilder.build();
        Set<String> queryParameterNames = uriBuild.getQueryParameterNames();
        Uri.Builder builderClearQuery = uriBuild.buildUpon().clearQuery();
        for (String str : queryParameterNames) {
            if (!paramToRemove.equals(str)) {
                builderClearQuery.appendQueryParameter(str, uriBuild.getQueryParameter(str));
            }
        }
        return builderClearQuery;
    }
}
