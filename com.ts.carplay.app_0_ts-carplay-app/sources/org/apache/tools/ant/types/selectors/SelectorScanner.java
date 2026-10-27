package org.apache.tools.ant.types.selectors;

/* JADX INFO: loaded from: classes.dex */
public interface SelectorScanner {
    String[] getDeselectedDirectories();

    String[] getDeselectedFiles();

    void setSelectors(FileSelector[] fileSelectorArr);
}
