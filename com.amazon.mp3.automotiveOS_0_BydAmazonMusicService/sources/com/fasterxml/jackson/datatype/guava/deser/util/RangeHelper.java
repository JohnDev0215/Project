package com.fasterxml.jackson.datatype.guava.deser.util;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import java.io.Serializable;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes6.dex */
public class RangeHelper {
    private static final Field[] FIELDS;
    private static final RangeProperties STD_NAMES;

    public static class RangeProperties implements Serializable {
        private static final long serialVersionUID = 2;
        public final String lowerBoundType;
        public final String lowerEndpoint;
        public final String upperBoundType;
        public final String upperEndpoint;

        protected RangeProperties() {
            this("lowerEndpoint", "upperEndpoint", "lowerBoundType", "upperBoundType");
        }

        public RangeProperties(String str, String str2, String str3, String str4) {
            this.lowerEndpoint = str;
            this.upperEndpoint = str2;
            this.lowerBoundType = str3;
            this.upperBoundType = str4;
        }

        protected Field[] fields() {
            return new Field[]{_field(this.lowerEndpoint), _field(this.upperEndpoint), _field(this.lowerBoundType), _field(this.upperBoundType)};
        }

        private Field _field(String str) {
            try {
                return getClass().getField(str);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    static {
        RangeProperties rangeProperties = new RangeProperties();
        STD_NAMES = rangeProperties;
        FIELDS = rangeProperties.fields();
    }

    public static RangeProperties standardNames() {
        return STD_NAMES;
    }

    public static RangeProperties getPropertyNames(MapperConfig<?> mapperConfig, PropertyNamingStrategy propertyNamingStrategy) {
        if (propertyNamingStrategy == null) {
            return STD_NAMES;
        }
        TypeResolutionContext.Empty empty = new TypeResolutionContext.Empty(mapperConfig.getTypeFactory());
        Field[] fieldArr = FIELDS;
        return new RangeProperties(_find(mapperConfig, empty, propertyNamingStrategy, fieldArr[0]), _find(mapperConfig, empty, propertyNamingStrategy, fieldArr[1]), _find(mapperConfig, empty, propertyNamingStrategy, fieldArr[2]), _find(mapperConfig, empty, propertyNamingStrategy, fieldArr[3]));
    }

    private static String _find(MapperConfig<?> mapperConfig, TypeResolutionContext typeResolutionContext, PropertyNamingStrategy propertyNamingStrategy, Field field) {
        return propertyNamingStrategy.nameForField(mapperConfig, new AnnotatedField(typeResolutionContext, field, null), field.getName());
    }
}
