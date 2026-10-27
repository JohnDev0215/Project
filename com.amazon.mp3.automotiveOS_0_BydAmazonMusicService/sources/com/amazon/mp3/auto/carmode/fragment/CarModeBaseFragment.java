package com.amazon.mp3.auto.carmode.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.auto.carmode.CarModeNavigation;
import com.amazon.mp3.auto.carmode.OnBitmapChangeListener;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.BlurStyleKey;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: compiled from: CarModeBaseFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b,\u0010-J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u0006\u0010\b\u001a\u00020\u0007J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0004J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0004R\"\u0010\u0011\u001a\u00020\r8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0018\u001a\u00020\u00178\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R$\u0010&\u001a\u0004\u0018\u00010%8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u0006."}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModeBaseFragment;", "Landroidx/fragment/app/Fragment;", "Landroid/content/Context;", "context", "", "onAttach", "onDestroy", "", "getBlurRadius", "Landroid/graphics/Bitmap;", "bitmap", "Landroid/graphics/drawable/Drawable;", "createBackgroundBlur", "Landroid/view/View;", "targetView", "Lcom/amazon/mp3/auto/carmode/OnBitmapChangeListener;", "getBitmapChangeListener", "root", "Landroid/view/View;", "getRoot", "()Landroid/view/View;", "setRoot", "(Landroid/view/View;)V", "Lrx/subscriptions/CompositeSubscription;", "subscriptions", "Lrx/subscriptions/CompositeSubscription;", "getSubscriptions", "()Lrx/subscriptions/CompositeSubscription;", "setSubscriptions", "(Lrx/subscriptions/CompositeSubscription;)V", "Lcom/amazon/mp3/auto/carmode/CarModeNavigation;", "navigationListener", "Lcom/amazon/mp3/auto/carmode/CarModeNavigation;", "getNavigationListener", "()Lcom/amazon/mp3/auto/carmode/CarModeNavigation;", "setNavigationListener", "(Lcom/amazon/mp3/auto/carmode/CarModeNavigation;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setStyleSheet", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class CarModeBaseFragment extends Fragment {
    private CarModeNavigation navigationListener;
    protected View root;
    private StyleSheet styleSheet;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    protected final View getRoot() {
        View view = this.root;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("root");
        return null;
    }

    protected final void setRoot(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.root = view;
    }

    protected final CompositeSubscription getSubscriptions() {
        return this.subscriptions;
    }

    protected final CarModeNavigation getNavigationListener() {
        return this.navigationListener;
    }

    protected final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    protected final void setStyleSheet(StyleSheet styleSheet) {
        this.styleSheet = styleSheet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof CarModeNavigation) {
            this.navigationListener = (CarModeNavigation) context;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.subscriptions.unsubscribe();
        super.onDestroy();
        this.navigationListener = null;
    }

    public final float getBlurRadius() {
        StyleSheet styleSheet = this.styleSheet;
        Float blurStyle = styleSheet == null ? null : styleSheet.getBlurStyle(BlurStyleKey.BLUR_2);
        if (blurStyle != null) {
            return blurStyle.floatValue();
        }
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.blur_2, typedValue, true);
        return typedValue.getFloat();
    }

    protected final Drawable createBackgroundBlur(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Bitmap bitmapBackground = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        BitmapEffectUtils bitmapEffectUtils = BitmapEffectUtils.INSTANCE;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        Intrinsics.checkNotNullExpressionValue(bitmapBackground, "bitmapBackground");
        return bitmapEffectUtils.getBlurBitmapDrawable(contextRequireContext, bitmapBackground, getBlurRadius());
    }

    protected final OnBitmapChangeListener getBitmapChangeListener(final View targetView) {
        Intrinsics.checkNotNullParameter(targetView, "targetView");
        return new OnBitmapChangeListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeBaseFragment.getBitmapChangeListener.1
            @Override // com.amazon.mp3.auto.carmode.OnBitmapChangeListener
            public void onChange(Bitmap bitmap) {
                Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                targetView.setBackground(CarModeBaseFragment.this.createBackgroundBlur(bitmap));
            }
        };
    }
}
