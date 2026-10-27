package com.android.textclassifier;

import android.os.LocaleList;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.android.textclassifier.common.base.TcLog;
import com.android.textclassifier.common.logging.ResultIdUtils;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
final class ModelFileManager {
    private final Supplier<ImmutableList<ModelFile>> modelFileSupplier;

    public ModelFileManager(Supplier<ImmutableList<ModelFile>> supplier) {
        Preconditions.checkNotNull(supplier);
        this.modelFileSupplier = supplier;
    }

    public ImmutableList<ModelFile> listModelFiles() {
        return this.modelFileSupplier.get();
    }

    public ModelFile findBestModelFile(LocaleList localeList) {
        String languageTags;
        if (localeList == null || localeList.isEmpty()) {
            languageTags = LocaleList.getDefault().toLanguageTags();
        } else {
            languageTags = localeList.toLanguageTags();
        }
        List<Locale.LanguageRange> list = Locale.LanguageRange.parse(languageTags);
        ModelFile modelFile = null;
        UnmodifiableIterator<ModelFile> it = listModelFiles().iterator();
        while (it.hasNext()) {
            ModelFile next = it.next();
            if (next.isAnyLanguageSupported(list) && next.isPreferredTo(modelFile)) {
                modelFile = next;
            }
        }
        return modelFile;
    }

    public static final class ModelFileSupplierImpl implements Supplier<ImmutableList<ModelFile>> {
        private final File factoryModelDir;
        private ImmutableList<ModelFile> factoryModels;
        private final Object lock = new Object();
        private final Pattern modelFilenamePattern;
        private final Function<Integer, String> supportedLocalesSupplier;
        private final File updatedModelFile;
        private final Function<Integer, Integer> versionSupplier;

