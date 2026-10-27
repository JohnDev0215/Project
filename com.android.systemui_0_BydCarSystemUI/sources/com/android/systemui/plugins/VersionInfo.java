package com.android.systemui.plugins;

import android.util.ArrayMap;
import com.android.systemui.plugins.VersionInfo;
import com.android.systemui.plugins.annotations.Dependencies;
import com.android.systemui.plugins.annotations.DependsOn;
import com.android.systemui.plugins.annotations.ProvidesInterface;
import com.android.systemui.plugins.annotations.Requirements;
import com.android.systemui.plugins.annotations.Requires;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes.dex */
public class VersionInfo {
    private Class<?> mDefault;
    private final ArrayMap<Class<?>, Version> mVersions = new ArrayMap<>();

    public boolean hasVersionInfo() {
        return !this.mVersions.isEmpty();
    }

    public int getDefaultVersion() {
        return this.mVersions.get(this.mDefault).mVersion;
    }

    public VersionInfo addClass(Class<?> cls) {
        if (this.mDefault == null) {
            this.mDefault = cls;
        }
        addClass(cls, false);
        return this;
    }

    private void addClass(Class<?> cls, boolean required) {
        if (this.mVersions.containsKey(cls)) {
            return;
        }
        ProvidesInterface providesInterface = (ProvidesInterface) cls.getDeclaredAnnotation(ProvidesInterface.class);
        if (providesInterface != null) {
            this.mVersions.put(cls, new Version(providesInterface.version(), true));
        }
        Requires requires = (Requires) cls.getDeclaredAnnotation(Requires.class);
        if (requires != null) {
            this.mVersions.put(requires.target(), new Version(requires.version(), required));
        }
        Requirements requirements = (Requirements) cls.getDeclaredAnnotation(Requirements.class);
        if (requirements != null) {
            for (Requires requires2 : requirements.value()) {
                this.mVersions.put(requires2.target(), new Version(requires2.version(), required));
            }
        }
        DependsOn dependsOn = (DependsOn) cls.getDeclaredAnnotation(DependsOn.class);
        if (dependsOn != null) {
            addClass(dependsOn.target(), true);
        }
        Dependencies dependencies = (Dependencies) cls.getDeclaredAnnotation(Dependencies.class);
        if (dependencies != null) {
            for (DependsOn dependsOn2 : dependencies.value()) {
                addClass(dependsOn2.target(), true);
            }
        }
    }

    public void checkVersion(VersionInfo plugin) throws InvalidVersionException {
        final ArrayMap arrayMap = new ArrayMap(this.mVersions);
        plugin.mVersions.forEach(new BiConsumer() { // from class: com.android.systemui.plugins.-$$Lambda$VersionInfo$yYTgXK7PYJqT1P5ouo0K0io_be8
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.lambda$checkVersion$0$VersionInfo(arrayMap, (Class) obj, (VersionInfo.Version) obj2);
            }
        });
        arrayMap.forEach(new BiConsumer() { // from class: com.android.systemui.plugins.-$$Lambda$VersionInfo$eGfTcyX6tPpgNJW8C0LWWrdUAu8
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                VersionInfo.lambda$checkVersion$1((Class) obj, (VersionInfo.Version) obj2);
            }
        });
    }

    public /* synthetic */ void lambda$checkVersion$0$VersionInfo(ArrayMap arrayMap, Class cls, Version version) {
        Version versionCreateVersion = (Version) arrayMap.remove(cls);
        if (versionCreateVersion == null) {
            versionCreateVersion = createVersion(cls);
        }
        if (versionCreateVersion == null) {
            throw new InvalidVersionException(cls.getSimpleName() + " does not provide an interface", false);
        }
        if (versionCreateVersion.mVersion != version.mVersion) {
            throw new InvalidVersionException(cls, versionCreateVersion.mVersion < version.mVersion, versionCreateVersion.mVersion, version.mVersion);
        }
    }

    static /* synthetic */ void lambda$checkVersion$1(Class cls, Version version) {
        if (version.mRequired) {
            throw new InvalidVersionException("Missing required dependency " + cls.getSimpleName(), false);
        }
    }

    private Version createVersion(Class<?> cls) {
        ProvidesInterface providesInterface = (ProvidesInterface) cls.getDeclaredAnnotation(ProvidesInterface.class);
        if (providesInterface != null) {
            return new Version(providesInterface.version(), false);
        }
        return null;
    }

    public <T> boolean hasClass(Class<T> cls) {
        return this.mVersions.containsKey(cls);
    }

    public static class InvalidVersionException extends RuntimeException {
        private final boolean mTooNew;

        public InvalidVersionException(String str, boolean tooNew) {
            super(str);
            this.mTooNew = tooNew;
        }

        public InvalidVersionException(Class<?> cls, boolean tooNew, int expected, int actual) {
            super(cls.getSimpleName() + " expected version " + expected + " but had " + actual);
            this.mTooNew = tooNew;
        }

        public boolean isTooNew() {
            return this.mTooNew;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class Version {
        private final boolean mRequired;
        private final int mVersion;

        public Version(int version, boolean required) {
            this.mVersion = version;
            this.mRequired = required;
        }
    }
}
