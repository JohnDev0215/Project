package androidx.mediarouter.app;

import android.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MediaRouteButton extends View {
    private static final String CHOOSER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteChooserDialogFragment";
    private static final int CONNECTION_STATE_CONNECTED = 2;
    private static final int CONNECTION_STATE_CONNECTING = 1;
    private static final int CONNECTION_STATE_DISCONNECTED = 0;
    private static final String CONTROLLER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteControllerDialogFragment";
    private static final String TAG = "MediaRouteButton";
    private static final boolean USE_OUTPUT_SWITCHER_IF_AVAILABLE = true;
    private static ConnectivityReceiver sConnectivityReceiver;
    private boolean mAlwaysVisible;
    private boolean mAttachedToWindow;
    private ColorStateList mButtonTint;
    private final MediaRouterCallback mCallback;
    private boolean mCheatSheetEnabled;
    private int mConnectionState;
    private MediaRouteDialogFactory mDialogFactory;
    private int mMinHeight;
    private int mMinWidth;
    private Drawable mRemoteIndicator;
    RemoteIndicatorLoader mRemoteIndicatorLoader;
    private int mRemoteIndicatorResIdToLoad;
    private final MediaRouter mRouter;
    private MediaRouteSelector mSelector;
    private boolean mUseDynamicGroup;
    private int mVisibility;
    static final SparseArray<Drawable.ConstantState> sRemoteIndicatorCache = new SparseArray<>(2);
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int[] CHECKABLE_STATE_SET = {R.attr.state_checkable};

    public MediaRouteButton(Context context) {
        this(context, null);
    }

    public MediaRouteButton(Context context, AttributeSet attrs) {
        this(context, attrs, androidx.mediarouter.R.attr.mediaRouteButtonStyle);
    }

    public MediaRouteButton(Context context, AttributeSet attrs, int defStyleAttr) {
        Drawable.ConstantState remoteIndicatorState;
        super(MediaRouterThemeHelper.createThemedButtonContext(context), attrs, defStyleAttr);
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mDialogFactory = MediaRouteDialogFactory.getDefault();
        this.mVisibility = 0;
        Context context2 = getContext();
        TypedArray a = context2.obtainStyledAttributes(attrs, androidx.mediarouter.R.styleable.MediaRouteButton, defStyleAttr, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context2, androidx.mediarouter.R.styleable.MediaRouteButton, attrs, a, defStyleAttr, 0);
        if (isInEditMode()) {
            this.mRouter = null;
            this.mCallback = null;
            this.mRemoteIndicator = getResources().getDrawable(a.getResourceId(androidx.mediarouter.R.styleable.MediaRouteButton_externalRouteEnabledDrawableStatic, 0));
            return;
        }
        this.mRouter = MediaRouter.getInstance(context2);
        this.mCallback = new MediaRouterCallback();
        if (sConnectivityReceiver == null) {
            sConnectivityReceiver = new ConnectivityReceiver(context2.getApplicationContext());
        }
        this.mButtonTint = a.getColorStateList(androidx.mediarouter.R.styleable.MediaRouteButton_mediaRouteButtonTint);
        this.mMinWidth = a.getDimensionPixelSize(androidx.mediarouter.R.styleable.MediaRouteButton_android_minWidth, 0);
        this.mMinHeight = a.getDimensionPixelSize(androidx.mediarouter.R.styleable.MediaRouteButton_android_minHeight, 0);
        int remoteIndicatorStaticResId = a.getResourceId(androidx.mediarouter.R.styleable.MediaRouteButton_externalRouteEnabledDrawableStatic, 0);
        this.mRemoteIndicatorResIdToLoad = a.getResourceId(androidx.mediarouter.R.styleable.MediaRouteButton_externalRouteEnabledDrawable, 0);
        a.recycle();
        int i = this.mRemoteIndicatorResIdToLoad;
        if (i != 0 && (remoteIndicatorState = sRemoteIndicatorCache.get(i)) != null) {
            setRemoteIndicatorDrawable(remoteIndicatorState.newDrawable());
        }
        if (this.mRemoteIndicator == null) {
            if (remoteIndicatorStaticResId != 0) {
                Drawable.ConstantState remoteIndicatorStaticState = sRemoteIndicatorCache.get(remoteIndicatorStaticResId);
                if (remoteIndicatorStaticState != null) {
                    setRemoteIndicatorDrawableInternal(remoteIndicatorStaticState.newDrawable());
                } else {
                    RemoteIndicatorLoader remoteIndicatorLoader = new RemoteIndicatorLoader(remoteIndicatorStaticResId, getContext());
                    this.mRemoteIndicatorLoader = remoteIndicatorLoader;
                    remoteIndicatorLoader.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
                }
            } else {
                loadRemoteIndicatorIfNeeded();
            }
        }
        updateContentDescription();
        setClickable(USE_OUTPUT_SWITCHER_IF_AVAILABLE);
    }

    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }

    public void setRouteSelector(MediaRouteSelector selector) {
        if (selector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (!this.mSelector.equals(selector)) {
            if (this.mAttachedToWindow) {
                if (!this.mSelector.isEmpty()) {
                    this.mRouter.removeCallback(this.mCallback);
                }
                if (!selector.isEmpty()) {
                    this.mRouter.addCallback(selector, this.mCallback);
                }
            }
            this.mSelector = selector;
            refreshRoute();
        }
    }

    public MediaRouteDialogFactory getDialogFactory() {
        return this.mDialogFactory;
    }

    public void setDialogFactory(MediaRouteDialogFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("factory must not be null");
        }
        this.mDialogFactory = factory;
    }

    public void enableDynamicGroup() {
        this.mUseDynamicGroup = USE_OUTPUT_SWITCHER_IF_AVAILABLE;
    }

    public boolean showDialog() {
        if (!this.mAttachedToWindow) {
            return false;
        }
        if (MediaRouter.isTransferEnabled()) {
            Context context = getContext();
            Intent intent = new Intent().setAction("com.android.settings.panel.action.MEDIA_OUTPUT").putExtra("com.android.settings.panel.extra.PACKAGE_NAME", context.getPackageName()).putExtra("key_media_session_token", this.mRouter.getMediaSessionToken());
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0);
            for (ResolveInfo resolveInfo : resolveInfos) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (activityInfo != null && activityInfo.applicationInfo != null) {
                    ApplicationInfo appInfo = activityInfo.applicationInfo;
                    if ((appInfo.flags & 129) != 0) {
                        context.startActivity(intent);
                        return USE_OUTPUT_SWITCHER_IF_AVAILABLE;
                    }
                }
            }
        }
        FragmentManager fm = getFragmentManager();
        if (fm == null) {
            throw new IllegalStateException("The activity must be a subclass of FragmentActivity");
        }
        MediaRouter.RouteInfo route = this.mRouter.getSelectedRoute();
        if (route.isDefaultOrBluetooth() || !route.matchesSelector(this.mSelector)) {
            if (fm.findFragmentByTag(CHOOSER_FRAGMENT_TAG) != null) {
                Log.w(TAG, "showDialog(): Route chooser dialog already showing!");
                return false;
            }
            MediaRouteChooserDialogFragment f = this.mDialogFactory.onCreateChooserDialogFragment();
            f.setRouteSelector(this.mSelector);
            f.setUseDynamicGroup(this.mUseDynamicGroup);
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(f, CHOOSER_FRAGMENT_TAG);
            transaction.commitAllowingStateLoss();
        } else {
            if (fm.findFragmentByTag(CONTROLLER_FRAGMENT_TAG) != null) {
                Log.w(TAG, "showDialog(): Route controller dialog already showing!");
                return false;
            }
            MediaRouteControllerDialogFragment f2 = this.mDialogFactory.onCreateControllerDialogFragment();
            f2.setRouteSelector(this.mSelector);
            f2.setUseDynamicGroup(this.mUseDynamicGroup);
            FragmentTransaction transaction2 = fm.beginTransaction();
            transaction2.add(f2, CONTROLLER_FRAGMENT_TAG);
            transaction2.commitAllowingStateLoss();
        }
        return USE_OUTPUT_SWITCHER_IF_AVAILABLE;
    }

    private FragmentManager getFragmentManager() {
        Activity activity = getActivity();
        if (activity instanceof FragmentActivity) {
            return ((FragmentActivity) activity).getSupportFragmentManager();
        }
        return null;
    }

    private Activity getActivity() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    void setCheatSheetEnabled(boolean enable) {
        if (enable != this.mCheatSheetEnabled) {
            this.mCheatSheetEnabled = enable;
            updateContentDescription();
        }
    }

    @Override // android.view.View
    public boolean performClick() {
        boolean handled = super.performClick();
        if (!handled) {
            playSoundEffect(0);
        }
        loadRemoteIndicatorIfNeeded();
        if (showDialog() || handled) {
            return USE_OUTPUT_SWITCHER_IF_AVAILABLE;
        }
        return false;
    }

    @Override // android.view.View
    protected int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        int i = this.mConnectionState;
        if (i == 1) {
            mergeDrawableStates(drawableState, CHECKABLE_STATE_SET);
        } else if (i == 2) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mRemoteIndicator != null) {
            int[] myDrawableState = getDrawableState();
            this.mRemoteIndicator.setState(myDrawableState);
            invalidate();
        }
    }

    public void setRemoteIndicatorDrawable(Drawable d) {
        this.mRemoteIndicatorResIdToLoad = 0;
        setRemoteIndicatorDrawableInternal(d);
    }

    public void setAlwaysVisible(boolean alwaysVisible) {
        if (alwaysVisible != this.mAlwaysVisible) {
            this.mAlwaysVisible = alwaysVisible;
            refreshVisibility();
            refreshRoute();
        }
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable who) {
        if (super.verifyDrawable(who) || who == this.mRemoteIndicator) {
            return USE_OUTPUT_SWITCHER_IF_AVAILABLE;
        }
        return false;
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mRemoteIndicator;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void setVisibility(int visibility) {
        this.mVisibility = visibility;
        refreshVisibility();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            return;
        }
        this.mAttachedToWindow = USE_OUTPUT_SWITCHER_IF_AVAILABLE;
        if (!this.mSelector.isEmpty()) {
            this.mRouter.addCallback(this.mSelector, this.mCallback);
        }
        refreshRoute();
        sConnectivityReceiver.registerReceiver(this);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        if (!isInEditMode()) {
            this.mAttachedToWindow = false;
            if (!this.mSelector.isEmpty()) {
                this.mRouter.removeCallback(this.mCallback);
            }
            sConnectivityReceiver.unregisterReceiver(this);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth;
        int measuredHeight;
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int i = this.mMinWidth;
        Drawable drawable = this.mRemoteIndicator;
        int width = Math.max(i, drawable != null ? drawable.getIntrinsicWidth() + getPaddingLeft() + getPaddingRight() : 0);
        int i2 = this.mMinHeight;
        Drawable drawable2 = this.mRemoteIndicator;
        int height = Math.max(i2, drawable2 != null ? drawable2.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom() : 0);
        if (widthMode != Integer.MIN_VALUE) {
            if (widthMode == 1073741824) {
                measuredWidth = widthSize;
            } else {
                measuredWidth = width;
            }
        } else {
            measuredWidth = Math.min(widthSize, width);
        }
        if (heightMode != Integer.MIN_VALUE) {
            if (heightMode == 1073741824) {
                measuredHeight = heightSize;
            } else {
                measuredHeight = height;
            }
        } else {
            measuredHeight = Math.min(heightSize, height);
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mRemoteIndicator != null) {
            int left = getPaddingLeft();
            int right = getWidth() - getPaddingRight();
            int top = getPaddingTop();
            int bottom = getHeight() - getPaddingBottom();
            int drawWidth = this.mRemoteIndicator.getIntrinsicWidth();
            int drawHeight = this.mRemoteIndicator.getIntrinsicHeight();
            int drawLeft = (((right - left) - drawWidth) / 2) + left;
            int drawTop = (((bottom - top) - drawHeight) / 2) + top;
            this.mRemoteIndicator.setBounds(drawLeft, drawTop, drawLeft + drawWidth, drawTop + drawHeight);
            this.mRemoteIndicator.draw(canvas);
        }
    }

    private void loadRemoteIndicatorIfNeeded() {
        if (this.mRemoteIndicatorResIdToLoad > 0) {
            RemoteIndicatorLoader remoteIndicatorLoader = this.mRemoteIndicatorLoader;
            if (remoteIndicatorLoader != null) {
                remoteIndicatorLoader.cancel(false);
            }
            RemoteIndicatorLoader remoteIndicatorLoader2 = new RemoteIndicatorLoader(this.mRemoteIndicatorResIdToLoad, getContext());
            this.mRemoteIndicatorLoader = remoteIndicatorLoader2;
            this.mRemoteIndicatorResIdToLoad = 0;
            remoteIndicatorLoader2.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }
    }

    void setRemoteIndicatorDrawableInternal(Drawable d) {
        Drawable drawable;
        RemoteIndicatorLoader remoteIndicatorLoader = this.mRemoteIndicatorLoader;
        if (remoteIndicatorLoader != null) {
            remoteIndicatorLoader.cancel(false);
        }
        Drawable drawable2 = this.mRemoteIndicator;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.mRemoteIndicator);
        }
        if (d != null) {
            if (this.mButtonTint != null) {
                d = DrawableCompat.wrap(d.mutate());
                DrawableCompat.setTintList(d, this.mButtonTint);
            }
            d.setCallback(this);
            d.setState(getDrawableState());
            d.setVisible(getVisibility() == 0, false);
        }
        this.mRemoteIndicator = d;
        refreshDrawableState();
        if (this.mAttachedToWindow && (drawable = this.mRemoteIndicator) != null && (drawable.getCurrent() instanceof AnimationDrawable)) {
            AnimationDrawable curDrawable = (AnimationDrawable) this.mRemoteIndicator.getCurrent();
            int i = this.mConnectionState;
            if (i == 1) {
                if (!curDrawable.isRunning()) {
                    curDrawable.start();
                }
            } else if (i == 2) {
                if (curDrawable.isRunning()) {
                    curDrawable.stop();
                }
                curDrawable.selectDrawable(curDrawable.getNumberOfFrames() - 1);
            }
        }
    }

    void refreshVisibility() {
        super.setVisibility((this.mVisibility != 0 || this.mAlwaysVisible || sConnectivityReceiver.isConnected()) ? this.mVisibility : 4);
        Drawable drawable = this.mRemoteIndicator;
        if (drawable != null) {
            drawable.setVisible(getVisibility() == 0 ? USE_OUTPUT_SWITCHER_IF_AVAILABLE : false, false);
        }
    }

    void refreshRoute() {
        MediaRouter.RouteInfo route = this.mRouter.getSelectedRoute();
        boolean isRemote = !route.isDefaultOrBluetooth() && route.matchesSelector(this.mSelector);
        int connectionState = isRemote ? route.getConnectionState() : 0;
        boolean needsRefresh = false;
        if (this.mConnectionState != connectionState) {
            this.mConnectionState = connectionState;
            needsRefresh = USE_OUTPUT_SWITCHER_IF_AVAILABLE;
        }
        if (needsRefresh) {
            updateContentDescription();
            refreshDrawableState();
        }
        if (connectionState == 1) {
            loadRemoteIndicatorIfNeeded();
        }
        if (this.mAttachedToWindow) {
            setEnabled(this.mAlwaysVisible || this.mRouter.isRouteAvailable(this.mSelector, 1));
        }
        Drawable drawable = this.mRemoteIndicator;
        if (drawable != null && (drawable.getCurrent() instanceof AnimationDrawable)) {
            AnimationDrawable curDrawable = (AnimationDrawable) this.mRemoteIndicator.getCurrent();
            if (this.mAttachedToWindow) {
                if ((needsRefresh || connectionState == 1) && !curDrawable.isRunning()) {
                    curDrawable.start();
                    return;
                }
                return;
            }
            if (connectionState == 2) {
                if (curDrawable.isRunning()) {
                    curDrawable.stop();
                }
                curDrawable.selectDrawable(curDrawable.getNumberOfFrames() - 1);
            }
        }
    }

    private void updateContentDescription() {
        int resId;
        int i = this.mConnectionState;
        if (i == 1) {
            resId = androidx.mediarouter.R.string.mr_cast_button_connecting;
        } else if (i == 2) {
            resId = androidx.mediarouter.R.string.mr_cast_button_connected;
        } else {
            resId = androidx.mediarouter.R.string.mr_cast_button_disconnected;
        }
        String contentDesc = getContext().getString(resId);
        setContentDescription(contentDesc);
        TooltipCompat.setTooltipText(this, (!this.mCheatSheetEnabled || TextUtils.isEmpty(contentDesc)) ? null : contentDesc);
    }

    private final class MediaRouterCallback extends MediaRouter.Callback {
        MediaRouterCallback() {
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteAdded(MediaRouter router, MediaRouter.RouteInfo info) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteRemoved(MediaRouter router, MediaRouter.RouteInfo info) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteChanged(MediaRouter router, MediaRouter.RouteInfo info) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteSelected(MediaRouter router, MediaRouter.RouteInfo info) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteUnselected(MediaRouter router, MediaRouter.RouteInfo info) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onProviderAdded(MediaRouter router, MediaRouter.ProviderInfo provider) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onProviderRemoved(MediaRouter router, MediaRouter.ProviderInfo provider) {
            MediaRouteButton.this.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onProviderChanged(MediaRouter router, MediaRouter.ProviderInfo provider) {
            MediaRouteButton.this.refreshRoute();
        }
    }

    private final class RemoteIndicatorLoader extends AsyncTask<Void, Void, Drawable> {
        private final Context mContext;
        private final int mResId;

        RemoteIndicatorLoader(int resId, Context context) {
            this.mResId = resId;
            this.mContext = context;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Drawable doInBackground(Void... params) {
            Drawable.ConstantState remoteIndicatorState = MediaRouteButton.sRemoteIndicatorCache.get(this.mResId);
            if (remoteIndicatorState == null) {
                return this.mContext.getResources().getDrawable(this.mResId);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Drawable remoteIndicator) {
            if (remoteIndicator != null) {
                cacheAndReset(remoteIndicator);
            } else {
                Drawable.ConstantState remoteIndicatorState = MediaRouteButton.sRemoteIndicatorCache.get(this.mResId);
                if (remoteIndicatorState != null) {
                    remoteIndicator = remoteIndicatorState.newDrawable();
                }
                MediaRouteButton.this.mRemoteIndicatorLoader = null;
            }
            MediaRouteButton.this.setRemoteIndicatorDrawableInternal(remoteIndicator);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onCancelled(Drawable remoteIndicator) {
            cacheAndReset(remoteIndicator);
        }

        private void cacheAndReset(Drawable remoteIndicator) {
            if (remoteIndicator != null) {
                MediaRouteButton.sRemoteIndicatorCache.put(this.mResId, remoteIndicator.getConstantState());
            }
            MediaRouteButton.this.mRemoteIndicatorLoader = null;
        }
    }

    private static final class ConnectivityReceiver extends BroadcastReceiver {
        private final Context mContext;
        private boolean mIsConnected = MediaRouteButton.USE_OUTPUT_SWITCHER_IF_AVAILABLE;
        private List<MediaRouteButton> mButtons = new ArrayList();

        ConnectivityReceiver(Context context) {
            this.mContext = context;
        }

        public void registerReceiver(MediaRouteButton button) {
            if (this.mButtons.size() == 0) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                this.mContext.registerReceiver(this, intentFilter);
            }
            this.mButtons.add(button);
        }

        public void unregisterReceiver(MediaRouteButton button) {
            this.mButtons.remove(button);
            if (this.mButtons.size() == 0) {
                this.mContext.unregisterReceiver(this);
            }
        }

        public boolean isConnected() {
            return this.mIsConnected;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean isConnected;
            String action = intent.getAction();
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action) && this.mIsConnected != (isConnected = intent.getBooleanExtra("noConnectivity", false) ^ MediaRouteButton.USE_OUTPUT_SWITCHER_IF_AVAILABLE)) {
                this.mIsConnected = isConnected;
                for (MediaRouteButton button : this.mButtons) {
                    button.refreshVisibility();
                }
            }
        }
    }
}
