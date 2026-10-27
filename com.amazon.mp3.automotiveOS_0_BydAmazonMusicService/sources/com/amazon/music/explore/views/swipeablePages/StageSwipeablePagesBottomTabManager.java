package com.amazon.music.explore.views.swipeablePages;

import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.amazon.music.explore.R;
import com.amazon.music.explore.views.swipeablePages.pages.manager.BottomTabStateManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class StageSwipeablePagesBottomTabManager {
    private final LinearLayout bottomTabBarLayout;
    private final BottomTabStateManager bottomTabStateManager;
    private final List<SwipeablePageElement> currentTabElements;
    private final int numberOfTabs;
    private int currentActiveTabPos = 0;
    private final List<TextView> bottomTabTextViews = new ArrayList();
    private final List<FrameLayout> bottomTabButtonViews = new ArrayList();

    public StageSwipeablePagesBottomTabManager(LinearLayout linearLayout, BottomTabStateManager bottomTabStateManager, List<SwipeablePageElement> list) {
        this.bottomTabBarLayout = linearLayout;
        this.bottomTabStateManager = bottomTabStateManager;
        this.currentTabElements = list;
        this.numberOfTabs = list.size();
        init();
    }

    private void init() {
        this.bottomTabBarLayout.setOrientation(0);
        initializeBottomTabs();
        setupStyleAndActionListeners();
    }

    private void initializeBottomTabs() {
        for (int i = 0; i < this.numberOfTabs; i++) {
            this.bottomTabButtonViews.add(initBottomTabButtonView(this.bottomTabBarLayout.getContext(), this.currentTabElements.get(i).label().title()));
        }
        this.bottomTabBarLayout.post(new Runnable() { // from class: com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesBottomTabManager.1
            @Override // java.lang.Runnable
            public void run() {
                for (int i2 = 0; i2 < StageSwipeablePagesBottomTabManager.this.numberOfTabs - 1; i2++) {
                    StageSwipeablePagesBottomTabManager.this.bottomTabBarLayout.addView((View) StageSwipeablePagesBottomTabManager.this.bottomTabButtonViews.get(i2));
                    StageSwipeablePagesBottomTabManager stageSwipeablePagesBottomTabManager = StageSwipeablePagesBottomTabManager.this;
                    stageSwipeablePagesBottomTabManager.initBottomTabSeparator(stageSwipeablePagesBottomTabManager.bottomTabBarLayout.getContext());
                }
                if (StageSwipeablePagesBottomTabManager.this.numberOfTabs > 0 && StageSwipeablePagesBottomTabManager.this.numberOfTabs - 1 < StageSwipeablePagesBottomTabManager.this.bottomTabButtonViews.size()) {
                    StageSwipeablePagesBottomTabManager.this.bottomTabBarLayout.addView((View) StageSwipeablePagesBottomTabManager.this.bottomTabButtonViews.get(StageSwipeablePagesBottomTabManager.this.numberOfTabs - 1));
                }
                StageSwipeablePagesBottomTabManager.this.bottomTabBarLayout.invalidate();
            }
        });
    }

    private void setupStyleAndActionListeners() {
        for (final int i = 0; i < this.numberOfTabs; i++) {
            this.bottomTabTextViews.get(i).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesBottomTabManager$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$setupStyleAndActionListeners$0(i, view);
                }
            });
        }
        if (this.bottomTabTextViews.isEmpty()) {
            return;
        }
        this.bottomTabStateManager.setActive(this.bottomTabTextViews.get(0), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupStyleAndActionListeners$0(int i, View view) {
        int i2 = this.currentActiveTabPos;
        if (i != i2) {
            this.bottomTabStateManager.setActive(this.bottomTabTextViews.get(i2), false);
            this.bottomTabStateManager.goToPage(i, this.currentActiveTabPos);
            this.currentActiveTabPos = i;
            this.bottomTabStateManager.setActive(this.bottomTabTextViews.get(i), true);
        }
    }

    public void highlightBottomTab(int i) {
        this.bottomTabStateManager.setActive(this.bottomTabTextViews.get(this.currentActiveTabPos), false);
        this.currentActiveTabPos = i;
        this.bottomTabStateManager.setActive(this.bottomTabTextViews.get(i), true);
    }

    private FrameLayout initBottomTabButtonView(Context context, String str) {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.bottom_tab_bar_button, (ViewGroup) null, false);
        TextView textView = (TextView) frameLayout.findViewById(R.id.bottom_button_textView);
        textView.setText(str);
        textView.setContentDescription(str);
        this.bottomTabTextViews.add(textView);
        return frameLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initBottomTabSeparator(Context context) {
        View view = new View(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) context.getResources().getDimension(R.dimen.spacer_1), (int) context.getResources().getDimension(R.dimen.spacer_24));
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.primary_glass_4));
        Resources resources = context.getResources();
        int i = R.dimen.spacer_2;
        view.setPadding(0, (int) resources.getDimension(i), 0, (int) context.getResources().getDimension(i));
        view.setLayoutParams(layoutParams);
        this.bottomTabBarLayout.addView(view);
    }
}
