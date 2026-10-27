package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: UniversalBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005J\u001d\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\r\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0011H&¢\u0006\u0002\u0010\u0012R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/views/library/binders/UniversalBinder;", "View", "Landroid/view/View;", "Model", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "(Landroid/view/View;Lcom/amazon/music/views/library/models/base/BaseViewModel;)V", "createView", "context", "Landroid/content/Context;", "(Landroid/content/Context;)Landroid/view/View;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface UniversalBinder<View extends View, Model extends BaseViewModel> {
    void bind(View view, Model model);

    View createView(Context context);

    KClass<Model> getModelClass();
}
