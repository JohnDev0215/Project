package io.objectbox.ideasonly;

/* JADX INFO: loaded from: classes5.dex */
public class ModelModifier {

    public class EntityModifier {
        final String name;
        final String schemaName;

        public void remove() {
        }

        public void renameTo(String str) {
        }

        EntityModifier(String str, String str2) {
            this.schemaName = str;
            this.name = str2;
        }

        public PropertyModifier property(String str) {
            return ModelModifier.this.new PropertyModifier(this, str);
        }
    }

    public class PropertyModifier {
        final EntityModifier entityModifier;
        final String name;

        public void remove() {
        }

        public void renameTo(String str) {
        }

        PropertyModifier(EntityModifier entityModifier, String str) {
            this.entityModifier = entityModifier;
            this.name = str;
        }
    }

    public EntityModifier entity(String str) {
        return new EntityModifier("default", str);
    }
}
