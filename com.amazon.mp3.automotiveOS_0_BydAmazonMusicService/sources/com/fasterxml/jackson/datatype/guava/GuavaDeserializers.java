package com.fasterxml.jackson.datatype.guava;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.datatype.guava.deser.GuavaOptionalDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.HashCodeDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.HashMultisetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.HostAndPortDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableBiMapDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableListDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableMapDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableMultisetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableSetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableSortedMapDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableSortedMultisetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.ImmutableSortedSetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.InternetDomainNameDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.LinkedHashMultisetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.RangeDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.RangeSetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.TreeMultisetDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.multimap.list.ArrayListMultimapDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.multimap.list.LinkedListMultimapDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.multimap.set.HashMultimapDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.multimap.set.LinkedHashMultimapDeserializer;
import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.BoundType;
import com.google.common.collect.EnumBiMap;
import com.google.common.collect.EnumHashBiMap;
import com.google.common.collect.EnumMultiset;
import com.google.common.collect.ForwardingListMultimap;
import com.google.common.collect.ForwardingSetMultimap;
import com.google.common.collect.ForwardingSortedSetMultimap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedMultiset;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.Table;
import com.google.common.collect.TreeMultimap;
import com.google.common.collect.TreeMultiset;
import com.google.common.hash.HashCode;
import com.google.common.net.HostAndPort;
import com.google.common.net.InternetDomainName;
import java.io.Serializable;

/* JADX INFO: loaded from: classes6.dex */
public class GuavaDeserializers extends Deserializers.Base implements Serializable {
    static final long serialVersionUID = 1;
    protected BoundType _defaultBoundType;

    public GuavaDeserializers() {
        this(null);
    }

    public GuavaDeserializers(BoundType boundType) {
        this._defaultBoundType = boundType;
    }

