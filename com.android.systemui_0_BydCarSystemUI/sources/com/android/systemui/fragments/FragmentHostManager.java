package com.android.systemui.fragments;

import android.app.Fragment;
import android.app.FragmentController;
import android.app.FragmentHostCallback;
import android.app.FragmentManager;
import android.app.FragmentManagerNonConfig;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import com.android.systemui.Dependency;
import com.android.systemui.fragments.FragmentHostManager;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.util.InterestingConfigChanges;
import com.android.systemui.util.leak.LeakDetector;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class FragmentHostManager {
    private final InterestingConfigChanges mConfigChanges;
    private final Context mContext;
    private FragmentController mFragments;
    private FragmentManager.FragmentLifecycleCallbacks mLifecycleCallbacks;
    private final FragmentService mManager;
    private final ExtensionFragmentManager mPlugins;
    private final View mRootView;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final HashMap<String, ArrayList<FragmentListener>> mListeners = new HashMap<>();

    public interface FragmentListener {
        void onFragmentViewCreated(String tag, Fragment fragment);

        default void onFragmentViewDestroyed(String tag, Fragment fragment) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
    }

    FragmentHostManager(Context context, FragmentService manager, View rootView) {
        InterestingConfigChanges interestingConfigChanges = new InterestingConfigChanges(-1073740924);
        this.mConfigChanges = interestingConfigChanges;
        this.mPlugins = new ExtensionFragmentManager();
        this.mContext = context;
        this.mManager = manager;
        this.mRootView = rootView;
        interestingConfigChanges.applyNewConfig(context.getResources());
        createFragmentHost(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createFragmentHost(Parcelable savedState) {
        FragmentController fragmentControllerCreateController = FragmentController.createController(new HostCallbacks());
        this.mFragments = fragmentControllerCreateController;
        fragmentControllerCreateController.attachHost(null);
        this.mLifecycleCallbacks = new FragmentManager.FragmentLifecycleCallbacks() { // from class: com.android.systemui.fragments.FragmentHostManager.1
            @Override // android.app.FragmentManager.FragmentLifecycleCallbacks
            public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
                FragmentHostManager.this.onFragmentViewCreated(f);
            }

            @Override // android.app.FragmentManager.FragmentLifecycleCallbacks
            public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                FragmentHostManager.this.onFragmentViewDestroyed(f);
            }

            @Override // android.app.FragmentManager.FragmentLifecycleCallbacks
            public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
                ((LeakDetector) Dependency.get(LeakDetector.class)).trackGarbage(f);
            }
        };
        this.mFragments.getFragmentManager().registerFragmentLifecycleCallbacks(this.mLifecycleCallbacks, true);
        if (savedState != null) {
            this.mFragments.restoreAllState(savedState, (FragmentManagerNonConfig) null);
        }
        this.mFragments.dispatchCreate();
        this.mFragments.dispatchStart();
        this.mFragments.dispatchResume();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Parcelable destroyFragmentHost() {
        this.mFragments.dispatchPause();
        Parcelable parcelableSaveAllState = this.mFragments.saveAllState();
        this.mFragments.dispatchStop();
        this.mFragments.dispatchDestroy();
        this.mFragments.getFragmentManager().unregisterFragmentLifecycleCallbacks(this.mLifecycleCallbacks);
        return parcelableSaveAllState;
    }

    public FragmentHostManager addTagListener(String tag, FragmentListener listener) {
        ArrayList<FragmentListener> arrayList = this.mListeners.get(tag);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mListeners.put(tag, arrayList);
        }
        arrayList.add(listener);
        Fragment fragmentFindFragmentByTag = getFragmentManager().findFragmentByTag(tag);
        if (fragmentFindFragmentByTag != null && fragmentFindFragmentByTag.getView() != null) {
            listener.onFragmentViewCreated(tag, fragmentFindFragmentByTag);
        }
        return this;
    }

    public void removeTagListener(String tag, FragmentListener listener) {
        ArrayList<FragmentListener> arrayList = this.mListeners.get(tag);
        if (arrayList != null && arrayList.remove(listener) && arrayList.size() == 0) {
            this.mListeners.remove(tag);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFragmentViewCreated(final Fragment fragment) {
        final String tag = fragment.getTag();
        ArrayList<FragmentListener> arrayList = this.mListeners.get(tag);
        if (arrayList != null) {
            arrayList.forEach(new Consumer() { // from class: com.android.systemui.fragments.-$$Lambda$FragmentHostManager$QlJMKD9uPd8hZrV2scpLIysb7yc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((FragmentHostManager.FragmentListener) obj).onFragmentViewCreated(tag, fragment);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFragmentViewDestroyed(final Fragment fragment) {
        final String tag = fragment.getTag();
        ArrayList<FragmentListener> arrayList = this.mListeners.get(tag);
        if (arrayList != null) {
            arrayList.forEach(new Consumer() { // from class: com.android.systemui.fragments.-$$Lambda$FragmentHostManager$jqcTN9upx2bwJ3BAaMHXjltVmKA
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((FragmentHostManager.FragmentListener) obj).onFragmentViewDestroyed(tag, fragment);
                }
            });
        }
    }

    public void reloadFragments() {
        createFragmentHost(destroyFragmentHost());
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (this.mConfigChanges.applyNewConfig(this.mContext.getResources())) {
            reloadFragments();
            BydLogHelper.logD("true");
        } else {
            this.mFragments.dispatchConfigurationChanged(newConfig);
            BydLogHelper.logD("false");
        }
    }

    public void dispatchConfigurationChanged(Configuration newConfig) {
        this.mFragments.dispatchConfigurationChanged(newConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T extends View> T findViewById(int i) {
        return (T) this.mRootView.findViewById(i);
    }

    public FragmentManager getFragmentManager() {
        return this.mFragments.getFragmentManager();
    }

    ExtensionFragmentManager getExtensionManager() {
        return this.mPlugins;
    }

    void destroy() {
        this.mFragments.dispatchDestroy();
    }

    public static FragmentHostManager get(View view) {
        try {
            return ((FragmentService) Dependency.get(FragmentService.class)).getFragmentHostManager(view);
        } catch (ClassCastException e) {
            throw e;
        }
    }

    class HostCallbacks extends FragmentHostCallback<FragmentHostManager> {
        @Override // android.app.FragmentHostCallback
        public void onAttachFragment(Fragment fragment) {
        }

        @Override // android.app.FragmentHostCallback
        public int onGetWindowAnimations() {
            return 0;
        }

        @Override // android.app.FragmentHostCallback, android.app.FragmentContainer
        public boolean onHasView() {
            return true;
        }

        @Override // android.app.FragmentHostCallback
        public boolean onHasWindowAnimations() {
            return false;
        }

        @Override // android.app.FragmentHostCallback
        public boolean onShouldSaveFragmentState(Fragment fragment) {
            return true;
        }

        @Override // android.app.FragmentHostCallback
        public boolean onUseFragmentManagerInflaterFactory() {
            return true;
        }

        public HostCallbacks() {
            super(FragmentHostManager.this.mContext, FragmentHostManager.this.mHandler, 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.app.FragmentHostCallback
        public FragmentHostManager onGetHost() {
            return FragmentHostManager.this;
        }

        @Override // android.app.FragmentHostCallback
        public void onDump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
            FragmentHostManager.this.dump(prefix, fd, writer, args);
        }

        public Fragment instantiate(Context context, String className, Bundle arguments) {
            return FragmentHostManager.this.mPlugins.instantiate(context, className, arguments);
        }

        @Override // android.app.FragmentHostCallback
        public LayoutInflater onGetLayoutInflater() {
            return LayoutInflater.from(FragmentHostManager.this.mContext);
        }

        @Override // android.app.FragmentHostCallback, android.app.FragmentContainer
        public <T extends View> T onFindViewById(int i) {
            return (T) FragmentHostManager.this.findViewById(i);
        }
    }

    class ExtensionFragmentManager {
        private final ArrayMap<String, Context> mExtensionLookup = new ArrayMap<>();

        ExtensionFragmentManager() {
        }

        public void setCurrentExtension(int id, String tag, String oldClass, String currentClass, Context context) {
            if (oldClass != null) {
                this.mExtensionLookup.remove(oldClass);
            }
            this.mExtensionLookup.put(currentClass, context);
            FragmentHostManager.this.getFragmentManager().beginTransaction().replace(id, instantiate(context, currentClass, null), tag).commit();
            reloadFragments();
        }

        private void reloadFragments() {
            FragmentHostManager.this.createFragmentHost(FragmentHostManager.this.destroyFragmentHost());
        }

        /* JADX WARN: Multi-variable type inference failed */
        Fragment instantiate(Context context, String className, Bundle arguments) {
            Context context2 = this.mExtensionLookup.get(className);
            if (context2 != null) {
                Fragment fragmentInstantiate = Fragment.instantiate(context2, className, arguments);
                if (fragmentInstantiate instanceof Plugin) {
                    ((Plugin) fragmentInstantiate).onCreate(FragmentHostManager.this.mContext, context2);
                }
                return fragmentInstantiate;
            }
            return Fragment.instantiate(context, className, arguments);
        }
    }

    private static class PluginState {
        String mCls;
        Context mContext;

        private PluginState(String cls, Context context) {
            this.mCls = cls;
            this.mContext = context;
        }
    }
}
