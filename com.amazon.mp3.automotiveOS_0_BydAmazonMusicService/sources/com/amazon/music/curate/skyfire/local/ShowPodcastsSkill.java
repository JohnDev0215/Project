package com.amazon.music.curate.skyfire.local;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.ExternalAction;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalActionMethod;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class ShowPodcastsSkill extends LocalSkill {
    public static List<Method> prepareInvoke() {
        return Collections.singletonList(new InvokeLocalSkillMethod(new ShowPodcastsSkillContext(), Queues.template()));
    }

    @Override // com.amazon.music.curate.skyfire.local.LocalSkill
    public List<Method> invoke(final LocalSkillContext localSkillContext) {
        return Arrays.asList(ImmutableTriggerExternalActionMethod.builder().name(ExternalAction.GO_LIBRARY_PODCASTS.name()).queue(Queues.template()).forced(Boolean.TRUE).build());
    }
}