    @Override // com.fasterxml.jackson.databind.deser.Deserializers.Base, com.fasterxml.jackson.databind.deser.Deserializers
    public JsonDeserializer<?> findCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Class<?> rawClass = collectionType.getRawClass();
        if (ImmutableCollection.class.isAssignableFrom(rawClass)) {
            if (ImmutableList.class.isAssignableFrom(rawClass)) {
                return new ImmutableListDeserializer(collectionType, jsonDeserializer, typeDeserializer, null, null);
            }
            if (ImmutableMultiset.class.isAssignableFrom(rawClass)) {
                if (ImmutableSortedMultiset.class.isAssignableFrom(rawClass)) {
                    requireCollectionOfComparableElements(collectionType, "ImmutableSortedMultiset");
                    return new ImmutableSortedMultisetDeserializer(collectionType, jsonDeserializer, typeDeserializer, null, null);
                }
                return new ImmutableMultisetDeserializer(collectionType, jsonDeserializer, typeDeserializer, null, null);
            }
            if (ImmutableSet.class.isAssignableFrom(rawClass)) {
                if (ImmutableSortedSet.class.isAssignableFrom(rawClass)) {
                    requireCollectionOfComparableElements(collectionType, "ImmutableSortedSet");
                    return new ImmutableSortedSetDeserializer(collectionType, jsonDeserializer, typeDeserializer, null, null);
                }
                return new ImmutableSetDeserializer(collectionType, jsonDeserializer, typeDeserializer, null, null);
            }
            return new ImmutableListDeserializer(collectionType, jsonDeserializer, typeDeserializer, null, null);
        }
        if (!Multiset.class.isAssignableFrom(rawClass)) {
            return null;
        }
        if (SortedMultiset.class.isAssignableFrom(rawClass)) {
            if (TreeMultiset.class.isAssignableFrom(rawClass)) {
                return new TreeMultisetDeserializer(collectionType, jsonDeserializer, typeDeserializer, null, null);
            }
            return new TreeMultisetDeserializer(collectionType, jsonDeserializer, typeDeserializer, null, null);
        }
        if (LinkedHashMultiset.class.isAssignableFrom(rawClass)) {
            return new LinkedHashMultisetDeserializer(collectionType, jsonDeserializer, typeDeserializer, null, null);
        }
        if (HashMultiset.class.isAssignableFrom(rawClass)) {
            return new HashMultisetDeserializer(collectionType, jsonDeserializer, typeDeserializer, null, null);
        }
        EnumMultiset.class.isAssignableFrom(rawClass);
        return new HashMultisetDeserializer(collectionType, jsonDeserializer, typeDeserializer, null, null);
    }

    private void requireCollectionOfComparableElements(CollectionType collectionType, String str) {
        Class<?> rawClass = collectionType.getContentType().getRawClass();
        if (Comparable.class.isAssignableFrom(rawClass)) {
            return;
        }
        throw new IllegalArgumentException("Can not handle " + str + " with elements that are not Comparable<?> (" + rawClass.getName() + ")");
    }

    @Override // com.fasterxml.jackson.databind.deser.Deserializers.Base, com.fasterxml.jackson.databind.deser.Deserializers
    public JsonDeserializer<?> findMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Class<?> rawClass = mapType.getRawClass();
        if (ImmutableMap.class.isAssignableFrom(rawClass)) {
            if (ImmutableSortedMap.class.isAssignableFrom(rawClass)) {
                return new ImmutableSortedMapDeserializer(mapType, keyDeserializer, jsonDeserializer, typeDeserializer, null);
            }
            if (ImmutableBiMap.class.isAssignableFrom(rawClass)) {
                return new ImmutableBiMapDeserializer(mapType, keyDeserializer, jsonDeserializer, typeDeserializer, null);
            }
            return new ImmutableMapDeserializer(mapType, keyDeserializer, jsonDeserializer, typeDeserializer, null);
        }
        if (!BiMap.class.isAssignableFrom(rawClass)) {
            return null;
        }
        EnumBiMap.class.isAssignableFrom(rawClass);
        EnumHashBiMap.class.isAssignableFrom(rawClass);
        HashBiMap.class.isAssignableFrom(rawClass);
        return null;
    }

    @Override // com.fasterxml.jackson.databind.deser.Deserializers.Base, com.fasterxml.jackson.databind.deser.Deserializers
    public JsonDeserializer<?> findMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Class<?> rawClass = mapLikeType.getRawClass();
        if (ListMultimap.class.isAssignableFrom(rawClass)) {
            ImmutableListMultimap.class.isAssignableFrom(rawClass);
            if (ArrayListMultimap.class.isAssignableFrom(rawClass)) {
                return new ArrayListMultimapDeserializer(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer);
            }
            if (LinkedListMultimap.class.isAssignableFrom(rawClass)) {
                return new LinkedListMultimapDeserializer(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer);
            }
            ForwardingListMultimap.class.isAssignableFrom(rawClass);
            return new ArrayListMultimapDeserializer(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer);
        }
        if (SetMultimap.class.isAssignableFrom(rawClass)) {
            if (SortedSetMultimap.class.isAssignableFrom(rawClass)) {
                TreeMultimap.class.isAssignableFrom(rawClass);
                ForwardingSortedSetMultimap.class.isAssignableFrom(rawClass);
            }
            if (ImmutableSetMultimap.class.isAssignableFrom(rawClass)) {
                return new LinkedHashMultimapDeserializer(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer);
            }
            if (HashMultimap.class.isAssignableFrom(rawClass)) {
                return new HashMultimapDeserializer(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer);
            }
            if (LinkedHashMultimap.class.isAssignableFrom(rawClass)) {
                return new LinkedHashMultimapDeserializer(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer);
            }
            ForwardingSetMultimap.class.isAssignableFrom(rawClass);
            return new LinkedHashMultimapDeserializer(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer);
        }
        if (Multimap.class.isAssignableFrom(rawClass)) {
            return new LinkedListMultimapDeserializer(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer);
        }
        Table.class.isAssignableFrom(rawClass);
        return null;
    }

    @Override // com.fasterxml.jackson.databind.deser.Deserializers.Base, com.fasterxml.jackson.databind.deser.Deserializers
    public JsonDeserializer<?> findReferenceDeserializer(ReferenceType referenceType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        if (referenceType.hasRawClass(Optional.class)) {
            return new GuavaOptionalDeserializer(referenceType, null, typeDeserializer, jsonDeserializer);
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.deser.Deserializers.Base, com.fasterxml.jackson.databind.deser.Deserializers
    public JsonDeserializer<?> findBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) {
        if (javaType.hasRawClass(RangeSet.class)) {
            return new RangeSetDeserializer();
        }
        if (javaType.hasRawClass(Range.class)) {
            return new RangeDeserializer(this._defaultBoundType, javaType);
        }
        if (javaType.hasRawClass(HostAndPort.class)) {
            return HostAndPortDeserializer.std;
        }
        if (javaType.hasRawClass(InternetDomainName.class)) {
            return InternetDomainNameDeserializer.std;
        }
        if (javaType.hasRawClass(HashCode.class)) {
            return HashCodeDeserializer.std;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.deser.Deserializers.Base
    public boolean hasDeserializerFor(DeserializationConfig deserializationConfig, Class<?> cls) {
        if (cls.getName().startsWith("com.google.")) {
            return cls == Optional.class || cls == RangeSet.class || cls == HostAndPort.class || cls == InternetDomainName.class || cls == HashCode.class || Multiset.class.isAssignableFrom(cls) || Multimap.class.isAssignableFrom(cls) || ImmutableCollection.class.isAssignableFrom(cls) || ImmutableMap.class.isAssignableFrom(cls) || BiMap.class.isAssignableFrom(cls);
        }
        return false;
    }
}
