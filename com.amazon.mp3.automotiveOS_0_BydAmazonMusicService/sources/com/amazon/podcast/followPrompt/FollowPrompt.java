package com.amazon.podcast.followPrompt;

import Podcast.FollowPromptInterface.v1_0.WriteHidePromptPreferencesMethod;
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
public final class FollowPrompt implements MethodCallback, MethodsDispatcher {
    private Context context;
    private final Engine engine;

    public FollowPrompt(final Engine engine) {
        this.engine = engine;
        HashSet hashSet = new HashSet();
        hashSet.add(WriteHidePromptPreferencesMethod.class.getCanonicalName());
        engine.registerMethods(Collections.EMPTY_SET, hashSet, this);
    }

    @Override // com.amazon.soa.core.MethodCallback
    public final void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof WriteHidePromptPreferencesMethod) {
            writeHidePromptPreferences((WriteHidePromptPreferencesMethod) method, listener);
        }
    }

    @Override // com.amazon.podcast.MethodsDispatcher
    public final void dispatch(final String ownerId, final List<Method> methods) {
        this.engine.handleMethods(ownerId, methods);
    }

    public final void init(final Context context) {
        this.context = context;
    }

    public final void clear(final Context context) {
        FollowPromptPreferences.INSTANCE.clearData(context);
    }

    private void writeHidePromptPreferences(final WriteHidePromptPreferencesMethod method, final OnMethodExecutedListener listener) {
        FollowPromptPreferences.INSTANCE.writeHidePromptPreference(this.context, method.getPodcastId(), method.getPreference());
        if (listener == null) {
            return;
        }
        listener.onMethodExecuted(method);
    }
}
