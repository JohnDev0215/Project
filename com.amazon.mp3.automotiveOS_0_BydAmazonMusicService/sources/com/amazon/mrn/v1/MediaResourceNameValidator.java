package com.amazon.mrn.v1;

import com.amazon.mrn.UUIDKt;
import com.amazon.mrn.exceptions.InvalidMediaResourceName;
import com.amazon.mrn.exceptions.InvalidMediaResourceNameValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;

/* JADX INFO: compiled from: MediaResourceNameValidator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\nJ\u000e\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\nJ\u000e\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\nJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/mrn/v1/MediaResourceNameValidator;", "", "()V", "catalogEntities", "", "Lcom/amazon/mrn/v1/MrnMediaType;", "identifierTypeToValidator", "", "Lcom/amazon/mrn/v1/MrnIdentifierType;", "Lkotlin/reflect/KFunction1;", "", "", "validAuthorityToMediaTypeMap", "Lcom/amazon/mrn/v1/MrnAuthority;", "validMediaTypeToIdentifierTypeMap", "getAuthorityEnum", "authority", "getMediaIdentifierTypeEnum", "mediaIdentifierType", "getMediaTypeEnum", "mediaType", "validateMrn", "", "mrn", "Lcom/amazon/mrn/v1/MediaResourceNameV1;", "MrnValueValidator", "AmazonMusicMediaResourceNameKotlin"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MediaResourceNameValidator {
    public static final MediaResourceNameValidator INSTANCE = new MediaResourceNameValidator();
    private static final List<MrnMediaType> catalogEntities;
    private static final Map<MrnIdentifierType, KFunction<Unit>> identifierTypeToValidator;
    private static final Map<MrnAuthority, List<MrnMediaType>> validAuthorityToMediaTypeMap;
    private static final Map<MrnMediaType, List<MrnIdentifierType>> validMediaTypeToIdentifierTypeMap;

    private MediaResourceNameValidator() {
    }

    public final boolean validateMrn(MediaResourceNameV1 mrn) throws InvalidMediaResourceName {
        Intrinsics.checkNotNullParameter(mrn, "mrn");
        List<MrnMediaType> list = validAuthorityToMediaTypeMap.get(mrn.getAuthority());
        boolean zContains = list != null ? list.contains(mrn.getMediaType()) : false;
        List<MrnIdentifierType> list2 = validMediaTypeToIdentifierTypeMap.get(mrn.getMediaType());
        boolean zContains2 = list2 != null ? list2.contains(mrn.getMediaIdentifierType()) : false;
        if (!zContains) {
            throw new InvalidMediaResourceName("Error constructing valid MRN: authority=" + mrn.getAuthority() + " does not match mediaType=" + mrn.getMediaType(), null, 2, null);
        }
        if (!zContains2) {
            throw new InvalidMediaResourceName("Error constructing valid MRN: mediaType=" + mrn.getMediaType() + " does not match mediaIdentifierType=" + mrn.getMediaIdentifierType(), null, 2, null);
        }
        KFunction<Unit> kFunction = identifierTypeToValidator.get(mrn.getMediaIdentifierType());
        if (kFunction == null) {
            return true;
        }
        ((Function1) kFunction).invoke(mrn.getIdentifier());
        return true;
    }

    public final MrnAuthority getAuthorityEnum(String authority) throws InvalidMediaResourceName {
        MrnAuthority mrnAuthority;
        Intrinsics.checkNotNullParameter(authority, "authority");
        MrnAuthority[] mrnAuthorityArrValues = MrnAuthority.values();
        int length = mrnAuthorityArrValues.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                mrnAuthority = null;
                break;
            }
            mrnAuthority = mrnAuthorityArrValues[i];
            if (Intrinsics.areEqual(mrnAuthority.getValue(), authority)) {
                break;
            }
            i++;
        }
        if (mrnAuthority != null) {
            return mrnAuthority;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Error constructing MRN: authority=");
        sb.append(authority);
        sb.append(" is invalid. Valid values are ");
        MrnAuthority[] mrnAuthorityArrValues2 = MrnAuthority.values();
        ArrayList arrayList = new ArrayList(mrnAuthorityArrValues2.length);
        for (MrnAuthority mrnAuthority2 : mrnAuthorityArrValues2) {
            arrayList.add(mrnAuthority2.getValue());
        }
        sb.append(arrayList);
        sb.append('.');
        throw new InvalidMediaResourceName(sb.toString(), null, 2, null);
    }

    public final MrnMediaType getMediaTypeEnum(String mediaType) throws InvalidMediaResourceName {
        MrnMediaType mrnMediaType;
        Intrinsics.checkNotNullParameter(mediaType, "mediaType");
        MrnMediaType[] mrnMediaTypeArrValues = MrnMediaType.values();
        int length = mrnMediaTypeArrValues.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                mrnMediaType = null;
                break;
            }
            mrnMediaType = mrnMediaTypeArrValues[i];
            if (Intrinsics.areEqual(mrnMediaType.getValue(), mediaType)) {
                break;
            }
            i++;
        }
        if (mrnMediaType != null) {
            return mrnMediaType;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Error constructing MRN: mediaType=");
        sb.append(mediaType);
        sb.append(" is invalid. Valid values are ");
        MrnMediaType[] mrnMediaTypeArrValues2 = MrnMediaType.values();
        ArrayList arrayList = new ArrayList(mrnMediaTypeArrValues2.length);
        for (MrnMediaType mrnMediaType2 : mrnMediaTypeArrValues2) {
            arrayList.add(mrnMediaType2.getValue());
        }
        sb.append(arrayList);
        sb.append('.');
        throw new InvalidMediaResourceName(sb.toString(), null, 2, null);
    }

    public final MrnIdentifierType getMediaIdentifierTypeEnum(String mediaIdentifierType) throws InvalidMediaResourceName {
        MrnIdentifierType mrnIdentifierType;
        Intrinsics.checkNotNullParameter(mediaIdentifierType, "mediaIdentifierType");
        MrnIdentifierType[] mrnIdentifierTypeArrValues = MrnIdentifierType.values();
        int length = mrnIdentifierTypeArrValues.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                mrnIdentifierType = null;
                break;
            }
            mrnIdentifierType = mrnIdentifierTypeArrValues[i];
            if (Intrinsics.areEqual(mrnIdentifierType.getValue(), mediaIdentifierType)) {
                break;
            }
            i++;
        }
        if (mrnIdentifierType != null) {
            return mrnIdentifierType;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Error constructing MRN: mediaIdentifierType=");
        sb.append(mediaIdentifierType);
        sb.append(" is invalid. Valid values are ");
        MrnIdentifierType[] mrnIdentifierTypeArrValues2 = MrnIdentifierType.values();
        ArrayList arrayList = new ArrayList(mrnIdentifierTypeArrValues2.length);
        for (MrnIdentifierType mrnIdentifierType2 : mrnIdentifierTypeArrValues2) {
            arrayList.add(mrnIdentifierType2.getValue());
        }
        sb.append(arrayList);
        sb.append('.');
        throw new InvalidMediaResourceName(sb.toString(), null, 2, null);
    }

    static {
        MrnMediaType mrnMediaType = MrnMediaType.MUSIC_TRACK;
        MrnMediaType mrnMediaType2 = MrnMediaType.ARTIST;
        MrnMediaType mrnMediaType3 = MrnMediaType.MUSIC_ALBUM;
        MrnMediaType mrnMediaType4 = MrnMediaType.PLAYLIST;
        MrnMediaType mrnMediaType5 = MrnMediaType.STATION;
        MrnMediaType mrnMediaType6 = MrnMediaType.GENRE;
        MrnMediaType mrnMediaType7 = MrnMediaType.ERA;
        MrnMediaType mrnMediaType8 = MrnMediaType.MOOD;
        MrnMediaType mrnMediaType9 = MrnMediaType.ACTIVITY;
        MrnMediaType mrnMediaType10 = MrnMediaType.INTERNAL_TAG;
        MrnMediaType mrnMediaType11 = MrnMediaType.THEME;
        List<MrnMediaType> listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new MrnMediaType[]{mrnMediaType, mrnMediaType2, mrnMediaType3, mrnMediaType4, mrnMediaType5, mrnMediaType2, mrnMediaType6, mrnMediaType7, mrnMediaType8, mrnMediaType9, mrnMediaType10, mrnMediaType11});
        catalogEntities = listListOf;
        MrnIdentifierType mrnIdentifierType = MrnIdentifierType.TITLESET_ASIN;
        MrnIdentifierType mrnIdentifierType2 = MrnIdentifierType.ASIN;
        MrnIdentifierType mrnIdentifierType3 = MrnIdentifierType.UUID;
        MrnIdentifierType[] mrnIdentifierTypeArr = {mrnIdentifierType2, mrnIdentifierType3, MrnIdentifierType.PLAYLIST_ID};
        MrnIdentifierType mrnIdentifierType4 = MrnIdentifierType.STATION_KEY;
        MrnIdentifierType mrnIdentifierType5 = MrnIdentifierType.STATION_SEED_ID;
        MrnIdentifierType[] mrnIdentifierTypeArr2 = {mrnIdentifierType4, mrnIdentifierType5, mrnIdentifierType3};
        MrnMediaType mrnMediaType12 = MrnMediaType.QUEUE_ID;
        MrnIdentifierType[] mrnIdentifierTypeArr3 = {mrnIdentifierType4, mrnIdentifierType5, mrnIdentifierType3};
        MrnIdentifierType mrnIdentifierType6 = MrnIdentifierType.TAG;
        MrnMediaType mrnMediaType13 = MrnMediaType.EPISODE;
        validMediaTypeToIdentifierTypeMap = MapsKt__MapsKt.mapOf(TuplesKt.to(mrnMediaType, CollectionsKt__CollectionsKt.listOf((Object[]) new MrnIdentifierType[]{mrnIdentifierType, mrnIdentifierType2, MrnIdentifierType.OBJECT_ID, MrnIdentifierType.ASIN_LIST})), TuplesKt.to(mrnMediaType2, CollectionsKt__CollectionsJVMKt.listOf(mrnIdentifierType2)), TuplesKt.to(mrnMediaType3, CollectionsKt__CollectionsKt.listOf((Object[]) new MrnIdentifierType[]{mrnIdentifierType, mrnIdentifierType2})), TuplesKt.to(mrnMediaType4, CollectionsKt__CollectionsKt.listOf((Object[]) mrnIdentifierTypeArr)), TuplesKt.to(mrnMediaType5, CollectionsKt__CollectionsKt.listOf((Object[]) mrnIdentifierTypeArr2)), TuplesKt.to(mrnMediaType12, CollectionsKt__CollectionsJVMKt.listOf(mrnIdentifierType3)), TuplesKt.to(mrnMediaType5, CollectionsKt__CollectionsKt.listOf((Object[]) mrnIdentifierTypeArr3)), TuplesKt.to(mrnMediaType6, CollectionsKt__CollectionsJVMKt.listOf(mrnIdentifierType6)), TuplesKt.to(mrnMediaType7, CollectionsKt__CollectionsJVMKt.listOf(mrnIdentifierType6)), TuplesKt.to(mrnMediaType8, CollectionsKt__CollectionsJVMKt.listOf(mrnIdentifierType6)), TuplesKt.to(mrnMediaType9, CollectionsKt__CollectionsJVMKt.listOf(mrnIdentifierType6)), TuplesKt.to(mrnMediaType10, CollectionsKt__CollectionsJVMKt.listOf(mrnIdentifierType6)), TuplesKt.to(mrnMediaType11, CollectionsKt__CollectionsJVMKt.listOf(mrnIdentifierType6)), TuplesKt.to(mrnMediaType13, CollectionsKt__CollectionsJVMKt.listOf(mrnIdentifierType3)));
        validAuthorityToMediaTypeMap = MapsKt__MapsKt.mapOf(TuplesKt.to(MrnAuthority.CATALOG, listListOf), TuplesKt.to(MrnAuthority.LIBRARY, listListOf), TuplesKt.to(MrnAuthority.ALGORITHMIC, CollectionsKt__CollectionsKt.listOf((Object[]) new MrnMediaType[]{mrnMediaType5, mrnMediaType4})), TuplesKt.to(MrnAuthority.QUEUE, CollectionsKt__CollectionsJVMKt.listOf(mrnMediaType12)), TuplesKt.to(MrnAuthority.PODCAST, CollectionsKt__CollectionsJVMKt.listOf(mrnMediaType13)));
        identifierTypeToValidator = MapsKt__MapsJVMKt.mapOf(TuplesKt.to(mrnIdentifierType3, new MediaResourceNameValidator$identifierTypeToValidator$1(MrnValueValidator.INSTANCE)));
    }

    /* JADX INFO: compiled from: MediaResourceNameValidator.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mrn/v1/MediaResourceNameValidator$MrnValueValidator;", "", "()V", "uuidValidator", "", "value", "", "AmazonMusicMediaResourceNameKotlin"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class MrnValueValidator {
        public static final MrnValueValidator INSTANCE = new MrnValueValidator();

        private MrnValueValidator() {
        }

        public final void uuidValidator(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (UUIDKt.ValidateUUID(value)) {
                return;
            }
            throw new InvalidMediaResourceNameValue("Invalid UUID value. value=" + value, null, 2, null);
        }
    }
}
