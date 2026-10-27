package com.amazon.music.curate.skyfire.local;

import CoreInterface.v1_0.Element;
import ExternalActionInterface.v1_0.PlayMode;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public abstract class LocalSkillContext extends Element {
    private final Map<String, String> contextMap = new HashMap();

    public abstract String destination();

    public LocalSkillContext(final Map<String, String> entity) {
        if (entity == null || entity.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : entity.entrySet()) {
            this.contextMap.put(entry.getKey(), entry.getValue());
        }
    }

    public Map<String, String> getContextMap() {
        return this.contextMap;
    }

    public String getPlayMode() {
        return this.contextMap.get(PlayMode.PLAY_MODE);
    }
}
