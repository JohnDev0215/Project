package com.amazon.avod.ads.api;

import com.amazon.avod.ads.api.internal.AdCreativeHolderNode;
import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.vast.VastExtension;
import com.amazon.avod.ads.parser.vast.VastExtensionSkipOffset;
import com.amazon.avod.ads.parser.vast.VastExtensionType;
import com.amazon.avod.ads.parser.vast.VastIcon;
import com.amazon.avod.ads.parser.vast.VastInlineLinear;
import com.amazon.avod.ads.parser.vast.VastMediaFile;
import com.amazon.avod.ads.parser.vast.VastWrapperLinear;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CreativeLinear extends Creative {
    private final AdSkipInfo mAdSkipInfo;
    private final boolean mAddToWatchListEnabled;
    private final URI mClickThrough;

    @Nullable
    private final String mContentType;
    private final Duration mDuration;
    private final List<Object> mExtensions;
    private final List<Icon> mIcons;
    private final VastInlineLinear mInlineLinear;
    private final List<MediaFile> mMediaFiles;
    private final Offset mSkipOffset;
    private final VastWrapperLinear mWrapperLinear;

    public static CreativeLinear create(@Nonnull AdCreativeHolderNode adCreativeHolderNode, @Nonnull AdHttpClient adHttpClient, @Nonnull VastWrapperLinear vastWrapperLinear, @Nullable String str) {
        VastInlineLinear vastInlineLinear;
        Duration duration;
        Offset offset;
        ArrayList arrayListNewArrayListWithCapacity;
        URI uri;
        Preconditions.checkNotNull(adCreativeHolderNode, "AdCreativeHolderNode");
        Preconditions.checkNotNull(adHttpClient, "AdHttpClient");
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        VastWrapperLinear vastWrapperLinear2 = (VastWrapperLinear) Preconditions.checkNotNull(vastWrapperLinear);
        if (vastWrapperLinear2.getIcons() != null) {
            Iterator<VastIcon> it = vastWrapperLinear2.getIcons().iterator();
            while (it.hasNext()) {
                linkedListNewLinkedList.add(new Icon(adHttpClient, it.next()));
            }
        }
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        boolean z = false;
        AdSkipInfo adSkipInfo = null;
        String contentType = null;
        for (VastExtension vastExtension : adCreativeHolderNode.getVastExtensions()) {
            arrayListNewArrayList.add(vastExtension);
            if (VastExtensionType.SKIPPABLE.equals(vastExtension.getExtensionType())) {
                VastExtensionSkipOffset skipOffset = vastExtension.getSkipOffset();
                adSkipInfo = new AdSkipInfo(skipOffset != null ? skipOffset.getShowTimer() : false, skipOffset != null ? skipOffset.getOffsetVal().getTotalMilliseconds() : 0L, vastExtension.getMinAdLength() != null ? vastExtension.getMinAdLength().getTotalMilliseconds() : 0L);
            } else if (VastExtensionType.CONTENT_METADATA.equals(vastExtension.getExtensionType())) {
                contentType = vastExtension.getContentType();
            }
            if (VastExtensionType.WATCHLIST.equals(vastExtension.getExtensionType())) {
                z = true;
            }
        }
        if (vastWrapperLinear instanceof VastInlineLinear) {
            VastInlineLinear vastInlineLinear2 = (VastInlineLinear) vastWrapperLinear;
            Duration duration2 = new Duration(vastInlineLinear2.getDuration());
            offset = vastInlineLinear2.getSkipOffset() != null ? new Offset(vastInlineLinear2.getSkipOffset()) : null;
            arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(vastInlineLinear2.getMediaFiles().size());
            Iterator<VastMediaFile> it2 = vastInlineLinear2.getMediaFiles().iterator();
            while (it2.hasNext()) {
                arrayListNewArrayListWithCapacity.add(new MediaFile(it2.next()));
            }
            if (vastInlineLinear2.getVideoClicks() == null || vastInlineLinear2.getVideoClicks().getClickThrough() == null) {
                duration = duration2;
                uri = null;
            } else {
                uri = vastInlineLinear2.getVideoClicks().getClickThrough().getUri();
                duration = duration2;
            }
            vastInlineLinear = vastInlineLinear2;
        } else {
            vastInlineLinear = null;
            duration = null;
            offset = null;
            arrayListNewArrayListWithCapacity = null;
            uri = null;
        }
        return new CreativeLinear(adCreativeHolderNode, adHttpClient, vastWrapperLinear, vastWrapperLinear2, vastInlineLinear, linkedListNewLinkedList, duration, offset, adSkipInfo, arrayListNewArrayListWithCapacity, uri, z, str, arrayListNewArrayList, contentType);
    }

    private CreativeLinear(AdCreativeHolderNode adCreativeHolderNode, AdHttpClient adHttpClient, VastWrapperLinear vastWrapperLinear, VastWrapperLinear vastWrapperLinear2, VastInlineLinear vastInlineLinear, List<Icon> list, Duration duration, Offset offset, AdSkipInfo adSkipInfo, List<MediaFile> list2, URI uri, boolean z, String str, @Nonnull List<Object> list3, @Nullable String str2) {
        super((AdCreativeHolderNode) Preconditions.checkNotNull(adCreativeHolderNode), (AdHttpClient) Preconditions.checkNotNull(adHttpClient), vastWrapperLinear.getTrackingEvents(), str);
        this.mWrapperLinear = vastWrapperLinear2;
        this.mInlineLinear = vastInlineLinear;
        this.mIcons = list;
        this.mDuration = duration;
        this.mSkipOffset = offset;
        this.mAdSkipInfo = adSkipInfo;
        this.mMediaFiles = list2;
        this.mClickThrough = uri;
        this.mAddToWatchListEnabled = z;
        this.mExtensions = (List) Preconditions.checkNotNull(list3, "extensions");
        this.mContentType = str2;
    }

    @Override // com.amazon.avod.ads.api.Creative
    public CreativeType getCreativeType() {
        return CreativeType.linear;
    }

    @Override // com.amazon.avod.ads.api.Creative
    public boolean isDisplayable() {
        return this.mInlineLinear != null;
    }

    @Nullable
    public Duration getDuration() {
        return this.mDuration;
    }

    @Nullable
    public AdSkipInfo getAdSkipInfo() {
        return this.mAdSkipInfo;
    }

    @Nullable
    public URI getClickThroughUri() {
        return this.mClickThrough;
    }

    @Nullable
    public List<MediaFile> getMediaFiles() {
        List<MediaFile> list = this.mMediaFiles;
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return null;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("Duration", this.mDuration).add("Skip Offset", this.mSkipOffset).add("Content Type", this.mContentType).add("Icon Count", this.mIcons.size()).toString();
    }

    @Nonnull
    public List<Object> getExtensions() {
        return this.mExtensions;
    }

    @Nullable
    public String getContentType() {
        return this.mContentType;
    }
}
