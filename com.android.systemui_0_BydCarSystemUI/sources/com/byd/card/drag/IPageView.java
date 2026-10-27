package com.byd.card.drag;

import android.graphics.Point;
import android.view.animation.Animation;
import com.byd.card.drag.CombineView;

/* JADX INFO: loaded from: classes.dex */
public interface IPageView {
    void addAppIcon(ApplicationInfo app, boolean isShow);

    void addToFolder(int i, ApplicationInfo app);

    void clearUp(ApplicationInfo info);

    void createFolderBound(int index);

    void deselect();

    Point getCorrectIconLocation(int index);

    ApplicationInfo getIcon(int index);

    Point getIconLocation(int index);

    int getIconsCount();

    boolean getNeedMoveAnimation();

    ApplicationInfo getSelectedApp();

    int getSelectedIndex();

    int hitTest2(int x, int y, HitResult hitTest2, boolean isFolder);

    int hitTest2Rtl(int x, int y, HitResult hitTest2, boolean isFolder);

    void hitTest3(int x, int y, HitResult hitTest3);

    void jiggle();

    void removeApp(int index);

    void removeFolderBound();

    void select(int index);

    void setAppMoveAnimationListener(Animation.AnimationListener animationListener);

    void setAppMoveListener(CombineView.AppMoveListener listener);

    void setIconIntoPage(int index, ApplicationInfo info);

    boolean setMoveTo(int index);

    void unJiggle();

    void updateLayout(ApplicationInfo app, int leltOrRight);
}
