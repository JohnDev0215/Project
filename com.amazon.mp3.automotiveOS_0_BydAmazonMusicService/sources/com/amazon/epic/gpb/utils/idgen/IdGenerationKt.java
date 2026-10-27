package com.amazon.epic.gpb.utils.idgen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.StringsKt___StringsKt;

/* JADX INFO: compiled from: IdGeneration.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\"\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0003H\u0002\u001a\f\u0010\u000b\u001a\u00020\b*\u00020\u0003H\u0002¨\u0006\f"}, d2 = {"compressRealm", "", "handleWithoutNamespace", "", "generateObfuscatedAccountId", "purchaseHandleId", "encodeByteArrayToBase64", "Lkotlin/Function1;", "", "validateFormat", "", "toByteArrayAsHex", "EPICGooglePlayBillingIDGenerator"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class IdGenerationKt {
    public static final String generateObfuscatedAccountId(String purchaseHandleId, Function1<? super byte[], String> encodeByteArrayToBase64) {
        Intrinsics.checkNotNullParameter(purchaseHandleId, "purchaseHandleId");
        Intrinsics.checkNotNullParameter(encodeByteArrayToBase64, "encodeByteArrayToBase64");
        validateFormat(purchaseHandleId);
        String strRemovePrefix = StringsKt__StringsKt.removePrefix(purchaseHandleId, (CharSequence) "amzn1.emp.handle.");
        char cCompressRealm = compressRealm(strRemovePrefix);
        List listSplit$default = StringsKt__StringsKt.split$default((CharSequence) StringsKt__StringsKt.removeRange((CharSequence) strRemovePrefix, new IntRange(0, 2)).toString(), new char[]{'_'}, false, 0, 6, (Object) null);
        String strInvoke = encodeByteArrayToBase64.invoke(toByteArrayAsHex((String) listSplit$default.get(0)));
        return StringsKt__StringsKt.removePrefix((String) listSplit$default.get(1), (CharSequence) "v") + cCompressRealm + strInvoke;
    }

    private static final char compressRealm(String str) {
        String strSubstring = str.substring(0, 2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        int iHashCode = strSubstring.hashCode();
        if (iHashCode != 3179) {
            if (iHashCode != 3248) {
                if (iHashCode != 3263) {
                    if (iHashCode == 3507 && strSubstring.equals("na")) {
                        return 'N';
                    }
                } else if (strSubstring.equals("fe")) {
                    return 'F';
                }
            } else if (strSubstring.equals("eu")) {
                return 'E';
            }
        } else if (strSubstring.equals("cn")) {
            throw new IllegalArgumentException("CN realm is not yet supported by EPIC services!");
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("Unknown realm: ", strSubstring));
    }

    private static final void validateFormat(String str) {
        if (!new Regex("^amzn1\\.emp\\.handle\\.(na|eu|fe|cn)\\.([a-z]|[0-9]){64}_v[0-9]*$").matches(str)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Invalid purchaseHandleId format for provided input: ", str).toString());
        }
    }

    private static final byte[] toByteArrayAsHex(String str) {
        List listChunked = StringsKt___StringsKt.chunked(str, 2);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listChunked, 10));
        Iterator it = listChunked.iterator();
        while (it.hasNext()) {
            arrayList.add(Byte.valueOf((byte) Integer.parseInt((String) it.next(), CharsKt__CharJVMKt.checkRadix(16))));
        }
        return CollectionsKt___CollectionsKt.toByteArray(arrayList);
    }
}
