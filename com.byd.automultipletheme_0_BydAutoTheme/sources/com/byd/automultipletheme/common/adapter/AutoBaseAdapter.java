package com.byd.automultipletheme.common.adapter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.byd.automultipletheme.common.AssetsUtils;
import com.byd.automultipletheme.common.IntentString;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: AutoBaseAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 P*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u0004:\u0001PB\u0005¢\u0006\u0002\u0010\u0005J\u0014\u00100\u001a\u00020\u001d2\f\b\u0001\u00101\u001a\u000202\"\u00020\bJY\u00103\u001a\u00020\u001d2Q\u0010\u0015\u001aM\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001d0\u0016J@\u00104\u001a\u00020\u001d26\u0010\"\u001a2\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001d0#H\u0016J\u000e\u00105\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J!\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u000209H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010;J \u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\bH&J\u0015\u0010C\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u001c\u001a\u00020\b¢\u0006\u0002\u0010DJ\b\u0010E\u001a\u00020\bH\u0016J'\u0010F\u001a\u00020\u001d2\u0006\u0010G\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\b2\b\u0010H\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010IJ\u0018\u0010J\u001a\u00020\u001d2\u0006\u0010G\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\bH\u0016J\u0018\u0010K\u001a\u00020\u00032\u0006\u0010L\u001a\u00020A2\u0006\u0010B\u001a\u00020\bH\u0016J\u0016\u0010M\u001a\u00020\u001d2\f\u0010N\u001a\b\u0012\u0004\u0012\u00028\u00000OH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014Re\u0010\u0015\u001aM\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001d0\u0016X\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R>\u0010\"\u001a2\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001d0#X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Q"}, d2 = {"Lcom/byd/automultipletheme/common/adapter/AutoBaseAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/byd/automultipletheme/common/adapter/AutoBaseHolder;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "childClickViewIds", "Ljava/util/LinkedHashSet;", "", "clickTime", "", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "mList", "", "getMList", "()Ljava/util/List;", "setMList", "(Ljava/util/List;)V", "onItemChildClick", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "adapter", "Landroid/view/View;", "view", IntentString.DATA_POSITION, "", "getOnItemChildClick", "()Lkotlin/jvm/functions/Function3;", "setOnItemChildClick", "(Lkotlin/jvm/functions/Function3;)V", "onItemClick", "Lkotlin/Function2;", "options", "Lcom/bumptech/glide/request/RequestOptions;", "getOptions", "()Lcom/bumptech/glide/request/RequestOptions;", "setOptions", "(Lcom/bumptech/glide/request/RequestOptions;)V", "roundedCorners", "Lcom/bumptech/glide/load/resource/bitmap/RoundedCorners;", "getRoundedCorners", "()Lcom/bumptech/glide/load/resource/bitmap/RoundedCorners;", "setRoundedCorners", "(Lcom/bumptech/glide/load/resource/bitmap/RoundedCorners;)V", "addChildClickViewIds", "viewIds", "", "addOnItemChildClickListener", "addOnItemClickListener", "getChildClickViewIds", "getConformBitmap", "Landroid/graphics/Bitmap;", "backgroundUrl", "", "foregroundUrl", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDataBinding", "Landroidx/viewbinding/ViewBinding;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "viewType", "getItem", "(I)Ljava/lang/Object;", "getItemCount", "onBindData", "holder", "item", "(Lcom/byd/automultipletheme/common/adapter/AutoBaseHolder;ILjava/lang/Object;)V", "onBindViewHolder", "onCreateViewHolder", "parent", "setData", IntentString.DATA_LIST, "Ljava/util/ArrayList;", "Companion", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AutoBaseAdapter<T> extends RecyclerView.Adapter<AutoBaseHolder> implements CoroutineScope {
    public static final int INTERVAL_DEFAULT_VALUE = 666;
    private final LinkedHashSet<Integer> childClickViewIds = new LinkedHashSet<>();
    private long clickTime;
    private List<T> mList;
    protected Function3<? super AutoBaseAdapter<T>, ? super View, ? super Integer, Unit> onItemChildClick;
    private Function2<? super View, ? super Integer, Unit> onItemClick;
    private RequestOptions options;
    private RoundedCorners roundedCorners;

    /* JADX INFO: renamed from: com.byd.automultipletheme.common.adapter.AutoBaseAdapter$getConformBitmap$1, reason: invalid class name */
    /* JADX INFO: compiled from: AutoBaseAdapter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.common.adapter.AutoBaseAdapter", f = "AutoBaseAdapter.kt", i = {}, l = {150}, m = "getConformBitmap", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ AutoBaseAdapter<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AutoBaseAdapter<T> autoBaseAdapter, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = autoBaseAdapter;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.getConformBitmap(null, null, this);
        }
    }

    public abstract ViewBinding getDataBinding(LayoutInflater inflater, ViewGroup container, int viewType);

    public abstract void onBindData(AutoBaseHolder holder, int position, T item);

    public AutoBaseAdapter() {
        RoundedCorners roundedCorners = new RoundedCorners(SizeUtils.dp2px(4.0f));
        this.roundedCorners = roundedCorners;
        RequestOptions requestOptionsBitmapTransform = RequestOptions.bitmapTransform(roundedCorners);
        Intrinsics.checkNotNullExpressionValue(requestOptionsBitmapTransform, "bitmapTransform(roundedCorners)");
        this.options = requestOptionsBitmapTransform;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return Dispatchers.getMain();
    }

    public final List<T> getMList() {
        return this.mList;
    }

    public final void setMList(List<T> list) {
        this.mList = list;
    }

    protected final Function3<AutoBaseAdapter<T>, View, Integer, Unit> getOnItemChildClick() {
        Function3<? super AutoBaseAdapter<T>, ? super View, ? super Integer, Unit> function3 = this.onItemChildClick;
        if (function3 != null) {
            return function3;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onItemChildClick");
        return null;
    }

    protected final void setOnItemChildClick(Function3<? super AutoBaseAdapter<T>, ? super View, ? super Integer, Unit> function3) {
        Intrinsics.checkNotNullParameter(function3, "<set-?>");
        this.onItemChildClick = function3;
    }

    public final RoundedCorners getRoundedCorners() {
        return this.roundedCorners;
    }

    public final void setRoundedCorners(RoundedCorners roundedCorners) {
        Intrinsics.checkNotNullParameter(roundedCorners, "<set-?>");
        this.roundedCorners = roundedCorners;
    }

    public final RequestOptions getOptions() {
        return this.options;
    }

    public final void setOptions(RequestOptions requestOptions) {
        Intrinsics.checkNotNullParameter(requestOptions, "<set-?>");
        this.options = requestOptions;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public AutoBaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(parent.getContext());
        Intrinsics.checkNotNullExpressionValue(layoutInflaterFrom, "from(parent.context)");
        View root = getDataBinding(layoutInflaterFrom, parent, viewType).getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return new AutoBaseHolder(root);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(AutoBaseHolder holder, final int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        onBindData(holder, position, getItem(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.common.adapter.AutoBaseAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AutoBaseAdapter.m51onBindViewHolder$lambda0(this.f$0, position, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindViewHolder$lambda-0, reason: not valid java name */
    public static final void m51onBindViewHolder$lambda0(AutoBaseAdapter this$0, int i, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Math.abs(TimeUtils.getTimeSpanByNow(this$0.clickTime, 1)) > 666) {
            Function2<? super View, ? super Integer, Unit> function2 = this$0.onItemClick;
            if (function2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onItemClick");
                function2 = null;
            }
            Intrinsics.checkNotNullExpressionValue(it, "it");
            function2.invoke(it, Integer.valueOf(i));
            this$0.clickTime = TimeUtils.getNowMills();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.mList;
        if (list == null) {
            return 0;
        }
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    public final T getItem(int position) {
        List<T> list = this.mList;
        if (list == null) {
            return null;
        }
        Intrinsics.checkNotNull(list);
        return list.get(position);
    }

    private final LinkedHashSet<Integer> getChildClickViewIds() {
        return this.childClickViewIds;
    }

    public final void addChildClickViewIds(int... viewIds) {
        Intrinsics.checkNotNullParameter(viewIds, "viewIds");
        for (int i : viewIds) {
            this.childClickViewIds.add(Integer.valueOf(i));
        }
    }

    public void addOnItemClickListener(Function2<? super View, ? super Integer, Unit> onItemClick) {
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        this.onItemClick = onItemClick;
    }

    public final void addOnItemChildClickListener(Function3<? super AutoBaseAdapter<T>, ? super View, ? super Integer, Unit> onItemChildClick) {
        Intrinsics.checkNotNullParameter(onItemChildClick, "onItemChildClick");
        setOnItemChildClick(onItemChildClick);
    }

    public void setData(final ArrayList<T> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        if (this.mList == null) {
            this.mList = list;
            notifyItemRangeInserted(0, list.size());
        } else {
            DiffUtil.DiffResult diffResultCalculateDiff = DiffUtil.calculateDiff(new DiffUtil.Callback(this) { // from class: com.byd.automultipletheme.common.adapter.AutoBaseAdapter$setData$result$1
                final /* synthetic */ AutoBaseAdapter<T> this$0;

                {
                    this.this$0 = this;
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public int getOldListSize() {
                    List mList = this.this$0.getMList();
                    Intrinsics.checkNotNull(mList);
                    return mList.size();
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public int getNewListSize() {
                    return list.size();
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    List mList = this.this$0.getMList();
                    Intrinsics.checkNotNull(mList);
                    return Objects.equals(mList.get(oldItemPosition), list.get(newItemPosition));
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    List mList = this.this$0.getMList();
                    Intrinsics.checkNotNull(mList);
                    return Objects.equals(mList.get(oldItemPosition), list.get(newItemPosition));
                }
            });
            Intrinsics.checkNotNullExpressionValue(diffResultCalculateDiff, "open fun setData(list: A…sTo(this)\n        }\n    }");
            this.mList = list;
            diffResultCalculateDiff.dispatchUpdatesTo(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getConformBitmap(java.lang.String r6, java.lang.String r7, kotlin.coroutines.Continuation<? super android.graphics.Bitmap> r8) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.byd.automultipletheme.common.adapter.AutoBaseAdapter.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r8
            com.byd.automultipletheme.common.adapter.AutoBaseAdapter$getConformBitmap$1 r0 = (com.byd.automultipletheme.common.adapter.AutoBaseAdapter.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.byd.automultipletheme.common.adapter.AutoBaseAdapter$getConformBitmap$1 r0 = new com.byd.automultipletheme.common.adapter.AutoBaseAdapter$getConformBitmap$1
            r0.<init>(r5, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L4c
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            com.byd.automultipletheme.common.adapter.AutoBaseAdapter$getConformBitmap$2 r2 = new com.byd.automultipletheme.common.adapter.AutoBaseAdapter$getConformBitmap$2
            r4 = 0
            r2.<init>(r6, r7, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)
            if (r8 != r1) goto L4c
            return r1
        L4c:
            java.lang.String r6 = "backgroundUrl: String, f…         newBmp\n        }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.automultipletheme.common.adapter.AutoBaseAdapter.getConformBitmap(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.common.adapter.AutoBaseAdapter$getConformBitmap$2, reason: invalid class name */
    /* JADX INFO: compiled from: AutoBaseAdapter.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001\"\u0004\b\u0000\u0010\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "kotlin.jvm.PlatformType", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.common.adapter.AutoBaseAdapter$getConformBitmap$2", f = "AutoBaseAdapter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
        final /* synthetic */ String $backgroundUrl;
        final /* synthetic */ String $foregroundUrl;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, String str2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$backgroundUrl = str;
            this.$foregroundUrl = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$backgroundUrl, this.$foregroundUrl, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Bitmap assetsBitmap = AssetsUtils.INSTANCE.getAssetsBitmap(this.$backgroundUrl);
                Bitmap assetsBitmap2 = AssetsUtils.INSTANCE.getAssetsBitmap(this.$foregroundUrl);
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(assetsBitmap.getWidth(), assetsBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                canvas.drawBitmap(assetsBitmap, 0.0f, 0.0f, (Paint) null);
                canvas.drawBitmap(assetsBitmap2, 0.0f, 0.0f, (Paint) null);
                canvas.save();
                canvas.restore();
                return bitmapCreateBitmap;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
