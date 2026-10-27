package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ContentEventFragmentRequested extends ContentEventBase {
    private final FragmentInformationHolder mFragmentInformationHolder;

    public ContentEventFragmentRequested(PlayableContent playableContent, ContentSessionType contentSessionType, FragmentInformationHolder fragmentInformationHolder) {
        super(playableContent, contentSessionType);
        this.mFragmentInformationHolder = (FragmentInformationHolder) Preconditions.checkNotNull(fragmentInformationHolder, "fragmentInformationHolder");
    }

    @Nonnull
    public FragmentInformationHolder getFragmentInformationHolder() {
        return this.mFragmentInformationHolder;
    }
}
