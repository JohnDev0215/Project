package com.google.protobuf;

import com.google.protobuf.Descriptors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes7.dex */
public class TextFormatParseInfoTree$Builder {
    private Map<Descriptors.FieldDescriptor, List<Object>> locationsFromField = new HashMap();
    private Map<Descriptors.FieldDescriptor, List<TextFormatParseInfoTree$Builder>> subtreeBuildersFromField = new HashMap();

    private TextFormatParseInfoTree$Builder() {
    }
}
