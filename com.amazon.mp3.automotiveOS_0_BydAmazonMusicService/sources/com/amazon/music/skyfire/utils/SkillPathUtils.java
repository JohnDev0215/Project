package com.amazon.music.skyfire.utils;

import java.util.Optional;
import java.util.function.Function;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes4.dex */
public final class SkillPathUtils {
    private static final String API_SEGMENT = "/api";

    public static String getSkillPath(String str) {
        try {
            return (String) Optional.ofNullable(HttpUrl.parse(str)).map(new Function() { // from class: com.amazon.music.skyfire.utils.SkillPathUtils$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SkillPathUtils.lambda$getSkillPath$0((HttpUrl) obj);
                }
            }).orElse(null);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getSkillPath$0(HttpUrl httpUrl) {
        String strSanitizeSkillPath = sanitizeSkillPath(httpUrl.encodedPath());
        String strEncodedQuery = httpUrl.encodedQuery();
        if (strEncodedQuery == null) {
            return strSanitizeSkillPath;
        }
        return strSanitizeSkillPath + '?' + strEncodedQuery;
    }

    private static String sanitizeSkillPath(String str) {
        return (String) Optional.ofNullable(str).map(new Function() { // from class: com.amazon.music.skyfire.utils.SkillPathUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SkillPathUtils.lambda$sanitizeSkillPath$1((String) obj);
            }
        }).orElse(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$sanitizeSkillPath$1(String str) {
        return str.startsWith(API_SEGMENT) ? str.substring(4) : str;
    }
}
