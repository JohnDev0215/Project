package com.bugsnag.android;

import com.amazonaws.http.HttpHeader;
import com.bugsnag.android.internal.DateUtils;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: DeliveryHeaders.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\u001a\u001e\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000\u001a\u0016\u0010\b\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000\u001a\u001e\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\t\u001a\u00020\u0003H\u0000\u001a\u0012\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0001\u001a\u00020\u000bH\u0000¨\u0006\r"}, d2 = {"Lcom/bugsnag/android/EventPayload;", "payload", "", "", "errorApiHeaders", "", "Lcom/bugsnag/android/ErrorType;", "errorTypes", "serializeErrorTypeHeader", "apiKey", "sessionApiHeaders", "", "computeSha1Digest", "bugsnag-android-core_release"}, k = 2, mv = {1, 4, 2})
public final class DeliveryHeadersKt {
    public static final Map<String, String> errorApiHeaders(EventPayload eventPayload) {
        Pair[] pairArr = new Pair[4];
        pairArr[0] = TuplesKt.to("Bugsnag-Payload-Version", "4.0");
        String apiKey = eventPayload.getApiKey();
        if (apiKey == null) {
            apiKey = "";
        }
        pairArr[1] = TuplesKt.to("Bugsnag-Api-Key", apiKey);
        pairArr[2] = TuplesKt.to("Bugsnag-Sent-At", DateUtils.toIso8601(new Date()));
        pairArr[3] = TuplesKt.to(HttpHeader.CONTENT_TYPE, "application/json");
        Map mapMutableMapOf = MapsKt__MapsKt.mutableMapOf(pairArr);
        Set<ErrorType> errorTypes$bugsnag_android_core_release = eventPayload.getErrorTypes$bugsnag_android_core_release();
        if (!errorTypes$bugsnag_android_core_release.isEmpty()) {
            mapMutableMapOf.put("Bugsnag-Stacktrace-Types", serializeErrorTypeHeader(errorTypes$bugsnag_android_core_release));
        }
        return MapsKt__MapsKt.toMap(mapMutableMapOf);
    }

    public static final String serializeErrorTypeHeader(Set<? extends ErrorType> set) {
        if (set.isEmpty()) {
            return "";
        }
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(set, 10));
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            arrayList.add(((ErrorType) it.next()).getDesc());
        }
        Iterator it2 = arrayList.iterator();
        if (!it2.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        Object next = it2.next();
        while (it2.hasNext()) {
            next = ((String) next) + ',' + ((String) it2.next());
        }
        return (String) next;
    }

    public static final Map<String, String> sessionApiHeaders(String str) {
        return MapsKt__MapsKt.mapOf(TuplesKt.to("Bugsnag-Payload-Version", "1.0"), TuplesKt.to("Bugsnag-Api-Key", str), TuplesKt.to(HttpHeader.CONTENT_TYPE, "application/json"), TuplesKt.to("Bugsnag-Sent-At", DateUtils.toIso8601(new Date())));
    }

    public static final String computeSha1Digest(byte[] bArr) {
        try {
            Result.Companion companion = Result.INSTANCE;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            StringBuilder sb = new StringBuilder("sha1 ");
            OutputStream digestOutputStream = new DigestOutputStream(new NullOutputStream(), messageDigest);
            try {
                BufferedOutputStream bufferedOutputStream = digestOutputStream instanceof BufferedOutputStream ? (BufferedOutputStream) digestOutputStream : new BufferedOutputStream(digestOutputStream, 8192);
                try {
                    bufferedOutputStream.write(bArr);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(bufferedOutputStream, null);
                    byte[] bArrDigest = messageDigest.digest();
                    kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(bArrDigest, "shaDigest.digest()");
                    for (byte b : bArrDigest) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                        sb.append(str);
                    }
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(digestOutputStream, null);
                    return sb.toString();
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            if (Result.m2813exceptionOrNullimpl(Result.m2810constructorimpl(ResultKt.createFailure(th))) != null) {
                return null;
            }
            throw null;
        }
    }
}
