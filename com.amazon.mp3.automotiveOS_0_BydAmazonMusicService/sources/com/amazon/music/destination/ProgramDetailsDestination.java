package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.Action;

/* JADX INFO: loaded from: classes4.dex */
public class ProgramDetailsDestination extends Destination {
    private final Action action;
    private final String programId;
    private final ProgramType programType;

    public enum ProgramType {
        SPORTS,
        SHOWS,
        NONE
    }

    public ProgramDetailsDestination(String str, String str2, String str3) {
        this(str, null, str2, str3);
    }

    public ProgramDetailsDestination(String str, Action action, String str2, String str3) {
        this(str, ProgramType.SPORTS, action, str2, str3);
    }

    public ProgramDetailsDestination(String str, ProgramType programType, Action action, String str2, String str3) {
        super(str2, str3);
        this.programId = str;
        this.programType = programType;
        this.action = action;
    }

    public String getProgramId() {
        return this.programId;
    }

    public Action getAction() {
        return this.action;
    }

    public ProgramType getProgramType() {
        return this.programType;
    }
}
