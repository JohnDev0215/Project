package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.Action;

/* JADX INFO: loaded from: classes4.dex */
public class StationDestination extends Destination {
    private final Action action;
    private final String stationKey;

    public StationDestination(String str, Action action, String str2, String str3) {
        super(str2, str3);
        this.stationKey = str;
        this.action = action;
    }

    public String getStationKey() {
        return this.stationKey;
    }

    public Action getAction() {
        return this.action;
    }
}
