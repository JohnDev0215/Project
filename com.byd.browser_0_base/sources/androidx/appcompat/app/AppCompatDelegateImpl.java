package androidx.appcompat.app;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.SupportActionModeWrapper;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.WindowCallbackWrapper;
import androidx.appcompat.view.menu.ListMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.DecorContentParent;
import androidx.appcompat.widget.FitWindowsViewGroup;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.appcompat.widget.ViewUtils;
import defpackage.bz;
import defpackage.in3;
import defpackage.kn3;
import defpackage.o91;
import defpackage.om3;
import defpackage.p91;
import defpackage.pt3;
import defpackage.q91;
import defpackage.rk1;
import defpackage.sa1;
import defpackage.wm2;
import defpackage.wq0;
import defpackage.wr1;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class AppCompatDelegateImpl extends AppCompatDelegate implements MenuBuilder.Callback, LayoutInflater.Factory2 {
    public static final wm2<String, Integer> m0 = new wm2<>();
    public static final int[] n0 = {R.attr.windowBackground};
    public static final boolean o0 = !"robolectric".equals(Build.FINGERPRINT);
    public static final boolean p0 = true;
    public PopupWindow A;
    public Runnable B;
    public boolean E;
    public ViewGroup F;
    public TextView G;
    public View H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f1J;
    public boolean K;
    public boolean L;
    public boolean M;
    public boolean N;
    public boolean O;
    public boolean P;
    public PanelFeatureState[] Q;
    public PanelFeatureState R;
    public boolean S;
    public boolean T;
    public boolean U;
    public boolean V;
    public Configuration W;
    public int X;
    public int Y;
    public int Z;
    public boolean a0;
    public AutoTimeNightModeManager b0;
    public AutoBatteryNightModeManager c0;
    public boolean d0;
    public int e0;
    public boolean g0;
    public Rect h0;
    public Rect i0;
    public AppCompatViewInflater j0;
    public OnBackInvokedDispatcher k0;
    public a l0;
    public final Object n;
    public final Context o;
    public Window p;
    public AppCompatWindowCallback q;
    public final AppCompatCallback r;
    public ActionBar s;
    public SupportMenuInflater t;
    public CharSequence u;
    public DecorContentParent v;
    public ActionMenuPresenterCallback w;
    public PanelMenuPresenterCallback x;
    public ActionMode y;
    public ActionBarContextView z;
    public in3 C = null;
    public boolean D = true;
    public final Runnable f0 = new AnonymousClass2();

    /* JADX INFO: renamed from: androidx.appcompat.app.AppCompatDelegateImpl$1, reason: invalid class name */
    public class AnonymousClass1 implements Thread.UncaughtExceptionHandler {
        public AnonymousClass1() {
            throw null;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public final void uncaughtException(Thread thread, Throwable th) {
            String message;
            if (!((th instanceof Resources.NotFoundException) && (message = th.getMessage()) != null && (message.contains("drawable") || message.contains("Drawable")))) {
                throw null;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            throw null;
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.app.AppCompatDelegateImpl$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        public AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl.e0 & 1) != 0) {
                appCompatDelegateImpl.P(0);
            }
            if ((appCompatDelegateImpl.e0 & 4096) != 0) {
                appCompatDelegateImpl.P(108);
            }
            appCompatDelegateImpl.d0 = false;
            appCompatDelegateImpl.e0 = 0;
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.app.AppCompatDelegateImpl$4, reason: invalid class name */
    class AnonymousClass4 implements FitWindowsViewGroup.OnFitSystemWindowsListener {
        public AnonymousClass4() {
            throw null;
        }

        @Override // androidx.appcompat.widget.FitWindowsViewGroup.OnFitSystemWindowsListener
        public final void a(Rect rect) {
            throw null;
        }
    }

    public class ActionBarDrawableToggleImpl implements ActionBarDrawerToggle.Delegate {
    }

    public interface ActionBarMenuCallback {
        void a(int i);

        View onCreatePanelView(int i);
    }

    public final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        public ActionMenuPresenterCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public final void c(MenuBuilder menuBuilder, boolean z) {
            AppCompatDelegateImpl.this.L(menuBuilder);
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public final boolean d(MenuBuilder menuBuilder) {
            Window.Callback callbackV = AppCompatDelegateImpl.this.V();
            if (callbackV == null) {
                return true;
            }
            callbackV.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    public class ActionModeCallbackWrapperV9 implements ActionMode.Callback {
        public final ActionMode.Callback a;

        public ActionModeCallbackWrapperV9(SupportActionModeWrapper.CallbackWrapper callbackWrapper) {
            this.a = callbackWrapper;
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public final void a(ActionMode actionMode) {
            this.a.a(actionMode);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.A != null) {
                appCompatDelegateImpl.p.getDecorView().removeCallbacks(appCompatDelegateImpl.B);
            }
            if (appCompatDelegateImpl.z != null) {
                in3 in3Var = appCompatDelegateImpl.C;
                if (in3Var != null) {
                    in3Var.b();
                }
                in3 in3VarB = om3.b(appCompatDelegateImpl.z);
                in3VarB.a(0.0f);
                appCompatDelegateImpl.C = in3VarB;
                in3VarB.d(new kn3() { // from class: androidx.appcompat.app.AppCompatDelegateImpl.ActionModeCallbackWrapperV9.1
                    @Override // defpackage.kn3, defpackage.jn3
                    public final void a() {
                        ActionModeCallbackWrapperV9 actionModeCallbackWrapperV9 = ActionModeCallbackWrapperV9.this;
                        AppCompatDelegateImpl.this.z.setVisibility(8);
                        AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                        PopupWindow popupWindow = appCompatDelegateImpl2.A;
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                        } else if (appCompatDelegateImpl2.z.getParent() instanceof View) {
                            View view = (View) appCompatDelegateImpl2.z.getParent();
                            WeakHashMap<View, in3> weakHashMap = om3.a;
                            om3.h.c(view);
                        }
                        appCompatDelegateImpl2.z.g();
                        appCompatDelegateImpl2.C.d(null);
                        appCompatDelegateImpl2.C = null;
                        ViewGroup viewGroup = appCompatDelegateImpl2.F;
                        WeakHashMap<View, in3> weakHashMap2 = om3.a;
                        om3.h.c(viewGroup);
                    }
                });
            }
            AppCompatCallback appCompatCallback = appCompatDelegateImpl.r;
            if (appCompatCallback != null) {
                appCompatCallback.I0();
            }
            appCompatDelegateImpl.y = null;
            ViewGroup viewGroup = appCompatDelegateImpl.F;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.h.c(viewGroup);
            appCompatDelegateImpl.d0();
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public final boolean b(ActionMode actionMode, MenuBuilder menuBuilder) {
            return this.a.b(actionMode, menuBuilder);
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public final boolean c(ActionMode actionMode, MenuItem menuItem) {
            return this.a.c(actionMode, menuItem);
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public final boolean d(ActionMode actionMode, MenuBuilder menuBuilder) {
            ViewGroup viewGroup = AppCompatDelegateImpl.this.F;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.h.c(viewGroup);
            return this.a.d(actionMode, menuBuilder);
        }
    }

    public static class Api17Impl {
        private Api17Impl() {
        }
    }

    public static class Api21Impl {
        private Api21Impl() {
        }
    }

    public static class Api24Impl {
        private Api24Impl() {
        }
    }

    public static class Api26Impl {
        private Api26Impl() {
        }
    }

    public static class Api33Impl {
        private Api33Impl() {
        }

        public static OnBackInvokedDispatcher a(Activity activity) {
            return activity.getOnBackInvokedDispatcher();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [android.window.OnBackInvokedCallback, androidx.appcompat.app.a] */
        public static a b(Object obj, final AppCompatDelegateImpl appCompatDelegateImpl) {
            Objects.requireNonNull(appCompatDelegateImpl);
            ?? r0 = new OnBackInvokedCallback() { // from class: androidx.appcompat.app.a
                @Override // android.window.OnBackInvokedCallback
                public final void onBackInvoked() {
                    appCompatDelegateImpl.Y();
                }
            };
            ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(1000000, r0);
            return r0;
        }

        public static void c(Object obj, Object obj2) {
            ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
        }
    }

    public class AppCompatWindowCallback extends WindowCallbackWrapper {
        public ActionBarMenuCallback f;
        public boolean g;
        public boolean h;
        public boolean i;

        public AppCompatWindowCallback(Window.Callback callback) {
            super(callback);
        }

        public final void a(Window.Callback callback) {
            try {
                this.g = true;
                callback.onContentChanged();
            } finally {
                this.g = false;
            }
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return this.h ? this.e.dispatchKeyEvent(keyEvent) : AppCompatDelegateImpl.this.O(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            boolean z;
            if (super.dispatchKeyShortcutEvent(keyEvent)) {
                return true;
            }
            int keyCode = keyEvent.getKeyCode();
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.W();
            ActionBar actionBar = appCompatDelegateImpl.s;
            if (actionBar == null || !actionBar.p(keyCode, keyEvent)) {
                PanelFeatureState panelFeatureState = appCompatDelegateImpl.R;
                if (panelFeatureState == null || !appCompatDelegateImpl.a0(panelFeatureState, keyEvent.getKeyCode(), keyEvent)) {
                    if (appCompatDelegateImpl.R == null) {
                        PanelFeatureState panelFeatureStateU = appCompatDelegateImpl.U(0);
                        appCompatDelegateImpl.b0(panelFeatureStateU, keyEvent);
                        boolean zA0 = appCompatDelegateImpl.a0(panelFeatureStateU, keyEvent.getKeyCode(), keyEvent);
                        panelFeatureStateU.k = false;
                        if (zA0) {
                        }
                    }
                    z = false;
                } else {
                    PanelFeatureState panelFeatureState2 = appCompatDelegateImpl.R;
                    if (panelFeatureState2 != null) {
                        panelFeatureState2.l = true;
                    }
                }
                z = true;
            } else {
                z = true;
            }
            return z;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public final void onContentChanged() {
            if (this.g) {
                this.e.onContentChanged();
            }
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public final boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof MenuBuilder)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public final View onCreatePanelView(int i) {
            View viewOnCreatePanelView;
            ActionBarMenuCallback actionBarMenuCallback = this.f;
            return (actionBarMenuCallback == null || (viewOnCreatePanelView = actionBarMenuCallback.onCreatePanelView(i)) == null) ? super.onCreatePanelView(i) : viewOnCreatePanelView;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public final boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (i == 108) {
                appCompatDelegateImpl.W();
                ActionBar actionBar = appCompatDelegateImpl.s;
                if (actionBar != null) {
                    actionBar.i(true);
                }
            } else {
                appCompatDelegateImpl.getClass();
            }
            return true;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public final void onPanelClosed(int i, Menu menu) {
            if (this.i) {
                this.e.onPanelClosed(i, menu);
                return;
            }
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (i == 108) {
                appCompatDelegateImpl.W();
                ActionBar actionBar = appCompatDelegateImpl.s;
                if (actionBar != null) {
                    actionBar.i(false);
                    return;
                }
                return;
            }
            if (i != 0) {
                appCompatDelegateImpl.getClass();
                return;
            }
            PanelFeatureState panelFeatureStateU = appCompatDelegateImpl.U(i);
            if (panelFeatureStateU.m) {
                appCompatDelegateImpl.M(panelFeatureStateU, false);
            }
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public final boolean onPreparePanel(int i, View view, Menu menu) {
            MenuBuilder menuBuilder = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
            if (i == 0 && menuBuilder == null) {
                return false;
            }
            if (menuBuilder != null) {
                menuBuilder.x = true;
            }
            ActionBarMenuCallback actionBarMenuCallback = this.f;
            if (actionBarMenuCallback != null) {
                actionBarMenuCallback.a(i);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i, view, menu);
            if (menuBuilder != null) {
                menuBuilder.x = false;
            }
            return zOnPreparePanel;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public final void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            MenuBuilder menuBuilder = AppCompatDelegateImpl.this.U(0).h;
            if (menuBuilder != null) {
                super.onProvideKeyboardShortcuts(list, menuBuilder, i);
            } else {
                super.onProvideKeyboardShortcuts(list, menu, i);
            }
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public final android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:59:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:62:0x0146  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x0160  */
        /* JADX WARN: Removed duplicated region for block: B:68:0x0185  */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback r9, int r10) {
            /*
                Method dump skipped, instruction units count: 436
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.AppCompatWindowCallback.onWindowStartingActionMode(android.view.ActionMode$Callback, int):android.view.ActionMode");
        }
    }

    public class AutoBatteryNightModeManager extends AutoNightModeManager {
        public final PowerManager c;

        public AutoBatteryNightModeManager(Context context) {
            super();
            this.c = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public final IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public final int c() {
            return this.c.isPowerSaveMode() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public final void d() {
            AppCompatDelegateImpl.this.e();
        }
    }

    public abstract class AutoNightModeManager {
        public BroadcastReceiver a;

        public AutoNightModeManager() {
        }

        public final void a() {
            BroadcastReceiver broadcastReceiver = this.a;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.o.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.a = null;
            }
        }

        public abstract IntentFilter b();

        public abstract int c();

        public abstract void d();

        public final void e() {
            a();
            IntentFilter intentFilterB = b();
            if (intentFilterB == null || intentFilterB.countActions() == 0) {
                return;
            }
            if (this.a == null) {
                this.a = new BroadcastReceiver() { // from class: androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager.1
                    @Override // android.content.BroadcastReceiver
                    public final void onReceive(Context context, Intent intent) {
                        AutoNightModeManager.this.d();
                    }
                };
            }
            AppCompatDelegateImpl.this.o.registerReceiver(this.a, intentFilterB);
        }
    }

    public class AutoTimeNightModeManager extends AutoNightModeManager {
        public final TwilightManager c;

        public AutoTimeNightModeManager(TwilightManager twilightManager) {
            super();
            this.c = twilightManager;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public final IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0035  */
        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int c() {
            /*
                Method dump skipped, instruction units count: 246
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.AutoTimeNightModeManager.c():int");
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public final void d() {
            AppCompatDelegateImpl.this.e();
        }
    }

    public static class ContextThemeWrapperCompatApi17Impl {
        private ContextThemeWrapperCompatApi17Impl() {
        }
    }

    public class ListMenuDecorView extends ContentFrameLayout {
        public ListMenuDecorView(ContextThemeWrapper contextThemeWrapper) {
            super(contextThemeWrapper);
        }

        @Override // android.view.ViewGroup, android.view.View
        public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.O(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (x < -5 || y < -5 || x > getWidth() + 5 || y > getHeight() + 5) {
                    AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                    appCompatDelegateImpl.M(appCompatDelegateImpl.U(0), true);
                    return true;
                }
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public final void setBackgroundResource(int i) {
            setBackgroundDrawable(AppCompatResources.a(getContext(), i));
        }
    }

    public static final class PanelFeatureState {
        public final int a;
        public int b;
        public int c;
        public int d;
        public ViewGroup e;
        public View f;
        public View g;
        public MenuBuilder h;
        public ListMenuPresenter i;
        public ContextThemeWrapper j;
        public boolean k;
        public boolean l;
        public boolean m;
        public boolean n = false;
        public boolean o;
        public Bundle p;

        @SuppressLint({"BanParcelableUsage"})
        public static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.appcompat.app.AppCompatDelegateImpl.PanelFeatureState.SavedState.1
                @Override // android.os.Parcelable.ClassLoaderCreator
                public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.a(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                public final Object[] newArray(int i) {
                    return new SavedState[i];
                }

                @Override // android.os.Parcelable.Creator
                public final Object createFromParcel(Parcel parcel) {
                    return SavedState.a(parcel, null);
                }
            };
            public int e;
            public boolean f;
            public Bundle g;

            public static SavedState a(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.e = parcel.readInt();
                boolean z = parcel.readInt() == 1;
                savedState.f = z;
                if (z) {
                    savedState.g = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.e);
                parcel.writeInt(this.f ? 1 : 0);
                if (this.f) {
                    parcel.writeBundle(this.g);
                }
            }
        }

        public PanelFeatureState(int i) {
            this.a = i;
        }
    }

    public final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
        public PanelMenuPresenterCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public final void c(MenuBuilder menuBuilder, boolean z) {
            PanelFeatureState panelFeatureState;
            MenuBuilder menuBuilderK = menuBuilder.k();
            int i = 0;
            boolean z2 = menuBuilderK != menuBuilder;
            if (z2) {
                menuBuilder = menuBuilderK;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            PanelFeatureState[] panelFeatureStateArr = appCompatDelegateImpl.Q;
            int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
            while (true) {
                if (i < length) {
                    panelFeatureState = panelFeatureStateArr[i];
                    if (panelFeatureState != null && panelFeatureState.h == menuBuilder) {
                        break;
                    } else {
                        i++;
                    }
                } else {
                    panelFeatureState = null;
                    break;
                }
            }
            if (panelFeatureState != null) {
                if (!z2) {
                    appCompatDelegateImpl.M(panelFeatureState, z);
                } else {
                    appCompatDelegateImpl.K(panelFeatureState.a, panelFeatureState, menuBuilderK);
                    appCompatDelegateImpl.M(panelFeatureState, true);
                }
            }
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public final boolean d(MenuBuilder menuBuilder) {
            Window.Callback callbackV;
            if (menuBuilder != menuBuilder.k()) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.K || (callbackV = appCompatDelegateImpl.V()) == null || appCompatDelegateImpl.V) {
                return true;
            }
            callbackV.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    public AppCompatDelegateImpl(Context context, Window window, AppCompatCallback appCompatCallback, Object obj) {
        wm2<String, Integer> wm2Var;
        Integer num;
        AppCompatActivity appCompatActivity = null;
        this.X = -100;
        this.o = context;
        this.r = appCompatCallback;
        this.n = obj;
        if (obj instanceof Dialog) {
            while (true) {
                if (context != null) {
                    if (!(context instanceof AppCompatActivity)) {
                        if (!(context instanceof ContextWrapper)) {
                            break;
                        } else {
                            context = ((ContextWrapper) context).getBaseContext();
                        }
                    } else {
                        appCompatActivity = (AppCompatActivity) context;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (appCompatActivity != null) {
                this.X = appCompatActivity.A1().i();
            }
        }
        if (this.X == -100 && (num = (wm2Var = m0).get(this.n.getClass().getName())) != null) {
            this.X = num.intValue();
            wm2Var.remove(this.n.getClass().getName());
        }
        if (window != null) {
            I(window);
        }
        AppCompatDrawableManager.d();
    }

    public static o91 J(Context context) {
        o91 o91Var;
        o91 o91Var2;
        if (Build.VERSION.SDK_INT >= 33 || (o91Var = AppCompatDelegate.g) == null) {
            return null;
        }
        o91 o91VarT = T(context.getApplicationContext().getResources().getConfiguration());
        p91 p91Var = o91Var.a;
        if (p91Var.isEmpty()) {
            o91Var2 = o91.b;
        } else {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i = 0;
            while (i < o91VarT.a.size() + p91Var.size()) {
                Locale locale = i < p91Var.size() ? p91Var.get(i) : o91VarT.a.get(i - p91Var.size());
                if (locale != null) {
                    linkedHashSet.add(locale);
                }
                i++;
            }
            o91Var2 = new o91(new q91(new LocaleList((Locale[]) linkedHashSet.toArray(new Locale[linkedHashSet.size()]))));
        }
        return o91Var2.a.isEmpty() ? o91VarT : o91Var2;
    }

    public static Configuration N(Context context, int i, o91 o91Var, Configuration configuration, boolean z) {
        int i2 = i != 1 ? i != 2 ? z ? 0 : context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i2 | (configuration2.uiMode & (-49));
        if (o91Var != null) {
            configuration2.setLocales(LocaleList.forLanguageTags(o91Var.a.a()));
        }
        return configuration2;
    }

    public static o91 T(Configuration configuration) {
        return o91.a(configuration.getLocales().toLanguageTags());
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void A(View view, ViewGroup.LayoutParams layoutParams) {
        Q();
        ViewGroup viewGroup = (ViewGroup) this.F.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.q.a(this.p.getCallback());
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void C() {
        this.D = false;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void D(int i) {
        if (this.X != i) {
            this.X = i;
            if (this.T) {
                e();
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void E(Toolbar toolbar) {
        Object obj = this.n;
        if (obj instanceof Activity) {
            W();
            ActionBar actionBar = this.s;
            if (actionBar instanceof WindowDecorActionBar) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.t = null;
            if (actionBar != null) {
                actionBar.o();
            }
            this.s = null;
            if (toolbar != null) {
                ToolbarActionBar toolbarActionBar = new ToolbarActionBar(toolbar, obj instanceof Activity ? ((Activity) obj).getTitle() : this.u, this.q);
                this.s = toolbarActionBar;
                this.q.f = toolbarActionBar.c;
                toolbar.setBackInvokedCallbackEnabled(true);
            } else {
                this.q.f = null;
            }
            m();
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void F(int i) {
        this.Y = i;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void G(CharSequence charSequence) {
        this.u = charSequence;
        DecorContentParent decorContentParent = this.v;
        if (decorContentParent != null) {
            decorContentParent.setWindowTitle(charSequence);
            return;
        }
        ActionBar actionBar = this.s;
        if (actionBar != null) {
            actionBar.x(charSequence);
            return;
        }
        TextView textView = this.G;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean H(boolean r13, boolean r14) {
        /*
            Method dump skipped, instruction units count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.H(boolean, boolean):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void I(android.view.Window r5) {
        /*
            r4 = this;
            android.view.Window r0 = r4.p
            java.lang.String r1 = "AppCompat has already installed itself into the Window"
            if (r0 != 0) goto L6b
            android.view.Window$Callback r0 = r5.getCallback()
            boolean r2 = r0 instanceof androidx.appcompat.app.AppCompatDelegateImpl.AppCompatWindowCallback
            if (r2 != 0) goto L65
            androidx.appcompat.app.AppCompatDelegateImpl$AppCompatWindowCallback r1 = new androidx.appcompat.app.AppCompatDelegateImpl$AppCompatWindowCallback
            r1.<init>(r0)
            r4.q = r1
            r5.setCallback(r1)
            androidx.appcompat.widget.TintTypedArray r0 = new androidx.appcompat.widget.TintTypedArray
            android.content.Context r1 = r4.o
            r2 = 0
            int[] r3 = androidx.appcompat.app.AppCompatDelegateImpl.n0
            android.content.res.TypedArray r3 = r1.obtainStyledAttributes(r2, r3)
            r0.<init>(r1, r3)
            r1 = 0
            android.graphics.drawable.Drawable r1 = r0.f(r1)
            if (r1 == 0) goto L30
            r5.setBackgroundDrawable(r1)
        L30:
            r0.n()
            r4.p = r5
            int r5 = android.os.Build.VERSION.SDK_INT
            r0 = 33
            if (r5 < r0) goto L64
            android.window.OnBackInvokedDispatcher r5 = r4.k0
            if (r5 != 0) goto L64
            if (r5 == 0) goto L4a
            androidx.appcompat.app.a r0 = r4.l0
            if (r0 == 0) goto L4a
            androidx.appcompat.app.AppCompatDelegateImpl.Api33Impl.c(r5, r0)
            r4.l0 = r2
        L4a:
            java.lang.Object r5 = r4.n
            boolean r0 = r5 instanceof android.app.Activity
            if (r0 == 0) goto L5f
            android.app.Activity r5 = (android.app.Activity) r5
            android.view.Window r0 = r5.getWindow()
            if (r0 == 0) goto L5f
            android.window.OnBackInvokedDispatcher r5 = androidx.appcompat.app.AppCompatDelegateImpl.Api33Impl.a(r5)
            r4.k0 = r5
            goto L61
        L5f:
            r4.k0 = r2
        L61:
            r4.d0()
        L64:
            return
        L65:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            r4.<init>(r1)
            throw r4
        L6b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            r4.<init>(r1)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.I(android.view.Window):void");
    }

    public final void K(int i, PanelFeatureState panelFeatureState, MenuBuilder menuBuilder) {
        if (menuBuilder == null) {
            if (panelFeatureState == null && i >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.Q;
                if (i < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i];
                }
            }
            if (panelFeatureState != null) {
                menuBuilder = panelFeatureState.h;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.m) && !this.V) {
            AppCompatWindowCallback appCompatWindowCallback = this.q;
            Window.Callback callback = this.p.getCallback();
            appCompatWindowCallback.getClass();
            try {
                appCompatWindowCallback.i = true;
                callback.onPanelClosed(i, menuBuilder);
            } finally {
                appCompatWindowCallback.i = false;
            }
        }
    }

    public final void L(MenuBuilder menuBuilder) {
        if (this.P) {
            return;
        }
        this.P = true;
        this.v.i();
        Window.Callback callbackV = V();
        if (callbackV != null && !this.V) {
            callbackV.onPanelClosed(108, menuBuilder);
        }
        this.P = false;
    }

    public final void M(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup viewGroup;
        DecorContentParent decorContentParent;
        if (z && panelFeatureState.a == 0 && (decorContentParent = this.v) != null && decorContentParent.a()) {
            L(panelFeatureState.h);
            return;
        }
        WindowManager windowManager = (WindowManager) this.o.getSystemService("window");
        if (windowManager != null && panelFeatureState.m && (viewGroup = panelFeatureState.e) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                K(panelFeatureState.a, panelFeatureState, null);
            }
        }
        panelFeatureState.k = false;
        panelFeatureState.l = false;
        panelFeatureState.m = false;
        panelFeatureState.f = null;
        panelFeatureState.n = true;
        if (this.R == panelFeatureState) {
            this.R = null;
        }
        if (panelFeatureState.a == 0) {
            d0();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean O(android.view.KeyEvent r7) {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.O(android.view.KeyEvent):boolean");
    }

    public final void P(int i) {
        PanelFeatureState panelFeatureStateU = U(i);
        if (panelFeatureStateU.h != null) {
            Bundle bundle = new Bundle();
            panelFeatureStateU.h.u(bundle);
            if (bundle.size() > 0) {
                panelFeatureStateU.p = bundle;
            }
            panelFeatureStateU.h.y();
            panelFeatureStateU.h.clear();
        }
        panelFeatureStateU.o = true;
        panelFeatureStateU.n = true;
        if ((i == 108 || i == 0) && this.v != null) {
            PanelFeatureState panelFeatureStateU2 = U(0);
            panelFeatureStateU2.k = false;
            b0(panelFeatureStateU2, null);
        }
    }

    public final void Q() {
        ViewGroup viewGroup;
        if (this.E) {
            return;
        }
        int[] iArr = androidx.appcompat.R.styleable.AppCompatTheme;
        Context context = this.o;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iArr);
        int i = androidx.appcompat.R.styleable.AppCompatTheme_windowActionBar;
        if (!typedArrayObtainStyledAttributes.hasValue(i)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AppCompatTheme_windowNoTitle, false)) {
            x(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(i, false)) {
            x(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            x(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            x(10);
        }
        this.N = typedArrayObtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        R();
        this.p.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        if (this.O) {
            viewGroup = this.M ? (ViewGroup) layoutInflaterFrom.inflate(androidx.appcompat.R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(androidx.appcompat.R.layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.N) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(androidx.appcompat.R.layout.abc_dialog_title_material, (ViewGroup) null);
            this.L = false;
            this.K = false;
        } else if (this.K) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(androidx.appcompat.R.attr.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new ContextThemeWrapper(context, typedValue.resourceId) : context).inflate(androidx.appcompat.R.layout.abc_screen_toolbar, (ViewGroup) null);
            DecorContentParent decorContentParent = (DecorContentParent) viewGroup.findViewById(androidx.appcompat.R.id.decor_content_parent);
            this.v = decorContentParent;
            decorContentParent.setWindowCallback(V());
            if (this.L) {
                this.v.h(109);
            }
            if (this.I) {
                this.v.h(2);
            }
            if (this.f1J) {
                this.v.h(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.K + ", windowActionBarOverlay: " + this.L + ", android:windowIsFloating: " + this.N + ", windowActionModeOverlay: " + this.M + ", windowNoTitle: " + this.O + " }");
        }
        wr1 wr1Var = new wr1() { // from class: androidx.appcompat.app.AppCompatDelegateImpl.3
            @Override // defpackage.wr1
            public final pt3 k(View view, pt3 pt3Var) {
                int iE = pt3Var.e();
                int iE0 = AppCompatDelegateImpl.this.e0(pt3Var, null);
                if (iE != iE0) {
                    pt3Var = pt3Var.g(pt3Var.c(), iE0, pt3Var.d(), pt3Var.b());
                }
                return om3.g(view, pt3Var);
            }
        };
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.i.m(viewGroup, wr1Var);
        if (this.v == null) {
            this.G = (TextView) viewGroup.findViewById(androidx.appcompat.R.id.title);
        }
        Method method = ViewUtils.a;
        try {
            Method method2 = viewGroup.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
            if (!method2.isAccessible()) {
                method2.setAccessible(true);
            }
            method2.invoke(viewGroup, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(androidx.appcompat.R.id.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.p.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.p.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new ContentFrameLayout.OnAttachListener() { // from class: androidx.appcompat.app.AppCompatDelegateImpl.5
            @Override // androidx.appcompat.widget.ContentFrameLayout.OnAttachListener
            public final void onDetachedFromWindow() {
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                DecorContentParent decorContentParent2 = appCompatDelegateImpl.v;
                if (decorContentParent2 != null) {
                    decorContentParent2.i();
                }
                if (appCompatDelegateImpl.A != null) {
                    appCompatDelegateImpl.p.getDecorView().removeCallbacks(appCompatDelegateImpl.B);
                    if (appCompatDelegateImpl.A.isShowing()) {
                        try {
                            appCompatDelegateImpl.A.dismiss();
                        } catch (IllegalArgumentException unused2) {
                        }
                    }
                    appCompatDelegateImpl.A = null;
                }
                in3 in3Var = appCompatDelegateImpl.C;
                if (in3Var != null) {
                    in3Var.b();
                }
                MenuBuilder menuBuilder = appCompatDelegateImpl.U(0).h;
                if (menuBuilder != null) {
                    menuBuilder.close();
                }
            }

            @Override // androidx.appcompat.widget.ContentFrameLayout.OnAttachListener
            public final void a() {
            }
        });
        this.F = viewGroup;
        Object obj = this.n;
        CharSequence title = obj instanceof Activity ? ((Activity) obj).getTitle() : this.u;
        if (!TextUtils.isEmpty(title)) {
            DecorContentParent decorContentParent2 = this.v;
            if (decorContentParent2 != null) {
                decorContentParent2.setWindowTitle(title);
            } else {
                ActionBar actionBar = this.s;
                if (actionBar != null) {
                    actionBar.x(title);
                } else {
                    TextView textView = this.G;
                    if (textView != null) {
                        textView.setText(title);
                    }
                }
            }
        }
        ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.F.findViewById(R.id.content);
        View decorView = this.p.getDecorView();
        contentFrameLayout2.k.set(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        WeakHashMap<View, in3> weakHashMap2 = om3.a;
        if (om3.g.c(contentFrameLayout2)) {
            contentFrameLayout2.requestLayout();
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(androidx.appcompat.R.styleable.AppCompatTheme);
        typedArrayObtainStyledAttributes2.getValue(androidx.appcompat.R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout2.getMinWidthMajor());
        typedArrayObtainStyledAttributes2.getValue(androidx.appcompat.R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout2.getMinWidthMinor());
        int i2 = androidx.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMajor;
        if (typedArrayObtainStyledAttributes2.hasValue(i2)) {
            typedArrayObtainStyledAttributes2.getValue(i2, contentFrameLayout2.getFixedWidthMajor());
        }
        int i3 = androidx.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMinor;
        if (typedArrayObtainStyledAttributes2.hasValue(i3)) {
            typedArrayObtainStyledAttributes2.getValue(i3, contentFrameLayout2.getFixedWidthMinor());
        }
        int i4 = androidx.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMajor;
        if (typedArrayObtainStyledAttributes2.hasValue(i4)) {
            typedArrayObtainStyledAttributes2.getValue(i4, contentFrameLayout2.getFixedHeightMajor());
        }
        int i5 = androidx.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMinor;
        if (typedArrayObtainStyledAttributes2.hasValue(i5)) {
            typedArrayObtainStyledAttributes2.getValue(i5, contentFrameLayout2.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes2.recycle();
        contentFrameLayout2.requestLayout();
        this.E = true;
        PanelFeatureState panelFeatureStateU = U(0);
        if (this.V || panelFeatureStateU.h != null) {
            return;
        }
        this.e0 |= 4096;
        if (this.d0) {
            return;
        }
        om3.d.k(this.p.getDecorView(), this.f0);
        this.d0 = true;
    }

    public final void R() {
        if (this.p == null) {
            Object obj = this.n;
            if (obj instanceof Activity) {
                I(((Activity) obj).getWindow());
            }
        }
        if (this.p == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public final AutoNightModeManager S(Context context) {
        if (this.b0 == null) {
            if (TwilightManager.d == null) {
                Context applicationContext = context.getApplicationContext();
                TwilightManager.d = new TwilightManager(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            }
            this.b0 = new AutoTimeNightModeManager(TwilightManager.d);
        }
        return this.b0;
    }

    public final PanelFeatureState U(int i) {
        PanelFeatureState[] panelFeatureStateArr = this.Q;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.Q = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState(i);
        panelFeatureStateArr[i] = panelFeatureState2;
        return panelFeatureState2;
    }

    public final Window.Callback V() {
        return this.p.getCallback();
    }

    public final void W() {
        Q();
        if (this.K && this.s == null) {
            Object obj = this.n;
            if (obj instanceof Activity) {
                this.s = new WindowDecorActionBar((Activity) obj, this.L);
            } else if (obj instanceof Dialog) {
                this.s = new WindowDecorActionBar((Dialog) obj);
            }
            ActionBar actionBar = this.s;
            if (actionBar != null) {
                actionBar.s(this.g0);
            }
        }
    }

    public final int X(Context context, int i) {
        if (i == -100) {
            return -1;
        }
        if (i != -1) {
            if (i == 0) {
                if (((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() == 0) {
                    return -1;
                }
                return S(context).c();
            }
            if (i != 1 && i != 2) {
                if (i != 3) {
                    throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                }
                if (this.c0 == null) {
                    this.c0 = new AutoBatteryNightModeManager(context);
                }
                return this.c0.c();
            }
        }
        return i;
    }

    public final boolean Y() {
        boolean z = this.S;
        this.S = false;
        PanelFeatureState panelFeatureStateU = U(0);
        if (panelFeatureStateU.m) {
            if (!z) {
                M(panelFeatureStateU, true);
            }
            return true;
        }
        androidx.appcompat.view.ActionMode actionMode = this.y;
        if (actionMode != null) {
            actionMode.c();
            return true;
        }
        W();
        ActionBar actionBar = this.s;
        return actionBar != null && actionBar.h();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:105:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0139  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Z(androidx.appcompat.app.AppCompatDelegateImpl.PanelFeatureState r18, android.view.KeyEvent r19) {
        /*
            Method dump skipped, instruction units count: 440
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.Z(androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState, android.view.KeyEvent):void");
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public final boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        int length;
        int i;
        PanelFeatureState panelFeatureState;
        Window.Callback callbackV = V();
        if (callbackV != null && !this.V) {
            MenuBuilder menuBuilderK = menuBuilder.k();
            PanelFeatureState[] panelFeatureStateArr = this.Q;
            if (panelFeatureStateArr != null) {
                length = panelFeatureStateArr.length;
                i = 0;
            } else {
                length = 0;
                i = 0;
            }
            while (true) {
                if (i < length) {
                    panelFeatureState = panelFeatureStateArr[i];
                    if (panelFeatureState != null && panelFeatureState.h == menuBuilderK) {
                        break;
                    }
                    i++;
                } else {
                    panelFeatureState = null;
                    break;
                }
            }
            if (panelFeatureState != null) {
                return callbackV.onMenuItemSelected(panelFeatureState.a, menuItem);
            }
        }
        return false;
    }

    public final boolean a0(PanelFeatureState panelFeatureState, int i, KeyEvent keyEvent) {
        MenuBuilder menuBuilder;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.k || b0(panelFeatureState, keyEvent)) && (menuBuilder = panelFeatureState.h) != null) {
            return menuBuilder.performShortcut(i, keyEvent, 1);
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public final void b(MenuBuilder menuBuilder) {
        DecorContentParent decorContentParent = this.v;
        if (decorContentParent == null || !decorContentParent.c() || (ViewConfiguration.get(this.o).hasPermanentMenuKey() && !this.v.e())) {
            PanelFeatureState panelFeatureStateU = U(0);
            panelFeatureStateU.n = true;
            M(panelFeatureStateU, false);
            Z(panelFeatureStateU, null);
            return;
        }
        Window.Callback callbackV = V();
        if (this.v.a()) {
            this.v.f();
            if (this.V) {
                return;
            }
            callbackV.onPanelClosed(108, U(0).h);
            return;
        }
        if (callbackV == null || this.V) {
            return;
        }
        if (this.d0 && (1 & this.e0) != 0) {
            View decorView = this.p.getDecorView();
            Runnable runnable = this.f0;
            decorView.removeCallbacks(runnable);
            ((AnonymousClass2) runnable).run();
        }
        PanelFeatureState panelFeatureStateU2 = U(0);
        MenuBuilder menuBuilder2 = panelFeatureStateU2.h;
        if (menuBuilder2 == null || panelFeatureStateU2.o || !callbackV.onPreparePanel(0, panelFeatureStateU2.g, menuBuilder2)) {
            return;
        }
        callbackV.onMenuOpened(108, panelFeatureStateU2.h);
        this.v.g();
    }

    public final boolean b0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        DecorContentParent decorContentParent;
        DecorContentParent decorContentParent2;
        Resources.Theme themeNewTheme;
        DecorContentParent decorContentParent3;
        DecorContentParent decorContentParent4;
        if (this.V) {
            return false;
        }
        if (panelFeatureState.k) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.R;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            M(panelFeatureState2, false);
        }
        Window.Callback callbackV = V();
        int i = panelFeatureState.a;
        if (callbackV != null) {
            panelFeatureState.g = callbackV.onCreatePanelView(i);
        }
        boolean z = i == 0 || i == 108;
        if (z && (decorContentParent4 = this.v) != null) {
            decorContentParent4.b();
        }
        if (panelFeatureState.g == null && (!z || !(this.s instanceof ToolbarActionBar))) {
            MenuBuilder menuBuilder = panelFeatureState.h;
            if (menuBuilder == null || panelFeatureState.o) {
                if (menuBuilder == null) {
                    Context context = this.o;
                    if ((i == 0 || i == 108) && this.v != null) {
                        TypedValue typedValue = new TypedValue();
                        Resources.Theme theme = context.getTheme();
                        theme.resolveAttribute(androidx.appcompat.R.attr.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            themeNewTheme = context.getResources().newTheme();
                            themeNewTheme.setTo(theme);
                            themeNewTheme.applyStyle(typedValue.resourceId, true);
                            themeNewTheme.resolveAttribute(androidx.appcompat.R.attr.actionBarWidgetTheme, typedValue, true);
                        } else {
                            theme.resolveAttribute(androidx.appcompat.R.attr.actionBarWidgetTheme, typedValue, true);
                            themeNewTheme = null;
                        }
                        if (typedValue.resourceId != 0) {
                            if (themeNewTheme == null) {
                                themeNewTheme = context.getResources().newTheme();
                                themeNewTheme.setTo(theme);
                            }
                            themeNewTheme.applyStyle(typedValue.resourceId, true);
                        }
                        if (themeNewTheme != null) {
                            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 0);
                            contextThemeWrapper.getTheme().setTo(themeNewTheme);
                            context = contextThemeWrapper;
                        }
                    }
                    MenuBuilder menuBuilder2 = new MenuBuilder(context);
                    menuBuilder2.e = this;
                    MenuBuilder menuBuilder3 = panelFeatureState.h;
                    if (menuBuilder2 != menuBuilder3) {
                        if (menuBuilder3 != null) {
                            menuBuilder3.r(panelFeatureState.i);
                        }
                        panelFeatureState.h = menuBuilder2;
                        ListMenuPresenter listMenuPresenter = panelFeatureState.i;
                        if (listMenuPresenter != null) {
                            menuBuilder2.b(listMenuPresenter, menuBuilder2.a);
                        }
                    }
                    if (panelFeatureState.h == null) {
                        return false;
                    }
                }
                if (z && (decorContentParent2 = this.v) != null) {
                    if (this.w == null) {
                        this.w = new ActionMenuPresenterCallback();
                    }
                    decorContentParent2.d(panelFeatureState.h, this.w);
                }
                panelFeatureState.h.y();
                if (!callbackV.onCreatePanelMenu(i, panelFeatureState.h)) {
                    MenuBuilder menuBuilder4 = panelFeatureState.h;
                    if (menuBuilder4 != null) {
                        if (menuBuilder4 != null) {
                            menuBuilder4.r(panelFeatureState.i);
                        }
                        panelFeatureState.h = null;
                    }
                    if (z && (decorContentParent = this.v) != null) {
                        decorContentParent.d(null, this.w);
                    }
                    return false;
                }
                panelFeatureState.o = false;
            }
            panelFeatureState.h.y();
            Bundle bundle = panelFeatureState.p;
            if (bundle != null) {
                panelFeatureState.h.s(bundle);
                panelFeatureState.p = null;
            }
            if (!callbackV.onPreparePanel(0, panelFeatureState.g, panelFeatureState.h)) {
                if (z && (decorContentParent3 = this.v) != null) {
                    decorContentParent3.d(null, this.w);
                }
                panelFeatureState.h.x();
                return false;
            }
            panelFeatureState.h.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
            panelFeatureState.h.x();
        }
        panelFeatureState.k = true;
        panelFeatureState.l = false;
        this.R = panelFeatureState;
        return true;
    }

    public final void c0() {
        if (this.E) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void d(View view, ViewGroup.LayoutParams layoutParams) {
        Q();
        ((ViewGroup) this.F.findViewById(R.id.content)).addView(view, layoutParams);
        this.q.a(this.p.getCallback());
    }

    public final void d0() {
        a aVar;
        if (Build.VERSION.SDK_INT >= 33) {
            boolean z = false;
            if (this.k0 != null && (U(0).m || this.y != null)) {
                z = true;
            }
            if (z && this.l0 == null) {
                this.l0 = Api33Impl.b(this.k0, this);
            } else {
                if (z || (aVar = this.l0) == null) {
                    return;
                }
                Api33Impl.c(this.k0, aVar);
                this.l0 = null;
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final boolean e() {
        return H(true, true);
    }

    public final int e0(pt3 pt3Var, Rect rect) {
        boolean z;
        boolean z2;
        int color;
        int iE = pt3Var != null ? pt3Var.e() : rect != null ? rect.top : 0;
        ActionBarContextView actionBarContextView = this.z;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.z.getLayoutParams();
            if (this.z.isShown()) {
                if (this.h0 == null) {
                    this.h0 = new Rect();
                    this.i0 = new Rect();
                }
                Rect rect2 = this.h0;
                Rect rect3 = this.i0;
                if (pt3Var == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(pt3Var.c(), pt3Var.e(), pt3Var.d(), pt3Var.b());
                }
                ViewGroup viewGroup = this.F;
                Method method = ViewUtils.a;
                if (method != null) {
                    try {
                        method.invoke(viewGroup, rect2, rect3);
                    } catch (Exception unused) {
                    }
                }
                int i = rect2.top;
                int i2 = rect2.left;
                int i3 = rect2.right;
                ViewGroup viewGroup2 = this.F;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                pt3 pt3VarA = om3.j.a(viewGroup2);
                int iC = pt3VarA == null ? 0 : pt3VarA.c();
                int iD = pt3VarA == null ? 0 : pt3VarA.d();
                if (marginLayoutParams.topMargin == i && marginLayoutParams.leftMargin == i2 && marginLayoutParams.rightMargin == i3) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = i;
                    marginLayoutParams.leftMargin = i2;
                    marginLayoutParams.rightMargin = i3;
                    z2 = true;
                }
                Context context = this.o;
                if (i <= 0 || this.H != null) {
                    View view = this.H;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        int i4 = marginLayoutParams2.height;
                        int i5 = marginLayoutParams.topMargin;
                        if (i4 != i5 || marginLayoutParams2.leftMargin != iC || marginLayoutParams2.rightMargin != iD) {
                            marginLayoutParams2.height = i5;
                            marginLayoutParams2.leftMargin = iC;
                            marginLayoutParams2.rightMargin = iD;
                            this.H.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(context);
                    this.H = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = iC;
                    layoutParams.rightMargin = iD;
                    this.F.addView(this.H, -1, layoutParams);
                }
                View view3 = this.H;
                z = view3 != null;
                if (z && view3.getVisibility() != 0) {
                    View view4 = this.H;
                    if ((om3.d.f(view4) & 8192) != 0) {
                        int i6 = androidx.appcompat.R.color.abc_decor_view_status_guard_light;
                        Object obj = bz.a;
                        color = context.getColor(i6);
                    } else {
                        int i7 = androidx.appcompat.R.color.abc_decor_view_status_guard;
                        Object obj2 = bz.a;
                        color = context.getColor(i7);
                    }
                    view4.setBackgroundColor(color);
                }
                if (!this.M && z) {
                    iE = 0;
                }
                z = z2;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z = false;
            } else {
                z = false;
                z = false;
            }
            if (z) {
                this.z.setLayoutParams(marginLayoutParams);
            }
        }
        View view5 = this.H;
        if (view5 != null) {
            view5.setVisibility(z ? 0 : 8);
        }
        return iE;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final Context f(Context context) {
        this.T = true;
        int i = this.X;
        if (i == -100) {
            i = AppCompatDelegate.f;
        }
        int iX = X(context, i);
        if (AppCompatDelegate.n(context) && AppCompatDelegate.n(context)) {
            if (Build.VERSION.SDK_INT < 33) {
                synchronized (AppCompatDelegate.m) {
                    o91 o91Var = AppCompatDelegate.g;
                    if (o91Var == null) {
                        if (AppCompatDelegate.h == null) {
                            AppCompatDelegate.h = o91.a(AppLocalesStorageHelper.b(context));
                        }
                        if (!AppCompatDelegate.h.a.isEmpty()) {
                            AppCompatDelegate.g = AppCompatDelegate.h;
                        }
                    } else if (!o91Var.equals(AppCompatDelegate.h)) {
                        o91 o91Var2 = AppCompatDelegate.g;
                        AppCompatDelegate.h = o91Var2;
                        AppLocalesStorageHelper.a(context, o91Var2.a.a());
                    }
                }
            } else if (!AppCompatDelegate.j) {
                AppCompatDelegate.e.execute(new wq0(i, context));
            }
        }
        o91 o91VarJ = J(context);
        int i2 = 0;
        Configuration configuration = null;
        if (p0 && (context instanceof android.view.ContextThemeWrapper)) {
            try {
                ((android.view.ContextThemeWrapper) context).applyOverrideConfiguration(N(context, iX, o91VarJ, null, false));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof ContextThemeWrapper) {
            try {
                ((ContextThemeWrapper) context).a(N(context, iX, o91VarJ, null, false));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!o0) {
            return context;
        }
        Configuration configuration2 = new Configuration();
        configuration2.uiMode = -1;
        configuration2.fontScale = 0.0f;
        Configuration configuration3 = context.createConfigurationContext(configuration2).getResources().getConfiguration();
        Configuration configuration4 = context.getResources().getConfiguration();
        configuration3.uiMode = configuration4.uiMode;
        if (!configuration3.equals(configuration4)) {
            configuration = new Configuration();
            configuration.fontScale = 0.0f;
            if (configuration3.diff(configuration4) != 0) {
                float f = configuration3.fontScale;
                float f2 = configuration4.fontScale;
                if (f != f2) {
                    configuration.fontScale = f2;
                }
                int i3 = configuration3.mcc;
                int i4 = configuration4.mcc;
                if (i3 != i4) {
                    configuration.mcc = i4;
                }
                int i5 = configuration3.mnc;
                int i6 = configuration4.mnc;
                if (i5 != i6) {
                    configuration.mnc = i6;
                }
                LocaleList locales = configuration3.getLocales();
                LocaleList locales2 = configuration4.getLocales();
                if (!locales.equals(locales2)) {
                    configuration.setLocales(locales2);
                    configuration.locale = configuration4.locale;
                }
                int i7 = configuration3.touchscreen;
                int i8 = configuration4.touchscreen;
                if (i7 != i8) {
                    configuration.touchscreen = i8;
                }
                int i9 = configuration3.keyboard;
                int i10 = configuration4.keyboard;
                if (i9 != i10) {
                    configuration.keyboard = i10;
                }
                int i11 = configuration3.keyboardHidden;
                int i12 = configuration4.keyboardHidden;
                if (i11 != i12) {
                    configuration.keyboardHidden = i12;
                }
                int i13 = configuration3.navigation;
                int i14 = configuration4.navigation;
                if (i13 != i14) {
                    configuration.navigation = i14;
                }
                int i15 = configuration3.navigationHidden;
                int i16 = configuration4.navigationHidden;
                if (i15 != i16) {
                    configuration.navigationHidden = i16;
                }
                int i17 = configuration3.orientation;
                int i18 = configuration4.orientation;
                if (i17 != i18) {
                    configuration.orientation = i18;
                }
                int i19 = configuration3.screenLayout & 15;
                int i20 = configuration4.screenLayout & 15;
                if (i19 != i20) {
                    configuration.screenLayout |= i20;
                }
                int i21 = configuration3.screenLayout & 192;
                int i22 = configuration4.screenLayout & 192;
                if (i21 != i22) {
                    configuration.screenLayout |= i22;
                }
                int i23 = configuration3.screenLayout & 48;
                int i24 = configuration4.screenLayout & 48;
                if (i23 != i24) {
                    configuration.screenLayout |= i24;
                }
                int i25 = configuration3.screenLayout & 768;
                int i26 = configuration4.screenLayout & 768;
                if (i25 != i26) {
                    configuration.screenLayout |= i26;
                }
                int i27 = configuration3.colorMode & 3;
                int i28 = configuration4.colorMode & 3;
                if (i27 != i28) {
                    configuration.colorMode |= i28;
                }
                int i29 = configuration3.colorMode & 12;
                int i30 = configuration4.colorMode & 12;
                if (i29 != i30) {
                    configuration.colorMode |= i30;
                }
                int i31 = configuration3.uiMode & 15;
                int i32 = configuration4.uiMode & 15;
                if (i31 != i32) {
                    configuration.uiMode |= i32;
                }
                int i33 = configuration3.uiMode & 48;
                int i34 = configuration4.uiMode & 48;
                if (i33 != i34) {
                    configuration.uiMode |= i34;
                }
                int i35 = configuration3.screenWidthDp;
                int i36 = configuration4.screenWidthDp;
                if (i35 != i36) {
                    configuration.screenWidthDp = i36;
                }
                int i37 = configuration3.screenHeightDp;
                int i38 = configuration4.screenHeightDp;
                if (i37 != i38) {
                    configuration.screenHeightDp = i38;
                }
                int i39 = configuration3.smallestScreenWidthDp;
                int i40 = configuration4.smallestScreenWidthDp;
                if (i39 != i40) {
                    configuration.smallestScreenWidthDp = i40;
                }
                int i41 = configuration3.densityDpi;
                int i42 = configuration4.densityDpi;
                if (i41 != i42) {
                    configuration.densityDpi = i42;
                }
            }
        }
        Configuration configurationN = N(context, iX, o91VarJ, configuration, true);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, androidx.appcompat.R.style.Theme_AppCompat_Empty);
        contextThemeWrapper.a(configurationN);
        try {
            i2 = context.getTheme() == null ? 0 : 1;
        } catch (NullPointerException unused3) {
        }
        if (i2 != 0) {
            contextThemeWrapper.getTheme().rebase();
        }
        return contextThemeWrapper;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final <T extends View> T g(int i) {
        Q();
        return (T) this.p.findViewById(i);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final Context h() {
        return this.o;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final int i() {
        return this.X;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final MenuInflater j() {
        if (this.t == null) {
            W();
            ActionBar actionBar = this.s;
            this.t = new SupportMenuInflater(actionBar != null ? actionBar.l() : this.o);
        }
        return this.t;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final ActionBar k() {
        W();
        return this.s;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void l() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.o);
        if (layoutInflaterFrom.getFactory() == null) {
            layoutInflaterFrom.setFactory2(this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof AppCompatDelegateImpl) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void m() {
        if (this.s != null) {
            W();
            if (this.s.m()) {
                return;
            }
            this.e0 |= 1;
            if (this.d0) {
                return;
            }
            View decorView = this.p.getDecorView();
            Runnable runnable = this.f0;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.k(decorView, runnable);
            this.d0 = true;
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void o(Configuration configuration) {
        if (this.K && this.E) {
            W();
            ActionBar actionBar = this.s;
            if (actionBar != null) {
                actionBar.n();
            }
        }
        AppCompatDrawableManager appCompatDrawableManagerA = AppCompatDrawableManager.a();
        Context context = this.o;
        synchronized (appCompatDrawableManagerA) {
            ResourceManagerInternal resourceManagerInternal = appCompatDrawableManagerA.a;
            synchronized (resourceManagerInternal) {
                sa1<WeakReference<Drawable.ConstantState>> sa1Var = resourceManagerInternal.b.get(context);
                if (sa1Var != null) {
                    sa1Var.b();
                }
            }
        }
        this.W = new Configuration(this.o.getResources().getConfiguration());
        H(false, false);
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (this.j0 == null) {
            int[] iArr = androidx.appcompat.R.styleable.AppCompatTheme;
            Context context2 = this.o;
            String string = context2.obtainStyledAttributes(iArr).getString(androidx.appcompat.R.styleable.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.j0 = new AppCompatViewInflater();
            } else {
                try {
                    this.j0 = (AppCompatViewInflater) context2.getClassLoader().loadClass(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.j0 = new AppCompatViewInflater();
                }
            }
        }
        AppCompatViewInflater appCompatViewInflater = this.j0;
        int i = VectorEnabledTintResources.a;
        return appCompatViewInflater.f(view, str, context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void p() {
        String strC;
        this.T = true;
        H(false, true);
        R();
        Object obj = this.n;
        if (obj instanceof Activity) {
            try {
                Activity activity = (Activity) obj;
                try {
                    strC = rk1.c(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e) {
                    throw new IllegalArgumentException(e);
                }
            } catch (IllegalArgumentException unused) {
                strC = null;
            }
            if (strC != null) {
                ActionBar actionBar = this.s;
                if (actionBar == null) {
                    this.g0 = true;
                } else {
                    actionBar.s(true);
                }
            }
            synchronized (AppCompatDelegate.l) {
                AppCompatDelegate.w(this);
                AppCompatDelegate.k.add(new WeakReference<>(this));
            }
        }
        this.W = new Configuration(this.o.getResources().getConfiguration());
        this.U = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    @Override // androidx.appcompat.app.AppCompatDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void q() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.n
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L11
            java.lang.Object r0 = androidx.appcompat.app.AppCompatDelegate.l
            monitor-enter(r0)
            androidx.appcompat.app.AppCompatDelegate.w(r3)     // Catch: java.lang.Throwable -> Le
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            goto L11
        Le:
            r3 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            throw r3
        L11:
            boolean r0 = r3.d0
            if (r0 == 0) goto L20
            android.view.Window r0 = r3.p
            android.view.View r0 = r0.getDecorView()
            java.lang.Runnable r1 = r3.f0
            r0.removeCallbacks(r1)
        L20:
            r0 = 1
            r3.V = r0
            int r0 = r3.X
            r1 = -100
            if (r0 == r1) goto L4d
            java.lang.Object r0 = r3.n
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L4d
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L4d
            wm2<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.m0
            java.lang.Object r1 = r3.n
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.X
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L5c
        L4d:
            wm2<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.m0
            java.lang.Object r1 = r3.n
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L5c:
            androidx.appcompat.app.ActionBar r0 = r3.s
            if (r0 == 0) goto L63
            r0.o()
        L63:
            androidx.appcompat.app.AppCompatDelegateImpl$AutoTimeNightModeManager r0 = r3.b0
            if (r0 == 0) goto L6a
            r0.a()
        L6a:
            androidx.appcompat.app.AppCompatDelegateImpl$AutoBatteryNightModeManager r3 = r3.c0
            if (r3 == 0) goto L71
            r3.a()
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.q():void");
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void r() {
        Q();
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void s() {
        W();
        ActionBar actionBar = this.s;
        if (actionBar != null) {
            actionBar.v(true);
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void u() {
        H(true, false);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void v() {
        W();
        ActionBar actionBar = this.s;
        if (actionBar != null) {
            actionBar.v(false);
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final boolean x(int i) {
        if (i == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            i = 108;
        } else if (i == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            i = 109;
        }
        if (this.O && i == 108) {
            return false;
        }
        if (this.K && i == 1) {
            this.K = false;
        }
        if (i == 1) {
            c0();
            this.O = true;
            return true;
        }
        if (i == 2) {
            c0();
            this.I = true;
            return true;
        }
        if (i == 5) {
            c0();
            this.f1J = true;
            return true;
        }
        if (i == 10) {
            c0();
            this.M = true;
            return true;
        }
        if (i == 108) {
            c0();
            this.K = true;
            return true;
        }
        if (i != 109) {
            return this.p.requestFeature(i);
        }
        c0();
        this.L = true;
        return true;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void y(int i) {
        Q();
        ViewGroup viewGroup = (ViewGroup) this.F.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.o).inflate(i, viewGroup);
        this.q.a(this.p.getCallback());
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void z(View view) {
        Q();
        ViewGroup viewGroup = (ViewGroup) this.F.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.q.a(this.p.getCallback());
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void t() {
    }
}
