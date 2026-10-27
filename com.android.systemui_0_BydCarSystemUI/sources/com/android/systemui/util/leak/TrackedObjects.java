package com.android.systemui.util.leak;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class TrackedObjects {
    private final WeakHashMap<Class<?>, TrackedClass<?>> mTrackedClasses = new WeakHashMap<>();
    private final TrackedCollections mTrackedCollections;

    public TrackedObjects(TrackedCollections trackedCollections) {
        this.mTrackedCollections = trackedCollections;
    }

    public synchronized <T> void track(T object) {
        Class<?> cls = object.getClass();
        TrackedClass<?> trackedClass = this.mTrackedClasses.get(cls);
        if (trackedClass == null) {
            trackedClass = new TrackedClass<>();
            this.mTrackedClasses.put(cls, trackedClass);
        }
        trackedClass.track(object);
        this.mTrackedCollections.track(trackedClass, cls.getName());
    }

    public static boolean isTrackedObject(Collection<?> collection) {
        return collection instanceof TrackedClass;
    }

    private static class TrackedClass<T> extends AbstractCollection<T> {
        final WeakIdentityHashMap<T, Void> instances;

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<T> iterator() {
            return null;
        }

        private TrackedClass() {
            this.instances = new WeakIdentityHashMap<>();
        }

        void track(T object) {
            this.instances.put(object, null);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.instances.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.instances.isEmpty();
        }
    }
}
