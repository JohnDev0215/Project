package com.amazon.music.languagepreference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
class AvailableContentLanguageToLanguageConverter {
    AvailableContentLanguageToLanguageConverter() {
    }

    static List<Language> convert(Map<String, Map<String, String>> map) {
        ArrayList arrayList = new ArrayList();
        if (map == null) {
            return arrayList;
        }
        for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
            Map<String, String> value = entry.getValue();
            arrayList.add(new Language(entry.getKey(), value.get("displayName"), value.get("nativeScript"), value.containsKey("rank") ? Integer.parseInt(value.get("rank")) : 0, false));
        }
        Collections.sort(arrayList, new Comparator<Language>() { // from class: com.amazon.music.languagepreference.AvailableContentLanguageToLanguageConverter.1
            @Override // java.util.Comparator
            public int compare(Language language, Language language2) {
                return language.getRank() - language2.getRank();
            }
        });
        return arrayList;
    }
}
