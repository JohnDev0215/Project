package com.amazon.music.explore.views.swipeablePages.pages;

import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesAnimations;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesStyleSheet;
import com.amazon.music.explore.views.swipeablePages.peek.PeekProgressBar;
import com.amazon.music.skyfire.platform.MethodsDispatcher;

/* JADX INFO: loaded from: classes4.dex */
public abstract class SwipeablePageView extends SwipeablePageRelativeLayout {
    protected static final float PULL_DOWN_THRESHOLD = 100.0f;
    protected final SwipeablePagesAnimations animations;
    protected final Context context;
    protected boolean hasBeenViewed;
    private boolean isFirstPage;
    private boolean isLastPage;
    protected final MethodsDispatcher methodsDispatcher;
    private boolean onClose;
    protected float onTouchedX;
    protected float onTouchedY;
    protected final String ownerId;
    protected final PeekProgressBar progressBar;
    protected final Resources resources;
    protected final SwipeablePagesStyleSheet styleSheet;

    public abstract void begin();

    public abstract void bind(SwipeablePageElement swipeablePageElement);

    public abstract int getMaxProgress();

    public abstract View getVerticalContainer();

    public abstract void reset();

    public SwipeablePageView(String str, Context context, MethodsDispatcher methodsDispatcher, PeekProgressBar peekProgressBar, SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        super(context);
        this.hasBeenViewed = false;
        this.ownerId = str;
        this.context = context;
        this.methodsDispatcher = methodsDispatcher;
        this.progressBar = peekProgressBar;
        this.styleSheet = swipeablePagesStyleSheet;
        this.resources = getResources();
        this.animations = new SwipeablePagesAnimations();
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public MethodsDispatcher getMethodsDispatcher() {
        return this.methodsDispatcher;
    }

    public boolean isFirstPage() {
        return this.isFirstPage;
    }

    public boolean isLastPage() {
        return this.isLastPage;
    }

    public void setIsFirstPage(boolean z) {
        this.isFirstPage = z;
    }

    public void setIsLastPage(boolean z) {
        this.isLastPage = z;
    }

    public void onClose() {
        if (this.onClose) {
            return;
        }
        ((Activity) this.context).onBackPressed();
        this.onClose = true;
    }
}
