package com.amazon.atv.title;

import com.amazon.atv.title.v1.TitleV1;
import com.amazon.avod.util.json.ParserMapFunction;

/* JADX INFO: loaded from: classes2.dex */
public class ParserConstants {
    public static final ParserMapFunction<Title> TITLE_TYPE_PARSER_MAP_FUNCTION = ParserMapFunction.Builder.newBuilder().addMatch(TitleV1.Parser.class, "version", "1").build();
}
