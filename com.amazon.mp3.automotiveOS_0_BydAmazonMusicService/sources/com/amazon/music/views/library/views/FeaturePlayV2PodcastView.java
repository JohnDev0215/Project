package com.amazon.music.views.library.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.models.PodcastFeaturePlayModel;
import com.amazon.music.views.library.providers.FeaturedPlayMusicProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturePlayV2PodcastView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\t¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u001a\u0010\u0011\u001a\u00020\rX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/views/library/views/FeaturePlayV2PodcastView;", "Lcom/amazon/music/views/library/views/FeaturePlayV2BaseView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "attrs", "Landroid/util/AttributeSet;", "isScreenSizeGreaterThanMD", "", "playIconOnClickMusicProvider", "Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "defStyleAttr", "", "defStyleRes", "isSonicRushEnabled", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Landroid/util/AttributeSet;ZLcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;IIZ)V", "errorStateImage", "getErrorStateImage", "()I", "setErrorStateImage", "(I)V", "setPlayState", "", "model", "Lcom/amazon/music/views/library/models/PodcastFeaturePlayModel;", "stopEqualizer", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeaturePlayV2PodcastView extends FeaturePlayV2BaseView {
    private int errorStateImage;

    public /* synthetic */ FeaturePlayV2PodcastView(Context context, StyleSheet styleSheet, AttributeSet attributeSet, boolean z, FeaturedPlayMusicProvider featuredPlayMusicProvider, int i, int i2, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, (i3 & 4) != 0 ? null : attributeSet, z, featuredPlayMusicProvider, (i3 & 32) != 0 ? 0 : i, (i3 & 64) != 0 ? 0 : i2, (i3 & 128) != 0 ? false : z2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeaturePlayV2PodcastView(Context context, StyleSheet styleSheet, AttributeSet attributeSet, boolean z, FeaturedPlayMusicProvider playIconOnClickMusicProvider, int i, int i2, boolean z2) {
        super(context, styleSheet, attributeSet, z, playIconOnClickMusicProvider, i, i2, z2);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(playIconOnClickMusicProvider, "playIconOnClickMusicProvider");
        this.errorStateImage = R$drawable.ic_empty_state_image;
    }

    @Override // com.amazon.music.views.library.views.FeaturePlayV2BaseView
    protected int getErrorStateImage() {
        return this.errorStateImage;
    }

    @Override // com.amazon.music.views.library.views.FeaturePlayV2BaseView
    protected void setErrorStateImage(int i) {
        this.errorStateImage = i;
    }

    @Override // com.amazon.music.views.library.views.FeaturePlayV2BaseView
    public void setPlayState(PodcastFeaturePlayModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        Integer playState = model.getPlayState();
        if (playState != null && playState.intValue() == 3) {
            startEqualizer();
            return;
        }
        boolean z = true;
        if ((playState == null || playState.intValue() != 2) && (playState == null || playState.intValue() != 6)) {
            z = false;
        }
        if (z) {
            pauseEqualizer();
        } else {
            stopEqualizer(model);
        }
    }

    public void stopEqualizer(final PodcastFeaturePlayModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        if (getIsScreenSizeGreaterThanMD()) {
            if (getIsSonicRushEnabled()) {
                getPlayButtonWideScreenTextView().setTextColor(getResources().getColor(R$color.secondary));
                getPlayButtonWideScreen().setBackgroundResource(R$drawable.refresh_accent_btn);
                getPlayButtonWideScreenImageView().setImageDrawable(getRefreshPlayDrawable());
            } else {
                getPlayButtonWideScreenTextView().setTextColor(getResources().getColor(R$color.primary));
                getPlayButtonWideScreen().setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
                getPlayButtonWideScreenImageView().setImageDrawable(getPlayDrawable());
            }
            getPlayButtonWideScreenTextView().setText(getButtonPlayText());
            getPlayButtonWideScreen().setClickable(true);
            getPlayButtonWideScreen().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.FeaturePlayV2PodcastView$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FeaturePlayV2PodcastView.m2413stopEqualizer$lambda0(this.f$0, model, view);
                }
            });
            return;
        }
        if (getIsSonicRushEnabled()) {
            getPlayButton().setImageDrawable(getRefreshPlayDrawable());
            getPlayButton().setBackgroundResource(R$drawable.refresh_accent_btn);
        } else {
            getPlayButton().setImageDrawable(getPlayDrawable());
            getPlayButton().setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
        }
        getPlayButton().setClickable(true);
        getPlayButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.FeaturePlayV2PodcastView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeaturePlayV2PodcastView.m2414stopEqualizer$lambda1(this.f$0, model, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: stopEqualizer$lambda-0, reason: not valid java name */
    public static final void m2413stopEqualizer$lambda0(FeaturePlayV2PodcastView this$0, PodcastFeaturePlayModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.getPlayIconOnClickMusicProvider().startPlayback(model.getMetadata());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: stopEqualizer$lambda-1, reason: not valid java name */
    public static final void m2414stopEqualizer$lambda1(FeaturePlayV2PodcastView this$0, PodcastFeaturePlayModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.getPlayIconOnClickMusicProvider().startPlayback(model.getMetadata());
    }
}
