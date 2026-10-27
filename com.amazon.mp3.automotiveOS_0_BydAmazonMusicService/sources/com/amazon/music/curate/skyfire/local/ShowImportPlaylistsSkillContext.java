package com.amazon.music.curate.skyfire.local;

/* JADX INFO: loaded from: classes4.dex */
public final class ShowImportPlaylistsSkillContext extends LocalSkillContext {
    public ShowImportPlaylistsSkillContext() {
        super(null);
    }

    @Override // com.amazon.music.curate.skyfire.local.LocalSkillContext
    public String destination() {
        return ShowImportPlaylistsSkill.class.getCanonicalName();
    }
}
