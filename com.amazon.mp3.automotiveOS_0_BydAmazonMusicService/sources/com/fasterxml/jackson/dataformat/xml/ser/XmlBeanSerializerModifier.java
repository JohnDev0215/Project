package com.fasterxml.jackson.dataformat.xml.ser;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.dataformat.xml.util.AnnotationUtil;
import com.fasterxml.jackson.dataformat.xml.util.TypeUtil;
import com.fasterxml.jackson.dataformat.xml.util.XmlInfo;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class XmlBeanSerializerModifier extends BeanSerializerModifier implements Serializable {
    private static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.ser.BeanSerializerModifier
    public List<BeanPropertyWriter> changeProperties(SerializationConfig serializationConfig, BeanDescription beanDescription, List<BeanPropertyWriter> list) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            BeanPropertyWriter beanPropertyWriter = list.get(i);
            AnnotatedMember member = beanPropertyWriter.getMember();
            String strFindNamespaceAnnotation = AnnotationUtil.findNamespaceAnnotation(annotationIntrospector, member);
            beanPropertyWriter.setInternalSetting(XmlBeanSerializerBase.KEY_XML_INFO, new XmlInfo(AnnotationUtil.findIsAttributeAnnotation(annotationIntrospector, member), strFindNamespaceAnnotation, AnnotationUtil.findIsTextAnnotation(annotationIntrospector, member), AnnotationUtil.findIsCDataAnnotation(annotationIntrospector, member)));
            if (TypeUtil.isIndexedType(beanPropertyWriter.getType())) {
                PropertyName propertyNameConstruct = PropertyName.construct(beanPropertyWriter.getName(), strFindNamespaceAnnotation);
                PropertyName wrapperName = beanPropertyWriter.getWrapperName();
                if (wrapperName != null && wrapperName != PropertyName.NO_NAME) {
                    String simpleName = wrapperName.getSimpleName();
                    if (simpleName == null || simpleName.length() == 0) {
                        wrapperName = propertyNameConstruct;
                    }
                    list.set(i, new XmlBeanPropertyWriter(beanPropertyWriter, wrapperName, propertyNameConstruct));
                }
            }
        }
        return list;
    }

    @Override // com.fasterxml.jackson.databind.ser.BeanSerializerModifier
    public JsonSerializer<?> modifySerializer(SerializationConfig serializationConfig, BeanDescription beanDescription, JsonSerializer<?> jsonSerializer) {
        return !(jsonSerializer instanceof BeanSerializerBase) ? jsonSerializer : new XmlBeanSerializer((BeanSerializerBase) jsonSerializer);
    }
}
