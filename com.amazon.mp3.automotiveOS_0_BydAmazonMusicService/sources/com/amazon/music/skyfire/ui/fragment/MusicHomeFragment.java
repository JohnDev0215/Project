package com.amazon.music.skyfire.ui.fragment;

import CoreInterface.v1_0.Template;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.amazon.music.skyfire.ui.providers.ChromeProvider;
import com.amazon.music.views.library.views.presentation.TranslucentActionBarDecorator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicHomeFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0006H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u001a\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/skyfire/ui/fragment/MusicHomeFragment;", "T", "LCoreInterface/v1_0/Template;", "Lcom/amazon/music/skyfire/ui/fragment/TemplateFragment;", "()V", "actionBarView", "Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", "getActionBarView", "()Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", "setActionBarView", "(Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;)V", "chromeProvider", "Lcom/amazon/music/skyfire/ui/providers/ChromeProvider;", "getChromeProvider", "()Lcom/amazon/music/skyfire/ui/providers/ChromeProvider;", "setChromeProvider", "(Lcom/amazon/music/skyfire/ui/providers/ChromeProvider;)V", "onCreateActionBar", "onResume", "", "onStyleActionBar", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class MusicHomeFragment<T extends Template> extends TemplateFragment<T> {
    protected TranslucentActionBarDecorator actionBarView;
    public ChromeProvider chromeProvider;

    public void onStyleActionBar() {
    }

    public final ChromeProvider getChromeProvider() {
        ChromeProvider chromeProvider = this.chromeProvider;
        if (chromeProvider != null) {
            return chromeProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("chromeProvider");
        return null;
    }

    public final void setChromeProvider(ChromeProvider chromeProvider) {
        Intrinsics.checkNotNullParameter(chromeProvider, "<set-?>");
        this.chromeProvider = chromeProvider;
    }

    protected final TranslucentActionBarDecorator getActionBarView() {
        TranslucentActionBarDecorator translucentActionBarDecorator = this.actionBarView;
        if (translucentActionBarDecorator != null) {
            return translucentActionBarDecorator;
        }
        Intrinsics.throwUninitializedPropertyAccessException("actionBarView");
        return null;
    }

    protected final void setActionBarView(TranslucentActionBarDecorator translucentActionBarDecorator) {
        Intrinsics.checkNotNullParameter(translucentActionBarDecorator, "<set-?>");
        this.actionBarView = translucentActionBarDecorator;
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        setActionBarView(onCreateActionBar());
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        onStyleActionBar();
    }

    public TranslucentActionBarDecorator onCreateActionBar() {
        ChromeProvider chromeProvider = getChromeProvider();
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        Intrinsics.checkNotNullExpressionValue(activity, "activity!!");
        return chromeProvider.createTranslucentActionBarDecorator(activity, false, Boolean.FALSE);
    }
}
