package com.spotify.webapi.service.models;

import java.util.List;
import p.c71;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public class EpisodeSimple extends Entity {
    public String audioPreviewUrl;
    public String description;
    public int durationMs;
    public Boolean explicit;
    public String href;
    public String id;
    public List<Image> images;
    public Boolean is_playable;
    public String name;
    public String releaseDate;
    public ResumePoint resumePoint;
    public String uri;

    @z61(name = "audio_preview_url")
    public static /* synthetic */ void getAudioPreviewUrl$annotations() {
    }

    @z61(name = "description")
    public static /* synthetic */ void getDescription$annotations() {
    }

    @z61(name = "duration_ms")
    public static /* synthetic */ void getDurationMs$annotations() {
    }

    @z61(name = "explicit")
    public static /* synthetic */ void getExplicit$annotations() {
    }

    @z61(name = "href")
    public static /* synthetic */ void getHref$annotations() {
    }

    @z61(name = "id")
    public static /* synthetic */ void getId$annotations() {
    }

    @z61(name = "images")
    public static /* synthetic */ void getImages$annotations() {
    }

    @z61(name = "name")
    public static /* synthetic */ void getName$annotations() {
    }

    @z61(name = "release_date")
    public static /* synthetic */ void getReleaseDate$annotations() {
    }

    @z61(name = "resume_point")
    public static /* synthetic */ void getResumePoint$annotations() {
    }

    @z61(name = "uri")
    public static /* synthetic */ void getUri$annotations() {
    }

    @z61(name = "is_playable")
    public static /* synthetic */ void is_playable$annotations() {
    }

    @Override // com.spotify.webapi.service.models.Entity
    public String href() {
        return this.href;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public String id() {
        return this.id;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public List<Image> images() {
        return this.images;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public boolean isExplicit() {
        Boolean bool = this.explicit;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public boolean isPlayable() {
        Boolean bool = this.is_playable;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public String name() {
        return this.name;
    }

    @Override // com.spotify.webapi.service.models.Entity
    public String uri() {
        return this.uri;
    }
}
