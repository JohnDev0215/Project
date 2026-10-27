package com.amazon.music.find.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.amazon.music.find.R;
import com.amazon.music.find.providers.SpellCorrectionListenerClickProvider;
import com.amazon.music.search.SpellCorrections;
import com.amazon.music.views.library.styles.LinkStyleKey;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.LinkStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.Link;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchResponseMessageView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J,\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J:\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\rJT\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/find/view/SearchResponseMessageView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "addViews", "", "firstLine", "Landroid/widget/TextView;", "secondLine", "shouldCenterMessage", "", "spacer", "", "showNoResultFoundMessage", "primaryMessage", "", "secondaryMessage", "primaryMessageFontStyle", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "secondaryMessageFontStyle", "spaceBetweenMessage", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "showSearchResponseMessage", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "linkText", "onClickListener", "Lcom/amazon/music/find/providers/SpellCorrectionListenerClickProvider;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchResponseMessageView extends ConstraintLayout {
    private final StyleSheet styleSheet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchResponseMessageView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        setId(View.generateViewId());
        LayoutParamUtils.INSTANCE.setLayoutMargins(this, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : spacerInPixels, (240 & 32) != 0 ? null : 0, (240 & 64) != 0 ? null : spacerInPixels, (240 & 128) != 0 ? null : 0);
    }

    public final void showSearchResponseMessage(final SpellCorrections spellCorrections, final String linkText, final SpellCorrectionListenerClickProvider onClickListener, String primaryMessage, String secondaryMessage, FontStyleKey primaryMessageFontStyle, FontStyleKey secondaryMessageFontStyle, SpacerKey spaceBetweenMessage, boolean shouldCenterMessage) {
        Link linkBuild;
        Intrinsics.checkNotNullParameter(spellCorrections, "spellCorrections");
        Intrinsics.checkNotNullParameter(linkText, "linkText");
        Intrinsics.checkNotNullParameter(primaryMessageFontStyle, "primaryMessageFontStyle");
        Intrinsics.checkNotNullParameter(secondaryMessageFontStyle, "secondaryMessageFontStyle");
        Intrinsics.checkNotNullParameter(spaceBetweenMessage, "spaceBetweenMessage");
        FontStyle fontStyle = this.styleSheet.getFontStyle(primaryMessageFontStyle);
        FontStyle fontStyle2 = this.styleSheet.getFontStyle(secondaryMessageFontStyle);
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(spaceBetweenMessage);
        LinkStyle linkStyle = this.styleSheet.getLinkStyle(LinkStyleKey.ACCENT);
        LinkStyle linkStyle2 = this.styleSheet.getLinkStyle(LinkStyleKey.STANDARD);
        if (fontStyle2 == null || fontStyle == null || linkStyle == null || linkStyle2 == null || spacerInPixels == null) {
            return;
        }
        Link linkBuild2 = null;
        if (primaryMessage != null) {
            Link.LinkStringBuilder linkStringBuilderAppend = new Link.LinkStringBuilder().withLinkStyle(linkStyle2).withFontStyle(fontStyle).append(primaryMessage);
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            linkBuild = linkStringBuilderAppend.build(context);
        } else {
            linkBuild = null;
        }
        if (secondaryMessage != null) {
            Link.LinkStringBuilder linkSubstring = new Link.LinkStringBuilder().withLinkStyle(linkStyle).withFontStyle(fontStyle2).setLinkSubstring(secondaryMessage, linkText, new Function0<Unit>() { // from class: com.amazon.music.find.view.SearchResponseMessageView$showSearchResponseMessage$secondLine$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    SpellCorrectionListenerClickProvider spellCorrectionListenerClickProvider = onClickListener;
                    if (spellCorrectionListenerClickProvider == null) {
                        return;
                    }
                    spellCorrectionListenerClickProvider.onClick(linkText, spellCorrections);
                }
            });
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            linkBuild2 = linkSubstring.build(context2);
        }
        if (spellCorrections.hasAutoCorrection()) {
            if (linkBuild != null) {
                linkBuild.setId(R.id.auto_correction_text_view);
            }
            if (linkBuild2 != null) {
                linkBuild2.setId(R.id.correction_original_text_view);
            }
        } else if (spellCorrections.hasDidYouMeanCorrection()) {
            if (linkBuild != null) {
                linkBuild.setId(R.id.correction_original_text_view);
            }
            if (linkBuild2 != null) {
                linkBuild2.setId(R.id.did_you_mean_text_view);
            }
        } else {
            if (linkBuild != null) {
                linkBuild.setId(View.generateViewId());
            }
            if (linkBuild2 != null) {
                linkBuild2.setId(View.generateViewId());
            }
        }
        addViews(linkBuild, linkBuild2, shouldCenterMessage, spacerInPixels.intValue());
    }

    public final void showNoResultFoundMessage(String primaryMessage, String secondaryMessage, FontStyleKey primaryMessageFontStyle, FontStyleKey secondaryMessageFontStyle, SpacerKey spaceBetweenMessage, boolean shouldCenterMessage) {
        Intrinsics.checkNotNullParameter(primaryMessageFontStyle, "primaryMessageFontStyle");
        Intrinsics.checkNotNullParameter(secondaryMessageFontStyle, "secondaryMessageFontStyle");
        Intrinsics.checkNotNullParameter(spaceBetweenMessage, "spaceBetweenMessage");
        TextView textView = new TextView(getContext());
        textView.setText(primaryMessage);
        textView.setId(View.generateViewId());
        TextView textView2 = new TextView(getContext());
        textView2.setText(secondaryMessage);
        textView2.setId(R.id.search_no_results);
        FontStyle fontStyle = this.styleSheet.getFontStyle(primaryMessageFontStyle);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(textView, fontStyle);
        }
        FontStyle fontStyle2 = this.styleSheet.getFontStyle(secondaryMessageFontStyle);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(textView2, fontStyle2);
        }
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(spaceBetweenMessage);
        if (spacerInPixels != null) {
            addViews(textView, textView2, shouldCenterMessage, spacerInPixels.intValue());
        }
        Integer spacerInPixels2 = this.styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        LayoutParamUtils.INSTANCE.setLayoutMargins(this, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : spacerInPixels2, (240 & 32) != 0 ? null : spacerInPixels2, (240 & 64) != 0 ? null : spacerInPixels2, (240 & 128) != 0 ? null : 0);
    }

    private final void addViews(TextView firstLine, TextView secondLine, boolean shouldCenterMessage, int spacer) {
        if (firstLine != null) {
            addView(firstLine);
        }
        if (secondLine != null) {
            addView(secondLine);
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        if (firstLine != null) {
            constraintSet.connect(firstLine.getId(), 3, getId(), 3, 0);
        }
        if (secondLine != null) {
            if (firstLine != null) {
                constraintSet.connect(secondLine.getId(), 3, firstLine.getId(), 4, spacer);
            }
            constraintSet.connect(secondLine.getId(), 4, getId(), 4, 0);
        }
        if (shouldCenterMessage) {
            if (firstLine != null) {
                constraintSet.connect(firstLine.getId(), 6, getId(), 6, 0);
            }
            if (firstLine != null) {
                constraintSet.connect(firstLine.getId(), 7, getId(), 7, 0);
            }
            if (secondLine != null) {
                constraintSet.connect(secondLine.getId(), 6, getId(), 6, 0);
            }
            if (secondLine != null) {
                constraintSet.connect(secondLine.getId(), 7, getId(), 7, 0);
            }
        }
        constraintSet.applyTo(this);
    }
}
