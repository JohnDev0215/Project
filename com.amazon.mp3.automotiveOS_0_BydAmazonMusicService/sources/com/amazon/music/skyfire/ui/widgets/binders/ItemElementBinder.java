package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Element;
import android.view.View;
import kotlin.Metadata;

/* JADX INFO: compiled from: ItemElementBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0005B\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u00012\u0006\u0010\u000b\u001a\u00020\fH&¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/ItemElementBinder;", "E", "LCoreInterface/v1_0/Element;", "V", "Landroid/view/View;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "()V", "bind", "", "element", "view", "width", "", "(LCoreInterface/v1_0/Element;Landroid/view/View;I)V", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class ItemElementBinder<E extends Element, V extends View> extends ElementBinder<E, V> {
    public abstract void bind(E element, V view, int width);
}
