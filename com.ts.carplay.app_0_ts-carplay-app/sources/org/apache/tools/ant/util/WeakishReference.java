package org.apache.tools.ant.util;

import java.lang.reflect.Constructor;
import org.apache.tools.ant.BuildException;

/* JADX INFO: loaded from: classes.dex */
public abstract class WeakishReference {
    private static final String WEAK_REFERENCE_NAME = "org.apache.tools.ant.util.optional.WeakishReference12";
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$org$apache$tools$ant$util$WeakishReference$HardReference;
    private static Constructor referenceConstructor;

    public abstract Object get();

    public static WeakishReference createReference(Object object) {
        if (referenceConstructor == null) {
            createReferenceConstructor();
        }
        try {
            return (WeakishReference) referenceConstructor.newInstance(object);
        } catch (Exception e) {
            throw new BuildException("while creating a weakish reference", e);
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    private static void createReferenceConstructor() {
        Class clsClass$;
        Class<?>[] clsArr = new Class[1];
        Class<?> clsClass$2 = class$java$lang$Object;
        if (clsClass$2 == null) {
            clsClass$2 = class$("java.lang.Object");
            class$java$lang$Object = clsClass$2;
        }
        clsArr[0] = clsClass$2;
        try {
            if (class$org$apache$tools$ant$util$WeakishReference$HardReference == null) {
                clsClass$ = class$("org.apache.tools.ant.util.WeakishReference$HardReference");
                class$org$apache$tools$ant$util$WeakishReference$HardReference = clsClass$;
            } else {
                clsClass$ = class$org$apache$tools$ant$util$WeakishReference$HardReference;
            }
            referenceConstructor = clsClass$.getConstructor(clsArr);
            if (!JavaEnvUtils.isJavaVersion("1.1")) {
                try {
                    referenceConstructor = Class.forName(WEAK_REFERENCE_NAME).getConstructor(clsArr);
                } catch (ClassNotFoundException e) {
                } catch (NoSuchMethodException e2) {
                }
            }
        } catch (NoSuchMethodException e3) {
            throw new BuildException("when creating a Hard Reference constructor", e3);
        }
    }

    public static class HardReference extends WeakishReference {
        private Object object;

        public HardReference(Object object) {
            this.object = object;
        }

        @Override // org.apache.tools.ant.util.WeakishReference
        public Object get() {
            return this.object;
        }
    }
}
