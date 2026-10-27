package com.android.car.developeroptions.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceGroupAdapter;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.SettingsPreferenceFragment;

/* JADX INFO: loaded from: classes.dex */
public class HighlightablePreferenceGroupAdapter extends PreferenceGroupAdapter {
    static final long DELAY_HIGHLIGHT_DURATION_MILLIS = 600;
    boolean mFadeInAnimated;
    final int mHighlightColor;
    private final String mHighlightKey;
    private int mHighlightPosition;
    private boolean mHighlightRequested;
    private final int mNormalBackgroundRes;

    public static void adjustInitialExpandedChildCount(SettingsPreferenceFragment settingsPreferenceFragment) {
        PreferenceScreen preferenceScreen;
        if (settingsPreferenceFragment == null || (preferenceScreen = settingsPreferenceFragment.getPreferenceScreen()) == null) {
            return;
        }
        Bundle arguments = settingsPreferenceFragment.getArguments();
        if (arguments != null && !TextUtils.isEmpty(arguments.getString(":settings:fragment_args_key"))) {
            preferenceScreen.setInitialExpandedChildrenCount(Integer.MAX_VALUE);
            return;
        }
        int initialExpandedChildCount = settingsPreferenceFragment.getInitialExpandedChildCount();
        if (initialExpandedChildCount <= 0) {
            return;
        }
        preferenceScreen.setInitialExpandedChildrenCount(initialExpandedChildCount);
    }

    public HighlightablePreferenceGroupAdapter(PreferenceGroup preferenceGroup, String str, boolean z) {
        super(preferenceGroup);
        this.mHighlightPosition = -1;
        this.mHighlightKey = str;
        this.mHighlightRequested = z;
        Context context = preferenceGroup.getContext();
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
        this.mNormalBackgroundRes = typedValue.resourceId;
        this.mHighlightColor = context.getColor(com.android.car.developeroptions.R.color.preference_highligh_color);
    }

    @Override // androidx.preference.PreferenceGroupAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder, int i) {
        super.onBindViewHolder(preferenceViewHolder, i);
        updateBackground(preferenceViewHolder, i);
    }

    void updateBackground(PreferenceViewHolder preferenceViewHolder, int i) {
        View view = preferenceViewHolder.itemView;
        if (i == this.mHighlightPosition) {
            addHighlightBackground(view, !this.mFadeInAnimated);
        } else if (Boolean.TRUE.equals(view.getTag(com.android.car.developeroptions.R.id.preference_highlighted))) {
            removeHighlightBackground(view, false);
        }
    }

    public void requestHighlight(View view, final RecyclerView recyclerView) {
        if (this.mHighlightRequested || recyclerView == null || TextUtils.isEmpty(this.mHighlightKey)) {
            return;
        }
        view.postDelayed(new Runnable() { // from class: com.android.car.developeroptions.widget.-$$Lambda$HighlightablePreferenceGroupAdapter$XgZjmeU0RrnQBw7fMvgi08bdwHU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$requestHighlight$0$HighlightablePreferenceGroupAdapter(recyclerView);
            }
        }, DELAY_HIGHLIGHT_DURATION_MILLIS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$requestHighlight$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$requestHighlight$0$HighlightablePreferenceGroupAdapter(RecyclerView recyclerView) {
        int preferenceAdapterPosition = getPreferenceAdapterPosition(this.mHighlightKey);
        if (preferenceAdapterPosition < 0) {
            return;
        }
        this.mHighlightRequested = true;
        recyclerView.smoothScrollToPosition(preferenceAdapterPosition);
        this.mHighlightPosition = preferenceAdapterPosition;
        notifyItemChanged(preferenceAdapterPosition);
    }

    public boolean isHighlightRequested() {
        return this.mHighlightRequested;
    }

    void requestRemoveHighlightDelayed(final View view) {
        view.postDelayed(new Runnable() { // from class: com.android.car.developeroptions.widget.-$$Lambda$HighlightablePreferenceGroupAdapter$sENPBrWttoAhFymjqUrzVKjLqEA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$requestRemoveHighlightDelayed$1$HighlightablePreferenceGroupAdapter(view);
            }
        }, 15000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$requestRemoveHighlightDelayed$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$requestRemoveHighlightDelayed$1$HighlightablePreferenceGroupAdapter(View view) {
        this.mHighlightPosition = -1;
        removeHighlightBackground(view, true);
    }

    private void addHighlightBackground(final View view, boolean z) {
        view.setTag(com.android.car.developeroptions.R.id.preference_highlighted, Boolean.TRUE);
        if (!z) {
            view.setBackgroundColor(this.mHighlightColor);
            Log.d("HighlightableAdapter", "AddHighlight: Not animation requested - setting highlight background");
            requestRemoveHighlightDelayed(view);
            return;
        }
        this.mFadeInAnimated = true;
        ValueAnimator valueAnimatorOfObject = ValueAnimator.ofObject(new ArgbEvaluator(), -1, Integer.valueOf(this.mHighlightColor));
        valueAnimatorOfObject.setDuration(200L);
        valueAnimatorOfObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.car.developeroptions.widget.-$$Lambda$HighlightablePreferenceGroupAdapter$I3Gfv8oz0onfDj3L95yMu71PHR0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        valueAnimatorOfObject.setRepeatMode(2);
        valueAnimatorOfObject.setRepeatCount(4);
        valueAnimatorOfObject.start();
        Log.d("HighlightableAdapter", "AddHighlight: starting fade in animation");
        requestRemoveHighlightDelayed(view);
    }

    private void removeHighlightBackground(final View view, boolean z) {
        Boolean bool = Boolean.FALSE;
        if (!z) {
            view.setTag(com.android.car.developeroptions.R.id.preference_highlighted, bool);
            view.setBackgroundResource(this.mNormalBackgroundRes);
            Log.d("HighlightableAdapter", "RemoveHighlight: No animation requested - setting normal background");
        } else {
            if (!Boolean.TRUE.equals(view.getTag(com.android.car.developeroptions.R.id.preference_highlighted))) {
                Log.d("HighlightableAdapter", "RemoveHighlight: Not highlighted - skipping");
                return;
            }
            int i = this.mHighlightColor;
            view.setTag(com.android.car.developeroptions.R.id.preference_highlighted, bool);
            ValueAnimator valueAnimatorOfObject = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(i), -1);
            valueAnimatorOfObject.setDuration(500L);
            valueAnimatorOfObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.car.developeroptions.widget.-$$Lambda$HighlightablePreferenceGroupAdapter$llx9CtDPLQfnIK6uEub7VvQcREQ
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    view.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                }
            });
            valueAnimatorOfObject.addListener(new AnimatorListenerAdapter() { // from class: com.android.car.developeroptions.widget.HighlightablePreferenceGroupAdapter.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    view.setBackgroundResource(HighlightablePreferenceGroupAdapter.this.mNormalBackgroundRes);
                }
            });
            valueAnimatorOfObject.start();
            Log.d("HighlightableAdapter", "Starting fade out animation");
        }
    }
}
