package androidx.leanback.widget;

import android.content.Context;
import android.view.View;
import androidx.leanback.widget.ItemBridgeAdapter;

/* JADX INFO: loaded from: classes.dex */
public class ItemBridgeAdapterShadowOverlayWrapper extends ItemBridgeAdapter.Wrapper {
    private final ShadowOverlayHelper mHelper;

    public ItemBridgeAdapterShadowOverlayWrapper(ShadowOverlayHelper helper) {
        this.mHelper = helper;
    }

    @Override // androidx.leanback.widget.ItemBridgeAdapter.Wrapper
    public View createWrapper(View root) {
        Context context = root.getContext();
        ShadowOverlayContainer wrapper = this.mHelper.createShadowOverlayContainer(context);
        return wrapper;
    }

    @Override // androidx.leanback.widget.ItemBridgeAdapter.Wrapper
    public void wrap(View wrapper, View wrapped) {
        ((ShadowOverlayContainer) wrapper).wrap(wrapped);
    }
}
