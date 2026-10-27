package com.amazon.mp3.prime.upsell.nightwing.client;

import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class UpsellClient {
    private String albumAsin;
    private String artistName;
    private long disappearsAfterDuration = 20;
    private String itemUrl;
    private List<PlayableEntityIdentifier> playableEntityIdentifierList;
    private String seedName;
    private List<String> tags;
    private String trackAsinQueryParameter;
    private UpsellReason upsellReason;
    private UpsellSourceEntity upsellSourceEntity;

    public UpsellInformation buildUpsell() {
        return new UpsellInformation(this.playableEntityIdentifierList, this.upsellSourceEntity, this.upsellReason, this.seedName, this.artistName, this.albumAsin, this.itemUrl, this.trackAsinQueryParameter, this.disappearsAfterDuration, this.tags);
    }

    public UpsellClient setUpsellReason(final UpsellReason upsellReason) {
        this.upsellReason = upsellReason;
        return this;
    }

    public UpsellClient setUpsellSourceEntity(final UpsellSourceEntity upsellSourceEntity) {
        this.upsellSourceEntity = upsellSourceEntity;
        return this;
    }

    public UpsellClient setPlayableEntityIdentifierList(final List<PlayableEntityIdentifier> playableEntityIdentifierList) {
        this.playableEntityIdentifierList = playableEntityIdentifierList;
        return this;
    }

    public UpsellClient setSeedName(final String seedName) {
        this.seedName = seedName;
        return this;
    }

    public UpsellClient setArtistName(final String artistName) {
        this.artistName = artistName;
        return this;
    }

    public UpsellClient setAlbumAsin(final String albumAsin) {
        this.albumAsin = albumAsin;
        return this;
    }

    public UpsellClient setItemUrl(final String itemUrl) {
        this.itemUrl = itemUrl;
        return this;
    }

    public UpsellClient setTrackAsinQueryParameter(final String trackAsinQueryParameter) {
        this.trackAsinQueryParameter = trackAsinQueryParameter;
        return this;
    }

    public UpsellClient setTags(final List<String> tags) {
        this.tags = tags;
        return this;
    }
}
