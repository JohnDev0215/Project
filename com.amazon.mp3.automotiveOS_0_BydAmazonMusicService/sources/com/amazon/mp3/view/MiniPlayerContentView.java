package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.view.PlayerContentView;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.BitmapUtil;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.extensions.MediaItemExtensions;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.FontSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseImage;

/* JADX INFO: loaded from: classes4.dex */
public class MiniPlayerContentView extends LinearLayout implements PlayerContentView {
    private BaseImage mArtwork;
    private ImageView mArtworkOverlay;
    private Context mContext;
    private TextView mDeviceName;
    private boolean mIsLoading;
    private FrameLayout mMiniPlayerArtworkContainer;
    private LinearLayout mMiniPlayerItemInfo;
    private ImageView mMinimizeIcon;
    private ImageView mPlayingOnIcon;
    private StyleSheet mStyleSheet;
    private TextView mSubtitle;
    private View mSubtitlePlaceholder;
    private TextView mTitle;
    private View mTitlePlaceholder;

    public MiniPlayerContentView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        this.mIsLoading = true;
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View.inflate(context, R.layout.mini_player_content_view, this);
        this.mTitle = (TextView) findViewById(R.id.MiniPlayerTitle);
        this.mSubtitle = (TextView) findViewById(R.id.MiniPlayerSubtitle);
        this.mDeviceName = (TextView) findViewById(R.id.DeviceNameTextView);
        this.mPlayingOnIcon = (ImageView) findViewById(R.id.miniPlayerPlayingOnIcon);
        this.mTitlePlaceholder = findViewById(R.id.TitlePlaceHolder);
        this.mSubtitlePlaceholder = findViewById(R.id.SubtitlePlaceholder);
        this.mArtwork = (BaseImage) findViewById(R.id.MiniPlayerArtwork);
        this.mArtworkOverlay = (ImageView) findViewById(R.id.miniPlayerArtworkOverlay);
        this.mMinimizeIcon = (ImageView) findViewById(R.id.miniPlayerMinimize);
        this.mMiniPlayerArtworkContainer = (FrameLayout) findViewById(R.id.MiniPlayerArtworkContainer);
        this.mMiniPlayerItemInfo = (LinearLayout) findViewById(R.id.MiniPlayerItemInfo);
        StyleSheetProvider.getStyleSheet().observe((FragmentActivity) context, new Observer() { // from class: com.amazon.mp3.view.MiniPlayerContentView$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$init$0((StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0(StyleSheet styleSheet) {
        this.mStyleSheet = styleSheet;
        initStyle();
    }

    private void initStyle() {
        FontUtil fontUtil = FontUtil.INSTANCE;
        fontUtil.applyFontStyle(this.mTitle, this.mStyleSheet.getFontStyle(FontStyleKey.PRIMARY));
        fontUtil.applyFontStyle(this.mSubtitle, this.mStyleSheet.getFontStyle(FontStyleKey.SECONDARY));
        fontUtil.applyFontStyle(this.mDeviceName, this.mStyleSheet.getFontStyle(FontStyleKey.LABEL));
        if (isStage() && isLyricsSharingEnabled() && (this.mContext instanceof NowPlayingFragmentActivity)) {
            FontSize fontSize = this.mStyleSheet.getFontSize(FontSizeKey.BODY_1);
            if (fontSize != null) {
                FontSize fontSizeCopy = fontSize.copy(getResources().getDimensionPixelSize(R.dimen.spacer_15), Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.spacer_28)));
                fontUtil.applyFontSize(this.mTitle, fontSizeCopy);
                fontUtil.applyFontSize(this.mSubtitle, fontSizeCopy);
            }
            TextView textView = this.mTitle;
            textView.setTypeface(textView.getTypeface(), 1);
            TextView textView2 = this.mSubtitle;
            textView2.setTypeface(textView2.getTypeface(), 1);
            this.mMiniPlayerArtworkContainer.setVisibility(8);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.spacer_12), 0, 0, 0);
            this.mMiniPlayerItemInfo.setLayoutParams(layoutParams);
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setTitle(final String title) {
        TextView textView = this.mTitle;
        if (textView != null) {
            textView.setText(StringUtil.removeExplicitLabelFromAlbumOrTrackTitle(title));
            this.mTitle.setContentDescription(title);
            if (AmazonApplication.getCapabilities().isAMConnectUXUpdateEnabled()) {
                this.mTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                this.mTitle.setFocusable(true);
                this.mTitle.setSingleLine(true);
                this.mTitle.setMarqueeRepeatLimit(-1);
                this.mTitle.setSelected(true);
            }
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setSubtitle(final String subtitle) {
        if (this.mSubtitle != null) {
            if (ChildUserUtil.INSTANCE.isChildUser(getContext())) {
                this.mSubtitle.setText(StringUtil.removeExplicitLabelFromAlbumOrTrackTitle(subtitle));
            } else {
                this.mSubtitle.setText(subtitle);
            }
            this.mSubtitle.setContentDescription(subtitle);
        }
    }

    public void setDeviceName(final String deviceName) {
        if (this.mDeviceName != null) {
            if (TextUtils.isEmpty(deviceName)) {
                this.mDeviceName.setVisibility(8);
                return;
            }
            this.mDeviceName.setText(deviceName);
            this.mDeviceName.setContentDescription(deviceName);
            this.mDeviceName.setVisibility(0);
        }
    }

    public void setArtworkDrawable(final Drawable drawable) {
        if (this.mArtwork != null) {
            setArtworkAttributes();
            this.mArtwork.setImage(drawable);
        }
    }

    public void setArtworkBitmap(final Bitmap bitmap) {
        if (this.mArtwork != null) {
            setArtworkAttributes();
            this.mArtwork.setImage(BitmapUtil.convertBitmapToDrawable(getResources(), bitmap));
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setDefaultArtwork() {
        if (this.mArtwork != null) {
            setArtworkAttributes();
            this.mArtwork.setImage(this.mContext.getDrawable(R.drawable.placeholder));
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setLoading(boolean isLoading) {
        this.mIsLoading = isLoading;
        if (isLoading) {
            this.mTitle.setVisibility(8);
            this.mSubtitle.setVisibility(8);
            this.mTitlePlaceholder.setVisibility(0);
            this.mSubtitlePlaceholder.setVisibility(0);
            return;
        }
        this.mTitlePlaceholder.setVisibility(8);
        this.mSubtitlePlaceholder.setVisibility(8);
        this.mTitle.setVisibility(0);
        this.mSubtitle.setVisibility(0);
    }

    private void setArtworkAttributes() {
        float fraction;
        StyleSheet styleSheet;
        MediaItem currentMediaItem = PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentMediaItem();
        if (getContext() == null) {
            return;
        }
        BaseImage baseImage = this.mArtwork;
        if (baseImage != null && (styleSheet = this.mStyleSheet) != null) {
            baseImage.applyCornerRadius(styleSheet.getCornerSize(CornerSizeKey.SMALL).intValue());
        }
        ViewGroup.LayoutParams layoutParams = this.mArtwork.getLayoutParams();
        if (MediaItemExtensions.isVideo(currentMediaItem)) {
            fraction = getResources().getFraction(R.fraction.aspect_ratio_16_9, 1, 1);
            this.mArtwork.getImage().setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            fraction = getResources().getFraction(R.fraction.aspect_ratio_1_1, 1, 1);
            this.mArtwork.getImage().setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        this.mArtwork.setScalingFactor(fraction);
        layoutParams.width = (int) (layoutParams.height * fraction);
        this.mArtwork.setLayoutParams(layoutParams);
        this.mArtworkOverlay.setLayoutParams(layoutParams);
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setMinimizeViewVisibility(int visibility) {
        this.mArtworkOverlay.setVisibility(visibility);
        this.mMinimizeIcon.setVisibility(visibility);
    }

    public boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }

    public boolean isLyricsSharingEnabled() {
        return AmazonApplication.getCapabilities().isLyricsSharingEnabled();
    }
}
