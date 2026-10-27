package com.amazon.music.skyfire.core.template;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import CoreInterface.v1_0.TemplateLaunchMode;
import TemplateListInterface.v1_0.BindTemplateMethod;
import TemplateListInterface.v1_0.ClearTemplatesMethod;
import TemplateListInterface.v1_0.CreateAndBindTemplateMethod;
import TemplateListInterface.v1_0.CreateTemplateMethod;
import TemplateListInterface.v1_0.InvalidateTemplatesMethod;
import TemplateListInterface.v1_0.RemoveTemplateMethod;
import com.amazon.music.skyfire.core.OwnerRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract class TemplateStack {
    private static final Logger logger = LoggerFactory.getLogger("TemplateStack");
    private OwnerRegistry ownerRegistry;
    private final Map<String, List<Method>> methodLookup = new HashMap();
    private final Stack<TemplateInstance> templateStack = new Stack<>();

    protected abstract void bindTemplateToTopInstance(String str, BindTemplateMethod bindTemplateMethod, TemplateInstance templateInstance);

    protected abstract void createTemplateInstance(TemplateInstanceCreationObject templateInstanceCreationObject);

    public void setOwnerRegistry(OwnerRegistry ownerRegistry) {
        this.ownerRegistry = ownerRegistry;
    }

    void handleMethod(String str, Method method) {
        if (method instanceof CreateTemplateMethod) {
            createTemplate(str, (CreateTemplateMethod) method);
            return;
        }
        if (method instanceof CreateAndBindTemplateMethod) {
            createAndBindTemplate(str, (CreateAndBindTemplateMethod) method);
            return;
        }
        if (method instanceof BindTemplateMethod) {
            bindTemplate(str, (BindTemplateMethod) method);
            return;
        }
        if (method instanceof ClearTemplatesMethod) {
            clearTemplates(str, (ClearTemplatesMethod) method);
            return;
        }
        if (method instanceof InvalidateTemplatesMethod) {
            invalidateTemplates(str, (InvalidateTemplatesMethod) method);
        } else if (method instanceof RemoveTemplateMethod) {
            removeTemplate(str, (RemoveTemplateMethod) method);
        } else if (this.ownerRegistry.isOwnerAvailable(str)) {
            delegateOrParkMethod(str, method);
        }
    }

    protected void unParkMethods(String str) {
        if (this.methodLookup.containsKey(str)) {
            List<Method> list = this.methodLookup.get(str);
            this.methodLookup.remove(str);
            Iterator<Method> it = list.iterator();
            while (it.hasNext()) {
                handleMethod(str, it.next());
            }
        }
    }

    protected void finishSingleInstanceTemplate(TemplateInstance templateInstance) {
        if (this.templateStack.size() <= 1) {
            return;
        }
        ArrayList<TemplateInstance> arrayList = new ArrayList();
        for (int i = 0; i < this.templateStack.size() - 1; i++) {
            TemplateInstance templateInstance2 = this.templateStack.get(i);
            Template template = templateInstance2.getTemplate();
            if (TemplateLaunchMode.singleInstance == template.launchMode() && templateInstance.getTemplate().getClass() == template.getClass()) {
                arrayList.add(templateInstance2);
            }
        }
        for (TemplateInstance templateInstance3 : arrayList) {
            logger.debug("finishing singleInstance template instance", templateInstance3);
            this.templateStack.remove(templateInstance3);
            this.methodLookup.remove(templateInstance3.getOwnerId());
            templateInstance3.closeAsSingleInstance();
        }
    }

    protected void finishSingleUseTemplate(TemplateInstance templateInstance) {
        if (templateInstance.getTemplate().launchMode() == TemplateLaunchMode.singleUse) {
            logger.debug("finishing singleUse template instance", templateInstance);
            this.templateStack.remove(templateInstance);
            this.methodLookup.remove(templateInstance.getOwnerId());
            this.ownerRegistry.unregisterOwner(templateInstance.getOwnerId());
            templateInstance.closeAsSingleUse();
        }
    }

    protected void finishTemplate(TemplateInstance templateInstance) {
        this.templateStack.remove(templateInstance);
        this.methodLookup.remove(templateInstance.getOwnerId());
        templateInstance.close();
        logger.debug("finishTemplate, stack size " + this.templateStack.size());
    }

    protected void removeTemplate(TemplateInstance templateInstance) {
        this.templateStack.remove(templateInstance);
        this.methodLookup.remove(templateInstance.getOwnerId());
        logger.debug("removeTemplate, stack size " + this.templateStack.size());
    }

    protected TemplateInstance getTopTemplateInstance() {
        if (this.templateStack.empty()) {
            return null;
        }
        return this.templateStack.peek();
    }

    protected int getTemplateStackSize() {
        return this.templateStack.size();
    }

    protected TemplateInstance getTemplateInstanceAt(int i) {
        return this.templateStack.get(i);
    }

    protected boolean isOwnerInTemplateStack(String str) {
        if (str != null) {
            Iterator<TemplateInstance> it = this.templateStack.iterator();
            while (it.hasNext()) {
                if (str.startsWith(it.next().getOwnerId())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void createTemplate(String str, CreateTemplateMethod createTemplateMethod) {
        createTemplateInstance(new TemplateInstanceCreationObject(str, createTemplateMethod, createTemplateMethod.template()));
    }

    private void createAndBindTemplate(String str, CreateAndBindTemplateMethod createAndBindTemplateMethod) {
        createTemplateInstance(new TemplateInstanceCreationObject(str, createAndBindTemplateMethod, createAndBindTemplateMethod.template()));
    }

    private void bindTemplate(String str, BindTemplateMethod bindTemplateMethod) {
        if (this.ownerRegistry.isOwnerAvailable(str) && !this.templateStack.isEmpty()) {
            TemplateInstance templateInstancePeek = this.templateStack.peek();
            if (str.startsWith(templateInstancePeek.getOwnerId())) {
                bindTemplateToTopInstance(str, bindTemplateMethod, templateInstancePeek);
            }
        }
    }

    private void removeTemplate(String str, RemoveTemplateMethod removeTemplateMethod) {
        for (TemplateInstance templateInstance : this.templateStack) {
            if (str.startsWith(templateInstance.getOwnerId())) {
                this.templateStack.remove(templateInstance);
                this.methodLookup.remove(templateInstance.getOwnerId());
                templateInstance.close();
                return;
            }
        }
    }

    private void delegateOrParkMethod(String str, Method method) {
        TemplateInstance templateInstancePeek = this.templateStack.peek();
        if (!str.startsWith(templateInstancePeek.getOwnerId())) {
            parkMethod(method, str);
        } else {
            templateInstancePeek.handleTemplateMethod(str, method);
        }
    }

    private void clearTemplates(String str, ClearTemplatesMethod clearTemplatesMethod) {
        for (TemplateInstance templateInstance : this.templateStack) {
            this.methodLookup.remove(templateInstance.getOwnerId());
            templateInstance.close();
        }
        this.templateStack.clear();
    }

    private void invalidateTemplates(String str, InvalidateTemplatesMethod invalidateTemplatesMethod) {
        for (TemplateInstance templateInstance : this.templateStack) {
            this.methodLookup.remove(templateInstance.getOwnerId());
            templateInstance.invalidate();
        }
    }

    private void parkMethod(Method method, String str) {
        List<Method> list;
        if (this.methodLookup.containsKey(str)) {
            list = this.methodLookup.get(str);
        } else {
            ArrayList arrayList = new ArrayList();
            this.methodLookup.put(str, arrayList);
            list = arrayList;
        }
        list.add(method);
    }

    protected class TemplateInstanceCreationObject {
        public final Method method;
        public final String ownerId;
        public final Template template;

        public TemplateInstanceCreationObject(String str, Method method, Template template) {
            this.ownerId = str;
            this.method = method;
            this.template = template;
        }

        public void onTemplateInstanceCreated(TemplateInstance templateInstance) {
            Template template;
            TemplateStack.logger.debug("stack count " + TemplateStack.this.templateStack.size());
            Method method = this.method;
            boolean z = false;
            if (method instanceof CreateTemplateMethod) {
                template = ((CreateTemplateMethod) method).template();
                z = true;
            } else {
                if (!(method instanceof CreateAndBindTemplateMethod)) {
                    TemplateStack.logger.error("could not create or bind template to template instance", templateInstance, this.ownerId, this.method);
                    return;
                }
                template = ((CreateAndBindTemplateMethod) method).template();
            }
            if (z) {
                templateInstance.create(template);
            } else {
                templateInstance.createAndBind(template);
            }
            TemplateStack.this.templateStack.push(templateInstance);
            TemplateStack.logger.debug("onTemplateInstanceCreated, stack size " + TemplateStack.this.templateStack.size());
        }
    }
}
