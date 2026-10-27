package com.amazon.avod.playbackclient.subtitle.internal;

import android.text.Editable;
import android.text.Html;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleTagHandler implements Html.TagHandler {
    private final String mCurrentLanguageCode;
    private final SubtitleTextElement mSubtitleElement;
    private final TTMLV2CanvasUtil mTTMLV2CanvasUtil;

    public SubtitleTagHandler(@Nonnull SubtitleTextElement subtitleTextElement, @Nonnull TTMLV2CanvasUtil tTMLV2CanvasUtil, @Nullable String str) {
        this.mSubtitleElement = (SubtitleTextElement) Preconditions.checkNotNull(subtitleTextElement, "subtitleElement");
        this.mTTMLV2CanvasUtil = (TTMLV2CanvasUtil) Preconditions.checkNotNull(tTMLV2CanvasUtil, "TTMLV2CanvasUtil");
        this.mCurrentLanguageCode = str;
    }

    @Override // android.text.Html.TagHandler
    public void handleTag(boolean z, String str, Editable editable, XMLReader xMLReader) {
        if (editable == null || editable.length() == 0) {
            return;
        }
        String string = editable.toString();
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.addAll((Iterable) this.mSubtitleElement.getAnnotationElements());
        editable.setSpan(new SubtitleSpan(this.mSubtitleElement.getSubtitleText(), builder.build(), this.mSubtitleElement.isVertical(), this.mTTMLV2CanvasUtil, this.mCurrentLanguageCode), 0, string.length(), 33);
    }
}
