package com.android.launcher3.graphics;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.CellLayout;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.Hotseat;
import com.android.launcher3.InsettableFrameLayout;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.WorkspaceLayoutManager;
import com.android.launcher3.allapps.SearchUiManager;
import com.android.launcher3.icons.BaseIconFactory;
import com.android.launcher3.icons.BitmapInfo;
import com.android.launcher3.icons.BitmapRenderer;
import com.android.launcher3.views.ActivityContext;
import com.android.launcher3.views.BaseDragLayer;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherPreviewRenderer implements Callable<Bitmap> {
    private static final String TAG = "LauncherPreviewRenderer";
    private final Context mContext;
    private final DeviceProfile mDp;
    private final InvariantDeviceProfile mIdp;
    private final Rect mInsets;
    private final Handler mUiHandler = new Handler(Looper.getMainLooper());
    private final WorkspaceItemInfo mWorkspaceItemInfo;

    public LauncherPreviewRenderer(Context context, InvariantDeviceProfile idp) {
        this.mContext = context;
        this.mIdp = idp;
        DeviceProfile deviceProfileCopy = idp.portraitProfile.copy(context);
        this.mDp = deviceProfileCopy;
        Rect rect = new Rect();
        this.mInsets = rect;
        int i = (deviceProfileCopy.widthPx - deviceProfileCopy.availableWidthPx) / 2;
        rect.right = i;
        rect.left = i;
        int i2 = (deviceProfileCopy.heightPx - deviceProfileCopy.availableHeightPx) / 2;
        rect.bottom = i2;
        rect.top = i2;
        deviceProfileCopy.updateInsets(rect);
        BitmapInfo bitmapInfoCreateBadgedIconBitmap = new BaseIconFactory(context, idp.fillResIconDpi, idp.iconBitmapSize) { // from class: com.android.launcher3.graphics.LauncherPreviewRenderer.1
        }.createBadgedIconBitmap(new AdaptiveIconDrawable(new ColorDrawable(-1), new ColorDrawable(-1)), Process.myUserHandle(), Build.VERSION.SDK_INT);
        WorkspaceItemInfo workspaceItemInfo = new WorkspaceItemInfo();
        this.mWorkspaceItemInfo = workspaceItemInfo;
        workspaceItemInfo.applyFrom(bitmapInfoCreateBadgedIconBitmap);
        workspaceItemInfo.intent = new Intent();
        String string = context.getString(R.string.label_application);
        workspaceItemInfo.title = string;
        workspaceItemInfo.contentDescription = string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Bitmap call() {
        return BitmapRenderer.createHardwareBitmap(this.mDp.widthPx, this.mDp.heightPx, new BitmapRenderer() { // from class: com.android.launcher3.graphics.-$$Lambda$LauncherPreviewRenderer$bw2L0iH_iTZejil3gO8mWUyT56E
            @Override // com.android.launcher3.icons.BitmapRenderer
            public final void draw(Canvas canvas) {
                this.f$0.lambda$call$1$LauncherPreviewRenderer(canvas);
            }
        });
    }

    public /* synthetic */ void lambda$call$1$LauncherPreviewRenderer(final Canvas canvas) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            Utilities.postAsyncCallback(this.mUiHandler, new Runnable() { // from class: com.android.launcher3.graphics.-$$Lambda$LauncherPreviewRenderer$8xxZHDFlwh8GWb81dNAZkWr5DKY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$call$0$LauncherPreviewRenderer(canvas, countDownLatch);
                }
            });
            try {
                countDownLatch.await();
                return;
            } catch (Exception e) {
                Log.e(TAG, "Error drawing on main thread", e);
                return;
            }
        }
        new MainThreadRenderer(this.mContext).renderScreenShot(canvas);
    }

    public /* synthetic */ void lambda$call$0$LauncherPreviewRenderer(Canvas canvas, CountDownLatch countDownLatch) {
        new MainThreadRenderer(this.mContext).renderScreenShot(canvas);
        countDownLatch.countDown();
    }

    private class MainThreadRenderer extends ContextThemeWrapper implements ActivityContext, WorkspaceLayoutManager, LayoutInflater.Factory2 {
        private final LayoutInflater mHomeElementInflater;
        private final Hotseat mHotseat;
        private final InsettableFrameLayout mRootView;
        private final CellLayout mWorkspace;

        MainThreadRenderer(Context context) {
            super(context, R.style.AppTheme);
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(new ContextThemeWrapper(this, R.style.HomeScreenElementTheme));
            this.mHomeElementInflater = layoutInflaterFrom;
            layoutInflaterFrom.setFactory2(this);
            InsettableFrameLayout insettableFrameLayout = (InsettableFrameLayout) layoutInflaterFrom.inflate(R.layout.launcher_preview_layout, (ViewGroup) null, false);
            this.mRootView = insettableFrameLayout;
            insettableFrameLayout.setInsets(LauncherPreviewRenderer.this.mInsets);
            LauncherPreviewRenderer.measureView(insettableFrameLayout, LauncherPreviewRenderer.this.mDp.widthPx, LauncherPreviewRenderer.this.mDp.heightPx);
            Hotseat hotseat = (Hotseat) insettableFrameLayout.findViewById(R.id.hotseat);
            this.mHotseat = hotseat;
            hotseat.resetLayout(false);
            CellLayout cellLayout = (CellLayout) insettableFrameLayout.findViewById(R.id.workspace);
            this.mWorkspace = cellLayout;
            cellLayout.setPadding(LauncherPreviewRenderer.this.mDp.workspacePadding.left + LauncherPreviewRenderer.this.mDp.cellLayoutPaddingLeftRightPx, LauncherPreviewRenderer.this.mDp.workspacePadding.top, LauncherPreviewRenderer.this.mDp.workspacePadding.right + LauncherPreviewRenderer.this.mDp.cellLayoutPaddingLeftRightPx, LauncherPreviewRenderer.this.mDp.workspacePadding.bottom);
        }

        @Override // android.view.LayoutInflater.Factory2
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            if ("TextClock".equals(name)) {
                return new TextClock(context, attrs) { // from class: com.android.launcher3.graphics.LauncherPreviewRenderer.MainThreadRenderer.1
                    @Override // android.view.View
                    public Handler getHandler() {
                        return LauncherPreviewRenderer.this.mUiHandler;
                    }
                };
            }
            if (!"fragment".equals(name)) {
                return null;
            }
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.PreviewFragment);
            FragmentWithPreview fragmentWithPreview = (FragmentWithPreview) Fragment.instantiate(context, typedArrayObtainStyledAttributes.getString(0));
            fragmentWithPreview.enterPreviewMode(context);
            fragmentWithPreview.onInit(null);
            View viewOnCreateView = fragmentWithPreview.onCreateView(LayoutInflater.from(context), (ViewGroup) parent, null);
            viewOnCreateView.setId(typedArrayObtainStyledAttributes.getInt(1, -1));
            return viewOnCreateView;
        }

        @Override // android.view.LayoutInflater.Factory
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return onCreateView(null, name, context, attrs);
        }

        @Override // com.android.launcher3.views.ActivityContext
        public BaseDragLayer getDragLayer() {
            throw new UnsupportedOperationException();
        }

        @Override // com.android.launcher3.views.ActivityContext
        public DeviceProfile getDeviceProfile() {
            return LauncherPreviewRenderer.this.mDp;
        }

        @Override // com.android.launcher3.WorkspaceLayoutManager
        public Hotseat getHotseat() {
            return this.mHotseat;
        }

        @Override // com.android.launcher3.WorkspaceLayoutManager
        public CellLayout getScreenWithId(int screenId) {
            return this.mWorkspace;
        }

        private void inflateAndAddIcon(WorkspaceItemInfo info) {
            BubbleTextView bubbleTextView = (BubbleTextView) this.mHomeElementInflater.inflate(R.layout.app_icon, (ViewGroup) this.mWorkspace, false);
            bubbleTextView.applyFromWorkspaceItem(info);
            addInScreenFromBind(bubbleTextView, info);
        }

        private void dispatchVisibilityAggregated(View view, boolean isVisible) {
            boolean z = view.getVisibility() == 0;
            if (z || !isVisible) {
                view.onVisibilityAggregated(isVisible);
            }
            if (view instanceof ViewGroup) {
                boolean z2 = z && isVisible;
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    dispatchVisibilityAggregated(viewGroup.getChildAt(i), z2);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void renderScreenShot(Canvas canvas) {
            for (int i = 0; i < LauncherPreviewRenderer.this.mIdp.numHotseatIcons; i++) {
                WorkspaceItemInfo workspaceItemInfo = new WorkspaceItemInfo(LauncherPreviewRenderer.this.mWorkspaceItemInfo);
                workspaceItemInfo.container = -101;
                workspaceItemInfo.screenId = i;
                inflateAndAddIcon(workspaceItemInfo);
            }
            for (int i2 = 0; i2 < LauncherPreviewRenderer.this.mIdp.numColumns; i2++) {
                WorkspaceItemInfo workspaceItemInfo2 = new WorkspaceItemInfo(LauncherPreviewRenderer.this.mWorkspaceItemInfo);
                workspaceItemInfo2.container = -100;
                workspaceItemInfo2.screenId = 0;
                workspaceItemInfo2.cellX = i2;
                workspaceItemInfo2.cellY = LauncherPreviewRenderer.this.mIdp.numRows - 1;
                inflateAndAddIcon(workspaceItemInfo2);
            }
            this.mRootView.findViewById(R.id.apps_view).setTranslationY(LauncherPreviewRenderer.this.mDp.heightPx - ((SearchUiManager) this.mRootView.findViewById(R.id.search_container_all_apps)).getScrollRangeDelta(LauncherPreviewRenderer.this.mInsets));
            LauncherPreviewRenderer.measureView(this.mRootView, LauncherPreviewRenderer.this.mDp.widthPx, LauncherPreviewRenderer.this.mDp.heightPx);
            dispatchVisibilityAggregated(this.mRootView, true);
            LauncherPreviewRenderer.measureView(this.mRootView, LauncherPreviewRenderer.this.mDp.widthPx, LauncherPreviewRenderer.this.mDp.heightPx);
            LauncherPreviewRenderer.measureView(this.mRootView, LauncherPreviewRenderer.this.mDp.widthPx, LauncherPreviewRenderer.this.mDp.heightPx);
            this.mRootView.draw(canvas);
            dispatchVisibilityAggregated(this.mRootView, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void measureView(View view, int width, int height) {
        view.measure(View.MeasureSpec.makeMeasureSpec(width, 1073741824), View.MeasureSpec.makeMeasureSpec(height, 1073741824));
        view.layout(0, 0, width, height);
    }
}
