package com.amazon.mrn.v1;

import com.amazon.mrn.exceptions.InvalidMediaResourceName;
import com.amazon.mrn.model.MrnVersion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: MediaResourceNameProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u0005\u001a\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0002J\f\u0010\u0006\u001a\u00020\u0003*\u00020\u0003H\u0002J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0003J\u000e\u0010\u000b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\b¨\u0006\u000e"}, d2 = {"Lcom/amazon/mrn/v1/MediaResourceNameProvider;", "", "", "", "attributes", "toQueryString", "sanitize", "mrnString", "Lcom/amazon/mrn/v1/MediaResourceNameV1;", "fromString", "mrn", "toMrnString", "<init>", "()V", "AmazonMusicMediaResourceNameKotlin"}, k = 1, mv = {1, 7, 1})
public final class MediaResourceNameProvider {
    public static final MediaResourceNameProvider INSTANCE = new MediaResourceNameProvider();

    private MediaResourceNameProvider() {
    }

    public final MediaResourceNameV1 fromString(String mrnString) throws InvalidMediaResourceName {
        LinkedHashMap linkedHashMap;
        Intrinsics.checkNotNullParameter(mrnString, "mrnString");
        String str = "mrn:" + MrnVersion.V1.getValue() + ':';
        if (!StringsKt__StringsKt.contains$default((CharSequence) mrnString, (CharSequence) str, false, 2, (Object) null)) {
            throw new InvalidMediaResourceName("Invalid MRN string. Expected prefix " + str + ". Valid MRN requires prefix containing mrn scheme and version.", null, 2, null);
        }
        List listListOf = CollectionsKt__CollectionsJVMKt.listOf(mrnString);
        if (StringsKt__StringsKt.contains$default((CharSequence) mrnString, (CharSequence) "?", false, 2, (Object) null)) {
            listListOf = StringsKt__StringsKt.split$default((CharSequence) mrnString, new String[]{"?"}, false, 0, 6, (Object) null);
            String str2 = (String) listListOf.get(1);
            List<String> listSplit$default = StringsKt__StringsKt.split$default((CharSequence) str2, new String[]{"&"}, false, 0, 6, (Object) null);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(listSplit$default, 10)), 16));
            for (String str3 : listSplit$default) {
                List listSplit$default2 = StringsKt__StringsKt.split$default((CharSequence) str3, new String[]{"="}, false, 0, 6, (Object) null);
                String str4 = (String) listSplit$default2.get(0);
                String str5 = (String) listSplit$default2.get(1);
                if (!(str4.length() == 0)) {
                    if (!(str5.length() == 0)) {
                        Pair pair = TuplesKt.to(INSTANCE.sanitize(str4), StringsKt__StringsKt.trim((CharSequence) str5).toString());
                        linkedHashMap2.put(pair.getFirst(), pair.getSecond());
                    }
                }
                throw new InvalidMediaResourceName("Invalid MRN string. When reading parameters " + str2 + ", unexpect attribute " + str3 + " was found. Expected the form ...?key=value&...", null, 2, null);
            }
            linkedHashMap = linkedHashMap2;
        } else {
            linkedHashMap = null;
        }
        String strSubstring = ((String) listListOf.get(0)).substring(str.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
        List listSplit$default3 = StringsKt__StringsKt.split$default((CharSequence) strSubstring, new String[]{":"}, false, 0, 6, (Object) null);
        if (listSplit$default3.size() < 4) {
            throw new InvalidMediaResourceName("Invalid MRN string. Wrong number of segments in name: Has " + listSplit$default3.size() + ", but expected >= 4.", null, 2, null);
        }
        String strSanitize = sanitize((String) listSplit$default3.get(0));
        String strSanitize2 = sanitize((String) listSplit$default3.get(1));
        String strSanitize3 = sanitize((String) listSplit$default3.get(2));
        MediaResourceNameValidator mediaResourceNameValidator = MediaResourceNameValidator.INSTANCE;
        MediaResourceNameV1 mediaResourceNameV1 = new MediaResourceNameV1(null, mediaResourceNameValidator.getAuthorityEnum(strSanitize), mediaResourceNameValidator.getMediaTypeEnum(strSanitize2), mediaResourceNameValidator.getMediaIdentifierTypeEnum(strSanitize3), StringsKt__StringsKt.trim((CharSequence) listSplit$default3.get(3)).toString(), linkedHashMap, 1, null);
        mediaResourceNameValidator.validateMrn(mediaResourceNameV1);
        return mediaResourceNameV1;
    }

    public final String toMrnString(MediaResourceNameV1 mrn) {
        Intrinsics.checkNotNullParameter(mrn, "mrn");
        return ("mrn:" + mrn.getVersion() + ':' + mrn.getAuthority().getValue() + ':' + mrn.getMediaType().getValue() + ':' + mrn.getMediaIdentifierType().getValue() + ':' + mrn.getIdentifier()) + toQueryString(mrn.getAttributes());
    }

    private final String toQueryString(Map<String, String> attributes) {
        ArrayList arrayList;
        String strJoinToString$default;
        Set<Map.Entry<String, String>> setEntrySet;
        if (attributes == null || (setEntrySet = attributes.entrySet()) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(setEntrySet, 10));
            Iterator<T> it = setEntrySet.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                StringBuilder sb = new StringBuilder();
                String lowerCase = StringsKt__StringsKt.trim((CharSequence) entry.getKey()).toString().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                sb.append(lowerCase);
                sb.append('=');
                sb.append(StringsKt__StringsKt.trim((CharSequence) entry.getValue()).toString());
                arrayList.add(sb.toString());
            }
        }
        ArrayList arrayList2 = arrayList;
        return (arrayList2 == null || (strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList2, "&", "?", null, 0, null, null, 60, null)) == null) ? "" : strJoinToString$default;
    }

    private final String sanitize(String str) {
        String lowerCase = StringsKt__StringsKt.trim((CharSequence) str).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        return lowerCase;
    }
}
