package com.byd.music.fragment.radio;

import androidx.recyclerview.widget.DiffUtil;
import com.byd.music.beans.RadioEpgDBBean;

/* JADX INFO: loaded from: classes5.dex */
public class RadioEpgDiffCallback extends DiffUtil.ItemCallback<RadioEpgDBBean> {
    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public boolean areItemsTheSame(RadioEpgDBBean radioEpgDBBean, RadioEpgDBBean radioEpgDBBean2) {
        return radioEpgDBBean == radioEpgDBBean2 || radioEpgDBBean.getDabSid() == radioEpgDBBean2.getDabSid();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public boolean areContentsTheSame(RadioEpgDBBean radioEpgDBBean, RadioEpgDBBean radioEpgDBBean2) {
        return radioEpgDBBean.equals(radioEpgDBBean2);
    }
}
