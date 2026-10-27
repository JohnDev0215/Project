package com.byd.automultipletheme.adapter;

import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.bean.AutoTheme;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.adapter.AutoBaseHolder;
import com.byd.automultipletheme.databinding.AdapterThemeItemBinding;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: ThemeAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.byd.automultipletheme.adapter.ThemeAdapter$onBindData$1$1", f = "ThemeAdapter.kt", i = {}, l = {62}, m = "invokeSuspend", n = {}, s = {})
final class ThemeAdapter$onBindData$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AdapterThemeItemBinding $binding;
    final /* synthetic */ AutoBaseHolder $holder;
    final /* synthetic */ AutoTheme $it;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ThemeAdapter$onBindData$1$1(AutoTheme autoTheme, AdapterThemeItemBinding adapterThemeItemBinding, AutoBaseHolder autoBaseHolder, Continuation<? super ThemeAdapter$onBindData$1$1> continuation) {
        super(2, continuation);
        this.$it = autoTheme;
        this.$binding = adapterThemeItemBinding;
        this.$holder = autoBaseHolder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ThemeAdapter$onBindData$1$1(this.$it, this.$binding, this.$holder, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ThemeAdapter$onBindData$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.adapter.ThemeAdapter$onBindData$1$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: ThemeAdapter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.adapter.ThemeAdapter$onBindData$1$1$1", f = "ThemeAdapter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AdapterThemeItemBinding $binding;
        final /* synthetic */ AutoBaseHolder $holder;
        final /* synthetic */ AutoTheme $it;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AutoTheme autoTheme, AdapterThemeItemBinding adapterThemeItemBinding, AutoBaseHolder autoBaseHolder, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$it = autoTheme;
            this.$binding = adapterThemeItemBinding;
            this.$holder = autoBaseHolder;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$it, this.$binding, this.$holder, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (this.$it.getRsId() == AutoUtils.INSTANCE.getLocalThemeId()) {
                if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
                    this.$binding.itemCurrent.setBackgroundResource(R.mipmap.ic_current_rtl);
                }
                this.$binding.itemCurrent.setVisibility(0);
                this.$binding.itemAction.setText(R.string.str_using);
                if (AutoUtils.INSTANCE.getModelType() == 3) {
                    this.$binding.itemCurrent.setBackground(null);
                    this.$binding.itemAction.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.bnt_text_color_denza));
                    this.$binding.itemAction.setBackgroundResource(R.drawable.ic_item_button_bg_inuse_denza);
                } else {
                    this.$binding.itemAction.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.inUse_color));
                    this.$binding.itemAction.setBackgroundResource(R.drawable.ic_item_button_bg_inuse);
                }
                this.$binding.itemAction.setEnabled(false);
                this.$holder.itemView.setSelected(true);
            } else {
                this.$binding.itemCurrent.setVisibility(8);
                this.$binding.itemAction.setText(R.string.str_use_now);
                if (AutoUtils.INSTANCE.getModelType() == 3) {
                    this.$binding.itemAction.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.bnt_text_color_denza));
                    this.$binding.itemAction.setBackgroundResource(R.drawable.ic_item_button_bg_apply_denza);
                } else {
                    this.$binding.itemAction.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.inUse_color));
                    this.$binding.itemAction.setBackgroundResource(R.drawable.ic_item_button_bg_apply);
                }
                this.$binding.itemAction.setEnabled(true);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(this.$it, this.$binding, this.$holder, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
