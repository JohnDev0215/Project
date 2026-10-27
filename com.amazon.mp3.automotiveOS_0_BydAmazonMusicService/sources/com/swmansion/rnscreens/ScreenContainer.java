package com.swmansion.rnscreens;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.modules.core.ReactChoreographer;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.ScreenFragment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScreenContainer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003B\u0011\u0012\b\u0010M\u001a\u0004\u0018\u00010L¢\u0006\u0004\bN\u0010OJ\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0002J\b\u0010\u000b\u001a\u00020\u0006H\u0002J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0001H\u0002J\u0018\u0010\u0010\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0001H\u0002J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000e\u001a\u00020\u0001H\u0002J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\b\u0010\u0015\u001a\u00020\u0006H\u0002J0\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018H\u0014J\u0010\u0010 \u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u001eH\u0016J\b\u0010!\u001a\u00020\u0006H\u0016J\u0006\u0010\"\u001a\u00020\u0006J\u0017\u0010%\u001a\u00028\u00002\u0006\u0010$\u001a\u00020#H\u0014¢\u0006\u0004\b%\u0010&J\u0016\u0010(\u001a\u00020\u00062\u0006\u0010$\u001a\u00020#2\u0006\u0010'\u001a\u00020\u0018J\u0010\u0010)\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u0018H\u0016J\b\u0010*\u001a\u00020\u0006H\u0016J\u000e\u0010+\u001a\u00020#2\u0006\u0010'\u001a\u00020\u0018J\b\u0010,\u001a\u00020\fH\u0004J\u0012\u0010-\u001a\u00020\u00162\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0016J\b\u0010.\u001a\u00020\u0006H\u0014J\b\u0010/\u001a\u00020\u0006H\u0014J\u0018\u00102\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u00182\u0006\u00101\u001a\u00020\u0018H\u0014J\b\u00103\u001a\u00020\u0006H\u0004J\u0006\u00104\u001a\u00020\u0006J\b\u00105\u001a\u00020\u0006H\u0016J\b\u00106\u001a\u00020\u0006H\u0014R$\u00109\u001a\u0012\u0012\u0004\u0012\u00028\u000007j\b\u0012\u0004\u0012\u00028\u0000`88\u0004X\u0085\u0004¢\u0006\u0006\n\u0004\b9\u0010:R\u0018\u0010;\u001a\u0004\u0018\u00010\u00048\u0004@\u0004X\u0085\u000e¢\u0006\u0006\n\u0004\b;\u0010<R\u0016\u0010=\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u0010>R\u0016\u0010?\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b?\u0010>R\u0016\u0010@\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010>R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\u0018\u0010D\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u0010ER\u0011\u0010H\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\bF\u0010GR\u0016\u0010K\u001a\u0004\u0018\u00010#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bI\u0010J¨\u0006P"}, d2 = {"Lcom/swmansion/rnscreens/ScreenContainer;", "Lcom/swmansion/rnscreens/ScreenFragment;", "T", "Landroid/view/ViewGroup;", "Landroidx/fragment/app/FragmentManager;", "fm", "", "setFragmentManager", "Lcom/facebook/react/ReactRootView;", "rootView", "findFragmentManagerForReactRootView", "setupFragmentManager", "Landroidx/fragment/app/FragmentTransaction;", "transaction", "screenFragment", "attachScreen", "detachScreen", "Lcom/swmansion/rnscreens/Screen$ActivityState;", "getActivityState", "fragmentManager", "removeMyFragments", "onScreenChanged", "", "changed", "", "l", "t", "r", "b", "onLayout", "Landroid/view/View;", "view", "removeView", "requestLayout", "notifyChildUpdate", "Lcom/swmansion/rnscreens/Screen;", "screen", "adapt", "(Lcom/swmansion/rnscreens/Screen;)Lcom/swmansion/rnscreens/ScreenFragment;", "index", "addScreen", "removeScreenAt", "removeAllScreens", "getScreenAt", "createTransaction", "hasScreen", "onAttachedToWindow", "onDetachedFromWindow", "widthMeasureSpec", "heightMeasureSpec", "onMeasure", "performUpdatesNow", "performUpdates", "onUpdate", "notifyContainerUpdate", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "mScreenFragments", "Ljava/util/ArrayList;", "mFragmentManager", "Landroidx/fragment/app/FragmentManager;", "mIsAttached", "Z", "mNeedUpdate", "mLayoutEnqueued", "Lcom/facebook/react/modules/core/ChoreographerCompat$FrameCallback;", "mLayoutCallback", "Lcom/facebook/react/modules/core/ChoreographerCompat$FrameCallback;", "mParentScreenFragment", "Lcom/swmansion/rnscreens/ScreenFragment;", "getScreenCount", "()I", "screenCount", "getTopScreen", "()Lcom/swmansion/rnscreens/Screen;", "topScreen", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "react-native-screens_release"}, k = 1, mv = {1, 6, 0})
public class ScreenContainer<T extends ScreenFragment> extends ViewGroup {

