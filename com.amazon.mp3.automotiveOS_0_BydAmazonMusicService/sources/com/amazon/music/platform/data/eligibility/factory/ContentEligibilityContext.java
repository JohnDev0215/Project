package com.amazon.music.platform.data.eligibility.factory;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.data.eligibility.ContentEligibilityInput;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: ContentEligibilityContext.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0086\b\u0018\u00002\u00020\u0001B\u007f\b\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\u000f\u001a\u00020\b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\b\u0012\b\b\u0002\u0010\u0013\u001a\u00020\b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\b\u0012\b\b\u0002\u0010\u0016\u001a\u00020\b\u0012\b\b\u0002\u0010\u0017\u001a\u00020\b\u0012\b\b\u0002\u0010\u0018\u001a\u00020\b\u0012\b\b\u0002\u0010\u0019\u001a\u00020\b\u0012\b\b\u0002\u0010\u001a\u001a\u00020\b\u0012\b\b\u0002\u0010\u001b\u001a\u00020\b¢\u0006\u0004\b\u001d\u0010\u001eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000f\u0010\u0011R\u0017\u0010\u0012\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0010\u001a\u0004\b\u0012\u0010\u0011R\u0017\u0010\u0013\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0014\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0010\u001a\u0004\b\u0014\u0010\u0011R\u0017\u0010\u0015\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0015\u0010\u0011R\u0017\u0010\u0016\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0016\u0010\u0011R\u0017\u0010\u0017\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0010\u001a\u0004\b\u0017\u0010\u0011R\u0017\u0010\u0018\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0018\u0010\u0011R\u0017\u0010\u0019\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0010\u001a\u0004\b\u0019\u0010\u0011R\u0017\u0010\u001a\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u0010\u001a\u0004\b\u001a\u0010\u0011R\u0017\u0010\u001b\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u001c\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/factory/ContentEligibilityContext;", "Lcom/amazon/music/platform/data/eligibility/factory/EligibilityContext;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "entityItemIdentifier", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getEntityItemIdentifier", "()Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "isUnavailable", "Z", "()Z", "isOwned", "isFree", "isPrime", "isSonicRush", "isMusicUnlimited", "isFreeOnDemandPlayable", "isSonicRushOnDemandPlayable", "isDownloaded", "isAvailable", "canBeOnDemandByService", "getCanBeOnDemandByService", "<init>", "(Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;ZZZZZZZZZZZ)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ContentEligibilityContext extends EligibilityContext {
    private final boolean canBeOnDemandByService;
    private final EntityItemIdentifier entityItemIdentifier;
    private final boolean isAvailable;
    private final boolean isDownloaded;
    private final boolean isFree;
    private final boolean isFreeOnDemandPlayable;
    private final boolean isMusicUnlimited;
    private final boolean isOwned;
    private final boolean isPrime;
    private final boolean isSonicRush;
    private final boolean isSonicRushOnDemandPlayable;
    private final boolean isUnavailable;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ContentEligibilityContext(EntityItemIdentifier entityItemIdentifier, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
        this(entityItemIdentifier, z, z2, z3, z4, z5, z6, z7, z8, false, false, false, 3584, null);
        Intrinsics.checkNotNullParameter(entityItemIdentifier, "entityItemIdentifier");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ContentEligibilityContext(EntityItemIdentifier entityItemIdentifier, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10) {
        this(entityItemIdentifier, z, z2, z3, z4, z5, z6, z7, z8, z9, z10, false, 2048, null);
        Intrinsics.checkNotNullParameter(entityItemIdentifier, "entityItemIdentifier");
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContentEligibilityContext)) {
            return false;
        }
        ContentEligibilityContext contentEligibilityContext = (ContentEligibilityContext) other;
        return Intrinsics.areEqual(this.entityItemIdentifier, contentEligibilityContext.entityItemIdentifier) && this.isUnavailable == contentEligibilityContext.isUnavailable && this.isOwned == contentEligibilityContext.isOwned && this.isFree == contentEligibilityContext.isFree && this.isPrime == contentEligibilityContext.isPrime && this.isSonicRush == contentEligibilityContext.isSonicRush && this.isMusicUnlimited == contentEligibilityContext.isMusicUnlimited && this.isFreeOnDemandPlayable == contentEligibilityContext.isFreeOnDemandPlayable && this.isSonicRushOnDemandPlayable == contentEligibilityContext.isSonicRushOnDemandPlayable && this.isDownloaded == contentEligibilityContext.isDownloaded && this.isAvailable == contentEligibilityContext.isAvailable && this.canBeOnDemandByService == contentEligibilityContext.canBeOnDemandByService;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v11, types: [int] */
    /* JADX WARN: Type inference failed for: r1v13, types: [int] */
    /* JADX WARN: Type inference failed for: r1v15, types: [int] */
    /* JADX WARN: Type inference failed for: r1v17, types: [int] */
    /* JADX WARN: Type inference failed for: r1v19, types: [int] */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r1v39 */
    /* JADX WARN: Type inference failed for: r1v40 */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    /* JADX WARN: Type inference failed for: r1v7, types: [int] */
    /* JADX WARN: Type inference failed for: r1v9, types: [int] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v2 */
    public int hashCode() {
        int iHashCode = this.entityItemIdentifier.hashCode() * 31;
        boolean z = this.isUnavailable;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode + r1) * 31;
        boolean z2 = this.isOwned;
        ?? r12 = z2;
        if (z2) {
            r12 = 1;
        }
        int i2 = (i + r12) * 31;
        boolean z3 = this.isFree;
        ?? r13 = z3;
        if (z3) {
            r13 = 1;
        }
        int i3 = (i2 + r13) * 31;
        boolean z4 = this.isPrime;
        ?? r14 = z4;
        if (z4) {
            r14 = 1;
        }
        int i4 = (i3 + r14) * 31;
        boolean z5 = this.isSonicRush;
        ?? r15 = z5;
        if (z5) {
            r15 = 1;
        }
        int i5 = (i4 + r15) * 31;
        boolean z6 = this.isMusicUnlimited;
        ?? r16 = z6;
        if (z6) {
            r16 = 1;
        }
        int i6 = (i5 + r16) * 31;
        boolean z7 = this.isFreeOnDemandPlayable;
        ?? r17 = z7;
        if (z7) {
            r17 = 1;
        }
        int i7 = (i6 + r17) * 31;
        boolean z8 = this.isSonicRushOnDemandPlayable;
        ?? r18 = z8;
        if (z8) {
            r18 = 1;
        }
        int i8 = (i7 + r18) * 31;
        boolean z9 = this.isDownloaded;
        ?? r19 = z9;
        if (z9) {
            r19 = 1;
        }
        int i9 = (i8 + r19) * 31;
        boolean z10 = this.isAvailable;
        ?? r110 = z10;
        if (z10) {
            r110 = 1;
        }
        int i10 = (i9 + r110) * 31;
        boolean z11 = this.canBeOnDemandByService;
        return i10 + (z11 ? 1 : z11);
    }

    public String toString() {
        return "ContentEligibilityContext(entityItemIdentifier=" + this.entityItemIdentifier + ", isUnavailable=" + this.isUnavailable + ", isOwned=" + this.isOwned + ", isFree=" + this.isFree + ", isPrime=" + this.isPrime + ", isSonicRush=" + this.isSonicRush + ", isMusicUnlimited=" + this.isMusicUnlimited + ", isFreeOnDemandPlayable=" + this.isFreeOnDemandPlayable + ", isSonicRushOnDemandPlayable=" + this.isSonicRushOnDemandPlayable + ", isDownloaded=" + this.isDownloaded + ", isAvailable=" + this.isAvailable + ", canBeOnDemandByService=" + this.canBeOnDemandByService + ')';
    }

    public /* synthetic */ ContentEligibilityContext(EntityItemIdentifier entityItemIdentifier, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(entityItemIdentifier, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? false : z3, (i & 16) != 0 ? false : z4, (i & 32) != 0 ? false : z5, (i & 64) != 0 ? false : z6, (i & 128) != 0 ? false : z7, (i & 256) != 0 ? false : z8, (i & 512) != 0 ? false : z9, (i & 1024) != 0 ? false : z10, (i & 2048) == 0 ? z11 : false);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ContentEligibilityContext(EntityItemIdentifier entityItemIdentifier, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11) {
        super(CollectionsKt__CollectionsKt.emptyList());
        Intrinsics.checkNotNullParameter(entityItemIdentifier, "entityItemIdentifier");
        this.entityItemIdentifier = entityItemIdentifier;
        this.isUnavailable = z;
        this.isOwned = z2;
        this.isFree = z3;
        this.isPrime = z4;
        this.isSonicRush = z5;
        this.isMusicUnlimited = z6;
        this.isFreeOnDemandPlayable = z7;
        this.isSonicRushOnDemandPlayable = z8;
        this.isDownloaded = z9;
        this.isAvailable = z10;
        this.canBeOnDemandByService = z11;
        getInputs().put(Reflection.getOrCreateKotlinClass(entityItemIdentifier.getClass()), entityItemIdentifier);
        if (z2) {
            add$MusicPlatformCore_release(ContentEligibilityInput.IsOwned.INSTANCE);
        }
        if (z10) {
            add$MusicPlatformCore_release(ContentEligibilityInput.IsAvailable.INSTANCE);
        }
        if (z3) {
            add$MusicPlatformCore_release(ContentEligibilityInput.IsFree.INSTANCE);
        }
        if (z7) {
            add$MusicPlatformCore_release(ContentEligibilityInput.IsFreeOnDemandPlayable.INSTANCE);
        }
        if (z4) {
            add$MusicPlatformCore_release(ContentEligibilityInput.IsPrime.INSTANCE);
        }
        if (z5) {
            add$MusicPlatformCore_release(ContentEligibilityInput.IsSonicRush.INSTANCE);
        }
        if (z8) {
            add$MusicPlatformCore_release(ContentEligibilityInput.IsSonicRushOnDemandPlayable.INSTANCE);
        }
        if (z6) {
            add$MusicPlatformCore_release(ContentEligibilityInput.IsMusicUnlimited.INSTANCE);
        }
        if (z9) {
            add$MusicPlatformCore_release(ContentEligibilityInput.IsDownloaded.INSTANCE);
        }
        if (z11) {
            add$MusicPlatformCore_release(ContentEligibilityInput.IsOnDemandByService.INSTANCE);
        }
        PlatformProviderUtilKt.freeze(this);
    }
}
