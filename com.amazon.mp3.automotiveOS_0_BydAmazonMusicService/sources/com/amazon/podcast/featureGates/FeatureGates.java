package com.amazon.podcast.featureGates;

import Podcast.FeatureGatingInterface.v1_0.SetBooleanFeaturesMethod;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.soa.core.Engine;
import com.amazon.soa.core.MethodCallback;
import com.amazon.soa.core.OnMethodExecutedListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class FeatureGates implements MethodCallback, MethodsDispatcher {
    private Context context;
    private final Engine engine;

    public FeatureGates(final Engine engine) {
        this.engine = engine;
        HashSet hashSet = new HashSet();
        hashSet.add(SetBooleanFeaturesMethod.class.getCanonicalName());
        engine.registerMethods(Collections.EMPTY_SET, hashSet, this);
    }

    @Override // com.amazon.soa.core.MethodCallback
    public final void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof SetBooleanFeaturesMethod) {
            writeFeatureGatePreferences((SetBooleanFeaturesMethod) method, listener);
        }
    }

    @Override // com.amazon.podcast.MethodsDispatcher
    public final void dispatch(final String ownerId, final List<Method> methods) {
        this.engine.handleMethods(ownerId, methods);
    }

    public final void init(final Context context) {
        this.context = context;
    }

    public final void clear() {
        if (Podcast.getFeatureGatePreferences() == null) {
            return;
        }
        Podcast.getFeatureGatePreferences().clearData();
    }

    private void writeFeatureGatePreferences(final SetBooleanFeaturesMethod method, final OnMethodExecutedListener listener) {
        Podcast.getFeatureGatePreferences().writeFeatureGatePreferences(method.getFeatures());
        if (listener == null) {
            return;
        }
        listener.onMethodExecuted(method);
    }
}
