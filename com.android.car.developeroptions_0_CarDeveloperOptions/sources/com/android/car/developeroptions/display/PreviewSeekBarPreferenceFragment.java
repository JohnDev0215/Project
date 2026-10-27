package com.android.car.developeroptions.display;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.widget.DotsPageIndicator;
import com.android.car.developeroptions.widget.LabeledSeekBar;

/* JADX INFO: loaded from: classes.dex */
public abstract class PreviewSeekBarPreferenceFragment extends SettingsPreferenceFragment {
    protected int mCurrentIndex;
    protected String[] mEntries;
    protected int mInitialIndex;
    private TextView mLabel;
    private View mLarger;
    private DotsPageIndicator mPageIndicator;
    private ViewPager mPreviewPager;
    private PreviewPagerAdapter mPreviewPagerAdapter;
    private LabeledSeekBar mSeekBar;
    private View mSmaller;
    private ViewPager.OnPageChangeListener mPreviewPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.android.car.developeroptions.display.PreviewSeekBarPreferenceFragment.1
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            PreviewSeekBarPreferenceFragment.this.mPreviewPager.sendAccessibilityEvent(16384);
        }
    };
    private ViewPager.OnPageChangeListener mPageIndicatorPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.android.car.developeroptions.display.PreviewSeekBarPreferenceFragment.2
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            PreviewSeekBarPreferenceFragment.this.setPagerIndicatorContentDescription(i);
        }
    };

    protected abstract void commit();

    protected abstract Configuration createConfig(Configuration configuration, int i);

    protected abstract int getActivityLayoutResId();

    protected abstract int[] getPreviewSampleResIds();

    /* JADX INFO: Access modifiers changed from: private */
    class onPreviewSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        private boolean mSeekByTouch;

        private onPreviewSeekBarChangeListener() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            PreviewSeekBarPreferenceFragment.this.setPreviewLayer(i, false);
            if (this.mSeekByTouch) {
                return;
            }
            PreviewSeekBarPreferenceFragment.this.commit();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            this.mSeekByTouch = true;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (PreviewSeekBarPreferenceFragment.this.mPreviewPagerAdapter.isAnimating()) {
                PreviewSeekBarPreferenceFragment.this.mPreviewPagerAdapter.setAnimationEndAction(new Runnable() { // from class: com.android.car.developeroptions.display.-$$Lambda$PreviewSeekBarPreferenceFragment$onPreviewSeekBarChangeListener$CYb4YShEh64gn_X7iPr5S-PwhjU
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onStopTrackingTouch$0$PreviewSeekBarPreferenceFragment$onPreviewSeekBarChangeListener();
                    }
                });
            } else {
                PreviewSeekBarPreferenceFragment.this.commit();
            }
            this.mSeekByTouch = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onStopTrackingTouch$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onStopTrackingTouch$0$PreviewSeekBarPreferenceFragment$onPreviewSeekBarChangeListener() {
            PreviewSeekBarPreferenceFragment.this.commit();
        }
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        ViewGroup viewGroup2 = (ViewGroup) viewOnCreateView.findViewById(R.id.list_container);
        viewGroup2.removeAllViews();
        View viewInflate = layoutInflater.inflate(getActivityLayoutResId(), viewGroup2, false);
        viewGroup2.addView(viewInflate);
        this.mLabel = (TextView) viewInflate.findViewById(com.android.car.developeroptions.R.id.current_label);
        int iMax = Math.max(1, this.mEntries.length - 1);
        LabeledSeekBar labeledSeekBar = (LabeledSeekBar) viewInflate.findViewById(com.android.car.developeroptions.R.id.seek_bar);
        this.mSeekBar = labeledSeekBar;
        labeledSeekBar.setLabels(this.mEntries);
        this.mSeekBar.setMax(iMax);
        View viewFindViewById = viewInflate.findViewById(com.android.car.developeroptions.R.id.smaller);
        this.mSmaller = viewFindViewById;
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.display.-$$Lambda$PreviewSeekBarPreferenceFragment$I2Id4G4xQSyfif6PPL46RHaRwMY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$0$PreviewSeekBarPreferenceFragment(view);
            }
        });
        View viewFindViewById2 = viewInflate.findViewById(com.android.car.developeroptions.R.id.larger);
        this.mLarger = viewFindViewById2;
        viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.display.-$$Lambda$PreviewSeekBarPreferenceFragment$Y8t22EJFasdHvYxYTmcinqu6Loo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$1$PreviewSeekBarPreferenceFragment(view);
            }
        });
        if (this.mEntries.length == 1) {
            this.mSeekBar.setEnabled(false);
        }
        Context context = getContext();
        Configuration configuration = context.getResources().getConfiguration();
        boolean z = configuration.getLayoutDirection() == 1;
        Configuration[] configurationArr = new Configuration[this.mEntries.length];
        for (int i = 0; i < this.mEntries.length; i++) {
            configurationArr[i] = createConfig(configuration, i);
        }
        int[] previewSampleResIds = getPreviewSampleResIds();
        this.mPreviewPager = (ViewPager) viewInflate.findViewById(com.android.car.developeroptions.R.id.preview_pager);
        PreviewPagerAdapter previewPagerAdapter = new PreviewPagerAdapter(context, z, previewSampleResIds, configurationArr);
        this.mPreviewPagerAdapter = previewPagerAdapter;
        this.mPreviewPager.setAdapter(previewPagerAdapter);
        this.mPreviewPager.setCurrentItem(z ? previewSampleResIds.length - 1 : 0);
        this.mPreviewPager.addOnPageChangeListener(this.mPreviewPageChangeListener);
        DotsPageIndicator dotsPageIndicator = (DotsPageIndicator) viewInflate.findViewById(com.android.car.developeroptions.R.id.page_indicator);
        this.mPageIndicator = dotsPageIndicator;
        if (previewSampleResIds.length > 1) {
            dotsPageIndicator.setViewPager(this.mPreviewPager);
            this.mPageIndicator.setVisibility(0);
            this.mPageIndicator.setOnPageChangeListener(this.mPageIndicatorPageChangeListener);
        } else {
            dotsPageIndicator.setVisibility(8);
        }
        setPreviewLayer(this.mInitialIndex, false);
        return viewOnCreateView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateView$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateView$0$PreviewSeekBarPreferenceFragment(View view) {
        int progress = this.mSeekBar.getProgress();
        if (progress > 0) {
            this.mSeekBar.setProgress(progress - 1, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateView$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateView$1$PreviewSeekBarPreferenceFragment(View view) {
        int progress = this.mSeekBar.getProgress();
        if (progress < this.mSeekBar.getMax()) {
            this.mSeekBar.setProgress(progress + 1, true);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mSeekBar.setProgress(this.mCurrentIndex);
        this.mSeekBar.setOnSeekBarChangeListener(new onPreviewSeekBarChangeListener());
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mSeekBar.setOnSeekBarChangeListener(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPreviewLayer(int i, boolean z) {
        this.mLabel.setText(this.mEntries[i]);
        this.mSmaller.setEnabled(i > 0);
        this.mLarger.setEnabled(i < this.mEntries.length - 1);
        setPagerIndicatorContentDescription(this.mPreviewPager.getCurrentItem());
        this.mPreviewPagerAdapter.setPreviewLayer(i, this.mCurrentIndex, this.mPreviewPager.getCurrentItem(), z);
        this.mCurrentIndex = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPagerIndicatorContentDescription(int i) {
        this.mPageIndicator.setContentDescription(getString(com.android.car.developeroptions.R.string.preview_page_indicator_content_description, Integer.valueOf(i + 1), Integer.valueOf(getPreviewSampleResIds().length)));
    }
}
