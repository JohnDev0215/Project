package com.amazon.podcast.ptc;

import Podcast.Touch.PTCTemplateInterface.v1_0.InvalidatePTCCacheMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCEndMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCWriteCacheElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.SetPTCPreferencesMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.WritePTCCacheMethod;
import SOACacheInterface.v1_0.WriteCacheElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.soa.core.Engine;
import com.amazon.soa.core.MethodCallback;
import com.amazon.soa.core.OnMethodExecutedListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastTasteCollection implements MethodCallback, MethodsDispatcher {
    private Context context;
    private final Engine engine;

    public PodcastTasteCollection(final Engine engine) {
        this.engine = engine;
        HashSet hashSet = new HashSet();
        hashSet.add(SetPTCPreferencesMethod.class.getCanonicalName());
        hashSet.add(InvalidatePTCCacheMethod.class.getCanonicalName());
        hashSet.add(PTCEndMethod.class.getCanonicalName());
        hashSet.add(WritePTCCacheMethod.class.getCanonicalName());
        hashSet.add(PTCWriteCacheElement.class.getCanonicalName());
        engine.registerMethods(Collections.EMPTY_SET, hashSet, this);
    }

    public final void init(final Context context) {
        this.context = context;
    }

    @Override // com.amazon.soa.core.MethodCallback
    public final void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof SetPTCPreferencesMethod) {
            setPTCPreferences((SetPTCPreferencesMethod) method);
        } else if (method instanceof InvalidatePTCCacheMethod) {
            clearPTCTrailerCache((InvalidatePTCCacheMethod) method);
        } else if (method instanceof PTCEndMethod) {
            executePTCEndBasedOnCache((PTCEndMethod) method, ownerId);
        } else if (method instanceof WritePTCCacheMethod) {
            writePTCCache((WritePTCCacheMethod) method);
        }
        if (listener == null) {
            return;
        }
        listener.onMethodExecuted(method);
    }

    private void writePTCCache(final WritePTCCacheMethod method) {
        WriteCacheElement element = method.getElement();
        if (element instanceof PTCWriteCacheElement) {
            PTCPreferences.INSTANCE.setPTCTrailerCache((PTCWriteCacheElement) element);
        }
    }

    private void clearPTCTrailerCache(final InvalidatePTCCacheMethod method) {
        PTCPreferences.INSTANCE.clearPTCTrailerCache();
    }

    private void executePTCEndBasedOnCache(final PTCEndMethod method, final String ownerId) {
        if (PTCPreferences.INSTANCE.getPTCTrailerCacheValue()) {
            dispatch(ownerId, method.getOnTrailerPlayed());
        } else {
            dispatch(ownerId, method.getOnTrailerNotPlayed());
        }
    }

    @Override // com.amazon.podcast.MethodsDispatcher
    public final void dispatch(final String ownerId, final List<Method> methods) {
        this.engine.handleMethods(ownerId, methods);
    }

    public final void clear(final Context context) {
        PTCPreferences.INSTANCE.clearData(context);
    }

    private final void setPTCPreferences(final SetPTCPreferencesMethod method) {
        PTCPreferences.INSTANCE.writeShowPTCIngress(this.context, method.isShowPTCIngress());
    }
}
