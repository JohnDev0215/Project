package org.chromium.chrome.features.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import defpackage.ag2;
import defpackage.c6;
import defpackage.dg2;
import defpackage.in3;
import defpackage.ll0;
import defpackage.o73;
import defpackage.om3;
import defpackage.qh3;
import defpackage.yj1;
import defpackage.yp;
import defpackage.zx0;
import java.util.WeakHashMap;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$style;
import org.chromium.components.browser_ui.widget.CoordinatorLayoutForPointer;

/* JADX INFO: loaded from: classes2.dex */
public class TasksView extends CoordinatorLayoutForPointer {
    public static final yj1 O = new yj1("IncognitoNtpRevamp", false);
    public final Context C;
    public FrameLayout D;
    public AppBarLayout E;
    public ViewGroup F;
    public ag2 G;
    public zx0 H;
    public View.OnClickListener I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f86J;
    public CompoundButton.OnCheckedChangeListener K;
    public int L;
    public View.OnClickListener M;
    public qh3 N;

    public TasksView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.L = 0;
        this.C = context;
    }

    public void addHeaderOffsetChangeListener(AppBarLayout.f fVar) {
        AppBarLayout appBarLayout = this.E;
        if (appBarLayout != null) {
            appBarLayout.addOnOffsetChangedListener(fVar);
        }
    }

    public ViewGroup getBodyViewContainer() {
        return (ViewGroup) findViewById(R$id.tasks_surface_body);
    }

    public ViewGroup getCarouselTabSwitcherContainer() {
        return this.D;
    }

    public ag2 getSearchBoxCoordinator() {
        return this.G;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.N.c();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.D = (FrameLayout) findViewById(R$id.carousel_tab_switcher_container);
        this.F = (ViewGroup) findViewById(R$id.mv_tiles_container);
        this.G = new ag2(getContext(), this);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R$id.task_surface_header);
        this.E = appBarLayout;
        CoordinatorLayout.e eVar = (CoordinatorLayout.e) appBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
        behavior.o = new o73();
        eVar.b(behavior);
        qh3 qh3Var = new qh3(this);
        this.N = qh3Var;
        Activity activity = (Activity) this.C;
        new ll0(activity, this.E, qh3Var, activity.getResources().getDimensionPixelSize(R$dimen.content_suggestions_card_modern_margin), activity.getResources().getDimensionPixelSize(R$dimen.ntp_wide_card_lateral_margins)).a();
        TextView textView = (TextView) findViewById(R$id.tab_switcher_title_description);
        TextView textView2 = (TextView) findViewById(R$id.more_tabs);
        c6.f(textView, R$style.TextAppearance_TextAccentMediumThick_Secondary);
        c6.f(textView2, R$style.TextAppearance_Button_Text_Blue);
        int paddingStart = textView.getPaddingStart();
        int paddingTop = textView.getPaddingTop();
        int paddingEnd = textView.getPaddingEnd();
        int paddingBottom = textView.getPaddingBottom();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(textView, paddingStart, paddingTop, paddingEnd, paddingBottom);
    }

    public void removeHeaderOffsetChangeListener(AppBarLayout.f fVar) {
        AppBarLayout appBarLayout = this.E;
        if (appBarLayout != null) {
            appBarLayout.removeOnOffsetChangedListener(fVar);
        }
    }

    public void setIncognitoCookieControlsIconClickListener(View.OnClickListener onClickListener) {
        this.M = onClickListener;
        zx0 zx0Var = this.H;
        if (zx0Var != null) {
            zx0Var.setCookieControlsIconOnclickListener(onClickListener);
            this.M = null;
        }
    }

    public void setIncognitoCookieControlsToggleChecked(boolean z) {
        this.f86J = z;
        zx0 zx0Var = this.H;
        if (zx0Var != null) {
            zx0Var.setCookieControlsToggle(z);
        }
    }

    public void setIncognitoCookieControlsToggleCheckedListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.K = onCheckedChangeListener;
        zx0 zx0Var = this.H;
        if (zx0Var != null) {
            zx0Var.setCookieControlsToggleOnCheckedChangeListener(onCheckedChangeListener);
            this.K = null;
        }
    }

    public void setIncognitoCookieControlsToggleEnforcement(int i) {
        this.L = i;
        zx0 zx0Var = this.H;
        if (zx0Var != null) {
            zx0Var.setCookieControlsEnforcement(i);
        }
    }

    public void setIncognitoDescriptionLearnMoreClickListener(View.OnClickListener onClickListener) {
        this.I = onClickListener;
        zx0 zx0Var = this.H;
        if (zx0Var != null) {
            zx0Var.setLearnMoreOnclickListener(onClickListener);
            this.I = null;
        }
    }

    public void setIncognitoDescriptionVisibility(boolean z) {
        ((View) this.H).setVisibility(z ? 0 : 8);
    }

    public void setIncognitoMode(boolean z) {
        Drawable drawableFindDrawableByLayerId;
        Context context = this.C;
        int iB = yp.b(context, z);
        setBackgroundColor(iB);
        this.E.setBackgroundColor(iB);
        this.G.getClass();
        Drawable drawableA = AppCompatResources.a(context, z ? R$drawable.fake_search_box_bg_incognito : R$drawable.ntp_search_box);
        if ((drawableA instanceof LayerDrawable) && (drawableFindDrawableByLayerId = ((LayerDrawable) drawableA).findDrawableByLayerId(R$id.fake_search_box_bg_shape)) != null) {
            int color = z ? getResources().getColor(R$color.toolbar_text_box_background_incognito) : yp.c(context, R$dimen.toolbar_text_box_elevation);
            drawableFindDrawableByLayerId.mutate();
            drawableFindDrawableByLayerId.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
        this.G.a.p(dg2.b, drawableA);
        this.G.a.n(dg2.n, context.getColor(z ? R$color.locationbar_light_hint_text : R$color.locationbar_dark_hint_text));
    }

    public void setMVTilesContainerLeftAndRightMargin(int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.F.getLayoutParams();
        marginLayoutParams.leftMargin = i;
        marginLayoutParams.rightMargin = i;
    }

    public void setMVTilesContainerTopMargin(int i) {
        ((ViewGroup.MarginLayoutParams) this.F.getLayoutParams()).topMargin = i;
    }

    public void setMoreTabsOnClickListener(View.OnClickListener onClickListener) {
        findViewById(R$id.more_tabs).setOnClickListener(onClickListener);
    }

    public void setMostVisitedVisibility(int i) {
        this.F.setVisibility(i);
    }

    public void setQueryTilesVisibility(int i) {
        findViewById(R$id.query_tiles_container).setVisibility(i);
    }

    public void setSingleTabTopMargin(int i) {
        ((ViewGroup.MarginLayoutParams) this.E.findViewById(R$id.single_tab_view).getLayoutParams()).topMargin = i;
    }

    public void setSurfaceBodyVisibility(boolean z) {
        getBodyViewContainer().setVisibility(z ? 0 : 8);
    }

    public void setTabCarouselTitleVisibility(boolean z) {
        findViewById(R$id.tab_switcher_title).setVisibility(z ? 0 : 8);
    }

    public void setTabCarouselVisibility(boolean z) {
        this.D.setVisibility(z ? 0 : 8);
    }

    public void setTabSwitcherTitleTopMargin(int i) {
        ((ViewGroup.MarginLayoutParams) this.E.findViewById(R$id.tab_switcher_title).getLayoutParams()).topMargin = i;
    }

    public void setTasksSurfaceBodyTopMargin(int i) {
        ((ViewGroup.MarginLayoutParams) getBodyViewContainer().getLayoutParams()).topMargin = i;
    }

    public void setTopToolbarPlaceholderHeight(int i) {
        View viewFindViewById = findViewById(R$id.top_toolbar_placeholder);
        ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
        layoutParams.height = i;
        viewFindViewById.setLayoutParams(layoutParams);
    }
}
