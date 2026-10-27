package androidx.mediarouter.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.util.ObjectsCompat;
import androidx.mediarouter.R;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MediaRouteDynamicControllerDialog extends AppCompatDialog {
    private static final int BLUR_RADIUS = 10;
    private static final int COLOR_WHITE_ON_DARK_BACKGROUND = -1;
    private static final int CONNECTION_TIMEOUT_MS = 30000;
    private static final int MIN_UNMUTED_VOLUME = 1;
    private static final int MSG_UPDATE_ROUTES_VIEW = 1;
    private static final int MSG_UPDATE_ROUTE_VOLUME_BY_USER = 2;
    private static final int MUTED_VOLUME = 0;
    private static final int UPDATE_ROUTES_VIEW_DELAY_MS = 300;
    private static final int UPDATE_VOLUME_DELAY_MS = 500;
    RecyclerAdapter mAdapter;
    int mArtIconBackgroundColor;
    Bitmap mArtIconBitmap;
    boolean mArtIconIsLoaded;
    Bitmap mArtIconLoadedBitmap;
    Uri mArtIconUri;
    ImageView mArtView;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private ImageButton mCloseButton;
    Context mContext;
    MediaControllerCallback mControllerCallback;
    private boolean mCreated;
    MediaDescriptionCompat mDescription;
    FetchArtTask mFetchArtTask;
    final List<MediaRouter.RouteInfo> mGroupableRoutes;
    final Handler mHandler;
    boolean mIsAnimatingVolumeSliderLayout;
    boolean mIsSelectingRoute;
    private long mLastUpdateTime;
    MediaControllerCompat mMediaController;
    final List<MediaRouter.RouteInfo> mMemberRoutes;
    private ImageView mMetadataBackground;
    private View mMetadataBlackScrim;
    RecyclerView mRecyclerView;
    MediaRouter.RouteInfo mRouteForVolumeUpdatingByUser;
    final MediaRouter mRouter;
    MediaRouter.RouteInfo mSelectedRoute;
    private MediaRouteSelector mSelector;
    private Button mStopCastingButton;
    private TextView mSubtitleView;
    private String mTitlePlaceholder;
    private TextView mTitleView;
    final List<MediaRouter.RouteInfo> mTransferableRoutes;
    final List<MediaRouter.RouteInfo> mUngroupableRoutes;
    Map<String, Integer> mUnmutedVolumeMap;
    private boolean mUpdateMetadataViewsDeferred;
    private boolean mUpdateRoutesViewDeferred;
    VolumeChangeListener mVolumeChangeListener;
    Map<String, MediaRouteVolumeSliderHolder> mVolumeSliderHolderMap;
    private static final String TAG = "MediaRouteCtrlDialog";
    static final boolean DEBUG = Log.isLoggable(TAG, 3);

    public MediaRouteDynamicControllerDialog(Context context) {
        this(context, 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MediaRouteDynamicControllerDialog(Context context, int theme) {
        Context context2 = MediaRouterThemeHelper.createThemedDialogContext(context, theme, false);
        super(context2, MediaRouterThemeHelper.createThemedDialogStyle(context2));
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mMemberRoutes = new ArrayList();
        this.mGroupableRoutes = new ArrayList();
        this.mTransferableRoutes = new ArrayList();
        this.mUngroupableRoutes = new ArrayList();
        this.mHandler = new Handler() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    MediaRouteDynamicControllerDialog.this.updateRoutesView();
                } else if (i == 2 && MediaRouteDynamicControllerDialog.this.mRouteForVolumeUpdatingByUser != null) {
                    MediaRouteDynamicControllerDialog.this.mRouteForVolumeUpdatingByUser = null;
                    MediaRouteDynamicControllerDialog.this.updateViewsIfNeeded();
                }
            }
        };
        Context context3 = getContext();
        this.mContext = context3;
        this.mRouter = MediaRouter.getInstance(context3);
        this.mCallback = new MediaRouterCallback();
        this.mSelectedRoute = this.mRouter.getSelectedRoute();
        this.mControllerCallback = new MediaControllerCallback();
        setMediaSession(this.mRouter.getMediaSessionToken());
    }

    private void setMediaSession(MediaSessionCompat.Token sessionToken) {
        MediaControllerCompat mediaControllerCompat = this.mMediaController;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.unregisterCallback(this.mControllerCallback);
            this.mMediaController = null;
        }
        if (sessionToken == null || !this.mAttachedToWindow) {
            return;
        }
        MediaControllerCompat mediaControllerCompat2 = new MediaControllerCompat(this.mContext, sessionToken);
        this.mMediaController = mediaControllerCompat2;
        mediaControllerCompat2.registerCallback(this.mControllerCallback);
        MediaMetadataCompat metadata = this.mMediaController.getMetadata();
        this.mDescription = metadata != null ? metadata.getDescription() : null;
        reloadIconIfNeeded();
        updateMetadataViews();
    }

    public MediaSessionCompat.Token getMediaSession() {
        MediaControllerCompat mediaControllerCompat = this.mMediaController;
        if (mediaControllerCompat == null) {
            return null;
        }
        return mediaControllerCompat.getSessionToken();
    }

    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }

    public void setRouteSelector(MediaRouteSelector selector) {
        if (selector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (!this.mSelector.equals(selector)) {
            this.mSelector = selector;
            if (this.mAttachedToWindow) {
                this.mRouter.removeCallback(this.mCallback);
                this.mRouter.addCallback(selector, this.mCallback, 1);
                updateRoutes();
            }
        }
    }

    public void onFilterRoutes(List<MediaRouter.RouteInfo> routes) {
        for (int i = routes.size() - 1; i >= 0; i--) {
            if (!onFilterRoute(routes.get(i))) {
                routes.remove(i);
            }
        }
    }

    public boolean onFilterRoute(MediaRouter.RouteInfo route) {
        return !route.isDefaultOrBluetooth() && route.isEnabled() && route.matchesSelector(this.mSelector) && this.mSelectedRoute != route;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mr_cast_dialog);
        MediaRouterThemeHelper.setDialogBackgroundColor(this.mContext, this);
        ImageButton imageButton = (ImageButton) findViewById(R.id.mr_cast_close_button);
        this.mCloseButton = imageButton;
        imageButton.setColorFilter(-1);
        this.mCloseButton.setOnClickListener(new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MediaRouteDynamicControllerDialog.this.dismiss();
            }
        });
        Button button = (Button) findViewById(R.id.mr_cast_stop_button);
        this.mStopCastingButton = button;
        button.setTextColor(-1);
        this.mStopCastingButton.setOnClickListener(new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MediaRouteDynamicControllerDialog.this.mSelectedRoute.isSelected()) {
                    MediaRouteDynamicControllerDialog.this.mRouter.unselect(2);
                }
                MediaRouteDynamicControllerDialog.this.dismiss();
            }
        });
        this.mAdapter = new RecyclerAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mr_cast_list);
        this.mRecyclerView = recyclerView;
        recyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mVolumeChangeListener = new VolumeChangeListener();
        this.mVolumeSliderHolderMap = new HashMap();
        this.mUnmutedVolumeMap = new HashMap();
        this.mMetadataBackground = (ImageView) findViewById(R.id.mr_cast_meta_background);
        this.mMetadataBlackScrim = findViewById(R.id.mr_cast_meta_black_scrim);
        this.mArtView = (ImageView) findViewById(R.id.mr_cast_meta_art);
        TextView textView = (TextView) findViewById(R.id.mr_cast_meta_title);
        this.mTitleView = textView;
        textView.setTextColor(-1);
        TextView textView2 = (TextView) findViewById(R.id.mr_cast_meta_subtitle);
        this.mSubtitleView = textView2;
        textView2.setTextColor(-1);
        Resources res = this.mContext.getResources();
        this.mTitlePlaceholder = res.getString(R.string.mr_cast_dialog_title_view_placeholder);
        this.mCreated = true;
        updateLayout();
    }

    void updateLayout() {
        int width = MediaRouteDialogHelper.getDialogWidthForDynamicGroup(this.mContext);
        int height = MediaRouteDialogHelper.getDialogHeight(this.mContext);
        getWindow().setLayout(width, height);
        this.mArtIconBitmap = null;
        this.mArtIconUri = null;
        reloadIconIfNeeded();
        updateMetadataViews();
        updateRoutesView();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.addCallback(this.mSelector, this.mCallback, 1);
        updateRoutes();
        setMediaSession(this.mRouter.getMediaSessionToken());
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
        this.mRouter.removeCallback(this.mCallback);
        this.mHandler.removeCallbacksAndMessages(null);
        setMediaSession(null);
    }

    static boolean isBitmapRecycled(Bitmap bitmap) {
        return bitmap != null && bitmap.isRecycled();
    }

    void reloadIconIfNeeded() {
        MediaDescriptionCompat mediaDescriptionCompat = this.mDescription;
        Bitmap newBitmap = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.getIconBitmap();
        MediaDescriptionCompat mediaDescriptionCompat2 = this.mDescription;
        Uri newUri = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.getIconUri() : null;
        FetchArtTask fetchArtTask = this.mFetchArtTask;
        Bitmap oldBitmap = fetchArtTask == null ? this.mArtIconBitmap : fetchArtTask.getIconBitmap();
        FetchArtTask fetchArtTask2 = this.mFetchArtTask;
        Uri oldUri = fetchArtTask2 == null ? this.mArtIconUri : fetchArtTask2.getIconUri();
        if (oldBitmap == newBitmap && (oldBitmap != null || ObjectsCompat.equals(oldUri, newUri))) {
            return;
        }
        FetchArtTask fetchArtTask3 = this.mFetchArtTask;
        if (fetchArtTask3 != null) {
            fetchArtTask3.cancel(true);
        }
        FetchArtTask fetchArtTask4 = new FetchArtTask();
        this.mFetchArtTask = fetchArtTask4;
        fetchArtTask4.execute(new Void[0]);
    }

    void clearLoadedBitmap() {
        this.mArtIconIsLoaded = false;
        this.mArtIconLoadedBitmap = null;
        this.mArtIconBackgroundColor = 0;
    }

    private boolean shouldDeferUpdateViews() {
        if (this.mRouteForVolumeUpdatingByUser != null || this.mIsSelectingRoute || this.mIsAnimatingVolumeSliderLayout) {
            return true;
        }
        return !this.mCreated;
    }

    void updateViewsIfNeeded() {
        if (this.mUpdateRoutesViewDeferred) {
            updateRoutesView();
        }
        if (this.mUpdateMetadataViewsDeferred) {
            updateMetadataViews();
        }
    }

    void updateMetadataViews() {
        if (shouldDeferUpdateViews()) {
            this.mUpdateMetadataViewsDeferred = true;
            return;
        }
        this.mUpdateMetadataViewsDeferred = false;
        if (!this.mSelectedRoute.isSelected() || this.mSelectedRoute.isDefaultOrBluetooth()) {
            dismiss();
        }
        if (this.mArtIconIsLoaded && !isBitmapRecycled(this.mArtIconLoadedBitmap) && this.mArtIconLoadedBitmap != null) {
            this.mArtView.setVisibility(0);
            this.mArtView.setImageBitmap(this.mArtIconLoadedBitmap);
            this.mArtView.setBackgroundColor(this.mArtIconBackgroundColor);
            this.mMetadataBlackScrim.setVisibility(0);
            if (Build.VERSION.SDK_INT >= 17) {
                Bitmap blurredBitmap = blurBitmap(this.mArtIconLoadedBitmap, 10.0f, this.mContext);
                this.mMetadataBackground.setImageBitmap(blurredBitmap);
            } else {
                this.mMetadataBackground.setImageBitmap(Bitmap.createBitmap(this.mArtIconLoadedBitmap));
            }
        } else {
            if (isBitmapRecycled(this.mArtIconLoadedBitmap)) {
                Log.w(TAG, "Can't set artwork image with recycled bitmap: " + this.mArtIconLoadedBitmap);
            }
            this.mArtView.setVisibility(8);
            this.mMetadataBlackScrim.setVisibility(8);
            this.mMetadataBackground.setImageBitmap(null);
        }
        clearLoadedBitmap();
        MediaDescriptionCompat mediaDescriptionCompat = this.mDescription;
        CharSequence title = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.getTitle();
        boolean hasTitle = !TextUtils.isEmpty(title);
        MediaDescriptionCompat mediaDescriptionCompat2 = this.mDescription;
        CharSequence subtitle = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.getSubtitle() : null;
        boolean hasSubtitle = true ^ TextUtils.isEmpty(subtitle);
        if (hasTitle) {
            this.mTitleView.setText(title);
        } else {
            this.mTitleView.setText(this.mTitlePlaceholder);
        }
        if (hasSubtitle) {
            this.mSubtitleView.setText(subtitle);
            this.mSubtitleView.setVisibility(0);
        } else {
            this.mSubtitleView.setVisibility(8);
        }
    }

    static void setLayoutHeight(View view, int height) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = height;
        view.setLayoutParams(lp);
    }

    private class VolumeChangeListener implements SeekBar.OnSeekBarChangeListener {
        VolumeChangeListener() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (MediaRouteDynamicControllerDialog.this.mRouteForVolumeUpdatingByUser != null) {
                MediaRouteDynamicControllerDialog.this.mHandler.removeMessages(2);
            }
            MediaRouteDynamicControllerDialog.this.mRouteForVolumeUpdatingByUser = (MediaRouter.RouteInfo) seekBar.getTag();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            MediaRouteDynamicControllerDialog.this.mHandler.sendEmptyMessageDelayed(2, 500L);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                MediaRouter.RouteInfo route = (MediaRouter.RouteInfo) seekBar.getTag();
                MediaRouteVolumeSliderHolder holder = MediaRouteDynamicControllerDialog.this.mVolumeSliderHolderMap.get(route.getId());
                if (holder != null) {
                    holder.setMute(progress == 0);
                }
                route.requestSetVolume(progress);
            }
        }
    }

    List<MediaRouter.RouteInfo> getCurrentGroupableRoutes() {
        List<MediaRouter.RouteInfo> groupableRoutes = new ArrayList<>();
        for (MediaRouter.RouteInfo route : this.mSelectedRoute.getProvider().getRoutes()) {
            MediaRouter.RouteInfo.DynamicGroupState state = route.getDynamicGroupState();
            if (state != null && state.isGroupable()) {
                groupableRoutes.add(route);
            }
        }
        return groupableRoutes;
    }

    void updateRoutesView() {
        if (this.mAttachedToWindow) {
            if (SystemClock.uptimeMillis() - this.mLastUpdateTime >= 300) {
                if (shouldDeferUpdateViews()) {
                    this.mUpdateRoutesViewDeferred = true;
                    return;
                }
                this.mUpdateRoutesViewDeferred = false;
                if (!this.mSelectedRoute.isSelected() || this.mSelectedRoute.isDefaultOrBluetooth()) {
                    dismiss();
                }
                this.mLastUpdateTime = SystemClock.uptimeMillis();
                this.mAdapter.notifyAdapterDataSetChanged();
                return;
            }
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessageAtTime(1, this.mLastUpdateTime + 300);
        }
    }

    void updateRoutes() {
        this.mMemberRoutes.clear();
        this.mGroupableRoutes.clear();
        this.mTransferableRoutes.clear();
        this.mMemberRoutes.addAll(this.mSelectedRoute.getMemberRoutes());
        for (MediaRouter.RouteInfo route : this.mSelectedRoute.getProvider().getRoutes()) {
            MediaRouter.RouteInfo.DynamicGroupState state = route.getDynamicGroupState();
            if (state != null) {
                if (state.isGroupable()) {
                    this.mGroupableRoutes.add(route);
                }
                if (state.isTransferable()) {
                    this.mTransferableRoutes.add(route);
                }
            }
        }
        onFilterRoutes(this.mGroupableRoutes);
        onFilterRoutes(this.mTransferableRoutes);
        Collections.sort(this.mMemberRoutes, RouteComparator.sInstance);
        Collections.sort(this.mGroupableRoutes, RouteComparator.sInstance);
        Collections.sort(this.mTransferableRoutes, RouteComparator.sInstance);
        this.mAdapter.updateItems();
    }

    private static Bitmap blurBitmap(Bitmap bitmap, float radius, Context context) {
        RenderScript rs = RenderScript.create(context);
        Allocation allocation = Allocation.createFromBitmap(rs, bitmap);
        Allocation blurAllocation = Allocation.createTyped(rs, allocation.getType());
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        blurScript.setRadius(radius);
        blurScript.setInput(allocation);
        blurScript.forEach(blurAllocation);
        Bitmap mutableBitmap = bitmap.copy(bitmap.getConfig(), true);
        blurAllocation.copyTo(mutableBitmap);
        allocation.destroy();
        blurAllocation.destroy();
        blurScript.destroy();
        rs.destroy();
        return mutableBitmap;
    }

    private abstract class MediaRouteVolumeSliderHolder extends RecyclerView.ViewHolder {
        final ImageButton mMuteButton;
        MediaRouter.RouteInfo mRoute;
        final MediaRouteVolumeSlider mVolumeSlider;

        MediaRouteVolumeSliderHolder(View itemView, ImageButton muteButton, MediaRouteVolumeSlider volumeSlider) {
            super(itemView);
            this.mMuteButton = muteButton;
            this.mVolumeSlider = volumeSlider;
            Drawable muteButtonIcon = MediaRouterThemeHelper.getMuteButtonDrawableIcon(MediaRouteDynamicControllerDialog.this.mContext);
            this.mMuteButton.setImageDrawable(muteButtonIcon);
            MediaRouterThemeHelper.setVolumeSliderColor(MediaRouteDynamicControllerDialog.this.mContext, this.mVolumeSlider);
        }

        void bindRouteVolumeSliderHolder(MediaRouter.RouteInfo route) {
            this.mRoute = route;
            int volume = route.getVolume();
            boolean isMuted = volume == 0;
            this.mMuteButton.setActivated(isMuted);
            this.mMuteButton.setOnClickListener(new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.MediaRouteVolumeSliderHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (MediaRouteDynamicControllerDialog.this.mRouteForVolumeUpdatingByUser != null) {
                        MediaRouteDynamicControllerDialog.this.mHandler.removeMessages(2);
                    }
                    MediaRouteDynamicControllerDialog.this.mRouteForVolumeUpdatingByUser = MediaRouteVolumeSliderHolder.this.mRoute;
                    boolean mute = !v.isActivated();
                    int volume2 = mute ? 0 : MediaRouteVolumeSliderHolder.this.getUnmutedVolume();
                    MediaRouteVolumeSliderHolder.this.setMute(mute);
                    MediaRouteVolumeSliderHolder.this.mVolumeSlider.setProgress(volume2);
                    MediaRouteVolumeSliderHolder.this.mRoute.requestSetVolume(volume2);
                    MediaRouteDynamicControllerDialog.this.mHandler.sendEmptyMessageDelayed(2, 500L);
                }
            });
            this.mVolumeSlider.setTag(this.mRoute);
            this.mVolumeSlider.setMax(route.getVolumeMax());
            this.mVolumeSlider.setProgress(volume);
            this.mVolumeSlider.setOnSeekBarChangeListener(MediaRouteDynamicControllerDialog.this.mVolumeChangeListener);
        }

        void updateVolume() {
            int volume = this.mRoute.getVolume();
            setMute(volume == 0);
            this.mVolumeSlider.setProgress(volume);
        }

        void setMute(boolean mute) {
            boolean wasMuted = this.mMuteButton.isActivated();
            if (wasMuted == mute) {
                return;
            }
            this.mMuteButton.setActivated(mute);
            if (mute) {
                MediaRouteDynamicControllerDialog.this.mUnmutedVolumeMap.put(this.mRoute.getId(), Integer.valueOf(this.mVolumeSlider.getProgress()));
            } else {
                MediaRouteDynamicControllerDialog.this.mUnmutedVolumeMap.remove(this.mRoute.getId());
            }
        }

        int getUnmutedVolume() {
            Integer beforeMuteVolume = MediaRouteDynamicControllerDialog.this.mUnmutedVolumeMap.get(this.mRoute.getId());
            if (beforeMuteVolume == null) {
                return 1;
            }
            return Math.max(1, beforeMuteVolume.intValue());
        }
    }

    private final class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int ITEM_TYPE_GROUP = 4;
        private static final int ITEM_TYPE_GROUP_VOLUME = 1;
        private static final int ITEM_TYPE_HEADER = 2;
        private static final int ITEM_TYPE_ROUTE = 3;
        private final Interpolator mAccelerateDecelerateInterpolator;
        private final Drawable mDefaultIcon;
        private Item mGroupVolumeItem;
        private final LayoutInflater mInflater;
        private final ArrayList<Item> mItems = new ArrayList<>();
        private final int mLayoutAnimationDurationMs;
        private final Drawable mSpeakerGroupIcon;
        private final Drawable mSpeakerIcon;
        private final Drawable mTvIcon;

        RecyclerAdapter() {
            this.mInflater = LayoutInflater.from(MediaRouteDynamicControllerDialog.this.mContext);
            this.mDefaultIcon = MediaRouterThemeHelper.getDefaultDrawableIcon(MediaRouteDynamicControllerDialog.this.mContext);
            this.mTvIcon = MediaRouterThemeHelper.getTvDrawableIcon(MediaRouteDynamicControllerDialog.this.mContext);
            this.mSpeakerIcon = MediaRouterThemeHelper.getSpeakerDrawableIcon(MediaRouteDynamicControllerDialog.this.mContext);
            this.mSpeakerGroupIcon = MediaRouterThemeHelper.getSpeakerGroupDrawableIcon(MediaRouteDynamicControllerDialog.this.mContext);
            Resources res = MediaRouteDynamicControllerDialog.this.mContext.getResources();
            this.mLayoutAnimationDurationMs = res.getInteger(R.integer.mr_cast_volume_slider_layout_animation_duration_ms);
            this.mAccelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
            updateItems();
        }

        boolean isGroupVolumeNeeded() {
            return MediaRouteDynamicControllerDialog.this.mSelectedRoute.getMemberRoutes().size() > 1;
        }

        void animateLayoutHeight(final View view, final int targetHeight) {
            final int startValue = view.getLayoutParams().height;
            Animation anim = new Animation() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.RecyclerAdapter.1
                @Override // android.view.animation.Animation
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    int i = targetHeight;
                    int deltaHeight = (int) ((i - r1) * interpolatedTime);
                    MediaRouteDynamicControllerDialog.setLayoutHeight(view, startValue + deltaHeight);
                }
            };
            anim.setAnimationListener(new Animation.AnimationListener() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.RecyclerAdapter.2
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    MediaRouteDynamicControllerDialog.this.mIsAnimatingVolumeSliderLayout = true;
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    MediaRouteDynamicControllerDialog.this.mIsAnimatingVolumeSliderLayout = false;
                    MediaRouteDynamicControllerDialog.this.updateViewsIfNeeded();
                }
            });
            anim.setDuration(this.mLayoutAnimationDurationMs);
            anim.setInterpolator(this.mAccelerateDecelerateInterpolator);
            view.startAnimation(anim);
        }

        void mayUpdateGroupVolume(MediaRouter.RouteInfo route, boolean selected) {
            List<MediaRouter.RouteInfo> members = MediaRouteDynamicControllerDialog.this.mSelectedRoute.getMemberRoutes();
            int memberCount = Math.max(1, members.size());
            if (route.isGroup()) {
                for (MediaRouter.RouteInfo changedRoute : route.getMemberRoutes()) {
                    if (members.contains(changedRoute) != selected) {
                        memberCount += selected ? 1 : -1;
                    }
                }
            } else {
                memberCount += selected ? 1 : -1;
            }
            boolean wasShown = isGroupVolumeNeeded();
            boolean shouldShow = memberCount >= 2;
            if (wasShown != shouldShow) {
                RecyclerView.ViewHolder viewHolder = MediaRouteDynamicControllerDialog.this.mRecyclerView.findViewHolderForAdapterPosition(0);
                if (viewHolder instanceof GroupVolumeViewHolder) {
                    GroupVolumeViewHolder groupVolumeHolder = (GroupVolumeViewHolder) viewHolder;
                    animateLayoutHeight(groupVolumeHolder.itemView, shouldShow ? groupVolumeHolder.getExpandedHeight() : 0);
                }
            }
        }

        void updateItems() {
            String title;
            this.mItems.clear();
            this.mGroupVolumeItem = new Item(MediaRouteDynamicControllerDialog.this.mSelectedRoute, 1);
            if (!MediaRouteDynamicControllerDialog.this.mMemberRoutes.isEmpty()) {
                for (MediaRouter.RouteInfo memberRoute : MediaRouteDynamicControllerDialog.this.mMemberRoutes) {
                    this.mItems.add(new Item(memberRoute, 3));
                }
            } else {
                this.mItems.add(new Item(MediaRouteDynamicControllerDialog.this.mSelectedRoute, 3));
            }
            if (!MediaRouteDynamicControllerDialog.this.mGroupableRoutes.isEmpty()) {
                boolean headerAdded = false;
                for (MediaRouter.RouteInfo groupableRoute : MediaRouteDynamicControllerDialog.this.mGroupableRoutes) {
                    if (!MediaRouteDynamicControllerDialog.this.mMemberRoutes.contains(groupableRoute)) {
                        if (!headerAdded) {
                            MediaRouteProvider.DynamicGroupRouteController controller = MediaRouteDynamicControllerDialog.this.mSelectedRoute.getDynamicGroupController();
                            String title2 = controller != null ? controller.getGroupableSelectionTitle() : null;
                            if (TextUtils.isEmpty(title2)) {
                                title2 = MediaRouteDynamicControllerDialog.this.mContext.getString(R.string.mr_dialog_groupable_header);
                            }
                            this.mItems.add(new Item(title2, 2));
                            headerAdded = true;
                        }
                        this.mItems.add(new Item(groupableRoute, 3));
                    }
                }
            }
            if (!MediaRouteDynamicControllerDialog.this.mTransferableRoutes.isEmpty()) {
                boolean headerAdded2 = false;
                for (MediaRouter.RouteInfo transferableRoute : MediaRouteDynamicControllerDialog.this.mTransferableRoutes) {
                    if (MediaRouteDynamicControllerDialog.this.mSelectedRoute != transferableRoute) {
                        if (!headerAdded2) {
                            headerAdded2 = true;
                            MediaRouteProvider.DynamicGroupRouteController controller2 = MediaRouteDynamicControllerDialog.this.mSelectedRoute.getDynamicGroupController();
                            if (controller2 != null) {
                                title = controller2.getTransferableSectionTitle();
                            } else {
                                title = null;
                            }
                            if (TextUtils.isEmpty(title)) {
                                title = MediaRouteDynamicControllerDialog.this.mContext.getString(R.string.mr_dialog_transferable_header);
                            }
                            this.mItems.add(new Item(title, 2));
                        }
                        this.mItems.add(new Item(transferableRoute, 4));
                    }
                }
            }
            notifyAdapterDataSetChanged();
        }

        void notifyAdapterDataSetChanged() {
            MediaRouteDynamicControllerDialog.this.mUngroupableRoutes.clear();
            MediaRouteDynamicControllerDialog.this.mUngroupableRoutes.addAll(MediaRouteDialogHelper.getItemsRemoved(MediaRouteDynamicControllerDialog.this.mGroupableRoutes, MediaRouteDynamicControllerDialog.this.getCurrentGroupableRoutes()));
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 1) {
                View view = this.mInflater.inflate(R.layout.mr_cast_group_volume_item, parent, false);
                return new GroupVolumeViewHolder(view);
            }
            if (viewType == 2) {
                View view2 = this.mInflater.inflate(R.layout.mr_cast_header_item, parent, false);
                return new HeaderViewHolder(view2);
            }
            if (viewType == 3) {
                View view3 = this.mInflater.inflate(R.layout.mr_cast_route_item, parent, false);
                return new RouteViewHolder(view3);
            }
            if (viewType == 4) {
                View view4 = this.mInflater.inflate(R.layout.mr_cast_group_item, parent, false);
                return new GroupViewHolder(view4);
            }
            Log.w(MediaRouteDynamicControllerDialog.TAG, "Cannot create ViewHolder because of wrong view type");
            return null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            int viewType = getItemViewType(position);
            Item item = getItem(position);
            if (viewType == 1) {
                MediaRouter.RouteInfo route = (MediaRouter.RouteInfo) item.getData();
                MediaRouteDynamicControllerDialog.this.mVolumeSliderHolderMap.put(route.getId(), (MediaRouteVolumeSliderHolder) holder);
                ((GroupVolumeViewHolder) holder).bindGroupVolumeViewHolder(item);
            } else {
                if (viewType == 2) {
                    ((HeaderViewHolder) holder).bindHeaderViewHolder(item);
                    return;
                }
                if (viewType == 3) {
                    MediaRouter.RouteInfo route2 = (MediaRouter.RouteInfo) item.getData();
                    MediaRouteDynamicControllerDialog.this.mVolumeSliderHolderMap.put(route2.getId(), (MediaRouteVolumeSliderHolder) holder);
                    ((RouteViewHolder) holder).bindRouteViewHolder(item);
                } else if (viewType == 4) {
                    ((GroupViewHolder) holder).bindGroupViewHolder(item);
                } else {
                    Log.w(MediaRouteDynamicControllerDialog.TAG, "Cannot bind item to ViewHolder because of wrong view type");
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder holder) {
            super.onViewRecycled(holder);
            MediaRouteDynamicControllerDialog.this.mVolumeSliderHolderMap.values().remove(holder);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mItems.size() + 1;
        }

        Drawable getIconDrawable(MediaRouter.RouteInfo route) {
            Uri iconUri = route.getIconUri();
            if (iconUri != null) {
                try {
                    InputStream is = MediaRouteDynamicControllerDialog.this.mContext.getContentResolver().openInputStream(iconUri);
                    Drawable drawable = Drawable.createFromStream(is, null);
                    if (drawable != null) {
                        return drawable;
                    }
                } catch (IOException e) {
                    Log.w(MediaRouteDynamicControllerDialog.TAG, "Failed to load " + iconUri, e);
                }
            }
            return getDefaultIconDrawable(route);
        }

        private Drawable getDefaultIconDrawable(MediaRouter.RouteInfo route) {
            int deviceType = route.getDeviceType();
            if (deviceType == 1) {
                return this.mTvIcon;
            }
            if (deviceType == 2) {
                return this.mSpeakerIcon;
            }
            if (route.isGroup()) {
                return this.mSpeakerGroupIcon;
            }
            return this.mDefaultIcon;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int position) {
            return getItem(position).getType();
        }

        public Item getItem(int position) {
            if (position == 0) {
                return this.mGroupVolumeItem;
            }
            return this.mItems.get(position - 1);
        }

        private class Item {
            private final Object mData;
            private final int mType;

            Item(Object data, int type) {
                this.mData = data;
                this.mType = type;
            }

            public Object getData() {
                return this.mData;
            }

            public int getType() {
                return this.mType;
            }
        }

        private class GroupVolumeViewHolder extends MediaRouteVolumeSliderHolder {
            private final int mExpandedHeight;
            private final TextView mTextView;

            GroupVolumeViewHolder(View itemView) {
                super(itemView, (ImageButton) itemView.findViewById(R.id.mr_cast_mute_button), (MediaRouteVolumeSlider) itemView.findViewById(R.id.mr_cast_volume_slider));
                this.mTextView = (TextView) itemView.findViewById(R.id.mr_group_volume_route_name);
                Resources res = MediaRouteDynamicControllerDialog.this.mContext.getResources();
                DisplayMetrics metrics = res.getDisplayMetrics();
                TypedValue value = new TypedValue();
                res.getValue(R.dimen.mr_dynamic_volume_group_list_item_height, value, true);
                this.mExpandedHeight = (int) value.getDimension(metrics);
            }

            void bindGroupVolumeViewHolder(Item item) {
                MediaRouteDynamicControllerDialog.setLayoutHeight(this.itemView, RecyclerAdapter.this.isGroupVolumeNeeded() ? this.mExpandedHeight : 0);
                MediaRouter.RouteInfo route = (MediaRouter.RouteInfo) item.getData();
                super.bindRouteVolumeSliderHolder(route);
                this.mTextView.setText(route.getName());
            }

            int getExpandedHeight() {
                return this.mExpandedHeight;
            }
        }

        private class HeaderViewHolder extends RecyclerView.ViewHolder {
            private final TextView mTextView;

            HeaderViewHolder(View itemView) {
                super(itemView);
                this.mTextView = (TextView) itemView.findViewById(R.id.mr_cast_header_name);
            }

            void bindHeaderViewHolder(Item item) {
                String headerName = item.getData().toString();
                this.mTextView.setText(headerName);
            }
        }

        private class RouteViewHolder extends MediaRouteVolumeSliderHolder {
            final CheckBox mCheckBox;
            final int mCollapsedLayoutHeight;
            final float mDisabledAlpha;
            final int mExpandedLayoutHeight;
            final ImageView mImageView;
            final View mItemView;
            final ProgressBar mProgressBar;
            final TextView mTextView;
            final View.OnClickListener mViewClickListener;
            final RelativeLayout mVolumeSliderLayout;

            RouteViewHolder(View itemView) {
                super(itemView, (ImageButton) itemView.findViewById(R.id.mr_cast_mute_button), (MediaRouteVolumeSlider) itemView.findViewById(R.id.mr_cast_volume_slider));
                this.mViewClickListener = new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.RecyclerAdapter.RouteViewHolder.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        RouteViewHolder routeViewHolder = RouteViewHolder.this;
                        boolean selected = !routeViewHolder.isSelected(routeViewHolder.mRoute);
                        boolean isGroup = RouteViewHolder.this.mRoute.isGroup();
                        if (selected) {
                            MediaRouteDynamicControllerDialog.this.mRouter.addMemberToDynamicGroup(RouteViewHolder.this.mRoute);
                        } else {
                            MediaRouteDynamicControllerDialog.this.mRouter.removeMemberFromDynamicGroup(RouteViewHolder.this.mRoute);
                        }
                        RouteViewHolder.this.showSelectingProgress(selected, !isGroup);
                        if (isGroup) {
                            List<MediaRouter.RouteInfo> selectedRoutes = MediaRouteDynamicControllerDialog.this.mSelectedRoute.getMemberRoutes();
                            for (MediaRouter.RouteInfo route : RouteViewHolder.this.mRoute.getMemberRoutes()) {
                                if (selectedRoutes.contains(route) != selected) {
                                    MediaRouteVolumeSliderHolder volumeSliderHolder = MediaRouteDynamicControllerDialog.this.mVolumeSliderHolderMap.get(route.getId());
                                    if (volumeSliderHolder instanceof RouteViewHolder) {
                                        RouteViewHolder routeViewHolder2 = (RouteViewHolder) volumeSliderHolder;
                                        routeViewHolder2.showSelectingProgress(selected, true);
                                    }
                                }
                            }
                        }
                        RecyclerAdapter.this.mayUpdateGroupVolume(RouteViewHolder.this.mRoute, selected);
                    }
                };
                this.mItemView = itemView;
                this.mImageView = (ImageView) itemView.findViewById(R.id.mr_cast_route_icon);
                this.mProgressBar = (ProgressBar) itemView.findViewById(R.id.mr_cast_route_progress_bar);
                this.mTextView = (TextView) itemView.findViewById(R.id.mr_cast_route_name);
                this.mVolumeSliderLayout = (RelativeLayout) itemView.findViewById(R.id.mr_cast_volume_layout);
                this.mCheckBox = (CheckBox) itemView.findViewById(R.id.mr_cast_checkbox);
                Drawable checkBoxIcon = MediaRouterThemeHelper.getCheckBoxDrawableIcon(MediaRouteDynamicControllerDialog.this.mContext);
                this.mCheckBox.setButtonDrawable(checkBoxIcon);
                MediaRouterThemeHelper.setIndeterminateProgressBarColor(MediaRouteDynamicControllerDialog.this.mContext, this.mProgressBar);
                this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(MediaRouteDynamicControllerDialog.this.mContext);
                Resources res = MediaRouteDynamicControllerDialog.this.mContext.getResources();
                DisplayMetrics metrics = res.getDisplayMetrics();
                TypedValue value = new TypedValue();
                res.getValue(R.dimen.mr_dynamic_dialog_row_height, value, true);
                this.mExpandedLayoutHeight = (int) value.getDimension(metrics);
                this.mCollapsedLayoutHeight = 0;
            }

            boolean isSelected(MediaRouter.RouteInfo route) {
                if (route.isSelected()) {
                    return true;
                }
                MediaRouter.RouteInfo.DynamicGroupState state = route.getDynamicGroupState();
                return state != null && state.getSelectionState() == 3;
            }

            private boolean isEnabled(MediaRouter.RouteInfo route) {
                if (MediaRouteDynamicControllerDialog.this.mUngroupableRoutes.contains(route)) {
                    return false;
                }
                if (isSelected(route) && MediaRouteDynamicControllerDialog.this.mSelectedRoute.getMemberRoutes().size() < 2) {
                    return false;
                }
                if (!isSelected(route)) {
                    return true;
                }
                MediaRouter.RouteInfo.DynamicGroupState state = route.getDynamicGroupState();
                return state != null && state.isUnselectable();
            }

            void bindRouteViewHolder(Item item) {
                MediaRouter.RouteInfo route = (MediaRouter.RouteInfo) item.getData();
                if (route == MediaRouteDynamicControllerDialog.this.mSelectedRoute && route.getMemberRoutes().size() > 0) {
                    Iterator<MediaRouter.RouteInfo> it = route.getMemberRoutes().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        MediaRouter.RouteInfo memberRoute = it.next();
                        if (!MediaRouteDynamicControllerDialog.this.mGroupableRoutes.contains(memberRoute)) {
                            route = memberRoute;
                            break;
                        }
                    }
                }
                bindRouteVolumeSliderHolder(route);
                this.mImageView.setImageDrawable(RecyclerAdapter.this.getIconDrawable(route));
                this.mTextView.setText(route.getName());
                this.mCheckBox.setVisibility(0);
                boolean selected = isSelected(route);
                boolean enabled = isEnabled(route);
                this.mCheckBox.setChecked(selected);
                this.mProgressBar.setVisibility(4);
                this.mImageView.setVisibility(0);
                this.mItemView.setEnabled(enabled);
                this.mCheckBox.setEnabled(enabled);
                this.mMuteButton.setEnabled(enabled || selected);
                this.mVolumeSlider.setEnabled(enabled || selected);
                this.mItemView.setOnClickListener(this.mViewClickListener);
                this.mCheckBox.setOnClickListener(this.mViewClickListener);
                MediaRouteDynamicControllerDialog.setLayoutHeight(this.mVolumeSliderLayout, (!selected || this.mRoute.isGroup()) ? this.mCollapsedLayoutHeight : this.mExpandedLayoutHeight);
                float f = 1.0f;
                this.mItemView.setAlpha((enabled || selected) ? 1.0f : this.mDisabledAlpha);
                CheckBox checkBox = this.mCheckBox;
                if (!enabled && selected) {
                    f = this.mDisabledAlpha;
                }
                checkBox.setAlpha(f);
            }

            void showSelectingProgress(boolean selected, boolean shouldChangeHeight) {
                this.mCheckBox.setEnabled(false);
                this.mItemView.setEnabled(false);
                this.mCheckBox.setChecked(selected);
                if (selected) {
                    this.mImageView.setVisibility(4);
                    this.mProgressBar.setVisibility(0);
                }
                if (shouldChangeHeight) {
                    RecyclerAdapter.this.animateLayoutHeight(this.mVolumeSliderLayout, selected ? this.mExpandedLayoutHeight : this.mCollapsedLayoutHeight);
                }
            }
        }

        private class GroupViewHolder extends RecyclerView.ViewHolder {
            final float mDisabledAlpha;
            final ImageView mImageView;
            final View mItemView;
            final ProgressBar mProgressBar;
            MediaRouter.RouteInfo mRoute;
            final TextView mTextView;

            GroupViewHolder(View itemView) {
                super(itemView);
                this.mItemView = itemView;
                this.mImageView = (ImageView) itemView.findViewById(R.id.mr_cast_group_icon);
                this.mProgressBar = (ProgressBar) itemView.findViewById(R.id.mr_cast_group_progress_bar);
                this.mTextView = (TextView) itemView.findViewById(R.id.mr_cast_group_name);
                this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(MediaRouteDynamicControllerDialog.this.mContext);
                MediaRouterThemeHelper.setIndeterminateProgressBarColor(MediaRouteDynamicControllerDialog.this.mContext, this.mProgressBar);
            }

            private boolean isEnabled(MediaRouter.RouteInfo route) {
                List<MediaRouter.RouteInfo> currentMemberRoutes = MediaRouteDynamicControllerDialog.this.mSelectedRoute.getMemberRoutes();
                return (currentMemberRoutes.size() == 1 && currentMemberRoutes.get(0) == route) ? false : true;
            }

            void bindGroupViewHolder(Item item) {
                MediaRouter.RouteInfo route = (MediaRouter.RouteInfo) item.getData();
                this.mRoute = route;
                this.mImageView.setVisibility(0);
                this.mProgressBar.setVisibility(4);
                boolean enabled = isEnabled(route);
                this.mItemView.setAlpha(enabled ? 1.0f : this.mDisabledAlpha);
                this.mItemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.RecyclerAdapter.GroupViewHolder.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MediaRouteDynamicControllerDialog.this.mRouter.transferToRoute(GroupViewHolder.this.mRoute);
                        GroupViewHolder.this.mImageView.setVisibility(4);
                        GroupViewHolder.this.mProgressBar.setVisibility(0);
                    }
                });
                this.mImageView.setImageDrawable(RecyclerAdapter.this.getIconDrawable(route));
                this.mTextView.setText(route.getName());
            }
        }
    }

    private final class MediaRouterCallback extends MediaRouter.Callback {
        MediaRouterCallback() {
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteAdded(MediaRouter router, MediaRouter.RouteInfo info) {
            MediaRouteDynamicControllerDialog.this.updateRoutesView();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteRemoved(MediaRouter router, MediaRouter.RouteInfo info) {
            MediaRouteDynamicControllerDialog.this.updateRoutesView();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteSelected(MediaRouter router, MediaRouter.RouteInfo route) {
            MediaRouteDynamicControllerDialog.this.mSelectedRoute = route;
            MediaRouteDynamicControllerDialog.this.mIsSelectingRoute = false;
            MediaRouteDynamicControllerDialog.this.updateViewsIfNeeded();
            MediaRouteDynamicControllerDialog.this.updateRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteUnselected(MediaRouter router, MediaRouter.RouteInfo route) {
            MediaRouteDynamicControllerDialog.this.updateRoutesView();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteChanged(MediaRouter router, MediaRouter.RouteInfo route) {
            MediaRouter.RouteInfo.DynamicGroupState state;
            boolean shouldRefreshRoute = false;
            if (route == MediaRouteDynamicControllerDialog.this.mSelectedRoute && route.getDynamicGroupState() != null) {
                Iterator<MediaRouter.RouteInfo> it = route.getProvider().getRoutes().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MediaRouter.RouteInfo memberRoute = it.next();
                    if (!MediaRouteDynamicControllerDialog.this.mSelectedRoute.getMemberRoutes().contains(memberRoute) && (state = memberRoute.getDynamicGroupState()) != null && state.isGroupable() && !MediaRouteDynamicControllerDialog.this.mGroupableRoutes.contains(memberRoute)) {
                        shouldRefreshRoute = true;
                        break;
                    }
                }
            }
            if (shouldRefreshRoute) {
                MediaRouteDynamicControllerDialog.this.updateViewsIfNeeded();
                MediaRouteDynamicControllerDialog.this.updateRoutes();
            } else {
                MediaRouteDynamicControllerDialog.this.updateRoutesView();
            }
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteVolumeChanged(MediaRouter router, MediaRouter.RouteInfo route) {
            MediaRouteVolumeSliderHolder holder;
            int volume = route.getVolume();
            if (MediaRouteDynamicControllerDialog.DEBUG) {
                Log.d(MediaRouteDynamicControllerDialog.TAG, "onRouteVolumeChanged(), route.getVolume:" + volume);
            }
            if (MediaRouteDynamicControllerDialog.this.mRouteForVolumeUpdatingByUser != route && (holder = MediaRouteDynamicControllerDialog.this.mVolumeSliderHolderMap.get(route.getId())) != null) {
                holder.updateVolume();
            }
        }
    }

    private final class MediaControllerCallback extends MediaControllerCompat.Callback {
        MediaControllerCallback() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onSessionDestroyed() {
            if (MediaRouteDynamicControllerDialog.this.mMediaController != null) {
                MediaRouteDynamicControllerDialog.this.mMediaController.unregisterCallback(MediaRouteDynamicControllerDialog.this.mControllerCallback);
                MediaRouteDynamicControllerDialog.this.mMediaController = null;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onMetadataChanged(MediaMetadataCompat metadata) {
            MediaRouteDynamicControllerDialog.this.mDescription = metadata == null ? null : metadata.getDescription();
            MediaRouteDynamicControllerDialog.this.reloadIconIfNeeded();
            MediaRouteDynamicControllerDialog.this.updateMetadataViews();
        }
    }

    private class FetchArtTask extends AsyncTask<Void, Void, Bitmap> {
        private int mBackgroundColor;
        private final Bitmap mIconBitmap;
        private final Uri mIconUri;

        FetchArtTask() {
            Bitmap bitmap = MediaRouteDynamicControllerDialog.this.mDescription == null ? null : MediaRouteDynamicControllerDialog.this.mDescription.getIconBitmap();
            if (MediaRouteDynamicControllerDialog.isBitmapRecycled(bitmap)) {
                Log.w(MediaRouteDynamicControllerDialog.TAG, "Can't fetch the given art bitmap because it's already recycled.");
                bitmap = null;
            }
            this.mIconBitmap = bitmap;
            this.mIconUri = MediaRouteDynamicControllerDialog.this.mDescription != null ? MediaRouteDynamicControllerDialog.this.mDescription.getIconUri() : null;
        }

        Bitmap getIconBitmap() {
            return this.mIconBitmap;
        }

        Uri getIconUri() {
            return this.mIconUri;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            MediaRouteDynamicControllerDialog.this.clearLoadedBitmap();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(Void... arg) {
            InputStream stream;
            Bitmap art = null;
            if (this.mIconBitmap != null) {
                art = this.mIconBitmap;
            } else {
                Uri uri = this.mIconUri;
                if (uri != null) {
                    InputStream stream2 = null;
                    try {
                        try {
                            try {
                                InputStream inputStreamOpenInputStreamByScheme = openInputStreamByScheme(uri);
                                stream2 = inputStreamOpenInputStreamByScheme;
                                if (inputStreamOpenInputStreamByScheme == null) {
                                    Log.w(MediaRouteDynamicControllerDialog.TAG, "Unable to open: " + this.mIconUri);
                                    return null;
                                }
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inJustDecodeBounds = true;
                                BitmapFactory.decodeStream(stream2, null, options);
                                if (options.outWidth == 0 || options.outHeight == 0) {
                                    if (stream2 != null) {
                                        try {
                                            stream2.close();
                                        } catch (IOException e) {
                                        }
                                    }
                                    return null;
                                }
                                try {
                                    stream2.reset();
                                } catch (IOException e2) {
                                    stream2.close();
                                    InputStream inputStreamOpenInputStreamByScheme2 = openInputStreamByScheme(this.mIconUri);
                                    stream = inputStreamOpenInputStreamByScheme2;
                                    if (inputStreamOpenInputStreamByScheme2 == null) {
                                        Log.w(MediaRouteDynamicControllerDialog.TAG, "Unable to open: " + this.mIconUri);
                                        if (stream != null) {
                                            try {
                                                stream.close();
                                            } catch (IOException e3) {
                                            }
                                        }
                                        return null;
                                    }
                                }
                                options.inJustDecodeBounds = false;
                                int reqHeight = MediaRouteDynamicControllerDialog.this.mContext.getResources().getDimensionPixelSize(R.dimen.mr_cast_meta_art_size);
                                int ratio = options.outHeight / reqHeight;
                                options.inSampleSize = Math.max(1, Integer.highestOneBit(ratio));
                                if (isCancelled()) {
                                    if (stream != null) {
                                        try {
                                            stream.close();
                                        } catch (IOException e4) {
                                        }
                                    }
                                    return null;
                                }
                                art = BitmapFactory.decodeStream(stream, null, options);
                                if (stream != null) {
                                    stream.close();
                                }
                            } catch (IOException e5) {
                                Log.w(MediaRouteDynamicControllerDialog.TAG, "Unable to open: " + this.mIconUri, e5);
                                if (stream2 != null) {
                                    stream2.close();
                                }
                            }
                        } catch (IOException e6) {
                        }
                    } finally {
                        if (stream2 != null) {
                            try {
                                stream2.close();
                            } catch (IOException e7) {
                            }
                        }
                    }
                }
            }
            if (MediaRouteDynamicControllerDialog.isBitmapRecycled(art)) {
                Log.w(MediaRouteDynamicControllerDialog.TAG, "Can't use recycled bitmap: " + art);
                return null;
            }
            if (art != null && art.getWidth() < art.getHeight()) {
                Palette palette = new Palette.Builder(art).maximumColorCount(1).generate();
                this.mBackgroundColor = palette.getSwatches().isEmpty() ? 0 : ((Palette.Swatch) palette.getSwatches().get(0)).getRgb();
            }
            return art;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap art) {
            MediaRouteDynamicControllerDialog.this.mFetchArtTask = null;
            if (!ObjectsCompat.equals(MediaRouteDynamicControllerDialog.this.mArtIconBitmap, this.mIconBitmap) || !ObjectsCompat.equals(MediaRouteDynamicControllerDialog.this.mArtIconUri, this.mIconUri)) {
                MediaRouteDynamicControllerDialog.this.mArtIconBitmap = this.mIconBitmap;
                MediaRouteDynamicControllerDialog.this.mArtIconLoadedBitmap = art;
                MediaRouteDynamicControllerDialog.this.mArtIconUri = this.mIconUri;
                MediaRouteDynamicControllerDialog.this.mArtIconBackgroundColor = this.mBackgroundColor;
                MediaRouteDynamicControllerDialog.this.mArtIconIsLoaded = true;
                MediaRouteDynamicControllerDialog.this.updateMetadataViews();
            }
        }

        private InputStream openInputStreamByScheme(Uri uri) throws IOException {
            InputStream stream;
            String scheme = uri.getScheme().toLowerCase();
            if ("android.resource".equals(scheme) || "content".equals(scheme) || "file".equals(scheme)) {
                stream = MediaRouteDynamicControllerDialog.this.mContext.getContentResolver().openInputStream(uri);
            } else {
                URL url = new URL(uri.toString());
                URLConnection conn = url.openConnection();
                conn.setConnectTimeout(MediaRouteDynamicControllerDialog.CONNECTION_TIMEOUT_MS);
                conn.setReadTimeout(MediaRouteDynamicControllerDialog.CONNECTION_TIMEOUT_MS);
                stream = conn.getInputStream();
            }
            if (stream == null) {
                return null;
            }
            return new BufferedInputStream(stream);
        }
    }

    static final class RouteComparator implements Comparator<MediaRouter.RouteInfo> {
        static final RouteComparator sInstance = new RouteComparator();

        RouteComparator() {
        }

        @Override // java.util.Comparator
        public int compare(MediaRouter.RouteInfo lhs, MediaRouter.RouteInfo rhs) {
            return lhs.getName().compareToIgnoreCase(rhs.getName());
        }
    }
}
