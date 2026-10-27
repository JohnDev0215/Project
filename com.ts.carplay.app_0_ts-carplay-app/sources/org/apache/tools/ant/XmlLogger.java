package org.apache.tools.ant;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/* JADX INFO: loaded from: classes.dex */
public class XmlLogger implements BuildLogger {
    private static final String BUILD_TAG = "build";
    private static final String ERROR_ATTR = "error";
    private static final String LOCATION_ATTR = "location";
    private static final String MESSAGE_TAG = "message";
    private static final String NAME_ATTR = "name";
    private static final String PRIORITY_ATTR = "priority";
    private static final String STACKTRACE_TAG = "stacktrace";
    private static final String TARGET_TAG = "target";
    private static final String TASK_TAG = "task";
    private static final String TIME_ATTR = "time";
    private static DocumentBuilder builder = getDocumentBuilder();
    private PrintStream outStream;
    private int msgOutputLevel = 4;
    private Document doc = builder.newDocument();
    private Hashtable tasks = new Hashtable();
    private Hashtable targets = new Hashtable();
    private Hashtable threadStacks = new Hashtable();
    private TimedElement buildElement = null;

    /* JADX INFO: renamed from: org.apache.tools.ant.XmlLogger$1, reason: invalid class name */
    class AnonymousClass1 {
    }

