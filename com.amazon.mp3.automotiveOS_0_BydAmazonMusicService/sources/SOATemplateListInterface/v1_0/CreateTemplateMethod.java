package SOATemplateListInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class CreateTemplateMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("SOATemplateListInterface.v1_0.CreateTemplateMethod");
    private String screenMode;
    private Template template;

    public static class Builder extends Method.Builder {
        protected String screenMode;
        protected Template template;

        public CreateTemplateMethod build() {
            CreateTemplateMethod createTemplateMethod = new CreateTemplateMethod();
            populate(createTemplateMethod);
            return createTemplateMethod;
        }

        protected void populate(CreateTemplateMethod createTemplateMethod) {
            super.populate((Method) createTemplateMethod);
            createTemplateMethod.setScreenMode(this.screenMode);
            createTemplateMethod.setTemplate(this.template);
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

        public Builder withScreenMode(String str) {
            this.screenMode = str;
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
        if (!(sOAObject instanceof CreateTemplateMethod)) {
            return 1;
        }
        CreateTemplateMethod createTemplateMethod = (CreateTemplateMethod) sOAObject;
        String screenMode = getScreenMode();
        String screenMode2 = createTemplateMethod.getScreenMode();
        if (screenMode != screenMode2) {
            if (screenMode == null) {
                return -1;
            }
            if (screenMode2 == null) {
                return 1;
            }
            int iCompareTo = screenMode.compareTo(screenMode2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Template template = getTemplate();
        Template template2 = createTemplateMethod.getTemplate();
        if (template != template2) {
            if (template == null) {
                return -1;
            }
            if (template2 == null) {
                return 1;
            }
            int iCompareTo2 = template.compareTo(template2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof CreateTemplateMethod)) {
            return false;
        }
        CreateTemplateMethod createTemplateMethod = (CreateTemplateMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getScreenMode(), createTemplateMethod.getScreenMode()) && internalEqualityCheck(getTemplate(), createTemplateMethod.getTemplate());
    }

    public String getScreenMode() {
        return this.screenMode;
    }

    public Template getTemplate() {
        return this.template;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getScreenMode(), getTemplate());
    }

    public void setScreenMode(String str) {
        this.screenMode = str;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
