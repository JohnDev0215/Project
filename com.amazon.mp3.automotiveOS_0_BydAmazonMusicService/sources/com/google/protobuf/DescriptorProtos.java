package com.google.protobuf;

import com.amazonaws.http.HttpHeader;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.android.gms.cast.MediaError;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.visualon.OSMPUtils.voOSType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
public final class DescriptorProtos {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n google/protobuf/descriptor.proto\u0012\u000fgoogle.protobuf\"M\n\u0011FileDescriptorSet\u00128\n\u0004file\u0018\u0001 \u0003(\u000b2$.google.protobuf.FileDescriptorProtoR\u0004file\"þ\u0004\n\u0013FileDescriptorProto\u0012\u0012\n\u0004name\u0018\u0001 \u0001(\tR\u0004name\u0012\u0018\n\u0007package\u0018\u0002 \u0001(\tR\u0007package\u0012\u001e\n\ndependency\u0018\u0003 \u0003(\tR\ndependency\u0012+\n\u0011public_dependency\u0018\n \u0003(\u0005R\u0010publicDependency\u0012'\n\u000fweak_dependency\u0018\u000b \u0003(\u0005R\u000eweakDependency\u0012C\n\fmessage_type\u0018\u0004 \u0003(\u000b2 .google.protobuf.DescriptorProtoR\u000bmessageType\u0012A\n\tenum_type\u0018\u0005 \u0003(\u000b2$.google.protobuf.EnumDescriptorProtoR\benumType\u0012A\n\u0007service\u0018\u0006 \u0003(\u000b2'.google.protobuf.ServiceDescriptorProtoR\u0007service\u0012C\n\textension\u0018\u0007 \u0003(\u000b2%.google.protobuf.FieldDescriptorProtoR\textension\u00126\n\u0007options\u0018\b \u0001(\u000b2\u001c.google.protobuf.FileOptionsR\u0007options\u0012I\n\u0010source_code_info\u0018\t \u0001(\u000b2\u001f.google.protobuf.SourceCodeInfoR\u000esourceCodeInfo\u0012\u0016\n\u0006syntax\u0018\f \u0001(\tR\u0006syntax\u0012\u0018\n\u0007edition\u0018\r \u0001(\tR\u0007edition\"¹\u0006\n\u000fDescriptorProto\u0012\u0012\n\u0004name\u0018\u0001 \u0001(\tR\u0004name\u0012;\n\u0005field\u0018\u0002 \u0003(\u000b2%.google.protobuf.FieldDescriptorProtoR\u0005field\u0012C\n\textension\u0018\u0006 \u0003(\u000b2%.google.protobuf.FieldDescriptorProtoR\textension\u0012A\n\u000bnested_type\u0018\u0003 \u0003(\u000b2 .google.protobuf.DescriptorProtoR\nnestedType\u0012A\n\tenum_type\u0018\u0004 \u0003(\u000b2$.google.protobuf.EnumDescriptorProtoR\benumType\u0012X\n\u000fextension_range\u0018\u0005 \u0003(\u000b2/.google.protobuf.DescriptorProto.ExtensionRangeR\u000eextensionRange\u0012D\n\noneof_decl\u0018\b \u0003(\u000b2%.google.protobuf.OneofDescriptorProtoR\toneofDecl\u00129\n\u0007options\u0018\u0007 \u0001(\u000b2\u001f.google.protobuf.MessageOptionsR\u0007options\u0012U\n\u000ereserved_range\u0018\t \u0003(\u000b2..google.protobuf.DescriptorProto.ReservedRangeR\rreservedRange\u0012#\n\rreserved_name\u0018\n \u0003(\tR\freservedName\u001az\n\u000eExtensionRange\u0012\u0014\n\u0005start\u0018\u0001 \u0001(\u0005R\u0005start\u0012\u0010\n\u0003end\u0018\u0002 \u0001(\u0005R\u0003end\u0012@\n\u0007options\u0018\u0003 \u0001(\u000b2&.google.protobuf.ExtensionRangeOptionsR\u0007options\u001a7\n\rReservedRange\u0012\u0014\n\u0005start\u0018\u0001 \u0001(\u0005R\u0005start\u0012\u0010\n\u0003end\u0018\u0002 \u0001(\u0005R\u0003end\"|\n\u0015ExtensionRangeOptions\u0012X\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOptionR\u0013uninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"Á\u0006\n\u0014FieldDescriptorProto\u0012\u0012\n\u0004name\u0018\u0001 \u0001(\tR\u0004name\u0012\u0016\n\u0006number\u0018\u0003 \u0001(\u0005R\u0006number\u0012A\n\u0005label\u0018\u0004 \u0001(\u000e2+.google.protobuf.FieldDescriptorProto.LabelR\u0005label\u0012>\n\u0004type\u0018\u0005 \u0001(\u000e2*.google.protobuf.FieldDescriptorProto.TypeR\u0004type\u0012\u001b\n\ttype_name\u0018\u0006 \u0001(\tR\btypeName\u0012\u001a\n\bextendee\u0018\u0002 \u0001(\tR\bextendee\u0012#\n\rdefault_value\u0018\u0007 \u0001(\tR\fdefaultValue\u0012\u001f\n\u000boneof_index\u0018\t \u0001(\u0005R\noneofIndex\u0012\u001b\n\tjson_name\u0018\n \u0001(\tR\bjsonName\u00127\n\u0007options\u0018\b \u0001(\u000b2\u001d.google.protobuf.FieldOptionsR\u0007options\u0012'\n\u000fproto3_optional\u0018\u0011 \u0001(\bR\u000eproto3Optional\"¶\u0002\n\u0004Type\u0012\u000f\n\u000bTYPE_DOUBLE\u0010\u0001\u0012\u000e\n\nTYPE_FLOAT\u0010\u0002\u0012\u000e\n\nTYPE_INT64\u0010\u0003\u0012\u000f\n\u000bTYPE_UINT64\u0010\u0004\u0012\u000e\n\nTYPE_INT32\u0010\u0005\u0012\u0010\n\fTYPE_FIXED64\u0010\u0006\u0012\u0010\n\fTYPE_FIXED32\u0010\u0007\u0012\r\n\tTYPE_BOOL\u0010\b\u0012\u000f\n\u000bTYPE_STRING\u0010\t\u0012\u000e\n\nTYPE_GROUP\u0010\n\u0012\u0010\n\fTYPE_MESSAGE\u0010\u000b\u0012\u000e\n\nTYPE_BYTES\u0010\f\u0012\u000f\n\u000bTYPE_UINT32\u0010\r\u0012\r\n\tTYPE_ENUM\u0010\u000e\u0012\u0011\n\rTYPE_SFIXED32\u0010\u000f\u0012\u0011\n\rTYPE_SFIXED64\u0010\u0010\u0012\u000f\n\u000bTYPE_SINT32\u0010\u0011\u0012\u000f\n\u000bTYPE_SINT64\u0010\u0012\"C\n\u0005Label\u0012\u0012\n\u000eLABEL_OPTIONAL\u0010\u0001\u0012\u0012\n\u000eLABEL_REQUIRED\u0010\u0002\u0012\u0012\n\u000eLABEL_REPEATED\u0010\u0003\"c\n\u0014OneofDescriptorProto\u0012\u0012\n\u0004name\u0018\u0001 \u0001(\tR\u0004name\u00127\n\u0007options\u0018\u0002 \u0001(\u000b2\u001d.google.protobuf.OneofOptionsR\u0007options\"ã\u0002\n\u0013EnumDescriptorProto\u0012\u0012\n\u0004name\u0018\u0001 \u0001(\tR\u0004name\u0012?\n\u0005value\u0018\u0002 \u0003(\u000b2).google.protobuf.EnumValueDescriptorProtoR\u0005value\u00126\n\u0007options\u0018\u0003 \u0001(\u000b2\u001c.google.protobuf.EnumOptionsR\u0007options\u0012]\n\u000ereserved_range\u0018\u0004 \u0003(\u000b26.google.protobuf.EnumDescriptorProto.EnumReservedRangeR\rreservedRange\u0012#\n\rreserved_name\u0018\u0005 \u0003(\tR\freservedName\u001a;\n\u0011EnumReservedRange\u0012\u0014\n\u0005start\u0018\u0001 \u0001(\u0005R\u0005start\u0012\u0010\n\u0003end\u0018\u0002 \u0001(\u0005R\u0003end\"\u0083\u0001\n\u0018EnumValueDescriptorProto\u0012\u0012\n\u0004name\u0018\u0001 \u0001(\tR\u0004name\u0012\u0016\n\u0006number\u0018\u0002 \u0001(\u0005R\u0006number\u0012;\n\u0007options\u0018\u0003 \u0001(\u000b2!.google.protobuf.EnumValueOptionsR\u0007options\"§\u0001\n\u0016ServiceDescriptorProto\u0012\u0012\n\u0004name\u0018\u0001 \u0001(\tR\u0004name\u0012>\n\u0006method\u0018\u0002 \u0003(\u000b2&.google.protobuf.MethodDescriptorProtoR\u0006method\u00129\n\u0007options\u0018\u0003 \u0001(\u000b2\u001f.google.protobuf.ServiceOptionsR\u0007options\"\u0089\u0002\n\u0015MethodDescriptorProto\u0012\u0012\n\u0004name\u0018\u0001 \u0001(\tR\u0004name\u0012\u001d\n\ninput_type\u0018\u0002 \u0001(\tR\tinputType\u0012\u001f\n\u000boutput_type\u0018\u0003 \u0001(\tR\noutputType\u00128\n\u0007options\u0018\u0004 \u0001(\u000b2\u001e.google.protobuf.MethodOptionsR\u0007options\u00120\n\u0010client_streaming\u0018\u0005 \u0001(\b:\u0005falseR\u000fclientStreaming\u00120\n\u0010server_streaming\u0018\u0006 \u0001(\b:\u0005falseR\u000fserverStreaming\"\u0091\t\n\u000bFileOptions\u0012!\n\fjava_package\u0018\u0001 \u0001(\tR\u000bjavaPackage\u00120\n\u0014java_outer_classname\u0018\b \u0001(\tR\u0012javaOuterClassname\u00125\n\u0013java_multiple_files\u0018\n \u0001(\b:\u0005falseR\u0011javaMultipleFiles\u0012D\n\u001djava_generate_equals_and_hash\u0018\u0014 \u0001(\bB\u0002\u0018\u0001R\u0019javaGenerateEqualsAndHash\u0012:\n\u0016java_string_check_utf8\u0018\u001b \u0001(\b:\u0005falseR\u0013javaStringCheckUtf8\u0012S\n\foptimize_for\u0018\t \u0001(\u000e2).google.protobuf.FileOptions.OptimizeMode:\u0005SPEEDR\u000boptimizeFor\u0012\u001d\n\ngo_package\u0018\u000b \u0001(\tR\tgoPackage\u00125\n\u0013cc_generic_services\u0018\u0010 \u0001(\b:\u0005falseR\u0011ccGenericServices\u00129\n\u0015java_generic_services\u0018\u0011 \u0001(\b:\u0005falseR\u0013javaGenericServices\u00125\n\u0013py_generic_services\u0018\u0012 \u0001(\b:\u0005falseR\u0011pyGenericServices\u00127\n\u0014php_generic_services\u0018* \u0001(\b:\u0005falseR\u0012phpGenericServices\u0012%\n\ndeprecated\u0018\u0017 \u0001(\b:\u0005falseR\ndeprecated\u0012.\n\u0010cc_enable_arenas\u0018\u001f \u0001(\b:\u0004trueR\u000eccEnableArenas\u0012*\n\u0011objc_class_prefix\u0018$ \u0001(\tR\u000fobjcClassPrefix\u0012)\n\u0010csharp_namespace\u0018% \u0001(\tR\u000fcsharpNamespace\u0012!\n\fswift_prefix\u0018' \u0001(\tR\u000bswiftPrefix\u0012(\n\u0010php_class_prefix\u0018( \u0001(\tR\u000ephpClassPrefix\u0012#\n\rphp_namespace\u0018) \u0001(\tR\fphpNamespace\u00124\n\u0016php_metadata_namespace\u0018, \u0001(\tR\u0014phpMetadataNamespace\u0012!\n\fruby_package\u0018- \u0001(\tR\u000brubyPackage\u0012X\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOptionR\u0013uninterpretedOption\":\n\fOptimizeMode\u0012\t\n\u0005SPEED\u0010\u0001\u0012\r\n\tCODE_SIZE\u0010\u0002\u0012\u0010\n\fLITE_RUNTIME\u0010\u0003*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002J\u0004\b&\u0010'\"»\u0003\n\u000eMessageOptions\u0012<\n\u0017message_set_wire_format\u0018\u0001 \u0001(\b:\u0005falseR\u0014messageSetWireFormat\u0012L\n\u001fno_standard_descriptor_accessor\u0018\u0002 \u0001(\b:\u0005falseR\u001cnoStandardDescriptorAccessor\u0012%\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005falseR\ndeprecated\u0012\u001b\n\tmap_entry\u0018\u0007 \u0001(\bR\bmapEntry\u0012V\n&deprecated_legacy_json_field_conflicts\u0018\u000b \u0001(\bB\u0002\u0018\u0001R\"deprecatedLegacyJsonFieldConflicts\u0012X\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOptionR\u0013uninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002J\u0004\b\u0004\u0010\u0005J\u0004\b\u0005\u0010\u0006J\u0004\b\u0006\u0010\u0007J\u0004\b\b\u0010\tJ\u0004\b\t\u0010\n\"·\b\n\fFieldOptions\u0012A\n\u0005ctype\u0018\u0001 \u0001(\u000e2#.google.protobuf.FieldOptions.CType:\u0006STRINGR\u0005ctype\u0012\u0016\n\u0006packed\u0018\u0002 \u0001(\bR\u0006packed\u0012G\n\u0006jstype\u0018\u0006 \u0001(\u000e2$.google.protobuf.FieldOptions.JSType:\tJS_NORMALR\u0006jstype\u0012\u0019\n\u0004lazy\u0018\u0005 \u0001(\b:\u0005falseR\u0004lazy\u0012.\n\u000funverified_lazy\u0018\u000f \u0001(\b:\u0005falseR\u000eunverifiedLazy\u0012%\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005falseR\ndeprecated\u0012\u0019\n\u0004weak\u0018\n \u0001(\b:\u0005falseR\u0004weak\u0012(\n\fdebug_redact\u0018\u0010 \u0001(\b:\u0005falseR\u000bdebugRedact\u0012K\n\tretention\u0018\u0011 \u0001(\u000e2-.google.protobuf.FieldOptions.OptionRetentionR\tretention\u0012F\n\u0006target\u0018\u0012 \u0001(\u000e2..google.protobuf.FieldOptions.OptionTargetTypeR\u0006target\u0012X\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOptionR\u0013uninterpretedOption\"/\n\u0005CType\u0012\n\n\u0006STRING\u0010\u0000\u0012\b\n\u0004CORD\u0010\u0001\u0012\u0010\n\fSTRING_PIECE\u0010\u0002\"5\n\u0006JSType\u0012\r\n\tJS_NORMAL\u0010\u0000\u0012\r\n\tJS_STRING\u0010\u0001\u0012\r\n\tJS_NUMBER\u0010\u0002\"U\n\u000fOptionRetention\u0012\u0015\n\u0011RETENTION_UNKNOWN\u0010\u0000\u0012\u0015\n\u0011RETENTION_RUNTIME\u0010\u0001\u0012\u0014\n\u0010RETENTION_SOURCE\u0010\u0002\"\u008c\u0002\n\u0010OptionTargetType\u0012\u0017\n\u0013TARGET_TYPE_UNKNOWN\u0010\u0000\u0012\u0014\n\u0010TARGET_TYPE_FILE\u0010\u0001\u0012\u001f\n\u001bTARGET_TYPE_EXTENSION_RANGE\u0010\u0002\u0012\u0017\n\u0013TARGET_TYPE_MESSAGE\u0010\u0003\u0012\u0015\n\u0011TARGET_TYPE_FIELD\u0010\u0004\u0012\u0015\n\u0011TARGET_TYPE_ONEOF\u0010\u0005\u0012\u0014\n\u0010TARGET_TYPE_ENUM\u0010\u0006\u0012\u001a\n\u0016TARGET_TYPE_ENUM_ENTRY\u0010\u0007\u0012\u0017\n\u0013TARGET_TYPE_SERVICE\u0010\b\u0012\u0016\n\u0012TARGET_TYPE_METHOD\u0010\t*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002J\u0004\b\u0004\u0010\u0005\"s\n\fOneofOptions\u0012X\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOptionR\u0013uninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"\u0098\u0002\n\u000bEnumOptions\u0012\u001f\n\u000ballow_alias\u0018\u0002 \u0001(\bR\nallowAlias\u0012%\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005falseR\ndeprecated\u0012V\n&deprecated_legacy_json_field_conflicts\u0018\u0006 \u0001(\bB\u0002\u0018\u0001R\"deprecatedLegacyJsonFieldConflicts\u0012X\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOptionR\u0013uninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002J\u0004\b\u0005\u0010\u0006\"\u009e\u0001\n\u0010EnumValueOptions\u0012%\n\ndeprecated\u0018\u0001 \u0001(\b:\u0005falseR\ndeprecated\u0012X\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOptionR\u0013uninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"\u009c\u0001\n\u000eServiceOptions\u0012%\n\ndeprecated\u0018! \u0001(\b:\u0005falseR\ndeprecated\u0012X\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOptionR\u0013uninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"à\u0002\n\rMethodOptions\u0012%\n\ndeprecated\u0018! \u0001(\b:\u0005falseR\ndeprecated\u0012q\n\u0011idempotency_level\u0018\" \u0001(\u000e2/.google.protobuf.MethodOptions.IdempotencyLevel:\u0013IDEMPOTENCY_UNKNOWNR\u0010idempotencyLevel\u0012X\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOptionR\u0013uninterpretedOption\"P\n\u0010IdempotencyLevel\u0012\u0017\n\u0013IDEMPOTENCY_UNKNOWN\u0010\u0000\u0012\u0013\n\u000fNO_SIDE_EFFECTS\u0010\u0001\u0012\u000e\n\nIDEMPOTENT\u0010\u0002*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"\u009a\u0003\n\u0013UninterpretedOption\u0012A\n\u0004name\u0018\u0002 \u0003(\u000b2-.google.protobuf.UninterpretedOption.NamePartR\u0004name\u0012)\n\u0010identifier_value\u0018\u0003 \u0001(\tR\u000fidentifierValue\u0012,\n\u0012positive_int_value\u0018\u0004 \u0001(\u0004R\u0010positiveIntValue\u0012,\n\u0012negative_int_value\u0018\u0005 \u0001(\u0003R\u0010negativeIntValue\u0012!\n\fdouble_value\u0018\u0006 \u0001(\u0001R\u000bdoubleValue\u0012!\n\fstring_value\u0018\u0007 \u0001(\fR\u000bstringValue\u0012'\n\u000faggregate_value\u0018\b \u0001(\tR\u000eaggregateValue\u001aJ\n\bNamePart\u0012\u001b\n\tname_part\u0018\u0001 \u0002(\tR\bnamePart\u0012!\n\fis_extension\u0018\u0002 \u0002(\bR\u000bisExtension\"§\u0002\n\u000eSourceCodeInfo\u0012D\n\blocation\u0018\u0001 \u0003(\u000b2(.google.protobuf.SourceCodeInfo.LocationR\blocation\u001aÎ\u0001\n\bLocation\u0012\u0016\n\u0004path\u0018\u0001 \u0003(\u0005B\u0002\u0010\u0001R\u0004path\u0012\u0016\n\u0004span\u0018\u0002 \u0003(\u0005B\u0002\u0010\u0001R\u0004span\u0012)\n\u0010leading_comments\u0018\u0003 \u0001(\tR\u000fleadingComments\u0012+\n\u0011trailing_comments\u0018\u0004 \u0001(\tR\u0010trailingComments\u0012:\n\u0019leading_detached_comments\u0018\u0006 \u0003(\tR\u0017leadingDetachedComments\"Ð\u0002\n\u0011GeneratedCodeInfo\u0012M\n\nannotation\u0018\u0001 \u0003(\u000b2-.google.protobuf.GeneratedCodeInfo.AnnotationR\nannotation\u001aë\u0001\n\nAnnotation\u0012\u0016\n\u0004path\u0018\u0001 \u0003(\u0005B\u0002\u0010\u0001R\u0004path\u0012\u001f\n\u000bsource_file\u0018\u0002 \u0001(\tR\nsourceFile\u0012\u0014\n\u0005begin\u0018\u0003 \u0001(\u0005R\u0005begin\u0012\u0010\n\u0003end\u0018\u0004 \u0001(\u0005R\u0003end\u0012R\n\bsemantic\u0018\u0005 \u0001(\u000e26.google.protobuf.GeneratedCodeInfo.Annotation.SemanticR\bsemantic\"(\n\bSemantic\u0012\b\n\u0004NONE\u0010\u0000\u0012\u0007\n\u0003SET\u0010\u0001\u0012\t\n\u0005ALIAS\u0010\u0002B~\n\u0013com.google.protobufB\u0010DescriptorProtosH\u0001Z-google.golang.org/protobuf/types/descriptorpbø\u0001\u0001¢\u0002\u0003GPBª\u0002\u001aGoogle.Protobuf.Reflection"}, new Descriptors.FileDescriptor[0]);
    private static final Descriptors.Descriptor internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_DescriptorProto_ReservedRange_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_DescriptorProto_ReservedRange_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_DescriptorProto_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_DescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_EnumDescriptorProto_EnumReservedRange_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_EnumDescriptorProto_EnumReservedRange_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_EnumDescriptorProto_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_EnumOptions_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_EnumOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_EnumValueOptions_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_ExtensionRangeOptions_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_ExtensionRangeOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_FieldDescriptorProto_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_FieldOptions_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_FieldOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_FileDescriptorProto_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_FileDescriptorSet_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_FileDescriptorSet_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_FileOptions_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_FileOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_GeneratedCodeInfo_Annotation_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_GeneratedCodeInfo_Annotation_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_GeneratedCodeInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_GeneratedCodeInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_MessageOptions_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_MessageOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_MethodDescriptorProto_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_MethodOptions_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_MethodOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_OneofDescriptorProto_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_OneofDescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_OneofOptions_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_OneofOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_ServiceOptions_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_ServiceOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_SourceCodeInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_UninterpretedOption_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable;

    public interface DescriptorProtoOrBuilder extends MessageOrBuilder {
        int getEnumTypeCount();

        int getExtensionCount();

        int getExtensionRangeCount();

        int getFieldCount();

        int getNestedTypeCount();

        int getOneofDeclCount();

        int getReservedNameCount();

        int getReservedRangeCount();
    }

    public interface EnumDescriptorProtoOrBuilder extends MessageOrBuilder {
        int getReservedNameCount();

        int getReservedRangeCount();

        int getValueCount();
    }

    public interface EnumOptionsOrBuilder extends GeneratedMessageV3.ExtendableMessageOrBuilder<EnumOptions> {
        int getUninterpretedOptionCount();
    }

    public interface EnumValueDescriptorProtoOrBuilder extends MessageOrBuilder {
        int getNumber();
    }

    public interface EnumValueOptionsOrBuilder extends GeneratedMessageV3.ExtendableMessageOrBuilder<EnumValueOptions> {
        int getUninterpretedOptionCount();
    }

    public interface ExtensionRangeOptionsOrBuilder extends GeneratedMessageV3.ExtendableMessageOrBuilder<ExtensionRangeOptions> {
        int getUninterpretedOptionCount();
    }

    public interface FieldDescriptorProtoOrBuilder extends MessageOrBuilder {
        int getNumber();

        int getOneofIndex();
    }

    public interface FieldOptionsOrBuilder extends GeneratedMessageV3.ExtendableMessageOrBuilder<FieldOptions> {
        int getUninterpretedOptionCount();
    }

    public interface FileDescriptorProtoOrBuilder extends MessageOrBuilder {
        int getDependencyCount();

        int getEnumTypeCount();

        int getExtensionCount();

        int getMessageTypeCount();

        int getPublicDependencyCount();

        int getServiceCount();

        int getWeakDependencyCount();
    }

    public interface FileOptionsOrBuilder extends GeneratedMessageV3.ExtendableMessageOrBuilder<FileOptions> {
        int getUninterpretedOptionCount();
    }

    public interface MessageOptionsOrBuilder extends GeneratedMessageV3.ExtendableMessageOrBuilder<MessageOptions> {
        int getUninterpretedOptionCount();
    }

    public interface MethodOptionsOrBuilder extends GeneratedMessageV3.ExtendableMessageOrBuilder<MethodOptions> {
        int getUninterpretedOptionCount();
    }

    public interface OneofOptionsOrBuilder extends GeneratedMessageV3.ExtendableMessageOrBuilder<OneofOptions> {
        int getUninterpretedOptionCount();
    }

    public interface ServiceDescriptorProtoOrBuilder extends MessageOrBuilder {
        int getMethodCount();
    }

    public interface ServiceOptionsOrBuilder extends GeneratedMessageV3.ExtendableMessageOrBuilder<ServiceOptions> {
        int getUninterpretedOptionCount();
    }

    public interface SourceCodeInfoOrBuilder extends MessageOrBuilder {
        int getLocationCount();
    }

    public interface UninterpretedOptionOrBuilder extends MessageOrBuilder {
        double getDoubleValue();

        int getNameCount();
    }

    private DescriptorProtos() {
    }

    public static final class FileDescriptorProto extends GeneratedMessageV3 implements FileDescriptorProtoOrBuilder {
        private static final FileDescriptorProto DEFAULT_INSTANCE = new FileDescriptorProto();

        @Deprecated
        public static final Parser<FileDescriptorProto> PARSER = new AbstractParser<FileDescriptorProto>() { // from class: com.google.protobuf.DescriptorProtos.FileDescriptorProto.1
            @Override // com.google.protobuf.Parser
            public FileDescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = FileDescriptorProto.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private int bitField0_;
        private LazyStringArrayList dependency_;
        private volatile Object edition_;
        private List<EnumDescriptorProto> enumType_;
        private List<FieldDescriptorProto> extension_;
        private byte memoizedIsInitialized;
        private List<DescriptorProto> messageType_;
        private volatile Object name_;
        private FileOptions options_;
        private volatile Object package_;
        private Internal.IntList publicDependency_;
        private List<ServiceDescriptorProto> service_;
        private SourceCodeInfo sourceCodeInfo_;
        private volatile Object syntax_;
        private Internal.IntList weakDependency_;

        static /* synthetic */ int access$2476(FileDescriptorProto fileDescriptorProto, int i) {
            int i2 = i | fileDescriptorProto.bitField0_;
            fileDescriptorProto.bitField0_ = i2;
            return i2;
        }

        private FileDescriptorProto(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.name_ = "";
            this.package_ = "";
            this.dependency_ = LazyStringArrayList.emptyList();
            this.syntax_ = "";
            this.edition_ = "";
            this.memoizedIsInitialized = (byte) -1;
        }

        private FileDescriptorProto() {
            this.name_ = "";
            this.package_ = "";
            this.dependency_ = LazyStringArrayList.emptyList();
            this.syntax_ = "";
            this.edition_ = "";
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
            this.package_ = "";
            this.dependency_ = LazyStringArrayList.emptyList();
            this.publicDependency_ = GeneratedMessageV3.emptyIntList();
            this.weakDependency_ = GeneratedMessageV3.emptyIntList();
            this.messageType_ = Collections.emptyList();
            this.enumType_ = Collections.emptyList();
            this.service_ = Collections.emptyList();
            this.extension_ = Collections.emptyList();
            this.syntax_ = "";
            this.edition_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new FileDescriptorProto();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(FileDescriptorProto.class, Builder.class);
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasPackage() {
            return (this.bitField0_ & 2) != 0;
        }

        public String getPackage() {
            Object obj = this.package_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.package_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ProtocolStringList getDependencyList() {
            return this.dependency_;
        }

        @Override // com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder
        public int getDependencyCount() {
            return this.dependency_.size();
        }

        public String getDependency(int index) {
            return this.dependency_.get(index);
        }

        public List<Integer> getPublicDependencyList() {
            return this.publicDependency_;
        }

        @Override // com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder
        public int getPublicDependencyCount() {
            return this.publicDependency_.size();
        }

        public int getPublicDependency(int index) {
            return this.publicDependency_.getInt(index);
        }

        public List<Integer> getWeakDependencyList() {
            return this.weakDependency_;
        }

        @Override // com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder
        public int getWeakDependencyCount() {
            return this.weakDependency_.size();
        }

        public List<DescriptorProto> getMessageTypeList() {
            return this.messageType_;
        }

        @Override // com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder
        public int getMessageTypeCount() {
            return this.messageType_.size();
        }

        public DescriptorProto getMessageType(int index) {
            return this.messageType_.get(index);
        }

        public List<EnumDescriptorProto> getEnumTypeList() {
            return this.enumType_;
        }

        @Override // com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder
        public int getEnumTypeCount() {
            return this.enumType_.size();
        }

        public EnumDescriptorProto getEnumType(int index) {
            return this.enumType_.get(index);
        }

        public List<ServiceDescriptorProto> getServiceList() {
            return this.service_;
        }

        @Override // com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder
        public int getServiceCount() {
            return this.service_.size();
        }

        public ServiceDescriptorProto getService(int index) {
            return this.service_.get(index);
        }

        public List<FieldDescriptorProto> getExtensionList() {
            return this.extension_;
        }

        @Override // com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder
        public int getExtensionCount() {
            return this.extension_.size();
        }

        public FieldDescriptorProto getExtension(int index) {
            return this.extension_.get(index);
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 4) != 0;
        }

        public FileOptions getOptions() {
            FileOptions fileOptions = this.options_;
            return fileOptions == null ? FileOptions.getDefaultInstance() : fileOptions;
        }

        public boolean hasSourceCodeInfo() {
            return (this.bitField0_ & 8) != 0;
        }

        public SourceCodeInfo getSourceCodeInfo() {
            SourceCodeInfo sourceCodeInfo = this.sourceCodeInfo_;
            return sourceCodeInfo == null ? SourceCodeInfo.getDefaultInstance() : sourceCodeInfo;
        }

        public boolean hasSyntax() {
            return (this.bitField0_ & 16) != 0;
        }

        public String getSyntax() {
            Object obj = this.syntax_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.syntax_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasEdition() {
            return (this.bitField0_ & 32) != 0;
        }

        public String getEdition() {
            Object obj = this.edition_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.edition_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getMessageTypeCount(); i++) {
                if (!getMessageType(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < getEnumTypeCount(); i2++) {
                if (!getEnumType(i2).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i3 = 0; i3 < getServiceCount(); i3++) {
                if (!getService(i3).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i4 = 0; i4 < getExtensionCount(); i4++) {
                if (!getExtension(i4).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (hasOptions() && !getOptions().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString(output, 1, this.name_);
            }
            if ((this.bitField0_ & 2) != 0) {
                GeneratedMessageV3.writeString(output, 2, this.package_);
            }
            for (int i = 0; i < this.dependency_.size(); i++) {
                GeneratedMessageV3.writeString(output, 3, this.dependency_.getRaw(i));
            }
            for (int i2 = 0; i2 < this.messageType_.size(); i2++) {
                output.writeMessage(4, this.messageType_.get(i2));
            }
            for (int i3 = 0; i3 < this.enumType_.size(); i3++) {
                output.writeMessage(5, this.enumType_.get(i3));
            }
            for (int i4 = 0; i4 < this.service_.size(); i4++) {
                output.writeMessage(6, this.service_.get(i4));
            }
            for (int i5 = 0; i5 < this.extension_.size(); i5++) {
                output.writeMessage(7, this.extension_.get(i5));
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeMessage(8, getOptions());
            }
            if ((this.bitField0_ & 8) != 0) {
                output.writeMessage(9, getSourceCodeInfo());
            }
            for (int i6 = 0; i6 < this.publicDependency_.size(); i6++) {
                output.writeInt32(10, this.publicDependency_.getInt(i6));
            }
            for (int i7 = 0; i7 < this.weakDependency_.size(); i7++) {
                output.writeInt32(11, this.weakDependency_.getInt(i7));
            }
            if ((this.bitField0_ & 16) != 0) {
                GeneratedMessageV3.writeString(output, 12, this.syntax_);
            }
            if ((this.bitField0_ & 32) != 0) {
                GeneratedMessageV3.writeString(output, 13, this.edition_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (this.bitField0_ & 1) != 0 ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
            if ((this.bitField0_ & 2) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.package_);
            }
            int iComputeStringSizeNoTag = 0;
            for (int i2 = 0; i2 < this.dependency_.size(); i2++) {
                iComputeStringSizeNoTag += GeneratedMessageV3.computeStringSizeNoTag(this.dependency_.getRaw(i2));
            }
            int size = iComputeStringSize + iComputeStringSizeNoTag + (getDependencyList().size() * 1);
            for (int i3 = 0; i3 < this.messageType_.size(); i3++) {
                size += CodedOutputStream.computeMessageSize(4, this.messageType_.get(i3));
            }
            for (int i4 = 0; i4 < this.enumType_.size(); i4++) {
                size += CodedOutputStream.computeMessageSize(5, this.enumType_.get(i4));
            }
            for (int i5 = 0; i5 < this.service_.size(); i5++) {
                size += CodedOutputStream.computeMessageSize(6, this.service_.get(i5));
            }
            for (int i6 = 0; i6 < this.extension_.size(); i6++) {
                size += CodedOutputStream.computeMessageSize(7, this.extension_.get(i6));
            }
            if ((this.bitField0_ & 4) != 0) {
                size += CodedOutputStream.computeMessageSize(8, getOptions());
            }
            if ((this.bitField0_ & 8) != 0) {
                size += CodedOutputStream.computeMessageSize(9, getSourceCodeInfo());
            }
            int iComputeInt32SizeNoTag = 0;
            for (int i7 = 0; i7 < this.publicDependency_.size(); i7++) {
                iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(this.publicDependency_.getInt(i7));
            }
            int size2 = size + iComputeInt32SizeNoTag + (getPublicDependencyList().size() * 1);
            int iComputeInt32SizeNoTag2 = 0;
            for (int i8 = 0; i8 < this.weakDependency_.size(); i8++) {
                iComputeInt32SizeNoTag2 += CodedOutputStream.computeInt32SizeNoTag(this.weakDependency_.getInt(i8));
            }
            int size3 = size2 + iComputeInt32SizeNoTag2 + (getWeakDependencyList().size() * 1);
            if ((this.bitField0_ & 16) != 0) {
                size3 += GeneratedMessageV3.computeStringSize(12, this.syntax_);
            }
            if ((this.bitField0_ & 32) != 0) {
                size3 += GeneratedMessageV3.computeStringSize(13, this.edition_);
            }
            int serializedSize = size3 + getUnknownFields().getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FileDescriptorProto)) {
                return super.equals(obj);
            }
            FileDescriptorProto fileDescriptorProto = (FileDescriptorProto) obj;
            if (hasName() != fileDescriptorProto.hasName()) {
                return false;
            }
            if ((hasName() && !getName().equals(fileDescriptorProto.getName())) || hasPackage() != fileDescriptorProto.hasPackage()) {
                return false;
            }
            if ((hasPackage() && !getPackage().equals(fileDescriptorProto.getPackage())) || !getDependencyList().equals(fileDescriptorProto.getDependencyList()) || !getPublicDependencyList().equals(fileDescriptorProto.getPublicDependencyList()) || !getWeakDependencyList().equals(fileDescriptorProto.getWeakDependencyList()) || !getMessageTypeList().equals(fileDescriptorProto.getMessageTypeList()) || !getEnumTypeList().equals(fileDescriptorProto.getEnumTypeList()) || !getServiceList().equals(fileDescriptorProto.getServiceList()) || !getExtensionList().equals(fileDescriptorProto.getExtensionList()) || hasOptions() != fileDescriptorProto.hasOptions()) {
                return false;
            }
            if ((hasOptions() && !getOptions().equals(fileDescriptorProto.getOptions())) || hasSourceCodeInfo() != fileDescriptorProto.hasSourceCodeInfo()) {
                return false;
            }
            if ((hasSourceCodeInfo() && !getSourceCodeInfo().equals(fileDescriptorProto.getSourceCodeInfo())) || hasSyntax() != fileDescriptorProto.hasSyntax()) {
                return false;
            }
            if ((!hasSyntax() || getSyntax().equals(fileDescriptorProto.getSyntax())) && hasEdition() == fileDescriptorProto.hasEdition()) {
                return (!hasEdition() || getEdition().equals(fileDescriptorProto.getEdition())) && getUnknownFields().equals(fileDescriptorProto.getUnknownFields());
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasName()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getName().hashCode();
            }
            if (hasPackage()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getPackage().hashCode();
            }
            if (getDependencyCount() > 0) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getDependencyList().hashCode();
            }
            if (getPublicDependencyCount() > 0) {
                iHashCode = (((iHashCode * 37) + 10) * 53) + getPublicDependencyList().hashCode();
            }
            if (getWeakDependencyCount() > 0) {
                iHashCode = (((iHashCode * 37) + 11) * 53) + getWeakDependencyList().hashCode();
            }
            if (getMessageTypeCount() > 0) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + getMessageTypeList().hashCode();
            }
            if (getEnumTypeCount() > 0) {
                iHashCode = (((iHashCode * 37) + 5) * 53) + getEnumTypeList().hashCode();
            }
            if (getServiceCount() > 0) {
                iHashCode = (((iHashCode * 37) + 6) * 53) + getServiceList().hashCode();
            }
            if (getExtensionCount() > 0) {
                iHashCode = (((iHashCode * 37) + 7) * 53) + getExtensionList().hashCode();
            }
            if (hasOptions()) {
                iHashCode = (((iHashCode * 37) + 8) * 53) + getOptions().hashCode();
            }
            if (hasSourceCodeInfo()) {
                iHashCode = (((iHashCode * 37) + 9) * 53) + getSourceCodeInfo().hashCode();
            }
            if (hasSyntax()) {
                iHashCode = (((iHashCode * 37) + 12) * 53) + getSyntax().hashCode();
            }
            if (hasEdition()) {
                iHashCode = (((iHashCode * 37) + 13) * 53) + getEdition().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        public static FileDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FileDescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FileDescriptorProtoOrBuilder {
            private int bitField0_;
            private LazyStringArrayList dependency_;
            private Object edition_;
            private RepeatedFieldBuilderV3<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> enumTypeBuilder_;
            private List<EnumDescriptorProto> enumType_;
            private RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> extensionBuilder_;
            private List<FieldDescriptorProto> extension_;
            private RepeatedFieldBuilderV3<DescriptorProto, DescriptorProto.Builder, DescriptorProtoOrBuilder> messageTypeBuilder_;
            private List<DescriptorProto> messageType_;
            private Object name_;
            private SingleFieldBuilderV3<FileOptions, FileOptions.Builder, FileOptionsOrBuilder> optionsBuilder_;
            private FileOptions options_;
            private Object package_;
            private Internal.IntList publicDependency_;
            private RepeatedFieldBuilderV3<ServiceDescriptorProto, ServiceDescriptorProto.Builder, ServiceDescriptorProtoOrBuilder> serviceBuilder_;
            private List<ServiceDescriptorProto> service_;
            private SingleFieldBuilderV3<SourceCodeInfo, SourceCodeInfo.Builder, SourceCodeInfoOrBuilder> sourceCodeInfoBuilder_;
            private SourceCodeInfo sourceCodeInfo_;
            private Object syntax_;
            private Internal.IntList weakDependency_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(FileDescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.name_ = "";
                this.package_ = "";
                this.dependency_ = LazyStringArrayList.emptyList();
                this.publicDependency_ = GeneratedMessageV3.emptyIntList();
                this.weakDependency_ = GeneratedMessageV3.emptyIntList();
                this.messageType_ = Collections.emptyList();
                this.enumType_ = Collections.emptyList();
                this.service_ = Collections.emptyList();
                this.extension_ = Collections.emptyList();
                this.syntax_ = "";
                this.edition_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.name_ = "";
                this.package_ = "";
                this.dependency_ = LazyStringArrayList.emptyList();
                this.publicDependency_ = GeneratedMessageV3.emptyIntList();
                this.weakDependency_ = GeneratedMessageV3.emptyIntList();
                this.messageType_ = Collections.emptyList();
                this.enumType_ = Collections.emptyList();
                this.service_ = Collections.emptyList();
                this.extension_ = Collections.emptyList();
                this.syntax_ = "";
                this.edition_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getMessageTypeFieldBuilder();
                    getEnumTypeFieldBuilder();
                    getServiceFieldBuilder();
                    getExtensionFieldBuilder();
                    getOptionsFieldBuilder();
                    getSourceCodeInfoFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public FileDescriptorProto getDefaultInstanceForType() {
                return FileDescriptorProto.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FileDescriptorProto build() {
                FileDescriptorProto fileDescriptorProtoBuildPartial = buildPartial();
                if (fileDescriptorProtoBuildPartial.isInitialized()) {
                    return fileDescriptorProtoBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) fileDescriptorProtoBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FileDescriptorProto buildPartial() {
                FileDescriptorProto fileDescriptorProto = new FileDescriptorProto(this);
                buildPartialRepeatedFields(fileDescriptorProto);
                if (this.bitField0_ != 0) {
                    buildPartial0(fileDescriptorProto);
                }
                onBuilt();
                return fileDescriptorProto;
            }

            private void buildPartialRepeatedFields(FileDescriptorProto result) {
                if ((this.bitField0_ & 8) != 0) {
                    this.publicDependency_.makeImmutable();
                    this.bitField0_ &= -9;
                }
                result.publicDependency_ = this.publicDependency_;
                if ((this.bitField0_ & 16) != 0) {
                    this.weakDependency_.makeImmutable();
                    this.bitField0_ &= -17;
                }
                result.weakDependency_ = this.weakDependency_;
                RepeatedFieldBuilderV3<DescriptorProto, DescriptorProto.Builder, DescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.messageTypeBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.messageType_ = repeatedFieldBuilderV3.build();
                } else {
                    if ((this.bitField0_ & 32) != 0) {
                        this.messageType_ = Collections.unmodifiableList(this.messageType_);
                        this.bitField0_ &= -33;
                    }
                    result.messageType_ = this.messageType_;
                }
                RepeatedFieldBuilderV3<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> repeatedFieldBuilderV32 = this.enumTypeBuilder_;
                if (repeatedFieldBuilderV32 != null) {
                    result.enumType_ = repeatedFieldBuilderV32.build();
                } else {
                    if ((this.bitField0_ & 64) != 0) {
                        this.enumType_ = Collections.unmodifiableList(this.enumType_);
                        this.bitField0_ &= -65;
                    }
                    result.enumType_ = this.enumType_;
                }
                RepeatedFieldBuilderV3<ServiceDescriptorProto, ServiceDescriptorProto.Builder, ServiceDescriptorProtoOrBuilder> repeatedFieldBuilderV33 = this.serviceBuilder_;
                if (repeatedFieldBuilderV33 != null) {
                    result.service_ = repeatedFieldBuilderV33.build();
                } else {
                    if ((this.bitField0_ & 128) != 0) {
                        this.service_ = Collections.unmodifiableList(this.service_);
                        this.bitField0_ &= -129;
                    }
                    result.service_ = this.service_;
                }
                RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> repeatedFieldBuilderV34 = this.extensionBuilder_;
                if (repeatedFieldBuilderV34 != null) {
                    result.extension_ = repeatedFieldBuilderV34.build();
                    return;
                }
                if ((this.bitField0_ & 256) != 0) {
                    this.extension_ = Collections.unmodifiableList(this.extension_);
                    this.bitField0_ &= -257;
                }
                result.extension_ = this.extension_;
            }

            private void buildPartial0(FileDescriptorProto result) {
                int i;
                SourceCodeInfo sourceCodeInfo;
                FileOptions fileOptions;
                int i2 = this.bitField0_;
                if ((i2 & 1) != 0) {
                    result.name_ = this.name_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    result.package_ = this.package_;
                    i |= 2;
                }
                if ((i2 & 4) != 0) {
                    this.dependency_.makeImmutable();
                    result.dependency_ = this.dependency_;
                }
                if ((i2 & 512) != 0) {
                    SingleFieldBuilderV3<FileOptions, FileOptions.Builder, FileOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        fileOptions = this.options_;
                    } else {
                        fileOptions = (FileOptions) singleFieldBuilderV3.build();
                    }
                    result.options_ = fileOptions;
                    i |= 4;
                }
                if ((i2 & 1024) != 0) {
                    SingleFieldBuilderV3<SourceCodeInfo, SourceCodeInfo.Builder, SourceCodeInfoOrBuilder> singleFieldBuilderV32 = this.sourceCodeInfoBuilder_;
                    if (singleFieldBuilderV32 == null) {
                        sourceCodeInfo = this.sourceCodeInfo_;
                    } else {
                        sourceCodeInfo = (SourceCodeInfo) singleFieldBuilderV32.build();
                    }
                    result.sourceCodeInfo_ = sourceCodeInfo;
                    i |= 8;
                }
                if ((i2 & 2048) != 0) {
                    result.syntax_ = this.syntax_;
                    i |= 16;
                }
                if ((i2 & 4096) != 0) {
                    result.edition_ = this.edition_;
                    i |= 32;
                }
                FileDescriptorProto.access$2476(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof FileDescriptorProto) {
                    return mergeFrom((FileDescriptorProto) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FileDescriptorProto other) {
                if (other == FileDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.name_ = other.name_;
                    this.bitField0_ |= 1;
                    onChanged();
                }
                if (other.hasPackage()) {
                    this.package_ = other.package_;
                    this.bitField0_ |= 2;
                    onChanged();
                }
                if (!other.dependency_.isEmpty()) {
                    if (this.dependency_.isEmpty()) {
                        this.dependency_ = other.dependency_;
                        this.bitField0_ |= 4;
                    } else {
                        ensureDependencyIsMutable();
                        this.dependency_.addAll(other.dependency_);
                    }
                    onChanged();
                }
                if (!other.publicDependency_.isEmpty()) {
                    if (this.publicDependency_.isEmpty()) {
                        this.publicDependency_ = other.publicDependency_;
                        this.bitField0_ &= -9;
                    } else {
                        ensurePublicDependencyIsMutable();
                        this.publicDependency_.addAll(other.publicDependency_);
                    }
                    onChanged();
                }
                if (!other.weakDependency_.isEmpty()) {
                    if (this.weakDependency_.isEmpty()) {
                        this.weakDependency_ = other.weakDependency_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureWeakDependencyIsMutable();
                        this.weakDependency_.addAll(other.weakDependency_);
                    }
                    onChanged();
                }
                if (this.messageTypeBuilder_ == null) {
                    if (!other.messageType_.isEmpty()) {
                        if (this.messageType_.isEmpty()) {
                            this.messageType_ = other.messageType_;
                            this.bitField0_ &= -33;
                        } else {
                            ensureMessageTypeIsMutable();
                            this.messageType_.addAll(other.messageType_);
                        }
                        onChanged();
                    }
                } else if (!other.messageType_.isEmpty()) {
                    if (!this.messageTypeBuilder_.isEmpty()) {
                        this.messageTypeBuilder_.addAllMessages(other.messageType_);
                    } else {
                        this.messageTypeBuilder_.dispose();
                        this.messageTypeBuilder_ = null;
                        this.messageType_ = other.messageType_;
                        this.bitField0_ &= -33;
                        this.messageTypeBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getMessageTypeFieldBuilder() : null;
                    }
                }
                if (this.enumTypeBuilder_ == null) {
                    if (!other.enumType_.isEmpty()) {
                        if (this.enumType_.isEmpty()) {
                            this.enumType_ = other.enumType_;
                            this.bitField0_ &= -65;
                        } else {
                            ensureEnumTypeIsMutable();
                            this.enumType_.addAll(other.enumType_);
                        }
                        onChanged();
                    }
                } else if (!other.enumType_.isEmpty()) {
                    if (!this.enumTypeBuilder_.isEmpty()) {
                        this.enumTypeBuilder_.addAllMessages(other.enumType_);
                    } else {
                        this.enumTypeBuilder_.dispose();
                        this.enumTypeBuilder_ = null;
                        this.enumType_ = other.enumType_;
                        this.bitField0_ &= -65;
                        this.enumTypeBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getEnumTypeFieldBuilder() : null;
                    }
                }
                if (this.serviceBuilder_ == null) {
                    if (!other.service_.isEmpty()) {
                        if (this.service_.isEmpty()) {
                            this.service_ = other.service_;
                            this.bitField0_ &= -129;
                        } else {
                            ensureServiceIsMutable();
                            this.service_.addAll(other.service_);
                        }
                        onChanged();
                    }
                } else if (!other.service_.isEmpty()) {
                    if (!this.serviceBuilder_.isEmpty()) {
                        this.serviceBuilder_.addAllMessages(other.service_);
                    } else {
                        this.serviceBuilder_.dispose();
                        this.serviceBuilder_ = null;
                        this.service_ = other.service_;
                        this.bitField0_ &= -129;
                        this.serviceBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getServiceFieldBuilder() : null;
                    }
                }
                if (this.extensionBuilder_ == null) {
                    if (!other.extension_.isEmpty()) {
                        if (this.extension_.isEmpty()) {
                            this.extension_ = other.extension_;
                            this.bitField0_ &= -257;
                        } else {
                            ensureExtensionIsMutable();
                            this.extension_.addAll(other.extension_);
                        }
                        onChanged();
                    }
                } else if (!other.extension_.isEmpty()) {
                    if (!this.extensionBuilder_.isEmpty()) {
                        this.extensionBuilder_.addAllMessages(other.extension_);
                    } else {
                        this.extensionBuilder_.dispose();
                        this.extensionBuilder_ = null;
                        this.extension_ = other.extension_;
                        this.bitField0_ &= -257;
                        this.extensionBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getExtensionFieldBuilder() : null;
                    }
                }
                if (other.hasOptions()) {
                    mergeOptions(other.getOptions());
                }
                if (other.hasSourceCodeInfo()) {
                    mergeSourceCodeInfo(other.getSourceCodeInfo());
                }
                if (other.hasSyntax()) {
                    this.syntax_ = other.syntax_;
                    this.bitField0_ |= 2048;
                    onChanged();
                }
                if (other.hasEdition()) {
                    this.edition_ = other.edition_;
                    this.bitField0_ |= 4096;
                    onChanged();
                }
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getMessageTypeCount(); i++) {
                    if (!getMessageType(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getEnumTypeCount(); i2++) {
                    if (!getEnumType(i2).isInitialized()) {
                        return false;
                    }
                }
                for (int i3 = 0; i3 < getServiceCount(); i3++) {
                    if (!getService(i3).isInitialized()) {
                        return false;
                    }
                }
                for (int i4 = 0; i4 < getExtensionCount(); i4++) {
                    if (!getExtension(i4).isInitialized()) {
                        return false;
                    }
                }
                return !hasOptions() || getOptions().isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    z = true;
                                    break;
                                case 10:
                                    this.name_ = input.readBytes();
                                    this.bitField0_ |= 1;
                                    break;
                                case 18:
                                    this.package_ = input.readBytes();
                                    this.bitField0_ |= 2;
                                    break;
                                case 26:
                                    ByteString bytes = input.readBytes();
                                    ensureDependencyIsMutable();
                                    this.dependency_.add(bytes);
                                    break;
                                case 34:
                                    DescriptorProto descriptorProto = (DescriptorProto) input.readMessage(DescriptorProto.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<DescriptorProto, DescriptorProto.Builder, DescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.messageTypeBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureMessageTypeIsMutable();
                                        this.messageType_.add(descriptorProto);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(descriptorProto);
                                    }
                                    break;
                                case 42:
                                    EnumDescriptorProto enumDescriptorProto = (EnumDescriptorProto) input.readMessage(EnumDescriptorProto.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> repeatedFieldBuilderV32 = this.enumTypeBuilder_;
                                    if (repeatedFieldBuilderV32 == null) {
                                        ensureEnumTypeIsMutable();
                                        this.enumType_.add(enumDescriptorProto);
                                    } else {
                                        repeatedFieldBuilderV32.addMessage(enumDescriptorProto);
                                    }
                                    break;
                                case 50:
                                    ServiceDescriptorProto serviceDescriptorProto = (ServiceDescriptorProto) input.readMessage(ServiceDescriptorProto.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<ServiceDescriptorProto, ServiceDescriptorProto.Builder, ServiceDescriptorProtoOrBuilder> repeatedFieldBuilderV33 = this.serviceBuilder_;
                                    if (repeatedFieldBuilderV33 == null) {
                                        ensureServiceIsMutable();
                                        this.service_.add(serviceDescriptorProto);
                                    } else {
                                        repeatedFieldBuilderV33.addMessage(serviceDescriptorProto);
                                    }
                                    break;
                                case 58:
                                    FieldDescriptorProto fieldDescriptorProto = (FieldDescriptorProto) input.readMessage(FieldDescriptorProto.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> repeatedFieldBuilderV34 = this.extensionBuilder_;
                                    if (repeatedFieldBuilderV34 == null) {
                                        ensureExtensionIsMutable();
                                        this.extension_.add(fieldDescriptorProto);
                                    } else {
                                        repeatedFieldBuilderV34.addMessage(fieldDescriptorProto);
                                    }
                                    break;
                                case 66:
                                    input.readMessage(getOptionsFieldBuilder().getBuilder(), extensionRegistry);
                                    this.bitField0_ |= 512;
                                    break;
                                case 74:
                                    input.readMessage(getSourceCodeInfoFieldBuilder().getBuilder(), extensionRegistry);
                                    this.bitField0_ |= 1024;
                                    break;
                                case 80:
                                    int int32 = input.readInt32();
                                    ensurePublicDependencyIsMutable();
                                    this.publicDependency_.addInt(int32);
                                    break;
                                case 82:
                                    int iPushLimit = input.pushLimit(input.readRawVarint32());
                                    ensurePublicDependencyIsMutable();
                                    while (input.getBytesUntilLimit() > 0) {
                                        this.publicDependency_.addInt(input.readInt32());
                                    }
                                    input.popLimit(iPushLimit);
                                    break;
                                case 88:
                                    int int322 = input.readInt32();
                                    ensureWeakDependencyIsMutable();
                                    this.weakDependency_.addInt(int322);
                                    break;
                                case 90:
                                    int iPushLimit2 = input.pushLimit(input.readRawVarint32());
                                    ensureWeakDependencyIsMutable();
                                    while (input.getBytesUntilLimit() > 0) {
                                        this.weakDependency_.addInt(input.readInt32());
                                    }
                                    input.popLimit(iPushLimit2);
                                    break;
                                case 98:
                                    this.syntax_ = input.readBytes();
                                    this.bitField0_ |= 2048;
                                    break;
                                case 106:
                                    this.edition_ = input.readBytes();
                                    this.bitField0_ |= 4096;
                                    break;
                                default:
                                    if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                        z = true;
                                    }
                                    break;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            public Builder setName(String value) {
                value.getClass();
                this.name_ = value;
                this.bitField0_ |= 1;
                onChanged();
                return this;
            }

            public Builder setPackage(String value) {
                value.getClass();
                this.package_ = value;
                this.bitField0_ |= 2;
                onChanged();
                return this;
            }

            private void ensureDependencyIsMutable() {
                if (!this.dependency_.isModifiable()) {
                    this.dependency_ = new LazyStringArrayList(this.dependency_);
                }
                this.bitField0_ |= 4;
            }

            @Override // com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder
            public int getDependencyCount() {
                return this.dependency_.size();
            }

            private void ensurePublicDependencyIsMutable() {
                if ((this.bitField0_ & 8) == 0) {
                    this.publicDependency_ = GeneratedMessageV3.mutableCopy(this.publicDependency_);
                    this.bitField0_ |= 8;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder
            public int getPublicDependencyCount() {
                return this.publicDependency_.size();
            }

            private void ensureWeakDependencyIsMutable() {
                if ((this.bitField0_ & 16) == 0) {
                    this.weakDependency_ = GeneratedMessageV3.mutableCopy(this.weakDependency_);
                    this.bitField0_ |= 16;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder
            public int getWeakDependencyCount() {
                return this.weakDependency_.size();
            }

            private void ensureMessageTypeIsMutable() {
                if ((this.bitField0_ & 32) == 0) {
                    this.messageType_ = new ArrayList(this.messageType_);
                    this.bitField0_ |= 32;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder
            public int getMessageTypeCount() {
                RepeatedFieldBuilderV3<DescriptorProto, DescriptorProto.Builder, DescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.messageTypeBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.messageType_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public DescriptorProto getMessageType(int index) {
                RepeatedFieldBuilderV3<DescriptorProto, DescriptorProto.Builder, DescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.messageTypeBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.messageType_.get(index);
                }
                return (DescriptorProto) repeatedFieldBuilderV3.getMessage(index);
            }

            public Builder addMessageType(DescriptorProto value) {
                RepeatedFieldBuilderV3<DescriptorProto, DescriptorProto.Builder, DescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.messageTypeBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    value.getClass();
                    ensureMessageTypeIsMutable();
                    this.messageType_.add(value);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(value);
                }
                return this;
            }

            private RepeatedFieldBuilderV3<DescriptorProto, DescriptorProto.Builder, DescriptorProtoOrBuilder> getMessageTypeFieldBuilder() {
                if (this.messageTypeBuilder_ == null) {
                    this.messageTypeBuilder_ = new RepeatedFieldBuilderV3<>(this.messageType_, (this.bitField0_ & 32) != 0, getParentForChildren(), isClean());
                    this.messageType_ = null;
                }
                return this.messageTypeBuilder_;
            }

            private void ensureEnumTypeIsMutable() {
                if ((this.bitField0_ & 64) == 0) {
                    this.enumType_ = new ArrayList(this.enumType_);
                    this.bitField0_ |= 64;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder
            public int getEnumTypeCount() {
                RepeatedFieldBuilderV3<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.enumTypeBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.enumType_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public EnumDescriptorProto getEnumType(int index) {
                RepeatedFieldBuilderV3<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.enumTypeBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.enumType_.get(index);
                }
                return (EnumDescriptorProto) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> getEnumTypeFieldBuilder() {
                if (this.enumTypeBuilder_ == null) {
                    this.enumTypeBuilder_ = new RepeatedFieldBuilderV3<>(this.enumType_, (this.bitField0_ & 64) != 0, getParentForChildren(), isClean());
                    this.enumType_ = null;
                }
                return this.enumTypeBuilder_;
            }

            private void ensureServiceIsMutable() {
                if ((this.bitField0_ & 128) == 0) {
                    this.service_ = new ArrayList(this.service_);
                    this.bitField0_ |= 128;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder
            public int getServiceCount() {
                RepeatedFieldBuilderV3<ServiceDescriptorProto, ServiceDescriptorProto.Builder, ServiceDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.service_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public ServiceDescriptorProto getService(int index) {
                RepeatedFieldBuilderV3<ServiceDescriptorProto, ServiceDescriptorProto.Builder, ServiceDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.serviceBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.service_.get(index);
                }
                return (ServiceDescriptorProto) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<ServiceDescriptorProto, ServiceDescriptorProto.Builder, ServiceDescriptorProtoOrBuilder> getServiceFieldBuilder() {
                if (this.serviceBuilder_ == null) {
                    this.serviceBuilder_ = new RepeatedFieldBuilderV3<>(this.service_, (this.bitField0_ & 128) != 0, getParentForChildren(), isClean());
                    this.service_ = null;
                }
                return this.serviceBuilder_;
            }

            private void ensureExtensionIsMutable() {
                if ((this.bitField0_ & 256) == 0) {
                    this.extension_ = new ArrayList(this.extension_);
                    this.bitField0_ |= 256;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder
            public int getExtensionCount() {
                RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.extensionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.extension_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public FieldDescriptorProto getExtension(int index) {
                RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.extensionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.extension_.get(index);
                }
                return (FieldDescriptorProto) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> getExtensionFieldBuilder() {
                if (this.extensionBuilder_ == null) {
                    this.extensionBuilder_ = new RepeatedFieldBuilderV3<>(this.extension_, (this.bitField0_ & 256) != 0, getParentForChildren(), isClean());
                    this.extension_ = null;
                }
                return this.extensionBuilder_;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 512) != 0;
            }

            public FileOptions getOptions() {
                SingleFieldBuilderV3<FileOptions, FileOptions.Builder, FileOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    FileOptions fileOptions = this.options_;
                    return fileOptions == null ? FileOptions.getDefaultInstance() : fileOptions;
                }
                return (FileOptions) singleFieldBuilderV3.getMessage();
            }

            public Builder mergeOptions(FileOptions value) {
                FileOptions fileOptions;
                SingleFieldBuilderV3<FileOptions, FileOptions.Builder, FileOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 512) != 0 && (fileOptions = this.options_) != null && fileOptions != FileOptions.getDefaultInstance()) {
                        getOptionsBuilder().mergeFrom(value);
                    } else {
                        this.options_ = value;
                    }
                } else {
                    singleFieldBuilderV3.mergeFrom(value);
                }
                this.bitField0_ |= 512;
                onChanged();
                return this;
            }

            public FileOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 512;
                onChanged();
                return (FileOptions.Builder) getOptionsFieldBuilder().getBuilder();
            }

            private SingleFieldBuilderV3<FileOptions, FileOptions.Builder, FileOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilderV3<>(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            public SourceCodeInfo getSourceCodeInfo() {
                SingleFieldBuilderV3<SourceCodeInfo, SourceCodeInfo.Builder, SourceCodeInfoOrBuilder> singleFieldBuilderV3 = this.sourceCodeInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    SourceCodeInfo sourceCodeInfo = this.sourceCodeInfo_;
                    return sourceCodeInfo == null ? SourceCodeInfo.getDefaultInstance() : sourceCodeInfo;
                }
                return (SourceCodeInfo) singleFieldBuilderV3.getMessage();
            }

            public Builder mergeSourceCodeInfo(SourceCodeInfo value) {
                SourceCodeInfo sourceCodeInfo;
                SingleFieldBuilderV3<SourceCodeInfo, SourceCodeInfo.Builder, SourceCodeInfoOrBuilder> singleFieldBuilderV3 = this.sourceCodeInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 1024) != 0 && (sourceCodeInfo = this.sourceCodeInfo_) != null && sourceCodeInfo != SourceCodeInfo.getDefaultInstance()) {
                        getSourceCodeInfoBuilder().mergeFrom(value);
                    } else {
                        this.sourceCodeInfo_ = value;
                    }
                } else {
                    singleFieldBuilderV3.mergeFrom(value);
                }
                this.bitField0_ |= 1024;
                onChanged();
                return this;
            }

            public SourceCodeInfo.Builder getSourceCodeInfoBuilder() {
                this.bitField0_ |= 1024;
                onChanged();
                return (SourceCodeInfo.Builder) getSourceCodeInfoFieldBuilder().getBuilder();
            }

            private SingleFieldBuilderV3<SourceCodeInfo, SourceCodeInfo.Builder, SourceCodeInfoOrBuilder> getSourceCodeInfoFieldBuilder() {
                if (this.sourceCodeInfoBuilder_ == null) {
                    this.sourceCodeInfoBuilder_ = new SingleFieldBuilderV3<>(getSourceCodeInfo(), getParentForChildren(), isClean());
                    this.sourceCodeInfo_ = null;
                }
                return this.sourceCodeInfoBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static FileDescriptorProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<FileDescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public FileDescriptorProto getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class DescriptorProto extends GeneratedMessageV3 implements DescriptorProtoOrBuilder {
        private static final DescriptorProto DEFAULT_INSTANCE = new DescriptorProto();

        @Deprecated
        public static final Parser<DescriptorProto> PARSER = new AbstractParser<DescriptorProto>() { // from class: com.google.protobuf.DescriptorProtos.DescriptorProto.1
            @Override // com.google.protobuf.Parser
            public DescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = DescriptorProto.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private int bitField0_;
        private List<EnumDescriptorProto> enumType_;
        private List<ExtensionRange> extensionRange_;
        private List<FieldDescriptorProto> extension_;
        private List<FieldDescriptorProto> field_;
        private byte memoizedIsInitialized;
        private volatile Object name_;
        private List<DescriptorProto> nestedType_;
        private List<OneofDescriptorProto> oneofDecl_;
        private MessageOptions options_;
        private LazyStringArrayList reservedName_;
        private List<ReservedRange> reservedRange_;

        public interface ExtensionRangeOrBuilder extends MessageOrBuilder {
            int getEnd();

            int getStart();
        }

        public interface ReservedRangeOrBuilder extends MessageOrBuilder {
            int getEnd();

            int getStart();
        }

        static /* synthetic */ int access$5776(DescriptorProto descriptorProto, int i) {
            int i2 = i | descriptorProto.bitField0_;
            descriptorProto.bitField0_ = i2;
            return i2;
        }

        private DescriptorProto(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.name_ = "";
            this.reservedName_ = LazyStringArrayList.emptyList();
            this.memoizedIsInitialized = (byte) -1;
        }

        private DescriptorProto() {
            this.name_ = "";
            this.reservedName_ = LazyStringArrayList.emptyList();
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
            this.field_ = Collections.emptyList();
            this.extension_ = Collections.emptyList();
            this.nestedType_ = Collections.emptyList();
            this.enumType_ = Collections.emptyList();
            this.extensionRange_ = Collections.emptyList();
            this.oneofDecl_ = Collections.emptyList();
            this.reservedRange_ = Collections.emptyList();
            this.reservedName_ = LazyStringArrayList.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new DescriptorProto();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProto.class, Builder.class);
        }

        public static final class ExtensionRange extends GeneratedMessageV3 implements ExtensionRangeOrBuilder {
            private static final ExtensionRange DEFAULT_INSTANCE = new ExtensionRange();

            @Deprecated
            public static final Parser<ExtensionRange> PARSER = new AbstractParser<ExtensionRange>() { // from class: com.google.protobuf.DescriptorProtos.DescriptorProto.ExtensionRange.1
                @Override // com.google.protobuf.Parser
                public ExtensionRange parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    Builder builderNewBuilder = ExtensionRange.newBuilder();
                    try {
                        builderNewBuilder.mergeFrom(input, extensionRegistry);
                        return builderNewBuilder.buildPartial();
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                    } catch (UninitializedMessageException e2) {
                        throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                    }
                }
            };
            private int bitField0_;
            private int end_;
            private byte memoizedIsInitialized;
            private ExtensionRangeOptions options_;
            private int start_;

            static /* synthetic */ int access$3576(ExtensionRange extensionRange, int i) {
                int i2 = i | extensionRange.bitField0_;
                extensionRange.bitField0_ = i2;
                return i2;
            }

            private ExtensionRange(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.start_ = 0;
                this.end_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            private ExtensionRange() {
                this.start_ = 0;
                this.end_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
                return new ExtensionRange();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable.ensureFieldAccessorsInitialized(ExtensionRange.class, Builder.class);
            }

            public boolean hasStart() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.google.protobuf.DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder
            public int getStart() {
                return this.start_;
            }

            public boolean hasEnd() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.google.protobuf.DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder
            public int getEnd() {
                return this.end_;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 4) != 0;
            }

            public ExtensionRangeOptions getOptions() {
                ExtensionRangeOptions extensionRangeOptions = this.options_;
                return extensionRangeOptions == null ? ExtensionRangeOptions.getDefaultInstance() : extensionRangeOptions;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                if (hasOptions() && !getOptions().isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    output.writeInt32(1, this.start_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    output.writeInt32(2, this.end_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    output.writeMessage(3, getOptions());
                }
                getUnknownFields().writeTo(output);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeInt32Size = (this.bitField0_ & 1) != 0 ? 0 + CodedOutputStream.computeInt32Size(1, this.start_) : 0;
                if ((this.bitField0_ & 2) != 0) {
                    iComputeInt32Size += CodedOutputStream.computeInt32Size(2, this.end_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    iComputeInt32Size += CodedOutputStream.computeMessageSize(3, getOptions());
                }
                int serializedSize = iComputeInt32Size + getUnknownFields().getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.AbstractMessage
            public boolean equals(final Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ExtensionRange)) {
                    return super.equals(obj);
                }
                ExtensionRange extensionRange = (ExtensionRange) obj;
                if (hasStart() != extensionRange.hasStart()) {
                    return false;
                }
                if ((hasStart() && getStart() != extensionRange.getStart()) || hasEnd() != extensionRange.hasEnd()) {
                    return false;
                }
                if ((!hasEnd() || getEnd() == extensionRange.getEnd()) && hasOptions() == extensionRange.hasOptions()) {
                    return (!hasOptions() || getOptions().equals(extensionRange.getOptions())) && getUnknownFields().equals(extensionRange.getUnknownFields());
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int iHashCode = 779 + getDescriptor().hashCode();
                if (hasStart()) {
                    iHashCode = (((iHashCode * 37) + 1) * 53) + getStart();
                }
                if (hasEnd()) {
                    iHashCode = (((iHashCode * 37) + 2) * 53) + getEnd();
                }
                if (hasOptions()) {
                    iHashCode = (((iHashCode * 37) + 3) * 53) + getOptions().hashCode();
                }
                int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
                return new Builder(parent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ExtensionRangeOrBuilder {
                private int bitField0_;
                private int end_;
                private SingleFieldBuilderV3<ExtensionRangeOptions, ExtensionRangeOptions.Builder, ExtensionRangeOptionsOrBuilder> optionsBuilder_;
                private ExtensionRangeOptions options_;
                private int start_;

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable.ensureFieldAccessorsInitialized(ExtensionRange.class, Builder.class);
                }

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent parent) {
                    super(parent);
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        getOptionsFieldBuilder();
                    }
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public ExtensionRange getDefaultInstanceForType() {
                    return ExtensionRange.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public ExtensionRange build() {
                    ExtensionRange extensionRangeBuildPartial = buildPartial();
                    if (extensionRangeBuildPartial.isInitialized()) {
                        return extensionRangeBuildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) extensionRangeBuildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public ExtensionRange buildPartial() {
                    ExtensionRange extensionRange = new ExtensionRange(this);
                    if (this.bitField0_ != 0) {
                        buildPartial0(extensionRange);
                    }
                    onBuilt();
                    return extensionRange;
                }

                private void buildPartial0(ExtensionRange result) {
                    int i;
                    ExtensionRangeOptions extensionRangeOptions;
                    int i2 = this.bitField0_;
                    if ((i2 & 1) != 0) {
                        result.start_ = this.start_;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 2) != 0) {
                        result.end_ = this.end_;
                        i |= 2;
                    }
                    if ((i2 & 4) != 0) {
                        SingleFieldBuilderV3<ExtensionRangeOptions, ExtensionRangeOptions.Builder, ExtensionRangeOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            extensionRangeOptions = this.options_;
                        } else {
                            extensionRangeOptions = (ExtensionRangeOptions) singleFieldBuilderV3.build();
                        }
                        result.options_ = extensionRangeOptions;
                        i |= 4;
                    }
                    ExtensionRange.access$3576(result, i);
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Builder mergeFrom(Message other) {
                    if (other instanceof ExtensionRange) {
                        return mergeFrom((ExtensionRange) other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(ExtensionRange other) {
                    if (other == ExtensionRange.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasStart()) {
                        setStart(other.getStart());
                    }
                    if (other.hasEnd()) {
                        setEnd(other.getEnd());
                    }
                    if (other.hasOptions()) {
                        mergeOptions(other.getOptions());
                    }
                    mergeUnknownFields(other.getUnknownFields());
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return !hasOptions() || getOptions().isInitialized();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    extensionRegistry.getClass();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                int tag = input.readTag();
                                if (tag != 0) {
                                    if (tag == 8) {
                                        this.start_ = input.readInt32();
                                        this.bitField0_ |= 1;
                                    } else if (tag == 16) {
                                        this.end_ = input.readInt32();
                                        this.bitField0_ |= 2;
                                    } else if (tag == 26) {
                                        input.readMessage(getOptionsFieldBuilder().getBuilder(), extensionRegistry);
                                        this.bitField0_ |= 4;
                                    } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                    }
                                }
                                z = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw e.unwrapIOException();
                            }
                        } finally {
                            onChanged();
                        }
                    }
                    return this;
                }

                @Override // com.google.protobuf.DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder
                public int getStart() {
                    return this.start_;
                }

                public Builder setStart(int value) {
                    this.start_ = value;
                    this.bitField0_ |= 1;
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder
                public int getEnd() {
                    return this.end_;
                }

                public Builder setEnd(int value) {
                    this.end_ = value;
                    this.bitField0_ |= 2;
                    onChanged();
                    return this;
                }

                public boolean hasOptions() {
                    return (this.bitField0_ & 4) != 0;
                }

                public ExtensionRangeOptions getOptions() {
                    SingleFieldBuilderV3<ExtensionRangeOptions, ExtensionRangeOptions.Builder, ExtensionRangeOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        ExtensionRangeOptions extensionRangeOptions = this.options_;
                        return extensionRangeOptions == null ? ExtensionRangeOptions.getDefaultInstance() : extensionRangeOptions;
                    }
                    return (ExtensionRangeOptions) singleFieldBuilderV3.getMessage();
                }

                public Builder mergeOptions(ExtensionRangeOptions value) {
                    ExtensionRangeOptions extensionRangeOptions;
                    SingleFieldBuilderV3<ExtensionRangeOptions, ExtensionRangeOptions.Builder, ExtensionRangeOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if ((this.bitField0_ & 4) != 0 && (extensionRangeOptions = this.options_) != null && extensionRangeOptions != ExtensionRangeOptions.getDefaultInstance()) {
                            getOptionsBuilder().mergeFrom(value);
                        } else {
                            this.options_ = value;
                        }
                    } else {
                        singleFieldBuilderV3.mergeFrom(value);
                    }
                    this.bitField0_ |= 4;
                    onChanged();
                    return this;
                }

                public ExtensionRangeOptions.Builder getOptionsBuilder() {
                    this.bitField0_ |= 4;
                    onChanged();
                    return (ExtensionRangeOptions.Builder) getOptionsFieldBuilder().getBuilder();
                }

                private SingleFieldBuilderV3<ExtensionRangeOptions, ExtensionRangeOptions.Builder, ExtensionRangeOptionsOrBuilder> getOptionsFieldBuilder() {
                    if (this.optionsBuilder_ == null) {
                        this.optionsBuilder_ = new SingleFieldBuilderV3<>(getOptions(), getParentForChildren(), isClean());
                        this.options_ = null;
                    }
                    return this.optionsBuilder_;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                    return (Builder) super.setUnknownFields(unknownFields);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
                public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                    return (Builder) super.mergeUnknownFields(unknownFields);
                }
            }

            public static ExtensionRange getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
            public Parser<ExtensionRange> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ExtensionRange getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public static final class ReservedRange extends GeneratedMessageV3 implements ReservedRangeOrBuilder {
            private static final ReservedRange DEFAULT_INSTANCE = new ReservedRange();

            @Deprecated
            public static final Parser<ReservedRange> PARSER = new AbstractParser<ReservedRange>() { // from class: com.google.protobuf.DescriptorProtos.DescriptorProto.ReservedRange.1
                @Override // com.google.protobuf.Parser
                public ReservedRange parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    Builder builderNewBuilder = ReservedRange.newBuilder();
                    try {
                        builderNewBuilder.mergeFrom(input, extensionRegistry);
                        return builderNewBuilder.buildPartial();
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                    } catch (UninitializedMessageException e2) {
                        throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                    }
                }
            };
            private int bitField0_;
            private int end_;
            private byte memoizedIsInitialized;
            private int start_;

            static /* synthetic */ int access$4376(ReservedRange reservedRange, int i) {
                int i2 = i | reservedRange.bitField0_;
                reservedRange.bitField0_ = i2;
                return i2;
            }

            private ReservedRange(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.start_ = 0;
                this.end_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            private ReservedRange() {
                this.start_ = 0;
                this.end_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
                return new ReservedRange();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ReservedRange_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ReservedRange_fieldAccessorTable.ensureFieldAccessorsInitialized(ReservedRange.class, Builder.class);
            }

            public boolean hasStart() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.google.protobuf.DescriptorProtos.DescriptorProto.ReservedRangeOrBuilder
            public int getStart() {
                return this.start_;
            }

            public boolean hasEnd() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.google.protobuf.DescriptorProtos.DescriptorProto.ReservedRangeOrBuilder
            public int getEnd() {
                return this.end_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    output.writeInt32(1, this.start_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    output.writeInt32(2, this.end_);
                }
                getUnknownFields().writeTo(output);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeInt32Size = (this.bitField0_ & 1) != 0 ? 0 + CodedOutputStream.computeInt32Size(1, this.start_) : 0;
                if ((this.bitField0_ & 2) != 0) {
                    iComputeInt32Size += CodedOutputStream.computeInt32Size(2, this.end_);
                }
                int serializedSize = iComputeInt32Size + getUnknownFields().getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.AbstractMessage
            public boolean equals(final Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ReservedRange)) {
                    return super.equals(obj);
                }
                ReservedRange reservedRange = (ReservedRange) obj;
                if (hasStart() != reservedRange.hasStart()) {
                    return false;
                }
                if ((!hasStart() || getStart() == reservedRange.getStart()) && hasEnd() == reservedRange.hasEnd()) {
                    return (!hasEnd() || getEnd() == reservedRange.getEnd()) && getUnknownFields().equals(reservedRange.getUnknownFields());
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int iHashCode = 779 + getDescriptor().hashCode();
                if (hasStart()) {
                    iHashCode = (((iHashCode * 37) + 1) * 53) + getStart();
                }
                if (hasEnd()) {
                    iHashCode = (((iHashCode * 37) + 2) * 53) + getEnd();
                }
                int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
                return new Builder(parent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ReservedRangeOrBuilder {
                private int bitField0_;
                private int end_;
                private int start_;

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ReservedRange_fieldAccessorTable.ensureFieldAccessorsInitialized(ReservedRange.class, Builder.class);
                }

                private Builder() {
                }

                private Builder(GeneratedMessageV3.BuilderParent parent) {
                    super(parent);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ReservedRange_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public ReservedRange getDefaultInstanceForType() {
                    return ReservedRange.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public ReservedRange build() {
                    ReservedRange reservedRangeBuildPartial = buildPartial();
                    if (reservedRangeBuildPartial.isInitialized()) {
                        return reservedRangeBuildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) reservedRangeBuildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public ReservedRange buildPartial() {
                    ReservedRange reservedRange = new ReservedRange(this);
                    if (this.bitField0_ != 0) {
                        buildPartial0(reservedRange);
                    }
                    onBuilt();
                    return reservedRange;
                }

                private void buildPartial0(ReservedRange result) {
                    int i;
                    int i2 = this.bitField0_;
                    if ((i2 & 1) != 0) {
                        result.start_ = this.start_;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 2) != 0) {
                        result.end_ = this.end_;
                        i |= 2;
                    }
                    ReservedRange.access$4376(result, i);
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Builder mergeFrom(Message other) {
                    if (other instanceof ReservedRange) {
                        return mergeFrom((ReservedRange) other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(ReservedRange other) {
                    if (other == ReservedRange.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasStart()) {
                        setStart(other.getStart());
                    }
                    if (other.hasEnd()) {
                        setEnd(other.getEnd());
                    }
                    mergeUnknownFields(other.getUnknownFields());
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    extensionRegistry.getClass();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                int tag = input.readTag();
                                if (tag != 0) {
                                    if (tag == 8) {
                                        this.start_ = input.readInt32();
                                        this.bitField0_ |= 1;
                                    } else if (tag == 16) {
                                        this.end_ = input.readInt32();
                                        this.bitField0_ |= 2;
                                    } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                    }
                                }
                                z = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw e.unwrapIOException();
                            }
                        } finally {
                            onChanged();
                        }
                    }
                    return this;
                }

                @Override // com.google.protobuf.DescriptorProtos.DescriptorProto.ReservedRangeOrBuilder
                public int getStart() {
                    return this.start_;
                }

                public Builder setStart(int value) {
                    this.start_ = value;
                    this.bitField0_ |= 1;
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.DescriptorProtos.DescriptorProto.ReservedRangeOrBuilder
                public int getEnd() {
                    return this.end_;
                }

                public Builder setEnd(int value) {
                    this.end_ = value;
                    this.bitField0_ |= 2;
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                    return (Builder) super.setUnknownFields(unknownFields);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
                public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                    return (Builder) super.mergeUnknownFields(unknownFields);
                }
            }

            public static ReservedRange getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
            public Parser<ReservedRange> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ReservedRange getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = stringUtf8;
            }
            return stringUtf8;
        }

        public List<FieldDescriptorProto> getFieldList() {
            return this.field_;
        }

        @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
        public int getFieldCount() {
            return this.field_.size();
        }

        public FieldDescriptorProto getField(int index) {
            return this.field_.get(index);
        }

        public List<FieldDescriptorProto> getExtensionList() {
            return this.extension_;
        }

        @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
        public int getExtensionCount() {
            return this.extension_.size();
        }

        public FieldDescriptorProto getExtension(int index) {
            return this.extension_.get(index);
        }

        public List<DescriptorProto> getNestedTypeList() {
            return this.nestedType_;
        }

        @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
        public int getNestedTypeCount() {
            return this.nestedType_.size();
        }

        public DescriptorProto getNestedType(int index) {
            return this.nestedType_.get(index);
        }

        public List<EnumDescriptorProto> getEnumTypeList() {
            return this.enumType_;
        }

        @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
        public int getEnumTypeCount() {
            return this.enumType_.size();
        }

        public EnumDescriptorProto getEnumType(int index) {
            return this.enumType_.get(index);
        }

        public List<ExtensionRange> getExtensionRangeList() {
            return this.extensionRange_;
        }

        @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
        public int getExtensionRangeCount() {
            return this.extensionRange_.size();
        }

        public ExtensionRange getExtensionRange(int index) {
            return this.extensionRange_.get(index);
        }

        public List<OneofDescriptorProto> getOneofDeclList() {
            return this.oneofDecl_;
        }

        @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
        public int getOneofDeclCount() {
            return this.oneofDecl_.size();
        }

        public OneofDescriptorProto getOneofDecl(int index) {
            return this.oneofDecl_.get(index);
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 2) != 0;
        }

        public MessageOptions getOptions() {
            MessageOptions messageOptions = this.options_;
            return messageOptions == null ? MessageOptions.getDefaultInstance() : messageOptions;
        }

        public List<ReservedRange> getReservedRangeList() {
            return this.reservedRange_;
        }

        @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
        public int getReservedRangeCount() {
            return this.reservedRange_.size();
        }

        public ProtocolStringList getReservedNameList() {
            return this.reservedName_;
        }

        @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
        public int getReservedNameCount() {
            return this.reservedName_.size();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getFieldCount(); i++) {
                if (!getField(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < getExtensionCount(); i2++) {
                if (!getExtension(i2).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i3 = 0; i3 < getNestedTypeCount(); i3++) {
                if (!getNestedType(i3).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i4 = 0; i4 < getEnumTypeCount(); i4++) {
                if (!getEnumType(i4).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i5 = 0; i5 < getExtensionRangeCount(); i5++) {
                if (!getExtensionRange(i5).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i6 = 0; i6 < getOneofDeclCount(); i6++) {
                if (!getOneofDecl(i6).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (hasOptions() && !getOptions().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString(output, 1, this.name_);
            }
            for (int i = 0; i < this.field_.size(); i++) {
                output.writeMessage(2, this.field_.get(i));
            }
            for (int i2 = 0; i2 < this.nestedType_.size(); i2++) {
                output.writeMessage(3, this.nestedType_.get(i2));
            }
            for (int i3 = 0; i3 < this.enumType_.size(); i3++) {
                output.writeMessage(4, this.enumType_.get(i3));
            }
            for (int i4 = 0; i4 < this.extensionRange_.size(); i4++) {
                output.writeMessage(5, this.extensionRange_.get(i4));
            }
            for (int i5 = 0; i5 < this.extension_.size(); i5++) {
                output.writeMessage(6, this.extension_.get(i5));
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeMessage(7, getOptions());
            }
            for (int i6 = 0; i6 < this.oneofDecl_.size(); i6++) {
                output.writeMessage(8, this.oneofDecl_.get(i6));
            }
            for (int i7 = 0; i7 < this.reservedRange_.size(); i7++) {
                output.writeMessage(9, this.reservedRange_.get(i7));
            }
            for (int i8 = 0; i8 < this.reservedName_.size(); i8++) {
                GeneratedMessageV3.writeString(output, 10, this.reservedName_.getRaw(i8));
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (this.bitField0_ & 1) != 0 ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
            for (int i2 = 0; i2 < this.field_.size(); i2++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(2, this.field_.get(i2));
            }
            for (int i3 = 0; i3 < this.nestedType_.size(); i3++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(3, this.nestedType_.get(i3));
            }
            for (int i4 = 0; i4 < this.enumType_.size(); i4++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(4, this.enumType_.get(i4));
            }
            for (int i5 = 0; i5 < this.extensionRange_.size(); i5++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(5, this.extensionRange_.get(i5));
            }
            for (int i6 = 0; i6 < this.extension_.size(); i6++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(6, this.extension_.get(i6));
            }
            if ((this.bitField0_ & 2) != 0) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(7, getOptions());
            }
            for (int i7 = 0; i7 < this.oneofDecl_.size(); i7++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(8, this.oneofDecl_.get(i7));
            }
            for (int i8 = 0; i8 < this.reservedRange_.size(); i8++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(9, this.reservedRange_.get(i8));
            }
            int iComputeStringSizeNoTag = 0;
            for (int i9 = 0; i9 < this.reservedName_.size(); i9++) {
                iComputeStringSizeNoTag += GeneratedMessageV3.computeStringSizeNoTag(this.reservedName_.getRaw(i9));
            }
            int size = iComputeStringSize + iComputeStringSizeNoTag + (getReservedNameList().size() * 1) + getUnknownFields().getSerializedSize();
            this.memoizedSize = size;
            return size;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DescriptorProto)) {
                return super.equals(obj);
            }
            DescriptorProto descriptorProto = (DescriptorProto) obj;
            if (hasName() != descriptorProto.hasName()) {
                return false;
            }
            if ((!hasName() || getName().equals(descriptorProto.getName())) && getFieldList().equals(descriptorProto.getFieldList()) && getExtensionList().equals(descriptorProto.getExtensionList()) && getNestedTypeList().equals(descriptorProto.getNestedTypeList()) && getEnumTypeList().equals(descriptorProto.getEnumTypeList()) && getExtensionRangeList().equals(descriptorProto.getExtensionRangeList()) && getOneofDeclList().equals(descriptorProto.getOneofDeclList()) && hasOptions() == descriptorProto.hasOptions()) {
                return (!hasOptions() || getOptions().equals(descriptorProto.getOptions())) && getReservedRangeList().equals(descriptorProto.getReservedRangeList()) && getReservedNameList().equals(descriptorProto.getReservedNameList()) && getUnknownFields().equals(descriptorProto.getUnknownFields());
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasName()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getName().hashCode();
            }
            if (getFieldCount() > 0) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getFieldList().hashCode();
            }
            if (getExtensionCount() > 0) {
                iHashCode = (((iHashCode * 37) + 6) * 53) + getExtensionList().hashCode();
            }
            if (getNestedTypeCount() > 0) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getNestedTypeList().hashCode();
            }
            if (getEnumTypeCount() > 0) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + getEnumTypeList().hashCode();
            }
            if (getExtensionRangeCount() > 0) {
                iHashCode = (((iHashCode * 37) + 5) * 53) + getExtensionRangeList().hashCode();
            }
            if (getOneofDeclCount() > 0) {
                iHashCode = (((iHashCode * 37) + 8) * 53) + getOneofDeclList().hashCode();
            }
            if (hasOptions()) {
                iHashCode = (((iHashCode * 37) + 7) * 53) + getOptions().hashCode();
            }
            if (getReservedRangeCount() > 0) {
                iHashCode = (((iHashCode * 37) + 9) * 53) + getReservedRangeList().hashCode();
            }
            if (getReservedNameCount() > 0) {
                iHashCode = (((iHashCode * 37) + 10) * 53) + getReservedNameList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DescriptorProtoOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> enumTypeBuilder_;
            private List<EnumDescriptorProto> enumType_;
            private RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> extensionBuilder_;
            private RepeatedFieldBuilderV3<ExtensionRange, ExtensionRange.Builder, ExtensionRangeOrBuilder> extensionRangeBuilder_;
            private List<ExtensionRange> extensionRange_;
            private List<FieldDescriptorProto> extension_;
            private RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> fieldBuilder_;
            private List<FieldDescriptorProto> field_;
            private Object name_;
            private RepeatedFieldBuilderV3<DescriptorProto, Builder, DescriptorProtoOrBuilder> nestedTypeBuilder_;
            private List<DescriptorProto> nestedType_;
            private RepeatedFieldBuilderV3<OneofDescriptorProto, OneofDescriptorProto.Builder, Object> oneofDeclBuilder_;
            private List<OneofDescriptorProto> oneofDecl_;
            private SingleFieldBuilderV3<MessageOptions, MessageOptions.Builder, MessageOptionsOrBuilder> optionsBuilder_;
            private MessageOptions options_;
            private LazyStringArrayList reservedName_;
            private RepeatedFieldBuilderV3<ReservedRange, ReservedRange.Builder, ReservedRangeOrBuilder> reservedRangeBuilder_;
            private List<ReservedRange> reservedRange_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.name_ = "";
                this.field_ = Collections.emptyList();
                this.extension_ = Collections.emptyList();
                this.nestedType_ = Collections.emptyList();
                this.enumType_ = Collections.emptyList();
                this.extensionRange_ = Collections.emptyList();
                this.oneofDecl_ = Collections.emptyList();
                this.reservedRange_ = Collections.emptyList();
                this.reservedName_ = LazyStringArrayList.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.name_ = "";
                this.field_ = Collections.emptyList();
                this.extension_ = Collections.emptyList();
                this.nestedType_ = Collections.emptyList();
                this.enumType_ = Collections.emptyList();
                this.extensionRange_ = Collections.emptyList();
                this.oneofDecl_ = Collections.emptyList();
                this.reservedRange_ = Collections.emptyList();
                this.reservedName_ = LazyStringArrayList.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getFieldFieldBuilder();
                    getExtensionFieldBuilder();
                    getNestedTypeFieldBuilder();
                    getEnumTypeFieldBuilder();
                    getExtensionRangeFieldBuilder();
                    getOneofDeclFieldBuilder();
                    getOptionsFieldBuilder();
                    getReservedRangeFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public DescriptorProto getDefaultInstanceForType() {
                return DescriptorProto.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DescriptorProto build() {
                DescriptorProto descriptorProtoBuildPartial = buildPartial();
                if (descriptorProtoBuildPartial.isInitialized()) {
                    return descriptorProtoBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) descriptorProtoBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DescriptorProto buildPartial() {
                DescriptorProto descriptorProto = new DescriptorProto(this);
                buildPartialRepeatedFields(descriptorProto);
                if (this.bitField0_ != 0) {
                    buildPartial0(descriptorProto);
                }
                onBuilt();
                return descriptorProto;
            }

            private void buildPartialRepeatedFields(DescriptorProto result) {
                RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.fieldBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.field_ = repeatedFieldBuilderV3.build();
                } else {
                    if ((this.bitField0_ & 2) != 0) {
                        this.field_ = Collections.unmodifiableList(this.field_);
                        this.bitField0_ &= -3;
                    }
                    result.field_ = this.field_;
                }
                RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> repeatedFieldBuilderV32 = this.extensionBuilder_;
                if (repeatedFieldBuilderV32 != null) {
                    result.extension_ = repeatedFieldBuilderV32.build();
                } else {
                    if ((this.bitField0_ & 4) != 0) {
                        this.extension_ = Collections.unmodifiableList(this.extension_);
                        this.bitField0_ &= -5;
                    }
                    result.extension_ = this.extension_;
                }
                RepeatedFieldBuilderV3<DescriptorProto, Builder, DescriptorProtoOrBuilder> repeatedFieldBuilderV33 = this.nestedTypeBuilder_;
                if (repeatedFieldBuilderV33 != null) {
                    result.nestedType_ = repeatedFieldBuilderV33.build();
                } else {
                    if ((this.bitField0_ & 8) != 0) {
                        this.nestedType_ = Collections.unmodifiableList(this.nestedType_);
                        this.bitField0_ &= -9;
                    }
                    result.nestedType_ = this.nestedType_;
                }
                RepeatedFieldBuilderV3<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> repeatedFieldBuilderV34 = this.enumTypeBuilder_;
                if (repeatedFieldBuilderV34 != null) {
                    result.enumType_ = repeatedFieldBuilderV34.build();
                } else {
                    if ((this.bitField0_ & 16) != 0) {
                        this.enumType_ = Collections.unmodifiableList(this.enumType_);
                        this.bitField0_ &= -17;
                    }
                    result.enumType_ = this.enumType_;
                }
                RepeatedFieldBuilderV3<ExtensionRange, ExtensionRange.Builder, ExtensionRangeOrBuilder> repeatedFieldBuilderV35 = this.extensionRangeBuilder_;
                if (repeatedFieldBuilderV35 != null) {
                    result.extensionRange_ = repeatedFieldBuilderV35.build();
                } else {
                    if ((this.bitField0_ & 32) != 0) {
                        this.extensionRange_ = Collections.unmodifiableList(this.extensionRange_);
                        this.bitField0_ &= -33;
                    }
                    result.extensionRange_ = this.extensionRange_;
                }
                RepeatedFieldBuilderV3<OneofDescriptorProto, OneofDescriptorProto.Builder, Object> repeatedFieldBuilderV36 = this.oneofDeclBuilder_;
                if (repeatedFieldBuilderV36 != null) {
                    result.oneofDecl_ = repeatedFieldBuilderV36.build();
                } else {
                    if ((this.bitField0_ & 64) != 0) {
                        this.oneofDecl_ = Collections.unmodifiableList(this.oneofDecl_);
                        this.bitField0_ &= -65;
                    }
                    result.oneofDecl_ = this.oneofDecl_;
                }
                RepeatedFieldBuilderV3<ReservedRange, ReservedRange.Builder, ReservedRangeOrBuilder> repeatedFieldBuilderV37 = this.reservedRangeBuilder_;
                if (repeatedFieldBuilderV37 != null) {
                    result.reservedRange_ = repeatedFieldBuilderV37.build();
                    return;
                }
                if ((this.bitField0_ & 256) != 0) {
                    this.reservedRange_ = Collections.unmodifiableList(this.reservedRange_);
                    this.bitField0_ &= -257;
                }
                result.reservedRange_ = this.reservedRange_;
            }

            private void buildPartial0(DescriptorProto result) {
                int i;
                MessageOptions messageOptions;
                int i2 = this.bitField0_;
                if ((i2 & 1) != 0) {
                    result.name_ = this.name_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 128) != 0) {
                    SingleFieldBuilderV3<MessageOptions, MessageOptions.Builder, MessageOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        messageOptions = this.options_;
                    } else {
                        messageOptions = (MessageOptions) singleFieldBuilderV3.build();
                    }
                    result.options_ = messageOptions;
                    i |= 2;
                }
                if ((i2 & 512) != 0) {
                    this.reservedName_.makeImmutable();
                    result.reservedName_ = this.reservedName_;
                }
                DescriptorProto.access$5776(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof DescriptorProto) {
                    return mergeFrom((DescriptorProto) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(DescriptorProto other) {
                if (other == DescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.name_ = other.name_;
                    this.bitField0_ |= 1;
                    onChanged();
                }
                if (this.fieldBuilder_ == null) {
                    if (!other.field_.isEmpty()) {
                        if (this.field_.isEmpty()) {
                            this.field_ = other.field_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureFieldIsMutable();
                            this.field_.addAll(other.field_);
                        }
                        onChanged();
                    }
                } else if (!other.field_.isEmpty()) {
                    if (!this.fieldBuilder_.isEmpty()) {
                        this.fieldBuilder_.addAllMessages(other.field_);
                    } else {
                        this.fieldBuilder_.dispose();
                        this.fieldBuilder_ = null;
                        this.field_ = other.field_;
                        this.bitField0_ &= -3;
                        this.fieldBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getFieldFieldBuilder() : null;
                    }
                }
                if (this.extensionBuilder_ == null) {
                    if (!other.extension_.isEmpty()) {
                        if (this.extension_.isEmpty()) {
                            this.extension_ = other.extension_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureExtensionIsMutable();
                            this.extension_.addAll(other.extension_);
                        }
                        onChanged();
                    }
                } else if (!other.extension_.isEmpty()) {
                    if (!this.extensionBuilder_.isEmpty()) {
                        this.extensionBuilder_.addAllMessages(other.extension_);
                    } else {
                        this.extensionBuilder_.dispose();
                        this.extensionBuilder_ = null;
                        this.extension_ = other.extension_;
                        this.bitField0_ &= -5;
                        this.extensionBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getExtensionFieldBuilder() : null;
                    }
                }
                if (this.nestedTypeBuilder_ == null) {
                    if (!other.nestedType_.isEmpty()) {
                        if (this.nestedType_.isEmpty()) {
                            this.nestedType_ = other.nestedType_;
                            this.bitField0_ &= -9;
                        } else {
                            ensureNestedTypeIsMutable();
                            this.nestedType_.addAll(other.nestedType_);
                        }
                        onChanged();
                    }
                } else if (!other.nestedType_.isEmpty()) {
                    if (!this.nestedTypeBuilder_.isEmpty()) {
                        this.nestedTypeBuilder_.addAllMessages(other.nestedType_);
                    } else {
                        this.nestedTypeBuilder_.dispose();
                        this.nestedTypeBuilder_ = null;
                        this.nestedType_ = other.nestedType_;
                        this.bitField0_ &= -9;
                        this.nestedTypeBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getNestedTypeFieldBuilder() : null;
                    }
                }
                if (this.enumTypeBuilder_ == null) {
                    if (!other.enumType_.isEmpty()) {
                        if (this.enumType_.isEmpty()) {
                            this.enumType_ = other.enumType_;
                            this.bitField0_ &= -17;
                        } else {
                            ensureEnumTypeIsMutable();
                            this.enumType_.addAll(other.enumType_);
                        }
                        onChanged();
                    }
                } else if (!other.enumType_.isEmpty()) {
                    if (!this.enumTypeBuilder_.isEmpty()) {
                        this.enumTypeBuilder_.addAllMessages(other.enumType_);
                    } else {
                        this.enumTypeBuilder_.dispose();
                        this.enumTypeBuilder_ = null;
                        this.enumType_ = other.enumType_;
                        this.bitField0_ &= -17;
                        this.enumTypeBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getEnumTypeFieldBuilder() : null;
                    }
                }
                if (this.extensionRangeBuilder_ == null) {
                    if (!other.extensionRange_.isEmpty()) {
                        if (this.extensionRange_.isEmpty()) {
                            this.extensionRange_ = other.extensionRange_;
                            this.bitField0_ &= -33;
                        } else {
                            ensureExtensionRangeIsMutable();
                            this.extensionRange_.addAll(other.extensionRange_);
                        }
                        onChanged();
                    }
                } else if (!other.extensionRange_.isEmpty()) {
                    if (!this.extensionRangeBuilder_.isEmpty()) {
                        this.extensionRangeBuilder_.addAllMessages(other.extensionRange_);
                    } else {
                        this.extensionRangeBuilder_.dispose();
                        this.extensionRangeBuilder_ = null;
                        this.extensionRange_ = other.extensionRange_;
                        this.bitField0_ &= -33;
                        this.extensionRangeBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getExtensionRangeFieldBuilder() : null;
                    }
                }
                if (this.oneofDeclBuilder_ == null) {
                    if (!other.oneofDecl_.isEmpty()) {
                        if (this.oneofDecl_.isEmpty()) {
                            this.oneofDecl_ = other.oneofDecl_;
                            this.bitField0_ &= -65;
                        } else {
                            ensureOneofDeclIsMutable();
                            this.oneofDecl_.addAll(other.oneofDecl_);
                        }
                        onChanged();
                    }
                } else if (!other.oneofDecl_.isEmpty()) {
                    if (!this.oneofDeclBuilder_.isEmpty()) {
                        this.oneofDeclBuilder_.addAllMessages(other.oneofDecl_);
                    } else {
                        this.oneofDeclBuilder_.dispose();
                        this.oneofDeclBuilder_ = null;
                        this.oneofDecl_ = other.oneofDecl_;
                        this.bitField0_ &= -65;
                        this.oneofDeclBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getOneofDeclFieldBuilder() : null;
                    }
                }
                if (other.hasOptions()) {
                    mergeOptions(other.getOptions());
                }
                if (this.reservedRangeBuilder_ == null) {
                    if (!other.reservedRange_.isEmpty()) {
                        if (this.reservedRange_.isEmpty()) {
                            this.reservedRange_ = other.reservedRange_;
                            this.bitField0_ &= -257;
                        } else {
                            ensureReservedRangeIsMutable();
                            this.reservedRange_.addAll(other.reservedRange_);
                        }
                        onChanged();
                    }
                } else if (!other.reservedRange_.isEmpty()) {
                    if (!this.reservedRangeBuilder_.isEmpty()) {
                        this.reservedRangeBuilder_.addAllMessages(other.reservedRange_);
                    } else {
                        this.reservedRangeBuilder_.dispose();
                        this.reservedRangeBuilder_ = null;
                        this.reservedRange_ = other.reservedRange_;
                        this.bitField0_ &= -257;
                        this.reservedRangeBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getReservedRangeFieldBuilder() : null;
                    }
                }
                if (!other.reservedName_.isEmpty()) {
                    if (this.reservedName_.isEmpty()) {
                        this.reservedName_ = other.reservedName_;
                        this.bitField0_ |= 512;
                    } else {
                        ensureReservedNameIsMutable();
                        this.reservedName_.addAll(other.reservedName_);
                    }
                    onChanged();
                }
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getFieldCount(); i++) {
                    if (!getField(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getExtensionCount(); i2++) {
                    if (!getExtension(i2).isInitialized()) {
                        return false;
                    }
                }
                for (int i3 = 0; i3 < getNestedTypeCount(); i3++) {
                    if (!getNestedType(i3).isInitialized()) {
                        return false;
                    }
                }
                for (int i4 = 0; i4 < getEnumTypeCount(); i4++) {
                    if (!getEnumType(i4).isInitialized()) {
                        return false;
                    }
                }
                for (int i5 = 0; i5 < getExtensionRangeCount(); i5++) {
                    if (!getExtensionRange(i5).isInitialized()) {
                        return false;
                    }
                }
                for (int i6 = 0; i6 < getOneofDeclCount(); i6++) {
                    if (!getOneofDecl(i6).isInitialized()) {
                        return false;
                    }
                }
                return !hasOptions() || getOptions().isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    z = true;
                                    break;
                                case 10:
                                    this.name_ = input.readBytes();
                                    this.bitField0_ |= 1;
                                    break;
                                case 18:
                                    FieldDescriptorProto fieldDescriptorProto = (FieldDescriptorProto) input.readMessage(FieldDescriptorProto.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.fieldBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureFieldIsMutable();
                                        this.field_.add(fieldDescriptorProto);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(fieldDescriptorProto);
                                    }
                                    break;
                                case 26:
                                    DescriptorProto descriptorProto = (DescriptorProto) input.readMessage(DescriptorProto.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<DescriptorProto, Builder, DescriptorProtoOrBuilder> repeatedFieldBuilderV32 = this.nestedTypeBuilder_;
                                    if (repeatedFieldBuilderV32 == null) {
                                        ensureNestedTypeIsMutable();
                                        this.nestedType_.add(descriptorProto);
                                    } else {
                                        repeatedFieldBuilderV32.addMessage(descriptorProto);
                                    }
                                    break;
                                case 34:
                                    EnumDescriptorProto enumDescriptorProto = (EnumDescriptorProto) input.readMessage(EnumDescriptorProto.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> repeatedFieldBuilderV33 = this.enumTypeBuilder_;
                                    if (repeatedFieldBuilderV33 == null) {
                                        ensureEnumTypeIsMutable();
                                        this.enumType_.add(enumDescriptorProto);
                                    } else {
                                        repeatedFieldBuilderV33.addMessage(enumDescriptorProto);
                                    }
                                    break;
                                case 42:
                                    ExtensionRange extensionRange = (ExtensionRange) input.readMessage(ExtensionRange.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<ExtensionRange, ExtensionRange.Builder, ExtensionRangeOrBuilder> repeatedFieldBuilderV34 = this.extensionRangeBuilder_;
                                    if (repeatedFieldBuilderV34 == null) {
                                        ensureExtensionRangeIsMutable();
                                        this.extensionRange_.add(extensionRange);
                                    } else {
                                        repeatedFieldBuilderV34.addMessage(extensionRange);
                                    }
                                    break;
                                case 50:
                                    FieldDescriptorProto fieldDescriptorProto2 = (FieldDescriptorProto) input.readMessage(FieldDescriptorProto.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> repeatedFieldBuilderV35 = this.extensionBuilder_;
                                    if (repeatedFieldBuilderV35 == null) {
                                        ensureExtensionIsMutable();
                                        this.extension_.add(fieldDescriptorProto2);
                                    } else {
                                        repeatedFieldBuilderV35.addMessage(fieldDescriptorProto2);
                                    }
                                    break;
                                case 58:
                                    input.readMessage(getOptionsFieldBuilder().getBuilder(), extensionRegistry);
                                    this.bitField0_ |= 128;
                                    break;
                                case 66:
                                    OneofDescriptorProto oneofDescriptorProto = (OneofDescriptorProto) input.readMessage(OneofDescriptorProto.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<OneofDescriptorProto, OneofDescriptorProto.Builder, Object> repeatedFieldBuilderV36 = this.oneofDeclBuilder_;
                                    if (repeatedFieldBuilderV36 == null) {
                                        ensureOneofDeclIsMutable();
                                        this.oneofDecl_.add(oneofDescriptorProto);
                                    } else {
                                        repeatedFieldBuilderV36.addMessage(oneofDescriptorProto);
                                    }
                                    break;
                                case 74:
                                    ReservedRange reservedRange = (ReservedRange) input.readMessage(ReservedRange.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<ReservedRange, ReservedRange.Builder, ReservedRangeOrBuilder> repeatedFieldBuilderV37 = this.reservedRangeBuilder_;
                                    if (repeatedFieldBuilderV37 == null) {
                                        ensureReservedRangeIsMutable();
                                        this.reservedRange_.add(reservedRange);
                                    } else {
                                        repeatedFieldBuilderV37.addMessage(reservedRange);
                                    }
                                    break;
                                case 82:
                                    ByteString bytes = input.readBytes();
                                    ensureReservedNameIsMutable();
                                    this.reservedName_.add(bytes);
                                    break;
                                default:
                                    if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                        z = true;
                                    }
                                    break;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            public Builder setName(String value) {
                value.getClass();
                this.name_ = value;
                this.bitField0_ |= 1;
                onChanged();
                return this;
            }

            private void ensureFieldIsMutable() {
                if ((this.bitField0_ & 2) == 0) {
                    this.field_ = new ArrayList(this.field_);
                    this.bitField0_ |= 2;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
            public int getFieldCount() {
                RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.fieldBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.field_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public FieldDescriptorProto getField(int index) {
                RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.fieldBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.field_.get(index);
                }
                return (FieldDescriptorProto) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> getFieldFieldBuilder() {
                if (this.fieldBuilder_ == null) {
                    this.fieldBuilder_ = new RepeatedFieldBuilderV3<>(this.field_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                    this.field_ = null;
                }
                return this.fieldBuilder_;
            }

            private void ensureExtensionIsMutable() {
                if ((this.bitField0_ & 4) == 0) {
                    this.extension_ = new ArrayList(this.extension_);
                    this.bitField0_ |= 4;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
            public int getExtensionCount() {
                RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.extensionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.extension_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public FieldDescriptorProto getExtension(int index) {
                RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.extensionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.extension_.get(index);
                }
                return (FieldDescriptorProto) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> getExtensionFieldBuilder() {
                if (this.extensionBuilder_ == null) {
                    this.extensionBuilder_ = new RepeatedFieldBuilderV3<>(this.extension_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                    this.extension_ = null;
                }
                return this.extensionBuilder_;
            }

            private void ensureNestedTypeIsMutable() {
                if ((this.bitField0_ & 8) == 0) {
                    this.nestedType_ = new ArrayList(this.nestedType_);
                    this.bitField0_ |= 8;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
            public int getNestedTypeCount() {
                RepeatedFieldBuilderV3<DescriptorProto, Builder, DescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.nestedTypeBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.nestedType_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public DescriptorProto getNestedType(int index) {
                RepeatedFieldBuilderV3<DescriptorProto, Builder, DescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.nestedTypeBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.nestedType_.get(index);
                }
                return (DescriptorProto) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<DescriptorProto, Builder, DescriptorProtoOrBuilder> getNestedTypeFieldBuilder() {
                if (this.nestedTypeBuilder_ == null) {
                    this.nestedTypeBuilder_ = new RepeatedFieldBuilderV3<>(this.nestedType_, (this.bitField0_ & 8) != 0, getParentForChildren(), isClean());
                    this.nestedType_ = null;
                }
                return this.nestedTypeBuilder_;
            }

            private void ensureEnumTypeIsMutable() {
                if ((this.bitField0_ & 16) == 0) {
                    this.enumType_ = new ArrayList(this.enumType_);
                    this.bitField0_ |= 16;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
            public int getEnumTypeCount() {
                RepeatedFieldBuilderV3<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.enumTypeBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.enumType_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public EnumDescriptorProto getEnumType(int index) {
                RepeatedFieldBuilderV3<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.enumTypeBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.enumType_.get(index);
                }
                return (EnumDescriptorProto) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> getEnumTypeFieldBuilder() {
                if (this.enumTypeBuilder_ == null) {
                    this.enumTypeBuilder_ = new RepeatedFieldBuilderV3<>(this.enumType_, (this.bitField0_ & 16) != 0, getParentForChildren(), isClean());
                    this.enumType_ = null;
                }
                return this.enumTypeBuilder_;
            }

            private void ensureExtensionRangeIsMutable() {
                if ((this.bitField0_ & 32) == 0) {
                    this.extensionRange_ = new ArrayList(this.extensionRange_);
                    this.bitField0_ |= 32;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
            public int getExtensionRangeCount() {
                RepeatedFieldBuilderV3<ExtensionRange, ExtensionRange.Builder, ExtensionRangeOrBuilder> repeatedFieldBuilderV3 = this.extensionRangeBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.extensionRange_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public ExtensionRange getExtensionRange(int index) {
                RepeatedFieldBuilderV3<ExtensionRange, ExtensionRange.Builder, ExtensionRangeOrBuilder> repeatedFieldBuilderV3 = this.extensionRangeBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.extensionRange_.get(index);
                }
                return (ExtensionRange) repeatedFieldBuilderV3.getMessage(index);
            }

            public Builder addExtensionRange(ExtensionRange value) {
                RepeatedFieldBuilderV3<ExtensionRange, ExtensionRange.Builder, ExtensionRangeOrBuilder> repeatedFieldBuilderV3 = this.extensionRangeBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    value.getClass();
                    ensureExtensionRangeIsMutable();
                    this.extensionRange_.add(value);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(value);
                }
                return this;
            }

            private RepeatedFieldBuilderV3<ExtensionRange, ExtensionRange.Builder, ExtensionRangeOrBuilder> getExtensionRangeFieldBuilder() {
                if (this.extensionRangeBuilder_ == null) {
                    this.extensionRangeBuilder_ = new RepeatedFieldBuilderV3<>(this.extensionRange_, (this.bitField0_ & 32) != 0, getParentForChildren(), isClean());
                    this.extensionRange_ = null;
                }
                return this.extensionRangeBuilder_;
            }

            private void ensureOneofDeclIsMutable() {
                if ((this.bitField0_ & 64) == 0) {
                    this.oneofDecl_ = new ArrayList(this.oneofDecl_);
                    this.bitField0_ |= 64;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
            public int getOneofDeclCount() {
                RepeatedFieldBuilderV3<OneofDescriptorProto, OneofDescriptorProto.Builder, Object> repeatedFieldBuilderV3 = this.oneofDeclBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.oneofDecl_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public OneofDescriptorProto getOneofDecl(int index) {
                RepeatedFieldBuilderV3<OneofDescriptorProto, OneofDescriptorProto.Builder, Object> repeatedFieldBuilderV3 = this.oneofDeclBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.oneofDecl_.get(index);
                }
                return (OneofDescriptorProto) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<OneofDescriptorProto, OneofDescriptorProto.Builder, Object> getOneofDeclFieldBuilder() {
                if (this.oneofDeclBuilder_ == null) {
                    this.oneofDeclBuilder_ = new RepeatedFieldBuilderV3<>(this.oneofDecl_, (this.bitField0_ & 64) != 0, getParentForChildren(), isClean());
                    this.oneofDecl_ = null;
                }
                return this.oneofDeclBuilder_;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 128) != 0;
            }

            public MessageOptions getOptions() {
                SingleFieldBuilderV3<MessageOptions, MessageOptions.Builder, MessageOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    MessageOptions messageOptions = this.options_;
                    return messageOptions == null ? MessageOptions.getDefaultInstance() : messageOptions;
                }
                return (MessageOptions) singleFieldBuilderV3.getMessage();
            }

            public Builder mergeOptions(MessageOptions value) {
                MessageOptions messageOptions;
                SingleFieldBuilderV3<MessageOptions, MessageOptions.Builder, MessageOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 128) != 0 && (messageOptions = this.options_) != null && messageOptions != MessageOptions.getDefaultInstance()) {
                        getOptionsBuilder().mergeFrom(value);
                    } else {
                        this.options_ = value;
                    }
                } else {
                    singleFieldBuilderV3.mergeFrom(value);
                }
                this.bitField0_ |= 128;
                onChanged();
                return this;
            }

            public MessageOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 128;
                onChanged();
                return (MessageOptions.Builder) getOptionsFieldBuilder().getBuilder();
            }

            private SingleFieldBuilderV3<MessageOptions, MessageOptions.Builder, MessageOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilderV3<>(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            private void ensureReservedRangeIsMutable() {
                if ((this.bitField0_ & 256) == 0) {
                    this.reservedRange_ = new ArrayList(this.reservedRange_);
                    this.bitField0_ |= 256;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
            public int getReservedRangeCount() {
                RepeatedFieldBuilderV3<ReservedRange, ReservedRange.Builder, ReservedRangeOrBuilder> repeatedFieldBuilderV3 = this.reservedRangeBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.reservedRange_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            private RepeatedFieldBuilderV3<ReservedRange, ReservedRange.Builder, ReservedRangeOrBuilder> getReservedRangeFieldBuilder() {
                if (this.reservedRangeBuilder_ == null) {
                    this.reservedRangeBuilder_ = new RepeatedFieldBuilderV3<>(this.reservedRange_, (this.bitField0_ & 256) != 0, getParentForChildren(), isClean());
                    this.reservedRange_ = null;
                }
                return this.reservedRangeBuilder_;
            }

            private void ensureReservedNameIsMutable() {
                if (!this.reservedName_.isModifiable()) {
                    this.reservedName_ = new LazyStringArrayList(this.reservedName_);
                }
                this.bitField0_ |= 512;
            }

            @Override // com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder
            public int getReservedNameCount() {
                return this.reservedName_.size();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static DescriptorProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<DescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public DescriptorProto getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class ExtensionRangeOptions extends GeneratedMessageV3.ExtendableMessage<ExtensionRangeOptions> implements ExtensionRangeOptionsOrBuilder {
        private static final ExtensionRangeOptions DEFAULT_INSTANCE = new ExtensionRangeOptions();

        @Deprecated
        public static final Parser<ExtensionRangeOptions> PARSER = new AbstractParser<ExtensionRangeOptions>() { // from class: com.google.protobuf.DescriptorProtos.ExtensionRangeOptions.1
            @Override // com.google.protobuf.Parser
            public ExtensionRangeOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = ExtensionRangeOptions.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private byte memoizedIsInitialized;
        private List<UninterpretedOption> uninterpretedOption_;

        private ExtensionRangeOptions(GeneratedMessageV3.ExtendableBuilder<ExtensionRangeOptions, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ExtensionRangeOptions() {
            this.memoizedIsInitialized = (byte) -1;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new ExtensionRangeOptions();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_ExtensionRangeOptions_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_ExtensionRangeOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(ExtensionRangeOptions.class, Builder.class);
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override // com.google.protobuf.DescriptorProtos.ExtensionRangeOptionsOrBuilder
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.ExtendableMessage, com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                if (!getUninterpretedOption(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            GeneratedMessageV3.ExtendableMessage<MessageT>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriterNewExtensionWriter.writeUntil(536870912, output);
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = 0;
            for (int i2 = 0; i2 < this.uninterpretedOption_.size(); i2++) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i2));
            }
            int iExtensionsSerializedSize = iComputeMessageSize + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSize = iExtensionsSerializedSize;
            return iExtensionsSerializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ExtensionRangeOptions)) {
                return super.equals(obj);
            }
            ExtensionRangeOptions extensionRangeOptions = (ExtensionRangeOptions) obj;
            return getUninterpretedOptionList().equals(extensionRangeOptions.getUninterpretedOptionList()) && getUnknownFields().equals(extensionRangeOptions.getUnknownFields()) && getExtensionFields().equals(extensionRangeOptions.getExtensionFields());
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (getUninterpretedOptionCount() > 0) {
                iHashCode = (((iHashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
            }
            int iHashFields = (AbstractMessage.hashFields(iHashCode, getExtensionFields()) * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashFields;
            return iHashFields;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.ExtendableBuilder<ExtensionRangeOptions, Builder> implements ExtensionRangeOptionsOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
            private List<UninterpretedOption> uninterpretedOption_;

            private void buildPartial0(ExtensionRangeOptions result) {
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_ExtensionRangeOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(ExtensionRangeOptions.class, Builder.class);
            }

            private Builder() {
                this.uninterpretedOption_ = Collections.emptyList();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.uninterpretedOption_ = Collections.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_ExtensionRangeOptions_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ExtensionRangeOptions getDefaultInstanceForType() {
                return ExtensionRangeOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ExtensionRangeOptions build() {
                ExtensionRangeOptions extensionRangeOptionsBuildPartial = buildPartial();
                if (extensionRangeOptionsBuildPartial.isInitialized()) {
                    return extensionRangeOptionsBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) extensionRangeOptionsBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ExtensionRangeOptions buildPartial() {
                ExtensionRangeOptions extensionRangeOptions = new ExtensionRangeOptions(this);
                buildPartialRepeatedFields(extensionRangeOptions);
                if (this.bitField0_ != 0) {
                    buildPartial0(extensionRangeOptions);
                }
                onBuilt();
                return extensionRangeOptions;
            }

            private void buildPartialRepeatedFields(ExtensionRangeOptions result) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.uninterpretedOption_ = repeatedFieldBuilderV3.build();
                    return;
                }
                if ((this.bitField0_ & 1) != 0) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    this.bitField0_ &= -2;
                }
                result.uninterpretedOption_ = this.uninterpretedOption_;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof ExtensionRangeOptions) {
                    return mergeFrom((ExtensionRangeOptions) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ExtensionRangeOptions other) {
                if (other == ExtensionRangeOptions.getDefaultInstance()) {
                    return this;
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (!this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    } else {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= -2;
                        this.uninterpretedOptionBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
                    }
                }
                mergeExtensionFields(other);
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.ExtendableBuilder, com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag != 0) {
                                if (tag == 7994) {
                                    UninterpretedOption uninterpretedOption = (UninterpretedOption) input.readMessage(UninterpretedOption.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureUninterpretedOptionIsMutable();
                                        this.uninterpretedOption_.add(uninterpretedOption);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(uninterpretedOption);
                                    }
                                } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.ExtensionRangeOptionsOrBuilder
            public int getUninterpretedOptionCount() {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public UninterpretedOption getUninterpretedOption(int index) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return (UninterpretedOption) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3<>(this.uninterpretedOption_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static ExtensionRangeOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<ExtensionRangeOptions> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ExtensionRangeOptions getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class FieldDescriptorProto extends GeneratedMessageV3 implements FieldDescriptorProtoOrBuilder {
        private static final FieldDescriptorProto DEFAULT_INSTANCE = new FieldDescriptorProto();

        @Deprecated
        public static final Parser<FieldDescriptorProto> PARSER = new AbstractParser<FieldDescriptorProto>() { // from class: com.google.protobuf.DescriptorProtos.FieldDescriptorProto.1
            @Override // com.google.protobuf.Parser
            public FieldDescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = FieldDescriptorProto.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private int bitField0_;
        private volatile Object defaultValue_;
        private volatile Object extendee_;
        private volatile Object jsonName_;
        private int label_;
        private byte memoizedIsInitialized;
        private volatile Object name_;
        private int number_;
        private int oneofIndex_;
        private FieldOptions options_;
        private boolean proto3Optional_;
        private volatile Object typeName_;
        private int type_;

        static /* synthetic */ int access$8076(FieldDescriptorProto fieldDescriptorProto, int i) {
            int i2 = i | fieldDescriptorProto.bitField0_;
            fieldDescriptorProto.bitField0_ = i2;
            return i2;
        }

        private FieldDescriptorProto(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.name_ = "";
            this.number_ = 0;
            this.label_ = 1;
            this.type_ = 1;
            this.typeName_ = "";
            this.extendee_ = "";
            this.defaultValue_ = "";
            this.oneofIndex_ = 0;
            this.jsonName_ = "";
            this.proto3Optional_ = false;
            this.memoizedIsInitialized = (byte) -1;
        }

        private FieldDescriptorProto() {
            this.name_ = "";
            this.number_ = 0;
            this.label_ = 1;
            this.type_ = 1;
            this.typeName_ = "";
            this.extendee_ = "";
            this.defaultValue_ = "";
            this.oneofIndex_ = 0;
            this.jsonName_ = "";
            this.proto3Optional_ = false;
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
            this.label_ = 1;
            this.type_ = 1;
            this.typeName_ = "";
            this.extendee_ = "";
            this.defaultValue_ = "";
            this.jsonName_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new FieldDescriptorProto();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldDescriptorProto.class, Builder.class);
        }

        public enum Type implements ProtocolMessageEnum {
            TYPE_DOUBLE(1),
            TYPE_FLOAT(2),
            TYPE_INT64(3),
            TYPE_UINT64(4),
            TYPE_INT32(5),
            TYPE_FIXED64(6),
            TYPE_FIXED32(7),
            TYPE_BOOL(8),
            TYPE_STRING(9),
            TYPE_GROUP(10),
            TYPE_MESSAGE(11),
            TYPE_BYTES(12),
            TYPE_UINT32(13),
            TYPE_ENUM(14),
            TYPE_SFIXED32(15),
            TYPE_SFIXED64(16),
            TYPE_SINT32(17),
            TYPE_SINT64(18);

            public static final int TYPE_BOOL_VALUE = 8;
            public static final int TYPE_BYTES_VALUE = 12;
            public static final int TYPE_DOUBLE_VALUE = 1;
            public static final int TYPE_ENUM_VALUE = 14;
            public static final int TYPE_FIXED32_VALUE = 7;
            public static final int TYPE_FIXED64_VALUE = 6;
            public static final int TYPE_FLOAT_VALUE = 2;
            public static final int TYPE_GROUP_VALUE = 10;
            public static final int TYPE_INT32_VALUE = 5;
            public static final int TYPE_INT64_VALUE = 3;
            public static final int TYPE_MESSAGE_VALUE = 11;
            public static final int TYPE_SFIXED32_VALUE = 15;
            public static final int TYPE_SFIXED64_VALUE = 16;
            public static final int TYPE_SINT32_VALUE = 17;
            public static final int TYPE_SINT64_VALUE = 18;
            public static final int TYPE_STRING_VALUE = 9;
            public static final int TYPE_UINT32_VALUE = 13;
            public static final int TYPE_UINT64_VALUE = 4;
            private final int value;
            private static final Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() { // from class: com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Type findValueByNumber(int number) {
                    return Type.forNumber(number);
                }
            };
            private static final Type[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static Type valueOf(int value) {
                return forNumber(value);
            }

            public static Type forNumber(int value) {
                switch (value) {
                    case 1:
                        return TYPE_DOUBLE;
                    case 2:
                        return TYPE_FLOAT;
                    case 3:
                        return TYPE_INT64;
                    case 4:
                        return TYPE_UINT64;
                    case 5:
                        return TYPE_INT32;
                    case 6:
                        return TYPE_FIXED64;
                    case 7:
                        return TYPE_FIXED32;
                    case 8:
                        return TYPE_BOOL;
                    case 9:
                        return TYPE_STRING;
                    case 10:
                        return TYPE_GROUP;
                    case 11:
                        return TYPE_MESSAGE;
                    case 12:
                        return TYPE_BYTES;
                    case 13:
                        return TYPE_UINT32;
                    case 14:
                        return TYPE_ENUM;
                    case 15:
                        return TYPE_SFIXED32;
                    case 16:
                        return TYPE_SFIXED64;
                    case 17:
                        return TYPE_SINT32;
                    case 18:
                        return TYPE_SINT64;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<Type> internalGetValueMap() {
                return internalValueMap;
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FieldDescriptorProto.getDescriptor().getEnumTypes().get(0);
            }

            public static Type valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            Type(int value) {
                this.value = value;
            }
        }

        public enum Label implements ProtocolMessageEnum {
            LABEL_OPTIONAL(1),
            LABEL_REQUIRED(2),
            LABEL_REPEATED(3);

            public static final int LABEL_OPTIONAL_VALUE = 1;
            public static final int LABEL_REPEATED_VALUE = 3;
            public static final int LABEL_REQUIRED_VALUE = 2;
            private final int value;
            private static final Internal.EnumLiteMap<Label> internalValueMap = new Internal.EnumLiteMap<Label>() { // from class: com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Label.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Label findValueByNumber(int number) {
                    return Label.forNumber(number);
                }
            };
            private static final Label[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static Label valueOf(int value) {
                return forNumber(value);
            }

            public static Label forNumber(int value) {
                if (value == 1) {
                    return LABEL_OPTIONAL;
                }
                if (value == 2) {
                    return LABEL_REQUIRED;
                }
                if (value != 3) {
                    return null;
                }
                return LABEL_REPEATED;
            }

            public static Internal.EnumLiteMap<Label> internalGetValueMap() {
                return internalValueMap;
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FieldDescriptorProto.getDescriptor().getEnumTypes().get(1);
            }

            public static Label valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            Label(int value) {
                this.value = value;
            }
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasNumber() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.protobuf.DescriptorProtos.FieldDescriptorProtoOrBuilder
        public int getNumber() {
            return this.number_;
        }

        public boolean hasLabel() {
            return (this.bitField0_ & 4) != 0;
        }

        public Label getLabel() {
            Label labelForNumber = Label.forNumber(this.label_);
            return labelForNumber == null ? Label.LABEL_OPTIONAL : labelForNumber;
        }

        public boolean hasType() {
            return (this.bitField0_ & 8) != 0;
        }

        public Type getType() {
            Type typeForNumber = Type.forNumber(this.type_);
            return typeForNumber == null ? Type.TYPE_DOUBLE : typeForNumber;
        }

        public boolean hasTypeName() {
            return (this.bitField0_ & 16) != 0;
        }

        public String getTypeName() {
            Object obj = this.typeName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.typeName_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasExtendee() {
            return (this.bitField0_ & 32) != 0;
        }

        public String getExtendee() {
            Object obj = this.extendee_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.extendee_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasDefaultValue() {
            return (this.bitField0_ & 64) != 0;
        }

        public String getDefaultValue() {
            Object obj = this.defaultValue_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.defaultValue_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasOneofIndex() {
            return (this.bitField0_ & 128) != 0;
        }

        @Override // com.google.protobuf.DescriptorProtos.FieldDescriptorProtoOrBuilder
        public int getOneofIndex() {
            return this.oneofIndex_;
        }

        public boolean hasJsonName() {
            return (this.bitField0_ & 256) != 0;
        }

        public String getJsonName() {
            Object obj = this.jsonName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.jsonName_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 512) != 0;
        }

        public FieldOptions getOptions() {
            FieldOptions fieldOptions = this.options_;
            return fieldOptions == null ? FieldOptions.getDefaultInstance() : fieldOptions;
        }

        public boolean hasProto3Optional() {
            return (this.bitField0_ & 1024) != 0;
        }

        public boolean getProto3Optional() {
            return this.proto3Optional_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (hasOptions() && !getOptions().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString(output, 1, this.name_);
            }
            if ((this.bitField0_ & 32) != 0) {
                GeneratedMessageV3.writeString(output, 2, this.extendee_);
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeInt32(3, this.number_);
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeEnum(4, this.label_);
            }
            if ((this.bitField0_ & 8) != 0) {
                output.writeEnum(5, this.type_);
            }
            if ((this.bitField0_ & 16) != 0) {
                GeneratedMessageV3.writeString(output, 6, this.typeName_);
            }
            if ((this.bitField0_ & 64) != 0) {
                GeneratedMessageV3.writeString(output, 7, this.defaultValue_);
            }
            if ((this.bitField0_ & 512) != 0) {
                output.writeMessage(8, getOptions());
            }
            if ((this.bitField0_ & 128) != 0) {
                output.writeInt32(9, this.oneofIndex_);
            }
            if ((this.bitField0_ & 256) != 0) {
                GeneratedMessageV3.writeString(output, 10, this.jsonName_);
            }
            if ((this.bitField0_ & 1024) != 0) {
                output.writeBool(17, this.proto3Optional_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (this.bitField0_ & 1) != 0 ? 0 + GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
            if ((this.bitField0_ & 32) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.extendee_);
            }
            if ((this.bitField0_ & 2) != 0) {
                iComputeStringSize += CodedOutputStream.computeInt32Size(3, this.number_);
            }
            if ((this.bitField0_ & 4) != 0) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(4, this.label_);
            }
            if ((this.bitField0_ & 8) != 0) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(5, this.type_);
            }
            if ((this.bitField0_ & 16) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(6, this.typeName_);
            }
            if ((this.bitField0_ & 64) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(7, this.defaultValue_);
            }
            if ((this.bitField0_ & 512) != 0) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(8, getOptions());
            }
            if ((this.bitField0_ & 128) != 0) {
                iComputeStringSize += CodedOutputStream.computeInt32Size(9, this.oneofIndex_);
            }
            if ((this.bitField0_ & 256) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(10, this.jsonName_);
            }
            if ((this.bitField0_ & 1024) != 0) {
                iComputeStringSize += CodedOutputStream.computeBoolSize(17, this.proto3Optional_);
            }
            int serializedSize = iComputeStringSize + getUnknownFields().getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FieldDescriptorProto)) {
                return super.equals(obj);
            }
            FieldDescriptorProto fieldDescriptorProto = (FieldDescriptorProto) obj;
            if (hasName() != fieldDescriptorProto.hasName()) {
                return false;
            }
            if ((hasName() && !getName().equals(fieldDescriptorProto.getName())) || hasNumber() != fieldDescriptorProto.hasNumber()) {
                return false;
            }
            if ((hasNumber() && getNumber() != fieldDescriptorProto.getNumber()) || hasLabel() != fieldDescriptorProto.hasLabel()) {
                return false;
            }
            if ((hasLabel() && this.label_ != fieldDescriptorProto.label_) || hasType() != fieldDescriptorProto.hasType()) {
                return false;
            }
            if ((hasType() && this.type_ != fieldDescriptorProto.type_) || hasTypeName() != fieldDescriptorProto.hasTypeName()) {
                return false;
            }
            if ((hasTypeName() && !getTypeName().equals(fieldDescriptorProto.getTypeName())) || hasExtendee() != fieldDescriptorProto.hasExtendee()) {
                return false;
            }
            if ((hasExtendee() && !getExtendee().equals(fieldDescriptorProto.getExtendee())) || hasDefaultValue() != fieldDescriptorProto.hasDefaultValue()) {
                return false;
            }
            if ((hasDefaultValue() && !getDefaultValue().equals(fieldDescriptorProto.getDefaultValue())) || hasOneofIndex() != fieldDescriptorProto.hasOneofIndex()) {
                return false;
            }
            if ((hasOneofIndex() && getOneofIndex() != fieldDescriptorProto.getOneofIndex()) || hasJsonName() != fieldDescriptorProto.hasJsonName()) {
                return false;
            }
            if ((hasJsonName() && !getJsonName().equals(fieldDescriptorProto.getJsonName())) || hasOptions() != fieldDescriptorProto.hasOptions()) {
                return false;
            }
            if ((!hasOptions() || getOptions().equals(fieldDescriptorProto.getOptions())) && hasProto3Optional() == fieldDescriptorProto.hasProto3Optional()) {
                return (!hasProto3Optional() || getProto3Optional() == fieldDescriptorProto.getProto3Optional()) && getUnknownFields().equals(fieldDescriptorProto.getUnknownFields());
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasName()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getName().hashCode();
            }
            if (hasNumber()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getNumber();
            }
            if (hasLabel()) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + this.label_;
            }
            if (hasType()) {
                iHashCode = (((iHashCode * 37) + 5) * 53) + this.type_;
            }
            if (hasTypeName()) {
                iHashCode = (((iHashCode * 37) + 6) * 53) + getTypeName().hashCode();
            }
            if (hasExtendee()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getExtendee().hashCode();
            }
            if (hasDefaultValue()) {
                iHashCode = (((iHashCode * 37) + 7) * 53) + getDefaultValue().hashCode();
            }
            if (hasOneofIndex()) {
                iHashCode = (((iHashCode * 37) + 9) * 53) + getOneofIndex();
            }
            if (hasJsonName()) {
                iHashCode = (((iHashCode * 37) + 10) * 53) + getJsonName().hashCode();
            }
            if (hasOptions()) {
                iHashCode = (((iHashCode * 37) + 8) * 53) + getOptions().hashCode();
            }
            if (hasProto3Optional()) {
                iHashCode = (((iHashCode * 37) + 17) * 53) + Internal.hashBoolean(getProto3Optional());
            }
            int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FieldDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object defaultValue_;
            private Object extendee_;
            private Object jsonName_;
            private int label_;
            private Object name_;
            private int number_;
            private int oneofIndex_;
            private SingleFieldBuilderV3<FieldOptions, FieldOptions.Builder, FieldOptionsOrBuilder> optionsBuilder_;
            private FieldOptions options_;
            private boolean proto3Optional_;
            private Object typeName_;
            private int type_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldDescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.name_ = "";
                this.label_ = 1;
                this.type_ = 1;
                this.typeName_ = "";
                this.extendee_ = "";
                this.defaultValue_ = "";
                this.jsonName_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.name_ = "";
                this.label_ = 1;
                this.type_ = 1;
                this.typeName_ = "";
                this.extendee_ = "";
                this.defaultValue_ = "";
                this.jsonName_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getOptionsFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public FieldDescriptorProto getDefaultInstanceForType() {
                return FieldDescriptorProto.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FieldDescriptorProto build() {
                FieldDescriptorProto fieldDescriptorProtoBuildPartial = buildPartial();
                if (fieldDescriptorProtoBuildPartial.isInitialized()) {
                    return fieldDescriptorProtoBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) fieldDescriptorProtoBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FieldDescriptorProto buildPartial() {
                FieldDescriptorProto fieldDescriptorProto = new FieldDescriptorProto(this);
                if (this.bitField0_ != 0) {
                    buildPartial0(fieldDescriptorProto);
                }
                onBuilt();
                return fieldDescriptorProto;
            }

            private void buildPartial0(FieldDescriptorProto result) {
                int i;
                FieldOptions fieldOptions;
                int i2 = this.bitField0_;
                if ((i2 & 1) != 0) {
                    result.name_ = this.name_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    result.number_ = this.number_;
                    i |= 2;
                }
                if ((i2 & 4) != 0) {
                    result.label_ = this.label_;
                    i |= 4;
                }
                if ((i2 & 8) != 0) {
                    result.type_ = this.type_;
                    i |= 8;
                }
                if ((i2 & 16) != 0) {
                    result.typeName_ = this.typeName_;
                    i |= 16;
                }
                if ((i2 & 32) != 0) {
                    result.extendee_ = this.extendee_;
                    i |= 32;
                }
                if ((i2 & 64) != 0) {
                    result.defaultValue_ = this.defaultValue_;
                    i |= 64;
                }
                if ((i2 & 128) != 0) {
                    result.oneofIndex_ = this.oneofIndex_;
                    i |= 128;
                }
                if ((i2 & 256) != 0) {
                    result.jsonName_ = this.jsonName_;
                    i |= 256;
                }
                if ((i2 & 512) != 0) {
                    SingleFieldBuilderV3<FieldOptions, FieldOptions.Builder, FieldOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        fieldOptions = this.options_;
                    } else {
                        fieldOptions = (FieldOptions) singleFieldBuilderV3.build();
                    }
                    result.options_ = fieldOptions;
                    i |= 512;
                }
                if ((i2 & 1024) != 0) {
                    result.proto3Optional_ = this.proto3Optional_;
                    i |= 1024;
                }
                FieldDescriptorProto.access$8076(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof FieldDescriptorProto) {
                    return mergeFrom((FieldDescriptorProto) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FieldDescriptorProto other) {
                if (other == FieldDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.name_ = other.name_;
                    this.bitField0_ |= 1;
                    onChanged();
                }
                if (other.hasNumber()) {
                    setNumber(other.getNumber());
                }
                if (other.hasLabel()) {
                    setLabel(other.getLabel());
                }
                if (other.hasType()) {
                    setType(other.getType());
                }
                if (other.hasTypeName()) {
                    this.typeName_ = other.typeName_;
                    this.bitField0_ |= 16;
                    onChanged();
                }
                if (other.hasExtendee()) {
                    this.extendee_ = other.extendee_;
                    this.bitField0_ |= 32;
                    onChanged();
                }
                if (other.hasDefaultValue()) {
                    this.defaultValue_ = other.defaultValue_;
                    this.bitField0_ |= 64;
                    onChanged();
                }
                if (other.hasOneofIndex()) {
                    setOneofIndex(other.getOneofIndex());
                }
                if (other.hasJsonName()) {
                    this.jsonName_ = other.jsonName_;
                    this.bitField0_ |= 256;
                    onChanged();
                }
                if (other.hasOptions()) {
                    mergeOptions(other.getOptions());
                }
                if (other.hasProto3Optional()) {
                    setProto3Optional(other.getProto3Optional());
                }
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return !hasOptions() || getOptions().isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    z = true;
                                    break;
                                case 10:
                                    this.name_ = input.readBytes();
                                    this.bitField0_ |= 1;
                                    break;
                                case 18:
                                    this.extendee_ = input.readBytes();
                                    this.bitField0_ |= 32;
                                    break;
                                case 24:
                                    this.number_ = input.readInt32();
                                    this.bitField0_ |= 2;
                                    break;
                                case 32:
                                    int i = input.readEnum();
                                    if (Label.forNumber(i) == null) {
                                        mergeUnknownVarintField(4, i);
                                    } else {
                                        this.label_ = i;
                                        this.bitField0_ |= 4;
                                    }
                                    break;
                                case 40:
                                    int i2 = input.readEnum();
                                    if (Type.forNumber(i2) == null) {
                                        mergeUnknownVarintField(5, i2);
                                    } else {
                                        this.type_ = i2;
                                        this.bitField0_ |= 8;
                                    }
                                    break;
                                case 50:
                                    this.typeName_ = input.readBytes();
                                    this.bitField0_ |= 16;
                                    break;
                                case 58:
                                    this.defaultValue_ = input.readBytes();
                                    this.bitField0_ |= 64;
                                    break;
                                case 66:
                                    input.readMessage(getOptionsFieldBuilder().getBuilder(), extensionRegistry);
                                    this.bitField0_ |= 512;
                                    break;
                                case 72:
                                    this.oneofIndex_ = input.readInt32();
                                    this.bitField0_ |= 128;
                                    break;
                                case 82:
                                    this.jsonName_ = input.readBytes();
                                    this.bitField0_ |= 256;
                                    break;
                                case 136:
                                    this.proto3Optional_ = input.readBool();
                                    this.bitField0_ |= 1024;
                                    break;
                                default:
                                    if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                        z = true;
                                    }
                                    break;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            @Override // com.google.protobuf.DescriptorProtos.FieldDescriptorProtoOrBuilder
            public int getNumber() {
                return this.number_;
            }

            public Builder setNumber(int value) {
                this.number_ = value;
                this.bitField0_ |= 2;
                onChanged();
                return this;
            }

            public Builder setLabel(Label value) {
                value.getClass();
                this.bitField0_ |= 4;
                this.label_ = value.getNumber();
                onChanged();
                return this;
            }

            public Builder setType(Type value) {
                value.getClass();
                this.bitField0_ |= 8;
                this.type_ = value.getNumber();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.DescriptorProtos.FieldDescriptorProtoOrBuilder
            public int getOneofIndex() {
                return this.oneofIndex_;
            }

            public Builder setOneofIndex(int value) {
                this.oneofIndex_ = value;
                this.bitField0_ |= 128;
                onChanged();
                return this;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 512) != 0;
            }

            public FieldOptions getOptions() {
                SingleFieldBuilderV3<FieldOptions, FieldOptions.Builder, FieldOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    FieldOptions fieldOptions = this.options_;
                    return fieldOptions == null ? FieldOptions.getDefaultInstance() : fieldOptions;
                }
                return (FieldOptions) singleFieldBuilderV3.getMessage();
            }

            public Builder mergeOptions(FieldOptions value) {
                FieldOptions fieldOptions;
                SingleFieldBuilderV3<FieldOptions, FieldOptions.Builder, FieldOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 512) != 0 && (fieldOptions = this.options_) != null && fieldOptions != FieldOptions.getDefaultInstance()) {
                        getOptionsBuilder().mergeFrom(value);
                    } else {
                        this.options_ = value;
                    }
                } else {
                    singleFieldBuilderV3.mergeFrom(value);
                }
                this.bitField0_ |= 512;
                onChanged();
                return this;
            }

            public FieldOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 512;
                onChanged();
                return (FieldOptions.Builder) getOptionsFieldBuilder().getBuilder();
            }

            private SingleFieldBuilderV3<FieldOptions, FieldOptions.Builder, FieldOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilderV3<>(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            public Builder setProto3Optional(boolean value) {
                this.proto3Optional_ = value;
                this.bitField0_ |= 1024;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static FieldDescriptorProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<FieldDescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public FieldDescriptorProto getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class OneofDescriptorProto extends GeneratedMessageV3 implements MessageOrBuilder {
        private static final OneofDescriptorProto DEFAULT_INSTANCE = new OneofDescriptorProto();

        @Deprecated
        public static final Parser<OneofDescriptorProto> PARSER = new AbstractParser<OneofDescriptorProto>() { // from class: com.google.protobuf.DescriptorProtos.OneofDescriptorProto.1
            @Override // com.google.protobuf.Parser
            public OneofDescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = OneofDescriptorProto.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private int bitField0_;
        private byte memoizedIsInitialized;
        private volatile Object name_;
        private OneofOptions options_;

        static /* synthetic */ int access$8876(OneofDescriptorProto oneofDescriptorProto, int i) {
            int i2 = i | oneofDescriptorProto.bitField0_;
            oneofDescriptorProto.bitField0_ = i2;
            return i2;
        }

        private OneofDescriptorProto(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.name_ = "";
            this.memoizedIsInitialized = (byte) -1;
        }

        private OneofDescriptorProto() {
            this.name_ = "";
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new OneofDescriptorProto();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_OneofDescriptorProto_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_OneofDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(OneofDescriptorProto.class, Builder.class);
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 2) != 0;
        }

        public OneofOptions getOptions() {
            OneofOptions oneofOptions = this.options_;
            return oneofOptions == null ? OneofOptions.getDefaultInstance() : oneofOptions;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (hasOptions() && !getOptions().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString(output, 1, this.name_);
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeMessage(2, getOptions());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (this.bitField0_ & 1) != 0 ? 0 + GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
            if ((this.bitField0_ & 2) != 0) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(2, getOptions());
            }
            int serializedSize = iComputeStringSize + getUnknownFields().getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof OneofDescriptorProto)) {
                return super.equals(obj);
            }
            OneofDescriptorProto oneofDescriptorProto = (OneofDescriptorProto) obj;
            if (hasName() != oneofDescriptorProto.hasName()) {
                return false;
            }
            if ((!hasName() || getName().equals(oneofDescriptorProto.getName())) && hasOptions() == oneofDescriptorProto.hasOptions()) {
                return (!hasOptions() || getOptions().equals(oneofDescriptorProto.getOptions())) && getUnknownFields().equals(oneofDescriptorProto.getUnknownFields());
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasName()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getName().hashCode();
            }
            if (hasOptions()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getOptions().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MessageOrBuilder {
            private int bitField0_;
            private Object name_;
            private SingleFieldBuilderV3<OneofOptions, OneofOptions.Builder, OneofOptionsOrBuilder> optionsBuilder_;
            private OneofOptions options_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_OneofDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(OneofDescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getOptionsFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_OneofDescriptorProto_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public OneofDescriptorProto getDefaultInstanceForType() {
                return OneofDescriptorProto.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public OneofDescriptorProto build() {
                OneofDescriptorProto oneofDescriptorProtoBuildPartial = buildPartial();
                if (oneofDescriptorProtoBuildPartial.isInitialized()) {
                    return oneofDescriptorProtoBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) oneofDescriptorProtoBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public OneofDescriptorProto buildPartial() {
                OneofDescriptorProto oneofDescriptorProto = new OneofDescriptorProto(this);
                if (this.bitField0_ != 0) {
                    buildPartial0(oneofDescriptorProto);
                }
                onBuilt();
                return oneofDescriptorProto;
            }

            private void buildPartial0(OneofDescriptorProto result) {
                int i;
                OneofOptions oneofOptions;
                int i2 = this.bitField0_;
                if ((i2 & 1) != 0) {
                    result.name_ = this.name_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    SingleFieldBuilderV3<OneofOptions, OneofOptions.Builder, OneofOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        oneofOptions = this.options_;
                    } else {
                        oneofOptions = (OneofOptions) singleFieldBuilderV3.build();
                    }
                    result.options_ = oneofOptions;
                    i |= 2;
                }
                OneofDescriptorProto.access$8876(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof OneofDescriptorProto) {
                    return mergeFrom((OneofDescriptorProto) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(OneofDescriptorProto other) {
                if (other == OneofDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.name_ = other.name_;
                    this.bitField0_ |= 1;
                    onChanged();
                }
                if (other.hasOptions()) {
                    mergeOptions(other.getOptions());
                }
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return !hasOptions() || getOptions().isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.name_ = input.readBytes();
                                    this.bitField0_ |= 1;
                                } else if (tag == 18) {
                                    input.readMessage(getOptionsFieldBuilder().getBuilder(), extensionRegistry);
                                    this.bitField0_ |= 2;
                                } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 2) != 0;
            }

            public OneofOptions getOptions() {
                SingleFieldBuilderV3<OneofOptions, OneofOptions.Builder, OneofOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    OneofOptions oneofOptions = this.options_;
                    return oneofOptions == null ? OneofOptions.getDefaultInstance() : oneofOptions;
                }
                return (OneofOptions) singleFieldBuilderV3.getMessage();
            }

            public Builder mergeOptions(OneofOptions value) {
                OneofOptions oneofOptions;
                SingleFieldBuilderV3<OneofOptions, OneofOptions.Builder, OneofOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) != 0 && (oneofOptions = this.options_) != null && oneofOptions != OneofOptions.getDefaultInstance()) {
                        getOptionsBuilder().mergeFrom(value);
                    } else {
                        this.options_ = value;
                    }
                } else {
                    singleFieldBuilderV3.mergeFrom(value);
                }
                this.bitField0_ |= 2;
                onChanged();
                return this;
            }

            public OneofOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 2;
                onChanged();
                return (OneofOptions.Builder) getOptionsFieldBuilder().getBuilder();
            }

            private SingleFieldBuilderV3<OneofOptions, OneofOptions.Builder, OneofOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilderV3<>(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static OneofDescriptorProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<OneofDescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public OneofDescriptorProto getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class EnumDescriptorProto extends GeneratedMessageV3 implements EnumDescriptorProtoOrBuilder {
        private static final EnumDescriptorProto DEFAULT_INSTANCE = new EnumDescriptorProto();

        @Deprecated
        public static final Parser<EnumDescriptorProto> PARSER = new AbstractParser<EnumDescriptorProto>() { // from class: com.google.protobuf.DescriptorProtos.EnumDescriptorProto.1
            @Override // com.google.protobuf.Parser
            public EnumDescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = EnumDescriptorProto.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private int bitField0_;
        private byte memoizedIsInitialized;
        private volatile Object name_;
        private EnumOptions options_;
        private LazyStringArrayList reservedName_;
        private List<EnumReservedRange> reservedRange_;
        private List<EnumValueDescriptorProto> value_;

        public interface EnumReservedRangeOrBuilder extends MessageOrBuilder {
            int getEnd();

            int getStart();
        }

        static /* synthetic */ int access$10776(EnumDescriptorProto enumDescriptorProto, int i) {
            int i2 = i | enumDescriptorProto.bitField0_;
            enumDescriptorProto.bitField0_ = i2;
            return i2;
        }

        private EnumDescriptorProto(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.name_ = "";
            this.reservedName_ = LazyStringArrayList.emptyList();
            this.memoizedIsInitialized = (byte) -1;
        }

        private EnumDescriptorProto() {
            this.name_ = "";
            this.reservedName_ = LazyStringArrayList.emptyList();
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
            this.value_ = Collections.emptyList();
            this.reservedRange_ = Collections.emptyList();
            this.reservedName_ = LazyStringArrayList.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new EnumDescriptorProto();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumDescriptorProto.class, Builder.class);
        }

        public static final class EnumReservedRange extends GeneratedMessageV3 implements EnumReservedRangeOrBuilder {
            private static final EnumReservedRange DEFAULT_INSTANCE = new EnumReservedRange();

            @Deprecated
            public static final Parser<EnumReservedRange> PARSER = new AbstractParser<EnumReservedRange>() { // from class: com.google.protobuf.DescriptorProtos.EnumDescriptorProto.EnumReservedRange.1
                @Override // com.google.protobuf.Parser
                public EnumReservedRange parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    Builder builderNewBuilder = EnumReservedRange.newBuilder();
                    try {
                        builderNewBuilder.mergeFrom(input, extensionRegistry);
                        return builderNewBuilder.buildPartial();
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                    } catch (UninitializedMessageException e2) {
                        throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                    }
                }
            };
            private int bitField0_;
            private int end_;
            private byte memoizedIsInitialized;
            private int start_;

            static /* synthetic */ int access$9876(EnumReservedRange enumReservedRange, int i) {
                int i2 = i | enumReservedRange.bitField0_;
                enumReservedRange.bitField0_ = i2;
                return i2;
            }

            private EnumReservedRange(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.start_ = 0;
                this.end_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            private EnumReservedRange() {
                this.start_ = 0;
                this.end_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
                return new EnumReservedRange();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_EnumReservedRange_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_EnumReservedRange_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumReservedRange.class, Builder.class);
            }

            public boolean hasStart() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.google.protobuf.DescriptorProtos.EnumDescriptorProto.EnumReservedRangeOrBuilder
            public int getStart() {
                return this.start_;
            }

            public boolean hasEnd() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.google.protobuf.DescriptorProtos.EnumDescriptorProto.EnumReservedRangeOrBuilder
            public int getEnd() {
                return this.end_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    output.writeInt32(1, this.start_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    output.writeInt32(2, this.end_);
                }
                getUnknownFields().writeTo(output);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeInt32Size = (this.bitField0_ & 1) != 0 ? 0 + CodedOutputStream.computeInt32Size(1, this.start_) : 0;
                if ((this.bitField0_ & 2) != 0) {
                    iComputeInt32Size += CodedOutputStream.computeInt32Size(2, this.end_);
                }
                int serializedSize = iComputeInt32Size + getUnknownFields().getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.AbstractMessage
            public boolean equals(final Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof EnumReservedRange)) {
                    return super.equals(obj);
                }
                EnumReservedRange enumReservedRange = (EnumReservedRange) obj;
                if (hasStart() != enumReservedRange.hasStart()) {
                    return false;
                }
                if ((!hasStart() || getStart() == enumReservedRange.getStart()) && hasEnd() == enumReservedRange.hasEnd()) {
                    return (!hasEnd() || getEnd() == enumReservedRange.getEnd()) && getUnknownFields().equals(enumReservedRange.getUnknownFields());
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int iHashCode = 779 + getDescriptor().hashCode();
                if (hasStart()) {
                    iHashCode = (((iHashCode * 37) + 1) * 53) + getStart();
                }
                if (hasEnd()) {
                    iHashCode = (((iHashCode * 37) + 2) * 53) + getEnd();
                }
                int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
                return new Builder(parent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EnumReservedRangeOrBuilder {
                private int bitField0_;
                private int end_;
                private int start_;

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_EnumReservedRange_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumReservedRange.class, Builder.class);
                }

                private Builder() {
                }

                private Builder(GeneratedMessageV3.BuilderParent parent) {
                    super(parent);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_EnumReservedRange_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public EnumReservedRange getDefaultInstanceForType() {
                    return EnumReservedRange.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public EnumReservedRange build() {
                    EnumReservedRange enumReservedRangeBuildPartial = buildPartial();
                    if (enumReservedRangeBuildPartial.isInitialized()) {
                        return enumReservedRangeBuildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) enumReservedRangeBuildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public EnumReservedRange buildPartial() {
                    EnumReservedRange enumReservedRange = new EnumReservedRange(this);
                    if (this.bitField0_ != 0) {
                        buildPartial0(enumReservedRange);
                    }
                    onBuilt();
                    return enumReservedRange;
                }

                private void buildPartial0(EnumReservedRange result) {
                    int i;
                    int i2 = this.bitField0_;
                    if ((i2 & 1) != 0) {
                        result.start_ = this.start_;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 2) != 0) {
                        result.end_ = this.end_;
                        i |= 2;
                    }
                    EnumReservedRange.access$9876(result, i);
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Builder mergeFrom(Message other) {
                    if (other instanceof EnumReservedRange) {
                        return mergeFrom((EnumReservedRange) other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(EnumReservedRange other) {
                    if (other == EnumReservedRange.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasStart()) {
                        setStart(other.getStart());
                    }
                    if (other.hasEnd()) {
                        setEnd(other.getEnd());
                    }
                    mergeUnknownFields(other.getUnknownFields());
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    extensionRegistry.getClass();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                int tag = input.readTag();
                                if (tag != 0) {
                                    if (tag == 8) {
                                        this.start_ = input.readInt32();
                                        this.bitField0_ |= 1;
                                    } else if (tag == 16) {
                                        this.end_ = input.readInt32();
                                        this.bitField0_ |= 2;
                                    } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                    }
                                }
                                z = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw e.unwrapIOException();
                            }
                        } finally {
                            onChanged();
                        }
                    }
                    return this;
                }

                @Override // com.google.protobuf.DescriptorProtos.EnumDescriptorProto.EnumReservedRangeOrBuilder
                public int getStart() {
                    return this.start_;
                }

                public Builder setStart(int value) {
                    this.start_ = value;
                    this.bitField0_ |= 1;
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.DescriptorProtos.EnumDescriptorProto.EnumReservedRangeOrBuilder
                public int getEnd() {
                    return this.end_;
                }

                public Builder setEnd(int value) {
                    this.end_ = value;
                    this.bitField0_ |= 2;
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                    return (Builder) super.setUnknownFields(unknownFields);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
                public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                    return (Builder) super.mergeUnknownFields(unknownFields);
                }
            }

            public static EnumReservedRange getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
            public Parser<EnumReservedRange> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public EnumReservedRange getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = stringUtf8;
            }
            return stringUtf8;
        }

        public List<EnumValueDescriptorProto> getValueList() {
            return this.value_;
        }

        @Override // com.google.protobuf.DescriptorProtos.EnumDescriptorProtoOrBuilder
        public int getValueCount() {
            return this.value_.size();
        }

        public EnumValueDescriptorProto getValue(int index) {
            return this.value_.get(index);
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 2) != 0;
        }

        public EnumOptions getOptions() {
            EnumOptions enumOptions = this.options_;
            return enumOptions == null ? EnumOptions.getDefaultInstance() : enumOptions;
        }

        public List<EnumReservedRange> getReservedRangeList() {
            return this.reservedRange_;
        }

        @Override // com.google.protobuf.DescriptorProtos.EnumDescriptorProtoOrBuilder
        public int getReservedRangeCount() {
            return this.reservedRange_.size();
        }

        public ProtocolStringList getReservedNameList() {
            return this.reservedName_;
        }

        @Override // com.google.protobuf.DescriptorProtos.EnumDescriptorProtoOrBuilder
        public int getReservedNameCount() {
            return this.reservedName_.size();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getValueCount(); i++) {
                if (!getValue(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (hasOptions() && !getOptions().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString(output, 1, this.name_);
            }
            for (int i = 0; i < this.value_.size(); i++) {
                output.writeMessage(2, this.value_.get(i));
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeMessage(3, getOptions());
            }
            for (int i2 = 0; i2 < this.reservedRange_.size(); i2++) {
                output.writeMessage(4, this.reservedRange_.get(i2));
            }
            for (int i3 = 0; i3 < this.reservedName_.size(); i3++) {
                GeneratedMessageV3.writeString(output, 5, this.reservedName_.getRaw(i3));
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (this.bitField0_ & 1) != 0 ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
            for (int i2 = 0; i2 < this.value_.size(); i2++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(2, this.value_.get(i2));
            }
            if ((this.bitField0_ & 2) != 0) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(3, getOptions());
            }
            for (int i3 = 0; i3 < this.reservedRange_.size(); i3++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(4, this.reservedRange_.get(i3));
            }
            int iComputeStringSizeNoTag = 0;
            for (int i4 = 0; i4 < this.reservedName_.size(); i4++) {
                iComputeStringSizeNoTag += GeneratedMessageV3.computeStringSizeNoTag(this.reservedName_.getRaw(i4));
            }
            int size = iComputeStringSize + iComputeStringSizeNoTag + (getReservedNameList().size() * 1) + getUnknownFields().getSerializedSize();
            this.memoizedSize = size;
            return size;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EnumDescriptorProto)) {
                return super.equals(obj);
            }
            EnumDescriptorProto enumDescriptorProto = (EnumDescriptorProto) obj;
            if (hasName() != enumDescriptorProto.hasName()) {
                return false;
            }
            if ((!hasName() || getName().equals(enumDescriptorProto.getName())) && getValueList().equals(enumDescriptorProto.getValueList()) && hasOptions() == enumDescriptorProto.hasOptions()) {
                return (!hasOptions() || getOptions().equals(enumDescriptorProto.getOptions())) && getReservedRangeList().equals(enumDescriptorProto.getReservedRangeList()) && getReservedNameList().equals(enumDescriptorProto.getReservedNameList()) && getUnknownFields().equals(enumDescriptorProto.getUnknownFields());
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasName()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getName().hashCode();
            }
            if (getValueCount() > 0) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getValueList().hashCode();
            }
            if (hasOptions()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getOptions().hashCode();
            }
            if (getReservedRangeCount() > 0) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + getReservedRangeList().hashCode();
            }
            if (getReservedNameCount() > 0) {
                iHashCode = (((iHashCode * 37) + 5) * 53) + getReservedNameList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EnumDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object name_;
            private SingleFieldBuilderV3<EnumOptions, EnumOptions.Builder, EnumOptionsOrBuilder> optionsBuilder_;
            private EnumOptions options_;
            private LazyStringArrayList reservedName_;
            private RepeatedFieldBuilderV3<EnumReservedRange, EnumReservedRange.Builder, EnumReservedRangeOrBuilder> reservedRangeBuilder_;
            private List<EnumReservedRange> reservedRange_;
            private RepeatedFieldBuilderV3<EnumValueDescriptorProto, EnumValueDescriptorProto.Builder, EnumValueDescriptorProtoOrBuilder> valueBuilder_;
            private List<EnumValueDescriptorProto> value_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumDescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.name_ = "";
                this.value_ = Collections.emptyList();
                this.reservedRange_ = Collections.emptyList();
                this.reservedName_ = LazyStringArrayList.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.name_ = "";
                this.value_ = Collections.emptyList();
                this.reservedRange_ = Collections.emptyList();
                this.reservedName_ = LazyStringArrayList.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getValueFieldBuilder();
                    getOptionsFieldBuilder();
                    getReservedRangeFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public EnumDescriptorProto getDefaultInstanceForType() {
                return EnumDescriptorProto.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumDescriptorProto build() {
                EnumDescriptorProto enumDescriptorProtoBuildPartial = buildPartial();
                if (enumDescriptorProtoBuildPartial.isInitialized()) {
                    return enumDescriptorProtoBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) enumDescriptorProtoBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumDescriptorProto buildPartial() {
                EnumDescriptorProto enumDescriptorProto = new EnumDescriptorProto(this);
                buildPartialRepeatedFields(enumDescriptorProto);
                if (this.bitField0_ != 0) {
                    buildPartial0(enumDescriptorProto);
                }
                onBuilt();
                return enumDescriptorProto;
            }

            private void buildPartialRepeatedFields(EnumDescriptorProto result) {
                RepeatedFieldBuilderV3<EnumValueDescriptorProto, EnumValueDescriptorProto.Builder, EnumValueDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.valueBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.value_ = repeatedFieldBuilderV3.build();
                } else {
                    if ((this.bitField0_ & 2) != 0) {
                        this.value_ = Collections.unmodifiableList(this.value_);
                        this.bitField0_ &= -3;
                    }
                    result.value_ = this.value_;
                }
                RepeatedFieldBuilderV3<EnumReservedRange, EnumReservedRange.Builder, EnumReservedRangeOrBuilder> repeatedFieldBuilderV32 = this.reservedRangeBuilder_;
                if (repeatedFieldBuilderV32 != null) {
                    result.reservedRange_ = repeatedFieldBuilderV32.build();
                    return;
                }
                if ((this.bitField0_ & 8) != 0) {
                    this.reservedRange_ = Collections.unmodifiableList(this.reservedRange_);
                    this.bitField0_ &= -9;
                }
                result.reservedRange_ = this.reservedRange_;
            }

            private void buildPartial0(EnumDescriptorProto result) {
                int i;
                EnumOptions enumOptions;
                int i2 = this.bitField0_;
                if ((i2 & 1) != 0) {
                    result.name_ = this.name_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 4) != 0) {
                    SingleFieldBuilderV3<EnumOptions, EnumOptions.Builder, EnumOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        enumOptions = this.options_;
                    } else {
                        enumOptions = (EnumOptions) singleFieldBuilderV3.build();
                    }
                    result.options_ = enumOptions;
                    i |= 2;
                }
                if ((i2 & 16) != 0) {
                    this.reservedName_.makeImmutable();
                    result.reservedName_ = this.reservedName_;
                }
                EnumDescriptorProto.access$10776(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof EnumDescriptorProto) {
                    return mergeFrom((EnumDescriptorProto) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(EnumDescriptorProto other) {
                if (other == EnumDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.name_ = other.name_;
                    this.bitField0_ |= 1;
                    onChanged();
                }
                if (this.valueBuilder_ == null) {
                    if (!other.value_.isEmpty()) {
                        if (this.value_.isEmpty()) {
                            this.value_ = other.value_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureValueIsMutable();
                            this.value_.addAll(other.value_);
                        }
                        onChanged();
                    }
                } else if (!other.value_.isEmpty()) {
                    if (!this.valueBuilder_.isEmpty()) {
                        this.valueBuilder_.addAllMessages(other.value_);
                    } else {
                        this.valueBuilder_.dispose();
                        this.valueBuilder_ = null;
                        this.value_ = other.value_;
                        this.bitField0_ &= -3;
                        this.valueBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getValueFieldBuilder() : null;
                    }
                }
                if (other.hasOptions()) {
                    mergeOptions(other.getOptions());
                }
                if (this.reservedRangeBuilder_ == null) {
                    if (!other.reservedRange_.isEmpty()) {
                        if (this.reservedRange_.isEmpty()) {
                            this.reservedRange_ = other.reservedRange_;
                            this.bitField0_ &= -9;
                        } else {
                            ensureReservedRangeIsMutable();
                            this.reservedRange_.addAll(other.reservedRange_);
                        }
                        onChanged();
                    }
                } else if (!other.reservedRange_.isEmpty()) {
                    if (!this.reservedRangeBuilder_.isEmpty()) {
                        this.reservedRangeBuilder_.addAllMessages(other.reservedRange_);
                    } else {
                        this.reservedRangeBuilder_.dispose();
                        this.reservedRangeBuilder_ = null;
                        this.reservedRange_ = other.reservedRange_;
                        this.bitField0_ &= -9;
                        this.reservedRangeBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getReservedRangeFieldBuilder() : null;
                    }
                }
                if (!other.reservedName_.isEmpty()) {
                    if (this.reservedName_.isEmpty()) {
                        this.reservedName_ = other.reservedName_;
                        this.bitField0_ |= 16;
                    } else {
                        ensureReservedNameIsMutable();
                        this.reservedName_.addAll(other.reservedName_);
                    }
                    onChanged();
                }
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getValueCount(); i++) {
                    if (!getValue(i).isInitialized()) {
                        return false;
                    }
                }
                return !hasOptions() || getOptions().isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.name_ = input.readBytes();
                                    this.bitField0_ |= 1;
                                } else if (tag == 18) {
                                    EnumValueDescriptorProto enumValueDescriptorProto = (EnumValueDescriptorProto) input.readMessage(EnumValueDescriptorProto.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<EnumValueDescriptorProto, EnumValueDescriptorProto.Builder, EnumValueDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.valueBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureValueIsMutable();
                                        this.value_.add(enumValueDescriptorProto);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(enumValueDescriptorProto);
                                    }
                                } else if (tag == 26) {
                                    input.readMessage(getOptionsFieldBuilder().getBuilder(), extensionRegistry);
                                    this.bitField0_ |= 4;
                                } else if (tag == 34) {
                                    EnumReservedRange enumReservedRange = (EnumReservedRange) input.readMessage(EnumReservedRange.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<EnumReservedRange, EnumReservedRange.Builder, EnumReservedRangeOrBuilder> repeatedFieldBuilderV32 = this.reservedRangeBuilder_;
                                    if (repeatedFieldBuilderV32 == null) {
                                        ensureReservedRangeIsMutable();
                                        this.reservedRange_.add(enumReservedRange);
                                    } else {
                                        repeatedFieldBuilderV32.addMessage(enumReservedRange);
                                    }
                                } else if (tag == 42) {
                                    ByteString bytes = input.readBytes();
                                    ensureReservedNameIsMutable();
                                    this.reservedName_.add(bytes);
                                } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            private void ensureValueIsMutable() {
                if ((this.bitField0_ & 2) == 0) {
                    this.value_ = new ArrayList(this.value_);
                    this.bitField0_ |= 2;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.EnumDescriptorProtoOrBuilder
            public int getValueCount() {
                RepeatedFieldBuilderV3<EnumValueDescriptorProto, EnumValueDescriptorProto.Builder, EnumValueDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.valueBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.value_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public EnumValueDescriptorProto getValue(int index) {
                RepeatedFieldBuilderV3<EnumValueDescriptorProto, EnumValueDescriptorProto.Builder, EnumValueDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.valueBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.value_.get(index);
                }
                return (EnumValueDescriptorProto) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<EnumValueDescriptorProto, EnumValueDescriptorProto.Builder, EnumValueDescriptorProtoOrBuilder> getValueFieldBuilder() {
                if (this.valueBuilder_ == null) {
                    this.valueBuilder_ = new RepeatedFieldBuilderV3<>(this.value_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                    this.value_ = null;
                }
                return this.valueBuilder_;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 4) != 0;
            }

            public EnumOptions getOptions() {
                SingleFieldBuilderV3<EnumOptions, EnumOptions.Builder, EnumOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    EnumOptions enumOptions = this.options_;
                    return enumOptions == null ? EnumOptions.getDefaultInstance() : enumOptions;
                }
                return (EnumOptions) singleFieldBuilderV3.getMessage();
            }

            public Builder mergeOptions(EnumOptions value) {
                EnumOptions enumOptions;
                SingleFieldBuilderV3<EnumOptions, EnumOptions.Builder, EnumOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 4) != 0 && (enumOptions = this.options_) != null && enumOptions != EnumOptions.getDefaultInstance()) {
                        getOptionsBuilder().mergeFrom(value);
                    } else {
                        this.options_ = value;
                    }
                } else {
                    singleFieldBuilderV3.mergeFrom(value);
                }
                this.bitField0_ |= 4;
                onChanged();
                return this;
            }

            public EnumOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 4;
                onChanged();
                return (EnumOptions.Builder) getOptionsFieldBuilder().getBuilder();
            }

            private SingleFieldBuilderV3<EnumOptions, EnumOptions.Builder, EnumOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilderV3<>(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            private void ensureReservedRangeIsMutable() {
                if ((this.bitField0_ & 8) == 0) {
                    this.reservedRange_ = new ArrayList(this.reservedRange_);
                    this.bitField0_ |= 8;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.EnumDescriptorProtoOrBuilder
            public int getReservedRangeCount() {
                RepeatedFieldBuilderV3<EnumReservedRange, EnumReservedRange.Builder, EnumReservedRangeOrBuilder> repeatedFieldBuilderV3 = this.reservedRangeBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.reservedRange_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            private RepeatedFieldBuilderV3<EnumReservedRange, EnumReservedRange.Builder, EnumReservedRangeOrBuilder> getReservedRangeFieldBuilder() {
                if (this.reservedRangeBuilder_ == null) {
                    this.reservedRangeBuilder_ = new RepeatedFieldBuilderV3<>(this.reservedRange_, (this.bitField0_ & 8) != 0, getParentForChildren(), isClean());
                    this.reservedRange_ = null;
                }
                return this.reservedRangeBuilder_;
            }

            private void ensureReservedNameIsMutable() {
                if (!this.reservedName_.isModifiable()) {
                    this.reservedName_ = new LazyStringArrayList(this.reservedName_);
                }
                this.bitField0_ |= 16;
            }

            @Override // com.google.protobuf.DescriptorProtos.EnumDescriptorProtoOrBuilder
            public int getReservedNameCount() {
                return this.reservedName_.size();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static EnumDescriptorProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<EnumDescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public EnumDescriptorProto getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class EnumValueDescriptorProto extends GeneratedMessageV3 implements EnumValueDescriptorProtoOrBuilder {
        private static final EnumValueDescriptorProto DEFAULT_INSTANCE = new EnumValueDescriptorProto();

        @Deprecated
        public static final Parser<EnumValueDescriptorProto> PARSER = new AbstractParser<EnumValueDescriptorProto>() { // from class: com.google.protobuf.DescriptorProtos.EnumValueDescriptorProto.1
            @Override // com.google.protobuf.Parser
            public EnumValueDescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = EnumValueDescriptorProto.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private int bitField0_;
        private byte memoizedIsInitialized;
        private volatile Object name_;
        private int number_;
        private EnumValueOptions options_;

        static /* synthetic */ int access$11676(EnumValueDescriptorProto enumValueDescriptorProto, int i) {
            int i2 = i | enumValueDescriptorProto.bitField0_;
            enumValueDescriptorProto.bitField0_ = i2;
            return i2;
        }

        private EnumValueDescriptorProto(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.name_ = "";
            this.number_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private EnumValueDescriptorProto() {
            this.name_ = "";
            this.number_ = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new EnumValueDescriptorProto();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumValueDescriptorProto.class, Builder.class);
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasNumber() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.protobuf.DescriptorProtos.EnumValueDescriptorProtoOrBuilder
        public int getNumber() {
            return this.number_;
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 4) != 0;
        }

        public EnumValueOptions getOptions() {
            EnumValueOptions enumValueOptions = this.options_;
            return enumValueOptions == null ? EnumValueOptions.getDefaultInstance() : enumValueOptions;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (hasOptions() && !getOptions().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString(output, 1, this.name_);
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeInt32(2, this.number_);
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeMessage(3, getOptions());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (this.bitField0_ & 1) != 0 ? 0 + GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
            if ((this.bitField0_ & 2) != 0) {
                iComputeStringSize += CodedOutputStream.computeInt32Size(2, this.number_);
            }
            if ((this.bitField0_ & 4) != 0) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(3, getOptions());
            }
            int serializedSize = iComputeStringSize + getUnknownFields().getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EnumValueDescriptorProto)) {
                return super.equals(obj);
            }
            EnumValueDescriptorProto enumValueDescriptorProto = (EnumValueDescriptorProto) obj;
            if (hasName() != enumValueDescriptorProto.hasName()) {
                return false;
            }
            if ((hasName() && !getName().equals(enumValueDescriptorProto.getName())) || hasNumber() != enumValueDescriptorProto.hasNumber()) {
                return false;
            }
            if ((!hasNumber() || getNumber() == enumValueDescriptorProto.getNumber()) && hasOptions() == enumValueDescriptorProto.hasOptions()) {
                return (!hasOptions() || getOptions().equals(enumValueDescriptorProto.getOptions())) && getUnknownFields().equals(enumValueDescriptorProto.getUnknownFields());
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasName()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getName().hashCode();
            }
            if (hasNumber()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getNumber();
            }
            if (hasOptions()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getOptions().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EnumValueDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object name_;
            private int number_;
            private SingleFieldBuilderV3<EnumValueOptions, EnumValueOptions.Builder, EnumValueOptionsOrBuilder> optionsBuilder_;
            private EnumValueOptions options_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumValueDescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getOptionsFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public EnumValueDescriptorProto getDefaultInstanceForType() {
                return EnumValueDescriptorProto.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumValueDescriptorProto build() {
                EnumValueDescriptorProto enumValueDescriptorProtoBuildPartial = buildPartial();
                if (enumValueDescriptorProtoBuildPartial.isInitialized()) {
                    return enumValueDescriptorProtoBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) enumValueDescriptorProtoBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumValueDescriptorProto buildPartial() {
                EnumValueDescriptorProto enumValueDescriptorProto = new EnumValueDescriptorProto(this);
                if (this.bitField0_ != 0) {
                    buildPartial0(enumValueDescriptorProto);
                }
                onBuilt();
                return enumValueDescriptorProto;
            }

            private void buildPartial0(EnumValueDescriptorProto result) {
                int i;
                EnumValueOptions enumValueOptions;
                int i2 = this.bitField0_;
                if ((i2 & 1) != 0) {
                    result.name_ = this.name_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    result.number_ = this.number_;
                    i |= 2;
                }
                if ((i2 & 4) != 0) {
                    SingleFieldBuilderV3<EnumValueOptions, EnumValueOptions.Builder, EnumValueOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        enumValueOptions = this.options_;
                    } else {
                        enumValueOptions = (EnumValueOptions) singleFieldBuilderV3.build();
                    }
                    result.options_ = enumValueOptions;
                    i |= 4;
                }
                EnumValueDescriptorProto.access$11676(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof EnumValueDescriptorProto) {
                    return mergeFrom((EnumValueDescriptorProto) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(EnumValueDescriptorProto other) {
                if (other == EnumValueDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.name_ = other.name_;
                    this.bitField0_ |= 1;
                    onChanged();
                }
                if (other.hasNumber()) {
                    setNumber(other.getNumber());
                }
                if (other.hasOptions()) {
                    mergeOptions(other.getOptions());
                }
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return !hasOptions() || getOptions().isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.name_ = input.readBytes();
                                    this.bitField0_ |= 1;
                                } else if (tag == 16) {
                                    this.number_ = input.readInt32();
                                    this.bitField0_ |= 2;
                                } else if (tag == 26) {
                                    input.readMessage(getOptionsFieldBuilder().getBuilder(), extensionRegistry);
                                    this.bitField0_ |= 4;
                                } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            public Builder setName(String value) {
                value.getClass();
                this.name_ = value;
                this.bitField0_ |= 1;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.DescriptorProtos.EnumValueDescriptorProtoOrBuilder
            public int getNumber() {
                return this.number_;
            }

            public Builder setNumber(int value) {
                this.number_ = value;
                this.bitField0_ |= 2;
                onChanged();
                return this;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 4) != 0;
            }

            public EnumValueOptions getOptions() {
                SingleFieldBuilderV3<EnumValueOptions, EnumValueOptions.Builder, EnumValueOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    EnumValueOptions enumValueOptions = this.options_;
                    return enumValueOptions == null ? EnumValueOptions.getDefaultInstance() : enumValueOptions;
                }
                return (EnumValueOptions) singleFieldBuilderV3.getMessage();
            }

            public Builder mergeOptions(EnumValueOptions value) {
                EnumValueOptions enumValueOptions;
                SingleFieldBuilderV3<EnumValueOptions, EnumValueOptions.Builder, EnumValueOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 4) != 0 && (enumValueOptions = this.options_) != null && enumValueOptions != EnumValueOptions.getDefaultInstance()) {
                        getOptionsBuilder().mergeFrom(value);
                    } else {
                        this.options_ = value;
                    }
                } else {
                    singleFieldBuilderV3.mergeFrom(value);
                }
                this.bitField0_ |= 4;
                onChanged();
                return this;
            }

            public EnumValueOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 4;
                onChanged();
                return (EnumValueOptions.Builder) getOptionsFieldBuilder().getBuilder();
            }

            private SingleFieldBuilderV3<EnumValueOptions, EnumValueOptions.Builder, EnumValueOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilderV3<>(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static EnumValueDescriptorProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<EnumValueDescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public EnumValueDescriptorProto getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class ServiceDescriptorProto extends GeneratedMessageV3 implements ServiceDescriptorProtoOrBuilder {
        private static final ServiceDescriptorProto DEFAULT_INSTANCE = new ServiceDescriptorProto();

        @Deprecated
        public static final Parser<ServiceDescriptorProto> PARSER = new AbstractParser<ServiceDescriptorProto>() { // from class: com.google.protobuf.DescriptorProtos.ServiceDescriptorProto.1
            @Override // com.google.protobuf.Parser
            public ServiceDescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = ServiceDescriptorProto.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private int bitField0_;
        private byte memoizedIsInitialized;
        private List<MethodDescriptorProto> method_;
        private volatile Object name_;
        private ServiceOptions options_;

        static /* synthetic */ int access$12576(ServiceDescriptorProto serviceDescriptorProto, int i) {
            int i2 = i | serviceDescriptorProto.bitField0_;
            serviceDescriptorProto.bitField0_ = i2;
            return i2;
        }

        private ServiceDescriptorProto(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.name_ = "";
            this.memoizedIsInitialized = (byte) -1;
        }

        private ServiceDescriptorProto() {
            this.name_ = "";
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
            this.method_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new ServiceDescriptorProto();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(ServiceDescriptorProto.class, Builder.class);
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = stringUtf8;
            }
            return stringUtf8;
        }

        public List<MethodDescriptorProto> getMethodList() {
            return this.method_;
        }

        @Override // com.google.protobuf.DescriptorProtos.ServiceDescriptorProtoOrBuilder
        public int getMethodCount() {
            return this.method_.size();
        }

        public MethodDescriptorProto getMethod(int index) {
            return this.method_.get(index);
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 2) != 0;
        }

        public ServiceOptions getOptions() {
            ServiceOptions serviceOptions = this.options_;
            return serviceOptions == null ? ServiceOptions.getDefaultInstance() : serviceOptions;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getMethodCount(); i++) {
                if (!getMethod(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (hasOptions() && !getOptions().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString(output, 1, this.name_);
            }
            for (int i = 0; i < this.method_.size(); i++) {
                output.writeMessage(2, this.method_.get(i));
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeMessage(3, getOptions());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (this.bitField0_ & 1) != 0 ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
            for (int i2 = 0; i2 < this.method_.size(); i2++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(2, this.method_.get(i2));
            }
            if ((this.bitField0_ & 2) != 0) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(3, getOptions());
            }
            int serializedSize = iComputeStringSize + getUnknownFields().getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ServiceDescriptorProto)) {
                return super.equals(obj);
            }
            ServiceDescriptorProto serviceDescriptorProto = (ServiceDescriptorProto) obj;
            if (hasName() != serviceDescriptorProto.hasName()) {
                return false;
            }
            if ((!hasName() || getName().equals(serviceDescriptorProto.getName())) && getMethodList().equals(serviceDescriptorProto.getMethodList()) && hasOptions() == serviceDescriptorProto.hasOptions()) {
                return (!hasOptions() || getOptions().equals(serviceDescriptorProto.getOptions())) && getUnknownFields().equals(serviceDescriptorProto.getUnknownFields());
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasName()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getName().hashCode();
            }
            if (getMethodCount() > 0) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getMethodList().hashCode();
            }
            if (hasOptions()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getOptions().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ServiceDescriptorProtoOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<MethodDescriptorProto, MethodDescriptorProto.Builder, Object> methodBuilder_;
            private List<MethodDescriptorProto> method_;
            private Object name_;
            private SingleFieldBuilderV3<ServiceOptions, ServiceOptions.Builder, ServiceOptionsOrBuilder> optionsBuilder_;
            private ServiceOptions options_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(ServiceDescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.name_ = "";
                this.method_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.name_ = "";
                this.method_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getMethodFieldBuilder();
                    getOptionsFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ServiceDescriptorProto getDefaultInstanceForType() {
                return ServiceDescriptorProto.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ServiceDescriptorProto build() {
                ServiceDescriptorProto serviceDescriptorProtoBuildPartial = buildPartial();
                if (serviceDescriptorProtoBuildPartial.isInitialized()) {
                    return serviceDescriptorProtoBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) serviceDescriptorProtoBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ServiceDescriptorProto buildPartial() {
                ServiceDescriptorProto serviceDescriptorProto = new ServiceDescriptorProto(this);
                buildPartialRepeatedFields(serviceDescriptorProto);
                if (this.bitField0_ != 0) {
                    buildPartial0(serviceDescriptorProto);
                }
                onBuilt();
                return serviceDescriptorProto;
            }

            private void buildPartialRepeatedFields(ServiceDescriptorProto result) {
                RepeatedFieldBuilderV3<MethodDescriptorProto, MethodDescriptorProto.Builder, Object> repeatedFieldBuilderV3 = this.methodBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.method_ = repeatedFieldBuilderV3.build();
                    return;
                }
                if ((this.bitField0_ & 2) != 0) {
                    this.method_ = Collections.unmodifiableList(this.method_);
                    this.bitField0_ &= -3;
                }
                result.method_ = this.method_;
            }

            private void buildPartial0(ServiceDescriptorProto result) {
                int i;
                ServiceOptions serviceOptions;
                int i2 = this.bitField0_;
                if ((i2 & 1) != 0) {
                    result.name_ = this.name_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 4) != 0) {
                    SingleFieldBuilderV3<ServiceOptions, ServiceOptions.Builder, ServiceOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        serviceOptions = this.options_;
                    } else {
                        serviceOptions = (ServiceOptions) singleFieldBuilderV3.build();
                    }
                    result.options_ = serviceOptions;
                    i |= 2;
                }
                ServiceDescriptorProto.access$12576(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof ServiceDescriptorProto) {
                    return mergeFrom((ServiceDescriptorProto) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ServiceDescriptorProto other) {
                if (other == ServiceDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.name_ = other.name_;
                    this.bitField0_ |= 1;
                    onChanged();
                }
                if (this.methodBuilder_ == null) {
                    if (!other.method_.isEmpty()) {
                        if (this.method_.isEmpty()) {
                            this.method_ = other.method_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureMethodIsMutable();
                            this.method_.addAll(other.method_);
                        }
                        onChanged();
                    }
                } else if (!other.method_.isEmpty()) {
                    if (!this.methodBuilder_.isEmpty()) {
                        this.methodBuilder_.addAllMessages(other.method_);
                    } else {
                        this.methodBuilder_.dispose();
                        this.methodBuilder_ = null;
                        this.method_ = other.method_;
                        this.bitField0_ &= -3;
                        this.methodBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getMethodFieldBuilder() : null;
                    }
                }
                if (other.hasOptions()) {
                    mergeOptions(other.getOptions());
                }
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getMethodCount(); i++) {
                    if (!getMethod(i).isInitialized()) {
                        return false;
                    }
                }
                return !hasOptions() || getOptions().isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.name_ = input.readBytes();
                                    this.bitField0_ |= 1;
                                } else if (tag == 18) {
                                    MethodDescriptorProto methodDescriptorProto = (MethodDescriptorProto) input.readMessage(MethodDescriptorProto.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<MethodDescriptorProto, MethodDescriptorProto.Builder, Object> repeatedFieldBuilderV3 = this.methodBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureMethodIsMutable();
                                        this.method_.add(methodDescriptorProto);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(methodDescriptorProto);
                                    }
                                } else if (tag == 26) {
                                    input.readMessage(getOptionsFieldBuilder().getBuilder(), extensionRegistry);
                                    this.bitField0_ |= 4;
                                } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            private void ensureMethodIsMutable() {
                if ((this.bitField0_ & 2) == 0) {
                    this.method_ = new ArrayList(this.method_);
                    this.bitField0_ |= 2;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.ServiceDescriptorProtoOrBuilder
            public int getMethodCount() {
                RepeatedFieldBuilderV3<MethodDescriptorProto, MethodDescriptorProto.Builder, Object> repeatedFieldBuilderV3 = this.methodBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.method_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public MethodDescriptorProto getMethod(int index) {
                RepeatedFieldBuilderV3<MethodDescriptorProto, MethodDescriptorProto.Builder, Object> repeatedFieldBuilderV3 = this.methodBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.method_.get(index);
                }
                return (MethodDescriptorProto) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<MethodDescriptorProto, MethodDescriptorProto.Builder, Object> getMethodFieldBuilder() {
                if (this.methodBuilder_ == null) {
                    this.methodBuilder_ = new RepeatedFieldBuilderV3<>(this.method_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                    this.method_ = null;
                }
                return this.methodBuilder_;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 4) != 0;
            }

            public ServiceOptions getOptions() {
                SingleFieldBuilderV3<ServiceOptions, ServiceOptions.Builder, ServiceOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ServiceOptions serviceOptions = this.options_;
                    return serviceOptions == null ? ServiceOptions.getDefaultInstance() : serviceOptions;
                }
                return (ServiceOptions) singleFieldBuilderV3.getMessage();
            }

            public Builder mergeOptions(ServiceOptions value) {
                ServiceOptions serviceOptions;
                SingleFieldBuilderV3<ServiceOptions, ServiceOptions.Builder, ServiceOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 4) != 0 && (serviceOptions = this.options_) != null && serviceOptions != ServiceOptions.getDefaultInstance()) {
                        getOptionsBuilder().mergeFrom(value);
                    } else {
                        this.options_ = value;
                    }
                } else {
                    singleFieldBuilderV3.mergeFrom(value);
                }
                this.bitField0_ |= 4;
                onChanged();
                return this;
            }

            public ServiceOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 4;
                onChanged();
                return (ServiceOptions.Builder) getOptionsFieldBuilder().getBuilder();
            }

            private SingleFieldBuilderV3<ServiceOptions, ServiceOptions.Builder, ServiceOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilderV3<>(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static ServiceDescriptorProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<ServiceDescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ServiceDescriptorProto getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class MethodDescriptorProto extends GeneratedMessageV3 implements MessageOrBuilder {
        private static final MethodDescriptorProto DEFAULT_INSTANCE = new MethodDescriptorProto();

        @Deprecated
        public static final Parser<MethodDescriptorProto> PARSER = new AbstractParser<MethodDescriptorProto>() { // from class: com.google.protobuf.DescriptorProtos.MethodDescriptorProto.1
            @Override // com.google.protobuf.Parser
            public MethodDescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = MethodDescriptorProto.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private int bitField0_;
        private boolean clientStreaming_;
        private volatile Object inputType_;
        private byte memoizedIsInitialized;
        private volatile Object name_;
        private MethodOptions options_;
        private volatile Object outputType_;
        private boolean serverStreaming_;

        static /* synthetic */ int access$13776(MethodDescriptorProto methodDescriptorProto, int i) {
            int i2 = i | methodDescriptorProto.bitField0_;
            methodDescriptorProto.bitField0_ = i2;
            return i2;
        }

        private MethodDescriptorProto(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.name_ = "";
            this.inputType_ = "";
            this.outputType_ = "";
            this.clientStreaming_ = false;
            this.serverStreaming_ = false;
            this.memoizedIsInitialized = (byte) -1;
        }

        private MethodDescriptorProto() {
            this.name_ = "";
            this.inputType_ = "";
            this.outputType_ = "";
            this.clientStreaming_ = false;
            this.serverStreaming_ = false;
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
            this.inputType_ = "";
            this.outputType_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new MethodDescriptorProto();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(MethodDescriptorProto.class, Builder.class);
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasInputType() {
            return (this.bitField0_ & 2) != 0;
        }

        public String getInputType() {
            Object obj = this.inputType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.inputType_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasOutputType() {
            return (this.bitField0_ & 4) != 0;
        }

        public String getOutputType() {
            Object obj = this.outputType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.outputType_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 8) != 0;
        }

        public MethodOptions getOptions() {
            MethodOptions methodOptions = this.options_;
            return methodOptions == null ? MethodOptions.getDefaultInstance() : methodOptions;
        }

        public boolean hasClientStreaming() {
            return (this.bitField0_ & 16) != 0;
        }

        public boolean getClientStreaming() {
            return this.clientStreaming_;
        }

        public boolean hasServerStreaming() {
            return (this.bitField0_ & 32) != 0;
        }

        public boolean getServerStreaming() {
            return this.serverStreaming_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (hasOptions() && !getOptions().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString(output, 1, this.name_);
            }
            if ((this.bitField0_ & 2) != 0) {
                GeneratedMessageV3.writeString(output, 2, this.inputType_);
            }
            if ((this.bitField0_ & 4) != 0) {
                GeneratedMessageV3.writeString(output, 3, this.outputType_);
            }
            if ((this.bitField0_ & 8) != 0) {
                output.writeMessage(4, getOptions());
            }
            if ((this.bitField0_ & 16) != 0) {
                output.writeBool(5, this.clientStreaming_);
            }
            if ((this.bitField0_ & 32) != 0) {
                output.writeBool(6, this.serverStreaming_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (this.bitField0_ & 1) != 0 ? 0 + GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
            if ((this.bitField0_ & 2) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.inputType_);
            }
            if ((this.bitField0_ & 4) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.outputType_);
            }
            if ((this.bitField0_ & 8) != 0) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(4, getOptions());
            }
            if ((this.bitField0_ & 16) != 0) {
                iComputeStringSize += CodedOutputStream.computeBoolSize(5, this.clientStreaming_);
            }
            if ((this.bitField0_ & 32) != 0) {
                iComputeStringSize += CodedOutputStream.computeBoolSize(6, this.serverStreaming_);
            }
            int serializedSize = iComputeStringSize + getUnknownFields().getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MethodDescriptorProto)) {
                return super.equals(obj);
            }
            MethodDescriptorProto methodDescriptorProto = (MethodDescriptorProto) obj;
            if (hasName() != methodDescriptorProto.hasName()) {
                return false;
            }
            if ((hasName() && !getName().equals(methodDescriptorProto.getName())) || hasInputType() != methodDescriptorProto.hasInputType()) {
                return false;
            }
            if ((hasInputType() && !getInputType().equals(methodDescriptorProto.getInputType())) || hasOutputType() != methodDescriptorProto.hasOutputType()) {
                return false;
            }
            if ((hasOutputType() && !getOutputType().equals(methodDescriptorProto.getOutputType())) || hasOptions() != methodDescriptorProto.hasOptions()) {
                return false;
            }
            if ((hasOptions() && !getOptions().equals(methodDescriptorProto.getOptions())) || hasClientStreaming() != methodDescriptorProto.hasClientStreaming()) {
                return false;
            }
            if ((!hasClientStreaming() || getClientStreaming() == methodDescriptorProto.getClientStreaming()) && hasServerStreaming() == methodDescriptorProto.hasServerStreaming()) {
                return (!hasServerStreaming() || getServerStreaming() == methodDescriptorProto.getServerStreaming()) && getUnknownFields().equals(methodDescriptorProto.getUnknownFields());
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasName()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getName().hashCode();
            }
            if (hasInputType()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getInputType().hashCode();
            }
            if (hasOutputType()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getOutputType().hashCode();
            }
            if (hasOptions()) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + getOptions().hashCode();
            }
            if (hasClientStreaming()) {
                iHashCode = (((iHashCode * 37) + 5) * 53) + Internal.hashBoolean(getClientStreaming());
            }
            if (hasServerStreaming()) {
                iHashCode = (((iHashCode * 37) + 6) * 53) + Internal.hashBoolean(getServerStreaming());
            }
            int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MessageOrBuilder {
            private int bitField0_;
            private boolean clientStreaming_;
            private Object inputType_;
            private Object name_;
            private SingleFieldBuilderV3<MethodOptions, MethodOptions.Builder, MethodOptionsOrBuilder> optionsBuilder_;
            private MethodOptions options_;
            private Object outputType_;
            private boolean serverStreaming_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(MethodDescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.name_ = "";
                this.inputType_ = "";
                this.outputType_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.name_ = "";
                this.inputType_ = "";
                this.outputType_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getOptionsFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MethodDescriptorProto getDefaultInstanceForType() {
                return MethodDescriptorProto.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MethodDescriptorProto build() {
                MethodDescriptorProto methodDescriptorProtoBuildPartial = buildPartial();
                if (methodDescriptorProtoBuildPartial.isInitialized()) {
                    return methodDescriptorProtoBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) methodDescriptorProtoBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MethodDescriptorProto buildPartial() {
                MethodDescriptorProto methodDescriptorProto = new MethodDescriptorProto(this);
                if (this.bitField0_ != 0) {
                    buildPartial0(methodDescriptorProto);
                }
                onBuilt();
                return methodDescriptorProto;
            }

            private void buildPartial0(MethodDescriptorProto result) {
                int i;
                MethodOptions methodOptions;
                int i2 = this.bitField0_;
                if ((i2 & 1) != 0) {
                    result.name_ = this.name_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    result.inputType_ = this.inputType_;
                    i |= 2;
                }
                if ((i2 & 4) != 0) {
                    result.outputType_ = this.outputType_;
                    i |= 4;
                }
                if ((i2 & 8) != 0) {
                    SingleFieldBuilderV3<MethodOptions, MethodOptions.Builder, MethodOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        methodOptions = this.options_;
                    } else {
                        methodOptions = (MethodOptions) singleFieldBuilderV3.build();
                    }
                    result.options_ = methodOptions;
                    i |= 8;
                }
                if ((i2 & 16) != 0) {
                    result.clientStreaming_ = this.clientStreaming_;
                    i |= 16;
                }
                if ((i2 & 32) != 0) {
                    result.serverStreaming_ = this.serverStreaming_;
                    i |= 32;
                }
                MethodDescriptorProto.access$13776(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof MethodDescriptorProto) {
                    return mergeFrom((MethodDescriptorProto) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(MethodDescriptorProto other) {
                if (other == MethodDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.name_ = other.name_;
                    this.bitField0_ |= 1;
                    onChanged();
                }
                if (other.hasInputType()) {
                    this.inputType_ = other.inputType_;
                    this.bitField0_ |= 2;
                    onChanged();
                }
                if (other.hasOutputType()) {
                    this.outputType_ = other.outputType_;
                    this.bitField0_ |= 4;
                    onChanged();
                }
                if (other.hasOptions()) {
                    mergeOptions(other.getOptions());
                }
                if (other.hasClientStreaming()) {
                    setClientStreaming(other.getClientStreaming());
                }
                if (other.hasServerStreaming()) {
                    setServerStreaming(other.getServerStreaming());
                }
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return !hasOptions() || getOptions().isInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.name_ = input.readBytes();
                                    this.bitField0_ |= 1;
                                } else if (tag == 18) {
                                    this.inputType_ = input.readBytes();
                                    this.bitField0_ |= 2;
                                } else if (tag == 26) {
                                    this.outputType_ = input.readBytes();
                                    this.bitField0_ |= 4;
                                } else if (tag == 34) {
                                    input.readMessage(getOptionsFieldBuilder().getBuilder(), extensionRegistry);
                                    this.bitField0_ |= 8;
                                } else if (tag == 40) {
                                    this.clientStreaming_ = input.readBool();
                                    this.bitField0_ |= 16;
                                } else if (tag == 48) {
                                    this.serverStreaming_ = input.readBool();
                                    this.bitField0_ |= 32;
                                } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 8) != 0;
            }

            public MethodOptions getOptions() {
                SingleFieldBuilderV3<MethodOptions, MethodOptions.Builder, MethodOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    MethodOptions methodOptions = this.options_;
                    return methodOptions == null ? MethodOptions.getDefaultInstance() : methodOptions;
                }
                return (MethodOptions) singleFieldBuilderV3.getMessage();
            }

            public Builder mergeOptions(MethodOptions value) {
                MethodOptions methodOptions;
                SingleFieldBuilderV3<MethodOptions, MethodOptions.Builder, MethodOptionsOrBuilder> singleFieldBuilderV3 = this.optionsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 8) != 0 && (methodOptions = this.options_) != null && methodOptions != MethodOptions.getDefaultInstance()) {
                        getOptionsBuilder().mergeFrom(value);
                    } else {
                        this.options_ = value;
                    }
                } else {
                    singleFieldBuilderV3.mergeFrom(value);
                }
                this.bitField0_ |= 8;
                onChanged();
                return this;
            }

            public MethodOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 8;
                onChanged();
                return (MethodOptions.Builder) getOptionsFieldBuilder().getBuilder();
            }

            private SingleFieldBuilderV3<MethodOptions, MethodOptions.Builder, MethodOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilderV3<>(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            public Builder setClientStreaming(boolean value) {
                this.clientStreaming_ = value;
                this.bitField0_ |= 16;
                onChanged();
                return this;
            }

            public Builder setServerStreaming(boolean value) {
                this.serverStreaming_ = value;
                this.bitField0_ |= 32;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static MethodDescriptorProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<MethodDescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MethodDescriptorProto getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class FileOptions extends GeneratedMessageV3.ExtendableMessage<FileOptions> implements FileOptionsOrBuilder {
        private static final FileOptions DEFAULT_INSTANCE = new FileOptions();

        @Deprecated
        public static final Parser<FileOptions> PARSER = new AbstractParser<FileOptions>() { // from class: com.google.protobuf.DescriptorProtos.FileOptions.1
            @Override // com.google.protobuf.Parser
            public FileOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = FileOptions.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private int bitField0_;
        private boolean ccEnableArenas_;
        private boolean ccGenericServices_;
        private volatile Object csharpNamespace_;
        private boolean deprecated_;
        private volatile Object goPackage_;
        private boolean javaGenerateEqualsAndHash_;
        private boolean javaGenericServices_;
        private boolean javaMultipleFiles_;
        private volatile Object javaOuterClassname_;
        private volatile Object javaPackage_;
        private boolean javaStringCheckUtf8_;
        private byte memoizedIsInitialized;
        private volatile Object objcClassPrefix_;
        private int optimizeFor_;
        private volatile Object phpClassPrefix_;
        private boolean phpGenericServices_;
        private volatile Object phpMetadataNamespace_;
        private volatile Object phpNamespace_;
        private boolean pyGenericServices_;
        private volatile Object rubyPackage_;
        private volatile Object swiftPrefix_;
        private List<UninterpretedOption> uninterpretedOption_;

        static /* synthetic */ int access$16476(FileOptions fileOptions, int i) {
            int i2 = i | fileOptions.bitField0_;
            fileOptions.bitField0_ = i2;
            return i2;
        }

        private FileOptions(GeneratedMessageV3.ExtendableBuilder<FileOptions, ?> builder) {
            super(builder);
            this.javaPackage_ = "";
            this.javaOuterClassname_ = "";
            this.javaMultipleFiles_ = false;
            this.javaGenerateEqualsAndHash_ = false;
            this.javaStringCheckUtf8_ = false;
            this.optimizeFor_ = 1;
            this.goPackage_ = "";
            this.ccGenericServices_ = false;
            this.javaGenericServices_ = false;
            this.pyGenericServices_ = false;
            this.phpGenericServices_ = false;
            this.deprecated_ = false;
            this.ccEnableArenas_ = true;
            this.objcClassPrefix_ = "";
            this.csharpNamespace_ = "";
            this.swiftPrefix_ = "";
            this.phpClassPrefix_ = "";
            this.phpNamespace_ = "";
            this.phpMetadataNamespace_ = "";
            this.rubyPackage_ = "";
            this.memoizedIsInitialized = (byte) -1;
        }

        private FileOptions() {
            this.javaPackage_ = "";
            this.javaOuterClassname_ = "";
            this.javaMultipleFiles_ = false;
            this.javaGenerateEqualsAndHash_ = false;
            this.javaStringCheckUtf8_ = false;
            this.optimizeFor_ = 1;
            this.goPackage_ = "";
            this.ccGenericServices_ = false;
            this.javaGenericServices_ = false;
            this.pyGenericServices_ = false;
            this.phpGenericServices_ = false;
            this.deprecated_ = false;
            this.ccEnableArenas_ = true;
            this.objcClassPrefix_ = "";
            this.csharpNamespace_ = "";
            this.swiftPrefix_ = "";
            this.phpClassPrefix_ = "";
            this.phpNamespace_ = "";
            this.phpMetadataNamespace_ = "";
            this.rubyPackage_ = "";
            this.memoizedIsInitialized = (byte) -1;
            this.javaPackage_ = "";
            this.javaOuterClassname_ = "";
            this.optimizeFor_ = 1;
            this.goPackage_ = "";
            this.ccEnableArenas_ = true;
            this.objcClassPrefix_ = "";
            this.csharpNamespace_ = "";
            this.swiftPrefix_ = "";
            this.phpClassPrefix_ = "";
            this.phpNamespace_ = "";
            this.phpMetadataNamespace_ = "";
            this.rubyPackage_ = "";
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new FileOptions();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_FileOptions_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_FileOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(FileOptions.class, Builder.class);
        }

        public enum OptimizeMode implements ProtocolMessageEnum {
            SPEED(1),
            CODE_SIZE(2),
            LITE_RUNTIME(3);

            public static final int CODE_SIZE_VALUE = 2;
            public static final int LITE_RUNTIME_VALUE = 3;
            public static final int SPEED_VALUE = 1;
            private final int value;
            private static final Internal.EnumLiteMap<OptimizeMode> internalValueMap = new Internal.EnumLiteMap<OptimizeMode>() { // from class: com.google.protobuf.DescriptorProtos.FileOptions.OptimizeMode.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public OptimizeMode findValueByNumber(int number) {
                    return OptimizeMode.forNumber(number);
                }
            };
            private static final OptimizeMode[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static OptimizeMode valueOf(int value) {
                return forNumber(value);
            }

            public static OptimizeMode forNumber(int value) {
                if (value == 1) {
                    return SPEED;
                }
                if (value == 2) {
                    return CODE_SIZE;
                }
                if (value != 3) {
                    return null;
                }
                return LITE_RUNTIME;
            }

            public static Internal.EnumLiteMap<OptimizeMode> internalGetValueMap() {
                return internalValueMap;
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FileOptions.getDescriptor().getEnumTypes().get(0);
            }

            public static OptimizeMode valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            OptimizeMode(int value) {
                this.value = value;
            }
        }

        public boolean hasJavaPackage() {
            return (this.bitField0_ & 1) != 0;
        }

        public String getJavaPackage() {
            Object obj = this.javaPackage_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.javaPackage_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasJavaOuterClassname() {
            return (this.bitField0_ & 2) != 0;
        }

        public String getJavaOuterClassname() {
            Object obj = this.javaOuterClassname_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.javaOuterClassname_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasJavaMultipleFiles() {
            return (this.bitField0_ & 4) != 0;
        }

        public boolean getJavaMultipleFiles() {
            return this.javaMultipleFiles_;
        }

        @Deprecated
        public boolean hasJavaGenerateEqualsAndHash() {
            return (this.bitField0_ & 8) != 0;
        }

        @Deprecated
        public boolean getJavaGenerateEqualsAndHash() {
            return this.javaGenerateEqualsAndHash_;
        }

        public boolean hasJavaStringCheckUtf8() {
            return (this.bitField0_ & 16) != 0;
        }

        public boolean getJavaStringCheckUtf8() {
            return this.javaStringCheckUtf8_;
        }

        public boolean hasOptimizeFor() {
            return (this.bitField0_ & 32) != 0;
        }

        public OptimizeMode getOptimizeFor() {
            OptimizeMode optimizeModeForNumber = OptimizeMode.forNumber(this.optimizeFor_);
            return optimizeModeForNumber == null ? OptimizeMode.SPEED : optimizeModeForNumber;
        }

        public boolean hasGoPackage() {
            return (this.bitField0_ & 64) != 0;
        }

        public String getGoPackage() {
            Object obj = this.goPackage_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.goPackage_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasCcGenericServices() {
            return (this.bitField0_ & 128) != 0;
        }

        public boolean getCcGenericServices() {
            return this.ccGenericServices_;
        }

        public boolean hasJavaGenericServices() {
            return (this.bitField0_ & 256) != 0;
        }

        public boolean getJavaGenericServices() {
            return this.javaGenericServices_;
        }

        public boolean hasPyGenericServices() {
            return (this.bitField0_ & 512) != 0;
        }

        public boolean getPyGenericServices() {
            return this.pyGenericServices_;
        }

        public boolean hasPhpGenericServices() {
            return (this.bitField0_ & 1024) != 0;
        }

        public boolean getPhpGenericServices() {
            return this.phpGenericServices_;
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 2048) != 0;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public boolean hasCcEnableArenas() {
            return (this.bitField0_ & 4096) != 0;
        }

        public boolean getCcEnableArenas() {
            return this.ccEnableArenas_;
        }

        public boolean hasObjcClassPrefix() {
            return (this.bitField0_ & 8192) != 0;
        }

        public String getObjcClassPrefix() {
            Object obj = this.objcClassPrefix_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.objcClassPrefix_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasCsharpNamespace() {
            return (this.bitField0_ & 16384) != 0;
        }

        public String getCsharpNamespace() {
            Object obj = this.csharpNamespace_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.csharpNamespace_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasSwiftPrefix() {
            return (this.bitField0_ & 32768) != 0;
        }

        public String getSwiftPrefix() {
            Object obj = this.swiftPrefix_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.swiftPrefix_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasPhpClassPrefix() {
            return (this.bitField0_ & 65536) != 0;
        }

        public String getPhpClassPrefix() {
            Object obj = this.phpClassPrefix_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.phpClassPrefix_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasPhpNamespace() {
            return (this.bitField0_ & 131072) != 0;
        }

        public String getPhpNamespace() {
            Object obj = this.phpNamespace_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.phpNamespace_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasPhpMetadataNamespace() {
            return (this.bitField0_ & 262144) != 0;
        }

        public String getPhpMetadataNamespace() {
            Object obj = this.phpMetadataNamespace_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.phpMetadataNamespace_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasRubyPackage() {
            return (this.bitField0_ & 524288) != 0;
        }

        public String getRubyPackage() {
            Object obj = this.rubyPackage_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.rubyPackage_ = stringUtf8;
            }
            return stringUtf8;
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override // com.google.protobuf.DescriptorProtos.FileOptionsOrBuilder
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.ExtendableMessage, com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                if (!getUninterpretedOption(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            GeneratedMessageV3.ExtendableMessage<MessageT>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString(output, 1, this.javaPackage_);
            }
            if ((this.bitField0_ & 2) != 0) {
                GeneratedMessageV3.writeString(output, 8, this.javaOuterClassname_);
            }
            if ((this.bitField0_ & 32) != 0) {
                output.writeEnum(9, this.optimizeFor_);
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeBool(10, this.javaMultipleFiles_);
            }
            if ((this.bitField0_ & 64) != 0) {
                GeneratedMessageV3.writeString(output, 11, this.goPackage_);
            }
            if ((this.bitField0_ & 128) != 0) {
                output.writeBool(16, this.ccGenericServices_);
            }
            if ((this.bitField0_ & 256) != 0) {
                output.writeBool(17, this.javaGenericServices_);
            }
            if ((this.bitField0_ & 512) != 0) {
                output.writeBool(18, this.pyGenericServices_);
            }
            if ((this.bitField0_ & 8) != 0) {
                output.writeBool(20, this.javaGenerateEqualsAndHash_);
            }
            if ((this.bitField0_ & 2048) != 0) {
                output.writeBool(23, this.deprecated_);
            }
            if ((this.bitField0_ & 16) != 0) {
                output.writeBool(27, this.javaStringCheckUtf8_);
            }
            if ((this.bitField0_ & 4096) != 0) {
                output.writeBool(31, this.ccEnableArenas_);
            }
            if ((this.bitField0_ & 8192) != 0) {
                GeneratedMessageV3.writeString(output, 36, this.objcClassPrefix_);
            }
            if ((this.bitField0_ & 16384) != 0) {
                GeneratedMessageV3.writeString(output, 37, this.csharpNamespace_);
            }
            if ((this.bitField0_ & 32768) != 0) {
                GeneratedMessageV3.writeString(output, 39, this.swiftPrefix_);
            }
            if ((this.bitField0_ & 65536) != 0) {
                GeneratedMessageV3.writeString(output, 40, this.phpClassPrefix_);
            }
            if ((this.bitField0_ & 131072) != 0) {
                GeneratedMessageV3.writeString(output, 41, this.phpNamespace_);
            }
            if ((this.bitField0_ & 1024) != 0) {
                output.writeBool(42, this.phpGenericServices_);
            }
            if ((this.bitField0_ & 262144) != 0) {
                GeneratedMessageV3.writeString(output, 44, this.phpMetadataNamespace_);
            }
            if ((this.bitField0_ & 524288) != 0) {
                GeneratedMessageV3.writeString(output, 45, this.rubyPackage_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriterNewExtensionWriter.writeUntil(536870912, output);
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (this.bitField0_ & 1) != 0 ? GeneratedMessageV3.computeStringSize(1, this.javaPackage_) + 0 : 0;
            if ((this.bitField0_ & 2) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(8, this.javaOuterClassname_);
            }
            if ((this.bitField0_ & 32) != 0) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(9, this.optimizeFor_);
            }
            if ((this.bitField0_ & 4) != 0) {
                iComputeStringSize += CodedOutputStream.computeBoolSize(10, this.javaMultipleFiles_);
            }
            if ((this.bitField0_ & 64) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(11, this.goPackage_);
            }
            if ((this.bitField0_ & 128) != 0) {
                iComputeStringSize += CodedOutputStream.computeBoolSize(16, this.ccGenericServices_);
            }
            if ((this.bitField0_ & 256) != 0) {
                iComputeStringSize += CodedOutputStream.computeBoolSize(17, this.javaGenericServices_);
            }
            if ((this.bitField0_ & 512) != 0) {
                iComputeStringSize += CodedOutputStream.computeBoolSize(18, this.pyGenericServices_);
            }
            if ((this.bitField0_ & 8) != 0) {
                iComputeStringSize += CodedOutputStream.computeBoolSize(20, this.javaGenerateEqualsAndHash_);
            }
            if ((this.bitField0_ & 2048) != 0) {
                iComputeStringSize += CodedOutputStream.computeBoolSize(23, this.deprecated_);
            }
            if ((this.bitField0_ & 16) != 0) {
                iComputeStringSize += CodedOutputStream.computeBoolSize(27, this.javaStringCheckUtf8_);
            }
            if ((this.bitField0_ & 4096) != 0) {
                iComputeStringSize += CodedOutputStream.computeBoolSize(31, this.ccEnableArenas_);
            }
            if ((this.bitField0_ & 8192) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(36, this.objcClassPrefix_);
            }
            if ((this.bitField0_ & 16384) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(37, this.csharpNamespace_);
            }
            if ((this.bitField0_ & 32768) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(39, this.swiftPrefix_);
            }
            if ((this.bitField0_ & 65536) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(40, this.phpClassPrefix_);
            }
            if ((this.bitField0_ & 131072) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(41, this.phpNamespace_);
            }
            if ((this.bitField0_ & 1024) != 0) {
                iComputeStringSize += CodedOutputStream.computeBoolSize(42, this.phpGenericServices_);
            }
            if ((this.bitField0_ & 262144) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(44, this.phpMetadataNamespace_);
            }
            if ((this.bitField0_ & 524288) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(45, this.rubyPackage_);
            }
            for (int i2 = 0; i2 < this.uninterpretedOption_.size(); i2++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i2));
            }
            int iExtensionsSerializedSize = iComputeStringSize + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSize = iExtensionsSerializedSize;
            return iExtensionsSerializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FileOptions)) {
                return super.equals(obj);
            }
            FileOptions fileOptions = (FileOptions) obj;
            if (hasJavaPackage() != fileOptions.hasJavaPackage()) {
                return false;
            }
            if ((hasJavaPackage() && !getJavaPackage().equals(fileOptions.getJavaPackage())) || hasJavaOuterClassname() != fileOptions.hasJavaOuterClassname()) {
                return false;
            }
            if ((hasJavaOuterClassname() && !getJavaOuterClassname().equals(fileOptions.getJavaOuterClassname())) || hasJavaMultipleFiles() != fileOptions.hasJavaMultipleFiles()) {
                return false;
            }
            if ((hasJavaMultipleFiles() && getJavaMultipleFiles() != fileOptions.getJavaMultipleFiles()) || hasJavaGenerateEqualsAndHash() != fileOptions.hasJavaGenerateEqualsAndHash()) {
                return false;
            }
            if ((hasJavaGenerateEqualsAndHash() && getJavaGenerateEqualsAndHash() != fileOptions.getJavaGenerateEqualsAndHash()) || hasJavaStringCheckUtf8() != fileOptions.hasJavaStringCheckUtf8()) {
                return false;
            }
            if ((hasJavaStringCheckUtf8() && getJavaStringCheckUtf8() != fileOptions.getJavaStringCheckUtf8()) || hasOptimizeFor() != fileOptions.hasOptimizeFor()) {
                return false;
            }
            if ((hasOptimizeFor() && this.optimizeFor_ != fileOptions.optimizeFor_) || hasGoPackage() != fileOptions.hasGoPackage()) {
                return false;
            }
            if ((hasGoPackage() && !getGoPackage().equals(fileOptions.getGoPackage())) || hasCcGenericServices() != fileOptions.hasCcGenericServices()) {
                return false;
            }
            if ((hasCcGenericServices() && getCcGenericServices() != fileOptions.getCcGenericServices()) || hasJavaGenericServices() != fileOptions.hasJavaGenericServices()) {
                return false;
            }
            if ((hasJavaGenericServices() && getJavaGenericServices() != fileOptions.getJavaGenericServices()) || hasPyGenericServices() != fileOptions.hasPyGenericServices()) {
                return false;
            }
            if ((hasPyGenericServices() && getPyGenericServices() != fileOptions.getPyGenericServices()) || hasPhpGenericServices() != fileOptions.hasPhpGenericServices()) {
                return false;
            }
            if ((hasPhpGenericServices() && getPhpGenericServices() != fileOptions.getPhpGenericServices()) || hasDeprecated() != fileOptions.hasDeprecated()) {
                return false;
            }
            if ((hasDeprecated() && getDeprecated() != fileOptions.getDeprecated()) || hasCcEnableArenas() != fileOptions.hasCcEnableArenas()) {
                return false;
            }
            if ((hasCcEnableArenas() && getCcEnableArenas() != fileOptions.getCcEnableArenas()) || hasObjcClassPrefix() != fileOptions.hasObjcClassPrefix()) {
                return false;
            }
            if ((hasObjcClassPrefix() && !getObjcClassPrefix().equals(fileOptions.getObjcClassPrefix())) || hasCsharpNamespace() != fileOptions.hasCsharpNamespace()) {
                return false;
            }
            if ((hasCsharpNamespace() && !getCsharpNamespace().equals(fileOptions.getCsharpNamespace())) || hasSwiftPrefix() != fileOptions.hasSwiftPrefix()) {
                return false;
            }
            if ((hasSwiftPrefix() && !getSwiftPrefix().equals(fileOptions.getSwiftPrefix())) || hasPhpClassPrefix() != fileOptions.hasPhpClassPrefix()) {
                return false;
            }
            if ((hasPhpClassPrefix() && !getPhpClassPrefix().equals(fileOptions.getPhpClassPrefix())) || hasPhpNamespace() != fileOptions.hasPhpNamespace()) {
                return false;
            }
            if ((hasPhpNamespace() && !getPhpNamespace().equals(fileOptions.getPhpNamespace())) || hasPhpMetadataNamespace() != fileOptions.hasPhpMetadataNamespace()) {
                return false;
            }
            if ((!hasPhpMetadataNamespace() || getPhpMetadataNamespace().equals(fileOptions.getPhpMetadataNamespace())) && hasRubyPackage() == fileOptions.hasRubyPackage()) {
                return (!hasRubyPackage() || getRubyPackage().equals(fileOptions.getRubyPackage())) && getUninterpretedOptionList().equals(fileOptions.getUninterpretedOptionList()) && getUnknownFields().equals(fileOptions.getUnknownFields()) && getExtensionFields().equals(fileOptions.getExtensionFields());
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasJavaPackage()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getJavaPackage().hashCode();
            }
            if (hasJavaOuterClassname()) {
                iHashCode = (((iHashCode * 37) + 8) * 53) + getJavaOuterClassname().hashCode();
            }
            if (hasJavaMultipleFiles()) {
                iHashCode = (((iHashCode * 37) + 10) * 53) + Internal.hashBoolean(getJavaMultipleFiles());
            }
            if (hasJavaGenerateEqualsAndHash()) {
                iHashCode = (((iHashCode * 37) + 20) * 53) + Internal.hashBoolean(getJavaGenerateEqualsAndHash());
            }
            if (hasJavaStringCheckUtf8()) {
                iHashCode = (((iHashCode * 37) + 27) * 53) + Internal.hashBoolean(getJavaStringCheckUtf8());
            }
            if (hasOptimizeFor()) {
                iHashCode = (((iHashCode * 37) + 9) * 53) + this.optimizeFor_;
            }
            if (hasGoPackage()) {
                iHashCode = (((iHashCode * 37) + 11) * 53) + getGoPackage().hashCode();
            }
            if (hasCcGenericServices()) {
                iHashCode = (((iHashCode * 37) + 16) * 53) + Internal.hashBoolean(getCcGenericServices());
            }
            if (hasJavaGenericServices()) {
                iHashCode = (((iHashCode * 37) + 17) * 53) + Internal.hashBoolean(getJavaGenericServices());
            }
            if (hasPyGenericServices()) {
                iHashCode = (((iHashCode * 37) + 18) * 53) + Internal.hashBoolean(getPyGenericServices());
            }
            if (hasPhpGenericServices()) {
                iHashCode = (((iHashCode * 37) + 42) * 53) + Internal.hashBoolean(getPhpGenericServices());
            }
            if (hasDeprecated()) {
                iHashCode = (((iHashCode * 37) + 23) * 53) + Internal.hashBoolean(getDeprecated());
            }
            if (hasCcEnableArenas()) {
                iHashCode = (((iHashCode * 37) + 31) * 53) + Internal.hashBoolean(getCcEnableArenas());
            }
            if (hasObjcClassPrefix()) {
                iHashCode = (((iHashCode * 37) + 36) * 53) + getObjcClassPrefix().hashCode();
            }
            if (hasCsharpNamespace()) {
                iHashCode = (((iHashCode * 37) + 37) * 53) + getCsharpNamespace().hashCode();
            }
            if (hasSwiftPrefix()) {
                iHashCode = (((iHashCode * 37) + 39) * 53) + getSwiftPrefix().hashCode();
            }
            if (hasPhpClassPrefix()) {
                iHashCode = (((iHashCode * 37) + 40) * 53) + getPhpClassPrefix().hashCode();
            }
            if (hasPhpNamespace()) {
                iHashCode = (((iHashCode * 37) + 41) * 53) + getPhpNamespace().hashCode();
            }
            if (hasPhpMetadataNamespace()) {
                iHashCode = (((iHashCode * 37) + 44) * 53) + getPhpMetadataNamespace().hashCode();
            }
            if (hasRubyPackage()) {
                iHashCode = (((iHashCode * 37) + 45) * 53) + getRubyPackage().hashCode();
            }
            if (getUninterpretedOptionCount() > 0) {
                iHashCode = (((iHashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
            }
            int iHashFields = (AbstractMessage.hashFields(iHashCode, getExtensionFields()) * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashFields;
            return iHashFields;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.ExtendableBuilder<FileOptions, Builder> implements FileOptionsOrBuilder {
            private int bitField0_;
            private boolean ccEnableArenas_;
            private boolean ccGenericServices_;
            private Object csharpNamespace_;
            private boolean deprecated_;
            private Object goPackage_;
            private boolean javaGenerateEqualsAndHash_;
            private boolean javaGenericServices_;
            private boolean javaMultipleFiles_;
            private Object javaOuterClassname_;
            private Object javaPackage_;
            private boolean javaStringCheckUtf8_;
            private Object objcClassPrefix_;
            private int optimizeFor_;
            private Object phpClassPrefix_;
            private boolean phpGenericServices_;
            private Object phpMetadataNamespace_;
            private Object phpNamespace_;
            private boolean pyGenericServices_;
            private Object rubyPackage_;
            private Object swiftPrefix_;
            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
            private List<UninterpretedOption> uninterpretedOption_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_FileOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(FileOptions.class, Builder.class);
            }

            private Builder() {
                this.javaPackage_ = "";
                this.javaOuterClassname_ = "";
                this.optimizeFor_ = 1;
                this.goPackage_ = "";
                this.ccEnableArenas_ = true;
                this.objcClassPrefix_ = "";
                this.csharpNamespace_ = "";
                this.swiftPrefix_ = "";
                this.phpClassPrefix_ = "";
                this.phpNamespace_ = "";
                this.phpMetadataNamespace_ = "";
                this.rubyPackage_ = "";
                this.uninterpretedOption_ = Collections.emptyList();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.javaPackage_ = "";
                this.javaOuterClassname_ = "";
                this.optimizeFor_ = 1;
                this.goPackage_ = "";
                this.ccEnableArenas_ = true;
                this.objcClassPrefix_ = "";
                this.csharpNamespace_ = "";
                this.swiftPrefix_ = "";
                this.phpClassPrefix_ = "";
                this.phpNamespace_ = "";
                this.phpMetadataNamespace_ = "";
                this.rubyPackage_ = "";
                this.uninterpretedOption_ = Collections.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_FileOptions_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public FileOptions getDefaultInstanceForType() {
                return FileOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FileOptions build() {
                FileOptions fileOptionsBuildPartial = buildPartial();
                if (fileOptionsBuildPartial.isInitialized()) {
                    return fileOptionsBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) fileOptionsBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FileOptions buildPartial() {
                FileOptions fileOptions = new FileOptions(this);
                buildPartialRepeatedFields(fileOptions);
                if (this.bitField0_ != 0) {
                    buildPartial0(fileOptions);
                }
                onBuilt();
                return fileOptions;
            }

            private void buildPartialRepeatedFields(FileOptions result) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.uninterpretedOption_ = repeatedFieldBuilderV3.build();
                    return;
                }
                if ((this.bitField0_ & 1048576) != 0) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    this.bitField0_ &= -1048577;
                }
                result.uninterpretedOption_ = this.uninterpretedOption_;
            }

            private void buildPartial0(FileOptions result) {
                int i;
                int i2 = this.bitField0_;
                if ((i2 & 1) != 0) {
                    result.javaPackage_ = this.javaPackage_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    result.javaOuterClassname_ = this.javaOuterClassname_;
                    i |= 2;
                }
                if ((i2 & 4) != 0) {
                    result.javaMultipleFiles_ = this.javaMultipleFiles_;
                    i |= 4;
                }
                if ((i2 & 8) != 0) {
                    result.javaGenerateEqualsAndHash_ = this.javaGenerateEqualsAndHash_;
                    i |= 8;
                }
                if ((i2 & 16) != 0) {
                    result.javaStringCheckUtf8_ = this.javaStringCheckUtf8_;
                    i |= 16;
                }
                if ((i2 & 32) != 0) {
                    result.optimizeFor_ = this.optimizeFor_;
                    i |= 32;
                }
                if ((i2 & 64) != 0) {
                    result.goPackage_ = this.goPackage_;
                    i |= 64;
                }
                if ((i2 & 128) != 0) {
                    result.ccGenericServices_ = this.ccGenericServices_;
                    i |= 128;
                }
                if ((i2 & 256) != 0) {
                    result.javaGenericServices_ = this.javaGenericServices_;
                    i |= 256;
                }
                if ((i2 & 512) != 0) {
                    result.pyGenericServices_ = this.pyGenericServices_;
                    i |= 512;
                }
                if ((i2 & 1024) != 0) {
                    result.phpGenericServices_ = this.phpGenericServices_;
                    i |= 1024;
                }
                if ((i2 & 2048) != 0) {
                    result.deprecated_ = this.deprecated_;
                    i |= 2048;
                }
                if ((i2 & 4096) != 0) {
                    result.ccEnableArenas_ = this.ccEnableArenas_;
                    i |= 4096;
                }
                if ((i2 & 8192) != 0) {
                    result.objcClassPrefix_ = this.objcClassPrefix_;
                    i |= 8192;
                }
                if ((i2 & 16384) != 0) {
                    result.csharpNamespace_ = this.csharpNamespace_;
                    i |= 16384;
                }
                if ((i2 & 32768) != 0) {
                    result.swiftPrefix_ = this.swiftPrefix_;
                    i |= 32768;
                }
                if ((i2 & 65536) != 0) {
                    result.phpClassPrefix_ = this.phpClassPrefix_;
                    i |= 65536;
                }
                if ((i2 & 131072) != 0) {
                    result.phpNamespace_ = this.phpNamespace_;
                    i |= 131072;
                }
                if ((i2 & 262144) != 0) {
                    result.phpMetadataNamespace_ = this.phpMetadataNamespace_;
                    i |= 262144;
                }
                if ((i2 & 524288) != 0) {
                    result.rubyPackage_ = this.rubyPackage_;
                    i |= 524288;
                }
                FileOptions.access$16476(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof FileOptions) {
                    return mergeFrom((FileOptions) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FileOptions other) {
                if (other == FileOptions.getDefaultInstance()) {
                    return this;
                }
                if (other.hasJavaPackage()) {
                    this.javaPackage_ = other.javaPackage_;
                    this.bitField0_ |= 1;
                    onChanged();
                }
                if (other.hasJavaOuterClassname()) {
                    this.javaOuterClassname_ = other.javaOuterClassname_;
                    this.bitField0_ |= 2;
                    onChanged();
                }
                if (other.hasJavaMultipleFiles()) {
                    setJavaMultipleFiles(other.getJavaMultipleFiles());
                }
                if (other.hasJavaGenerateEqualsAndHash()) {
                    setJavaGenerateEqualsAndHash(other.getJavaGenerateEqualsAndHash());
                }
                if (other.hasJavaStringCheckUtf8()) {
                    setJavaStringCheckUtf8(other.getJavaStringCheckUtf8());
                }
                if (other.hasOptimizeFor()) {
                    setOptimizeFor(other.getOptimizeFor());
                }
                if (other.hasGoPackage()) {
                    this.goPackage_ = other.goPackage_;
                    this.bitField0_ |= 64;
                    onChanged();
                }
                if (other.hasCcGenericServices()) {
                    setCcGenericServices(other.getCcGenericServices());
                }
                if (other.hasJavaGenericServices()) {
                    setJavaGenericServices(other.getJavaGenericServices());
                }
                if (other.hasPyGenericServices()) {
                    setPyGenericServices(other.getPyGenericServices());
                }
                if (other.hasPhpGenericServices()) {
                    setPhpGenericServices(other.getPhpGenericServices());
                }
                if (other.hasDeprecated()) {
                    setDeprecated(other.getDeprecated());
                }
                if (other.hasCcEnableArenas()) {
                    setCcEnableArenas(other.getCcEnableArenas());
                }
                if (other.hasObjcClassPrefix()) {
                    this.objcClassPrefix_ = other.objcClassPrefix_;
                    this.bitField0_ |= 8192;
                    onChanged();
                }
                if (other.hasCsharpNamespace()) {
                    this.csharpNamespace_ = other.csharpNamespace_;
                    this.bitField0_ |= 16384;
                    onChanged();
                }
                if (other.hasSwiftPrefix()) {
                    this.swiftPrefix_ = other.swiftPrefix_;
                    this.bitField0_ |= 32768;
                    onChanged();
                }
                if (other.hasPhpClassPrefix()) {
                    this.phpClassPrefix_ = other.phpClassPrefix_;
                    this.bitField0_ |= 65536;
                    onChanged();
                }
                if (other.hasPhpNamespace()) {
                    this.phpNamespace_ = other.phpNamespace_;
                    this.bitField0_ |= 131072;
                    onChanged();
                }
                if (other.hasPhpMetadataNamespace()) {
                    this.phpMetadataNamespace_ = other.phpMetadataNamespace_;
                    this.bitField0_ |= 262144;
                    onChanged();
                }
                if (other.hasRubyPackage()) {
                    this.rubyPackage_ = other.rubyPackage_;
                    this.bitField0_ |= 524288;
                    onChanged();
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= -1048577;
                        } else {
                            ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (!this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    } else {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ = (-1048577) & this.bitField0_;
                        this.uninterpretedOptionBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
                    }
                }
                mergeExtensionFields(other);
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.ExtendableBuilder, com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    z = true;
                                    break;
                                case 10:
                                    this.javaPackage_ = input.readBytes();
                                    this.bitField0_ |= 1;
                                    break;
                                case 66:
                                    this.javaOuterClassname_ = input.readBytes();
                                    this.bitField0_ |= 2;
                                    break;
                                case 72:
                                    int i = input.readEnum();
                                    if (OptimizeMode.forNumber(i) == null) {
                                        mergeUnknownVarintField(9, i);
                                    } else {
                                        this.optimizeFor_ = i;
                                        this.bitField0_ |= 32;
                                    }
                                    break;
                                case 80:
                                    this.javaMultipleFiles_ = input.readBool();
                                    this.bitField0_ |= 4;
                                    break;
                                case 90:
                                    this.goPackage_ = input.readBytes();
                                    this.bitField0_ |= 64;
                                    break;
                                case 128:
                                    this.ccGenericServices_ = input.readBool();
                                    this.bitField0_ |= 128;
                                    break;
                                case 136:
                                    this.javaGenericServices_ = input.readBool();
                                    this.bitField0_ |= 256;
                                    break;
                                case voOSType.VOOSMP_PID_ANALYTICS_AGENT /* 144 */:
                                    this.pyGenericServices_ = input.readBool();
                                    this.bitField0_ |= 512;
                                    break;
                                case CBORConstants.PREFIX_TYPE_OBJECT /* 160 */:
                                    this.javaGenerateEqualsAndHash_ = input.readBool();
                                    this.bitField0_ |= 8;
                                    break;
                                case 184:
                                    this.deprecated_ = input.readBool();
                                    this.bitField0_ |= 2048;
                                    break;
                                case 216:
                                    this.javaStringCheckUtf8_ = input.readBool();
                                    this.bitField0_ |= 16;
                                    break;
                                case 248:
                                    this.ccEnableArenas_ = input.readBool();
                                    this.bitField0_ |= 4096;
                                    break;
                                case 290:
                                    this.objcClassPrefix_ = input.readBytes();
                                    this.bitField0_ |= 8192;
                                    break;
                                case 298:
                                    this.csharpNamespace_ = input.readBytes();
                                    this.bitField0_ |= 16384;
                                    break;
                                case MediaError.DetailedErrorCode.HLS_NETWORK_KEY_LOAD /* 314 */:
                                    this.swiftPrefix_ = input.readBytes();
                                    this.bitField0_ |= 32768;
                                    break;
                                case MediaError.DetailedErrorCode.DASH_NO_INIT /* 322 */:
                                    this.phpClassPrefix_ = input.readBytes();
                                    this.bitField0_ |= 65536;
                                    break;
                                case 330:
                                    this.phpNamespace_ = input.readBytes();
                                    this.bitField0_ |= 131072;
                                    break;
                                case 336:
                                    this.phpGenericServices_ = input.readBool();
                                    this.bitField0_ |= 1024;
                                    break;
                                case 354:
                                    this.phpMetadataNamespace_ = input.readBytes();
                                    this.bitField0_ |= 262144;
                                    break;
                                case 362:
                                    this.rubyPackage_ = input.readBytes();
                                    this.bitField0_ |= 524288;
                                    break;
                                case 7994:
                                    UninterpretedOption uninterpretedOption = (UninterpretedOption) input.readMessage(UninterpretedOption.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureUninterpretedOptionIsMutable();
                                        this.uninterpretedOption_.add(uninterpretedOption);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(uninterpretedOption);
                                    }
                                    break;
                                default:
                                    if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                        z = true;
                                    }
                                    break;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            public Builder setJavaMultipleFiles(boolean value) {
                this.javaMultipleFiles_ = value;
                this.bitField0_ |= 4;
                onChanged();
                return this;
            }

            @Deprecated
            public Builder setJavaGenerateEqualsAndHash(boolean value) {
                this.javaGenerateEqualsAndHash_ = value;
                this.bitField0_ |= 8;
                onChanged();
                return this;
            }

            public Builder setJavaStringCheckUtf8(boolean value) {
                this.javaStringCheckUtf8_ = value;
                this.bitField0_ |= 16;
                onChanged();
                return this;
            }

            public Builder setOptimizeFor(OptimizeMode value) {
                value.getClass();
                this.bitField0_ |= 32;
                this.optimizeFor_ = value.getNumber();
                onChanged();
                return this;
            }

            public Builder setCcGenericServices(boolean value) {
                this.ccGenericServices_ = value;
                this.bitField0_ |= 128;
                onChanged();
                return this;
            }

            public Builder setJavaGenericServices(boolean value) {
                this.javaGenericServices_ = value;
                this.bitField0_ |= 256;
                onChanged();
                return this;
            }

            public Builder setPyGenericServices(boolean value) {
                this.pyGenericServices_ = value;
                this.bitField0_ |= 512;
                onChanged();
                return this;
            }

            public Builder setPhpGenericServices(boolean value) {
                this.phpGenericServices_ = value;
                this.bitField0_ |= 1024;
                onChanged();
                return this;
            }

            public Builder setDeprecated(boolean value) {
                this.deprecated_ = value;
                this.bitField0_ |= 2048;
                onChanged();
                return this;
            }

            public Builder setCcEnableArenas(boolean value) {
                this.ccEnableArenas_ = value;
                this.bitField0_ |= 4096;
                onChanged();
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 1048576) == 0) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 1048576;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.FileOptionsOrBuilder
            public int getUninterpretedOptionCount() {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public UninterpretedOption getUninterpretedOption(int index) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return (UninterpretedOption) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3<>(this.uninterpretedOption_, (this.bitField0_ & 1048576) != 0, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static FileOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<FileOptions> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public FileOptions getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class MessageOptions extends GeneratedMessageV3.ExtendableMessage<MessageOptions> implements MessageOptionsOrBuilder {
        private static final MessageOptions DEFAULT_INSTANCE = new MessageOptions();

        @Deprecated
        public static final Parser<MessageOptions> PARSER = new AbstractParser<MessageOptions>() { // from class: com.google.protobuf.DescriptorProtos.MessageOptions.1
            @Override // com.google.protobuf.Parser
            public MessageOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = MessageOptions.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private int bitField0_;
        private boolean deprecatedLegacyJsonFieldConflicts_;
        private boolean deprecated_;
        private boolean mapEntry_;
        private byte memoizedIsInitialized;
        private boolean messageSetWireFormat_;
        private boolean noStandardDescriptorAccessor_;
        private List<UninterpretedOption> uninterpretedOption_;

        static /* synthetic */ int access$17676(MessageOptions messageOptions, int i) {
            int i2 = i | messageOptions.bitField0_;
            messageOptions.bitField0_ = i2;
            return i2;
        }

        private MessageOptions(GeneratedMessageV3.ExtendableBuilder<MessageOptions, ?> builder) {
            super(builder);
            this.messageSetWireFormat_ = false;
            this.noStandardDescriptorAccessor_ = false;
            this.deprecated_ = false;
            this.mapEntry_ = false;
            this.deprecatedLegacyJsonFieldConflicts_ = false;
            this.memoizedIsInitialized = (byte) -1;
        }

        private MessageOptions() {
            this.messageSetWireFormat_ = false;
            this.noStandardDescriptorAccessor_ = false;
            this.deprecated_ = false;
            this.mapEntry_ = false;
            this.deprecatedLegacyJsonFieldConflicts_ = false;
            this.memoizedIsInitialized = (byte) -1;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new MessageOptions();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_MessageOptions_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_MessageOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(MessageOptions.class, Builder.class);
        }

        public boolean hasMessageSetWireFormat() {
            return (this.bitField0_ & 1) != 0;
        }

        public boolean getMessageSetWireFormat() {
            return this.messageSetWireFormat_;
        }

        public boolean hasNoStandardDescriptorAccessor() {
            return (this.bitField0_ & 2) != 0;
        }

        public boolean getNoStandardDescriptorAccessor() {
            return this.noStandardDescriptorAccessor_;
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 4) != 0;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public boolean hasMapEntry() {
            return (this.bitField0_ & 8) != 0;
        }

        public boolean getMapEntry() {
            return this.mapEntry_;
        }

        @Deprecated
        public boolean hasDeprecatedLegacyJsonFieldConflicts() {
            return (this.bitField0_ & 16) != 0;
        }

        @Deprecated
        public boolean getDeprecatedLegacyJsonFieldConflicts() {
            return this.deprecatedLegacyJsonFieldConflicts_;
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override // com.google.protobuf.DescriptorProtos.MessageOptionsOrBuilder
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.ExtendableMessage, com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                if (!getUninterpretedOption(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            GeneratedMessageV3.ExtendableMessage<MessageT>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) != 0) {
                output.writeBool(1, this.messageSetWireFormat_);
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeBool(2, this.noStandardDescriptorAccessor_);
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeBool(3, this.deprecated_);
            }
            if ((this.bitField0_ & 8) != 0) {
                output.writeBool(7, this.mapEntry_);
            }
            if ((this.bitField0_ & 16) != 0) {
                output.writeBool(11, this.deprecatedLegacyJsonFieldConflicts_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriterNewExtensionWriter.writeUntil(536870912, output);
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeBoolSize = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeBoolSize(1, this.messageSetWireFormat_) + 0 : 0;
            if ((this.bitField0_ & 2) != 0) {
                iComputeBoolSize += CodedOutputStream.computeBoolSize(2, this.noStandardDescriptorAccessor_);
            }
            if ((this.bitField0_ & 4) != 0) {
                iComputeBoolSize += CodedOutputStream.computeBoolSize(3, this.deprecated_);
            }
            if ((this.bitField0_ & 8) != 0) {
                iComputeBoolSize += CodedOutputStream.computeBoolSize(7, this.mapEntry_);
            }
            if ((this.bitField0_ & 16) != 0) {
                iComputeBoolSize += CodedOutputStream.computeBoolSize(11, this.deprecatedLegacyJsonFieldConflicts_);
            }
            for (int i2 = 0; i2 < this.uninterpretedOption_.size(); i2++) {
                iComputeBoolSize += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i2));
            }
            int iExtensionsSerializedSize = iComputeBoolSize + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSize = iExtensionsSerializedSize;
            return iExtensionsSerializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MessageOptions)) {
                return super.equals(obj);
            }
            MessageOptions messageOptions = (MessageOptions) obj;
            if (hasMessageSetWireFormat() != messageOptions.hasMessageSetWireFormat()) {
                return false;
            }
            if ((hasMessageSetWireFormat() && getMessageSetWireFormat() != messageOptions.getMessageSetWireFormat()) || hasNoStandardDescriptorAccessor() != messageOptions.hasNoStandardDescriptorAccessor()) {
                return false;
            }
            if ((hasNoStandardDescriptorAccessor() && getNoStandardDescriptorAccessor() != messageOptions.getNoStandardDescriptorAccessor()) || hasDeprecated() != messageOptions.hasDeprecated()) {
                return false;
            }
            if ((hasDeprecated() && getDeprecated() != messageOptions.getDeprecated()) || hasMapEntry() != messageOptions.hasMapEntry()) {
                return false;
            }
            if ((!hasMapEntry() || getMapEntry() == messageOptions.getMapEntry()) && hasDeprecatedLegacyJsonFieldConflicts() == messageOptions.hasDeprecatedLegacyJsonFieldConflicts()) {
                return (!hasDeprecatedLegacyJsonFieldConflicts() || getDeprecatedLegacyJsonFieldConflicts() == messageOptions.getDeprecatedLegacyJsonFieldConflicts()) && getUninterpretedOptionList().equals(messageOptions.getUninterpretedOptionList()) && getUnknownFields().equals(messageOptions.getUnknownFields()) && getExtensionFields().equals(messageOptions.getExtensionFields());
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasMessageSetWireFormat()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + Internal.hashBoolean(getMessageSetWireFormat());
            }
            if (hasNoStandardDescriptorAccessor()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + Internal.hashBoolean(getNoStandardDescriptorAccessor());
            }
            if (hasDeprecated()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + Internal.hashBoolean(getDeprecated());
            }
            if (hasMapEntry()) {
                iHashCode = (((iHashCode * 37) + 7) * 53) + Internal.hashBoolean(getMapEntry());
            }
            if (hasDeprecatedLegacyJsonFieldConflicts()) {
                iHashCode = (((iHashCode * 37) + 11) * 53) + Internal.hashBoolean(getDeprecatedLegacyJsonFieldConflicts());
            }
            if (getUninterpretedOptionCount() > 0) {
                iHashCode = (((iHashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
            }
            int iHashFields = (AbstractMessage.hashFields(iHashCode, getExtensionFields()) * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashFields;
            return iHashFields;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.ExtendableBuilder<MessageOptions, Builder> implements MessageOptionsOrBuilder {
            private int bitField0_;
            private boolean deprecatedLegacyJsonFieldConflicts_;
            private boolean deprecated_;
            private boolean mapEntry_;
            private boolean messageSetWireFormat_;
            private boolean noStandardDescriptorAccessor_;
            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
            private List<UninterpretedOption> uninterpretedOption_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_MessageOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(MessageOptions.class, Builder.class);
            }

            private Builder() {
                this.uninterpretedOption_ = Collections.emptyList();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.uninterpretedOption_ = Collections.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_MessageOptions_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageOptions getDefaultInstanceForType() {
                return MessageOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageOptions build() {
                MessageOptions messageOptionsBuildPartial = buildPartial();
                if (messageOptionsBuildPartial.isInitialized()) {
                    return messageOptionsBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) messageOptionsBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageOptions buildPartial() {
                MessageOptions messageOptions = new MessageOptions(this);
                buildPartialRepeatedFields(messageOptions);
                if (this.bitField0_ != 0) {
                    buildPartial0(messageOptions);
                }
                onBuilt();
                return messageOptions;
            }

            private void buildPartialRepeatedFields(MessageOptions result) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.uninterpretedOption_ = repeatedFieldBuilderV3.build();
                    return;
                }
                if ((this.bitField0_ & 32) != 0) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    this.bitField0_ &= -33;
                }
                result.uninterpretedOption_ = this.uninterpretedOption_;
            }

            private void buildPartial0(MessageOptions result) {
                int i;
                int i2 = this.bitField0_;
                if ((i2 & 1) != 0) {
                    result.messageSetWireFormat_ = this.messageSetWireFormat_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    result.noStandardDescriptorAccessor_ = this.noStandardDescriptorAccessor_;
                    i |= 2;
                }
                if ((i2 & 4) != 0) {
                    result.deprecated_ = this.deprecated_;
                    i |= 4;
                }
                if ((i2 & 8) != 0) {
                    result.mapEntry_ = this.mapEntry_;
                    i |= 8;
                }
                if ((i2 & 16) != 0) {
                    result.deprecatedLegacyJsonFieldConflicts_ = this.deprecatedLegacyJsonFieldConflicts_;
                    i |= 16;
                }
                MessageOptions.access$17676(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof MessageOptions) {
                    return mergeFrom((MessageOptions) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(MessageOptions other) {
                if (other == MessageOptions.getDefaultInstance()) {
                    return this;
                }
                if (other.hasMessageSetWireFormat()) {
                    setMessageSetWireFormat(other.getMessageSetWireFormat());
                }
                if (other.hasNoStandardDescriptorAccessor()) {
                    setNoStandardDescriptorAccessor(other.getNoStandardDescriptorAccessor());
                }
                if (other.hasDeprecated()) {
                    setDeprecated(other.getDeprecated());
                }
                if (other.hasMapEntry()) {
                    setMapEntry(other.getMapEntry());
                }
                if (other.hasDeprecatedLegacyJsonFieldConflicts()) {
                    setDeprecatedLegacyJsonFieldConflicts(other.getDeprecatedLegacyJsonFieldConflicts());
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= -33;
                        } else {
                            ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (!this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    } else {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= -33;
                        this.uninterpretedOptionBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
                    }
                }
                mergeExtensionFields(other);
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.ExtendableBuilder, com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag != 0) {
                                if (tag == 8) {
                                    this.messageSetWireFormat_ = input.readBool();
                                    this.bitField0_ |= 1;
                                } else if (tag == 16) {
                                    this.noStandardDescriptorAccessor_ = input.readBool();
                                    this.bitField0_ |= 2;
                                } else if (tag == 24) {
                                    this.deprecated_ = input.readBool();
                                    this.bitField0_ |= 4;
                                } else if (tag == 56) {
                                    this.mapEntry_ = input.readBool();
                                    this.bitField0_ |= 8;
                                } else if (tag == 88) {
                                    this.deprecatedLegacyJsonFieldConflicts_ = input.readBool();
                                    this.bitField0_ |= 16;
                                } else if (tag == 7994) {
                                    UninterpretedOption uninterpretedOption = (UninterpretedOption) input.readMessage(UninterpretedOption.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureUninterpretedOptionIsMutable();
                                        this.uninterpretedOption_.add(uninterpretedOption);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(uninterpretedOption);
                                    }
                                } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            public Builder setMessageSetWireFormat(boolean value) {
                this.messageSetWireFormat_ = value;
                this.bitField0_ |= 1;
                onChanged();
                return this;
            }

            public Builder setNoStandardDescriptorAccessor(boolean value) {
                this.noStandardDescriptorAccessor_ = value;
                this.bitField0_ |= 2;
                onChanged();
                return this;
            }

            public Builder setDeprecated(boolean value) {
                this.deprecated_ = value;
                this.bitField0_ |= 4;
                onChanged();
                return this;
            }

            public Builder setMapEntry(boolean value) {
                this.mapEntry_ = value;
                this.bitField0_ |= 8;
                onChanged();
                return this;
            }

            @Deprecated
            public Builder setDeprecatedLegacyJsonFieldConflicts(boolean value) {
                this.deprecatedLegacyJsonFieldConflicts_ = value;
                this.bitField0_ |= 16;
                onChanged();
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 32) == 0) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 32;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.MessageOptionsOrBuilder
            public int getUninterpretedOptionCount() {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public UninterpretedOption getUninterpretedOption(int index) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return (UninterpretedOption) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3<>(this.uninterpretedOption_, (this.bitField0_ & 32) != 0, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static MessageOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<MessageOptions> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageOptions getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class FieldOptions extends GeneratedMessageV3.ExtendableMessage<FieldOptions> implements FieldOptionsOrBuilder {
        private static final FieldOptions DEFAULT_INSTANCE = new FieldOptions();

        @Deprecated
        public static final Parser<FieldOptions> PARSER = new AbstractParser<FieldOptions>() { // from class: com.google.protobuf.DescriptorProtos.FieldOptions.1
            @Override // com.google.protobuf.Parser
            public FieldOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = FieldOptions.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private int bitField0_;
        private int ctype_;
        private boolean debugRedact_;
        private boolean deprecated_;
        private int jstype_;
        private boolean lazy_;
        private byte memoizedIsInitialized;
        private boolean packed_;
        private int retention_;
        private int target_;
        private List<UninterpretedOption> uninterpretedOption_;
        private boolean unverifiedLazy_;
        private boolean weak_;

        static /* synthetic */ int access$19376(FieldOptions fieldOptions, int i) {
            int i2 = i | fieldOptions.bitField0_;
            fieldOptions.bitField0_ = i2;
            return i2;
        }

        private FieldOptions(GeneratedMessageV3.ExtendableBuilder<FieldOptions, ?> builder) {
            super(builder);
            this.ctype_ = 0;
            this.packed_ = false;
            this.jstype_ = 0;
            this.lazy_ = false;
            this.unverifiedLazy_ = false;
            this.deprecated_ = false;
            this.weak_ = false;
            this.debugRedact_ = false;
            this.retention_ = 0;
            this.target_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private FieldOptions() {
            this.packed_ = false;
            this.lazy_ = false;
            this.unverifiedLazy_ = false;
            this.deprecated_ = false;
            this.weak_ = false;
            this.debugRedact_ = false;
            this.memoizedIsInitialized = (byte) -1;
            this.ctype_ = 0;
            this.jstype_ = 0;
            this.retention_ = 0;
            this.target_ = 0;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new FieldOptions();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_FieldOptions_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_FieldOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldOptions.class, Builder.class);
        }

        public enum CType implements ProtocolMessageEnum {
            STRING(0),
            CORD(1),
            STRING_PIECE(2);

            public static final int CORD_VALUE = 1;
            public static final int STRING_PIECE_VALUE = 2;
            public static final int STRING_VALUE = 0;
            private final int value;
            private static final Internal.EnumLiteMap<CType> internalValueMap = new Internal.EnumLiteMap<CType>() { // from class: com.google.protobuf.DescriptorProtos.FieldOptions.CType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public CType findValueByNumber(int number) {
                    return CType.forNumber(number);
                }
            };
            private static final CType[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static CType valueOf(int value) {
                return forNumber(value);
            }

            public static CType forNumber(int value) {
                if (value == 0) {
                    return STRING;
                }
                if (value == 1) {
                    return CORD;
                }
                if (value != 2) {
                    return null;
                }
                return STRING_PIECE;
            }

            public static Internal.EnumLiteMap<CType> internalGetValueMap() {
                return internalValueMap;
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FieldOptions.getDescriptor().getEnumTypes().get(0);
            }

            public static CType valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            CType(int value) {
                this.value = value;
            }
        }

        public enum JSType implements ProtocolMessageEnum {
            JS_NORMAL(0),
            JS_STRING(1),
            JS_NUMBER(2);

            public static final int JS_NORMAL_VALUE = 0;
            public static final int JS_NUMBER_VALUE = 2;
            public static final int JS_STRING_VALUE = 1;
            private final int value;
            private static final Internal.EnumLiteMap<JSType> internalValueMap = new Internal.EnumLiteMap<JSType>() { // from class: com.google.protobuf.DescriptorProtos.FieldOptions.JSType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public JSType findValueByNumber(int number) {
                    return JSType.forNumber(number);
                }
            };
            private static final JSType[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static JSType valueOf(int value) {
                return forNumber(value);
            }

            public static JSType forNumber(int value) {
                if (value == 0) {
                    return JS_NORMAL;
                }
                if (value == 1) {
                    return JS_STRING;
                }
                if (value != 2) {
                    return null;
                }
                return JS_NUMBER;
            }

            public static Internal.EnumLiteMap<JSType> internalGetValueMap() {
                return internalValueMap;
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FieldOptions.getDescriptor().getEnumTypes().get(1);
            }

            public static JSType valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            JSType(int value) {
                this.value = value;
            }
        }

        public enum OptionRetention implements ProtocolMessageEnum {
            RETENTION_UNKNOWN(0),
            RETENTION_RUNTIME(1),
            RETENTION_SOURCE(2);

            public static final int RETENTION_RUNTIME_VALUE = 1;
            public static final int RETENTION_SOURCE_VALUE = 2;
            public static final int RETENTION_UNKNOWN_VALUE = 0;
            private final int value;
            private static final Internal.EnumLiteMap<OptionRetention> internalValueMap = new Internal.EnumLiteMap<OptionRetention>() { // from class: com.google.protobuf.DescriptorProtos.FieldOptions.OptionRetention.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public OptionRetention findValueByNumber(int number) {
                    return OptionRetention.forNumber(number);
                }
            };
            private static final OptionRetention[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static OptionRetention valueOf(int value) {
                return forNumber(value);
            }

            public static OptionRetention forNumber(int value) {
                if (value == 0) {
                    return RETENTION_UNKNOWN;
                }
                if (value == 1) {
                    return RETENTION_RUNTIME;
                }
                if (value != 2) {
                    return null;
                }
                return RETENTION_SOURCE;
            }

            public static Internal.EnumLiteMap<OptionRetention> internalGetValueMap() {
                return internalValueMap;
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FieldOptions.getDescriptor().getEnumTypes().get(2);
            }

            public static OptionRetention valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            OptionRetention(int value) {
                this.value = value;
            }
        }

        public enum OptionTargetType implements ProtocolMessageEnum {
            TARGET_TYPE_UNKNOWN(0),
            TARGET_TYPE_FILE(1),
            TARGET_TYPE_EXTENSION_RANGE(2),
            TARGET_TYPE_MESSAGE(3),
            TARGET_TYPE_FIELD(4),
            TARGET_TYPE_ONEOF(5),
            TARGET_TYPE_ENUM(6),
            TARGET_TYPE_ENUM_ENTRY(7),
            TARGET_TYPE_SERVICE(8),
            TARGET_TYPE_METHOD(9);

            public static final int TARGET_TYPE_ENUM_ENTRY_VALUE = 7;
            public static final int TARGET_TYPE_ENUM_VALUE = 6;
            public static final int TARGET_TYPE_EXTENSION_RANGE_VALUE = 2;
            public static final int TARGET_TYPE_FIELD_VALUE = 4;
            public static final int TARGET_TYPE_FILE_VALUE = 1;
            public static final int TARGET_TYPE_MESSAGE_VALUE = 3;
            public static final int TARGET_TYPE_METHOD_VALUE = 9;
            public static final int TARGET_TYPE_ONEOF_VALUE = 5;
            public static final int TARGET_TYPE_SERVICE_VALUE = 8;
            public static final int TARGET_TYPE_UNKNOWN_VALUE = 0;
            private final int value;
            private static final Internal.EnumLiteMap<OptionTargetType> internalValueMap = new Internal.EnumLiteMap<OptionTargetType>() { // from class: com.google.protobuf.DescriptorProtos.FieldOptions.OptionTargetType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public OptionTargetType findValueByNumber(int number) {
                    return OptionTargetType.forNumber(number);
                }
            };
            private static final OptionTargetType[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static OptionTargetType valueOf(int value) {
                return forNumber(value);
            }

            public static OptionTargetType forNumber(int value) {
                switch (value) {
                    case 0:
                        return TARGET_TYPE_UNKNOWN;
                    case 1:
                        return TARGET_TYPE_FILE;
                    case 2:
                        return TARGET_TYPE_EXTENSION_RANGE;
                    case 3:
                        return TARGET_TYPE_MESSAGE;
                    case 4:
                        return TARGET_TYPE_FIELD;
                    case 5:
                        return TARGET_TYPE_ONEOF;
                    case 6:
                        return TARGET_TYPE_ENUM;
                    case 7:
                        return TARGET_TYPE_ENUM_ENTRY;
                    case 8:
                        return TARGET_TYPE_SERVICE;
                    case 9:
                        return TARGET_TYPE_METHOD;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<OptionTargetType> internalGetValueMap() {
                return internalValueMap;
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FieldOptions.getDescriptor().getEnumTypes().get(3);
            }

            public static OptionTargetType valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            OptionTargetType(int value) {
                this.value = value;
            }
        }

        public boolean hasCtype() {
            return (this.bitField0_ & 1) != 0;
        }

        public CType getCtype() {
            CType cTypeForNumber = CType.forNumber(this.ctype_);
            return cTypeForNumber == null ? CType.STRING : cTypeForNumber;
        }

        public boolean hasPacked() {
            return (this.bitField0_ & 2) != 0;
        }

        public boolean getPacked() {
            return this.packed_;
        }

        public boolean hasJstype() {
            return (this.bitField0_ & 4) != 0;
        }

        public JSType getJstype() {
            JSType jSTypeForNumber = JSType.forNumber(this.jstype_);
            return jSTypeForNumber == null ? JSType.JS_NORMAL : jSTypeForNumber;
        }

        public boolean hasLazy() {
            return (this.bitField0_ & 8) != 0;
        }

        public boolean getLazy() {
            return this.lazy_;
        }

        public boolean hasUnverifiedLazy() {
            return (this.bitField0_ & 16) != 0;
        }

        public boolean getUnverifiedLazy() {
            return this.unverifiedLazy_;
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 32) != 0;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public boolean hasWeak() {
            return (this.bitField0_ & 64) != 0;
        }

        public boolean getWeak() {
            return this.weak_;
        }

        public boolean hasDebugRedact() {
            return (this.bitField0_ & 128) != 0;
        }

        public boolean getDebugRedact() {
            return this.debugRedact_;
        }

        public boolean hasRetention() {
            return (this.bitField0_ & 256) != 0;
        }

        public OptionRetention getRetention() {
            OptionRetention optionRetentionForNumber = OptionRetention.forNumber(this.retention_);
            return optionRetentionForNumber == null ? OptionRetention.RETENTION_UNKNOWN : optionRetentionForNumber;
        }

        public boolean hasTarget() {
            return (this.bitField0_ & 512) != 0;
        }

        public OptionTargetType getTarget() {
            OptionTargetType optionTargetTypeForNumber = OptionTargetType.forNumber(this.target_);
            return optionTargetTypeForNumber == null ? OptionTargetType.TARGET_TYPE_UNKNOWN : optionTargetTypeForNumber;
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override // com.google.protobuf.DescriptorProtos.FieldOptionsOrBuilder
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.ExtendableMessage, com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                if (!getUninterpretedOption(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            GeneratedMessageV3.ExtendableMessage<MessageT>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) != 0) {
                output.writeEnum(1, this.ctype_);
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeBool(2, this.packed_);
            }
            if ((this.bitField0_ & 32) != 0) {
                output.writeBool(3, this.deprecated_);
            }
            if ((this.bitField0_ & 8) != 0) {
                output.writeBool(5, this.lazy_);
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeEnum(6, this.jstype_);
            }
            if ((this.bitField0_ & 64) != 0) {
                output.writeBool(10, this.weak_);
            }
            if ((this.bitField0_ & 16) != 0) {
                output.writeBool(15, this.unverifiedLazy_);
            }
            if ((this.bitField0_ & 128) != 0) {
                output.writeBool(16, this.debugRedact_);
            }
            if ((this.bitField0_ & 256) != 0) {
                output.writeEnum(17, this.retention_);
            }
            if ((this.bitField0_ & 512) != 0) {
                output.writeEnum(18, this.target_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriterNewExtensionWriter.writeUntil(536870912, output);
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeEnumSize = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeEnumSize(1, this.ctype_) + 0 : 0;
            if ((this.bitField0_ & 2) != 0) {
                iComputeEnumSize += CodedOutputStream.computeBoolSize(2, this.packed_);
            }
            if ((this.bitField0_ & 32) != 0) {
                iComputeEnumSize += CodedOutputStream.computeBoolSize(3, this.deprecated_);
            }
            if ((this.bitField0_ & 8) != 0) {
                iComputeEnumSize += CodedOutputStream.computeBoolSize(5, this.lazy_);
            }
            if ((this.bitField0_ & 4) != 0) {
                iComputeEnumSize += CodedOutputStream.computeEnumSize(6, this.jstype_);
            }
            if ((this.bitField0_ & 64) != 0) {
                iComputeEnumSize += CodedOutputStream.computeBoolSize(10, this.weak_);
            }
            if ((this.bitField0_ & 16) != 0) {
                iComputeEnumSize += CodedOutputStream.computeBoolSize(15, this.unverifiedLazy_);
            }
            if ((this.bitField0_ & 128) != 0) {
                iComputeEnumSize += CodedOutputStream.computeBoolSize(16, this.debugRedact_);
            }
            if ((this.bitField0_ & 256) != 0) {
                iComputeEnumSize += CodedOutputStream.computeEnumSize(17, this.retention_);
            }
            if ((this.bitField0_ & 512) != 0) {
                iComputeEnumSize += CodedOutputStream.computeEnumSize(18, this.target_);
            }
            for (int i2 = 0; i2 < this.uninterpretedOption_.size(); i2++) {
                iComputeEnumSize += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i2));
            }
            int iExtensionsSerializedSize = iComputeEnumSize + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSize = iExtensionsSerializedSize;
            return iExtensionsSerializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FieldOptions)) {
                return super.equals(obj);
            }
            FieldOptions fieldOptions = (FieldOptions) obj;
            if (hasCtype() != fieldOptions.hasCtype()) {
                return false;
            }
            if ((hasCtype() && this.ctype_ != fieldOptions.ctype_) || hasPacked() != fieldOptions.hasPacked()) {
                return false;
            }
            if ((hasPacked() && getPacked() != fieldOptions.getPacked()) || hasJstype() != fieldOptions.hasJstype()) {
                return false;
            }
            if ((hasJstype() && this.jstype_ != fieldOptions.jstype_) || hasLazy() != fieldOptions.hasLazy()) {
                return false;
            }
            if ((hasLazy() && getLazy() != fieldOptions.getLazy()) || hasUnverifiedLazy() != fieldOptions.hasUnverifiedLazy()) {
                return false;
            }
            if ((hasUnverifiedLazy() && getUnverifiedLazy() != fieldOptions.getUnverifiedLazy()) || hasDeprecated() != fieldOptions.hasDeprecated()) {
                return false;
            }
            if ((hasDeprecated() && getDeprecated() != fieldOptions.getDeprecated()) || hasWeak() != fieldOptions.hasWeak()) {
                return false;
            }
            if ((hasWeak() && getWeak() != fieldOptions.getWeak()) || hasDebugRedact() != fieldOptions.hasDebugRedact()) {
                return false;
            }
            if ((hasDebugRedact() && getDebugRedact() != fieldOptions.getDebugRedact()) || hasRetention() != fieldOptions.hasRetention()) {
                return false;
            }
            if ((!hasRetention() || this.retention_ == fieldOptions.retention_) && hasTarget() == fieldOptions.hasTarget()) {
                return (!hasTarget() || this.target_ == fieldOptions.target_) && getUninterpretedOptionList().equals(fieldOptions.getUninterpretedOptionList()) && getUnknownFields().equals(fieldOptions.getUnknownFields()) && getExtensionFields().equals(fieldOptions.getExtensionFields());
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasCtype()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + this.ctype_;
            }
            if (hasPacked()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + Internal.hashBoolean(getPacked());
            }
            if (hasJstype()) {
                iHashCode = (((iHashCode * 37) + 6) * 53) + this.jstype_;
            }
            if (hasLazy()) {
                iHashCode = (((iHashCode * 37) + 5) * 53) + Internal.hashBoolean(getLazy());
            }
            if (hasUnverifiedLazy()) {
                iHashCode = (((iHashCode * 37) + 15) * 53) + Internal.hashBoolean(getUnverifiedLazy());
            }
            if (hasDeprecated()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + Internal.hashBoolean(getDeprecated());
            }
            if (hasWeak()) {
                iHashCode = (((iHashCode * 37) + 10) * 53) + Internal.hashBoolean(getWeak());
            }
            if (hasDebugRedact()) {
                iHashCode = (((iHashCode * 37) + 16) * 53) + Internal.hashBoolean(getDebugRedact());
            }
            if (hasRetention()) {
                iHashCode = (((iHashCode * 37) + 17) * 53) + this.retention_;
            }
            if (hasTarget()) {
                iHashCode = (((iHashCode * 37) + 18) * 53) + this.target_;
            }
            if (getUninterpretedOptionCount() > 0) {
                iHashCode = (((iHashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
            }
            int iHashFields = (AbstractMessage.hashFields(iHashCode, getExtensionFields()) * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashFields;
            return iHashFields;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.ExtendableBuilder<FieldOptions, Builder> implements FieldOptionsOrBuilder {
            private int bitField0_;
            private int ctype_;
            private boolean debugRedact_;
            private boolean deprecated_;
            private int jstype_;
            private boolean lazy_;
            private boolean packed_;
            private int retention_;
            private int target_;
            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
            private List<UninterpretedOption> uninterpretedOption_;
            private boolean unverifiedLazy_;
            private boolean weak_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_FieldOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldOptions.class, Builder.class);
            }

            private Builder() {
                this.ctype_ = 0;
                this.jstype_ = 0;
                this.retention_ = 0;
                this.target_ = 0;
                this.uninterpretedOption_ = Collections.emptyList();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.ctype_ = 0;
                this.jstype_ = 0;
                this.retention_ = 0;
                this.target_ = 0;
                this.uninterpretedOption_ = Collections.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_FieldOptions_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public FieldOptions getDefaultInstanceForType() {
                return FieldOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FieldOptions build() {
                FieldOptions fieldOptionsBuildPartial = buildPartial();
                if (fieldOptionsBuildPartial.isInitialized()) {
                    return fieldOptionsBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) fieldOptionsBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FieldOptions buildPartial() {
                FieldOptions fieldOptions = new FieldOptions(this);
                buildPartialRepeatedFields(fieldOptions);
                if (this.bitField0_ != 0) {
                    buildPartial0(fieldOptions);
                }
                onBuilt();
                return fieldOptions;
            }

            private void buildPartialRepeatedFields(FieldOptions result) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.uninterpretedOption_ = repeatedFieldBuilderV3.build();
                    return;
                }
                if ((this.bitField0_ & 1024) != 0) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    this.bitField0_ &= -1025;
                }
                result.uninterpretedOption_ = this.uninterpretedOption_;
            }

            private void buildPartial0(FieldOptions result) {
                int i;
                int i2 = this.bitField0_;
                if ((i2 & 1) != 0) {
                    result.ctype_ = this.ctype_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    result.packed_ = this.packed_;
                    i |= 2;
                }
                if ((i2 & 4) != 0) {
                    result.jstype_ = this.jstype_;
                    i |= 4;
                }
                if ((i2 & 8) != 0) {
                    result.lazy_ = this.lazy_;
                    i |= 8;
                }
                if ((i2 & 16) != 0) {
                    result.unverifiedLazy_ = this.unverifiedLazy_;
                    i |= 16;
                }
                if ((i2 & 32) != 0) {
                    result.deprecated_ = this.deprecated_;
                    i |= 32;
                }
                if ((i2 & 64) != 0) {
                    result.weak_ = this.weak_;
                    i |= 64;
                }
                if ((i2 & 128) != 0) {
                    result.debugRedact_ = this.debugRedact_;
                    i |= 128;
                }
                if ((i2 & 256) != 0) {
                    result.retention_ = this.retention_;
                    i |= 256;
                }
                if ((i2 & 512) != 0) {
                    result.target_ = this.target_;
                    i |= 512;
                }
                FieldOptions.access$19376(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof FieldOptions) {
                    return mergeFrom((FieldOptions) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FieldOptions other) {
                if (other == FieldOptions.getDefaultInstance()) {
                    return this;
                }
                if (other.hasCtype()) {
                    setCtype(other.getCtype());
                }
                if (other.hasPacked()) {
                    setPacked(other.getPacked());
                }
                if (other.hasJstype()) {
                    setJstype(other.getJstype());
                }
                if (other.hasLazy()) {
                    setLazy(other.getLazy());
                }
                if (other.hasUnverifiedLazy()) {
                    setUnverifiedLazy(other.getUnverifiedLazy());
                }
                if (other.hasDeprecated()) {
                    setDeprecated(other.getDeprecated());
                }
                if (other.hasWeak()) {
                    setWeak(other.getWeak());
                }
                if (other.hasDebugRedact()) {
                    setDebugRedact(other.getDebugRedact());
                }
                if (other.hasRetention()) {
                    setRetention(other.getRetention());
                }
                if (other.hasTarget()) {
                    setTarget(other.getTarget());
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= -1025;
                        } else {
                            ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (!this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    } else {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= -1025;
                        this.uninterpretedOptionBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
                    }
                }
                mergeExtensionFields(other);
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.ExtendableBuilder, com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    z = true;
                                    break;
                                case 8:
                                    int i = input.readEnum();
                                    if (CType.forNumber(i) == null) {
                                        mergeUnknownVarintField(1, i);
                                    } else {
                                        this.ctype_ = i;
                                        this.bitField0_ |= 1;
                                    }
                                    break;
                                case 16:
                                    this.packed_ = input.readBool();
                                    this.bitField0_ |= 2;
                                    break;
                                case 24:
                                    this.deprecated_ = input.readBool();
                                    this.bitField0_ |= 32;
                                    break;
                                case 40:
                                    this.lazy_ = input.readBool();
                                    this.bitField0_ |= 8;
                                    break;
                                case 48:
                                    int i2 = input.readEnum();
                                    if (JSType.forNumber(i2) == null) {
                                        mergeUnknownVarintField(6, i2);
                                    } else {
                                        this.jstype_ = i2;
                                        this.bitField0_ |= 4;
                                    }
                                    break;
                                case 80:
                                    this.weak_ = input.readBool();
                                    this.bitField0_ |= 64;
                                    break;
                                case 120:
                                    this.unverifiedLazy_ = input.readBool();
                                    this.bitField0_ |= 16;
                                    break;
                                case 128:
                                    this.debugRedact_ = input.readBool();
                                    this.bitField0_ |= 128;
                                    break;
                                case 136:
                                    int i3 = input.readEnum();
                                    if (OptionRetention.forNumber(i3) == null) {
                                        mergeUnknownVarintField(17, i3);
                                    } else {
                                        this.retention_ = i3;
                                        this.bitField0_ |= 256;
                                    }
                                    break;
                                case voOSType.VOOSMP_PID_ANALYTICS_AGENT /* 144 */:
                                    int i4 = input.readEnum();
                                    if (OptionTargetType.forNumber(i4) == null) {
                                        mergeUnknownVarintField(18, i4);
                                    } else {
                                        this.target_ = i4;
                                        this.bitField0_ |= 512;
                                    }
                                    break;
                                case 7994:
                                    UninterpretedOption uninterpretedOption = (UninterpretedOption) input.readMessage(UninterpretedOption.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureUninterpretedOptionIsMutable();
                                        this.uninterpretedOption_.add(uninterpretedOption);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(uninterpretedOption);
                                    }
                                    break;
                                default:
                                    if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                        z = true;
                                    }
                                    break;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            public Builder setCtype(CType value) {
                value.getClass();
                this.bitField0_ |= 1;
                this.ctype_ = value.getNumber();
                onChanged();
                return this;
            }

            public Builder setPacked(boolean value) {
                this.packed_ = value;
                this.bitField0_ |= 2;
                onChanged();
                return this;
            }

            public Builder setJstype(JSType value) {
                value.getClass();
                this.bitField0_ |= 4;
                this.jstype_ = value.getNumber();
                onChanged();
                return this;
            }

            public Builder setLazy(boolean value) {
                this.lazy_ = value;
                this.bitField0_ |= 8;
                onChanged();
                return this;
            }

            public Builder setUnverifiedLazy(boolean value) {
                this.unverifiedLazy_ = value;
                this.bitField0_ |= 16;
                onChanged();
                return this;
            }

            public Builder setDeprecated(boolean value) {
                this.deprecated_ = value;
                this.bitField0_ |= 32;
                onChanged();
                return this;
            }

            public Builder setWeak(boolean value) {
                this.weak_ = value;
                this.bitField0_ |= 64;
                onChanged();
                return this;
            }

            public Builder setDebugRedact(boolean value) {
                this.debugRedact_ = value;
                this.bitField0_ |= 128;
                onChanged();
                return this;
            }

            public Builder setRetention(OptionRetention value) {
                value.getClass();
                this.bitField0_ |= 256;
                this.retention_ = value.getNumber();
                onChanged();
                return this;
            }

            public Builder setTarget(OptionTargetType value) {
                value.getClass();
                this.bitField0_ |= 512;
                this.target_ = value.getNumber();
                onChanged();
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 1024) == 0) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 1024;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.FieldOptionsOrBuilder
            public int getUninterpretedOptionCount() {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public UninterpretedOption getUninterpretedOption(int index) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return (UninterpretedOption) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3<>(this.uninterpretedOption_, (this.bitField0_ & 1024) != 0, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static FieldOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<FieldOptions> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public FieldOptions getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class OneofOptions extends GeneratedMessageV3.ExtendableMessage<OneofOptions> implements OneofOptionsOrBuilder {
        private static final OneofOptions DEFAULT_INSTANCE = new OneofOptions();

        @Deprecated
        public static final Parser<OneofOptions> PARSER = new AbstractParser<OneofOptions>() { // from class: com.google.protobuf.DescriptorProtos.OneofOptions.1
            @Override // com.google.protobuf.Parser
            public OneofOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = OneofOptions.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private byte memoizedIsInitialized;
        private List<UninterpretedOption> uninterpretedOption_;

        private OneofOptions(GeneratedMessageV3.ExtendableBuilder<OneofOptions, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private OneofOptions() {
            this.memoizedIsInitialized = (byte) -1;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new OneofOptions();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_OneofOptions_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_OneofOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(OneofOptions.class, Builder.class);
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override // com.google.protobuf.DescriptorProtos.OneofOptionsOrBuilder
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.ExtendableMessage, com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                if (!getUninterpretedOption(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            GeneratedMessageV3.ExtendableMessage<MessageT>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriterNewExtensionWriter.writeUntil(536870912, output);
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = 0;
            for (int i2 = 0; i2 < this.uninterpretedOption_.size(); i2++) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i2));
            }
            int iExtensionsSerializedSize = iComputeMessageSize + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSize = iExtensionsSerializedSize;
            return iExtensionsSerializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof OneofOptions)) {
                return super.equals(obj);
            }
            OneofOptions oneofOptions = (OneofOptions) obj;
            return getUninterpretedOptionList().equals(oneofOptions.getUninterpretedOptionList()) && getUnknownFields().equals(oneofOptions.getUnknownFields()) && getExtensionFields().equals(oneofOptions.getExtensionFields());
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (getUninterpretedOptionCount() > 0) {
                iHashCode = (((iHashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
            }
            int iHashFields = (AbstractMessage.hashFields(iHashCode, getExtensionFields()) * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashFields;
            return iHashFields;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.ExtendableBuilder<OneofOptions, Builder> implements OneofOptionsOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
            private List<UninterpretedOption> uninterpretedOption_;

            private void buildPartial0(OneofOptions result) {
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_OneofOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(OneofOptions.class, Builder.class);
            }

            private Builder() {
                this.uninterpretedOption_ = Collections.emptyList();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.uninterpretedOption_ = Collections.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_OneofOptions_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public OneofOptions getDefaultInstanceForType() {
                return OneofOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public OneofOptions build() {
                OneofOptions oneofOptionsBuildPartial = buildPartial();
                if (oneofOptionsBuildPartial.isInitialized()) {
                    return oneofOptionsBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) oneofOptionsBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public OneofOptions buildPartial() {
                OneofOptions oneofOptions = new OneofOptions(this);
                buildPartialRepeatedFields(oneofOptions);
                if (this.bitField0_ != 0) {
                    buildPartial0(oneofOptions);
                }
                onBuilt();
                return oneofOptions;
            }

            private void buildPartialRepeatedFields(OneofOptions result) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.uninterpretedOption_ = repeatedFieldBuilderV3.build();
                    return;
                }
                if ((this.bitField0_ & 1) != 0) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    this.bitField0_ &= -2;
                }
                result.uninterpretedOption_ = this.uninterpretedOption_;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof OneofOptions) {
                    return mergeFrom((OneofOptions) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(OneofOptions other) {
                if (other == OneofOptions.getDefaultInstance()) {
                    return this;
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (!this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    } else {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= -2;
                        this.uninterpretedOptionBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
                    }
                }
                mergeExtensionFields(other);
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.ExtendableBuilder, com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag != 0) {
                                if (tag == 7994) {
                                    UninterpretedOption uninterpretedOption = (UninterpretedOption) input.readMessage(UninterpretedOption.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureUninterpretedOptionIsMutable();
                                        this.uninterpretedOption_.add(uninterpretedOption);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(uninterpretedOption);
                                    }
                                } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.OneofOptionsOrBuilder
            public int getUninterpretedOptionCount() {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public UninterpretedOption getUninterpretedOption(int index) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return (UninterpretedOption) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3<>(this.uninterpretedOption_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static OneofOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<OneofOptions> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public OneofOptions getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class EnumOptions extends GeneratedMessageV3.ExtendableMessage<EnumOptions> implements EnumOptionsOrBuilder {
        private static final EnumOptions DEFAULT_INSTANCE = new EnumOptions();

        @Deprecated
        public static final Parser<EnumOptions> PARSER = new AbstractParser<EnumOptions>() { // from class: com.google.protobuf.DescriptorProtos.EnumOptions.1
            @Override // com.google.protobuf.Parser
            public EnumOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = EnumOptions.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private boolean allowAlias_;
        private int bitField0_;
        private boolean deprecatedLegacyJsonFieldConflicts_;
        private boolean deprecated_;
        private byte memoizedIsInitialized;
        private List<UninterpretedOption> uninterpretedOption_;

        static /* synthetic */ int access$20976(EnumOptions enumOptions, int i) {
            int i2 = i | enumOptions.bitField0_;
            enumOptions.bitField0_ = i2;
            return i2;
        }

        private EnumOptions(GeneratedMessageV3.ExtendableBuilder<EnumOptions, ?> builder) {
            super(builder);
            this.allowAlias_ = false;
            this.deprecated_ = false;
            this.deprecatedLegacyJsonFieldConflicts_ = false;
            this.memoizedIsInitialized = (byte) -1;
        }

        private EnumOptions() {
            this.allowAlias_ = false;
            this.deprecated_ = false;
            this.deprecatedLegacyJsonFieldConflicts_ = false;
            this.memoizedIsInitialized = (byte) -1;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new EnumOptions();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_EnumOptions_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_EnumOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumOptions.class, Builder.class);
        }

        public boolean hasAllowAlias() {
            return (this.bitField0_ & 1) != 0;
        }

        public boolean getAllowAlias() {
            return this.allowAlias_;
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 2) != 0;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        @Deprecated
        public boolean hasDeprecatedLegacyJsonFieldConflicts() {
            return (this.bitField0_ & 4) != 0;
        }

        @Deprecated
        public boolean getDeprecatedLegacyJsonFieldConflicts() {
            return this.deprecatedLegacyJsonFieldConflicts_;
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override // com.google.protobuf.DescriptorProtos.EnumOptionsOrBuilder
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.ExtendableMessage, com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                if (!getUninterpretedOption(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            GeneratedMessageV3.ExtendableMessage<MessageT>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) != 0) {
                output.writeBool(2, this.allowAlias_);
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeBool(3, this.deprecated_);
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeBool(6, this.deprecatedLegacyJsonFieldConflicts_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriterNewExtensionWriter.writeUntil(536870912, output);
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeBoolSize = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeBoolSize(2, this.allowAlias_) + 0 : 0;
            if ((2 & this.bitField0_) != 0) {
                iComputeBoolSize += CodedOutputStream.computeBoolSize(3, this.deprecated_);
            }
            if ((this.bitField0_ & 4) != 0) {
                iComputeBoolSize += CodedOutputStream.computeBoolSize(6, this.deprecatedLegacyJsonFieldConflicts_);
            }
            for (int i2 = 0; i2 < this.uninterpretedOption_.size(); i2++) {
                iComputeBoolSize += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i2));
            }
            int iExtensionsSerializedSize = iComputeBoolSize + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSize = iExtensionsSerializedSize;
            return iExtensionsSerializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EnumOptions)) {
                return super.equals(obj);
            }
            EnumOptions enumOptions = (EnumOptions) obj;
            if (hasAllowAlias() != enumOptions.hasAllowAlias()) {
                return false;
            }
            if ((hasAllowAlias() && getAllowAlias() != enumOptions.getAllowAlias()) || hasDeprecated() != enumOptions.hasDeprecated()) {
                return false;
            }
            if ((!hasDeprecated() || getDeprecated() == enumOptions.getDeprecated()) && hasDeprecatedLegacyJsonFieldConflicts() == enumOptions.hasDeprecatedLegacyJsonFieldConflicts()) {
                return (!hasDeprecatedLegacyJsonFieldConflicts() || getDeprecatedLegacyJsonFieldConflicts() == enumOptions.getDeprecatedLegacyJsonFieldConflicts()) && getUninterpretedOptionList().equals(enumOptions.getUninterpretedOptionList()) && getUnknownFields().equals(enumOptions.getUnknownFields()) && getExtensionFields().equals(enumOptions.getExtensionFields());
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasAllowAlias()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + Internal.hashBoolean(getAllowAlias());
            }
            if (hasDeprecated()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + Internal.hashBoolean(getDeprecated());
            }
            if (hasDeprecatedLegacyJsonFieldConflicts()) {
                iHashCode = (((iHashCode * 37) + 6) * 53) + Internal.hashBoolean(getDeprecatedLegacyJsonFieldConflicts());
            }
            if (getUninterpretedOptionCount() > 0) {
                iHashCode = (((iHashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
            }
            int iHashFields = (AbstractMessage.hashFields(iHashCode, getExtensionFields()) * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashFields;
            return iHashFields;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.ExtendableBuilder<EnumOptions, Builder> implements EnumOptionsOrBuilder {
            private boolean allowAlias_;
            private int bitField0_;
            private boolean deprecatedLegacyJsonFieldConflicts_;
            private boolean deprecated_;
            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
            private List<UninterpretedOption> uninterpretedOption_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_EnumOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumOptions.class, Builder.class);
            }

            private Builder() {
                this.uninterpretedOption_ = Collections.emptyList();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.uninterpretedOption_ = Collections.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_EnumOptions_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public EnumOptions getDefaultInstanceForType() {
                return EnumOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumOptions build() {
                EnumOptions enumOptionsBuildPartial = buildPartial();
                if (enumOptionsBuildPartial.isInitialized()) {
                    return enumOptionsBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) enumOptionsBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumOptions buildPartial() {
                EnumOptions enumOptions = new EnumOptions(this);
                buildPartialRepeatedFields(enumOptions);
                if (this.bitField0_ != 0) {
                    buildPartial0(enumOptions);
                }
                onBuilt();
                return enumOptions;
            }

            private void buildPartialRepeatedFields(EnumOptions result) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.uninterpretedOption_ = repeatedFieldBuilderV3.build();
                    return;
                }
                if ((this.bitField0_ & 8) != 0) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    this.bitField0_ &= -9;
                }
                result.uninterpretedOption_ = this.uninterpretedOption_;
            }

            private void buildPartial0(EnumOptions result) {
                int i;
                int i2 = this.bitField0_;
                if ((i2 & 1) != 0) {
                    result.allowAlias_ = this.allowAlias_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    result.deprecated_ = this.deprecated_;
                    i |= 2;
                }
                if ((i2 & 4) != 0) {
                    result.deprecatedLegacyJsonFieldConflicts_ = this.deprecatedLegacyJsonFieldConflicts_;
                    i |= 4;
                }
                EnumOptions.access$20976(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof EnumOptions) {
                    return mergeFrom((EnumOptions) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(EnumOptions other) {
                if (other == EnumOptions.getDefaultInstance()) {
                    return this;
                }
                if (other.hasAllowAlias()) {
                    setAllowAlias(other.getAllowAlias());
                }
                if (other.hasDeprecated()) {
                    setDeprecated(other.getDeprecated());
                }
                if (other.hasDeprecatedLegacyJsonFieldConflicts()) {
                    setDeprecatedLegacyJsonFieldConflicts(other.getDeprecatedLegacyJsonFieldConflicts());
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= -9;
                        } else {
                            ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (!this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    } else {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= -9;
                        this.uninterpretedOptionBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
                    }
                }
                mergeExtensionFields(other);
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.ExtendableBuilder, com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag != 0) {
                                if (tag == 16) {
                                    this.allowAlias_ = input.readBool();
                                    this.bitField0_ |= 1;
                                } else if (tag == 24) {
                                    this.deprecated_ = input.readBool();
                                    this.bitField0_ |= 2;
                                } else if (tag == 48) {
                                    this.deprecatedLegacyJsonFieldConflicts_ = input.readBool();
                                    this.bitField0_ |= 4;
                                } else if (tag == 7994) {
                                    UninterpretedOption uninterpretedOption = (UninterpretedOption) input.readMessage(UninterpretedOption.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureUninterpretedOptionIsMutable();
                                        this.uninterpretedOption_.add(uninterpretedOption);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(uninterpretedOption);
                                    }
                                } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            public Builder setAllowAlias(boolean value) {
                this.allowAlias_ = value;
                this.bitField0_ |= 1;
                onChanged();
                return this;
            }

            public Builder setDeprecated(boolean value) {
                this.deprecated_ = value;
                this.bitField0_ |= 2;
                onChanged();
                return this;
            }

            @Deprecated
            public Builder setDeprecatedLegacyJsonFieldConflicts(boolean value) {
                this.deprecatedLegacyJsonFieldConflicts_ = value;
                this.bitField0_ |= 4;
                onChanged();
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 8) == 0) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 8;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.EnumOptionsOrBuilder
            public int getUninterpretedOptionCount() {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public UninterpretedOption getUninterpretedOption(int index) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return (UninterpretedOption) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3<>(this.uninterpretedOption_, (this.bitField0_ & 8) != 0, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static EnumOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<EnumOptions> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public EnumOptions getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class EnumValueOptions extends GeneratedMessageV3.ExtendableMessage<EnumValueOptions> implements EnumValueOptionsOrBuilder {
        private static final EnumValueOptions DEFAULT_INSTANCE = new EnumValueOptions();

        @Deprecated
        public static final Parser<EnumValueOptions> PARSER = new AbstractParser<EnumValueOptions>() { // from class: com.google.protobuf.DescriptorProtos.EnumValueOptions.1
            @Override // com.google.protobuf.Parser
            public EnumValueOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = EnumValueOptions.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private int bitField0_;
        private boolean deprecated_;
        private byte memoizedIsInitialized;
        private List<UninterpretedOption> uninterpretedOption_;

        static /* synthetic */ int access$21776(EnumValueOptions enumValueOptions, int i) {
            int i2 = i | enumValueOptions.bitField0_;
            enumValueOptions.bitField0_ = i2;
            return i2;
        }

        private EnumValueOptions(GeneratedMessageV3.ExtendableBuilder<EnumValueOptions, ?> builder) {
            super(builder);
            this.deprecated_ = false;
            this.memoizedIsInitialized = (byte) -1;
        }

        private EnumValueOptions() {
            this.deprecated_ = false;
            this.memoizedIsInitialized = (byte) -1;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new EnumValueOptions();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumValueOptions.class, Builder.class);
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 1) != 0;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override // com.google.protobuf.DescriptorProtos.EnumValueOptionsOrBuilder
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.ExtendableMessage, com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                if (!getUninterpretedOption(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            GeneratedMessageV3.ExtendableMessage<MessageT>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) != 0) {
                output.writeBool(1, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriterNewExtensionWriter.writeUntil(536870912, output);
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeBoolSize = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeBoolSize(1, this.deprecated_) + 0 : 0;
            for (int i2 = 0; i2 < this.uninterpretedOption_.size(); i2++) {
                iComputeBoolSize += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i2));
            }
            int iExtensionsSerializedSize = iComputeBoolSize + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSize = iExtensionsSerializedSize;
            return iExtensionsSerializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EnumValueOptions)) {
                return super.equals(obj);
            }
            EnumValueOptions enumValueOptions = (EnumValueOptions) obj;
            if (hasDeprecated() != enumValueOptions.hasDeprecated()) {
                return false;
            }
            return (!hasDeprecated() || getDeprecated() == enumValueOptions.getDeprecated()) && getUninterpretedOptionList().equals(enumValueOptions.getUninterpretedOptionList()) && getUnknownFields().equals(enumValueOptions.getUnknownFields()) && getExtensionFields().equals(enumValueOptions.getExtensionFields());
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasDeprecated()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + Internal.hashBoolean(getDeprecated());
            }
            if (getUninterpretedOptionCount() > 0) {
                iHashCode = (((iHashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
            }
            int iHashFields = (AbstractMessage.hashFields(iHashCode, getExtensionFields()) * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashFields;
            return iHashFields;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.ExtendableBuilder<EnumValueOptions, Builder> implements EnumValueOptionsOrBuilder {
            private int bitField0_;
            private boolean deprecated_;
            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
            private List<UninterpretedOption> uninterpretedOption_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumValueOptions.class, Builder.class);
            }

            private Builder() {
                this.uninterpretedOption_ = Collections.emptyList();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.uninterpretedOption_ = Collections.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public EnumValueOptions getDefaultInstanceForType() {
                return EnumValueOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumValueOptions build() {
                EnumValueOptions enumValueOptionsBuildPartial = buildPartial();
                if (enumValueOptionsBuildPartial.isInitialized()) {
                    return enumValueOptionsBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) enumValueOptionsBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnumValueOptions buildPartial() {
                EnumValueOptions enumValueOptions = new EnumValueOptions(this);
                buildPartialRepeatedFields(enumValueOptions);
                if (this.bitField0_ != 0) {
                    buildPartial0(enumValueOptions);
                }
                onBuilt();
                return enumValueOptions;
            }

            private void buildPartialRepeatedFields(EnumValueOptions result) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.uninterpretedOption_ = repeatedFieldBuilderV3.build();
                    return;
                }
                if ((this.bitField0_ & 2) != 0) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    this.bitField0_ &= -3;
                }
                result.uninterpretedOption_ = this.uninterpretedOption_;
            }

            private void buildPartial0(EnumValueOptions result) {
                int i = 1;
                if ((this.bitField0_ & 1) != 0) {
                    result.deprecated_ = this.deprecated_;
                } else {
                    i = 0;
                }
                EnumValueOptions.access$21776(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof EnumValueOptions) {
                    return mergeFrom((EnumValueOptions) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(EnumValueOptions other) {
                if (other == EnumValueOptions.getDefaultInstance()) {
                    return this;
                }
                if (other.hasDeprecated()) {
                    setDeprecated(other.getDeprecated());
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (!this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    } else {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= -3;
                        this.uninterpretedOptionBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
                    }
                }
                mergeExtensionFields(other);
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.ExtendableBuilder, com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag != 0) {
                                if (tag == 8) {
                                    this.deprecated_ = input.readBool();
                                    this.bitField0_ |= 1;
                                } else if (tag == 7994) {
                                    UninterpretedOption uninterpretedOption = (UninterpretedOption) input.readMessage(UninterpretedOption.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureUninterpretedOptionIsMutable();
                                        this.uninterpretedOption_.add(uninterpretedOption);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(uninterpretedOption);
                                    }
                                } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            public Builder setDeprecated(boolean value) {
                this.deprecated_ = value;
                this.bitField0_ |= 1;
                onChanged();
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 2) == 0) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 2;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.EnumValueOptionsOrBuilder
            public int getUninterpretedOptionCount() {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public UninterpretedOption getUninterpretedOption(int index) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return (UninterpretedOption) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3<>(this.uninterpretedOption_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static EnumValueOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<EnumValueOptions> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public EnumValueOptions getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class ServiceOptions extends GeneratedMessageV3.ExtendableMessage<ServiceOptions> implements ServiceOptionsOrBuilder {
        private static final ServiceOptions DEFAULT_INSTANCE = new ServiceOptions();

        @Deprecated
        public static final Parser<ServiceOptions> PARSER = new AbstractParser<ServiceOptions>() { // from class: com.google.protobuf.DescriptorProtos.ServiceOptions.1
            @Override // com.google.protobuf.Parser
            public ServiceOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = ServiceOptions.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private int bitField0_;
        private boolean deprecated_;
        private byte memoizedIsInitialized;
        private List<UninterpretedOption> uninterpretedOption_;

        static /* synthetic */ int access$22576(ServiceOptions serviceOptions, int i) {
            int i2 = i | serviceOptions.bitField0_;
            serviceOptions.bitField0_ = i2;
            return i2;
        }

        private ServiceOptions(GeneratedMessageV3.ExtendableBuilder<ServiceOptions, ?> builder) {
            super(builder);
            this.deprecated_ = false;
            this.memoizedIsInitialized = (byte) -1;
        }

        private ServiceOptions() {
            this.deprecated_ = false;
            this.memoizedIsInitialized = (byte) -1;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new ServiceOptions();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(ServiceOptions.class, Builder.class);
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 1) != 0;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override // com.google.protobuf.DescriptorProtos.ServiceOptionsOrBuilder
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.ExtendableMessage, com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                if (!getUninterpretedOption(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            GeneratedMessageV3.ExtendableMessage<MessageT>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) != 0) {
                output.writeBool(33, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriterNewExtensionWriter.writeUntil(536870912, output);
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeBoolSize = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeBoolSize(33, this.deprecated_) + 0 : 0;
            for (int i2 = 0; i2 < this.uninterpretedOption_.size(); i2++) {
                iComputeBoolSize += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i2));
            }
            int iExtensionsSerializedSize = iComputeBoolSize + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSize = iExtensionsSerializedSize;
            return iExtensionsSerializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ServiceOptions)) {
                return super.equals(obj);
            }
            ServiceOptions serviceOptions = (ServiceOptions) obj;
            if (hasDeprecated() != serviceOptions.hasDeprecated()) {
                return false;
            }
            return (!hasDeprecated() || getDeprecated() == serviceOptions.getDeprecated()) && getUninterpretedOptionList().equals(serviceOptions.getUninterpretedOptionList()) && getUnknownFields().equals(serviceOptions.getUnknownFields()) && getExtensionFields().equals(serviceOptions.getExtensionFields());
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasDeprecated()) {
                iHashCode = (((iHashCode * 37) + 33) * 53) + Internal.hashBoolean(getDeprecated());
            }
            if (getUninterpretedOptionCount() > 0) {
                iHashCode = (((iHashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
            }
            int iHashFields = (AbstractMessage.hashFields(iHashCode, getExtensionFields()) * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashFields;
            return iHashFields;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.ExtendableBuilder<ServiceOptions, Builder> implements ServiceOptionsOrBuilder {
            private int bitField0_;
            private boolean deprecated_;
            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
            private List<UninterpretedOption> uninterpretedOption_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(ServiceOptions.class, Builder.class);
            }

            private Builder() {
                this.uninterpretedOption_ = Collections.emptyList();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.uninterpretedOption_ = Collections.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ServiceOptions getDefaultInstanceForType() {
                return ServiceOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ServiceOptions build() {
                ServiceOptions serviceOptionsBuildPartial = buildPartial();
                if (serviceOptionsBuildPartial.isInitialized()) {
                    return serviceOptionsBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) serviceOptionsBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ServiceOptions buildPartial() {
                ServiceOptions serviceOptions = new ServiceOptions(this);
                buildPartialRepeatedFields(serviceOptions);
                if (this.bitField0_ != 0) {
                    buildPartial0(serviceOptions);
                }
                onBuilt();
                return serviceOptions;
            }

            private void buildPartialRepeatedFields(ServiceOptions result) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.uninterpretedOption_ = repeatedFieldBuilderV3.build();
                    return;
                }
                if ((this.bitField0_ & 2) != 0) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    this.bitField0_ &= -3;
                }
                result.uninterpretedOption_ = this.uninterpretedOption_;
            }

            private void buildPartial0(ServiceOptions result) {
                int i = 1;
                if ((this.bitField0_ & 1) != 0) {
                    result.deprecated_ = this.deprecated_;
                } else {
                    i = 0;
                }
                ServiceOptions.access$22576(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof ServiceOptions) {
                    return mergeFrom((ServiceOptions) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ServiceOptions other) {
                if (other == ServiceOptions.getDefaultInstance()) {
                    return this;
                }
                if (other.hasDeprecated()) {
                    setDeprecated(other.getDeprecated());
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (!this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    } else {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= -3;
                        this.uninterpretedOptionBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
                    }
                }
                mergeExtensionFields(other);
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.ExtendableBuilder, com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag != 0) {
                                if (tag == 264) {
                                    this.deprecated_ = input.readBool();
                                    this.bitField0_ |= 1;
                                } else if (tag == 7994) {
                                    UninterpretedOption uninterpretedOption = (UninterpretedOption) input.readMessage(UninterpretedOption.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureUninterpretedOptionIsMutable();
                                        this.uninterpretedOption_.add(uninterpretedOption);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(uninterpretedOption);
                                    }
                                } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            public Builder setDeprecated(boolean value) {
                this.deprecated_ = value;
                this.bitField0_ |= 1;
                onChanged();
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 2) == 0) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 2;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.ServiceOptionsOrBuilder
            public int getUninterpretedOptionCount() {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public UninterpretedOption getUninterpretedOption(int index) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return (UninterpretedOption) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3<>(this.uninterpretedOption_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static ServiceOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<ServiceOptions> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ServiceOptions getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class MethodOptions extends GeneratedMessageV3.ExtendableMessage<MethodOptions> implements MethodOptionsOrBuilder {
        private static final MethodOptions DEFAULT_INSTANCE = new MethodOptions();

        @Deprecated
        public static final Parser<MethodOptions> PARSER = new AbstractParser<MethodOptions>() { // from class: com.google.protobuf.DescriptorProtos.MethodOptions.1
            @Override // com.google.protobuf.Parser
            public MethodOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = MethodOptions.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private int bitField0_;
        private boolean deprecated_;
        private int idempotencyLevel_;
        private byte memoizedIsInitialized;
        private List<UninterpretedOption> uninterpretedOption_;

        static /* synthetic */ int access$23476(MethodOptions methodOptions, int i) {
            int i2 = i | methodOptions.bitField0_;
            methodOptions.bitField0_ = i2;
            return i2;
        }

        private MethodOptions(GeneratedMessageV3.ExtendableBuilder<MethodOptions, ?> builder) {
            super(builder);
            this.deprecated_ = false;
            this.idempotencyLevel_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private MethodOptions() {
            this.deprecated_ = false;
            this.memoizedIsInitialized = (byte) -1;
            this.idempotencyLevel_ = 0;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new MethodOptions();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_MethodOptions_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_MethodOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(MethodOptions.class, Builder.class);
        }

        public enum IdempotencyLevel implements ProtocolMessageEnum {
            IDEMPOTENCY_UNKNOWN(0),
            NO_SIDE_EFFECTS(1),
            IDEMPOTENT(2);

            public static final int IDEMPOTENCY_UNKNOWN_VALUE = 0;
            public static final int IDEMPOTENT_VALUE = 2;
            public static final int NO_SIDE_EFFECTS_VALUE = 1;
            private final int value;
            private static final Internal.EnumLiteMap<IdempotencyLevel> internalValueMap = new Internal.EnumLiteMap<IdempotencyLevel>() { // from class: com.google.protobuf.DescriptorProtos.MethodOptions.IdempotencyLevel.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public IdempotencyLevel findValueByNumber(int number) {
                    return IdempotencyLevel.forNumber(number);
                }
            };
            private static final IdempotencyLevel[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static IdempotencyLevel valueOf(int value) {
                return forNumber(value);
            }

            public static IdempotencyLevel forNumber(int value) {
                if (value == 0) {
                    return IDEMPOTENCY_UNKNOWN;
                }
                if (value == 1) {
                    return NO_SIDE_EFFECTS;
                }
                if (value != 2) {
                    return null;
                }
                return IDEMPOTENT;
            }

            public static Internal.EnumLiteMap<IdempotencyLevel> internalGetValueMap() {
                return internalValueMap;
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return MethodOptions.getDescriptor().getEnumTypes().get(0);
            }

            public static IdempotencyLevel valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            IdempotencyLevel(int value) {
                this.value = value;
            }
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 1) != 0;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public boolean hasIdempotencyLevel() {
            return (this.bitField0_ & 2) != 0;
        }

        public IdempotencyLevel getIdempotencyLevel() {
            IdempotencyLevel idempotencyLevelForNumber = IdempotencyLevel.forNumber(this.idempotencyLevel_);
            return idempotencyLevelForNumber == null ? IdempotencyLevel.IDEMPOTENCY_UNKNOWN : idempotencyLevelForNumber;
        }

        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override // com.google.protobuf.DescriptorProtos.MethodOptionsOrBuilder
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.ExtendableMessage, com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                if (!getUninterpretedOption(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            GeneratedMessageV3.ExtendableMessage<MessageT>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) != 0) {
                output.writeBool(33, this.deprecated_);
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeEnum(34, this.idempotencyLevel_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriterNewExtensionWriter.writeUntil(536870912, output);
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeBoolSize = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeBoolSize(33, this.deprecated_) + 0 : 0;
            if ((this.bitField0_ & 2) != 0) {
                iComputeBoolSize += CodedOutputStream.computeEnumSize(34, this.idempotencyLevel_);
            }
            for (int i2 = 0; i2 < this.uninterpretedOption_.size(); i2++) {
                iComputeBoolSize += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i2));
            }
            int iExtensionsSerializedSize = iComputeBoolSize + extensionsSerializedSize() + getUnknownFields().getSerializedSize();
            this.memoizedSize = iExtensionsSerializedSize;
            return iExtensionsSerializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MethodOptions)) {
                return super.equals(obj);
            }
            MethodOptions methodOptions = (MethodOptions) obj;
            if (hasDeprecated() != methodOptions.hasDeprecated()) {
                return false;
            }
            if ((!hasDeprecated() || getDeprecated() == methodOptions.getDeprecated()) && hasIdempotencyLevel() == methodOptions.hasIdempotencyLevel()) {
                return (!hasIdempotencyLevel() || this.idempotencyLevel_ == methodOptions.idempotencyLevel_) && getUninterpretedOptionList().equals(methodOptions.getUninterpretedOptionList()) && getUnknownFields().equals(methodOptions.getUnknownFields()) && getExtensionFields().equals(methodOptions.getExtensionFields());
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasDeprecated()) {
                iHashCode = (((iHashCode * 37) + 33) * 53) + Internal.hashBoolean(getDeprecated());
            }
            if (hasIdempotencyLevel()) {
                iHashCode = (((iHashCode * 37) + 34) * 53) + this.idempotencyLevel_;
            }
            if (getUninterpretedOptionCount() > 0) {
                iHashCode = (((iHashCode * 37) + 999) * 53) + getUninterpretedOptionList().hashCode();
            }
            int iHashFields = (AbstractMessage.hashFields(iHashCode, getExtensionFields()) * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashFields;
            return iHashFields;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.ExtendableBuilder<MethodOptions, Builder> implements MethodOptionsOrBuilder {
            private int bitField0_;
            private boolean deprecated_;
            private int idempotencyLevel_;
            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
            private List<UninterpretedOption> uninterpretedOption_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_MethodOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(MethodOptions.class, Builder.class);
            }

            private Builder() {
                this.idempotencyLevel_ = 0;
                this.uninterpretedOption_ = Collections.emptyList();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.idempotencyLevel_ = 0;
                this.uninterpretedOption_ = Collections.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_MethodOptions_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MethodOptions getDefaultInstanceForType() {
                return MethodOptions.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MethodOptions build() {
                MethodOptions methodOptionsBuildPartial = buildPartial();
                if (methodOptionsBuildPartial.isInitialized()) {
                    return methodOptionsBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) methodOptionsBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MethodOptions buildPartial() {
                MethodOptions methodOptions = new MethodOptions(this);
                buildPartialRepeatedFields(methodOptions);
                if (this.bitField0_ != 0) {
                    buildPartial0(methodOptions);
                }
                onBuilt();
                return methodOptions;
            }

            private void buildPartialRepeatedFields(MethodOptions result) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.uninterpretedOption_ = repeatedFieldBuilderV3.build();
                    return;
                }
                if ((this.bitField0_ & 4) != 0) {
                    this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    this.bitField0_ &= -5;
                }
                result.uninterpretedOption_ = this.uninterpretedOption_;
            }

            private void buildPartial0(MethodOptions result) {
                int i;
                int i2 = this.bitField0_;
                if ((i2 & 1) != 0) {
                    result.deprecated_ = this.deprecated_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    result.idempotencyLevel_ = this.idempotencyLevel_;
                    i |= 2;
                }
                MethodOptions.access$23476(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof MethodOptions) {
                    return mergeFrom((MethodOptions) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(MethodOptions other) {
                if (other == MethodOptions.getDefaultInstance()) {
                    return this;
                }
                if (other.hasDeprecated()) {
                    setDeprecated(other.getDeprecated());
                }
                if (other.hasIdempotencyLevel()) {
                    setIdempotencyLevel(other.getIdempotencyLevel());
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (!this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    } else {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= -5;
                        this.uninterpretedOptionBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
                    }
                }
                mergeExtensionFields(other);
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.ExtendableBuilder, com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag != 0) {
                                if (tag == 264) {
                                    this.deprecated_ = input.readBool();
                                    this.bitField0_ |= 1;
                                } else if (tag == 272) {
                                    int i = input.readEnum();
                                    if (IdempotencyLevel.forNumber(i) == null) {
                                        mergeUnknownVarintField(34, i);
                                    } else {
                                        this.idempotencyLevel_ = i;
                                        this.bitField0_ |= 2;
                                    }
                                } else if (tag == 7994) {
                                    UninterpretedOption uninterpretedOption = (UninterpretedOption) input.readMessage(UninterpretedOption.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureUninterpretedOptionIsMutable();
                                        this.uninterpretedOption_.add(uninterpretedOption);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(uninterpretedOption);
                                    }
                                } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            public Builder setDeprecated(boolean value) {
                this.deprecated_ = value;
                this.bitField0_ |= 1;
                onChanged();
                return this;
            }

            public Builder setIdempotencyLevel(IdempotencyLevel value) {
                value.getClass();
                this.bitField0_ |= 2;
                this.idempotencyLevel_ = value.getNumber();
                onChanged();
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 4) == 0) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 4;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.MethodOptionsOrBuilder
            public int getUninterpretedOptionCount() {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public UninterpretedOption getUninterpretedOption(int index) {
                RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> repeatedFieldBuilderV3 = this.uninterpretedOptionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return (UninterpretedOption) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilderV3<>(this.uninterpretedOption_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static MethodOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<MethodOptions> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MethodOptions getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class UninterpretedOption extends GeneratedMessageV3 implements UninterpretedOptionOrBuilder {
        private static final UninterpretedOption DEFAULT_INSTANCE = new UninterpretedOption();

        @Deprecated
        public static final Parser<UninterpretedOption> PARSER = new AbstractParser<UninterpretedOption>() { // from class: com.google.protobuf.DescriptorProtos.UninterpretedOption.1
            @Override // com.google.protobuf.Parser
            public UninterpretedOption parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = UninterpretedOption.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private volatile Object aggregateValue_;
        private int bitField0_;
        private double doubleValue_;
        private volatile Object identifierValue_;
        private byte memoizedIsInitialized;
        private List<NamePart> name_;
        private long negativeIntValue_;
        private long positiveIntValue_;
        private ByteString stringValue_;

        static /* synthetic */ int access$25576(UninterpretedOption uninterpretedOption, int i) {
            int i2 = i | uninterpretedOption.bitField0_;
            uninterpretedOption.bitField0_ = i2;
            return i2;
        }

        private UninterpretedOption(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.identifierValue_ = "";
            this.positiveIntValue_ = 0L;
            this.negativeIntValue_ = 0L;
            this.doubleValue_ = 0.0d;
            this.stringValue_ = ByteString.EMPTY;
            this.aggregateValue_ = "";
            this.memoizedIsInitialized = (byte) -1;
        }

        private UninterpretedOption() {
            this.identifierValue_ = "";
            this.positiveIntValue_ = 0L;
            this.negativeIntValue_ = 0L;
            this.doubleValue_ = 0.0d;
            ByteString byteString = ByteString.EMPTY;
            this.stringValue_ = byteString;
            this.aggregateValue_ = "";
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = Collections.emptyList();
            this.identifierValue_ = "";
            this.stringValue_ = byteString;
            this.aggregateValue_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new UninterpretedOption();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable.ensureFieldAccessorsInitialized(UninterpretedOption.class, Builder.class);
        }

        public static final class NamePart extends GeneratedMessageV3 implements MessageOrBuilder {
            private static final NamePart DEFAULT_INSTANCE = new NamePart();

            @Deprecated
            public static final Parser<NamePart> PARSER = new AbstractParser<NamePart>() { // from class: com.google.protobuf.DescriptorProtos.UninterpretedOption.NamePart.1
                @Override // com.google.protobuf.Parser
                public NamePart parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    Builder builderNewBuilder = NamePart.newBuilder();
                    try {
                        builderNewBuilder.mergeFrom(input, extensionRegistry);
                        return builderNewBuilder.buildPartial();
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                    } catch (UninitializedMessageException e2) {
                        throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                    }
                }
            };
            private int bitField0_;
            private boolean isExtension_;
            private byte memoizedIsInitialized;
            private volatile Object namePart_;

            static /* synthetic */ int access$24476(NamePart namePart, int i) {
                int i2 = i | namePart.bitField0_;
                namePart.bitField0_ = i2;
                return i2;
            }

            private NamePart(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.namePart_ = "";
                this.isExtension_ = false;
                this.memoizedIsInitialized = (byte) -1;
            }

            private NamePart() {
                this.namePart_ = "";
                this.isExtension_ = false;
                this.memoizedIsInitialized = (byte) -1;
                this.namePart_ = "";
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
                return new NamePart();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable.ensureFieldAccessorsInitialized(NamePart.class, Builder.class);
            }

            public boolean hasNamePart() {
                return (this.bitField0_ & 1) != 0;
            }

            public String getNamePart() {
                Object obj = this.namePart_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.namePart_ = stringUtf8;
                }
                return stringUtf8;
            }

            public boolean hasIsExtension() {
                return (this.bitField0_ & 2) != 0;
            }

            public boolean getIsExtension() {
                return this.isExtension_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                if (!hasNamePart()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasIsExtension()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    GeneratedMessageV3.writeString(output, 1, this.namePart_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    output.writeBool(2, this.isExtension_);
                }
                getUnknownFields().writeTo(output);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeStringSize = (this.bitField0_ & 1) != 0 ? 0 + GeneratedMessageV3.computeStringSize(1, this.namePart_) : 0;
                if ((this.bitField0_ & 2) != 0) {
                    iComputeStringSize += CodedOutputStream.computeBoolSize(2, this.isExtension_);
                }
                int serializedSize = iComputeStringSize + getUnknownFields().getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.AbstractMessage
            public boolean equals(final Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof NamePart)) {
                    return super.equals(obj);
                }
                NamePart namePart = (NamePart) obj;
                if (hasNamePart() != namePart.hasNamePart()) {
                    return false;
                }
                if ((!hasNamePart() || getNamePart().equals(namePart.getNamePart())) && hasIsExtension() == namePart.hasIsExtension()) {
                    return (!hasIsExtension() || getIsExtension() == namePart.getIsExtension()) && getUnknownFields().equals(namePart.getUnknownFields());
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int iHashCode = 779 + getDescriptor().hashCode();
                if (hasNamePart()) {
                    iHashCode = (((iHashCode * 37) + 1) * 53) + getNamePart().hashCode();
                }
                if (hasIsExtension()) {
                    iHashCode = (((iHashCode * 37) + 2) * 53) + Internal.hashBoolean(getIsExtension());
                }
                int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
                return new Builder(parent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MessageOrBuilder {
                private int bitField0_;
                private boolean isExtension_;
                private Object namePart_;

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable.ensureFieldAccessorsInitialized(NamePart.class, Builder.class);
                }

                private Builder() {
                    this.namePart_ = "";
                }

                private Builder(GeneratedMessageV3.BuilderParent parent) {
                    super(parent);
                    this.namePart_ = "";
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public NamePart getDefaultInstanceForType() {
                    return NamePart.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public NamePart build() {
                    NamePart namePartBuildPartial = buildPartial();
                    if (namePartBuildPartial.isInitialized()) {
                        return namePartBuildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) namePartBuildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public NamePart buildPartial() {
                    NamePart namePart = new NamePart(this);
                    if (this.bitField0_ != 0) {
                        buildPartial0(namePart);
                    }
                    onBuilt();
                    return namePart;
                }

                private void buildPartial0(NamePart result) {
                    int i;
                    int i2 = this.bitField0_;
                    if ((i2 & 1) != 0) {
                        result.namePart_ = this.namePart_;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 2) != 0) {
                        result.isExtension_ = this.isExtension_;
                        i |= 2;
                    }
                    NamePart.access$24476(result, i);
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Builder mergeFrom(Message other) {
                    if (other instanceof NamePart) {
                        return mergeFrom((NamePart) other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(NamePart other) {
                    if (other == NamePart.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasNamePart()) {
                        this.namePart_ = other.namePart_;
                        this.bitField0_ |= 1;
                        onChanged();
                    }
                    if (other.hasIsExtension()) {
                        setIsExtension(other.getIsExtension());
                    }
                    mergeUnknownFields(other.getUnknownFields());
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return hasNamePart() && hasIsExtension();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    extensionRegistry.getClass();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                int tag = input.readTag();
                                if (tag != 0) {
                                    if (tag == 10) {
                                        this.namePart_ = input.readBytes();
                                        this.bitField0_ |= 1;
                                    } else if (tag == 16) {
                                        this.isExtension_ = input.readBool();
                                        this.bitField0_ |= 2;
                                    } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                    }
                                }
                                z = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw e.unwrapIOException();
                            }
                        } finally {
                            onChanged();
                        }
                    }
                    return this;
                }

                public boolean hasNamePart() {
                    return (this.bitField0_ & 1) != 0;
                }

                public boolean hasIsExtension() {
                    return (this.bitField0_ & 2) != 0;
                }

                public Builder setIsExtension(boolean value) {
                    this.isExtension_ = value;
                    this.bitField0_ |= 2;
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                    return (Builder) super.setUnknownFields(unknownFields);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
                public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                    return (Builder) super.mergeUnknownFields(unknownFields);
                }
            }

            public static NamePart getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
            public Parser<NamePart> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public NamePart getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public List<NamePart> getNameList() {
            return this.name_;
        }

        @Override // com.google.protobuf.DescriptorProtos.UninterpretedOptionOrBuilder
        public int getNameCount() {
            return this.name_.size();
        }

        public NamePart getName(int index) {
            return this.name_.get(index);
        }

        public boolean hasIdentifierValue() {
            return (this.bitField0_ & 1) != 0;
        }

        public String getIdentifierValue() {
            Object obj = this.identifierValue_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.identifierValue_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasPositiveIntValue() {
            return (this.bitField0_ & 2) != 0;
        }

        public long getPositiveIntValue() {
            return this.positiveIntValue_;
        }

        public boolean hasNegativeIntValue() {
            return (this.bitField0_ & 4) != 0;
        }

        public long getNegativeIntValue() {
            return this.negativeIntValue_;
        }

        public boolean hasDoubleValue() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.protobuf.DescriptorProtos.UninterpretedOptionOrBuilder
        public double getDoubleValue() {
            return this.doubleValue_;
        }

        public boolean hasStringValue() {
            return (this.bitField0_ & 16) != 0;
        }

        public ByteString getStringValue() {
            return this.stringValue_;
        }

        public boolean hasAggregateValue() {
            return (this.bitField0_ & 32) != 0;
        }

        public String getAggregateValue() {
            Object obj = this.aggregateValue_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.aggregateValue_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getNameCount(); i++) {
                if (!getName(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.name_.size(); i++) {
                output.writeMessage(2, this.name_.get(i));
            }
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString(output, 3, this.identifierValue_);
            }
            if ((this.bitField0_ & 2) != 0) {
                output.writeUInt64(4, this.positiveIntValue_);
            }
            if ((this.bitField0_ & 4) != 0) {
                output.writeInt64(5, this.negativeIntValue_);
            }
            if ((this.bitField0_ & 8) != 0) {
                output.writeDouble(6, this.doubleValue_);
            }
            if ((this.bitField0_ & 16) != 0) {
                output.writeBytes(7, this.stringValue_);
            }
            if ((this.bitField0_ & 32) != 0) {
                GeneratedMessageV3.writeString(output, 8, this.aggregateValue_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = 0;
            for (int i2 = 0; i2 < this.name_.size(); i2++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(2, this.name_.get(i2));
            }
            if ((this.bitField0_ & 1) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.identifierValue_);
            }
            if ((this.bitField0_ & 2) != 0) {
                iComputeStringSize += CodedOutputStream.computeUInt64Size(4, this.positiveIntValue_);
            }
            if ((this.bitField0_ & 4) != 0) {
                iComputeStringSize += CodedOutputStream.computeInt64Size(5, this.negativeIntValue_);
            }
            if ((this.bitField0_ & 8) != 0) {
                iComputeStringSize += CodedOutputStream.computeDoubleSize(6, this.doubleValue_);
            }
            if ((this.bitField0_ & 16) != 0) {
                iComputeStringSize += CodedOutputStream.computeBytesSize(7, this.stringValue_);
            }
            if ((this.bitField0_ & 32) != 0) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(8, this.aggregateValue_);
            }
            int serializedSize = iComputeStringSize + getUnknownFields().getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UninterpretedOption)) {
                return super.equals(obj);
            }
            UninterpretedOption uninterpretedOption = (UninterpretedOption) obj;
            if (!getNameList().equals(uninterpretedOption.getNameList()) || hasIdentifierValue() != uninterpretedOption.hasIdentifierValue()) {
                return false;
            }
            if ((hasIdentifierValue() && !getIdentifierValue().equals(uninterpretedOption.getIdentifierValue())) || hasPositiveIntValue() != uninterpretedOption.hasPositiveIntValue()) {
                return false;
            }
            if ((hasPositiveIntValue() && getPositiveIntValue() != uninterpretedOption.getPositiveIntValue()) || hasNegativeIntValue() != uninterpretedOption.hasNegativeIntValue()) {
                return false;
            }
            if ((hasNegativeIntValue() && getNegativeIntValue() != uninterpretedOption.getNegativeIntValue()) || hasDoubleValue() != uninterpretedOption.hasDoubleValue()) {
                return false;
            }
            if ((hasDoubleValue() && Double.doubleToLongBits(getDoubleValue()) != Double.doubleToLongBits(uninterpretedOption.getDoubleValue())) || hasStringValue() != uninterpretedOption.hasStringValue()) {
                return false;
            }
            if ((!hasStringValue() || getStringValue().equals(uninterpretedOption.getStringValue())) && hasAggregateValue() == uninterpretedOption.hasAggregateValue()) {
                return (!hasAggregateValue() || getAggregateValue().equals(uninterpretedOption.getAggregateValue())) && getUnknownFields().equals(uninterpretedOption.getUnknownFields());
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (getNameCount() > 0) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getNameList().hashCode();
            }
            if (hasIdentifierValue()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getIdentifierValue().hashCode();
            }
            if (hasPositiveIntValue()) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + Internal.hashLong(getPositiveIntValue());
            }
            if (hasNegativeIntValue()) {
                iHashCode = (((iHashCode * 37) + 5) * 53) + Internal.hashLong(getNegativeIntValue());
            }
            if (hasDoubleValue()) {
                iHashCode = (((iHashCode * 37) + 6) * 53) + Internal.hashLong(Double.doubleToLongBits(getDoubleValue()));
            }
            if (hasStringValue()) {
                iHashCode = (((iHashCode * 37) + 7) * 53) + getStringValue().hashCode();
            }
            if (hasAggregateValue()) {
                iHashCode = (((iHashCode * 37) + 8) * 53) + getAggregateValue().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UninterpretedOptionOrBuilder {
            private Object aggregateValue_;
            private int bitField0_;
            private double doubleValue_;
            private Object identifierValue_;
            private RepeatedFieldBuilderV3<NamePart, NamePart.Builder, Object> nameBuilder_;
            private List<NamePart> name_;
            private long negativeIntValue_;
            private long positiveIntValue_;
            private ByteString stringValue_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable.ensureFieldAccessorsInitialized(UninterpretedOption.class, Builder.class);
            }

            private Builder() {
                this.name_ = Collections.emptyList();
                this.identifierValue_ = "";
                this.stringValue_ = ByteString.EMPTY;
                this.aggregateValue_ = "";
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.name_ = Collections.emptyList();
                this.identifierValue_ = "";
                this.stringValue_ = ByteString.EMPTY;
                this.aggregateValue_ = "";
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public UninterpretedOption getDefaultInstanceForType() {
                return UninterpretedOption.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UninterpretedOption build() {
                UninterpretedOption uninterpretedOptionBuildPartial = buildPartial();
                if (uninterpretedOptionBuildPartial.isInitialized()) {
                    return uninterpretedOptionBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) uninterpretedOptionBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UninterpretedOption buildPartial() {
                UninterpretedOption uninterpretedOption = new UninterpretedOption(this);
                buildPartialRepeatedFields(uninterpretedOption);
                if (this.bitField0_ != 0) {
                    buildPartial0(uninterpretedOption);
                }
                onBuilt();
                return uninterpretedOption;
            }

            private void buildPartialRepeatedFields(UninterpretedOption result) {
                RepeatedFieldBuilderV3<NamePart, NamePart.Builder, Object> repeatedFieldBuilderV3 = this.nameBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.name_ = repeatedFieldBuilderV3.build();
                    return;
                }
                if ((this.bitField0_ & 1) != 0) {
                    this.name_ = Collections.unmodifiableList(this.name_);
                    this.bitField0_ &= -2;
                }
                result.name_ = this.name_;
            }

            private void buildPartial0(UninterpretedOption result) {
                int i;
                int i2 = this.bitField0_;
                if ((i2 & 2) != 0) {
                    result.identifierValue_ = this.identifierValue_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 4) != 0) {
                    result.positiveIntValue_ = this.positiveIntValue_;
                    i |= 2;
                }
                if ((i2 & 8) != 0) {
                    result.negativeIntValue_ = this.negativeIntValue_;
                    i |= 4;
                }
                if ((i2 & 16) != 0) {
                    result.doubleValue_ = this.doubleValue_;
                    i |= 8;
                }
                if ((i2 & 32) != 0) {
                    result.stringValue_ = this.stringValue_;
                    i |= 16;
                }
                if ((i2 & 64) != 0) {
                    result.aggregateValue_ = this.aggregateValue_;
                    i |= 32;
                }
                UninterpretedOption.access$25576(result, i);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof UninterpretedOption) {
                    return mergeFrom((UninterpretedOption) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(UninterpretedOption other) {
                if (other == UninterpretedOption.getDefaultInstance()) {
                    return this;
                }
                if (this.nameBuilder_ == null) {
                    if (!other.name_.isEmpty()) {
                        if (this.name_.isEmpty()) {
                            this.name_ = other.name_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureNameIsMutable();
                            this.name_.addAll(other.name_);
                        }
                        onChanged();
                    }
                } else if (!other.name_.isEmpty()) {
                    if (!this.nameBuilder_.isEmpty()) {
                        this.nameBuilder_.addAllMessages(other.name_);
                    } else {
                        this.nameBuilder_.dispose();
                        this.nameBuilder_ = null;
                        this.name_ = other.name_;
                        this.bitField0_ &= -2;
                        this.nameBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getNameFieldBuilder() : null;
                    }
                }
                if (other.hasIdentifierValue()) {
                    this.identifierValue_ = other.identifierValue_;
                    this.bitField0_ |= 2;
                    onChanged();
                }
                if (other.hasPositiveIntValue()) {
                    setPositiveIntValue(other.getPositiveIntValue());
                }
                if (other.hasNegativeIntValue()) {
                    setNegativeIntValue(other.getNegativeIntValue());
                }
                if (other.hasDoubleValue()) {
                    setDoubleValue(other.getDoubleValue());
                }
                if (other.hasStringValue()) {
                    setStringValue(other.getStringValue());
                }
                if (other.hasAggregateValue()) {
                    this.aggregateValue_ = other.aggregateValue_;
                    this.bitField0_ |= 64;
                    onChanged();
                }
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getNameCount(); i++) {
                    if (!getName(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag != 0) {
                                if (tag == 18) {
                                    NamePart namePart = (NamePart) input.readMessage(NamePart.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<NamePart, NamePart.Builder, Object> repeatedFieldBuilderV3 = this.nameBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureNameIsMutable();
                                        this.name_.add(namePart);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(namePart);
                                    }
                                } else if (tag == 26) {
                                    this.identifierValue_ = input.readBytes();
                                    this.bitField0_ |= 2;
                                } else if (tag == 32) {
                                    this.positiveIntValue_ = input.readUInt64();
                                    this.bitField0_ |= 4;
                                } else if (tag == 40) {
                                    this.negativeIntValue_ = input.readInt64();
                                    this.bitField0_ |= 8;
                                } else if (tag == 49) {
                                    this.doubleValue_ = input.readDouble();
                                    this.bitField0_ |= 16;
                                } else if (tag == 58) {
                                    this.stringValue_ = input.readBytes();
                                    this.bitField0_ |= 32;
                                } else if (tag == 66) {
                                    this.aggregateValue_ = input.readBytes();
                                    this.bitField0_ |= 64;
                                } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            private void ensureNameIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.name_ = new ArrayList(this.name_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.UninterpretedOptionOrBuilder
            public int getNameCount() {
                RepeatedFieldBuilderV3<NamePart, NamePart.Builder, Object> repeatedFieldBuilderV3 = this.nameBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.name_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            public NamePart getName(int index) {
                RepeatedFieldBuilderV3<NamePart, NamePart.Builder, Object> repeatedFieldBuilderV3 = this.nameBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.name_.get(index);
                }
                return (NamePart) repeatedFieldBuilderV3.getMessage(index);
            }

            private RepeatedFieldBuilderV3<NamePart, NamePart.Builder, Object> getNameFieldBuilder() {
                if (this.nameBuilder_ == null) {
                    this.nameBuilder_ = new RepeatedFieldBuilderV3<>(this.name_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.name_ = null;
                }
                return this.nameBuilder_;
            }

            public Builder setPositiveIntValue(long value) {
                this.positiveIntValue_ = value;
                this.bitField0_ |= 4;
                onChanged();
                return this;
            }

            public Builder setNegativeIntValue(long value) {
                this.negativeIntValue_ = value;
                this.bitField0_ |= 8;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.DescriptorProtos.UninterpretedOptionOrBuilder
            public double getDoubleValue() {
                return this.doubleValue_;
            }

            public Builder setDoubleValue(double value) {
                this.doubleValue_ = value;
                this.bitField0_ |= 16;
                onChanged();
                return this;
            }

            public Builder setStringValue(ByteString value) {
                value.getClass();
                this.stringValue_ = value;
                this.bitField0_ |= 32;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static UninterpretedOption getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<UninterpretedOption> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UninterpretedOption getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class SourceCodeInfo extends GeneratedMessageV3 implements SourceCodeInfoOrBuilder {
        private static final SourceCodeInfo DEFAULT_INSTANCE = new SourceCodeInfo();

        @Deprecated
        public static final Parser<SourceCodeInfo> PARSER = new AbstractParser<SourceCodeInfo>() { // from class: com.google.protobuf.DescriptorProtos.SourceCodeInfo.1
            @Override // com.google.protobuf.Parser
            public SourceCodeInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = SourceCodeInfo.newBuilder();
                try {
                    builderNewBuilder.mergeFrom(input, extensionRegistry);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
        private List<Location> location_;
        private byte memoizedIsInitialized;

        public interface LocationOrBuilder extends MessageOrBuilder {
            int getLeadingDetachedCommentsCount();

            int getPathCount();

            int getSpanCount();
        }

        private SourceCodeInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private SourceCodeInfo() {
            this.memoizedIsInitialized = (byte) -1;
            this.location_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
            return new SourceCodeInfo();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SourceCodeInfo.class, Builder.class);
        }

        public static final class Location extends GeneratedMessageV3 implements LocationOrBuilder {
            private static final Location DEFAULT_INSTANCE = new Location();

            @Deprecated
            public static final Parser<Location> PARSER = new AbstractParser<Location>() { // from class: com.google.protobuf.DescriptorProtos.SourceCodeInfo.Location.1
                @Override // com.google.protobuf.Parser
                public Location parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    Builder builderNewBuilder = Location.newBuilder();
                    try {
                        builderNewBuilder.mergeFrom(input, extensionRegistry);
                        return builderNewBuilder.buildPartial();
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(builderNewBuilder.buildPartial());
                    } catch (UninitializedMessageException e2) {
                        throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(builderNewBuilder.buildPartial());
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(builderNewBuilder.buildPartial());
                    }
                }
            };
            private int bitField0_;
            private volatile Object leadingComments_;
            private LazyStringArrayList leadingDetachedComments_;
            private byte memoizedIsInitialized;
            private int pathMemoizedSerializedSize;
            private Internal.IntList path_;
            private int spanMemoizedSerializedSize;
            private Internal.IntList span_;
            private volatile Object trailingComments_;

            static /* synthetic */ int access$26876(Location location, int i) {
                int i2 = i | location.bitField0_;
                location.bitField0_ = i2;
                return i2;
            }

            private Location(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.pathMemoizedSerializedSize = -1;
                this.spanMemoizedSerializedSize = -1;
                this.leadingComments_ = "";
                this.trailingComments_ = "";
                this.leadingDetachedComments_ = LazyStringArrayList.emptyList();
                this.memoizedIsInitialized = (byte) -1;
            }

            private Location() {
                this.pathMemoizedSerializedSize = -1;
                this.spanMemoizedSerializedSize = -1;
                this.leadingComments_ = "";
                this.trailingComments_ = "";
                this.leadingDetachedComments_ = LazyStringArrayList.emptyList();
                this.memoizedIsInitialized = (byte) -1;
                this.path_ = GeneratedMessageV3.emptyIntList();
                this.span_ = GeneratedMessageV3.emptyIntList();
                this.leadingComments_ = "";
                this.trailingComments_ = "";
                this.leadingDetachedComments_ = LazyStringArrayList.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
                return new Location();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable.ensureFieldAccessorsInitialized(Location.class, Builder.class);
            }

            public List<Integer> getPathList() {
                return this.path_;
            }

            @Override // com.google.protobuf.DescriptorProtos.SourceCodeInfo.LocationOrBuilder
            public int getPathCount() {
                return this.path_.size();
            }

            public List<Integer> getSpanList() {
                return this.span_;
            }

            @Override // com.google.protobuf.DescriptorProtos.SourceCodeInfo.LocationOrBuilder
            public int getSpanCount() {
                return this.span_.size();
            }

            public boolean hasLeadingComments() {
                return (this.bitField0_ & 1) != 0;
            }

            public String getLeadingComments() {
                Object obj = this.leadingComments_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.leadingComments_ = stringUtf8;
                }
                return stringUtf8;
            }

            public boolean hasTrailingComments() {
                return (this.bitField0_ & 2) != 0;
            }

            public String getTrailingComments() {
                Object obj = this.trailingComments_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.trailingComments_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ProtocolStringList getLeadingDetachedCommentsList() {
                return this.leadingDetachedComments_;
            }

            @Override // com.google.protobuf.DescriptorProtos.SourceCodeInfo.LocationOrBuilder
            public int getLeadingDetachedCommentsCount() {
                return this.leadingDetachedComments_.size();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                getSerializedSize();
                if (getPathList().size() > 0) {
                    output.writeUInt32NoTag(10);
                    output.writeUInt32NoTag(this.pathMemoizedSerializedSize);
                }
                for (int i = 0; i < this.path_.size(); i++) {
                    output.writeInt32NoTag(this.path_.getInt(i));
                }
                if (getSpanList().size() > 0) {
                    output.writeUInt32NoTag(18);
                    output.writeUInt32NoTag(this.spanMemoizedSerializedSize);
                }
                for (int i2 = 0; i2 < this.span_.size(); i2++) {
                    output.writeInt32NoTag(this.span_.getInt(i2));
                }
                if ((this.bitField0_ & 1) != 0) {
                    GeneratedMessageV3.writeString(output, 3, this.leadingComments_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    GeneratedMessageV3.writeString(output, 4, this.trailingComments_);
                }
                for (int i3 = 0; i3 < this.leadingDetachedComments_.size(); i3++) {
                    GeneratedMessageV3.writeString(output, 6, this.leadingDetachedComments_.getRaw(i3));
                }
                getUnknownFields().writeTo(output);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeInt32SizeNoTag = 0;
                for (int i2 = 0; i2 < this.path_.size(); i2++) {
                    iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(this.path_.getInt(i2));
                }
                int iComputeInt32SizeNoTag2 = 0 + iComputeInt32SizeNoTag;
                if (!getPathList().isEmpty()) {
                    iComputeInt32SizeNoTag2 = iComputeInt32SizeNoTag2 + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag);
                }
                this.pathMemoizedSerializedSize = iComputeInt32SizeNoTag;
                int iComputeInt32SizeNoTag3 = 0;
                for (int i3 = 0; i3 < this.span_.size(); i3++) {
                    iComputeInt32SizeNoTag3 += CodedOutputStream.computeInt32SizeNoTag(this.span_.getInt(i3));
                }
                int iComputeStringSize = iComputeInt32SizeNoTag2 + iComputeInt32SizeNoTag3;
                if (!getSpanList().isEmpty()) {
                    iComputeStringSize = iComputeStringSize + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag3);
                }
                this.spanMemoizedSerializedSize = iComputeInt32SizeNoTag3;
                if ((this.bitField0_ & 1) != 0) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.leadingComments_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.trailingComments_);
                }
                int iComputeStringSizeNoTag = 0;
                for (int i4 = 0; i4 < this.leadingDetachedComments_.size(); i4++) {
                    iComputeStringSizeNoTag += GeneratedMessageV3.computeStringSizeNoTag(this.leadingDetachedComments_.getRaw(i4));
                }
                int size = iComputeStringSize + iComputeStringSizeNoTag + (getLeadingDetachedCommentsList().size() * 1) + getUnknownFields().getSerializedSize();
                this.memoizedSize = size;
                return size;
            }

            @Override // com.google.protobuf.AbstractMessage
            public boolean equals(final Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Location)) {
                    return super.equals(obj);
                }
                Location location = (Location) obj;
                if (!getPathList().equals(location.getPathList()) || !getSpanList().equals(location.getSpanList()) || hasLeadingComments() != location.hasLeadingComments()) {
                    return false;
                }
                if ((!hasLeadingComments() || getLeadingComments().equals(location.getLeadingComments())) && hasTrailingComments() == location.hasTrailingComments()) {
                    return (!hasTrailingComments() || getTrailingComments().equals(location.getTrailingComments())) && getLeadingDetachedCommentsList().equals(location.getLeadingDetachedCommentsList()) && getUnknownFields().equals(location.getUnknownFields());
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int iHashCode = 779 + getDescriptor().hashCode();
                if (getPathCount() > 0) {
                    iHashCode = (((iHashCode * 37) + 1) * 53) + getPathList().hashCode();
                }
                if (getSpanCount() > 0) {
                    iHashCode = (((iHashCode * 37) + 2) * 53) + getSpanList().hashCode();
                }
                if (hasLeadingComments()) {
                    iHashCode = (((iHashCode * 37) + 3) * 53) + getLeadingComments().hashCode();
                }
                if (hasTrailingComments()) {
                    iHashCode = (((iHashCode * 37) + 4) * 53) + getTrailingComments().hashCode();
                }
                if (getLeadingDetachedCommentsCount() > 0) {
                    iHashCode = (((iHashCode * 37) + 6) * 53) + getLeadingDetachedCommentsList().hashCode();
                }
                int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
                return new Builder(parent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LocationOrBuilder {
                private int bitField0_;
                private Object leadingComments_;
                private LazyStringArrayList leadingDetachedComments_;
                private Internal.IntList path_;
                private Internal.IntList span_;
                private Object trailingComments_;

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable.ensureFieldAccessorsInitialized(Location.class, Builder.class);
                }

                private Builder() {
                    this.path_ = GeneratedMessageV3.emptyIntList();
                    this.span_ = GeneratedMessageV3.emptyIntList();
                    this.leadingComments_ = "";
                    this.trailingComments_ = "";
                    this.leadingDetachedComments_ = LazyStringArrayList.emptyList();
                }

                private Builder(GeneratedMessageV3.BuilderParent parent) {
                    super(parent);
                    this.path_ = GeneratedMessageV3.emptyIntList();
                    this.span_ = GeneratedMessageV3.emptyIntList();
                    this.leadingComments_ = "";
                    this.trailingComments_ = "";
                    this.leadingDetachedComments_ = LazyStringArrayList.emptyList();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Location getDefaultInstanceForType() {
                    return Location.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Location build() {
                    Location locationBuildPartial = buildPartial();
                    if (locationBuildPartial.isInitialized()) {
                        return locationBuildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) locationBuildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Location buildPartial() {
                    Location location = new Location(this);
                    buildPartialRepeatedFields(location);
                    if (this.bitField0_ != 0) {
                        buildPartial0(location);
                    }
                    onBuilt();
                    return location;
                }

                private void buildPartialRepeatedFields(Location result) {
                    if ((this.bitField0_ & 1) != 0) {
                        this.path_.makeImmutable();
                        this.bitField0_ &= -2;
                    }
                    result.path_ = this.path_;
                    if ((this.bitField0_ & 2) != 0) {
                        this.span_.makeImmutable();
                        this.bitField0_ &= -3;
                    }
                    result.span_ = this.span_;
                }

                private void buildPartial0(Location result) {
                    int i;
                    int i2 = this.bitField0_;
                    if ((i2 & 4) != 0) {
                        result.leadingComments_ = this.leadingComments_;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 8) != 0) {
                        result.trailingComments_ = this.trailingComments_;
                        i |= 2;
                    }
                    if ((i2 & 16) != 0) {
                        this.leadingDetachedComments_.makeImmutable();
                        result.leadingDetachedComments_ = this.leadingDetachedComments_;
                    }
                    Location.access$26876(result, i);
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Builder mergeFrom(Message other) {
                    if (other instanceof Location) {
                        return mergeFrom((Location) other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(Location other) {
                    if (other == Location.getDefaultInstance()) {
                        return this;
                    }
                    if (!other.path_.isEmpty()) {
                        if (this.path_.isEmpty()) {
                            this.path_ = other.path_;
                            this.bitField0_ &= -2;
                        } else {
                            ensurePathIsMutable();
                            this.path_.addAll(other.path_);
                        }
                        onChanged();
                    }
                    if (!other.span_.isEmpty()) {
                        if (this.span_.isEmpty()) {
                            this.span_ = other.span_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureSpanIsMutable();
                            this.span_.addAll(other.span_);
                        }
                        onChanged();
                    }
                    if (other.hasLeadingComments()) {
                        this.leadingComments_ = other.leadingComments_;
                        this.bitField0_ |= 4;
                        onChanged();
                    }
                    if (other.hasTrailingComments()) {
                        this.trailingComments_ = other.trailingComments_;
                        this.bitField0_ |= 8;
                        onChanged();
                    }
                    if (!other.leadingDetachedComments_.isEmpty()) {
                        if (this.leadingDetachedComments_.isEmpty()) {
                            this.leadingDetachedComments_ = other.leadingDetachedComments_;
                            this.bitField0_ |= 16;
                        } else {
                            ensureLeadingDetachedCommentsIsMutable();
                            this.leadingDetachedComments_.addAll(other.leadingDetachedComments_);
                        }
                        onChanged();
                    }
                    mergeUnknownFields(other.getUnknownFields());
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    extensionRegistry.getClass();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                int tag = input.readTag();
                                if (tag != 0) {
                                    if (tag == 8) {
                                        int int32 = input.readInt32();
                                        ensurePathIsMutable();
                                        this.path_.addInt(int32);
                                    } else if (tag == 10) {
                                        int iPushLimit = input.pushLimit(input.readRawVarint32());
                                        ensurePathIsMutable();
                                        while (input.getBytesUntilLimit() > 0) {
                                            this.path_.addInt(input.readInt32());
                                        }
                                        input.popLimit(iPushLimit);
                                    } else if (tag == 16) {
                                        int int322 = input.readInt32();
                                        ensureSpanIsMutable();
                                        this.span_.addInt(int322);
                                    } else if (tag == 18) {
                                        int iPushLimit2 = input.pushLimit(input.readRawVarint32());
                                        ensureSpanIsMutable();
                                        while (input.getBytesUntilLimit() > 0) {
                                            this.span_.addInt(input.readInt32());
                                        }
                                        input.popLimit(iPushLimit2);
                                    } else if (tag == 26) {
                                        this.leadingComments_ = input.readBytes();
                                        this.bitField0_ |= 4;
                                    } else if (tag == 34) {
                                        this.trailingComments_ = input.readBytes();
                                        this.bitField0_ |= 8;
                                    } else if (tag == 50) {
                                        ByteString bytes = input.readBytes();
                                        ensureLeadingDetachedCommentsIsMutable();
                                        this.leadingDetachedComments_.add(bytes);
                                    } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                    }
                                }
                                z = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw e.unwrapIOException();
                            }
                        } finally {
                            onChanged();
                        }
                    }
                    return this;
                }

                private void ensurePathIsMutable() {
                    if ((this.bitField0_ & 1) == 0) {
                        this.path_ = GeneratedMessageV3.mutableCopy(this.path_);
                        this.bitField0_ |= 1;
                    }
                }

                @Override // com.google.protobuf.DescriptorProtos.SourceCodeInfo.LocationOrBuilder
                public int getPathCount() {
                    return this.path_.size();
                }

                private void ensureSpanIsMutable() {
                    if ((this.bitField0_ & 2) == 0) {
                        this.span_ = GeneratedMessageV3.mutableCopy(this.span_);
                        this.bitField0_ |= 2;
                    }
                }

                @Override // com.google.protobuf.DescriptorProtos.SourceCodeInfo.LocationOrBuilder
                public int getSpanCount() {
                    return this.span_.size();
                }

                private void ensureLeadingDetachedCommentsIsMutable() {
                    if (!this.leadingDetachedComments_.isModifiable()) {
                        this.leadingDetachedComments_ = new LazyStringArrayList(this.leadingDetachedComments_);
                    }
                    this.bitField0_ |= 16;
                }

                @Override // com.google.protobuf.DescriptorProtos.SourceCodeInfo.LocationOrBuilder
                public int getLeadingDetachedCommentsCount() {
                    return this.leadingDetachedComments_.size();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                    return (Builder) super.setUnknownFields(unknownFields);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
                public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                    return (Builder) super.mergeUnknownFields(unknownFields);
                }
            }

            public static Location getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
            public Parser<Location> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Location getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public List<Location> getLocationList() {
            return this.location_;
        }

        @Override // com.google.protobuf.DescriptorProtos.SourceCodeInfoOrBuilder
        public int getLocationCount() {
            return this.location_.size();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.location_.size(); i++) {
                output.writeMessage(1, this.location_.get(i));
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = 0;
            for (int i2 = 0; i2 < this.location_.size(); i2++) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.location_.get(i2));
            }
            int serializedSize = iComputeMessageSize + getUnknownFields().getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof SourceCodeInfo)) {
                return super.equals(obj);
            }
            SourceCodeInfo sourceCodeInfo = (SourceCodeInfo) obj;
            return getLocationList().equals(sourceCodeInfo.getLocationList()) && getUnknownFields().equals(sourceCodeInfo.getUnknownFields());
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (getLocationCount() > 0) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getLocationList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SourceCodeInfoOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> locationBuilder_;
            private List<Location> location_;

            private void buildPartial0(SourceCodeInfo result) {
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SourceCodeInfo.class, Builder.class);
            }

            private Builder() {
                this.location_ = Collections.emptyList();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.location_ = Collections.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public SourceCodeInfo getDefaultInstanceForType() {
                return SourceCodeInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public SourceCodeInfo build() {
                SourceCodeInfo sourceCodeInfoBuildPartial = buildPartial();
                if (sourceCodeInfoBuildPartial.isInitialized()) {
                    return sourceCodeInfoBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) sourceCodeInfoBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public SourceCodeInfo buildPartial() {
                SourceCodeInfo sourceCodeInfo = new SourceCodeInfo(this);
                buildPartialRepeatedFields(sourceCodeInfo);
                if (this.bitField0_ != 0) {
                    buildPartial0(sourceCodeInfo);
                }
                onBuilt();
                return sourceCodeInfo;
            }

            private void buildPartialRepeatedFields(SourceCodeInfo result) {
                RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    result.location_ = repeatedFieldBuilderV3.build();
                    return;
                }
                if ((this.bitField0_ & 1) != 0) {
                    this.location_ = Collections.unmodifiableList(this.location_);
                    this.bitField0_ &= -2;
                }
                result.location_ = this.location_;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof SourceCodeInfo) {
                    return mergeFrom((SourceCodeInfo) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(SourceCodeInfo other) {
                if (other == SourceCodeInfo.getDefaultInstance()) {
                    return this;
                }
                if (this.locationBuilder_ == null) {
                    if (!other.location_.isEmpty()) {
                        if (this.location_.isEmpty()) {
                            this.location_ = other.location_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureLocationIsMutable();
                            this.location_.addAll(other.location_);
                        }
                        onChanged();
                    }
                } else if (!other.location_.isEmpty()) {
                    if (!this.locationBuilder_.isEmpty()) {
                        this.locationBuilder_.addAllMessages(other.location_);
                    } else {
                        this.locationBuilder_.dispose();
                        this.locationBuilder_ = null;
                        this.location_ = other.location_;
                        this.bitField0_ &= -2;
                        this.locationBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getLocationFieldBuilder() : null;
                    }
                }
                mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                extensionRegistry.getClass();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    Location location = (Location) input.readMessage(Location.PARSER, extensionRegistry);
                                    RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationBuilder_;
                                    if (repeatedFieldBuilderV3 == null) {
                                        ensureLocationIsMutable();
                                        this.location_.add(location);
                                    } else {
                                        repeatedFieldBuilderV3.addMessage(location);
                                    }
                                } else if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.unwrapIOException();
                        }
                    } finally {
                        onChanged();
                    }
                }
                return this;
            }

            private void ensureLocationIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.location_ = new ArrayList(this.location_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // com.google.protobuf.DescriptorProtos.SourceCodeInfoOrBuilder
            public int getLocationCount() {
                RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> repeatedFieldBuilderV3 = this.locationBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.location_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            private RepeatedFieldBuilderV3<Location, Location.Builder, LocationOrBuilder> getLocationFieldBuilder() {
                if (this.locationBuilder_ == null) {
                    this.locationBuilder_ = new RepeatedFieldBuilderV3<>(this.location_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.location_ = null;
                }
                return this.locationBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static SourceCodeInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<SourceCodeInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public SourceCodeInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_google_protobuf_FileDescriptorSet_descriptor = descriptor2;
        internal_static_google_protobuf_FileDescriptorSet_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"File"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_google_protobuf_FileDescriptorProto_descriptor = descriptor3;
        internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Name", "Package", "Dependency", "PublicDependency", "WeakDependency", "MessageType", "EnumType", "Service", "Extension", "Options", "SourceCodeInfo", "Syntax", "Edition"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_google_protobuf_DescriptorProto_descriptor = descriptor4;
        internal_static_google_protobuf_DescriptorProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Name", "Field", "Extension", "NestedType", "EnumType", "ExtensionRange", "OneofDecl", "Options", "ReservedRange", "ReservedName"});
        Descriptors.Descriptor descriptor5 = descriptor4.getNestedTypes().get(0);
        internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor = descriptor5;
        internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Start", "End", "Options"});
        Descriptors.Descriptor descriptor6 = descriptor4.getNestedTypes().get(1);
        internal_static_google_protobuf_DescriptorProto_ReservedRange_descriptor = descriptor6;
        internal_static_google_protobuf_DescriptorProto_ReservedRange_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"Start", "End"});
        Descriptors.Descriptor descriptor7 = getDescriptor().getMessageTypes().get(3);
        internal_static_google_protobuf_ExtensionRangeOptions_descriptor = descriptor7;
        internal_static_google_protobuf_ExtensionRangeOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"UninterpretedOption"});
        Descriptors.Descriptor descriptor8 = getDescriptor().getMessageTypes().get(4);
        internal_static_google_protobuf_FieldDescriptorProto_descriptor = descriptor8;
        internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"Name", "Number", "Label", "Type", "TypeName", "Extendee", "DefaultValue", "OneofIndex", "JsonName", "Options", "Proto3Optional"});
        Descriptors.Descriptor descriptor9 = getDescriptor().getMessageTypes().get(5);
        internal_static_google_protobuf_OneofDescriptorProto_descriptor = descriptor9;
        internal_static_google_protobuf_OneofDescriptorProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"Name", "Options"});
        Descriptors.Descriptor descriptor10 = getDescriptor().getMessageTypes().get(6);
        internal_static_google_protobuf_EnumDescriptorProto_descriptor = descriptor10;
        internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"Name", "Value", "Options", "ReservedRange", "ReservedName"});
        Descriptors.Descriptor descriptor11 = descriptor10.getNestedTypes().get(0);
        internal_static_google_protobuf_EnumDescriptorProto_EnumReservedRange_descriptor = descriptor11;
        internal_static_google_protobuf_EnumDescriptorProto_EnumReservedRange_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor11, new String[]{"Start", "End"});
        Descriptors.Descriptor descriptor12 = getDescriptor().getMessageTypes().get(7);
        internal_static_google_protobuf_EnumValueDescriptorProto_descriptor = descriptor12;
        internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor12, new String[]{"Name", "Number", "Options"});
        Descriptors.Descriptor descriptor13 = getDescriptor().getMessageTypes().get(8);
        internal_static_google_protobuf_ServiceDescriptorProto_descriptor = descriptor13;
        internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor13, new String[]{"Name", "Method", "Options"});
        Descriptors.Descriptor descriptor14 = getDescriptor().getMessageTypes().get(9);
        internal_static_google_protobuf_MethodDescriptorProto_descriptor = descriptor14;
        internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor14, new String[]{"Name", "InputType", "OutputType", "Options", "ClientStreaming", "ServerStreaming"});
        Descriptors.Descriptor descriptor15 = getDescriptor().getMessageTypes().get(10);
        internal_static_google_protobuf_FileOptions_descriptor = descriptor15;
        internal_static_google_protobuf_FileOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor15, new String[]{"JavaPackage", "JavaOuterClassname", "JavaMultipleFiles", "JavaGenerateEqualsAndHash", "JavaStringCheckUtf8", "OptimizeFor", "GoPackage", "CcGenericServices", "JavaGenericServices", "PyGenericServices", "PhpGenericServices", "Deprecated", "CcEnableArenas", "ObjcClassPrefix", "CsharpNamespace", "SwiftPrefix", "PhpClassPrefix", "PhpNamespace", "PhpMetadataNamespace", "RubyPackage", "UninterpretedOption"});
        Descriptors.Descriptor descriptor16 = getDescriptor().getMessageTypes().get(11);
        internal_static_google_protobuf_MessageOptions_descriptor = descriptor16;
        internal_static_google_protobuf_MessageOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor16, new String[]{"MessageSetWireFormat", "NoStandardDescriptorAccessor", "Deprecated", "MapEntry", "DeprecatedLegacyJsonFieldConflicts", "UninterpretedOption"});
        Descriptors.Descriptor descriptor17 = getDescriptor().getMessageTypes().get(12);
        internal_static_google_protobuf_FieldOptions_descriptor = descriptor17;
        internal_static_google_protobuf_FieldOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor17, new String[]{"Ctype", "Packed", "Jstype", "Lazy", "UnverifiedLazy", "Deprecated", "Weak", "DebugRedact", "Retention", "Target", "UninterpretedOption"});
        Descriptors.Descriptor descriptor18 = getDescriptor().getMessageTypes().get(13);
        internal_static_google_protobuf_OneofOptions_descriptor = descriptor18;
        internal_static_google_protobuf_OneofOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor18, new String[]{"UninterpretedOption"});
        Descriptors.Descriptor descriptor19 = getDescriptor().getMessageTypes().get(14);
        internal_static_google_protobuf_EnumOptions_descriptor = descriptor19;
        internal_static_google_protobuf_EnumOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor19, new String[]{"AllowAlias", "Deprecated", "DeprecatedLegacyJsonFieldConflicts", "UninterpretedOption"});
        Descriptors.Descriptor descriptor20 = getDescriptor().getMessageTypes().get(15);
        internal_static_google_protobuf_EnumValueOptions_descriptor = descriptor20;
        internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor20, new String[]{"Deprecated", "UninterpretedOption"});
        Descriptors.Descriptor descriptor21 = getDescriptor().getMessageTypes().get(16);
        internal_static_google_protobuf_ServiceOptions_descriptor = descriptor21;
        internal_static_google_protobuf_ServiceOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor21, new String[]{"Deprecated", "UninterpretedOption"});
        Descriptors.Descriptor descriptor22 = getDescriptor().getMessageTypes().get(17);
        internal_static_google_protobuf_MethodOptions_descriptor = descriptor22;
        internal_static_google_protobuf_MethodOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor22, new String[]{"Deprecated", "IdempotencyLevel", "UninterpretedOption"});
        Descriptors.Descriptor descriptor23 = getDescriptor().getMessageTypes().get(18);
        internal_static_google_protobuf_UninterpretedOption_descriptor = descriptor23;
        internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor23, new String[]{"Name", "IdentifierValue", "PositiveIntValue", "NegativeIntValue", "DoubleValue", "StringValue", "AggregateValue"});
        Descriptors.Descriptor descriptor24 = descriptor23.getNestedTypes().get(0);
        internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor = descriptor24;
        internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor24, new String[]{"NamePart", "IsExtension"});
        Descriptors.Descriptor descriptor25 = getDescriptor().getMessageTypes().get(19);
        internal_static_google_protobuf_SourceCodeInfo_descriptor = descriptor25;
        internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor25, new String[]{HttpHeader.LOCATION});
        Descriptors.Descriptor descriptor26 = descriptor25.getNestedTypes().get(0);
        internal_static_google_protobuf_SourceCodeInfo_Location_descriptor = descriptor26;
        internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor26, new String[]{"Path", "Span", "LeadingComments", "TrailingComments", "LeadingDetachedComments"});
        Descriptors.Descriptor descriptor27 = getDescriptor().getMessageTypes().get(20);
        internal_static_google_protobuf_GeneratedCodeInfo_descriptor = descriptor27;
        internal_static_google_protobuf_GeneratedCodeInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor27, new String[]{"Annotation"});
        Descriptors.Descriptor descriptor28 = descriptor27.getNestedTypes().get(0);
        internal_static_google_protobuf_GeneratedCodeInfo_Annotation_descriptor = descriptor28;
        internal_static_google_protobuf_GeneratedCodeInfo_Annotation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor28, new String[]{"Path", "SourceFile", "Begin", "End", "Semantic"});
    }
}
