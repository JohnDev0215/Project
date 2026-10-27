package com.amazon.avod.media.service.prsv2;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.prs.ResourceParams;
import com.amazon.avod.prs.ResourceParamsCreator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDataParamsCreator implements ResourceParamsCreator {
    @Override // com.amazon.avod.prs.ResourceParamsCreator
    @Nonnull
    public ResourceParams create() throws ContentException {
        return new PlaybackDataParams();
    }

    private static class PlaybackDataParams implements ResourceParams {
        private PlaybackDataParams() {
        }
    }
}
