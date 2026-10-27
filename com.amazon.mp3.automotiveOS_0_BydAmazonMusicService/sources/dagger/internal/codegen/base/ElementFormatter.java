package dagger.internal.codegen.base;

import dagger.internal.codegen.xprocessing.XElements;
import dagger.internal.codegen.xprocessing.XTypes;
import dagger.spi.shaded.androidx.room.compiler.processing.XElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XElementKt;
import dagger.spi.shaded.androidx.room.compiler.processing.XExecutableElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XExecutableParameterElement;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes7.dex */
public final class ElementFormatter extends Formatter<XElement> {
    ElementFormatter() {
    }

    @Override // dagger.internal.codegen.base.Formatter
    public String format(XElement element) {
        return elementToString(element);
    }

    public static String elementToString(XElement element) {
        if (XElements.isExecutable(element)) {
            StringBuilder sbEnclosingTypeAndMemberName = enclosingTypeAndMemberName(element);
            sbEnclosingTypeAndMemberName.append((String) XElements.asExecutable(element).getParameters().stream().map(new Function() { // from class: dagger.internal.codegen.base.ElementFormatter$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ElementFormatter.lambda$elementToString$0((XExecutableParameterElement) obj);
                }
            }).collect(Collectors.joining(", ", "(", ")")));
            return sbEnclosingTypeAndMemberName.toString();
        }
        if (XElementKt.isMethodParameter(element)) {
            XExecutableElement enclosingElement = XElements.asMethodParameter(element).getEnclosingElement();
            StringBuilder sbEnclosingTypeAndMemberName2 = enclosingTypeAndMemberName(enclosingElement);
            sbEnclosingTypeAndMemberName2.append('(');
            sbEnclosingTypeAndMemberName2.append(Formatter.formatArgumentInList(enclosingElement.getParameters().indexOf(element), enclosingElement.getParameters().size(), XElements.getSimpleName(element)));
            sbEnclosingTypeAndMemberName2.append(')');
            return sbEnclosingTypeAndMemberName2.toString();
        }
        if (XElementKt.isField(element)) {
            return enclosingTypeAndMemberName(element).toString();
        }
        if (XElementKt.isTypeElement(element)) {
            return XElements.asTypeElement(element).getQualifiedName();
        }
        throw new UnsupportedOperationException("Can't determine string for element " + element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$elementToString$0(XExecutableParameterElement xExecutableParameterElement) {
        return XTypes.toStableString(xExecutableParameterElement.getType());
    }

    private static StringBuilder enclosingTypeAndMemberName(XElement element) {
        StringBuilder sb = new StringBuilder(elementToString(element.getEnclosingElement()));
        if (!XElements.getSimpleName(element).contentEquals("<init>")) {
            sb.append('.');
            sb.append(XElements.getSimpleName(element));
        }
        return sb;
    }
}
