package com.amazon.music.curate.skyfire.local;

/* JADX INFO: loaded from: classes4.dex */
public final class ShowPodcastsSkillContext extends LocalSkillContext {
    public ShowPodcastsSkillContext() {
        super(null);
    }

    @Override // com.amazon.music.curate.skyfire.local.LocalSkillContext
    public String destination() {
        return ShowPodcastsSkill.class.getCanonicalName();
    }
}
