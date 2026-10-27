package com.amazon.music.destination;

import com.amazon.music.destination.parser.Action;

/* JADX INFO: loaded from: classes4.dex */
public class ArtistDestination extends AsinActionDestination {
    private String artistName;
    private String experienceId;
    private long id;
    private String storyId;
    private final Target target;

    public ArtistDestination(Target target, String str, String str2, Action action, String str3, String str4) {
        super(str, str2, action, str3, str4);
        this.target = target;
    }

    public ArtistDestination(Target target, long j, String str, String str2, Action action, String str3, String str4) {
        super(str, str2, action, str3, str4);
        this.target = target;
        this.id = j;
    }

    public ArtistDestination(Target target, String str, String str2, String str3, Action action, String str4, String str5, String str6) {
        super(str, str3, action, str4, str5);
        this.target = target;
        this.artistName = str2;
        this.experienceId = str6;
    }

    public ArtistDestination(Target target, String str, String str2, String str3, Action action, String str4, String str5) {
        super(str, str3, action, str4, str5);
        this.target = target;
        this.artistName = str2;
    }

    public ArtistDestination(Target target, String str, String str2, String str3, Action action, String str4, String str5, String str6, String str7) {
        super(str, str3, action, str4, str5);
        this.target = target;
        this.artistName = str2;
        this.experienceId = str6;
        this.storyId = str7;
    }

    public Target getTarget() {
        return this.target;
    }

    public long getId() {
        return this.id;
    }

    public String getExperienceId() {
        return this.experienceId;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public String getStoryId() {
        return this.storyId;
    }
}