        public ModelFileSupplierImpl(File file, String str, File file2, Function<Integer, Integer> function, Function<Integer, String> function2) {
            Preconditions.checkNotNull(file2);
            this.updatedModelFile = file2;
            Preconditions.checkNotNull(file);
            this.factoryModelDir = file;
            Preconditions.checkNotNull(str);
            this.modelFilenamePattern = Pattern.compile(str);
            Preconditions.checkNotNull(function);
            this.versionSupplier = function;
            Preconditions.checkNotNull(function2);
            this.supportedLocalesSupplier = function2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.function.Supplier
        public ImmutableList<ModelFile> get() {
            ModelFile modelFileCreateModelFile;
            ArrayList arrayList = new ArrayList();
            if (this.updatedModelFile.exists() && (modelFileCreateModelFile = createModelFile(this.updatedModelFile)) != null) {
                arrayList.add(modelFileCreateModelFile);
            }
            synchronized (this.lock) {
                if (this.factoryModels == null) {
                    this.factoryModels = getFactoryModels();
                }
                arrayList.addAll(this.factoryModels);
            }
            return ImmutableList.copyOf((Collection) arrayList);
        }

        private ImmutableList<ModelFile> getFactoryModels() {
            ModelFile modelFileCreateModelFile;
            ArrayList arrayList = new ArrayList();
            if (this.factoryModelDir.exists() && this.factoryModelDir.isDirectory()) {
                for (File file : this.factoryModelDir.listFiles()) {
                    if (this.modelFilenamePattern.matcher(file.getName()).matches() && file.isFile() && (modelFileCreateModelFile = createModelFile(file)) != null) {
                        arrayList.add(modelFileCreateModelFile);
                    }
                }
            }
            return ImmutableList.copyOf((Collection) arrayList);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private ModelFile createModelFile(File file) throws Throwable {
            ParcelFileDescriptor parcelFileDescriptorOpen;
            ParcelFileDescriptor parcelFileDescriptor = 0;
            if (!file.exists()) {
                return null;
            }
            try {
                try {
                    parcelFileDescriptorOpen = ParcelFileDescriptor.open(file, 268435456);
                    if (parcelFileDescriptorOpen == null) {
                        maybeCloseAndLogError(parcelFileDescriptorOpen);
                        return null;
                    }
                    try {
                        int fd = parcelFileDescriptorOpen.getFd();
                        int iIntValue = this.versionSupplier.apply(Integer.valueOf(fd)).intValue();
                        String strApply = this.supportedLocalesSupplier.apply(Integer.valueOf(fd));
                        if (strApply.isEmpty()) {
                            TcLog.d("ModelFileManager", "Ignoring " + file.getAbsolutePath());
                            maybeCloseAndLogError(parcelFileDescriptorOpen);
                            return null;
                        }
                        ArrayList arrayList = new ArrayList();
                        Iterator<String> it = Splitter.on(',').split(strApply).iterator();
                        while (it.hasNext()) {
                            arrayList.add(Locale.forLanguageTag(it.next()));
                        }
                        ModelFile modelFile = new ModelFile(file, iIntValue, arrayList, strApply, "*".equals(strApply));
                        maybeCloseAndLogError(parcelFileDescriptorOpen);
                        return modelFile;
                    } catch (FileNotFoundException e) {
                        e = e;
                        TcLog.e("ModelFileManager", "Failed to find " + file.getAbsolutePath(), e);
                        maybeCloseAndLogError(parcelFileDescriptorOpen);
                        return null;
                    }
                } catch (FileNotFoundException e2) {
                    e = e2;
                    parcelFileDescriptorOpen = null;
                } catch (Throwable th) {
                    th = th;
                    maybeCloseAndLogError(parcelFileDescriptor);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                parcelFileDescriptor = 268435456;
                maybeCloseAndLogError(parcelFileDescriptor);
                throw th;
            }
        }

        private static void maybeCloseAndLogError(ParcelFileDescriptor parcelFileDescriptor) {
            if (parcelFileDescriptor == null) {
                return;
            }
            try {
                parcelFileDescriptor.close();
            } catch (IOException e) {
                TcLog.e("ModelFileManager", "Error closing file.", e);
            }
        }
    }

    public static final class ModelFile {
        private final File file;
        private final boolean languageIndependent;
        private final List<Locale> supportedLocales;
        private final String supportedLocalesStr;
        private final int version;

        public ModelFile(File file, int i, List<Locale> list, String str, boolean z) {
            Preconditions.checkNotNull(file);
            this.file = file;
            this.version = i;
            Preconditions.checkNotNull(list);
            this.supportedLocales = list;
            Preconditions.checkNotNull(str);
            this.supportedLocalesStr = str;
            this.languageIndependent = z;
        }

        public String getPath() {
            return this.file.getAbsolutePath();
        }

        public String getName() {
            return this.file.getName();
        }

        public int getVersion() {
            return this.version;
        }

        public boolean isAnyLanguageSupported(List<Locale.LanguageRange> list) {
            Preconditions.checkNotNull(list);
            return this.languageIndependent || Locale.lookup(list, this.supportedLocales) != null;
        }

        public boolean isPreferredTo(ModelFile modelFile) {
            if (modelFile == null) {
                return true;
            }
            if (this.languageIndependent || !modelFile.languageIndependent) {
                return (!this.languageIndependent || modelFile.languageIndependent) && this.version > modelFile.getVersion();
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(getPath());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ModelFile) {
                return TextUtils.equals(getPath(), ((ModelFile) obj).getPath());
            }
            return false;
        }

        public ResultIdUtils.ModelInfo toModelInfo() {
            return new ResultIdUtils.ModelInfo(getVersion(), this.supportedLocalesStr);
        }

        public String toString() {
            return String.format(Locale.US, "ModelFile { path=%s name=%s version=%d locales=%s }", getPath(), getName(), Integer.valueOf(this.version), this.supportedLocalesStr);
        }

        public static ImmutableList<Optional<ResultIdUtils.ModelInfo>> toModelInfos(Optional<ModelFile>... optionalArr) {
            return (ImmutableList) Arrays.stream(optionalArr).map(new Function() { // from class: com.android.textclassifier.-$$Lambda$ModelFileManager$ModelFile$4IreSFYJfbt_zxltbOb9rbCXkRk
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Optional) obj).transform($$Lambda$2YrDcjRKLOb8HwDLV5g9t6JgsSg.INSTANCE);
                }
            }).collect(Collectors.collectingAndThen(Collectors.toList(), new Function() { // from class: com.android.textclassifier.-$$Lambda$YbiVpFDG9z1kfIe3loPZGRwmp-k
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ImmutableList.copyOf((Collection) obj);
                }
            }));
        }
    }
}
