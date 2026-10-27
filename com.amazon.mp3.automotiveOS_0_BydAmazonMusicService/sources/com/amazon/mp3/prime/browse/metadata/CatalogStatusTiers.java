package com.amazon.mp3.prime.browse.metadata;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.ContentAccessType;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: CatalogStatusTiers.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\t\b\u0086\b\u0018\u0000 \"2\u00020\u0001:\u0001\"BO\b\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0002¢\u0006\u0004\b\u001f\u0010 B\u0011\b\u0016\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u001f\u0010!J\u0006\u0010\u0003\u001a\u00020\u0002J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u0006\u0010\b\u001a\u00020\u0007J\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0007H\u0016J\b\u0010\u000e\u001a\u00020\u0007H\u0016JO\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u0002HÆ\u0001J\t\u0010\u0018\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\u001c\u001a\u00020\u00022\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aHÖ\u0003R\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u001d\u001a\u0004\b\u000f\u0010\u001eR\u0017\u0010\u0010\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u001d\u001a\u0004\b\u0010\u0010\u001eR\u0017\u0010\u0011\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u001d\u001a\u0004\b\u0011\u0010\u001eR\u0017\u0010\u0012\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u001d\u001a\u0004\b\u0012\u0010\u001eR\u0017\u0010\u0013\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u001d\u001a\u0004\b\u0013\u0010\u001eR\u0017\u0010\u0014\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u001d\u001a\u0004\b\u0014\u0010\u001eR\u0017\u0010\u0015\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u001d\u001a\u0004\b\u0015\u0010\u001e¨\u0006#"}, d2 = {"Lcom/amazon/mp3/prime/browse/metadata/CatalogStatusTiers;", "Landroid/os/Parcelable;", "", "isInCatalog", "", "Lcom/amazon/music/ContentAccessType;", "toSet", "", "serialize", "Landroid/os/Parcel;", "parcel", "flags", "", "writeToParcel", "describeContents", "isPrime", "isMusicSubscription", "isFree", "isFreeOnDemandPlayable", "isSonicRush", "isSonicRushOnDemandPlayable", "isSonicRushGolden", "copy", "", "toString", "hashCode", "", "other", "equals", "Z", "()Z", "<init>", "(ZZZZZZZ)V", "(Landroid/os/Parcel;)V", "CREATOR", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class CatalogStatusTiers implements Parcelable {

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean isFree;
    private final boolean isFreeOnDemandPlayable;
    private final boolean isMusicSubscription;
    private final boolean isPrime;
    private final boolean isSonicRush;
    private final boolean isSonicRushGolden;
    private final boolean isSonicRushOnDemandPlayable;

    @JvmOverloads
    public CatalogStatusTiers() {
        this(false, false, false, false, false, false, false, WorkQueueKt.MASK, null);
    }

    @JvmOverloads
    public CatalogStatusTiers(boolean z, boolean z2) {
        this(z, z2, false, false, false, false, false, 124, null);
    }

    @JvmOverloads
    public CatalogStatusTiers(boolean z, boolean z2, boolean z3, boolean z4) {
        this(z, z2, z3, z4, false, false, false, 112, null);
    }

    @JvmOverloads
    public CatalogStatusTiers(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this(z, z2, z3, z4, z5, false, false, 96, null);
    }

    @JvmOverloads
    public CatalogStatusTiers(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this(z, z2, z3, z4, z5, z6, false, 64, null);
    }

    public static /* synthetic */ CatalogStatusTiers copy$default(CatalogStatusTiers catalogStatusTiers, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i, Object obj) {
        if ((i & 1) != 0) {
            z = catalogStatusTiers.isPrime;
        }
        if ((i & 2) != 0) {
            z2 = catalogStatusTiers.isMusicSubscription;
        }
        boolean z8 = z2;
        if ((i & 4) != 0) {
            z3 = catalogStatusTiers.isFree;
        }
        boolean z9 = z3;
        if ((i & 8) != 0) {
            z4 = catalogStatusTiers.isFreeOnDemandPlayable;
        }
        boolean z10 = z4;
        if ((i & 16) != 0) {
            z5 = catalogStatusTiers.isSonicRush;
        }
        boolean z11 = z5;
        if ((i & 32) != 0) {
            z6 = catalogStatusTiers.isSonicRushOnDemandPlayable;
        }
        boolean z12 = z6;
        if ((i & 64) != 0) {
            z7 = catalogStatusTiers.isSonicRushGolden;
        }
        return catalogStatusTiers.copy(z, z8, z9, z10, z11, z12, z7);
    }

    @JvmStatic
    public static final CatalogStatusTiers fallbackValues() {
        return INSTANCE.fallbackValues();
    }

    public final CatalogStatusTiers copy(boolean isPrime, boolean isMusicSubscription, boolean isFree, boolean isFreeOnDemandPlayable, boolean isSonicRush, boolean isSonicRushOnDemandPlayable, boolean isSonicRushGolden) {
        return new CatalogStatusTiers(isPrime, isMusicSubscription, isFree, isFreeOnDemandPlayable, isSonicRush, isSonicRushOnDemandPlayable, isSonicRushGolden);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CatalogStatusTiers)) {
            return false;
        }
        CatalogStatusTiers catalogStatusTiers = (CatalogStatusTiers) other;
        return this.isPrime == catalogStatusTiers.isPrime && this.isMusicSubscription == catalogStatusTiers.isMusicSubscription && this.isFree == catalogStatusTiers.isFree && this.isFreeOnDemandPlayable == catalogStatusTiers.isFreeOnDemandPlayable && this.isSonicRush == catalogStatusTiers.isSonicRush && this.isSonicRushOnDemandPlayable == catalogStatusTiers.isSonicRushOnDemandPlayable && this.isSonicRushGolden == catalogStatusTiers.isSonicRushGolden;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v5, types: [int] */
    /* JADX WARN: Type inference failed for: r2v7, types: [int] */
    /* JADX WARN: Type inference failed for: r2v9, types: [int] */
    public int hashCode() {
        boolean z = this.isPrime;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.isMusicSubscription;
        ?? r2 = z2;
        if (z2) {
            r2 = 1;
        }
        int i2 = (i + r2) * 31;
        boolean z3 = this.isFree;
        ?? r22 = z3;
        if (z3) {
            r22 = 1;
        }
        int i3 = (i2 + r22) * 31;
        boolean z4 = this.isFreeOnDemandPlayable;
        ?? r23 = z4;
        if (z4) {
            r23 = 1;
        }
        int i4 = (i3 + r23) * 31;
        boolean z5 = this.isSonicRush;
        ?? r24 = z5;
        if (z5) {
            r24 = 1;
        }
        int i5 = (i4 + r24) * 31;
        boolean z6 = this.isSonicRushOnDemandPlayable;
        ?? r25 = z6;
        if (z6) {
            r25 = 1;
        }
        int i6 = (i5 + r25) * 31;
        boolean z7 = this.isSonicRushGolden;
        return i6 + (z7 ? 1 : z7);
    }

    public String toString() {
        return "CatalogStatusTiers(isPrime=" + this.isPrime + ", isMusicSubscription=" + this.isMusicSubscription + ", isFree=" + this.isFree + ", isFreeOnDemandPlayable=" + this.isFreeOnDemandPlayable + ", isSonicRush=" + this.isSonicRush + ", isSonicRushOnDemandPlayable=" + this.isSonicRushOnDemandPlayable + ", isSonicRushGolden=" + this.isSonicRushGolden + ')';
    }

    @JvmOverloads
    public CatalogStatusTiers(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.isPrime = z;
        this.isMusicSubscription = z2;
        this.isFree = z3;
        this.isFreeOnDemandPlayable = z4;
        this.isSonicRush = z5;
        this.isSonicRushOnDemandPlayable = z6;
        this.isSonicRushGolden = z7;
    }

    public /* synthetic */ CatalogStatusTiers(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? false : z4, (i & 16) != 0 ? false : z5, (i & 32) != 0 ? false : z6, (i & 64) != 0 ? false : z7);
    }

    /* JADX INFO: renamed from: isPrime, reason: from getter */
    public final boolean getIsPrime() {
        return this.isPrime;
    }

    /* JADX INFO: renamed from: isMusicSubscription, reason: from getter */
    public final boolean getIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    /* JADX INFO: renamed from: isFree, reason: from getter */
    public final boolean getIsFree() {
        return this.isFree;
    }

    /* JADX INFO: renamed from: isFreeOnDemandPlayable, reason: from getter */
    public final boolean getIsFreeOnDemandPlayable() {
        return this.isFreeOnDemandPlayable;
    }

    /* JADX INFO: renamed from: isSonicRush, reason: from getter */
    public final boolean getIsSonicRush() {
        return this.isSonicRush;
    }

    /* JADX INFO: renamed from: isSonicRushOnDemandPlayable, reason: from getter */
    public final boolean getIsSonicRushOnDemandPlayable() {
        return this.isSonicRushOnDemandPlayable;
    }

    /* JADX INFO: renamed from: isSonicRushGolden, reason: from getter */
    public final boolean getIsSonicRushGolden() {
        return this.isSonicRushGolden;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CatalogStatusTiers(Parcel parcel) {
        this(parcel.readByte() != 0, parcel.readByte() != 0, parcel.readByte() != 0, parcel.readByte() != 0, parcel.readByte() != 0, parcel.readByte() != 0, parcel.readByte() != 0);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
    }

    public final boolean isInCatalog() {
        return this.isFree || this.isPrime || this.isMusicSubscription || this.isSonicRush;
    }

    public final Set<ContentAccessType> toSet() {
        HashSet hashSet = new HashSet();
        if (this.isPrime) {
            hashSet.add(ContentAccessType.PRIME);
        }
        if (this.isMusicSubscription) {
            hashSet.add(ContentAccessType.HAWKFIRE);
        }
        if (this.isFree) {
            hashSet.add(ContentAccessType.NIGHTWING);
        }
        if (this.isFreeOnDemandPlayable) {
            hashSet.add(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE);
        }
        if (this.isSonicRush) {
            hashSet.add(ContentAccessType.SONIC_RUSH);
        }
        if (this.isSonicRushOnDemandPlayable) {
            hashSet.add(ContentAccessType.SONIC_RUSH_ONDEMAND_PLAYABLE);
        }
        if (this.isSonicRushGolden) {
            hashSet.add(ContentAccessType.SONIC_RUSH_GOLDEN);
        }
        return hashSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [int] */
    public final int serialize() {
        boolean z = this.isPrime;
        ?? r0 = z;
        if (this.isMusicSubscription) {
            r0 = (z ? 1 : 0) | 2;
        }
        ?? r02 = r0;
        if (this.isFree) {
            r02 = (r0 == true ? 1 : 0) | 4;
        }
        ?? r03 = r02;
        if (this.isFreeOnDemandPlayable) {
            r03 = (r02 == true ? 1 : 0) | 8;
        }
        ?? r04 = r03;
        if (this.isSonicRush) {
            r04 = (r03 == true ? 1 : 0) | 16;
        }
        ?? r05 = r04;
        if (this.isSonicRushOnDemandPlayable) {
            r05 = (r04 == true ? 1 : 0) | 32;
        }
        return this.isSonicRushGolden ? r05 | 64 : r05;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeByte(this.isPrime ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isMusicSubscription ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isFree ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isFreeOnDemandPlayable ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isSonicRush ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isSonicRushOnDemandPlayable ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isSonicRushGolden ? (byte) 1 : (byte) 0);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: CatalogStatusTiers.kt */
    @Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000e\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u001f\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0014\u0010\u0011\u001a\u00020\u00022\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u000e\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0012J\b\u0010\u0014\u001a\u00020\u0002H\u0007J\u000e\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\t¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/prime/browse/metadata/CatalogStatusTiers$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/amazon/mp3/prime/browse/metadata/CatalogStatusTiers;", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "createFromSubscription", "Landroid/os/Parcel;", "parcel", "createFromParcel", "", "size", "", "newArray", "(I)[Lcom/amazon/mp3/prime/browse/metadata/CatalogStatusTiers;", "", "Lcom/amazon/music/ContentAccessType;", "set", "from", "Lcom/amazon/music/media/playback/access/MediaAccessInfo;", "mediaAccessInfo", "fallbackValues", "serializedInt", "deserialize", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion implements Parcelable.Creator<CatalogStatusTiers> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CatalogStatusTiers createFromSubscription(SubscriptionType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            boolean z = type == SubscriptionType.PRIME;
            boolean z2 = type == SubscriptionType.MUSIC_HD || type == SubscriptionType.MUSIC_HOME || type == SubscriptionType.MUSIC_UNLIMITED;
            SubscriptionType subscriptionType = SubscriptionType.FREE;
            boolean z3 = type == subscriptionType;
            boolean z4 = type == subscriptionType;
            SubscriptionType subscriptionType2 = SubscriptionType.SONIC_RUSH;
            return new CatalogStatusTiers(z, z2, z3, z4, type == subscriptionType2, type == subscriptionType2, false);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CatalogStatusTiers createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new CatalogStatusTiers(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CatalogStatusTiers[] newArray(int size) {
            return new CatalogStatusTiers[size];
        }

        public final CatalogStatusTiers from(Set<? extends ContentAccessType> set) {
            Intrinsics.checkNotNullParameter(set, "set");
            return new CatalogStatusTiers(set.contains(ContentAccessType.PRIME), set.contains(ContentAccessType.HAWKFIRE), set.contains(ContentAccessType.NIGHTWING), set.contains(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE), set.contains(ContentAccessType.SONIC_RUSH), set.contains(ContentAccessType.SONIC_RUSH_ONDEMAND_PLAYABLE), set.contains(ContentAccessType.SONIC_RUSH_GOLDEN));
        }

        public final CatalogStatusTiers from(MediaAccessInfo mediaAccessInfo) {
            Intrinsics.checkNotNullParameter(mediaAccessInfo, "mediaAccessInfo");
            return new CatalogStatusTiers(mediaAccessInfo.isPrime(), mediaAccessInfo.isHawkfire(), mediaAccessInfo.isFree(), mediaAccessInfo.isFreeOnDemandPlayable(), mediaAccessInfo.isSonicRush(), mediaAccessInfo.isSonicRushOnDemandPlayable(), mediaAccessInfo.isSonicRushGolden());
        }

        @JvmStatic
        public final CatalogStatusTiers fallbackValues() {
            return new CatalogStatusTiers(false, false, false, false, false, false, false, WorkQueueKt.MASK, null);
        }

        public final CatalogStatusTiers deserialize(int serializedInt) {
            return new CatalogStatusTiers((serializedInt & 1) == 1, ((serializedInt >> 1) & 1) == 1, ((serializedInt >> 2) & 1) == 1, ((serializedInt >> 3) & 1) == 1, ((serializedInt >> 4) & 1) == 1, ((serializedInt >> 5) & 1) == 1, ((serializedInt >> 6) & 1) == 1);
        }
    }
}
