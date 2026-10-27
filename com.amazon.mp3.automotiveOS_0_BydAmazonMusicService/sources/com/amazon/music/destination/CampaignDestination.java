package com.amazon.music.destination;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class CampaignDestination extends AbstractWebDestination {
    private final List<String> segments;

    public CampaignDestination(Map<String, String> map, String str, String str2, List<String> list) {
        super(map, str, str2);
        this.segments = list;
    }

    public List<String> getSegments() {
        return this.segments;
    }
}
