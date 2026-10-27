package com.amazon.avod.ads.api.internal;

import com.amazon.avod.ads.api.AdException;
import com.amazon.avod.ads.api.AdInfoErrorCode;
import com.amazon.avod.ads.api.AdNetworkException;
import com.amazon.avod.ads.api.AdResolutionException;
import com.amazon.avod.ads.api.AdSkipInfo;
import com.amazon.avod.ads.api.Creative;
import com.amazon.avod.ads.api.CreativeCompanionAds;
import com.amazon.avod.ads.api.CreativeLinear;
import com.amazon.avod.ads.api.CreativeNonLinearAds;
import com.amazon.avod.ads.api.CreativeType;
import com.amazon.avod.ads.api.Duration;
import com.amazon.avod.ads.api.internal.AdInfoNode;
import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.vast.VastAdData;
import com.amazon.avod.ads.parser.vast.VastCompanionAds;
import com.amazon.avod.ads.parser.vast.VastExtension;
import com.amazon.avod.ads.parser.vast.VastExtensionSkipOffset;
import com.amazon.avod.ads.parser.vast.VastExtensionType;
import com.amazon.avod.ads.parser.vast.VastInline;
import com.amazon.avod.ads.parser.vast.VastWrapper;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.net.URI;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdCreativeHolderNode implements AdNode {
    private List<URI> mErrors;
    private boolean mExternalResolved;
    protected URI mExternalUri;
    private final AdHttpClient mHttpClient;
    private VastInline mInline;
    private final AdInfoNode mParent;
    private final List<VastExtension> mVastExtensions;
    private VastWrapper mWrapper;
    private final List<CreativeLinear> mLinearCreatives = Lists.newLinkedList();
    private final List<CreativeNonLinearAds> mNonLinearCreatives = Lists.newLinkedList();
    private final List<CreativeCompanionAds> mCompanionCreatives = Lists.newLinkedList();
    private final AtomicBoolean mImpressionSent = new AtomicBoolean(false);

    private static class CycleFoundException extends Exception {
        private CycleFoundException() {
        }
    }

    private static class RedirectCycleDetector implements AdInfoNode.ThrowingParentCaller<CycleFoundException> {
        Set<URI> mSeenUris;

        private RedirectCycleDetector() {
            this.mSeenUris = Sets.newHashSet();
        }

        @Override // com.amazon.avod.ads.api.internal.AdInfoNode.ThrowingParentCaller
        public void call(AdInfoNode adInfoNode) throws CycleFoundException {
            if (adInfoNode.getCreativeHolder() != null) {
                if (this.mSeenUris.contains(adInfoNode.getCreativeHolder().getExternalUri())) {
                    throw new CycleFoundException();
                }
                this.mSeenUris.add(adInfoNode.getCreativeHolder().getExternalUri());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00b7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a5 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AdCreativeHolderNode(com.amazon.avod.ads.api.internal.AdInfoNode r12, com.amazon.avod.ads.http.AdHttpClient r13, @javax.annotation.Nullable com.amazon.avod.ads.parser.vast.VastAdData r14) {
        /*
            Method dump skipped, instruction units count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.ads.api.internal.AdCreativeHolderNode.<init>(com.amazon.avod.ads.api.internal.AdInfoNode, com.amazon.avod.ads.http.AdHttpClient, com.amazon.avod.ads.parser.vast.VastAdData):void");
    }

    public boolean hasRequiredCompanion() {
        boolean z;
        while (true) {
            for (CreativeCompanionAds creativeCompanionAds : this.mCompanionCreatives) {
                z = z || creativeCompanionAds.getAdsRequired() != VastCompanionAds.AdsRequired.none;
            }
            return z;
        }
    }

    @Override // com.amazon.avod.ads.api.internal.AdNode
    public void sendImpression(Duration duration, URI uri, String str) throws AdNetworkException {
        if (this.mImpressionSent.getAndSet(true)) {
            return;
        }
        this.mHttpClient.sendVastBeacon(((VastAdData) MoreObjects.firstNonNull(this.mInline, this.mWrapper)).getImpressions(), duration, uri, str);
    }

    @Override // com.amazon.avod.ads.api.internal.AdNode
    public void sendError(AdInfoErrorCode adInfoErrorCode, Duration duration, URI uri) throws AdNetworkException {
        List<URI> list = this.mErrors;
        if (list != null) {
            this.mHttpClient.sendVastBeacon(list, duration, uri, adInfoErrorCode, (String) null);
        }
    }

    @Override // com.amazon.avod.ads.api.internal.AdNode
    public void resolveExternalEntities() throws AdException {
        if (this.mExternalResolved) {
            return;
        }
        if (this.mExternalUri == null) {
            throw new AdResolutionException("The external uri of a wrapper is null.");
        }
        if (checkForCycles()) {
            throw new AdResolutionException("Cycle in wrapper resolution");
        }
        AdDocument adDocumentRetrieveAdDocument = AdDocument.retrieveAdDocument(this.mHttpClient, this.mExternalUri);
        if (adDocumentRetrieveAdDocument.getAdPod() != null) {
            getParent().addChild(adDocumentRetrieveAdDocument.getAdPod());
        }
        if (adDocumentRetrieveAdDocument.getAdBuffet() != null) {
            Iterator<AdInfoNode> it = adDocumentRetrieveAdDocument.getAdBuffet().iterator();
            while (it.hasNext()) {
                getParent().addChild(it.next());
            }
        }
        this.mExternalResolved = true;
    }

    @Override // com.amazon.avod.ads.api.internal.AdNode
    public boolean hasUnresolvedEntities() {
        return !this.mExternalResolved;
    }

    @Override // com.amazon.avod.ads.api.internal.AdNode
    public List<Creative> getDisplayableCreatives() {
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        for (CreativeLinear creativeLinear : this.mLinearCreatives) {
            if (creativeLinear.isDisplayable()) {
                linkedListNewLinkedList.add(creativeLinear);
            }
        }
        for (CreativeNonLinearAds creativeNonLinearAds : this.mNonLinearCreatives) {
            if (creativeNonLinearAds.isDisplayable()) {
                linkedListNewLinkedList.add(creativeNonLinearAds);
            }
        }
        for (CreativeCompanionAds creativeCompanionAds : this.mCompanionCreatives) {
            if (creativeCompanionAds.isDisplayable()) {
                linkedListNewLinkedList.addAll(creativeCompanionAds.getCompanions());
            }
        }
        return linkedListNewLinkedList;
    }

    @Nonnull
    public List<Creative> getCreatives(CreativeType creativeType) {
        List<Creative> listEmptyList = Collections.emptyList();
        if (creativeType == CreativeType.linear) {
            return this.mLinearCreatives;
        }
        if (creativeType == CreativeType.nonlinear) {
            return this.mNonLinearCreatives;
        }
        if (creativeType != CreativeType.companion) {
            return listEmptyList;
        }
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        Iterator<CreativeCompanionAds> it = this.mCompanionCreatives.iterator();
        while (it.hasNext()) {
            linkedListNewLinkedList.addAll(it.next().getCompanions());
        }
        return linkedListNewLinkedList;
    }

    @Nonnull
    public List<VastExtension> getVastExtensions() {
        return this.mVastExtensions;
    }

    @Nonnull
    public AdInfoNode getParent() {
        return this.mParent;
    }

    URI getExternalUri() {
        return this.mExternalUri;
    }

    boolean checkForCycles() throws Exception {
        try {
            getParent().callOnParentsAndThrow(new RedirectCycleDetector());
            return false;
        } catch (CycleFoundException unused) {
            return true;
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("Wrapper", this.mWrapper != null).add("Inline", this.mInline != null).add("Linear Count", this.mLinearCreatives.size()).add("NonLinear Count", this.mNonLinearCreatives.size()).add("Companion Count", this.mCompanionCreatives.size()).toString();
    }

    @Nullable
    public AdSkipInfo getAdSkipInfo() {
        for (VastExtension vastExtension : this.mVastExtensions) {
            if (VastExtensionType.SKIPPABLE.equals(vastExtension.getExtensionType())) {
                VastExtensionSkipOffset skipOffset = vastExtension.getSkipOffset();
                return new AdSkipInfo(skipOffset != null ? skipOffset.getShowTimer() : false, skipOffset != null ? skipOffset.getOffsetVal().getTotalMilliseconds() : 0L, vastExtension.getMinAdLength() != null ? vastExtension.getMinAdLength().getTotalMilliseconds() : 0L);
            }
        }
        return null;
    }
}
