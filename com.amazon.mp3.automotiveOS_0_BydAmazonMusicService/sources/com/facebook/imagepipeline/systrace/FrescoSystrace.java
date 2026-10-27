package com.facebook.imagepipeline.systrace;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class FrescoSystrace {
    public static final ArgsBuilder NO_OP_ARGS_BUILDER = new NoOpArgsBuilder();

    @Nullable
    private static volatile Systrace sInstance = null;

    public interface ArgsBuilder {
    }

    public interface Systrace {
        void beginSection(String name);

        void endSection();

        boolean isTracing();
    }

    private FrescoSystrace() {
    }

    public static void beginSection(String name) {
        getInstance().beginSection(name);
    }

    public static void endSection() {
        getInstance().endSection();
    }

    public static boolean isTracing() {
        return getInstance().isTracing();
    }

    private static Systrace getInstance() {
        if (sInstance == null) {
            synchronized (FrescoSystrace.class) {
                if (sInstance == null) {
                    sInstance = new DefaultFrescoSystrace();
                }
            }
        }
        return sInstance;
    }

    private static final class NoOpArgsBuilder implements ArgsBuilder {
        private NoOpArgsBuilder() {
        }
    }
}
