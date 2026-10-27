package org.chromium.chrome.browser.tasks.tab_management;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.text.TextWatcher;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.ao3;
import defpackage.bq;
import defpackage.bz;
import defpackage.df0;
import defpackage.ef0;
import defpackage.ii2;
import defpackage.ij3;
import defpackage.kg;
import defpackage.o03;
import defpackage.oj2;
import defpackage.pc;
import defpackage.t41;
import defpackage.v62;
import defpackage.xl2;
import defpackage.yp;
import defpackage.yr;
import defpackage.za3;
import java.util.HashMap;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.chrome.browser.tasks.tab_management.MessageCardView;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;
import org.chromium.chrome.browser.tasks.tab_management.TabSelectionEditorToolbar;
import org.chromium.chrome.browser.tasks.tab_management.n0;
import org.chromium.chrome.browser.tasks.tab_management.r;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.ChromeImageView;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class y0 implements j.a {
    public final /* synthetic */ int a;

    public /* synthetic */ y0(int i) {
        this.a = i;
    }

    @Override // org.chromium.ui.modelutil.j.a
    public final void b(org.chromium.ui.modelutil.k kVar, Object obj, Object obj2) {
        int color;
        df0 df0Var;
        int i = 2;
        switch (this.a) {
            case 0:
                PropertyModel propertyModel = (PropertyModel) kVar;
                TabSelectionEditorLayout tabSelectionEditorLayout = (TabSelectionEditorLayout) obj;
                v62 v62Var = (v62) obj2;
                PropertyModel.n nVar = e1.a;
                if (nVar != v62Var) {
                    PropertyModel.r<View.OnClickListener> rVar = e1.b;
                    if (rVar != v62Var) {
                        PropertyModel.r<View.OnClickListener> rVar2 = e1.e;
                        if (rVar2 != v62Var) {
                            PropertyModel.p pVar = e1.f;
                            if (pVar != v62Var) {
                                PropertyModel.p pVar2 = e1.g;
                                if (pVar2 != v62Var) {
                                    PropertyModel.r<ColorStateList> rVar3 = e1.h;
                                    if (rVar3 != v62Var) {
                                        PropertyModel.r<ColorStateList> rVar4 = e1.i;
                                        if (rVar4 != v62Var) {
                                            PropertyModel.r<String> rVar5 = e1.c;
                                            if (rVar5 != v62Var) {
                                                PropertyModel.p pVar3 = e1.d;
                                                if (pVar3 != v62Var) {
                                                    PropertyModel.p pVar4 = e1.j;
                                                    if (pVar4 != v62Var) {
                                                        PropertyModel.p pVar5 = e1.k;
                                                        if (pVar5 != v62Var) {
                                                            PropertyModel.r<TabSelectionEditorToolbar.a> rVar6 = e1.l;
                                                            if (rVar6 == v62Var) {
                                                                tabSelectionEditorLayout.s.H0 = (TabSelectionEditorToolbar.a) propertyModel.j(rVar6);
                                                            }
                                                        } else {
                                                            tabSelectionEditorLayout.s.B0.setVisibility(propertyModel.h(pVar5));
                                                        }
                                                    } else {
                                                        tabSelectionEditorLayout.s.E0 = Integer.valueOf(propertyModel.h(pVar4));
                                                    }
                                                } else {
                                                    tabSelectionEditorLayout.s.G0 = propertyModel.h(pVar3);
                                                }
                                            } else {
                                                tabSelectionEditorLayout.s.B0.setText((String) propertyModel.j(rVar5));
                                            }
                                        } else {
                                            TabSelectionEditorToolbar tabSelectionEditorToolbar = tabSelectionEditorLayout.s;
                                            ColorStateList colorStateList = (ColorStateList) propertyModel.j(rVar4);
                                            tabSelectionEditorToolbar.B0.setTextColor(colorStateList);
                                            ((za3) tabSelectionEditorToolbar.getNavigationIcon()).c(colorStateList);
                                            tabSelectionEditorToolbar.C0.setImageTintList(colorStateList);
                                        }
                                    } else {
                                        tabSelectionEditorLayout.s.k0.setTextColorStateList((ColorStateList) propertyModel.j(rVar3));
                                    }
                                } else {
                                    tabSelectionEditorLayout.s.F0 = propertyModel.h(pVar2);
                                }
                            } else {
                                tabSelectionEditorLayout.setBackgroundColor(propertyModel.h(pVar));
                            }
                        } else {
                            tabSelectionEditorLayout.s.setNavigationOnClickListener((View.OnClickListener) propertyModel.j(rVar2));
                        }
                    } else {
                        tabSelectionEditorLayout.s.setActionButtonOnClickListener((View.OnClickListener) propertyModel.j(rVar));
                    }
                } else if (!propertyModel.k(nVar)) {
                    tabSelectionEditorLayout.getClass();
                    tabSelectionEditorLayout.t.removeView(tabSelectionEditorLayout);
                    int i2 = 0;
                    while (true) {
                        int childCount = tabSelectionEditorLayout.t.getChildCount();
                        HashMap map = tabSelectionEditorLayout.u;
                        if (i2 >= childCount) {
                            Integer num = (Integer) map.get(tabSelectionEditorLayout.t);
                            tabSelectionEditorLayout.t.setImportantForAccessibility(num != null ? num.intValue() : 0);
                            map.clear();
                        } else {
                            View childAt = tabSelectionEditorLayout.t.getChildAt(i2);
                            Integer num2 = (Integer) map.get(childAt);
                            childAt.setImportantForAccessibility(num2 == null ? 0 : num2.intValue());
                            i2++;
                        }
                    }
                } else {
                    tabSelectionEditorLayout.getClass();
                    while (true) {
                        int childCount2 = tabSelectionEditorLayout.t.getChildCount();
                        HashMap map2 = tabSelectionEditorLayout.u;
                        if (iMax >= childCount2) {
                            ViewGroup viewGroup = tabSelectionEditorLayout.t;
                            map2.put(viewGroup, Integer.valueOf(viewGroup.getImportantForAccessibility()));
                            tabSelectionEditorLayout.t.setImportantForAccessibility(2);
                            tabSelectionEditorLayout.t.addView(tabSelectionEditorLayout);
                        } else {
                            View childAt2 = tabSelectionEditorLayout.t.getChildAt(iMax);
                            map2.put(childAt2, Integer.valueOf(childAt2.getImportantForAccessibility()));
                            childAt2.setImportantForAccessibility(4);
                            iMax++;
                        }
                    }
                }
                break;
            case 1:
                PropertyModel propertyModel2 = (PropertyModel) kVar;
                TabListRecyclerView tabListRecyclerView = (TabListRecyclerView) obj;
                v62 v62Var2 = (v62) obj2;
                PropertyModel.n nVar2 = b0.a;
                if (nVar2 == v62Var2) {
                    if (!propertyModel2.k(nVar2)) {
                        boolean zK = propertyModel2.k(b0.e);
                        ValueAnimator valueAnimator = tabListRecyclerView.Q0;
                        if (valueAnimator != null) {
                            valueAnimator.end();
                        }
                        ValueAnimator valueAnimator2 = tabListRecyclerView.R0;
                        if (valueAnimator2 != null) {
                            valueAnimator2.end();
                        }
                        if (!tabListRecyclerView.V0 && (df0Var = tabListRecyclerView.T0) != null) {
                            df0Var.d(tabListRecyclerView.P0, tabListRecyclerView.U0);
                            tabListRecyclerView.V0 = true;
                        }
                        s0 s0Var = tabListRecyclerView.U0;
                        if (s0Var != null) {
                            s0Var.a(new ef0(s0Var, new p0(tabListRecyclerView, iMax, zK)));
                            tabListRecyclerView.U0.i();
                        } else {
                            tabListRecyclerView.p0(zK);
                        }
                    } else {
                        boolean zK2 = propertyModel2.k(b0.e);
                        tabListRecyclerView.S0.h();
                        long j = oj2.H() ? 50L : 218L;
                        tabListRecyclerView.setAlpha(0.0f);
                        tabListRecyclerView.setVisibility(0);
                        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(tabListRecyclerView, (Property<TabListRecyclerView, Float>) View.ALPHA, 1.0f);
                        tabListRecyclerView.Q0 = objectAnimatorOfFloat;
                        objectAnimatorOfFloat.setInterpolator(pc.e);
                        tabListRecyclerView.Q0.setDuration(j);
                        tabListRecyclerView.Q0.start();
                        tabListRecyclerView.Q0.addListener(new r0(tabListRecyclerView));
                        if (!zK2) {
                            tabListRecyclerView.Q0.end();
                        }
                    }
                    break;
                } else {
                    PropertyModel.n nVar3 = b0.b;
                    if (nVar3 == v62Var2) {
                        tabListRecyclerView.setBackgroundColor(yp.b(tabListRecyclerView.getContext(), propertyModel2.k(nVar3)));
                        break;
                    } else {
                        PropertyModel.r<TabListRecyclerView.d> rVar7 = b0.c;
                        if (rVar7 == v62Var2) {
                            tabListRecyclerView.S0 = (TabListRecyclerView.d) propertyModel2.j(rVar7);
                            break;
                        } else {
                            PropertyModel.r<Integer> rVar8 = b0.d;
                            if (rVar8 == v62Var2) {
                                int iIntValue = ((Integer) propertyModel2.j(rVar8)).intValue();
                                int width = tabListRecyclerView.getWidth();
                                int height = tabListRecyclerView.getHeight();
                                if (width == 0 || height == 0) {
                                    Rect rect = new Rect();
                                    ((Activity) tabListRecyclerView.getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                                    int iWidth = rect.width();
                                    height = rect.height() - tabListRecyclerView.getContext().getResources().getDimensionPixelSize(R$dimen.toolbar_height_no_shadow);
                                    width = iWidth;
                                }
                                if (width > 0 && height > 0) {
                                    int iH = propertyModel2.h(b0.j);
                                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) tabListRecyclerView.getLayoutManager();
                                    if (iH == 0) {
                                        iMax = Math.max(0, (height / 2) - (((int) ((width / ((GridLayoutManager) linearLayoutManager).F) / TabUtils.c(tabListRecyclerView.getContext()))) / 2));
                                    } else if (iH == 2) {
                                        iMax = Math.max(0, (width / 2) - (tabListRecyclerView.getContext().getResources().getDimensionPixelSize(R$dimen.tab_carousel_card_width) / 2));
                                    } else if (iH == 3 && linearLayoutManager.D() != 0) {
                                        iMax = Math.max(0, (height / 2) - ((tabListRecyclerView.computeVerticalScrollRange() / linearLayoutManager.D()) / 2));
                                    }
                                }
                                ((LinearLayoutManager) tabListRecyclerView.getLayoutManager()).g1(iIntValue, iMax);
                                break;
                            } else {
                                PropertyModel.p pVar6 = b0.f;
                                if (pVar6 != v62Var2) {
                                    PropertyModel.p pVar7 = b0.g;
                                    if (pVar7 == v62Var2) {
                                        ((FrameLayout.LayoutParams) tabListRecyclerView.getLayoutParams()).bottomMargin = propertyModel2.h(pVar7);
                                        ao3.e(tabListRecyclerView, "TabListContainerViewBinder.bind BOTTOM_CONTROLS_HEIGHT");
                                        break;
                                    } else {
                                        PropertyModel.p pVar8 = b0.h;
                                        if (pVar8 != v62Var2) {
                                            PropertyModel.p pVar9 = b0.i;
                                            if (pVar9 == v62Var2) {
                                                tabListRecyclerView.setPadding(tabListRecyclerView.getPaddingLeft(), tabListRecyclerView.getPaddingTop(), tabListRecyclerView.getPaddingRight(), propertyModel2.h(pVar9));
                                            }
                                            break;
                                        } else {
                                            tabListRecyclerView.Y0 = propertyModel2.h(pVar8);
                                            if (tabListRecyclerView.X0 != null && (tabListRecyclerView.getParent() instanceof FrameLayout)) {
                                                tabListRecyclerView.X0.setTranslationY(tabListRecyclerView.Y0);
                                                int iComputeVerticalScrollOffset = tabListRecyclerView.computeVerticalScrollOffset();
                                                if (iComputeVerticalScrollOffset == 0) {
                                                    tabListRecyclerView.q0(false);
                                                } else if (iComputeVerticalScrollOffset > 0) {
                                                    tabListRecyclerView.q0(true);
                                                }
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) tabListRecyclerView.getLayoutParams();
                                    int iH2 = propertyModel2.h(pVar6);
                                    if (iH2 != layoutParams.topMargin) {
                                        layoutParams.topMargin = iH2;
                                        ao3.e(tabListRecyclerView, "TabListContainerViewBinder.bind TOP_MARGIN");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 2:
                PropertyModel propertyModel3 = (PropertyModel) kVar;
                r.a aVar = (r.a) obj;
                v62 v62Var3 = (v62) obj2;
                PropertyModel.r<View.OnClickListener> rVar9 = q.a;
                if (rVar9 == v62Var3) {
                    aVar.a.setLeftButtonOnClickListener((View.OnClickListener) propertyModel3.j(rVar9));
                    break;
                } else {
                    PropertyModel.r<View.OnClickListener> rVar10 = q.b;
                    if (rVar10 == v62Var3) {
                        aVar.a.setRightButtonOnClickListener((View.OnClickListener) propertyModel3.j(rVar10));
                        break;
                    } else {
                        PropertyModel.r<String> rVar11 = q.c;
                        if (rVar11 == v62Var3) {
                            aVar.a.setTitle((String) propertyModel3.j(rVar11));
                            break;
                        } else {
                            PropertyModel.p pVar10 = q.d;
                            if (pVar10 == v62Var3) {
                                ((FrameLayout.LayoutParams) aVar.b.getLayoutParams()).topMargin = propertyModel3.h(pVar10);
                                ao3.e(aVar.b, "TabGridPanelViewBinder.bind");
                                break;
                            } else {
                                PropertyModel.p pVar11 = q.e;
                                if (pVar11 == v62Var3) {
                                    aVar.a.setPrimaryColor(propertyModel3.h(pVar11));
                                    aVar.b.setBackgroundColor(propertyModel3.h(pVar11));
                                    break;
                                } else {
                                    PropertyModel.r<ColorStateList> rVar12 = q.g;
                                    if (rVar12 == v62Var3) {
                                        aVar.a.setTint((ColorStateList) propertyModel3.j(rVar12));
                                        break;
                                    } else {
                                        PropertyModel.r<Runnable> rVar13 = q.j;
                                        if (rVar13 == v62Var3) {
                                            aVar.c.setScrimClickRunnable((Runnable) propertyModel3.j(rVar13));
                                            break;
                                        } else {
                                            PropertyModel.n nVar4 = q.h;
                                            if (nVar4 == v62Var3) {
                                                if (propertyModel3.k(nVar4)) {
                                                    TabGridDialogView tabGridDialogView = aVar.c;
                                                    tabGridDialogView.o.removeAllViews();
                                                    tabGridDialogView.o.addView(aVar.a);
                                                    RelativeLayout relativeLayout = tabGridDialogView.o;
                                                    RecyclerView recyclerView = aVar.b;
                                                    relativeLayout.addView(recyclerView);
                                                    tabGridDialogView.o.addView(tabGridDialogView.k);
                                                    tabGridDialogView.o.addView(tabGridDialogView.l);
                                                    ((RelativeLayout.LayoutParams) recyclerView.getLayoutParams()).setMargins(0, tabGridDialogView.f, 0, 0);
                                                    recyclerView.setVisibility(0);
                                                    TabGridDialogView tabGridDialogView2 = aVar.c;
                                                    Animator animator = tabGridDialogView2.u;
                                                    if (animator != null && animator != tabGridDialogView2.B) {
                                                        animator.end();
                                                    }
                                                    tabGridDialogView2.u = tabGridDialogView2.B;
                                                    tabGridDialogView2.q.a(tabGridDialogView2.p);
                                                    tabGridDialogView2.setVisibility(0);
                                                    tabGridDialogView2.B.start();
                                                    break;
                                                } else {
                                                    TabGridDialogView tabGridDialogView3 = aVar.c;
                                                    if (tabGridDialogView3.getVisibility() == 0) {
                                                        Animator animator2 = tabGridDialogView3.u;
                                                        if (animator2 != null && animator2 != tabGridDialogView3.C) {
                                                            animator2.end();
                                                        }
                                                        tabGridDialogView3.u = tabGridDialogView3.C;
                                                        if ((tabGridDialogView3.q.b.f != null ? 1 : 0) != 0) {
                                                            if (DeviceFormFactor.a(tabGridDialogView3.e)) {
                                                                tabGridDialogView3.q.b.a(350, true);
                                                            } else {
                                                                tabGridDialogView3.q.b.a(300, true);
                                                            }
                                                        }
                                                        tabGridDialogView3.C.start();
                                                        break;
                                                    }
                                                }
                                            } else {
                                                PropertyModel.r<TabGridDialogView.f> rVar14 = q.i;
                                                if (rVar14 == v62Var3) {
                                                    aVar.c.setVisibilityListener((TabGridDialogView.f) propertyModel3.j(rVar14));
                                                    break;
                                                } else {
                                                    PropertyModel.r<View> rVar15 = q.k;
                                                    if (rVar15 == v62Var3) {
                                                        aVar.c.setupDialogAnimation((View) propertyModel3.j(rVar15));
                                                        break;
                                                    } else {
                                                        PropertyModel.p pVar12 = q.l;
                                                        if (pVar12 != v62Var3) {
                                                            PropertyModel.p pVar13 = q.f;
                                                            if (pVar13 == v62Var3) {
                                                                if (aVar.c != null) {
                                                                    int iH3 = propertyModel3.h(pVar13);
                                                                    TabGridDialogView tabGridDialogView4 = aVar.c;
                                                                    tabGridDialogView4.L = iH3;
                                                                    tabGridDialogView4.o.getBackground().setTint(iH3);
                                                                    tabGridDialogView4.h.getBackground().setTint(iH3);
                                                                    aVar.a.setBackgroundColorTint(iH3);
                                                                }
                                                                break;
                                                            } else {
                                                                PropertyModel.p pVar14 = q.m;
                                                                if (pVar14 == v62Var3) {
                                                                    TabGridDialogView tabGridDialogView5 = aVar.c;
                                                                    if (tabGridDialogView5 != null) {
                                                                        tabGridDialogView5.N = propertyModel3.h(pVar14);
                                                                    }
                                                                    break;
                                                                } else {
                                                                    PropertyModel.p pVar15 = q.n;
                                                                    if (pVar15 == v62Var3) {
                                                                        TabGridDialogView tabGridDialogView6 = aVar.c;
                                                                        if (tabGridDialogView6 != null) {
                                                                            tabGridDialogView6.O = propertyModel3.h(pVar15);
                                                                        }
                                                                        break;
                                                                    } else {
                                                                        PropertyModel.p pVar16 = q.o;
                                                                        if (pVar16 == v62Var3) {
                                                                            TabGridDialogView tabGridDialogView7 = aVar.c;
                                                                            if (tabGridDialogView7 != null) {
                                                                                tabGridDialogView7.P = propertyModel3.h(pVar16);
                                                                            }
                                                                            break;
                                                                        } else {
                                                                            PropertyModel.p pVar17 = q.p;
                                                                            if (pVar17 == v62Var3) {
                                                                                TabGridDialogView tabGridDialogView8 = aVar.c;
                                                                                if (tabGridDialogView8 != null) {
                                                                                    tabGridDialogView8.Q = propertyModel3.h(pVar17);
                                                                                }
                                                                                break;
                                                                            } else {
                                                                                PropertyModel.r<Integer> rVar16 = q.q;
                                                                                if (rVar16 == v62Var3) {
                                                                                    int iIntValue2 = ((Integer) propertyModel3.j(rVar16)).intValue();
                                                                                    RecyclerView recyclerView2 = aVar.b;
                                                                                    if (recyclerView2.getWidth() == 0 || recyclerView2.getHeight() == 0) {
                                                                                        recyclerView2.post(new yr(iIntValue2, i, recyclerView2));
                                                                                    } else {
                                                                                        r.a(iIntValue2, aVar.b);
                                                                                    }
                                                                                    break;
                                                                                } else if (q.r == v62Var3) {
                                                                                    aVar.b.setVisibility(0);
                                                                                    break;
                                                                                } else {
                                                                                    PropertyModel.r<View.OnClickListener> rVar17 = q.s;
                                                                                    if (rVar17 == v62Var3) {
                                                                                        aVar.a.setMenuButtonOnClickListener((View.OnClickListener) propertyModel3.j(rVar17));
                                                                                        break;
                                                                                    } else {
                                                                                        PropertyModel.r<TextWatcher> rVar18 = q.t;
                                                                                        if (rVar18 == v62Var3) {
                                                                                            aVar.a.setTitleTextOnChangedListener((TextWatcher) propertyModel3.j(rVar18));
                                                                                            break;
                                                                                        } else {
                                                                                            PropertyModel.r<View.OnFocusChangeListener> rVar19 = q.u;
                                                                                            if (rVar19 == v62Var3) {
                                                                                                aVar.a.setTitleTextOnFocusChangeListener((View.OnFocusChangeListener) propertyModel3.j(rVar19));
                                                                                                break;
                                                                                            } else {
                                                                                                PropertyModel.n nVar5 = q.v;
                                                                                                if (nVar5 == v62Var3) {
                                                                                                    aVar.a.setTitleCursorVisibility(propertyModel3.k(nVar5));
                                                                                                    break;
                                                                                                } else {
                                                                                                    PropertyModel.n nVar6 = q.w;
                                                                                                    if (nVar6 == v62Var3) {
                                                                                                        if (oj2.C(aVar.b.getContext())) {
                                                                                                            boolean zK3 = propertyModel3.k(nVar6);
                                                                                                            TabGroupUiToolbarView tabGroupUiToolbarView = aVar.a;
                                                                                                            if (oj2.C(tabGroupUiToolbarView.getContext()) && tabGroupUiToolbarView.k.isFocused() != zK3) {
                                                                                                                if (!zK3) {
                                                                                                                    tabGroupUiToolbarView.k.clearFocus();
                                                                                                                } else {
                                                                                                                    tabGroupUiToolbarView.k.requestFocus();
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                    } else {
                                                                                                        PropertyModel.n nVar7 = q.x;
                                                                                                        if (nVar7 != v62Var3) {
                                                                                                            PropertyModel.r<String> rVar20 = q.y;
                                                                                                            if (rVar20 == v62Var3 && oj2.C(aVar.b.getContext())) {
                                                                                                                aVar.a.setLeftButtonContentDescription((String) propertyModel3.j(rVar20));
                                                                                                                break;
                                                                                                            }
                                                                                                        } else if (oj2.C(aVar.b.getContext())) {
                                                                                                            boolean zK4 = propertyModel3.k(nVar7);
                                                                                                            TabGroupUiToolbarView tabGroupUiToolbarView2 = aVar.a;
                                                                                                            if (oj2.C(tabGroupUiToolbarView2.getContext())) {
                                                                                                                if (!zK4) {
                                                                                                                    t41.f.e(tabGroupUiToolbarView2);
                                                                                                                } else {
                                                                                                                    tabGroupUiToolbarView2.postDelayed(new o03(tabGroupUiToolbarView2, t41.f), 150);
                                                                                                                }
                                                                                                                break;
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            TabGridDialogView tabGridDialogView9 = aVar.c;
                                                            int iH4 = propertyModel3.h(pVar12);
                                                            if (iH4 != tabGridDialogView9.M) {
                                                                if (iH4 == 0) {
                                                                    tabGridDialogView9.c(false);
                                                                    if (tabGridDialogView9.M == 1) {
                                                                        tabGridDialogView9.z.start();
                                                                    }
                                                                } else if (iH4 == 1) {
                                                                    tabGridDialogView9.A.start();
                                                                } else if (iH4 == 2) {
                                                                    tabGridDialogView9.c(true);
                                                                    if (tabGridDialogView9.M == 1) {
                                                                        tabGridDialogView9.z.start();
                                                                    }
                                                                }
                                                                tabGridDialogView9.M = iH4;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 3:
                PropertyModel propertyModel4 = (PropertyModel) kVar;
                ViewGroup viewGroup2 = (ViewGroup) obj;
                v62 v62Var4 = (v62) obj2;
                if (v62Var4 != null) {
                    ViewLookupCachingFrameLayout viewLookupCachingFrameLayout = (ViewLookupCachingFrameLayout) viewGroup2;
                    s.b(propertyModel4, viewLookupCachingFrameLayout, v62Var4);
                    s.c(propertyModel4, viewLookupCachingFrameLayout, v62Var4);
                } else {
                    ViewLookupCachingFrameLayout viewLookupCachingFrameLayout2 = (ViewLookupCachingFrameLayout) viewGroup2;
                    v62[] v62VarArr = u0.C;
                    int length = v62VarArr.length;
                    while (iMax < length) {
                        v62 v62Var5 = v62VarArr[iMax];
                        s.b(propertyModel4, viewLookupCachingFrameLayout2, v62Var5);
                        s.c(propertyModel4, viewLookupCachingFrameLayout2, v62Var5);
                        iMax++;
                    }
                }
                break;
            default:
                PropertyModel propertyModel5 = (PropertyModel) kVar;
                v62 v62Var6 = (v62) obj2;
                LargeMessageCardView largeMessageCardView = (LargeMessageCardView) ((ViewGroup) obj);
                PropertyModel.r<String> rVar21 = f.c;
                if (rVar21 != v62Var6) {
                    PropertyModel.r<String> rVar22 = f.t;
                    if (rVar22 != v62Var6) {
                        PropertyModel.r<String> rVar23 = f.e;
                        if (rVar23 != v62Var6) {
                            PropertyModel.r<String> rVar24 = f.m;
                            if (rVar24 != v62Var6) {
                                PropertyModel.r<String> rVar25 = f.d;
                                if (rVar25 != v62Var6) {
                                    PropertyModel.r<View.OnClickListener> rVar26 = f.j;
                                    if (rVar26 != v62Var6) {
                                        PropertyModel.r<xl2.c> rVar27 = f.v;
                                        if (rVar27 != v62Var6) {
                                            PropertyModel.r<MessageCardView.b> rVar28 = f.g;
                                            if (rVar28 != v62Var6) {
                                                PropertyModel.n nVar8 = f.p;
                                                if (nVar8 != v62Var6) {
                                                    PropertyModel.o oVar = n0.a.b;
                                                    if (oVar != v62Var6) {
                                                        PropertyModel.n nVar9 = f.o;
                                                        if (nVar9 != v62Var6) {
                                                            PropertyModel.n nVar10 = f.s;
                                                            if (nVar10 != v62Var6) {
                                                                PropertyModel.p pVar18 = f.q;
                                                                if (pVar18 != v62Var6) {
                                                                    PropertyModel.p pVar19 = f.r;
                                                                    if (pVar19 == v62Var6) {
                                                                        largeMessageCardView.i.getLayoutParams().height = propertyModel5.h(pVar19);
                                                                    }
                                                                } else {
                                                                    largeMessageCardView.i.getLayoutParams().width = propertyModel5.h(pVar18);
                                                                }
                                                            } else {
                                                                boolean zK5 = propertyModel5.k(nVar10);
                                                                largeMessageCardView.getClass();
                                                                int i3 = bq.d.a() ? R$dimen.default_elevation_2 : R$dimen.card_elevation;
                                                                Context context = largeMessageCardView.e;
                                                                largeMessageCardView.g.setBackgroundTintList(ColorStateList.valueOf(zK5 ? context.getColor(R$color.incognito_card_bg_color) : yp.c(context, i3)));
                                                                TextView textView = largeMessageCardView.j;
                                                                textView.setTextAppearance(textView.getContext(), zK5 ? R$style.TextAppearance_TextLarge_Primary_Baseline_Light : R$style.TextAppearance_TextLarge_Primary);
                                                                TextView textView2 = largeMessageCardView.k;
                                                                textView2.setTextAppearance(textView2.getContext(), zK5 ? R$style.TextAppearance_TextMedium_Secondary_Baseline_Light : R$style.TextAppearance_TextMedium_Secondary);
                                                                ButtonCompat buttonCompat = largeMessageCardView.l;
                                                                buttonCompat.setTextAppearance(buttonCompat.getContext(), zK5 ? R$style.TextAppearance_Button_Text_Filled_Baseline_Dark : R$style.TextAppearance_Button_Text_Filled);
                                                                ButtonCompat buttonCompat2 = largeMessageCardView.l;
                                                                Context context2 = buttonCompat2.getContext();
                                                                buttonCompat2.setButtonColor(ColorStateList.valueOf(zK5 ? context2.getColor(R$color.filled_button_bg_color_light) : context2.getColor(R$color.filled_button_bg_color)));
                                                                ButtonCompat buttonCompat3 = largeMessageCardView.m;
                                                                Context context3 = buttonCompat3.getContext();
                                                                if (zK5) {
                                                                    color = context3.getColor(R$color.default_text_color_link_light);
                                                                } else {
                                                                    String str = ii2.a;
                                                                    color = context3.getColor(org.chromium.components.browser_ui.styles.R$color.dialog_color_text_message_info_hint_url);
                                                                }
                                                                buttonCompat3.setTextColor(color);
                                                                ChromeImageView chromeImageView = largeMessageCardView.n;
                                                                chromeImageView.setImageTintList(bz.b(chromeImageView.getContext(), zK5 ? R$color.default_icon_color_light : R$color.default_icon_color_tint_list));
                                                            }
                                                        } else if (!propertyModel5.k(nVar9)) {
                                                            largeMessageCardView.n.setVisibility(8);
                                                        } else {
                                                            largeMessageCardView.n.setVisibility(0);
                                                        }
                                                    } else {
                                                        largeMessageCardView.setAlpha(propertyModel5.g(oVar));
                                                    }
                                                } else if (!propertyModel5.k(nVar8)) {
                                                    largeMessageCardView.i.setVisibility(8);
                                                } else {
                                                    largeMessageCardView.i.setVisibility(0);
                                                }
                                            } else {
                                                largeMessageCardView.i.setImageDrawable(((MessageCardView.b) propertyModel5.j(rVar28)).a());
                                            }
                                        } else {
                                            xl2.c cVar = (xl2.c) propertyModel5.j(rVar27);
                                            if (cVar == null) {
                                                largeMessageCardView.h.setVisibility(8);
                                            } else {
                                                PriceCardView priceCardView = largeMessageCardView.h;
                                                priceCardView.e.setText(cVar.a);
                                                priceCardView.f.setText(cVar.b);
                                                largeMessageCardView.h.setVisibility(0);
                                            }
                                        }
                                    } else {
                                        largeMessageCardView.setSecondaryActionButtonOnClickListener((View.OnClickListener) propertyModel5.j(rVar26));
                                    }
                                } else {
                                    String str2 = (String) propertyModel5.j(rVar25);
                                    if (largeMessageCardView.m.getVisibility() == 8) {
                                        largeMessageCardView.m.setVisibility(0);
                                    }
                                    largeMessageCardView.m.setText(str2);
                                }
                            } else {
                                largeMessageCardView.n.setContentDescription((String) propertyModel5.j(rVar24));
                                largeMessageCardView.setDismissButtonOnClickListener(new kg(3, propertyModel5));
                            }
                        } else {
                            largeMessageCardView.k.setText((String) propertyModel5.j(rVar23));
                        }
                    } else {
                        largeMessageCardView.j.setText((String) propertyModel5.j(rVar22));
                    }
                } else {
                    largeMessageCardView.l.setText((String) propertyModel5.j(rVar21));
                    largeMessageCardView.setActionButtonOnClickListener(new ij3(i, propertyModel5));
                }
                break;
        }
    }
}
