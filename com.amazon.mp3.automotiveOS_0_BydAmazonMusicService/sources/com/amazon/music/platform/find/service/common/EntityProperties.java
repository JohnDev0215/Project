package com.amazon.music.platform.find.service.common;

import com.amazon.music.platform.find.common.AssetQuality;
import com.amazon.music.platform.find.common.AssetQuality$$serializer;
import com.amazon.music.platform.find.common.ContentTier;
import com.amazon.music.platform.find.common.ParentalControls;
import com.amazon.music.platform.find.common.ParentalControls$$serializer;
import com.amazon.music.platform.find.common.ParentalControlsPerContentTier;
import com.amazon.music.platform.find.common.ParentalControlsPerContentTier$$serializer;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: EntityProperties.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 O2\u00020\u0001:\u0002POBý\u0001\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\t\u0012\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u00102\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u000b\u0012\u0010\b\u0002\u0010;\u001a\n\u0012\u0004\u0012\u00020:\u0018\u00010\u001c\u0012\u0010\b\u0002\u0010=\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u001c\u0012\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\bI\u0010JBñ\u0001\b\u0017\u0012\u0006\u0010K\u001a\u00020\u000b\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\t\u0012\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c\u0012\b\u0010#\u001a\u0004\u0018\u00010\"\u0012\b\u0010(\u001a\u0004\u0018\u00010'\u0012\b\u0010,\u001a\u0004\u0018\u00010\t\u0012\b\u0010.\u001a\u0004\u0018\u00010\u000e\u0012\b\u00101\u001a\u0004\u0018\u00010\u000e\u0012\b\u00102\u001a\u0004\u0018\u00010\t\u0012\b\u00104\u001a\u0004\u0018\u00010\u000e\u0012\b\u00106\u001a\u0004\u0018\u00010\u000b\u0012\u000e\u0010;\u001a\n\u0012\u0004\u0012\u00020:\u0018\u00010\u001c\u0012\u000e\u0010=\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u001c\u0012\n\b\u0001\u0010?\u001a\u0004\u0018\u00010\t\u0012\n\b\u0001\u0010C\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0001\u0010F\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010M\u001a\u0004\u0018\u00010L¢\u0006\u0004\bI\u0010NJ!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005HÇ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u0013R\u0019\u0010\u001a\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u0013R\u001f\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0019\u0010#\u001a\u0004\u0018\u00010\"8\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u0019\u0010(\u001a\u0004\u0018\u00010'8\u0006¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u0019\u0010,\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b,\u0010\u0011\u001a\u0004\b-\u0010\u0013R\u0019\u0010.\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b.\u00100R\u0019\u00101\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b1\u0010/\u001a\u0004\b1\u00100R\u0019\u00102\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b2\u0010\u0011\u001a\u0004\b3\u0010\u0013R\u0019\u00104\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b4\u0010/\u001a\u0004\b5\u00100R\u0019\u00106\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\u001f\u0010;\u001a\n\u0012\u0004\u0012\u00020:\u0018\u00010\u001c8\u0006¢\u0006\f\n\u0004\b;\u0010\u001f\u001a\u0004\b<\u0010!R\u001f\u0010=\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u001c8\u0006¢\u0006\f\n\u0004\b=\u0010\u001f\u001a\u0004\b>\u0010!R\"\u0010?\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b?\u0010\u0011\u0012\u0004\bA\u0010B\u001a\u0004\b@\u0010\u0013R\"\u0010C\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\bC\u0010/\u0012\u0004\bE\u0010B\u001a\u0004\bD\u00100R\"\u0010F\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\bF\u0010/\u0012\u0004\bH\u0010B\u001a\u0004\bG\u00100¨\u0006Q"}, d2 = {"Lcom/amazon/music/platform/find/service/common/EntityProperties;", "", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "", "toString", "", "hashCode", "other", "", "equals", "artistAsin", "Ljava/lang/String;", "getArtistAsin", "()Ljava/lang/String;", "artistName", "getArtistName", "albumAsin", "getAlbumAsin", "albumName", "getAlbumName", "subTitle", "getSubTitle", "", "Lcom/amazon/music/platform/find/common/ContentTier;", "contentTier", "Ljava/util/List;", "getContentTier", "()Ljava/util/List;", "Lcom/amazon/music/platform/find/common/ParentalControlsPerContentTier;", "parentalControlsPerTier", "Lcom/amazon/music/platform/find/common/ParentalControlsPerContentTier;", "getParentalControlsPerTier", "()Lcom/amazon/music/platform/find/common/ParentalControlsPerContentTier;", "Lcom/amazon/music/platform/find/common/ParentalControls;", "parentControls", "Lcom/amazon/music/platform/find/common/ParentalControls;", "getParentControls", "()Lcom/amazon/music/platform/find/common/ParentalControls;", "podcastEntityVariantId", "getPodcastEntityVariantId", "isOnDemand", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "isSfa", "curator", "getCurator", "purchased", "getPurchased", "durationInMilliseconds", "Ljava/lang/Integer;", "getDurationInMilliseconds", "()Ljava/lang/Integer;", "Lcom/amazon/music/platform/find/common/AssetQuality;", "assetQualities", "getAssetQualities", "languageOfPerformances", "getLanguageOfPerformances", "variantId", "getVariantId", "getVariantId$annotations", "()V", "onDemand", "getOnDemand", "getOnDemand$annotations", "sfa", "getSfa", "getSfa$annotations", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/amazon/music/platform/find/common/ParentalControlsPerContentTier;Lcom/amazon/music/platform/find/common/ParentalControls;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/amazon/music/platform/find/common/ParentalControlsPerContentTier;Lcom/amazon/music/platform/find/common/ParentalControls;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "$serializer", "MusicPlatformFind_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public final /* data */ class EntityProperties {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String albumAsin;
    private final String albumName;
    private final String artistAsin;
    private final String artistName;
    private final List<AssetQuality> assetQualities;
    private final List<ContentTier> contentTier;
    private final String curator;
    private final Integer durationInMilliseconds;
    private final Boolean isOnDemand;
    private final Boolean isSfa;
    private final List<String> languageOfPerformances;
    private final Boolean onDemand;
    private final ParentalControls parentControls;
    private final ParentalControlsPerContentTier parentalControlsPerTier;
    private final String podcastEntityVariantId;
    private final Boolean purchased;
    private final Boolean sfa;
    private final String subTitle;
    private final String variantId;

    public EntityProperties() {
        this((String) null, (String) null, (String) null, (String) null, (String) null, (List) null, (ParentalControlsPerContentTier) null, (ParentalControls) null, (String) null, (Boolean) null, (Boolean) null, (String) null, (Boolean) null, (Integer) null, (List) null, (List) null, (String) null, (Boolean) null, (Boolean) null, 524287, (DefaultConstructorMarker) null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EntityProperties)) {
            return false;
        }
        EntityProperties entityProperties = (EntityProperties) other;
        return Intrinsics.areEqual(this.artistAsin, entityProperties.artistAsin) && Intrinsics.areEqual(this.artistName, entityProperties.artistName) && Intrinsics.areEqual(this.albumAsin, entityProperties.albumAsin) && Intrinsics.areEqual(this.albumName, entityProperties.albumName) && Intrinsics.areEqual(this.subTitle, entityProperties.subTitle) && Intrinsics.areEqual(this.contentTier, entityProperties.contentTier) && Intrinsics.areEqual(this.parentalControlsPerTier, entityProperties.parentalControlsPerTier) && Intrinsics.areEqual(this.parentControls, entityProperties.parentControls) && Intrinsics.areEqual(this.podcastEntityVariantId, entityProperties.podcastEntityVariantId) && Intrinsics.areEqual(this.isOnDemand, entityProperties.isOnDemand) && Intrinsics.areEqual(this.isSfa, entityProperties.isSfa) && Intrinsics.areEqual(this.curator, entityProperties.curator) && Intrinsics.areEqual(this.purchased, entityProperties.purchased) && Intrinsics.areEqual(this.durationInMilliseconds, entityProperties.durationInMilliseconds) && Intrinsics.areEqual(this.assetQualities, entityProperties.assetQualities) && Intrinsics.areEqual(this.languageOfPerformances, entityProperties.languageOfPerformances) && Intrinsics.areEqual(this.variantId, entityProperties.variantId) && Intrinsics.areEqual(this.onDemand, entityProperties.onDemand) && Intrinsics.areEqual(this.sfa, entityProperties.sfa);
    }

    public int hashCode() {
        String str = this.artistAsin;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.artistName;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.albumAsin;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.albumName;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.subTitle;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        List<ContentTier> list = this.contentTier;
        int iHashCode6 = (iHashCode5 + (list == null ? 0 : list.hashCode())) * 31;
        ParentalControlsPerContentTier parentalControlsPerContentTier = this.parentalControlsPerTier;
        int iHashCode7 = (iHashCode6 + (parentalControlsPerContentTier == null ? 0 : parentalControlsPerContentTier.hashCode())) * 31;
        ParentalControls parentalControls = this.parentControls;
        int iHashCode8 = (iHashCode7 + (parentalControls == null ? 0 : parentalControls.hashCode())) * 31;
        String str6 = this.podcastEntityVariantId;
        int iHashCode9 = (iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Boolean bool = this.isOnDemand;
        int iHashCode10 = (iHashCode9 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.isSfa;
        int iHashCode11 = (iHashCode10 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        String str7 = this.curator;
        int iHashCode12 = (iHashCode11 + (str7 == null ? 0 : str7.hashCode())) * 31;
        Boolean bool3 = this.purchased;
        int iHashCode13 = (iHashCode12 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Integer num = this.durationInMilliseconds;
        int iHashCode14 = (iHashCode13 + (num == null ? 0 : num.hashCode())) * 31;
        List<AssetQuality> list2 = this.assetQualities;
        int iHashCode15 = (iHashCode14 + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<String> list3 = this.languageOfPerformances;
        int iHashCode16 = (iHashCode15 + (list3 == null ? 0 : list3.hashCode())) * 31;
        String str8 = this.variantId;
        int iHashCode17 = (iHashCode16 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Boolean bool4 = this.onDemand;
        int iHashCode18 = (iHashCode17 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        Boolean bool5 = this.sfa;
        return iHashCode18 + (bool5 != null ? bool5.hashCode() : 0);
    }

    public String toString() {
        return "EntityProperties(artistAsin=" + this.artistAsin + ", artistName=" + this.artistName + ", albumAsin=" + this.albumAsin + ", albumName=" + this.albumName + ", subTitle=" + this.subTitle + ", contentTier=" + this.contentTier + ", parentalControlsPerTier=" + this.parentalControlsPerTier + ", parentControls=" + this.parentControls + ", podcastEntityVariantId=" + this.podcastEntityVariantId + ", isOnDemand=" + this.isOnDemand + ", isSfa=" + this.isSfa + ", curator=" + this.curator + ", purchased=" + this.purchased + ", durationInMilliseconds=" + this.durationInMilliseconds + ", assetQualities=" + this.assetQualities + ", languageOfPerformances=" + this.languageOfPerformances + ", variantId=" + this.variantId + ", onDemand=" + this.onDemand + ", sfa=" + this.sfa + ')';
    }

    /* JADX INFO: compiled from: EntityProperties.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/platform/find/service/common/EntityProperties$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/music/platform/find/service/common/EntityProperties;", "MusicPlatformFind_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<EntityProperties> serializer() {
            return EntityProperties$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ EntityProperties(int i, String str, String str2, String str3, String str4, String str5, List list, ParentalControlsPerContentTier parentalControlsPerContentTier, ParentalControls parentalControls, String str6, Boolean bool, Boolean bool2, String str7, Boolean bool3, Integer num, List list2, List list3, @Deprecated(level = DeprecationLevel.WARNING, message = "Was used for legacy iOS serialization", replaceWith = @ReplaceWith(expression = "podcastEntityVariantId", imports = {})) String str8, @Deprecated(level = DeprecationLevel.WARNING, message = "Was used for legacy Android serialization", replaceWith = @ReplaceWith(expression = "isOnDemand", imports = {})) Boolean bool4, @Deprecated(level = DeprecationLevel.WARNING, message = "Was used for legacy Android serialization", replaceWith = @ReplaceWith(expression = "isSfa", imports = {})) Boolean bool5, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(i, 0, EntityProperties$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.artistAsin = null;
        } else {
            this.artistAsin = str;
        }
        if ((i & 2) == 0) {
            this.artistName = null;
        } else {
            this.artistName = str2;
        }
        if ((i & 4) == 0) {
            this.albumAsin = null;
        } else {
            this.albumAsin = str3;
        }
        if ((i & 8) == 0) {
            this.albumName = null;
        } else {
            this.albumName = str4;
        }
        if ((i & 16) == 0) {
            this.subTitle = null;
        } else {
            this.subTitle = str5;
        }
        if ((i & 32) == 0) {
            this.contentTier = null;
        } else {
            this.contentTier = list;
        }
        if ((i & 64) == 0) {
            this.parentalControlsPerTier = null;
        } else {
            this.parentalControlsPerTier = parentalControlsPerContentTier;
        }
        if ((i & 128) == 0) {
            this.parentControls = null;
        } else {
            this.parentControls = parentalControls;
        }
        if ((i & 256) == 0) {
            this.podcastEntityVariantId = null;
        } else {
            this.podcastEntityVariantId = str6;
        }
        if ((i & 512) == 0) {
            this.isOnDemand = null;
        } else {
            this.isOnDemand = bool;
        }
        if ((i & 1024) == 0) {
            this.isSfa = null;
        } else {
            this.isSfa = bool2;
        }
        if ((i & 2048) == 0) {
            this.curator = null;
        } else {
            this.curator = str7;
        }
        if ((i & 4096) == 0) {
            this.purchased = null;
        } else {
            this.purchased = bool3;
        }
        if ((i & 8192) == 0) {
            this.durationInMilliseconds = null;
        } else {
            this.durationInMilliseconds = num;
        }
        if ((i & 16384) == 0) {
            this.assetQualities = null;
        } else {
            this.assetQualities = list2;
        }
        if ((32768 & i) == 0) {
            this.languageOfPerformances = null;
        } else {
            this.languageOfPerformances = list3;
        }
        if ((65536 & i) == 0) {
            this.variantId = null;
        } else {
            this.variantId = str8;
        }
        if ((131072 & i) == 0) {
            this.onDemand = null;
        } else {
            this.onDemand = bool4;
        }
        if ((i & 262144) == 0) {
            this.sfa = null;
        } else {
            this.sfa = bool5;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EntityProperties(String str, String str2, String str3, String str4, String str5, List<? extends ContentTier> list, ParentalControlsPerContentTier parentalControlsPerContentTier, ParentalControls parentalControls, String str6, Boolean bool, Boolean bool2, String str7, Boolean bool3, Integer num, List<AssetQuality> list2, List<String> list3, String str8, Boolean bool4, Boolean bool5) {
        this.artistAsin = str;
        this.artistName = str2;
        this.albumAsin = str3;
        this.albumName = str4;
        this.subTitle = str5;
        this.contentTier = list;
        this.parentalControlsPerTier = parentalControlsPerContentTier;
        this.parentControls = parentalControls;
        this.podcastEntityVariantId = str6;
        this.isOnDemand = bool;
        this.isSfa = bool2;
        this.curator = str7;
        this.purchased = bool3;
        this.durationInMilliseconds = num;
        this.assetQualities = list2;
        this.languageOfPerformances = list3;
        this.variantId = str8;
        this.onDemand = bool4;
        this.sfa = bool5;
    }

    @JvmStatic
    public static final void write$Self(EntityProperties self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.artistAsin != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.artistAsin);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.artistName != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.artistName);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.albumAsin != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.albumAsin);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.albumName != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.albumName);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.subTitle != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.subTitle);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.contentTier != null) {
            output.encodeNullableSerializableElement(serialDesc, 5, new ArrayListSerializer(new EnumSerializer("com.amazon.music.platform.find.common.ContentTier", ContentTier.values())), self.contentTier);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.parentalControlsPerTier != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, ParentalControlsPerContentTier$$serializer.INSTANCE, self.parentalControlsPerTier);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.parentControls != null) {
            output.encodeNullableSerializableElement(serialDesc, 7, ParentalControls$$serializer.INSTANCE, self.parentControls);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.podcastEntityVariantId != null) {
            output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.podcastEntityVariantId);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 9) || self.isOnDemand != null) {
            output.encodeNullableSerializableElement(serialDesc, 9, BooleanSerializer.INSTANCE, self.isOnDemand);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 10) || self.isSfa != null) {
            output.encodeNullableSerializableElement(serialDesc, 10, BooleanSerializer.INSTANCE, self.isSfa);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 11) || self.curator != null) {
            output.encodeNullableSerializableElement(serialDesc, 11, StringSerializer.INSTANCE, self.curator);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 12) || self.purchased != null) {
            output.encodeNullableSerializableElement(serialDesc, 12, BooleanSerializer.INSTANCE, self.purchased);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 13) || self.durationInMilliseconds != null) {
            output.encodeNullableSerializableElement(serialDesc, 13, IntSerializer.INSTANCE, self.durationInMilliseconds);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 14) || self.assetQualities != null) {
            output.encodeNullableSerializableElement(serialDesc, 14, new ArrayListSerializer(AssetQuality$$serializer.INSTANCE), self.assetQualities);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 15) || self.languageOfPerformances != null) {
            output.encodeNullableSerializableElement(serialDesc, 15, new ArrayListSerializer(StringSerializer.INSTANCE), self.languageOfPerformances);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 16) || self.variantId != null) {
            output.encodeNullableSerializableElement(serialDesc, 16, StringSerializer.INSTANCE, self.variantId);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 17) || self.onDemand != null) {
            output.encodeNullableSerializableElement(serialDesc, 17, BooleanSerializer.INSTANCE, self.onDemand);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 18) || self.sfa != null) {
            output.encodeNullableSerializableElement(serialDesc, 18, BooleanSerializer.INSTANCE, self.sfa);
        }
    }

    public /* synthetic */ EntityProperties(String str, String str2, String str3, String str4, String str5, List list, ParentalControlsPerContentTier parentalControlsPerContentTier, ParentalControls parentalControls, String str6, Boolean bool, Boolean bool2, String str7, Boolean bool3, Integer num, List list2, List list3, String str8, Boolean bool4, Boolean bool5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : list, (i & 64) != 0 ? null : parentalControlsPerContentTier, (i & 128) != 0 ? null : parentalControls, (i & 256) != 0 ? null : str6, (i & 512) != 0 ? null : bool, (i & 1024) != 0 ? null : bool2, (i & 2048) != 0 ? null : str7, (i & 4096) != 0 ? null : bool3, (i & 8192) != 0 ? null : num, (i & 16384) != 0 ? null : list2, (i & 32768) != 0 ? null : list3, (i & 65536) != 0 ? null : str8, (i & 131072) != 0 ? null : bool4, (i & 262144) != 0 ? null : bool5);
    }

    public final String getArtistAsin() {
        return this.artistAsin;
    }

    public final String getArtistName() {
        return this.artistName;
    }

    public final String getAlbumAsin() {
        return this.albumAsin;
    }

    public final String getAlbumName() {
        return this.albumName;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final List<ContentTier> getContentTier() {
        return this.contentTier;
    }

    public final ParentalControlsPerContentTier getParentalControlsPerTier() {
        return this.parentalControlsPerTier;
    }

    public final ParentalControls getParentControls() {
        return this.parentControls;
    }

    public final String getPodcastEntityVariantId() {
        return this.podcastEntityVariantId;
    }

    /* JADX INFO: renamed from: isOnDemand, reason: from getter */
    public final Boolean getIsOnDemand() {
        return this.isOnDemand;
    }

    /* JADX INFO: renamed from: isSfa, reason: from getter */
    public final Boolean getIsSfa() {
        return this.isSfa;
    }

    public final String getCurator() {
        return this.curator;
    }

    public final Boolean getPurchased() {
        return this.purchased;
    }

    public final Integer getDurationInMilliseconds() {
        return this.durationInMilliseconds;
    }

    public final List<AssetQuality> getAssetQualities() {
        return this.assetQualities;
    }
}
