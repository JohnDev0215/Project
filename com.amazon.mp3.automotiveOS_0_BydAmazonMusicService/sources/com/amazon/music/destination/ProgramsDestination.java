package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public class ProgramsDestination extends Destination {
    private final String competitionId;

    public ProgramsDestination(String str, String str2, String str3) {
        super(str2, str3);
        this.competitionId = str;
    }

    public String getCompetitionId() {
        return this.competitionId;
    }
}
