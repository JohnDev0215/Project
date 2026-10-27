package com.byd.music.fragment.radio;

import androidx.recyclerview.widget.DiffUtil;
import com.byd.music.beans.RadioDBBean;

/* JADX INFO: loaded from: classes5.dex */
public class RadioDiffCallback extends DiffUtil.ItemCallback<RadioDBBean> {
    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public boolean areItemsTheSame(RadioDBBean radioDBBean, RadioDBBean radioDBBean2) {
        return radioDBBean.equals(radioDBBean2);
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public boolean areContentsTheSame(RadioDBBean radioDBBean, RadioDBBean radioDBBean2) {
        return radioDBBean.isTheSameRadio(radioDBBean2);
    }
}
