package net.sourceforge.pinyin4j;

import com.bydux.api.common.tools.util.ListUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
class ChineseToPinyinResource {
    private Properties unicodeToHanyuPinyinTable;

    private static class ChineseToPinyinResourceHolder {
        static final ChineseToPinyinResource theInstance = new ChineseToPinyinResource();

        private ChineseToPinyinResourceHolder() {
        }
    }

    class Field {
        static final String COMMA = ",";
        static final String LEFT_BRACKET = "(";
        static final String RIGHT_BRACKET = ")";

        Field() {
        }
    }

    private ChineseToPinyinResource() {
        this.unicodeToHanyuPinyinTable = null;
        initializeResource();
    }

    private String getHanyuPinyinRecordFromChar(char c2) {
        String property = getUnicodeToHanyuPinyinTable().getProperty(Integer.toHexString(c2).toUpperCase());
        if (isValidRecord(property)) {
            return property;
        }
        return null;
    }

    static ChineseToPinyinResource getInstance() {
        return ChineseToPinyinResourceHolder.theInstance;
    }

    private Properties getUnicodeToHanyuPinyinTable() {
        return this.unicodeToHanyuPinyinTable;
    }

    private void initializeResource() {
        try {
            setUnicodeToHanyuPinyinTable(new Properties());
            getUnicodeToHanyuPinyinTable().load(ResourceHelper.getResourceInputStream("/pinyindb/unicode_to_hanyu_pinyin.txt"));
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    private boolean isValidRecord(String str) {
        return str != null && !str.equals("(none0)") && str.startsWith("(") && str.endsWith(")");
    }

    private void setUnicodeToHanyuPinyinTable(Properties properties) {
        this.unicodeToHanyuPinyinTable = properties;
    }

    String[] getHanyuPinyinStringArray(char c2) {
        String hanyuPinyinRecordFromChar = getHanyuPinyinRecordFromChar(c2);
        if (hanyuPinyinRecordFromChar == null) {
            return null;
        }
        return hanyuPinyinRecordFromChar.substring(hanyuPinyinRecordFromChar.indexOf("(") + 1, hanyuPinyinRecordFromChar.lastIndexOf(")")).split(ListUtils.DEFAULT_JOIN_SEPARATOR);
    }
}
