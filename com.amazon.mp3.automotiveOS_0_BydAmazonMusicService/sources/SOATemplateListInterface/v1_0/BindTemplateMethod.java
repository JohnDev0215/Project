package SOATemplateListInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class BindTemplateMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("SOATemplateListInterface.v1_0.BindTemplateMethod");
    private Template template;

    public static class Builder extends Method.Builder {
        protected Template template;

        public BindTemplateMethod build() {
            BindTemplateMethod bindTemplateMethod = new BindTemplateMethod();
            populate(bindTemplateMethod);
            return bindTemplateMethod;
        }

        protected void populate(BindTemplateMethod bindTemplateMethod) {
            super.populate((Method) bindTemplateMethod);
            bindTemplateMethod.setTemplate(this.template);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withQueue(Queue queue) {
            super.withQueue(queue);
            return this;
        }

        public Builder withTemplate(Template template) {
            this.template = template;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof BindTemplateMethod)) {
            return 1;
        }
        Template template = getTemplate();
        Template template2 = ((BindTemplateMethod) sOAObject).getTemplate();
        if (template != template2) {
            if (template == null) {
                return -1;
            }
            if (template2 == null) {
                return 1;
            }
            int iCompareTo = template.compareTo(template2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof BindTemplateMethod) {
            return super.equals(obj) && internalEqualityCheck(getTemplate(), ((BindTemplateMethod) obj).getTemplate());
        }
        return false;
    }

    public Template getTemplate() {
        return this.template;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTemplate());
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
