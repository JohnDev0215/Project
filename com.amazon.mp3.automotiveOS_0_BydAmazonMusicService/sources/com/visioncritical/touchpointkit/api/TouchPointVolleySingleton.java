package com.visioncritical.touchpointkit.api;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TouchPointVolleySingleton.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000fR\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/visioncritical/touchpointkit/api/TouchPointVolleySingleton;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "requestQueue", "Lcom/android/volley/RequestQueue;", "getRequestQueue", "()Lcom/android/volley/RequestQueue;", "requestQueue$delegate", "Lkotlin/Lazy;", "addToRequestQueue", "", "T", "req", "Lcom/android/volley/Request;", "Companion", "touchpointkit_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TouchPointVolleySingleton {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile TouchPointVolleySingleton INSTANCE;

    /* JADX INFO: renamed from: requestQueue$delegate, reason: from kotlin metadata */
    private final Lazy requestQueue;

    /* JADX INFO: compiled from: TouchPointVolleySingleton.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/visioncritical/touchpointkit/api/TouchPointVolleySingleton$Companion;", "", "()V", "INSTANCE", "Lcom/visioncritical/touchpointkit/api/TouchPointVolleySingleton;", "getInstance", "context", "Landroid/content/Context;", "touchpointkit_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TouchPointVolleySingleton getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            TouchPointVolleySingleton touchPointVolleySingleton = TouchPointVolleySingleton.INSTANCE;
            if (touchPointVolleySingleton == null) {
                synchronized (this) {
                    touchPointVolleySingleton = TouchPointVolleySingleton.INSTANCE;
                    if (touchPointVolleySingleton == null) {
                        touchPointVolleySingleton = new TouchPointVolleySingleton(context);
                        TouchPointVolleySingleton.INSTANCE = touchPointVolleySingleton;
                    }
                }
            }
            return touchPointVolleySingleton;
        }
    }

    public TouchPointVolleySingleton(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.requestQueue = LazyKt__LazyJVMKt.lazy(new Function0<RequestQueue>() { // from class: com.visioncritical.touchpointkit.api.TouchPointVolleySingleton$requestQueue$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RequestQueue invoke() {
                return Volley.newRequestQueue(context.getApplicationContext());
            }
        });
    }

    private final RequestQueue getRequestQueue() {
        Object value = this.requestQueue.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-requestQueue>(...)");
        return (RequestQueue) value;
    }

    public final <T> void addToRequestQueue(Request<T> req) {
        Intrinsics.checkNotNullParameter(req, "req");
        getRequestQueue().add(req);
    }
}
