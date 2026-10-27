package com.shopify.reactnativeperformance;

import android.content.Context;
import android.view.View;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: PerformanceMarker.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001!B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001f\u001a\u00020 H\u0002R/\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR/\u0010\u000e\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR/\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0005\u001a\u0004\u0018\u00010\u00128F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\r\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R/\u0010\u0019\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\r\u001a\u0004\b\u001a\u0010\t\"\u0004\b\u001b\u0010\u000bR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/shopify/reactnativeperformance/PerformanceMarker;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "<set-?>", "", "componentInstanceId", "getComponentInstanceId", "()Ljava/lang/String;", "setComponentInstanceId", "(Ljava/lang/String;)V", "componentInstanceId$delegate", "Lcom/shopify/reactnativeperformance/PerformanceMarker$PerformanceMarkerProp;", "destinationScreen", "getDestinationScreen", "setDestinationScreen", "destinationScreen$delegate", "Lcom/shopify/reactnativeperformance/Interactive;", "interactive", "getInteractive", "()Lcom/shopify/reactnativeperformance/Interactive;", "setInteractive", "(Lcom/shopify/reactnativeperformance/Interactive;)V", "interactive$delegate", "renderPassName", "getRenderPassName", "setRenderPassName", "renderPassName$delegate", "reportedOnce", "", "sendRenderCompletionEventIfNeeded", "", "PerformanceMarkerProp", "shopify_react-native-performance_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PerformanceMarker extends View {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(PerformanceMarker.class, "destinationScreen", "getDestinationScreen()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PerformanceMarker.class, "renderPassName", "getRenderPassName()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PerformanceMarker.class, "interactive", "getInteractive()Lcom/shopify/reactnativeperformance/Interactive;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PerformanceMarker.class, "componentInstanceId", "getComponentInstanceId()Ljava/lang/String;", 0))};

    /* JADX INFO: renamed from: componentInstanceId$delegate, reason: from kotlin metadata */
    private final PerformanceMarkerProp componentInstanceId;

    /* JADX INFO: renamed from: destinationScreen$delegate, reason: from kotlin metadata */
    private final PerformanceMarkerProp destinationScreen;

    /* JADX INFO: renamed from: interactive$delegate, reason: from kotlin metadata */
    private final PerformanceMarkerProp interactive;

    /* JADX INFO: renamed from: renderPassName$delegate, reason: from kotlin metadata */
    private final PerformanceMarkerProp renderPassName;
    private boolean reportedOnce;

    public PerformanceMarker(Context context) {
        super(context);
        this.destinationScreen = new PerformanceMarkerProp();
        this.renderPassName = new PerformanceMarkerProp();
        this.interactive = new PerformanceMarkerProp();
        this.componentInstanceId = new PerformanceMarkerProp();
        setWillNotDraw(true);
    }

    public final String getDestinationScreen() {
        return (String) this.destinationScreen.getValue(this, $$delegatedProperties[0]);
    }

    public final void setDestinationScreen(String str) {
        this.destinationScreen.setValue2(this, $$delegatedProperties[0], str);
    }

    public final String getRenderPassName() {
        return (String) this.renderPassName.getValue(this, $$delegatedProperties[1]);
    }

    public final void setRenderPassName(String str) {
        this.renderPassName.setValue2(this, $$delegatedProperties[1], str);
    }

    public final Interactive getInteractive() {
        return (Interactive) this.interactive.getValue(this, $$delegatedProperties[2]);
    }

    public final void setInteractive(Interactive interactive) {
        this.interactive.setValue2(this, $$delegatedProperties[2], interactive);
    }

    public final String getComponentInstanceId() {
        return (String) this.componentInstanceId.getValue(this, $$delegatedProperties[3]);
    }

    public final void setComponentInstanceId(String str) {
        this.componentInstanceId.setValue2(this, $$delegatedProperties[3], str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendRenderCompletionEventIfNeeded() {
        String destinationScreen = getDestinationScreen();
        String renderPassName = getRenderPassName();
        Interactive interactive = getInteractive();
        String componentInstanceId = getComponentInstanceId();
        if (destinationScreen == null || renderPassName == null || interactive == null || componentInstanceId == null) {
            return;
        }
        if (this.reportedOnce) {
            throw new IllegalStateException("Cannot report render completion event multiple times.");
        }
        this.reportedOnce = true;
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString(TetheredMessageKey.timestamp, String.valueOf(System.currentTimeMillis()));
        writableMapCreateMap.putString("renderPassName", renderPassName);
        writableMapCreateMap.putString("interactive", interactive.toString());
        writableMapCreateMap.putString("destinationScreen", destinationScreen);
        writableMapCreateMap.putString("componentInstanceId", componentInstanceId);
        Context context = getContext();
        if (context == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        }
        ((RCTEventEmitter) ((ReactContext) context).getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "onRenderComplete", writableMapCreateMap);
    }

    /* JADX INFO: compiled from: PerformanceMarker.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0005J$\u0010\b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\t\u001a\u00020\u00042\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0096\u0002¢\u0006\u0002\u0010\fJ,\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u00042\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00018\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0010R\u0012\u0010\u0006\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/shopify/reactnativeperformance/PerformanceMarker$PerformanceMarkerProp;", "T", "", "Lkotlin/properties/ReadWriteProperty;", "Lcom/shopify/reactnativeperformance/PerformanceMarker;", "()V", "field", "Ljava/lang/Object;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Lcom/shopify/reactnativeperformance/PerformanceMarker;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "value", "(Lcom/shopify/reactnativeperformance/PerformanceMarker;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "shopify_react-native-performance_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class PerformanceMarkerProp<T> implements ReadWriteProperty<PerformanceMarker, T> {
        private T field;

        @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
        public /* bridge */ /* synthetic */ Object getValue(Object obj, KProperty kProperty) {
            return getValue((PerformanceMarker) obj, (KProperty<?>) kProperty);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.properties.ReadWriteProperty
        public /* bridge */ /* synthetic */ void setValue(PerformanceMarker performanceMarker, KProperty kProperty, Object obj) {
            setValue2(performanceMarker, (KProperty<?>) kProperty, obj);
        }

        public T getValue(PerformanceMarker thisRef, KProperty<?> property) {
            Intrinsics.checkNotNullParameter(thisRef, "thisRef");
            Intrinsics.checkNotNullParameter(property, "property");
            return this.field;
        }

        /* JADX INFO: renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(PerformanceMarker thisRef, KProperty<?> property, T value) {
            Intrinsics.checkNotNullParameter(thisRef, "thisRef");
            Intrinsics.checkNotNullParameter(property, "property");
            if (value == null) {
                throw new IllegalArgumentException("Property '" + property.getName() + "' cannot be set to null.");
            }
            if (this.field != null) {
                throw new IllegalStateException("Property '" + property.getName() + "' cannot be set multiple times. Make sure that you're asking React Native to create a new native instance when changing the '" + property.getName() + "' prop by supplying the 'key' prop as well.");
            }
            this.field = value;
            thisRef.sendRenderCompletionEventIfNeeded();
        }
    }
}
