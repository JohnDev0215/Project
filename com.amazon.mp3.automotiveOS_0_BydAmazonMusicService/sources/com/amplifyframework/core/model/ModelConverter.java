package com.amplifyframework.core.model;

import com.amplifyframework.AmplifyException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelConverter {
    private ModelConverter() {
    }

    public static <T extends Model> Map<String, Object> toMap(T t, ModelSchema modelSchema) throws AmplifyException {
        SchemaRegistry schemaRegistryInstance = SchemaRegistry.instance();
        HashMap map = new HashMap();
        for (ModelField modelField : modelSchema.getFields().values()) {
            String name = modelField.getName();
            String targetType = modelField.getTargetType();
            ModelAssociation modelAssociation = modelSchema.getAssociations().get(name);
            if (modelAssociation == null) {
                if (!(t instanceof SerializedModel) || ((SerializedModel) t).getSerializedData().containsKey(modelField.getName())) {
                    map.put(name, extractFieldValue(modelField.getName(), t, modelSchema));
                }
            } else if (modelAssociation.isOwner()) {
                ModelSchema modelSchemaForModelClass = schemaRegistryInstance.getModelSchemaForModelClass(targetType);
                Map<String, Object> mapExtractAssociateId = extractAssociateId(modelField, t, modelSchema);
                if (mapExtractAssociateId != null) {
                    map.put(name, SerializedModel.builder().modelSchema(modelSchemaForModelClass).serializedData(mapExtractAssociateId).build());
                }
            }
        }
        return map;
    }

    private static Map<String, Object> extractAssociateId(ModelField modelField, Model model, ModelSchema modelSchema) throws AmplifyException {
        Object objExtractFieldValue = extractFieldValue(modelField.getName(), model, modelSchema);
        if (modelField.isModel() && (objExtractFieldValue instanceof Model)) {
            Model model2 = (Model) objExtractFieldValue;
            ModelSchema modelSchemaForModelClass = SchemaRegistry.instance().getModelSchemaForModelClass(model2.getModelName());
            HashMap map = new HashMap();
            if (modelSchemaForModelClass.getPrimaryIndexFields().size() > 1 && (model2.resolveIdentifier() instanceof ModelIdentifier)) {
                ModelIdentifier modelIdentifier = (ModelIdentifier) model2.resolveIdentifier();
                ListIterator<String> listIterator = modelSchemaForModelClass.getPrimaryIndexFields().listIterator();
                map.put(listIterator.next(), modelIdentifier.key());
                ListIterator<? extends Serializable> listIterator2 = modelIdentifier.sortedKeys().listIterator();
                while (listIterator.hasNext()) {
                    map.put(listIterator.next(), listIterator2.next());
                }
                return map;
            }
            if (modelSchemaForModelClass.getPrimaryIndexFields().size() > 1 && (model2 instanceof SerializedModel)) {
                for (String str : modelSchemaForModelClass.getPrimaryIndexFields()) {
                    map.put(str, ((SerializedModel) model2).getSerializedData().get(str));
                }
                return map;
            }
            return Collections.singletonMap(modelSchemaForModelClass.getPrimaryIndexFields().get(0), model2.getPrimaryKeyString());
        }
        if (modelField.isModel() && (objExtractFieldValue instanceof Map)) {
            return Collections.singletonMap("id", ((Map) objExtractFieldValue).get("id"));
        }
        if (modelField.isModel() && objExtractFieldValue == null) {
            return null;
        }
        throw new IllegalStateException("Associated data is not a Model or Map.");
    }

    private static Object extractFieldValue(String str, Model model, ModelSchema modelSchema) throws AmplifyException {
        if (model instanceof SerializedModel) {
            return ((SerializedModel) model).getSerializedData().get(str);
        }
        try {
            Field declaredField = model.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(model);
        } catch (Exception e) {
            throw new AmplifyException("An invalid field was provided. " + str + " is not present in " + modelSchema.getName(), e, "Check if this model schema is a correct representation of the fields in the provided Object");
        }
    }
}
