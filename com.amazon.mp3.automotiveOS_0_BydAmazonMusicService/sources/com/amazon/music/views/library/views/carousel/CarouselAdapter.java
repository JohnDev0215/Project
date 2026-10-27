package com.amazon.music.views.library.views.carousel;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.models.FeatureBarkerModel;
import com.amazon.music.views.library.views.FeatureBarkerView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarouselAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0010\u001a\u00020\u00112\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0012J \u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\bH&J\u0006\u0010\u0017\u001a\u00020\u0011J\b\u0010\u0018\u001a\u00020\u0003H&J\b\u0010\u0019\u001a\u00020\bH\u0016J\r\u0010\u001a\u001a\u00020\bH\u0000¢\u0006\u0002\b\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0011H\u0002J\u001e\u0010\u001f\u001a\u00020\u00112\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0016\u001a\u00020\bH\u0016J\u001e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0016\u001a\u00020\bH\u0016J\b\u0010$\u001a\u00020\u0011H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/music/views/library/views/carousel/CarouselAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/music/views/library/views/carousel/CarouselViewHolder;", "Lcom/amazon/music/views/library/views/FeatureBarkerView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "DEFAULT_MIDDLE_POSITION", "", "handler", "Landroid/os/Handler;", ContextMappingConstants.ITEMS, "Ljava/util/ArrayList;", "Lcom/amazon/music/views/library/models/FeatureBarkerModel;", "mContext", "mPositionOffset", "addItems", "", "", "bindView", "view", "model", "position", "clear", "createView", "getItemCount", "getMiddle", "getMiddle$DMMViewLibrary_release", "isAccessibilityModeOn", "", "notifyDataSetChangedOnUiThread", "onBindViewHolder", "viewHolder", "onCreateViewHolder", "viewGroup", "Landroid/view/ViewGroup;", "setPositionOffset", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class CarouselAdapter extends RecyclerView.Adapter<CarouselViewHolder<FeatureBarkerView>> {
    private final int DEFAULT_MIDDLE_POSITION;
    private final Handler handler;
    private final ArrayList<FeatureBarkerModel> items;
    private Context mContext;
    private int mPositionOffset;

    public abstract void bindView(FeatureBarkerView view, FeatureBarkerModel model, int position);

    public abstract FeatureBarkerView createView();

    public CarouselAdapter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.handler = new Handler(Looper.getMainLooper());
        this.items = new ArrayList<>();
        this.mContext = context;
    }

    public final void addItems(List<FeatureBarkerModel> items) {
        if (items == null || items.size() <= 0) {
            return;
        }
        this.items.addAll(items);
        setPositionOffset();
        notifyDataSetChangedOnUiThread();
    }

    public final void clear() {
        this.items.clear();
    }

    public final int getMiddle$DMMViewLibrary_release() {
        if (!isAccessibilityModeOn()) {
            return getItemCount() / 2;
        }
        return this.DEFAULT_MIDDLE_POSITION;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CarouselViewHolder<FeatureBarkerView> onCreateViewHolder(ViewGroup viewGroup, int position) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        return new CarouselViewHolder<>(createView());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(CarouselViewHolder<FeatureBarkerView> viewHolder, int position) {
        int size;
        FeatureBarkerModel featureBarkerModel;
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        int i = this.mPositionOffset;
        if (position - i >= 0) {
            size = (position - i) % this.items.size();
            FeatureBarkerModel featureBarkerModel2 = this.items.get(size);
            Intrinsics.checkNotNullExpressionValue(featureBarkerModel2, "items[itemPosition]");
            featureBarkerModel = featureBarkerModel2;
        } else {
            size = position % this.items.size();
            FeatureBarkerModel featureBarkerModel3 = this.items.get(size);
            Intrinsics.checkNotNullExpressionValue(featureBarkerModel3, "items[itemPosition]");
            featureBarkerModel = featureBarkerModel3;
        }
        bindView((FeatureBarkerView) viewHolder.itemView, featureBarkerModel, size);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int size = this.items.size();
        if (!isAccessibilityModeOn() && size > 1) {
            return Integer.MAX_VALUE;
        }
        return size;
    }

    private final void notifyDataSetChangedOnUiThread() {
        this.handler.post(new Runnable() { // from class: com.amazon.music.views.library.views.carousel.CarouselAdapter$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CarouselAdapter.m2437notifyDataSetChangedOnUiThread$lambda0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: notifyDataSetChangedOnUiThread$lambda-0, reason: not valid java name */
    public static final void m2437notifyDataSetChangedOnUiThread$lambda0(CarouselAdapter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.notifyDataSetChanged();
    }

    private final boolean isAccessibilityModeOn() {
        try {
            return Settings.Secure.getInt(this.mContext.getContentResolver(), "accessibility_enabled") == 1;
        } catch (Settings.SettingNotFoundException unused) {
            return false;
        }
    }

    private final void setPositionOffset() {
        if (isAccessibilityModeOn()) {
            return;
        }
        this.mPositionOffset = getMiddle$DMMViewLibrary_release() % this.items.size();
    }
}
