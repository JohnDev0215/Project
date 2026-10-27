package com.amazon.music.search;

import com.amazon.music.platform.model.Art;
import com.amazon.music.platform.model.ArtURL;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class RelatedPlaylist {
    private final com.amazon.music.platform.model.RelatedPlaylist relatedPlaylist;

    RelatedPlaylist(com.amazon.music.platform.model.RelatedPlaylist relatedPlaylist) {
        Objects.requireNonNull(relatedPlaylist, "relatedPlaylist cannot be null");
        this.relatedPlaylist = relatedPlaylist;
    }

    public String getAsin() {
        return this.relatedPlaylist.getAsin();
    }

    public String getPlaylistId() {
        return this.relatedPlaylist.getPlaylistId();
    }

    public String getTitle() {
        return this.relatedPlaylist.getTitle();
    }

    public String getImageUrl() {
        Art artOriginal = this.relatedPlaylist.getArtOriginal();
        if (artOriginal == null || !(artOriginal instanceof ArtURL)) {
            return null;
        }
        return ((ArtURL) artOriginal).getArtUrl();
    }

    public String getBlockRef() {
        return this.relatedPlaylist.getBlockRef();
    }

    public EntityRelation getEntityRelation() {
        if (this.relatedPlaylist.getEntityRelation() == null) {
            return null;
        }
        return new EntityRelation(this.relatedPlaylist.getEntityRelation());
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        com.amazon.music.platform.model.PlaymodeEligibility playmodeEligibility = this.relatedPlaylist.getPlaymodeEligibility();
        if (playmodeEligibility == null) {
            return null;
        }
        return new PlaymodeEligibility(playmodeEligibility);
    }
}
