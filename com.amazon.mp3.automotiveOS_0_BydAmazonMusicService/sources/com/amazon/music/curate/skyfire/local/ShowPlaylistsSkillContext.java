package com.amazon.music.curate.skyfire.local;

/* JADX INFO: loaded from: classes4.dex */
public final class ShowPlaylistsSkillContext extends LocalSkillContext {
    public ShowPlaylistsSkillContext() {
        super(null);
    }

    @Override // com.amazon.music.curate.skyfire.local.LocalSkillContext
    public String destination() {
        return ShowPlaylistsSkill.class.getCanonicalName();
    }
}
