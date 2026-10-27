package com.amazon.mp3.actionbar.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.customerprofile.CustomerProfileDetailManager;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.library.fragment.SourceToggleDialogFragment;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.CompositeOnSourceChangedListener;
import com.amazon.mp3.util.Log;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.utils.FontUtil;

/* JADX INFO: loaded from: classes3.dex */
public class ActionBarSourceToggleView extends LinearLayout {
    private static final String TAG = ActionBarSourceToggleView.class.getSimpleName();
    private ActionBarRightButton mActionButtonOverflow;
    private String mCloudLibraryName;
    private String mDeviceLibraryName;
    private FragmentManager mFragmentManager;
    private boolean mIsTogglable;
    private CompositeOnSourceChangedListener mListener;
    private TextView mSourceToggleView;

    public enum Source {
        CLOUD,
        DEVICE
    }

    public interface SourceChangedListener {
        void onSourceToggled(Source source);
    }

    public ActionBarSourceToggleView(final FragmentActivity activity, final FragmentManager fm, final boolean isTogglable) {
        super(activity);
        this.mFragmentManager = fm;
        this.mIsTogglable = isTogglable;
        init(activity);
    }

    private void init(final FragmentActivity activity) {
        ActionBarRightButton actionBarRightButton;
        View.inflate(getContext(), R.layout.action_bar_source_toggle_view, this);
        this.mCloudLibraryName = getResources().getString(R.string.dmusic_library_source_cirrus_header);
        this.mDeviceLibraryName = getResources().getString(R.string.dmusic_library_source_local_header);
        this.mSourceToggleView = (TextView) findViewById(R.id.SourceToggle);
        this.mActionButtonOverflow = (ActionBarRightButton) findViewById(R.id.Action_Button_Overflow);
        View viewFindViewById = findViewById(R.id.SourceToggleContainer);
        View viewFindViewById2 = findViewById(R.id.SourceToggleIcon);
        this.mSourceToggleView.setText(getSourceString());
        if (AmazonApplication.getCapabilities().isCustomerProfileSupported() && (actionBarRightButton = (ActionBarRightButton) findViewById(R.id.Action_Button_Profile)) != null) {
            CustomerProfileDetailManager.getInstance().loadAvatarImage(getContext(), (ImageView) actionBarRightButton.findViewById(R.id.Profile));
        }
        if (this.mIsTogglable) {
            viewFindViewById2.setVisibility(0);
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarSourceToggleView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    ActionBarSourceToggleView.this.showToggleDialog();
                }
            });
        } else {
            viewFindViewById2.setVisibility(8);
        }
        StyleSheetProvider.getStyleSheet().observe(activity, new Observer() { // from class: com.amazon.mp3.actionbar.view.ActionBarSourceToggleView$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$0((StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0(StyleSheet styleSheet) {
        FontUtil.INSTANCE.applyFontStyle(this.mSourceToggleView, styleSheet.getFontStyle(FontStyleKey.HEADLINE_4));
        this.mActionButtonOverflow.initButtonStyle(styleSheet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToggleDialog() {
        SourceToggleDialogFragment.newInstance().show(this.mFragmentManager, "MusicSourceToggleDialog");
    }

    public String getSourceString() {
        if (LastViewedScreenUtil.getLastViewedSource(getContext(), LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource().equals("cirrus")) {
            return this.mCloudLibraryName;
        }
        return this.mDeviceLibraryName;
    }

    public void setCompositeSourceChangedListener(CompositeOnSourceChangedListener listener) {
        this.mListener = listener;
    }

    public void registerOnSourceChangedListener(SourceChangedListener listener) {
        this.mListener.addSourceChangedListener(listener);
    }

    public void setSource(Source source) {
        if (source == Source.CLOUD) {
            Log.debug(TAG, "Changed to Cloud library");
            this.mSourceToggleView.setText(this.mCloudLibraryName);
        } else {
            Log.debug(TAG, "Changed to Device Library");
            this.mSourceToggleView.setText(this.mDeviceLibraryName);
        }
    }

    public void onSourceToggled(Source source) {
        if (this.mListener == null || getCurrentSource() == source) {
            return;
        }
        setSource(source);
        this.mListener.onSourceToggled(source);
    }

    public Source getCurrentSource() {
        if (LastViewedScreenUtil.getLastViewedSource(getContext(), LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource().equals("cirrus")) {
            return Source.CLOUD;
        }
        return Source.DEVICE;
    }

    public void updateSourceText() {
        setSource(getCurrentSource());
    }
}
