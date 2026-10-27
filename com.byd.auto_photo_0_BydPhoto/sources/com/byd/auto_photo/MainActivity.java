package com.byd.auto_photo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.fragment.AlertDialogFragment;
import com.byd.util.e;
import com.byd.util.h;
import com.byd.util.w;
import com.byd.view.TabGroup;
import com.byd.view.TabItem;
import com.byd.view.TabItemLinearContainer;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends MainBaseActivity {
    private View Q;
    private OTAReceiver R;
    private TabItemLinearContainer S;
    private TabItemLinearContainer T;
    private LottieAnimationView U;
    private LottieAnimationView V;
    private int W;
    private LinkedList<c> X;

    class a implements TabItemLinearContainer.OnContainerTouchListener {
        a() {
        }

        @Override // com.byd.view.TabItemLinearContainer.OnContainerTouchListener
        public void onTouchAction(TabItemLinearContainer tabItemLinearContainer, int i) {
            MainActivity mainActivity = MainActivity.this;
            mainActivity.i1(tabItemLinearContainer, mainActivity.U, i);
        }
    }

    class b implements TabItemLinearContainer.OnContainerTouchListener {
        b() {
        }

        @Override // com.byd.view.TabItemLinearContainer.OnContainerTouchListener
        public void onTouchAction(TabItemLinearContainer tabItemLinearContainer, int i) {
            MainActivity mainActivity = MainActivity.this;
            mainActivity.i1(tabItemLinearContainer, mainActivity.V, i);
        }
    }

    public interface c {
        void onThemeChange();
    }

    private void d1() {
        LinkedList<c> linkedList = this.X;
        if (linkedList == null) {
            return;
        }
        Iterator<c> it = linkedList.iterator();
        while (it.hasNext()) {
            it.next().onThemeChange();
        }
    }

    private Drawable e1(int i) {
        return h.c(getResources().getDrawable(i), getResources().getColorStateList(R.color.tab_text_color, getTheme()));
    }

    private void f1() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1280);
    }

    private void h1(View view, float f) {
        view.setScaleX(f);
        view.setScaleY(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i1(TabItemLinearContainer tabItemLinearContainer, View view, int i) {
        if (i == 0) {
            h1(view, 0.9f);
            return;
        }
        if (i != 1) {
            h1(view, 1.0f);
        } else if (tabItemLinearContainer.isChecked()) {
            h1(view, 1.0f);
        } else {
            tabItemLinearContainer.playSoundEffect(0);
            tabItemLinearContainer.setChecked(true);
        }
    }

    private void j1() {
        LottieAnimationView lottieAnimationView;
        int checkedId = this.v.getCheckedId();
        if (checkedId == R.id.tab_album) {
            this.U.e();
            this.U.setImageDrawable(e1(R.drawable.icon_picture));
            this.V.setAnimation(this.W == 1 ? "icon_denza_album_48.json" : "album.json");
            this.V.m();
            lottieAnimationView = this.V;
        } else {
            if (checkedId != R.id.tab_image) {
                return;
            }
            this.V.e();
            this.U.setAnimation(this.W == 1 ? "icon_denza_photo_48.json" : "photo.json");
            this.V.setImageDrawable(e1(R.drawable.icon_album));
            this.U.m();
            lottieAnimationView = this.U;
        }
        h1(lottieAnimationView, 1.0f);
    }

    @Override // com.byd.auto_photo.MainBaseActivity
    protected void H0() {
        this.S = (TabItemLinearContainer) findViewById(R.id.tab_image);
        this.T = (TabItemLinearContainer) findViewById(R.id.tab_album);
        this.x = (TabItem) findViewById(R.id.tab_text_image);
        this.y = (TabItem) findViewById(R.id.tab_text_album);
        this.U = (LottieAnimationView) findViewById(R.id.anim_image);
        this.V = (LottieAnimationView) findViewById(R.id.anim_album);
        this.S.setAutoCheck(false);
        this.T.setAutoCheck(false);
        this.S.setOnContainerTouchListener(new a());
        this.T.setOnContainerTouchListener(new b());
    }

    @Override // com.byd.theme.ThemeActivity
    protected int K(boolean z) {
        return this.W != 1 ? super.K(z) : R.style.AppThemeTS;
    }

    @Override // com.byd.auto_photo.MainBaseActivity, com.byd.theme.ThemeActivity
    protected void N() {
        super.N();
        this.Q = findViewById(R.id.tab_shadow);
        j1();
    }

    @Override // com.byd.auto_photo.MainBaseActivity, com.byd.theme.ThemeActivity
    protected void R() {
        int i = getResources().getConfiguration().uiMode;
        this.e = i;
        setTheme((i & 48) == 32 ? R.style.AppThemeNight : R.style.AppThemeLight);
        getWindow().setBackgroundDrawable(e.p(this, R.attr.bg_content));
        super.R();
    }

    @Override // com.byd.theme.ThemeActivity
    protected void T(Configuration configuration) {
        if (this.x != null) {
            Resources resources = getResources();
            this.x.setText(resources.getString(R.string.tab_image));
            this.y.setText(resources.getString(R.string.tab_album));
            this.x.requestLayout();
            this.y.requestLayout();
        }
    }

    @Override // com.byd.theme.ThemeActivity
    protected void V(boolean z) {
        View view;
        int i;
        w.c("PhotoMainActivity onUIModeChanged");
        b0(z, true);
        if (this.v == null) {
            return;
        }
        d1();
        if (getResources().getConfiguration().orientation == 2 && e.H(this)) {
            this.v.setBackground(e.p(this, R.attr.tab_bg_right_drive));
            this.S.setBackground(e.p(this, R.attr.tab_selected_bg_right_drive));
            this.T.setBackground(e.p(this, R.attr.tab_selected_bg_right_drive));
            view = this.Q;
            i = R.attr.tab_shadow_right_drive;
        } else {
            this.v.setBackground(e.p(this, R.attr.tab_bg));
            this.S.setBackground(e.p(this, R.attr.tab_selected_bg));
            this.T.setBackground(e.p(this, R.attr.tab_selected_bg));
            view = this.Q;
            i = R.attr.tab_shadow;
        }
        view.setBackground(e.p(this, i));
        j1();
        ColorStateList colorStateList = getResources().getColorStateList(R.color.tab_text_color, getTheme());
        this.x.setTextColor(colorStateList);
        this.y.setTextColor(colorStateList);
        this.x.refreshDrawableState();
        this.y.refreshDrawableState();
        this.S.refreshDrawableState();
        this.T.refreshDrawableState();
        this.t.setBackground(e.p(this, R.attr.bg_content));
        this.u.setBackground(e.p(this, R.attr.bg_content));
    }

    @Override // com.byd.auto_photo.MainBaseActivity
    protected boolean Z0() {
        this.M = true;
        Intent intent = new Intent();
        intent.setAction("com.byd.carsettings.ui.activity.AppDetailActivity");
        Bundle bundle = new Bundle();
        bundle.putString("app_package", getApplication().getPackageName());
        bundle.putString("jump_to", "jump_permission");
        intent.putExtras(bundle);
        startActivity(intent);
        return true;
    }

    public void g1(c cVar) {
        LinkedList<c> linkedList = this.X;
        if (linkedList == null || linkedList.contains(cVar)) {
            return;
        }
        this.X.addFirst(cVar);
    }

    public void k1(c cVar) {
        LinkedList<c> linkedList = this.X;
        if (linkedList == null) {
            return;
        }
        linkedList.remove(cVar);
    }

    @Override // com.byd.auto_photo.MainBaseActivity, com.byd.view.TabGroup.OnCheckedChangeListener
    public void onCheckedChanged(TabGroup tabGroup, int i) {
        super.onCheckedChanged(tabGroup, i);
        j1();
    }

    @Override // com.byd.auto_photo.MainBaseActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.X = new LinkedList<>();
        OTAReceiver oTAReceiver = new OTAReceiver(this);
        this.R = oTAReceiver;
        oTAReceiver.a();
        this.W = b.a.a.a(this);
        super.onCreate(bundle);
    }

    @Override // com.byd.auto_photo.MainBaseActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.R.b();
        super.onDestroy();
        LinkedList<c> linkedList = this.X;
        if (linkedList != null) {
            linkedList.clear();
            this.X = null;
        }
    }

    @Override // com.byd.auto_photo.MainBaseActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        f1();
        super.onStart();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            f1();
        }
    }

    @Override // com.byd.auto_photo.MainBaseActivity
    protected void q0(int i) {
        super.q0(i);
        j1();
    }

    @Override // com.byd.auto_photo.MainBaseActivity
    protected void t0(AlertDialogFragment alertDialogFragment) {
    }

    @Override // com.byd.auto_photo.MainBaseActivity
    protected int z0() {
        return e.H(this) ? R.layout.activity_main_photo_right_drive : R.layout.activity_main_photo;
    }
}
