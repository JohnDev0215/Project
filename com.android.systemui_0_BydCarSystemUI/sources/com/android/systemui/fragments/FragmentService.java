package com.android.systemui.fragments;

import android.content.Context;
import android.util.ArrayMap;
import android.view.View;
import com.android.systemui.Dumpable;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class FragmentService implements Dumpable {
    private static final String TAG = "FragmentService";
    private final Context mContext;
    private final ArrayMap<View, FragmentHostState> mHosts = new ArrayMap<>();

    public FragmentService(Context context) {
        this.mContext = context;
    }

    public FragmentHostManager getFragmentHostManager(View view) {
        View rootView = view.getRootView();
        FragmentHostState fragmentHostState = this.mHosts.get(rootView);
        if (fragmentHostState == null) {
            fragmentHostState = new FragmentHostState(rootView);
            this.mHosts.put(rootView, fragmentHostState);
        }
        return fragmentHostState.getFragmentHostManager();
    }

    public void destroyAll() {
        Iterator<FragmentHostState> it = this.mHosts.values().iterator();
        while (it.hasNext()) {
            it.next().mFragmentHostManager.destroy();
        }
    }

    @Override // com.android.systemui.Dumpable
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        pw.println("Dumping fragments:");
        Iterator<FragmentHostState> it = this.mHosts.values().iterator();
        while (it.hasNext()) {
            it.next().mFragmentHostManager.getFragmentManager().dump("  ", fd, pw, args);
        }
    }

    private class FragmentHostState {
        private FragmentHostManager mFragmentHostManager;
        private final View mView;

        public FragmentHostState(View view) {
            this.mView = view;
            this.mFragmentHostManager = new FragmentHostManager(FragmentService.this.mContext, FragmentService.this, view);
        }

        public FragmentHostManager getFragmentHostManager() {
            return this.mFragmentHostManager;
        }
    }
}
