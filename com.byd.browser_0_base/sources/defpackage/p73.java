package defpackage;

import J.N;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewStub;
import android.widget.CompoundButton;
import com.google.android.material.appbar.AppBarLayout;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.ntp.a;
import org.chromium.chrome.features.tasks.TasksView;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TasksViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class p73 {
    public static void a(PropertyModel propertyModel, TasksView tasksView, v62 v62Var) {
        PropertyModel.r<View.OnClickListener> rVar = n73.p;
        if (v62Var == rVar) {
            tasksView.getSearchBoxCoordinator().setSearchBoxClickListener((View.OnClickListener) propertyModel.j(rVar));
            return;
        }
        PropertyModel.r<TextWatcher> rVar2 = n73.q;
        if (v62Var == rVar2) {
            tasksView.getSearchBoxCoordinator().a.p(dg2.m, (TextWatcher) propertyModel.j(rVar2));
            return;
        }
        PropertyModel.r<View.OnClickListener> rVar3 = n73.j;
        if (v62Var == rVar3) {
            tasksView.setIncognitoCookieControlsIconClickListener((View.OnClickListener) propertyModel.j(rVar3));
            return;
        }
        PropertyModel.n nVar = n73.k;
        if (v62Var == nVar) {
            tasksView.setIncognitoCookieControlsToggleChecked(propertyModel.k(nVar));
            return;
        }
        PropertyModel.r<CompoundButton.OnCheckedChangeListener> rVar4 = n73.l;
        if (v62Var == rVar4) {
            tasksView.setIncognitoCookieControlsToggleCheckedListener((CompoundButton.OnCheckedChangeListener) propertyModel.j(rVar4));
            return;
        }
        PropertyModel.p pVar = n73.m;
        if (v62Var == pVar) {
            tasksView.setIncognitoCookieControlsToggleEnforcement(propertyModel.h(pVar));
            return;
        }
        PropertyModel.r<View.OnClickListener> rVar5 = n73.o;
        if (v62Var == rVar5) {
            tasksView.setIncognitoDescriptionLearnMoreClickListener((View.OnClickListener) propertyModel.j(rVar5));
            return;
        }
        PropertyModel.n nVar2 = n73.a;
        if (v62Var == nVar2) {
            tasksView.getSearchBoxCoordinator().a.l(dg2.c, propertyModel.k(nVar2));
            return;
        }
        PropertyModel.n nVar3 = n73.b;
        if (v62Var == nVar3) {
            tasksView.setIncognitoMode(propertyModel.k(nVar3));
            return;
        }
        PropertyModel.n nVar4 = n73.c;
        if (v62Var == nVar4) {
            if (propertyModel.k(nVar4)) {
                ((ViewStub) tasksView.findViewById(R$id.incognito_description_container_layout_stub)).inflate();
                ViewStub viewStub = (ViewStub) tasksView.findViewById(R$id.task_view_incognito_layout_stub);
                if (TasksView.O.a()) {
                    viewStub.setLayoutResource(R$layout.revamped_incognito_description_layout);
                } else {
                    viewStub.setLayoutResource(R$layout.incognito_description_layout);
                }
                tasksView.H = (zx0) viewStub.inflate();
                View.OnClickListener onClickListener = tasksView.I;
                if (onClickListener != null) {
                    tasksView.setIncognitoDescriptionLearnMoreClickListener(onClickListener);
                }
                tasksView.setIncognitoCookieControlsToggleChecked(tasksView.f86J);
                CompoundButton.OnCheckedChangeListener onCheckedChangeListener = tasksView.K;
                if (onCheckedChangeListener != null) {
                    tasksView.setIncognitoCookieControlsToggleCheckedListener(onCheckedChangeListener);
                }
                tasksView.setIncognitoCookieControlsToggleEnforcement(tasksView.L);
                View.OnClickListener onClickListener2 = tasksView.M;
                if (onClickListener2 != null) {
                    tasksView.setIncognitoCookieControlsIconClickListener(onClickListener2);
                    return;
                }
                return;
            }
            return;
        }
        PropertyModel.n nVar5 = n73.d;
        if (v62Var == nVar5) {
            boolean zK = propertyModel.k(nVar5);
            if (zK) {
                a aVar = (a) propertyModel.j(n73.n);
                if (aVar.g) {
                    N.Ml$8f4xR(aVar.e.a);
                }
            }
            tasksView.setIncognitoDescriptionVisibility(zK);
            return;
        }
        PropertyModel.n nVar6 = n73.e;
        if (v62Var == nVar6) {
            tasksView.getSearchBoxCoordinator().a.l(dg2.h, propertyModel.k(nVar6));
            return;
        }
        PropertyModel.n nVar7 = n73.f;
        if (v62Var == nVar7) {
            tasksView.setSurfaceBodyVisibility(propertyModel.k(nVar7));
            return;
        }
        PropertyModel.n nVar8 = n73.g;
        if (v62Var == nVar8) {
            tasksView.setTabCarouselVisibility(propertyModel.k(nVar8));
            return;
        }
        PropertyModel.n nVar9 = n73.h;
        if (v62Var == nVar9) {
            tasksView.setTabCarouselTitleVisibility(propertyModel.k(nVar9));
            return;
        }
        PropertyModel.n nVar10 = n73.i;
        if (v62Var == nVar10) {
            tasksView.getSearchBoxCoordinator().a.l(dg2.d, propertyModel.k(nVar10));
            return;
        }
        PropertyModel.r<View.OnClickListener> rVar6 = n73.r;
        if (v62Var == rVar6) {
            tasksView.getSearchBoxCoordinator().addLensButtonClickListener((View.OnClickListener) propertyModel.j(rVar6));
            return;
        }
        PropertyModel.r<View.OnClickListener> rVar7 = n73.s;
        if (v62Var == rVar7) {
            tasksView.setMoreTabsOnClickListener((View.OnClickListener) propertyModel.j(rVar7));
            return;
        }
        PropertyModel.n nVar11 = n73.t;
        if (v62Var == nVar11) {
            tasksView.setMostVisitedVisibility(propertyModel.k(nVar11) ? 0 : 8);
            return;
        }
        PropertyModel.n nVar12 = n73.u;
        if (v62Var == nVar12) {
            tasksView.setQueryTilesVisibility(propertyModel.k(nVar12) ? 0 : 8);
            return;
        }
        PropertyModel.r<View.OnClickListener> rVar8 = n73.v;
        if (v62Var == rVar8) {
            tasksView.getSearchBoxCoordinator().addVoiceSearchButtonClickListener((View.OnClickListener) propertyModel.j(rVar8));
            return;
        }
        PropertyModel.p pVar2 = n73.w;
        if (v62Var == pVar2) {
            tasksView.setTasksSurfaceBodyTopMargin(propertyModel.h(pVar2));
            return;
        }
        PropertyModel.p pVar3 = n73.x;
        if (v62Var == pVar3) {
            tasksView.setMVTilesContainerTopMargin(propertyModel.h(pVar3));
            return;
        }
        PropertyModel.p pVar4 = n73.y;
        if (v62Var == pVar4) {
            tasksView.setMVTilesContainerLeftAndRightMargin(propertyModel.h(pVar4));
            return;
        }
        PropertyModel.p pVar5 = n73.z;
        if (v62Var == pVar5) {
            tasksView.setTabSwitcherTitleTopMargin(propertyModel.h(pVar5));
            return;
        }
        PropertyModel.p pVar6 = n73.A;
        if (v62Var == pVar6) {
            tasksView.setSingleTabTopMargin(propertyModel.h(pVar6));
            return;
        }
        if (v62Var != n73.C) {
            PropertyModel.p pVar7 = n73.B;
            if (v62Var == pVar7) {
                tasksView.setTopToolbarPlaceholderHeight(propertyModel.h(pVar7));
                return;
            }
            return;
        }
        AppBarLayout appBarLayout = tasksView.E;
        if (appBarLayout == null || appBarLayout.getHeight() == tasksView.E.getBottom()) {
            return;
        }
        tasksView.E.d(true, false, true);
    }
}
