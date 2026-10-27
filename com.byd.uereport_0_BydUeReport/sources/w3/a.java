package w3;

import com.byd.mvp.callback.PagerBtnType;

/* JADX INFO: compiled from: OnBtnClickedCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public interface a {
    default void onBtnClicked(PagerBtnType pagerBtnType) {
    }

    default void onBtnClicked(Class<?> cls, PagerBtnType pagerBtnType) {
    }

    default void onBtnClicked(Class<?> cls, PagerBtnType pagerBtnType, Object obj, Object obj2) {
    }
}