    @JvmField
    protected FragmentManager mFragmentManager;
    private boolean mIsAttached;
    private final ChoreographerCompat.FrameCallback mLayoutCallback;
    private boolean mLayoutEnqueued;
    private boolean mNeedUpdate;
    private ScreenFragment mParentScreenFragment;

    @JvmField
    protected final ArrayList<T> mScreenFragments;

    public ScreenContainer(Context context) {
        super(context);
        this.mScreenFragments = new ArrayList<>();
        this.mLayoutCallback = new ChoreographerCompat.FrameCallback(this) { // from class: com.swmansion.rnscreens.ScreenContainer$mLayoutCallback$1
            final /* synthetic */ ScreenContainer<T> this$0;

            {
                this.this$0 = this;
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
            public void doFrame(long frameTimeNanos) {
                ((ScreenContainer) this.this$0).mLayoutEnqueued = false;
                View view = this.this$0;
                view.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.this$0.getHeight(), 1073741824));
                View view2 = this.this$0;
                view2.layout(view2.getLeft(), this.this$0.getTop(), this.this$0.getRight(), this.this$0.getBottom());
            }
        };
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).layout(0, 0, getWidth(), getHeight());
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view == getFocusedChild()) {
            Object systemService = getContext().getSystemService("input_method");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            }
            ((InputMethodManager) systemService).hideSoftInputFromWindow(getWindowToken(), 2);
        }
        super.removeView(view);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        if (this.mLayoutEnqueued || this.mLayoutCallback == null) {
            return;
        }
        this.mLayoutEnqueued = true;
        ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.mLayoutCallback);
    }

    public final void notifyChildUpdate() {
        performUpdatesNow();
    }

    protected T adapt(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        return (T) new ScreenFragment(screen);
    }

    public final void addScreen(Screen screen, int index) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        ScreenFragment screenFragmentAdapt = adapt(screen);
        screen.setFragment(screenFragmentAdapt);
        this.mScreenFragments.add(index, (T) screenFragmentAdapt);
        screen.setContainer(this);
        onScreenChanged();
    }

    public void removeScreenAt(int index) {
        this.mScreenFragments.get(index).getScreen().setContainer(null);
        this.mScreenFragments.remove(index);
        onScreenChanged();
    }

    public void removeAllScreens() {
        Iterator<T> it = this.mScreenFragments.iterator();
        while (it.hasNext()) {
            it.next().getScreen().setContainer(null);
        }
        this.mScreenFragments.clear();
        onScreenChanged();
    }

    public final int getScreenCount() {
        return this.mScreenFragments.size();
    }

    public final Screen getScreenAt(int index) {
        return this.mScreenFragments.get(index).getScreen();
    }

    public Screen getTopScreen() {
        Object next;
        Iterator<T> it = this.mScreenFragments.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (getActivityState((ScreenFragment) next) == Screen.ActivityState.ON_TOP) {
                break;
            }
        }
        ScreenFragment screenFragment = (ScreenFragment) next;
        if (screenFragment == null) {
            return null;
        }
        return screenFragment.getScreen();
    }

    private final void setFragmentManager(FragmentManager fm) {
        this.mFragmentManager = fm;
        performUpdatesNow();
    }

    private final FragmentManager findFragmentManagerForReactRootView(ReactRootView rootView) {
        boolean z;
        FragmentManager supportFragmentManager;
        Context context = rootView.getContext();
        while (true) {
            z = context instanceof FragmentActivity;
            if (z || !(context instanceof ContextWrapper)) {
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (!z) {
            throw new IllegalStateException("In order to use RNScreens components your app's activity need to extend ReactActivity".toString());
        }
        FragmentActivity fragmentActivity = (FragmentActivity) context;
        if (fragmentActivity.getSupportFragmentManager().getFragments().isEmpty()) {
            FragmentManager supportFragmentManager2 = fragmentActivity.getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager2, "{\n            // We are …FragmentManager\n        }");
            return supportFragmentManager2;
        }
        try {
            supportFragmentManager = FragmentManager.findFragment(rootView).getChildFragmentManager();
        } catch (IllegalStateException unused) {
            supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        }
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "{\n            // We are …r\n            }\n        }");
        return supportFragmentManager;
    }

    private final void setupFragmentManager() {
        boolean z;
        Unit unit;
        ViewParent parent = this;
        while (true) {
            z = parent instanceof ReactRootView;
            if (z || (parent instanceof Screen) || parent.getParent() == null) {
                break;
            }
            parent = parent.getParent();
            Intrinsics.checkNotNullExpressionValue(parent, "parent.parent");
        }
        if (!(parent instanceof Screen)) {
            if (!z) {
                throw new IllegalStateException("ScreenContainer is not attached under ReactRootView".toString());
            }
            setFragmentManager(findFragmentManagerForReactRootView((ReactRootView) parent));
            return;
        }
        ScreenFragment fragment = ((Screen) parent).getFragment();
        if (fragment == null) {
            unit = null;
        } else {
            this.mParentScreenFragment = fragment;
            fragment.registerChildScreenContainer(this);
            FragmentManager childFragmentManager = fragment.getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "screenFragment.childFragmentManager");
            setFragmentManager(childFragmentManager);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            throw new IllegalStateException("Parent Screen does not have its Fragment attached".toString());
        }
    }

    protected final FragmentTransaction createTransaction() {
        FragmentManager fragmentManager = this.mFragmentManager;
        if (fragmentManager == null) {
            throw new IllegalArgumentException("mFragmentManager is null when creating transaction".toString());
        }
        FragmentTransaction reorderingAllowed = fragmentManager.beginTransaction().setReorderingAllowed(true);
        Intrinsics.checkNotNullExpressionValue(reorderingAllowed, "requireNotNull(mFragment…etReorderingAllowed(true)");
        return reorderingAllowed;
    }

    private final void attachScreen(FragmentTransaction transaction, ScreenFragment screenFragment) {
        transaction.add(getId(), screenFragment);
    }

    private final void detachScreen(FragmentTransaction transaction, ScreenFragment screenFragment) {
        transaction.remove(screenFragment);
    }

    private final Screen.ActivityState getActivityState(ScreenFragment screenFragment) {
        return screenFragment.getScreen().getActivityState();
    }

    public boolean hasScreen(ScreenFragment screenFragment) {
        return CollectionsKt___CollectionsKt.contains(this.mScreenFragments, screenFragment);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsAttached = true;
        setupFragmentManager();
    }

    private final void removeMyFragments(FragmentManager fragmentManager) {
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "fragmentManager.beginTransaction()");
        boolean z = false;
        for (Fragment fragment : fragmentManager.getFragments()) {
            if ((fragment instanceof ScreenFragment) && ((ScreenFragment) fragment).getScreen().getContainer() == this) {
                fragmentTransactionBeginTransaction.remove(fragment);
                z = true;
            }
        }
        if (z) {
            fragmentTransactionBeginTransaction.commitNowAllowingStateLoss();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        FragmentManager fragmentManager = this.mFragmentManager;
        if (fragmentManager != null && !fragmentManager.isDestroyed()) {
            removeMyFragments(fragmentManager);
            fragmentManager.executePendingTransactions();
        }
        ScreenFragment screenFragment = this.mParentScreenFragment;
        if (screenFragment != null) {
            screenFragment.unregisterChildScreenContainer(this);
        }
        this.mParentScreenFragment = null;
        super.onDetachedFromWindow();
        this.mIsAttached = false;
        int childCount = getChildCount() - 1;
        if (childCount < 0) {
            return;
        }
        while (true) {
            int i = childCount - 1;
            removeViewAt(childCount);
            if (i < 0) {
                return;
            } else {
                childCount = i;
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private final void onScreenChanged() {
        this.mNeedUpdate = true;
        Context context = getContext();
        ReactContext reactContext = context instanceof ReactContext ? (ReactContext) context : null;
        if (reactContext == null) {
            return;
        }
        reactContext.runOnUiQueueThread(new Runnable() { // from class: com.swmansion.rnscreens.ScreenContainer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ScreenContainer.m2762onScreenChanged$lambda7(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onScreenChanged$lambda-7, reason: not valid java name */
    public static final void m2762onScreenChanged$lambda7(ScreenContainer this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.performUpdates();
    }

    protected final void performUpdatesNow() {
        this.mNeedUpdate = true;
        performUpdates();
    }

    public final void performUpdates() {
        FragmentManager fragmentManager;
        if (this.mNeedUpdate && this.mIsAttached && (fragmentManager = this.mFragmentManager) != null) {
            if (fragmentManager != null && fragmentManager.isDestroyed()) {
                return;
            }
            this.mNeedUpdate = false;
            onUpdate();
            notifyContainerUpdate();
        }
    }

    public void onUpdate() {
        FragmentTransaction fragmentTransactionCreateTransaction = createTransaction();
        FragmentManager fragmentManager = this.mFragmentManager;
        if (fragmentManager != null) {
            HashSet hashSet = new HashSet(fragmentManager.getFragments());
            for (T screenFragment : this.mScreenFragments) {
                Intrinsics.checkNotNullExpressionValue(screenFragment, "screenFragment");
                if (getActivityState(screenFragment) == Screen.ActivityState.INACTIVE && screenFragment.isAdded()) {
                    detachScreen(fragmentTransactionCreateTransaction, screenFragment);
                }
                hashSet.remove(screenFragment);
            }
            boolean z = false;
            if (!hashSet.isEmpty()) {
                Object[] array = hashSet.toArray(new Fragment[0]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                }
                Fragment[] fragmentArr = (Fragment[]) array;
                int length = fragmentArr.length;
                int i = 0;
                while (i < length) {
                    Fragment fragment = fragmentArr[i];
                    i++;
                    if (fragment instanceof ScreenFragment) {
                        ScreenFragment screenFragment2 = (ScreenFragment) fragment;
                        if (screenFragment2.getScreen().getContainer() == null) {
                            detachScreen(fragmentTransactionCreateTransaction, screenFragment2);
                        }
                    }
                }
            }
            boolean z2 = getTopScreen() == null;
            ArrayList<ScreenFragment> arrayList = new ArrayList();
            for (T screenFragment3 : this.mScreenFragments) {
                Intrinsics.checkNotNullExpressionValue(screenFragment3, "screenFragment");
                Screen.ActivityState activityState = getActivityState(screenFragment3);
                Screen.ActivityState activityState2 = Screen.ActivityState.INACTIVE;
                if (activityState != activityState2 && !screenFragment3.isAdded()) {
                    attachScreen(fragmentTransactionCreateTransaction, screenFragment3);
                    z = true;
                } else if (activityState != activityState2 && z) {
                    detachScreen(fragmentTransactionCreateTransaction, screenFragment3);
                    arrayList.add(screenFragment3);
                }
                screenFragment3.getScreen().setTransitioning(z2);
            }
            for (ScreenFragment screenFragment4 : arrayList) {
                Intrinsics.checkNotNullExpressionValue(screenFragment4, "screenFragment");
                attachScreen(fragmentTransactionCreateTransaction, screenFragment4);
            }
            fragmentTransactionCreateTransaction.commitNowAllowingStateLoss();
            return;
        }
        throw new IllegalArgumentException("mFragmentManager is null when performing update in ScreenContainer".toString());
    }

    protected void notifyContainerUpdate() {
        ScreenFragment fragment;
        Screen topScreen = getTopScreen();
        if (topScreen == null || (fragment = topScreen.getFragment()) == null) {
            return;
        }
        fragment.onContainerUpdate();
    }
}
