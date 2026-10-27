package com.fasterxml.jackson.dataformat.xml.deser;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.dataformat.xml.util.AnnotationUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class XmlBeanDeserializerModifier extends BeanDeserializerModifier implements Serializable {
    private static final long serialVersionUID = 1;
    protected String _cfgNameForTextValue;

    public XmlBeanDeserializerModifier(String str) {
        this._cfgNameForTextValue = str;
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerModifier
    public List<BeanPropertyDefinition> updateProperties(DeserializationConfig deserializationConfig, BeanDescription beanDescription, List<BeanPropertyDefinition> list) {
        String simpleName;
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        int size = list.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            BeanPropertyDefinition beanPropertyDefinition = list.get(i2);
            AnnotatedMember primaryMember = beanPropertyDefinition.getPrimaryMember();
            if (primaryMember != null) {
                Boolean boolFindIsTextAnnotation = AnnotationUtil.findIsTextAnnotation(annotationIntrospector, primaryMember);
                if (boolFindIsTextAnnotation != null && boolFindIsTextAnnotation.booleanValue()) {
                    BeanPropertyDefinition beanPropertyDefinitionWithSimpleName = beanPropertyDefinition.withSimpleName(this._cfgNameForTextValue);
                    if (beanPropertyDefinitionWithSimpleName != beanPropertyDefinition) {
                        list.set(i2, beanPropertyDefinitionWithSimpleName);
                    }
                } else {
                    PropertyName wrapperName = beanPropertyDefinition.getWrapperName();
                    if (wrapperName != null && wrapperName != PropertyName.NO_NAME && (simpleName = wrapperName.getSimpleName()) != null && simpleName.length() > 0 && !simpleName.equals(beanPropertyDefinition.getName())) {
                        if (i == 0) {
                            list = new ArrayList(list);
                        }
                        i++;
                        list.set(i2, beanPropertyDefinition.withSimpleName(simpleName));
                    }
                }
            }
        }
        return list;
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerModifier
    public JsonDeserializer<?> modifyDeserializer(DeserializationConfig deserializationConfig, BeanDescription beanDescription, JsonDeserializer<?> jsonDeserializer) {
        SettableBeanProperty settableBeanProperty_findSoleTextProp;
        if (!(jsonDeserializer instanceof BeanDeserializerBase)) {
            return jsonDeserializer;
        }
        BeanDeserializerBase beanDeserializerBase = (BeanDeserializerBase) jsonDeserializer;
        if (!beanDeserializerBase.getValueInstantiator().canCreateFromString() && (settableBeanProperty_findSoleTextProp = _findSoleTextProp(deserializationConfig, beanDeserializerBase.properties())) != null) {
            return new XmlTextDeserializer(beanDeserializerBase, settableBeanProperty_findSoleTextProp);
        }
        return new WrapperHandlingDeserializer(beanDeserializerBase);
    }

    private SettableBeanProperty _findSoleTextProp(DeserializationConfig deserializationConfig, Iterator<SettableBeanProperty> it) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        SettableBeanProperty settableBeanProperty = null;
        while (it.hasNext()) {
            SettableBeanProperty next = it.next();
            AnnotatedMember member = next.getMember();
            if (member != null) {
                if (this._cfgNameForTextValue.equals(next.getFullName().getSimpleName())) {
                    settableBeanProperty = next;
                } else {
                    Boolean boolFindIsAttributeAnnotation = AnnotationUtil.findIsAttributeAnnotation(annotationIntrospector, member);
                    if (boolFindIsAttributeAnnotation == null || !boolFindIsAttributeAnnotation.booleanValue()) {
                    }
                }
            }
            return null;
        }
        return settableBeanProperty;
    }
}
