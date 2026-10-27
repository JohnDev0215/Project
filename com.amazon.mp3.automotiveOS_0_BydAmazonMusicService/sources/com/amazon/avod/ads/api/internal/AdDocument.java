package com.amazon.avod.ads.api.internal;

import com.amazon.avod.ads.api.AdNetworkException;
import com.amazon.avod.ads.api.AdParsingException;
import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.VastVmapParser;
import com.amazon.avod.ads.parser.vast.VastAd;
import com.amazon.avod.ads.parser.vast.VastDocument;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdDocument {
    private static final AdInfoComparator AD_INFO_COMPARATOR = new AdInfoComparator();
    private final List<AdInfoNode> mAdBuffet;
    private final AdInfoNode mAdPod;
    private final AdHttpClient mHttpClient;
    private final VastDocument mInnerAdDocument;

    private static class AdInfoComparator implements Comparator<AdInfoNode>, Serializable {
        private AdInfoComparator() {
        }

        @Override // java.util.Comparator
        public int compare(AdInfoNode adInfoNode, AdInfoNode adInfoNode2) {
            return ComparisonChain.start().compare(adInfoNode.getSequence(), adInfoNode2.getSequence()).result();
        }
    }

    public AdDocument(AdHttpClient adHttpClient, VastDocument vastDocument) {
        this.mHttpClient = (AdHttpClient) Preconditions.checkNotNull(adHttpClient);
        VastDocument vastDocument2 = (VastDocument) Preconditions.checkNotNull(vastDocument);
        this.mInnerAdDocument = vastDocument2;
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        ArrayList arrayListNewArrayList2 = Lists.newArrayList();
        if (vastDocument2.getAds() != null) {
            for (VastAd vastAd : vastDocument2.getAds()) {
                if (vastAd.getSequence() != null) {
                    arrayListNewArrayList.add(new AdInfoNode(this.mHttpClient, vastAd));
                } else {
                    arrayListNewArrayList2.add(new AdInfoNode(this.mHttpClient, vastAd));
                }
            }
        }
        arrayListNewArrayList.trimToSize();
        arrayListNewArrayList2.trimToSize();
        if (arrayListNewArrayList.size() > 0) {
            Collections.sort(arrayListNewArrayList, AD_INFO_COMPARATOR);
            AdInfoNode adInfoNode = new AdInfoNode(this.mHttpClient, true);
            Iterator it = arrayListNewArrayList.iterator();
            while (it.hasNext()) {
                adInfoNode.addChild((AdInfoNode) it.next());
            }
            this.mAdPod = adInfoNode;
        } else {
            this.mAdPod = null;
        }
        this.mAdBuffet = arrayListNewArrayList2;
    }

    public boolean hasAdPod() {
        return this.mAdPod != null;
    }

    @Nullable
    public AdInfoNode getAdPod() {
        return this.mAdPod;
    }

    @Nonnull
    public List<AdInfoNode> getAdBuffet() {
        return this.mAdBuffet;
    }

    public static AdDocument retrieveAdDocument(AdHttpClient adHttpClient, URI uri) throws AdNetworkException, AdParsingException {
        try {
            return new AdDocument(adHttpClient, VastVmapParser.loadVastDocument(adHttpClient.processRequest(uri)));
        } catch (Exception e) {
            throw new AdParsingException(e);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("Contains Ad Pod", hasAdPod()).add("Buffet Size", this.mAdBuffet.size()).toString();
    }
}
