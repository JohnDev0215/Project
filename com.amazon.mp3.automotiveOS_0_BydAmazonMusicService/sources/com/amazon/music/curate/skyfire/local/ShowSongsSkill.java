package com.amazon.music.curate.skyfire.local;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.ExternalAction;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalActionMethod;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class ShowSongsSkill extends LocalSkill {
    public static List<Method> prepareInvoke() {
        return Collections.singletonList(new InvokeLocalSkillMethod(new ShowSongsSkillContext(), Queues.template()));
    }

    @Override // com.amazon.music.curate.skyfire.local.LocalSkill
    public List<Method> invoke(final LocalSkillContext localSkillContext) {
        return Arrays.asList(ImmutableTriggerExternalActionMethod.builder().name(ExternalAction.GO_LIBRARY_SONGS.name()).queue(Queues.template()).forced(Boolean.TRUE).build());
    }
}
