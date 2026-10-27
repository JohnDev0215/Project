package com.amazon.music.curate.skyfire.local;

import ExternalActionInterface.v1_0.PlayMode;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class PlayCatalogPlaylistSkillContext extends LocalSkillContext {
    public PlayCatalogPlaylistSkillContext(final Map<String, String> entity, final String playMode) {
        super(entity);
        if (StringUtils.isNotBlank(playMode)) {
            getContextMap().put(PlayMode.PLAY_MODE, playMode);
        }
    }

    @Override // com.amazon.music.curate.skyfire.local.LocalSkillContext
    public String destination() {
        return PlayCatalogPlaylistSkill.class.getCanonicalName();
    }
}
