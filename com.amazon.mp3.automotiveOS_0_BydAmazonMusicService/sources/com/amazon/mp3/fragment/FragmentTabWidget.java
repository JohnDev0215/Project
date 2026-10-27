package com.amazon.mp3.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.service.metrics.BaseMetricsRecorder;
import com.amazon.mp3.styles.BauhausTextStyler;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class FragmentTabWidget extends LinearLayout {
    private static final String TAG = FragmentTabWidget.class.getSimpleName();
    private int mCloudToggleButtonID;
    private int mDeviceToggleButtonID;
    private boolean mLeftAligned;
    private OnTabClickListener mListener;
    private int mSelectedId;
    private SparseArray<Integer> mTabIDs;
    private ArrayList<TextView> mTabViewList;

    interface OnTabClickListener {
        void onTabClicked(int position);
    }

    public void setLeftAligned(boolean value) {
        this.mLeftAligned = value;
        if (value) {
            setGravity(8388611);
        }
    }

    public FragmentTabWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSelectedId = -1;
        this.mLeftAligned = false;
        init();
    }

    public void defocusAllTabs() {
        for (TextView textView : this.mTabViewList) {
            if (textView != null && textView.isPressed() && textView.getBackground() != null) {
                textView.getBackground().setState(View.ENABLED_STATE_SET);
            }
        }
    }

    void setOnTabClickListener(OnTabClickListener listener) {
        this.mListener = listener;
    }

    void addTab(int titleId, int position, int background, int width, int height) {
        TabTextView tabTextView = new TabTextView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        tabTextView.setBackgroundResource(background);
        setTabTextViewValues(titleId, position, tabTextView, layoutParams);
    }

    void setTabTextViewValues(final int titleId, final int position, TabTextView tv, LinearLayout.LayoutParams params) {
        float dimension;
        Resources resources = getResources();
        if (this.mLeftAligned && position == 0) {
            dimension = resources.getDimension(R.dimen.tiny_padding);
        } else {
            dimension = resources.getDimension(R.dimen.tab_left_right_padding);
        }
        int dimension2 = (int) resources.getDimension(R.dimen.tiny_padding);
        tv.setTextSize(0, resources.getDimension(R.dimen.small_text_size));
        tv.setPadding((int) dimension, 0, dimension2, 0);
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            tv.setText(new BauhausTextStyler().styleText(getContext().getResources().getText(titleId)));
        } else {
            tv.setText(titleId);
        }
        tv.setAllCaps(true);
        tv.setTextColor(getResources().getColorStateList(R.color.fragment_tab_text_color));
        tv.setFocusable(true);
        tv.setLayoutParams(params);
        tv.setSelected(false);
        tv.setGravity(17);
        tv.setTextAppearance(getContext(), R.style.TextShadow);
        tv.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.FragmentTabWidget.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BaseMetricsRecorder.getMetricsRecorder(FragmentTabWidget.this.getContext().getApplicationContext()).tabSelectedEvent(titleId);
                FragmentTabWidget.this.mListener.onTabClicked(position);
            }
        });
        this.mTabViewList.add(tv);
        addView(tv);
        if (this.mTabIDs.get(titleId) == null) {
            return;
        }
        tv.setId(this.mTabIDs.get(titleId).intValue());
    }

    void selectTab(int tabId) {
        int i = this.mSelectedId;
        if (i != -1) {
            this.mTabViewList.get(i).setSelected(false);
        }
        this.mSelectedId = tabId;
        this.mTabViewList.get(tabId).setSelected(true);
    }

    private void createTabIDs() {
        SparseArray<Integer> sparseArray = new SparseArray<>();
        this.mTabIDs = sparseArray;
        sparseArray.put(R.string.dmusic_library_playlists_tab, Integer.valueOf(R.id.TabWidgetPlaylistsTab));
        this.mTabIDs.put(R.string.dmusic_library_artists_tab, Integer.valueOf(R.id.TabWidgetArtistsTab));
        this.mTabIDs.put(R.string.dmusic_library_albums_tab, Integer.valueOf(R.id.TabWidgetAlbumsTab));
        this.mTabIDs.put(R.string.dmusic_library_songs_tab, Integer.valueOf(R.id.TabWidgetSongsTab));
        this.mTabIDs.put(R.string.dmusic_library_genres_tab, Integer.valueOf(R.id.TabWidgetGenreTab));
    }

    public void setCloudToggleButtonID(int id) {
        this.mCloudToggleButtonID = id;
    }

    public void setDeviceToggleButtonID(int id) {
        this.mDeviceToggleButtonID = id;
    }

    private static class TabTextView extends TextView {
        int mBackgroundID;

        public TabTextView(Context context) {
            super(context);
        }

        @Override // android.view.View
        public void setBackgroundResource(int resid) {
            super.setBackgroundResource(resid);
            this.mBackgroundID = resid;
        }

        @Override // android.view.View
        public void setBackgroundDrawable(Drawable background) {
            super.setBackgroundDrawable(background);
            this.mBackgroundID = 0;
        }

        @Override // android.widget.TextView, android.view.View
        public Parcelable onSaveInstanceState() {
            Bundle bundle = new Bundle();
            bundle.putParcelable("SUPER_INSTANCE_STATE", super.onSaveInstanceState());
            bundle.putInt("BACKGROUND", this.mBackgroundID);
            bundle.putInt("WIDTH", getLayoutParams().width);
            bundle.putInt("HEIGHT", getLayoutParams().height);
            return bundle;
        }

        @Override // android.widget.TextView, android.view.View
        public void onRestoreInstanceState(Parcelable state) {
            if (state instanceof Bundle) {
                Bundle bundle = (Bundle) state;
                this.mBackgroundID = bundle.getInt("BACKGROUND");
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.width = bundle.getInt("WIDTH");
                layoutParams.height = bundle.getInt("HEIGHT");
                setLayoutParams(layoutParams);
                int i = this.mBackgroundID;
                if (i != 0) {
                    setBackgroundResource(i);
                }
                state = bundle.getParcelable("SUPER_INSTANCE_STATE");
            }
            super.onRestoreInstanceState(state);
        }
    }

    private void init() {
        this.mTabViewList = new ArrayList<>();
        setGravity(17);
        setOrientation(0);
        createTabIDs();
    }
}
