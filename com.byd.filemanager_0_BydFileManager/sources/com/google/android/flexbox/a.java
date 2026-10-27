package com.google.android.flexbox;

import android.view.View;
import java.util.List;

/* JADX INFO: compiled from: FlexContainer.java */
/* JADX INFO: loaded from: classes.dex */
interface a {
    View a(int i);

    int b(View view, int i, int i2);

    void c(b bVar);

    int d(int i, int i2, int i3);

    View f(int i);

    void g(View view, int i, int i2, b bVar);

    int getAlignContent();

    int getAlignItems();

    int getFlexDirection();

    int getFlexItemCount();

    List<b> getFlexLinesInternal();

    int getFlexWrap();

    int getLargestMainSize();

    int getMaxLine();

    int getPaddingBottom();

    int getPaddingEnd();

    int getPaddingLeft();

    int getPaddingRight();

    int getPaddingStart();

    int getPaddingTop();

    int getSumOfCrossSize();

    int h(int i, int i2, int i3);

    void i(int i, View view);

    boolean j();

    int k(View view);

    void setFlexLines(List<b> list);
}
