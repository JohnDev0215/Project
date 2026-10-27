package com.amazon.video.sdk.avod.playbackclient.subtitle.views;

import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.amazon.avod.playbackclient.subtitle.views.util.EdgeRenderer;
import com.amazon.avod.playbackclient.subtitle.views.util.SubtitleRenderPreset;
import com.amazon.video.player.R$dimen;
import com.amazon.video.player.R$drawable;
import com.amazon.video.player.R$id;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public class TTMLV2TextViewPool {
    private final int mBaseTextSize;
    private final EdgeRenderer mEdgeRenderer;
    private final ViewGroup mRootView;
    private final TextView mSubtitleTextView;
    private final FrameLayout mTextContainer;
    private final Map<TextView, Boolean> mTextViewTracker;
    private final Object mMutex = new Object();
    private float mFontSize = -1.0f;
    private SubtitleRenderPreset mSubtitleRenderPreset = null;

    public TTMLV2TextViewPool(@Nonnull ViewGroup viewGroup, @Nonnull EdgeRenderer edgeRenderer) {
        ViewGroup viewGroup2 = (ViewGroup) Preconditions.checkNotNull(viewGroup, "rootView");
        this.mRootView = viewGroup2;
        this.mEdgeRenderer = (EdgeRenderer) Preconditions.checkNotNull(edgeRenderer, "edgeRenderer");
        this.mTextViewTracker = new HashMap();
        this.mBaseTextSize = viewGroup2.getResources().getDimensionPixelSize(R$dimen.avod_languages_base_text_size);
        FrameLayout frameLayout = (FrameLayout) Preconditions.checkNotNull((FrameLayout) viewGroup2.findViewById(R$id.SubtitleContainer));
        this.mTextContainer = frameLayout;
        this.mSubtitleTextView = (TextView) Preconditions.checkNotNull((TextView) frameLayout.findViewById(R$id.SubtitleView));
    }

    public void clear() {
        synchronized (this.mMutex) {
            Iterator<Map.Entry<TextView, Boolean>> it = this.mTextViewTracker.entrySet().iterator();
            while (it.hasNext()) {
                this.mTextViewTracker.put(it.next().getKey(), Boolean.FALSE);
            }
        }
    }

    @Nonnull
    public TextView vendTextView() {
        synchronized (this.mMutex) {
            Iterator<Map.Entry<TextView, Boolean>> it = this.mTextViewTracker.entrySet().iterator();
            while (it.hasNext()) {
                TextView key = it.next().getKey();
                if (!this.mTextViewTracker.get(key).booleanValue()) {
                    this.mTextViewTracker.put(key, Boolean.TRUE);
                    return key;
                }
            }
            TextView textViewCreateTextView = createTextView();
            this.mTextViewTracker.put(textViewCreateTextView, Boolean.TRUE);
            return textViewCreateTextView;
        }
    }

    public void hideAllTextViews() {
        synchronized (this.mMutex) {
            Iterator<Map.Entry<TextView, Boolean>> it = this.mTextViewTracker.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getKey().setVisibility(8);
            }
        }
    }

    public void applyRenderPreset(@Nonnull SubtitleRenderPreset subtitleRenderPreset) {
        Preconditions.checkNotNull(subtitleRenderPreset, "Cannot set null subtitle preset");
        synchronized (this.mMutex) {
            if (this.mTextViewTracker.size() == 0) {
                this.mSubtitleTextView.setEllipsize(null);
                this.mTextViewTracker.put(this.mSubtitleTextView, Boolean.FALSE);
            }
            Iterator<Map.Entry<TextView, Boolean>> it = this.mTextViewTracker.entrySet().iterator();
            while (it.hasNext()) {
                applyPresetToView(subtitleRenderPreset, it.next().getKey());
            }
            this.mSubtitleRenderPreset = subtitleRenderPreset;
        }
    }

    private TextView createTextView() {
        TextView textView = new TextView(this.mRootView.getContext());
        FrameLayout.LayoutParams layoutParams = textView.getLayoutParams() instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) textView.getLayoutParams() : new FrameLayout.LayoutParams(-2, -2);
        Resources resources = this.mRootView.getResources();
        textView.setBackground(resources.getDrawable(R$drawable.window_with_border));
        int dimension = (int) resources.getDimension(R$dimen.avod_spacing_xxsmall);
        textView.setPadding(dimension, dimension, dimension, dimension);
        textView.setLayoutParams(layoutParams);
        textView.setEllipsize(null);
        SubtitleRenderPreset subtitleRenderPreset = this.mSubtitleRenderPreset;
        if (subtitleRenderPreset != null) {
            applyPresetToView(subtitleRenderPreset, textView);
        }
        float f = this.mFontSize;
        if (f != -1.0f) {
            textView.setTextSize(f);
        }
        this.mTextContainer.addView(textView);
        return textView;
    }

    private void applyPresetToView(@Nonnull SubtitleRenderPreset subtitleRenderPreset, @Nonnull TextView textView) {
        textView.setTextSize(this.mBaseTextSize * subtitleRenderPreset.getTextFontScale());
        textView.setTextColor(subtitleRenderPreset.getTextArgbColor());
        textView.setTypeface(subtitleRenderPreset.getTextTypeface());
        textView.setTransformationMethod(subtitleRenderPreset.getTextTransformationMethod());
        GradientDrawable gradientDrawable = (GradientDrawable) textView.getBackground();
        gradientDrawable.mutate();
        gradientDrawable.setColor(subtitleRenderPreset.getBackgroundArgbColor());
        gradientDrawable.setStroke((int) textView.getResources().getDimension(R$dimen.avod_spacing_micro), subtitleRenderPreset.getWindowArgbColor());
        this.mEdgeRenderer.applyEdge(textView, subtitleRenderPreset.getTextEdgeType());
        textView.setText(textView.getText(), TextView.BufferType.SPANNABLE);
    }
}
