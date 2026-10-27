package com.amazon.music.curate.skyfire.local;

/* JADX INFO: loaded from: classes4.dex */
public final class ShowRecentlyPlayedSkillContext extends LocalSkillContext {
    public ShowRecentlyPlayedSkillContext() {
        super(null);
    }

    @Override // com.amazon.music.curate.skyfire.local.LocalSkillContext
    public String destination() {
        return ShowRecentlyPlayedSkill.class.getCanonicalName();
    }
}
