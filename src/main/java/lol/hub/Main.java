package lol.hub;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.web.dom.HTMLElement;
import de.inetsoftware.jwebassembly.web.dom.Node;
import de.inetsoftware.jwebassembly.web.dom.NodeList;
import de.inetsoftware.jwebassembly.web.dom.Window;

import java.util.ArrayList;
import java.util.List;

public class Main {
    @Export
    public static void main() {
        HTMLElement document = Window.document().documentElement();

        for (Node node : nodeList(document.childNodes())) {
            HTMLElement div1 = Window.document().createElement("div");
            div1.appendChild(Window.document().createTextNode(String.valueOf(node.nodeType())));
            Window.document().body().appendChild(div1);
            Window.document().body().appendChild(Window.document().createElement("br"));
            for (Node child : nodeList(node.childNodes())) {
                HTMLElement div2 = Window.document().createElement("div");
                div2.appendChild(Window.document().createTextNode(">" + child.nodeType()));
                Window.document().body().appendChild(div2);
                Window.document().body().appendChild(Window.document().createElement("br"));
            }
        }

        HTMLElement canvas = Window.document().createElement("canvas");
        Window.document().body().appendChild(canvas);
    }

    public static List<Node> nodeList(NodeList nodeList) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < nodeList.length(); i++) {
            list.add(nodeList.item(i));
        }
        return list;
    }

}
