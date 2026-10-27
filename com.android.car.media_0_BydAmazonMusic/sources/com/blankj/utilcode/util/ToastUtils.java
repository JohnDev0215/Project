package com.blankj.utilcode.util;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.CallSuper;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public final class ToastUtils {
    private static final int COLOR_DEFAULT = -16777217;
    private static final ToastUtils DEFAULT_MAKER = make();
    private static final String NOTHING = "toast nothing";
    private static final String NULL = "toast null";
    private static final String TAG_TOAST = "TAG_TOAST";
    private static WeakReference<e> sWeakToast;
    private String mMode;
    private int mGravity = -1;
    private int mXOffset = -1;
    private int mYOffset = -1;
    private int mBgColor = COLOR_DEFAULT;
    private int mBgResource = -1;
    private int mTextColor = COLOR_DEFAULT;
    private int mTextSize = -1;
    private boolean isLong = false;
    private Drawable[] mIcons = new Drawable[4];
    private boolean isNotUseSystemToast = false;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MODE {
        public static final String DARK = "dark";
        public static final String LIGHT = "light";
    }

    public static final class UtilsMaxWidthRelativeLayout extends RelativeLayout {
        private static final int SPACING = SizeUtils.dp2px(80.0f);

        public UtilsMaxWidthRelativeLayout(Context context) {
            super(context);
        }

        @Override // android.widget.RelativeLayout, android.view.View
        public void onMeasure(int i, int i2) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(ScreenUtils.getAppScreenWidth() - SPACING, Integer.MIN_VALUE), i2);
        }

        public UtilsMaxWidthRelativeLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public UtilsMaxWidthRelativeLayout(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }
    }

    public static class a implements Runnable {
        @Override // java.lang.Runnable
        public final void run() {
            if (ToastUtils.sWeakToast != null) {
                e eVar = (e) ToastUtils.sWeakToast.get();
                if (eVar != null) {
                    eVar.cancel();
                }
                WeakReference unused = ToastUtils.sWeakToast = null;
            }
        }
    }

    public static class b implements Runnable {
        public final /* synthetic */ View b;
        public final /* synthetic */ CharSequence c;
        public final /* synthetic */ int d;

        public b(View view, CharSequence charSequence, int i) {
            this.b = view;
            this.c = charSequence;
            this.d = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ToastUtils.cancel();
            e eVarNewToast = ToastUtils.newToast(ToastUtils.this);
            WeakReference unused = ToastUtils.sWeakToast = new WeakReference(eVarNewToast);
            View view = this.b;
            if (view != null) {
                eVarNewToast.c(view);
            } else {
                eVarNewToast.b(this.c);
            }
            eVarNewToast.a(this.d);
        }
    }

    public static abstract class c implements e {
        public Toast a = new Toast(Utils.getApp());
        public ToastUtils b;
        public View c;

        public c(ToastUtils toastUtils) {
            this.b = toastUtils;
            if (toastUtils.mGravity == -1 && this.b.mXOffset == -1 && this.b.mYOffset == -1) {
                return;
            }
            this.a.setGravity(this.b.mGravity, this.b.mXOffset, this.b.mYOffset);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public final void b(CharSequence charSequence) {
            View viewTryApplyUtilsToastView = this.b.tryApplyUtilsToastView(charSequence);
            if (viewTryApplyUtilsToastView != null) {
                c(viewTryApplyUtilsToastView);
                if (ViewUtils.isLayoutRtl()) {
                    c(d(-1));
                    return;
                }
                return;
            }
            View view = this.a.getView();
            this.c = view;
            if (view == null || view.findViewById(R.id.message) == null) {
                c(ViewUtils.layoutId2View(com.blankj.utilcode.R.layout.utils_toast_view));
            }
            TextView textView = (TextView) this.c.findViewById(R.id.message);
            textView.setText(charSequence);
            if (this.b.mTextColor != ToastUtils.COLOR_DEFAULT) {
                textView.setTextColor(this.b.mTextColor);
            }
            if (this.b.mTextSize != -1) {
                textView.setTextSize(this.b.mTextSize);
            }
            if (this.b.mBgResource != -1) {
                this.c.setBackgroundResource(this.b.mBgResource);
                textView.setBackgroundColor(0);
            } else if (this.b.mBgColor != ToastUtils.COLOR_DEFAULT) {
                Drawable background = this.c.getBackground();
                Drawable background2 = textView.getBackground();
                if (background != null && background2 != null) {
                    background.mutate().setColorFilter(new PorterDuffColorFilter(this.b.mBgColor, PorterDuff.Mode.SRC_IN));
                    textView.setBackgroundColor(0);
                } else if (background != null) {
                    background.mutate().setColorFilter(new PorterDuffColorFilter(this.b.mBgColor, PorterDuff.Mode.SRC_IN));
                } else if (background2 != null) {
                    background2.mutate().setColorFilter(new PorterDuffColorFilter(this.b.mBgColor, PorterDuff.Mode.SRC_IN));
                } else {
                    this.c.setBackgroundColor(this.b.mBgColor);
                }
            }
            if (ViewUtils.isLayoutRtl()) {
                c(d(-1));
            }
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public final void c(View view) {
            this.c = view;
            this.a.setView(view);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        @CallSuper
        public void cancel() {
            Toast toast = this.a;
            if (toast != null) {
                toast.cancel();
            }
            this.a = null;
            this.c = null;
        }

        public final View d(int i) {
            Bitmap bitmapView2Bitmap = ImageUtils.view2Bitmap(this.c);
            ImageView imageView = new ImageView(Utils.getApp());
            imageView.setTag(ToastUtils.TAG_TOAST + i);
            imageView.setImageBitmap(bitmapView2Bitmap);
            return imageView;
        }
    }

    public static final class d extends c {
        public static int f;
        public com.blankj.utilcode.util.b d;
        public c e;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                d.this.cancel();
            }
        }

        public d(ToastUtils toastUtils) {
            super(toastUtils);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public final void a(int i) {
            if (this.a == null) {
                return;
            }
            if (!com.blankj.utilcode.util.f.e()) {
                this.e = (c) e(i);
                return;
            }
            boolean z = false;
            for (Activity activity : com.blankj.utilcode.util.f.c()) {
                if (ActivityUtils.isActivityAlive(activity)) {
                    if (z) {
                        f(activity, f, true);
                    } else {
                        g gVar = new g(this.b, activity.getWindowManager(), 99);
                        gVar.c = d(-1);
                        gVar.a = this.a;
                        gVar.a(i);
                        this.e = gVar;
                        z = true;
                    }
                }
            }
            if (!z) {
                this.e = (c) e(i);
                return;
            }
            com.blankj.utilcode.util.b bVar = new com.blankj.utilcode.util.b(this, f);
            this.d = bVar;
            com.blankj.utilcode.util.f.a(bVar);
            ThreadUtils.runOnUiThreadDelayed(new a(), i == 0 ? 2000L : 3500L);
            f++;
        }

        @Override // com.blankj.utilcode.util.ToastUtils.c, com.blankj.utilcode.util.ToastUtils.e
        public final void cancel() {
            Window window;
            com.blankj.utilcode.util.b bVar = this.d;
            if (bVar != null) {
                com.blankj.utilcode.util.f.f(bVar);
                this.d = null;
                for (Activity activity : com.blankj.utilcode.util.f.c()) {
                    if (ActivityUtils.isActivityAlive(activity) && (window = activity.getWindow()) != null) {
                        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
                        StringBuilder sbK = spi.c.k(ToastUtils.TAG_TOAST);
                        sbK.append(f - 1);
                        View viewFindViewWithTag = viewGroup.findViewWithTag(sbK.toString());
                        if (viewFindViewWithTag != null) {
                            try {
                                viewGroup.removeView(viewFindViewWithTag);
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
            }
            c cVar = this.e;
            if (cVar != null) {
                cVar.cancel();
                this.e = null;
            }
            super.cancel();
        }

        public final e e(int i) {
            f fVar = new f(this.b);
            Toast toast = this.a;
            fVar.a = toast;
            if (toast != null) {
                toast.setDuration(i);
                fVar.a.show();
            }
            return fVar;
        }

        public final void f(Activity activity, int i, boolean z) {
            Window window = activity.getWindow();
            if (window != null) {
                ViewGroup viewGroup = (ViewGroup) window.getDecorView();
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.gravity = this.a.getGravity();
                layoutParams.bottomMargin = BarUtils.getNavBarHeight() + this.a.getYOffset();
                layoutParams.topMargin = BarUtils.getStatusBarHeight() + this.a.getYOffset();
                layoutParams.leftMargin = this.a.getXOffset();
                View viewD = d(i);
                if (z) {
                    viewD.setAlpha(0.0f);
                    viewD.animate().alpha(1.0f).setDuration(200L).start();
                }
                viewGroup.addView(viewD, layoutParams);
            }
        }
    }

    public interface e {
        void a(int i);

        void b(CharSequence charSequence);

        void c(View view);

        void cancel();
    }

    public static final class f extends c {
        public f(ToastUtils toastUtils) {
            super(toastUtils);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public final void a(int i) {
            Toast toast = this.a;
            if (toast == null) {
                return;
            }
            toast.setDuration(i);
            this.a.show();
        }
    }

    public static void cancel() {
        ThreadUtils.runOnUiThread(new a());
    }

    @NonNull
    public static ToastUtils getDefaultMaker() {
        return DEFAULT_MAKER;
    }

    private int getDuration() {
        return this.isLong ? 1 : 0;
    }

    private static CharSequence getToastFriendlyText(CharSequence charSequence) {
        return charSequence == null ? NULL : charSequence.length() == 0 ? NOTHING : charSequence;
    }

    @NonNull
    public static ToastUtils make() {
        return new ToastUtils();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static e newToast(ToastUtils toastUtils) {
        return (toastUtils.isNotUseSystemToast || !NotificationManagerCompat.from(Utils.getApp()).areNotificationsEnabled() || PermissionUtils.isGrantedDrawOverlays()) ? PermissionUtils.isGrantedDrawOverlays() ? new g(toastUtils, 2038) : new d(toastUtils) : new f(toastUtils);
    }

    public static void showLong(@Nullable CharSequence charSequence) {
        show(charSequence, 1, DEFAULT_MAKER);
    }

    public static void showShort(@Nullable CharSequence charSequence) {
        show(charSequence, 0, DEFAULT_MAKER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View tryApplyUtilsToastView(CharSequence charSequence) {
        if (!MODE.DARK.equals(this.mMode) && !MODE.LIGHT.equals(this.mMode)) {
            Drawable[] drawableArr = this.mIcons;
            if (drawableArr[0] == null && drawableArr[1] == null && drawableArr[2] == null && drawableArr[3] == null) {
                return null;
            }
        }
        View viewLayoutId2View = ViewUtils.layoutId2View(com.blankj.utilcode.R.layout.utils_toast_view);
        TextView textView = (TextView) viewLayoutId2View.findViewById(R.id.message);
        if (MODE.DARK.equals(this.mMode)) {
            ((GradientDrawable) viewLayoutId2View.getBackground().mutate()).setColor(Color.parseColor("#BB000000"));
            textView.setTextColor(-1);
        }
        textView.setText(charSequence);
        if (this.mIcons[0] != null) {
            View viewFindViewById = viewLayoutId2View.findViewById(com.blankj.utilcode.R.id.utvLeftIconView);
            ViewCompat.setBackground(viewFindViewById, this.mIcons[0]);
            viewFindViewById.setVisibility(0);
        }
        if (this.mIcons[1] != null) {
            View viewFindViewById2 = viewLayoutId2View.findViewById(com.blankj.utilcode.R.id.utvTopIconView);
            ViewCompat.setBackground(viewFindViewById2, this.mIcons[1]);
            viewFindViewById2.setVisibility(0);
        }
        if (this.mIcons[2] != null) {
            View viewFindViewById3 = viewLayoutId2View.findViewById(com.blankj.utilcode.R.id.utvRightIconView);
            ViewCompat.setBackground(viewFindViewById3, this.mIcons[2]);
            viewFindViewById3.setVisibility(0);
        }
        if (this.mIcons[3] != null) {
            View viewFindViewById4 = viewLayoutId2View.findViewById(com.blankj.utilcode.R.id.utvBottomIconView);
            ViewCompat.setBackground(viewFindViewById4, this.mIcons[3]);
            viewFindViewById4.setVisibility(0);
        }
        return viewLayoutId2View;
    }

    @NonNull
    public final ToastUtils setBgColor(@ColorInt int i) {
        this.mBgColor = i;
        return this;
    }

    @NonNull
    public final ToastUtils setBgResource(@DrawableRes int i) {
        this.mBgResource = i;
        return this;
    }

    @NonNull
    public final ToastUtils setBottomIcon(int i) {
        return setBottomIcon(ContextCompat.getDrawable(Utils.getApp(), i));
    }

    @NonNull
    public final ToastUtils setDurationIsLong(boolean z) {
        this.isLong = z;
        return this;
    }

    @NonNull
    public final ToastUtils setGravity(int i, int i2, int i3) {
        this.mGravity = i;
        this.mXOffset = i2;
        this.mYOffset = i3;
        return this;
    }

    @NonNull
    public final ToastUtils setLeftIcon(@DrawableRes int i) {
        return setLeftIcon(ContextCompat.getDrawable(Utils.getApp(), i));
    }

    @NonNull
    public final ToastUtils setMode(String str) {
        this.mMode = str;
        return this;
    }

    @NonNull
    public final ToastUtils setNotUseSystemToast() {
        this.isNotUseSystemToast = true;
        return this;
    }

    @NonNull
    public final ToastUtils setRightIcon(@DrawableRes int i) {
        return setRightIcon(ContextCompat.getDrawable(Utils.getApp(), i));
    }

    @NonNull
    public final ToastUtils setTextColor(@ColorInt int i) {
        this.mTextColor = i;
        return this;
    }

    @NonNull
    public final ToastUtils setTextSize(int i) {
        this.mTextSize = i;
        return this;
    }

    @NonNull
    public final ToastUtils setTopIcon(@DrawableRes int i) {
        return setTopIcon(ContextCompat.getDrawable(Utils.getApp(), i));
    }

    public final void show(@Nullable CharSequence charSequence) {
        show(charSequence, getDuration(), this);
    }

    public static void showLong(@StringRes int i) {
        show(StringUtils.getString(i), 1, DEFAULT_MAKER);
    }

    public static void showShort(@StringRes int i) {
        show(StringUtils.getString(i), 0, DEFAULT_MAKER);
    }

    @NonNull
    public final ToastUtils setBottomIcon(@Nullable Drawable drawable) {
        this.mIcons[3] = drawable;
        return this;
    }

    @NonNull
    public final ToastUtils setLeftIcon(@Nullable Drawable drawable) {
        this.mIcons[0] = drawable;
        return this;
    }

    @NonNull
    public final ToastUtils setRightIcon(@Nullable Drawable drawable) {
        this.mIcons[2] = drawable;
        return this;
    }

    @NonNull
    public final ToastUtils setTopIcon(@Nullable Drawable drawable) {
        this.mIcons[1] = drawable;
        return this;
    }

    public final void show(@NonNull View view) {
        show(view, getDuration(), this);
    }

    private static void show(@Nullable CharSequence charSequence, int i, ToastUtils toastUtils) {
        show(null, getToastFriendlyText(charSequence), i, toastUtils);
    }

    public static final class g extends c {
        public WindowManager d;
        public WindowManager.LayoutParams e;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                g.this.cancel();
            }
        }

        public g(ToastUtils toastUtils, int i) {
            super(toastUtils);
            this.e = new WindowManager.LayoutParams();
            this.d = (WindowManager) Utils.getApp().getSystemService("window");
            this.e.type = i;
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public final void a(int i) {
            if (this.a == null) {
                return;
            }
            WindowManager.LayoutParams layoutParams = this.e;
            layoutParams.height = -2;
            layoutParams.width = -2;
            layoutParams.format = -3;
            layoutParams.windowAnimations = R.style.Animation.Toast;
            layoutParams.setTitle("ToastWithoutNotification");
            WindowManager.LayoutParams layoutParams2 = this.e;
            layoutParams2.flags = 152;
            layoutParams2.packageName = Utils.getApp().getPackageName();
            this.e.gravity = this.a.getGravity();
            WindowManager.LayoutParams layoutParams3 = this.e;
            int i2 = layoutParams3.gravity;
            if ((i2 & 7) == 7) {
                layoutParams3.horizontalWeight = 1.0f;
            }
            if ((i2 & 112) == 112) {
                layoutParams3.verticalWeight = 1.0f;
            }
            layoutParams3.x = this.a.getXOffset();
            this.e.y = this.a.getYOffset();
            this.e.horizontalMargin = this.a.getHorizontalMargin();
            this.e.verticalMargin = this.a.getVerticalMargin();
            try {
                WindowManager windowManager = this.d;
                if (windowManager != null) {
                    windowManager.addView(this.c, this.e);
                }
            } catch (Exception unused) {
            }
            ThreadUtils.runOnUiThreadDelayed(new a(), i == 0 ? 2000L : 3500L);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.c, com.blankj.utilcode.util.ToastUtils.e
        public final void cancel() {
            try {
                WindowManager windowManager = this.d;
                if (windowManager != null) {
                    windowManager.removeViewImmediate(this.c);
                    this.d = null;
                }
            } catch (Exception unused) {
            }
            super.cancel();
        }

        public g(ToastUtils toastUtils, WindowManager windowManager, int i) {
            super(toastUtils);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.e = layoutParams;
            this.d = windowManager;
            layoutParams.type = i;
        }
    }

    private static void show(@NonNull View view, int i, ToastUtils toastUtils) {
        show(view, null, i, toastUtils);
    }

    public static void showLong(@StringRes int i, Object... objArr) {
        show(StringUtils.getString(i, objArr), 1, DEFAULT_MAKER);
    }

    public static void showShort(@StringRes int i, Object... objArr) {
        show(StringUtils.getString(i, objArr), 0, DEFAULT_MAKER);
    }

    private static void show(@Nullable View view, @Nullable CharSequence charSequence, int i, @NonNull ToastUtils toastUtils) {
        ThreadUtils.runOnUiThread(toastUtils.new b(view, charSequence, i));
    }

    public static void showLong(@Nullable String str, Object... objArr) {
        show(StringUtils.format(str, objArr), 1, DEFAULT_MAKER);
    }

    public static void showShort(@Nullable String str, Object... objArr) {
        show(StringUtils.format(str, objArr), 0, DEFAULT_MAKER);
    }

    public final void show(@StringRes int i) {
        show(StringUtils.getString(i), getDuration(), this);
    }

    public final void show(@StringRes int i, Object... objArr) {
        show(StringUtils.getString(i, objArr), getDuration(), this);
    }

    public final void show(@Nullable String str, Object... objArr) {
        show(StringUtils.format(str, objArr), getDuration(), this);
    }
}
