package lol.hub;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.web.dom.HTMLElement;
import de.inetsoftware.jwebassembly.web.dom.Node;
import de.inetsoftware.jwebassembly.web.dom.Window;

public class Main {

    @Export
    public static void main() {
        walk(Window.document().documentElement(), 0);
        Window.document().body().appendChild(Window.document().createElement("canvas"));
    }

    private static void walk(Node node, int level) {
        HTMLElement div = Window.document().createElement("div");
        div.appendChild(Window.document().createTextNode(prefix(level) + node.nodeType()));
        Window.document().body().appendChild(div);
        Window.document().body().appendChild(Window.document().createElement("br"));
        for (int i = 0; i < node.childNodes().length(); i++) {
            walk(node.childNodes().item(i), level++);
        }
    }

    private static String prefix(int level) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < level; i++) {
            s.append(">");
        }
        return s.toString();
    }

}
