package lol.hub;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.web.dom.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    @Export
    public static void main() {
        Document document = Window.document();

        StringBuilder n = new StringBuilder();

        for (Node node : nodeList(document.childNodes())) {
            n.append(node.nodeType()).append("\n");
            for (Node child : nodeList(node.childNodes())) {
                n.append("  - ").append(child.nodeType()).append("\n");
            }
        }

        // de.inetsoftware.jwebassembly.web.dom.Document
        // does not allow access to the head element,
        // means this is not possible without workarounds?
        /* HTMLElement title = document.createElement("title");
        title.appendChild(document.createTextNode("JWebAssembly Example"));
        document.head().appendChild(title); */

        HTMLElement div = document.createElement("div");
        div.appendChild(document.createTextNode("Hello World, this text come from WebAssembly. " + n));
        document.body().appendChild(div);

        HTMLElement canvas = document.createElement("canvas");
        document.body().appendChild(canvas);
    }

    public static List<Node> nodeList(NodeList nodeList) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < nodeList.length(); i++) {
            list.add(nodeList.item(i));
        }
        return list;
    }

}
