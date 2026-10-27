package com.amazon.avod.ads.api.internal;

import com.amazon.avod.ads.api.AdException;
import com.amazon.avod.ads.api.AdInfo;
import com.amazon.avod.ads.api.AdInfoErrorCode;
import com.amazon.avod.ads.api.AdNetworkException;
import com.amazon.avod.ads.api.AdResolutionException;
import com.amazon.avod.ads.api.AdSkipInfo;
import com.amazon.avod.ads.api.Creative;
import com.amazon.avod.ads.api.Duration;
import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.vast.VastAd;
import com.amazon.avod.ads.parser.vast.VastAdData;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.net.URI;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdInfoNode implements AdInfo {
    private final String mAdId;
    private final String mAdServer;
    private final List<AdInfoNode> mChildren;
    private final URI mErrorUri;
    private final AdHttpClient mHttpClient;
    protected AdCreativeHolderNode mInfoContainer;
    private final boolean mIsAdPod;
    private AdInfoNode mParent;
    private final int mSequence;

    public interface ParentCaller {
        void call(AdInfoNode adInfoNode);
    }

    public interface ThrowingParentCaller<E extends Exception> {
        void call(AdInfoNode adInfoNode) throws Exception;
    }

    public AdInfoNode(AdHttpClient adHttpClient, VastAd vastAd) {
        this(adHttpClient, vastAd, false);
    }

    public AdInfoNode(AdHttpClient adHttpClient, boolean z) {
        this(adHttpClient, null, z);
    }

    public AdInfoNode(AdHttpClient adHttpClient, VastAd vastAd, boolean z) {
        this.mChildren = Lists.newLinkedList();
        this.mInfoContainer = null;
        this.mParent = null;
        AdHttpClient adHttpClient2 = (AdHttpClient) Preconditions.checkNotNull(adHttpClient);
        this.mHttpClient = adHttpClient2;
        this.mIsAdPod = z;
        if (vastAd != null) {
            this.mAdId = vastAd.getId();
            VastAdData adData = vastAd.getAdData();
            this.mAdServer = adData.getAdSystem().getAdServer();
            if (adData.getErrors() != null && !adData.getErrors().isEmpty()) {
                this.mErrorUri = adData.getErrors().get(0);
            } else {
                this.mErrorUri = null;
            }
            this.mInfoContainer = new AdCreativeHolderNode(this, adHttpClient2, vastAd.getAdData());
            this.mSequence = ((Integer) Optional.fromNullable(vastAd.getSequence()).or(Integer.MAX_VALUE)).intValue();
            return;
        }
        this.mAdId = null;
        this.mErrorUri = null;
        this.mSequence = Integer.MAX_VALUE;
        this.mAdServer = null;
    }

    public void addChild(AdInfoNode adInfoNode) {
        adInfoNode.mParent = this;
        this.mChildren.add(adInfoNode);
    }

    @Nullable
    public AdCreativeHolderNode getCreativeHolder() {
        return this.mInfoContainer;
    }

    public int getSequence() {
        return this.mSequence;
    }

    public boolean isAdPod() {
        return this.mIsAdPod;
    }

    @Override // com.amazon.avod.ads.api.AdInfo
    public String getAdId() {
        return this.mAdId;
    }

    @Override // com.amazon.avod.ads.api.AdInfo
    public String getAdServer() {
        return this.mAdServer;
    }

    @Override // com.amazon.avod.ads.api.internal.AdNode
    public void sendImpression(final Duration duration, final URI uri, final String str) throws Exception {
        callOnParentsAndThrow(new ThrowingParentCaller<AdNetworkException>() { // from class: com.amazon.avod.ads.api.internal.AdInfoNode.1
            @Override // com.amazon.avod.ads.api.internal.AdInfoNode.ThrowingParentCaller
            public void call(AdInfoNode adInfoNode) throws AdNetworkException {
                if (adInfoNode.getCreativeHolder() != null) {
                    adInfoNode.getCreativeHolder().sendImpression(duration, uri, str);
                }
            }
        });
    }

    @Override // com.amazon.avod.ads.api.internal.AdNode
    public void sendError(final AdInfoErrorCode adInfoErrorCode, final Duration duration, final URI uri) throws Exception {
        callOnParentsAndThrow(new ThrowingParentCaller<AdNetworkException>() { // from class: com.amazon.avod.ads.api.internal.AdInfoNode.2
            @Override // com.amazon.avod.ads.api.internal.AdInfoNode.ThrowingParentCaller
            public void call(AdInfoNode adInfoNode) throws AdNetworkException {
                if (adInfoNode.getErrorUri() != null) {
                    AdInfoNode.this.mHttpClient.sendVastBeacon(adInfoNode.getErrorUri(), duration, uri, adInfoErrorCode, (String) null);
                }
                if (adInfoNode.getCreativeHolder() != null) {
                    adInfoNode.getCreativeHolder().sendError(adInfoErrorCode, duration, uri);
                }
            }
        });
    }

    @Override // com.amazon.avod.ads.api.AdInfo
    public List<AdInfo> getDisplayableAds() {
        AdCreativeHolderNode adCreativeHolderNode;
        if (isAdPod() && hasUnresolvedEntities()) {
            return Collections.emptyList();
        }
        if (this.mChildren.isEmpty() && (adCreativeHolderNode = this.mInfoContainer) != null && !adCreativeHolderNode.hasUnresolvedEntities()) {
            return Lists.newArrayList(this);
        }
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        Iterator<AdInfoNode> it = this.mChildren.iterator();
        while (it.hasNext()) {
            linkedListNewLinkedList.addAll(it.next().getDisplayableAds());
        }
        return linkedListNewLinkedList;
    }

    @Override // com.amazon.avod.ads.api.internal.AdNode
    public List<Creative> getDisplayableCreatives() {
        return this.mInfoContainer.getDisplayableCreatives();
    }

    @Override // com.amazon.avod.ads.api.internal.AdNode
    public boolean hasUnresolvedEntities() {
        AdCreativeHolderNode adCreativeHolderNode = this.mInfoContainer;
        if (adCreativeHolderNode != null && adCreativeHolderNode.hasUnresolvedEntities()) {
            return true;
        }
        Iterator<AdInfoNode> it = this.mChildren.iterator();
        while (it.hasNext()) {
            if (it.next().hasUnresolvedEntities()) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public AdInfoNode getParent() {
        return this.mParent;
    }

    @Override // com.amazon.avod.ads.api.AdInfo
    public AdSkipInfo getParentSkipInfo() {
        AdInfoNode adInfoNode = this.mParent;
        if (adInfoNode == null || adInfoNode.getParent() == null || this.mParent.getParent().getCreativeHolder() == null) {
            return null;
        }
        return this.mParent.getParent().getCreativeHolder().getAdSkipInfo();
    }

    @Override // com.amazon.avod.ads.api.internal.AdNode
    public void resolveExternalEntities() throws AdException {
        AdCreativeHolderNode adCreativeHolderNode = this.mInfoContainer;
        if (adCreativeHolderNode != null) {
            adCreativeHolderNode.resolveExternalEntities();
        }
        if (hasUnresolvedEntities()) {
            Iterator<AdInfoNode> it = this.mChildren.iterator();
            AdInfoNode adInfoNode = null;
            AdException e = null;
            while (it.hasNext()) {
                try {
                    it.next().resolveExternalEntities();
                } catch (AdException e2) {
                    e = e2;
                }
            }
            if (isAdPod()) {
                if (e != null) {
                    throw e;
                }
                return;
            }
            for (AdInfoNode adInfoNode2 : this.mChildren) {
                if (!adInfoNode2.hasUnresolvedEntities() && adInfoNode2.getCreativeHolder() != null && !adInfoNode2.getCreativeHolder().hasRequiredCompanion()) {
                    if (adInfoNode == null) {
                        adInfoNode = adInfoNode2;
                    }
                    if (adInfoNode.isAdPod() && !adInfoNode2.isAdPod()) {
                        adInfoNode = adInfoNode2;
                    }
                }
            }
            if (adInfoNode == null) {
                if (e != null) {
                    throw e;
                }
                throw new AdResolutionException("Could not find a suitable child node.");
            }
            this.mChildren.clear();
            this.mChildren.add(adInfoNode);
        }
    }

    public void callOnParents(ParentCaller parentCaller) {
        parentCaller.call(this);
        AdInfoNode adInfoNode = this.mParent;
        if (adInfoNode != null) {
            adInfoNode.callOnParents(parentCaller);
        }
    }

    public <T extends Exception> void callOnParentsAndThrow(ThrowingParentCaller<T> throwingParentCaller) throws Exception {
        throwingParentCaller.call(this);
        AdInfoNode adInfoNode = this.mParent;
        if (adInfoNode != null) {
            adInfoNode.callOnParentsAndThrow(throwingParentCaller);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("Ad Pod", this.mIsAdPod).add("Children", this.mChildren.size()).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public URI getErrorUri() {
        return this.mErrorUri;
    }
}