    private static DocumentBuilder getDocumentBuilder() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (Exception exc) {
            throw new ExceptionInInitializerError(exc);
        }
    }

    private static class TimedElement {
        private Element element;
        private long startTime;

        private TimedElement() {
        }

        /* synthetic */ TimedElement(AnonymousClass1 x0) {
            this();
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.element.getTagName());
            stringBuffer.append(":");
            stringBuffer.append(this.element.getAttribute("name"));
            return stringBuffer.toString();
        }
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildStarted(BuildEvent event) {
        TimedElement timedElement = new TimedElement(null);
        this.buildElement = timedElement;
        timedElement.startTime = System.currentTimeMillis();
        this.buildElement.element = this.doc.createElement(BUILD_TAG);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00de A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.tools.ant.BuildListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void buildFinished(org.apache.tools.ant.BuildEvent r13) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.XmlLogger.buildFinished(org.apache.tools.ant.BuildEvent):void");
    }

    private Stack getStack() {
        Stack threadStack = (Stack) this.threadStacks.get(Thread.currentThread());
        if (threadStack == null) {
            Stack threadStack2 = new Stack();
            this.threadStacks.put(Thread.currentThread(), threadStack2);
            return threadStack2;
        }
        return threadStack;
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetStarted(BuildEvent event) {
        Target target = event.getTarget();
        TimedElement targetElement = new TimedElement(null);
        targetElement.startTime = System.currentTimeMillis();
        targetElement.element = this.doc.createElement(TARGET_TAG);
        targetElement.element.setAttribute("name", target.getName());
        this.targets.put(target, targetElement);
        getStack().push(targetElement);
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetFinished(BuildEvent event) {
        Target target = event.getTarget();
        TimedElement targetElement = (TimedElement) this.targets.get(target);
        if (targetElement != null) {
            long totalTime = System.currentTimeMillis() - targetElement.startTime;
            targetElement.element.setAttribute(TIME_ATTR, DefaultLogger.formatTime(totalTime));
            TimedElement parentElement = null;
            Stack threadStack = getStack();
            if (!threadStack.empty()) {
                TimedElement poppedStack = (TimedElement) threadStack.pop();
                if (poppedStack != targetElement) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Mismatch - popped element = ");
                    stringBuffer.append(poppedStack);
                    stringBuffer.append(" finished target element = ");
                    stringBuffer.append(targetElement);
                    throw new RuntimeException(stringBuffer.toString());
                }
                if (!threadStack.empty()) {
                    parentElement = (TimedElement) threadStack.peek();
                }
            }
            if (parentElement == null) {
                this.buildElement.element.appendChild(targetElement.element);
            } else {
                parentElement.element.appendChild(targetElement.element);
            }
        }
        this.targets.remove(target);
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskStarted(BuildEvent event) {
        TimedElement taskElement = new TimedElement(null);
        taskElement.startTime = System.currentTimeMillis();
        taskElement.element = this.doc.createElement(TASK_TAG);
        Task task = event.getTask();
        String name = event.getTask().getTaskName();
        if (name == null) {
            name = "";
        }
        taskElement.element.setAttribute("name", name);
        taskElement.element.setAttribute("location", event.getTask().getLocation().toString());
        this.tasks.put(task, taskElement);
        getStack().push(taskElement);
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskFinished(BuildEvent event) {
        TimedElement poppedStack;
        Task task = event.getTask();
        TimedElement taskElement = (TimedElement) this.tasks.get(task);
        if (taskElement != null) {
            long totalTime = System.currentTimeMillis() - taskElement.startTime;
            taskElement.element.setAttribute(TIME_ATTR, DefaultLogger.formatTime(totalTime));
            Target target = task.getOwningTarget();
            TimedElement targetElement = null;
            if (target != null) {
                targetElement = (TimedElement) this.targets.get(target);
            }
            if (targetElement == null) {
                this.buildElement.element.appendChild(taskElement.element);
            } else {
                targetElement.element.appendChild(taskElement.element);
            }
            Stack threadStack = getStack();
            if (!threadStack.empty() && (poppedStack = (TimedElement) threadStack.pop()) != taskElement) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Mismatch - popped element = ");
                stringBuffer.append(poppedStack);
                stringBuffer.append(" finished task element = ");
                stringBuffer.append(taskElement);
                throw new RuntimeException(stringBuffer.toString());
            }
            this.tasks.remove(task);
            return;
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Unknown task ");
        stringBuffer2.append(task);
        stringBuffer2.append(" not in ");
        stringBuffer2.append(this.tasks);
        throw new RuntimeException(stringBuffer2.toString());
    }

    private TimedElement getTaskElement(Task task) {
        TimedElement element = (TimedElement) this.tasks.get(task);
        if (element != null) {
            return element;
        }
        Enumeration e = this.tasks.keys();
        while (e.hasMoreElements()) {
            Task key = (Task) e.nextElement();
            if ((key instanceof UnknownElement) && ((UnknownElement) key).getTask() == task) {
                return (TimedElement) this.tasks.get(key);
            }
        }
        return null;
    }

    @Override // org.apache.tools.ant.BuildListener
    public void messageLogged(BuildEvent event) {
        String name;
        int priority = event.getPriority();
        if (priority > this.msgOutputLevel) {
            return;
        }
        Element messageElement = this.doc.createElement(MESSAGE_TAG);
        int priority2 = event.getPriority();
        if (priority2 == 0) {
            name = ERROR_ATTR;
        } else if (priority2 == 1) {
            name = "warn";
        } else if (priority2 == 2) {
            name = "info";
        } else {
            name = "debug";
        }
        messageElement.setAttribute(PRIORITY_ATTR, name);
        Text messageText = this.doc.createCDATASection(event.getMessage());
        messageElement.appendChild(messageText);
        TimedElement parentElement = null;
        Task task = event.getTask();
        Target target = event.getTarget();
        if (task != null) {
            parentElement = getTaskElement(task);
        }
        if (parentElement == null && target != null) {
            parentElement = (TimedElement) this.targets.get(target);
        }
        if (parentElement != null) {
            parentElement.element.appendChild(messageElement);
        } else {
            this.buildElement.element.appendChild(messageElement);
        }
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setMessageOutputLevel(int level) {
        this.msgOutputLevel = level;
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setOutputPrintStream(PrintStream output) {
        this.outStream = new PrintStream((OutputStream) output, true);
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setEmacsMode(boolean emacsMode) {
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setErrorPrintStream(PrintStream err) {
    }
}
