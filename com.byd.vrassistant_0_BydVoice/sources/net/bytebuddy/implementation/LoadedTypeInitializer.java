package net.bytebuddy.implementation;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.utility.privilege.SetAccessibleAction;

/* JADX INFO: loaded from: classes3.dex */
public interface LoadedTypeInitializer {

    public enum NoOp implements LoadedTypeInitializer {
        INSTANCE;

        @Override // net.bytebuddy.implementation.LoadedTypeInitializer
        public boolean isAlive() {
            return false;
        }

        @Override // net.bytebuddy.implementation.LoadedTypeInitializer
        public void onLoad(Class<?> cls) {
        }
    }

    boolean isAlive();

    void onLoad(Class<?> cls);

    public static class ForStaticField implements LoadedTypeInitializer, Serializable {
        private static final Object STATIC_FIELD = null;
        private static final long serialVersionUID = 1;
        private final String fieldName;
        private final Object value;

        @Override // net.bytebuddy.implementation.LoadedTypeInitializer
        public boolean isAlive() {
            return true;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ForStaticField;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForStaticField)) {
                return false;
            }
            ForStaticField forStaticField = (ForStaticField) obj;
            if (!forStaticField.canEqual(this)) {
                return false;
            }
            String str = this.fieldName;
            String str2 = forStaticField.fieldName;
            if (str != null ? !str.equals(str2) : str2 != null) {
                return false;
            }
            Object obj2 = this.value;
            Object obj3 = forStaticField.value;
            return obj2 != null ? obj2.equals(obj3) : obj3 == null;
        }

        public int hashCode() {
            String str = this.fieldName;
            int iHashCode = str == null ? 43 : str.hashCode();
            Object obj = this.value;
            return ((iHashCode + 59) * 59) + (obj != null ? obj.hashCode() : 43);
        }

        protected ForStaticField(String str, Object obj) {
            this.fieldName = str;
            this.value = obj;
        }

        @Override // net.bytebuddy.implementation.LoadedTypeInitializer
        public void onLoad(Class<?> cls) {
            try {
                Field declaredField = cls.getDeclaredField(this.fieldName);
                if (!Modifier.isPublic(declaredField.getModifiers()) || !Modifier.isPublic(declaredField.getDeclaringClass().getModifiers())) {
                    AccessController.doPrivileged(new SetAccessibleAction(declaredField));
                }
                declaredField.set(STATIC_FIELD, this.value);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Cannot access " + this.fieldName + " from " + cls, e);
            } catch (NoSuchFieldException e2) {
                throw new IllegalStateException("There is no field " + this.fieldName + " defined on " + cls, e2);
            }
        }
    }

    public static class Compound implements LoadedTypeInitializer, Serializable {
        private static final long serialVersionUID = 1;
        private final List<LoadedTypeInitializer> loadedTypeInitializers;

        protected boolean canEqual(Object obj) {
            return obj instanceof Compound;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Compound)) {
                return false;
            }
            Compound compound = (Compound) obj;
            if (!compound.canEqual(this)) {
                return false;
            }
            List<LoadedTypeInitializer> list = this.loadedTypeInitializers;
            List<LoadedTypeInitializer> list2 = compound.loadedTypeInitializers;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<LoadedTypeInitializer> list = this.loadedTypeInitializers;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Compound(LoadedTypeInitializer... loadedTypeInitializerArr) {
            this((List<? extends LoadedTypeInitializer>) Arrays.asList(loadedTypeInitializerArr));
        }

        public Compound(List<? extends LoadedTypeInitializer> list) {
            this.loadedTypeInitializers = new ArrayList();
            for (LoadedTypeInitializer loadedTypeInitializer : list) {
                if (loadedTypeInitializer instanceof Compound) {
                    this.loadedTypeInitializers.addAll(((Compound) loadedTypeInitializer).loadedTypeInitializers);
                } else if (!(loadedTypeInitializer instanceof NoOp)) {
                    this.loadedTypeInitializers.add(loadedTypeInitializer);
                }
            }
        }

        @Override // net.bytebuddy.implementation.LoadedTypeInitializer
        public void onLoad(Class<?> cls) {
            Iterator<LoadedTypeInitializer> it = this.loadedTypeInitializers.iterator();
            while (it.hasNext()) {
                it.next().onLoad(cls);
            }
        }

        @Override // net.bytebuddy.implementation.LoadedTypeInitializer
        public boolean isAlive() {
            Iterator<LoadedTypeInitializer> it = this.loadedTypeInitializers.iterator();
            while (it.hasNext()) {
                if (it.next().isAlive()) {
                    return true;
                }
            }
            return false;
        }
    }
}
