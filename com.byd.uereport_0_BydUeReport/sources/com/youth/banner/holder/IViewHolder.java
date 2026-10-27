package com.youth.banner.holder;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes2.dex */
public interface IViewHolder<T, VH> {
    void onBindView(VH vh, T t7, int i8, int i9);

    VH onCreateHolder(ViewGroup viewGroup, int i8);
}
