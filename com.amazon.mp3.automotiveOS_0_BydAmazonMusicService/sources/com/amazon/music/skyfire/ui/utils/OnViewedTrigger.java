package com.amazon.music.skyfire.ui.utils;

import CoreInterface.v1_0.Method;
import InteractionInterface.v1_0.GlobalInvokeHttpSkillMethod;
import android.util.LruCache;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: OnViewedTrigger.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/skyfire/ui/utils/OnViewedTrigger;", "", "()V", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OnViewedTrigger {
    private static final int CACHE_SIZE = 5;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final LruCache<String, Set<String>> cache = new LruCache<>(5);

    /* JADX INFO: compiled from: OnViewedTrigger.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/skyfire/ui/utils/OnViewedTrigger$Companion;", "", "()V", "CACHE_SIZE", "", "cache", "Landroid/util/LruCache;", "", "", "emitOnViewed", "", "dispatcherContext", "Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "methods", "", "LCoreInterface/v1_0/Method;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void emitOnViewed(DispatcherContext dispatcherContext, List<? extends Method> methods) {
            Unit unit;
            Intrinsics.checkNotNullParameter(methods, "methods");
            if (dispatcherContext == null) {
                return;
            }
            String listenerId = dispatcherContext.getListenerId();
            if (OnViewedTrigger.cache.get(listenerId) == null) {
                OnViewedTrigger.cache.put(listenerId, new LinkedHashSet());
            }
            for (Method method : methods) {
                GlobalInvokeHttpSkillMethod globalInvokeHttpSkillMethod = method instanceof GlobalInvokeHttpSkillMethod ? (GlobalInvokeHttpSkillMethod) method : null;
                if (globalInvokeHttpSkillMethod == null) {
                    unit = null;
                } else {
                    Object obj = OnViewedTrigger.cache.get(listenerId);
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableSet<kotlin.String>");
                    }
                    Set setAsMutableSet = TypeIntrinsics.asMutableSet(obj);
                    if (!setAsMutableSet.contains(globalInvokeHttpSkillMethod.url())) {
                        String strUrl = globalInvokeHttpSkillMethod.url();
                        Intrinsics.checkNotNullExpressionValue(strUrl, "it.url()");
                        setAsMutableSet.add(strUrl);
                        SkyFireKt.dispatch$default(dispatcherContext, new Method[]{method}, (String) null, 2, (Object) null);
                    }
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    Companion companion = OnViewedTrigger.INSTANCE;
                    SkyFireKt.dispatch$default(dispatcherContext, new Method[]{method}, (String) null, 2, (Object) null);
                }
            }
        }
    }
}
