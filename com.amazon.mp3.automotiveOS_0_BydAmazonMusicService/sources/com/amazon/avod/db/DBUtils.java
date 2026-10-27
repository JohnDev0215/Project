package com.amazon.avod.db;

import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public class DBUtils {
    private static final ImmutableList<String> EMPTY_LIST = ImmutableList.of();
    private static final Set<String> EMPTY_SET = Sets.newHashSet();

    public static ImmutableList<String> convertJSONToList(@Nonnull String str) {
        Preconditions.checkNotNull(str, "JSON String to convert to ImmutableList cannot be null");
        if (str.isEmpty()) {
            return EMPTY_LIST;
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                builder.add(jSONArray.getString(i));
            }
        } catch (JSONException unused) {
            DLog.errorf("Error converting json String to json Array");
        }
        return builder.build();
    }

    public static Set<String> convertJSONToSet(@Nonnull String str) {
        Preconditions.checkNotNull(str, "JSON String to convert to Set cannot be null");
        if (str.isEmpty()) {
            return EMPTY_SET;
        }
        HashSet hashSet = new HashSet();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.getString(i));
            }
        } catch (JSONException unused) {
            DLog.errorf("Error converting json String to json Array");
        }
        return hashSet;
    }

    public static String convertListToJSON(@Nonnull ImmutableList<String> immutableList) {
        return immutableList.isEmpty() ? HttpUrl.PATH_SEGMENT_ENCODE_SET_URI : new JSONArray((Collection<?>) immutableList).toString();
    }

    public static String convertSetToJSON(@Nonnull Set<String> set) {
        return set.isEmpty() ? HttpUrl.PATH_SEGMENT_ENCODE_SET_URI : new JSONArray((Collection<?>) set).toString();
    }
}
