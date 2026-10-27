package com.amazon.music.curate.skyfire.local;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.ExternalAction;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalActionMethod;
import ExternalActionInterface.v1_0.PlayMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class PlayAlbumSkill extends LocalSkill {
    public static List<Method> prepareInvoke(final String entityIdType, final String entityId, final String playMode, final Map<String, String> contentInfo) {
        HashMap map = new HashMap();
        map.put(entityIdType, entityId);
        map.putAll(contentInfo);
        return Collections.singletonList(new InvokeLocalSkillMethod(new PlayAlbumSkillContext(map, playMode), Queues.template()));
    }

    @Override // com.amazon.music.curate.skyfire.local.LocalSkill
    public List<Method> invoke(final LocalSkillContext localSkillContext) {
        String strName;
        String playMode = localSkillContext.getPlayMode();
        if (StringUtils.isNoneBlank(playMode) && PlayMode.FREE_STATION.name().equalsIgnoreCase(playMode)) {
            strName = ExternalAction.PLAY_STATION.name();
        } else {
            strName = ExternalAction.PLAY_ALBUM.name();
        }
        return Arrays.asList(ImmutableTriggerExternalActionMethod.builder().name(strName).context(localSkillContext.getContextMap()).queue(Queues.template()).forced(Boolean.TRUE).build());
    }
}
