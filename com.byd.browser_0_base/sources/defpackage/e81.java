package defpackage;

import android.R;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

/* JADX INFO: compiled from: ListFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class e81 extends Fragment {
    public final Handler Z = new Handler();
    public final a a0 = new a();
    public final b b0 = new b();
    public ListAdapter c0;
    public ListView d0;
    public View e0;
    public View f0;
    public View g0;
    public boolean h0;

    /* JADX INFO: compiled from: ListFragment.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ListView listView = e81.this.d0;
            listView.focusableViewAvailable(listView);
        }
    }

    /* JADX INFO: compiled from: ListFragment.java */
    public class b implements AdapterView.OnItemClickListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            e81.this.getClass();
        }
    }

    public final void H0() {
        if (this.d0 != null) {
            return;
        }
        View view = this.f12J;
        if (view == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        if (view instanceof ListView) {
            this.d0 = (ListView) view;
        } else {
            TextView textView = (TextView) view.findViewById(16711681);
            if (textView == null) {
                this.e0 = view.findViewById(R.id.empty);
            } else {
                textView.setVisibility(8);
            }
            this.f0 = view.findViewById(16711682);
            this.g0 = view.findViewById(16711683);
            View viewFindViewById = view.findViewById(R.id.list);
            if (!(viewFindViewById instanceof ListView)) {
                if (viewFindViewById != null) {
                    throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
                }
                throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
            }
            ListView listView = (ListView) viewFindViewById;
            this.d0 = listView;
            View view2 = this.e0;
            if (view2 != null) {
                listView.setEmptyView(view2);
            }
        }
        this.h0 = true;
        this.d0.setOnItemClickListener(this.b0);
        ListAdapter listAdapter = this.c0;
        if (listAdapter != null) {
            this.c0 = null;
            I0(listAdapter);
        } else if (this.f0 != null) {
            J0(false, false);
        }
        this.Z.post(this.a0);
    }

    public final void I0(ListAdapter listAdapter) {
        boolean z = this.c0 != null;
        this.c0 = listAdapter;
        ListView listView = this.d0;
        if (listView != null) {
            listView.setAdapter(listAdapter);
            if (this.h0 || z) {
                return;
            }
            J0(true, y0().getWindowToken() != null);
        }
    }

    public final void J0(boolean z, boolean z2) {
        H0();
        View view = this.f0;
        if (view == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        if (this.h0 == z) {
            return;
        }
        this.h0 = z;
        if (z) {
            if (z2) {
                view.startAnimation(AnimationUtils.loadAnimation(K(), R.anim.fade_out));
                this.g0.startAnimation(AnimationUtils.loadAnimation(K(), R.anim.fade_in));
            } else {
                view.clearAnimation();
                this.g0.clearAnimation();
            }
            this.f0.setVisibility(8);
            this.g0.setVisibility(0);
            return;
        }
        if (z2) {
            view.startAnimation(AnimationUtils.loadAnimation(K(), R.anim.fade_in));
            this.g0.startAnimation(AnimationUtils.loadAnimation(K(), R.anim.fade_out));
        } else {
            view.clearAnimation();
            this.g0.clearAnimation();
        }
        this.f0.setVisibility(0);
        this.g0.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context contextX0 = x0();
        FrameLayout frameLayout = new FrameLayout(contextX0);
        LinearLayout linearLayout = new LinearLayout(contextX0);
        linearLayout.setId(16711682);
        linearLayout.setOrientation(1);
        linearLayout.setVisibility(8);
        linearLayout.setGravity(17);
        linearLayout.addView(new ProgressBar(contextX0, null, R.attr.progressBarStyleLarge), new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout frameLayout2 = new FrameLayout(contextX0);
        frameLayout2.setId(16711683);
        TextView textView = new TextView(contextX0);
        textView.setId(16711681);
        textView.setGravity(17);
        frameLayout2.addView(textView, new FrameLayout.LayoutParams(-1, -1));
        ListView listView = new ListView(contextX0);
        listView.setId(R.id.list);
        listView.setDrawSelectorOnTop(false);
        frameLayout2.addView(listView, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(frameLayout2, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return frameLayout;
    }

    @Override // androidx.fragment.app.Fragment
    public final void g0() {
        this.Z.removeCallbacks(this.a0);
        this.d0 = null;
        this.h0 = false;
        this.g0 = null;
        this.f0 = null;
        this.e0 = null;
        this.H = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void q0(View view, Bundle bundle) {
        H0();
    }
}
