package com.amazon.music.curate.skyfire.local;

/* JADX INFO: loaded from: classes4.dex */
public final class ShowSongsSkillContext extends LocalSkillContext {
    public ShowSongsSkillContext() {
        super(null);
    }

    @Override // com.amazon.music.curate.skyfire.local.LocalSkillContext
    public String destination() {
        return ShowSongsSkill.class.getCanonicalName();
    }
}
